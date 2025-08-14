package com.henninghall.date_picker;

import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.henninghall.date_picker.ui.SpinnerState;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class Emitter {
    private static RCTEventEmitter eventEmitter() {
        return (RCTEventEmitter) DatePickerPackage.context.getJSModule(RCTEventEmitter.class);
    }

    private static DeviceEventManagerModule.RCTDeviceEventEmitter deviceEventEmitter() {
        return (DeviceEventManagerModule.RCTDeviceEventEmitter) DatePickerPackage.context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }

    public static void onSpinnerStateChange(SpinnerState spinnerState, String str, View view) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("spinnerState", spinnerState.toString());
        writableMapCreateMap.putString("id", str);
        eventEmitter().receiveEvent(view.getId(), "spinnerStateChange", writableMapCreateMap);
    }

    public static void onDateChange(Calendar calendar, String str, String str2, View view) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("date", Utils.dateToIso(calendar));
        writableMapCreateMap.putString("dateString", str);
        writableMapCreateMap.putString("id", str2);
        eventEmitter().receiveEvent(view.getId(), "dateChange", writableMapCreateMap);
    }

    public static void onConfirm(String str, String str2) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("date", str);
        writableMapCreateMap.putString("id", str2);
        deviceEventEmitter().emit("onConfirm", writableMapCreateMap);
    }

    public static void onCancel(String str) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("id", str);
        deviceEventEmitter().emit("onCancel", writableMapCreateMap);
    }
}
