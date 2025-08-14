package com.clevertap.android.sdk;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes5.dex */
public final class StorageHelper {
    public static SharedPreferences getPreferences(Context context, String str) {
        return context.getSharedPreferences(str != null ? "WizRocket_" + str : Constants.CLEVERTAP_STORAGE_TAG, 0);
    }

    public static SharedPreferences getPreferences(Context context) {
        return getPreferences(context, null);
    }

    public static String getString(Context context, String str, String str2) {
        return getPreferences(context).getString(str, str2);
    }

    public static String getStringFromPrefs(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, String str, String str2) {
        if (cleverTapInstanceConfig.isDefaultInstance()) {
            String string = getString(context, storageKeyWithSuffix(cleverTapInstanceConfig, str), str2);
            return string != null ? string : getString(context, str, str2);
        }
        return getString(context, storageKeyWithSuffix(cleverTapInstanceConfig, str), str2);
    }

    public static void persist(SharedPreferences.Editor editor) {
        try {
            editor.apply();
        } catch (Throwable th) {
            Logger.v("CRITICAL: Failed to persist shared preferences!", th);
        }
    }

    public static void persistImmediately(SharedPreferences.Editor editor) {
        try {
            editor.commit();
        } catch (Throwable th) {
            Logger.v("CRITICAL: Failed to persist shared preferences!", th);
        }
    }

    public static void putString(Context context, String str, String str2) {
        persist(getPreferences(context).edit().putString(str, str2));
    }

    public static void putString(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, String str, String str2) {
        persist(getPreferences(context).edit().putString(storageKeyWithSuffix(cleverTapInstanceConfig, str), str2));
    }

    public static void putStringImmediate(Context context, String str, String str2) {
        persistImmediately(getPreferences(context).edit().putString(str, str2));
    }

    public static void remove(Context context, String str) {
        persist(getPreferences(context).edit().remove(str));
    }

    public static void removeImmediate(Context context, String str) {
        persistImmediately(getPreferences(context).edit().remove(str));
    }

    public static String storageKeyWithSuffix(CleverTapInstanceConfig cleverTapInstanceConfig, String str) {
        return str + ":" + cleverTapInstanceConfig.getAccountId();
    }

    public static boolean getBoolean(Context context, String str, boolean z) {
        return getPreferences(context).getBoolean(str, z);
    }

    static boolean getBooleanFromPrefs(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, String str) {
        if (cleverTapInstanceConfig.isDefaultInstance()) {
            boolean z = getBoolean(context, storageKeyWithSuffix(cleverTapInstanceConfig, str), false);
            return !z ? getBoolean(context, str, false) : z;
        }
        return getBoolean(context, storageKeyWithSuffix(cleverTapInstanceConfig, str), false);
    }

    public static int getInt(Context context, String str, int i) {
        return getPreferences(context).getInt(str, i);
    }

    public static int getIntFromPrefs(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, String str, int i) {
        if (cleverTapInstanceConfig.isDefaultInstance()) {
            int i2 = getInt(context, storageKeyWithSuffix(cleverTapInstanceConfig, str), -1000);
            return i2 != -1000 ? i2 : getInt(context, str, i);
        }
        return getInt(context, storageKeyWithSuffix(cleverTapInstanceConfig, str), i);
    }

    static long getLong(Context context, String str, long j) {
        return getPreferences(context).getLong(str, j);
    }

    static long getLong(Context context, String str, String str2, long j) {
        return getPreferences(context, str).getLong(str2, j);
    }

    public static long getLongFromPrefs(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, String str, int i, String str2) {
        if (cleverTapInstanceConfig.isDefaultInstance()) {
            long j = getLong(context, str2, storageKeyWithSuffix(cleverTapInstanceConfig, str), -1000L);
            return j != -1000 ? j : getLong(context, str2, str, i);
        }
        return getLong(context, str2, storageKeyWithSuffix(cleverTapInstanceConfig, str), i);
    }

    static String getString(Context context, String str, String str2, String str3) {
        return getPreferences(context, str).getString(str2, str3);
    }

    public static void putBoolean(Context context, String str, boolean z) {
        persist(getPreferences(context).edit().putBoolean(str, z));
    }

    public static void putBooleanImmediate(Context context, String str, boolean z) {
        persistImmediately(getPreferences(context).edit().putBoolean(str, z));
    }

    public static void putInt(Context context, String str, int i) {
        persist(getPreferences(context).edit().putInt(str, i));
    }

    public static void putIntImmediate(Context context, String str, int i) {
        persistImmediately(getPreferences(context).edit().putInt(str, i));
    }

    static void putLong(Context context, String str, long j) {
        persist(getPreferences(context).edit().putLong(str, j));
    }
}
