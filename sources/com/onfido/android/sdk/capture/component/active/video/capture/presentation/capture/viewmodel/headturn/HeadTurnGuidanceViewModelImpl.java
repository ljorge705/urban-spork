package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn;

import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.component.active.video.capture.analytics.AvcAnalyticsCaptureStatus;
import com.onfido.android.sdk.capture.component.active.video.capture.analytics.AvcAnalyticsEvent;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 +2\u00020\u0001:\u0001+B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\r\u0010\u0016\u001a\u00070\u0015¢\u0006\u0002\b\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0019H\u0016J\u0010\u0010\u001f\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0019H\u0002J\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0019H\u0002J\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\"H\u0002J\b\u0010$\u001a\u00020\u001dH\u0016J\b\u0010%\u001a\u00020\u001dH\u0016J\b\u0010&\u001a\u00020\u001dH\u0016J\u0010\u0010'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020\u001dH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u000b0\u000f¢\u0006\u0002\b\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0019\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u000b0\u000f¢\u0006\u0002\b\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\rR\u0019\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00150\u000f¢\u0006\u0002\b\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModelImpl;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel;", "analytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;)V", "headTurnTimerDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "leftTrackState", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel$TrackState;", "getLeftTrackState", "()Lio/reactivex/rxjava3/core/Observable;", "leftTrackStateSubject", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "Lio/reactivex/rxjava3/annotations/NonNull;", "rightTrackState", "getRightTrackState", "rightTrackStateSubject", "viewState", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel$ViewState;", "getViewState", "viewStateSubject", "getHeadTurnProgress", "", ViewProps.ROTATION, "Lio/reactivex/rxjava3/annotations/Nullable;", "handleFaceRotation", "", "faceAngle", "handleLeftSideTurn", "handleRightSideTurn", "isLeftTrackCompleted", "", "isRightTrackCompleted", "onViewDetached", "reset", "startHeadTurnTimer", "trackCaptureStatusAnalyticsEvent", "status", "Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsCaptureStatus;", "trackScreenEvent", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HeadTurnGuidanceViewModelImpl implements HeadTurnGuidanceViewModel {
    public static final float MAX_FACE_ANGLE = 25.0f;
    public static final long MAX_HEAD_TURN_TIME_MILLISECONDS = 5000;
    public static final float MIN_FACE_ANGLE = 5.0f;
    private final OnfidoAnalytics analytics;
    private final CompositeDisposable headTurnTimerDisposable;
    private final Observable<HeadTurnGuidanceViewModel.TrackState> leftTrackState;
    private final BehaviorSubject<HeadTurnGuidanceViewModel.TrackState> leftTrackStateSubject;
    private final Observable<HeadTurnGuidanceViewModel.TrackState> rightTrackState;
    private final BehaviorSubject<HeadTurnGuidanceViewModel.TrackState> rightTrackStateSubject;
    private final SchedulersProvider schedulersProvider;
    private final Observable<HeadTurnGuidanceViewModel.ViewState> viewState;
    private final BehaviorSubject<HeadTurnGuidanceViewModel.ViewState> viewStateSubject;

    @Inject
    public HeadTurnGuidanceViewModelImpl(OnfidoAnalytics analytics, SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        this.analytics = analytics;
        this.schedulersProvider = schedulersProvider;
        this.headTurnTimerDisposable = new CompositeDisposable();
        BehaviorSubject<HeadTurnGuidanceViewModel.ViewState> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(new HeadTurnGuidanceViewModel.ViewState(false, false));
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault, "createDefault(...)");
        this.viewStateSubject = behaviorSubjectCreateDefault;
        Observable<HeadTurnGuidanceViewModel.ViewState> observableShare = behaviorSubjectCreateDefault.hide().distinctUntilChanged().doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModelImpl$viewState$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(HeadTurnGuidanceViewModel.ViewState viewState) {
                HeadTurnGuidanceViewModelImpl headTurnGuidanceViewModelImpl;
                AvcAnalyticsCaptureStatus avcAnalyticsCaptureStatus;
                if (viewState.getShowHeadTurnAnim()) {
                    headTurnGuidanceViewModelImpl = this.this$0;
                    avcAnalyticsCaptureStatus = AvcAnalyticsCaptureStatus.ANIMATION_DISPLAYED;
                } else {
                    if (!viewState.isCompleted()) {
                        return;
                    }
                    headTurnGuidanceViewModelImpl = this.this$0;
                    avcAnalyticsCaptureStatus = AvcAnalyticsCaptureStatus.HEADTURN_COMPLETED;
                }
                headTurnGuidanceViewModelImpl.trackCaptureStatusAnalyticsEvent(avcAnalyticsCaptureStatus);
            }
        }).share();
        Intrinsics.checkNotNullExpressionValue(observableShare, "share(...)");
        this.viewState = observableShare;
        HeadTurnGuidanceViewModel.TrackState.Reset reset = HeadTurnGuidanceViewModel.TrackState.Reset.INSTANCE;
        BehaviorSubject<HeadTurnGuidanceViewModel.TrackState> behaviorSubjectCreateDefault2 = BehaviorSubject.createDefault(reset);
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault2, "createDefault(...)");
        this.leftTrackStateSubject = behaviorSubjectCreateDefault2;
        Observable<HeadTurnGuidanceViewModel.TrackState> observableDistinctUntilChanged = behaviorSubjectCreateDefault2.hide().distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        this.leftTrackState = observableDistinctUntilChanged;
        BehaviorSubject<HeadTurnGuidanceViewModel.TrackState> behaviorSubjectCreateDefault3 = BehaviorSubject.createDefault(reset);
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault3, "createDefault(...)");
        this.rightTrackStateSubject = behaviorSubjectCreateDefault3;
        Observable<HeadTurnGuidanceViewModel.TrackState> observableDistinctUntilChanged2 = behaviorSubjectCreateDefault3.hide().distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged2, "distinctUntilChanged(...)");
        this.rightTrackState = observableDistinctUntilChanged2;
    }

    private final float getHeadTurnProgress(float rotation) {
        if (rotation < 5.0f) {
            return 0.0f;
        }
        return (rotation - 5.0f) / 20.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HeadTurnGuidanceViewModel.ViewState getViewState() {
        HeadTurnGuidanceViewModel.ViewState value = this.viewStateSubject.getValue();
        Intrinsics.checkNotNull(value);
        return value;
    }

    private final void handleLeftSideTurn(float faceAngle) {
        if (!isLeftTrackCompleted()) {
            if (faceAngle >= 25.0f) {
                this.viewStateSubject.onNext(HeadTurnGuidanceViewModel.ViewState.copy$default(getViewState(), false, false, 1, null));
                this.leftTrackStateSubject.onNext(HeadTurnGuidanceViewModel.TrackState.Completed.INSTANCE);
            } else {
                this.leftTrackStateSubject.onNext(new HeadTurnGuidanceViewModel.TrackState.ProgressUpdated(getHeadTurnProgress(faceAngle)));
            }
        }
        if (isRightTrackCompleted()) {
            return;
        }
        this.rightTrackStateSubject.onNext(HeadTurnGuidanceViewModel.TrackState.Reset.INSTANCE);
    }

    private final void handleRightSideTurn(float faceAngle) {
        if (!isRightTrackCompleted()) {
            if (faceAngle >= 25.0f) {
                this.viewStateSubject.onNext(HeadTurnGuidanceViewModel.ViewState.copy$default(getViewState(), false, false, 1, null));
                this.rightTrackStateSubject.onNext(HeadTurnGuidanceViewModel.TrackState.Completed.INSTANCE);
            } else {
                this.rightTrackStateSubject.onNext(new HeadTurnGuidanceViewModel.TrackState.ProgressUpdated(getHeadTurnProgress(faceAngle)));
            }
        }
        if (isLeftTrackCompleted()) {
            return;
        }
        this.leftTrackStateSubject.onNext(HeadTurnGuidanceViewModel.TrackState.Reset.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isLeftTrackCompleted() {
        return this.leftTrackStateSubject.getValue() instanceof HeadTurnGuidanceViewModel.TrackState.Completed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isRightTrackCompleted() {
        return this.rightTrackStateSubject.getValue() instanceof HeadTurnGuidanceViewModel.TrackState.Completed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void trackCaptureStatusAnalyticsEvent(AvcAnalyticsCaptureStatus status) {
        this.analytics.track(new AvcAnalyticsEvent.FaceCaptureStatus(status.getValue()));
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel
    public Observable<HeadTurnGuidanceViewModel.TrackState> getLeftTrackState() {
        return this.leftTrackState;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel
    public Observable<HeadTurnGuidanceViewModel.TrackState> getRightTrackState() {
        return this.rightTrackState;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel
    /* renamed from: getViewState, reason: collision with other method in class */
    public Observable<HeadTurnGuidanceViewModel.ViewState> mo5553getViewState() {
        return this.viewState;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel
    public void handleFaceRotation(float faceAngle) {
        if (isLeftTrackCompleted() && isRightTrackCompleted()) {
            this.viewStateSubject.onNext(new HeadTurnGuidanceViewModel.ViewState(true, false));
        } else if (faceAngle < 0.0f) {
            handleLeftSideTurn(Math.abs(faceAngle));
        } else {
            handleRightSideTurn(faceAngle);
        }
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel
    public void onViewDetached() {
        this.headTurnTimerDisposable.clear();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel
    public void reset() {
        this.headTurnTimerDisposable.clear();
        this.viewStateSubject.onNext(new HeadTurnGuidanceViewModel.ViewState(false, false));
        BehaviorSubject<HeadTurnGuidanceViewModel.TrackState> behaviorSubject = this.leftTrackStateSubject;
        HeadTurnGuidanceViewModel.TrackState.Reset reset = HeadTurnGuidanceViewModel.TrackState.Reset.INSTANCE;
        behaviorSubject.onNext(reset);
        this.rightTrackStateSubject.onNext(reset);
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel
    public void startHeadTurnTimer() {
        this.headTurnTimerDisposable.clear();
        CompositeDisposable compositeDisposable = this.headTurnTimerDisposable;
        Disposable disposableSubscribe = Observable.timer(5000L, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModelImpl.startHeadTurnTimer.1
            public final void accept(long j) {
                if (HeadTurnGuidanceViewModelImpl.this.isLeftTrackCompleted() && HeadTurnGuidanceViewModelImpl.this.isRightTrackCompleted()) {
                    return;
                }
                HeadTurnGuidanceViewModelImpl.this.viewStateSubject.onNext(HeadTurnGuidanceViewModel.ViewState.copy$default(HeadTurnGuidanceViewModelImpl.this.getViewState(), false, true, 1, null));
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept(((Number) obj).longValue());
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel
    public void trackScreenEvent() {
        this.analytics.track(AvcAnalyticsEvent.FaceCapture.INSTANCE);
    }
}
