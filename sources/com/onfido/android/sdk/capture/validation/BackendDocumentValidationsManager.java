package com.onfido.android.sdk.capture.validation;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.validation.backend.DefaultBackendValidations;
import com.onfido.android.sdk.capture.internal.validation.backend.IQSValidations;
import com.onfido.api.client.data.DocSide;
import com.onfido.javax.inject.Inject;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0010\u0018\u00002\u00020\u0001B\u001f\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ(\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0090D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/BackendDocumentValidationsManager;", "", "defaultValidations", "Lcom/onfido/android/sdk/capture/internal/validation/backend/DefaultBackendValidations;", "iQSValidations", "Lcom/onfido/android/sdk/capture/internal/validation/backend/IQSValidations;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Lcom/onfido/android/sdk/capture/internal/validation/backend/DefaultBackendValidations;Lcom/onfido/android/sdk/capture/internal/validation/backend/IQSValidations;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "shouldPerformFaceValidation", "", "getShouldPerformFaceValidation$onfido_capture_sdk_core_release", "()Z", "getRequiredValidations", "", "Lcom/onfido/android/sdk/capture/validation/Validation;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "rejectionCount", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class BackendDocumentValidationsManager {
    private final DefaultBackendValidations defaultValidations;
    private final IQSValidations iQSValidations;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private final boolean shouldPerformFaceValidation;

    @Inject
    public BackendDocumentValidationsManager(DefaultBackendValidations defaultValidations, IQSValidations iQSValidations, OnfidoRemoteConfig onfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(defaultValidations, "defaultValidations");
        Intrinsics.checkNotNullParameter(iQSValidations, "iQSValidations");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        this.defaultValidations = defaultValidations;
        this.iQSValidations = iQSValidations;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
        this.shouldPerformFaceValidation = true;
    }

    public List<Validation> getRequiredValidations(DocumentType documentType, DocSide documentSide, int rejectionCount) {
        Intrinsics.checkNotNullParameter(documentSide, "documentSide");
        return this.onfidoRemoteConfig.isImageQualityServiceEnabled() ? this.iQSValidations.getRequiredValidations(documentType, documentSide, rejectionCount) : this.defaultValidations.getRequiredValidations(documentType, documentSide, rejectionCount);
    }

    /* renamed from: getShouldPerformFaceValidation$onfido_capture_sdk_core_release, reason: from getter */
    public boolean getShouldPerformFaceValidation() {
        return this.shouldPerformFaceValidation;
    }
}
