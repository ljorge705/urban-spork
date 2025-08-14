package org.tensorflow.lite;

import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import org.tensorflow.lite.Tensor;

/* loaded from: classes7.dex */
final class TensorImpl implements Tensor {
    private final DataType dtype;
    private long nativeHandle;
    private final Tensor.QuantizationParams quantizationParamsCopy;
    private int[] shapeCopy;
    private final int[] shapeSignatureCopy;

    private static native ByteBuffer buffer(long handle);

    private static native long create(long interpreterHandle, int tensorIndex, int subgraphIndex);

    private static native long createSignatureInputTensor(long signatureRunnerHandle, String inputName);

    private static native long createSignatureOutputTensor(long signatureRunnerHandle, String outputName);

    private static native void delete(long handle);

    private static native int dtype(long handle);

    private static native boolean hasDelegateBufferHandle(long handle);

    private static native int index(long handle);

    private static native String name(long handle);

    private static native int numBytes(long handle);

    private static native float quantizationScale(long handle);

    private static native int quantizationZeroPoint(long handle);

    private static native void readMultiDimensionalArray(long handle, Object dst);

    private static native int[] shape(long handle);

    private static native int[] shapeSignature(long handle);

    private static native void writeDirectBuffer(long handle, Buffer src);

    private static native void writeMultiDimensionalArray(long handle, Object src);

    private static native void writeScalar(long handle, Object src);

    @Override // org.tensorflow.lite.Tensor
    public DataType dataType() {
        return this.dtype;
    }

    @Override // org.tensorflow.lite.Tensor
    public Tensor.QuantizationParams quantizationParams() {
        return this.quantizationParamsCopy;
    }

    @Override // org.tensorflow.lite.Tensor
    public int[] shape() {
        return this.shapeCopy;
    }

    @Override // org.tensorflow.lite.Tensor
    public int[] shapeSignature() {
        return this.shapeSignatureCopy;
    }

    static TensorImpl fromIndex(long nativeInterpreterHandle, int tensorIndex) {
        return new TensorImpl(create(nativeInterpreterHandle, tensorIndex, 0));
    }

    static TensorImpl fromSignatureInput(long signatureRunnerHandle, String inputName) {
        long jCreateSignatureInputTensor = createSignatureInputTensor(signatureRunnerHandle, inputName);
        if (jCreateSignatureInputTensor == -1) {
            throw new IllegalArgumentException("Input error: input " + inputName + " not found.");
        }
        return new TensorImpl(jCreateSignatureInputTensor);
    }

    static TensorImpl fromSignatureOutput(long signatureRunnerHandle, String outputName) {
        long jCreateSignatureOutputTensor = createSignatureOutputTensor(signatureRunnerHandle, outputName);
        if (jCreateSignatureOutputTensor == -1) {
            throw new IllegalArgumentException("Input error: output " + outputName + " not found.");
        }
        return new TensorImpl(jCreateSignatureOutputTensor);
    }

    void close() {
        delete(this.nativeHandle);
        this.nativeHandle = 0L;
    }

    @Override // org.tensorflow.lite.Tensor
    public int numDimensions() {
        return this.shapeCopy.length;
    }

    @Override // org.tensorflow.lite.Tensor
    public int numBytes() {
        return numBytes(this.nativeHandle);
    }

    @Override // org.tensorflow.lite.Tensor
    public int numElements() {
        return computeNumElements(this.shapeCopy);
    }

    @Override // org.tensorflow.lite.Tensor
    public int index() {
        return index(this.nativeHandle);
    }

    @Override // org.tensorflow.lite.Tensor
    public String name() {
        return name(this.nativeHandle);
    }

    @Override // org.tensorflow.lite.Tensor
    public ByteBuffer asReadOnlyBuffer() {
        return buffer().asReadOnlyBuffer().order(ByteOrder.nativeOrder());
    }

    void setTo(Object src) {
        if (src == null) {
            if (!hasDelegateBufferHandle(this.nativeHandle)) {
                throw new IllegalArgumentException("Null inputs are allowed only if the Tensor is bound to a buffer handle.");
            }
            return;
        }
        throwIfTypeIsIncompatible(src);
        throwIfSrcShapeIsIncompatible(src);
        if (isBuffer(src)) {
            setTo((Buffer) src);
            return;
        }
        if (this.dtype == DataType.STRING && this.shapeCopy.length == 0) {
            writeScalar(this.nativeHandle, src);
        } else if (src.getClass().isArray()) {
            writeMultiDimensionalArray(this.nativeHandle, src);
        } else {
            writeScalar(this.nativeHandle, src);
        }
    }

    private void setTo(Buffer src) {
        if (src instanceof ByteBuffer) {
            ByteBuffer byteBuffer = (ByteBuffer) src;
            if (byteBuffer.isDirect() && byteBuffer.order() == ByteOrder.nativeOrder()) {
                writeDirectBuffer(this.nativeHandle, src);
                return;
            } else {
                buffer().put(byteBuffer);
                return;
            }
        }
        if (src instanceof LongBuffer) {
            LongBuffer longBuffer = (LongBuffer) src;
            if (longBuffer.isDirect() && longBuffer.order() == ByteOrder.nativeOrder()) {
                writeDirectBuffer(this.nativeHandle, src);
                return;
            } else {
                buffer().asLongBuffer().put(longBuffer);
                return;
            }
        }
        if (src instanceof FloatBuffer) {
            FloatBuffer floatBuffer = (FloatBuffer) src;
            if (floatBuffer.isDirect() && floatBuffer.order() == ByteOrder.nativeOrder()) {
                writeDirectBuffer(this.nativeHandle, src);
                return;
            } else {
                buffer().asFloatBuffer().put(floatBuffer);
                return;
            }
        }
        if (src instanceof IntBuffer) {
            IntBuffer intBuffer = (IntBuffer) src;
            if (intBuffer.isDirect() && intBuffer.order() == ByteOrder.nativeOrder()) {
                writeDirectBuffer(this.nativeHandle, src);
                return;
            } else {
                buffer().asIntBuffer().put(intBuffer);
                return;
            }
        }
        if (src instanceof ShortBuffer) {
            ShortBuffer shortBuffer = (ShortBuffer) src;
            if (shortBuffer.isDirect() && shortBuffer.order() == ByteOrder.nativeOrder()) {
                writeDirectBuffer(this.nativeHandle, src);
                return;
            } else {
                buffer().asShortBuffer().put(shortBuffer);
                return;
            }
        }
        throw new IllegalArgumentException("Unexpected input buffer type: " + src);
    }

    void copyTo(Object dst) {
        if (dst == null) {
            if (!hasDelegateBufferHandle(this.nativeHandle)) {
                throw new IllegalArgumentException("Null outputs are allowed only if the Tensor is bound to a buffer handle.");
            }
            return;
        }
        throwIfTypeIsIncompatible(dst);
        throwIfDstShapeIsIncompatible(dst);
        if (isBuffer(dst)) {
            copyTo((Buffer) dst);
        } else {
            readMultiDimensionalArray(this.nativeHandle, dst);
        }
    }

    private void copyTo(Buffer dst) {
        if (dst instanceof ByteBuffer) {
            ((ByteBuffer) dst).put(buffer());
            return;
        }
        if (dst instanceof FloatBuffer) {
            ((FloatBuffer) dst).put(buffer().asFloatBuffer());
            return;
        }
        if (dst instanceof LongBuffer) {
            ((LongBuffer) dst).put(buffer().asLongBuffer());
        } else if (dst instanceof IntBuffer) {
            ((IntBuffer) dst).put(buffer().asIntBuffer());
        } else {
            if (dst instanceof ShortBuffer) {
                ((ShortBuffer) dst).put(buffer().asShortBuffer());
                return;
            }
            throw new IllegalArgumentException("Unexpected output buffer type: " + dst);
        }
    }

    int[] getInputShapeIfDifferent(Object input) {
        if (input == null || isBuffer(input)) {
            return null;
        }
        throwIfTypeIsIncompatible(input);
        int[] iArrComputeShapeOf = computeShapeOf(input);
        if (Arrays.equals(this.shapeCopy, iArrComputeShapeOf)) {
            return null;
        }
        return iArrComputeShapeOf;
    }

    void refreshShape() {
        this.shapeCopy = shape(this.nativeHandle);
    }

    DataType dataTypeOf(Object o) {
        Class<?> componentType = o.getClass();
        if (componentType.isArray()) {
            while (componentType.isArray()) {
                componentType = componentType.getComponentType();
            }
            if (Float.TYPE.equals(componentType)) {
                return DataType.FLOAT32;
            }
            if (Integer.TYPE.equals(componentType)) {
                return DataType.INT32;
            }
            if (Short.TYPE.equals(componentType)) {
                return DataType.INT16;
            }
            if (Byte.TYPE.equals(componentType)) {
                if (this.dtype == DataType.STRING) {
                    return DataType.STRING;
                }
                return DataType.UINT8;
            }
            if (Long.TYPE.equals(componentType)) {
                return DataType.INT64;
            }
            if (Boolean.TYPE.equals(componentType)) {
                return DataType.BOOL;
            }
            if (String.class.equals(componentType)) {
                return DataType.STRING;
            }
        } else {
            if (Float.class.equals(componentType) || (o instanceof FloatBuffer)) {
                return DataType.FLOAT32;
            }
            if (Integer.class.equals(componentType) || (o instanceof IntBuffer)) {
                return DataType.INT32;
            }
            if (Short.class.equals(componentType) || (o instanceof ShortBuffer)) {
                return DataType.INT16;
            }
            if (Byte.class.equals(componentType)) {
                return DataType.UINT8;
            }
            if (Long.class.equals(componentType) || (o instanceof LongBuffer)) {
                return DataType.INT64;
            }
            if (Boolean.class.equals(componentType)) {
                return DataType.BOOL;
            }
            if (String.class.equals(componentType)) {
                return DataType.STRING;
            }
        }
        throw new IllegalArgumentException("DataType error: cannot resolve DataType of " + o.getClass().getName());
    }

    private int[] computeShapeOf(Object o) {
        int iComputeNumDimensions = computeNumDimensions(o);
        if (this.dtype == DataType.STRING) {
            Class<?> componentType = o.getClass();
            if (componentType.isArray()) {
                while (componentType.isArray()) {
                    componentType = componentType.getComponentType();
                }
                if (Byte.TYPE.equals(componentType)) {
                    iComputeNumDimensions--;
                }
            }
        }
        int[] iArr = new int[iComputeNumDimensions];
        fillShape(o, 0, iArr);
        return iArr;
    }

    static int computeNumElements(int[] shape) {
        int i = 1;
        for (int i2 : shape) {
            i *= i2;
        }
        return i;
    }

    static int computeNumDimensions(Object o) {
        if (o == null || !o.getClass().isArray()) {
            return 0;
        }
        if (Array.getLength(o) == 0) {
            throw new IllegalArgumentException("Array lengths cannot be 0.");
        }
        return computeNumDimensions(Array.get(o, 0)) + 1;
    }

    static void fillShape(Object o, int dim, int[] shape) {
        if (shape == null || dim == shape.length) {
            return;
        }
        int length = Array.getLength(o);
        int i = shape[dim];
        if (i == 0) {
            shape[dim] = length;
        } else if (i != length) {
            throw new IllegalArgumentException(String.format("Mismatched lengths (%d and %d) in dimension %d", Integer.valueOf(shape[dim]), Integer.valueOf(length), Integer.valueOf(dim)));
        }
        int i2 = dim + 1;
        if (i2 == shape.length) {
            return;
        }
        for (int i3 = 0; i3 < length; i3++) {
            fillShape(Array.get(o, i3), i2, shape);
        }
    }

    private void throwIfTypeIsIncompatible(Object o) {
        DataType dataTypeDataTypeOf;
        if (!isByteBuffer(o) && (dataTypeDataTypeOf = dataTypeOf(o)) != this.dtype && !DataTypeUtils.toStringName(dataTypeDataTypeOf).equals(DataTypeUtils.toStringName(this.dtype))) {
            throw new IllegalArgumentException(String.format("Cannot convert between a TensorFlowLite tensor with type %s and a Java object of type %s (which is compatible with the TensorFlowLite type %s).", this.dtype, o.getClass().getName(), dataTypeDataTypeOf));
        }
    }

    private void throwIfSrcShapeIsIncompatible(Object src) {
        if (isBuffer(src)) {
            Buffer buffer = (Buffer) src;
            int iNumBytes = numBytes();
            int iCapacity = isByteBuffer(src) ? buffer.capacity() : buffer.capacity() * this.dtype.byteSize();
            if (iNumBytes != iCapacity) {
                throw new IllegalArgumentException(String.format("Cannot copy to a TensorFlowLite tensor (%s) with %d bytes from a Java Buffer with %d bytes.", name(), Integer.valueOf(iNumBytes), Integer.valueOf(iCapacity)));
            }
            return;
        }
        int[] iArrComputeShapeOf = computeShapeOf(src);
        if (!Arrays.equals(iArrComputeShapeOf, this.shapeCopy)) {
            throw new IllegalArgumentException(String.format("Cannot copy to a TensorFlowLite tensor (%s) with shape %s from a Java object with shape %s.", name(), Arrays.toString(this.shapeCopy), Arrays.toString(iArrComputeShapeOf)));
        }
    }

    private void throwIfDstShapeIsIncompatible(Object dst) {
        if (isBuffer(dst)) {
            Buffer buffer = (Buffer) dst;
            int iNumBytes = numBytes();
            int iCapacity = isByteBuffer(dst) ? buffer.capacity() : buffer.capacity() * this.dtype.byteSize();
            if (iNumBytes > iCapacity) {
                throw new IllegalArgumentException(String.format("Cannot copy from a TensorFlowLite tensor (%s) with %d bytes to a Java Buffer with %d bytes.", name(), Integer.valueOf(iNumBytes), Integer.valueOf(iCapacity)));
            }
            return;
        }
        int[] iArrComputeShapeOf = computeShapeOf(dst);
        if (!Arrays.equals(iArrComputeShapeOf, this.shapeCopy)) {
            throw new IllegalArgumentException(String.format("Cannot copy from a TensorFlowLite tensor (%s) with shape %s to a Java object with shape %s.", name(), Arrays.toString(this.shapeCopy), Arrays.toString(iArrComputeShapeOf)));
        }
    }

    private static boolean isBuffer(Object o) {
        return o instanceof Buffer;
    }

    private static boolean isByteBuffer(Object o) {
        return o instanceof ByteBuffer;
    }

    private TensorImpl(long nativeHandle) {
        this.nativeHandle = nativeHandle;
        this.dtype = DataTypeUtils.fromC(dtype(nativeHandle));
        this.shapeCopy = shape(nativeHandle);
        this.shapeSignatureCopy = shapeSignature(nativeHandle);
        this.quantizationParamsCopy = new Tensor.QuantizationParams(quantizationScale(nativeHandle), quantizationZeroPoint(nativeHandle));
    }

    private ByteBuffer buffer() {
        return buffer(this.nativeHandle).order(ByteOrder.nativeOrder());
    }
}
