package com.onfido.workflow.internal.workflow;

import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.WorkflowConfig;
import com.onfido.workflow.internal.network.CompleteTaskRequest;
import com.onfido.workflow.internal.network.DocumentCaptureCompleteTaskRequest;
import com.onfido.workflow.internal.network.WorkflowApi;
import com.onfido.workflow.internal.ui.model.MediaUpload;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubmitDocumentTaskCompletionUseCase.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u001e\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u001c\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\f\u0010\u0016\u001a\u00020\f*\u00020\fH\u0002J\u000e\u0010\u0017\u001a\u0004\u0018\u00010\u0018*\u00020\u0011H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/onfido/workflow/internal/workflow/SubmitDocumentTaskCompletionUseCase;", "", "workflowApi", "Lcom/onfido/workflow/internal/network/WorkflowApi;", "workflowConfig", "Lcom/onfido/workflow/WorkflowConfig;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "tokenProvider", "Lcom/onfido/android/sdk/capture/internal/token/OnfidoTokenProvider;", "(Lcom/onfido/workflow/internal/network/WorkflowApi;Lcom/onfido/workflow/WorkflowConfig;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/internal/token/OnfidoTokenProvider;)V", "completeDocumentCaptureTask", "Lio/reactivex/rxjava3/core/Completable;", "taskId", "", "mediaUploads", "", "Lcom/onfido/workflow/internal/ui/model/MediaUpload;", "completeLegacyDocumentCaptureTask", "task", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "execute", "retryWithExponentialBackOff", "toCompletionMetadata", "Lcom/onfido/workflow/internal/network/CompleteTaskRequest$CompletionMetaData;", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class SubmitDocumentTaskCompletionUseCase {
    private static final Companion Companion = new Companion(null);
    private static final int MAX_RETRIES = 5;
    private static final int RETRY_FACTOR = 2;
    private final SchedulersProvider schedulersProvider;
    private final OnfidoTokenProvider tokenProvider;
    private final WorkflowApi workflowApi;
    private final WorkflowConfig workflowConfig;

    /* compiled from: SubmitDocumentTaskCompletionUseCase.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MediaUpload.Type.values().length];
            try {
                iArr[MediaUpload.Type.DOCUMENT_PHOTO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MediaUpload.Type.DOCUMENT_VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[MediaUpload.Type.DOCUMENT_NFC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public SubmitDocumentTaskCompletionUseCase(WorkflowApi workflowApi, WorkflowConfig workflowConfig, SchedulersProvider schedulersProvider, OnfidoTokenProvider tokenProvider) {
        Intrinsics.checkNotNullParameter(workflowApi, "workflowApi");
        Intrinsics.checkNotNullParameter(workflowConfig, "workflowConfig");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(tokenProvider, "tokenProvider");
        this.workflowApi = workflowApi;
        this.workflowConfig = workflowConfig;
        this.schedulersProvider = schedulersProvider;
        this.tokenProvider = tokenProvider;
    }

    public final Completable execute(WorkflowTask task, List<MediaUpload> mediaUploads) {
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(mediaUploads, "mediaUploads");
        if (this.tokenProvider.provideToken().isStudioToken()) {
            return completeDocumentCaptureTask(task.getId(), mediaUploads);
        }
        return completeLegacyDocumentCaptureTask(task, mediaUploads);
    }

    private final Completable completeDocumentCaptureTask(String taskId, List<MediaUpload> mediaUploads) {
        ArrayList arrayList = new ArrayList();
        for (MediaUpload mediaUpload : mediaUploads) {
            DocumentCaptureCompleteTaskRequest.MediaData mediaData = mediaUpload.getId().length() > 0 ? new DocumentCaptureCompleteTaskRequest.MediaData(mediaUpload.getId()) : null;
            if (mediaData != null) {
                arrayList.add(mediaData);
            }
        }
        return retryWithExponentialBackOff(this.workflowApi.completeDocumentCaptureTask(taskId, new DocumentCaptureCompleteTaskRequest(arrayList)));
    }

    private final Completable completeLegacyDocumentCaptureTask(WorkflowTask task, List<MediaUpload> mediaUploads) {
        WorkflowApi workflowApi = this.workflowApi;
        String workflowRunId = this.workflowConfig.getWorkflowRunId();
        String id = task.getId();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = mediaUploads.iterator();
        while (it.hasNext()) {
            CompleteTaskRequest.CompletionMetaData completionMetadata = toCompletionMetadata((MediaUpload) it.next());
            if (completionMetadata != null) {
                arrayList.add(completionMetadata);
            }
        }
        return retryWithExponentialBackOff(workflowApi.completeTask(workflowRunId, id, new CompleteTaskRequest(arrayList)));
    }

    private final Completable retryWithExponentialBackOff(Completable completable) {
        Observable observable = completable.doOnError(new Consumer() { // from class: com.onfido.workflow.internal.workflow.SubmitDocumentTaskCompletionUseCase.retryWithExponentialBackOff.1
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

    private final CompleteTaskRequest.CompletionMetaData toCompletionMetadata(MediaUpload mediaUpload) {
        String str = null;
        if (mediaUpload.getId().length() == 0) {
            return null;
        }
        String id = mediaUpload.getId();
        MediaUpload.Type type = mediaUpload.getType();
        int i = type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i == 1) {
            str = "document_photo";
        } else if (i == 2) {
            str = "document_video";
        } else if (i == 3) {
            str = "document_nfc";
        }
        return new CompleteTaskRequest.CompletionMetaData(id, str);
    }

    /* compiled from: SubmitDocumentTaskCompletionUseCase.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/workflow/SubmitDocumentTaskCompletionUseCase$Companion;", "", "()V", "MAX_RETRIES", "", "RETRY_FACTOR", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
