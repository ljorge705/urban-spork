package com.onfido.android.sdk.capture.detector.rectangle;

import android.graphics.Rect;
import android.graphics.RectF;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.detector.rectangle.RectDetectionFaceResult;
import com.onfido.android.sdk.capture.detector.rectangle.RectDetectionResult;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.TaskExtensionKt;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.ui.camera.OverlayMetrics;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u0000 02\u00020\u0001:\u00010B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0015\u0010\u0016\u001a\u00070\u0017¢\u0006\u0002\b\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0012J2\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001c2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001c2\u0006\u0010\"\u001a\u00020#H\u0016J(\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001d0%2\u0006\u0010&\u001a\u00020!2\u0006\u0010'\u001a\u00020\u001f2\b\b\u0002\u0010\"\u001a\u00020#H\u0012J \u0010(\u001a\b\u0012\u0004\u0012\u00020*0)*\b\u0012\u0004\u0012\u00020*0)2\u0006\u0010+\u001a\u00020*H\u0012J$\u0010$\u001a\u00020\u001d*\u00020,2\u0006\u0010'\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020!2\u0006\u0010-\u001a\u00020.H\u0012J\u001a\u0010$\u001a\u00020.*\b\u0012\u0004\u0012\u00020/0)2\u0006\u0010&\u001a\u00020!H\u0012R\u001b\u0010\u0007\u001a\u00020\b8RX\u0092\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0092\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108RX\u0092\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\f\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleDetectorGoogle;", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleDetector;", "transformer", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleTransformer;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "(Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleTransformer;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;)V", "faceDetector", "Lcom/google/mlkit/vision/face/FaceDetector;", "getFaceDetector", "()Lcom/google/mlkit/vision/face/FaceDetector;", "faceDetector$delegate", "Lkotlin/Lazy;", "shouldProcessNextFrame", "Ljava/util/concurrent/atomic/AtomicBoolean;", "textDetector", "Lcom/google/mlkit/vision/text/TextRecognizer;", "getTextDetector", "()Lcom/google/mlkit/vision/text/TextRecognizer;", "textDetector$delegate", Constants.KEY_HIDE_CLOSE, "", "computationThread", "Lio/reactivex/rxjava3/disposables/Disposable;", "Lio/reactivex/rxjava3/annotations/NonNull;", "runnable", "Ljava/lang/Runnable;", "observeRectDetection", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectDetectionResult;", "overlayMetricsObservable", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayMetrics;", "documentFrameObservable", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "shouldUseFaceDetection", "", "process", "Lio/reactivex/rxjava3/core/Single;", "documentDetectionFrame", "overlayMetrics", "filterByFace", "", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "faceRectF", "Lcom/google/mlkit/vision/text/Text;", "faceDetectionResult", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectDetectionFaceResult;", "Lcom/google/mlkit/vision/face/Face;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class RectangleDetectorGoogle implements RectangleDetector {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final float DOCUMENT_RATIO = 1.5f;

    @Deprecated
    public static final float ID_FACE_RATIO = 2.2f;

    @Deprecated
    public static final float ID_WIDTH_RATIO_BY_FACE = 4.0f;

    @Deprecated
    public static final int MIN_TEXT_LENGTH = 1;

    @Deprecated
    public static final int ROTATION_MULTIPLIER = 90;

    /* renamed from: faceDetector$delegate, reason: from kotlin metadata */
    private final Lazy faceDetector;
    private final SchedulersProvider schedulersProvider;
    private AtomicBoolean shouldProcessNextFrame;

    /* renamed from: textDetector$delegate, reason: from kotlin metadata */
    private final Lazy textDetector;
    private final RectangleTransformer transformer;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleDetectorGoogle$Companion;", "", "()V", "DOCUMENT_RATIO", "", "ID_FACE_RATIO", "ID_WIDTH_RATIO_BY_FACE", "MIN_TEXT_LENGTH", "", "ROTATION_MULTIPLIER", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lio/reactivex/rxjava3/core/SingleSource;", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectDetectionResult;", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/detector/rectangle/RectDetectionFaceResult;", "apply"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle$process$2, reason: invalid class name and case insensitive filesystem */
    static final class C05722<T, R> implements Function {
        final /* synthetic */ DocumentDetectionFrame $documentDetectionFrame;
        final /* synthetic */ InputImage $inputImage;
        final /* synthetic */ OverlayMetrics $overlayMetrics;

        C05722(InputImage inputImage, OverlayMetrics overlayMetrics, DocumentDetectionFrame documentDetectionFrame) {
            this.$inputImage = inputImage;
            this.$overlayMetrics = overlayMetrics;
            this.$documentDetectionFrame = documentDetectionFrame;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final RectDetectionResult apply$lambda$0(Throwable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return RectDetectionResult.NoRectDetected.INSTANCE;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public final SingleSource<? extends RectDetectionResult> apply(final RectDetectionFaceResult result) {
            Intrinsics.checkNotNullParameter(result, "result");
            Task<Text> taskProcess = RectangleDetectorGoogle.this.getTextDetector().process(this.$inputImage);
            Intrinsics.checkNotNullExpressionValue(taskProcess, "process(...)");
            final RectangleDetectorGoogle rectangleDetectorGoogle = RectangleDetectorGoogle.this;
            Single single = TaskExtensionKt.toSingle(taskProcess, new Executor() { // from class: com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle$process$2$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Executor
                public final void execute(Runnable runnable) {
                    RectangleDetectorGoogle.access$computationThread(rectangleDetectorGoogle, runnable);
                }
            });
            final RectangleDetectorGoogle rectangleDetectorGoogle2 = RectangleDetectorGoogle.this;
            final OverlayMetrics overlayMetrics = this.$overlayMetrics;
            final DocumentDetectionFrame documentDetectionFrame = this.$documentDetectionFrame;
            return single.map(new Function() { // from class: com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle.process.2.2
                @Override // io.reactivex.rxjava3.functions.Function
                public final RectDetectionResult apply(Text text) {
                    RectangleDetectorGoogle rectangleDetectorGoogle3 = rectangleDetectorGoogle2;
                    Intrinsics.checkNotNull(text);
                    return rectangleDetectorGoogle3.process(text, overlayMetrics, documentDetectionFrame, result);
                }
            }).onErrorReturn(new Function() { // from class: com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle$process$2$$ExternalSyntheticLambda1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return RectangleDetectorGoogle.C05722.apply$lambda$0((Throwable) obj);
                }
            });
        }
    }

    @Inject
    public RectangleDetectorGoogle(RectangleTransformer transformer, SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(transformer, "transformer");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        this.transformer = transformer;
        this.schedulersProvider = schedulersProvider;
        this.textDetector = LazyKt.lazy(new Function0<TextRecognizer>() { // from class: com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle$textDetector$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextRecognizer invoke() {
                return TextRecognition.getClient(new TextRecognizerOptions.Builder().build());
            }
        });
        this.faceDetector = LazyKt.lazy(new Function0<FaceDetector>() { // from class: com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle$faceDetector$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FaceDetector invoke() {
                FaceDetectorOptions faceDetectorOptionsBuild = new FaceDetectorOptions.Builder().setClassificationMode(2).setContourMode(1).build();
                Intrinsics.checkNotNullExpressionValue(faceDetectorOptionsBuild, "build(...)");
                return FaceDetection.getClient(faceDetectorOptionsBuild);
            }
        });
        this.shouldProcessNextFrame = new AtomicBoolean(true);
    }

    public static final /* synthetic */ Disposable access$computationThread(RectangleDetectorGoogle rectangleDetectorGoogle, Runnable runnable) {
        return rectangleDetectorGoogle.computationThread(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Disposable computationThread(Runnable runnable) {
        Disposable disposableScheduleDirect = this.schedulersProvider.getComputation().scheduleDirect(runnable);
        Intrinsics.checkNotNullExpressionValue(disposableScheduleDirect, "scheduleDirect(...)");
        return disposableScheduleDirect;
    }

    private List<OnfidoRectF> filterByFace(List<OnfidoRectF> list, OnfidoRectF onfidoRectF) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            OnfidoRectF onfidoRectF2 = (OnfidoRectF) obj;
            if (onfidoRectF.getLeft() - onfidoRectF.width() <= onfidoRectF2.getLeft() && onfidoRectF.getTop() - (onfidoRectF.height() * 2.0f) <= onfidoRectF2.getTop() && onfidoRectF.getBottom() + (onfidoRectF.height() * 2.0f) >= onfidoRectF2.getBottom() && onfidoRectF.getRight() + (onfidoRectF.width() * 4.0f) >= onfidoRectF2.getRight()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private FaceDetector getFaceDetector() {
        return (FaceDetector) this.faceDetector.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TextRecognizer getTextDetector() {
        return (TextRecognizer) this.textDetector.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectDetectionFaceResult process(List<? extends Face> list, DocumentDetectionFrame documentDetectionFrame) {
        Face face = (Face) CollectionsKt.firstOrNull((List) list);
        if (face == null) {
            return RectDetectionFaceResult.NoFaceDetected.INSTANCE;
        }
        Intrinsics.checkNotNullExpressionValue(face.getBoundingBox(), "getBoundingBox(...)");
        return new RectDetectionFaceResult.FaceDetected(this.transformer.invoke$onfido_capture_sdk_core_release(new OnfidoRectF(r5.left, r5.top, r5.right, r5.bottom), documentDetectionFrame.getCropRect()));
    }

    static /* synthetic */ Single process$default(RectangleDetectorGoogle rectangleDetectorGoogle, DocumentDetectionFrame documentDetectionFrame, OverlayMetrics overlayMetrics, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: process");
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return rectangleDetectorGoogle.process(documentDetectionFrame, overlayMetrics, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SingleSource process$lambda$1(boolean z, final RectangleDetectorGoogle this$0, InputImage inputImage, final DocumentDetectionFrame documentDetectionFrame) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(inputImage, "$inputImage");
        Intrinsics.checkNotNullParameter(documentDetectionFrame, "$documentDetectionFrame");
        if (!z) {
            return Single.just(RectDetectionFaceResult.NoFaceDetected.INSTANCE);
        }
        Task<List<Face>> taskProcess = this$0.getFaceDetector().process(inputImage);
        Intrinsics.checkNotNullExpressionValue(taskProcess, "process(...)");
        return TaskExtensionKt.toSingle(taskProcess, new Executor() { // from class: com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                this.f$0.computationThread(runnable);
            }
        }).map(new Function() { // from class: com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle$process$1$2
            @Override // io.reactivex.rxjava3.functions.Function
            public final RectDetectionFaceResult apply(List<Face> list) {
                RectangleDetectorGoogle rectangleDetectorGoogle = this.this$0;
                Intrinsics.checkNotNull(list);
                return rectangleDetectorGoogle.process(list, documentDetectionFrame);
            }
        }).onErrorReturn(new Function() { // from class: com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return RectangleDetectorGoogle.process$lambda$1$lambda$0((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RectDetectionFaceResult process$lambda$1$lambda$0(Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return RectDetectionFaceResult.NoFaceDetected.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void process$lambda$2(RectangleDetectorGoogle this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.shouldProcessNextFrame.set(true);
    }

    @Override // com.onfido.android.sdk.capture.detector.rectangle.RectangleDetector
    public void close() {
        getFaceDetector().close();
        getTextDetector().close();
    }

    @Override // com.onfido.android.sdk.capture.detector.rectangle.RectangleDetector
    public Observable<RectDetectionResult> observeRectDetection(Observable<OverlayMetrics> overlayMetricsObservable, Observable<DocumentDetectionFrame> documentFrameObservable, final boolean shouldUseFaceDetection) {
        Intrinsics.checkNotNullParameter(overlayMetricsObservable, "overlayMetricsObservable");
        Intrinsics.checkNotNullParameter(documentFrameObservable, "documentFrameObservable");
        Observable<RectDetectionResult> observableFlatMapSingle = Observable.combineLatest(overlayMetricsObservable, documentFrameObservable, new BiFunction() { // from class: com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle.observeRectDetection.1
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public final Pair<OverlayMetrics, DocumentDetectionFrame> apply(OverlayMetrics p0, DocumentDetectionFrame p1) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                Intrinsics.checkNotNullParameter(p1, "p1");
                return new Pair<>(p0, p1);
            }
        }).filter(new Predicate() { // from class: com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle.observeRectDetection.2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Pair<OverlayMetrics, DocumentDetectionFrame> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return RectangleDetectorGoogle.this.shouldProcessNextFrame.get();
            }
        }).flatMapSingle(new Function() { // from class: com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle.observeRectDetection.3
            @Override // io.reactivex.rxjava3.functions.Function
            public final SingleSource<? extends RectDetectionResult> apply(Pair<OverlayMetrics, DocumentDetectionFrame> pair) {
                Intrinsics.checkNotNullParameter(pair, "<name for destructuring parameter 0>");
                OverlayMetrics overlayMetricsComponent1 = pair.component1();
                DocumentDetectionFrame documentDetectionFrameComponent2 = pair.component2();
                RectangleDetectorGoogle.this.shouldProcessNextFrame.set(false);
                RectangleDetectorGoogle rectangleDetectorGoogle = RectangleDetectorGoogle.this;
                Intrinsics.checkNotNull(documentDetectionFrameComponent2);
                Intrinsics.checkNotNull(overlayMetricsComponent1);
                return rectangleDetectorGoogle.process(documentDetectionFrameComponent2, overlayMetricsComponent1, shouldUseFaceDetection);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMapSingle, "flatMapSingle(...)");
        return observableFlatMapSingle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectDetectionResult process(Text text, OverlayMetrics overlayMetrics, DocumentDetectionFrame documentDetectionFrame, RectDetectionFaceResult rectDetectionFaceResult) {
        OnfidoRectF onfidoRectF;
        OnfidoRectF onfidoRectF2;
        List<Text.TextBlock> textBlocks = text.getTextBlocks();
        Intrinsics.checkNotNullExpressionValue(textBlocks, "getTextBlocks(...)");
        ArrayList arrayList = new ArrayList();
        for (Object obj : textBlocks) {
            if (((Text.TextBlock) obj).getText().length() > 1) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Rect boundingBox = ((Text.TextBlock) it.next()).getBoundingBox();
            if (boundingBox != null) {
                Intrinsics.checkNotNull(boundingBox);
                onfidoRectF2 = OnfidoRectF.INSTANCE.toOnfidoRectF(new RectF(boundingBox));
            } else {
                onfidoRectF2 = null;
            }
            if (onfidoRectF2 != null) {
                arrayList2.add(onfidoRectF2);
            }
        }
        List<OnfidoRectF> arrayList3 = new ArrayList<>(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            arrayList3.add(this.transformer.invoke$onfido_capture_sdk_core_release((OnfidoRectF) it2.next(), documentDetectionFrame.getCropRect()));
        }
        boolean z = rectDetectionFaceResult instanceof RectDetectionFaceResult.FaceDetected;
        if (z) {
            arrayList3 = filterByFace(arrayList3, ((RectDetectionFaceResult.FaceDetected) rectDetectionFaceResult).getRect());
        }
        if (!(!arrayList3.isEmpty())) {
            return RectDetectionResult.NoRectDetected.INSTANCE;
        }
        if (z) {
            OnfidoRectF rect = ((RectDetectionFaceResult.FaceDetected) rectDetectionFaceResult).getRect();
            Iterator<T> it3 = arrayList3.iterator();
            if (!it3.hasNext()) {
                throw new NoSuchElementException();
            }
            float top = ((OnfidoRectF) it3.next()).getTop();
            while (it3.hasNext()) {
                top = Math.min(top, ((OnfidoRectF) it3.next()).getTop());
            }
            float fCenterY = (rect.centerY() - top) * 2.2f;
            float f = 2;
            float f2 = fCenterY / f;
            onfidoRectF = new OnfidoRectF(rect.getLeft() - (rect.width() / f), rect.centerY() - f2, (rect.getLeft() - (rect.width() / f)) + (fCenterY * 1.5f), rect.centerY() + f2);
        } else {
            Iterator<T> it4 = arrayList3.iterator();
            if (!it4.hasNext()) {
                throw new NoSuchElementException();
            }
            float left = ((OnfidoRectF) it4.next()).getLeft();
            while (it4.hasNext()) {
                left = Math.min(left, ((OnfidoRectF) it4.next()).getLeft());
            }
            Iterator<T> it5 = arrayList3.iterator();
            if (!it5.hasNext()) {
                throw new NoSuchElementException();
            }
            float top2 = ((OnfidoRectF) it5.next()).getTop();
            while (it5.hasNext()) {
                top2 = Math.min(top2, ((OnfidoRectF) it5.next()).getTop());
            }
            Iterator<T> it6 = arrayList3.iterator();
            if (!it6.hasNext()) {
                throw new NoSuchElementException();
            }
            float right = ((OnfidoRectF) it6.next()).getRight();
            while (it6.hasNext()) {
                right = Math.max(right, ((OnfidoRectF) it6.next()).getRight());
            }
            Iterator<T> it7 = arrayList3.iterator();
            if (!it7.hasNext()) {
                throw new NoSuchElementException();
            }
            float bottom = ((OnfidoRectF) it7.next()).getBottom();
            while (it7.hasNext()) {
                bottom = Math.max(bottom, ((OnfidoRectF) it7.next()).getBottom());
            }
            onfidoRectF = new OnfidoRectF(left, top2, right, bottom);
        }
        OnfidoRectF.Companion companion = OnfidoRectF.INSTANCE;
        return new RectDetectionResult.RectDetected(onfidoRectF, companion.toOnfidoRectF(overlayMetrics.getVisibleCaptureRect()), companion.toOnfidoRectF(overlayMetrics.getRealCaptureRect()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<RectDetectionResult> process(final DocumentDetectionFrame documentDetectionFrame, OverlayMetrics overlayMetrics, final boolean shouldUseFaceDetection) {
        final InputImage inputImageFromByteArray = InputImage.fromByteArray(documentDetectionFrame.getYuv(), documentDetectionFrame.getFrameWidth(), documentDetectionFrame.getFrameHeight(), documentDetectionFrame.getRotation() * 90, 17);
        Intrinsics.checkNotNullExpressionValue(inputImageFromByteArray, "fromByteArray(...)");
        Single<RectDetectionResult> singleDoFinally = Single.defer(new Supplier() { // from class: com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Supplier
            public final Object get() {
                return RectangleDetectorGoogle.process$lambda$1(shouldUseFaceDetection, this, inputImageFromByteArray, documentDetectionFrame);
            }
        }).flatMap(new C05722(inputImageFromByteArray, overlayMetrics, documentDetectionFrame)).doFinally(new Action() { // from class: com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                RectangleDetectorGoogle.process$lambda$2(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleDoFinally, "doFinally(...)");
        return singleDoFinally;
    }
}
