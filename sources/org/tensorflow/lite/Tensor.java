package org.tensorflow.lite;

import java.nio.ByteBuffer;

/* loaded from: classes7.dex */
public interface Tensor {
    ByteBuffer asReadOnlyBuffer();

    DataType dataType();

    int index();

    String name();

    int numBytes();

    int numDimensions();

    int numElements();

    QuantizationParams quantizationParams();

    int[] shape();

    int[] shapeSignature();

    public static class QuantizationParams {
        private final float scale;
        private final int zeroPoint;

        public float getScale() {
            return this.scale;
        }

        public int getZeroPoint() {
            return this.zeroPoint;
        }

        public QuantizationParams(final float scale, final int zeroPoint) {
            this.scale = scale;
            this.zeroPoint = zeroPoint;
        }
    }
}
