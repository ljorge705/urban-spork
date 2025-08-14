package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.internal.ui.PermissionsScreen;
import com.onfido.workflow.internal.ui.model.UIEvent;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;

/* compiled from: PermissionsFlowHelper.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u0006\u0010\f\u001a\u00020\tJ$\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u0006\u0010\f\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/PermissionsFlowHelper;", "", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "permissionsManager", "Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;", "(Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;)V", "checkRuntimePermissions", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "uiEvents", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "captureStepDataBundle", "openPermissionsScreen", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class PermissionsFlowHelper {
    private final Navigator navigator;
    private final RuntimePermissionsManager permissionsManager;

    @Inject
    public PermissionsFlowHelper(Navigator navigator, RuntimePermissionsManager permissionsManager) {
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(permissionsManager, "permissionsManager");
        this.navigator = navigator;
        this.permissionsManager = permissionsManager;
    }

    public final Observable<CaptureStepDataBundle> checkRuntimePermissions(final Observable<UIEvent> uiEvents, final CaptureStepDataBundle captureStepDataBundle) {
        Intrinsics.checkNotNullParameter(uiEvents, "uiEvents");
        Intrinsics.checkNotNullParameter(captureStepDataBundle, "captureStepDataBundle");
        Observable<CaptureStepDataBundle> observableDefer = Observable.defer(new Supplier() { // from class: com.onfido.workflow.internal.ui.processor.PermissionsFlowHelper$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Supplier
            public final Object get() {
                return PermissionsFlowHelper.checkRuntimePermissions$lambda$0(this.f$0, captureStepDataBundle, uiEvents);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableDefer, "defer(...)");
        return observableDefer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource checkRuntimePermissions$lambda$0(PermissionsFlowHelper this$0, CaptureStepDataBundle captureStepDataBundle, Observable uiEvents) {
        Observable<CaptureStepDataBundle> observableOpenPermissionsScreen;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(captureStepDataBundle, "$captureStepDataBundle");
        Intrinsics.checkNotNullParameter(uiEvents, "$uiEvents");
        if (this$0.permissionsManager.hasPermissionsForCaptureType(captureStepDataBundle.getCaptureType())) {
            observableOpenPermissionsScreen = Observable.just(captureStepDataBundle);
            Intrinsics.checkNotNull(observableOpenPermissionsScreen);
        } else {
            observableOpenPermissionsScreen = this$0.openPermissionsScreen(uiEvents, captureStepDataBundle);
        }
        return observableOpenPermissionsScreen;
    }

    private final Observable<CaptureStepDataBundle> openPermissionsScreen(Observable<UIEvent> uiEvents, final CaptureStepDataBundle captureStepDataBundle) {
        Completable completableFromAction = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.PermissionsFlowHelper$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                PermissionsFlowHelper.openPermissionsScreen$lambda$1(this.f$0, captureStepDataBundle);
            }
        });
        Observable<U> observableCast = uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.PermissionsFlowHelper$openPermissionsScreen$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnPermissionsScreenResult;
            }
        }).cast(UIEvent.OnFragmentResult.OnPermissionsScreenResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable observableDoOnNext = observableCast.doOnNext(new Consumer() { // from class: com.onfido.workflow.internal.ui.processor.PermissionsFlowHelper.openPermissionsScreen.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(UIEvent.OnFragmentResult.OnPermissionsScreenResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                PermissionsFlowHelper.this.navigator.exitCurrentScreen();
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableDoOnNext, "doOnNext(...)");
        Observable observableCast2 = observableDoOnNext.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.PermissionsFlowHelper$openPermissionsScreen$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnPermissionsScreenResult.Granted;
            }
        }).cast(UIEvent.OnFragmentResult.OnPermissionsScreenResult.Granted.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        final AnonymousClass3 anonymousClass3 = new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.processor.PermissionsFlowHelper.openPermissionsScreen.3
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UIEvent.OnFragmentResult.OnPermissionsScreenResult.Granted) obj).getCaptureStepDataBundle();
            }
        };
        Observable<CaptureStepDataBundle> observableAndThen = completableFromAction.andThen(observableCast2.map(new Function(anonymousClass3) { // from class: com.onfido.workflow.internal.ui.processor.PermissionsFlowHelper$sam$io_reactivex_rxjava3_functions_Function$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(anonymousClass3, "function");
                this.function = anonymousClass3;
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public final /* synthetic */ Object apply(Object obj) {
                return this.function.invoke(obj);
            }
        }).take(1L));
        Intrinsics.checkNotNullExpressionValue(observableAndThen, "andThen(...)");
        return observableAndThen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openPermissionsScreen$lambda$1(PermissionsFlowHelper this$0, CaptureStepDataBundle captureStepDataBundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(captureStepDataBundle, "$captureStepDataBundle");
        this$0.navigator.navigateTo(new PermissionsScreen(captureStepDataBundle));
    }
}
