package com.uxcam.screenshot;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import com.uxcam.screenshot.utils.ScreenShotBitmapUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenshot/BitmapCreatorImpl;", "Lcom/uxcam/screenshot/BitmapCreator;", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class BitmapCreatorImpl implements BitmapCreator {

    /* renamed from: a, reason: collision with root package name */
    public final ScreenShotBitmapUtil f259a;

    public BitmapCreatorImpl(ScreenShotBitmapUtil screenShotBitmapUtil) {
        Intrinsics.checkNotNullParameter(screenShotBitmapUtil, "screenShotBitmapUtil");
        this.f259a = screenShotBitmapUtil;
    }

    public static boolean b(Activity activity) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels > displayMetrics.heightPixels;
    }

    @Override // com.uxcam.screenshot.BitmapCreator
    public final Bitmap a(Activity activity) {
        int bitmapHeight;
        int bitmapWidth;
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (b(activity)) {
            bitmapHeight = this.f259a.getBitmapWidth();
            bitmapWidth = this.f259a.getBitmapHeight();
        } else {
            bitmapHeight = this.f259a.getBitmapHeight();
            bitmapWidth = this.f259a.getBitmapWidth();
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(bitmapWidth… Bitmap.Config.ARGB_8888)");
        return bitmapCreateBitmap;
    }
}
