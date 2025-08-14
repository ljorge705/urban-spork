package com.clevertap.android.sdk;

import android.os.Bundle;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AnalyticsManagerBundler.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\t"}, d2 = {"Lcom/clevertap/android/sdk/AnalyticsManagerBundler;", "", "()V", "notificationClickedJson", "Lorg/json/JSONObject;", "root", "Landroid/os/Bundle;", "notificationViewedJson", "wzrkBundleToJson", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class AnalyticsManagerBundler {
    public static final AnalyticsManagerBundler INSTANCE = new AnalyticsManagerBundler();

    private AnalyticsManagerBundler() {
    }

    @JvmStatic
    public static final JSONObject wzrkBundleToJson(Bundle root) throws JSONException {
        Intrinsics.checkNotNullParameter(root, "root");
        JSONObject jSONObject = new JSONObject();
        for (String s : root.keySet()) {
            Object obj = root.get(s);
            if (obj instanceof Bundle) {
                JSONObject jSONObjectWzrkBundleToJson = wzrkBundleToJson((Bundle) obj);
                Iterator<String> itKeys = jSONObjectWzrkBundleToJson.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    jSONObject.put(next, jSONObjectWzrkBundleToJson.get(next));
                }
            } else {
                Intrinsics.checkNotNullExpressionValue(s, "s");
                if (StringsKt.startsWith$default(s, Constants.WZRK_PREFIX, false, 2, (Object) null)) {
                    jSONObject.put(s, root.get(s));
                }
            }
        }
        return jSONObject;
    }

    @JvmStatic
    public static final JSONObject notificationViewedJson(Bundle root) {
        Intrinsics.checkNotNullParameter(root, "root");
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObjectWzrkBundleToJson = wzrkBundleToJson(root);
            jSONObject.put(Constants.KEY_EVT_NAME, Constants.NOTIFICATION_VIEWED_EVENT_NAME);
            jSONObject.put(Constants.KEY_EVT_DATA, jSONObjectWzrkBundleToJson);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    @JvmStatic
    public static final JSONObject notificationClickedJson(Bundle root) {
        Intrinsics.checkNotNullParameter(root, "root");
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObjectWzrkBundleToJson = wzrkBundleToJson(root);
            jSONObject.put(Constants.KEY_EVT_NAME, Constants.NOTIFICATION_CLICKED_EVENT_NAME);
            jSONObject.put(Constants.KEY_EVT_DATA, jSONObjectWzrkBundleToJson);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }
}
