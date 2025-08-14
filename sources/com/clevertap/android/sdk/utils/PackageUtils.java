package com.clevertap.android.sdk.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.common.GoogleApiAvailabilityLight;

/* loaded from: classes5.dex */
public class PackageUtils {
    private static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    private static final String GOOGLE_PLAY_STORE_PACKAGE_OLD = "com.google.market";

    public static boolean isGooglePlayServicesAvailable(Context context) throws ClassNotFoundException {
        try {
            Class.forName("com.google.android.gms.common.GooglePlayServicesUtil");
            return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context) == 0;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean isGooglePlayStoreAvailable(Context context) {
        return isPackageAvailable(context, "com.android.vending") || isPackageAvailable(context, GOOGLE_PLAY_STORE_PACKAGE_OLD);
    }

    private static boolean isPackageAvailable(Context context, String str) throws PackageManager.NameNotFoundException {
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
