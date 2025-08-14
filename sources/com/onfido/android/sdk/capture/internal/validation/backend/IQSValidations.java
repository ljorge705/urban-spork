package com.onfido.android.sdk.capture.internal.validation.backend;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.validation.ValidationProvider;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.validation.Validation;
import com.onfido.api.client.ValidationLevel;
import com.onfido.api.client.ValidationType;
import com.onfido.api.client.data.DocSide;
import com.onfido.javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J(\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/validation/backend/IQSValidations;", "Lcom/onfido/android/sdk/capture/internal/validation/ValidationProvider;", "nativeDetector", "Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "getRequiredValidations", "", "Lcom/onfido/android/sdk/capture/validation/Validation;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "rejectionCount", "", "validations", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class IQSValidations implements ValidationProvider {
    private final NativeDetector nativeDetector;
    private final OnfidoRemoteConfig onfidoRemoteConfig;

    @Inject
    public IQSValidations(NativeDetector nativeDetector, OnfidoRemoteConfig onfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(nativeDetector, "nativeDetector");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        this.nativeDetector = nativeDetector;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
    }

    private final List<Validation> validations(DocSide documentSide, int rejectionCount) {
        Validation validation;
        ArrayList arrayList = new ArrayList();
        if (!this.nativeDetector.hasNativeLibrary()) {
            arrayList.add(new Validation(ValidationType.GLARE, ValidationLevel.WARNING));
        }
        if (rejectionCount < this.onfidoRemoteConfig.getDocumentCapture().getMaxTotalRetries()) {
            ValidationType validationType = ValidationType.CUTOFF;
            ValidationLevel validationLevel = ValidationLevel.ERROR;
            arrayList.add(new Validation(validationType, validationLevel));
            arrayList.add(new Validation(ValidationType.BLUR, validationLevel));
            if (documentSide == DocSide.FRONT) {
                validation = new Validation(ValidationType.DOCUMENT, validationLevel);
                arrayList.add(validation);
            }
        } else {
            ValidationType validationType2 = ValidationType.CUTOFF;
            ValidationLevel validationLevel2 = ValidationLevel.WARNING;
            arrayList.add(new Validation(validationType2, validationLevel2));
            arrayList.add(new Validation(ValidationType.BLUR, validationLevel2));
            if (documentSide == DocSide.FRONT) {
                validation = new Validation(ValidationType.DOCUMENT, validationLevel2);
                arrayList.add(validation);
            }
        }
        return arrayList;
    }

    @Override // com.onfido.android.sdk.capture.internal.validation.ValidationProvider
    public List<Validation> getRequiredValidations(DocumentType documentType, DocSide documentSide, int rejectionCount) {
        Intrinsics.checkNotNullParameter(documentSide, "documentSide");
        return documentType == DocumentType.GENERIC ? CollectionsKt.emptyList() : validations(documentSide, rejectionCount);
    }
}
