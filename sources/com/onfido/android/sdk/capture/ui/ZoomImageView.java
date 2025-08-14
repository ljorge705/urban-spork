package com.onfido.android.sdk.capture.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;

/* loaded from: classes2.dex */
public class ZoomImageView extends AppCompatImageView {
    private static final float SUPER_MAX_MULTIPLIER = 1.25f;
    private static final float SUPER_MIN_MULTIPLIER = 0.75f;
    private ZoomVariables delayedZoomVariables;
    private boolean imageRenderedAtLeastOnce;
    private float[] m;
    private GestureDetector mGestureDetector;
    private ScaleGestureDetector mScaleDetector;
    private ImageView.ScaleType mScaleType;
    private float matchViewHeight;
    private float matchViewWidth;
    private Matrix matrix;
    private float maxScale;
    private float minScale;
    private float normalizedScale;
    private boolean onDrawReady;
    private float prevMatchViewHeight;
    private float prevMatchViewWidth;
    private Matrix prevMatrix;
    private int prevViewHeight;
    private int prevViewWidth;
    private State state;
    private float superMaxScale;
    private float superMinScale;
    private OnTouchImageViewListener touchImageViewListener;
    private View.OnTouchListener userTouchListener;
    private int viewHeight;
    private int viewWidth;

    /* renamed from: com.onfido.android.sdk.capture.ui.ZoomImageView$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            $SwitchMap$android$widget$ImageView$ScaleType = iArr;
            try {
                iArr[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private class DoubleTapZoom implements Runnable {
        private static final float ZOOM_TIME = 200.0f;
        private float bitmapX;
        private float bitmapY;
        private PointF endTouch;
        private AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
        private long startTime;
        private PointF startTouch;
        private float startZoom;
        private boolean stretchImageToSuper;
        private float targetZoom;

        DoubleTapZoom(float f, float f2, float f3, boolean z) {
            ZoomImageView.this.setState(State.ANIMATE_ZOOM);
            this.startTime = System.currentTimeMillis();
            this.startZoom = ZoomImageView.this.normalizedScale;
            this.targetZoom = f;
            this.stretchImageToSuper = z;
            PointF pointFTransformCoordTouchToBitmap = ZoomImageView.this.transformCoordTouchToBitmap(f2, f3, false);
            float f4 = pointFTransformCoordTouchToBitmap.x;
            this.bitmapX = f4;
            float f5 = pointFTransformCoordTouchToBitmap.y;
            this.bitmapY = f5;
            this.startTouch = ZoomImageView.this.transformCoordBitmapToTouch(f4, f5);
            this.endTouch = new PointF(ZoomImageView.this.viewWidth / 2, ZoomImageView.this.viewHeight / 2);
        }

        private double calculateDeltaScale(float f) {
            float f2 = this.startZoom;
            return (f2 + (f * (this.targetZoom - f2))) / ZoomImageView.this.normalizedScale;
        }

        private float interpolate() {
            return this.interpolator.getInterpolation(Math.min(1.0f, (System.currentTimeMillis() - this.startTime) / 200.0f));
        }

        private void translateImageToCenterTouchPosition(float f) {
            PointF pointF = this.startTouch;
            float f2 = pointF.x;
            PointF pointF2 = this.endTouch;
            float f3 = f2 + ((pointF2.x - f2) * f);
            float f4 = pointF.y;
            float f5 = f4 + (f * (pointF2.y - f4));
            PointF pointFTransformCoordBitmapToTouch = ZoomImageView.this.transformCoordBitmapToTouch(this.bitmapX, this.bitmapY);
            ZoomImageView.this.matrix.postTranslate(f3 - pointFTransformCoordBitmapToTouch.x, f5 - pointFTransformCoordBitmapToTouch.y);
        }

        @Override // java.lang.Runnable
        public void run() {
            float fInterpolate = interpolate();
            ZoomImageView.this.scaleImage(calculateDeltaScale(fInterpolate), this.bitmapX, this.bitmapY, this.stretchImageToSuper);
            translateImageToCenterTouchPosition(fInterpolate);
            ZoomImageView.this.fixScaleTrans();
            ZoomImageView zoomImageView = ZoomImageView.this;
            zoomImageView.setImageMatrix(zoomImageView.matrix);
            OnTouchImageViewListener onTouchImageViewListener = ZoomImageView.this.touchImageViewListener;
            if (onTouchImageViewListener != null) {
                onTouchImageViewListener.onMove();
            }
            if (fInterpolate < 1.0f) {
                ZoomImageView.this.compatPostOnAnimation(this);
            } else {
                ZoomImageView.this.fitImageToView();
                ZoomImageView.this.setState(State.NONE);
            }
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private GestureListener() {
        }
    }

    public interface OnTouchImageViewListener {
        void onMove();
    }

    private class PrivateOnTouchListener implements View.OnTouchListener {
        private PointF last;

        private PrivateOnTouchListener() {
            this.last = new PointF();
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            ZoomImageView.this.mScaleDetector.onTouchEvent(motionEvent);
            ZoomImageView.this.mGestureDetector.onTouchEvent(motionEvent);
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
            State state = ZoomImageView.this.state;
            State state2 = State.NONE;
            if (state == state2 || state == State.DRAG || state == State.ZOOM) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.last.set(pointF);
                    ZoomImageView.this.setState(State.DRAG);
                } else if (action == 1) {
                    ZoomImageView zoomImageView = ZoomImageView.this;
                    float f = zoomImageView.normalizedScale;
                    float f2 = zoomImageView.minScale;
                    if (f > f2) {
                        ZoomImageView.this.compatPostOnAnimation(zoomImageView.new DoubleTapZoom(f2, motionEvent.getX(), motionEvent.getY(), false));
                    }
                } else if (action == 2) {
                    ZoomImageView zoomImageView2 = ZoomImageView.this;
                    State state3 = zoomImageView2.state;
                    if ((state3 == State.DRAG || state3 == State.ZOOM) && zoomImageView2.normalizedScale > zoomImageView2.minScale) {
                        float f3 = pointF.x;
                        PointF pointF2 = this.last;
                        float f4 = f3 - pointF2.x;
                        float f5 = pointF.y - pointF2.y;
                        float fixDragTrans = zoomImageView2.getFixDragTrans(f4, zoomImageView2.viewWidth, zoomImageView2.getImageWidth());
                        ZoomImageView zoomImageView3 = ZoomImageView.this;
                        ZoomImageView.this.matrix.postTranslate(fixDragTrans, zoomImageView3.getFixDragTrans(f5, zoomImageView3.viewHeight, zoomImageView3.getImageHeight()));
                        ZoomImageView.this.fixTrans();
                        this.last.set(pointF.x, pointF.y);
                    }
                } else if (action == 6) {
                    ZoomImageView.this.setState(state2);
                }
            }
            ZoomImageView zoomImageView4 = ZoomImageView.this;
            zoomImageView4.setImageMatrix(zoomImageView4.matrix);
            View.OnTouchListener onTouchListener = ZoomImageView.this.userTouchListener;
            if (onTouchListener != null) {
                onTouchListener.onTouch(view, motionEvent);
            }
            OnTouchImageViewListener onTouchImageViewListener = ZoomImageView.this.touchImageViewListener;
            if (onTouchImageViewListener != null) {
                onTouchImageViewListener.onMove();
            }
            return true;
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            ZoomImageView.this.scaleImage(scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), false);
            OnTouchImageViewListener onTouchImageViewListener = ZoomImageView.this.touchImageViewListener;
            if (onTouchImageViewListener == null) {
                return true;
            }
            onTouchImageViewListener.onMove();
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            ZoomImageView.this.setState(State.ZOOM);
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0021 A[PHI: r0
          0x0021: PHI (r0v2 float) = (r0v1 float), (r0v8 float) binds: [B:3:0x0016, B:6:0x001f] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onScaleEnd(android.view.ScaleGestureDetector r8) {
            /*
                r7 = this;
                super.onScaleEnd(r8)
                com.onfido.android.sdk.capture.ui.ZoomImageView r8 = com.onfido.android.sdk.capture.ui.ZoomImageView.this
                com.onfido.android.sdk.capture.ui.ZoomImageView$State r0 = com.onfido.android.sdk.capture.ui.ZoomImageView.State.NONE
                com.onfido.android.sdk.capture.ui.ZoomImageView.m5652$$Nest$msetState(r8, r0)
                com.onfido.android.sdk.capture.ui.ZoomImageView r2 = com.onfido.android.sdk.capture.ui.ZoomImageView.this
                float r8 = com.onfido.android.sdk.capture.ui.ZoomImageView.m5638$$Nest$fgetnormalizedScale(r2)
                float r0 = com.onfido.android.sdk.capture.ui.ZoomImageView.m5636$$Nest$fgetmaxScale(r2)
                int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
                if (r1 <= 0) goto L19
                goto L21
            L19:
                float r0 = com.onfido.android.sdk.capture.ui.ZoomImageView.m5637$$Nest$fgetminScale(r2)
                int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
                if (r1 >= 0) goto L24
            L21:
                r8 = 1
                r3 = r0
                goto L27
            L24:
                r0 = 0
                r3 = r8
                r8 = r0
            L27:
                if (r8 == 0) goto L43
                com.onfido.android.sdk.capture.ui.ZoomImageView$DoubleTapZoom r8 = new com.onfido.android.sdk.capture.ui.ZoomImageView$DoubleTapZoom
                int r0 = com.onfido.android.sdk.capture.ui.ZoomImageView.m5643$$Nest$fgetviewWidth(r2)
                int r0 = r0 / 2
                float r4 = (float) r0
                int r0 = com.onfido.android.sdk.capture.ui.ZoomImageView.m5642$$Nest$fgetviewHeight(r2)
                int r0 = r0 / 2
                float r5 = (float) r0
                r6 = 0
                r1 = r8
                r1.<init>(r3, r4, r5, r6)
                com.onfido.android.sdk.capture.ui.ZoomImageView r0 = com.onfido.android.sdk.capture.ui.ZoomImageView.this
                com.onfido.android.sdk.capture.ui.ZoomImageView.m5644$$Nest$mcompatPostOnAnimation(r0, r8)
            L43:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.ZoomImageView.ScaleListener.onScaleEnd(android.view.ScaleGestureDetector):void");
        }
    }

    private enum State {
        NONE,
        DRAG,
        ZOOM,
        FLING,
        ANIMATE_ZOOM
    }

    private class ZoomVariables {
        public float focusX;
        public float focusY;
        public float scale;
        public ImageView.ScaleType scaleType;

        public ZoomVariables(float f, float f2, float f3, ImageView.ScaleType scaleType) {
            this.scale = f;
            this.focusX = f2;
            this.focusY = f3;
            this.scaleType = scaleType;
        }
    }

    public ZoomImageView(Context context) {
        super(context);
        this.userTouchListener = null;
        this.touchImageViewListener = null;
        sharedConstructing(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void compatPostOnAnimation(Runnable runnable) {
        postOnAnimation(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0095  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void fitImageToView() {
        /*
            Method dump skipped, instructions count: 247
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.ZoomImageView.fitImageToView():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fixScaleTrans() {
        fixTrans();
        this.matrix.getValues(this.m);
        float imageWidth = getImageWidth();
        float f = this.viewWidth;
        if (imageWidth < f) {
            this.m[2] = (f - getImageWidth()) / 2.0f;
        }
        float imageHeight = getImageHeight();
        float f2 = this.viewHeight;
        if (imageHeight < f2) {
            this.m[5] = (f2 - getImageHeight()) / 2.0f;
        }
        this.matrix.setValues(this.m);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fixTrans() {
        this.matrix.getValues(this.m);
        float[] fArr = this.m;
        float f = fArr[2];
        float f2 = fArr[5];
        float fixTrans = getFixTrans(f, this.viewWidth, getImageWidth());
        float fixTrans2 = getFixTrans(f2, this.viewHeight, getImageHeight());
        if (fixTrans == 0.0f && fixTrans2 == 0.0f) {
            return;
        }
        this.matrix.postTranslate(fixTrans, fixTrans2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFixDragTrans(float f, float f2, float f3) {
        if (f3 <= f2) {
            return 0.0f;
        }
        return f;
    }

    private float getFixTrans(float f, float f2, float f3) {
        float f4;
        float f5 = f2 - f3;
        if (f3 <= f2) {
            f4 = f5;
            f5 = 0.0f;
        } else {
            f4 = 0.0f;
        }
        if (f < f5) {
            return (-f) + f5;
        }
        if (f > f4) {
            return (-f) + f4;
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getImageHeight() {
        return this.matchViewHeight * this.normalizedScale;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getImageWidth() {
        return this.matchViewWidth * this.normalizedScale;
    }

    private void savePreviousImageValues() {
        Matrix matrix = this.matrix;
        if (matrix == null || this.viewHeight == 0 || this.viewWidth == 0) {
            return;
        }
        matrix.getValues(this.m);
        this.prevMatrix.setValues(this.m);
        this.prevMatchViewHeight = this.matchViewHeight;
        this.prevMatchViewWidth = this.matchViewWidth;
        this.prevViewHeight = this.viewHeight;
        this.prevViewWidth = this.viewWidth;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scaleImage(double d, float f, float f2, boolean z) {
        float f3;
        float f4;
        if (z) {
            f3 = this.superMinScale;
            f4 = this.superMaxScale;
        } else {
            f3 = this.minScale;
            f4 = this.maxScale;
        }
        float f5 = this.normalizedScale;
        float f6 = (float) (f5 * d);
        this.normalizedScale = f6;
        if (f6 > f4) {
            this.normalizedScale = f4;
            d = f4 / f5;
        } else if (f6 < f3) {
            this.normalizedScale = f3;
            d = f3 / f5;
        }
        float f7 = (float) d;
        this.matrix.postScale(f7, f7, f, f2);
        fixScaleTrans();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(State state) {
        this.state = state;
    }

    private int setViewSize(int i, int i2, int i3) {
        return i != Integer.MIN_VALUE ? i != 0 ? i2 : i3 : Math.min(i3, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void sharedConstructing(Context context) {
        super.setClickable(true);
        Object[] objArr = 0;
        this.mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        this.mGestureDetector = new GestureDetector(context, new GestureListener());
        this.matrix = new Matrix();
        this.prevMatrix = new Matrix();
        this.m = new float[9];
        this.normalizedScale = 1.0f;
        if (this.mScaleType == null) {
            this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        }
        this.minScale = 1.0f;
        this.maxScale = 3.0f;
        this.superMinScale = 0.75f;
        this.superMaxScale = 3.75f;
        setImageMatrix(this.matrix);
        setScaleType(ImageView.ScaleType.MATRIX);
        setState(State.NONE);
        this.onDrawReady = false;
        super.setOnTouchListener(new PrivateOnTouchListener());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PointF transformCoordBitmapToTouch(float f, float f2) {
        if (getDrawable() == null) {
            return new PointF(-1.0f, -1.0f);
        }
        this.matrix.getValues(this.m);
        return new PointF(this.m[2] + (getImageWidth() * (f / r0.getIntrinsicWidth())), this.m[5] + (getImageHeight() * (f2 / r0.getIntrinsicHeight())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PointF transformCoordTouchToBitmap(float f, float f2, boolean z) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return new PointF(-1.0f, -1.0f);
        }
        this.matrix.getValues(this.m);
        float intrinsicWidth = drawable.getIntrinsicWidth();
        float intrinsicHeight = drawable.getIntrinsicHeight();
        float[] fArr = this.m;
        float f3 = fArr[2];
        float f4 = fArr[5];
        float imageWidth = ((f - f3) * intrinsicWidth) / getImageWidth();
        float imageHeight = ((f2 - f4) * intrinsicHeight) / getImageHeight();
        if (z) {
            imageWidth = Math.min(Math.max(imageWidth, 0.0f), intrinsicWidth);
            imageHeight = Math.min(Math.max(imageHeight, 0.0f), intrinsicHeight);
        }
        return new PointF(imageWidth, imageHeight);
    }

    private void translateMatrixAfterRotate(int i, float f, float f2, float f3, int i2, int i3, int i4) {
        float f4 = i3;
        if (f3 < f4) {
            float[] fArr = this.m;
            fArr[i] = (f4 - (i4 * fArr[0])) * 0.5f;
        } else if (f > 0.0f) {
            this.m[i] = -((f3 - f4) * 0.5f);
        } else {
            this.m[i] = -((((Math.abs(f) + (i2 * 0.5f)) / f2) * f3) - (f4 * 0.5f));
        }
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i) {
        this.matrix.getValues(this.m);
        float f = this.m[2];
        if (getImageWidth() < this.viewWidth) {
            return false;
        }
        if (f < -1.0f || i >= 0) {
            return (Math.abs(f) + ((float) this.viewWidth)) + 1.0f < getImageWidth() || i <= 0;
        }
        return false;
    }

    public float getCurrentZoom() {
        return this.normalizedScale;
    }

    public float getMaxZoom() {
        return this.maxScale;
    }

    public float getMinZoom() {
        return this.minScale;
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    public PointF getScrollPosition() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        PointF pointFTransformCoordTouchToBitmap = transformCoordTouchToBitmap(this.viewWidth / 2, this.viewHeight / 2, true);
        pointFTransformCoordTouchToBitmap.x /= intrinsicWidth;
        pointFTransformCoordTouchToBitmap.y /= intrinsicHeight;
        return pointFTransformCoordTouchToBitmap;
    }

    public RectF getZoomedRect() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        }
        if (this.mScaleType == ImageView.ScaleType.FIT_XY) {
            throw new UnsupportedOperationException("getZoomedRect() not supported with FIT_XY");
        }
        PointF pointFTransformCoordTouchToBitmap = transformCoordTouchToBitmap(0.0f, 0.0f, true);
        PointF pointFTransformCoordTouchToBitmap2 = transformCoordTouchToBitmap(this.viewWidth, this.viewHeight, true);
        float intrinsicWidth = drawable.getIntrinsicWidth();
        float intrinsicHeight = drawable.getIntrinsicHeight();
        return new RectF(pointFTransformCoordTouchToBitmap.x / intrinsicWidth, pointFTransformCoordTouchToBitmap.y / intrinsicHeight, pointFTransformCoordTouchToBitmap2.x / intrinsicWidth, pointFTransformCoordTouchToBitmap2.y / intrinsicHeight);
    }

    public boolean isZoomed() {
        return this.normalizedScale != 1.0f;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        savePreviousImageValues();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        this.onDrawReady = true;
        this.imageRenderedAtLeastOnce = true;
        ZoomVariables zoomVariables = this.delayedZoomVariables;
        if (zoomVariables != null) {
            setZoom(zoomVariables.scale, zoomVariables.focusX, zoomVariables.focusY, zoomVariables.scaleType);
            this.delayedZoomVariables = null;
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
            setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.viewWidth = setViewSize(mode, size, intrinsicWidth);
        int viewSize = setViewSize(mode2, size2, intrinsicHeight);
        this.viewHeight = viewSize;
        setMeasuredDimension(this.viewWidth, viewSize);
        fitImageToView();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        this.normalizedScale = bundle.getFloat("saveScale");
        float[] floatArray = bundle.getFloatArray("matrix");
        this.m = floatArray;
        this.prevMatrix.setValues(floatArray);
        this.prevMatchViewHeight = bundle.getFloat("matchViewHeight");
        this.prevMatchViewWidth = bundle.getFloat("matchViewWidth");
        this.prevViewHeight = bundle.getInt("viewHeight");
        this.prevViewWidth = bundle.getInt("viewWidth");
        this.imageRenderedAtLeastOnce = bundle.getBoolean("imageRendered");
        super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putFloat("saveScale", this.normalizedScale);
        bundle.putFloat("matchViewHeight", this.matchViewHeight);
        bundle.putFloat("matchViewWidth", this.matchViewWidth);
        bundle.putInt("viewWidth", this.viewWidth);
        bundle.putInt("viewHeight", this.viewHeight);
        this.matrix.getValues(this.m);
        bundle.putFloatArray("matrix", this.m);
        bundle.putBoolean("imageRendered", this.imageRenderedAtLeastOnce);
        return bundle;
    }

    public void resetZoom() {
        this.normalizedScale = 1.0f;
        fitImageToView();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        savePreviousImageValues();
        fitImageToView();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        savePreviousImageValues();
        fitImageToView();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        savePreviousImageValues();
        fitImageToView();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        savePreviousImageValues();
        fitImageToView();
    }

    public void setMaxZoom(float f) {
        this.maxScale = f;
        this.superMaxScale = f * SUPER_MAX_MULTIPLIER;
    }

    public void setMinZoom(float f) {
        this.minScale = f;
        this.superMinScale = f * 0.75f;
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.userTouchListener = onTouchListener;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == ImageView.ScaleType.FIT_START || scaleType == ImageView.ScaleType.FIT_END) {
            throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
        }
        ImageView.ScaleType scaleType2 = ImageView.ScaleType.MATRIX;
        if (scaleType == scaleType2) {
            super.setScaleType(scaleType2);
            return;
        }
        this.mScaleType = scaleType;
        if (this.onDrawReady) {
            setZoom(this);
        }
    }

    public void setScrollPosition(float f, float f2) {
        setZoom(this.normalizedScale, f, f2);
    }

    public void setZoom(float f) {
        setZoom(f, 0.5f, 0.5f);
    }

    public ZoomImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.userTouchListener = null;
        this.touchImageViewListener = null;
        sharedConstructing(context);
    }

    public void setZoom(float f, float f2, float f3) {
        setZoom(f, f2, f3, this.mScaleType);
    }

    public ZoomImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.userTouchListener = null;
        this.touchImageViewListener = null;
        sharedConstructing(context);
    }

    public void setZoom(float f, float f2, float f3, ImageView.ScaleType scaleType) {
        if (!this.onDrawReady) {
            this.delayedZoomVariables = new ZoomVariables(f, f2, f3, scaleType);
            return;
        }
        if (scaleType != this.mScaleType) {
            setScaleType(scaleType);
        }
        resetZoom();
        scaleImage(f, this.viewWidth / 2, this.viewHeight / 2, false);
        this.matrix.getValues(this.m);
        this.m[2] = -((f2 * getImageWidth()) - (this.viewWidth * 0.5f));
        this.m[5] = -((f3 * getImageHeight()) - (this.viewHeight * 0.5f));
        this.matrix.setValues(this.m);
        fixTrans();
        setImageMatrix(this.matrix);
    }

    public void setZoom(ZoomImageView zoomImageView) {
        PointF scrollPosition = zoomImageView.getScrollPosition();
        setZoom(zoomImageView.getCurrentZoom(), scrollPosition.x, scrollPosition.y, zoomImageView.getScaleType());
    }
}
