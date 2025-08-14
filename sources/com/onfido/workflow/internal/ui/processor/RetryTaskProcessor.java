package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.internal.ui.RetryWorkflowScreen;
import com.onfido.workflow.internal.ui.model.UIEvent;
import com.onfido.workflow.internal.ui.retry.RetryWorkflowViewDescriptor;
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
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RetryTaskProcessor.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/RetryTaskProcessor;", "", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "completionUseCase", "Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;", "(Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;)V", "navigateToRetryWorkflowScreen", "Lio/reactivex/rxjava3/core/Completable;", "task", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$RetryTask;", "process", "Lio/reactivex/rxjava3/core/Observable;", "", "retryTask", "uiEvents", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class RetryTaskProcessor {
    private final SubmitTaskCompletionUseCase completionUseCase;
    private final Navigator navigator;

    /* compiled from: RetryTaskProcessor.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WorkflowTask.RetryTask.RetryReason.values().length];
            try {
                iArr[WorkflowTask.RetryTask.RetryReason.GENERIC_DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[WorkflowTask.RetryTask.RetryReason.DEFAULT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[WorkflowTask.RetryTask.RetryReason.EXPIRED_DOCUMENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[WorkflowTask.RetryTask.RetryReason.UNACCEPTED_DOCUMENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[WorkflowTask.RetryTask.RetryReason.GENERIC_SELFIE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[WorkflowTask.RetryTask.RetryReason.CUSTOM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public RetryTaskProcessor(Navigator navigator, SubmitTaskCompletionUseCase completionUseCase) {
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(completionUseCase, "completionUseCase");
        this.navigator = navigator;
        this.completionUseCase = completionUseCase;
    }

    public final Observable<Unit> process(final WorkflowTask.RetryTask retryTask, Observable<UIEvent> uiEvents) {
        Intrinsics.checkNotNullParameter(retryTask, "retryTask");
        Intrinsics.checkNotNullParameter(uiEvents, "uiEvents");
        Completable completableNavigateToRetryWorkflowScreen = navigateToRetryWorkflowScreen(retryTask);
        ObservableSource observableSourceCast = uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.RetryTaskProcessor$process$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnWorkflowRetryClick;
            }
        }).cast(UIEvent.OnFragmentResult.OnWorkflowRetryClick.class);
        Intrinsics.checkNotNullExpressionValue(observableSourceCast, "cast(...)");
        Observable<Unit> observableFlatMap = completableNavigateToRetryWorkflowScreen.andThen(observableSourceCast).flatMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.RetryTaskProcessor.process.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends Unit> apply(UIEvent.OnFragmentResult.OnWorkflowRetryClick it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Completable completableExecute = RetryTaskProcessor.this.completionUseCase.execute(retryTask);
                final RetryTaskProcessor retryTaskProcessor = RetryTaskProcessor.this;
                return completableExecute.doOnSubscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.processor.RetryTaskProcessor.process.1.1
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Disposable it2) {
                        Intrinsics.checkNotNullParameter(it2, "it");
                        NavigatorExtKt.backToWorkflowRoot(retryTaskProcessor.navigator);
                    }
                }).andThen(Observable.just(Unit.INSTANCE));
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMap, "flatMap(...)");
        return observableFlatMap;
    }

    private final Completable navigateToRetryWorkflowScreen(final WorkflowTask.RetryTask task) {
        Completable completableFromAction = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.RetryTaskProcessor$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                RetryTaskProcessor.navigateToRetryWorkflowScreen$lambda$1(task, this);
            }
        });
        Intrinsics.checkNotNullExpressionValue(completableFromAction, "fromAction(...)");
        return completableFromAction;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void navigateToRetryWorkflowScreen$lambda$1(WorkflowTask.RetryTask task, RetryTaskProcessor this$0) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(task, "$task");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WorkflowTask.RetryTask.RetryReason reason = task.getReason();
        switch (reason == null ? -1 : WhenMappings.$EnumSwitchMapping$0[reason.ordinal()]) {
            case -1:
            case 1:
            case 2:
                i = R.string.onfido_retry_feedback_id_generic_title;
                break;
            case 0:
            default:
                throw new NoWhenBranchMatchedException();
            case 3:
                i = R.string.onfido_retry_feedback_id_expired_title;
                break;
            case 4:
                i = R.string.onfido_retry_feedback_id_unaccepted_title;
                break;
            case 5:
                i = R.string.onfido_retry_feedback_selfie_generic_title;
                break;
            case 6:
                i = R.string.onfido_retry_feedback_custom_title;
                break;
        }
        WorkflowTask.RetryTask.RetryReason reason2 = task.getReason();
        switch (reason2 != null ? WhenMappings.$EnumSwitchMapping$0[reason2.ordinal()] : -1) {
            case -1:
            case 1:
            case 2:
                i2 = R.string.onfido_retry_feedback_id_generic_subtitle;
                break;
            case 0:
            default:
                throw new NoWhenBranchMatchedException();
            case 3:
                i2 = R.string.onfido_retry_feedback_id_expired_subtitle;
                break;
            case 4:
                i2 = R.string.onfido_retry_feedback_id_unaccepted_subtitle;
                break;
            case 5:
                i2 = R.string.onfido_retry_feedback_selfie_generic_subtitle;
                break;
            case 6:
                i2 = R.string.onfido_retry_feedback_custom_subtitle;
                break;
        }
        RetryWorkflowViewDescriptor.StringResIds stringResIds = new RetryWorkflowViewDescriptor.StringResIds(i, i2, R.string.onfido_retry_feedback_button_primary);
        WorkflowTask.RetryTask.RetryTexts customTexts = task.getCustomTexts();
        this$0.navigator.navigateTo(new RetryWorkflowScreen(new RetryWorkflowViewDescriptor(stringResIds, customTexts != null ? new RetryWorkflowViewDescriptor.Texts(customTexts.getTitle(), customTexts.getDescription(), customTexts.getButtonText()) : null)));
    }
}
