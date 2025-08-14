package com.onfido.android.sdk.capture.document.supported.domain.usecase;

import com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/document/supported/domain/usecase/GetSupportedDocumentsUseCase;", "", "remoteSupportedDocumentsRepository", "Lcom/onfido/android/sdk/capture/document/supported/data/repository/RemoteSupportedDocumentsRepository;", "(Lcom/onfido/android/sdk/capture/document/supported/data/repository/RemoteSupportedDocumentsRepository;)V", "invoke", "Lio/reactivex/rxjava3/core/Completable;", "hasPreselectedDocuments", "", "isDocumentSupportRulesEnabled", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GetSupportedDocumentsUseCase {
    private final RemoteSupportedDocumentsRepository remoteSupportedDocumentsRepository;

    @Inject
    public GetSupportedDocumentsUseCase(RemoteSupportedDocumentsRepository remoteSupportedDocumentsRepository) {
        Intrinsics.checkNotNullParameter(remoteSupportedDocumentsRepository, "remoteSupportedDocumentsRepository");
        this.remoteSupportedDocumentsRepository = remoteSupportedDocumentsRepository;
    }

    public final Completable invoke(boolean hasPreselectedDocuments, boolean isDocumentSupportRulesEnabled) {
        Completable completableComplete;
        String str;
        if (!isDocumentSupportRulesEnabled || hasPreselectedDocuments) {
            completableComplete = Completable.complete();
            str = "complete(...)";
        } else {
            completableComplete = this.remoteSupportedDocumentsRepository.fetchSupportedDocuments().onErrorComplete();
            str = "onErrorComplete(...)";
        }
        Intrinsics.checkNotNullExpressionValue(completableComplete, str);
        return completableComplete;
    }
}
