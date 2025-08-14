package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.displayunits.CTDisplayUnitController;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class DisplayUnitResponse extends CleverTapResponseDecorator {
    private final BaseCallbackManager callbackManager;
    private final CleverTapInstanceConfig config;
    private final ControllerManager controllerManager;
    private final Object displayUnitControllerLock = new Object();
    private final Logger logger;

    public DisplayUnitResponse(CleverTapInstanceConfig cleverTapInstanceConfig, BaseCallbackManager baseCallbackManager, ControllerManager controllerManager) {
        this.config = cleverTapInstanceConfig;
        this.logger = cleverTapInstanceConfig.getLogger();
        this.callbackManager = baseCallbackManager;
        this.controllerManager = controllerManager;
    }

    @Override // com.clevertap.android.sdk.response.CleverTapResponseDecorator, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        this.logger.verbose(this.config.getAccountId(), "Processing Display Unit items...");
        if (this.config.isAnalyticsOnly()) {
            this.logger.verbose(this.config.getAccountId(), "CleverTap instance is configured to analytics only, not processing Display Unit response");
            return;
        }
        if (jSONObject == null) {
            this.logger.verbose(this.config.getAccountId(), "DisplayUnit : Can't parse Display Unit Response, JSON response object is null");
            return;
        }
        if (!jSONObject.has(Constants.DISPLAY_UNIT_JSON_RESPONSE_KEY)) {
            this.logger.verbose(this.config.getAccountId(), "DisplayUnit : JSON object doesn't contain the Display Units key");
            return;
        }
        try {
            this.logger.verbose(this.config.getAccountId(), "DisplayUnit : Processing Display Unit response");
            parseDisplayUnits(jSONObject.getJSONArray(Constants.DISPLAY_UNIT_JSON_RESPONSE_KEY));
        } catch (Throwable th) {
            this.logger.verbose(this.config.getAccountId(), "DisplayUnit : Failed to parse response", th);
        }
    }

    private void parseDisplayUnits(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            this.logger.verbose(this.config.getAccountId(), "DisplayUnit : Can't parse Display Units, jsonArray is either empty or null");
            return;
        }
        synchronized (this.displayUnitControllerLock) {
            if (this.controllerManager.getCTDisplayUnitController() == null) {
                this.controllerManager.setCTDisplayUnitController(new CTDisplayUnitController());
            }
        }
        this.callbackManager.notifyDisplayUnitsLoaded(this.controllerManager.getCTDisplayUnitController().updateDisplayUnits(jSONArray));
    }
}
