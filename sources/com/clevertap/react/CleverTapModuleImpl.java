package com.clevertap.react;

import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.clevertap.android.sdk.CTInboxStyleConfig;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.UTMDetail;
import com.clevertap.android.sdk.db.Column;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.clevertap.android.sdk.events.EventDetail;
import com.clevertap.android.sdk.featureFlags.CTFeatureFlagsController;
import com.clevertap.android.sdk.inapp.CTLocalInApp;
import com.clevertap.android.sdk.inapp.callbacks.FetchInAppsCallback;
import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateContext;
import com.clevertap.android.sdk.inbox.CTInboxMessage;
import com.clevertap.android.sdk.interfaces.OnInitCleverTapIDListener;
import com.clevertap.android.sdk.product_config.CTProductConfigController;
import com.clevertap.android.sdk.usereventlogs.UserEventLog;
import com.clevertap.android.sdk.variables.Var;
import com.clevertap.android.sdk.variables.callbacks.FetchVariablesCallback;
import com.clevertap.android.sdk.variables.callbacks.VariableCallback;
import com.clevertap.android.sdk.variables.callbacks.VariablesChangedCallback;
import com.clevertap.react.CleverTapUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ViewProps;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CleverTapModuleImpl {
    private static final String TAG = "CleverTapReact";
    private static Uri sLaunchUri;
    public static Map<String, Object> variables = new HashMap();
    private final ReactApplicationContext context;
    private CleverTapAPI mCleverTap;

    /* JADX INFO: Access modifiers changed from: private */
    @FunctionalInterface
    interface TemplateContextAction {
        Object execute(CustomTemplateContext customTemplateContext);
    }

    private boolean checkKitkatVersion(String str) {
        return true;
    }

    public static void setInitialUri(Uri uri) {
        sLaunchUri = uri;
    }

    private enum InBoxMessages {
        ALL(0),
        UNREAD(1);

        private final int value;

        InBoxMessages(int i) {
            this.value = i;
        }
    }

    public CleverTapModuleImpl(ReactApplicationContext reactApplicationContext) {
        this.context = reactApplicationContext;
        enableEventEmitter(reactApplicationContext);
        getCleverTapAPI();
    }

    public Map<String, Object> getClevertapConstants() {
        HashMap map = new HashMap();
        for (CleverTapEvent cleverTapEvent : CleverTapEvent.values()) {
            map.put(cleverTapEvent.getEventName(), cleverTapEvent.getEventName());
        }
        map.put("FCM", "FCM");
        map.put(Constants.BPS, Constants.BPS);
        map.put(Constants.HPS, Constants.HPS);
        return map;
    }

    public void setLibrary(String str, int i) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.setCustomSdkVersion(str, i);
        }
    }

    public void setLocale(String str) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.setLocale(str);
        } else {
            Log.e("CleverTapReact", ErrorMessages.CLEVERTAP_NOT_INITIALIZED);
        }
    }

    public void activate() {
        CTProductConfigController ctProductConfigController = getCtProductConfigController();
        if (ctProductConfigController == null) {
            return;
        }
        ctProductConfigController.activate();
    }

    public void createNotification(ReadableMap readableMap) {
        if (getCleverTapAPI() == null) {
            return;
        }
        try {
            JSONObject jSONObjectJsonObjectFromReadableMap = jsonObjectFromReadableMap(readableMap);
            Bundle bundle = new Bundle();
            Iterator<String> itKeys = jSONObjectJsonObjectFromReadableMap.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                bundle.putString(next, jSONObjectJsonObjectFromReadableMap.optString(next));
            }
            CleverTapAPI.createNotification(this.context, bundle);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void createNotificationChannel(String str, String str2, String str3, int i, boolean z) {
        if (getCleverTapAPI() == null || str == null || str2 == null || str3 == null) {
            return;
        }
        CleverTapAPI.createNotificationChannel(this.context, str, str2, str3, i, z);
        Log.i("CleverTapReact", "Notification Channel " + str2 + " created");
    }

    public void createNotificationChannelGroup(String str, String str2) {
        if (getCleverTapAPI() == null || str == null || str2 == null) {
            return;
        }
        CleverTapAPI.createNotificationChannelGroup(this.context, str, str2);
        Log.i("CleverTapReact", "Notification Channel Group " + str2 + " created");
    }

    public void createNotificationChannelWithGroupId(String str, String str2, String str3, int i, String str4, boolean z) {
        if (getCleverTapAPI() == null || str == null || str2 == null || str3 == null || str4 == null) {
            return;
        }
        CleverTapAPI.createNotificationChannel(this.context, str, str2, str3, i, str4, z);
        Log.i("CleverTapReact", "Notification Channel " + str2 + " with Group Id " + str4 + " created");
    }

    public void createNotificationChannelWithGroupIdAndSound(String str, String str2, String str3, int i, String str4, boolean z, String str5) {
        if (getCleverTapAPI() == null || str == null || str2 == null || str3 == null || str4 == null || str5 == null) {
            return;
        }
        CleverTapAPI.createNotificationChannel(this.context, str, str2, str3, i, str4, z, str5);
        Log.i("CleverTapReact", "Notification Channel " + str2 + " with Group Id " + str4 + " and sound file " + str5 + " created");
    }

    public void createNotificationChannelWithSound(String str, String str2, String str3, int i, boolean z, String str4) {
        if (getCleverTapAPI() == null || str == null || str2 == null || str3 == null || str4 == null) {
            return;
        }
        CleverTapAPI.createNotificationChannel(this.context, str, str2, str3, i, z, str4);
        Log.i("CleverTapReact", "Notification Channel " + str2 + " with sound file " + str4 + " created");
    }

    public void deleteNotificationChannel(String str) {
        if (getCleverTapAPI() == null || str == null) {
            return;
        }
        CleverTapAPI.deleteNotificationChannel(this.context, str);
        Log.i("CleverTapReact", "Notification Channel Id " + str + " deleted");
    }

    public void deleteNotificationChannelGroup(String str) {
        if (getCleverTapAPI() == null || str == null) {
            return;
        }
        CleverTapAPI.deleteNotificationChannelGroup(this.context, str);
        Log.i("CleverTapReact", "Notification Channel Group Id " + str + " deleted");
    }

    public void promptForPushPermission(boolean z) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.promptForPushPermission(z);
        } else {
            Log.e("CleverTapReact", ErrorMessages.CLEVERTAP_NOT_INITIALIZED);
        }
    }

    public void promptPushPrimer(ReadableMap readableMap) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.promptPushPrimer(localInAppConfigFromReadableMap(readableMap));
        } else {
            Log.e("CleverTapReact", ErrorMessages.CLEVERTAP_NOT_INITIALIZED);
        }
    }

    public void isPushPermissionGranted(Callback callback) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            callbackWithErrorAndResult(callback, null, Boolean.valueOf(cleverTapAPI.isPushPermissionGranted()));
        } else {
            callbackWithErrorAndResult(callback, ErrorMessages.CLEVERTAP_NOT_INITIALIZED, null);
        }
    }

    public void disablePersonalization() {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        cleverTapAPI.disablePersonalization();
    }

    public void enableDeviceNetworkInfoReporting(boolean z) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        cleverTapAPI.enableDeviceNetworkInfoReporting(z);
    }

    public void enablePersonalization() {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        cleverTapAPI.enablePersonalization();
    }

    public void getUserEventLog(String str, Callback callback) {
        WritableMap writableMapEventLogToWritableMap;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        String str2 = null;
        if (cleverTapAPI != null) {
            writableMapEventLogToWritableMap = eventLogToWritableMap(cleverTapAPI.getUserEventLog(str));
        } else {
            str2 = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
            writableMapEventLogToWritableMap = null;
        }
        callbackWithErrorAndResult(callback, str2, writableMapEventLogToWritableMap);
    }

    public void getUserEventLogCount(String str, Callback callback) {
        int userEventLogCount;
        String str2;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            userEventLogCount = cleverTapAPI.getUserEventLogCount(str);
            str2 = null;
        } else {
            userEventLogCount = -1;
            str2 = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        callbackWithErrorAndResult(callback, str2, Integer.valueOf(userEventLogCount));
    }

    public void getUserLastVisitTs(Callback callback) {
        double userLastVisitTs;
        String str;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            userLastVisitTs = cleverTapAPI.getUserLastVisitTs();
            str = null;
        } else {
            userLastVisitTs = -1.0d;
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        callbackWithErrorAndResult(callback, str, Double.valueOf(userLastVisitTs));
    }

    public void getUserAppLaunchCount(Callback callback) {
        int userAppLaunchCount;
        String str;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            userAppLaunchCount = cleverTapAPI.getUserAppLaunchCount();
            str = null;
        } else {
            userAppLaunchCount = -1;
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        callbackWithErrorAndResult(callback, str, Integer.valueOf(userAppLaunchCount));
    }

    public void getUserEventLogHistory(Callback callback) {
        WritableMap writableMapEventLogHistoryToWritableMap;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        String str = null;
        if (cleverTapAPI != null) {
            writableMapEventLogHistoryToWritableMap = eventLogHistoryToWritableMap(cleverTapAPI.getUserEventLogHistory());
        } else {
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
            writableMapEventLogHistoryToWritableMap = null;
        }
        callbackWithErrorAndResult(callback, str, writableMapEventLogHistoryToWritableMap);
    }

    @Deprecated(since = "3.2.0")
    public void eventGetDetail(String str, Callback callback) {
        WritableMap writableMapEventDetailToWritableMap;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        String str2 = null;
        if (cleverTapAPI != null) {
            writableMapEventDetailToWritableMap = eventDetailToWritableMap(cleverTapAPI.getDetails(str));
        } else {
            str2 = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
            writableMapEventDetailToWritableMap = null;
        }
        callbackWithErrorAndResult(callback, str2, writableMapEventDetailToWritableMap);
    }

    @Deprecated(since = "3.2.0")
    public void eventGetFirstTime(String str, Callback callback) {
        int firstTime;
        String str2;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            firstTime = cleverTapAPI.getFirstTime(str);
            str2 = null;
        } else {
            firstTime = -1;
            str2 = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        callbackWithErrorAndResult(callback, str2, Integer.valueOf(firstTime));
    }

    @Deprecated(since = "3.2.0")
    public void eventGetLastTime(String str, Callback callback) {
        int lastTime;
        String str2;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            lastTime = cleverTapAPI.getLastTime(str);
            str2 = null;
        } else {
            lastTime = -1;
            str2 = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        callbackWithErrorAndResult(callback, str2, Integer.valueOf(lastTime));
    }

    @Deprecated(since = "3.2.0")
    public void eventGetOccurrences(String str, Callback callback) {
        int count;
        String str2;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            count = cleverTapAPI.getCount(str);
            str2 = null;
        } else {
            count = -1;
            str2 = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        callbackWithErrorAndResult(callback, str2, Integer.valueOf(count));
    }

    public void fetch() {
        CTProductConfigController ctProductConfigController = getCtProductConfigController();
        if (ctProductConfigController == null) {
            return;
        }
        ctProductConfigController.fetch();
    }

    public void fetchAndActivate() {
        CTProductConfigController ctProductConfigController = getCtProductConfigController();
        if (ctProductConfigController == null) {
            return;
        }
        ctProductConfigController.fetchAndActivate();
    }

    public void fetchWithMinimumFetchIntervalInSeconds(int i) {
        CTProductConfigController ctProductConfigController = getCtProductConfigController();
        if (ctProductConfigController == null) {
            return;
        }
        ctProductConfigController.fetch(i);
    }

    public void getAllDisplayUnits(Callback callback) {
        String str;
        WritableArray writableArrayCreateArray = Arguments.createArray();
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            writableArrayCreateArray = CleverTapUtils.getWritableArrayFromDisplayUnitList(cleverTapAPI.getAllDisplayUnits());
            str = null;
        } else {
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        callbackWithErrorAndResult(callback, str, writableArrayCreateArray);
    }

    public void getBoolean(String str, Callback callback) {
        String str2;
        Boolean bool;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        String str3 = null;
        if (cleverTapAPI != null) {
            CTProductConfigController cTProductConfigControllerProductConfig = cleverTapAPI.productConfig();
            if (cTProductConfigControllerProductConfig != null) {
                bool = cTProductConfigControllerProductConfig.getBoolean(str);
                callbackWithErrorAndResult(callback, str3, bool);
            }
            str2 = ErrorMessages.PRODUCTCONFIG_NOT_INITIALIZED;
        } else {
            str2 = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        str3 = str2;
        bool = null;
        callbackWithErrorAndResult(callback, str3, bool);
    }

    public void getDisplayUnitForId(String str, Callback callback) {
        WritableMap writableMapConvertObjectToWritableMap;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        String str2 = null;
        if (cleverTapAPI != null) {
            CleverTapDisplayUnit displayUnitForId = cleverTapAPI.getDisplayUnitForId(str);
            writableMapConvertObjectToWritableMap = (displayUnitForId == null || displayUnitForId.getJsonObject() == null) ? null : CleverTapUtils.convertObjectToWritableMap(displayUnitForId.getJsonObject());
        } else {
            str2 = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
            writableMapConvertObjectToWritableMap = null;
        }
        callbackWithErrorAndResult(callback, str2, writableMapConvertObjectToWritableMap);
    }

    public void getDouble(String str, Callback callback) {
        String str2;
        Double d;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        String str3 = null;
        if (cleverTapAPI != null) {
            CTProductConfigController cTProductConfigControllerProductConfig = cleverTapAPI.productConfig();
            if (cTProductConfigControllerProductConfig != null) {
                d = cTProductConfigControllerProductConfig.getDouble(str);
                callbackWithErrorAndResult(callback, str3, d);
            }
            str2 = ErrorMessages.PRODUCTCONFIG_NOT_INITIALIZED;
        } else {
            str2 = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        str3 = str2;
        d = null;
        callbackWithErrorAndResult(callback, str3, d);
    }

    @Deprecated(since = "3.2.0")
    public void getEventHistory(Callback callback) {
        WritableMap writableMapEventHistoryToWritableMap;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        String str = null;
        if (cleverTapAPI != null) {
            writableMapEventHistoryToWritableMap = eventHistoryToWritableMap(cleverTapAPI.getHistory());
        } else {
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
            writableMapEventHistoryToWritableMap = null;
        }
        callbackWithErrorAndResult(callback, str, writableMapEventHistoryToWritableMap);
    }

    public void getFeatureFlag(String str, Boolean bool, Callback callback) {
        String str2;
        Boolean bool2;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        String str3 = null;
        if (cleverTapAPI != null) {
            CTFeatureFlagsController cTFeatureFlagsControllerFeatureFlag = cleverTapAPI.featureFlag();
            if (cTFeatureFlagsControllerFeatureFlag != null) {
                bool2 = cTFeatureFlagsControllerFeatureFlag.get(str, bool.booleanValue());
                callbackWithErrorAndResult(callback, str3, bool2);
            }
            str2 = ErrorMessages.FF_NOT_INITIALIZED;
        } else {
            str2 = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        str3 = str2;
        bool2 = null;
        callbackWithErrorAndResult(callback, str3, bool2);
    }

    public void getAllInboxMessages(Callback callback) {
        getInboxMessages(callback, InBoxMessages.ALL);
    }

    public void getInboxMessageCount(Callback callback) {
        int inboxMessageCount;
        String str;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            inboxMessageCount = cleverTapAPI.getInboxMessageCount();
            str = null;
        } else {
            inboxMessageCount = -1;
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        callbackWithErrorAndResult(callback, str, Integer.valueOf(inboxMessageCount));
    }

    public void getInboxMessageForId(String str, Callback callback) {
        WritableMap writableMapConvertObjectToWritableMap;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        String str2 = null;
        if (cleverTapAPI != null) {
            CTInboxMessage inboxMessageForId = cleverTapAPI.getInboxMessageForId(str);
            writableMapConvertObjectToWritableMap = (inboxMessageForId == null || inboxMessageForId.getData() == null) ? null : CleverTapUtils.convertObjectToWritableMap(inboxMessageForId.getData());
        } else {
            str2 = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
            writableMapConvertObjectToWritableMap = null;
        }
        callbackWithErrorAndResult(callback, str2, writableMapConvertObjectToWritableMap);
    }

    public void getInboxMessageUnreadCount(Callback callback) {
        int inboxMessageUnreadCount;
        String str;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            inboxMessageUnreadCount = cleverTapAPI.getInboxMessageUnreadCount();
            str = null;
        } else {
            inboxMessageUnreadCount = -1;
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        callbackWithErrorAndResult(callback, str, Integer.valueOf(inboxMessageUnreadCount));
    }

    public void deleteInboxMessageForId(String str) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.deleteInboxMessage(str);
        } else {
            Log.e("CleverTapReact", ErrorMessages.CLEVERTAP_NOT_INITIALIZED);
        }
    }

    public void getUnreadInboxMessages(Callback callback) {
        getInboxMessages(callback, InBoxMessages.UNREAD);
    }

    public void initializeInbox() {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.initializeInbox();
            Log.e("CleverTapReact", "initializeInbox Called");
        }
    }

    public void markReadInboxMessageForId(String str) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.markReadInboxMessage(str);
        } else {
            Log.e("CleverTapReact", ErrorMessages.CLEVERTAP_NOT_INITIALIZED);
        }
    }

    public void markReadInboxMessagesForIDs(ReadableArray readableArray) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.markReadInboxMessagesForIDs(arrayListStringFromReadableArray(readableArray));
        } else {
            Log.e("CleverTapReact", ErrorMessages.CLEVERTAP_NOT_INITIALIZED);
        }
    }

    public void deleteInboxMessagesForIDs(ReadableArray readableArray) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.deleteInboxMessagesForIDs(arrayListStringFromReadableArray(readableArray));
        } else {
            Log.e("CleverTapReact", ErrorMessages.CLEVERTAP_NOT_INITIALIZED);
        }
    }

    public void pushInboxNotificationClickedEventForId(String str) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.pushInboxNotificationClickedEvent(str);
        } else {
            Log.e("CleverTapReact", ErrorMessages.CLEVERTAP_NOT_INITIALIZED);
        }
    }

    public void pushInboxNotificationViewedEventForId(String str) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.pushInboxNotificationViewedEvent(str);
        } else {
            Log.e("CleverTapReact", ErrorMessages.CLEVERTAP_NOT_INITIALIZED);
        }
    }

    public void showInbox(ReadableMap readableMap) {
        CTInboxStyleConfig cTInboxStyleConfigStyleConfigFromReadableMap = styleConfigFromReadableMap(readableMap);
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.showAppInbox(cTInboxStyleConfigStyleConfigFromReadableMap);
        }
    }

    public void dismissInbox() {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.dismissAppInbox();
        }
    }

    public void getInitialUrl(Callback callback) {
        String string;
        Uri uri = sLaunchUri;
        String str = null;
        if (uri == null) {
            str = "CleverTap InitialUrl is null";
            string = null;
        } else {
            string = uri.toString();
        }
        callbackWithErrorAndResult(callback, str, string);
    }

    public void getLastFetchTimeStampInMillis(Callback callback) {
        String str;
        String strValueOf;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        String str2 = null;
        if (cleverTapAPI != null) {
            CTProductConfigController cTProductConfigControllerProductConfig = cleverTapAPI.productConfig();
            if (cTProductConfigControllerProductConfig != null) {
                strValueOf = String.valueOf(cTProductConfigControllerProductConfig.getLastFetchTimeStampInMillis());
                callbackWithErrorAndResult(callback, str2, strValueOf);
            }
            str = ErrorMessages.PRODUCTCONFIG_NOT_INITIALIZED;
        } else {
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        str2 = str;
        strValueOf = null;
        callbackWithErrorAndResult(callback, str2, strValueOf);
    }

    public void getString(String str, Callback callback) {
        String str2;
        String string;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        String str3 = null;
        if (cleverTapAPI != null) {
            CTProductConfigController cTProductConfigControllerProductConfig = cleverTapAPI.productConfig();
            if (cTProductConfigControllerProductConfig != null) {
                string = cTProductConfigControllerProductConfig.getString(str);
                callbackWithErrorAndResult(callback, str3, string);
            }
            str2 = ErrorMessages.PRODUCTCONFIG_NOT_INITIALIZED;
        } else {
            str2 = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        str3 = str2;
        string = null;
        callbackWithErrorAndResult(callback, str3, string);
    }

    public void onUserLogin(ReadableMap readableMap) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        cleverTapAPI.onUserLogin(profileFromReadableMap(readableMap));
    }

    public void profileAddMultiValue(String str, String str2) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        cleverTapAPI.addMultiValueForKey(str2, str);
    }

    public void profileAddMultiValues(ReadableArray readableArray, String str) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        cleverTapAPI.addMultiValuesForKey(str, arrayListStringFromReadableArray(readableArray));
    }

    public void profileGetCleverTapAttributionIdentifier(Callback callback) {
        String cleverTapAttributionIdentifier;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        String str = null;
        if (cleverTapAPI != null) {
            cleverTapAttributionIdentifier = cleverTapAPI.getCleverTapAttributionIdentifier();
        } else {
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
            cleverTapAttributionIdentifier = null;
        }
        callbackWithErrorAndResult(callback, str, cleverTapAttributionIdentifier);
    }

    public void profileGetCleverTapID(Callback callback) {
        String cleverTapID;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        String str = null;
        if (cleverTapAPI != null) {
            cleverTapID = cleverTapAPI.getCleverTapID();
        } else {
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
            cleverTapID = null;
        }
        callbackWithErrorAndResult(callback, str, cleverTapID);
    }

    public void getCleverTapID(final Callback callback) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.getCleverTapID(new OnInitCleverTapIDListener() { // from class: com.clevertap.react.CleverTapModuleImpl.1
                @Override // com.clevertap.android.sdk.interfaces.OnInitCleverTapIDListener
                public void onInitCleverTapID(String str) {
                    CleverTapModuleImpl.this.callbackWithErrorAndResult(callback, null, str);
                }
            });
        } else {
            callbackWithErrorAndResult(callback, ErrorMessages.CLEVERTAP_NOT_INITIALIZED, null);
        }
    }

    public void profileGetProperty(String str, Callback callback) {
        Object property;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        String str2 = null;
        if (cleverTapAPI != null) {
            property = cleverTapAPI.getProperty(str);
            if (property instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) property;
                WritableArray writableArrayCreateArray = Arguments.createArray();
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        writableArrayCreateArray.pushString(jSONArray.get(i).toString());
                    } catch (JSONException unused) {
                    }
                }
                property = writableArrayCreateArray;
            }
        } else {
            str2 = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
            property = null;
        }
        callbackWithErrorAndResult(callback, str2, property);
    }

    public void profileRemoveMultiValue(String str, String str2) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        cleverTapAPI.removeMultiValueForKey(str2, str);
    }

    public void profileRemoveMultiValues(ReadableArray readableArray, String str) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        cleverTapAPI.removeMultiValuesForKey(str, arrayListStringFromReadableArray(readableArray));
    }

    public void profileRemoveValueForKey(String str) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        cleverTapAPI.removeValueForKey(str);
    }

    public void profileSet(ReadableMap readableMap) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        cleverTapAPI.pushProfile(profileFromReadableMap(readableMap));
    }

    public void profileSetMultiValues(ReadableArray readableArray, String str) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        cleverTapAPI.setMultiValuesForKey(str, arrayListStringFromReadableArray(readableArray));
    }

    public void pushDisplayUnitClickedEventForID(String str) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.pushDisplayUnitClickedEventForID(str);
        } else {
            Log.e("CleverTapReact", ErrorMessages.CLEVERTAP_NOT_INITIALIZED);
        }
    }

    public void pushDisplayUnitViewedEventForID(String str) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.pushDisplayUnitViewedEventForID(str);
        } else {
            Log.e("CleverTapReact", ErrorMessages.CLEVERTAP_NOT_INITIALIZED);
        }
    }

    public void pushInstallReferrer(String str, String str2, String str3) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        cleverTapAPI.pushInstallReferrer(str, str2, str3);
    }

    public void recordChargedEvent(ReadableMap readableMap, ReadableArray readableArray) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null || readableMap == null) {
            return;
        }
        HashMap<String, Object> mapEventPropsFromReadableMap = eventPropsFromReadableMap(readableMap, Object.class);
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        if (readableArray != null) {
            for (int i = 0; i < readableArray.size(); i++) {
                try {
                    arrayList.add(eventPropsFromReadableMap(readableArray.getMap(i), Object.class));
                } catch (Throwable th) {
                    Log.e("CleverTapReact", th.getLocalizedMessage());
                }
            }
        }
        try {
            cleverTapAPI.pushChargedEvent(mapEventPropsFromReadableMap, arrayList);
        } catch (Throwable th2) {
            Log.e("CleverTapReact", th2.getLocalizedMessage());
        }
    }

    public void recordEvent(String str, ReadableMap readableMap) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        HashMap mapEventPropsFromReadableMap = eventPropsFromReadableMap(readableMap, Object.class);
        if (mapEventPropsFromReadableMap == null) {
            cleverTapAPI.pushEvent(str);
        } else {
            cleverTapAPI.pushEvent(str, mapEventPropsFromReadableMap);
        }
    }

    public void recordScreenView(String str) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        try {
            cleverTapAPI.recordScreen(str);
        } catch (NullPointerException e) {
            Log.e("CleverTapReact", "Something went wrong in native SDK!");
            e.printStackTrace();
        }
    }

    public void registerForPush() {
        Log.i("CleverTapReact", "CleverTap.registerForPush is a no-op in Android");
    }

    public void reset() {
        CTProductConfigController ctProductConfigController = getCtProductConfigController();
        if (ctProductConfigController == null) {
            return;
        }
        ctProductConfigController.reset();
    }

    @Deprecated(since = "3.2.0")
    public void sessionGetPreviousVisitTime(Callback callback) {
        int previousVisitTime;
        String str;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            previousVisitTime = cleverTapAPI.getPreviousVisitTime();
            str = null;
        } else {
            previousVisitTime = -1;
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        callbackWithErrorAndResult(callback, str, Integer.valueOf(previousVisitTime));
    }

    public void sessionGetScreenCount(Callback callback) {
        int screenCount;
        String str;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            screenCount = cleverTapAPI.getScreenCount();
            str = null;
        } else {
            screenCount = -1;
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        callbackWithErrorAndResult(callback, str, Integer.valueOf(screenCount));
    }

    public void sessionGetTimeElapsed(Callback callback) {
        int timeElapsed;
        String str;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            timeElapsed = cleverTapAPI.getTimeElapsed();
            str = null;
        } else {
            timeElapsed = -1;
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        callbackWithErrorAndResult(callback, str, Integer.valueOf(timeElapsed));
    }

    @Deprecated(since = "3.2.0")
    public void sessionGetTotalVisits(Callback callback) {
        int totalVisits;
        String str;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            totalVisits = cleverTapAPI.getTotalVisits();
            str = null;
        } else {
            totalVisits = -1;
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        callbackWithErrorAndResult(callback, str, Integer.valueOf(totalVisits));
    }

    public void sessionGetUTMDetails(Callback callback) {
        WritableMap writableMapUtmDetailsToWritableMap;
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        String str = null;
        if (cleverTapAPI != null) {
            writableMapUtmDetailsToWritableMap = utmDetailsToWritableMap(cleverTapAPI.getUTMDetails());
        } else {
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
            writableMapUtmDetailsToWritableMap = null;
        }
        callbackWithErrorAndResult(callback, str, writableMapUtmDetailsToWritableMap);
    }

    public void setDebugLevel(int i) {
        CleverTapAPI.setDebugLevel(i);
    }

    public void setDefaultsMap(ReadableMap readableMap) {
        CTProductConfigController ctProductConfigController = getCtProductConfigController();
        if (ctProductConfigController == null) {
            return;
        }
        ctProductConfigController.setDefaults(eventPropsFromReadableMap(readableMap, Object.class));
    }

    public void setLocation(double d, double d2) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        Location location = new Location("CleverTapReact");
        location.setLatitude(d);
        location.setLongitude(d2);
        cleverTapAPI.setLocation(location);
    }

    public void setMinimumFetchIntervalInSeconds(int i) {
        CTProductConfigController ctProductConfigController = getCtProductConfigController();
        if (ctProductConfigController == null) {
            return;
        }
        ctProductConfigController.setMinimumFetchIntervalInSeconds(i);
    }

    public void setOffline(boolean z) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        cleverTapAPI.setOffline(z);
    }

    public void setOptOut(boolean z) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return;
        }
        cleverTapAPI.setOptOut(z);
    }

    public void setPushTokenAsString(String str, String str2) {
        Logger.v("setPushTokenAsString() called with: token = [" + str + "], type = [" + str2 + "]");
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null || str == null || str2 == null) {
            return;
        }
        str2.hashCode();
        switch (str2) {
            case "BPS":
                cleverTapAPI.pushBaiduRegistrationId(str, true);
                break;
            case "FCM":
                cleverTapAPI.pushFcmRegistrationId(str, true);
                break;
            case "HPS":
                cleverTapAPI.pushHuaweiRegistrationId(str, true);
                break;
            default:
                Log.e("CleverTapReact", "Unknown push token type " + str2);
                break;
        }
    }

    public void profileIncrementValueForKey(Double d, String str) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.incrementValue(str, d);
        }
    }

    public void profileDecrementValueForKey(Double d, String str) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.decrementValue(str, d);
        }
    }

    public void suspendInAppNotifications() {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.suspendInAppNotifications();
        }
    }

    public void discardInAppNotifications() {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.discardInAppNotifications();
        }
    }

    public void resumeInAppNotifications() {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.resumeInAppNotifications();
        }
    }

    public void customTemplateSetDismissed(String str, Promise promise) {
        resolveWithTemplateContext(str, promise, new TemplateContextAction() { // from class: com.clevertap.react.CleverTapModuleImpl$$ExternalSyntheticLambda2
            @Override // com.clevertap.react.CleverTapModuleImpl.TemplateContextAction
            public final Object execute(CustomTemplateContext customTemplateContext) {
                return CleverTapModuleImpl.lambda$customTemplateSetDismissed$0(customTemplateContext);
            }
        });
    }

    static /* synthetic */ Object lambda$customTemplateSetDismissed$0(CustomTemplateContext customTemplateContext) {
        customTemplateContext.setDismissed();
        return null;
    }

    public void customTemplateSetPresented(String str, Promise promise) {
        resolveWithTemplateContext(str, promise, new TemplateContextAction() { // from class: com.clevertap.react.CleverTapModuleImpl$$ExternalSyntheticLambda0
            @Override // com.clevertap.react.CleverTapModuleImpl.TemplateContextAction
            public final Object execute(CustomTemplateContext customTemplateContext) {
                return CleverTapModuleImpl.lambda$customTemplateSetPresented$1(customTemplateContext);
            }
        });
    }

    static /* synthetic */ Object lambda$customTemplateSetPresented$1(CustomTemplateContext customTemplateContext) {
        customTemplateContext.setPresented();
        return null;
    }

    public void customTemplateRunAction(String str, final String str2, Promise promise) {
        resolveWithTemplateContext(str, promise, new TemplateContextAction() { // from class: com.clevertap.react.CleverTapModuleImpl$$ExternalSyntheticLambda3
            @Override // com.clevertap.react.CleverTapModuleImpl.TemplateContextAction
            public final Object execute(CustomTemplateContext customTemplateContext) {
                return CleverTapModuleImpl.lambda$customTemplateRunAction$2(str2, customTemplateContext);
            }
        });
    }

    static /* synthetic */ Object lambda$customTemplateRunAction$2(String str, CustomTemplateContext customTemplateContext) {
        if (customTemplateContext instanceof CustomTemplateContext.TemplateContext) {
            ((CustomTemplateContext.TemplateContext) customTemplateContext).triggerActionArgument(str, null);
        }
        return null;
    }

    public void customTemplateGetStringArg(String str, final String str2, Promise promise) {
        resolveWithTemplateContext(str, promise, new TemplateContextAction() { // from class: com.clevertap.react.CleverTapModuleImpl$$ExternalSyntheticLambda5
            @Override // com.clevertap.react.CleverTapModuleImpl.TemplateContextAction
            public final Object execute(CustomTemplateContext customTemplateContext) {
                return customTemplateContext.getString(str2);
            }
        });
    }

    public void customTemplateGetNumberArg(String str, final String str2, Promise promise) {
        resolveWithTemplateContext(str, promise, new TemplateContextAction() { // from class: com.clevertap.react.CleverTapModuleImpl$$ExternalSyntheticLambda1
            @Override // com.clevertap.react.CleverTapModuleImpl.TemplateContextAction
            public final Object execute(CustomTemplateContext customTemplateContext) {
                return customTemplateContext.getDouble(str2);
            }
        });
    }

    public void customTemplateGetBooleanArg(String str, final String str2, Promise promise) {
        resolveWithTemplateContext(str, promise, new TemplateContextAction() { // from class: com.clevertap.react.CleverTapModuleImpl$$ExternalSyntheticLambda8
            @Override // com.clevertap.react.CleverTapModuleImpl.TemplateContextAction
            public final Object execute(CustomTemplateContext customTemplateContext) {
                return customTemplateContext.getBoolean(str2);
            }
        });
    }

    public void customTemplateGetFileArg(String str, final String str2, Promise promise) {
        resolveWithTemplateContext(str, promise, new TemplateContextAction() { // from class: com.clevertap.react.CleverTapModuleImpl$$ExternalSyntheticLambda7
            @Override // com.clevertap.react.CleverTapModuleImpl.TemplateContextAction
            public final Object execute(CustomTemplateContext customTemplateContext) {
                return customTemplateContext.getFile(str2);
            }
        });
    }

    public void customTemplateGetObjectArg(String str, final String str2, Promise promise) {
        resolveWithTemplateContext(str, promise, new TemplateContextAction() { // from class: com.clevertap.react.CleverTapModuleImpl$$ExternalSyntheticLambda4
            @Override // com.clevertap.react.CleverTapModuleImpl.TemplateContextAction
            public final Object execute(CustomTemplateContext customTemplateContext) {
                return CleverTapModuleImpl.lambda$customTemplateGetObjectArg$7(str2, customTemplateContext);
            }
        });
    }

    static /* synthetic */ Object lambda$customTemplateGetObjectArg$7(String str, CustomTemplateContext customTemplateContext) {
        Map<String, Object> map = customTemplateContext.getMap(str);
        if (map != null) {
            return CleverTapUtils.MapUtil.toWritableMap(map);
        }
        return null;
    }

    public void customTemplateContextToString(String str, Promise promise) {
        resolveWithTemplateContext(str, promise, new TemplateContextAction() { // from class: com.clevertap.react.CleverTapModuleImpl$$ExternalSyntheticLambda6
            @Override // com.clevertap.react.CleverTapModuleImpl.TemplateContextAction
            public final Object execute(CustomTemplateContext customTemplateContext) {
                return customTemplateContext.toString();
            }
        });
    }

    public void syncCustomTemplates() {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.syncRegisteredInAppTemplates();
        }
    }

    private void resolveWithTemplateContext(String str, Promise promise, TemplateContextAction templateContextAction) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            CustomTemplateContext activeContextForTemplate = cleverTapAPI.getActiveContextForTemplate(str);
            if (activeContextForTemplate != null) {
                promise.resolve(templateContextAction.execute(activeContextForTemplate));
                return;
            } else {
                promise.reject("CustomTemplateError", "Custom template: " + str + " is not currently being presented");
                return;
            }
        }
        promise.reject("CustomTemplateError", "CleverTap is not initialized");
    }

    public void syncVariables() {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.syncVariables();
        }
    }

    public void syncVariablesinProd(boolean z, Callback callback) {
        Log.i("CleverTapReact", "CleverTap syncVariablesinProd is no-op in Android");
    }

    public void fetchVariables() throws JSONException {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.fetchVariables();
        }
    }

    public void defineVariables(ReadableMap readableMap) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            for (Map.Entry<String, Object> entry : readableMap.toHashMap().entrySet()) {
                String key = entry.getKey();
                variables.put(key, cleverTapAPI.defineVariable(key, entry.getValue()));
            }
        }
    }

    public void defineFileVariable(String str) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            variables.put(str, cleverTapAPI.defineFileVariable(str));
        }
    }

    public void fetchVariables(final Callback callback) throws JSONException {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.fetchVariables(new FetchVariablesCallback() { // from class: com.clevertap.react.CleverTapModuleImpl.2
                @Override // com.clevertap.android.sdk.variables.callbacks.FetchVariablesCallback
                public void onVariablesFetched(boolean z) {
                    CleverTapModuleImpl.this.callbackWithErrorAndResult(callback, null, Boolean.valueOf(z));
                }
            });
        } else {
            callbackWithErrorAndResult(callback, ErrorMessages.CLEVERTAP_NOT_INITIALIZED, null);
        }
    }

    public void getVariable(String str, Callback callback) {
        String localizedMessage;
        Object variableValue;
        String str2 = null;
        if (getCleverTapAPI() != null) {
            try {
                variableValue = getVariableValue(str);
            } catch (IllegalArgumentException e) {
                localizedMessage = e.getLocalizedMessage();
            }
            callbackWithErrorAndResult(callback, str2, variableValue);
        }
        localizedMessage = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        str2 = localizedMessage;
        variableValue = null;
        callbackWithErrorAndResult(callback, str2, variableValue);
    }

    public void getVariables(Callback callback) {
        callbackWithErrorAndResult(callback, null, getVariablesValues());
    }

    public void onValueChanged(final String str) {
        if (variables.containsKey(str)) {
            Var var = (Var) variables.get(str);
            if (var != null) {
                var.addValueChangedCallback(new VariableCallback<Object>() { // from class: com.clevertap.react.CleverTapModuleImpl.3
                    @Override // com.clevertap.android.sdk.variables.callbacks.VariableCallback
                    public void onValueChanged(Var<Object> var2) {
                        WritableMap variableValueAsWritableMap;
                        try {
                            variableValueAsWritableMap = CleverTapModuleImpl.this.getVariableValueAsWritableMap(str);
                        } catch (IllegalArgumentException e) {
                            Log.e("CleverTapReact", e.getLocalizedMessage());
                            variableValueAsWritableMap = null;
                        }
                        CleverTapModuleImpl.this.sendEvent(CleverTapEvent.CLEVERTAP_ON_VALUE_CHANGED, variableValueAsWritableMap);
                    }
                });
                return;
            } else {
                Log.d("CleverTapReact", "Variable value with name = " + str + " contains null value. Not setting onValueChanged callback.");
                return;
            }
        }
        Log.e("CleverTapReact", "Variable name = " + str + " does not exist. Make sure you set variable first.");
    }

    public void onFileValueChanged(final String str) {
        if (variables.containsKey(str)) {
            Var var = (Var) variables.get(str);
            if (var != null) {
                var.addFileReadyHandler(new VariableCallback<Object>() { // from class: com.clevertap.react.CleverTapModuleImpl.4
                    @Override // com.clevertap.android.sdk.variables.callbacks.VariableCallback
                    public void onValueChanged(Var<Object> var2) {
                        WritableMap variableValueAsWritableMap;
                        try {
                            variableValueAsWritableMap = CleverTapModuleImpl.this.getVariableValueAsWritableMap(str);
                        } catch (IllegalArgumentException e) {
                            Log.e("CleverTapReact", e.getLocalizedMessage());
                            variableValueAsWritableMap = null;
                        }
                        CleverTapModuleImpl.this.sendEvent(CleverTapEvent.CLEVERTAP_ON_FILE_VALUE_CHANGED, variableValueAsWritableMap);
                    }
                });
                return;
            } else {
                Log.d("CleverTapReact", "File variable object with name = " + str + " contains null value. Not setting onFileValueChanged callback.");
                return;
            }
        }
        Log.e("CleverTapReact", "File variable name = " + str + " does not exist. Make sure you set file variable first.");
    }

    public void onVariablesChanged() {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.addVariablesChangedCallback(new VariablesChangedCallback() { // from class: com.clevertap.react.CleverTapModuleImpl.5
                @Override // com.clevertap.android.sdk.variables.callbacks.VariablesChangedCallback
                public void variablesChanged() {
                    CleverTapModuleImpl.this.sendEvent(CleverTapEvent.CLEVERTAP_ON_VARIABLES_CHANGED, CleverTapModuleImpl.this.getVariablesValues());
                }
            });
        }
    }

    public void onOneTimeVariablesChanged() {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.addOneTimeVariablesChangedCallback(new VariablesChangedCallback() { // from class: com.clevertap.react.CleverTapModuleImpl.6
                @Override // com.clevertap.android.sdk.variables.callbacks.VariablesChangedCallback
                public void variablesChanged() {
                    CleverTapModuleImpl.this.sendEvent(CleverTapEvent.CLEVERTAP_ON_ONE_TIME_VARIABLES_CHANGED, CleverTapModuleImpl.this.getVariablesValues());
                }
            });
        }
    }

    public void onVariablesChangedAndNoDownloadsPending() {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.onVariablesChangedAndNoDownloadsPending(new VariablesChangedCallback() { // from class: com.clevertap.react.CleverTapModuleImpl.7
                @Override // com.clevertap.android.sdk.variables.callbacks.VariablesChangedCallback
                public void variablesChanged() {
                    CleverTapModuleImpl.this.sendEvent(CleverTapEvent.CLEVERTAP_ON_VARIABLES_CHANGED_AND_NO_DOWNLOADS_PENDING, CleverTapModuleImpl.this.getVariablesValues());
                }
            });
        }
    }

    public void onceVariablesChangedAndNoDownloadsPending() {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.onceVariablesChangedAndNoDownloadsPending(new VariablesChangedCallback() { // from class: com.clevertap.react.CleverTapModuleImpl.8
                @Override // com.clevertap.android.sdk.variables.callbacks.VariablesChangedCallback
                public void variablesChanged() {
                    CleverTapModuleImpl.this.sendEvent(CleverTapEvent.CLEVERTAP_ONCE_VARIABLES_CHANGED_AND_NO_DOWNLOADS_PENDING, CleverTapModuleImpl.this.getVariablesValues());
                }
            });
        }
    }

    public void clearInAppResources(boolean z) {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.clearInAppResources(z);
        }
    }

    public void fetchInApps(final Callback callback) throws JSONException {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            cleverTapAPI.fetchInApps(new FetchInAppsCallback() { // from class: com.clevertap.react.CleverTapModuleImpl.9
                @Override // com.clevertap.android.sdk.inapp.callbacks.FetchInAppsCallback
                public void onInAppsFetched(boolean z) {
                    CleverTapModuleImpl.this.callbackWithErrorAndResult(callback, null, Boolean.valueOf(z));
                }
            });
        } else {
            callbackWithErrorAndResult(callback, ErrorMessages.CLEVERTAP_NOT_INITIALIZED, null);
        }
    }

    public void onEventListenerAdded(String str) {
        CleverTapEvent cleverTapEventFromName = CleverTapEvent.fromName(str);
        if (cleverTapEventFromName == null) {
            Log.e("CleverTapReact", "Event listener added for unsupported event " + str);
        } else {
            CleverTapEventEmitter.INSTANCE.disableBuffer(cleverTapEventFromName);
            CleverTapEventEmitter.INSTANCE.flushBuffer(cleverTapEventFromName);
        }
    }

    private void enableEventEmitter(ReactContext reactContext) {
        CleverTapEventEmitter.INSTANCE.setReactContext(reactContext);
        new Handler().postDelayed(new Runnable() { // from class: com.clevertap.react.CleverTapModuleImpl$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                CleverTapEventEmitter.INSTANCE.resetAllBuffers(false);
            }
        }, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackWithErrorAndResult(Callback callback, String str, Object obj) {
        if (callback == null) {
            Log.i("CleverTapReact", "CleverTap callback is null");
            return;
        }
        try {
            callback.invoke(str, obj);
        } catch (Throwable th) {
            Log.e("CleverTapReact", th.getLocalizedMessage());
        }
    }

    private Object getVariableValue(String str) {
        if (variables.containsKey(str)) {
            Var var = (Var) variables.get(str);
            Object objValue = var.value();
            String strKind = var.kind();
            strKind.hashCode();
            return !strKind.equals("group") ? objValue : CleverTapUtils.MapUtil.toWritableMap((Map) objValue);
        }
        throw new IllegalArgumentException("Variable name = " + str + " does not exist. Make sure you set variable first.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WritableMap getVariableValueAsWritableMap(String str) {
        if (variables.containsKey(str)) {
            Var var = (Var) variables.get(str);
            var.value();
            return CleverTapUtils.MapUtil.addValue(str, var.value());
        }
        throw new IllegalArgumentException("Variable name = " + str + " does not exist.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WritableMap getVariablesValues() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            writableMapCreateMap.merge(CleverTapUtils.MapUtil.addValue(entry.getKey(), ((Var) entry.getValue()).value()));
        }
        return writableMapCreateMap;
    }

    private <T> HashMap<String, T> eventPropsFromReadableMap(ReadableMap readableMap, Class<T> cls) {
        if (readableMap == null) {
            return null;
        }
        HashMap<String, T> map = new HashMap<>();
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            try {
                String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
                ReadableType type = readableMap.getType(strNextKey);
                if (type == ReadableType.String) {
                    map.put(strNextKey, cls.cast(readableMap.getString(strNextKey)));
                } else if (type == ReadableType.Boolean) {
                    map.put(strNextKey, cls.cast(Boolean.valueOf(readableMap.getBoolean(strNextKey))));
                } else if (type == ReadableType.Number) {
                    try {
                        try {
                            map.put(strNextKey, cls.cast(Double.valueOf(readableMap.getDouble(strNextKey))));
                        } catch (Throwable unused) {
                            Log.e("CleverTapReact", "Unhandled ReadableType.Number from ReadableMap");
                        }
                    } catch (Throwable unused2) {
                        map.put(strNextKey, cls.cast(Integer.valueOf(readableMap.getInt(strNextKey))));
                    }
                } else {
                    Log.e("CleverTapReact", "Unhandled event property ReadableType");
                }
            } catch (Throwable th) {
                Log.e("CleverTapReact", th.getLocalizedMessage());
            }
        }
        return map;
    }

    private void initCtInstance(CleverTapAPI cleverTapAPI) {
        cleverTapAPI.setLibrary("React-Native");
        CleverTapListenerProxy.INSTANCE.attachToInstance(cleverTapAPI);
    }

    private CleverTapAPI getCleverTapAPI() {
        if (this.mCleverTap == null) {
            CleverTapAPI defaultInstance = CleverTapAPI.getDefaultInstance(this.context);
            if (defaultInstance != null) {
                initCtInstance(defaultInstance);
            }
            this.mCleverTap = defaultInstance;
        }
        return this.mCleverTap;
    }

    public void setInstanceWithAccountId(String str) {
        CleverTapAPI globalInstance;
        CleverTapAPI cleverTapAPI = this.mCleverTap;
        if ((cleverTapAPI == null || !str.equals(cleverTapAPI.getAccountId())) && (globalInstance = CleverTapAPI.getGlobalInstance(this.context, str)) != null) {
            initCtInstance(globalInstance);
            this.mCleverTap = globalInstance;
            Log.i("CleverTapReact", "CleverTap instance changed for accountId " + str);
        }
    }

    private CTProductConfigController getCtProductConfigController() {
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI == null) {
            return null;
        }
        return cleverTapAPI.productConfig();
    }

    private void getInboxMessages(Callback callback, InBoxMessages inBoxMessages) {
        String str;
        ArrayList<CTInboxMessage> arrayList = new ArrayList<>();
        WritableArray writableArrayCreateArray = Arguments.createArray();
        CleverTapAPI cleverTapAPI = getCleverTapAPI();
        if (cleverTapAPI != null) {
            if (inBoxMessages == InBoxMessages.ALL) {
                arrayList = cleverTapAPI.getAllInboxMessages();
            } else if (inBoxMessages == InBoxMessages.UNREAD) {
                arrayList = cleverTapAPI.getUnreadInboxMessages();
            }
            Iterator<CTInboxMessage> it = arrayList.iterator();
            while (it.hasNext()) {
                CTInboxMessage next = it.next();
                if (next != null && next.getData() != null) {
                    writableArrayCreateArray.pushMap(CleverTapUtils.convertObjectToWritableMap(next.getData()));
                }
            }
            str = null;
        } else {
            str = ErrorMessages.CLEVERTAP_NOT_INITIALIZED;
        }
        callbackWithErrorAndResult(callback, str, writableArrayCreateArray);
    }

    private HashMap<String, Object> profileFromReadableMap(ReadableMap readableMap) {
        String strNextKey;
        ReadableType type;
        if (readableMap == null) {
            return null;
        }
        HashMap<String, Object> map = new HashMap<>();
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            try {
                strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
                type = readableMap.getType(strNextKey);
            } catch (Throwable th) {
                Log.e("CleverTapReact", th.getLocalizedMessage());
            }
            if ("DOB".equals(strNextKey) && type == ReadableType.String) {
                try {
                    map.put(strNextKey, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(readableMap.getString(strNextKey)));
                } catch (Throwable th2) {
                    Log.e("CleverTapReact", th2.getLocalizedMessage());
                }
            } else if (type == ReadableType.String) {
                map.put(strNextKey, readableMap.getString(strNextKey));
            } else if (type == ReadableType.Boolean) {
                map.put(strNextKey, Boolean.valueOf(readableMap.getBoolean(strNextKey)));
            } else if (type == ReadableType.Number) {
                try {
                    try {
                        map.put(strNextKey, Double.valueOf(readableMap.getDouble(strNextKey)));
                    } catch (Throwable unused) {
                        Log.e("CleverTapReact", "Unhandled ReadableType.Number from ReadableMap");
                    }
                } catch (Throwable unused2) {
                    map.put(strNextKey, Integer.valueOf(readableMap.getInt(strNextKey)));
                }
            } else if (type == ReadableType.Array) {
                try {
                    map.put(strNextKey, arrayListStringFromReadableArray(readableMap.getArray(strNextKey)));
                } catch (Throwable unused3) {
                    Log.e("CleverTapReact", "Unhandled ReadableType.Array from ReadableMap");
                }
            } else {
                Log.e("CleverTapReact", "Unhandled profile property ReadableType");
            }
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(CleverTapEvent cleverTapEvent, @Nullable Object obj) {
        CleverTapEventEmitter.INSTANCE.emit(cleverTapEvent, obj);
    }

    private JSONObject localInAppConfigFromReadableMap(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        boolean z = false;
        String str = null;
        CTLocalInApp.InAppType inAppTypeInAppTypeFromString = null;
        String string = null;
        String string2 = null;
        String string3 = null;
        String string4 = null;
        String string5 = null;
        String string6 = null;
        String string7 = null;
        String string8 = null;
        String string9 = null;
        String string10 = null;
        String string11 = null;
        boolean z2 = false;
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String string12 = str;
            try {
                String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
                ReadableMapKeySetIterator readableMapKeySetIterator = readableMapKeySetIteratorKeySetIterator;
                ReadableType type = readableMap.getType(strNextKey);
                boolean z3 = z;
                if ("inAppType".equals(strNextKey) && type == ReadableType.String) {
                    try {
                        inAppTypeInAppTypeFromString = inAppTypeFromString(readableMap.getString(strNextKey));
                    } catch (Throwable th) {
                        th = th;
                        Log.e("CleverTapReact", "invalid parameters in push primer config" + th.getLocalizedMessage());
                        return null;
                    }
                }
                if ("titleText".equals(strNextKey) && type == ReadableType.String) {
                    string = readableMap.getString(strNextKey);
                }
                if ("messageText".equals(strNextKey) && type == ReadableType.String) {
                    string2 = readableMap.getString(strNextKey);
                }
                if ("followDeviceOrientation".equals(strNextKey) && type == ReadableType.Boolean) {
                    z2 = readableMap.getBoolean(strNextKey);
                }
                if ("positiveBtnText".equals(strNextKey) && type == ReadableType.String) {
                    string3 = readableMap.getString(strNextKey);
                }
                if ("negativeBtnText".equals(strNextKey) && type == ReadableType.String) {
                    string4 = readableMap.getString(strNextKey);
                }
                if ("fallbackToSettings".equals(strNextKey) && type == ReadableType.Boolean) {
                    z3 = readableMap.getBoolean(strNextKey);
                }
                if (ViewProps.BACKGROUND_COLOR.equals(strNextKey) && type == ReadableType.String) {
                    string5 = readableMap.getString(strNextKey);
                }
                if ("btnBorderColor".equals(strNextKey) && type == ReadableType.String) {
                    string6 = readableMap.getString(strNextKey);
                }
                if ("titleTextColor".equals(strNextKey) && type == ReadableType.String) {
                    string7 = readableMap.getString(strNextKey);
                }
                if ("messageTextColor".equals(strNextKey) && type == ReadableType.String) {
                    string8 = readableMap.getString(strNextKey);
                }
                if ("btnTextColor".equals(strNextKey) && type == ReadableType.String) {
                    string9 = readableMap.getString(strNextKey);
                }
                if ("imageUrl".equals(strNextKey) && type == ReadableType.String) {
                    string12 = readableMap.getString(strNextKey);
                }
                if ("btnBackgroundColor".equals(strNextKey) && type == ReadableType.String) {
                    string10 = readableMap.getString(strNextKey);
                }
                if ("btnBorderRadius".equals(strNextKey) && type == ReadableType.String) {
                    string11 = readableMap.getString(strNextKey);
                }
                str = string12;
                readableMapKeySetIteratorKeySetIterator = readableMapKeySetIterator;
                z = z3;
            } catch (Throwable th2) {
                th = th2;
            }
        }
        boolean z4 = z;
        String str2 = str;
        CTLocalInApp.Builder.Builder6 localInAppBuilderWithRequiredParam = getLocalInAppBuilderWithRequiredParam(inAppTypeInAppTypeFromString, string, string2, z2, string3, string4);
        if (string5 != null) {
            localInAppBuilderWithRequiredParam.setBackgroundColor(string5);
        }
        if (string6 != null) {
            localInAppBuilderWithRequiredParam.setBtnBorderColor(string6);
        }
        if (string7 != null) {
            localInAppBuilderWithRequiredParam.setTitleTextColor(string7);
        }
        if (string8 != null) {
            localInAppBuilderWithRequiredParam.setMessageTextColor(string8);
        }
        if (string9 != null) {
            localInAppBuilderWithRequiredParam.setBtnTextColor(string9);
        }
        if (str2 != null) {
            localInAppBuilderWithRequiredParam.setImageUrl(str2);
        }
        String str3 = string10;
        if (str3 != null) {
            localInAppBuilderWithRequiredParam.setBtnBackgroundColor(str3);
        }
        String str4 = string11;
        if (str4 != null) {
            localInAppBuilderWithRequiredParam.setBtnBorderRadius(str4);
        }
        localInAppBuilderWithRequiredParam.setFallbackToSettings(z4);
        JSONObject jSONObjectBuild = localInAppBuilderWithRequiredParam.getJsonObject();
        Log.i("CleverTapReact", "LocalInAppConfig for push primer prompt: " + jSONObjectBuild);
        return jSONObjectBuild;
    }

    private CTLocalInApp.Builder.Builder6 getLocalInAppBuilderWithRequiredParam(CTLocalInApp.InAppType inAppType, String str, String str2, boolean z, String str3, String str4) {
        if (inAppType == null || str == null || str2 == null || str3 == null || str4 == null) {
            throw new IllegalArgumentException("mandatory parameters are missing in push primer config");
        }
        return CTLocalInApp.builder().setInAppType(inAppType).setTitleText(str).setMessageText(str2).followDeviceOrientation(z).setPositiveBtnText(str3).setNegativeBtnText(str4);
    }

    private CTLocalInApp.InAppType inAppTypeFromString(String str) {
        if (str == null) {
            return null;
        }
        str.hashCode();
        if (str.equals("alert")) {
            return CTLocalInApp.InAppType.ALERT;
        }
        if (str.equals("half-interstitial")) {
            return CTLocalInApp.InAppType.HALF_INTERSTITIAL;
        }
        return null;
    }

    private CTInboxStyleConfig styleConfigFromReadableMap(ReadableMap readableMap) {
        if (readableMap == null) {
            return new CTInboxStyleConfig();
        }
        CTInboxStyleConfig cTInboxStyleConfig = new CTInboxStyleConfig();
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            try {
                String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
                ReadableType type = readableMap.getType(strNextKey);
                if ("navBarTitle".equals(strNextKey) && type == ReadableType.String) {
                    cTInboxStyleConfig.setNavBarTitle(readableMap.getString(strNextKey));
                }
                if ("navBarTitleColor".equals(strNextKey) && type == ReadableType.String) {
                    cTInboxStyleConfig.setNavBarTitleColor(readableMap.getString(strNextKey));
                }
                if ("navBarColor".equals(strNextKey) && type == ReadableType.String) {
                    cTInboxStyleConfig.setNavBarColor(readableMap.getString(strNextKey));
                }
                if ("inboxBackgroundColor".equals(strNextKey) && type == ReadableType.String) {
                    cTInboxStyleConfig.setInboxBackgroundColor(readableMap.getString(strNextKey));
                }
                if ("backButtonColor".equals(strNextKey) && type == ReadableType.String) {
                    cTInboxStyleConfig.setBackButtonColor(readableMap.getString(strNextKey));
                }
                if ("unselectedTabColor".equals(strNextKey) && type == ReadableType.String) {
                    cTInboxStyleConfig.setUnselectedTabColor(readableMap.getString(strNextKey));
                }
                if ("selectedTabColor".equals(strNextKey) && type == ReadableType.String) {
                    cTInboxStyleConfig.setSelectedTabColor(readableMap.getString(strNextKey));
                }
                if ("selectedTabIndicatorColor".equals(strNextKey) && type == ReadableType.String) {
                    cTInboxStyleConfig.setSelectedTabIndicatorColor(readableMap.getString(strNextKey));
                }
                if ("tabBackgroundColor".equals(strNextKey) && type == ReadableType.String) {
                    cTInboxStyleConfig.setTabBackgroundColor(readableMap.getString(strNextKey));
                }
                if ("tabs".equals(strNextKey) && type == ReadableType.Array) {
                    try {
                        cTInboxStyleConfig.setTabs(arrayListStringFromReadableArray(readableMap.getArray(strNextKey)));
                    } catch (Throwable unused) {
                        Log.e("CleverTapReact", "Unhandled ReadableType.Array from ReadableMap");
                    }
                }
                if ("noMessageText".equals(strNextKey) && type == ReadableType.String) {
                    cTInboxStyleConfig.setNoMessageViewText(readableMap.getString(strNextKey));
                }
                if ("noMessageTextColor".equals(strNextKey) && type == ReadableType.String) {
                    cTInboxStyleConfig.setNoMessageViewTextColor(readableMap.getString(strNextKey));
                }
                if ("firstTabTitle".equals(strNextKey) && type == ReadableType.String) {
                    cTInboxStyleConfig.setFirstTabTitle(readableMap.getString(strNextKey));
                }
            } catch (Throwable th) {
                Log.e("CleverTapReact", th.getLocalizedMessage());
                return new CTInboxStyleConfig();
            }
        }
        return cTInboxStyleConfig;
    }

    private static ArrayList<String> arrayListStringFromReadableArray(ReadableArray readableArray) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < readableArray.size(); i++) {
            int i2 = AnonymousClass10.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()];
            if (i2 == 2) {
                arrayList.add(String.valueOf(readableArray.getBoolean(i)));
            } else if (i2 == 3) {
                arrayList.add(String.valueOf(readableArray.getDouble(i)));
            } else if (i2 == 4) {
                arrayList.add(readableArray.getString(i));
            }
        }
        return arrayList;
    }

    /* renamed from: com.clevertap.react.CleverTapModuleImpl$10, reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] iArr = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = iArr;
            try {
                iArr[ReadableType.Null.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Map.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Array.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private static WritableMap eventLogToWritableMap(UserEventLog userEventLog) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        if (userEventLog != null) {
            writableMapCreateMap.putString("eventName", userEventLog.getEventName());
            writableMapCreateMap.putString(Column.NORMALIZED_EVENT_NAME, userEventLog.getNormalizedEventName());
            writableMapCreateMap.putDouble("firstTime", userEventLog.getFirstTs());
            writableMapCreateMap.putDouble("lastTime", userEventLog.getLastTs());
            writableMapCreateMap.putInt("count", userEventLog.getCountOfEvents());
            writableMapCreateMap.putString(Column.DEVICE_ID, userEventLog.getDeviceID());
        }
        return writableMapCreateMap;
    }

    private static WritableMap eventLogHistoryToWritableMap(Map<String, UserEventLog> map) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        if (map != null) {
            for (String str : map.keySet()) {
                writableMapCreateMap.putMap(str, eventLogToWritableMap(map.get(str)));
            }
        }
        return writableMapCreateMap;
    }

    @Deprecated(since = "3.2.0")
    private static WritableMap eventDetailToWritableMap(EventDetail eventDetail) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        if (eventDetail != null) {
            writableMapCreateMap.putString("name", eventDetail.getName());
            writableMapCreateMap.putInt("firstTime", eventDetail.getFirstTime());
            writableMapCreateMap.putInt("lastTime", eventDetail.getLastTime());
            writableMapCreateMap.putInt("count", eventDetail.getCount());
        }
        return writableMapCreateMap;
    }

    @Deprecated(since = "3.2.0")
    private static WritableMap eventHistoryToWritableMap(Map<String, EventDetail> map) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        if (map != null) {
            for (String str : map.keySet()) {
                writableMapCreateMap.putMap(str, eventDetailToWritableMap(map.get(str)));
            }
        }
        return writableMapCreateMap;
    }

    private static JSONArray jsonArrayFromReadableArray(ReadableArray readableArray) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < readableArray.size(); i++) {
            int i2 = AnonymousClass10.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()];
            if (i2 == 2) {
                jSONArray.put(readableArray.getBoolean(i));
            } else if (i2 == 3) {
                jSONArray.put(readableArray.getDouble(i));
            } else if (i2 == 4) {
                jSONArray.put(readableArray.getString(i));
            } else if (i2 == 5) {
                jSONArray.put(jsonObjectFromReadableMap(readableArray.getMap(i)));
            } else if (i2 == 6) {
                jSONArray.put(jsonArrayFromReadableArray(readableArray.getArray(i)));
            }
        }
        return jSONArray;
    }

    private static JSONObject jsonObjectFromReadableMap(ReadableMap readableMap) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            switch (AnonymousClass10.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(strNextKey).ordinal()]) {
                case 1:
                    jSONObject.put(strNextKey, JSONObject.NULL);
                    break;
                case 2:
                    jSONObject.put(strNextKey, readableMap.getBoolean(strNextKey));
                    break;
                case 3:
                    jSONObject.put(strNextKey, readableMap.getDouble(strNextKey));
                    break;
                case 4:
                    jSONObject.put(strNextKey, readableMap.getString(strNextKey));
                    break;
                case 5:
                    jSONObject.put(strNextKey, jsonObjectFromReadableMap(readableMap.getMap(strNextKey)));
                    break;
                case 6:
                    jSONObject.put(strNextKey, jsonArrayFromReadableArray(readableMap.getArray(strNextKey)));
                    break;
            }
        }
        return jSONObject;
    }

    private static WritableMap utmDetailsToWritableMap(UTMDetail uTMDetail) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        if (uTMDetail != null) {
            writableMapCreateMap.putString("campaign", uTMDetail.getCampaign());
            writableMapCreateMap.putString("source", uTMDetail.getSource());
            writableMapCreateMap.putString("medium", uTMDetail.getMedium());
        }
        return writableMapCreateMap;
    }
}
