package com.onfido.workflow.internal.ui;

import androidx.lifecycle.ViewModel;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.android.sdk.workflow.internal.WaitingScreenThreshold;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.internal.data.WorkflowTaskDataSource;
import com.onfido.workflow.internal.ui.model.FlowTask;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: WorkflowLoadingViewModel.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001%B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\u0006\u0010\u001e\u001a\u00020\u001dJ\u0006\u0010\u001f\u001a\u00020\u001dJ\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0002J\f\u0010#\u001a\u00020$*\u00020\"H\u0002R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/onfido/workflow/internal/ui/WorkflowLoadingViewModel;", "Landroidx/lifecycle/ViewModel;", "workflowTaskDataSource", "Lcom/onfido/workflow/internal/data/WorkflowTaskDataSource;", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "waitingScreenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;", "(Lcom/onfido/workflow/internal/data/WorkflowTaskDataSource;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;)V", "_viewState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/onfido/workflow/internal/ui/WorkflowLoadingViewModel$ViewState;", "disposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "previousTask", "", "viewState", "Lkotlinx/coroutines/flow/StateFlow;", "getViewState", "()Lkotlinx/coroutines/flow/StateFlow;", "waitingReason", "createWaitingMessagesList", "", "Lcom/onfido/workflow/internal/ui/WorkflowLoadingViewModel$ViewState$ShowWaitingMessages;", "observeWaitingMessages", "Lio/reactivex/rxjava3/core/Observable;", "onCleared", "", "onPause", "onResume", "trackInBetweenStudioTask", "task", "Lcom/onfido/workflow/internal/ui/model/FlowTask;", "shouldShowWaitingMessages", "", "ViewState", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WorkflowLoadingViewModel extends ViewModel {
    private final MutableStateFlow<ViewState> _viewState;
    private final CompositeDisposable disposable;
    private String previousTask;
    private final OnfidoRemoteConfig remoteConfig;
    private final SchedulersProvider schedulersProvider;
    private final StateFlow<ViewState> viewState;
    private String waitingReason;
    private final WaitingScreenTracker waitingScreenTracker;
    private final WorkflowTaskDataSource workflowTaskDataSource;

    public final StateFlow<ViewState> getViewState() {
        return this.viewState;
    }

    @Inject
    public WorkflowLoadingViewModel(WorkflowTaskDataSource workflowTaskDataSource, OnfidoRemoteConfig remoteConfig, SchedulersProvider schedulersProvider, WaitingScreenTracker waitingScreenTracker) {
        Intrinsics.checkNotNullParameter(workflowTaskDataSource, "workflowTaskDataSource");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(waitingScreenTracker, "waitingScreenTracker");
        this.workflowTaskDataSource = workflowTaskDataSource;
        this.remoteConfig = remoteConfig;
        this.schedulersProvider = schedulersProvider;
        this.waitingScreenTracker = waitingScreenTracker;
        this.disposable = new CompositeDisposable();
        MutableStateFlow<ViewState> MutableStateFlow = StateFlowKt.MutableStateFlow(ViewState.ShowProgressOnly.INSTANCE);
        this._viewState = MutableStateFlow;
        this.viewState = MutableStateFlow;
        this.previousTask = "";
        this.waitingReason = "";
    }

    public final void onResume() {
        CompositeDisposable compositeDisposable = this.disposable;
        Disposable disposableSubscribe = this.workflowTaskDataSource.getFlowTask().distinctUntilChanged().switchMap(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowLoadingViewModel.onResume.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends ViewState> apply(FlowTask flowTask) {
                Observable observableJust;
                WorkflowLoadingViewModel workflowLoadingViewModel = WorkflowLoadingViewModel.this;
                Intrinsics.checkNotNull(flowTask);
                workflowLoadingViewModel.trackInBetweenStudioTask(flowTask);
                if (WorkflowLoadingViewModel.this.shouldShowWaitingMessages(flowTask)) {
                    observableJust = WorkflowLoadingViewModel.this.observeWaitingMessages();
                } else {
                    observableJust = Observable.just(ViewState.ShowProgressOnly.INSTANCE);
                    Intrinsics.checkNotNull(observableJust);
                }
                return observableJust;
            }
        }).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowLoadingViewModel.onResume.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(ViewState viewState) {
                Intrinsics.checkNotNullParameter(viewState, "viewState");
                WorkflowLoadingViewModel.this._viewState.setValue(viewState);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowLoadingViewModel.onResume.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error during subscribing to workflow state", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    public final void onPause() {
        this.waitingScreenTracker.trackWaitingScreenEnded(WaitingScreenTracker.WaitingTaskTypes.IN_BETWEEN_STUDIO_TASKS, this.waitingReason);
        this.disposable.clear();
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.disposable.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void trackInBetweenStudioTask(FlowTask task) {
        if (task instanceof FlowTask.AwaitingNextTask) {
            String str = this.previousTask;
            this.waitingReason = str;
            this.waitingScreenTracker.trackWaitingScreenStart(WaitingScreenTracker.WaitingTaskTypes.IN_BETWEEN_STUDIO_TASKS, str, CollectionsKt.toList(this.remoteConfig.getStudioWaitingScreensTimeThresholds().values()));
        } else if (task instanceof FlowTask.InteractiveTask) {
            this.waitingScreenTracker.trackWaitingScreenCompletion(WaitingScreenTracker.WaitingTaskTypes.IN_BETWEEN_STUDIO_TASKS, this.waitingReason);
            this.previousTask = ((FlowTask.InteractiveTask) task).getWorkflowTask().getId();
        } else if (task instanceof FlowTask.NonInteractiveTask) {
            this.waitingScreenTracker.trackWaitingScreenCompletion(WaitingScreenTracker.WaitingTaskTypes.IN_BETWEEN_STUDIO_TASKS, this.waitingReason);
            this.previousTask = WaitingScreenTracker.StudioFlowWaitingReason.NON_INTERACTIVE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldShowWaitingMessages(FlowTask flowTask) {
        return flowTask instanceof FlowTask.AwaitingNextTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<ViewState.ShowWaitingMessages> observeWaitingMessages() {
        Observable<ViewState.ShowWaitingMessages> observableConcatMap = Observable.fromIterable(createWaitingMessagesList()).concatMap(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowLoadingViewModel.observeWaitingMessages.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends ViewState.ShowWaitingMessages> apply(ViewState.ShowWaitingMessages message) {
                Intrinsics.checkNotNullParameter(message, "message");
                Observable observableJust = Observable.just(message);
                Long delayInMs = message.getDelayInMs();
                return observableJust.delay(delayInMs != null ? delayInMs.longValue() : 0L, TimeUnit.MILLISECONDS, WorkflowLoadingViewModel.this.schedulersProvider.getSingle());
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableConcatMap, "concatMap(...)");
        return observableConcatMap;
    }

    private final List<ViewState.ShowWaitingMessages> createWaitingMessagesList() {
        return CollectionsKt.mutableListOf(new ViewState.ShowWaitingMessages(R.string.onfido_studio_between_interactive_tasks_waiting_title_1, R.string.onfido_studio_between_interactive_tasks_waiting_subtitle_1, null), new ViewState.ShowWaitingMessages(R.string.onfido_studio_between_interactive_tasks_waiting_title_2, R.string.onfido_studio_between_interactive_tasks_waiting_subtitle_2, this.remoteConfig.getStudioWaitingScreensTimeThresholds().get(WaitingScreenThreshold.ShortWaiting1.INSTANCE)), new ViewState.ShowWaitingMessages(R.string.onfido_studio_between_interactive_tasks_waiting_title_2, R.string.onfido_studio_between_interactive_tasks_waiting_subtitle_3, this.remoteConfig.getStudioWaitingScreensTimeThresholds().get(WaitingScreenThreshold.ShortWaiting2.INSTANCE)), new ViewState.ShowWaitingMessages(R.string.onfido_studio_between_interactive_tasks_waiting_title_3, R.string.onfido_studio_between_interactive_tasks_waiting_subtitle_4, this.remoteConfig.getStudioWaitingScreensTimeThresholds().get(WaitingScreenThreshold.MediumWaiting1.INSTANCE)), new ViewState.ShowWaitingMessages(R.string.onfido_studio_between_interactive_tasks_waiting_title_4, R.string.onfido_studio_between_interactive_tasks_waiting_subtitle_5, this.remoteConfig.getStudioWaitingScreensTimeThresholds().get(WaitingScreenThreshold.MediumWaiting2.INSTANCE)), new ViewState.ShowWaitingMessages(R.string.onfido_studio_between_interactive_tasks_waiting_title_3, R.string.onfido_studio_between_interactive_tasks_waiting_subtitle_4, this.remoteConfig.getStudioWaitingScreensTimeThresholds().get(WaitingScreenThreshold.LongWaiting1.INSTANCE)), new ViewState.ShowWaitingMessages(R.string.onfido_studio_between_interactive_tasks_waiting_title_4, R.string.onfido_studio_between_interactive_tasks_waiting_subtitle_5, this.remoteConfig.getStudioWaitingScreensTimeThresholds().get(WaitingScreenThreshold.LongWaiting2.INSTANCE)));
    }

    /* compiled from: WorkflowLoadingViewModel.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/ui/WorkflowLoadingViewModel$ViewState;", "", "()V", "ShowProgressOnly", "ShowWaitingMessages", "Lcom/onfido/workflow/internal/ui/WorkflowLoadingViewModel$ViewState$ShowProgressOnly;", "Lcom/onfido/workflow/internal/ui/WorkflowLoadingViewModel$ViewState$ShowWaitingMessages;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class ViewState {
        public /* synthetic */ ViewState(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: WorkflowLoadingViewModel.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/WorkflowLoadingViewModel$ViewState$ShowProgressOnly;", "Lcom/onfido/workflow/internal/ui/WorkflowLoadingViewModel$ViewState;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class ShowProgressOnly extends ViewState {
            public static final ShowProgressOnly INSTANCE = new ShowProgressOnly();

            private ShowProgressOnly() {
                super(null);
            }
        }

        private ViewState() {
        }

        /* compiled from: WorkflowLoadingViewModel.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\tJ.\u0010\u0011\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/onfido/workflow/internal/ui/WorkflowLoadingViewModel$ViewState$ShowWaitingMessages;", "Lcom/onfido/workflow/internal/ui/WorkflowLoadingViewModel$ViewState;", "titleId", "", "subTitleId", "delayInMs", "", "(IILjava/lang/Long;)V", "getDelayInMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getSubTitleId", "()I", "getTitleId", "component1", "component2", "component3", Constants.COPY_TYPE, "(IILjava/lang/Long;)Lcom/onfido/workflow/internal/ui/WorkflowLoadingViewModel$ViewState$ShowWaitingMessages;", "equals", "", "other", "", "hashCode", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class ShowWaitingMessages extends ViewState {
            private final Long delayInMs;
            private final int subTitleId;
            private final int titleId;

            public static /* synthetic */ ShowWaitingMessages copy$default(ShowWaitingMessages showWaitingMessages, int i, int i2, Long l, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    i = showWaitingMessages.titleId;
                }
                if ((i3 & 2) != 0) {
                    i2 = showWaitingMessages.subTitleId;
                }
                if ((i3 & 4) != 0) {
                    l = showWaitingMessages.delayInMs;
                }
                return showWaitingMessages.copy(i, i2, l);
            }

            /* renamed from: component1, reason: from getter */
            public final int getTitleId() {
                return this.titleId;
            }

            /* renamed from: component2, reason: from getter */
            public final int getSubTitleId() {
                return this.subTitleId;
            }

            /* renamed from: component3, reason: from getter */
            public final Long getDelayInMs() {
                return this.delayInMs;
            }

            public final ShowWaitingMessages copy(int titleId, int subTitleId, Long delayInMs) {
                return new ShowWaitingMessages(titleId, subTitleId, delayInMs);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ShowWaitingMessages)) {
                    return false;
                }
                ShowWaitingMessages showWaitingMessages = (ShowWaitingMessages) other;
                return this.titleId == showWaitingMessages.titleId && this.subTitleId == showWaitingMessages.subTitleId && Intrinsics.areEqual(this.delayInMs, showWaitingMessages.delayInMs);
            }

            public final Long getDelayInMs() {
                return this.delayInMs;
            }

            public final int getSubTitleId() {
                return this.subTitleId;
            }

            public final int getTitleId() {
                return this.titleId;
            }

            public int hashCode() {
                int iHashCode = ((Integer.hashCode(this.titleId) * 31) + Integer.hashCode(this.subTitleId)) * 31;
                Long l = this.delayInMs;
                return iHashCode + (l == null ? 0 : l.hashCode());
            }

            public String toString() {
                return "ShowWaitingMessages(titleId=" + this.titleId + ", subTitleId=" + this.subTitleId + ", delayInMs=" + this.delayInMs + ")";
            }

            public ShowWaitingMessages(int i, int i2, Long l) {
                super(null);
                this.titleId = i;
                this.subTitleId = i2;
                this.delayInMs = l;
            }
        }
    }
}
