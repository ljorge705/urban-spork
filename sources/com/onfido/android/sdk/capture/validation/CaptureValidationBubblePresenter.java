package com.onfido.android.sdk.capture.validation;

import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.android.sdk.capture.utils.Visibility;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import com.onfido.dagger.assisted.Assisted;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0010\u0018\u0000 \u00162\u00020\u0001:\u0003\u0016\u0017\u0018B+\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u001d\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0002\b\u0013*\b\u0012\u0004\u0012\u00020\u00140\u0011H\u0012J\u001d\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0002\b\u0013*\b\u0012\u0004\u0012\u00020\u00140\u0011H\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/CaptureValidationBubblePresenter;", "", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "bubbleMode", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$BubbleMode;", "view", "Lcom/onfido/android/sdk/capture/validation/CaptureValidationBubblePresenter$View;", "(Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$BubbleMode;Lcom/onfido/android/sdk/capture/validation/CaptureValidationBubblePresenter$View;)V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "onAttach", "", "onDetach", "liveCaptureObservable", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/utils/Visibility;", "Lio/reactivex/rxjava3/annotations/NonNull;", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand;", "postCaptureObservable", "Companion", "Factory", "View", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class CaptureValidationBubblePresenter {
    private static final long DELAY_VISIBILITY_MILLIS = 2000;
    private final AnnouncementService announcementService;
    private final OnfidoCaptureValidationBubble.BubbleMode bubbleMode;
    private final CompositeDisposable compositeDisposable;
    private final SchedulersProvider schedulersProvider;
    private final View view;

    @AssistedFactory
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/CaptureValidationBubblePresenter$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/validation/CaptureValidationBubblePresenter;", "bubbleMode", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$BubbleMode;", "view", "Lcom/onfido/android/sdk/capture/validation/CaptureValidationBubblePresenter$View;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        CaptureValidationBubblePresenter create(OnfidoCaptureValidationBubble.BubbleMode bubbleMode, View view);
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/CaptureValidationBubblePresenter$View;", "", "displayedText", "", "getDisplayedText", "()Ljava/lang/String;", "renderVisibility", "", ViewHierarchyNode.JsonKeys.VISIBILITY, "Lcom/onfido/android/sdk/capture/utils/Visibility;", "visibilityChange", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface View {
        String getDisplayedText();

        void renderVisibility(Visibility visibility);

        Observable<OnfidoCaptureValidationBubble.VisibilityCommand> visibilityChange();
    }

    @AssistedInject
    public CaptureValidationBubblePresenter(AnnouncementService announcementService, SchedulersProvider schedulersProvider, @Assisted OnfidoCaptureValidationBubble.BubbleMode bubbleMode, @Assisted View view) {
        Intrinsics.checkNotNullParameter(announcementService, "announcementService");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(bubbleMode, "bubbleMode");
        Intrinsics.checkNotNullParameter(view, "view");
        this.announcementService = announcementService;
        this.schedulersProvider = schedulersProvider;
        this.bubbleMode = bubbleMode;
        this.view = view;
        this.compositeDisposable = new CompositeDisposable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<Visibility> liveCaptureObservable(Observable<OnfidoCaptureValidationBubble.VisibilityCommand> observable) {
        Observable<Visibility> observableSwitchMap = observable.filter(new Predicate() { // from class: com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter.liveCaptureObservable.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(OnfidoCaptureValidationBubble.VisibilityCommand it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return CaptureValidationBubblePresenter.this.bubbleMode == OnfidoCaptureValidationBubble.BubbleMode.LIVE_CAPTURE;
            }
        }).map(new Function() { // from class: com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter.liveCaptureObservable.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final Pair<Visibility, Boolean> apply(OnfidoCaptureValidationBubble.VisibilityCommand visibilityCommand) {
                Intrinsics.checkNotNullParameter(visibilityCommand, "visibilityCommand");
                OnfidoCaptureValidationBubble.VisibilityCommand.Visible visible = visibilityCommand instanceof OnfidoCaptureValidationBubble.VisibilityCommand.Visible ? (OnfidoCaptureValidationBubble.VisibilityCommand.Visible) visibilityCommand : null;
                Object focusType = visible != null ? visible.getFocusType() : null;
                OnfidoCaptureValidationBubble.FocusType.AnnounceContent announceContent = focusType instanceof OnfidoCaptureValidationBubble.FocusType.AnnounceContent ? (OnfidoCaptureValidationBubble.FocusType.AnnounceContent) focusType : null;
                return TuplesKt.to(visibilityCommand.getVisibility(), Boolean.valueOf(announceContent != null ? announceContent.getShouldInterrupt() : false));
            }
        }).switchMap(new Function() { // from class: com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter.liveCaptureObservable.3
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends Visibility> apply(Pair<? extends Visibility, Boolean> pair) {
                Intrinsics.checkNotNullParameter(pair, "<name for destructuring parameter 0>");
                final Visibility visibilityComponent1 = pair.component1();
                return visibilityComponent1.getIsAppearing() ? CaptureValidationBubblePresenter.this.announcementService.announceString(new String[]{CaptureValidationBubblePresenter.this.view.getDisplayedText()}, pair.component2().booleanValue()).andThen(Observable.just(visibilityComponent1)) : Observable.timer(2000L, TimeUnit.MILLISECONDS, CaptureValidationBubblePresenter.this.schedulersProvider.getTimer()).map(new Function() { // from class: com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter.liveCaptureObservable.3.1
                    public final Visibility apply(long j) {
                        return visibilityComponent1;
                    }

                    @Override // io.reactivex.rxjava3.functions.Function
                    public /* bridge */ /* synthetic */ Object apply(Object obj) {
                        return apply(((Number) obj).longValue());
                    }
                });
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableSwitchMap, "switchMap(...)");
        return observableSwitchMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<Visibility> postCaptureObservable(Observable<OnfidoCaptureValidationBubble.VisibilityCommand> observable) {
        Observable<Visibility> observableFlatMap = observable.filter(new Predicate() { // from class: com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter.postCaptureObservable.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(OnfidoCaptureValidationBubble.VisibilityCommand it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return CaptureValidationBubblePresenter.this.bubbleMode == OnfidoCaptureValidationBubble.BubbleMode.POST_CAPTURE;
            }
        }).map(new Function() { // from class: com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter.postCaptureObservable.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final Visibility apply(OnfidoCaptureValidationBubble.VisibilityCommand it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getVisibility();
            }
        }).flatMap(new Function() { // from class: com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter.postCaptureObservable.3
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends Visibility> apply(Visibility visibility) {
                Intrinsics.checkNotNullParameter(visibility, "visibility");
                Observable observableJust = Observable.just(visibility);
                Intrinsics.checkNotNullExpressionValue(observableJust, "just(...)");
                return visibility.getIsAppearing() ? AnnouncementService.announceString$default(CaptureValidationBubblePresenter.this.announcementService, new String[]{CaptureValidationBubblePresenter.this.view.getDisplayedText()}, false, 2, (Object) null).andThen(observableJust) : observableJust;
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMap, "flatMap(...)");
        return observableFlatMap;
    }

    public void onAttach() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Observable observableObserveOn = this.view.visibilityChange().distinctUntilChanged().publish(new Function() { // from class: com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter.onAttach.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<Visibility> apply(Observable<OnfidoCaptureValidationBubble.VisibilityCommand> shared) {
                Intrinsics.checkNotNullParameter(shared, "shared");
                return CaptureValidationBubblePresenter.this.liveCaptureObservable(shared).mergeWith(CaptureValidationBubblePresenter.this.postCaptureObservable(shared));
            }
        }).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi());
        final View view = this.view;
        Disposable disposableSubscribe = observableObserveOn.subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter.onAttach.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Visibility p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                view.renderVisibility(p0);
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter.onAttach.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Failure during visibility change", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    public void onDetach() {
        this.compositeDisposable.clear();
    }
}
