package com.clevertap.android.sdk;

import android.util.Log;
import com.clevertap.android.sdk.CleverTapAPI;

/* loaded from: classes5.dex */
public final class Logger implements ILogger {
    private int debugLevel;

    private int getDebugLevel() {
        return this.debugLevel;
    }

    public void setDebugLevel(int i) {
        this.debugLevel = i;
    }

    public static void d(String str) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.d(Constants.CLEVERTAP_LOG_TAG, str);
        }
    }

    public static void d(String str, String str2) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.d("CleverTap:" + str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.d("CleverTap:" + str, str2, th);
        }
    }

    public static void d(String str, Throwable th) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.d(Constants.CLEVERTAP_LOG_TAG, str, th);
        }
    }

    public static void i(String str) {
        if (getStaticDebugLevel() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i(Constants.CLEVERTAP_LOG_TAG, str);
        }
    }

    public static void i(String str, String str2) {
        if (getStaticDebugLevel() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i("CleverTap:" + str, str2);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (getStaticDebugLevel() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i("CleverTap:" + str, str2, th);
        }
    }

    public static void i(String str, Throwable th) {
        if (getStaticDebugLevel() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i(Constants.CLEVERTAP_LOG_TAG, str, th);
        }
    }

    public static void v(String str) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            Log.v(Constants.CLEVERTAP_LOG_TAG, str);
        }
    }

    public static void v(String str, String str2) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            Log.v("CleverTap:" + str, str2);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            Log.v("CleverTap:" + str, str2, th);
        }
    }

    public static void v(String str, Throwable th) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            Log.v(Constants.CLEVERTAP_LOG_TAG, str, th);
        }
    }

    Logger(int i) {
        this.debugLevel = i;
    }

    @Override // com.clevertap.android.sdk.ILogger
    public void debug(String str) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.d(Constants.CLEVERTAP_LOG_TAG, str);
        }
    }

    @Override // com.clevertap.android.sdk.ILogger
    public void debug(String str, String str2) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.INFO.intValue()) {
            if (str2.length() > 4000) {
                Log.d("CleverTap:" + str, str2.substring(0, 4000));
                debug(str, str2.substring(4000));
            } else {
                Log.d("CleverTap:" + str, str2);
            }
        }
    }

    @Override // com.clevertap.android.sdk.ILogger
    public void debug(String str, String str2, Throwable th) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.d("CleverTap:" + str, str2, th);
        }
    }

    @Override // com.clevertap.android.sdk.ILogger
    public void debug(String str, Throwable th) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.d(Constants.CLEVERTAP_LOG_TAG, str, th);
        }
    }

    @Override // com.clevertap.android.sdk.ILogger
    public void info(String str) {
        if (getDebugLevel() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i(Constants.CLEVERTAP_LOG_TAG, str);
        }
    }

    @Override // com.clevertap.android.sdk.ILogger
    public void info(String str, String str2) {
        if (getDebugLevel() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i("CleverTap:" + str, str2);
        }
    }

    @Override // com.clevertap.android.sdk.ILogger
    public void info(String str, String str2, Throwable th) {
        if (getDebugLevel() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i("CleverTap:" + str, str2, th);
        }
    }

    @Override // com.clevertap.android.sdk.ILogger
    public void info(String str, Throwable th) {
        if (getDebugLevel() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i(Constants.CLEVERTAP_LOG_TAG, str, th);
        }
    }

    @Override // com.clevertap.android.sdk.ILogger
    public void verbose(String str) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            Log.v(Constants.CLEVERTAP_LOG_TAG, str);
        }
    }

    @Override // com.clevertap.android.sdk.ILogger
    public void verbose(String str, String str2) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            if (str2.length() > 4000) {
                Log.v("CleverTap:" + str, str2.substring(0, 4000));
                verbose(str, str2.substring(4000));
            } else {
                Log.v("CleverTap:" + str, str2);
            }
        }
    }

    @Override // com.clevertap.android.sdk.ILogger
    public void verbose(String str, String str2, Throwable th) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            Log.v("CleverTap:" + str, str2, th);
        }
    }

    @Override // com.clevertap.android.sdk.ILogger
    public void verbose(String str, Throwable th) {
        if (getStaticDebugLevel() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            Log.v(Constants.CLEVERTAP_LOG_TAG, str, th);
        }
    }

    private static int getStaticDebugLevel() {
        return CleverTapAPI.getDebugLevel();
    }
}
