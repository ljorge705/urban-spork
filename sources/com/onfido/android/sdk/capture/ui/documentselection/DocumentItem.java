package com.onfido.android.sdk.capture.ui.documentselection;

import com.onfido.android.sdk.capture.DocumentType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentItem;", "", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "(Lcom/onfido/android/sdk/capture/DocumentType;)V", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class DocumentItem {
    private final DocumentType documentType;

    public DocumentItem(DocumentType documentType) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        this.documentType = documentType;
    }

    public final DocumentType getDocumentType() {
        return this.documentType;
    }
}
