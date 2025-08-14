package com.onfido.android.sdk.capture.internal.validation;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.validation.Validation;
import com.onfido.api.client.data.DocSide;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b`\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/validation/ValidationProvider;", "", "getRequiredValidations", "", "Lcom/onfido/android/sdk/capture/validation/Validation;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "rejectionCount", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ValidationProvider {
    List<Validation> getRequiredValidations(DocumentType documentType, DocSide documentSide, int rejectionCount);
}
