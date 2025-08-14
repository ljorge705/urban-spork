package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionImage;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorResult;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.tfmodel.BlazeFaceModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.tfmodel.Detection;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\fH\u0002J\b\u0010\u001a\u001a\u00020\u0017H\u0016J\u0010\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\n\u001a\u0015\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f0\u000b¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcTflite;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvc;", "blazeFaceModel", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/tfmodel/BlazeFaceModel;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "resultMapper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcResultMapper;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/tfmodel/Detection;", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/tfmodel/BlazeFaceModel;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcResultMapper;)V", "frameSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionImage;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "isModelClosed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "resultObservable", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult;", "getResultObservable", "()Lio/reactivex/rxjava3/core/Observable;", Constants.KEY_HIDE_CLOSE, "", "detect", "motionImage", "initialize", "processFrame", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceDetectorAvcTflite implements FaceDetectorAvc {
    private final BlazeFaceModel blazeFaceModel;
    private final PublishSubject<MotionImage> frameSubject;
    private final AtomicBoolean isModelClosed;
    private final FaceDetectorAvcResultMapper<Detection> resultMapper;
    private final Observable<FaceDetectorResult> resultObservable;
    private final SchedulersProvider schedulersProvider;

    @Inject
    public FaceDetectorAvcTflite(BlazeFaceModel blazeFaceModel, SchedulersProvider schedulersProvider, FaceDetectorAvcResultMapper<Detection> resultMapper) {
        Intrinsics.checkNotNullParameter(blazeFaceModel, "blazeFaceModel");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(resultMapper, "resultMapper");
        this.blazeFaceModel = blazeFaceModel;
        this.schedulersProvider = schedulersProvider;
        this.resultMapper = resultMapper;
        this.isModelClosed = new AtomicBoolean(false);
        PublishSubject<MotionImage> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this.frameSubject = publishSubjectCreate;
        Observable<FaceDetectorResult> observableShare = publishSubjectCreate.toFlowable(BackpressureStrategy.DROP).onBackpressureDrop(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcTflite$resultObservable$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(MotionImage motionImage) {
                Timber.INSTANCE.d("onBackpressureDrop", new Object[0]);
            }
        }).flatMapSingle(new FaceDetectorAvcTflite$resultObservable$2(this), false, 1).toObservable().share();
        Intrinsics.checkNotNullExpressionValue(observableShare, "share(...)");
        this.resultObservable = observableShare;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FaceDetectorResult detect(MotionImage motionImage) {
        if (this.isModelClosed.get()) {
            return FaceDetectorResult.FaceNotDetected.INSTANCE;
        }
        FaceDetectorResult faceDetectorResultMapToFaceDetectorResult = this.resultMapper.mapToFaceDetectorResult(this.blazeFaceModel.run(motionImage.getImage()), OnfidoRectF.INSTANCE.toOnfidoRectF(motionImage.getImage()), motionImage.getPreviewFrame());
        motionImage.getImage().recycle();
        return faceDetectorResultMapToFaceDetectorResult;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc
    public void close() {
        if (this.isModelClosed.compareAndSet(false, true)) {
            this.blazeFaceModel.close();
        }
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc
    public Observable<FaceDetectorResult> getResultObservable() {
        return this.resultObservable;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc
    public void initialize() throws IOException {
        this.blazeFaceModel.initialize();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc
    public void processFrame(MotionImage motionImage) {
        Intrinsics.checkNotNullParameter(motionImage, "motionImage");
        this.frameSubject.onNext(motionImage);
    }
}
