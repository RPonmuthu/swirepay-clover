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

package com.clover.sdk.v3.customers;

import com.clover.sdk.GenericClient;
import com.clover.sdk.GenericParcelable;

/**
 * This is an auto-generated Clover data object.
 * <p>
 * <h3>Fields</h3>
 * <ul>
 * <li>{@link #getId id}</li>
 * <li>{@link #getFirst6 first6}</li>
 * <li>{@link #getLast4 last4}</li>
 * <li>{@link #getFirstName firstName}</li>
 * <li>{@link #getLastName lastName}</li>
 * <li>{@link #getExpirationDate expirationDate}</li>
 * <li>{@link #getCardType cardType}</li>
 * <li>{@link #getToken token}</li>
 * <li>{@link #getTokenType tokenType}</li>
 * <li>{@link #getModifiedTime modifiedTime}</li>
 * <li>{@link #getCustomer customer}</li>
 * </ul>
 */
@SuppressWarnings("all")
public class Card extends GenericParcelable implements com.clover.sdk.v3.Validator, com.clover.sdk.JSONifiable {

  public java.lang.String getId() {
    return genClient.cacheGet(CacheKey.id);
  }

  public java.lang.String getFirst6() {
    return genClient.cacheGet(CacheKey.first6);
  }

  public java.lang.String getLast4() {
    return genClient.cacheGet(CacheKey.last4);
  }

  public java.lang.String getFirstName() {
    return genClient.cacheGet(CacheKey.firstName);
  }

  public java.lang.String getLastName() {
    return genClient.cacheGet(CacheKey.lastName);
  }

  public java.lang.String getExpirationDate() {
    return genClient.cacheGet(CacheKey.expirationDate);
  }

  public java.lang.String getCardType() {
    return genClient.cacheGet(CacheKey.cardType);
  }

  public java.lang.String getToken() {
    return genClient.cacheGet(CacheKey.token);
  }

  public com.clover.sdk.v3.customers.TokenType getTokenType() {
    return genClient.cacheGet(CacheKey.tokenType);
  }

  /**
   * The timestamp from when this card was last updated.
   */
  public java.lang.Long getModifiedTime() {
    return genClient.cacheGet(CacheKey.modifiedTime);
  }

  /**
   * Customer who this card belongs to.
   */
  public com.clover.sdk.v3.base.Reference getCustomer() {
    return genClient.cacheGet(CacheKey.customer);
  }




  private enum CacheKey implements com.clover.sdk.ExtractionStrategyEnum {
    id
        (com.clover.sdk.extractors.BasicExtractionStrategy.instance(java.lang.String.class)),
    first6
        (com.clover.sdk.extractors.BasicExtractionStrategy.instance(java.lang.String.class)),
    last4
        (com.clover.sdk.extractors.BasicExtractionStrategy.instance(java.lang.String.class)),
    firstName
        (com.clover.sdk.extractors.BasicExtractionStrategy.instance(java.lang.String.class)),
    lastName
        (com.clover.sdk.extractors.BasicExtractionStrategy.instance(java.lang.String.class)),
    expirationDate
        (com.clover.sdk.extractors.BasicExtractionStrategy.instance(java.lang.String.class)),
    cardType
        (com.clover.sdk.extractors.BasicExtractionStrategy.instance(java.lang.String.class)),
    token
        (com.clover.sdk.extractors.BasicExtractionStrategy.instance(java.lang.String.class)),
    tokenType
        (com.clover.sdk.extractors.EnumExtractionStrategy.instance(com.clover.sdk.v3.customers.TokenType.class)),
    modifiedTime
        (com.clover.sdk.extractors.BasicExtractionStrategy.instance(java.lang.Long.class)),
    customer
        (com.clover.sdk.extractors.RecordExtractionStrategy.instance(com.clover.sdk.v3.base.Reference.JSON_CREATOR)),
      ;

    private final com.clover.sdk.extractors.ExtractionStrategy extractionStrategy;

    private CacheKey(com.clover.sdk.extractors.ExtractionStrategy s) {
      extractionStrategy = s;
    }

    @Override
    public com.clover.sdk.extractors.ExtractionStrategy getExtractionStrategy() {
      return extractionStrategy;
    }
  }

  private final GenericClient<Card> genClient;

  /**
   * Constructs a new empty instance.
   */
  public Card() {
    genClient = new GenericClient<Card>(this);
  }

  @Override
  protected GenericClient getGenericClient() {
    return genClient;
  }

  /**
   * Constructs a new empty instance.
   */
  protected Card(boolean noInit) {
    genClient = null;
  }

  /**
   * Constructs a new instance from the given JSON String.
   */
  public Card(String json) throws IllegalArgumentException {
    this();
    genClient.initJsonObject(json);
  }

  /**
   * Construct a new instance backed by the given JSONObject, the parameter is not copied so changes to it will be
   * reflected in this instance and vice-versa.
   */
  public Card(org.json.JSONObject jsonObject) {
    this();
    genClient.setJsonObject(jsonObject);
  }

  /**
   * Constructs a new instance that is a deep copy of the source instance. It does not copy the bundle or changelog.
   */
  public Card(Card src) {
    this();
    if (src.genClient.getJsonObject() != null) {
      genClient.setJsonObject(com.clover.sdk.v3.JsonHelper.deepCopy(src.genClient.getJSONObject()));
    }
  }

  /**
   * Returns the internal JSONObject backing this instance, the return value is not a copy so changes to it will be
   * reflected in this instance and vice-versa.
   */
  public org.json.JSONObject getJSONObject() {
    return genClient.getJSONObject();
  }

  @Override
  public void validate() {

    genClient.validateNotNull(CacheKey.first6, getFirst6());
    genClient.validateLength(CacheKey.first6, getFirst6(), 6);

    genClient.validateNotNull(CacheKey.last4, getLast4());
    genClient.validateLength(CacheKey.last4, getLast4(), 4);

    genClient.validateLength(CacheKey.firstName, getFirstName(), 64);

    genClient.validateLength(CacheKey.lastName, getLastName(), 64);

    genClient.validateLength(CacheKey.expirationDate, getExpirationDate(), 4);

    genClient.validateLength(CacheKey.token, getToken(), 72);
    genClient.validateReferences(CacheKey.customer);
  }

  /** Checks whether the 'id' field is set and is not null */
  public boolean isNotNullId() {
    return genClient.cacheValueIsNotNull(CacheKey.id);
  }

  /** Checks whether the 'first6' field is set and is not null */
  public boolean isNotNullFirst6() {
    return genClient.cacheValueIsNotNull(CacheKey.first6);
  }

  /** Checks whether the 'last4' field is set and is not null */
  public boolean isNotNullLast4() {
    return genClient.cacheValueIsNotNull(CacheKey.last4);
  }

  /** Checks whether the 'firstName' field is set and is not null */
  public boolean isNotNullFirstName() {
    return genClient.cacheValueIsNotNull(CacheKey.firstName);
  }

  /** Checks whether the 'lastName' field is set and is not null */
  public boolean isNotNullLastName() {
    return genClient.cacheValueIsNotNull(CacheKey.lastName);
  }

  /** Checks whether the 'expirationDate' field is set and is not null */
  public boolean isNotNullExpirationDate() {
    return genClient.cacheValueIsNotNull(CacheKey.expirationDate);
  }

  /** Checks whether the 'cardType' field is set and is not null */
  public boolean isNotNullCardType() {
    return genClient.cacheValueIsNotNull(CacheKey.cardType);
  }

  /** Checks whether the 'token' field is set and is not null */
  public boolean isNotNullToken() {
    return genClient.cacheValueIsNotNull(CacheKey.token);
  }

  /** Checks whether the 'tokenType' field is set and is not null */
  public boolean isNotNullTokenType() {
    return genClient.cacheValueIsNotNull(CacheKey.tokenType);
  }

  /** Checks whether the 'modifiedTime' field is set and is not null */
  public boolean isNotNullModifiedTime() {
    return genClient.cacheValueIsNotNull(CacheKey.modifiedTime);
  }

  /** Checks whether the 'customer' field is set and is not null */
  public boolean isNotNullCustomer() {
    return genClient.cacheValueIsNotNull(CacheKey.customer);
  }



  /** Checks whether the 'id' field has been set, however the value could be null */
  public boolean hasId() {
    return genClient.cacheHasKey(CacheKey.id);
  }

  /** Checks whether the 'first6' field has been set, however the value could be null */
  public boolean hasFirst6() {
    return genClient.cacheHasKey(CacheKey.first6);
  }

  /** Checks whether the 'last4' field has been set, however the value could be null */
  public boolean hasLast4() {
    return genClient.cacheHasKey(CacheKey.last4);
  }

  /** Checks whether the 'firstName' field has been set, however the value could be null */
  public boolean hasFirstName() {
    return genClient.cacheHasKey(CacheKey.firstName);
  }

  /** Checks whether the 'lastName' field has been set, however the value could be null */
  public boolean hasLastName() {
    return genClient.cacheHasKey(CacheKey.lastName);
  }

  /** Checks whether the 'expirationDate' field has been set, however the value could be null */
  public boolean hasExpirationDate() {
    return genClient.cacheHasKey(CacheKey.expirationDate);
  }

  /** Checks whether the 'cardType' field has been set, however the value could be null */
  public boolean hasCardType() {
    return genClient.cacheHasKey(CacheKey.cardType);
  }

  /** Checks whether the 'token' field has been set, however the value could be null */
  public boolean hasToken() {
    return genClient.cacheHasKey(CacheKey.token);
  }

  /** Checks whether the 'tokenType' field has been set, however the value could be null */
  public boolean hasTokenType() {
    return genClient.cacheHasKey(CacheKey.tokenType);
  }

  /** Checks whether the 'modifiedTime' field has been set, however the value could be null */
  public boolean hasModifiedTime() {
    return genClient.cacheHasKey(CacheKey.modifiedTime);
  }

  /** Checks whether the 'customer' field has been set, however the value could be null */
  public boolean hasCustomer() {
    return genClient.cacheHasKey(CacheKey.customer);
  }


  /**
   * Sets the field 'id'.
   */
  public Card setId(java.lang.String id) {
    return genClient.setOther(id, CacheKey.id);
  }

  /**
   * Sets the field 'first6'.
   */
  public Card setFirst6(java.lang.String first6) {
    return genClient.setOther(first6, CacheKey.first6);
  }

  /**
   * Sets the field 'last4'.
   */
  public Card setLast4(java.lang.String last4) {
    return genClient.setOther(last4, CacheKey.last4);
  }

  /**
   * Sets the field 'firstName'.
   */
  public Card setFirstName(java.lang.String firstName) {
    return genClient.setOther(firstName, CacheKey.firstName);
  }

  /**
   * Sets the field 'lastName'.
   */
  public Card setLastName(java.lang.String lastName) {
    return genClient.setOther(lastName, CacheKey.lastName);
  }

  /**
   * Sets the field 'expirationDate'.
   */
  public Card setExpirationDate(java.lang.String expirationDate) {
    return genClient.setOther(expirationDate, CacheKey.expirationDate);
  }

  /**
   * Sets the field 'cardType'.
   */
  public Card setCardType(java.lang.String cardType) {
    return genClient.setOther(cardType, CacheKey.cardType);
  }

  /**
   * Sets the field 'token'.
   */
  public Card setToken(java.lang.String token) {
    return genClient.setOther(token, CacheKey.token);
  }

  /**
   * Sets the field 'tokenType'.
   */
  public Card setTokenType(com.clover.sdk.v3.customers.TokenType tokenType) {
    return genClient.setOther(tokenType, CacheKey.tokenType);
  }

  /**
   * Sets the field 'modifiedTime'.
   */
  public Card setModifiedTime(java.lang.Long modifiedTime) {
    return genClient.setOther(modifiedTime, CacheKey.modifiedTime);
  }

  /**
   * Sets the field 'customer'.
   *
   * The parameter is not copied so changes to it will be reflected in this instance and vice-versa.
   */
  public Card setCustomer(com.clover.sdk.v3.base.Reference customer) {
    return genClient.setRecord(customer, CacheKey.customer);
  }


  /** Clears the 'id' field, the 'has' method for this field will now return false */
  public void clearId() {
    genClient.clear(CacheKey.id);
  }
  /** Clears the 'first6' field, the 'has' method for this field will now return false */
  public void clearFirst6() {
    genClient.clear(CacheKey.first6);
  }
  /** Clears the 'last4' field, the 'has' method for this field will now return false */
  public void clearLast4() {
    genClient.clear(CacheKey.last4);
  }
  /** Clears the 'firstName' field, the 'has' method for this field will now return false */
  public void clearFirstName() {
    genClient.clear(CacheKey.firstName);
  }
  /** Clears the 'lastName' field, the 'has' method for this field will now return false */
  public void clearLastName() {
    genClient.clear(CacheKey.lastName);
  }
  /** Clears the 'expirationDate' field, the 'has' method for this field will now return false */
  public void clearExpirationDate() {
    genClient.clear(CacheKey.expirationDate);
  }
  /** Clears the 'cardType' field, the 'has' method for this field will now return false */
  public void clearCardType() {
    genClient.clear(CacheKey.cardType);
  }
  /** Clears the 'token' field, the 'has' method for this field will now return false */
  public void clearToken() {
    genClient.clear(CacheKey.token);
  }
  /** Clears the 'tokenType' field, the 'has' method for this field will now return false */
  public void clearTokenType() {
    genClient.clear(CacheKey.tokenType);
  }
  /** Clears the 'modifiedTime' field, the 'has' method for this field will now return false */
  public void clearModifiedTime() {
    genClient.clear(CacheKey.modifiedTime);
  }
  /** Clears the 'customer' field, the 'has' method for this field will now return false */
  public void clearCustomer() {
    genClient.clear(CacheKey.customer);
  }


  /**
   * Returns true if this instance has any changes.
   */
  public boolean containsChanges() {
    return genClient.containsChanges();
  }

  /**
   * Reset the log of changes made to this instance, calling copyChanges() after this would return an empty instance.
   */
  public void resetChangeLog() {
    genClient.resetChangeLog();
  }

  /**
   * Create a copy of this instance that contains only fields that were set after the constructor was called.
   */
  public Card copyChanges() {
    Card copy = new Card();
    copy.mergeChanges(this);
    copy.resetChangeLog();
    return copy;
  }

  /**
   * Copy all the changed fields from the given source to this instance.
   */
  public void mergeChanges(Card src) {
    if (src.genClient.getChangeLog() != null) {
      genClient.mergeChanges(new Card(src).getJSONObject(), src.genClient);
    }
  }

  public static final android.os.Parcelable.Creator<Card> CREATOR = new android.os.Parcelable.Creator<Card>() {
    @Override
    public Card createFromParcel(android.os.Parcel in) {
      Card instance = new Card(com.clover.sdk.v3.JsonParcelHelper.ObjectWrapper.CREATOR.createFromParcel(in).unwrap());
      instance.genClient.setBundle(in.readBundle(getClass().getClassLoader()));
      instance.genClient.setChangeLog(in.readBundle());
      return instance;
    }

    @Override
    public Card[] newArray(int size) {
      return new Card[size];
    }
  };

  public static final com.clover.sdk.JSONifiable.Creator<Card> JSON_CREATOR = new com.clover.sdk.JSONifiable.Creator<Card>() {
    public Class<Card> getCreatedClass() {
      return Card.class;
    }

    @Override
    public Card create(org.json.JSONObject jsonObject) {
      return new Card(jsonObject);
    }
  };

  public interface Constraints {
    public static final boolean ID_IS_REQUIRED = false;
    public static final boolean FIRST6_IS_REQUIRED = true;
    public static final long FIRST6_MAX_LEN = 6;
    public static final boolean LAST4_IS_REQUIRED = true;
    public static final long LAST4_MAX_LEN = 4;
    public static final boolean FIRSTNAME_IS_REQUIRED = false;
    public static final long FIRSTNAME_MAX_LEN = 64;
    public static final boolean LASTNAME_IS_REQUIRED = false;
    public static final long LASTNAME_MAX_LEN = 64;
    public static final boolean EXPIRATIONDATE_IS_REQUIRED = false;
    public static final long EXPIRATIONDATE_MAX_LEN = 4;
    public static final boolean CARDTYPE_IS_REQUIRED = false;
    public static final boolean TOKEN_IS_REQUIRED = false;
    public static final long TOKEN_MAX_LEN = 72;
    public static final boolean TOKENTYPE_IS_REQUIRED = false;
    public static final boolean MODIFIEDTIME_IS_REQUIRED = false;
    public static final boolean CUSTOMER_IS_REQUIRED = false;
  }

}
