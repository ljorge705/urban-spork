package com.clevertap.android.sdk.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import app.notifee.core.event.LogEvent;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CTLockManager;
import com.clevertap.android.sdk.CTXtensions;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.db.BaseDatabaseManager;
import com.clevertap.android.sdk.db.QueueData;
import com.clevertap.android.sdk.events.EventGroup;
import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplate;
import com.clevertap.android.sdk.inapp.evaluation.EventType;
import com.clevertap.android.sdk.interfaces.NotificationRenderedListener;
import com.clevertap.android.sdk.login.IdentityRepoFactory;
import com.clevertap.android.sdk.network.api.CtApiWrapper;
import com.clevertap.android.sdk.network.api.DefineTemplatesRequestBody;
import com.clevertap.android.sdk.network.api.SendQueueRequestBody;
import com.clevertap.android.sdk.network.http.Response;
import com.clevertap.android.sdk.pushnotification.PushNotificationUtil;
import com.clevertap.android.sdk.response.ARPResponse;
import com.clevertap.android.sdk.response.CleverTapResponse;
import com.clevertap.android.sdk.response.ConsoleResponse;
import com.clevertap.android.sdk.response.DisplayUnitResponse;
import com.clevertap.android.sdk.response.FeatureFlagResponse;
import com.clevertap.android.sdk.response.FetchVariablesResponse;
import com.clevertap.android.sdk.response.GeofenceResponse;
import com.clevertap.android.sdk.response.InAppResponse;
import com.clevertap.android.sdk.response.InboxResponse;
import com.clevertap.android.sdk.response.MetadataResponse;
import com.clevertap.android.sdk.response.ProductConfigResponse;
import com.clevertap.android.sdk.response.PushAmpResponse;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.utils.CTJsonConverter;
import com.clevertap.android.sdk.validation.ValidationResultStack;
import com.clevertap.android.sdk.validation.Validator;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class NetworkManager {
    private final BaseCallbackManager callbackManager;
    private final List<CleverTapResponse> cleverTapResponses;
    private final CleverTapInstanceConfig config;
    private final Context context;
    private final ControllerManager controllerManager;
    private final CoreMetaData coreMetaData;
    private final CtApiWrapper ctApiWrapper;
    private final BaseDatabaseManager databaseManager;
    private final DeviceInfo deviceInfo;
    private final Logger logger;
    private final List<NetworkHeadersListener> mNetworkHeadersListeners;
    private int minDelayFrequency;
    private int networkRetryCount;
    private int responseFailureCount;
    private final ValidationResultStack validationResultStack;
    private final Validator validator;

    public void addNetworkHeadersListener(NetworkHeadersListener networkHeadersListener) {
        this.mNetworkHeadersListeners.add(networkHeadersListener);
    }

    public void removeNetworkHeadersListener(NetworkHeadersListener networkHeadersListener) {
        this.mNetworkHeadersListeners.remove(networkHeadersListener);
    }

    public static boolean isNetworkOnline(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return true;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnected()) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public NetworkManager(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo, CoreMetaData coreMetaData, ValidationResultStack validationResultStack, ControllerManager controllerManager, BaseDatabaseManager baseDatabaseManager, BaseCallbackManager baseCallbackManager, CTLockManager cTLockManager, Validator validator, InAppResponse inAppResponse, CtApiWrapper ctApiWrapper) {
        ArrayList arrayList = new ArrayList();
        this.cleverTapResponses = arrayList;
        this.responseFailureCount = 0;
        this.networkRetryCount = 0;
        this.minDelayFrequency = 0;
        this.mNetworkHeadersListeners = new ArrayList();
        this.context = context;
        this.config = cleverTapInstanceConfig;
        this.deviceInfo = deviceInfo;
        this.callbackManager = baseCallbackManager;
        this.validator = validator;
        this.logger = cleverTapInstanceConfig.getLogger();
        this.coreMetaData = coreMetaData;
        this.validationResultStack = validationResultStack;
        this.controllerManager = controllerManager;
        this.databaseManager = baseDatabaseManager;
        this.ctApiWrapper = ctApiWrapper;
        arrayList.add(inAppResponse);
        arrayList.add(new MetadataResponse(cleverTapInstanceConfig, deviceInfo, this));
        arrayList.add(new ARPResponse(cleverTapInstanceConfig, this, validator, controllerManager));
        arrayList.add(new ConsoleResponse(cleverTapInstanceConfig));
        arrayList.add(new InboxResponse(cleverTapInstanceConfig, cTLockManager, baseCallbackManager, controllerManager));
        arrayList.add(new PushAmpResponse(context, cleverTapInstanceConfig, baseDatabaseManager, baseCallbackManager, controllerManager));
        arrayList.add(new FetchVariablesResponse(cleverTapInstanceConfig, controllerManager, baseCallbackManager));
        arrayList.add(new DisplayUnitResponse(cleverTapInstanceConfig, baseCallbackManager, controllerManager));
        arrayList.add(new FeatureFlagResponse(cleverTapInstanceConfig, controllerManager));
        arrayList.add(new ProductConfigResponse(cleverTapInstanceConfig, coreMetaData, controllerManager));
        arrayList.add(new GeofenceResponse(cleverTapInstanceConfig, baseCallbackManager));
    }

    public void flushDBQueue(Context context, EventGroup eventGroup, String str) throws JSONException {
        this.config.getLogger().verbose(this.config.getAccountId(), "Somebody has invoked me to send the queue to CleverTap servers");
        QueueData queueData = null;
        boolean z = true;
        while (z) {
            QueueData queuedEvents = this.databaseManager.getQueuedEvents(context, 50, queueData, eventGroup);
            if (queuedEvents == null || queuedEvents.isEmpty()) {
                this.config.getLogger().verbose(this.config.getAccountId(), "No events in the queue, failing");
                if (eventGroup != EventGroup.PUSH_NOTIFICATION_VIEWED || queueData == null || queueData.getData() == null) {
                    return;
                }
                try {
                    notifyListenersForPushImpressionSentToServer(queueData.getData());
                    return;
                } catch (Exception unused) {
                    this.config.getLogger().verbose(this.config.getAccountId(), "met with exception while notifying listeners for PushImpressionSentToServer event");
                    return;
                }
            }
            JSONArray data = queuedEvents.getData();
            if (data == null || data.length() <= 0) {
                this.config.getLogger().verbose(this.config.getAccountId(), "No events in the queue, failing");
                return;
            }
            boolean zSendQueue = sendQueue(context, eventGroup, data, str);
            if (!zSendQueue) {
                this.controllerManager.invokeCallbacksForNetworkError();
                this.controllerManager.invokeBatchListener(data, false);
            } else {
                this.controllerManager.invokeBatchListener(data, true);
            }
            queueData = queuedEvents;
            z = zSendQueue;
        }
    }

    public int getDelayFrequency() {
        this.logger.debug(this.config.getAccountId(), "Network retry #" + this.networkRetryCount);
        if (this.networkRetryCount < 10) {
            this.logger.debug(this.config.getAccountId(), "Failure count is " + this.networkRetryCount + ". Setting delay frequency to 1s");
            this.minDelayFrequency = 1000;
            return 1000;
        }
        if (this.config.getAccountRegion() == null) {
            this.logger.debug(this.config.getAccountId(), "Setting delay frequency to 1s");
            return 1000;
        }
        int iNextInt = this.minDelayFrequency + ((new SecureRandom().nextInt(10) + 1) * 1000);
        this.minDelayFrequency = iNextInt;
        if (iNextInt < 600000) {
            this.logger.debug(this.config.getAccountId(), "Setting delay frequency to " + this.minDelayFrequency);
            return this.minDelayFrequency;
        }
        this.minDelayFrequency = 1000;
        this.logger.debug(this.config.getAccountId(), "Setting delay frequency to " + this.minDelayFrequency);
        return this.minDelayFrequency;
    }

    public String getNewNamespaceARPKey() {
        String accountId = this.config.getAccountId();
        if (accountId == null) {
            return null;
        }
        this.logger.verbose(this.config.getAccountId(), "New ARP Key = ARP:" + accountId + ":" + this.deviceInfo.getDeviceID());
        return "ARP:" + accountId + ":" + this.deviceInfo.getDeviceID();
    }

    public void initHandshake(EventGroup eventGroup, Runnable runnable) {
        this.responseFailureCount = 0;
        performHandshakeForDomain(this.context, eventGroup, runnable);
    }

    public boolean needsHandshakeForDomain(EventGroup eventGroup) {
        boolean zNeedsHandshake = this.ctApiWrapper.needsHandshake(eventGroup == EventGroup.PUSH_NOTIFICATION_VIEWED);
        boolean z = this.responseFailureCount > 5;
        if (z) {
            setDomain(this.context, null);
        }
        return zNeedsHandshake || z;
    }

    public void setI(Context context, long j) {
        SharedPreferences.Editor editorEdit = StorageHelper.getPreferences(context, Constants.NAMESPACE_IJ).edit();
        editorEdit.putLong(StorageHelper.storageKeyWithSuffix(this.config, Constants.KEY_I), j);
        StorageHelper.persist(editorEdit);
    }

    public void setJ(Context context, long j) {
        SharedPreferences.Editor editorEdit = StorageHelper.getPreferences(context, Constants.NAMESPACE_IJ).edit();
        editorEdit.putLong(StorageHelper.storageKeyWithSuffix(this.config, Constants.KEY_J), j);
        StorageHelper.persist(editorEdit);
    }

    int getCurrentRequestTimestamp() {
        return this.ctApiWrapper.getCtApi().getCurrentRequestTimestampSeconds();
    }

    public String getDomain(EventGroup eventGroup) {
        return this.ctApiWrapper.getCtApi().getActualDomain(eventGroup == EventGroup.PUSH_NOTIFICATION_VIEWED);
    }

    int getFirstRequestTimestamp() {
        return StorageHelper.getIntFromPrefs(this.context, this.config, Constants.KEY_FIRST_TS, 0);
    }

    int getLastRequestTimestamp() {
        return StorageHelper.getIntFromPrefs(this.context, this.config, Constants.KEY_LAST_TS, 0);
    }

    void setLastRequestTimestamp(int i) {
        StorageHelper.putInt(this.context, StorageHelper.storageKeyWithSuffix(this.config, Constants.KEY_LAST_TS), i);
    }

    boolean hasDomainChanged(String str) {
        return !str.equals(StorageHelper.getStringFromPrefs(this.context, this.config, Constants.KEY_DOMAIN_NAME, null));
    }

    private JSONObject getQueueHeader(Context context, String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            if (str != null) {
                jSONObject.put(Constants.D_SRC, str);
            }
            String deviceID = this.deviceInfo.getDeviceID();
            if (deviceID != null && !deviceID.equals("")) {
                jSONObject.put("g", deviceID);
            } else {
                this.logger.verbose(this.config.getAccountId(), "CRITICAL: Couldn't finalise on a device ID! Using error device ID instead!");
            }
            jSONObject.put("type", "meta");
            JSONObject appLaunchedFields = this.deviceInfo.getAppLaunchedFields();
            if (this.coreMetaData.isWebInterfaceInitializedExternally()) {
                appLaunchedFields.put("wv_init", true);
            }
            jSONObject.put("af", appLaunchedFields);
            long i = getI();
            if (i > 0) {
                jSONObject.put("_i", i);
            }
            long j = getJ();
            if (j > 0) {
                jSONObject.put("_j", j);
            }
            String accountId = this.config.getAccountId();
            String accountToken = this.config.getAccountToken();
            if (accountId != null && accountToken != null) {
                jSONObject.put("id", accountId);
                jSONObject.put("tk", accountToken);
                jSONObject.put("l_ts", getLastRequestTimestamp());
                jSONObject.put("f_ts", getFirstRequestTimestamp());
                jSONObject.put("ct_pi", IdentityRepoFactory.getRepo(this.context, this.config, this.deviceInfo, this.validationResultStack).getIdentitySet().toString());
                jSONObject.put("ddnd", (CTXtensions.areAppNotificationsEnabled(this.context) && (this.controllerManager.getPushProviders() == null || this.controllerManager.getPushProviders().isNotificationSupported())) ? false : true);
                if (this.coreMetaData.isBgPing()) {
                    jSONObject.put("bk", 1);
                    this.coreMetaData.setBgPing(false);
                }
                jSONObject.put("rtl", CTJsonConverter.getRenderedTargetList(this.databaseManager.loadDBAdapter(this.context)));
                if (!this.coreMetaData.isInstallReferrerDataSent()) {
                    jSONObject.put("rct", this.coreMetaData.getReferrerClickTime());
                    jSONObject.put("ait", this.coreMetaData.getAppInstallTime());
                }
                jSONObject.put("frs", this.coreMetaData.isFirstRequestInSession());
                if (CleverTapAPI.getDebugLevel() == 3) {
                    jSONObject.put(LogEvent.LEVEL_DEBUG, true);
                }
                this.coreMetaData.setFirstRequestInSession(false);
                try {
                    JSONObject arp = getARP();
                    if (arp != null && arp.length() > 0) {
                        jSONObject.put("arp", arp);
                    }
                } catch (JSONException e) {
                    this.logger.verbose(this.config.getAccountId(), "Failed to attach ARP", e);
                }
                JSONObject jSONObject2 = new JSONObject();
                try {
                    String source = this.coreMetaData.getSource();
                    if (source != null) {
                        jSONObject2.put("us", source);
                    }
                    String medium = this.coreMetaData.getMedium();
                    if (medium != null) {
                        jSONObject2.put("um", medium);
                    }
                    String campaign = this.coreMetaData.getCampaign();
                    if (campaign != null) {
                        jSONObject2.put("uc", campaign);
                    }
                    if (jSONObject2.length() > 0) {
                        jSONObject.put("ref", jSONObject2);
                    }
                } catch (JSONException e2) {
                    this.logger.verbose(this.config.getAccountId(), "Failed to attach ref", e2);
                }
                JSONObject wzrkParams = this.coreMetaData.getWzrkParams();
                if (wzrkParams != null && wzrkParams.length() > 0) {
                    jSONObject.put("wzrk_ref", wzrkParams);
                }
                if (this.controllerManager.getInAppFCManager() != null) {
                    Logger.v("Attaching InAppFC to Header");
                    jSONObject.put(Constants.INAPP_MAX_PER_DAY_KEY, this.controllerManager.getInAppFCManager().getShownTodayCount());
                    jSONObject.put(Constants.KEY_TLC, this.controllerManager.getInAppFCManager().getInAppsCount(context));
                } else {
                    this.logger.verbose(this.config.getAccountId(), "controllerManager.getInAppFCManager() is NULL, not Attaching InAppFC to Header");
                }
                return jSONObject;
            }
            this.logger.debug(this.config.getAccountId(), "Account ID/token not found, unable to configure queue request");
            return null;
        } catch (JSONException e3) {
            this.logger.verbose(this.config.getAccountId(), "CommsManager: Failed to attach header", e3);
            return null;
        }
    }

    private void performHandshakeForDomain(Context context, EventGroup eventGroup, Runnable runnable) {
        try {
            Response responsePerformHandshakeForDomain = this.ctApiWrapper.getCtApi().performHandshakeForDomain(eventGroup == EventGroup.PUSH_NOTIFICATION_VIEWED);
            try {
                if (responsePerformHandshakeForDomain.isSuccess()) {
                    this.logger.verbose(this.config.getAccountId(), "Received success from handshake :)");
                    if (processIncomingHeaders(context, responsePerformHandshakeForDomain)) {
                        this.logger.verbose(this.config.getAccountId(), "We are not muted");
                        runnable.run();
                    }
                } else {
                    this.logger.verbose(this.config.getAccountId(), "Invalid HTTP status code received for handshake - " + responsePerformHandshakeForDomain.getCode());
                }
                if (responsePerformHandshakeForDomain != null) {
                    responsePerformHandshakeForDomain.close();
                }
            } finally {
            }
        } catch (Exception e) {
            this.logger.verbose(this.config.getAccountId(), "Failed to perform handshake!", e);
        }
    }

    private boolean processIncomingHeaders(Context context, Response response) {
        String headerValue = response.getHeaderValue(Constants.HEADER_MUTE);
        if (headerValue != null && headerValue.trim().length() > 0) {
            if (headerValue.equals("true")) {
                setMuted(context, true);
                return false;
            }
            setMuted(context, false);
        }
        String headerValue2 = response.getHeaderValue(Constants.HEADER_DOMAIN_NAME);
        Logger.v("Getting domain from header - " + headerValue2);
        if (headerValue2 != null && headerValue2.trim().length() != 0) {
            String headerValue3 = response.getHeaderValue(Constants.SPIKY_HEADER_DOMAIN_NAME);
            Logger.v("Getting spiky domain from header - " + headerValue3);
            setMuted(context, false);
            setDomain(context, headerValue2);
            Logger.v("Setting spiky domain from header as -" + headerValue3);
            if (headerValue3 == null) {
                setSpikyDomain(context, headerValue2);
            } else {
                setSpikyDomain(context, headerValue3);
            }
        }
        return true;
    }

    public boolean sendQueue(Context context, EventGroup eventGroup, JSONArray jSONArray, String str) throws JSONException {
        boolean zHandleSendQueueResponse;
        if (jSONArray != null && jSONArray.length() > 0) {
            if (this.deviceInfo.getDeviceID() == null) {
                this.logger.debug(this.config.getAccountId(), "CleverTap Id not finalized, unable to send queue");
                return false;
            }
            EndpointId endpointIdFromEventGroup = EndpointId.fromEventGroup(eventGroup);
            JSONObject queueHeader = getQueueHeader(context, str);
            applyQueueHeaderListeners(queueHeader, endpointIdFromEventGroup, jSONArray.optJSONObject(0).has("profile"));
            SendQueueRequestBody sendQueueRequestBody = new SendQueueRequestBody(queueHeader, jSONArray);
            this.logger.debug(this.config.getAccountId(), "Send queue contains " + jSONArray.length() + " items: " + sendQueueRequestBody);
            try {
                Response responseCallApiForEventGroup = callApiForEventGroup(eventGroup, sendQueueRequestBody);
                try {
                    this.networkRetryCount = 0;
                    if (eventGroup == EventGroup.VARIABLES) {
                        zHandleSendQueueResponse = handleVariablesResponse(responseCallApiForEventGroup);
                    } else {
                        zHandleSendQueueResponse = handleSendQueueResponse(responseCallApiForEventGroup, sendQueueRequestBody, endpointIdFromEventGroup);
                    }
                    if (zHandleSendQueueResponse) {
                        this.responseFailureCount = 0;
                    } else {
                        this.responseFailureCount++;
                    }
                    if (responseCallApiForEventGroup != null) {
                        responseCallApiForEventGroup.close();
                    }
                    return zHandleSendQueueResponse;
                } finally {
                }
            } catch (Exception e) {
                this.networkRetryCount++;
                this.responseFailureCount++;
                this.logger.debug(this.config.getAccountId(), "An exception occurred while sending the queue, will retry: ", e);
                if (this.callbackManager.getFailureFlushListener() != null) {
                    this.callbackManager.getFailureFlushListener().failureFlush(context);
                }
            }
        }
        return false;
    }

    public boolean defineTemplates(Context context, Collection<CustomTemplate> collection) {
        JSONObject queueHeader = getQueueHeader(context, null);
        if (queueHeader == null) {
            return false;
        }
        DefineTemplatesRequestBody defineTemplatesRequestBody = new DefineTemplatesRequestBody(queueHeader, collection);
        this.logger.debug(this.config.getAccountId(), "Will define templates: " + defineTemplatesRequestBody);
        try {
            Response responseDefineTemplates = this.ctApiWrapper.getCtApi().defineTemplates(defineTemplatesRequestBody);
            try {
                if (responseDefineTemplates.isSuccess()) {
                    handleTemplateResponseSuccess(responseDefineTemplates);
                    if (responseDefineTemplates == null) {
                        return true;
                    }
                    responseDefineTemplates.close();
                    return true;
                }
                handleVarsOrTemplatesResponseError(responseDefineTemplates, "CustomTemplates");
                if (responseDefineTemplates != null) {
                    responseDefineTemplates.close();
                }
                return false;
            } finally {
            }
        } catch (Exception e) {
            this.logger.debug(this.config.getAccountId(), "An exception occurred while defining templates.", e);
            return false;
        }
    }

    private void applyQueueHeaderListeners(JSONObject jSONObject, EndpointId endpointId, boolean z) {
        if (jSONObject != null) {
            Iterator<NetworkHeadersListener> it = this.mNetworkHeadersListeners.iterator();
            while (it.hasNext()) {
                JSONObject jSONObjectOnAttachHeaders = it.next().onAttachHeaders(endpointId, EventType.INSTANCE.fromBoolean(z));
                if (jSONObjectOnAttachHeaders != null) {
                    CTXtensions.copyFrom(jSONObject, jSONObjectOnAttachHeaders);
                }
            }
        }
    }

    private Response callApiForEventGroup(EventGroup eventGroup, SendQueueRequestBody sendQueueRequestBody) {
        if (eventGroup == EventGroup.VARIABLES) {
            return this.ctApiWrapper.getCtApi().defineVars(sendQueueRequestBody);
        }
        return this.ctApiWrapper.getCtApi().sendQueue(eventGroup == EventGroup.PUSH_NOTIFICATION_VIEWED, sendQueueRequestBody);
    }

    private boolean handleVariablesResponse(Response response) {
        if (response.isSuccess()) {
            String body = response.readBody();
            JSONObject jsonOrNull = CTXtensions.toJsonOrNull(body);
            this.logger.verbose(this.config.getAccountId(), "Processing variables response : " + jsonOrNull);
            new ARPResponse(this.config, this, this.validator, this.controllerManager).processResponse(jsonOrNull, body, this.context);
            return true;
        }
        handleVarsOrTemplatesResponseError(response, "Variables");
        return false;
    }

    private void handleVarsOrTemplatesResponseError(Response response, String str) {
        int code = response.getCode();
        if (code != 400) {
            if (code == 401) {
                this.logger.info(str, "Unauthorized access from a non-test profile. Please mark this profile as a test profile from the CleverTap dashboard.");
                return;
            } else {
                this.logger.info(str, "Response code " + response.getCode() + " while syncing.");
                return;
            }
        }
        JSONObject jsonOrNull = CTXtensions.toJsonOrNull(response.readBody());
        if (jsonOrNull != null && !TextUtils.isEmpty(jsonOrNull.optString("error"))) {
            this.logger.info(str, "Error while syncing: " + jsonOrNull.optString("error"));
        } else {
            this.logger.info(str, "Error while syncing.");
        }
    }

    private void handleTemplateResponseSuccess(Response response) {
        this.logger.info(this.config.getAccountId(), "Custom templates defined successfully.");
        JSONObject jsonOrNull = CTXtensions.toJsonOrNull(response.readBody());
        if (jsonOrNull != null) {
            String strOptString = jsonOrNull.optString("error");
            if (TextUtils.isEmpty(strOptString)) {
                return;
            }
            this.logger.info(this.config.getAccountId(), "Custom templates warnings: " + strOptString);
        }
    }

    private boolean handleSendQueueResponse(Response response, SendQueueRequestBody sendQueueRequestBody, EndpointId endpointId) throws JSONException {
        if (!response.isSuccess()) {
            handleSendQueueResponseError(response);
            return false;
        }
        String headerValue = response.getHeaderValue(Constants.HEADER_DOMAIN_NAME);
        if (headerValue != null && !headerValue.trim().isEmpty() && hasDomainChanged(headerValue)) {
            setDomain(this.context, headerValue);
            this.logger.debug(this.config.getAccountId(), "The domain has changed to " + headerValue + ". The request will be retried shortly.");
            return false;
        }
        if (sendQueueRequestBody.getQueueHeader() != null) {
            Iterator<NetworkHeadersListener> it = this.mNetworkHeadersListeners.iterator();
            while (it.hasNext()) {
                it.next().onSentHeaders(sendQueueRequestBody.getQueueHeader(), endpointId, EventType.INSTANCE.fromBoolean(sendQueueRequestBody.getQueue().optJSONObject(0).has("profile")));
            }
        }
        if (!processIncomingHeaders(this.context, response)) {
            return false;
        }
        this.logger.debug(this.config.getAccountId(), "Queue sent successfully");
        setLastRequestTimestamp(getCurrentRequestTimestamp());
        setFirstRequestTimestampIfNeeded(getCurrentRequestTimestamp());
        String body = response.readBody();
        JSONObject jsonOrNull = CTXtensions.toJsonOrNull(body);
        this.logger.verbose(this.config.getAccountId(), "Processing response : " + jsonOrNull);
        boolean zDoesBodyContainAppLaunchedOrFetchEvents = doesBodyContainAppLaunchedOrFetchEvents(sendQueueRequestBody);
        for (CleverTapResponse cleverTapResponse : this.cleverTapResponses) {
            cleverTapResponse.isFullResponse = zDoesBodyContainAppLaunchedOrFetchEvents;
            cleverTapResponse.processResponse(jsonOrNull, body, this.context);
        }
        return true;
    }

    private void handleSendQueueResponseError(Response response) {
        this.logger.info("Received error response code: " + response.getCode());
    }

    private boolean doesBodyContainAppLaunchedOrFetchEvents(SendQueueRequestBody sendQueueRequestBody) throws JSONException {
        for (int i = 0; i < sendQueueRequestBody.getQueue().length(); i++) {
            try {
                JSONObject jSONObject = sendQueueRequestBody.getQueue().getJSONObject(i);
                if ("event".equals(jSONObject.getString("type"))) {
                    String string = jSONObject.getString(Constants.KEY_EVT_NAME);
                    if (Constants.APP_LAUNCHED_EVENT.equals(string) || Constants.WZRK_FETCH.equals(string)) {
                        return true;
                    }
                } else {
                    continue;
                }
            } catch (JSONException unused) {
            }
        }
        return false;
    }

    private void notifyListenersForPushImpressionSentToServer(JSONArray jSONArray) throws JSONException {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObjectOptJSONObject = jSONArray.getJSONObject(i).optJSONObject(Constants.KEY_EVT_DATA);
                if (jSONObjectOptJSONObject != null) {
                    notifyListenerForPushImpressionSentToServer(PushNotificationUtil.buildPushNotificationRenderedListenerKey(jSONObjectOptJSONObject.optString(Constants.WZRK_ACCT_ID_KEY), jSONObjectOptJSONObject.optString(Constants.WZRK_PUSH_ID)));
                }
            } catch (JSONException unused) {
                this.logger.verbose(this.config.getAccountId(), "Encountered an exception while parsing the push notification viewed event queue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.logger.verbose(this.config.getAccountId(), "push notification viewed event sent successfully");
    }

    private void notifyListenerForPushImpressionSentToServer(String str) {
        NotificationRenderedListener notificationRenderedListener = CleverTapAPI.getNotificationRenderedListener(str);
        if (notificationRenderedListener != null) {
            this.logger.verbose(this.config.getAccountId(), "notifying listener " + str + ", that push impression sent successfully");
            notificationRenderedListener.onNotificationRendered(true);
        }
    }

    private void setDomain(Context context, String str) {
        this.logger.verbose(this.config.getAccountId(), "Setting domain to " + str);
        StorageHelper.putString(context, StorageHelper.storageKeyWithSuffix(this.config, Constants.KEY_DOMAIN_NAME), str);
        this.ctApiWrapper.getCtApi().setCachedDomain(str);
        if (this.callbackManager.getSCDomainListener() != null) {
            if (str != null) {
                this.callbackManager.getSCDomainListener().onSCDomainAvailable(Utils.getSCDomain(str));
            } else {
                this.callbackManager.getSCDomainListener().onSCDomainUnavailable();
            }
        }
    }

    private void setFirstRequestTimestampIfNeeded(int i) {
        if (getFirstRequestTimestamp() > 0) {
            return;
        }
        StorageHelper.putInt(this.context, StorageHelper.storageKeyWithSuffix(this.config, Constants.KEY_FIRST_TS), i);
    }

    private void setSpikyDomain(Context context, String str) {
        this.logger.verbose(this.config.getAccountId(), "Setting spiky domain to " + str);
        StorageHelper.putString(context, StorageHelper.storageKeyWithSuffix(this.config, Constants.SPIKY_KEY_DOMAIN_NAME), str);
        this.ctApiWrapper.getCtApi().setCachedSpikyDomain(str);
    }

    private JSONObject getARP() {
        SharedPreferences sharedPreferencesMigrateARPToNewNameSpace;
        try {
            String newNamespaceARPKey = getNewNamespaceARPKey();
            if (newNamespaceARPKey == null) {
                return null;
            }
            if (!StorageHelper.getPreferences(this.context, newNamespaceARPKey).getAll().isEmpty()) {
                sharedPreferencesMigrateARPToNewNameSpace = StorageHelper.getPreferences(this.context, newNamespaceARPKey);
            } else {
                sharedPreferencesMigrateARPToNewNameSpace = migrateARPToNewNameSpace(newNamespaceARPKey, getNamespaceARPKey());
            }
            Map<String, ?> all = sharedPreferencesMigrateARPToNewNameSpace.getAll();
            Iterator<Map.Entry<String, ?>> it = all.entrySet().iterator();
            while (it.hasNext()) {
                Object value = it.next().getValue();
                if ((value instanceof Number) && ((Number) value).intValue() == -1) {
                    it.remove();
                }
            }
            JSONObject jSONObject = new JSONObject(all);
            this.logger.verbose(this.config.getAccountId(), "Fetched ARP for namespace key: " + newNamespaceARPKey + " values: " + all);
            return jSONObject;
        } catch (Exception e) {
            this.logger.verbose(this.config.getAccountId(), "Failed to construct ARP object", e);
            return null;
        }
    }

    private long getI() {
        return StorageHelper.getLongFromPrefs(this.context, this.config, Constants.KEY_I, 0, Constants.NAMESPACE_IJ);
    }

    private long getJ() {
        return StorageHelper.getLongFromPrefs(this.context, this.config, Constants.KEY_J, 0, Constants.NAMESPACE_IJ);
    }

    private String getNamespaceARPKey() {
        String accountId = this.config.getAccountId();
        if (accountId == null) {
            return null;
        }
        this.logger.verbose(this.config.getAccountId(), "Old ARP Key = ARP:" + accountId);
        return "ARP:" + accountId;
    }

    private SharedPreferences migrateARPToNewNameSpace(String str, String str2) {
        SharedPreferences preferences = StorageHelper.getPreferences(this.context, str2);
        SharedPreferences preferences2 = StorageHelper.getPreferences(this.context, str);
        SharedPreferences.Editor editorEdit = preferences2.edit();
        for (Map.Entry<String, ?> entry : preferences.getAll().entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Number) {
                editorEdit.putInt(entry.getKey(), ((Number) value).intValue());
            } else if (value instanceof String) {
                String str3 = (String) value;
                if (str3.length() < 100) {
                    editorEdit.putString(entry.getKey(), str3);
                } else {
                    this.logger.verbose(this.config.getAccountId(), "ARP update for key " + entry.getKey() + " rejected (string value too long)");
                }
            } else if (value instanceof Boolean) {
                editorEdit.putBoolean(entry.getKey(), ((Boolean) value).booleanValue());
            } else {
                this.logger.verbose(this.config.getAccountId(), "ARP update for key " + entry.getKey() + " rejected (invalid data type)");
            }
        }
        this.logger.verbose(this.config.getAccountId(), "Completed ARP update for namespace key: " + str);
        StorageHelper.persist(editorEdit);
        preferences.edit().clear().apply();
        return preferences2;
    }

    private void setMuted(final Context context, boolean z) {
        if (z) {
            StorageHelper.putInt(context, StorageHelper.storageKeyWithSuffix(this.config, Constants.KEY_MUTED), (int) (System.currentTimeMillis() / 1000));
            setDomain(context, null);
            CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("CommsManager#setMuted", new Callable() { // from class: com.clevertap.android.sdk.network.NetworkManager$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.m4802x5b19c3e(context);
                }
            });
            return;
        }
        StorageHelper.putInt(context, StorageHelper.storageKeyWithSuffix(this.config, Constants.KEY_MUTED), 0);
    }

    /* renamed from: lambda$setMuted$0$com-clevertap-android-sdk-network-NetworkManager, reason: not valid java name */
    /* synthetic */ Void m4802x5b19c3e(Context context) throws Exception {
        this.databaseManager.clearQueues(context);
        return null;
    }
}
