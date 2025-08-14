package com.singular.sdk.internal.utils;

import android.net.Uri;

/* loaded from: classes6.dex */
public class UriUtils {
    private UriUtils() {
    }

    public static boolean isValid(Uri uri) {
        return uri != null && uri.isHierarchical() && uri.isAbsolute();
    }
}
