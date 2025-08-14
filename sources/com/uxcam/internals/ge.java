package com.uxcam.internals;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.view.ViewCompat;
import com.uxcam.internals.gi;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenshot.di.ScreenshotModule;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class ge implements gi.ab {

    /* renamed from: a, reason: collision with root package name */
    public final Paint f171a;

    public ge() {
        Paint paint = new Paint();
        this.f171a = paint;
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setTextSize(22.0f);
    }

    public final boolean a(Canvas canvas, Bitmap bitmap) {
        this.f171a.setColor(ViewCompat.MEASURED_STATE_MASK);
        canvas.drawRect(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), this.f171a);
        if (bitmap == null) {
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            bitmap = biVar.f98a.getBitmapSource().getLastFrameCache();
        }
        if (bitmap != null) {
            bitmap.getByteCount();
        }
        if (bitmap != null && !bitmap.isRecycled()) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        }
        return ff.i;
    }
}
