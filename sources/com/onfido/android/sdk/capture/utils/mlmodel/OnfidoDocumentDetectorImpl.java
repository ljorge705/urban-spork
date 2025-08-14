package com.onfido.android.sdk.capture.utils.mlmodel;

import android.graphics.Bitmap;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlDocument;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.image.ImageOperator;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001d\u001eB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\nH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0003H\u0002J\b\u0010\u0019\u001a\u00020\bH\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0012\u001a\u00020\u0011H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoDocumentDetectorImpl;", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoDocumentDetector;", "modelFile", "Ljava/io/File;", "threshold", "", "(Ljava/io/File;F)V", "inputShape", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoDocumentDetectorImpl$InputShape;", "interpreter", "Lorg/tensorflow/lite/Interpreter;", "labelMap", "", "", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlDocument$Side;", "createInterpreter", "cropBitmapToSquare", "Landroid/graphics/Bitmap;", "bitmap", "detect", "", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlDocument;", "fileToByteBuffer", "Ljava/nio/ByteBuffer;", "file", "getInputShape", "preprocessImage", "Lorg/tensorflow/lite/support/image/TensorImage;", "process", "Companion", "InputShape", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoDocumentDetectorImpl implements OnfidoDocumentDetector {

    @Deprecated
    public static final int X_MAX_INDEX = 3;

    @Deprecated
    public static final int X_MIN_INDEX = 1;

    @Deprecated
    public static final int Y_MAX_INDEX = 2;

    @Deprecated
    public static final int Y_MIN_INDEX = 0;
    private static final float[][][] outputBoxes;
    private static final Map<Integer, Object> outputMap;
    private final InputShape inputShape;
    private final Interpreter interpreter;
    private final Map<Integer, OnfidoMlDocument.Side> labelMap;
    private final File modelFile;
    private final float threshold;
    private static final Companion Companion = new Companion(null);
    private static final float[][] outputClasses = {new float[25]};
    private static final float[][] outputScores = {new float[25]};
    private static final float[] outputCount = new float[1];

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0002\b\u000b\n\u0002\u0010$\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001f\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\t¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0012\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u001a\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoDocumentDetectorImpl$Companion;", "", "()V", "X_MAX_INDEX", "", "X_MIN_INDEX", "Y_MAX_INDEX", "Y_MIN_INDEX", "outputBoxes", "", "", "getOutputBoxes", "()[[[F", "[[[F", "outputClasses", "getOutputClasses", "()[[F", "[[F", "outputCount", "getOutputCount", "()[F", "outputMap", "", "getOutputMap", "()Ljava/util/Map;", "outputScores", "getOutputScores", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public final float[][][] getOutputBoxes() {
            return OnfidoDocumentDetectorImpl.outputBoxes;
        }

        public final float[][] getOutputClasses() {
            return OnfidoDocumentDetectorImpl.outputClasses;
        }

        public final float[] getOutputCount() {
            return OnfidoDocumentDetectorImpl.outputCount;
        }

        public final Map<Integer, Object> getOutputMap() {
            return OnfidoDocumentDetectorImpl.outputMap;
        }

        public final float[][] getOutputScores() {
            return OnfidoDocumentDetectorImpl.outputScores;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoDocumentDetectorImpl$InputShape;", "", "height", "", "width", "(II)V", "getHeight", "()I", "getWidth", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final /* data */ class InputShape {
        private final int height;
        private final int width;

        public InputShape(int i, int i2) {
            this.height = i;
            this.width = i2;
        }

        public static /* synthetic */ InputShape copy$default(InputShape inputShape, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = inputShape.height;
            }
            if ((i3 & 2) != 0) {
                i2 = inputShape.width;
            }
            return inputShape.copy(i, i2);
        }

        /* renamed from: component1, reason: from getter */
        public final int getHeight() {
            return this.height;
        }

        /* renamed from: component2, reason: from getter */
        public final int getWidth() {
            return this.width;
        }

        public final InputShape copy(int height, int width) {
            return new InputShape(height, width);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InputShape)) {
                return false;
            }
            InputShape inputShape = (InputShape) other;
            return this.height == inputShape.height && this.width == inputShape.width;
        }

        public final int getHeight() {
            return this.height;
        }

        public final int getWidth() {
            return this.width;
        }

        public int hashCode() {
            return (Integer.hashCode(this.height) * 31) + Integer.hashCode(this.width);
        }

        public String toString() {
            return "InputShape(height=" + this.height + ", width=" + this.width + ')';
        }
    }

    static {
        float[][] fArr = new float[25][];
        for (int i = 0; i < 25; i++) {
            fArr[i] = new float[4];
        }
        float[][][] fArr2 = {fArr};
        outputBoxes = fArr2;
        outputMap = MapsKt.mapOf(TuplesKt.to(0, outputScores), TuplesKt.to(1, fArr2), TuplesKt.to(2, outputCount), TuplesKt.to(3, outputClasses));
    }

    public OnfidoDocumentDetectorImpl(File modelFile, float f) {
        Intrinsics.checkNotNullParameter(modelFile, "modelFile");
        this.modelFile = modelFile;
        this.threshold = f;
        this.labelMap = MapsKt.mapOf(TuplesKt.to(0, OnfidoMlDocument.Side.BACK), TuplesKt.to(1, OnfidoMlDocument.Side.FRONT));
        this.interpreter = createInterpreter();
        this.inputShape = getInputShape();
    }

    private final Interpreter createInterpreter() {
        Interpreter interpreter = new Interpreter(fileToByteBuffer(this.modelFile), new Interpreter.Options());
        interpreter.allocateTensors();
        return interpreter;
    }

    private final Bitmap cropBitmapToSquare(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, height > width ? (height - width) / 2 : 0, width, width);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        return bitmapCreateBitmap;
    }

    private final ByteBuffer fileToByteBuffer(File file) throws IOException {
        FileChannel channel = new FileInputStream(file).getChannel();
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect((int) channel.size());
        channel.read(byteBufferAllocateDirect);
        byteBufferAllocateDirect.flip();
        channel.close();
        Intrinsics.checkNotNull(byteBufferAllocateDirect);
        return byteBufferAllocateDirect;
    }

    private final InputShape getInputShape() {
        int[] iArrShape = this.interpreter.getInputTensor(0).shape();
        return new InputShape(iArrShape[1], iArrShape[2]);
    }

    private final TensorImage preprocessImage(Bitmap bitmap) {
        TensorImage tensorImageProcess = new ImageProcessor.Builder().add((ImageOperator) new ResizeOp(this.inputShape.getHeight(), this.inputShape.getWidth(), ResizeOp.ResizeMethod.BILINEAR)).build().process(TensorImage.fromBitmap(cropBitmapToSquare(bitmap)));
        Intrinsics.checkNotNullExpressionValue(tensorImageProcess, "process(...)");
        return tensorImageProcess;
    }

    private final List<OnfidoMlDocument> process(Bitmap bitmap) {
        OnfidoMlDocument onfidoMlDocument;
        this.interpreter.runForMultipleInputsOutputs(new ByteBuffer[]{preprocessImage(bitmap).getBuffer()}, outputMap);
        IntRange intRangeUntil = RangesKt.until(0, (int) outputCount[0]);
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            float f = outputScores[0][iNextInt];
            if (f >= this.threshold) {
                float[] fArr = outputBoxes[0][iNextInt];
                float height = (bitmap.getHeight() - bitmap.getWidth()) / 2;
                float width = (fArr[0] * bitmap.getWidth()) + height;
                float width2 = fArr[1] * bitmap.getWidth();
                float width3 = (fArr[2] * bitmap.getWidth()) + height;
                float width4 = fArr[3] * bitmap.getWidth();
                OnfidoMlDocument.Side side = this.labelMap.get(Integer.valueOf((int) outputClasses[0][iNextInt]));
                if (side == null) {
                    side = OnfidoMlDocument.Side.UNKNOWN;
                }
                onfidoMlDocument = new OnfidoMlDocument(new OnfidoRectF(width2, width, width4, width3), side, f);
            } else {
                onfidoMlDocument = null;
            }
            if (onfidoMlDocument != null) {
                arrayList.add(onfidoMlDocument);
            }
        }
        return arrayList;
    }

    @Override // com.onfido.android.sdk.capture.utils.mlmodel.OnfidoDocumentDetector
    public List<OnfidoMlDocument> detect(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        return process(bitmap);
    }
}
