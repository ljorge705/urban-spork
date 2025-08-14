package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CTLockManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class InboxResponse extends CleverTapResponseDecorator {
    private final BaseCallbackManager callbackManager;
    private final CleverTapInstanceConfig config;
    private final ControllerManager controllerManager;
    private final Object inboxControllerLock;
    private final Logger logger;

    public InboxResponse(CleverTapInstanceConfig cleverTapInstanceConfig, CTLockManager cTLockManager, BaseCallbackManager baseCallbackManager, ControllerManager controllerManager) {
        this.config = cleverTapInstanceConfig;
        this.callbackManager = baseCallbackManager;
        this.logger = cleverTapInstanceConfig.getLogger();
        this.inboxControllerLock = cTLockManager.getInboxControllerLock();
        this.controllerManager = controllerManager;
    }

    @Override // com.clevertap.android.sdk.response.CleverTapResponseDecorator, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        if (this.config.isAnalyticsOnly()) {
            this.logger.verbose(this.config.getAccountId(), "CleverTap instance is configured to analytics only, not processing inbox messages");
            return;
        }
        this.logger.verbose(this.config.getAccountId(), "Inbox: Processing response");
        if (!jSONObject.has(Constants.INBOX_JSON_RESPONSE_KEY)) {
            this.logger.verbose(this.config.getAccountId(), "Inbox: Response JSON object doesn't contain the inbox key");
            return;
        }
        try {
            _processInboxMessages(jSONObject.getJSONArray(Constants.INBOX_JSON_RESPONSE_KEY));
        } catch (Throwable th) {
            this.logger.verbose(this.config.getAccountId(), "InboxResponse: Failed to parse response", th);
        }
    }

    private void _processInboxMessages(JSONArray jSONArray) {
        synchronized (this.inboxControllerLock) {
            if (this.controllerManager.getCTInboxController() == null) {
                this.controllerManager.initializeInbox();
            }
            if (this.controllerManager.getCTInboxController() != null && this.controllerManager.getCTInboxController().updateMessages(jSONArray)) {
                this.callbackManager._notifyInboxMessagesDidUpdate();
            }
        }
    }
}
