package com.clevertap.android.sdk.utils;

import android.os.Parcel;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JsonUtils.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a-\u0010\u0000\u001a\u00020\u0001*\u00020\u00012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003\u001a\u0014\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\n\u001a\u0012\u0010\u000b\u001a\u00020\f*\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e\u001a+\u0010\u000f\u001a\u00020\f*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\n2\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f0\u0003¢\u0006\u0002\b\u0011\u001a\f\u0010\u0012\u001a\u0004\u0018\u00010\u0004*\u00020\u0013\u001a\u0014\u0010\u0014\u001a\u00020\f*\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004¨\u0006\u0016"}, d2 = {"filterObjects", "Lorg/json/JSONArray;", "predicate", "Lkotlin/Function1;", "Lorg/json/JSONObject;", "Lkotlin/ParameterName;", "name", "element", "", "getStringOrNull", "", "prepend", "", "value", "", "putObject", Session.JsonKeys.INIT, "Lkotlin/ExtensionFunctionType;", "readJson", "Landroid/os/Parcel;", "writeJson", "json", "clevertap-core_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class JsonUtilsKt {
    public static final void putObject(JSONObject jSONObject, String name, Function1<? super JSONObject, Unit> init) throws JSONException {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(init, "init");
        JSONObject jSONObject2 = new JSONObject();
        init.invoke(jSONObject2);
        jSONObject.put(name, jSONObject2);
    }

    public static final String getStringOrNull(JSONObject jSONObject, String name) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        if (jSONObject.has(name)) {
            return jSONObject.getString(name);
        }
        return null;
    }

    public static final void prepend(JSONArray jSONArray, Object value) throws JSONException {
        Intrinsics.checkNotNullParameter(jSONArray, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        int i = 0;
        while (i < jSONArray.length()) {
            Object currentItem = jSONArray.get(i);
            jSONArray.put(i, value);
            Intrinsics.checkNotNullExpressionValue(currentItem, "currentItem");
            i++;
            value = currentItem;
        }
        jSONArray.put(value);
    }

    public static final JSONObject readJson(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "<this>");
        try {
            String string = parcel.readString();
            if (string != null) {
                return new JSONObject(string);
            }
            return null;
        } catch (JSONException unused) {
            return null;
        }
    }

    public static final void writeJson(Parcel parcel, JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(parcel, "<this>");
        parcel.writeString(jSONObject != null ? jSONObject.toString() : null);
    }

    public static final JSONArray filterObjects(JSONArray jSONArray, Function1<? super JSONObject, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jSONArray, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        JSONArray jSONArray2 = new JSONArray();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null && predicate.invoke(jSONObjectOptJSONObject).booleanValue()) {
                jSONArray2.put(jSONObjectOptJSONObject);
            }
        }
        return jSONArray2;
    }
}
