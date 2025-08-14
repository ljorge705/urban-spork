package net.singular.react_native;

import android.content.Intent;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.singular.sdk.SDIDAccessorHandler;
import com.singular.sdk.ShortLinkHandler;
import com.singular.sdk.Singular;
import com.singular.sdk.SingularConfig;
import com.singular.sdk.SingularDeviceAttributionHandler;
import com.singular.sdk.SingularLinkHandler;
import com.singular.sdk.SingularLinkParams;
import com.singular.sdk.internal.SingularParamsBase;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class SingularBridgeModule extends ReactContextBaseJavaModule {
    public static final String REACT_CLASS = "SingularBridge";
    private static SingularConfig config;
    private static int currentIntentHash;
    private static ReactApplicationContext reactContext;
    private static SingularLinkHandler singularLinkHandler;

    private interface Constants {
        public static final String DEVICE_ATTRIBUTION_CALLBACK_HANDLER_CONST = "DeviceAttributionCallbackHandler";
        public static final String SHORT_LINK_HANDLER_CONST = "ShortLinkHandler";
        public static final String SINGULAR_LINK_HANDLER_CONST = "SingularLinkHandler";
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public SingularBridgeModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        reactContext = reactApplicationContext;
    }

    @ReactMethod
    public void init(String str) {
        buildSingularConfig(str);
        Singular.init(reactContext, config);
    }

    @ReactMethod
    public void setCustomUserId(String str) {
        Singular.setCustomUserId(str);
    }

    @ReactMethod
    public void unsetCustomUserId() {
        Singular.unsetCustomUserId();
    }

    @ReactMethod
    public void setDeviceCustomUserId(String str) {
        Singular.setDeviceCustomUserId(str);
    }

    @ReactMethod
    public void event(String str) {
        Singular.event(str);
    }

    @ReactMethod
    public void eventWithArgs(String str, String str2) {
        Singular.event(str, str2);
    }

    @ReactMethod
    public void revenue(String str, double d) {
        Singular.revenue(str, d);
    }

    @ReactMethod
    public void revenueWithArgs(String str, double d, String str2) {
        Singular.revenue(str, d, convertJsonToMap(str2));
    }

    @ReactMethod
    public void customRevenue(String str, String str2, double d) {
        Singular.customRevenue(str, str2, d);
    }

    @ReactMethod
    public void customRevenueWithArgs(String str, String str2, double d, String str3) throws JSONException {
        Singular.customRevenue(str, str2, d, convertJsonToMap(str3));
    }

    @ReactMethod
    public void setUninstallToken(String str) {
        Singular.setFCMDeviceToken(str);
    }

    @ReactMethod
    public void trackingOptIn() {
        Singular.trackingOptIn();
    }

    @ReactMethod
    public void trackingUnder13() {
        Singular.trackingUnder13();
    }

    @ReactMethod
    public void stopAllTracking() {
        Singular.stopAllTracking();
    }

    @ReactMethod
    public void resumeAllTracking() {
        Singular.resumeAllTracking();
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean isAllTrackingStopped() {
        return Singular.isAllTrackingStopped();
    }

    @ReactMethod
    public void limitDataSharing(boolean z) {
        Singular.limitDataSharing(z);
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean getLimitDataSharing() {
        return Singular.getLimitDataSharing();
    }

    @ReactMethod
    public void setReactSDKVersion(String str, String str2) {
        Singular.setWrapperNameAndVersion(str, str2);
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean setGlobalProperty(String str, String str2, boolean z) {
        return Singular.setGlobalProperty(str, str2, z);
    }

    @ReactMethod
    public void unsetGlobalProperty(String str) {
        Singular.unsetGlobalProperty(str);
    }

    @ReactMethod
    public void clearGlobalProperties() {
        Singular.clearGlobalProperties();
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getGlobalProperties() {
        return toWritableMap(Singular.getGlobalProperties());
    }

    private void buildSingularConfig(String str) {
        int iHashCode;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String str2 = null;
            config = new SingularConfig(jSONObject.optString("apikey", null), jSONObject.optString("secret", null));
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("espDomains");
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                LinkedList linkedList = new LinkedList();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    String string = jSONArrayOptJSONArray.getString(i);
                    if (string != null && string.length() > 0) {
                        linkedList.add(string);
                    }
                }
                config.withESPDomains(linkedList);
            }
            long jOptLong = jSONObject.optLong("ddlTimeoutSec", 0L);
            if (jOptLong > 0) {
                config.withDDLTimeoutInSec(jOptLong);
            }
            config.withSingularDeviceAttribution(new SingularDeviceAttributionHandler() { // from class: net.singular.react_native.SingularBridgeModule.1
                @Override // com.singular.sdk.SingularDeviceAttributionHandler
                public void onDeviceAttributionInfoReceived(Map<String, Object> map) {
                    try {
                        ((DeviceEventManagerModule.RCTDeviceEventEmitter) SingularBridgeModule.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(Constants.DEVICE_ATTRIBUTION_CALLBACK_HANDLER_CONST, SingularBridgeModule.this.convertJsonToWritableMap(new JSONObject(map)));
                    } catch (Throwable unused) {
                        Log.d("Singular", "could not convert json to writable map");
                    }
                }
            });
            singularLinkHandler = new SingularLinkHandler() { // from class: net.singular.react_native.SingularBridgeModule.2
                @Override // com.singular.sdk.SingularLinkHandler
                public void onResolved(SingularLinkParams singularLinkParams) {
                    WritableMap writableMapCreateMap = Arguments.createMap();
                    writableMapCreateMap.putString("deeplink", singularLinkParams.getDeeplink());
                    writableMapCreateMap.putString("passthrough", singularLinkParams.getPassthrough());
                    writableMapCreateMap.putBoolean("isDeferred", singularLinkParams.isDeferred());
                    WritableMap writableMapCreateMap2 = Arguments.createMap();
                    if (singularLinkParams.getUrlParameters() != null) {
                        for (Map.Entry<String, String> entry : singularLinkParams.getUrlParameters().entrySet()) {
                            writableMapCreateMap2.putString(entry.getKey(), entry.getValue());
                        }
                    }
                    writableMapCreateMap.putMap("urlParameters", writableMapCreateMap2);
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) SingularBridgeModule.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(Constants.SINGULAR_LINK_HANDLER_CONST, writableMapCreateMap);
                }
            };
            if (reactContext.hasCurrentActivity() && getCurrentActivity().getIntent() != null && (iHashCode = getCurrentActivity().getIntent().hashCode()) != currentIntentHash) {
                currentIntentHash = iHashCode;
                config.withSingularLink(getCurrentActivity().getIntent(), singularLinkHandler, jSONObject.optLong("shortLinkResolveTimeout", 10L));
            }
            String strOptString = jSONObject.optString("customUserId", null);
            if (strOptString != null) {
                config.withCustomUserId(strOptString);
            }
            String strOptString2 = jSONObject.optString(SingularParamsBase.Constants.IMEI_KEYSPACE_KEY, null);
            if (strOptString2 != null) {
                config.withIMEI(strOptString2);
            }
            int iOptInt = jSONObject.optInt("sessionTimeout", -1);
            if (iOptInt >= 0) {
                config.withSessionTimeoutInSec(iOptInt);
            }
            Object objOpt = jSONObject.opt("limitDataSharing");
            if (objOpt != JSONObject.NULL) {
                config.withLimitDataSharing(((Boolean) objOpt).booleanValue());
            }
            if (jSONObject.optBoolean("collectOAID", false)) {
                config.withOAIDCollection();
            }
            if (jSONObject.optBoolean("enableLogging", false)) {
                config.withLoggingEnabled();
            }
            int iOptInt2 = jSONObject.optInt("logLevel", -1);
            if (iOptInt2 >= 0) {
                config.withLogLevel(iOptInt2);
            }
            String strOptString3 = jSONObject.optString("facebookAppId", null);
            if (strOptString3 != null) {
                config.withFacebookAppId(strOptString3);
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("globalProperties");
            if (jSONObjectOptJSONObject != null) {
                Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
                while (itKeys.hasNext()) {
                    JSONObject jSONObject2 = jSONObjectOptJSONObject.getJSONObject(itKeys.next());
                    config.withGlobalProperty(jSONObject2.getString("Key"), jSONObject2.getString("Value"), jSONObject2.getBoolean("OverrideExisting"));
                }
            }
            String strOptString4 = jSONObject.optString("customSdid");
            if (isValidNonEmptyString(strOptString4)) {
                str2 = strOptString4;
            }
            config.withCustomSdid(str2, new SDIDAccessorHandler() { // from class: net.singular.react_native.SingularBridgeModule.3
                @Override // com.singular.sdk.SDIDAccessorHandler
                public void didSetSdid(String str3) {
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) SingularBridgeModule.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("DidSetSdidCallback", str3);
                }

                @Override // com.singular.sdk.SDIDAccessorHandler
                public void sdidReceived(String str3) {
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) SingularBridgeModule.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("SdidReceivedCallback", str3);
                }
            });
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WritableMap convertJsonToWritableMap(JSONObject jSONObject) throws JSONException {
        WritableMap writableMapCreateMap = Arguments.createMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONObject) {
                writableMapCreateMap.putMap(next, convertJsonToWritableMap((JSONObject) obj));
            } else if (obj instanceof JSONArray) {
                writableMapCreateMap.putArray(next, convertJsonToArray((JSONArray) obj));
            } else if (obj instanceof Boolean) {
                writableMapCreateMap.putBoolean(next, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Integer) {
                writableMapCreateMap.putInt(next, ((Integer) obj).intValue());
            } else if (obj instanceof Double) {
                writableMapCreateMap.putDouble(next, ((Double) obj).doubleValue());
            } else if (obj instanceof String) {
                writableMapCreateMap.putString(next, (String) obj);
            } else {
                writableMapCreateMap.putString(next, obj.toString());
            }
        }
        return writableMapCreateMap;
    }

    private WritableArray convertJsonToArray(JSONArray jSONArray) throws JSONException {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONObject) {
                writableNativeArray.pushMap(convertJsonToWritableMap((JSONObject) obj));
            } else if (obj instanceof JSONArray) {
                writableNativeArray.pushArray(convertJsonToArray((JSONArray) obj));
            } else if (obj instanceof Boolean) {
                writableNativeArray.pushBoolean(((Boolean) obj).booleanValue());
            } else if (obj instanceof Integer) {
                writableNativeArray.pushInt(((Integer) obj).intValue());
            } else if (obj instanceof Double) {
                writableNativeArray.pushDouble(((Double) obj).doubleValue());
            } else if (obj instanceof String) {
                writableNativeArray.pushString((String) obj);
            } else {
                writableNativeArray.pushString(obj.toString());
            }
        }
        return writableNativeArray;
    }

    private WritableMap toWritableMap(Map<String, String> map) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            String value = next.getValue();
            if (value == null) {
                writableMapCreateMap.putNull(next.getKey());
            } else if (value instanceof String) {
                writableMapCreateMap.putString(next.getKey(), value);
            }
            it.remove();
        }
        return writableMapCreateMap;
    }

    private Map<String, Object> convertJsonToMap(String str) {
        HashMap map = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, jSONObject.get(next));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return map;
    }

    public static void onNewIntent(Intent intent) {
        if (intent == null || config == null || singularLinkHandler == null || intent.hashCode() == currentIntentHash || intent.getData() == null) {
            return;
        }
        currentIntentHash = intent.hashCode();
        config.withSingularLink(intent, singularLinkHandler);
        Singular.init(reactContext, config);
    }

    @ReactMethod
    public void createReferrerShortLink(String str, String str2, String str3, String str4) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str4);
        } catch (JSONException e) {
            e.printStackTrace();
            jSONObject = null;
        }
        Singular.createReferrerShortLink(str, str2, str3, jSONObject, new ShortLinkHandler() { // from class: net.singular.react_native.SingularBridgeModule.4
            @Override // com.singular.sdk.ShortLinkHandler
            public void onSuccess(String str5) {
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putString("data", str5);
                writableMapCreateMap.putString("error", "");
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) SingularBridgeModule.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(Constants.SHORT_LINK_HANDLER_CONST, writableMapCreateMap);
            }

            @Override // com.singular.sdk.ShortLinkHandler
            public void onError(String str5) {
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putString("data", "");
                writableMapCreateMap.putString("error", str5);
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) SingularBridgeModule.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(Constants.SHORT_LINK_HANDLER_CONST, writableMapCreateMap);
            }
        });
    }

    private boolean isValidNonEmptyString(String str) {
        return (str == null || !(str instanceof String) || str.length() <= 0 || str.toLowerCase().equals("null") || str.toLowerCase().equals("undefined") || str.toLowerCase().equals(com.facebook.hermes.intl.Constants.CASEFIRST_FALSE) || str.equals("NaN")) ? false : true;
    }
}
