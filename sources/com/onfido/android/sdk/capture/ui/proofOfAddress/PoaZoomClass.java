package com.onfido.android.sdk.capture.ui.proofOfAddress;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.henninghall.date_picker.props.ModeProp;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 D2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002DEB\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001b\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB#\b\u0016\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\r\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b\"J\u0006\u0010#\u001a\u00020!J \u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u00172\u0006\u0010'\u001a\u00020\u0017H\u0002J \u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u00172\u0006\u0010'\u001a\u00020\u0017H\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0016J\u0010\u0010.\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0016J\u0010\u0010/\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0016J*\u00100\u001a\u00020+2\b\u00101\u001a\u0004\u0018\u00010-2\u0006\u00102\u001a\u00020-2\u0006\u00103\u001a\u00020\u00172\u0006\u00104\u001a\u00020\u0017H\u0016J\u0010\u00105\u001a\u00020!2\u0006\u0010,\u001a\u00020-H\u0016J\u0018\u00106\u001a\u00020!2\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u00020\fH\u0014J*\u00109\u001a\u00020+2\b\u00101\u001a\u0004\u0018\u00010-2\u0006\u00102\u001a\u00020-2\u0006\u0010:\u001a\u00020\u00172\u0006\u0010;\u001a\u00020\u0017H\u0016J\u0010\u0010<\u001a\u00020!2\u0006\u0010,\u001a\u00020-H\u0016J\u0010\u0010=\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0016J\u0010\u0010>\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0016J\u001a\u0010?\u001a\u00020+2\b\u0010@\u001a\u0004\u0018\u00010A2\u0006\u0010B\u001a\u00020-H\u0016J\u0010\u0010C\u001a\u00020!2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/PoaZoomClass;", "Landroidx/appcompat/widget/AppCompatImageView;", "Landroid/view/View$OnTouchListener;", "Landroid/view/GestureDetector$OnGestureListener;", "Landroid/view/GestureDetector$OnDoubleTapListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "gestureDetector", "Landroid/view/GestureDetector;", "mLast", "Landroid/graphics/PointF;", "mMatrix", "Landroid/graphics/Matrix;", "mMatrixValues", "", "mSaveScale", "", "mStart", ModeProp.name, "origHeight", "origWidth", "scaleDetector", "Landroid/view/ScaleGestureDetector;", "viewHeight", "viewWidth", "fitToScreen", "", "fitToScreen$onfido_capture_sdk_core_release", "fixTranslation", "getFixDragTrans", "delta", "viewSize", "contentSize", "getFixTranslation", "trans", "onDoubleTap", "", "motionEvent", "Landroid/view/MotionEvent;", "onDoubleTapEvent", "onDown", "onFling", "e1", "e2", "velocityX", "velocityY", "onLongPress", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onScroll", "distanceX", "distanceY", "onShowPress", "onSingleTapConfirmed", "onSingleTapUp", "onTouch", "view", "Landroid/view/View;", "event", "sharedConstructing", "Companion", "ScaleListener", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PoaZoomClass extends AppCompatImageView implements View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    public static final int DRAG = 1;
    public static final int NONE = 0;
    public static final int ZOOM = 2;
    private static final float mMaxScale = 4.0f;
    private static final float mMinScale = 1.0f;
    private static final int matrixValues = 9;
    private GestureDetector gestureDetector;
    private final PointF mLast;
    private Matrix mMatrix;
    private float[] mMatrixValues;
    private float mSaveScale;
    private final PointF mStart;
    private int mode;
    private float origHeight;
    private float origWidth;
    private ScaleGestureDetector scaleDetector;
    private int viewHeight;
    private int viewWidth;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/PoaZoomClass$ScaleListener;", "Landroid/view/ScaleGestureDetector$SimpleOnScaleGestureListener;", "(Lcom/onfido/android/sdk/capture/ui/proofOfAddress/PoaZoomClass;)V", "onScale", "", "detector", "Landroid/view/ScaleGestureDetector;", "onScaleBegin", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private final class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        public ScaleListener() {
        }

        /* JADX WARN: Removed duplicated region for block: B:4:0x0025 A[PHI: r3
          0x0025: PHI (r3v9 float) = (r3v2 float), (r3v3 float) binds: [B:3:0x0023, B:6:0x0037] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean onScale(android.view.ScaleGestureDetector r6) {
            /*
                r5 = this;
                java.lang.String r0 = "detector"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                float r0 = r6.getScaleFactor()
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r1 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                float r1 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$getMSaveScale$p(r1)
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r2 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                float r3 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$getMSaveScale$p(r2)
                float r3 = r3 * r0
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$setMSaveScale$p(r2, r3)
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r2 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                float r2 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$getMSaveScale$p(r2)
                r3 = 1082130432(0x40800000, float:4.0)
                int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r2 <= 0) goto L2d
            L25:
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r0 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$setMSaveScale$p(r0, r3)
                float r0 = r3 / r1
                goto L3a
            L2d:
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r2 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                float r2 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$getMSaveScale$p(r2)
                r3 = 1065353216(0x3f800000, float:1.0)
                int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r2 >= 0) goto L3a
                goto L25
            L3a:
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r1 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                float r1 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$getOrigWidth$p(r1)
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r2 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                float r2 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$getMSaveScale$p(r2)
                float r1 = r1 * r2
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r2 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                int r2 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$getViewWidth$p(r2)
                float r2 = (float) r2
                int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
                r2 = 0
                java.lang.String r3 = "mMatrix"
                if (r1 <= 0) goto L87
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r1 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                float r1 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$getOrigHeight$p(r1)
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r4 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                float r4 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$getMSaveScale$p(r4)
                float r1 = r1 * r4
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r4 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                int r4 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$getViewHeight$p(r4)
                float r4 = (float) r4
                int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
                if (r1 > 0) goto L6e
                goto L87
            L6e:
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r1 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                android.graphics.Matrix r1 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$getMMatrix$p(r1)
                if (r1 != 0) goto L7a
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
                goto L7b
            L7a:
                r2 = r1
            L7b:
                float r1 = r6.getFocusX()
                float r6 = r6.getFocusY()
                r2.postScale(r0, r0, r1, r6)
                goto La9
            L87:
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r6 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                android.graphics.Matrix r6 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$getMMatrix$p(r6)
                if (r6 != 0) goto L93
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
                goto L94
            L93:
                r2 = r6
            L94:
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r6 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                int r6 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$getViewWidth$p(r6)
                float r6 = (float) r6
                r1 = 1073741824(0x40000000, float:2.0)
                float r6 = r6 / r1
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r3 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                int r3 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.access$getViewHeight$p(r3)
                float r3 = (float) r3
                float r3 = r3 / r1
                r2.postScale(r0, r0, r6, r3)
            La9:
                com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass r6 = com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.this
                r6.fixTranslation()
                r6 = 1
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass.ScaleListener.onScale(android.view.ScaleGestureDetector):boolean");
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            Intrinsics.checkNotNullParameter(detector, "detector");
            PoaZoomClass.this.mode = 2;
            return true;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PoaZoomClass(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mSaveScale = 1.0f;
        this.mLast = new PointF();
        this.mStart = new PointF();
        sharedConstructing(context);
    }

    private final float getFixDragTrans(float delta, float viewSize, float contentSize) {
        if (contentSize <= viewSize) {
            return 0.0f;
        }
        return delta;
    }

    private final float getFixTranslation(float trans, float viewSize, float contentSize) {
        float f;
        float f2 = viewSize - contentSize;
        if (contentSize <= viewSize) {
            f = f2;
            f2 = 0.0f;
        } else {
            f = 0.0f;
        }
        if (trans < f2) {
            return (-trans) + f2;
        }
        if (trans > f) {
            return (-trans) + f;
        }
        return 0.0f;
    }

    private final void sharedConstructing(Context context) {
        super.setClickable(true);
        this.scaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        Matrix matrix = new Matrix();
        this.mMatrix = matrix;
        this.mMatrixValues = new float[9];
        setImageMatrix(matrix);
        setScaleType(ImageView.ScaleType.MATRIX);
        this.gestureDetector = new GestureDetector(context, this);
        setOnTouchListener(this);
    }

    public final void fitToScreen$onfido_capture_sdk_core_release() {
        this.mSaveScale = 1.0f;
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
            return;
        }
        float intrinsicWidth = drawable.getIntrinsicWidth();
        float intrinsicHeight = drawable.getIntrinsicHeight();
        float fCoerceAtMost = RangesKt.coerceAtMost(this.viewWidth / intrinsicWidth, this.viewHeight / intrinsicHeight);
        Matrix matrix = this.mMatrix;
        Matrix matrix2 = null;
        if (matrix == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMatrix");
            matrix = null;
        }
        matrix.setScale(fCoerceAtMost, fCoerceAtMost);
        float f = (this.viewHeight - (intrinsicHeight * fCoerceAtMost)) / 2.0f;
        float f2 = (this.viewWidth - (fCoerceAtMost * intrinsicWidth)) / 2.0f;
        Matrix matrix3 = this.mMatrix;
        if (matrix3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMatrix");
            matrix3 = null;
        }
        matrix3.postTranslate(f2, f);
        float f3 = 2;
        this.origWidth = this.viewWidth - (f2 * f3);
        this.origHeight = this.viewHeight - (f3 * f);
        Matrix matrix4 = this.mMatrix;
        if (matrix4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMatrix");
        } else {
            matrix2 = matrix4;
        }
        setImageMatrix(matrix2);
    }

    public final void fixTranslation() {
        Matrix matrix = this.mMatrix;
        Matrix matrix2 = null;
        if (matrix == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMatrix");
            matrix = null;
        }
        float[] fArr = this.mMatrixValues;
        if (fArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMatrixValues");
            fArr = null;
        }
        matrix.getValues(fArr);
        float[] fArr2 = this.mMatrixValues;
        if (fArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMatrixValues");
            fArr2 = null;
        }
        float f = fArr2[2];
        float[] fArr3 = this.mMatrixValues;
        if (fArr3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMatrixValues");
            fArr3 = null;
        }
        float f2 = fArr3[5];
        float fixTranslation = getFixTranslation(f, this.viewWidth, this.origWidth * this.mSaveScale);
        float fixTranslation2 = getFixTranslation(f2, this.viewHeight, this.origHeight * this.mSaveScale);
        if (fixTranslation == 0.0f && fixTranslation2 == 0.0f) {
            return;
        }
        Matrix matrix3 = this.mMatrix;
        if (matrix3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMatrix");
        } else {
            matrix2 = matrix3;
        }
        matrix2.postTranslate(fixTranslation, fixTranslation2);
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        fitToScreen$onfido_capture_sdk_core_release();
        return false;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Intrinsics.checkNotNullParameter(e2, "e2");
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.viewWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        this.viewHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        if (this.mSaveScale == 1.0f) {
            fitToScreen$onfido_capture_sdk_core_release();
        }
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Intrinsics.checkNotNullParameter(e2, "e2");
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        return false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        ScaleGestureDetector scaleGestureDetector = this.scaleDetector;
        Matrix matrix = null;
        if (scaleGestureDetector == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scaleDetector");
            scaleGestureDetector = null;
        }
        scaleGestureDetector.onTouchEvent(event);
        GestureDetector gestureDetector = this.gestureDetector;
        if (gestureDetector == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gestureDetector");
            gestureDetector = null;
        }
        gestureDetector.onTouchEvent(event);
        PointF pointF = new PointF(event.getX(), event.getY());
        int action = event.getAction();
        if (action == 0) {
            this.mLast.set(pointF);
            this.mStart.set(this.mLast);
            this.mode = 1;
        } else if (action != 2) {
            if (action == 6) {
                this.mode = 0;
            }
        } else if (this.mode == 1) {
            float f = pointF.x;
            PointF pointF2 = this.mLast;
            float f2 = f - pointF2.x;
            float f3 = pointF.y - pointF2.y;
            float fixDragTrans = getFixDragTrans(f2, this.viewWidth, this.origWidth * this.mSaveScale);
            float fixDragTrans2 = getFixDragTrans(f3, this.viewHeight, this.origHeight * this.mSaveScale);
            Matrix matrix2 = this.mMatrix;
            if (matrix2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mMatrix");
                matrix2 = null;
            }
            matrix2.postTranslate(fixDragTrans, fixDragTrans2);
            fixTranslation();
            this.mLast.set(pointF.x, pointF.y);
        }
        Matrix matrix3 = this.mMatrix;
        if (matrix3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMatrix");
        } else {
            matrix = matrix3;
        }
        setImageMatrix(matrix);
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PoaZoomClass(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mSaveScale = 1.0f;
        this.mLast = new PointF();
        this.mStart = new PointF();
        sharedConstructing(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PoaZoomClass(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNull(context);
        this.mSaveScale = 1.0f;
        this.mLast = new PointF();
        this.mStart = new PointF();
    }
}
