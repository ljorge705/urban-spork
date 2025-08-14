package com.facebook.react.modules.systeminfo;

import android.content.Context;
import android.os.Build;
import com.facebook.react.R;
import java.util.Locale;

/* loaded from: classes5.dex */
public class AndroidInfoHelpers {
    public static final String DEVICE_LOCALHOST = "localhost";
    public static final String EMULATOR_LOCALHOST = "10.0.2.2";
    public static final String GENYMOTION_LOCALHOST = "10.0.3.2";
    public static final String METRO_HOST_PROP_NAME = "metro.host";
    private static final String TAG = "AndroidInfoHelpers";
    private static String metroHostPropValue;

    private static boolean isRunningOnGenymotion() {
        return Build.FINGERPRINT.contains("vbox");
    }

    private static boolean isRunningOnStockEmulator() {
        return Build.FINGERPRINT.contains("generic") || Build.FINGERPRINT.startsWith("google/sdk_gphone");
    }

    public static String getServerHost(Integer num) {
        return getServerIpAddress(num.intValue());
    }

    public static String getServerHost(Context context) {
        return getServerIpAddress(getDevServerPort(context).intValue());
    }

    public static String getAdbReverseTcpCommand(Integer num) {
        return "adb reverse tcp:" + num + " tcp:" + num;
    }

    public static String getAdbReverseTcpCommand(Context context) {
        return getAdbReverseTcpCommand(getDevServerPort(context));
    }

    public static String getFriendlyDeviceName() {
        if (isRunningOnGenymotion()) {
            return Build.MODEL;
        }
        return Build.MODEL + " - " + Build.VERSION.RELEASE + " - API " + Build.VERSION.SDK_INT;
    }

    private static Integer getDevServerPort(Context context) {
        return Integer.valueOf(context.getResources().getInteger(R.integer.react_native_dev_server_port));
    }

    private static String getServerIpAddress(int i) {
        String metroHostPropValue2 = getMetroHostPropValue();
        if (metroHostPropValue2.equals("")) {
            if (isRunningOnGenymotion()) {
                metroHostPropValue2 = GENYMOTION_LOCALHOST;
            } else {
                metroHostPropValue2 = isRunningOnStockEmulator() ? EMULATOR_LOCALHOST : DEVICE_LOCALHOST;
            }
        }
        return String.format(Locale.US, "%s:%d", metroHostPropValue2, Integer.valueOf(i));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0045 A[Catch: all -> 0x0081, PHI: r2 r3
      0x0045: PHI (r2v8 java.lang.Process) = (r2v6 java.lang.Process), (r2v10 java.lang.Process) binds: [B:33:0x006e, B:17:0x0043] A[DONT_GENERATE, DONT_INLINE]
      0x0045: PHI (r3v6 java.io.BufferedReader) = (r3v4 java.io.BufferedReader), (r3v13 java.io.BufferedReader) binds: [B:33:0x006e, B:17:0x0043] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0003, B:16:0x0040, B:18:0x0045, B:35:0x0071, B:32:0x006b, B:40:0x0078, B:42:0x007d, B:43:0x0080), top: B:50:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static synchronized java.lang.String getMetroHostPropValue() {
        /*
            java.lang.Class<com.facebook.react.modules.systeminfo.AndroidInfoHelpers> r0 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.class
            monitor-enter(r0)
            java.lang.String r1 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue     // Catch: java.lang.Throwable -> L81
            if (r1 == 0) goto L9
            monitor-exit(r0)
            return r1
        L9:
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L5a
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L5a
            java.lang.String r4 = "/system/bin/getprop"
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L5a
            java.lang.String r4 = "metro.host"
            r5 = 1
            r3[r5] = r4     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L5a
            java.lang.Process r2 = r2.exec(r3)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L5a
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L50
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L50
            java.io.InputStream r5 = r2.getInputStream()     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L50
            java.lang.String r6 = "UTF-8"
            java.nio.charset.Charset r6 = java.nio.charset.Charset.forName(r6)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L50
            r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L50
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L50
            java.lang.String r1 = ""
        L36:
            java.lang.String r4 = r3.readLine()     // Catch: java.lang.Exception -> L49 java.lang.Throwable -> L75
            if (r4 == 0) goto L3e
            r1 = r4
            goto L36
        L3e:
            com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue = r1     // Catch: java.lang.Exception -> L49 java.lang.Throwable -> L75
            r3.close()     // Catch: java.lang.Exception -> L43 java.lang.Throwable -> L81
        L43:
            if (r2 == 0) goto L71
        L45:
            r2.destroy()     // Catch: java.lang.Throwable -> L81
            goto L71
        L49:
            r1 = move-exception
            goto L5e
        L4b:
            r3 = move-exception
            r7 = r3
            r3 = r1
            r1 = r7
            goto L76
        L50:
            r3 = move-exception
            r7 = r3
            r3 = r1
            r1 = r7
            goto L5e
        L55:
            r2 = move-exception
            r3 = r1
            r1 = r2
            r2 = r3
            goto L76
        L5a:
            r2 = move-exception
            r3 = r1
            r1 = r2
            r2 = r3
        L5e:
            java.lang.String r4 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.TAG     // Catch: java.lang.Throwable -> L75
            java.lang.String r5 = "Failed to query for metro.host prop:"
            com.facebook.common.logging.FLog.w(r4, r5, r1)     // Catch: java.lang.Throwable -> L75
            java.lang.String r1 = ""
            com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue = r1     // Catch: java.lang.Throwable -> L75
            if (r3 == 0) goto L6e
            r3.close()     // Catch: java.lang.Exception -> L6e java.lang.Throwable -> L81
        L6e:
            if (r2 == 0) goto L71
            goto L45
        L71:
            java.lang.String r1 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue     // Catch: java.lang.Throwable -> L81
            monitor-exit(r0)
            return r1
        L75:
            r1 = move-exception
        L76:
            if (r3 == 0) goto L7b
            r3.close()     // Catch: java.lang.Exception -> L7b java.lang.Throwable -> L81
        L7b:
            if (r2 == 0) goto L80
            r2.destroy()     // Catch: java.lang.Throwable -> L81
        L80:
            throw r1     // Catch: java.lang.Throwable -> L81
        L81:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.systeminfo.AndroidInfoHelpers.getMetroHostPropValue():java.lang.String");
    }
}
