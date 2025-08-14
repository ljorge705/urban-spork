package com.onfido.workflow.internal.workflow;

import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.WorkflowConfig;
import com.onfido.workflow.internal.network.WorkflowApi;
import com.onfido.workflow.internal.network.WorkflowResponse;
import com.onfido.workflow.internal.ui.model.FlowTask;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.HttpException;
import retrofit2.Response;

/* compiled from: WorkflowPoller.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0013\u001a\u00020\u0011R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowPoller;", "", "workflowApi", "Lcom/onfido/workflow/internal/network/WorkflowApi;", "workflowConfig", "Lcom/onfido/workflow/WorkflowConfig;", "workflowTaskMapper", "Lcom/onfido/workflow/internal/workflow/WorkflowTaskMapper;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "workflowPollerLocaleProvider", "Lcom/onfido/workflow/internal/workflow/WorkflowPollerLocaleProvider;", "applicantId", "Lcom/onfido/api/client/token/sdk/ApplicantId;", "(Lcom/onfido/workflow/internal/network/WorkflowApi;Lcom/onfido/workflow/WorkflowConfig;Lcom/onfido/workflow/internal/workflow/WorkflowTaskMapper;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/workflow/internal/workflow/WorkflowPollerLocaleProvider;Lcom/onfido/api/client/token/sdk/ApplicantId;)V", "createPoller", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/workflow/internal/ui/model/FlowTask;", "startPolling", "currentTask", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WorkflowPoller {
    private static final int MAX_RETRIES = 5;
    private static final long POLLING_INTERVAL = 2000;
    private static final int RETRY_FACTOR = 2;
    private final ApplicantId applicantId;
    private final SchedulersProvider schedulersProvider;
    private final WorkflowApi workflowApi;
    private final WorkflowConfig workflowConfig;
    private final WorkflowPollerLocaleProvider workflowPollerLocaleProvider;
    private final WorkflowTaskMapper workflowTaskMapper;
    private static final List<Integer> SKIP_RETRY_ERROR_CODES = CollectionsKt.listOf(400);

    @Inject
    public WorkflowPoller(WorkflowApi workflowApi, WorkflowConfig workflowConfig, WorkflowTaskMapper workflowTaskMapper, SchedulersProvider schedulersProvider, WorkflowPollerLocaleProvider workflowPollerLocaleProvider, ApplicantId applicantId) {
        Intrinsics.checkNotNullParameter(workflowApi, "workflowApi");
        Intrinsics.checkNotNullParameter(workflowConfig, "workflowConfig");
        Intrinsics.checkNotNullParameter(workflowTaskMapper, "workflowTaskMapper");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(workflowPollerLocaleProvider, "workflowPollerLocaleProvider");
        Intrinsics.checkNotNullParameter(applicantId, "applicantId");
        this.workflowApi = workflowApi;
        this.workflowConfig = workflowConfig;
        this.workflowTaskMapper = workflowTaskMapper;
        this.schedulersProvider = schedulersProvider;
        this.workflowPollerLocaleProvider = workflowPollerLocaleProvider;
        this.applicantId = applicantId;
    }

    public final Observable<FlowTask> startPolling(FlowTask currentTask) {
        Intrinsics.checkNotNullParameter(currentTask, "currentTask");
        if (SetsKt.setOf((Object[]) new FlowTask[]{FlowTask.NoRunningTask.INSTANCE, FlowTask.NonInteractiveTask.INSTANCE, FlowTask.AwaitingNextTask.INSTANCE}).contains(currentTask)) {
            return createPoller();
        }
        Observable<FlowTask> observableEmpty = Observable.empty();
        Intrinsics.checkNotNull(observableEmpty);
        return observableEmpty;
    }

    private final Observable<FlowTask> createPoller() {
        Observable map = Observable.interval(2000L, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).concatMapSingle(new Function() { // from class: com.onfido.workflow.internal.workflow.WorkflowPoller.createPoller.1
            @Override // io.reactivex.rxjava3.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply(((Number) obj).longValue());
            }

            public final SingleSource<? extends Response<WorkflowResponse>> apply(long j) {
                Locale workflowLocale = WorkflowPoller.this.workflowPollerLocaleProvider.getWorkflowLocale();
                WorkflowApi workflowApi = WorkflowPoller.this.workflowApi;
                String languageTag = workflowLocale.toLanguageTag();
                Intrinsics.checkNotNullExpressionValue(languageTag, "toLanguageTag(...)");
                return workflowApi.getNextTask(languageTag, WorkflowPoller.this.workflowConfig.getWorkflowRunId());
            }
        }).map(new Function() { // from class: com.onfido.workflow.internal.workflow.WorkflowPoller.createPoller.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final FlowTask apply(Response<WorkflowResponse> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return WorkflowPoller.this.workflowTaskMapper.mapFromWorkflowResponse(it, WorkflowPoller.this.applicantId, WorkflowPoller.this.workflowConfig.getWorkflowRunId());
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        Observable<FlowTask> observableDistinctUntilChanged = RxExtensionsKt.retryWithExponentialBackOff(map, 2, 5, this.schedulersProvider.getTimer(), new Function1<Throwable, Boolean>() { // from class: com.onfido.workflow.internal.workflow.WorkflowPoller.createPoller.3
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                return Boolean.valueOf((throwable instanceof HttpException) && WorkflowPoller.SKIP_RETRY_ERROR_CODES.contains(Integer.valueOf(((HttpException) throwable).code())));
            }
        }).doOnError(new Consumer() { // from class: com.onfido.workflow.internal.workflow.WorkflowPoller.createPoller.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Timber.INSTANCE.e(it, "Failure during polling", new Object[0]);
            }
        }).distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        return observableDistinctUntilChanged;
    }
}
