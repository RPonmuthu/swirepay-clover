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

package com.clover.sdk.v3.onlineorder;

import com.clover.sdk.GenericClient;
import com.clover.sdk.GenericParcelable;

/**
 * This is an auto-generated Clover data object.
 * <p>
 * <h3>Fields</h3>
 * <ul>
 * <li>{@link #getId id}</li>
 * <li>{@link #getServiceType serviceType}</li>
 * <li>{@link #getProvider provider}</li>
 * <li>{@link #getCreatedTime createdTime}</li>
 * <li>{@link #getModifiedTime modifiedTime}</li>
 * <li>{@link #getDeletedTime deletedTime}</li>
 * </ul>
 */
@SuppressWarnings("all")
public class OnlineOrderProviderService extends GenericParcelable implements com.clover.sdk.v3.Validator, com.clover.sdk.JSONifiable {

  /**
   * Unique identifier
   */
  public java.lang.String getId() {
    return genClient.cacheGet(CacheKey.id);
  }

  public com.clover.sdk.v3.onlineorder.ServiceType getServiceType() {
    return genClient.cacheGet(CacheKey.serviceType);
  }

  /**
   * Reference to the provider
   */
  public com.clover.sdk.v3.base.Reference getProvider() {
    return genClient.cacheGet(CacheKey.provider);
  }

  /**
   * Timestamp when the online ordering provider service was created
   */
  public java.lang.Long getCreatedTime() {
    return genClient.cacheGet(CacheKey.createdTime);
  }

  /**
   * Timestamp when the online ordering provider service was last modified
   */
  public java.lang.Long getModifiedTime() {
    return genClient.cacheGet(CacheKey.modifiedTime);
  }

  /**
   * Timestamp when online ordering provider service was last deleted
   */
  public java.lang.Long getDeletedTime() {
    return genClient.cacheGet(CacheKey.deletedTime);
  }




  private enum CacheKey implements com.clover.sdk.ExtractionStrategyEnum {
    id
        (com.clover.sdk.extractors.BasicExtractionStrategy.instance(java.lang.String.class)),
    serviceType
        (com.clover.sdk.extractors.EnumExtractionStrategy.instance(com.clover.sdk.v3.onlineorder.ServiceType.class)),
    provider
        (com.clover.sdk.extractors.RecordExtractionStrategy.instance(com.clover.sdk.v3.base.Reference.JSON_CREATOR)),
    createdTime
        (com.clover.sdk.extractors.BasicExtractionStrategy.instance(java.lang.Long.class)),
    modifiedTime
        (com.clover.sdk.extractors.BasicExtractionStrategy.instance(java.lang.Long.class)),
    deletedTime
        (com.clover.sdk.extractors.BasicExtractionStrategy.instance(java.lang.Long.class)),
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

  private final GenericClient<OnlineOrderProviderService> genClient;

  /**
   * Constructs a new empty instance.
   */
  public OnlineOrderProviderService() {
    genClient = new GenericClient<OnlineOrderProviderService>(this);
  }

  @Override
  protected GenericClient getGenericClient() {
    return genClient;
  }

  /**
   * Constructs a new empty instance.
   */
  protected OnlineOrderProviderService(boolean noInit) {
    genClient = null;
  }

  /**
   * Constructs a new instance from the given JSON String.
   */
  public OnlineOrderProviderService(String json) throws IllegalArgumentException {
    this();
    genClient.initJsonObject(json);
  }

  /**
   * Construct a new instance backed by the given JSONObject, the parameter is not copied so changes to it will be
   * reflected in this instance and vice-versa.
   */
  public OnlineOrderProviderService(org.json.JSONObject jsonObject) {
    this();
    genClient.setJsonObject(jsonObject);
  }

  /**
   * Constructs a new instance that is a deep copy of the source instance. It does not copy the bundle or changelog.
   */
  public OnlineOrderProviderService(OnlineOrderProviderService src) {
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
    genClient.validateCloverId(CacheKey.id, getId());

    genClient.validateNotNull(CacheKey.serviceType, getServiceType());

    genClient.validateNotNull(CacheKey.provider, getProvider());
    genClient.validateReferences(CacheKey.provider);
  }

  /** Checks whether the 'id' field is set and is not null */
  public boolean isNotNullId() {
    return genClient.cacheValueIsNotNull(CacheKey.id);
  }

  /** Checks whether the 'serviceType' field is set and is not null */
  public boolean isNotNullServiceType() {
    return genClient.cacheValueIsNotNull(CacheKey.serviceType);
  }

  /** Checks whether the 'provider' field is set and is not null */
  public boolean isNotNullProvider() {
    return genClient.cacheValueIsNotNull(CacheKey.provider);
  }

  /** Checks whether the 'createdTime' field is set and is not null */
  public boolean isNotNullCreatedTime() {
    return genClient.cacheValueIsNotNull(CacheKey.createdTime);
  }

  /** Checks whether the 'modifiedTime' field is set and is not null */
  public boolean isNotNullModifiedTime() {
    return genClient.cacheValueIsNotNull(CacheKey.modifiedTime);
  }

  /** Checks whether the 'deletedTime' field is set and is not null */
  public boolean isNotNullDeletedTime() {
    return genClient.cacheValueIsNotNull(CacheKey.deletedTime);
  }



  /** Checks whether the 'id' field has been set, however the value could be null */
  public boolean hasId() {
    return genClient.cacheHasKey(CacheKey.id);
  }

  /** Checks whether the 'serviceType' field has been set, however the value could be null */
  public boolean hasServiceType() {
    return genClient.cacheHasKey(CacheKey.serviceType);
  }

  /** Checks whether the 'provider' field has been set, however the value could be null */
  public boolean hasProvider() {
    return genClient.cacheHasKey(CacheKey.provider);
  }

  /** Checks whether the 'createdTime' field has been set, however the value could be null */
  public boolean hasCreatedTime() {
    return genClient.cacheHasKey(CacheKey.createdTime);
  }

  /** Checks whether the 'modifiedTime' field has been set, however the value could be null */
  public boolean hasModifiedTime() {
    return genClient.cacheHasKey(CacheKey.modifiedTime);
  }

  /** Checks whether the 'deletedTime' field has been set, however the value could be null */
  public boolean hasDeletedTime() {
    return genClient.cacheHasKey(CacheKey.deletedTime);
  }


  /**
   * Sets the field 'id'.
   */
  public OnlineOrderProviderService setId(java.lang.String id) {
    return genClient.setOther(id, CacheKey.id);
  }

  /**
   * Sets the field 'serviceType'.
   */
  public OnlineOrderProviderService setServiceType(com.clover.sdk.v3.onlineorder.ServiceType serviceType) {
    return genClient.setOther(serviceType, CacheKey.serviceType);
  }

  /**
   * Sets the field 'provider'.
   *
   * The parameter is not copied so changes to it will be reflected in this instance and vice-versa.
   */
  public OnlineOrderProviderService setProvider(com.clover.sdk.v3.base.Reference provider) {
    return genClient.setRecord(provider, CacheKey.provider);
  }

  /**
   * Sets the field 'createdTime'.
   */
  public OnlineOrderProviderService setCreatedTime(java.lang.Long createdTime) {
    return genClient.setOther(createdTime, CacheKey.createdTime);
  }

  /**
   * Sets the field 'modifiedTime'.
   */
  public OnlineOrderProviderService setModifiedTime(java.lang.Long modifiedTime) {
    return genClient.setOther(modifiedTime, CacheKey.modifiedTime);
  }

  /**
   * Sets the field 'deletedTime'.
   */
  public OnlineOrderProviderService setDeletedTime(java.lang.Long deletedTime) {
    return genClient.setOther(deletedTime, CacheKey.deletedTime);
  }


  /** Clears the 'id' field, the 'has' method for this field will now return false */
  public void clearId() {
    genClient.clear(CacheKey.id);
  }
  /** Clears the 'serviceType' field, the 'has' method for this field will now return false */
  public void clearServiceType() {
    genClient.clear(CacheKey.serviceType);
  }
  /** Clears the 'provider' field, the 'has' method for this field will now return false */
  public void clearProvider() {
    genClient.clear(CacheKey.provider);
  }
  /** Clears the 'createdTime' field, the 'has' method for this field will now return false */
  public void clearCreatedTime() {
    genClient.clear(CacheKey.createdTime);
  }
  /** Clears the 'modifiedTime' field, the 'has' method for this field will now return false */
  public void clearModifiedTime() {
    genClient.clear(CacheKey.modifiedTime);
  }
  /** Clears the 'deletedTime' field, the 'has' method for this field will now return false */
  public void clearDeletedTime() {
    genClient.clear(CacheKey.deletedTime);
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
  public OnlineOrderProviderService copyChanges() {
    OnlineOrderProviderService copy = new OnlineOrderProviderService();
    copy.mergeChanges(this);
    copy.resetChangeLog();
    return copy;
  }

  /**
   * Copy all the changed fields from the given source to this instance.
   */
  public void mergeChanges(OnlineOrderProviderService src) {
    if (src.genClient.getChangeLog() != null) {
      genClient.mergeChanges(new OnlineOrderProviderService(src).getJSONObject(), src.genClient);
    }
  }

  public static final android.os.Parcelable.Creator<OnlineOrderProviderService> CREATOR = new android.os.Parcelable.Creator<OnlineOrderProviderService>() {
    @Override
    public OnlineOrderProviderService createFromParcel(android.os.Parcel in) {
      OnlineOrderProviderService instance = new OnlineOrderProviderService(com.clover.sdk.v3.JsonParcelHelper.ObjectWrapper.CREATOR.createFromParcel(in).unwrap());
      instance.genClient.setBundle(in.readBundle(getClass().getClassLoader()));
      instance.genClient.setChangeLog(in.readBundle());
      return instance;
    }

    @Override
    public OnlineOrderProviderService[] newArray(int size) {
      return new OnlineOrderProviderService[size];
    }
  };

  public static final com.clover.sdk.JSONifiable.Creator<OnlineOrderProviderService> JSON_CREATOR = new com.clover.sdk.JSONifiable.Creator<OnlineOrderProviderService>() {
    public Class<OnlineOrderProviderService> getCreatedClass() {
      return OnlineOrderProviderService.class;
    }

    @Override
    public OnlineOrderProviderService create(org.json.JSONObject jsonObject) {
      return new OnlineOrderProviderService(jsonObject);
    }
  };

  public interface Constraints {
    public static final boolean ID_IS_REQUIRED = false;
    public static final long ID_MAX_LEN = 13;
    public static final boolean SERVICETYPE_IS_REQUIRED = true;
    public static final boolean PROVIDER_IS_REQUIRED = true;
    public static final boolean CREATEDTIME_IS_REQUIRED = false;
    public static final boolean MODIFIEDTIME_IS_REQUIRED = false;
    public static final boolean DELETEDTIME_IS_REQUIRED = false;
  }

}
