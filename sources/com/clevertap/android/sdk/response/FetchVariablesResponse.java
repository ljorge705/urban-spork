package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.Logger;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class FetchVariablesResponse extends CleverTapResponseDecorator {
    private final BaseCallbackManager callbackMgr;
    private final CleverTapInstanceConfig config;
    private final ControllerManager controllerManager;

    public FetchVariablesResponse(CleverTapInstanceConfig cleverTapInstanceConfig, ControllerManager controllerManager, BaseCallbackManager baseCallbackManager) {
        this.config = cleverTapInstanceConfig;
        this.controllerManager = controllerManager;
        this.callbackMgr = baseCallbackManager;
    }

    private void logD(String str) {
        Logger.d("variables", str);
    }

    private void logI(String str) {
        Logger.d("variables", str);
    }

    private void logI(String str, Throwable th) {
        Logger.i("variables", str, th);
    }

    @Override // com.clevertap.android.sdk.response.CleverTapResponseDecorator, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        logI("Processing Variable response...");
        logD("processResponse() called with: response = [" + jSONObject + "], stringBody = [" + str + "], context = [" + context + "]");
        if (this.config.isAnalyticsOnly()) {
            logI("CleverTap instance is configured to analytics only, not processing Variable response");
            return;
        }
        if (jSONObject == null) {
            logI("Can't parse Variable Response, JSON response object is null");
            return;
        }
        if (!jSONObject.has("vars")) {
            logI("JSON object doesn't contain the vars key");
            return;
        }
        try {
            logI("Processing Request Variables response");
            JSONObject jSONObject2 = jSONObject.getJSONObject("vars");
            if (this.controllerManager.getCtVariables() != null) {
                this.controllerManager.getCtVariables().handleVariableResponse(jSONObject2, this.callbackMgr.getFetchVariablesCallback());
                this.callbackMgr.setFetchVariablesCallback(null);
            } else {
                logI("Can't parse Variable Response, CTVariables is null");
            }
        } catch (Throwable th) {
            logI("Failed to parse response", th);
        }
    }
}
