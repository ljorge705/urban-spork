package com.onfido.workflow.internal.workflow;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.workflow.OnfidoWorkflow;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkflowTask.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001:\u000b\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005\u0082\u0001\u000b\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d¨\u0006\u001e"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "", "id", "", "getId", "()Ljava/lang/String;", "taskDefId", "getTaskDefId", "BiometricTokenRetrievalTask", "BiometricTokenStorageTask", "CaptureSdkModuleTask", "DocumentTask", "FaceMotionTask", "FacePhotoTask", "FaceVideoTask", "FinishFlowTask", "ProofOfAddressTask", "RetryTask", "UnsupportedTask", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$BiometricTokenRetrievalTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$BiometricTokenStorageTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$CaptureSdkModuleTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$DocumentTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$FaceMotionTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$FacePhotoTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$FaceVideoTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$FinishFlowTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$ProofOfAddressTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$RetryTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$UnsupportedTask;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface WorkflowTask {
    String getId();

    String getTaskDefId();

    /* compiled from: WorkflowTask.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\b¢\u0006\u0002\u0010\fJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\u001b\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\bHÆ\u0003JC\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\bHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R#\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000e¨\u0006 "}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowTask$DocumentTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "id", "", "taskDefId", "nfcOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions;", "supportedDocs", "", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "", "Lcom/onfido/android/sdk/capture/DocumentType;", "(Ljava/lang/String;Ljava/lang/String;Lcom/onfido/android/sdk/capture/model/NFCOptions;Ljava/util/Map;)V", "getId", "()Ljava/lang/String;", "getNfcOptions", "()Lcom/onfido/android/sdk/capture/model/NFCOptions;", "getSupportedDocs", "()Ljava/util/Map;", "getTaskDefId", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class DocumentTask implements WorkflowTask {
        private final String id;
        private final NFCOptions nfcOptions;
        private final Map<CountryCode, List<DocumentType>> supportedDocs;
        private final String taskDefId;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DocumentTask copy$default(DocumentTask documentTask, String str, String str2, NFCOptions nFCOptions, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = documentTask.id;
            }
            if ((i & 2) != 0) {
                str2 = documentTask.taskDefId;
            }
            if ((i & 4) != 0) {
                nFCOptions = documentTask.nfcOptions;
            }
            if ((i & 8) != 0) {
                map = documentTask.supportedDocs;
            }
            return documentTask.copy(str, str2, nFCOptions, map);
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTaskDefId() {
            return this.taskDefId;
        }

        /* renamed from: component3, reason: from getter */
        public final NFCOptions getNfcOptions() {
            return this.nfcOptions;
        }

        public final Map<CountryCode, List<DocumentType>> component4() {
            return this.supportedDocs;
        }

        public final DocumentTask copy(String id, String taskDefId, NFCOptions nfcOptions, Map<CountryCode, ? extends List<? extends DocumentType>> supportedDocs) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
            Intrinsics.checkNotNullParameter(supportedDocs, "supportedDocs");
            return new DocumentTask(id, taskDefId, nfcOptions, supportedDocs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DocumentTask)) {
                return false;
            }
            DocumentTask documentTask = (DocumentTask) other;
            return Intrinsics.areEqual(this.id, documentTask.id) && Intrinsics.areEqual(this.taskDefId, documentTask.taskDefId) && Intrinsics.areEqual(this.nfcOptions, documentTask.nfcOptions) && Intrinsics.areEqual(this.supportedDocs, documentTask.supportedDocs);
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getId() {
            return this.id;
        }

        public final NFCOptions getNfcOptions() {
            return this.nfcOptions;
        }

        public final Map<CountryCode, List<DocumentType>> getSupportedDocs() {
            return this.supportedDocs;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getTaskDefId() {
            return this.taskDefId;
        }

        public int hashCode() {
            return (((((this.id.hashCode() * 31) + this.taskDefId.hashCode()) * 31) + this.nfcOptions.hashCode()) * 31) + this.supportedDocs.hashCode();
        }

        public String toString() {
            return "DocumentTask(id=" + this.id + ", taskDefId=" + this.taskDefId + ", nfcOptions=" + this.nfcOptions + ", supportedDocs=" + this.supportedDocs + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public DocumentTask(String id, String taskDefId, NFCOptions nfcOptions, Map<CountryCode, ? extends List<? extends DocumentType>> supportedDocs) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
            Intrinsics.checkNotNullParameter(supportedDocs, "supportedDocs");
            this.id = id;
            this.taskDefId = taskDefId;
            this.nfcOptions = nfcOptions;
            this.supportedDocs = supportedDocs;
        }

        public /* synthetic */ DocumentTask(String str, String str2, NFCOptions nFCOptions, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, nFCOptions, (i & 8) != 0 ? MapsKt.emptyMap() : map);
        }
    }

    /* compiled from: WorkflowTask.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowTask$FacePhotoTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "id", "", "taskDefId", "showIntro", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "getShowIntro", "()Z", "getTaskDefId", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "other", "", "hashCode", "", "toString", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class FacePhotoTask implements WorkflowTask {
        private final String id;
        private final boolean showIntro;
        private final String taskDefId;

        public static /* synthetic */ FacePhotoTask copy$default(FacePhotoTask facePhotoTask, String str, String str2, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = facePhotoTask.id;
            }
            if ((i & 2) != 0) {
                str2 = facePhotoTask.taskDefId;
            }
            if ((i & 4) != 0) {
                z = facePhotoTask.showIntro;
            }
            return facePhotoTask.copy(str, str2, z);
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTaskDefId() {
            return this.taskDefId;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getShowIntro() {
            return this.showIntro;
        }

        public final FacePhotoTask copy(String id, String taskDefId, boolean showIntro) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            return new FacePhotoTask(id, taskDefId, showIntro);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FacePhotoTask)) {
                return false;
            }
            FacePhotoTask facePhotoTask = (FacePhotoTask) other;
            return Intrinsics.areEqual(this.id, facePhotoTask.id) && Intrinsics.areEqual(this.taskDefId, facePhotoTask.taskDefId) && this.showIntro == facePhotoTask.showIntro;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getId() {
            return this.id;
        }

        public final boolean getShowIntro() {
            return this.showIntro;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getTaskDefId() {
            return this.taskDefId;
        }

        public int hashCode() {
            return (((this.id.hashCode() * 31) + this.taskDefId.hashCode()) * 31) + Boolean.hashCode(this.showIntro);
        }

        public String toString() {
            return "FacePhotoTask(id=" + this.id + ", taskDefId=" + this.taskDefId + ", showIntro=" + this.showIntro + ")";
        }

        public FacePhotoTask(String id, String taskDefId, boolean z) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            this.id = id;
            this.taskDefId = taskDefId;
            this.showIntro = z;
        }

        public /* synthetic */ FacePhotoTask(String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? true : z);
        }
    }

    /* compiled from: WorkflowTask.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowTask$FaceVideoTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "id", "", "taskDefId", "showIntro", "", "showVideoConfirmation", "(Ljava/lang/String;Ljava/lang/String;ZZ)V", "getId", "()Ljava/lang/String;", "getShowIntro", "()Z", "getShowVideoConfirmation", "getTaskDefId", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "other", "", "hashCode", "", "toString", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class FaceVideoTask implements WorkflowTask {
        private final String id;
        private final boolean showIntro;
        private final boolean showVideoConfirmation;
        private final String taskDefId;

        public static /* synthetic */ FaceVideoTask copy$default(FaceVideoTask faceVideoTask, String str, String str2, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = faceVideoTask.id;
            }
            if ((i & 2) != 0) {
                str2 = faceVideoTask.taskDefId;
            }
            if ((i & 4) != 0) {
                z = faceVideoTask.showIntro;
            }
            if ((i & 8) != 0) {
                z2 = faceVideoTask.showVideoConfirmation;
            }
            return faceVideoTask.copy(str, str2, z, z2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTaskDefId() {
            return this.taskDefId;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getShowIntro() {
            return this.showIntro;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getShowVideoConfirmation() {
            return this.showVideoConfirmation;
        }

        public final FaceVideoTask copy(String id, String taskDefId, boolean showIntro, boolean showVideoConfirmation) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            return new FaceVideoTask(id, taskDefId, showIntro, showVideoConfirmation);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FaceVideoTask)) {
                return false;
            }
            FaceVideoTask faceVideoTask = (FaceVideoTask) other;
            return Intrinsics.areEqual(this.id, faceVideoTask.id) && Intrinsics.areEqual(this.taskDefId, faceVideoTask.taskDefId) && this.showIntro == faceVideoTask.showIntro && this.showVideoConfirmation == faceVideoTask.showVideoConfirmation;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getId() {
            return this.id;
        }

        public final boolean getShowIntro() {
            return this.showIntro;
        }

        public final boolean getShowVideoConfirmation() {
            return this.showVideoConfirmation;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getTaskDefId() {
            return this.taskDefId;
        }

        public int hashCode() {
            return (((((this.id.hashCode() * 31) + this.taskDefId.hashCode()) * 31) + Boolean.hashCode(this.showIntro)) * 31) + Boolean.hashCode(this.showVideoConfirmation);
        }

        public String toString() {
            return "FaceVideoTask(id=" + this.id + ", taskDefId=" + this.taskDefId + ", showIntro=" + this.showIntro + ", showVideoConfirmation=" + this.showVideoConfirmation + ")";
        }

        public FaceVideoTask(String id, String taskDefId, boolean z, boolean z2) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            this.id = id;
            this.taskDefId = taskDefId;
            this.showIntro = z;
            this.showVideoConfirmation = z2;
        }

        public /* synthetic */ FaceVideoTask(String str, String str2, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? true : z, (i & 8) != 0 ? true : z2);
        }
    }

    /* compiled from: WorkflowTask.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001d"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowTask$FaceMotionTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "id", "", "taskDefId", RRWebOptionsEvent.EVENT_TAG, "Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "applicantId", "Lcom/onfido/api/client/token/sdk/ApplicantId;", "(Ljava/lang/String;Ljava/lang/String;Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;Lcom/onfido/api/client/token/sdk/ApplicantId;)V", "getApplicantId", "()Lcom/onfido/api/client/token/sdk/ApplicantId;", "getId", "()Ljava/lang/String;", "getOptions", "()Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "getTaskDefId", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class FaceMotionTask implements WorkflowTask {
        private final ApplicantId applicantId;
        private final String id;
        private final MotionCaptureVariantOptions options;
        private final String taskDefId;

        public static /* synthetic */ FaceMotionTask copy$default(FaceMotionTask faceMotionTask, String str, String str2, MotionCaptureVariantOptions motionCaptureVariantOptions, ApplicantId applicantId, int i, Object obj) {
            if ((i & 1) != 0) {
                str = faceMotionTask.id;
            }
            if ((i & 2) != 0) {
                str2 = faceMotionTask.taskDefId;
            }
            if ((i & 4) != 0) {
                motionCaptureVariantOptions = faceMotionTask.options;
            }
            if ((i & 8) != 0) {
                applicantId = faceMotionTask.applicantId;
            }
            return faceMotionTask.copy(str, str2, motionCaptureVariantOptions, applicantId);
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTaskDefId() {
            return this.taskDefId;
        }

        /* renamed from: component3, reason: from getter */
        public final MotionCaptureVariantOptions getOptions() {
            return this.options;
        }

        /* renamed from: component4, reason: from getter */
        public final ApplicantId getApplicantId() {
            return this.applicantId;
        }

        public final FaceMotionTask copy(String id, String taskDefId, MotionCaptureVariantOptions options, ApplicantId applicantId) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(applicantId, "applicantId");
            return new FaceMotionTask(id, taskDefId, options, applicantId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FaceMotionTask)) {
                return false;
            }
            FaceMotionTask faceMotionTask = (FaceMotionTask) other;
            return Intrinsics.areEqual(this.id, faceMotionTask.id) && Intrinsics.areEqual(this.taskDefId, faceMotionTask.taskDefId) && Intrinsics.areEqual(this.options, faceMotionTask.options) && Intrinsics.areEqual(this.applicantId, faceMotionTask.applicantId);
        }

        public final ApplicantId getApplicantId() {
            return this.applicantId;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getId() {
            return this.id;
        }

        public final MotionCaptureVariantOptions getOptions() {
            return this.options;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getTaskDefId() {
            return this.taskDefId;
        }

        public int hashCode() {
            return (((((this.id.hashCode() * 31) + this.taskDefId.hashCode()) * 31) + this.options.hashCode()) * 31) + this.applicantId.hashCode();
        }

        public String toString() {
            return "FaceMotionTask(id=" + this.id + ", taskDefId=" + this.taskDefId + ", options=" + this.options + ", applicantId=" + this.applicantId + ")";
        }

        public FaceMotionTask(String id, String taskDefId, MotionCaptureVariantOptions options, ApplicantId applicantId) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(applicantId, "applicantId");
            this.id = id;
            this.taskDefId = taskDefId;
            this.options = options;
            this.applicantId = applicantId;
        }
    }

    /* compiled from: WorkflowTask.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowTask$BiometricTokenRetrievalTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "id", "", "taskDefId", "(Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getTaskDefId", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class BiometricTokenRetrievalTask implements WorkflowTask {
        private final String id;
        private final String taskDefId;

        public static /* synthetic */ BiometricTokenRetrievalTask copy$default(BiometricTokenRetrievalTask biometricTokenRetrievalTask, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biometricTokenRetrievalTask.id;
            }
            if ((i & 2) != 0) {
                str2 = biometricTokenRetrievalTask.taskDefId;
            }
            return biometricTokenRetrievalTask.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTaskDefId() {
            return this.taskDefId;
        }

        public final BiometricTokenRetrievalTask copy(String id, String taskDefId) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            return new BiometricTokenRetrievalTask(id, taskDefId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiometricTokenRetrievalTask)) {
                return false;
            }
            BiometricTokenRetrievalTask biometricTokenRetrievalTask = (BiometricTokenRetrievalTask) other;
            return Intrinsics.areEqual(this.id, biometricTokenRetrievalTask.id) && Intrinsics.areEqual(this.taskDefId, biometricTokenRetrievalTask.taskDefId);
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getId() {
            return this.id;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getTaskDefId() {
            return this.taskDefId;
        }

        public int hashCode() {
            return (this.id.hashCode() * 31) + this.taskDefId.hashCode();
        }

        public String toString() {
            return "BiometricTokenRetrievalTask(id=" + this.id + ", taskDefId=" + this.taskDefId + ")";
        }

        public BiometricTokenRetrievalTask(String id, String taskDefId) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            this.id = id;
            this.taskDefId = taskDefId;
        }
    }

    /* compiled from: WorkflowTask.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowTask$BiometricTokenStorageTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "id", "", "taskDefId", "mediaUuid", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getMediaUuid", "getTaskDefId", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class BiometricTokenStorageTask implements WorkflowTask {
        private final String id;
        private final String mediaUuid;
        private final String taskDefId;

        public static /* synthetic */ BiometricTokenStorageTask copy$default(BiometricTokenStorageTask biometricTokenStorageTask, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biometricTokenStorageTask.id;
            }
            if ((i & 2) != 0) {
                str2 = biometricTokenStorageTask.taskDefId;
            }
            if ((i & 4) != 0) {
                str3 = biometricTokenStorageTask.mediaUuid;
            }
            return biometricTokenStorageTask.copy(str, str2, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTaskDefId() {
            return this.taskDefId;
        }

        /* renamed from: component3, reason: from getter */
        public final String getMediaUuid() {
            return this.mediaUuid;
        }

        public final BiometricTokenStorageTask copy(String id, String taskDefId, String mediaUuid) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            Intrinsics.checkNotNullParameter(mediaUuid, "mediaUuid");
            return new BiometricTokenStorageTask(id, taskDefId, mediaUuid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiometricTokenStorageTask)) {
                return false;
            }
            BiometricTokenStorageTask biometricTokenStorageTask = (BiometricTokenStorageTask) other;
            return Intrinsics.areEqual(this.id, biometricTokenStorageTask.id) && Intrinsics.areEqual(this.taskDefId, biometricTokenStorageTask.taskDefId) && Intrinsics.areEqual(this.mediaUuid, biometricTokenStorageTask.mediaUuid);
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getId() {
            return this.id;
        }

        public final String getMediaUuid() {
            return this.mediaUuid;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getTaskDefId() {
            return this.taskDefId;
        }

        public int hashCode() {
            return (((this.id.hashCode() * 31) + this.taskDefId.hashCode()) * 31) + this.mediaUuid.hashCode();
        }

        public String toString() {
            return "BiometricTokenStorageTask(id=" + this.id + ", taskDefId=" + this.taskDefId + ", mediaUuid=" + this.mediaUuid + ")";
        }

        public BiometricTokenStorageTask(String id, String taskDefId, String mediaUuid) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            Intrinsics.checkNotNullParameter(mediaUuid, "mediaUuid");
            this.id = id;
            this.taskDefId = taskDefId;
            this.mediaUuid = mediaUuid;
        }
    }

    /* compiled from: WorkflowTask.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowTask$ProofOfAddressTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "id", "", "taskDefId", "(Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getTaskDefId", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ProofOfAddressTask implements WorkflowTask {
        private final String id;
        private final String taskDefId;

        public static /* synthetic */ ProofOfAddressTask copy$default(ProofOfAddressTask proofOfAddressTask, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = proofOfAddressTask.id;
            }
            if ((i & 2) != 0) {
                str2 = proofOfAddressTask.taskDefId;
            }
            return proofOfAddressTask.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTaskDefId() {
            return this.taskDefId;
        }

        public final ProofOfAddressTask copy(String id, String taskDefId) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            return new ProofOfAddressTask(id, taskDefId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ProofOfAddressTask)) {
                return false;
            }
            ProofOfAddressTask proofOfAddressTask = (ProofOfAddressTask) other;
            return Intrinsics.areEqual(this.id, proofOfAddressTask.id) && Intrinsics.areEqual(this.taskDefId, proofOfAddressTask.taskDefId);
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getId() {
            return this.id;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getTaskDefId() {
            return this.taskDefId;
        }

        public int hashCode() {
            return (this.id.hashCode() * 31) + this.taskDefId.hashCode();
        }

        public String toString() {
            return "ProofOfAddressTask(id=" + this.id + ", taskDefId=" + this.taskDefId + ")";
        }

        public ProofOfAddressTask(String id, String taskDefId) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            this.id = id;
            this.taskDefId = taskDefId;
        }
    }

    /* compiled from: WorkflowTask.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0019"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowTask$CaptureSdkModuleTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "id", "", "taskDefId", "moduleConfig", "moduleInput", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getModuleConfig", "getModuleInput", "getTaskDefId", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class CaptureSdkModuleTask implements WorkflowTask {
        private final String id;
        private final String moduleConfig;
        private final String moduleInput;
        private final String taskDefId;

        public static /* synthetic */ CaptureSdkModuleTask copy$default(CaptureSdkModuleTask captureSdkModuleTask, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = captureSdkModuleTask.id;
            }
            if ((i & 2) != 0) {
                str2 = captureSdkModuleTask.taskDefId;
            }
            if ((i & 4) != 0) {
                str3 = captureSdkModuleTask.moduleConfig;
            }
            if ((i & 8) != 0) {
                str4 = captureSdkModuleTask.moduleInput;
            }
            return captureSdkModuleTask.copy(str, str2, str3, str4);
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTaskDefId() {
            return this.taskDefId;
        }

        /* renamed from: component3, reason: from getter */
        public final String getModuleConfig() {
            return this.moduleConfig;
        }

        /* renamed from: component4, reason: from getter */
        public final String getModuleInput() {
            return this.moduleInput;
        }

        public final CaptureSdkModuleTask copy(String id, String taskDefId, String moduleConfig, String moduleInput) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            Intrinsics.checkNotNullParameter(moduleConfig, "moduleConfig");
            Intrinsics.checkNotNullParameter(moduleInput, "moduleInput");
            return new CaptureSdkModuleTask(id, taskDefId, moduleConfig, moduleInput);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CaptureSdkModuleTask)) {
                return false;
            }
            CaptureSdkModuleTask captureSdkModuleTask = (CaptureSdkModuleTask) other;
            return Intrinsics.areEqual(this.id, captureSdkModuleTask.id) && Intrinsics.areEqual(this.taskDefId, captureSdkModuleTask.taskDefId) && Intrinsics.areEqual(this.moduleConfig, captureSdkModuleTask.moduleConfig) && Intrinsics.areEqual(this.moduleInput, captureSdkModuleTask.moduleInput);
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getId() {
            return this.id;
        }

        public final String getModuleConfig() {
            return this.moduleConfig;
        }

        public final String getModuleInput() {
            return this.moduleInput;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getTaskDefId() {
            return this.taskDefId;
        }

        public int hashCode() {
            return (((((this.id.hashCode() * 31) + this.taskDefId.hashCode()) * 31) + this.moduleConfig.hashCode()) * 31) + this.moduleInput.hashCode();
        }

        public String toString() {
            return "CaptureSdkModuleTask(id=" + this.id + ", taskDefId=" + this.taskDefId + ", moduleConfig=" + this.moduleConfig + ", moduleInput=" + this.moduleInput + ")";
        }

        public CaptureSdkModuleTask(String id, String taskDefId, String moduleConfig, String moduleInput) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            Intrinsics.checkNotNullParameter(moduleConfig, "moduleConfig");
            Intrinsics.checkNotNullParameter(moduleInput, "moduleInput");
            this.id = id;
            this.taskDefId = taskDefId;
            this.moduleConfig = moduleConfig;
            this.moduleInput = moduleInput;
        }
    }

    /* compiled from: WorkflowTask.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0002\u001d\u001eB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\bHÆ\u0003J5\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001f"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowTask$RetryTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "id", "", "taskDefId", "reason", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$RetryTask$RetryReason;", "customTexts", "Lcom/onfido/workflow/internal/workflow/WorkflowTask$RetryTask$RetryTexts;", "(Ljava/lang/String;Ljava/lang/String;Lcom/onfido/workflow/internal/workflow/WorkflowTask$RetryTask$RetryReason;Lcom/onfido/workflow/internal/workflow/WorkflowTask$RetryTask$RetryTexts;)V", "getCustomTexts", "()Lcom/onfido/workflow/internal/workflow/WorkflowTask$RetryTask$RetryTexts;", "getId", "()Ljava/lang/String;", "getReason", "()Lcom/onfido/workflow/internal/workflow/WorkflowTask$RetryTask$RetryReason;", "getTaskDefId", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "RetryReason", "RetryTexts", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class RetryTask implements WorkflowTask {
        private final RetryTexts customTexts;
        private final String id;
        private final RetryReason reason;
        private final String taskDefId;

        public static /* synthetic */ RetryTask copy$default(RetryTask retryTask, String str, String str2, RetryReason retryReason, RetryTexts retryTexts, int i, Object obj) {
            if ((i & 1) != 0) {
                str = retryTask.id;
            }
            if ((i & 2) != 0) {
                str2 = retryTask.taskDefId;
            }
            if ((i & 4) != 0) {
                retryReason = retryTask.reason;
            }
            if ((i & 8) != 0) {
                retryTexts = retryTask.customTexts;
            }
            return retryTask.copy(str, str2, retryReason, retryTexts);
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTaskDefId() {
            return this.taskDefId;
        }

        /* renamed from: component3, reason: from getter */
        public final RetryReason getReason() {
            return this.reason;
        }

        /* renamed from: component4, reason: from getter */
        public final RetryTexts getCustomTexts() {
            return this.customTexts;
        }

        public final RetryTask copy(String id, String taskDefId, RetryReason reason, RetryTexts customTexts) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            return new RetryTask(id, taskDefId, reason, customTexts);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RetryTask)) {
                return false;
            }
            RetryTask retryTask = (RetryTask) other;
            return Intrinsics.areEqual(this.id, retryTask.id) && Intrinsics.areEqual(this.taskDefId, retryTask.taskDefId) && this.reason == retryTask.reason && Intrinsics.areEqual(this.customTexts, retryTask.customTexts);
        }

        public final RetryTexts getCustomTexts() {
            return this.customTexts;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getId() {
            return this.id;
        }

        public final RetryReason getReason() {
            return this.reason;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getTaskDefId() {
            return this.taskDefId;
        }

        public int hashCode() {
            int iHashCode = ((this.id.hashCode() * 31) + this.taskDefId.hashCode()) * 31;
            RetryReason retryReason = this.reason;
            int iHashCode2 = (iHashCode + (retryReason == null ? 0 : retryReason.hashCode())) * 31;
            RetryTexts retryTexts = this.customTexts;
            return iHashCode2 + (retryTexts != null ? retryTexts.hashCode() : 0);
        }

        public String toString() {
            return "RetryTask(id=" + this.id + ", taskDefId=" + this.taskDefId + ", reason=" + this.reason + ", customTexts=" + this.customTexts + ")";
        }

        public RetryTask(String id, String taskDefId, RetryReason retryReason, RetryTexts retryTexts) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            this.id = id;
            this.taskDefId = taskDefId;
            this.reason = retryReason;
            this.customTexts = retryTexts;
        }

        public /* synthetic */ RetryTask(String str, String str2, RetryReason retryReason, RetryTexts retryTexts, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, retryReason, (i & 8) != 0 ? null : retryTexts);
        }

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* compiled from: WorkflowTask.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowTask$RetryTask$RetryReason;", "", "(Ljava/lang/String;I)V", "EXPIRED_DOCUMENT", "UNACCEPTED_DOCUMENT", "GENERIC_DOCUMENT", "GENERIC_SELFIE", "CUSTOM", "DEFAULT", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class RetryReason {
            private static final /* synthetic */ EnumEntries $ENTRIES;
            private static final /* synthetic */ RetryReason[] $VALUES;
            public static final RetryReason EXPIRED_DOCUMENT = new RetryReason("EXPIRED_DOCUMENT", 0);
            public static final RetryReason UNACCEPTED_DOCUMENT = new RetryReason("UNACCEPTED_DOCUMENT", 1);
            public static final RetryReason GENERIC_DOCUMENT = new RetryReason("GENERIC_DOCUMENT", 2);
            public static final RetryReason GENERIC_SELFIE = new RetryReason("GENERIC_SELFIE", 3);
            public static final RetryReason CUSTOM = new RetryReason("CUSTOM", 4);
            public static final RetryReason DEFAULT = new RetryReason("DEFAULT", 5);

            private static final /* synthetic */ RetryReason[] $values() {
                return new RetryReason[]{EXPIRED_DOCUMENT, UNACCEPTED_DOCUMENT, GENERIC_DOCUMENT, GENERIC_SELFIE, CUSTOM, DEFAULT};
            }

            public static EnumEntries<RetryReason> getEntries() {
                return $ENTRIES;
            }

            public static RetryReason valueOf(String str) {
                return (RetryReason) Enum.valueOf(RetryReason.class, str);
            }

            public static RetryReason[] values() {
                return (RetryReason[]) $VALUES.clone();
            }

            private RetryReason(String str, int i) {
            }

            static {
                RetryReason[] retryReasonArr$values = $values();
                $VALUES = retryReasonArr$values;
                $ENTRIES = EnumEntriesKt.enumEntries(retryReasonArr$values);
            }
        }

        /* compiled from: WorkflowTask.kt */
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowTask$RetryTask$RetryTexts;", "", "title", "", "description", "buttonText", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getButtonText", "()Ljava/lang/String;", "getDescription", "getTitle", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class RetryTexts {
            private final String buttonText;
            private final String description;
            private final String title;

            public static /* synthetic */ RetryTexts copy$default(RetryTexts retryTexts, String str, String str2, String str3, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = retryTexts.title;
                }
                if ((i & 2) != 0) {
                    str2 = retryTexts.description;
                }
                if ((i & 4) != 0) {
                    str3 = retryTexts.buttonText;
                }
                return retryTexts.copy(str, str2, str3);
            }

            /* renamed from: component1, reason: from getter */
            public final String getTitle() {
                return this.title;
            }

            /* renamed from: component2, reason: from getter */
            public final String getDescription() {
                return this.description;
            }

            /* renamed from: component3, reason: from getter */
            public final String getButtonText() {
                return this.buttonText;
            }

            public final RetryTexts copy(String title, String description, String buttonText) {
                Intrinsics.checkNotNullParameter(title, "title");
                Intrinsics.checkNotNullParameter(description, "description");
                Intrinsics.checkNotNullParameter(buttonText, "buttonText");
                return new RetryTexts(title, description, buttonText);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof RetryTexts)) {
                    return false;
                }
                RetryTexts retryTexts = (RetryTexts) other;
                return Intrinsics.areEqual(this.title, retryTexts.title) && Intrinsics.areEqual(this.description, retryTexts.description) && Intrinsics.areEqual(this.buttonText, retryTexts.buttonText);
            }

            public final String getButtonText() {
                return this.buttonText;
            }

            public final String getDescription() {
                return this.description;
            }

            public final String getTitle() {
                return this.title;
            }

            public int hashCode() {
                return (((this.title.hashCode() * 31) + this.description.hashCode()) * 31) + this.buttonText.hashCode();
            }

            public String toString() {
                return "RetryTexts(title=" + this.title + ", description=" + this.description + ", buttonText=" + this.buttonText + ")";
            }

            public RetryTexts(String title, String description, String buttonText) {
                Intrinsics.checkNotNullParameter(title, "title");
                Intrinsics.checkNotNullParameter(description, "description");
                Intrinsics.checkNotNullParameter(buttonText, "buttonText");
                this.title = title;
                this.description = description;
                this.buttonText = buttonText;
            }
        }
    }

    /* compiled from: WorkflowTask.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowTask$FinishFlowTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "id", "", "taskDefId", "error", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "(Ljava/lang/String;Ljava/lang/String;Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;)V", "getError", "()Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "getId", "()Ljava/lang/String;", "getTaskDefId", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class FinishFlowTask implements WorkflowTask {
        private final OnfidoWorkflow.WorkflowException error;
        private final String id;
        private final String taskDefId;

        public FinishFlowTask() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ FinishFlowTask copy$default(FinishFlowTask finishFlowTask, String str, String str2, OnfidoWorkflow.WorkflowException workflowException, int i, Object obj) {
            if ((i & 1) != 0) {
                str = finishFlowTask.id;
            }
            if ((i & 2) != 0) {
                str2 = finishFlowTask.taskDefId;
            }
            if ((i & 4) != 0) {
                workflowException = finishFlowTask.error;
            }
            return finishFlowTask.copy(str, str2, workflowException);
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTaskDefId() {
            return this.taskDefId;
        }

        /* renamed from: component3, reason: from getter */
        public final OnfidoWorkflow.WorkflowException getError() {
            return this.error;
        }

        public final FinishFlowTask copy(String id, String taskDefId, OnfidoWorkflow.WorkflowException error) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            return new FinishFlowTask(id, taskDefId, error);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FinishFlowTask)) {
                return false;
            }
            FinishFlowTask finishFlowTask = (FinishFlowTask) other;
            return Intrinsics.areEqual(this.id, finishFlowTask.id) && Intrinsics.areEqual(this.taskDefId, finishFlowTask.taskDefId) && Intrinsics.areEqual(this.error, finishFlowTask.error);
        }

        public final OnfidoWorkflow.WorkflowException getError() {
            return this.error;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getId() {
            return this.id;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getTaskDefId() {
            return this.taskDefId;
        }

        public int hashCode() {
            int iHashCode = ((this.id.hashCode() * 31) + this.taskDefId.hashCode()) * 31;
            OnfidoWorkflow.WorkflowException workflowException = this.error;
            return iHashCode + (workflowException == null ? 0 : workflowException.hashCode());
        }

        public String toString() {
            return "FinishFlowTask(id=" + this.id + ", taskDefId=" + this.taskDefId + ", error=" + this.error + ")";
        }

        public FinishFlowTask(String id, String taskDefId, OnfidoWorkflow.WorkflowException workflowException) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            this.id = id;
            this.taskDefId = taskDefId;
            this.error = workflowException;
        }

        public /* synthetic */ FinishFlowTask(String str, String str2, OnfidoWorkflow.WorkflowException workflowException, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? null : workflowException);
        }
    }

    /* compiled from: WorkflowTask.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J3\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowTask$UnsupportedTask;", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "id", "", "taskDefId", "taskName", "reason", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V", "getId", "()Ljava/lang/String;", "getReason", "()Ljava/lang/Throwable;", "getTaskDefId", "getTaskName", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class UnsupportedTask implements WorkflowTask {
        private final String id;
        private final Throwable reason;
        private final String taskDefId;
        private final String taskName;

        public static /* synthetic */ UnsupportedTask copy$default(UnsupportedTask unsupportedTask, String str, String str2, String str3, Throwable th, int i, Object obj) {
            if ((i & 1) != 0) {
                str = unsupportedTask.id;
            }
            if ((i & 2) != 0) {
                str2 = unsupportedTask.taskDefId;
            }
            if ((i & 4) != 0) {
                str3 = unsupportedTask.taskName;
            }
            if ((i & 8) != 0) {
                th = unsupportedTask.reason;
            }
            return unsupportedTask.copy(str, str2, str3, th);
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTaskDefId() {
            return this.taskDefId;
        }

        /* renamed from: component3, reason: from getter */
        public final String getTaskName() {
            return this.taskName;
        }

        /* renamed from: component4, reason: from getter */
        public final Throwable getReason() {
            return this.reason;
        }

        public final UnsupportedTask copy(String id, String taskDefId, String taskName, Throwable reason) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            Intrinsics.checkNotNullParameter(taskName, "taskName");
            return new UnsupportedTask(id, taskDefId, taskName, reason);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UnsupportedTask)) {
                return false;
            }
            UnsupportedTask unsupportedTask = (UnsupportedTask) other;
            return Intrinsics.areEqual(this.id, unsupportedTask.id) && Intrinsics.areEqual(this.taskDefId, unsupportedTask.taskDefId) && Intrinsics.areEqual(this.taskName, unsupportedTask.taskName) && Intrinsics.areEqual(this.reason, unsupportedTask.reason);
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getId() {
            return this.id;
        }

        public final Throwable getReason() {
            return this.reason;
        }

        @Override // com.onfido.workflow.internal.workflow.WorkflowTask
        public String getTaskDefId() {
            return this.taskDefId;
        }

        public final String getTaskName() {
            return this.taskName;
        }

        public int hashCode() {
            int iHashCode = ((((this.id.hashCode() * 31) + this.taskDefId.hashCode()) * 31) + this.taskName.hashCode()) * 31;
            Throwable th = this.reason;
            return iHashCode + (th == null ? 0 : th.hashCode());
        }

        public String toString() {
            return "UnsupportedTask(id=" + this.id + ", taskDefId=" + this.taskDefId + ", taskName=" + this.taskName + ", reason=" + this.reason + ")";
        }

        public UnsupportedTask(String id, String taskDefId, String taskName, Throwable th) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
            Intrinsics.checkNotNullParameter(taskName, "taskName");
            this.id = id;
            this.taskDefId = taskDefId;
            this.taskName = taskName;
            this.reason = th;
        }

        public /* synthetic */ UnsupportedTask(String str, String str2, String str3, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, str3, (i & 8) != 0 ? null : th);
        }
    }
}
