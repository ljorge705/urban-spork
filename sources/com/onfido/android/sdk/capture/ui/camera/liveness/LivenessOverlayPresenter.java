package com.onfido.android.sdk.capture.ui.camera.liveness;

import com.onfido.android.sdk.capture.detector.face.FaceDetectionResult;
import com.onfido.android.sdk.capture.detector.face.FaceDetector;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.service.VibratorService;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.MovementType;
import com.onfido.android.sdk.capture.ui.camera.liveness.turn.LivenessProgressManager;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableTransformer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.reactivestreams.Publisher;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000  2\u00020\u0001:\u0002 !B/\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u0016J,\u0010\u001b\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u001d\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u001d0\u001cH\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessOverlayPresenter;", "", "faceDetector", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetector;", "livenessProgressManager", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/turn/LivenessProgressManager;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "vibratorService", "Lcom/onfido/android/sdk/capture/internal/service/VibratorService;", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "(Lcom/onfido/android/sdk/capture/detector/face/FaceDetector;Lcom/onfido/android/sdk/capture/ui/camera/liveness/turn/LivenessProgressManager;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/internal/service/VibratorService;Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;)V", "compositeSubscription", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getCompositeSubscription", "()Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "compositeSubscription$delegate", "Lkotlin/Lazy;", "view", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessOverlayPresenter$View;", "attachView", "", "startFaceTracker", "movementType", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/MovementType;", "stop", "vibrateWhenProgressIsDone", "Lio/reactivex/rxjava3/core/FlowableTransformer;", "Lkotlin/Pair;", "", "", "Companion", "View", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LivenessOverlayPresenter {
    public static final int FACE_TRACKING_SEQUENTIAL_OBSERVATIONS = 2;
    public static final long MAX_AMOUNT_NEGATIVE_FEEDBACKS = 2;
    public static final float MIN_NEGATIVE_PROGRESS_PERCENTAGE_FOR_NOTIFICATION = -0.4f;
    public static final long MIN_TIME_DIFFERENCE_BETWEEN_NEGATIVE_FEEDBACKS_MS = 2000;
    private final AnnouncementService announcementService;

    /* renamed from: compositeSubscription$delegate, reason: from kotlin metadata */
    private final Lazy compositeSubscription;
    private final FaceDetector faceDetector;
    private final LivenessProgressManager livenessProgressManager;
    private final SchedulersProvider schedulersProvider;
    private final VibratorService vibratorService;
    private View view;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0007H&J\b\u0010\t\u001a\u00020\u0007H&J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessOverlayPresenter$View;", "", "headTurnDetectedString", "", "getHeadTurnDetectedString", "()Ljava/lang/String;", "onErrorObservingHeadTurnResults", "", "onFullProgressReached", "onHalfOfProgressReached", "onProgress", "progress", "", "onWrongHeadTurn", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface View {
        String getHeadTurnDetectedString();

        void onErrorObservingHeadTurnResults();

        void onFullProgressReached();

        void onHalfOfProgressReached();

        void onProgress(float progress);

        void onWrongHeadTurn();
    }

    @Inject
    public LivenessOverlayPresenter(FaceDetector faceDetector, LivenessProgressManager livenessProgressManager, SchedulersProvider schedulersProvider, VibratorService vibratorService, AnnouncementService announcementService) {
        Intrinsics.checkNotNullParameter(faceDetector, "faceDetector");
        Intrinsics.checkNotNullParameter(livenessProgressManager, "livenessProgressManager");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(vibratorService, "vibratorService");
        Intrinsics.checkNotNullParameter(announcementService, "announcementService");
        this.faceDetector = faceDetector;
        this.livenessProgressManager = livenessProgressManager;
        this.schedulersProvider = schedulersProvider;
        this.vibratorService = vibratorService;
        this.announcementService = announcementService;
        this.compositeSubscription = LazyKt.lazy(new Function0<CompositeDisposable>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter$compositeSubscription$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CompositeDisposable invoke() {
                return new CompositeDisposable();
            }
        });
    }

    private final CompositeDisposable getCompositeSubscription() {
        return (CompositeDisposable) this.compositeSubscription.getValue();
    }

    private final FlowableTransformer<Pair<Float, Boolean>, Pair<Float, Boolean>> vibrateWhenProgressIsDone() {
        return new FlowableTransformer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.FlowableTransformer
            public final Publisher apply(Flowable flowable) {
                return LivenessOverlayPresenter.vibrateWhenProgressIsDone$lambda$0(this.f$0, flowable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Publisher vibrateWhenProgressIsDone$lambda$0(final LivenessOverlayPresenter this$0, Flowable upstream) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        return upstream.distinctUntilChanged().filter(new Predicate() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter$vibrateWhenProgressIsDone$1$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Pair<Float, Boolean> pair) {
                Intrinsics.checkNotNullParameter(pair, "<name for destructuring parameter 0>");
                return pair.component1().floatValue() == 1.0f && !pair.component2().booleanValue();
            }
        }).flatMapCompletable(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter$vibrateWhenProgressIsDone$1$2
            @Override // io.reactivex.rxjava3.functions.Function
            public final CompletableSource apply(Pair<Float, Boolean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Completable completableVibrateForSuccess = this.this$0.vibratorService.vibrateForSuccess();
                AnnouncementService announcementService = this.this$0.announcementService;
                LivenessOverlayPresenter.View view = this.this$0.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                return completableVibrateForSuccess.mergeWith(AnnouncementService.announceString$default(announcementService, new String[]{view.getHeadTurnDetectedString()}, false, 2, (Object) null));
            }
        }).andThen(upstream);
    }

    public final void attachView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
    }

    public final void startFaceTracker(final MovementType movementType) {
        Intrinsics.checkNotNullParameter(movementType, "movementType");
        Flowable<U> flowableOfType = this.faceDetector.observeFaceTracking().ofType(FaceDetectionResult.FaceDetected.class);
        Intrinsics.checkNotNullExpressionValue(flowableOfType, "ofType(...)");
        Flowable flowableAutoConnect = flowableOfType.concatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter$startFaceTracker$faceTrackingProgressConnectableObservable$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final Publisher<? extends Pair<Float, Boolean>> apply(FaceDetectionResult.FaceDetected faceDetectionData) {
                Intrinsics.checkNotNullParameter(faceDetectionData, "faceDetectionData");
                return Flowable.just(TuplesKt.to(Float.valueOf(this.this$0.livenessProgressManager.angleToProgress(faceDetectionData.getFaceAngle(), movementType)), Boolean.valueOf(this.this$0.livenessProgressManager.getIsFirstMovementHalf())));
            }
        }).publish().autoConnect();
        Intrinsics.checkNotNullExpressionValue(flowableAutoConnect, "autoConnect(...)");
        CompositeDisposable compositeSubscription = getCompositeSubscription();
        Disposable disposableSubscribe = flowableAutoConnect.doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.startFaceTracker.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Pair<Float, Boolean> pair) {
                Intrinsics.checkNotNullParameter(pair, "<name for destructuring parameter 0>");
                LivenessOverlayPresenter.this.livenessProgressManager.updateStateIfNeeded(pair.component1().floatValue());
            }
        }).mergeWith(flowableAutoConnect.compose(vibrateWhenProgressIsDone())).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.startFaceTracker.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Pair<Float, Boolean> pair) {
                Intrinsics.checkNotNullParameter(pair, "<name for destructuring parameter 0>");
                float fFloatValue = pair.component1().floatValue();
                boolean zBooleanValue = pair.component2().booleanValue();
                View view = null;
                if (fFloatValue != 1.0f) {
                    View view2 = LivenessOverlayPresenter.this.view;
                    if (view2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                    } else {
                        view = view2;
                    }
                    view.onProgress(fFloatValue);
                    return;
                }
                if (zBooleanValue) {
                    View view3 = LivenessOverlayPresenter.this.view;
                    if (view3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                    } else {
                        view = view3;
                    }
                    view.onHalfOfProgressReached();
                    return;
                }
                View view4 = LivenessOverlayPresenter.this.view;
                if (view4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                } else {
                    view = view4;
                }
                view.onFullProgressReached();
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.startFaceTracker.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Timber.INSTANCE.e(LivenessOverlayPresenter.this.getClass().getName(), "Error on observing the face angle results: " + it.getMessage());
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeSubscription, disposableSubscribe);
        CompositeDisposable compositeSubscription2 = getCompositeSubscription();
        Disposable disposableSubscribe2 = flowableAutoConnect.map(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.startFaceTracker.4
            @Override // io.reactivex.rxjava3.functions.Function
            public final Boolean apply(Pair<Float, Boolean> pair) {
                Intrinsics.checkNotNullParameter(pair, "<name for destructuring parameter 0>");
                return Boolean.valueOf(pair.component1().floatValue() < -0.4f);
            }
        }).throttleFirst(2000L, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).distinctUntilChanged().filter(new Predicate() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.startFaceTracker.5
            @Override // io.reactivex.rxjava3.functions.Predicate
            public /* bridge */ /* synthetic */ boolean test(Object obj) {
                return test(((Boolean) obj).booleanValue());
            }

            public final boolean test(boolean z) {
                return z;
            }
        }).take(2L).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).flatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.startFaceTracker.6
            @Override // io.reactivex.rxjava3.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply(((Boolean) obj).booleanValue());
            }

            public final Publisher<? extends Boolean> apply(boolean z) {
                return LivenessOverlayPresenter.this.vibratorService.vibrateForError().andThen(Flowable.just(Boolean.valueOf(z)));
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.startFaceTracker.7
            @Override // io.reactivex.rxjava3.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept(((Boolean) obj).booleanValue());
            }

            public final void accept(boolean z) {
                View view = LivenessOverlayPresenter.this.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                view.onWrongHeadTurn();
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.startFaceTracker.8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Timber.INSTANCE.e(LivenessOverlayPresenter.this.getClass().getName(), "Error on observing the wrong face turn notifications: " + it.getMessage());
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe2, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeSubscription2, disposableSubscribe2);
        CompositeDisposable compositeSubscription3 = getCompositeSubscription();
        Flowable<U> flowableOfType2 = this.faceDetector.observeFaceTracking().ofType(FaceDetectionResult.Error.class);
        Intrinsics.checkNotNullExpressionValue(flowableOfType2, "ofType(...)");
        Disposable disposableSubscribe3 = flowableOfType2.firstElement().subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.startFaceTracker.9
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(FaceDetectionResult.Error error) {
                View view = LivenessOverlayPresenter.this.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                view.onErrorObservingHeadTurnResults();
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.startFaceTracker.10
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Timber.INSTANCE.e(LivenessOverlayPresenter.this.getClass().getName(), "Error on observing the face tracking errors: " + it.getMessage());
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe3, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeSubscription3, disposableSubscribe3);
    }

    public final void stop() {
        getCompositeSubscription().clear();
    }
}
