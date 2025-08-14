package androidx.camera.view.transform;

import android.graphics.Matrix;
import android.util.Size;

/* loaded from: classes.dex */
public final class OutputTransform {
    final Matrix mMatrix;
    final Size mViewPortSize;

    public Matrix getMatrix() {
        return this.mMatrix;
    }

    Size getViewPortSize() {
        return this.mViewPortSize;
    }

    public OutputTransform(Matrix matrix, Size size) {
        this.mMatrix = matrix;
        this.mViewPortSize = size;
    }
}
