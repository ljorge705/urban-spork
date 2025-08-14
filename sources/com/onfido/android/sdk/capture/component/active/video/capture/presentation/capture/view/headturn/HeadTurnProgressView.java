package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraControllerFactory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.util.OvalRect;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.WindowHelperKt;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import io.sentry.Session;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.time.Duration;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u0000 I2\u00020\u0001:\u0001IB%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0002J\u0010\u00100\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0002J\u0010\u00101\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0002J\u0006\u00102\u001a\u00020\u001aJ\u0006\u00103\u001a\u00020\u001aJ\u0010\u00104\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0014J(\u00105\u001a\u00020-2\u0006\u00106\u001a\u00020\u00072\u0006\u00107\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u00072\u0006\u00109\u001a\u00020\u0007H\u0014J*\u0010:\u001a\u00020-2\u0006\u0010;\u001a\u00020\n2\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u000f2\b\b\u0002\u0010?\u001a\u00020\u000fH\u0002J\u0006\u0010@\u001a\u00020-J\u0006\u0010A\u001a\u00020-J\u0006\u0010B\u001a\u00020-J\u000e\u0010C\u001a\u00020-2\u0006\u0010D\u001a\u00020\u000fJ\u0006\u0010E\u001a\u00020-J\u000e\u0010F\u001a\u00020-2\u0006\u0010D\u001a\u00020\u000fJ\u0010\u0010G\u001a\u00020-2\u0006\u0010D\u001a\u00020\u000fH\u0002J\u0010\u0010H\u001a\u00020-2\u0006\u0010D\u001a\u00020\u000fH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R#\u0010\u0012\u001a\n \u0014*\u0004\u0018\u00010\u00130\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\"\u001a\n \u0014*\u0004\u0018\u00010\u00130\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u0018\u001a\u0004\b#\u0010\u0016R\u000e\u0010%\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/headturn/HeadTurnProgressView;", "Landroid/view/View;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "bottomLeftProgressPath", "Landroid/graphics/Path;", "bottomRightProgressPath", "completePaint", "Landroid/graphics/Paint;", "completeStrokeWidth", "", "currentLeftProgress", "currentRightProgress", "leftProgressAnimator", "Landroid/animation/ValueAnimator;", "kotlin.jvm.PlatformType", "getLeftProgressAnimator", "()Landroid/animation/ValueAnimator;", "leftProgressAnimator$delegate", "Lkotlin/Lazy;", "leftSideCompleted", "", "leftTrackPath", "leftTrackPathMeasure", "Landroid/graphics/PathMeasure;", "ovalPath", "paddingTrackAndOval", "progressPaint", "progressStrokeWidth", "rightProgressAnimator", "getRightProgressAnimator", "rightProgressAnimator$delegate", "rightSideCompleted", "rightTrackPath", "rightTrackPathMeasure", "topLeftProgressPath", "topRightProgressPath", "trackPaint", "trackStrokeWidth", "drawLeftSide", "", "canvas", "Landroid/graphics/Canvas;", "drawOval", "drawRightSide", "isLeftSideCompleted", "isRightSideCompleted", "onDraw", "onSizeChanged", Constants.INAPP_WINDOW, "h", "oldw", "oldh", "prepareLeftTrackPath", ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, "rect", "Landroid/graphics/RectF;", Constants.KEY_RADIUS, ViewProps.PADDING, "resetLeftSide", "resetRightSide", "setLeftSideCompleted", "setLeftSideProgress", "progress", "setRightSideCompleted", "setRightSideProgress", "setupLeftProgressForDrawing", "setupRightProgressForDrawing", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HeadTurnProgressView extends View {
    private static final float LEFT_START_ANGLE = 180.0f;
    private static final float PADDING_BETWEEN_TRACKS_IN_ANGLES = 3.0f;
    private static final float SWEEP_ANGLE = -90.0f;
    private static final float TOP_START_ANGLE = 270.0f;
    private Path bottomLeftProgressPath;
    private Path bottomRightProgressPath;
    private Paint completePaint;
    private final float completeStrokeWidth;
    private float currentLeftProgress;
    private float currentRightProgress;

    /* renamed from: leftProgressAnimator$delegate, reason: from kotlin metadata */
    private final Lazy leftProgressAnimator;
    private boolean leftSideCompleted;
    private final Path leftTrackPath;
    private PathMeasure leftTrackPathMeasure;
    private final Path ovalPath;
    private final float paddingTrackAndOval;
    private Paint progressPaint;
    private final float progressStrokeWidth;

    /* renamed from: rightProgressAnimator$delegate, reason: from kotlin metadata */
    private final Lazy rightProgressAnimator;
    private boolean rightSideCompleted;
    private final Path rightTrackPath;
    private PathMeasure rightTrackPathMeasure;
    private Path topLeftProgressPath;
    private Path topRightProgressPath;
    private Paint trackPaint;
    private final float trackStrokeWidth;
    private static final long ANIMATION_DURATION = Duration.m7440getInWholeMillisecondsimpl(MotionCameraControllerFactory.INSTANCE.m5540getFRAME_SAMPLING_PERIOD_MILLISECONDSUwyO8pc()) + 50;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HeadTurnProgressView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final void drawLeftSide(Canvas canvas) {
        Path path;
        Paint paint;
        if (this.leftSideCompleted) {
            path = this.leftTrackPath;
            paint = this.completePaint;
        } else {
            canvas.drawPath(this.leftTrackPath, this.trackPaint);
            canvas.drawPath(this.topLeftProgressPath, this.progressPaint);
            path = this.bottomLeftProgressPath;
            paint = this.progressPaint;
        }
        canvas.drawPath(path, paint);
    }

    private final void drawOval(Canvas canvas) {
        canvas.clipPath(this.ovalPath);
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        canvas.drawColor(ContextUtilsKt.colorFromAttr(context, R.attr.onfidoColorMotionHeadTurnProgressOvalOutsideFill));
    }

    private final void drawRightSide(Canvas canvas) {
        Path path;
        Paint paint;
        if (this.rightSideCompleted) {
            path = this.rightTrackPath;
            paint = this.completePaint;
        } else {
            canvas.drawPath(this.rightTrackPath, this.trackPaint);
            canvas.drawPath(this.topRightProgressPath, this.progressPaint);
            path = this.bottomRightProgressPath;
            paint = this.progressPaint;
        }
        canvas.drawPath(path, paint);
    }

    private final ValueAnimator getLeftProgressAnimator() {
        return (ValueAnimator) this.leftProgressAnimator.getValue();
    }

    private final ValueAnimator getRightProgressAnimator() {
        return (ValueAnimator) this.rightProgressAnimator.getValue();
    }

    private final void prepareLeftTrackPath(Path path, RectF rect, float radius, float padding) {
        float f = rect.right - rect.left;
        float f2 = rect.bottom - rect.top;
        float f3 = 2;
        float fCoerceIn = f3 * RangesKt.coerceIn(radius, 0.0f, f / f3);
        float f4 = rect.left;
        float f5 = rect.top;
        float f6 = padding + SWEEP_ANGLE;
        path.arcTo(f4, f5, f4 + fCoerceIn, f5 + fCoerceIn, 270.0f - padding, f6, false);
        path.rLineTo(0.0f, f2 - fCoerceIn);
        float f7 = rect.left;
        float f8 = rect.bottom;
        path.arcTo(f7, f8 - fCoerceIn, f7 + fCoerceIn, f8, 180.0f, f6, false);
    }

    static /* synthetic */ void prepareLeftTrackPath$default(HeadTurnProgressView headTurnProgressView, Path path, RectF rectF, float f, float f2, int i, Object obj) {
        if ((i & 8) != 0) {
            f2 = 3.0f;
        }
        headTurnProgressView.prepareLeftTrackPath(path, rectF, f, f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupLeftProgressForDrawing(float progress) {
        PathMeasure pathMeasure = this.leftTrackPathMeasure;
        if (pathMeasure == null) {
            return;
        }
        Intrinsics.checkNotNull(pathMeasure);
        float length = (pathMeasure.getLength() / 2) * progress;
        this.topLeftProgressPath.reset();
        PathMeasure pathMeasure2 = this.leftTrackPathMeasure;
        Intrinsics.checkNotNull(pathMeasure2);
        pathMeasure2.getSegment(0.0f, length, this.topLeftProgressPath, true);
        this.bottomLeftProgressPath.reset();
        PathMeasure pathMeasure3 = this.leftTrackPathMeasure;
        Intrinsics.checkNotNull(pathMeasure3);
        PathMeasure pathMeasure4 = this.leftTrackPathMeasure;
        Intrinsics.checkNotNull(pathMeasure4);
        float length2 = pathMeasure4.getLength() - length;
        PathMeasure pathMeasure5 = this.leftTrackPathMeasure;
        Intrinsics.checkNotNull(pathMeasure5);
        pathMeasure3.getSegment(length2, pathMeasure5.getLength(), this.bottomLeftProgressPath, true);
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupRightProgressForDrawing(float progress) {
        PathMeasure pathMeasure = this.rightTrackPathMeasure;
        if (pathMeasure == null) {
            return;
        }
        Intrinsics.checkNotNull(pathMeasure);
        float length = (pathMeasure.getLength() / 2) * progress;
        this.topRightProgressPath.reset();
        PathMeasure pathMeasure2 = this.rightTrackPathMeasure;
        Intrinsics.checkNotNull(pathMeasure2);
        pathMeasure2.getSegment(0.0f, length, this.topRightProgressPath, true);
        this.bottomRightProgressPath.reset();
        PathMeasure pathMeasure3 = this.rightTrackPathMeasure;
        Intrinsics.checkNotNull(pathMeasure3);
        PathMeasure pathMeasure4 = this.rightTrackPathMeasure;
        Intrinsics.checkNotNull(pathMeasure4);
        float length2 = pathMeasure4.getLength() - length;
        PathMeasure pathMeasure5 = this.rightTrackPathMeasure;
        Intrinsics.checkNotNull(pathMeasure5);
        pathMeasure3.getSegment(length2, pathMeasure5.getLength(), this.bottomRightProgressPath, true);
        invalidate();
    }

    /* renamed from: isLeftSideCompleted, reason: from getter */
    public final boolean getLeftSideCompleted() {
        return this.leftSideCompleted;
    }

    /* renamed from: isRightSideCompleted, reason: from getter */
    public final boolean getRightSideCompleted() {
        return this.rightSideCompleted;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        drawOval(canvas);
        drawLeftSide(canvas);
        drawRightSide(canvas);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        OvalRect ovalRect = OvalRect.INSTANCE;
        int width = getWidth();
        int height = getHeight();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        RectF rectF = ovalRect.get(width, height, WindowHelperKt.getWindowSizeClass(context)).toRectF();
        float f = 2;
        float fWidth = rectF.width() / f;
        this.ovalPath.addRoundRect(rectF, fWidth, fWidth, Path.Direction.CW);
        this.ovalPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        float f2 = -this.paddingTrackAndOval;
        rectF.inset(f2, f2);
        prepareLeftTrackPath$default(this, this.leftTrackPath, rectF, rectF.width() / f, 0.0f, 8, null);
        Matrix matrix = new Matrix();
        matrix.setScale(-1.0f, 1.0f);
        matrix.postTranslate(getWidth(), 0.0f);
        this.leftTrackPath.transform(matrix, this.rightTrackPath);
        this.leftTrackPathMeasure = new PathMeasure(this.leftTrackPath, false);
        this.rightTrackPathMeasure = new PathMeasure(this.rightTrackPath, false);
    }

    public final void resetLeftSide() {
        this.leftSideCompleted = false;
        setLeftSideProgress(0.0f);
    }

    public final void resetRightSide() {
        this.rightSideCompleted = false;
        setRightSideProgress(0.0f);
    }

    public final void setLeftSideCompleted() {
        this.leftSideCompleted = true;
        invalidate();
    }

    public final void setLeftSideProgress(float progress) {
        ValueAnimator leftProgressAnimator = getLeftProgressAnimator();
        leftProgressAnimator.cancel();
        leftProgressAnimator.setFloatValues(this.currentLeftProgress, progress);
        leftProgressAnimator.start();
    }

    public final void setRightSideCompleted() {
        this.rightSideCompleted = true;
        invalidate();
    }

    public final void setRightSideProgress(float progress) {
        ValueAnimator rightProgressAnimator = getRightProgressAnimator();
        rightProgressAnimator.cancel();
        rightProgressAnimator.setFloatValues(this.currentRightProgress, progress);
        rightProgressAnimator.start();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HeadTurnProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadTurnProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.ovalPath = new Path();
        this.leftTrackPath = new Path();
        this.rightTrackPath = new Path();
        this.topLeftProgressPath = new Path();
        this.bottomLeftProgressPath = new Path();
        this.topRightProgressPath = new Path();
        this.bottomRightProgressPath = new Path();
        float fDimenInt = ContextUtilsKt.dimenInt(context, R.dimen.onfido_avc_head_turn_track_stroke_width);
        this.trackStrokeWidth = fDimenInt;
        float fDimenInt2 = ContextUtilsKt.dimenInt(context, R.dimen.onfido_avc_head_turn_progress_stroke_width);
        this.progressStrokeWidth = fDimenInt2;
        float fDimenInt3 = ContextUtilsKt.dimenInt(context, R.dimen.onfido_avc_head_turn_complete_stroke_width);
        this.completeStrokeWidth = fDimenInt3;
        this.paddingTrackAndOval = ContextUtilsKt.dimenInt(context, R.dimen.onfido_avc_padding_track_and_oval);
        Paint paint = new Paint();
        paint.setColor(ContextUtilsKt.colorFromAttr(context, R.attr.onfidoColorMotionHeadTurnProgressTrack));
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        paint.setStrokeWidth(fDimenInt);
        Paint.Cap cap = Paint.Cap.ROUND;
        paint.setStrokeCap(cap);
        this.trackPaint = paint;
        Paint paint2 = new Paint();
        paint2.setColor(ContextUtilsKt.colorFromAttr(context, R.attr.onfidoColorMotionHeadTurnProgress));
        paint2.setStyle(style);
        paint2.setStrokeWidth(fDimenInt2);
        paint2.setStrokeCap(cap);
        this.progressPaint = paint2;
        Paint paint3 = new Paint();
        paint3.setColor(ContextUtilsKt.colorFromAttr(context, R.attr.onfidoColorMotionHeadTurnProgressCompleted));
        paint3.setStyle(style);
        paint3.setStrokeWidth(fDimenInt3);
        paint3.setStrokeCap(cap);
        this.completePaint = paint3;
        this.leftProgressAnimator = LazyKt.lazy(new HeadTurnProgressView$leftProgressAnimator$2(this));
        this.rightProgressAnimator = LazyKt.lazy(new HeadTurnProgressView$rightProgressAnimator$2(this));
    }

    public /* synthetic */ HeadTurnProgressView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
