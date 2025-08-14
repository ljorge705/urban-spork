package com.clevertap.android.sdk;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.FrameMetricsAggregator;
import com.clevertap.android.sdk.db.Column;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.clevertap.android.sdk.events.BaseEventQueueManager;
import com.clevertap.android.sdk.inapp.CTInAppNotification;
import com.clevertap.android.sdk.inbox.CTInboxMessage;
import com.clevertap.android.sdk.response.DisplayUnitResponse;
import com.clevertap.android.sdk.response.InAppResponse;
import com.clevertap.android.sdk.response.InboxResponse;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.utils.CTJsonConverter;
import com.clevertap.android.sdk.utils.Clock;
import com.clevertap.android.sdk.utils.UriHelper;
import com.clevertap.android.sdk.validation.ValidationResult;
import com.clevertap.android.sdk.validation.ValidationResultFactory;
import com.clevertap.android.sdk.validation.ValidationResultStack;
import com.clevertap.android.sdk.validation.Validator;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class AnalyticsManager extends BaseAnalyticsManager {
    private final BaseEventQueueManager baseEventQueueManager;
    private final BaseCallbackManager callbackManager;
    private final CleverTapInstanceConfig config;
    private final Context context;
    private final ControllerManager controllerManager;
    private final CoreMetaData coreMetaData;
    private final CTLockManager ctLockManager;
    private final Clock currentTimeProvider;
    private final DeviceInfo deviceInfo;
    private final InAppResponse inAppResponse;
    private final ValidationResultStack validationResultStack;
    private final Validator validator;
    private final HashMap<String, Integer> installReferrerMap = new HashMap<>(8);
    private final Object notificationMapLock = new Object();
    private final HashMap<String, Long> notificationIdTagMap = new HashMap<>();
    private final HashMap<String, Long> notificationViewedIdTagMap = new HashMap<>();

    AnalyticsManager(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, BaseEventQueueManager baseEventQueueManager, Validator validator, ValidationResultStack validationResultStack, CoreMetaData coreMetaData, DeviceInfo deviceInfo, BaseCallbackManager baseCallbackManager, ControllerManager controllerManager, CTLockManager cTLockManager, InAppResponse inAppResponse, Clock clock) {
        this.context = context;
        this.config = cleverTapInstanceConfig;
        this.baseEventQueueManager = baseEventQueueManager;
        this.validator = validator;
        this.validationResultStack = validationResultStack;
        this.coreMetaData = coreMetaData;
        this.deviceInfo = deviceInfo;
        this.callbackManager = baseCallbackManager;
        this.ctLockManager = cTLockManager;
        this.controllerManager = controllerManager;
        this.inAppResponse = inAppResponse;
        this.currentTimeProvider = clock;
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void addMultiValuesForKey(final String str, final ArrayList<String> arrayList) {
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("addMultiValuesForKey", new Callable<Void>() { // from class: com.clevertap.android.sdk.AnalyticsManager.1
            @Override // java.util.concurrent.Callable
            public Void call() {
                AnalyticsManager.this._handleMultiValues(arrayList, str, Constants.COMMAND_ADD);
                return null;
            }
        });
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void incrementValue(String str, Number number) {
        _constructIncrementDecrementValues(number, str, Constants.COMMAND_INCREMENT);
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void decrementValue(String str, Number number) {
        _constructIncrementDecrementValues(number, str, Constants.COMMAND_DECREMENT);
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void fetchFeatureFlags() {
        if (this.config.isAnalyticsOnly()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("t", 1);
            jSONObject.put(Constants.KEY_EVT_NAME, Constants.WZRK_FETCH);
            jSONObject.put(Constants.KEY_EVT_DATA, jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendFetchEvent(jSONObject);
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void forcePushAppLaunchedEvent() {
        this.coreMetaData.setAppLaunchPushed(false);
        pushAppLaunchedEvent();
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushAppLaunchedEvent() {
        if (this.config.isDisableAppLaunchedEvent()) {
            this.coreMetaData.setAppLaunchPushed(true);
            this.config.getLogger().debug(this.config.getAccountId(), "App Launched Events disabled in the Android Manifest file");
        } else {
            if (this.coreMetaData.isAppLaunchPushed()) {
                this.config.getLogger().verbose(this.config.getAccountId(), "App Launched has already been triggered. Will not trigger it ");
                return;
            }
            this.config.getLogger().verbose(this.config.getAccountId(), "Firing App Launched event");
            this.coreMetaData.setAppLaunchPushed(true);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Constants.KEY_EVT_NAME, Constants.APP_LAUNCHED_EVENT);
                jSONObject.put(Constants.KEY_EVT_DATA, this.deviceInfo.getAppLaunchedFields());
            } catch (Throwable unused) {
            }
            this.baseEventQueueManager.queueEvent(this.context, jSONObject, 4);
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushDefineVarsEvent(JSONObject jSONObject) {
        this.baseEventQueueManager.queueEvent(this.context, jSONObject, 8);
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushDisplayUnitClickedEventForID(String str) {
        CleverTapDisplayUnit displayUnitForID;
        JSONObject wZRKFields;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.KEY_EVT_NAME, Constants.NOTIFICATION_CLICKED_EVENT_NAME);
            if (this.controllerManager.getCTDisplayUnitController() != null && (displayUnitForID = this.controllerManager.getCTDisplayUnitController().getDisplayUnitForID(str)) != null && (wZRKFields = displayUnitForID.getWZRKFields()) != null) {
                jSONObject.put(Constants.KEY_EVT_DATA, wZRKFields);
                try {
                    this.coreMetaData.setWzrkParams(wZRKFields);
                } catch (Throwable unused) {
                }
            }
            this.baseEventQueueManager.queueEvent(this.context, jSONObject, 4);
        } catch (Throwable th) {
            this.config.getLogger().verbose(this.config.getAccountId(), "DisplayUnit : Failed to push Display Unit clicked event" + th);
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushDisplayUnitViewedEventForID(String str) {
        CleverTapDisplayUnit displayUnitForID;
        JSONObject wZRKFields;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.KEY_EVT_NAME, Constants.NOTIFICATION_VIEWED_EVENT_NAME);
            if (this.controllerManager.getCTDisplayUnitController() != null && (displayUnitForID = this.controllerManager.getCTDisplayUnitController().getDisplayUnitForID(str)) != null && (wZRKFields = displayUnitForID.getWZRKFields()) != null) {
                jSONObject.put(Constants.KEY_EVT_DATA, wZRKFields);
            }
            this.baseEventQueueManager.queueEvent(this.context, jSONObject, 4);
        } catch (Throwable th) {
            this.config.getLogger().verbose(this.config.getAccountId(), "DisplayUnit : Failed to push Display Unit viewed event" + th);
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushError(String str, int i) {
        HashMap map = new HashMap();
        map.put("Error Message", str);
        map.put("Error Code", Integer.valueOf(i));
        try {
            String currentActivityName = CoreMetaData.getCurrentActivityName();
            if (currentActivityName != null) {
                map.put(HttpHeaders.LOCATION, currentActivityName);
            } else {
                map.put(HttpHeaders.LOCATION, "Unknown");
            }
        } catch (Throwable unused) {
            map.put(HttpHeaders.LOCATION, "Unknown");
        }
        pushEvent("Error Occurred", map);
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushEvent(String str, Map<String, Object> map) {
        if (str == null || str.equals("")) {
            return;
        }
        ValidationResult validationResultIsRestrictedEventName = this.validator.isRestrictedEventName(str);
        if (validationResultIsRestrictedEventName.getErrorCode() > 0) {
            this.validationResultStack.pushValidationResult(validationResultIsRestrictedEventName);
            return;
        }
        ValidationResult validationResultIsEventDiscarded = this.validator.isEventDiscarded(str);
        if (validationResultIsEventDiscarded.getErrorCode() > 0) {
            this.validationResultStack.pushValidationResult(validationResultIsEventDiscarded);
            return;
        }
        if (map == null) {
            map = new HashMap<>();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            ValidationResult validationResultCleanEventName = this.validator.cleanEventName(str);
            if (validationResultCleanEventName.getErrorCode() != 0) {
                jSONObject.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(validationResultCleanEventName));
            }
            String string = validationResultCleanEventName.getObject().toString();
            JSONObject jSONObject2 = new JSONObject();
            for (String str2 : map.keySet()) {
                Object obj = map.get(str2);
                ValidationResult validationResultCleanObjectKey = this.validator.cleanObjectKey(str2);
                String string2 = validationResultCleanObjectKey.getObject().toString();
                if (validationResultCleanObjectKey.getErrorCode() != 0) {
                    jSONObject.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(validationResultCleanObjectKey));
                }
                try {
                    ValidationResult validationResultCleanObjectValue = this.validator.cleanObjectValue(obj, Validator.ValidationContext.Event);
                    Object object = validationResultCleanObjectValue.getObject();
                    if (validationResultCleanObjectValue.getErrorCode() != 0) {
                        jSONObject.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(validationResultCleanObjectValue));
                    }
                    jSONObject2.put(string2, object);
                } catch (IllegalArgumentException unused) {
                    String[] strArr = new String[3];
                    strArr[0] = string;
                    strArr[1] = string2;
                    strArr[2] = obj != null ? obj.toString() : "";
                    ValidationResult validationResultCreate = ValidationResultFactory.create(512, 7, strArr);
                    this.config.getLogger().debug(this.config.getAccountId(), validationResultCreate.getErrorDesc());
                    this.validationResultStack.pushValidationResult(validationResultCreate);
                }
            }
            jSONObject.put(Constants.KEY_EVT_NAME, string);
            jSONObject.put(Constants.KEY_EVT_DATA, jSONObject2);
            this.baseEventQueueManager.queueEvent(this.context, jSONObject, 4);
        } catch (Throwable unused2) {
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushInAppNotificationStateEvent(boolean z, CTInAppNotification cTInAppNotification, Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject wzrkFields = CTJsonConverter.getWzrkFields(cTInAppNotification);
            if (bundle != null) {
                for (String str : bundle.keySet()) {
                    Object obj = bundle.get(str);
                    if (obj != null) {
                        wzrkFields.put(str, obj);
                    }
                }
            }
            if (z) {
                try {
                    this.coreMetaData.setWzrkParams(wzrkFields);
                } catch (Throwable unused) {
                }
                jSONObject.put(Constants.KEY_EVT_NAME, Constants.NOTIFICATION_CLICKED_EVENT_NAME);
            } else {
                jSONObject.put(Constants.KEY_EVT_NAME, Constants.NOTIFICATION_VIEWED_EVENT_NAME);
            }
            jSONObject.put(Constants.KEY_EVT_DATA, wzrkFields);
            this.baseEventQueueManager.queueEvent(this.context, jSONObject, 4);
        } catch (Throwable unused2) {
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushInstallReferrer(String str) {
        try {
            this.config.getLogger().verbose(this.config.getAccountId(), "Referrer received: " + str);
            if (str == null) {
                return;
            }
            int iCurrentTimeMillis = (int) (System.currentTimeMillis() / 1000);
            if (this.installReferrerMap.containsKey(str) && iCurrentTimeMillis - this.installReferrerMap.get(str).intValue() < 10) {
                this.config.getLogger().verbose(this.config.getAccountId(), "Skipping install referrer due to duplicate within 10 seconds");
            } else {
                this.installReferrerMap.put(str, Integer.valueOf(iCurrentTimeMillis));
                pushDeepLink(Uri.parse("wzrk://track?install=true&" + str), true);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public synchronized void pushInstallReferrer(String str, String str2, String str3) {
        if (str == null && str2 == null && str3 == null) {
            return;
        }
        try {
        } catch (Throwable th) {
            Logger.v("Failed to push install referrer", th);
        }
        if (StorageHelper.getInt(this.context, "app_install_status", 0) != 0) {
            Logger.d("Install referrer has already been set. Will not override it");
            return;
        }
        StorageHelper.putInt(this.context, "app_install_status", 1);
        if (str != null) {
            str = Uri.encode(str);
        }
        if (str2 != null) {
            str2 = Uri.encode(str2);
        }
        if (str3 != null) {
            str3 = Uri.encode(str3);
        }
        String str4 = str != null ? "wzrk://track?install=true&utm_source=" + str : "wzrk://track?install=true";
        if (str2 != null) {
            str4 = str4 + "&utm_medium=" + str2;
        }
        if (str3 != null) {
            str4 = str4 + "&utm_campaign=" + str3;
        }
        pushDeepLink(Uri.parse(str4), true);
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushNotificationClickedEvent(Bundle bundle) {
        String string;
        if (this.config.isAnalyticsOnly()) {
            this.config.getLogger().debug(this.config.getAccountId(), "is Analytics Only - will not process Notification Clicked event.");
            return;
        }
        if (bundle == null || bundle.isEmpty() || bundle.get("wzrk_pn") == null) {
            this.config.getLogger().debug(this.config.getAccountId(), "Push notification not from CleverTap - will not process Notification Clicked event.");
            return;
        }
        try {
            string = bundle.getString(Constants.WZRK_ACCT_ID_KEY);
        } catch (Throwable unused) {
            string = null;
        }
        if ((string != null || !this.config.isDefaultInstance()) && !this.config.getAccountId().equals(string)) {
            this.config.getLogger().debug(this.config.getAccountId(), "Push notification not targeted at this instance, not processing Notification Clicked Event");
            return;
        }
        if (bundle.containsKey(Constants.INAPP_PREVIEW_PUSH_PAYLOAD_KEY)) {
            handleInAppPreview(bundle);
            return;
        }
        if (bundle.containsKey(Constants.INBOX_PREVIEW_PUSH_PAYLOAD_KEY)) {
            handleInboxPreview(bundle);
            return;
        }
        if (bundle.containsKey(Constants.DISPLAY_UNIT_PREVIEW_PUSH_PAYLOAD_KEY)) {
            handleSendTestForDisplayUnits(bundle);
            return;
        }
        if (!bundle.containsKey(Constants.NOTIFICATION_ID_TAG) || bundle.getString(Constants.NOTIFICATION_ID_TAG) == null) {
            this.config.getLogger().debug(this.config.getAccountId(), "Push notification ID Tag is null, not processing Notification Clicked event for:  " + bundle);
            return;
        }
        if (checkDuplicateNotificationIds(dedupeCheckKey(bundle), this.notificationIdTagMap, 5000)) {
            this.config.getLogger().debug(this.config.getAccountId(), "Already processed Notification Clicked event for " + bundle + ", dropping duplicate.");
            return;
        }
        try {
            this.baseEventQueueManager.queueEvent(this.context, AnalyticsManagerBundler.notificationClickedJson(bundle), 4);
            this.coreMetaData.setWzrkParams(AnalyticsManagerBundler.wzrkBundleToJson(bundle));
        } catch (Throwable unused2) {
        }
        if (this.callbackManager.getPushNotificationListener() != null) {
            this.callbackManager.getPushNotificationListener().onNotificationClickedPayloadReceived(Utils.convertBundleObjectToHashMap(bundle));
        } else {
            Logger.d("CTPushNotificationListener is not set");
        }
    }

    private void handleInboxPreview(final Bundle bundle) {
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("testInboxNotification", new Callable<Void>() { // from class: com.clevertap.android.sdk.AnalyticsManager.2
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    Logger.v("Received inbox via push payload: " + bundle.getString(Constants.INBOX_PREVIEW_PUSH_PAYLOAD_KEY));
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray = new JSONArray();
                    jSONObject.put(Constants.INBOX_JSON_RESPONSE_KEY, jSONArray);
                    JSONObject jSONObject2 = new JSONObject(bundle.getString(Constants.INBOX_PREVIEW_PUSH_PAYLOAD_KEY));
                    jSONObject2.put(Column.ID, String.valueOf(System.currentTimeMillis() / 1000));
                    jSONArray.put(jSONObject2);
                    new InboxResponse(AnalyticsManager.this.config, AnalyticsManager.this.ctLockManager, AnalyticsManager.this.callbackManager, AnalyticsManager.this.controllerManager).processResponse(jSONObject, null, AnalyticsManager.this.context);
                } catch (Throwable th) {
                    Logger.v("Failed to process inbox message from push notification payload", th);
                }
                return null;
            }
        });
    }

    private void handleInAppPreview(final Bundle bundle) {
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("testInappNotification", new Callable<Void>() { // from class: com.clevertap.android.sdk.AnalyticsManager.3
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    String string = bundle.getString(Constants.INAPP_PREVIEW_PUSH_PAYLOAD_TYPE_KEY);
                    JSONObject jSONObject = new JSONObject(bundle.getString(Constants.INAPP_PREVIEW_PUSH_PAYLOAD_KEY));
                    JSONArray jSONArray = new JSONArray();
                    if (Constants.INAPP_IMAGE_INTERSTITIAL_TYPE.equals(string) || Constants.INAPP_ADVANCED_BUILDER_TYPE.equals(string)) {
                        jSONArray.put(AnalyticsManager.this.getHalfInterstitialInApp(jSONObject));
                    } else {
                        jSONArray.put(jSONObject);
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(Constants.INAPP_JSON_RESPONSE_KEY, jSONArray);
                    AnalyticsManager.this.inAppResponse.processResponse(jSONObject2, null, AnalyticsManager.this.context);
                } catch (Throwable th) {
                    Logger.v("Failed to display inapp notification from push notification payload", th);
                }
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject getHalfInterstitialInApp(JSONObject jSONObject) throws JSONException {
        String strWrapImageInterstitialContent = wrapImageInterstitialContent(jSONObject.optString(Constants.INAPP_IMAGE_INTERSTITIAL_CONFIG));
        if (strWrapImageInterstitialContent != null) {
            jSONObject.put("type", Constants.KEY_CUSTOM_HTML);
            Object objOpt = jSONObject.opt("d");
            if (objOpt instanceof JSONObject) {
                JSONObject jSONObject2 = new JSONObject(((JSONObject) objOpt).toString());
                jSONObject2.put(Constants.INAPP_HTML_TAG, strWrapImageInterstitialContent);
                jSONObject.put("d", jSONObject2);
            } else {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(Constants.INAPP_HTML_TAG, strWrapImageInterstitialContent);
                jSONObject.put("d", jSONObject3);
            }
            return jSONObject;
        }
        this.config.getLogger().debug(this.config.getAccountId(), "Failed to parse the image-interstitial notification");
        return null;
    }

    public String wrapImageInterstitialContent(String str) {
        try {
            String assetFile = Utils.readAssetFile(this.context, Constants.INAPP_IMAGE_INTERSTITIAL_HTML_NAME);
            if (assetFile == null || str == null) {
                return null;
            }
            String[] strArrSplit = assetFile.split(Constants.INAPP_HTML_SPLIT);
            if (strArrSplit.length == 2) {
                return strArrSplit[0] + str + strArrSplit[1];
            }
            return null;
        } catch (IOException unused) {
            this.config.getLogger().debug(this.config.getAccountId(), "Failed to read the image-interstitial HTML file");
            return null;
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushNotificationViewedEvent(Bundle bundle) {
        if (bundle == null || bundle.isEmpty() || bundle.get("wzrk_pn") == null) {
            this.config.getLogger().debug(this.config.getAccountId(), "Push notification: " + (bundle == null ? "NULL" : bundle.toString()) + " not from CleverTap - will not process Notification Viewed event.");
            return;
        }
        if (!bundle.containsKey(Constants.NOTIFICATION_ID_TAG) || bundle.getString(Constants.NOTIFICATION_ID_TAG) == null) {
            this.config.getLogger().debug(this.config.getAccountId(), "Push notification ID Tag is null, not processing Notification Viewed event for:  " + bundle);
        } else if (checkDuplicateNotificationIds(dedupeCheckKey(bundle), this.notificationViewedIdTagMap, 2000)) {
            this.config.getLogger().debug(this.config.getAccountId(), "Already processed Notification Viewed event for " + bundle + ", dropping duplicate.");
        } else {
            this.config.getLogger().debug("Recording Notification Viewed event for notification:  " + bundle);
            this.baseEventQueueManager.queueEvent(this.context, AnalyticsManagerBundler.notificationViewedJson(bundle), 6);
        }
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void pushProfile(final Map<String, Object> map) {
        if (map == null || map.isEmpty() || this.deviceInfo.getDeviceID() == null) {
            return;
        }
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("profilePush", new Callable<Void>() { // from class: com.clevertap.android.sdk.AnalyticsManager.4
            @Override // java.util.concurrent.Callable
            public Void call() {
                AnalyticsManager.this._push(map);
                return null;
            }
        });
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void removeMultiValuesForKey(final String str, final ArrayList<String> arrayList) {
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("removeMultiValuesForKey", new Callable<Void>() { // from class: com.clevertap.android.sdk.AnalyticsManager.5
            @Override // java.util.concurrent.Callable
            public Void call() {
                AnalyticsManager.this._handleMultiValues(arrayList, str, Constants.COMMAND_REMOVE);
                return null;
            }
        });
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void removeValueForKey(final String str) {
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("removeValueForKey", new Callable<Void>() { // from class: com.clevertap.android.sdk.AnalyticsManager.6
            @Override // java.util.concurrent.Callable
            public Void call() {
                AnalyticsManager.this._removeValueForKey(str);
                return null;
            }
        });
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void sendDataEvent(JSONObject jSONObject) {
        this.baseEventQueueManager.queueEvent(this.context, jSONObject, 5);
    }

    void _generateEmptyMultiValueError(String str) {
        ValidationResult validationResultCreate = ValidationResultFactory.create(512, 1, str);
        this.validationResultStack.pushValidationResult(validationResultCreate);
        this.config.getLogger().debug(this.config.getAccountId(), validationResultCreate.getErrorDesc());
    }

    void pushChargedEvent(HashMap<String, Object> map, ArrayList<HashMap<String, Object>> arrayList) {
        Iterator<String> it;
        int i;
        boolean z;
        if (map == null || arrayList == null) {
            this.config.getLogger().debug(this.config.getAccountId(), "Invalid Charged event: details and or items is null");
            return;
        }
        if (arrayList.size() > 50) {
            ValidationResult validationResultCreate = ValidationResultFactory.create(522);
            this.config.getLogger().debug(this.config.getAccountId(), validationResultCreate.getErrorDesc());
            this.validationResultStack.pushValidationResult(validationResultCreate);
        }
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            it = map.keySet().iterator();
        } catch (Throwable unused) {
            return;
        }
        while (true) {
            i = 2;
            z = true;
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            Object obj = map.get(next);
            ValidationResult validationResultCleanObjectKey = this.validator.cleanObjectKey(next);
            String string = validationResultCleanObjectKey.getObject().toString();
            if (validationResultCleanObjectKey.getErrorCode() != 0) {
                jSONObject2.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(validationResultCleanObjectKey));
            }
            try {
                ValidationResult validationResultCleanObjectValue = this.validator.cleanObjectValue(obj, Validator.ValidationContext.Event);
                Object object = validationResultCleanObjectValue.getObject();
                if (validationResultCleanObjectValue.getErrorCode() != 0) {
                    jSONObject2.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(validationResultCleanObjectValue));
                }
                jSONObject.put(string, object);
            } catch (IllegalArgumentException unused2) {
                String[] strArr = new String[3];
                strArr[0] = Constants.CHARGED_EVENT;
                strArr[1] = string;
                strArr[2] = obj != null ? obj.toString() : "";
                ValidationResult validationResultCreate2 = ValidationResultFactory.create(FrameMetricsAggregator.EVERY_DURATION, 7, strArr);
                this.validationResultStack.pushValidationResult(validationResultCreate2);
                this.config.getLogger().debug(this.config.getAccountId(), validationResultCreate2.getErrorDesc());
            }
            return;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<HashMap<String, Object>> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            HashMap<String, Object> next2 = it2.next();
            JSONObject jSONObject3 = new JSONObject();
            for (String str : next2.keySet()) {
                Object obj2 = next2.get(str);
                ValidationResult validationResultCleanObjectKey2 = this.validator.cleanObjectKey(str);
                String string2 = validationResultCleanObjectKey2.getObject().toString();
                if (validationResultCleanObjectKey2.getErrorCode() != 0) {
                    jSONObject2.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(validationResultCleanObjectKey2));
                }
                try {
                    ValidationResult validationResultCleanObjectValue2 = this.validator.cleanObjectValue(obj2, Validator.ValidationContext.Event);
                    Object object2 = validationResultCleanObjectValue2.getObject();
                    if (validationResultCleanObjectValue2.getErrorCode() != 0) {
                        jSONObject2.put(Constants.ERROR_KEY, CTJsonConverter.getErrorObject(validationResultCleanObjectValue2));
                    }
                    jSONObject3.put(string2, object2);
                    z = true;
                } catch (IllegalArgumentException unused3) {
                    String[] strArr2 = new String[i];
                    strArr2[0] = string2;
                    strArr2[1] = obj2 != null ? obj2.toString() : "";
                    ValidationResult validationResultCreate3 = ValidationResultFactory.create(FrameMetricsAggregator.EVERY_DURATION, 15, strArr2);
                    this.config.getLogger().debug(this.config.getAccountId(), validationResultCreate3.getErrorDesc());
                    this.validationResultStack.pushValidationResult(validationResultCreate3);
                    z = true;
                    i = 2;
                }
            }
            jSONArray.put(jSONObject3);
            z = z;
            i = 2;
        }
        jSONObject.put(Constants.KEY_ITEMS, jSONArray);
        jSONObject2.put(Constants.KEY_EVT_NAME, Constants.CHARGED_EVENT);
        jSONObject2.put(Constants.KEY_EVT_DATA, jSONObject);
        this.baseEventQueueManager.queueEvent(this.context, jSONObject2, 4);
    }

    synchronized void pushDeepLink(Uri uri, boolean z) {
        if (uri == null) {
            return;
        }
        try {
            JSONObject urchinFromUri = UriHelper.getUrchinFromUri(uri);
            if (urchinFromUri.has("us")) {
                this.coreMetaData.setSource(urchinFromUri.get("us").toString());
            }
            if (urchinFromUri.has("um")) {
                this.coreMetaData.setMedium(urchinFromUri.get("um").toString());
            }
            if (urchinFromUri.has("uc")) {
                this.coreMetaData.setCampaign(urchinFromUri.get("uc").toString());
            }
            urchinFromUri.put("referrer", uri.toString());
            if (z) {
                urchinFromUri.put("install", true);
            }
            recordPageEventWithExtras(urchinFromUri);
        } finally {
        }
    }

    Future<?> raiseEventForSignedCall(String str, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(Constants.KEY_EVT_NAME, str);
            jSONObject2.put(Constants.KEY_EVT_DATA, jSONObject);
            return this.baseEventQueueManager.queueEvent(this.context, jSONObject2, 4);
        } catch (JSONException e) {
            this.config.getLogger().debug(this.config.getAccountId(), "SignedCall : JSON Exception when raising Signed Call event " + str + " - " + e.getLocalizedMessage());
            return null;
        }
    }

    Future<?> raiseEventForGeofences(String str, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(Constants.KEY_EVT_NAME, str);
            jSONObject2.put(Constants.KEY_EVT_DATA, jSONObject);
            Location location = new Location("");
            location.setLatitude(jSONObject.getDouble("triggered_lat"));
            location.setLongitude(jSONObject.getDouble("triggered_lng"));
            jSONObject.remove("triggered_lat");
            jSONObject.remove("triggered_lng");
            this.coreMetaData.setLocationFromUser(location);
            return this.baseEventQueueManager.queueEvent(this.context, jSONObject2, 4);
        } catch (JSONException e) {
            this.config.getLogger().debug(this.config.getAccountId(), "Geofences : JSON Exception when raising GeoFence event " + str + " - " + e.getLocalizedMessage());
            return null;
        }
    }

    void recordPageEventWithExtras(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (jSONObject != null && jSONObject.length() > 0) {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    try {
                        String next = itKeys.next();
                        jSONObject2.put(next, jSONObject.getString(next));
                    } catch (ClassCastException unused) {
                    }
                }
            }
            this.baseEventQueueManager.queueEvent(this.context, jSONObject2, 1);
        } catch (Throwable unused2) {
        }
    }

    void setMultiValuesForKey(final String str, final ArrayList<String> arrayList) {
        CTExecutorFactory.executors(this.config).postAsyncSafelyTask().execute("setMultiValuesForKey", new Callable<Void>() { // from class: com.clevertap.android.sdk.AnalyticsManager.7
            @Override // java.util.concurrent.Callable
            public Void call() {
                AnalyticsManager.this._handleMultiValues(arrayList, str, Constants.COMMAND_SET);
                return null;
            }
        });
    }

    private void _generateInvalidMultiValueKeyError(String str) {
        this.validationResultStack.pushValidationResult(ValidationResultFactory.create(523, 23, str));
        this.config.getLogger().debug(this.config.getAccountId(), "Invalid multi-value property key " + str + " profile multi value operation aborted");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _handleMultiValues(ArrayList<String> arrayList, String str, String str2) {
        if (str == null) {
            return;
        }
        if (arrayList == null || arrayList.isEmpty()) {
            _generateEmptyMultiValueError(str);
            return;
        }
        ValidationResult validationResultCleanMultiValuePropertyKey = this.validator.cleanMultiValuePropertyKey(str);
        if (validationResultCleanMultiValuePropertyKey.getErrorCode() != 0) {
            this.validationResultStack.pushValidationResult(validationResultCleanMultiValuePropertyKey);
        }
        String string = validationResultCleanMultiValuePropertyKey.getObject() != null ? validationResultCleanMultiValuePropertyKey.getObject().toString() : null;
        if (string == null || string.isEmpty()) {
            _generateInvalidMultiValueKeyError(str);
        } else {
            _pushMultiValue(arrayList, string, str2);
        }
    }

    private void _constructIncrementDecrementValues(Number number, String str, String str2) {
        if (str == null || number == null) {
            return;
        }
        try {
            ValidationResult validationResultCleanObjectKey = this.validator.cleanObjectKey(str);
            String string = validationResultCleanObjectKey.getObject().toString();
            if (string.isEmpty()) {
                ValidationResult validationResultCreate = ValidationResultFactory.create(512, 2, string);
                this.validationResultStack.pushValidationResult(validationResultCreate);
                this.config.getLogger().debug(this.config.getAccountId(), validationResultCreate.getErrorDesc());
                return;
            }
            if (number.intValue() >= 0 && number.doubleValue() >= 0.0d && number.floatValue() >= 0.0f) {
                if (validationResultCleanObjectKey.getErrorCode() != 0) {
                    this.validationResultStack.pushValidationResult(validationResultCleanObjectKey);
                }
                this.baseEventQueueManager.pushBasicProfile(new JSONObject().put(string, new JSONObject().put(str2, number)), false);
                return;
            }
            ValidationResult validationResultCreate2 = ValidationResultFactory.create(512, 25, string);
            this.validationResultStack.pushValidationResult(validationResultCreate2);
            this.config.getLogger().debug(this.config.getAccountId(), validationResultCreate2.getErrorDesc());
        } catch (Throwable th) {
            this.config.getLogger().verbose(this.config.getAccountId(), "Failed to update profile value for key " + str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v14, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.lang.Object] */
    public void _push(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : map.keySet()) {
                Object obj = map.get(str);
                ValidationResult validationResultCleanObjectKey = this.validator.cleanObjectKey(str);
                String string = validationResultCleanObjectKey.getObject().toString();
                if (validationResultCleanObjectKey.getErrorCode() != 0) {
                    this.validationResultStack.pushValidationResult(validationResultCleanObjectKey);
                }
                if (string.isEmpty()) {
                    ValidationResult validationResultCreate = ValidationResultFactory.create(512, 2, new String[0]);
                    this.validationResultStack.pushValidationResult(validationResultCreate);
                    this.config.getLogger().debug(this.config.getAccountId(), validationResultCreate.getErrorDesc());
                } else {
                    try {
                        ValidationResult validationResultCleanObjectValue = this.validator.cleanObjectValue(obj, Validator.ValidationContext.Profile);
                        Object object = validationResultCleanObjectValue.getObject();
                        if (validationResultCleanObjectValue.getErrorCode() != 0) {
                            this.validationResultStack.pushValidationResult(validationResultCleanObjectValue);
                        }
                        if (string.equalsIgnoreCase("Phone")) {
                            try {
                                object = object.toString();
                                String countryCode = this.deviceInfo.getCountryCode();
                                if (countryCode == null || countryCode.isEmpty()) {
                                    if (!object.startsWith("+")) {
                                        ValidationResult validationResultCreate2 = ValidationResultFactory.create(512, 4, object);
                                        this.validationResultStack.pushValidationResult(validationResultCreate2);
                                        this.config.getLogger().debug(this.config.getAccountId(), validationResultCreate2.getErrorDesc());
                                    }
                                }
                                Logger logger = this.config.getLogger();
                                String accountId = this.config.getAccountId();
                                StringBuilder sbAppend = new StringBuilder().append("Profile phone is: ").append((Object) object).append(" device country code is: ");
                                if (countryCode == null) {
                                    countryCode = "null";
                                }
                                logger.verbose(accountId, sbAppend.append(countryCode).toString());
                            } catch (Exception e) {
                                this.validationResultStack.pushValidationResult(ValidationResultFactory.create(512, 5, new String[0]));
                                this.config.getLogger().debug(this.config.getAccountId(), "Invalid phone number: " + e.getLocalizedMessage());
                            }
                        }
                        jSONObject.put(string, object);
                    } catch (Throwable unused) {
                        String[] strArr = new String[2];
                        strArr[0] = obj != null ? obj.toString() : "";
                        strArr[1] = string;
                        ValidationResult validationResultCreate3 = ValidationResultFactory.create(512, 3, strArr);
                        this.validationResultStack.pushValidationResult(validationResultCreate3);
                        this.config.getLogger().debug(this.config.getAccountId(), validationResultCreate3.getErrorDesc());
                    }
                }
            }
            this.config.getLogger().verbose(this.config.getAccountId(), "Constructed custom profile: " + jSONObject);
            this.baseEventQueueManager.pushBasicProfile(jSONObject, false);
        } catch (Throwable th) {
            this.config.getLogger().verbose(this.config.getAccountId(), "Failed to push profile", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _removeValueForKey(String str) {
        if (str == null) {
            str = "";
        }
        try {
            ValidationResult validationResultCleanObjectKey = this.validator.cleanObjectKey(str);
            String string = validationResultCleanObjectKey.getObject().toString();
            if (string.isEmpty()) {
                ValidationResult validationResultCreate = ValidationResultFactory.create(512, 6, new String[0]);
                this.validationResultStack.pushValidationResult(validationResultCreate);
                this.config.getLogger().debug(this.config.getAccountId(), validationResultCreate.getErrorDesc());
                return;
            }
            if (validationResultCleanObjectKey.getErrorCode() != 0) {
                this.validationResultStack.pushValidationResult(validationResultCleanObjectKey);
            }
            if (string.toLowerCase().contains(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY)) {
                this.config.getLogger().verbose(this.config.getAccountId(), "Cannot remove value for key " + string + " from user profile");
                return;
            }
            this.baseEventQueueManager.pushBasicProfile(new JSONObject().put(string, new JSONObject().put(Constants.COMMAND_DELETE, true)), true);
            this.config.getLogger().verbose(this.config.getAccountId(), "removing value for key " + string + " from user profile");
        } catch (Throwable th) {
            this.config.getLogger().verbose(this.config.getAccountId(), "Failed to remove profile value for key " + str, th);
        }
    }

    private void _pushMultiValue(ArrayList<String> arrayList, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(str2, new JSONArray((Collection) arrayList));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(str, jSONObject);
            this.baseEventQueueManager.pushBasicProfile(jSONObject2, false);
            this.config.getLogger().verbose(this.config.getAccountId(), "Constructed multi-value profile push: " + jSONObject2);
        } catch (Throwable th) {
            this.config.getLogger().verbose(this.config.getAccountId(), "Error pushing multiValue for key " + str, th);
        }
    }

    String dedupeCheckKey(Bundle bundle) {
        Object obj = bundle.get(Constants.WZRK_DEDUPE);
        if (obj != null) {
            boolean zEqualsIgnoreCase = obj instanceof String ? "true".equalsIgnoreCase((String) obj) : false;
            if (obj instanceof Boolean) {
                zEqualsIgnoreCase = ((Boolean) obj).booleanValue();
            }
            if (zEqualsIgnoreCase) {
                return bundle.getString(Constants.WZRK_PUSH_ID);
            }
        }
        return bundle.getString(Constants.NOTIFICATION_ID_TAG);
    }

    private boolean checkDuplicateNotificationIds(String str, HashMap<String, Long> map, int i) {
        boolean z;
        synchronized (this.notificationMapLock) {
            z = false;
            try {
                long jCurrentTimeMillis = this.currentTimeProvider.currentTimeMillis();
                if (map.containsKey(str) && jCurrentTimeMillis - map.get(str).longValue() < i) {
                    z = true;
                }
                map.put(str, Long.valueOf(jCurrentTimeMillis));
            } catch (Throwable unused) {
            }
        }
        return z;
    }

    public void sendPingEvent(JSONObject jSONObject) {
        this.baseEventQueueManager.queueEvent(this.context, jSONObject, 2);
    }

    @Override // com.clevertap.android.sdk.BaseAnalyticsManager
    public void sendFetchEvent(JSONObject jSONObject) {
        this.baseEventQueueManager.queueEvent(this.context, jSONObject, 7);
    }

    private void handleSendTestForDisplayUnits(Bundle bundle) {
        try {
            new DisplayUnitResponse(this.config, this.callbackManager, this.controllerManager).processResponse(CTJsonConverter.displayUnitFromExtras(bundle), null, this.context);
        } catch (Throwable th) {
            Logger.v("Failed to process Display Unit from push notification payload", th);
        }
    }

    void pushInboxMessageStateEvent(boolean z, CTInboxMessage cTInboxMessage, Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject wzrkFields = CTJsonConverter.getWzrkFields(cTInboxMessage);
            if (bundle != null) {
                for (String str : bundle.keySet()) {
                    Object obj = bundle.get(str);
                    if (obj != null) {
                        wzrkFields.put(str, obj);
                    }
                }
            }
            if (z) {
                try {
                    this.coreMetaData.setWzrkParams(wzrkFields);
                } catch (Throwable unused) {
                }
                jSONObject.put(Constants.KEY_EVT_NAME, Constants.NOTIFICATION_CLICKED_EVENT_NAME);
            } else {
                jSONObject.put(Constants.KEY_EVT_NAME, Constants.NOTIFICATION_VIEWED_EVENT_NAME);
            }
            jSONObject.put(Constants.KEY_EVT_DATA, wzrkFields);
            this.baseEventQueueManager.queueEvent(this.context, jSONObject, 4);
        } catch (Throwable unused2) {
        }
    }
}
