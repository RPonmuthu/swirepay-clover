/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */


/*
 * Copyright (C) 2019 Clover Network, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.clover.sdk.v3.payments;

import android.os.Parcelable;
import android.os.Parcel;

/**
 * This is an auto-generated Clover data enum.
 */
@SuppressWarnings("all")
public enum TerminalManagementComponentType implements Parcelable {
  ACQUIRER_PROTOCOL_PARAMS, APPLICATION_PARAMS, TERMINAL_PARAMS, SECURITY_PARAMS, SERVER, TERMINAL, DEVICE, SECURE_MODULE, PAYMENT_APPLICATION, EMV_KERNEL, EMV_LEVEL1, MIDDLEWARE, DRIVER, OPERATING_SYSTEM, MERCHANT_PARAMS, CERTIFICATE_PARAMS, TMS_PROTOCOL_PARAMS;

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(final Parcel dest, final int flags) {
    dest.writeString(name());
  }

  public static final Creator<TerminalManagementComponentType> CREATOR = new Creator<TerminalManagementComponentType>() {
    @Override
    public TerminalManagementComponentType createFromParcel(final Parcel source) {
      return TerminalManagementComponentType.valueOf(source.readString());
    }

    @Override
    public TerminalManagementComponentType[] newArray(final int size) {
      return new TerminalManagementComponentType[size];
    }
  };
}
