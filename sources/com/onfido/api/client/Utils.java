package com.onfido.api.client;

import androidx.exifinterface.media.ExifInterface;
import okhttp3.logging.HttpLoggingInterceptor;

/* loaded from: classes6.dex */
public class Utils {
    public static void Log(String str, String str2, String str3) {
        HttpLoggingInterceptor.Logger.DEFAULT.log(str + "/" + str2 + ":" + str3);
    }

    public static class Log {
        public static void d(String str, String str2) {
            Utils.Log("D", str, str2);
        }

        public static void e(String str, String str2) {
            Utils.Log(ExifInterface.LONGITUDE_EAST, str, str2);
        }
    }
}
