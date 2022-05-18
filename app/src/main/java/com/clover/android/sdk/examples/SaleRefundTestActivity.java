package com.clover.android.sdk.examples;

import com.clover.connector.sdk.v3.PaymentConnector;
import com.clover.sdk.util.CloverAccount;
import com.clover.sdk.v1.BindingException;
import com.clover.sdk.v1.ClientException;
import com.clover.sdk.v1.Intents;
import com.clover.sdk.v1.ServiceException;
import com.clover.sdk.v1.merchant.Merchant;
import com.clover.sdk.v3.connector.ExternalIdUtils;
import com.clover.sdk.v3.connector.IPaymentConnector;
import com.clover.sdk.v3.connector.IPaymentConnectorListener;
import com.clover.sdk.v3.inventory.TaxRate;
import com.clover.sdk.v3.order.LineItem;
import com.clover.sdk.v3.order.Order;
import com.clover.sdk.v3.order.OrderConnector;
import com.clover.sdk.v3.payments.DataEntryLocation;
import com.clover.sdk.v3.payments.TipMode;
import com.clover.sdk.v3.remotepay.AuthRequest;
import com.clover.sdk.v3.remotepay.AuthResponse;
import com.clover.sdk.v3.remotepay.CapturePreAuthResponse;
import com.clover.sdk.v3.remotepay.CloseoutRequest;
import com.clover.sdk.v3.remotepay.CloseoutResponse;
import com.clover.sdk.v3.remotepay.ConfirmPaymentRequest;
import com.clover.sdk.v3.remotepay.ManualRefundResponse;
import com.clover.sdk.v3.remotepay.PreAuthRequest;
import com.clover.sdk.v3.remotepay.PreAuthResponse;
import com.clover.sdk.v3.remotepay.ReadCardDataResponse;
import com.clover.sdk.v3.remotepay.RefundPaymentRequest;
import com.clover.sdk.v3.remotepay.RefundPaymentResponse;
import com.clover.sdk.v3.remotepay.RetrievePaymentResponse;
import com.clover.sdk.v3.remotepay.RetrievePendingPaymentsResponse;
import com.clover.sdk.v3.remotepay.SaleRequest;
import com.clover.sdk.v3.remotepay.SaleResponse;
import com.clover.sdk.v3.remotepay.TipAdded;
import com.clover.sdk.v3.remotepay.TipAdjustAuthRequest;
import com.clover.sdk.v3.remotepay.TipAdjustAuthResponse;
import com.clover.sdk.v3.remotepay.TransactionType;
import com.clover.sdk.v3.remotepay.VaultCardResponse;
import com.clover.sdk.v3.remotepay.VerifySignatureRequest;
import com.clover.sdk.v3.remotepay.VoidPaymentRefundResponse;
import com.clover.sdk.v3.remotepay.VoidPaymentRequest;
import com.clover.sdk.v3.remotepay.VoidPaymentResponse;

import android.accounts.Account;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * This test activity demonstrates use of the {@link com.clover.sdk.v1.Intents#ACTION_CLOVER_PAY}
 * intent for making a sale or a refund.
 */
public class SaleRefundTestActivity extends AppCompatActivity {

    private static final String TAG = SaleRefundTestActivity.class.getSimpleName();

    private static final int REQ_CODE_CLOVER_PAY = 101;

    private long AMOUNT = 100L;

    private TextView tvResult;
    private TextView tvResultTitle;
    private TextView tvAmount;

    private final Executor bgExecutor = Executors.newSingleThreadExecutor();

    private Merchant merchant;
    private CurrencyUtils currencyUtils;
    private OrderConnector orderConnector;
    private PaymentConnector paymentConnector;

    private static final SecureRandom random = new SecureRandom();
    private static final char[] vals = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'}; // Crockford's base 32 chars

    private static final int KIOSK_CARD_ENTRY_METHODS = 1 << 15;

    public static final int CARD_ENTRY_METHOD_MAG_STRIPE = 0b0001 | 0b0001_00000000 | KIOSK_CARD_ENTRY_METHODS; // 33026
    public static final int CARD_ENTRY_METHOD_ICC_CONTACT = 0b0010 | 0b0010_00000000 | KIOSK_CARD_ENTRY_METHODS; // 33282
    public static final int CARD_ENTRY_METHOD_NFC_CONTACTLESS = 0b0100 | 0b0100_00000000 | KIOSK_CARD_ENTRY_METHODS; // 33796
    public static final int CARD_ENTRY_METHOD_MANUAL = 0b1000 | 0b1000_00000000 | KIOSK_CARD_ENTRY_METHODS; // 34824

    private int cardEntryMethods = CARD_ENTRY_METHOD_MAG_STRIPE | CARD_ENTRY_METHOD_NFC_CONTACTLESS | CARD_ENTRY_METHOD_ICC_CONTACT | CARD_ENTRY_METHOD_MANUAL;

    private long total;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();

        setContentView(R.layout.activity_salerefund_test);

        tvResult = findViewById(R.id.text_result);
        tvResultTitle = findViewById(R.id.text_result_title);
        tvAmount = findViewById(R.id.text_amount);

        paymentConnector = initializePaymentConnector();

        bgExecutor.execute(() -> {
            try {
                merchant = Utils.fetchMerchantBlocking(ctx);
            } catch (IOException e) {
                e.printStackTrace();
                SaleRefundTestActivity.this.finish();
                return;
            }

            runOnUiThread(() -> {
                currencyUtils = new CurrencyUtils(ctx, merchant);
                orderConnector = new OrderConnector(ctx, CloverAccount.getAccount(ctx), null);

                tvAmount.setText(currencyUtils.longToAmountString(AMOUNT));

                findViewById(R.id.btn_sale).setOnClickListener((view) -> {
                    bgExecutor.execute(() -> startPayment(AMOUNT));
                });

                findViewById(R.id.btn_refund).setOnClickListener((view) -> {
                    bgExecutor.execute(this::refundPayment);
                });

                findViewById(R.id.btn_void).setOnClickListener((view) -> {
                    bgExecutor.execute(() -> voidPayment("", ""));
                });

                findViewById(R.id.btn_tipAuth).setOnClickListener((view) -> {
                    bgExecutor.execute(this::tipAdjustAuth);
                });

                findViewById(R.id.btn_closeOut).setOnClickListener((view) -> {
                    bgExecutor.execute(this::closeout);
                });
            });
        });
    }

    private PaymentConnector initializePaymentConnector() {
        // Get the Clover account that will be used with the service; uses the GET_ACCOUNTS permission
        Account cloverAccount = CloverAccount.getAccount(this);
        // Set your RAID as the remoteApplicationId
        String remoteApplicationId = "ZMRB7JXRRS082.08Z76Y29VR04J";

        //Implement the interface
        IPaymentConnectorListener paymentConnectorListener = new IPaymentConnectorListener() {
            @Override
            public void onDeviceDisconnected() {

            }

            @Override
            public void onDeviceConnected() {

            }

            @Override
            public void onPreAuthResponse(PreAuthResponse response) {

                String result;
                Log.e("Response", response.toString());
                if (response.getSuccess()) {
                    result = "PreAuth was successful";
                } else {
                    result = "PreAuth was unsuccessful" + response.getReason() + ":" + response.getMessage();
                }
                Toast.makeText(getApplication().getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthResponse(AuthResponse response) {

                String result;
                Log.e("Response", response.toString());
                if (response.getSuccess()) {
                    result = "Auth was successful";
                } else {
                    result = "Auth was unsuccessful" + response.getReason() + ":" + response.getMessage();
                }
                Toast.makeText(getApplication().getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTipAdjustAuthResponse(TipAdjustAuthResponse response) {

                String result;
                Log.e("Response", response.toString());
                if (response.getSuccess()) {
                    result = "Tip Auth was successful";
                } else {
                    result = "Tip Auth was unsuccessful" + response.getReason() + ":" + response.getMessage();
                }
                Toast.makeText(getApplication().getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCapturePreAuthResponse(CapturePreAuthResponse response) {

            }

            @Override
            public void onVerifySignatureRequest(VerifySignatureRequest request) {

            }

            @Override
            public void onConfirmPaymentRequest(ConfirmPaymentRequest request) {

            }

            @Override
            public void onSaleResponse(SaleResponse response) {

                Long amount = response.getPayment().getAmount();

                if (amount < total) {
//                    startPayment(amount);
                    voidPayment(response.getPayment().getId(), response.getPayment().getOrder().getId());
                }

                String result;
                Log.e("Response", response.toString());
                if (response.getSuccess()) {
                    result = "Sale was successful";
                } else {
                    result = "Sale was unsuccessful" + response.getReason() + ":" + response.getMessage();
                }
                Toast.makeText(getApplication().getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onManualRefundResponse(ManualRefundResponse response) {

                String result;
                Log.e("Response", response.toString());
                if (response.getSuccess()) {
                    result = "Refund was successful";
                } else {
                    result = "Refund was unsuccessful" + response.getReason() + ":" + response.getMessage();
                }
                Toast.makeText(getApplication().getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRefundPaymentResponse(RefundPaymentResponse response) {

                dismissProgress();

                String result;
                Log.e("Response", response.toString());
                if (response.getSuccess()) {
                    result = "Refund was successful";
                } else {
                    result = "Refund was unsuccessful" + response.getReason() + ":" + response.getMessage();
                }
                Toast.makeText(getApplication().getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTipAdded(TipAdded tipAdded) {

            }

            @Override
            public void onVoidPaymentResponse(VoidPaymentResponse response) {

                dismissProgress();

                String result;
                Log.e("Response", response.toString());
                if (response.getSuccess()) {
                    result = "Void was successful";
                } else {
                    result = "Void was unsuccessful" + response.getReason() + ":" + response.getMessage();
                }
                Toast.makeText(getApplication().getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onVaultCardResponse(VaultCardResponse response) {

            }

            @Override
            public void onRetrievePendingPaymentsResponse(RetrievePendingPaymentsResponse retrievePendingPaymentResponse) {

            }

            @Override
            public void onReadCardDataResponse(ReadCardDataResponse response) {

            }

            @Override
            public void onCloseoutResponse(CloseoutResponse response) {

                dismissProgress();

                String result;
                Log.e("Response", response.toString());
                if (response.getSuccess()) {
                    result = "Closeout was successful";
                } else {
                    result = " Closeout was unsuccessful" + response.getReason() + ":" + response.getMessage();
                }
                Toast.makeText(getApplication().getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRetrievePaymentResponse(RetrievePaymentResponse response) {
                Log.e("Payment Response", response.toString());
            }

            @Override
            public void onVoidPaymentRefundResponse(VoidPaymentRefundResponse response) {

                String result;
                Log.e("Response", response.toString());
                if (response.getSuccess()) {
                    result = "Void Refund was successful";
                } else {
                    result = "Void Refund was unsuccessful" + response.getReason() + ":" + response.getMessage();
                }
                Toast.makeText(getApplication().getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        };

        // Implement the other IPaymentConnector listener methods

        // Create the PaymentConnector with the context, account, listener, and RAID
        return new PaymentConnector(this, cloverAccount, paymentConnectorListener, remoteApplicationId);
    }

//    private void startPayment(long amount) {
//        try {
//            boolean taxable = true;
//            LineItem customLineItem = new LineItem();
//            customLineItem.setName("Manual Transaction");
//            customLineItem.setPrice(amount);
//
//            Order order = new Order().setManualTransaction(true);
//            order = orderConnector.createOrder(order);
//            customLineItem = orderConnector.addCustomLineItem(order.getId(), customLineItem, taxable);
//            order = orderConnector.getOrder(order.getId());
//
//            startPayment(100L);
//        } catch (RemoteException | ClientException | ServiceException | BindingException e) {
//            Log.w(TAG, e);
//        }
//    }

    private void startPayment(long amount) {

        String orderId = null;
        String serviceId = getNextId();
        Order order = null;
        TaxRate taxRate = null;
        try {

            ArrayList<LineItem> lineItems = new ArrayList<>();

            LineItem customLineItem = new LineItem();
            customLineItem.setName("LineItem 1");
            customLineItem.setPrice(300L);

            taxRate = new TaxRate();
            taxRate.setIsDefault(false);
            taxRate.setName("Service");
            taxRate.setRate(700000L);
            List<TaxRate> taxRates = new ArrayList<>();
            taxRates.add(taxRate);
            customLineItem.setTaxRates(taxRates);

            lineItems.add(customLineItem);

            order = new Order().setGroupLineItems(true);
            order = orderConnector.createOrder(order);
            order.setLineItems(lineItems);
            orderId = order.getId();
            orderConnector.addCustomLineItem(orderId, customLineItem, true);
//            orderConnector.getOrder(orderId);

        } catch (RemoteException | ClientException | ServiceException | BindingException e) {
            Log.e(TAG, e.getMessage());
        }

        String externalId = getNextId();
        Log.e("externalId", externalId);
        Log.e("orderId", orderId);
        Log.e("serviceId", serviceId);

////    Intent intent = new Intent(Intents.ACTION_SECURE_PAY);
//        Intent intent = new Intent(Intents.ACTION_CLOVER_PAY);
//        intent.putExtra(Intents.EXTRA_ORDER_ID, orderId);
////        intent.putExtra(Intents.EXTRA_PAYMENT_ID, externalId);
////        intent.putExtra(Intents.EXTRA_TAXABLE_AMOUNTS, taxRate);
//        intent.putExtra(Intents.EXTRA_AMOUNT, 10L);
//        intent.putExtra(Intents.EXTRA_DISABLE_CASHBACK, false);
//        intent.putExtra(Intents.EXTRA_CASHBACK_AMOUNT, 5L);
//        if (order.getTotal() < 0) {
//            intent.putExtra(Intents.EXTRA_TRANSACTION_TYPE, Intents.TRANSACTION_TYPE_PAYMENT);
//        }
//        startActivityForResult(intent, REQ_CODE_CLOVER_PAY);

        total = amount;

        long totalAmount = (long) (300L + (300L * 0.07));
        long taxAmount = (long) (300L * 0.07);

        SaleRequest saleRequest = new SaleRequest();
        saleRequest.setCardEntryMethods(cardEntryMethods);
        saleRequest.setExternalId(externalId);
        saleRequest.setDisableCashback(false);
        saleRequest.setTaxAmount(taxAmount);
        saleRequest.setDisablePrinting(true);
        saleRequest.setDisableReceiptSelection(true);
        saleRequest.setOrderId(orderId);
        saleRequest.setAmount(totalAmount);

        paymentConnector.sale(saleRequest);
    }

    private void tipAdjustAuth() {

//        TipAdjustAuthRequest tipRequest = new TipAdjustAuthRequest();
//        tipRequest.setPaymentId("A5KN7GNXJPJDE");
//        tipRequest.setOrderId("WHQ8WRMS85V72");
//        tipRequest.setTipAmount(50L);
//
//        paymentConnector.tipAdjustAuth(tipRequest);

        PreAuthRequest request = new PreAuthRequest();
        request.setAmount(100L);
        request.setExternalId(getNextId());
        request.setCardEntryMethods(cardEntryMethods);
//        request.setAllowOfflinePayment(false);
//        request.setForceOfflinePayment(false);
//        request.setApproveOfflinePaymentWithoutPrompt(false);
//        request.setTippableAmount(15L);
//        request.setTaxAmount(0L);
        request.setDisablePrinting(false);
//        request.setSignatureEntryLocation(DataEntryLocation.ON_SCREEN);
//        request.setSignatureThreshold(3000L);
        request.setDisableReceiptSelection(false);
        request.setDisableDuplicateChecking(false);
        request.setAutoAcceptPaymentConfirmations(false);
//        request.setAutoAcceptSignature(false);
        request.setDisableRestartTransactionOnFail(false);

        paymentConnector.preAuth(request);
    }

    private void refundPayment() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                new AlertDialog.Builder(SaleRefundTestActivity.this)
                        .setTitle("Refund?")
                        .setMessage("Are you sure you want to issue a refund?")
                        .setPositiveButton("Refund", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                showProgress();

                                RefundPaymentRequest refund = new RefundPaymentRequest();
                                refund.setPaymentId("RW0B7T3VGR992");
                                refund.setOrderId("QBCP6K482HCHW");
                                refund.setFullRefund(true);
                                refund.setAmount(100L);
                                paymentConnector.refundPayment(refund);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });
    }

    private void voidPayment(String paymentId, String orderId) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                new AlertDialog.Builder(SaleRefundTestActivity.this)
                        .setTitle("Void?")
                        .setMessage("Are you sure you want to void a payment?")
                        .setPositiveButton("Void", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                showProgress();

                                VoidPaymentRequest vpr = new VoidPaymentRequest();
                                vpr.setPaymentId(paymentId);
                                vpr.setOrderId(orderId);
                                vpr.setDisablePrinting(false);
                                vpr.setVoidReason("USER_CANCEL");

                                paymentConnector.voidPayment(vpr);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });
    }

    private void closeout() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(SaleRefundTestActivity.this)
                        .setTitle("Closeout?")
                        .setMessage("Are you sure you want to closeout batch?")
                        .setPositiveButton("CloseOut", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                showProgress();

                                CloseoutRequest request = new CloseoutRequest();
                                request.setRequestId(getNextId());
                                request.setAllowOpenTabs(true);
                                request.setVersion(1);

                                paymentConnector.closeout(request);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });
    }

    public void showProgress() {
        progressDialog = new ProgressDialog(SaleRefundTestActivity.this);
        progressDialog.setMessage("Processing...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismissProgress() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public static String getNextId() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            int idx = random.nextInt(vals.length);
            sb.append(vals[idx]);
        }
        return sb.toString();
    }

    private Order getOrder(String orderId) {
        try {
            return orderConnector.getOrder(orderId);
        } catch (RemoteException | ClientException | ServiceException | BindingException e) {
            Log.w(TAG, e);
        }
        return null;
    }

    private void deleteOrder(String orderId) {
        try {
            orderConnector.deleteOrder(orderId);
        } catch (RemoteException | ClientException | ServiceException | BindingException e) {
            Log.w(TAG, e);
        }
    }

    private void setResultTextSafe(String result) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            setResultText(result);
        } else {
            runOnUiThread(() -> setResultText(result));
        }
    }

    private void setResultText(String result) {
        if (TextUtils.isEmpty(result)) {
            tvResultTitle.setText("");
        } else {
            tvResultTitle.setText("Result");
        }
        tvResult.setText(result);

        Log.e("Result--", result);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        setResultTextSafe("");

        if (requestCode != REQ_CODE_CLOVER_PAY) {
            return;
        }

        bgExecutor.execute(() -> {
            String orderId = data == null ? null : data.getStringExtra(Intents.EXTRA_ORDER_ID);
            if (TextUtils.isEmpty(orderId)) {
                setResultTextSafe(String.format("Pay failed, missing order id\n\n%s", Utils.intentToString(data)));
                return;
            }

            Order order = getOrder(orderId);
            if (order == null) {
                setResultTextSafe(String.format("Pay failed, order not found\n\n%s", Utils.intentToString(data)));
                return;
            }

            if (resultCode != RESULT_OK) {
                deleteOrder(orderId);
                setResultTextSafe(String.format("Pay failed\n\n%s", order));
                return;
            }

            if (OrderUtils.isFullyPaid(order)) {
                setResultTextSafe(String.format("Complete payment\n\n%s", order));
            } else {
                setResultTextSafe(String.format("Partial payment\n\n%s", order));
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (orderConnector != null) {
            orderConnector.disconnect();
            orderConnector = null;
        }

        super.onDestroy();
    }
}
