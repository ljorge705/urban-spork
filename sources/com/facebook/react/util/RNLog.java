package com.facebook.react.util;

import app.notifee.core.event.LogEvent;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.onfido.android.sdk.capture.internal.util.logging.OnfidoLogMapper;
import javax.annotation.Nullable;

/* loaded from: classes5.dex */
public class RNLog {
    public static final int ADVICE = 4;
    public static final int ERROR = 6;
    public static final int LOG = 2;
    public static final int MINIMUM_LEVEL_FOR_UI = 5;
    public static final int TRACE = 3;
    public static final int WARN = 5;

    private static String levelToString(int i) {
        return (i == 2 || i == 3) ? OnfidoLogMapper.LOG_EVENT_TYPE : (i == 4 || i == 5) ? LogEvent.LEVEL_WARN : i != 6 ? "none" : "error";
    }

    public static void l(String str) {
        FLog.i("ReactNative", str);
    }

    public static void t(String str) {
        FLog.i("ReactNative", str);
    }

    public static void a(String str) {
        FLog.w("ReactNative", "(ADVICE)" + str);
    }

    public static void w(@Nullable ReactContext reactContext, String str) {
        logInternal(reactContext, str, 5);
        FLog.w("ReactNative", str);
    }

    public static void e(@Nullable ReactContext reactContext, String str) {
        logInternal(reactContext, str, 6);
        FLog.e("ReactNative", str);
    }

    public static void e(String str) {
        FLog.e("ReactNative", str);
    }

    private static void logInternal(@Nullable ReactContext reactContext, @Nullable String str, int i) {
        if (i < 5 || reactContext == null || !reactContext.hasActiveReactInstance() || str == null) {
            return;
        }
        ((RCTLog) reactContext.getJSModule(RCTLog.class)).logIfNoNativeHook(levelToString(i), str);
    }
}
