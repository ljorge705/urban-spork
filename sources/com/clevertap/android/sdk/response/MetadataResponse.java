package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.network.NetworkManager;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class MetadataResponse extends CleverTapResponseDecorator {
    private final CleverTapInstanceConfig config;
    private final DeviceInfo deviceInfo;
    private final Logger logger;
    private final NetworkManager networkManager;

    public MetadataResponse(CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo, NetworkManager networkManager) {
        this.config = cleverTapInstanceConfig;
        this.logger = cleverTapInstanceConfig.getLogger();
        this.deviceInfo = deviceInfo;
        this.networkManager = networkManager;
    }

    @Override // com.clevertap.android.sdk.response.CleverTapResponseDecorator, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        try {
            if (jSONObject.has("g")) {
                String string = jSONObject.getString("g");
                this.deviceInfo.forceUpdateDeviceId(string);
                this.logger.verbose(this.config.getAccountId(), "Got a new device ID: " + string);
            }
        } catch (Throwable th) {
            this.logger.verbose(this.config.getAccountId(), "Failed to update device ID!", th);
        }
        try {
            if (jSONObject.has("_i")) {
                this.networkManager.setI(context, jSONObject.getLong("_i"));
            }
        } catch (Throwable unused) {
        }
        try {
            if (jSONObject.has("_j")) {
                this.networkManager.setJ(context, jSONObject.getLong("_j"));
            }
        } catch (Throwable unused2) {
        }
    }
}
