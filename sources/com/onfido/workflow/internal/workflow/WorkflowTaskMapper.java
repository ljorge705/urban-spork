package com.onfido.workflow.internal.workflow;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.JsonExtKt;
import com.onfido.api.client.data.DocType;
import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.OnfidoWorkflow;
import com.onfido.workflow.internal.network.CurrentApplicantTask;
import com.onfido.workflow.internal.network.DocumentUploadTaskConfig;
import com.onfido.workflow.internal.network.FaceVideoTaskConfig;
import com.onfido.workflow.internal.network.InteractiveTaskStatus;
import com.onfido.workflow.internal.network.MotionTaskConfig;
import com.onfido.workflow.internal.network.MotionTaskInput;
import com.onfido.workflow.internal.network.SelfieTaskConfig;
import com.onfido.workflow.internal.network.WorkflowResponse;
import com.onfido.workflow.internal.ui.model.FlowTask;
import com.onfido.workflow.internal.workflow.WorkflowTask;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import retrofit2.Response;

/* compiled from: WorkflowTaskMapper.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 (2\u00020\u0001:\u0001(B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\"\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J2\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0002J \u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J(\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J:\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J$\u0010\u001a\u001a\u00020\u00122\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\fJ4\u0010\u001e\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J2\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0010H\u0002J \u0010 \u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J(\u0010!\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\f\u0010\"\u001a\u00020#*\u00020$H\u0002J\f\u0010%\u001a\u00020&*\u00020'H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowTaskMapper;", "", "supportedDocumentsRepository", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/OnfidoSupportedDocumentsRepository;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "jsonParser", "Lkotlinx/serialization/json/Json;", "(Lcom/onfido/android/sdk/capture/internal/ui/countryselection/OnfidoSupportedDocumentsRepository;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lkotlinx/serialization/json/Json;)V", "mapBiometricTokenRetrievalTask", "Lcom/onfido/workflow/internal/ui/model/FlowTask$InteractiveTask;", "taskId", "", "taskDefId", "mapBiometricTokenStorageTask", "taskInput", "Lkotlinx/serialization/json/JsonObject;", "mapCaptureSDKTask", "Lcom/onfido/workflow/internal/ui/model/FlowTask;", OnfidoLauncher.KEY_CONFIG, "workflowRunId", "mapDocumentTask", "mapFaceVideoTask", "applicantId", "Lcom/onfido/api/client/token/sdk/ApplicantId;", "mapFromTaskDefId", "mapFromWorkflowResponse", "workflowResponse", "Lretrofit2/Response;", "Lcom/onfido/workflow/internal/network/WorkflowResponse;", "mapMotionTask", "mapOtpTask", "mapRetryTask", "mapSelfieTask", "createNFCOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions;", "Lcom/onfido/workflow/internal/network/DocumentUploadTaskConfig;", "mapToOnfidoDocumentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "Lcom/onfido/api/client/data/DocType;", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WorkflowTaskMapper {
    private static final String DEFAULT_WEB_CAPTURE_SDK_MODULE_VERSION = "1";
    private static final String TASK_DEF_BIOMETRIC_TOKEN_RETRIEVAL = "biometric_token_retrieval";
    private static final String TASK_DEF_BIOMETRIC_TOKEN_STORAGE = "biometric_token_storage";
    private static final String TASK_DEF_DOCUMENT = "upload_document";
    private static final String TASK_DEF_DOCUMENT_PHOTO = "upload_document_photo";
    private static final String TASK_DEF_FACE_MOTION = "upload_face_motion";
    private static final String TASK_DEF_FACE_MOTION_AUTH = "upload_face_motion_auth";
    private static final String TASK_DEF_FACE_PHOTO = "upload_face_photo";
    private static final String TASK_DEF_FACE_VIDEO = "upload_face_video";
    private static final String TASK_DEF_PROOF_OF_ADDRESS = "proof_of_address_capture";
    private static final String TASK_DEF_RETRY = "retry";
    private static final String TASK_OTP = "one_time_password_capture";
    private static final String TASK_QES_CONSENT = "qualified_electronic_signature_capture";
    private final Json jsonParser;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private final OnfidoSupportedDocumentsRepository supportedDocumentsRepository;

    /* compiled from: WorkflowTaskMapper.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[InteractiveTaskStatus.values().length];
            try {
                iArr[InteractiveTaskStatus.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[InteractiveTaskStatus.ABANDONED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[InteractiveTaskStatus.FINISHED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[InteractiveTaskStatus.RUNNING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[DocumentUploadTaskConfig.NFCProcessingOption.values().length];
            try {
                iArr2[DocumentUploadTaskConfig.NFCProcessingOption.OFF.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[DocumentUploadTaskConfig.NFCProcessingOption.OPTIONAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[DocumentUploadTaskConfig.NFCProcessingOption.ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[DocType.values().length];
            try {
                iArr3[DocType.PASSPORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr3[DocType.DRIVING_LICENSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr3[DocType.NATIONAL_ID_CARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr3[DocType.VISA.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr3[DocType.WORK_PERMIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr3[DocType.RESIDENCE_PERMIT.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr3[DocType.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused14) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    @Inject
    public WorkflowTaskMapper(OnfidoSupportedDocumentsRepository supportedDocumentsRepository, OnfidoRemoteConfig onfidoRemoteConfig, Json jsonParser) {
        Intrinsics.checkNotNullParameter(supportedDocumentsRepository, "supportedDocumentsRepository");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        Intrinsics.checkNotNullParameter(jsonParser, "jsonParser");
        this.supportedDocumentsRepository = supportedDocumentsRepository;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
        this.jsonParser = jsonParser;
    }

    public final FlowTask mapFromWorkflowResponse(Response<WorkflowResponse> workflowResponse, ApplicantId applicantId, String workflowRunId) {
        Intrinsics.checkNotNullParameter(workflowResponse, "workflowResponse");
        Intrinsics.checkNotNullParameter(applicantId, "applicantId");
        Intrinsics.checkNotNullParameter(workflowRunId, "workflowRunId");
        WorkflowResponse workflowResponseBody = workflowResponse.body();
        InteractiveTaskStatus interactiveTaskStatus = workflowResponseBody != null ? workflowResponseBody.getInteractiveTaskStatus() : null;
        int i = interactiveTaskStatus == null ? -1 : WhenMappings.$EnumSwitchMapping$0[interactiveTaskStatus.ordinal()];
        if (i == -1 || i == 1) {
            return new FlowTask.InteractiveTask(new WorkflowTask.FinishFlowTask(null, null, new OnfidoWorkflow.WorkflowException.WorkflowUnknownException("Workflow is invalid", null, 2, null), 3, null));
        }
        if (i == 2) {
            return new FlowTask.InteractiveTask(new WorkflowTask.FinishFlowTask(null, null, new OnfidoWorkflow.WorkflowException.WorkflowAbandonedException("Workflow abandoned exception"), 3, null));
        }
        if (i == 3) {
            return new FlowTask.InteractiveTask(new WorkflowTask.FinishFlowTask(null, null, null, 7, null));
        }
        if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
        if (workflowResponseBody.getCurrentTask() != null) {
            CurrentApplicantTask currentTask = workflowResponseBody.getCurrentTask();
            return mapFromTaskDefId(currentTask.getId(), currentTask.getTaskDefId(), currentTask.getConfiguration(), applicantId, workflowRunId, currentTask.getTaskInput());
        }
        return FlowTask.NonInteractiveTask.INSTANCE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0094, code lost:
    
        if (r8.equals(com.onfido.workflow.internal.workflow.WorkflowTaskMapper.TASK_DEF_FACE_MOTION_AUTH) == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ba, code lost:
    
        if (r8.equals(com.onfido.workflow.internal.workflow.WorkflowTaskMapper.TASK_DEF_DOCUMENT) == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00e7, code lost:
    
        if (r8.equals(com.onfido.workflow.internal.workflow.WorkflowTaskMapper.TASK_DEF_DOCUMENT_PHOTO) == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:?, code lost:
    
        return mapMotionTask(r9, r7, r8, r10, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:?, code lost:
    
        return mapDocumentTask(r9, r7, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000f, code lost:
    
        if (r8.equals(com.onfido.workflow.internal.workflow.WorkflowTaskMapper.TASK_DEF_FACE_MOTION) == false) goto L54;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.onfido.workflow.internal.ui.model.FlowTask mapFromTaskDefId(java.lang.String r7, java.lang.String r8, kotlinx.serialization.json.JsonObject r9, com.onfido.api.client.token.sdk.ApplicantId r10, java.lang.String r11, kotlinx.serialization.json.JsonObject r12) {
        /*
            Method dump skipped, instructions count: 300
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.workflow.internal.workflow.WorkflowTaskMapper.mapFromTaskDefId(java.lang.String, java.lang.String, kotlinx.serialization.json.JsonObject, com.onfido.api.client.token.sdk.ApplicantId, java.lang.String, kotlinx.serialization.json.JsonObject):com.onfido.workflow.internal.ui.model.FlowTask");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.onfido.workflow.internal.ui.model.FlowTask mapRetryTask(kotlinx.serialization.json.JsonObject r8, java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 280
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.workflow.internal.workflow.WorkflowTaskMapper.mapRetryTask(kotlinx.serialization.json.JsonObject, java.lang.String, java.lang.String):com.onfido.workflow.internal.ui.model.FlowTask");
    }

    private final FlowTask.InteractiveTask mapSelfieTask(JsonObject configuration, String taskId, String taskDefId, ApplicantId applicantId) {
        Object objM6095constructorimpl;
        Object objM6095constructorimpl2;
        if (this.onfidoRemoteConfig.getMotionExperiment().isEnabled()) {
            return mapMotionTask$default(this, configuration, taskId, taskDefId, applicantId, null, 16, null);
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            WorkflowTaskMapper workflowTaskMapper = this;
            Json json = this.jsonParser;
            objM6095constructorimpl = Result.m6095constructorimpl((SelfieTaskConfig) json.decodeFromJsonElement(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(SelfieTaskConfig.class)), configuration));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM6095constructorimpl = Result.m6095constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6102isSuccessimpl(objM6095constructorimpl)) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                objM6095constructorimpl2 = Result.m6095constructorimpl(new FlowTask.InteractiveTask(new WorkflowTask.FacePhotoTask(taskId, taskDefId, ((SelfieTaskConfig) objM6095constructorimpl).getShowIntro())));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                objM6095constructorimpl = ResultKt.createFailure(th2);
            }
        } else {
            objM6095constructorimpl2 = Result.m6095constructorimpl(objM6095constructorimpl);
        }
        if (Result.m6098exceptionOrNullimpl(objM6095constructorimpl2) != null) {
            objM6095constructorimpl2 = new FlowTask.InteractiveTask(new WorkflowTask.FacePhotoTask(taskId, taskDefId, true));
        }
        return (FlowTask.InteractiveTask) objM6095constructorimpl2;
    }

    private final FlowTask.InteractiveTask mapFaceVideoTask(JsonObject configuration, String taskId, String taskDefId, ApplicantId applicantId) {
        Object objM6095constructorimpl;
        Object objM6095constructorimpl2;
        if (this.onfidoRemoteConfig.getMotionExperiment().isEnabled()) {
            return mapMotionTask$default(this, configuration, taskId, taskDefId, applicantId, null, 16, null);
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            WorkflowTaskMapper workflowTaskMapper = this;
            Json json = this.jsonParser;
            objM6095constructorimpl = Result.m6095constructorimpl((FaceVideoTaskConfig) json.decodeFromJsonElement(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(FaceVideoTaskConfig.class)), configuration));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM6095constructorimpl = Result.m6095constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6102isSuccessimpl(objM6095constructorimpl)) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                FaceVideoTaskConfig faceVideoTaskConfig = (FaceVideoTaskConfig) objM6095constructorimpl;
                objM6095constructorimpl2 = Result.m6095constructorimpl(new FlowTask.InteractiveTask(new WorkflowTask.FaceVideoTask(taskId, taskDefId, faceVideoTaskConfig.getShowIntro(), faceVideoTaskConfig.getShowVideoConfirmation())));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                objM6095constructorimpl = ResultKt.createFailure(th2);
            }
        } else {
            objM6095constructorimpl2 = Result.m6095constructorimpl(objM6095constructorimpl);
        }
        if (Result.m6098exceptionOrNullimpl(objM6095constructorimpl2) != null) {
            objM6095constructorimpl2 = new FlowTask.InteractiveTask(new WorkflowTask.FaceVideoTask(taskId, taskDefId, true, true));
        }
        return (FlowTask.InteractiveTask) objM6095constructorimpl2;
    }

    static /* synthetic */ FlowTask.InteractiveTask mapMotionTask$default(WorkflowTaskMapper workflowTaskMapper, JsonObject jsonObject, String str, String str2, ApplicantId applicantId, JsonObject jsonObject2, int i, Object obj) {
        if ((i & 16) != 0) {
            jsonObject2 = null;
        }
        return workflowTaskMapper.mapMotionTask(jsonObject, str, str2, applicantId, jsonObject2);
    }

    private final FlowTask.InteractiveTask mapMotionTask(JsonObject configuration, String taskId, String taskDefId, ApplicantId applicantId, JsonObject taskInput) {
        Object objM6095constructorimpl;
        String encryptedBiometricToken;
        Object objM6095constructorimpl2;
        try {
            Result.Companion companion = Result.INSTANCE;
            WorkflowTaskMapper workflowTaskMapper = this;
            Json json = this.jsonParser;
            objM6095constructorimpl = Result.m6095constructorimpl((MotionTaskConfig) json.decodeFromJsonElement(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(MotionTaskConfig.class)), configuration));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM6095constructorimpl = Result.m6095constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6102isSuccessimpl(objM6095constructorimpl)) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                MotionTaskConfig motionTaskConfig = (MotionTaskConfig) objM6095constructorimpl;
                boolean showIntro = motionTaskConfig.getShowIntro();
                boolean audioEnabled = motionTaskConfig.getAudioEnabled();
                if (taskInput != null) {
                    Json json2 = this.jsonParser;
                    encryptedBiometricToken = ((MotionTaskInput) json2.decodeFromJsonElement(SerializersKt.serializer(json2.getSerializersModule(), Reflection.typeOf(MotionTaskInput.class)), taskInput)).getEncryptedBiometricToken();
                } else {
                    encryptedBiometricToken = null;
                }
                objM6095constructorimpl2 = Result.m6095constructorimpl(new FlowTask.InteractiveTask(new WorkflowTask.FaceMotionTask(taskId, taskDefId, new MotionCaptureVariantOptions(showIntro, audioEnabled, false, encryptedBiometricToken, 4, null), applicantId)));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                objM6095constructorimpl = ResultKt.createFailure(th2);
            }
        } else {
            objM6095constructorimpl2 = Result.m6095constructorimpl(objM6095constructorimpl);
        }
        if (Result.m6098exceptionOrNullimpl(objM6095constructorimpl2) != null) {
            objM6095constructorimpl2 = new FlowTask.InteractiveTask(new WorkflowTask.FaceMotionTask(taskId, taskDefId, MotionCaptureVariantOptions.INSTANCE.getDEFAULT(), applicantId));
        }
        return (FlowTask.InteractiveTask) objM6095constructorimpl2;
    }

    private final FlowTask.InteractiveTask mapBiometricTokenRetrievalTask(String taskId, String taskDefId) {
        return new FlowTask.InteractiveTask(new WorkflowTask.BiometricTokenRetrievalTask(taskId, taskDefId));
    }

    private final FlowTask mapDocumentTask(JsonObject configuration, String taskId, String taskDefId) {
        Object objM6095constructorimpl;
        Object objM6095constructorimpl2;
        try {
            Result.Companion companion = Result.INSTANCE;
            WorkflowTaskMapper workflowTaskMapper = this;
            Json json = this.jsonParser;
            objM6095constructorimpl = Result.m6095constructorimpl((DocumentUploadTaskConfig) json.decodeFromJsonElement(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(DocumentUploadTaskConfig.class)), configuration));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM6095constructorimpl = Result.m6095constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6102isSuccessimpl(objM6095constructorimpl)) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                DocumentUploadTaskConfig documentUploadTaskConfig = (DocumentUploadTaskConfig) objM6095constructorimpl;
                List<CountryCode> listFindAllSupportedCountries = this.supportedDocumentsRepository.findAllSupportedCountries();
                LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listFindAllSupportedCountries, 10)), 16));
                for (Object obj : listFindAllSupportedCountries) {
                    linkedHashMap.put(((CountryCode) obj).getAlpha3(), obj);
                }
                List<DocumentUploadTaskConfig.DocumentSelectionItem> documentSelection = documentUploadTaskConfig.getDocumentSelection();
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                for (Object obj2 : documentSelection) {
                    String iso3CountryCode = ((DocumentUploadTaskConfig.DocumentSelectionItem) obj2).getIso3CountryCode();
                    Object obj3 = linkedHashMap2.get(iso3CountryCode);
                    if (obj3 == null) {
                        obj3 = (List) new ArrayList();
                        linkedHashMap2.put(iso3CountryCode, obj3);
                    }
                    ((List) obj3).add(obj2);
                }
                LinkedHashMap linkedHashMap3 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap2.size()));
                for (Object obj4 : linkedHashMap2.entrySet()) {
                    linkedHashMap3.put((CountryCode) linkedHashMap.get(((Map.Entry) obj4).getKey()), ((Map.Entry) obj4).getValue());
                }
                LinkedHashMap linkedHashMap4 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap3.size()));
                for (Object obj5 : linkedHashMap3.entrySet()) {
                    Object key = ((Map.Entry) obj5).getKey();
                    Iterable iterable = (Iterable) ((Map.Entry) obj5).getValue();
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                    Iterator it = iterable.iterator();
                    while (it.hasNext()) {
                        arrayList.add(mapToOnfidoDocumentType(((DocumentUploadTaskConfig.DocumentSelectionItem) it.next()).getDocumentType()));
                    }
                    linkedHashMap4.put(key, arrayList);
                }
                LinkedHashMap linkedHashMap5 = new LinkedHashMap();
                for (Map.Entry entry : linkedHashMap4.entrySet()) {
                    if (((CountryCode) entry.getKey()) != null) {
                        linkedHashMap5.put(entry.getKey(), entry.getValue());
                    }
                }
                objM6095constructorimpl2 = Result.m6095constructorimpl(new FlowTask.InteractiveTask(new WorkflowTask.DocumentTask(taskId, taskDefId, createNFCOptions(documentUploadTaskConfig), linkedHashMap5)));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                objM6095constructorimpl = ResultKt.createFailure(th2);
            }
        } else {
            objM6095constructorimpl2 = Result.m6095constructorimpl(objM6095constructorimpl);
        }
        Throwable thM6098exceptionOrNullimpl = Result.m6098exceptionOrNullimpl(objM6095constructorimpl2);
        if (thM6098exceptionOrNullimpl != null) {
            objM6095constructorimpl2 = new FlowTask.InteractiveTask(new WorkflowTask.UnsupportedTask(null, null, taskDefId, thM6098exceptionOrNullimpl, 3, null));
        }
        return (FlowTask) objM6095constructorimpl2;
    }

    private final FlowTask mapCaptureSDKTask(JsonObject configuration, JsonObject taskInput, String taskId, String taskDefId, String workflowRunId) {
        Json json = this.jsonParser;
        String strEncodeToString = json.encodeToString(SerializersKt.serializer(json.getSerializersModule(), Reflection.nullableTypeOf(JsonElement.class)), JsonExtKt.convertSnakeToCamelCase(configuration));
        Json json2 = this.jsonParser;
        JsonObject jsonObjectEmptyMap = taskInput;
        if (jsonObjectEmptyMap == null) {
            jsonObjectEmptyMap = MapsKt.emptyMap();
        }
        return new FlowTask.InteractiveTask(new WorkflowTask.CaptureSdkModuleTask(taskId, taskDefId, strEncodeToString, json2.encodeToString(SerializersKt.serializer(json2.getSerializersModule(), Reflection.nullableTypeOf(JsonElement.class)), JsonExtKt.convertSnakeToCamelCase(new JsonObject(MapsKt.plus(jsonObjectEmptyMap, TuplesKt.to("workflow_run_id", JsonElementKt.JsonPrimitive(workflowRunId))))))));
    }

    private final FlowTask mapOtpTask(String taskId, String taskDefId, String workflowRunId, JsonObject taskInput, JsonObject configuration) {
        JsonObject jsonObject = new JsonObject(MapsKt.plus(MapsKt.toMap(configuration), MapsKt.mapOf(TuplesKt.to("capture_module", new JsonObject(MapsKt.mapOf(TuplesKt.to("module", JsonElementKt.JsonPrimitive("oneTimePassword")), TuplesKt.to("type", JsonElementKt.JsonPrimitive("hosted")), TuplesKt.to("version", JsonElementKt.JsonPrimitive(DEFAULT_WEB_CAPTURE_SDK_MODULE_VERSION))))))));
        JsonObject jsonObjectEmptyMap = taskInput;
        if (jsonObjectEmptyMap == null) {
            jsonObjectEmptyMap = MapsKt.emptyMap();
        }
        return mapCaptureSDKTask(jsonObject, new JsonObject(MapsKt.plus(jsonObjectEmptyMap, TuplesKt.to("taskId", JsonElementKt.JsonPrimitive(taskId)))), taskId, taskDefId, workflowRunId);
    }

    private final NFCOptions createNFCOptions(DocumentUploadTaskConfig documentUploadTaskConfig) {
        DocumentUploadTaskConfig.NFCProcessingOption nfcProcessingOption = documentUploadTaskConfig.getNfcProcessingOption();
        if (nfcProcessingOption == null) {
            nfcProcessingOption = DocumentUploadTaskConfig.NFCProcessingOption.OPTIONAL;
        }
        int i = WhenMappings.$EnumSwitchMapping$1[nfcProcessingOption.ordinal()];
        if (i == 1) {
            return NFCOptions.Disabled.INSTANCE;
        }
        if (i == 2) {
            return NFCOptions.Enabled.Optional.INSTANCE;
        }
        if (i == 3) {
            return NFCOptions.Enabled.Required.INSTANCE;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final DocumentType mapToOnfidoDocumentType(DocType docType) {
        switch (WhenMappings.$EnumSwitchMapping$2[docType.ordinal()]) {
            case 1:
                return DocumentType.PASSPORT;
            case 2:
                return DocumentType.DRIVING_LICENCE;
            case 3:
                return DocumentType.NATIONAL_IDENTITY_CARD;
            case 4:
                return DocumentType.VISA;
            case 5:
                return DocumentType.WORK_PERMIT;
            case 6:
                return DocumentType.RESIDENCE_PERMIT;
            case 7:
                return DocumentType.UNKNOWN;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.onfido.workflow.internal.ui.model.FlowTask.InteractiveTask mapBiometricTokenStorageTask(java.lang.String r4, java.lang.String r5, kotlinx.serialization.json.JsonObject r6) {
        /*
            r3 = this;
            if (r6 == 0) goto L22
            kotlinx.serialization.json.Json r0 = r3.jsonParser
            kotlinx.serialization.modules.SerializersModule r1 = r0.getSerializersModule()
            java.lang.Class<com.onfido.workflow.internal.network.BiometricTokenStorageTaskInput> r2 = com.onfido.workflow.internal.network.BiometricTokenStorageTaskInput.class
            kotlin.reflect.KType r2 = kotlin.jvm.internal.Reflection.typeOf(r2)
            kotlinx.serialization.KSerializer r1 = kotlinx.serialization.SerializersKt.serializer(r1, r2)
            kotlinx.serialization.DeserializationStrategy r1 = (kotlinx.serialization.DeserializationStrategy) r1
            kotlinx.serialization.json.JsonElement r6 = (kotlinx.serialization.json.JsonElement) r6
            java.lang.Object r6 = r0.decodeFromJsonElement(r1, r6)
            com.onfido.workflow.internal.network.BiometricTokenStorageTaskInput r6 = (com.onfido.workflow.internal.network.BiometricTokenStorageTaskInput) r6
            java.lang.String r6 = r6.getMediaUuid()
            if (r6 != 0) goto L24
        L22:
            java.lang.String r6 = ""
        L24:
            com.onfido.workflow.internal.workflow.WorkflowTask$BiometricTokenStorageTask r0 = new com.onfido.workflow.internal.workflow.WorkflowTask$BiometricTokenStorageTask
            r0.<init>(r4, r5, r6)
            com.onfido.workflow.internal.workflow.WorkflowTask r0 = (com.onfido.workflow.internal.workflow.WorkflowTask) r0
            com.onfido.workflow.internal.ui.model.FlowTask$InteractiveTask r4 = new com.onfido.workflow.internal.ui.model.FlowTask$InteractiveTask
            r4.<init>(r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.workflow.internal.workflow.WorkflowTaskMapper.mapBiometricTokenStorageTask(java.lang.String, java.lang.String, kotlinx.serialization.json.JsonObject):com.onfido.workflow.internal.ui.model.FlowTask$InteractiveTask");
    }
}
