package com.onfido.android.sdk.capture.ui.documentselection;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.document.DocumentPages;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class DocumentTypeSelectionFragment$documentsAdapter$1 extends FunctionReferenceImpl implements Function3<DocumentType, String, DocumentPages, Unit> {
    DocumentTypeSelectionFragment$documentsAdapter$1(Object obj) {
        super(3, obj, DocumentTypeSelectionFragment.class, "onDocumentSelected", "onDocumentSelected(Lcom/onfido/android/sdk/capture/DocumentType;Ljava/lang/String;Lcom/onfido/android/sdk/capture/document/DocumentPages;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(DocumentType documentType, String str, DocumentPages documentPages) {
        invoke2(documentType, str, documentPages);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(DocumentType p0, String str, DocumentPages documentPages) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((DocumentTypeSelectionFragment) this.receiver).onDocumentSelected(p0, str, documentPages);
    }
}
