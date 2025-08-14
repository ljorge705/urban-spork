package com.onfido.android.sdk.capture.internal.validation.backend;

import com.onfido.android.sdk.capture.DocumentType;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0012J(\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/validation/backend/DefaultBackendValidations;", "Lcom/onfido/android/sdk/capture/internal/validation/ValidationProvider;", "nativeDetector", "Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;", "(Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;)V", "defaultValidations", "", "Lcom/onfido/android/sdk/capture/validation/Validation;", "rejectionCount", "", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "getRequiredValidations", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class DefaultBackendValidations implements ValidationProvider {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int MAX_NUMBER_OF_SERVER_VALIDATION_ATTEMPTS = 2;
    private final NativeDetector nativeDetector;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/validation/backend/DefaultBackendValidations$Companion;", "", "()V", "MAX_NUMBER_OF_SERVER_VALIDATION_ATTEMPTS", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public DefaultBackendValidations(NativeDetector nativeDetector) {
        Intrinsics.checkNotNullParameter(nativeDetector, "nativeDetector");
        this.nativeDetector = nativeDetector;
    }

    private List<Validation> defaultValidations(int rejectionCount, DocSide documentSide) {
        ArrayList arrayList = new ArrayList();
        if (!this.nativeDetector.hasNativeLibrary()) {
            arrayList.add(new Validation(ValidationType.GLARE, ValidationLevel.WARNING));
        }
        if (rejectionCount < 2 && DocSide.FRONT == documentSide) {
            arrayList.add(new Validation(ValidationType.DOCUMENT, ValidationLevel.ERROR));
        }
        return arrayList;
    }

    @Override // com.onfido.android.sdk.capture.internal.validation.ValidationProvider
    public List<Validation> getRequiredValidations(DocumentType documentType, DocSide documentSide, int rejectionCount) {
        Intrinsics.checkNotNullParameter(documentSide, "documentSide");
        return documentType == DocumentType.GENERIC ? CollectionsKt.emptyList() : defaultValidations(rejectionCount, documentSide);
    }
}
