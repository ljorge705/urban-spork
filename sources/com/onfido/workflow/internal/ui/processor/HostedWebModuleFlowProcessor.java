package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.common.data.ThemeDataSource;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.hosted.web.module.model.HostedWebModuleCancelled;
import com.onfido.hosted.web.module.model.HostedWebModuleExit;
import com.onfido.hosted.web.module.model.HostedWebModuleFailed;
import com.onfido.hosted.web.module.model.HostedWebModuleModuleInfo;
import com.onfido.hosted.web.module.model.HostedWebModuleResult;
import com.onfido.hosted.web.module.model.HostedWebModuleSuccess;
import com.onfido.hosted.web.module.model.StudioModuleInfo;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.internal.ui.HostedWebModuleScreen;
import com.onfido.workflow.internal.ui.model.UIEvent;
import com.onfido.workflow.internal.utils.NavigatorExtKt;
import com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase;
import com.onfido.workflow.internal.workflow.WorkflowTask;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;

/* compiled from: HostedWebModuleFlowProcessor.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0018B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ6\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0002J4\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u000f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/HostedWebModuleFlowProcessor;", "", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "submitTaskCompletionUseCase", "Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;", "themeDataSource", "Lcom/onfido/android/sdk/capture/common/data/ThemeDataSource;", "(Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;Lcom/onfido/android/sdk/capture/common/data/ThemeDataSource;)V", "openCaptureSDKModuleAndWaitForApproval", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/hosted/web/module/model/HostedWebModuleResult;", "uiEvents", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "captureSdkModuleTask", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$CaptureSdkModuleTask;", "workflowRunId", "", "withCustomBridgeUrl", "process", "Lcom/onfido/workflow/internal/ui/processor/HostedWebModuleFlowProcessor$ProcessorOutcome;", "task", "uiEventObservable", "bridgeUrl", "ProcessorOutcome", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class HostedWebModuleFlowProcessor {
    private final Navigator navigator;
    private final SubmitTaskCompletionUseCase submitTaskCompletionUseCase;
    private final ThemeDataSource themeDataSource;

    @Inject
    public HostedWebModuleFlowProcessor(Navigator navigator, SubmitTaskCompletionUseCase submitTaskCompletionUseCase, ThemeDataSource themeDataSource) {
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(submitTaskCompletionUseCase, "submitTaskCompletionUseCase");
        Intrinsics.checkNotNullParameter(themeDataSource, "themeDataSource");
        this.navigator = navigator;
        this.submitTaskCompletionUseCase = submitTaskCompletionUseCase;
        this.themeDataSource = themeDataSource;
    }

    public final Observable<ProcessorOutcome> process(String workflowRunId, final WorkflowTask.CaptureSdkModuleTask task, Observable<UIEvent> uiEventObservable, String bridgeUrl) {
        Intrinsics.checkNotNullParameter(workflowRunId, "workflowRunId");
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(uiEventObservable, "uiEventObservable");
        Observable observableSwitchMap = openCaptureSDKModuleAndWaitForApproval(uiEventObservable, task, workflowRunId, bridgeUrl).switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.HostedWebModuleFlowProcessor.process.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends ProcessorOutcome> apply(HostedWebModuleResult result) {
                Observable<T> observableError;
                Intrinsics.checkNotNullParameter(result, "result");
                if (result instanceof HostedWebModuleExit) {
                    observableError = Observable.just(ProcessorOutcome.Exit.INSTANCE);
                    Intrinsics.checkNotNull(observableError);
                } else if (result instanceof HostedWebModuleSuccess) {
                    Completable completableExecuteCustom = HostedWebModuleFlowProcessor.this.submitTaskCompletionUseCase.executeCustom(task, ((HostedWebModuleSuccess) result).getBody());
                    final HostedWebModuleFlowProcessor hostedWebModuleFlowProcessor = HostedWebModuleFlowProcessor.this;
                    observableError = completableExecuteCustom.doOnSubscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.processor.HostedWebModuleFlowProcessor.process.1.1
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        public final void accept(Disposable it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            NavigatorExtKt.backToWorkflowRoot(hostedWebModuleFlowProcessor.navigator);
                        }
                    }).andThen(Observable.just(ProcessorOutcome.Success.INSTANCE));
                    Intrinsics.checkNotNull(observableError);
                } else if (result instanceof HostedWebModuleFailed) {
                    HostedWebModuleFlowProcessor.this.navigator.exitCurrentScreen();
                    observableError = Observable.error(new Throwable(((HostedWebModuleFailed) result).getBody()));
                    Intrinsics.checkNotNull(observableError);
                } else {
                    if (!(result instanceof HostedWebModuleCancelled)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    observableError = Observable.error(new Throwable("Back pressing is not allowed"));
                    Intrinsics.checkNotNull(observableError);
                }
                return observableError;
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableSwitchMap, "switchMap(...)");
        return observableSwitchMap;
    }

    private final Observable<HostedWebModuleResult> openCaptureSDKModuleAndWaitForApproval(Observable<UIEvent> uiEvents, final WorkflowTask.CaptureSdkModuleTask captureSdkModuleTask, final String workflowRunId, final String withCustomBridgeUrl) {
        Completable completableFromAction = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.HostedWebModuleFlowProcessor$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                HostedWebModuleFlowProcessor.openCaptureSDKModuleAndWaitForApproval$lambda$0(captureSdkModuleTask, workflowRunId, this, withCustomBridgeUrl);
            }
        });
        Observable<U> observableCast = uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.HostedWebModuleFlowProcessor$openCaptureSDKModuleAndWaitForApproval$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnHostedWebModuleResult;
            }
        }).cast(UIEvent.OnFragmentResult.OnHostedWebModuleResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        final AnonymousClass2 anonymousClass2 = new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.processor.HostedWebModuleFlowProcessor.openCaptureSDKModuleAndWaitForApproval.2
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UIEvent.OnFragmentResult.OnHostedWebModuleResult) obj).getHostedWebModuleResult();
            }
        };
        Observable<HostedWebModuleResult> observableAndThen = completableFromAction.andThen(observableCast.map(new Function(anonymousClass2) { // from class: com.onfido.workflow.internal.ui.processor.HostedWebModuleFlowProcessor$sam$io_reactivex_rxjava3_functions_Function$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(anonymousClass2, "function");
                this.function = anonymousClass2;
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public final /* synthetic */ Object apply(Object obj) {
                return this.function.invoke(obj);
            }
        }));
        Intrinsics.checkNotNullExpressionValue(observableAndThen, "andThen(...)");
        return observableAndThen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openCaptureSDKModuleAndWaitForApproval$lambda$0(WorkflowTask.CaptureSdkModuleTask captureSdkModuleTask, String workflowRunId, HostedWebModuleFlowProcessor this$0, String str) {
        Intrinsics.checkNotNullParameter(captureSdkModuleTask, "$captureSdkModuleTask");
        Intrinsics.checkNotNullParameter(workflowRunId, "$workflowRunId");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.navigator.navigateTo(new HostedWebModuleScreen(new HostedWebModuleModuleInfo(captureSdkModuleTask.getModuleConfig(), captureSdkModuleTask.getModuleInput(), new StudioModuleInfo(workflowRunId, captureSdkModuleTask.getId(), captureSdkModuleTask.getTaskDefId()), null, 8, null), this$0.themeDataSource.isDarkModeEnabled(), str));
    }

    /* compiled from: HostedWebModuleFlowProcessor.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/HostedWebModuleFlowProcessor$ProcessorOutcome;", "", "()V", "Exit", "Success", "Lcom/onfido/workflow/internal/ui/processor/HostedWebModuleFlowProcessor$ProcessorOutcome$Exit;", "Lcom/onfido/workflow/internal/ui/processor/HostedWebModuleFlowProcessor$ProcessorOutcome$Success;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class ProcessorOutcome {
        public /* synthetic */ ProcessorOutcome(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: HostedWebModuleFlowProcessor.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/HostedWebModuleFlowProcessor$ProcessorOutcome$Success;", "Lcom/onfido/workflow/internal/ui/processor/HostedWebModuleFlowProcessor$ProcessorOutcome;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Success extends ProcessorOutcome {
            public static final Success INSTANCE = new Success();

            private Success() {
                super(null);
            }
        }

        private ProcessorOutcome() {
        }

        /* compiled from: HostedWebModuleFlowProcessor.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/HostedWebModuleFlowProcessor$ProcessorOutcome$Exit;", "Lcom/onfido/workflow/internal/ui/processor/HostedWebModuleFlowProcessor$ProcessorOutcome;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Exit extends ProcessorOutcome {
            public static final Exit INSTANCE = new Exit();

            private Exit() {
                super(null);
            }
        }
    }
}
