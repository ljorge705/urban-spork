package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostFragment;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.internal.ui.PoaScreen;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PoaFlowProcessor.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0011B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bH\u0002J\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/PoaFlowProcessor;", "", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "completionUseCase", "Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;", "(Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;)V", "openPoaScreenAndWaitForDocumentIdSubmission", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/host/PoaHostFragment$PoaResult;", "uiEvents", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "process", "Lcom/onfido/workflow/internal/ui/processor/PoaFlowProcessor$ProcessorOutcome;", "poaTask", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$ProofOfAddressTask;", "uiEventObservable", "ProcessorOutcome", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class PoaFlowProcessor {
    private final SubmitTaskCompletionUseCase completionUseCase;
    private final Navigator navigator;

    @Inject
    public PoaFlowProcessor(Navigator navigator, SubmitTaskCompletionUseCase completionUseCase) {
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(completionUseCase, "completionUseCase");
        this.navigator = navigator;
        this.completionUseCase = completionUseCase;
    }

    public final Observable<ProcessorOutcome> process(final WorkflowTask.ProofOfAddressTask poaTask, Observable<UIEvent> uiEventObservable) {
        Intrinsics.checkNotNullParameter(poaTask, "poaTask");
        Intrinsics.checkNotNullParameter(uiEventObservable, "uiEventObservable");
        Observable observableSwitchMap = openPoaScreenAndWaitForDocumentIdSubmission(uiEventObservable).switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.PoaFlowProcessor.process.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends ProcessorOutcome> apply(PoaHostFragment.PoaResult result) {
                Observable<T> observableAndThen;
                Intrinsics.checkNotNullParameter(result, "result");
                if (Intrinsics.areEqual(result, PoaHostFragment.PoaResult.Exit.INSTANCE)) {
                    observableAndThen = Observable.just(ProcessorOutcome.Exit.INSTANCE);
                    Intrinsics.checkNotNull(observableAndThen);
                } else if (Intrinsics.areEqual(result, PoaHostFragment.PoaResult.Canceled.INSTANCE)) {
                    observableAndThen = Observable.just(ProcessorOutcome.Cancelled.INSTANCE);
                    Intrinsics.checkNotNull(observableAndThen);
                } else {
                    if (!(result instanceof PoaHostFragment.PoaResult.OnDocumentSubmittedResult)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    Completable completableExecute = PoaFlowProcessor.this.completionUseCase.execute(poaTask, ((PoaHostFragment.PoaResult.OnDocumentSubmittedResult) result).getDocumentId());
                    final PoaFlowProcessor poaFlowProcessor = PoaFlowProcessor.this;
                    observableAndThen = completableExecute.doOnSubscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.processor.PoaFlowProcessor.process.1.1
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        public final void accept(Disposable it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            NavigatorExtKt.backToWorkflowRoot(poaFlowProcessor.navigator);
                        }
                    }).andThen(Observable.just(ProcessorOutcome.PoaFlowFinished.INSTANCE));
                    Intrinsics.checkNotNull(observableAndThen);
                }
                return observableAndThen;
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableSwitchMap, "switchMap(...)");
        return observableSwitchMap;
    }

    private final Observable<PoaHostFragment.PoaResult> openPoaScreenAndWaitForDocumentIdSubmission(Observable<UIEvent> uiEvents) {
        Completable completableFromAction = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.PoaFlowProcessor$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                PoaFlowProcessor.openPoaScreenAndWaitForDocumentIdSubmission$lambda$0(this.f$0);
            }
        });
        Observable<U> observableCast = uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.PoaFlowProcessor$openPoaScreenAndWaitForDocumentIdSubmission$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnPoaSubmissionResult;
            }
        }).cast(UIEvent.OnFragmentResult.OnPoaSubmissionResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Observable<PoaHostFragment.PoaResult> observableAndThen = completableFromAction.andThen(observableCast.map(new Function() { // from class: com.onfido.workflow.internal.ui.processor.PoaFlowProcessor.openPoaScreenAndWaitForDocumentIdSubmission.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final PoaHostFragment.PoaResult apply(UIEvent.OnFragmentResult.OnPoaSubmissionResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getPoaResult();
            }
        }));
        Intrinsics.checkNotNullExpressionValue(observableAndThen, "andThen(...)");
        return observableAndThen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openPoaScreenAndWaitForDocumentIdSubmission$lambda$0(PoaFlowProcessor this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.navigator.navigateTo(new PoaScreen(false, 1, null));
    }

    /* compiled from: PoaFlowProcessor.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/PoaFlowProcessor$ProcessorOutcome;", "", "()V", "Cancelled", "Exit", "PoaFlowFinished", "Lcom/onfido/workflow/internal/ui/processor/PoaFlowProcessor$ProcessorOutcome$Cancelled;", "Lcom/onfido/workflow/internal/ui/processor/PoaFlowProcessor$ProcessorOutcome$Exit;", "Lcom/onfido/workflow/internal/ui/processor/PoaFlowProcessor$ProcessorOutcome$PoaFlowFinished;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class ProcessorOutcome {
        public /* synthetic */ ProcessorOutcome(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: PoaFlowProcessor.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/PoaFlowProcessor$ProcessorOutcome$PoaFlowFinished;", "Lcom/onfido/workflow/internal/ui/processor/PoaFlowProcessor$ProcessorOutcome;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class PoaFlowFinished extends ProcessorOutcome {
            public static final PoaFlowFinished INSTANCE = new PoaFlowFinished();

            private PoaFlowFinished() {
                super(null);
            }
        }

        private ProcessorOutcome() {
        }

        /* compiled from: PoaFlowProcessor.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/PoaFlowProcessor$ProcessorOutcome$Cancelled;", "Lcom/onfido/workflow/internal/ui/processor/PoaFlowProcessor$ProcessorOutcome;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Cancelled extends ProcessorOutcome {
            public static final Cancelled INSTANCE = new Cancelled();

            private Cancelled() {
                super(null);
            }
        }

        /* compiled from: PoaFlowProcessor.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/PoaFlowProcessor$ProcessorOutcome$Exit;", "Lcom/onfido/workflow/internal/ui/processor/PoaFlowProcessor$ProcessorOutcome;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Exit extends ProcessorOutcome {
            public static final Exit INSTANCE = new Exit();

            private Exit() {
                super(null);
            }
        }
    }
}
