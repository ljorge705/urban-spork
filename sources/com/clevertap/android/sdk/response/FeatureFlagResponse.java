package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class FeatureFlagResponse extends CleverTapResponseDecorator {
    private final CleverTapInstanceConfig config;
    private final ControllerManager controllerManager;
    private final Logger logger;

    public FeatureFlagResponse(CleverTapInstanceConfig cleverTapInstanceConfig, ControllerManager controllerManager) {
        this.config = cleverTapInstanceConfig;
        this.logger = cleverTapInstanceConfig.getLogger();
        this.controllerManager = controllerManager;
    }

    @Override // com.clevertap.android.sdk.response.CleverTapResponseDecorator, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        this.logger.verbose(this.config.getAccountId(), "Processing Feature Flags response...");
        if (this.config.isAnalyticsOnly()) {
            this.logger.verbose(this.config.getAccountId(), "CleverTap instance is configured to analytics only, not processing Feature Flags response");
            return;
        }
        if (jSONObject == null) {
            this.logger.verbose(this.config.getAccountId(), "Feature Flag : Can't parse Feature Flags Response, JSON response object is null");
            return;
        }
        if (!jSONObject.has(Constants.FEATURE_FLAG_JSON_RESPONSE_KEY)) {
            this.logger.verbose(this.config.getAccountId(), "Feature Flag : JSON object doesn't contain the Feature Flags key");
            return;
        }
        try {
            this.logger.verbose(this.config.getAccountId(), "Feature Flag : Processing Feature Flags response");
            parseFeatureFlags(jSONObject.getJSONObject(Constants.FEATURE_FLAG_JSON_RESPONSE_KEY));
        } catch (Throwable th) {
            this.logger.verbose(this.config.getAccountId(), "Feature Flag : Failed to parse response", th);
        }
    }

    private void parseFeatureFlags(JSONObject jSONObject) throws JSONException {
        if (jSONObject.getJSONArray(Constants.KEY_KV) != null && this.controllerManager.getCTFeatureFlagsController() != null) {
            this.controllerManager.getCTFeatureFlagsController().updateFeatureFlags(jSONObject);
        } else {
            this.config.getLogger().verbose(this.config.getAccountId(), "Feature Flag : Can't parse feature flags, CTFeatureFlagsController is null");
        }
    }
}
