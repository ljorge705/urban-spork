package com.onfido.android.sdk.capture.document.supported.data;

import com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocument;
import com.onfido.api.client.data.DocumentIssuer;
import com.onfido.api.client.data.DocumentProperty;
import com.onfido.api.client.data.SupportedDocuments;
import com.onfido.javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/document/supported/data/SupportedDocumentMapper;", "", "()V", "isSupportedDocument", "", "supportedDocument", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocument;", "mapSupportedDocuments", "", "allSupportedDocuments", "Lcom/onfido/api/client/data/SupportedDocuments;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SupportedDocumentMapper {
    @Inject
    public SupportedDocumentMapper() {
    }

    private final boolean isSupportedDocument(SupportedDocument supportedDocument) {
        return !CollectionsKt.contains(SupportedDocumentMapperKt.NOT_SUPPORTED_DOCUMENTS, supportedDocument.getDocumentType());
    }

    public final List<SupportedDocument> mapSupportedDocuments(SupportedDocuments allSupportedDocuments) {
        SupportedDocument supportedDocument;
        Intrinsics.checkNotNullParameter(allSupportedDocuments, "allSupportedDocuments");
        List<com.onfido.api.client.data.SupportedDocument> docs = allSupportedDocuments.getDocs();
        ArrayList arrayList = new ArrayList();
        for (com.onfido.api.client.data.SupportedDocument supportedDocument2 : docs) {
            DocumentIssuer documentIssuer = allSupportedDocuments.getIssuers().get(supportedDocument2.getIssuerCountry());
            if (documentIssuer == null) {
                supportedDocument = null;
            } else {
                String displayName = documentIssuer.getDisplayName();
                String alpha2 = documentIssuer.getAlpha2();
                String issuerCountry = supportedDocument2.getIssuerCountry();
                String docType = supportedDocument2.getDocType();
                List<DocumentProperty> properties = supportedDocument2.getProperties();
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(properties, 10));
                Iterator<T> it = properties.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((DocumentProperty) it.next()).getUseCases());
                }
                supportedDocument = new SupportedDocument(alpha2, issuerCountry, displayName, docType, CollectionsKt.distinct(CollectionsKt.filterNotNull(CollectionsKt.flatten(arrayList2))));
            }
            if (supportedDocument != null) {
                arrayList.add(supportedDocument);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : arrayList) {
            if (isSupportedDocument((SupportedDocument) obj)) {
                arrayList3.add(obj);
            }
        }
        return arrayList3;
    }
}
