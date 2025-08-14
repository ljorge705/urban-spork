package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;

/* loaded from: classes3.dex */
abstract class DrawingDelegate<S extends BaseProgressIndicatorSpec> {
    protected DrawableWithAnimatedVisibilityChange drawable;
    S spec;

    abstract void adjustCanvas(Canvas canvas, Rect rect, float f);

    abstract void fillIndicator(Canvas canvas, Paint paint, float f, float f2, int i);

    abstract void fillTrack(Canvas canvas, Paint paint);

    abstract int getPreferredHeight();

    abstract int getPreferredWidth();

    protected void registerDrawable(DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange) {
        this.drawable = drawableWithAnimatedVisibilityChange;
    }

    public DrawingDelegate(S s) {
        this.spec = s;
    }

    void validateSpecAndAdjustCanvas(Canvas canvas, Rect rect, float f) {
        this.spec.validateSpec();
        adjustCanvas(canvas, rect, f);
    }
}
