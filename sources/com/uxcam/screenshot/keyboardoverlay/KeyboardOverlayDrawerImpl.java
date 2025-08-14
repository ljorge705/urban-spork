package com.uxcam.screenshot.keyboardoverlay;

import android.graphics.Canvas;
import android.graphics.Paint;

/* loaded from: classes6.dex */
public class KeyboardOverlayDrawerImpl implements KeyboardOverlayDrawer {
    @Override // com.uxcam.screenshot.keyboardoverlay.KeyboardOverlayDrawer
    public final void a(int i, float f, Canvas canvas, Paint paint, Paint paint2) {
        if (i > 0) {
            try {
                canvas.drawRect(0.0f, (int) (i * f), canvas.getWidth(), canvas.getHeight(), paint);
                canvas.drawText("Keyboard", (canvas.getWidth() / 2) - (((int) paint2.measureText("Keyboard")) / 2), r8 + ((canvas.getHeight() - r8) / 2), paint2);
            } catch (Exception e) {
                canvas.drawColor(-65281);
                e.printStackTrace();
            }
        }
    }
}
