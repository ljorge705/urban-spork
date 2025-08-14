package com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.document.GenericDocument;
import com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.CountryCodeExtensionsKt;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Singleton;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0007¢\u0006\u0002\u0010\u0003J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0007H\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00072\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\f\u001a\u00020\u0006H\u0016J\"\u0010\u000e\u001a\u00020\u000f2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0011H\u0016R\"\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00058\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/workflow/internal/workflow/tasks/documentupload/WorkflowSupportedDocumentsRepository;", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocumentsRepository;", "Lcom/onfido/android/sdk/workflow/internal/workflow/tasks/documentupload/WorkflowSupportedDocumentsStore;", "()V", "supportedDocumentsMap", "", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "", "Lcom/onfido/android/sdk/capture/DocumentType;", "findAllSupportedCountries", "findGenericDocuments", "Lcom/onfido/android/sdk/capture/document/GenericDocument;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "findSupportedDocumentTypes", "storeWorkflowSupportedDocuments", "", "documentsSelection", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Singleton
/* loaded from: classes2.dex */
public final class WorkflowSupportedDocumentsRepository implements SupportedDocumentsRepository, WorkflowSupportedDocumentsStore {
    private final Map<CountryCode, List<DocumentType>> supportedDocumentsMap = new LinkedHashMap();

    @Inject
    public WorkflowSupportedDocumentsRepository() {
    }

    @Override // com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository
    public List<CountryCode> findAllSupportedCountries() {
        List<CountryCode> listSortedWith;
        synchronized (this) {
            listSortedWith = CollectionsKt.sortedWith(this.supportedDocumentsMap.keySet(), new Comparator() { // from class: com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload.WorkflowSupportedDocumentsRepository$findAllSupportedCountries$lambda$0$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(CountryCodeExtensionsKt.getDisplayName((CountryCode) t), CountryCodeExtensionsKt.getDisplayName((CountryCode) t2));
                }
            });
        }
        return listSortedWith;
    }

    @Override // com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository
    public List<GenericDocument> findGenericDocuments(CountryCode countryCode) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        return CollectionsKt.emptyList();
    }

    @Override // com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository
    public List<DocumentType> findSupportedDocumentTypes(CountryCode countryCode) {
        List<DocumentType> listEmptyList;
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        synchronized (this) {
            listEmptyList = this.supportedDocumentsMap.get(countryCode);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
        }
        return listEmptyList;
    }

    @Override // com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload.WorkflowSupportedDocumentsStore
    public void storeWorkflowSupportedDocuments(Map<CountryCode, ? extends List<? extends DocumentType>> documentsSelection) {
        Intrinsics.checkNotNullParameter(documentsSelection, "documentsSelection");
        synchronized (this) {
            this.supportedDocumentsMap.clear();
            this.supportedDocumentsMap.putAll(documentsSelection);
            Unit unit = Unit.INSTANCE;
        }
    }
}
