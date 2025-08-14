package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.util;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import io.sentry.Session;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0014\u0010\u0012\u001a\u00020\u00132\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0014J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0014R\u0014\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/util/RectDebuggingView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "paint", "Landroid/graphics/Paint;", "getPaint$annotations", "()V", "rects", "", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "drawRects", "", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RectDebuggingView extends View {
    private final Paint paint;
    private final List<OnfidoRectF> rects;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RectDebuggingView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.rects = new ArrayList();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        paint.setColor(ContextCompat.getColor(context2, R.color.white));
        paint.setStrokeWidth(5.0f);
        this.paint = paint;
    }

    private static /* synthetic */ void getPaint$annotations() {
    }

    public final void drawRects(List<OnfidoRectF> rects) {
        Intrinsics.checkNotNullParameter(rects, "rects");
        this.rects.clear();
        this.rects.addAll(rects);
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Iterator<T> it = this.rects.iterator();
        while (it.hasNext()) {
            canvas.drawRect(((OnfidoRectF) it.next()).toRectF(), this.paint);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RectDebuggingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.rects = new ArrayList();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        paint.setColor(ContextCompat.getColor(context2, R.color.white));
        paint.setStrokeWidth(5.0f);
        this.paint = paint;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RectDebuggingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.rects = new ArrayList();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        paint.setColor(ContextCompat.getColor(context2, R.color.white));
        paint.setStrokeWidth(5.0f);
        this.paint = paint;
    }
}
