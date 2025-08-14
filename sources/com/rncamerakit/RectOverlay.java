package com.rncamerakit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CKCamera.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\n\u001a\u00020\u000b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\fJ\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/rncamerakit/RectOverlay;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "paint", "Landroid/graphics/Paint;", "rectBounds", "", "Landroid/graphics/RectF;", "drawRectBounds", "", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "react-native-camera-kit_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class RectOverlay extends View {
    private final Paint paint;
    private final List<RectF> rectBounds;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RectOverlay(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.rectBounds = new ArrayList();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(ContextCompat.getColor(context, android.R.color.holo_green_light));
        paint.setStrokeWidth(5.0f);
        this.paint = paint;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        Iterator<T> it = this.rectBounds.iterator();
        while (it.hasNext()) {
            canvas.drawRect((RectF) it.next(), this.paint);
        }
    }

    public final void drawRectBounds(List<? extends RectF> rectBounds) {
        Intrinsics.checkNotNullParameter(rectBounds, "rectBounds");
        this.rectBounds.clear();
        this.rectBounds.addAll(rectBounds);
        invalidate();
        postDelayed(new Runnable() { // from class: com.rncamerakit.RectOverlay$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RectOverlay.drawRectBounds$lambda$2(this.f$0);
            }
        }, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void drawRectBounds$lambda$2(RectOverlay this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.rectBounds.clear();
        this$0.invalidate();
    }
}
