package org.tensorflow.lite.support.image;

import android.graphics.PointF;
import org.tensorflow.lite.support.common.Operator;

/* loaded from: classes7.dex */
public interface ImageOperator extends Operator<TensorImage> {
    @Override // org.tensorflow.lite.support.common.Operator
    TensorImage apply(TensorImage image);

    int getOutputImageHeight(int inputImageHeight, int inputImageWidth);

    int getOutputImageWidth(int inputImageHeight, int inputImageWidth);

    PointF inverseTransform(PointF point, int inputImageHeight, int inputImageWidth);
}
