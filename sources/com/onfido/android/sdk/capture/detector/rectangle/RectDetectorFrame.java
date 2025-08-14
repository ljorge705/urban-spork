package com.onfido.android.sdk.capture.detector.rectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.detector.rectangle.RectDetectionResult;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\u0015\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0017R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/rectangle/RectDetectorFrame;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "detectedRect", "Landroid/graphics/RectF;", "fillPaint", "Landroid/graphics/Paint;", "lineWidth", "", "strokePaint", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "update", "detectedRectResult", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectDetectionResult;", "update$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RectDetectorFrame extends FrameLayout {
    private RectF detectedRect;
    private final Paint fillPaint;
    private final float lineWidth;
    private final Paint strokePaint;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RectDetectorFrame(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        RectF rectF = this.detectedRect;
        if (rectF != null) {
            canvas.drawRect(rectF, this.fillPaint);
            canvas.drawRect(rectF, this.strokePaint);
        }
    }

    public final void update$onfido_capture_sdk_core_release(RectDetectionResult detectedRectResult) {
        Intrinsics.checkNotNullParameter(detectedRectResult, "detectedRectResult");
        this.detectedRect = detectedRectResult instanceof RectDetectionResult.RectDetected ? ((RectDetectionResult.RectDetected) detectedRectResult).getRect().toRectF() : null;
        invalidate();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RectDetectorFrame(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RectDetectorFrame(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        float fDimenInt = ContextUtilsKt.dimenInt(context, R.dimen.onfido_capture_frame_stroke_width);
        this.lineWidth = fDimenInt;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ContextCompat.getColor(context, R.color.onfido_doc_capture_detected_rectangle_fill_color));
        this.fillPaint = paint;
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(ContextCompat.getColor(context, R.color.onfido_doc_capture_detected_rectangle_stroke_color));
        paint2.setStrokeWidth(fDimenInt);
        this.strokePaint = paint2;
        setWillNotDraw(false);
    }

    public /* synthetic */ RectDetectorFrame(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
