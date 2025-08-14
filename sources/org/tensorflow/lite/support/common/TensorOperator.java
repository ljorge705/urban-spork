package org.tensorflow.lite.support.common;

import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes7.dex */
public interface TensorOperator extends Operator<TensorBuffer> {
    @Override // org.tensorflow.lite.support.common.Operator
    TensorBuffer apply(TensorBuffer input);
}
