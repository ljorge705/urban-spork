package com.uxcam.screenshot.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0002\n\u0000¨\u0006\u0000"}, d2 = {"screenshot_littleRelease"}, k = 2, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class UxOrientationKt {
    public static final boolean a(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels > displayMetrics.heightPixels;
    }
}
