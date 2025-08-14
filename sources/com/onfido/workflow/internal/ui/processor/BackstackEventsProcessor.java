package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.internal.ui.HostedWebModuleScreen;
import com.onfido.workflow.internal.ui.LoadingScreen;
import com.onfido.workflow.internal.ui.model.ToolbarState;
import com.onfido.workflow.internal.ui.model.UIEvent;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;

/* compiled from: BackstackEventsProcessor.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J(\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\u0014\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\t0\u0010H\u0002J\"\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\t0\u00102\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u0018\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015*\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/BackstackEventsProcessor;", "", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "(Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;)V", "process", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/workflow/internal/ui/model/ToolbarState;", "uiEvents", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "onEmptyBackStack", "Lkotlin/Function0;", "", "processBackButton", "Lio/reactivex/rxjava3/core/ObservableTransformer;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnBackPressed;", "processBackStackChange", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnBackstackChange;", "excludeLoadingScreen", "", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class BackstackEventsProcessor {
    private final Navigator navigator;
    private final OnfidoRemoteConfig remoteConfig;

    @Inject
    public BackstackEventsProcessor(OnfidoRemoteConfig remoteConfig, Navigator navigator) {
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        this.remoteConfig = remoteConfig;
        this.navigator = navigator;
    }

    public final Observable<ToolbarState> process(Observable<UIEvent> uiEvents, final Function0<Unit> onEmptyBackStack) {
        Intrinsics.checkNotNullParameter(uiEvents, "uiEvents");
        Intrinsics.checkNotNullParameter(onEmptyBackStack, "onEmptyBackStack");
        Observable observablePublish = uiEvents.publish(new Function() { // from class: com.onfido.workflow.internal.ui.processor.BackstackEventsProcessor.process.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<ToolbarState> apply(Observable<UIEvent> sharedUIEvents) {
                Intrinsics.checkNotNullParameter(sharedUIEvents, "sharedUIEvents");
                Observable<U> observableCast = sharedUIEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.BackstackEventsProcessor$process$1$apply$$inlined$filterIsInstance$1
                    @Override // io.reactivex.rxjava3.functions.Predicate
                    public final boolean test(Object it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return it instanceof UIEvent.OnBackstackChange;
                    }
                }).cast(UIEvent.OnBackstackChange.class);
                Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
                Observable<R> observableCompose = observableCast.compose(BackstackEventsProcessor.this.processBackStackChange(onEmptyBackStack));
                Observable<U> observableCast2 = sharedUIEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.BackstackEventsProcessor$process$1$apply$$inlined$filterIsInstance$2
                    @Override // io.reactivex.rxjava3.functions.Predicate
                    public final boolean test(Object it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return it instanceof UIEvent.OnBackPressed;
                    }
                }).cast(UIEvent.OnBackPressed.class);
                Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
                return Observable.merge(observableCompose, observableCast2.compose(BackstackEventsProcessor.this.processBackButton()));
            }
        });
        Intrinsics.checkNotNullExpressionValue(observablePublish, "publish(...)");
        return observablePublish;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ObservableTransformer<UIEvent.OnBackstackChange, ToolbarState> processBackStackChange(final Function0<Unit> onEmptyBackStack) {
        return new ObservableTransformer() { // from class: com.onfido.workflow.internal.ui.processor.BackstackEventsProcessor$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.core.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                return BackstackEventsProcessor.processBackStackChange$lambda$0(onEmptyBackStack, this, observable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource processBackStackChange$lambda$0(final Function0 onEmptyBackStack, final BackstackEventsProcessor this$0, Observable upstream) {
        Intrinsics.checkNotNullParameter(onEmptyBackStack, "$onEmptyBackStack");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        return upstream.map(new BackstackEventsProcessor$sam$io_reactivex_rxjava3_functions_Function$0(new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.processor.BackstackEventsProcessor$processBackStackChange$1$1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UIEvent.OnBackstackChange) obj).getBackstackSnapshot();
            }
        })).map(new Function() { // from class: com.onfido.workflow.internal.ui.processor.BackstackEventsProcessor$processBackStackChange$1$2
            @Override // io.reactivex.rxjava3.functions.Function
            public final ToolbarState apply(List<? extends Screen> backStackSnapshot) {
                Intrinsics.checkNotNullParameter(backStackSnapshot, "backStackSnapshot");
                if (backStackSnapshot.isEmpty()) {
                    onEmptyBackStack.invoke();
                }
                List listExcludeLoadingScreen = this$0.excludeLoadingScreen(backStackSnapshot);
                Screen screen = (Screen) CollectionsKt.lastOrNull(listExcludeLoadingScreen);
                if (screen == null) {
                    return new ToolbarState(false, false, false, 7, null);
                }
                return new ToolbarState(listExcludeLoadingScreen.size() > 1, this$0.remoteConfig.isStudioUserFlowExitEnabled() && !CollectionsKt.contains(CollectionsKt.listOf(LoadingScreen.INSTANCE), screen), screen instanceof HostedWebModuleScreen);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ObservableTransformer<UIEvent.OnBackPressed, ToolbarState> processBackButton() {
        return new ObservableTransformer() { // from class: com.onfido.workflow.internal.ui.processor.BackstackEventsProcessor$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                return BackstackEventsProcessor.processBackButton$lambda$1(this.f$0, observable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource processBackButton$lambda$1(final BackstackEventsProcessor this$0, Observable upstream) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        return upstream.map(new BackstackEventsProcessor$sam$io_reactivex_rxjava3_functions_Function$0(new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.processor.BackstackEventsProcessor$processBackButton$1$1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UIEvent.OnBackPressed) obj).getBackstackSnapshot();
            }
        })).doOnNext(new Consumer() { // from class: com.onfido.workflow.internal.ui.processor.BackstackEventsProcessor$processBackButton$1$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(List<? extends Screen> backStackSnapshot) {
                Intrinsics.checkNotNullParameter(backStackSnapshot, "backStackSnapshot");
                if (this.this$0.excludeLoadingScreen(backStackSnapshot).size() > 1) {
                    this.this$0.navigator.exitCurrentScreen();
                }
            }
        }).ignoreElements().toObservable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Screen> excludeLoadingScreen(List<? extends Screen> list) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (!(((Screen) obj) instanceof LoadingScreen)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
