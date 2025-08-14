package com.ptc.reactnative.paygilant;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.paygilant.PG_FraudDetection_SDK.Biometric.PaygilantScreenListener;
import com.paygilant.PG_FraudDetection_SDK.PaygilantException;
import com.paygilant.PG_FraudDetection_SDK.PaygilantManager;
import com.paygilant.PG_FraudDetection_SDK.PaygilantRunTimeException;
import com.paygilant.PG_FraudDetection_SDK.SessionIdCallback;
import com.paygilant.pgdata.CheckPoint.ScreenListenerType;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public class PaygilantModule extends ReactContextBaseJavaModule {
    private static final String TAG = "PaygilantModule";
    private Boolean paygilantInitialized;
    private final ReactApplicationContext reactContext;
    private PaygilantScreenListener screenListener;

    @ReactMethod
    public void arriveToCheckPoint(String str, Promise promise) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "Paygilant";
    }

    @ReactMethod
    public void initializeDeviceId(Promise promise) {
    }

    @ReactMethod
    public void onRequestPermissionsResult(int i, ReadableArray readableArray, ReadableArray readableArray2, Promise promise) {
    }

    @ReactMethod
    public void setUserId(String str, Promise promise) {
    }

    @ReactMethod
    public void updateCheckPointStatus(String str, String str2, String str3, String str4, Promise promise) {
    }

    public PaygilantModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.paygilantInitialized = false;
        this.reactContext = reactApplicationContext;
    }

    @ReactMethod
    public void initialize(String str, String str2, Promise promise) {
        if (getCurrentActivity() == null || getCurrentActivity().isDestroyed() || this.paygilantInitialized.booleanValue()) {
            return;
        }
        try {
            PaygilantManager.init(this.reactContext, getCurrentActivity(), str);
        } catch (PaygilantException e) {
            promise.reject("error", e.toString());
        }
        this.paygilantInitialized = true;
    }

    @ReactMethod
    public void getRiskForCheckPoint(String str, Callback callback) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("riskLevel", -1);
        writableMapCreateMap.putString("signedRisk", "-1");
        writableMapCreateMap.putString("requestId", "-1");
        callback.invoke(writableMapCreateMap);
    }

    @ReactMethod
    public void getDeviceId(Callback callback) {
        try {
            callback.invoke(PaygilantManager.getInstance(this.reactContext).getDeviceId());
        } catch (PaygilantRunTimeException unused) {
            callback.invoke("no_device_id");
        }
    }

    @ReactMethod
    public void getSessionId(final Callback callback) {
        try {
            PaygilantManager.getInstance(this.reactContext).getSessionId(new SessionIdCallback() { // from class: com.ptc.reactnative.paygilant.PaygilantModule.1
                @Override // com.paygilant.PG_FraudDetection_SDK.SessionIdCallback
                public void onReceiver(String str) {
                    callback.invoke(str);
                }
            });
        } catch (PaygilantRunTimeException unused) {
            callback.invoke("no_session_id");
        }
    }

    @ReactMethod
    public void setApprovePolicy(boolean z, Promise promise) {
        try {
            PaygilantManager.getInstance(this.reactContext).setApprovePolicy(z);
        } catch (PaygilantRunTimeException e) {
            promise.reject("error", e.toString());
        }
    }

    @ReactMethod
    public void isApprovePolicy(Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(PaygilantManager.getInstance(this.reactContext).isApprovePolicy()));
        } catch (PaygilantRunTimeException e) {
            promise.reject("error", e.toString());
        }
    }

    @ReactMethod
    public void logout(Promise promise) {
        try {
            PaygilantManager.getInstance(this.reactContext).logout();
        } catch (PaygilantRunTimeException e) {
            promise.reject("error", e.toString());
        }
    }

    @ReactMethod
    public void startNewScreenListener(String str, int i, Promise promise) {
        try {
            this.screenListener = PaygilantManager.getInstance(this.reactContext).startNewScreenListener(ScreenListenerType.valueOf(str), i, getCurrentActivity());
        } catch (PaygilantRunTimeException e) {
            promise.reject("error", e.toString());
        }
    }

    @ReactMethod
    public void pauseListenToSensors() {
        PaygilantScreenListener paygilantScreenListener = this.screenListener;
        if (paygilantScreenListener != null) {
            paygilantScreenListener.pauseListenToSensors();
        }
    }

    private void sendEvent(ReactContext reactContext, String str, @Nullable WritableMap writableMap) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
    }
}
