package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector;

import android.graphics.Bitmap;
import androidx.media3.common.MimeTypes;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.onfido.android.sdk.capture.component.active.video.capture.analytics.AvcAnalyticsEvent;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionImage;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorResult;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 *2\u00020\u0001:\u0001*B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u00192\u0006\u0010\u001a\u001a\u00020\u000eH\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\b\u0010\u001e\u001a\u00020\u0017H\u0016J\u0014\u0010\u001f\u001a\u00020 2\n\u0010!\u001a\u00060\"j\u0002`#H\u0002J\u0010\u0010$\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u000eH\u0016J\u0018\u0010%\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\u001cH\u0002J\u001a\u0010'\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\u00130(2\u0006\u0010)\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R!\u0010\f\u001a\u0015\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u000e0\u000e0\r¢\u0006\u0002\b\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcMLKit;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvc;", "analytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "resultMapper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcResultMapper;", "Lcom/google/mlkit/vision/face/Face;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcResultMapper;)V", "detector", "Lcom/google/mlkit/vision/face/FaceDetector;", "frameSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionImage;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "resultObservable", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult;", "getResultObservable", "()Lio/reactivex/rxjava3/core/Observable;", Constants.KEY_HIDE_CLOSE, "", "detect", "Lio/reactivex/rxjava3/core/Single;", "motionImage", "getFaceDetectionBitmap", "Landroid/graphics/Bitmap;", MimeTypes.BASE_TYPE_IMAGE, "initialize", "mapToError", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult$Error;", "ex", "Ljava/lang/Exception;", "Lkotlin/Exception;", "processFrame", "recycleBitmaps", "faceDetectionBitmap", "emitOnSuccess", "Lio/reactivex/rxjava3/core/SingleEmitter;", "data", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceDetectorAvcMLKit implements FaceDetectorAvc {
    public static final int FACE_DETECTION_FRAME_WIDTH = 240;
    private final OnfidoAnalytics analytics;
    private FaceDetector detector;
    private final PublishSubject<MotionImage> frameSubject;
    private final FaceDetectorAvcResultMapper<Face> resultMapper;
    private final Observable<FaceDetectorResult> resultObservable;
    private final SchedulersProvider schedulersProvider;

    @Inject
    public FaceDetectorAvcMLKit(OnfidoAnalytics analytics, SchedulersProvider schedulersProvider, FaceDetectorAvcResultMapper<Face> resultMapper) {
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(resultMapper, "resultMapper");
        this.analytics = analytics;
        this.schedulersProvider = schedulersProvider;
        this.resultMapper = resultMapper;
        PublishSubject<MotionImage> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this.frameSubject = publishSubjectCreate;
        Observable<FaceDetectorResult> observableShare = publishSubjectCreate.toFlowable(BackpressureStrategy.DROP).onBackpressureDrop(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit$resultObservable$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(MotionImage motionImage) {
                Timber.INSTANCE.d("onBackpressureDrop", new Object[0]);
            }
        }).flatMapSingle(new Function() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit$resultObservable$2
            @Override // io.reactivex.rxjava3.functions.Function
            public final SingleSource<? extends FaceDetectorResult> apply(MotionImage motionImage) {
                FaceDetectorAvcMLKit faceDetectorAvcMLKit = this.this$0;
                Intrinsics.checkNotNull(motionImage);
                return faceDetectorAvcMLKit.detect(motionImage).subscribeOn(this.this$0.schedulersProvider.getComputation());
            }
        }, false, 1).toObservable().share();
        Intrinsics.checkNotNullExpressionValue(observableShare, "share(...)");
        this.resultObservable = observableShare;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Single<FaceDetectorResult> detect(final MotionImage motionImage) {
        final Bitmap faceDetectionBitmap = getFaceDetectionBitmap(motionImage.getImage());
        Single<FaceDetectorResult> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                FaceDetectorAvcMLKit.detect$lambda$3(faceDetectionBitmap, this, motionImage, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create(...)");
        return singleCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detect$lambda$3(final Bitmap faceDetectionBitmap, final FaceDetectorAvcMLKit this$0, final MotionImage motionImage, final SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(faceDetectionBitmap, "$faceDetectionBitmap");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(motionImage, "$motionImage");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        InputImage inputImageFromBitmap = InputImage.fromBitmap(faceDetectionBitmap, 0);
        Intrinsics.checkNotNullExpressionValue(inputImageFromBitmap, "fromBitmap(...)");
        if (emitter.isDisposed()) {
            this$0.recycleBitmaps(motionImage.getImage(), faceDetectionBitmap);
            return;
        }
        FaceDetector faceDetector = this$0.detector;
        if (faceDetector == null) {
            Intrinsics.throwUninitializedPropertyAccessException("detector");
            faceDetector = null;
        }
        Task<List<Face>> taskProcess = faceDetector.process(inputImageFromBitmap);
        final Function1<List<Face>, Unit> function1 = new Function1<List<Face>, Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit$detect$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<Face> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<Face> list) {
                FaceDetectorAvcMLKit faceDetectorAvcMLKit = this.this$0;
                SingleEmitter<FaceDetectorResult> singleEmitter = emitter;
                FaceDetectorAvcResultMapper faceDetectorAvcResultMapper = faceDetectorAvcMLKit.resultMapper;
                Intrinsics.checkNotNull(list);
                faceDetectorAvcMLKit.emitOnSuccess(singleEmitter, faceDetectorAvcResultMapper.mapToFaceDetectorResult(list, OnfidoRectF.INSTANCE.toOnfidoRectF(faceDetectionBitmap), motionImage.getPreviewFrame()));
            }
        };
        taskProcess.addOnSuccessListener(new OnSuccessListener() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                FaceDetectorAvcMLKit.detect$lambda$3$lambda$0(function1, obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                FaceDetectorAvcMLKit.detect$lambda$3$lambda$1(this.f$0, emitter, exc);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FaceDetectorAvcMLKit.detect$lambda$3$lambda$2(this.f$0, motionImage, faceDetectionBitmap, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detect$lambda$3$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detect$lambda$3$lambda$1(FaceDetectorAvcMLKit this$0, SingleEmitter emitter, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "$emitter");
        Intrinsics.checkNotNullParameter(it, "it");
        FaceDetectorResult.Error errorMapToError = this$0.mapToError(it);
        this$0.analytics.track(new AvcAnalyticsEvent.MLKitError(errorMapToError.getMessage()));
        Timber.INSTANCE.e(errorMapToError.getMessage(), new Object[0]);
        this$0.emitOnSuccess(emitter, errorMapToError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detect$lambda$3$lambda$2(FaceDetectorAvcMLKit this$0, MotionImage motionImage, Bitmap faceDetectionBitmap, Task it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(motionImage, "$motionImage");
        Intrinsics.checkNotNullParameter(faceDetectionBitmap, "$faceDetectionBitmap");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.recycleBitmaps(motionImage.getImage(), faceDetectionBitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitOnSuccess(SingleEmitter<FaceDetectorResult> singleEmitter, FaceDetectorResult faceDetectorResult) {
        if (singleEmitter.isDisposed()) {
            return;
        }
        singleEmitter.onSuccess(faceDetectorResult);
    }

    private final Bitmap getFaceDetectionBitmap(Bitmap image) {
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(image, FACE_DETECTION_FRAME_WIDTH, (int) (FACE_DETECTION_FRAME_WIDTH * (image.getHeight() / image.getWidth())), true);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap, "createScaledBitmap(...)");
        return bitmapCreateScaledBitmap;
    }

    private final FaceDetectorResult.Error mapToError(Exception ex) {
        int errorCode = ex instanceof MlKitException ? ((MlKitException) ex).getErrorCode() : -1;
        StringBuilder sb = new StringBuilder();
        String message = ex.getMessage();
        if (message == null) {
            message = "";
        }
        return new FaceDetectorResult.Error(sb.append(message).append(", Error code: ").append(errorCode).toString(), ex);
    }

    private final void recycleBitmaps(Bitmap motionImage, Bitmap faceDetectionBitmap) {
        motionImage.recycle();
        faceDetectionBitmap.recycle();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc
    public void close() {
        FaceDetector faceDetector = this.detector;
        if (faceDetector != null) {
            if (faceDetector == null) {
                Intrinsics.throwUninitializedPropertyAccessException("detector");
                faceDetector = null;
            }
            faceDetector.close();
        }
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc
    public Observable<FaceDetectorResult> getResultObservable() {
        return this.resultObservable;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc
    public void initialize() {
        FaceDetector client = FaceDetection.getClient(new FaceDetectorOptions.Builder().setPerformanceMode(1).setContourMode(1).setLandmarkMode(1).setClassificationMode(1).build());
        Intrinsics.checkNotNullExpressionValue(client, "getClient(...)");
        this.detector = client;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc
    public void processFrame(MotionImage motionImage) {
        Intrinsics.checkNotNullParameter(motionImage, "motionImage");
        this.frameSubject.onNext(motionImage);
    }
}
