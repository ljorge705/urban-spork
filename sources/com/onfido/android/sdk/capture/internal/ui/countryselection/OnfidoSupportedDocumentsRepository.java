package com.onfido.android.sdk.capture.internal.ui.countryselection;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.document.GenericDocument;
import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.AllDocumentsDataSource;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.CountryCodeExtensionsKt;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0017J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u0006\u0010\f\u001a\u00020\tH\u0016J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u0010\f\u001a\u00020\tH\u0017J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/OnfidoSupportedDocumentsRepository;", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocumentsRepository;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "allDocumentsDataSource", "Lcom/onfido/android/sdk/capture/document/supported/data/remote/datasource/AllDocumentsDataSource;", "(Lcom/onfido/android/sdk/capture/OnfidoConfig;Lcom/onfido/android/sdk/capture/document/supported/data/remote/datasource/AllDocumentsDataSource;)V", "findAllSupportedCountries", "", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "findGenericDocuments", "Lcom/onfido/android/sdk/capture/document/GenericDocument;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "findSupportedDocumentTypes", "Lcom/onfido/android/sdk/capture/DocumentType;", "isDocumentAllowed", "", "supportedDocument", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocument;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Singleton
/* loaded from: classes2.dex */
public class OnfidoSupportedDocumentsRepository implements SupportedDocumentsRepository {
    private final AllDocumentsDataSource allDocumentsDataSource;
    private final OnfidoConfig onfidoConfig;

    @Inject
    public OnfidoSupportedDocumentsRepository(OnfidoConfig onfidoConfig, AllDocumentsDataSource allDocumentsDataSource) {
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        Intrinsics.checkNotNullParameter(allDocumentsDataSource, "allDocumentsDataSource");
        this.onfidoConfig = onfidoConfig;
        this.allDocumentsDataSource = allDocumentsDataSource;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDocumentAllowed(SupportedDocument supportedDocument) {
        return this.onfidoConfig.getDocumentTypes().isEmpty() || CollectionsKt.contains(this.onfidoConfig.getDocumentTypes(), supportedDocument.getDocumentType());
    }

    @Override // com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository
    public List<CountryCode> findAllSupportedCountries() {
        Object objM6095constructorimpl;
        List<SupportedDocument> listAllSupportedDocuments = this.allDocumentsDataSource.allSupportedDocuments();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listAllSupportedDocuments) {
            if (((SupportedDocument) obj).getDocumentType() != null) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (CollectionsKt.contains(this.onfidoConfig.getDocumentTypes(), ((SupportedDocument) obj2).getDocumentType())) {
                arrayList2.add(obj2);
            }
        }
        if (!arrayList2.isEmpty()) {
            listAllSupportedDocuments = arrayList2;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj3 : listAllSupportedDocuments) {
            String countryCodeAlpha2 = ((SupportedDocument) obj3).getCountryCodeAlpha2();
            Object arrayList3 = linkedHashMap.get(countryCodeAlpha2);
            if (arrayList3 == null) {
                arrayList3 = new ArrayList();
                linkedHashMap.put(countryCodeAlpha2, arrayList3);
            }
            ((List) arrayList3).add(obj3);
        }
        CountryCode[] countryCodeArrValues = CountryCode.values();
        ArrayList arrayList4 = new ArrayList();
        for (CountryCode countryCode : countryCodeArrValues) {
            List list = (List) linkedHashMap.get(countryCode.name());
            if (list != null) {
                arrayList4.add(list);
            }
        }
        ArrayList arrayList5 = new ArrayList();
        for (Object obj4 : arrayList4) {
            List list2 = (List) obj4;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator it = list2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (((SupportedDocument) it.next()).getHasValidUseCase()) {
                        arrayList5.add(obj4);
                        break;
                    }
                }
            }
        }
        ArrayList arrayList6 = new ArrayList();
        Iterator it2 = arrayList5.iterator();
        while (it2.hasNext()) {
            SupportedDocument supportedDocument = (SupportedDocument) CollectionsKt.firstOrNull((List) it2.next());
            CountryCode countryCode2 = null;
            if (supportedDocument != null) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM6095constructorimpl = Result.m6095constructorimpl(CountryCode.valueOf(supportedDocument.getCountryCodeAlpha2()));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM6095constructorimpl = Result.m6095constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m6101isFailureimpl(objM6095constructorimpl)) {
                    objM6095constructorimpl = null;
                }
                CountryCode countryCode3 = (CountryCode) objM6095constructorimpl;
                if (countryCode3 != null) {
                    countryCode3.setNativeName(supportedDocument.getCountryNativeName());
                    countryCode3.setLocaleName(supportedDocument.getCountryLocaleName());
                    countryCode2 = countryCode3;
                }
            }
            if (countryCode2 != null) {
                arrayList6.add(countryCode2);
            }
        }
        List<GenericDocument> genericDocuments = this.onfidoConfig.getGenericDocuments();
        ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(genericDocuments, 10));
        Iterator<T> it3 = genericDocuments.iterator();
        while (it3.hasNext()) {
            arrayList7.add(((GenericDocument) it3.next()).getCountryCode());
        }
        return CollectionsKt.distinct(CollectionsKt.sortedWith(CollectionsKt.plus((Collection) arrayList6, (Iterable) arrayList7), new Comparator() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository$findAllSupportedCountries$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(CountryCodeExtensionsKt.getDisplayName((CountryCode) t), CountryCodeExtensionsKt.getDisplayName((CountryCode) t2));
            }
        }));
    }

    @Override // com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository
    public List<GenericDocument> findGenericDocuments(CountryCode countryCode) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        List<GenericDocument> genericDocuments = this.onfidoConfig.getGenericDocuments();
        ArrayList arrayList = new ArrayList();
        for (Object obj : genericDocuments) {
            if (((GenericDocument) obj).getCountryCode() == countryCode) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository
    public List<DocumentType> findSupportedDocumentTypes(final CountryCode countryCode) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        return SequencesKt.toList(SequencesKt.sortedWith(SequencesKt.distinct(SequencesKt.mapNotNull(SequencesKt.filter(CollectionsKt.asSequence(this.allDocumentsDataSource.allSupportedDocuments()), new Function1<SupportedDocument, Boolean>() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository.findSupportedDocumentTypes.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Boolean invoke(com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocument r3) {
                /*
                    r2 = this;
                    java.lang.String r0 = "doc"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                    com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository r0 = com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository.this
                    boolean r0 = com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository.access$isDocumentAllowed(r0, r3)
                    if (r0 == 0) goto L25
                    boolean r0 = r3.getHasValidUseCase()
                    if (r0 == 0) goto L25
                    java.lang.String r3 = r3.getCountryCodeAlpha2()
                    com.onfido.android.sdk.capture.utils.CountryCode r0 = r2
                    java.lang.String r0 = r0.name()
                    r1 = 1
                    boolean r3 = kotlin.text.StringsKt.equals(r3, r0, r1)
                    if (r3 == 0) goto L25
                    goto L26
                L25:
                    r1 = 0
                L26:
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r1)
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository.AnonymousClass1.invoke(com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocument):java.lang.Boolean");
            }
        }), new Function1<SupportedDocument, DocumentType>() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository.findSupportedDocumentTypes.2
            @Override // kotlin.jvm.functions.Function1
            public final DocumentType invoke(SupportedDocument it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getDocumentType();
            }
        })), new Comparator() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository$findSupportedDocumentTypes$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((DocumentType) t).ordinal()), Integer.valueOf(((DocumentType) t2).ordinal()));
            }
        }));
    }
}
