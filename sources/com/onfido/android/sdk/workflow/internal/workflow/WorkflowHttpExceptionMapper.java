package com.onfido.android.sdk.workflow.internal.workflow;

import com.onfido.android.sdk.capture.utils.NetworkExtensionsKt;
import com.onfido.android.sdk.capture.utils.StringExtensionsKt;
import com.onfido.android.sdk.capture.utils.mapper.Mapper;
import com.onfido.android.sdk.workflow.internal.network.OnfidoHttpException;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.OnfidoWorkflow;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;

/* compiled from: WorkflowHttpExceptionMapper.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/workflow/internal/workflow/WorkflowHttpExceptionMapper;", "Lcom/onfido/android/sdk/capture/utils/mapper/Mapper;", "", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "jsonParser", "Lkotlinx/serialization/json/Json;", "(Lkotlinx/serialization/json/Json;)V", "map", "input", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WorkflowHttpExceptionMapper implements Mapper<Throwable, OnfidoWorkflow.WorkflowException> {
    private final Json jsonParser;

    @Inject
    public WorkflowHttpExceptionMapper(Json jsonParser) {
        Intrinsics.checkNotNullParameter(jsonParser, "jsonParser");
        this.jsonParser = jsonParser;
    }

    @Override // com.onfido.android.sdk.capture.utils.mapper.Mapper
    public OnfidoWorkflow.WorkflowException map(Throwable input) {
        Intrinsics.checkNotNullParameter(input, "input");
        String strHttpErrorBodyString = NetworkExtensionsKt.httpErrorBodyString(input);
        if (StringExtensionsKt.isNotNullOrEmpty(strHttpErrorBodyString)) {
            Json json = this.jsonParser;
            Intrinsics.checkNotNull(strHttpErrorBodyString);
            OnfidoHttpException onfidoHttpException = (OnfidoHttpException) json.decodeFromString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(OnfidoHttpException.class)), strHttpErrorBodyString);
            if (Intrinsics.areEqual(onfidoHttpException.getType(), "unsupported_sdk_version")) {
                return new OnfidoWorkflow.WorkflowException.WorkflowInsufficientVersionException(onfidoHttpException.getMessage());
            }
            return new OnfidoWorkflow.WorkflowException.WorkflowHttpException(onfidoHttpException.getMessage(), input);
        }
        String message = input.getMessage();
        if (message == null) {
            message = "";
        }
        return new OnfidoWorkflow.WorkflowException.WorkflowUnknownException(message, input);
    }
}
