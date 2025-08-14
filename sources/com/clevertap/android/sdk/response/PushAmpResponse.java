package com.clevertap.android.sdk.response;

import android.content.Context;
import android.os.Bundle;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.db.BaseDatabaseManager;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.clevertap.android.sdk.pushnotification.PushNotificationHandler;
import com.clevertap.android.sdk.utils.CTJsonConverter;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class PushAmpResponse extends CleverTapResponseDecorator {
    private final BaseDatabaseManager baseDatabaseManager;
    private final BaseCallbackManager callbackManager;
    private final CleverTapInstanceConfig config;
    private final Context context;
    private final ControllerManager controllerManager;
    private final Logger logger;

    public PushAmpResponse(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, BaseDatabaseManager baseDatabaseManager, BaseCallbackManager baseCallbackManager, ControllerManager controllerManager) {
        this.context = context;
        this.config = cleverTapInstanceConfig;
        this.logger = cleverTapInstanceConfig.getLogger();
        this.baseDatabaseManager = baseDatabaseManager;
        this.callbackManager = baseCallbackManager;
        this.controllerManager = controllerManager;
    }

    @Override // com.clevertap.android.sdk.response.CleverTapResponseDecorator, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        if (this.config.isAnalyticsOnly()) {
            this.logger.verbose(this.config.getAccountId(), "CleverTap instance is configured to analytics only, not processing push amp response");
            return;
        }
        try {
            if (jSONObject.has("pushamp_notifs")) {
                this.logger.verbose(this.config.getAccountId(), "Processing pushamp messages...");
                JSONObject jSONObject2 = jSONObject.getJSONObject("pushamp_notifs");
                JSONArray jSONArray = jSONObject2.getJSONArray("list");
                if (jSONArray.length() > 0) {
                    this.logger.verbose(this.config.getAccountId(), "Handling Push payload locally");
                    handlePushNotificationsInResponse(jSONArray);
                }
                if (jSONObject2.has("pf")) {
                    try {
                        this.controllerManager.getPushProviders().updatePingFrequencyIfNeeded(context, jSONObject2.getInt("pf"));
                    } catch (Throwable th) {
                        this.logger.verbose("Error handling ping frequency in response : " + th.getMessage());
                    }
                }
                if (jSONObject2.has("ack")) {
                    boolean z = jSONObject2.getBoolean("ack");
                    this.logger.verbose("Received ACK -" + z);
                    if (z) {
                        JSONArray renderedTargetList = CTJsonConverter.getRenderedTargetList(this.baseDatabaseManager.loadDBAdapter(context));
                        String[] strArr = new String[0];
                        if (renderedTargetList != null) {
                            strArr = new String[renderedTargetList.length()];
                        }
                        for (int i = 0; i < strArr.length; i++) {
                            strArr[i] = renderedTargetList.getString(i);
                        }
                        this.logger.verbose("Updating RTL values...");
                        this.baseDatabaseManager.loadDBAdapter(context).updatePushNotificationIds(strArr);
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    private void handlePushNotificationsInResponse(JSONArray jSONArray) throws JSONException {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                Bundle bundle = new Bundle();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has("wzrk_ttl")) {
                    bundle.putLong("wzrk_ttl", jSONObject.getLong("wzrk_ttl"));
                }
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String string = itKeys.next().toString();
                    bundle.putString(string, jSONObject.getString(string));
                }
                if (!bundle.isEmpty() && !this.baseDatabaseManager.loadDBAdapter(this.context).doesPushNotificationIdExist(jSONObject.getString(Constants.WZRK_PUSH_ID))) {
                    this.logger.verbose("Creating Push Notification locally");
                    if (this.callbackManager.getPushAmpListener() != null) {
                        this.callbackManager.getPushAmpListener().onPushAmpPayloadReceived(bundle);
                    } else {
                        PushNotificationHandler.getPushNotificationHandler().onMessageReceived(this.context, bundle, PushConstants.PushType.FCM.toString());
                    }
                } else {
                    this.logger.verbose(this.config.getAccountId(), "Push Notification already shown, ignoring local notification :" + jSONObject.getString(Constants.WZRK_PUSH_ID));
                }
            } catch (JSONException unused) {
                this.logger.verbose(this.config.getAccountId(), "Error parsing push notification JSON");
                return;
            }
        }
    }
}
