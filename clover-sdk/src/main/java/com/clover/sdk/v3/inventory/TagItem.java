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
 * <li>{@link #getTag tag}</li>
 * <li>{@link #getItem item}</li>
 * </ul>
 * <p>
 * @see com.clover.sdk.v3.inventory.IInventoryService
 */
@SuppressWarnings("all")
public class TagItem extends GenericParcelable implements com.clover.sdk.v3.Validator, com.clover.sdk.JSONifiable {

  public com.clover.sdk.v3.inventory.Tag getTag() {
    return genClient.cacheGet(CacheKey.tag);
  }

  public com.clover.sdk.v3.inventory.Item getItem() {
    return genClient.cacheGet(CacheKey.item);
  }




  private enum CacheKey implements com.clover.sdk.ExtractionStrategyEnum {
    tag
        (com.clover.sdk.extractors.RecordExtractionStrategy.instance(com.clover.sdk.v3.inventory.Tag.JSON_CREATOR)),
    item
        (com.clover.sdk.extractors.RecordExtractionStrategy.instance(com.clover.sdk.v3.inventory.Item.JSON_CREATOR)),
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

  private final GenericClient<TagItem> genClient;

  /**
   * Constructs a new empty instance.
   */
  public TagItem() {
    genClient = new GenericClient<TagItem>(this);
  }

  @Override
  protected GenericClient getGenericClient() {
    return genClient;
  }

  /**
   * Constructs a new empty instance.
   */
  protected TagItem(boolean noInit) {
    genClient = null;
  }

  /**
   * Constructs a new instance from the given JSON String.
   */
  public TagItem(String json) throws IllegalArgumentException {
    this();
    genClient.initJsonObject(json);
  }

  /**
   * Construct a new instance backed by the given JSONObject, the parameter is not copied so changes to it will be
   * reflected in this instance and vice-versa.
   */
  public TagItem(org.json.JSONObject jsonObject) {
    this();
    genClient.setJsonObject(jsonObject);
  }

  /**
   * Constructs a new instance that is a deep copy of the source instance. It does not copy the bundle or changelog.
   */
  public TagItem(TagItem src) {
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
    genClient.validateNotNull(CacheKey.tag, getTag());

    genClient.validateNotNull(CacheKey.item, getItem());
  }

  /** Checks whether the 'tag' field is set and is not null */
  public boolean isNotNullTag() {
    return genClient.cacheValueIsNotNull(CacheKey.tag);
  }

  /** Checks whether the 'item' field is set and is not null */
  public boolean isNotNullItem() {
    return genClient.cacheValueIsNotNull(CacheKey.item);
  }



  /** Checks whether the 'tag' field has been set, however the value could be null */
  public boolean hasTag() {
    return genClient.cacheHasKey(CacheKey.tag);
  }

  /** Checks whether the 'item' field has been set, however the value could be null */
  public boolean hasItem() {
    return genClient.cacheHasKey(CacheKey.item);
  }


  /**
   * Sets the field 'tag'.
   *
   * The parameter is not copied so changes to it will be reflected in this instance and vice-versa.
   */
  public TagItem setTag(com.clover.sdk.v3.inventory.Tag tag) {
    return genClient.setRecord(tag, CacheKey.tag);
  }

  /**
   * Sets the field 'item'.
   *
   * The parameter is not copied so changes to it will be reflected in this instance and vice-versa.
   */
  public TagItem setItem(com.clover.sdk.v3.inventory.Item item) {
    return genClient.setRecord(item, CacheKey.item);
  }


  /** Clears the 'tag' field, the 'has' method for this field will now return false */
  public void clearTag() {
    genClient.clear(CacheKey.tag);
  }
  /** Clears the 'item' field, the 'has' method for this field will now return false */
  public void clearItem() {
    genClient.clear(CacheKey.item);
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
  public TagItem copyChanges() {
    TagItem copy = new TagItem();
    copy.mergeChanges(this);
    copy.resetChangeLog();
    return copy;
  }

  /**
   * Copy all the changed fields from the given source to this instance.
   */
  public void mergeChanges(TagItem src) {
    if (src.genClient.getChangeLog() != null) {
      genClient.mergeChanges(new TagItem(src).getJSONObject(), src.genClient);
    }
  }

  public static final android.os.Parcelable.Creator<TagItem> CREATOR = new android.os.Parcelable.Creator<TagItem>() {
    @Override
    public TagItem createFromParcel(android.os.Parcel in) {
      TagItem instance = new TagItem(com.clover.sdk.v3.JsonParcelHelper.ObjectWrapper.CREATOR.createFromParcel(in).unwrap());
      instance.genClient.setBundle(in.readBundle(getClass().getClassLoader()));
      instance.genClient.setChangeLog(in.readBundle());
      return instance;
    }

    @Override
    public TagItem[] newArray(int size) {
      return new TagItem[size];
    }
  };

  public static final com.clover.sdk.JSONifiable.Creator<TagItem> JSON_CREATOR = new com.clover.sdk.JSONifiable.Creator<TagItem>() {
    public Class<TagItem> getCreatedClass() {
      return TagItem.class;
    }

    @Override
    public TagItem create(org.json.JSONObject jsonObject) {
      return new TagItem(jsonObject);
    }
  };

  public interface Constraints {
    public static final boolean TAG_IS_REQUIRED = true;
    public static final boolean ITEM_IS_REQUIRED = true;
  }

}