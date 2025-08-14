package com.onfido.android.sdk.capture.edge_detector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\rR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/onfido/android/sdk/capture/edge_detector/EdgeDetectorFrame;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "circlePaint", "Landroid/graphics/Paint;", "detectedEdgesPaint", "edgeDetectionState", "Lcom/onfido/android/sdk/capture/edge_detector/EdgeDetectionResults;", "halfLineWidth", "", ViewProps.LINE_HEIGHT, "lineWidth", "placeholderPaint", "whitePaint", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "update", "results", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EdgeDetectorFrame extends RelativeLayout {
    private final Paint circlePaint;
    private final Paint detectedEdgesPaint;
    private EdgeDetectionResults edgeDetectionState;
    private final float halfLineWidth;
    private final float lineHeight;
    private final float lineWidth;
    private final Paint placeholderPaint;
    private final Paint whitePaint;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public EdgeDetectorFrame(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();
        float f = this.halfLineWidth;
        float f2 = width - f;
        float f3 = height - f;
        float f4 = width - this.lineHeight;
        canvas.drawLine(0.0f, f, width, f, this.placeholderPaint);
        float f5 = this.halfLineWidth;
        canvas.drawLine(f5, 0.0f, f5, height, this.placeholderPaint);
        canvas.drawLine(0.0f, f3, width, f3, this.placeholderPaint);
        canvas.drawLine(f2, 0.0f, f2, height, this.placeholderPaint);
        float f6 = this.halfLineWidth;
        canvas.drawLine(f6, height - this.lineHeight, f6, height, this.whitePaint);
        canvas.drawLine(f2, height - this.lineHeight, f2, height, this.whitePaint);
        float f7 = this.halfLineWidth;
        canvas.drawLine(f7, f7, f7, this.lineHeight, this.whitePaint);
        canvas.drawLine(f2, 0.0f, f2, this.lineHeight, this.whitePaint);
        float f8 = this.halfLineWidth;
        canvas.drawLine(0.0f, f8, this.lineHeight, f8, this.whitePaint);
        canvas.drawLine(0.0f, f3, this.lineHeight, f3, this.whitePaint);
        canvas.drawLine(f4, f3, width, f3, this.whitePaint);
        float f9 = this.halfLineWidth;
        canvas.drawLine(f4, f9, width, f9, this.whitePaint);
        if (this.edgeDetectionState.getLeftEdgeDetected()) {
            canvas.drawCircle(0.0f, 0.0f, this.lineHeight, this.circlePaint);
            canvas.drawCircle(width, 0.0f, this.lineHeight, this.circlePaint);
            float f10 = this.halfLineWidth;
            canvas.drawLine(f10, f10, f10, this.lineHeight, this.detectedEdgesPaint);
            float f11 = this.halfLineWidth;
            canvas.drawLine(0.0f, f11, width, f11, this.detectedEdgesPaint);
            canvas.drawLine(f2, 0.0f, f2, this.lineHeight, this.detectedEdgesPaint);
        }
        if (this.edgeDetectionState.getBottomEdgeDetected()) {
            canvas.drawCircle(0.0f, 0.0f, this.lineHeight, this.circlePaint);
            canvas.drawCircle(0.0f, height, this.lineHeight, this.circlePaint);
            float f12 = this.halfLineWidth;
            canvas.drawLine(0.0f, f12, this.lineHeight, f12, this.detectedEdgesPaint);
            float f13 = this.halfLineWidth;
            canvas.drawLine(f13, 0.0f, f13, height, this.detectedEdgesPaint);
            canvas.drawLine(0.0f, f3, this.lineHeight, f3, this.detectedEdgesPaint);
        }
        if (this.edgeDetectionState.getRightEdgeDetected()) {
            canvas.drawCircle(0.0f, height, this.lineHeight, this.circlePaint);
            canvas.drawCircle(width, height, this.lineHeight, this.circlePaint);
            float f14 = this.halfLineWidth;
            canvas.drawLine(f14, height - this.lineHeight, f14, height, this.detectedEdgesPaint);
            canvas.drawLine(0.0f, f3, width, f3, this.detectedEdgesPaint);
            canvas.drawLine(f2, height - this.lineHeight, f2, height, this.detectedEdgesPaint);
        }
        if (this.edgeDetectionState.getTopEdgeDetected()) {
            canvas.drawCircle(width, 0.0f, this.lineHeight, this.circlePaint);
            canvas.drawCircle(width, height, this.lineHeight, this.circlePaint);
            canvas.drawLine(f4, f3, width, f3, this.detectedEdgesPaint);
            canvas.drawLine(f2, 0.0f, f2, height, this.detectedEdgesPaint);
            float f15 = this.halfLineWidth;
            canvas.drawLine(f4, f15, width, f15, this.detectedEdgesPaint);
        }
    }

    public final void update(EdgeDetectionResults results) {
        Intrinsics.checkNotNullParameter(results, "results");
        this.edgeDetectionState = results;
        invalidate();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public EdgeDetectorFrame(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EdgeDetectorFrame(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        float fDimenInt = ContextUtilsKt.dimenInt(context, R.dimen.onfido_capture_frame_stroke_width);
        this.lineWidth = fDimenInt;
        this.lineHeight = ContextUtilsKt.dimenInt(context, R.dimen.onfido_capture_frame_stroke_height);
        this.halfLineWidth = fDimenInt / 2;
        Paint paint = new Paint();
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        paint.setColor(ContextUtilsKt.color(context, R.color.onfido_light_300_o_30));
        paint.setStrokeWidth(fDimenInt);
        this.placeholderPaint = paint;
        Paint paint2 = new Paint();
        paint2.setStyle(style);
        paint2.setColor(ContextUtilsKt.color(context, R.color.onfido_white));
        paint2.setStrokeWidth(fDimenInt);
        this.whitePaint = paint2;
        Paint paint3 = new Paint();
        paint3.setStyle(style);
        paint3.setColor(ContextUtilsKt.color(context, R.color.onfidoPrimaryButtonColor));
        paint3.setStrokeWidth(fDimenInt);
        this.detectedEdgesPaint = paint3;
        Paint paint4 = new Paint();
        paint4.setStyle(Paint.Style.FILL);
        paint4.setColor(ContextUtilsKt.color(context, R.color.onfidoPrimaryButtonColor));
        this.circlePaint = paint4;
        this.edgeDetectionState = new EdgeDetectionResults(false, false, false, false);
        setWillNotDraw(false);
    }

    public /* synthetic */ EdgeDetectorFrame(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
