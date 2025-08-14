package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.util.OvalRect;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.WindowHelperKt;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0014R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/headturn/HeadTurnCompletedOvalView;", "Landroid/view/View;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "completeStrokeWidth", "", "ovalBlurPaint", "Landroid/graphics/Paint;", "ovalPath", "Landroid/graphics/Path;", "paddingTrackAndOval", "trackPaint", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HeadTurnCompletedOvalView extends View {
    private final float completeStrokeWidth;
    private final Paint ovalBlurPaint;
    private final Path ovalPath;
    private final float paddingTrackAndOval;
    private final Paint trackPaint;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HeadTurnCompletedOvalView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        this.ovalPath.reset();
        OvalRect ovalRect = OvalRect.INSTANCE;
        int width = getWidth();
        int height = getHeight();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        RectF rectF = ovalRect.get(width, height, WindowHelperKt.getWindowSizeClass(context)).toRectF();
        float f = 2;
        float fWidth = rectF.width() / f;
        canvas.drawRoundRect(rectF, fWidth, fWidth, this.ovalBlurPaint);
        this.ovalPath.addRoundRect(rectF, fWidth, fWidth, Path.Direction.CW);
        this.ovalPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        canvas.clipPath(this.ovalPath);
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        canvas.drawColor(ContextUtilsKt.colorFromAttr(context2, R.attr.onfidoColorMotionHeadTurnCompletedOvalOutsideFill));
        float f2 = -this.paddingTrackAndOval;
        rectF.inset(f2, f2);
        float fWidth2 = rectF.width() / f;
        canvas.drawRoundRect(rectF, fWidth2, fWidth2, this.trackPaint);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HeadTurnCompletedOvalView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadTurnCompletedOvalView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.ovalPath = new Path();
        float fDimenInt = ContextUtilsKt.dimenInt(context, R.dimen.onfido_avc_head_turn_complete_stroke_width);
        this.completeStrokeWidth = fDimenInt;
        this.paddingTrackAndOval = ContextUtilsKt.dimenInt(context, R.dimen.onfido_avc_padding_track_and_oval);
        Paint paint = new Paint();
        paint.setColor(ContextUtilsKt.colorFromAttr(context, R.attr.onfidoColorMotionHeadTurnCompletedTrack));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(fDimenInt);
        this.trackPaint = paint;
        Paint paint2 = new Paint();
        paint2.setColor(ContextUtilsKt.colorFromAttr(context, R.attr.onfidoColorMotionHeadTurnCompletedOvalBlur));
        paint2.setStyle(Paint.Style.FILL);
        this.ovalBlurPaint = paint2;
    }

    public /* synthetic */ HeadTurnCompletedOvalView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
