package com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading;

import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesRepository;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesViewModel;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingView;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.api.client.exception.TokenExpiredException;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001d\u001eB\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u0006\u0010\u0012\u001a\u00020\u000eJ\b\u0010\u0013\u001a\u00020\u000eH\u0002J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u0006\u0010\u0018\u001a\u00020\u000eJ%\u0010\u0019\u001a\u0015\u0012\f\u0012\n \u001b*\u0004\u0018\u00010\u001a0\u001a0\u0010¢\u0006\u0002\b\u001c*\b\u0012\u0004\u0012\u00020\u001a0\u0010H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingPresenter;", "", "repository", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesRepository;", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "(Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesRepository;Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;)V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "view", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingPresenter$View;", "attachView", "", "fetchChallengeObservable", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesViewModel;", "fetchChallenges", "fetchFromApi", "handleError", "throwable", "", "minimumExecutionTimerObservable", "stop", "announceWhenLoading", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingView$ScreenState;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "Companion", "View", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LivenessChallengesLoadingPresenter {
    private static final Companion Companion = new Companion(null);
    private static final long LIVENESS_CHALLENGES_FETCH_TIMEOUT_MS = 5000;
    private static final long MINIMUM_EXECUTION_TIME_MS = 1000;
    private final AnnouncementService announcementService;
    private final CompositeDisposable compositeDisposable;
    private final LivenessChallengesRepository repository;
    private final SchedulersProvider schedulersProvider;
    private View view;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingPresenter$Companion;", "", "()V", "LIVENESS_CHALLENGES_FETCH_TIMEOUT_MS", "", "MINIMUM_EXECUTION_TIME_MS", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0007H&J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingPresenter$View;", "", "loadingAnnouncement", "", "getLoadingAnnouncement", "()Ljava/lang/String;", "onInvalidCertificate", "", "message", "onTokenExpired", "setScreenState", "state", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingView$ScreenState;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface View {
        String getLoadingAnnouncement();

        void onInvalidCertificate(String message);

        void onTokenExpired();

        void setScreenState(LivenessChallengesLoadingView.ScreenState state);
    }

    @Inject
    public LivenessChallengesLoadingPresenter(LivenessChallengesRepository repository, AnnouncementService announcementService, SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(announcementService, "announcementService");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        this.repository = repository;
        this.announcementService = announcementService;
        this.schedulersProvider = schedulersProvider;
        this.compositeDisposable = new CompositeDisposable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<LivenessChallengesLoadingView.ScreenState> announceWhenLoading(Observable<LivenessChallengesLoadingView.ScreenState> observable) {
        Observable<U> observableCast = observable.filter(new Predicate() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingPresenter$announceWhenLoading$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof LivenessChallengesLoadingView.ScreenState.Loading;
            }
        }).cast(LivenessChallengesLoadingView.ScreenState.Loading.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable<LivenessChallengesLoadingView.ScreenState> observable2 = observableCast.switchMapCompletable(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingPresenter.announceWhenLoading.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final CompletableSource apply(LivenessChallengesLoadingView.ScreenState.Loading it) {
                Intrinsics.checkNotNullParameter(it, "it");
                AnnouncementService announcementService = LivenessChallengesLoadingPresenter.this.announcementService;
                View view = LivenessChallengesLoadingPresenter.this.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                return AnnouncementService.announceString$default(announcementService, new String[]{view.getLoadingAnnouncement()}, false, 2, (Object) null);
            }
        }).toObservable();
        Intrinsics.checkNotNullExpressionValue(observable2, "toObservable(...)");
        return observable2;
    }

    private final Observable<LivenessChallengesViewModel> fetchChallengeObservable() {
        Observable<LivenessChallengesViewModel> observable = this.repository.get().timeout(5000L, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).toObservable();
        Intrinsics.checkNotNullExpressionValue(observable, "toObservable(...)");
        return observable;
    }

    private final void fetchFromApi() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Observable map = Observable.concatArrayEagerDelayError(minimumExecutionTimerObservable(), fetchChallengeObservable()).map(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingPresenter.fetchFromApi.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final LivenessChallengesLoadingView.ScreenState.Success apply(LivenessChallengesViewModel p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new LivenessChallengesLoadingView.ScreenState.Success(p0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast = map.cast(LivenessChallengesLoadingView.ScreenState.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable observablePublish = observableCast.startWithItem(LivenessChallengesLoadingView.ScreenState.Loading.INSTANCE).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).publish(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingPresenter.fetchFromApi.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<LivenessChallengesLoadingView.ScreenState> apply(Observable<LivenessChallengesLoadingView.ScreenState> shared) {
                Intrinsics.checkNotNullParameter(shared, "shared");
                return Observable.mergeDelayError(shared, LivenessChallengesLoadingPresenter.this.announceWhenLoading(shared));
            }
        });
        final View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        Disposable disposableSubscribe = observablePublish.subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingPresenter.fetchFromApi.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(LivenessChallengesLoadingView.ScreenState p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                view.setScreenState(p0);
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingPresenter.fetchFromApi.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                if (throwable instanceof CompositeException) {
                    List<Throwable> exceptions = ((CompositeException) throwable).getExceptions();
                    Intrinsics.checkNotNullExpressionValue(exceptions, "getExceptions(...)");
                    throwable = (Throwable) CollectionsKt.firstOrNull((List) exceptions);
                    if (throwable == null) {
                        return;
                    }
                }
                LivenessChallengesLoadingPresenter.this.handleError(throwable);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleError(Throwable throwable) {
        View view = null;
        if (throwable instanceof TokenExpiredException) {
            View view2 = this.view;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view = view2;
            }
            view.onTokenExpired();
            return;
        }
        if (!(throwable instanceof SSLPeerUnverifiedException)) {
            View view3 = this.view;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view = view3;
            }
            view.setScreenState(LivenessChallengesLoadingView.ScreenState.Error.INSTANCE);
            return;
        }
        View view4 = this.view;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view = view4;
        }
        String localizedMessage = ((SSLPeerUnverifiedException) throwable).getLocalizedMessage();
        Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
        view.onInvalidCertificate(localizedMessage);
    }

    private final Observable<LivenessChallengesViewModel> minimumExecutionTimerObservable() {
        Observable<LivenessChallengesViewModel> observable = Observable.timer(1000L, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).ignoreElements().toObservable();
        Intrinsics.checkNotNullExpressionValue(observable, "toObservable(...)");
        return observable;
    }

    public final void attachView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
    }

    public final void fetchChallenges() {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.setScreenState(LivenessChallengesLoadingView.ScreenState.Loading.INSTANCE);
        fetchFromApi();
    }

    public final void stop() {
        this.compositeDisposable.clear();
    }
}
