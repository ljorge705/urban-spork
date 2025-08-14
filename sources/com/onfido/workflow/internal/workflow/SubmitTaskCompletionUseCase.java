package com.onfido.workflow.internal.workflow;

import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.WorkflowConfig;
import com.onfido.workflow.internal.network.CompleteTaskRequest;
import com.onfido.workflow.internal.network.CompleteTaskRequestCustomBody;
import com.onfido.workflow.internal.network.WorkflowApi;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;

/* compiled from: SubmitTaskCompletionUseCase.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\b\u0000\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u001c\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010J\u0016\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u000eJ\u001e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010H\u0002J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u000eH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase;", "", "workflowApi", "Lcom/onfido/workflow/internal/network/WorkflowApi;", "workflowConfig", "Lcom/onfido/workflow/WorkflowConfig;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "(Lcom/onfido/workflow/internal/network/WorkflowApi;Lcom/onfido/workflow/WorkflowConfig;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;)V", "execute", "Lio/reactivex/rxjava3/core/Completable;", "task", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "uploadId", "", "uploadIds", "", "executeCustom", "requestBody", "executeInternal", "executeInternalCustom", "body", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class SubmitTaskCompletionUseCase {
    private static final Companion Companion = new Companion(null);
    private static final int MAX_RETRIES = 5;
    private static final int RETRY_FACTOR = 2;
    private final SchedulersProvider schedulersProvider;
    private final WorkflowApi workflowApi;
    private final WorkflowConfig workflowConfig;

    @Inject
    public SubmitTaskCompletionUseCase(WorkflowApi workflowApi, WorkflowConfig workflowConfig, SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(workflowApi, "workflowApi");
        Intrinsics.checkNotNullParameter(workflowConfig, "workflowConfig");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        this.workflowApi = workflowApi;
        this.workflowConfig = workflowConfig;
        this.schedulersProvider = schedulersProvider;
    }

    public final Completable execute(WorkflowTask task, String uploadId) {
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(uploadId, "uploadId");
        return executeInternal(task, CollectionsKt.listOf(uploadId));
    }

    public final Completable execute(WorkflowTask task, List<String> uploadIds) {
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(uploadIds, "uploadIds");
        return executeInternal(task, uploadIds);
    }

    public final Completable execute(WorkflowTask task) {
        Intrinsics.checkNotNullParameter(task, "task");
        return execute(task, CollectionsKt.emptyList());
    }

    public final Completable executeCustom(WorkflowTask task, String requestBody) {
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(requestBody, "requestBody");
        return executeInternalCustom(task, requestBody);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Completable executeInternal(WorkflowTask task, List<String> uploadIds) {
        WorkflowApi workflowApi = this.workflowApi;
        String workflowRunId = this.workflowConfig.getWorkflowRunId();
        String id = task.getId();
        List<String> list = uploadIds;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new CompleteTaskRequest.CompletionMetaData((String) it.next(), (String) null, 2, (DefaultConstructorMarker) (0 == true ? 1 : 0)));
        }
        Observable observable = workflowApi.completeTask(workflowRunId, id, new CompleteTaskRequest(arrayList)).doOnError(new Consumer() { // from class: com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase.executeInternal.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                it2.printStackTrace();
            }
        }).toObservable();
        Intrinsics.checkNotNullExpressionValue(observable, "toObservable(...)");
        Completable completableIgnoreElements = RxExtensionsKt.retryWithExponentialBackOff$default(observable, 2, 5, this.schedulersProvider.getTimer(), null, 8, null).ignoreElements();
        Intrinsics.checkNotNullExpressionValue(completableIgnoreElements, "ignoreElements(...)");
        return completableIgnoreElements;
    }

    private final Completable executeInternalCustom(WorkflowTask task, String body) {
        Observable observable = this.workflowApi.completeTaskCustomBody(this.workflowConfig.getWorkflowRunId(), task.getId(), new CompleteTaskRequestCustomBody(Json.INSTANCE.parseToJsonElement(body))).doOnError(new Consumer() { // from class: com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase.executeInternalCustom.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.printStackTrace();
            }
        }).toObservable();
        Intrinsics.checkNotNullExpressionValue(observable, "toObservable(...)");
        Completable completableIgnoreElements = RxExtensionsKt.retryWithExponentialBackOff$default(observable, 2, 5, this.schedulersProvider.getTimer(), null, 8, null).ignoreElements();
        Intrinsics.checkNotNullExpressionValue(completableIgnoreElements, "ignoreElements(...)");
        return completableIgnoreElements;
    }

    /* compiled from: SubmitTaskCompletionUseCase.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/workflow/SubmitTaskCompletionUseCase$Companion;", "", "()V", "MAX_RETRIES", "", "RETRY_FACTOR", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
