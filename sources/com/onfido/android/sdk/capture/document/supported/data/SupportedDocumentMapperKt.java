package com.onfido.android.sdk.capture.document.supported.data;

import com.onfido.android.sdk.capture.DocumentType;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0003"}, d2 = {"NOT_SUPPORTED_DOCUMENTS", "", "Lcom/onfido/android/sdk/capture/DocumentType;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SupportedDocumentMapperKt {
    private static final Set<DocumentType> NOT_SUPPORTED_DOCUMENTS = SetsKt.setOf((Object[]) new DocumentType[]{DocumentType.VISA, DocumentType.WORK_PERMIT});
}
