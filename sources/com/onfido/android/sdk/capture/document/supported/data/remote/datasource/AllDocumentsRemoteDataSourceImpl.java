package com.onfido.android.sdk.capture.document.supported.data.remote.datasource;

import com.onfido.android.sdk.capture.document.supported.data.SupportedDocumentMapper;
import com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocument;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.api.client.data.SupportedDocuments;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Singleton;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0011\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0092\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/document/supported/data/remote/datasource/AllDocumentsRemoteDataSourceImpl;", "Lcom/onfido/android/sdk/capture/document/supported/data/remote/datasource/AllDocumentsDataSource;", "apiService", "Lcom/onfido/android/sdk/capture/network/OnfidoApiService;", "documentMapper", "Lcom/onfido/android/sdk/capture/document/supported/data/SupportedDocumentMapper;", "(Lcom/onfido/android/sdk/capture/network/OnfidoApiService;Lcom/onfido/android/sdk/capture/document/supported/data/SupportedDocumentMapper;)V", "supportedDocuments", "", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocument;", "allSupportedDocuments", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Singleton
/* loaded from: classes2.dex */
public class AllDocumentsRemoteDataSourceImpl implements AllDocumentsDataSource {
    private final OnfidoApiService apiService;
    private final SupportedDocumentMapper documentMapper;
    private List<SupportedDocument> supportedDocuments;

    @Inject
    public AllDocumentsRemoteDataSourceImpl(OnfidoApiService apiService, SupportedDocumentMapper documentMapper) {
        Intrinsics.checkNotNullParameter(apiService, "apiService");
        Intrinsics.checkNotNullParameter(documentMapper, "documentMapper");
        this.apiService = apiService;
        this.documentMapper = documentMapper;
        this.supportedDocuments = CollectionsKt.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SupportedDocuments allSupportedDocuments$lambda$0(Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new SupportedDocuments(CollectionsKt.emptyList(), MapsKt.emptyMap());
    }

    @Override // com.onfido.android.sdk.capture.document.supported.data.remote.datasource.AllDocumentsDataSource
    public List<SupportedDocument> allSupportedDocuments() {
        if (this.supportedDocuments.isEmpty()) {
            SupportedDocuments supportedDocumentsBlockingGet = this.apiService.getSupportedDocuments$onfido_capture_sdk_core_release().onErrorReturn(new Function() { // from class: com.onfido.android.sdk.capture.document.supported.data.remote.datasource.AllDocumentsRemoteDataSourceImpl$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return AllDocumentsRemoteDataSourceImpl.allSupportedDocuments$lambda$0((Throwable) obj);
                }
            }).blockingGet();
            Intrinsics.checkNotNullExpressionValue(supportedDocumentsBlockingGet, "blockingGet(...)");
            this.supportedDocuments = this.documentMapper.mapSupportedDocuments(supportedDocumentsBlockingGet);
        }
        return this.supportedDocuments;
    }
}
