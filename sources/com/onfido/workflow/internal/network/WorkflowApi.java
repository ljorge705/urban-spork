package com.onfido.workflow.internal.network;

import com.google.common.net.HttpHeaders;
import com.onfido.api.client.interceptor.API;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/* compiled from: WorkflowApi.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007H'J&\u0010\b\u001a\u00020\u00032\b\b\u0001\u0010\t\u001a\u00020\u00052\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\n\u001a\u00020\u000bH'J&\u0010\f\u001a\u00020\u00032\b\b\u0001\u0010\t\u001a\u00020\u00052\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\n\u001a\u00020\rH'J(\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f2\b\b\u0001\u0010\u0012\u001a\u00020\u00052\b\b\u0001\u0010\t\u001a\u00020\u0005H'¨\u0006\u0013"}, d2 = {"Lcom/onfido/workflow/internal/network/WorkflowApi;", "", "completeDocumentCaptureTask", "Lio/reactivex/rxjava3/core/Completable;", "taskId", "", "request", "Lcom/onfido/workflow/internal/network/DocumentCaptureCompleteTaskRequest;", "completeTask", "workflowRunId", "completeTaskRequest", "Lcom/onfido/workflow/internal/network/CompleteTaskRequest;", "completeTaskCustomBody", "Lcom/onfido/workflow/internal/network/CompleteTaskRequestCustomBody;", "getNextTask", "Lio/reactivex/rxjava3/core/Single;", "Lretrofit2/Response;", "Lcom/onfido/workflow/internal/network/WorkflowResponse;", "acceptLanguageHeader", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface WorkflowApi {
    @API(version = "v3.6")
    @POST("documents_capture_task/complete")
    Completable completeDocumentCaptureTask(@Header("X-ONFIDO-WORKFLOW-TASK-ID") String taskId, @Body DocumentCaptureCompleteTaskRequest request);

    @API(version = "v3.6")
    @POST("workflow_runs/{workflow_run_id}/tasks/{task_id}/complete")
    Completable completeTask(@Path("workflow_run_id") String workflowRunId, @Path("task_id") String taskId, @Body CompleteTaskRequest completeTaskRequest);

    @API(version = "v3.6")
    @POST("workflow_runs/{workflow_run_id}/tasks/{task_id}/complete")
    Completable completeTaskCustomBody(@Path("workflow_run_id") String workflowRunId, @Path("task_id") String taskId, @Body CompleteTaskRequestCustomBody completeTaskRequest);

    @API(version = "v3.6")
    @GET("workflow_runs/{workflow_run_id}")
    Single<Response<WorkflowResponse>> getNextTask(@Header(HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguageHeader, @Path("workflow_run_id") String workflowRunId);
}
