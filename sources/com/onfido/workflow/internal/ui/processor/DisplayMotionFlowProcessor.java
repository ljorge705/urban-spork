package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifact;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.internal.ui.MotionScreen;
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
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;

/* compiled from: DisplayMotionFlowProcessor.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bH\u0002J\"\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\b2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\bJ\u001c\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/DisplayMotionFlowProcessor;", "", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "submitTaskCompletionUseCase", "Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;", "(Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;)V", "openMotionAndWaitForResult", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "motionTask", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$FaceMotionTask;", "uiEvents", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "process", "", "uiEventObservable", "submitTaskCompletion", "Lio/reactivex/rxjava3/core/Completable;", "task", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "uploadId", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class DisplayMotionFlowProcessor {
    private final Navigator navigator;
    private final SubmitTaskCompletionUseCase submitTaskCompletionUseCase;

    @Inject
    public DisplayMotionFlowProcessor(Navigator navigator, SubmitTaskCompletionUseCase submitTaskCompletionUseCase) {
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(submitTaskCompletionUseCase, "submitTaskCompletionUseCase");
        this.navigator = navigator;
        this.submitTaskCompletionUseCase = submitTaskCompletionUseCase;
    }

    public final Observable<Unit> process(final WorkflowTask.FaceMotionTask motionTask, Observable<UIEvent> uiEventObservable) {
        Intrinsics.checkNotNullParameter(motionTask, "motionTask");
        Intrinsics.checkNotNullParameter(uiEventObservable, "uiEventObservable");
        Observable observableSwitchMap = openMotionAndWaitForResult(motionTask, uiEventObservable).switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.processor.DisplayMotionFlowProcessor.process.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends Unit> apply(UploadedArtifact uploadedArtifact) {
                Intrinsics.checkNotNullParameter(uploadedArtifact, "uploadedArtifact");
                Completable completableSubmitTaskCompletion = DisplayMotionFlowProcessor.this.submitTaskCompletion(motionTask, uploadedArtifact.getId());
                final DisplayMotionFlowProcessor displayMotionFlowProcessor = DisplayMotionFlowProcessor.this;
                return completableSubmitTaskCompletion.doOnSubscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.processor.DisplayMotionFlowProcessor.process.1.1
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Disposable it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        NavigatorExtKt.backToWorkflowRoot(displayMotionFlowProcessor.navigator);
                    }
                }).andThen(Observable.just(Unit.INSTANCE));
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableSwitchMap, "switchMap(...)");
        return observableSwitchMap;
    }

    private final Observable<UploadedArtifact> openMotionAndWaitForResult(final WorkflowTask.FaceMotionTask motionTask, Observable<UIEvent> uiEvents) {
        Completable completableFromAction = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.DisplayMotionFlowProcessor$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DisplayMotionFlowProcessor.openMotionAndWaitForResult$lambda$0(this.f$0, motionTask);
            }
        });
        Observable<U> observableCast = uiEvents.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.DisplayMotionFlowProcessor$openMotionAndWaitForResult$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof UIEvent.OnFragmentResult.OnMotionResult;
            }
        }).cast(UIEvent.OnFragmentResult.OnMotionResult.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        final AnonymousClass2 anonymousClass2 = new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.processor.DisplayMotionFlowProcessor.openMotionAndWaitForResult.2
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((UIEvent.OnFragmentResult.OnMotionResult) obj).getAvcHostResult();
            }
        };
        Observable map = observableCast.map(new Function(anonymousClass2) { // from class: com.onfido.workflow.internal.ui.processor.DisplayMotionFlowProcessor$sam$io_reactivex_rxjava3_functions_Function$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(anonymousClass2, "function");
                this.function = anonymousClass2;
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public final /* synthetic */ Object apply(Object obj) {
                return this.function.invoke(obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable observableCast2 = map.filter(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.DisplayMotionFlowProcessor$openMotionAndWaitForResult$$inlined$filterIsInstance$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof MotionHostFragment.AvcHostResult.Completed;
            }
        }).cast(MotionHostFragment.AvcHostResult.Completed.class);
        Intrinsics.checkNotNullExpressionValue(observableCast2, "cast(...)");
        Observable observableAndThen = completableFromAction.andThen(observableCast2);
        final AnonymousClass3 anonymousClass3 = new PropertyReference1Impl() { // from class: com.onfido.workflow.internal.ui.processor.DisplayMotionFlowProcessor.openMotionAndWaitForResult.3
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((MotionHostFragment.AvcHostResult.Completed) obj).getUploadedArtifact();
            }
        };
        Observable<UploadedArtifact> map2 = observableAndThen.map(new Function(anonymousClass3) { // from class: com.onfido.workflow.internal.ui.processor.DisplayMotionFlowProcessor$sam$io_reactivex_rxjava3_functions_Function$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(anonymousClass3, "function");
                this.function = anonymousClass3;
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public final /* synthetic */ Object apply(Object obj) {
                return this.function.invoke(obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map2, "map(...)");
        return map2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openMotionAndWaitForResult$lambda$0(DisplayMotionFlowProcessor this$0, WorkflowTask.FaceMotionTask motionTask) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(motionTask, "$motionTask");
        this$0.navigator.navigateTo(new MotionScreen(motionTask.getOptions(), false, 2, null));
    }

    static /* synthetic */ Completable submitTaskCompletion$default(DisplayMotionFlowProcessor displayMotionFlowProcessor, WorkflowTask workflowTask, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return displayMotionFlowProcessor.submitTaskCompletion(workflowTask, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Completable submitTaskCompletion(final WorkflowTask task, String uploadId) {
        List<String> listEmptyList;
        if (uploadId == null || (listEmptyList = CollectionsKt.listOf(uploadId)) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        Completable completableOnErrorComplete = this.submitTaskCompletionUseCase.execute(task, listEmptyList).onErrorComplete(new Predicate() { // from class: com.onfido.workflow.internal.ui.processor.DisplayMotionFlowProcessor.submitTaskCompletion.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error during submitting Motion Capture: " + task + " completion", new Object[0]);
                return true;
            }
        });
        Intrinsics.checkNotNullExpressionValue(completableOnErrorComplete, "onErrorComplete(...)");
        return completableOnErrorComplete;
    }
}
