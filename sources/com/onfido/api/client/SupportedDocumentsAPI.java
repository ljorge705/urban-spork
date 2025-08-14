package com.onfido.api.client;

import com.onfido.api.client.data.SupportedDocuments;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SupportedDocumentsAPI.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/onfido/api/client/SupportedDocumentsAPI;", "", "onfidoService", "Lcom/onfido/api/client/OnfidoService;", "(Lcom/onfido/api/client/OnfidoService;)V", "getSupportedDocuments", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/api/client/data/SupportedDocuments;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class SupportedDocumentsAPI {
    private final OnfidoService onfidoService;

    public SupportedDocumentsAPI(OnfidoService onfidoService) {
        Intrinsics.checkNotNullParameter(onfidoService, "onfidoService");
        this.onfidoService = onfidoService;
    }

    public final Single<SupportedDocuments> getSupportedDocuments() {
        Single<SupportedDocuments> supportedDocuments = this.onfidoService.getSupportedDocuments();
        Intrinsics.checkNotNullExpressionValue(supportedDocuments, "getSupportedDocuments(...)");
        return supportedDocuments;
    }
}
