package com.uxcam.screenshot.floatingpanel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/* loaded from: classes6.dex */
public final class FloatingPanelRenderer {

    /* renamed from: a, reason: collision with root package name */
    public final FloatingPanelDataProvider f262a;

    public FloatingPanelRenderer(FloatingPanelDataProvider floatingPanelDataProvider) {
        this.f262a = floatingPanelDataProvider;
    }

    public static void a(Canvas canvas, FloatingPanelData floatingPanelData) {
        View view = floatingPanelData.f260a;
        if (view.getWidth() == 0 || view.getHeight() == 0) {
            return;
        }
        Rect rect = floatingPanelData.b;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(rect.right - rect.left, rect.bottom - rect.top, Bitmap.Config.ARGB_8888);
        floatingPanelData.f260a.draw(new Canvas(bitmapCreateBitmap));
        Rect rect2 = floatingPanelData.b;
        canvas.drawBitmap(bitmapCreateBitmap, rect2.left, rect2.top, new Paint());
        bitmapCreateBitmap.recycle();
    }
}
