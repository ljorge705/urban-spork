package com.onfido.android.sdk.capture.detector.mrzextraction;

import android.graphics.Rect;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognizer;
import com.onfido.javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0096@¢\u0006\u0002\u0010\u0013J \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015*\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u0016H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocumentExtractorGoogle;", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocumentExtractor;", "textRecognizerProvider", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/TextRecognizerProvider;", "(Lcom/onfido/android/sdk/capture/detector/mrzextraction/TextRecognizerProvider;)V", "mrzParser", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZParser;", "shouldProcessNextFrame", "Ljava/util/concurrent/atomic/AtomicBoolean;", "textRecognizer", "Lcom/google/mlkit/vision/text/TextRecognizer;", "getTextRecognizer", "()Lcom/google/mlkit/vision/text/TextRecognizer;", "textRecognizer$delegate", "Lkotlin/Lazy;", "detect", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZResult;", "documentDetectionFrame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "(Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findLinesCorrespondTo", "", "Lcom/google/mlkit/vision/text/Text$Line;", "givenLine", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MRZDocumentExtractorGoogle implements MRZDocumentExtractor {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int ROTATION_MULTIPLIER = 90;
    private final MRZParser mrzParser;
    private AtomicBoolean shouldProcessNextFrame;

    /* renamed from: textRecognizer$delegate, reason: from kotlin metadata */
    private final Lazy textRecognizer;
    private final TextRecognizerProvider textRecognizerProvider;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocumentExtractorGoogle$Companion;", "", "()V", "ROTATION_MULTIPLIER", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractorGoogle", f = "MRZDocumentExtractorGoogle.kt", i = {0}, l = {34}, m = "detect", n = {"this"}, s = {"L$0"})
    /* renamed from: com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractorGoogle$detect$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MRZDocumentExtractorGoogle.this.detect(null, this);
        }
    }

    @Inject
    public MRZDocumentExtractorGoogle(TextRecognizerProvider textRecognizerProvider) {
        Intrinsics.checkNotNullParameter(textRecognizerProvider, "textRecognizerProvider");
        this.textRecognizerProvider = textRecognizerProvider;
        this.textRecognizer = LazyKt.lazy(new Function0<TextRecognizer>() { // from class: com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractorGoogle$textRecognizer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextRecognizer invoke() {
                return this.this$0.textRecognizerProvider.provide();
            }
        });
        this.mrzParser = new MRZParser();
        this.shouldProcessNextFrame = new AtomicBoolean(true);
    }

    private final List<Text.Line> findLinesCorrespondTo(List<? extends Text.Line> list, Text.Line line) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            Rect boundingBox = ((Text.Line) obj).getBoundingBox();
            if (boundingBox != null) {
                Intrinsics.checkNotNull(boundingBox);
                Rect boundingBox2 = line.getBoundingBox();
                if (boundingBox2 != null) {
                    Intrinsics.checkNotNull(boundingBox2);
                    int iHeight = boundingBox.top + (boundingBox.height() / 2);
                    int i = boundingBox.bottom;
                    int i2 = boundingBox2.bottom;
                    boolean z = iHeight <= i2 && i2 <= i;
                    int i3 = boundingBox.top;
                    int iHeight2 = i - (boundingBox.height() / 2);
                    int i4 = boundingBox2.top;
                    boolean z2 = i3 <= i4 && i4 <= iHeight2;
                    if (z || z2) {
                        arrayList.add(obj);
                    }
                }
            }
        }
        return arrayList;
    }

    private final TextRecognizer getTextRecognizer() {
        return (TextRecognizer) this.textRecognizer.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01b6  */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractorGoogle$detect$1, kotlin.coroutines.Continuation] */
    /* JADX WARN: Type inference failed for: r2v5 */
    @Override // com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object detect(com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame r21, kotlin.coroutines.Continuation<? super com.onfido.android.sdk.capture.detector.mrzextraction.MRZResult> r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 455
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractorGoogle.detect(com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
