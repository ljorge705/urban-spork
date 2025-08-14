package androidx.camera.viewfinder;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Size;
import android.view.Display;
import android.view.TextureView;
import android.view.View;
import androidx.camera.viewfinder.CameraViewfinder;
import androidx.camera.viewfinder.internal.transform.TransformationInfo;
import androidx.camera.viewfinder.internal.utils.Logger;
import androidx.camera.viewfinder.internal.utils.TransformUtils;
import androidx.core.util.Preconditions;

/* loaded from: classes.dex */
final class ViewfinderTransformation {
    private static final CameraViewfinder.ScaleType DEFAULT_SCALE_TYPE = CameraViewfinder.ScaleType.FILL_CENTER;
    private static final String TAG = "ViewfinderTransformation";
    private boolean mIsFrontCamera;
    private Size mResolution;
    private CameraViewfinder.ScaleType mScaleType = DEFAULT_SCALE_TYPE;
    private Rect mSurfaceCropRect;
    private int mTargetRotation;
    private int mViewfinderRotationDegrees;

    private boolean isTransformationInfoReady() {
        return (this.mSurfaceCropRect == null || this.mResolution == null) ? false : true;
    }

    CameraViewfinder.ScaleType getScaleType() {
        return this.mScaleType;
    }

    void setScaleType(CameraViewfinder.ScaleType scaleType) {
        this.mScaleType = scaleType;
    }

    ViewfinderTransformation() {
    }

    void setTransformationInfo(TransformationInfo transformationInfo, Size size, boolean z) {
        updateTransformInfo(transformationInfo);
        this.mResolution = size;
        this.mIsFrontCamera = z;
    }

    void updateTransformInfo(TransformationInfo transformationInfo) {
        this.mSurfaceCropRect = transformationInfo.getCropRect();
        this.mViewfinderRotationDegrees = transformationInfo.getRotationDegrees();
        this.mTargetRotation = transformationInfo.getTargetRotation();
    }

    void transformView(Size size, int i, View view) {
        if (size.getHeight() == 0 || size.getWidth() == 0) {
            Logger.w(TAG, "Transform not applied due to Viewfinder size: " + size);
            return;
        }
        if (isTransformationInfoReady()) {
            if (view instanceof TextureView) {
                ((TextureView) view).setTransform(getTextureViewCorrectionMatrix());
            } else {
                Display display = view.getDisplay();
                if (display != null && display.getRotation() != this.mTargetRotation) {
                    Logger.e(TAG, "Non-display rotation not supported with SurfaceView / PERFORMANCE mode.");
                }
            }
            RectF transformedSurfaceRect = getTransformedSurfaceRect(size, i);
            view.setPivotX(0.0f);
            view.setPivotY(0.0f);
            view.setScaleX(transformedSurfaceRect.width() / this.mResolution.getWidth());
            view.setScaleY(transformedSurfaceRect.height() / this.mResolution.getHeight());
            view.setTranslationX(transformedSurfaceRect.left - view.getLeft());
            view.setTranslationY(transformedSurfaceRect.top - view.getTop());
        }
    }

    Bitmap createTransformedBitmap(Bitmap bitmap, Size size, int i) {
        if (!isTransformationInfoReady()) {
            return bitmap;
        }
        Matrix textureViewCorrectionMatrix = getTextureViewCorrectionMatrix();
        RectF transformedSurfaceRect = getTransformedSurfaceRect(size, i);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(size.getWidth(), size.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Matrix matrix = new Matrix();
        matrix.postConcat(textureViewCorrectionMatrix);
        matrix.postScale(transformedSurfaceRect.width() / this.mResolution.getWidth(), transformedSurfaceRect.height() / this.mResolution.getHeight());
        matrix.postTranslate(transformedSurfaceRect.left, transformedSurfaceRect.top);
        canvas.drawBitmap(bitmap, matrix, new Paint(7));
        return bitmapCreateBitmap;
    }

    Matrix getTextureViewCorrectionMatrix() {
        Preconditions.checkState(isTransformationInfoReady());
        RectF rectF = new RectF(0.0f, 0.0f, this.mResolution.getWidth(), this.mResolution.getHeight());
        return TransformUtils.getRectToRect(rectF, rectF, -TransformUtils.surfaceRotationToRotationDegrees(this.mTargetRotation));
    }

    private RectF getTransformedSurfaceRect(Size size, int i) {
        Preconditions.checkState(isTransformationInfoReady());
        Matrix surfaceToViewfinderMatrix = getSurfaceToViewfinderMatrix(size, i);
        RectF rectF = new RectF(0.0f, 0.0f, this.mResolution.getWidth(), this.mResolution.getHeight());
        surfaceToViewfinderMatrix.mapRect(rectF);
        return rectF;
    }

    private Matrix getSurfaceToViewfinderMatrix(Size size, int i) {
        RectF viewfinderViewportRectForMismatchedAspectRatios;
        Preconditions.checkState(isTransformationInfoReady());
        if (isViewportAspectRatioMatchViewfinder(size)) {
            viewfinderViewportRectForMismatchedAspectRatios = new RectF(0.0f, 0.0f, size.getWidth(), size.getHeight());
        } else {
            viewfinderViewportRectForMismatchedAspectRatios = getViewfinderViewportRectForMismatchedAspectRatios(size, i);
        }
        Matrix rectToRect = TransformUtils.getRectToRect(new RectF(this.mSurfaceCropRect), viewfinderViewportRectForMismatchedAspectRatios, this.mViewfinderRotationDegrees);
        if (this.mIsFrontCamera) {
            if (TransformUtils.is90or270(this.mViewfinderRotationDegrees)) {
                rectToRect.preScale(1.0f, -1.0f, this.mSurfaceCropRect.centerX(), this.mSurfaceCropRect.centerY());
            } else {
                rectToRect.preScale(-1.0f, 1.0f, this.mSurfaceCropRect.centerX(), this.mSurfaceCropRect.centerY());
            }
        }
        return rectToRect;
    }

    boolean isViewportAspectRatioMatchViewfinder(Size size) {
        return TransformUtils.isAspectRatioMatchingWithRoundingError(size, true, getRotatedViewportSize(), false);
    }

    private Size getRotatedViewportSize() {
        if (TransformUtils.is90or270(this.mViewfinderRotationDegrees)) {
            return new Size(this.mSurfaceCropRect.height(), this.mSurfaceCropRect.width());
        }
        return new Size(this.mSurfaceCropRect.width(), this.mSurfaceCropRect.height());
    }

    private RectF getViewfinderViewportRectForMismatchedAspectRatios(Size size, int i) {
        RectF rectF = new RectF(0.0f, 0.0f, size.getWidth(), size.getHeight());
        Size rotatedViewportSize = getRotatedViewportSize();
        RectF rectF2 = new RectF(0.0f, 0.0f, rotatedViewportSize.getWidth(), rotatedViewportSize.getHeight());
        Matrix matrix = new Matrix();
        setMatrixRectToRect(matrix, rectF2, rectF, this.mScaleType);
        matrix.mapRect(rectF2);
        return i == 1 ? flipHorizontally(rectF2, size.getWidth() / 2.0f) : rectF2;
    }

    /* renamed from: androidx.camera.viewfinder.ViewfinderTransformation$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$viewfinder$CameraViewfinder$ScaleType;

        static {
            int[] iArr = new int[CameraViewfinder.ScaleType.values().length];
            $SwitchMap$androidx$camera$viewfinder$CameraViewfinder$ScaleType = iArr;
            try {
                iArr[CameraViewfinder.ScaleType.FIT_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$camera$viewfinder$CameraViewfinder$ScaleType[CameraViewfinder.ScaleType.FILL_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$camera$viewfinder$CameraViewfinder$ScaleType[CameraViewfinder.ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$camera$viewfinder$CameraViewfinder$ScaleType[CameraViewfinder.ScaleType.FILL_END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$camera$viewfinder$CameraViewfinder$ScaleType[CameraViewfinder.ScaleType.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$androidx$camera$viewfinder$CameraViewfinder$ScaleType[CameraViewfinder.ScaleType.FILL_START.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private static void setMatrixRectToRect(Matrix matrix, RectF rectF, RectF rectF2, CameraViewfinder.ScaleType scaleType) {
        Matrix.ScaleToFit scaleToFit;
        switch (AnonymousClass1.$SwitchMap$androidx$camera$viewfinder$CameraViewfinder$ScaleType[scaleType.ordinal()]) {
            case 1:
            case 2:
                scaleToFit = Matrix.ScaleToFit.CENTER;
                break;
            case 3:
            case 4:
                scaleToFit = Matrix.ScaleToFit.END;
                break;
            case 5:
            case 6:
                scaleToFit = Matrix.ScaleToFit.START;
                break;
            default:
                Logger.e(TAG, "Unexpected crop rect: " + scaleType);
                scaleToFit = Matrix.ScaleToFit.FILL;
                break;
        }
        if (scaleType == CameraViewfinder.ScaleType.FIT_CENTER || scaleType == CameraViewfinder.ScaleType.FIT_START || scaleType == CameraViewfinder.ScaleType.FIT_END) {
            matrix.setRectToRect(rectF, rectF2, scaleToFit);
        } else {
            matrix.setRectToRect(rectF2, rectF, scaleToFit);
            matrix.invert(matrix);
        }
    }

    private static RectF flipHorizontally(RectF rectF, float f) {
        float f2 = f + f;
        return new RectF(f2 - rectF.right, rectF.top, f2 - rectF.left, rectF.bottom);
    }
}
