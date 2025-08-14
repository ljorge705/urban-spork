package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ProductConfigResponse extends CleverTapResponseDecorator {
    private final CleverTapInstanceConfig config;
    private final ControllerManager controllerManager;
    private final CoreMetaData coreMetaData;
    private final Logger logger;

    public ProductConfigResponse(CleverTapInstanceConfig cleverTapInstanceConfig, CoreMetaData coreMetaData, ControllerManager controllerManager) {
        this.config = cleverTapInstanceConfig;
        this.logger = cleverTapInstanceConfig.getLogger();
        this.coreMetaData = coreMetaData;
        this.controllerManager = controllerManager;
    }

    @Override // com.clevertap.android.sdk.response.CleverTapResponseDecorator, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        this.logger.verbose(this.config.getAccountId(), "Processing Product Config response...");
        if (this.config.isAnalyticsOnly()) {
            this.logger.verbose(this.config.getAccountId(), "CleverTap instance is configured to analytics only, not processing Product Config response");
            return;
        }
        if (jSONObject == null) {
            this.logger.verbose(this.config.getAccountId(), "Product Config : Can't parse Product Config Response, JSON response object is null");
            onProductConfigFailed();
        } else {
            if (!jSONObject.has(Constants.REMOTE_CONFIG_FLAG_JSON_RESPONSE_KEY)) {
                this.logger.verbose(this.config.getAccountId(), "Product Config : JSON object doesn't contain the Product Config key");
                onProductConfigFailed();
                return;
            }
            try {
                this.logger.verbose(this.config.getAccountId(), "Product Config : Processing Product Config response");
                parseProductConfigs(jSONObject.getJSONObject(Constants.REMOTE_CONFIG_FLAG_JSON_RESPONSE_KEY));
            } catch (Throwable th) {
                onProductConfigFailed();
                this.logger.verbose(this.config.getAccountId(), "Product Config : Failed to parse Product Config response", th);
            }
        }
    }

    private void onProductConfigFailed() {
        if (this.coreMetaData.isProductConfigRequested()) {
            if (this.controllerManager.getCTProductConfigController() != null) {
                this.controllerManager.getCTProductConfigController().onFetchFailed();
            }
            this.coreMetaData.setProductConfigRequested(false);
        }
    }

    private void parseProductConfigs(JSONObject jSONObject) throws JSONException {
        if (jSONObject.getJSONArray(Constants.KEY_KV) != null && this.controllerManager.getCTProductConfigController() != null) {
            this.controllerManager.getCTProductConfigController().onFetchSuccess(jSONObject);
        } else {
            onProductConfigFailed();
        }
    }
}
