package com.clevertap.android.sdk;

/* loaded from: classes5.dex */
public interface ILogger {
    void debug(String str);

    void debug(String str, String str2);

    void debug(String str, String str2, Throwable th);

    void debug(String str, Throwable th);

    void info(String str);

    void info(String str, String str2);

    void info(String str, String str2, Throwable th);

    void info(String str, Throwable th);

    void verbose(String str);

    void verbose(String str, String str2);

    void verbose(String str, String str2, Throwable th);

    void verbose(String str, Throwable th);
}
