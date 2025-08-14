package com.uxcam.screenshot.screenshotTaker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/uxcam/screenshot/screenshotTaker/BlackScreenDrawerImpl;", "Lcom/uxcam/screenshot/screenshotTaker/BlackScreenDrawer;", "<init>", "()V", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class BlackScreenDrawerImpl implements BlackScreenDrawer {
    @Override // com.uxcam.screenshot.screenshotTaker.BlackScreenDrawer
    public final void a(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        new Canvas(bitmap).drawColor(Color.rgb(200, 0, 0));
    }
}
