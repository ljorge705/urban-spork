package com.uxcam;

import android.util.Log;
import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.uxcam.datamodel.UXConfig;
import com.uxcam.screenshot.model.UXCamBlur;
import com.uxcam.screenshot.model.UXCamOccludeAllTextFields;
import com.uxcam.screenshot.model.UXCamOcclusion;
import com.uxcam.screenshot.model.UXCamOverlay;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class RNUxcamModule extends ReactContextBaseJavaModule {
    public static final String BLUR_RADIUS = "blurRadius";
    public static final String CONFIG = "config";
    public static final String ENABLE_AUTOMATIC_SCREEN_NAME_TAGGING = "enableAutomaticScreenNameTagging";
    public static final String ENABLE_CRASH_HANDLING = "enableCrashHandling";
    public static final String ENABLE_IMPROVED_SCREEN_CAPTURE = "enableImprovedScreenCapture";
    public static final String ENABLE_MUTLI_SESSION_RECORD = "enableMultiSessionRecord";
    public static final String EXCLUDE_MENTIONED_SCREENS = "excludeMentionedScreens";
    public static final String HIDE_GESTURES = "hideGestures";
    public static final String NAME = "name";
    public static final String OCCLUSION = "occlusions";
    private static final String PARAM_ERROR_MESSAGE_KEY = "error";
    private static final String PARAM_SUCCESS_KEY = "success";
    public static final String SCREENS = "screens";
    public static final String TYPE = "type";
    public static final String USER_APP_KEY = "userAppKey";
    private static final String UXCAM_PLUGIN_TYPE = "react-native";
    private static final String UXCAM_REACT_PLUGIN_VERSION = "5.4.9";
    private static final String UXCAM_VERIFICATION_EVENT_KEY = "UXCam_Verification_Event";
    private final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNUxcam";
    }

    @ReactMethod
    public void uploadPendingSession() {
    }

    public RNUxcamModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
        UXCam.addVerificationListener(new OnVerificationListener() { // from class: com.uxcam.RNUxcamModule.1
            @Override // com.uxcam.OnVerificationListener
            public void onVerificationSuccess() {
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putBoolean("success", true);
                RNUxcamModule rNUxcamModule = RNUxcamModule.this;
                rNUxcamModule.sendEvent(rNUxcamModule.reactContext, RNUxcamModule.UXCAM_VERIFICATION_EVENT_KEY, writableMapCreateMap);
            }

            @Override // com.uxcam.OnVerificationListener
            public void onVerificationFailed(String str) {
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putBoolean("success", false);
                writableMapCreateMap.putString("error", str);
                RNUxcamModule rNUxcamModule = RNUxcamModule.this;
                rNUxcamModule.sendEvent(rNUxcamModule.reactContext, RNUxcamModule.UXCAM_VERIFICATION_EVENT_KEY, writableMapCreateMap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(ReactContext reactContext, String str, WritableMap writableMap) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
    }

    @ReactMethod
    public void startWithKey(String str) {
        UXCam.pluginType(UXCAM_PLUGIN_TYPE, UXCAM_REACT_PLUGIN_VERSION);
        UXCam.startApplicationWithKeyForCordova(getCurrentActivity(), str);
    }

    @ReactMethod
    public void startWithConfiguration(ReadableMap readableMap) {
        try {
            HashMap<String, Object> hashMap = readableMap.toHashMap();
            String str = (String) hashMap.get(USER_APP_KEY);
            Boolean bool = (Boolean) hashMap.get(ENABLE_MUTLI_SESSION_RECORD);
            Boolean bool2 = (Boolean) hashMap.get(ENABLE_CRASH_HANDLING);
            Boolean bool3 = (Boolean) hashMap.get(ENABLE_AUTOMATIC_SCREEN_NAME_TAGGING);
            Boolean bool4 = (Boolean) hashMap.get(ENABLE_IMPROVED_SCREEN_CAPTURE);
            List<UXCamOcclusion> listConvertToOcclusionList = hashMap.get(OCCLUSION) != null ? convertToOcclusionList((List) hashMap.get(OCCLUSION)) : null;
            UXConfig.Builder builder = new UXConfig.Builder(str);
            if (bool != null) {
                builder.enableMultiSessionRecord(bool.booleanValue());
            }
            if (bool2 != null) {
                builder.enableCrashHandling(bool2.booleanValue());
            }
            if (bool3 != null) {
                builder.enableAutomaticScreenNameTagging(bool3.booleanValue());
            }
            if (bool4 != null) {
                Log.d("config", "improved screen capture enabled " + bool4);
                builder.enableImprovedScreenCapture(bool4.booleanValue());
            }
            if (listConvertToOcclusionList != null) {
                builder.occlusions(listConvertToOcclusionList);
            }
            UXConfig uXConfigBuild = builder.build();
            UXCam.pluginType(UXCAM_PLUGIN_TYPE, UXCAM_REACT_PLUGIN_VERSION);
            UXCam.startWithConfigurationCrossPlatform(getCurrentActivity(), uXConfigBuild);
        } catch (Exception e) {
            Log.d("config", "Error starting with configuration");
            e.printStackTrace();
        }
    }

    private List<UXCamOcclusion> convertToOcclusionList(List<Map<String, Object>> list) {
        ArrayList arrayList = new ArrayList();
        for (Map<String, Object> map : list) {
            if (getOcclusion(map) != null) {
                arrayList.add(getOcclusion(map));
            }
        }
        return arrayList;
    }

    private UXCamOcclusion getOcclusion(Map<String, Object> map) {
        int iDoubleValue = (int) ((Double) map.get("type")).doubleValue();
        if (iDoubleValue == 1) {
            return getOccludeAllTextFields();
        }
        if (iDoubleValue == 2) {
            return getOverlay(map);
        }
        if (iDoubleValue != 3) {
            return null;
        }
        return getBlur(map);
    }

    private UXCamOccludeAllTextFields getOccludeAllTextFields() {
        return new UXCamOccludeAllTextFields.Builder().build();
    }

    private UXCamOverlay getOverlay(Map<String, Object> map) {
        List<String> list = (List) map.get(SCREENS);
        Boolean bool = (Boolean) map.get(EXCLUDE_MENTIONED_SCREENS);
        Boolean bool2 = (Boolean) map.get(HIDE_GESTURES);
        UXCamOverlay.Builder builder = new UXCamOverlay.Builder();
        if (list != null && !list.isEmpty()) {
            builder.screens(list);
        }
        if (bool != null) {
            builder.excludeMentionedScreens(bool.booleanValue());
        }
        if (bool2 != null) {
            builder.withoutGesture(bool2.booleanValue());
        }
        return builder.build();
    }

    private UXCamBlur getBlur(Map<String, Object> map) {
        List<String> list = (List) map.get(SCREENS);
        Boolean bool = (Boolean) map.get(EXCLUDE_MENTIONED_SCREENS);
        Double d = (Double) map.get(BLUR_RADIUS);
        Boolean bool2 = (Boolean) map.get(HIDE_GESTURES);
        UXCamBlur.Builder builder = new UXCamBlur.Builder();
        if (list != null && !list.isEmpty()) {
            builder.screens(list);
        }
        if (bool != null) {
            builder.excludeMentionedScreens(bool.booleanValue());
        }
        if (d != null) {
            builder.blurRadius(d.intValue());
        }
        if (bool2 != null) {
            builder.withoutGesture(bool2.booleanValue());
        }
        return builder.build();
    }

    @ReactMethod
    public void applyOcclusion(ReadableMap readableMap) {
        UXCam.applyOcclusion(getOcclusion(readableMap.toHashMap()));
    }

    @ReactMethod
    public void removeOcclusion(ReadableMap readableMap) {
        UXCam.removeOcclusion(getOcclusion(readableMap.toHashMap()));
    }

    @ReactMethod
    public void startNewSession() {
        UXCam.startNewSession();
    }

    @ReactMethod
    public void stopSessionAndUploadData() {
        UXCam.stopSessionAndUploadData();
    }

    @ReactMethod
    public void occludeSensitiveScreen(boolean z) {
        UXCam.occludeSensitiveScreen(z, false);
    }

    @ReactMethod
    public void occludeSensitiveScreen(boolean z, boolean z2) {
        UXCam.occludeSensitiveScreen(z, z2);
    }

    @ReactMethod
    public void occludeAllTextFields(boolean z) {
        UXCam.occludeAllTextFields(z);
    }

    @ReactMethod
    public void tagScreenName(String str) {
        UXCam.tagScreenName(str);
    }

    @ReactMethod
    public void setAutomaticScreenNameTagging(boolean z) {
        UXCam.setAutomaticScreenNameTagging(z);
    }

    @ReactMethod
    public void addScreenNameToIgnore(String str) {
        UXCam.addScreenNameToIgnore(str);
    }

    @ReactMethod
    public void addScreenNamesToIgnore(ReadableArray readableArray) {
        Iterator<Object> it = readableArray.toArrayList().iterator();
        while (it.hasNext()) {
            UXCam.addScreenNameToIgnore(it.next().toString());
        }
    }

    @ReactMethod
    public void removeScreenNameToIgnore(String str) {
        UXCam.removeScreenNameToIgnore(str);
    }

    @ReactMethod
    public void removeScreenNamesToIgnore(ReadableArray readableArray) {
        Iterator<Object> it = readableArray.toArrayList().iterator();
        while (it.hasNext()) {
            UXCam.removeScreenNameToIgnore(it.next().toString());
        }
    }

    @ReactMethod
    public void removeAllScreenNamesToIgnore() {
        UXCam.removeAllScreenNamesToIgnore();
    }

    @ReactMethod
    public void screenNamesBeingIgnored(Promise promise) {
        List<String> listScreenNamesBeingIgnored = UXCam.screenNamesBeingIgnored();
        WritableArray writableArrayCreateArray = Arguments.createArray();
        Iterator<String> it = listScreenNamesBeingIgnored.iterator();
        while (it.hasNext()) {
            writableArrayCreateArray.pushString(it.next());
        }
        promise.resolve(writableArrayCreateArray);
    }

    @ReactMethod
    public void setUserIdentity(String str) {
        UXCam.setUserIdentity(str);
    }

    @ReactMethod
    public void setUserProperty(String str, String str2) {
        UXCam.setUserProperty(str, str2);
    }

    @ReactMethod
    public void setSessionProperty(String str, String str2) {
        UXCam.setSessionProperty(str, str2);
    }

    @ReactMethod
    public void logEvent(String str) {
        UXCam.logEvent(str);
    }

    @ReactMethod
    public void logEvent(String str, ReadableMap readableMap) {
        if (readableMap != null) {
            HashMap map = new HashMap();
            ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
            while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
                String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
                ReadableType type = readableMap.getType(strNextKey);
                if (type == ReadableType.Boolean) {
                    map.put(strNextKey, Boolean.valueOf(readableMap.getBoolean(strNextKey)));
                } else if (type == ReadableType.Number) {
                    map.put(strNextKey, Double.valueOf(readableMap.getDouble(strNextKey)));
                } else {
                    map.put(strNextKey, readableMap.getString(strNextKey));
                }
            }
            UXCam.logEvent(str, map);
            return;
        }
        UXCam.logEvent(str);
    }

    @ReactMethod
    public void addVerificationListener(final Promise promise) {
        UXCam.addVerificationListener(new OnVerificationListener() { // from class: com.uxcam.RNUxcamModule.2
            @Override // com.uxcam.OnVerificationListener
            public void onVerificationSuccess() {
                promise.resolve("success");
            }

            @Override // com.uxcam.OnVerificationListener
            public void onVerificationFailed(String str) {
                promise.reject("failed", str, new Throwable(str));
            }
        });
    }

    @ReactMethod
    public void urlForCurrentSession(Promise promise) {
        promise.resolve(UXCam.urlForCurrentSession());
    }

    @ReactMethod
    public void urlForCurrentUser(Promise promise) {
        promise.resolve(UXCam.urlForCurrentUser());
    }

    @ReactMethod
    public void isRecording(Promise promise) {
        promise.resolve(Boolean.valueOf(UXCam.isRecording()));
    }

    @ReactMethod
    public void pauseScreenRecording() {
        UXCam.pauseScreenRecording();
    }

    @ReactMethod
    public void resumeScreenRecording() {
        UXCam.resumeScreenRecording();
    }

    @ReactMethod
    public void optInOverall() {
        UXCam.optIn();
    }

    @ReactMethod
    public void optOutOverall() {
        UXCam.optOut();
    }

    @ReactMethod
    public void optInOverallStatus(Promise promise) {
        promise.resolve(Boolean.valueOf(UXCam.optStatus()));
    }

    @ReactMethod
    public void optIntoVideoRecording() {
        UXCam.optIntoVideoRecording();
    }

    @ReactMethod
    public void optOutOfVideoRecording() {
        UXCam.optOutOfVideoRecording();
    }

    @ReactMethod
    public void optInVideoRecordingStatus(Promise promise) {
        promise.resolve(Boolean.valueOf(UXCam.optInVideoRecordingStatus()));
    }

    @ReactMethod
    public void cancelCurrentSession() {
        UXCam.cancelCurrentSession();
    }

    @ReactMethod
    public void allowShortBreakForAnotherApp(boolean z) {
        UXCam.allowShortBreakForAnotherApp(z);
    }

    @ReactMethod
    public void allowShortBreakForAnotherAppInMillis(int i) {
        UXCam.allowShortBreakForAnotherApp(i);
    }

    @ReactMethod
    public void getMultiSessionRecord(Promise promise) {
        promise.resolve(Boolean.valueOf(UXCam.getMultiSessionRecord()));
    }

    @ReactMethod
    public void setMultiSessionRecord(boolean z) {
        UXCam.setMultiSessionRecord(z);
    }

    @ReactMethod
    public void deletePendingUploads() {
        UXCam.deletePendingUploads();
    }

    @ReactMethod
    public void pendingSessionCount(Promise promise) {
        promise.resolve(Integer.valueOf(UXCam.pendingSessionCount()));
    }

    @ReactMethod
    public void occludeSensitiveView(final int i) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: com.uxcam.RNUxcamModule.3
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    View viewResolveView = nativeViewHierarchyManager.resolveView(i);
                    if (viewResolveView != null) {
                        UXCam.occludeSensitiveView(viewResolveView);
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    @ReactMethod
    public void unOccludeSensitiveView(final int i) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: com.uxcam.RNUxcamModule.4
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    View viewResolveView = nativeViewHierarchyManager.resolveView(i);
                    if (viewResolveView != null) {
                        UXCam.unOccludeSensitiveView(viewResolveView);
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    @ReactMethod
    public void occludeSensitiveViewWithoutGesture(final int i) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: com.uxcam.RNUxcamModule.5
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    View viewResolveView = nativeViewHierarchyManager.resolveView(i);
                    if (viewResolveView != null) {
                        UXCam.occludeSensitiveViewWithoutGesture(viewResolveView);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ReactMethod
    public void setPushNotificationToken(String str) {
        UXCam.setPushNotificationToken(str);
    }

    @ReactMethod
    public void reportBugEvent(String str) {
        UXCam.reportBugEvent(str);
    }

    @ReactMethod
    public void reportBugEvent(String str, ReadableMap readableMap) {
        if (readableMap != null) {
            HashMap map = new HashMap();
            ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
            while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
                String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
                ReadableType type = readableMap.getType(strNextKey);
                if (type == ReadableType.Boolean) {
                    map.put(strNextKey, Boolean.valueOf(readableMap.getBoolean(strNextKey)));
                } else if (type == ReadableType.Number) {
                    map.put(strNextKey, Double.valueOf(readableMap.getDouble(strNextKey)));
                } else {
                    map.put(strNextKey, readableMap.getString(strNextKey));
                }
            }
            UXCam.reportBugEvent(str, map);
            return;
        }
        UXCam.reportBugEvent(str);
    }

    @ReactMethod
    public void disableCrashHandling(boolean z) {
        UXCam.disableCrashHandling(z);
    }
}
