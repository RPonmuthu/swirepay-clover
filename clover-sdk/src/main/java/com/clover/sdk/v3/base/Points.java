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

package com.clover.sdk.v3.base;

import com.clover.sdk.GenericClient;
import com.clover.sdk.GenericParcelable;

/**
 * This is an auto-generated Clover data object.
 * <p>
 * <h3>Fields</h3>
 * <ul>
 * <li>{@link #getPoints points}</li>
 * </ul>
 */
@SuppressWarnings("all")
public class Points extends GenericParcelable implements com.clover.sdk.v3.Validator, com.clover.sdk.JSONifiable {

  public java.util.List<com.clover.sdk.v3.base.Point> getPoints() {
    return genClient.cacheGet(CacheKey.points);
  }




  private enum CacheKey implements com.clover.sdk.ExtractionStrategyEnum {
    points
        (com.clover.sdk.extractors.RecordListExtractionStrategy.instance(com.clover.sdk.v3.base.Point.JSON_CREATOR)),
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

  private final GenericClient<Points> genClient;

  /**
   * Constructs a new empty instance.
   */
  public Points() {
    genClient = new GenericClient<Points>(this);
  }

  @Override
  protected GenericClient getGenericClient() {
    return genClient;
  }

  /**
   * Constructs a new empty instance.
   */
  protected Points(boolean noInit) {
    genClient = null;
  }

  /**
   * Constructs a new instance from the given JSON String.
   */
  public Points(String json) throws IllegalArgumentException {
    this();
    genClient.initJsonObject(json);
  }

  /**
   * Construct a new instance backed by the given JSONObject, the parameter is not copied so changes to it will be
   * reflected in this instance and vice-versa.
   */
  public Points(org.json.JSONObject jsonObject) {
    this();
    genClient.setJsonObject(jsonObject);
  }

  /**
   * Constructs a new instance that is a deep copy of the source instance. It does not copy the bundle or changelog.
   */
  public Points(Points src) {
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
  }

  /** Checks whether the 'points' field is set and is not null */
  public boolean isNotNullPoints() {
    return genClient.cacheValueIsNotNull(CacheKey.points);
  }

  /** Checks whether the 'points' field is set and is not null and is not empty */
  public boolean isNotEmptyPoints() { return isNotNullPoints() && !getPoints().isEmpty(); }



  /** Checks whether the 'points' field has been set, however the value could be null */
  public boolean hasPoints() {
    return genClient.cacheHasKey(CacheKey.points);
  }


  /**
   * Sets the field 'points'.
   *
   * Nulls in the given List are skipped. List parameter is copied, so it will not reflect any changes, but objects inside it will.
   */
  public Points setPoints(java.util.List<com.clover.sdk.v3.base.Point> points) {
    return genClient.setArrayRecord(points, CacheKey.points);
  }


  /** Clears the 'points' field, the 'has' method for this field will now return false */
  public void clearPoints() {
    genClient.clear(CacheKey.points);
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
  public Points copyChanges() {
    Points copy = new Points();
    copy.mergeChanges(this);
    copy.resetChangeLog();
    return copy;
  }

  /**
   * Copy all the changed fields from the given source to this instance.
   */
  public void mergeChanges(Points src) {
    if (src.genClient.getChangeLog() != null) {
      genClient.mergeChanges(new Points(src).getJSONObject(), src.genClient);
    }
  }

  public static final android.os.Parcelable.Creator<Points> CREATOR = new android.os.Parcelable.Creator<Points>() {
    @Override
    public Points createFromParcel(android.os.Parcel in) {
      Points instance = new Points(com.clover.sdk.v3.JsonParcelHelper.ObjectWrapper.CREATOR.createFromParcel(in).unwrap());
      instance.genClient.setBundle(in.readBundle(getClass().getClassLoader()));
      instance.genClient.setChangeLog(in.readBundle());
      return instance;
    }

    @Override
    public Points[] newArray(int size) {
      return new Points[size];
    }
  };

  public static final com.clover.sdk.JSONifiable.Creator<Points> JSON_CREATOR = new com.clover.sdk.JSONifiable.Creator<Points>() {
    public Class<Points> getCreatedClass() {
      return Points.class;
    }

    @Override
    public Points create(org.json.JSONObject jsonObject) {
      return new Points(jsonObject);
    }
  };

  public interface Constraints {
    public static final boolean POINTS_IS_REQUIRED = false;
  }

}
