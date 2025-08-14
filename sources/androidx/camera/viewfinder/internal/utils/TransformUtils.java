package androidx.camera.viewfinder.internal.utils;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Size;
import android.view.Display;
import androidx.camera.viewfinder.internal.transform.TransformationInfo;

/* loaded from: classes.dex */
public class TransformUtils {
    public static final RectF NORMALIZED_RECT = new RectF(-1.0f, -1.0f, 1.0f, 1.0f);

    private TransformUtils() {
    }

    public static int surfaceRotationToRotationDegrees(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 90;
        }
        if (i == 2) {
            return 180;
        }
        if (i == 3) {
            return 270;
        }
        throw new IllegalStateException("Unexpected rotation value " + i);
    }

    public static boolean is90or270(int i) {
        if (i == 90 || i == 270) {
            return true;
        }
        if (i == 0 || i == 180) {
            return false;
        }
        throw new IllegalArgumentException("Invalid rotation degrees: " + i);
    }

    public static float[] sizeToVertices(Size size) {
        return new float[]{0.0f, 0.0f, size.getWidth(), 0.0f, size.getWidth(), size.getHeight(), 0.0f, size.getHeight()};
    }

    public static boolean isAspectRatioMatchingWithRoundingError(Size size, boolean z, Size size2, boolean z2) {
        float width;
        float width2;
        float width3;
        float width4;
        if (z) {
            width = size.getWidth() / size.getHeight();
            width2 = width;
        } else {
            width = (size.getWidth() + 1.0f) / (size.getHeight() - 1.0f);
            width2 = (size.getWidth() - 1.0f) / (size.getHeight() + 1.0f);
        }
        if (z2) {
            width3 = size2.getWidth() / size2.getHeight();
            width4 = width3;
        } else {
            width3 = (size2.getWidth() - 1.0f) / (size2.getHeight() + 1.0f);
            width4 = (size2.getWidth() + 1.0f) / (size2.getHeight() - 1.0f);
        }
        return width >= width3 && width4 >= width2;
    }

    public static Matrix getRectToRect(RectF rectF, RectF rectF2, int i) {
        Matrix matrix = new Matrix();
        matrix.setRectToRect(rectF, NORMALIZED_RECT, Matrix.ScaleToFit.FILL);
        matrix.postRotate(i);
        matrix.postConcat(getNormalizedToBuffer(rectF2));
        return matrix;
    }

    public static TransformationInfo createTransformInfo(Size size, Display display, boolean z, int i) {
        return TransformationInfo.of(new Rect(0, 0, size.getWidth(), size.getHeight()), CameraOrientationUtil.getRelativeImageRotation(CameraOrientationUtil.surfaceRotationToDegrees(display.getRotation()), i, !z), display.getRotation());
    }

    private static Matrix getNormalizedToBuffer(RectF rectF) {
        Matrix matrix = new Matrix();
        matrix.setRectToRect(NORMALIZED_RECT, rectF, Matrix.ScaleToFit.FILL);
        return matrix;
    }
}
