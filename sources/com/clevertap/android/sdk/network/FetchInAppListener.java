package com.clevertap.android.sdk.network;

import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.inapp.callbacks.FetchInAppsCallback;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: BatchListeners.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/clevertap/android/sdk/network/FetchInAppListener;", "Lcom/clevertap/android/sdk/network/BatchListener;", "callbackManager", "Lcom/clevertap/android/sdk/BaseCallbackManager;", "(Lcom/clevertap/android/sdk/BaseCallbackManager;)V", "onBatchSent", "", "batch", "Lorg/json/JSONArray;", FirebaseAnalytics.Param.SUCCESS, "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public class FetchInAppListener implements BatchListener {
    private final BaseCallbackManager callbackManager;

    public FetchInAppListener(BaseCallbackManager callbackManager) {
        Intrinsics.checkNotNullParameter(callbackManager, "callbackManager");
        this.callbackManager = callbackManager;
    }

    @Override // com.clevertap.android.sdk.network.BatchListener
    public void onBatchSent(JSONArray batch, boolean success) {
        Intrinsics.checkNotNullParameter(batch, "batch");
        if (batch.length() == 0) {
            FetchInAppsCallback fetchInAppsCallback = this.callbackManager.getFetchInAppsCallback();
            if (fetchInAppsCallback != null) {
                fetchInAppsCallback.onInAppsFetched(success);
                return;
            }
            return;
        }
        int length = batch.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = batch.optJSONObject(i);
            if (jSONObjectOptJSONObject == null) {
                jSONObjectOptJSONObject = new JSONObject();
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(Constants.KEY_EVT_DATA);
            if (jSONObjectOptJSONObject2 == null) {
                jSONObjectOptJSONObject2 = new JSONObject();
            }
            if (Intrinsics.areEqual(jSONObjectOptJSONObject.optString(Constants.KEY_EVT_NAME), Constants.WZRK_FETCH) && jSONObjectOptJSONObject2.optInt("t") == 5) {
                FetchInAppsCallback fetchInAppsCallback2 = this.callbackManager.getFetchInAppsCallback();
                if (fetchInAppsCallback2 != null) {
                    fetchInAppsCallback2.onInAppsFetched(success);
                    return;
                }
                return;
            }
        }
    }
}
