package com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bç\u0080\u0001\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005H&¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/workflow/internal/workflow/tasks/documentupload/WorkflowSupportedDocumentsStore;", "", "storeWorkflowSupportedDocuments", "", "documentsSelection", "", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "", "Lcom/onfido/android/sdk/capture/DocumentType;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface WorkflowSupportedDocumentsStore {
    void storeWorkflowSupportedDocuments(Map<CountryCode, ? extends List<? extends DocumentType>> documentsSelection);
}
