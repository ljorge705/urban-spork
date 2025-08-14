package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class GeofenceResponse extends CleverTapResponseDecorator {
    private final BaseCallbackManager callbackManager;
    private final CleverTapInstanceConfig config;
    private final Logger logger;

    public GeofenceResponse(CleverTapInstanceConfig cleverTapInstanceConfig, BaseCallbackManager baseCallbackManager) {
        this.config = cleverTapInstanceConfig;
        this.logger = cleverTapInstanceConfig.getLogger();
        this.callbackManager = baseCallbackManager;
    }

    @Override // com.clevertap.android.sdk.response.CleverTapResponseDecorator, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        this.logger.verbose(this.config.getAccountId(), "Processing GeoFences response...");
        if (this.config.isAnalyticsOnly()) {
            this.logger.verbose(this.config.getAccountId(), "CleverTap instance is configured to analytics only, not processing geofence response");
            return;
        }
        if (jSONObject == null) {
            this.logger.verbose(this.config.getAccountId(), "Geofences : Can't parse Geofences Response, JSON response object is null");
            return;
        }
        if (!jSONObject.has(Constants.GEOFENCES_JSON_RESPONSE_KEY)) {
            this.logger.verbose(this.config.getAccountId(), "Geofences : JSON object doesn't contain the Geofences key");
            return;
        }
        try {
            if (this.callbackManager.getGeofenceCallback() != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(Constants.GEOFENCES_JSON_RESPONSE_KEY, jSONObject.getJSONArray(Constants.GEOFENCES_JSON_RESPONSE_KEY));
                this.logger.verbose(this.config.getAccountId(), "Geofences : Processing Geofences response");
                this.callbackManager.getGeofenceCallback().handleGeoFences(jSONObject2);
            } else {
                this.logger.debug(this.config.getAccountId(), "Geofences : Geofence SDK has not been initialized to handle the response");
            }
        } catch (Throwable th) {
            this.logger.verbose(this.config.getAccountId(), "Geofences : Failed to handle Geofences response", th);
        }
    }
}
