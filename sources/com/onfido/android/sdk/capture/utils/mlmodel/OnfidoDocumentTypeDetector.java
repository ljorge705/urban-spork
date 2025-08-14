package com.onfido.android.sdk.capture.utils.mlmodel;

import android.graphics.Bitmap;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.image.ImageOperator;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0002\u0013\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoDocumentTypeDetector;", "", "modelFile", "Ljava/io/File;", "(Ljava/io/File;)V", "interpreter", "Lorg/tensorflow/lite/Interpreter;", "createInterpreter", "detect", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoDocumentTypeDetector$Companion$Response;", "bitmap", "Landroid/graphics/Bitmap;", "fileToByteBuffer", "Ljava/nio/ByteBuffer;", "file", "preprocessImage", "Lorg/tensorflow/lite/support/image/TensorImage;", "process", "", "Companion", "InputShape", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoDocumentTypeDetector {
    private static final int CHANNEL = 3;
    private static final InputShape inputShape = new InputShape(300, 300);
    private static final float[][] outputClasses;
    private static final Map<Integer, float[][]> outputMap;
    private static final float[][] outputScores;
    private final Interpreter interpreter;
    private final File modelFile;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoDocumentTypeDetector$InputShape;", "", "height", "", "width", "(II)V", "getHeight", "()I", "getWidth", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
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
        float[][] fArr = {new float[1617]};
        outputClasses = fArr;
        float[][] fArr2 = {new float[1280]};
        outputScores = fArr2;
        outputMap = MapsKt.mapOf(TuplesKt.to(0, fArr2), TuplesKt.to(1, fArr));
    }

    public OnfidoDocumentTypeDetector(File modelFile) {
        Intrinsics.checkNotNullParameter(modelFile, "modelFile");
        this.modelFile = modelFile;
        Interpreter interpreterCreateInterpreter = createInterpreter();
        this.interpreter = interpreterCreateInterpreter;
        InputShape inputShape2 = inputShape;
        interpreterCreateInterpreter.resizeInput(0, new int[]{1, inputShape2.getHeight(), inputShape2.getHeight(), 3});
    }

    private final Interpreter createInterpreter() {
        return new Interpreter(fileToByteBuffer(this.modelFile), new Interpreter.Options());
    }

    private final ByteBuffer fileToByteBuffer(File file) throws IOException {
        FileChannel channel = new FileInputStream(file).getChannel();
        ByteBuffer byteBufferOrder = ByteBuffer.allocateDirect((int) channel.size()).order(ByteOrder.nativeOrder());
        channel.read(byteBufferOrder);
        byteBufferOrder.flip();
        channel.close();
        Intrinsics.checkNotNull(byteBufferOrder);
        return byteBufferOrder;
    }

    private final TensorImage preprocessImage(Bitmap bitmap) {
        TensorImage tensorImage = new TensorImage(this.interpreter.getInputTensor(0).dataType());
        tensorImage.load(bitmap);
        ImageProcessor.Builder builder = new ImageProcessor.Builder();
        InputShape inputShape2 = inputShape;
        TensorImage tensorImageProcess = builder.add((ImageOperator) new ResizeOp(inputShape2.getHeight(), inputShape2.getWidth(), ResizeOp.ResizeMethod.BILINEAR)).build().process(tensorImage);
        Intrinsics.checkNotNullExpressionValue(tensorImageProcess, "process(...)");
        return tensorImageProcess;
    }

    private final String process(Bitmap bitmap) {
        this.interpreter.runForMultipleInputsOutputs(new ByteBuffer[]{preprocessImage(bitmap).getBuffer()}, outputMap);
        Iterator<IndexedValue<Float>> it = ArraysKt.withIndex(outputClasses[0]).iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        IndexedValue<Float> next = it.next();
        if (it.hasNext()) {
            float fFloatValue = next.getValue().floatValue();
            do {
                IndexedValue<Float> next2 = it.next();
                float fFloatValue2 = next2.getValue().floatValue();
                if (Float.compare(fFloatValue, fFloatValue2) < 0) {
                    next = next2;
                    fFloatValue = fFloatValue2;
                }
            } while (it.hasNext());
        }
        return OnfidoMlModelDocumentTypeMapperKt.getOnfidoMlModelDocumentTypes().get(next.getIndex());
    }

    public final Companion.Response detect(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        String strProcess = process(bitmap);
        OnfidoMlDocumentTypeParser onfidoMlDocumentTypeParser = OnfidoMlDocumentTypeParser.INSTANCE;
        DocumentType documentTypeFrom = onfidoMlDocumentTypeParser.getDocumentTypeFrom(strProcess);
        CountryCode documentCountryCode = onfidoMlDocumentTypeParser.getDocumentCountryCode(strProcess);
        OnfidoMlDocumentSide documentSide = onfidoMlDocumentTypeParser.getDocumentSide(strProcess);
        String documentVersion = onfidoMlDocumentTypeParser.getDocumentVersion(strProcess);
        if (documentTypeFrom == null || documentCountryCode == null || documentSide == null) {
            return null;
        }
        return new Companion.Response(documentSide, documentTypeFrom, documentCountryCode, documentVersion);
    }
}
