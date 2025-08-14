package com.clevertap.android.sdk.inapp.evaluation;

import com.clevertap.android.sdk.CTXtensions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventResultReceiver;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: TriggerAdapter.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 '2\u00020\u0001:\u0001'B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\u0012J\u0010\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010!\u001a\u00020\u0012J\u0010\u0010$\u001a\u0004\u0018\u00010#2\u0006\u0010!\u001a\u00020\u0012J\u0010\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020\u0003H\u0007R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0017\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0014R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\bR\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010R\u0011\u0010\u001d\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0014¨\u0006("}, d2 = {"Lcom/clevertap/android/sdk/inapp/evaluation/TriggerAdapter;", "", "triggerJSON", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "eventName", "", "getEventName", "()Ljava/lang/String;", TriggerAdapter.KEY_FIRST_TIME_ONLY, "", "getFirstTimeOnly", "()Z", "geoRadiusArray", "Lorg/json/JSONArray;", "getGeoRadiusArray", "()Lorg/json/JSONArray;", "geoRadiusCount", "", "getGeoRadiusCount", "()I", FirebaseAnalytics.Param.ITEMS, "getItems", "itemsCount", "getItemsCount", TriggerAdapter.KEY_PROFILE_ATTR_NAME, "getProfileAttrName", OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES, "getProperties", "propertyCount", "getPropertyCount", "geoRadiusAtIndex", "Lcom/clevertap/android/sdk/inapp/evaluation/TriggerGeoRadius;", "index", "itemAtIndex", "Lcom/clevertap/android/sdk/inapp/evaluation/TriggerCondition;", "propertyAtIndex", "triggerConditionFromJSON", "property", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class TriggerAdapter {
    public static final String INAPP_OPERATOR = "operator";
    public static final String INAPP_PROPERTYNAME = "propertyName";
    public static final String KEY_EVENT_NAME = "eventName";
    public static final String KEY_EVENT_PROPERTIES = "eventProperties";
    public static final String KEY_FIRST_TIME_ONLY = "firstTimeOnly";
    public static final String KEY_GEO_RADIUS_PROPERTIES = "geoRadius";
    public static final String KEY_ITEM_PROPERTIES = "itemProperties";
    public static final String KEY_PROFILE_ATTR_NAME = "profileAttrName";
    public static final String KEY_PROPERTY_VALUE = "propertyValue";
    private final String eventName;
    private final boolean firstTimeOnly;
    private final JSONArray geoRadiusArray;
    private final JSONArray items;
    private final String profileAttrName;
    private final JSONArray properties;

    public final String getEventName() {
        return this.eventName;
    }

    public final boolean getFirstTimeOnly() {
        return this.firstTimeOnly;
    }

    public final JSONArray getGeoRadiusArray() {
        return this.geoRadiusArray;
    }

    public final JSONArray getItems() {
        return this.items;
    }

    public final String getProfileAttrName() {
        return this.profileAttrName;
    }

    public final JSONArray getProperties() {
        return this.properties;
    }

    public TriggerAdapter(JSONObject triggerJSON) {
        Intrinsics.checkNotNullParameter(triggerJSON, "triggerJSON");
        String strOptString = triggerJSON.optString("eventName", "");
        Intrinsics.checkNotNullExpressionValue(strOptString, "triggerJSON.optString(KEY_EVENT_NAME, \"\")");
        this.eventName = strOptString;
        this.properties = triggerJSON.optJSONArray(KEY_EVENT_PROPERTIES);
        this.items = triggerJSON.optJSONArray(KEY_ITEM_PROPERTIES);
        this.geoRadiusArray = triggerJSON.optJSONArray(KEY_GEO_RADIUS_PROPERTIES);
        this.profileAttrName = triggerJSON.optString(KEY_PROFILE_ATTR_NAME, null);
        this.firstTimeOnly = triggerJSON.optBoolean(KEY_FIRST_TIME_ONLY, false);
    }

    public final int getPropertyCount() {
        JSONArray jSONArray = this.properties;
        if (jSONArray != null) {
            return jSONArray.length();
        }
        return 0;
    }

    public final int getItemsCount() {
        JSONArray jSONArray = this.items;
        if (jSONArray != null) {
            return jSONArray.length();
        }
        return 0;
    }

    public final int getGeoRadiusCount() {
        JSONArray jSONArray = this.geoRadiusArray;
        if (jSONArray != null) {
            return jSONArray.length();
        }
        return 0;
    }

    public final TriggerCondition triggerConditionFromJSON(JSONObject property) {
        Intrinsics.checkNotNullParameter(property, "property");
        TriggerValue triggerValue = new TriggerValue(property.opt(KEY_PROPERTY_VALUE), null, 2, null);
        TriggerOperator triggerOperatorOptTriggerOperator = TriggerAdapterKt.optTriggerOperator(property, INAPP_OPERATOR);
        String strOptString = property.optString(INAPP_PROPERTYNAME, "");
        Intrinsics.checkNotNullExpressionValue(strOptString, "property.optString(INAPP_PROPERTYNAME, \"\")");
        return new TriggerCondition(strOptString, triggerOperatorOptTriggerOperator, triggerValue);
    }

    public final TriggerCondition propertyAtIndex(int index) {
        if (CTXtensions.isInvalidIndex(this.properties, index)) {
            return null;
        }
        JSONArray jSONArray = this.properties;
        JSONObject jSONObjectOptJSONObject = jSONArray != null ? jSONArray.optJSONObject(index) : null;
        if (jSONObjectOptJSONObject == null) {
            return null;
        }
        return triggerConditionFromJSON(jSONObjectOptJSONObject);
    }

    public final TriggerCondition itemAtIndex(int index) {
        if (CTXtensions.isInvalidIndex(this.items, index)) {
            return null;
        }
        JSONArray jSONArray = this.items;
        JSONObject jSONObjectOptJSONObject = jSONArray != null ? jSONArray.optJSONObject(index) : null;
        if (jSONObjectOptJSONObject == null) {
            return null;
        }
        return triggerConditionFromJSON(jSONObjectOptJSONObject);
    }

    public final TriggerGeoRadius geoRadiusAtIndex(int index) {
        if (CTXtensions.isInvalidIndex(this.geoRadiusArray, index)) {
            return null;
        }
        JSONArray jSONArray = this.geoRadiusArray;
        JSONObject jSONObjectOptJSONObject = jSONArray != null ? jSONArray.optJSONObject(index) : null;
        if (jSONObjectOptJSONObject == null) {
            return null;
        }
        return new TriggerGeoRadius(jSONObjectOptJSONObject.optDouble("lat"), jSONObjectOptJSONObject.optDouble("lng"), jSONObjectOptJSONObject.optDouble("rad"));
    }
}
