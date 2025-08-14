package com.onfido.android.sdk.capture.detector.face;

import android.graphics.Rect;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.onfido.android.sdk.capture.detector.face.FaceDetectionResult;
import com.onfido.android.sdk.capture.ui.camera.FaceDetectionFrame;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Singleton;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 !2\u00020\u0001:\u0001!B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0004H\u0012J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0012J\u0013\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u0017H\u0012J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016J0\u0010\u001a\u001a\u00020\u0011*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\u001b2\u0006\u0010\u001e\u001a\u00020\r2\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0000\u0012\u00020\n0 H\u0012R\u001e\u0010\u0003\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00058RX\u0092\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0092\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0092\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0092\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/onfido/android/sdk/capture/detector/face/FaceDetectorGoogle;", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetector;", "()V", "detector", "Lcom/google/mlkit/vision/face/FaceDetector;", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectorMLKit;", "getDetector", "()Lcom/google/mlkit/vision/face/FaceDetector;", "faceTrackingObservable", "Lio/reactivex/rxjava3/core/Flowable;", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult;", "faceTrackingSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/onfido/android/sdk/capture/ui/camera/FaceDetectionFrame;", "shouldProcessNextFrame", "Ljava/util/concurrent/atomic/AtomicBoolean;", Constants.KEY_HIDE_CLOSE, "", "createFaceDetector", "detect", "Lio/reactivex/rxjava3/core/Single;", "faceDetectionFrame", "getFaceDetectionObservable", "Lio/reactivex/rxjava3/annotations/NonNull;", "getFaceDetectionSubject", "observeFaceTracking", "addSuccessAndFailureListeners", "Lcom/google/android/gms/tasks/Task;", "", "Lcom/google/mlkit/vision/face/Face;", "frame", "subscriber", "Lio/reactivex/rxjava3/core/SingleEmitter;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Singleton
/* loaded from: classes2.dex */
public class FaceDetectorGoogle implements FaceDetector {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int ROTATION_MULTIPLIER = 90;
    private com.google.mlkit.vision.face.FaceDetector detector;
    private Flowable<FaceDetectionResult> faceTrackingObservable;
    private PublishSubject<FaceDetectionFrame> faceTrackingSubject;
    private AtomicBoolean shouldProcessNextFrame = new AtomicBoolean(true);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/face/FaceDetectorGoogle$Companion;", "", "()V", "ROTATION_MULTIPLIER", "", "toFaceDetectionRect", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionRect;", "Landroid/graphics/Rect;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final FaceDetectionRect toFaceDetectionRect(Rect rect) {
            return new FaceDetectionRect(rect.left, rect.top, rect.right, rect.bottom);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public FaceDetectorGoogle() {
        PublishSubject<FaceDetectionFrame> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this.faceTrackingSubject = publishSubjectCreate;
        this.faceTrackingObservable = getFaceDetectionObservable();
    }

    private void addSuccessAndFailureListeners(Task<List<Face>> task, final FaceDetectionFrame faceDetectionFrame, final SingleEmitter<? super FaceDetectionResult> singleEmitter) {
        final int rotation = faceDetectionFrame.getRotation();
        final int pictureWidth = faceDetectionFrame.getPictureWidth();
        final int pictureHeight = faceDetectionFrame.getPictureHeight();
        final FaceDetectionRect faceDetectionRect = new FaceDetectionRect(0, 0, faceDetectionFrame.getCropRect().getPreviewWidth(), faceDetectionFrame.getCropRect().getPreviewHeight());
        final Function1<List<? extends Face>, Unit> function1 = new Function1<List<? extends Face>, Unit>() { // from class: com.onfido.android.sdk.capture.detector.face.FaceDetectorGoogle.addSuccessAndFailureListeners.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Face> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<? extends Face> list) {
                FaceDetectorGoogle.this.shouldProcessNextFrame.set(true);
                Intrinsics.checkNotNull(list);
                if (!(!list.isEmpty())) {
                    singleEmitter.onSuccess(FaceDetectionResult.NoFaceDetected.INSTANCE);
                    return;
                }
                Companion companion = FaceDetectorGoogle.Companion;
                Rect boundingBox = ((Face) CollectionsKt.first((List) list)).getBoundingBox();
                Intrinsics.checkNotNullExpressionValue(boundingBox, "getBoundingBox(...)");
                FaceDetectionRect faceDetectionRect2 = companion.toFaceDetectionRect(boundingBox);
                float headEulerAngleY = ((Face) CollectionsKt.first((List) list)).getHeadEulerAngleY();
                int i = rotation;
                singleEmitter.onSuccess(new FaceDetectionResult.FaceDetected(FaceDetectionRect.INSTANCE.fromOnfidoRect$onfido_capture_sdk_core_release(faceDetectionRect2.rotate$onfido_capture_sdk_core_release(i, pictureWidth, pictureHeight).toOnfidoRect$onfido_capture_sdk_core_release()), headEulerAngleY * (i != 0 ? -1 : 1), faceDetectionRect, faceDetectionFrame.getCropRect()));
            }
        };
        task.addOnSuccessListener(new OnSuccessListener() { // from class: com.onfido.android.sdk.capture.detector.face.FaceDetectorGoogle$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                FaceDetectorGoogle.addSuccessAndFailureListeners$lambda$1(function1, obj);
            }
        });
        task.addOnFailureListener(new OnFailureListener() { // from class: com.onfido.android.sdk.capture.detector.face.FaceDetectorGoogle$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                FaceDetectorGoogle.addSuccessAndFailureListeners$lambda$2(this.f$0, singleEmitter, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addSuccessAndFailureListeners$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addSuccessAndFailureListeners$lambda$2(FaceDetectorGoogle this$0, SingleEmitter subscriber, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(subscriber, "$subscriber");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.shouldProcessNextFrame.set(true);
        String message = it.getMessage();
        if (message == null) {
            message = "";
        }
        subscriber.onSuccess(new FaceDetectionResult.Error(message));
    }

    private com.google.mlkit.vision.face.FaceDetector createFaceDetector() {
        com.google.mlkit.vision.face.FaceDetector client = FaceDetection.getClient(new FaceDetectorOptions.Builder().setPerformanceMode(1).setContourMode(1).setLandmarkMode(1).setClassificationMode(1).build());
        Intrinsics.checkNotNullExpressionValue(client, "getClient(...)");
        return client;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<FaceDetectionResult> detect(final FaceDetectionFrame faceDetectionFrame) {
        this.shouldProcessNextFrame.set(false);
        Single<FaceDetectionResult> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.onfido.android.sdk.capture.detector.face.FaceDetectorGoogle$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                FaceDetectorGoogle.detect$lambda$0(faceDetectionFrame, this, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create(...)");
        return singleCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detect$lambda$0(FaceDetectionFrame faceDetectionFrame, FaceDetectorGoogle this$0, SingleEmitter emitter) {
        Task<List<Face>> taskProcess;
        Intrinsics.checkNotNullParameter(faceDetectionFrame, "$faceDetectionFrame");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        InputImage inputImageFromByteArray = InputImage.fromByteArray(faceDetectionFrame.getYuv(), faceDetectionFrame.getPictureWidth(), faceDetectionFrame.getPictureHeight(), faceDetectionFrame.getRotation() * 90, 17);
        Intrinsics.checkNotNullExpressionValue(inputImageFromByteArray, "fromByteArray(...)");
        com.google.mlkit.vision.face.FaceDetector detector = this$0.getDetector();
        if (detector == null || (taskProcess = detector.process(inputImageFromByteArray)) == null) {
            return;
        }
        this$0.addSuccessAndFailureListeners(taskProcess, faceDetectionFrame, emitter);
    }

    private com.google.mlkit.vision.face.FaceDetector getDetector() {
        com.google.mlkit.vision.face.FaceDetector faceDetector = this.detector;
        if (faceDetector != null) {
            return faceDetector;
        }
        com.google.mlkit.vision.face.FaceDetector faceDetectorCreateFaceDetector = createFaceDetector();
        this.detector = faceDetectorCreateFaceDetector;
        return faceDetectorCreateFaceDetector;
    }

    private Flowable<FaceDetectionResult> getFaceDetectionObservable() {
        Flowable<FaceDetectionResult> flowableShare = this.faceTrackingSubject.hide().toFlowable(BackpressureStrategy.LATEST).filter(new Predicate() { // from class: com.onfido.android.sdk.capture.detector.face.FaceDetectorGoogle.getFaceDetectionObservable.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(FaceDetectionFrame faceDetectionFrame) {
                return FaceDetectorGoogle.this.shouldProcessNextFrame.get();
            }
        }).flatMapSingle(new Function() { // from class: com.onfido.android.sdk.capture.detector.face.FaceDetectorGoogle.getFaceDetectionObservable.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final Single<FaceDetectionResult> apply(FaceDetectionFrame p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return FaceDetectorGoogle.this.detect(p0);
            }
        }).share();
        Intrinsics.checkNotNullExpressionValue(flowableShare, "share(...)");
        return flowableShare;
    }

    @Override // com.onfido.android.sdk.capture.detector.face.FaceDetector
    public void close() {
        com.google.mlkit.vision.face.FaceDetector detector = getDetector();
        if (detector != null) {
            detector.close();
        }
        this.detector = null;
        PublishSubject<FaceDetectionFrame> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this.faceTrackingSubject = publishSubjectCreate;
        this.faceTrackingObservable = getFaceDetectionObservable();
        this.shouldProcessNextFrame.set(true);
    }

    @Override // com.onfido.android.sdk.capture.detector.face.FaceDetector
    public PublishSubject<FaceDetectionFrame> getFaceDetectionSubject() {
        return this.faceTrackingSubject;
    }

    @Override // com.onfido.android.sdk.capture.detector.face.FaceDetector
    public Flowable<FaceDetectionResult> observeFaceTracking() {
        return this.faceTrackingObservable;
    }
}
