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

package com.clover.sdk.v3.inventory;

import com.clover.sdk.GenericClient;
import com.clover.sdk.GenericParcelable;

/**
 * This is an auto-generated Clover data object.
 * <p>
 * <h3>Fields</h3>
 * <ul>
 * <li>{@link #getItems items}</li>
 * </ul>
 */
@SuppressWarnings("all")
public class InventoryItems extends GenericParcelable implements com.clover.sdk.v3.Validator, com.clover.sdk.JSONifiable {

  /**
   * Array of inventory items
   */
  public java.util.List<com.clover.sdk.v3.inventory.Item> getItems() {
    return genClient.cacheGet(CacheKey.items);
  }




  private enum CacheKey implements com.clover.sdk.ExtractionStrategyEnum {
    items
        (com.clover.sdk.extractors.RecordListExtractionStrategy.instance(com.clover.sdk.v3.inventory.Item.JSON_CREATOR)),
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

  private final GenericClient<InventoryItems> genClient;

  /**
   * Constructs a new empty instance.
   */
  public InventoryItems() {
    genClient = new GenericClient<InventoryItems>(this);
  }

  @Override
  protected GenericClient getGenericClient() {
    return genClient;
  }

  /**
   * Constructs a new empty instance.
   */
  protected InventoryItems(boolean noInit) {
    genClient = null;
  }

  /**
   * Constructs a new instance from the given JSON String.
   */
  public InventoryItems(String json) throws IllegalArgumentException {
    this();
    genClient.initJsonObject(json);
  }

  /**
   * Construct a new instance backed by the given JSONObject, the parameter is not copied so changes to it will be
   * reflected in this instance and vice-versa.
   */
  public InventoryItems(org.json.JSONObject jsonObject) {
    this();
    genClient.setJsonObject(jsonObject);
  }

  /**
   * Constructs a new instance that is a deep copy of the source instance. It does not copy the bundle or changelog.
   */
  public InventoryItems(InventoryItems src) {
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
    genClient.validateNotNull(CacheKey.items, getItems());
  }

  /** Checks whether the 'items' field is set and is not null */
  public boolean isNotNullItems() {
    return genClient.cacheValueIsNotNull(CacheKey.items);
  }

  /** Checks whether the 'items' field is set and is not null and is not empty */
  public boolean isNotEmptyItems() { return isNotNullItems() && !getItems().isEmpty(); }



  /** Checks whether the 'items' field has been set, however the value could be null */
  public boolean hasItems() {
    return genClient.cacheHasKey(CacheKey.items);
  }


  /**
   * Sets the field 'items'.
   *
   * Nulls in the given List are skipped. List parameter is copied, so it will not reflect any changes, but objects inside it will.
   */
  public InventoryItems setItems(java.util.List<com.clover.sdk.v3.inventory.Item> items) {
    return genClient.setArrayRecord(items, CacheKey.items);
  }


  /** Clears the 'items' field, the 'has' method for this field will now return false */
  public void clearItems() {
    genClient.clear(CacheKey.items);
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
  public InventoryItems copyChanges() {
    InventoryItems copy = new InventoryItems();
    copy.mergeChanges(this);
    copy.resetChangeLog();
    return copy;
  }

  /**
   * Copy all the changed fields from the given source to this instance.
   */
  public void mergeChanges(InventoryItems src) {
    if (src.genClient.getChangeLog() != null) {
      genClient.mergeChanges(new InventoryItems(src).getJSONObject(), src.genClient);
    }
  }

  public static final android.os.Parcelable.Creator<InventoryItems> CREATOR = new android.os.Parcelable.Creator<InventoryItems>() {
    @Override
    public InventoryItems createFromParcel(android.os.Parcel in) {
      InventoryItems instance = new InventoryItems(com.clover.sdk.v3.JsonParcelHelper.ObjectWrapper.CREATOR.createFromParcel(in).unwrap());
      instance.genClient.setBundle(in.readBundle(getClass().getClassLoader()));
      instance.genClient.setChangeLog(in.readBundle());
      return instance;
    }

    @Override
    public InventoryItems[] newArray(int size) {
      return new InventoryItems[size];
    }
  };

  public static final com.clover.sdk.JSONifiable.Creator<InventoryItems> JSON_CREATOR = new com.clover.sdk.JSONifiable.Creator<InventoryItems>() {
    public Class<InventoryItems> getCreatedClass() {
      return InventoryItems.class;
    }

    @Override
    public InventoryItems create(org.json.JSONObject jsonObject) {
      return new InventoryItems(jsonObject);
    }
  };

  public interface Constraints {
    public static final boolean ITEMS_IS_REQUIRED = true;
  }

}
