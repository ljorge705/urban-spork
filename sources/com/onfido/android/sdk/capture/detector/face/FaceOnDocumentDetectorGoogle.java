package com.onfido.android.sdk.capture.detector.face;

import com.clevertap.android.sdk.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.detector.helper.DocumentDetectionExtensionKt;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.ui.camera.FaceDetectionFrame;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.android.sdk.capture.validation.device.FaceOnDocumentValidationResult;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Singleton
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000 $2\u00020\u0001:\u0001$B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\rH\u0002J\u0010\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0019H\u0002J\u001a\u0010\u001e\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00140\u001f2\u0006\u0010 \u001a\u00020\u0014H\u0002J\u0012\u0010!\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020#0\"H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u000f8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/face/FaceOnDocumentDetectorGoogle;", "Lcom/onfido/android/sdk/capture/detector/face/FaceOnDocumentDetector;", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "faceDetectionSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/onfido/android/sdk/capture/ui/camera/FaceDetectionFrame;", "fastDetector", "Lcom/google/mlkit/vision/face/FaceDetector;", "getFastDetector", "()Lcom/google/mlkit/vision/face/FaceDetector;", "fastFaceDetectionOptions", "Lcom/google/mlkit/vision/face/FaceDetectorOptions;", "isRealtimeProcessingOngoing", "", Constants.KEY_HIDE_CLOSE, "", "detect", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/validation/device/FaceOnDocumentValidationResult;", "documentFrame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "detectWithDegree", "degree", "", "getFaceDetector", RRWebOptionsEvent.EVENT_TAG, "getFaceDetectorOptions", "faceDetectionMode", "onSuccessIfNotDisposed", "Lio/reactivex/rxjava3/core/SingleEmitter;", OnfidoLauncher.KEY_RESULT, "toFaceDetectionResult", "", "Lcom/google/mlkit/vision/face/Face;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceOnDocumentDetectorGoogle implements FaceOnDocumentDetector {
    private PublishSubject<FaceDetectionFrame> faceDetectionSubject;
    private com.google.mlkit.vision.face.FaceDetector fastDetector;
    private final FaceDetectorOptions fastFaceDetectionOptions;
    private boolean isRealtimeProcessingOngoing;
    private final OnfidoRemoteConfig remoteConfig;
    private static final FaceOnDocumentValidationResult OMITTED_FRAME_ANALYSE_RESULT = new FaceOnDocumentValidationResult(null, false, 1, null);
    private static final Integer[] IMAGE_DEGREES = {0, 90, 180, 270};

    @Inject
    public FaceOnDocumentDetectorGoogle(OnfidoRemoteConfig remoteConfig) {
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        this.remoteConfig = remoteConfig;
        this.fastFaceDetectionOptions = getFaceDetectorOptions(1);
        PublishSubject<FaceDetectionFrame> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this.faceDetectionSubject = publishSubjectCreate;
    }

    private final Single<FaceOnDocumentValidationResult> detectWithDegree(final DocumentDetectionFrame documentFrame, final int degree) {
        Single<FaceOnDocumentValidationResult> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetectorGoogle$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                FaceOnDocumentDetectorGoogle.detectWithDegree$lambda$3(documentFrame, degree, this, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create(...)");
        return singleCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detectWithDegree$lambda$3(DocumentDetectionFrame documentFrame, int i, final FaceOnDocumentDetectorGoogle this$0, final SingleEmitter emitter) {
        Task<List<Face>> taskProcess;
        Intrinsics.checkNotNullParameter(documentFrame, "$documentFrame");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        try {
            InputImage inputImageFromJpeg = DocumentDetectionExtensionKt.toInputImageFromJpeg(documentFrame, i, this$0.remoteConfig.getDocumentPostCaptureValidationTargetWidth());
            com.google.mlkit.vision.face.FaceDetector fastDetector = this$0.getFastDetector();
            if (fastDetector == null || (taskProcess = fastDetector.process(inputImageFromJpeg)) == null) {
                return;
            }
            final Function1<List<? extends Face>, Unit> function1 = new Function1<List<? extends Face>, Unit>() { // from class: com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetectorGoogle$detectWithDegree$1$1
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
                public final void invoke2(List<? extends Face> faces) {
                    Intrinsics.checkNotNullParameter(faces, "faces");
                    this.this$0.isRealtimeProcessingOngoing = false;
                    this.this$0.onSuccessIfNotDisposed(emitter, this.this$0.toFaceDetectionResult(faces));
                }
            };
            Task<List<Face>> taskAddOnSuccessListener = taskProcess.addOnSuccessListener(new OnSuccessListener() { // from class: com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetectorGoogle$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    FaceOnDocumentDetectorGoogle.detectWithDegree$lambda$3$lambda$1(function1, obj);
                }
            });
            if (taskAddOnSuccessListener != null) {
                taskAddOnSuccessListener.addOnFailureListener(new OnFailureListener() { // from class: com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetectorGoogle$$ExternalSyntheticLambda2
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        FaceOnDocumentDetectorGoogle.detectWithDegree$lambda$3$lambda$2(this.f$0, emitter, exc);
                    }
                });
            }
        } catch (Exception unused) {
            this$0.isRealtimeProcessingOngoing = false;
            this$0.onSuccessIfNotDisposed(emitter, OMITTED_FRAME_ANALYSE_RESULT);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detectWithDegree$lambda$3$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detectWithDegree$lambda$3$lambda$2(FaceOnDocumentDetectorGoogle this$0, SingleEmitter emitter, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "$emitter");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.isRealtimeProcessingOngoing = false;
        this$0.onSuccessIfNotDisposed(emitter, OMITTED_FRAME_ANALYSE_RESULT);
    }

    private final com.google.mlkit.vision.face.FaceDetector getFaceDetector(FaceDetectorOptions options) {
        com.google.mlkit.vision.face.FaceDetector client = FaceDetection.getClient(options);
        Intrinsics.checkNotNullExpressionValue(client, "getClient(...)");
        return client;
    }

    private final FaceDetectorOptions getFaceDetectorOptions(int faceDetectionMode) {
        FaceDetectorOptions faceDetectorOptionsBuild = new FaceDetectorOptions.Builder().setPerformanceMode(faceDetectionMode).build();
        Intrinsics.checkNotNullExpressionValue(faceDetectorOptionsBuild, "build(...)");
        return faceDetectorOptionsBuild;
    }

    private final com.google.mlkit.vision.face.FaceDetector getFastDetector() {
        com.google.mlkit.vision.face.FaceDetector faceDetector = this.fastDetector;
        return faceDetector == null ? getFaceDetector(this.fastFaceDetectionOptions) : faceDetector;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onSuccessIfNotDisposed(SingleEmitter<FaceOnDocumentValidationResult> singleEmitter, FaceOnDocumentValidationResult faceOnDocumentValidationResult) {
        if (singleEmitter.isDisposed()) {
            return;
        }
        singleEmitter.onSuccess(faceOnDocumentValidationResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FaceOnDocumentValidationResult toFaceDetectionResult(List<? extends Face> list) {
        return new FaceOnDocumentValidationResult(Boolean.valueOf(!list.isEmpty()), true);
    }

    @Override // com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetector
    public void close() {
        com.google.mlkit.vision.face.FaceDetector fastDetector = getFastDetector();
        if (fastDetector != null) {
            fastDetector.close();
        }
        this.fastDetector = null;
        PublishSubject<FaceDetectionFrame> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this.faceDetectionSubject = publishSubjectCreate;
        this.isRealtimeProcessingOngoing = false;
    }

    @Override // com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetector
    public synchronized Single<FaceOnDocumentValidationResult> detect(DocumentDetectionFrame documentFrame) {
        Intrinsics.checkNotNullParameter(documentFrame, "documentFrame");
        if (this.isRealtimeProcessingOngoing) {
            return RxExtensionsKt.asSingle(OMITTED_FRAME_ANALYSE_RESULT);
        }
        this.isRealtimeProcessingOngoing = true;
        Integer[] numArr = IMAGE_DEGREES;
        ArrayList arrayList = new ArrayList(numArr.length);
        for (Integer num : numArr) {
            arrayList.add(detectWithDegree(documentFrame, num.intValue()));
        }
        Single<FaceOnDocumentValidationResult> singleZip = Single.zip(arrayList, new Function() { // from class: com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetectorGoogle.detect.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final FaceOnDocumentValidationResult apply(Object[] outputs) {
                Intrinsics.checkNotNullParameter(outputs, "outputs");
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : outputs) {
                    if (obj instanceof FaceOnDocumentValidationResult) {
                        arrayList2.add(obj);
                    }
                }
                FaceOnDocumentValidationResult faceOnDocumentValidationResult = (FaceOnDocumentValidationResult) CollectionsKt.firstOrNull((List) arrayList2);
                return faceOnDocumentValidationResult == null ? FaceOnDocumentDetectorGoogle.OMITTED_FRAME_ANALYSE_RESULT : faceOnDocumentValidationResult;
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleZip, "zip(...)");
        return singleZip;
    }
}
