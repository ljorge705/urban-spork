package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ConsoleResponse extends CleverTapResponseDecorator {
    private final CleverTapInstanceConfig config;
    private final Logger logger;

    public ConsoleResponse(CleverTapInstanceConfig cleverTapInstanceConfig) {
        this.config = cleverTapInstanceConfig;
        this.logger = cleverTapInstanceConfig.getLogger();
    }

    @Override // com.clevertap.android.sdk.response.CleverTapResponseDecorator, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        int i;
        try {
            if (jSONObject.has("console")) {
                JSONArray jSONArray = (JSONArray) jSONObject.get("console");
                if (jSONArray.length() > 0) {
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        this.logger.debug(this.config.getAccountId(), jSONArray.get(i2).toString());
                    }
                }
            }
        } catch (Throwable unused) {
        }
        try {
            if (!jSONObject.has("dbg_lvl") || (i = jSONObject.getInt("dbg_lvl")) < 0) {
                return;
            }
            CleverTapAPI.setDebugLevel(i);
            this.logger.verbose(this.config.getAccountId(), "Set debug level to " + i + " for this session (set by upstream)");
        } catch (Throwable unused2) {
        }
    }
}
