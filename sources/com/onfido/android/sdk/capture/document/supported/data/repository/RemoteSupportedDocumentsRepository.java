package com.onfido.android.sdk.capture.document.supported.data.repository;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.document.GenericDocument;
import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.AllDocumentsDataSource;
import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.SupportedDocumentsApi;
import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.SupportedDocumentsLocalDataSource;
import com.onfido.android.sdk.capture.document.supported.data.remote.model.SupportedDocumentResponse;
import com.onfido.android.sdk.capture.document.supported.data.remote.model.SupportedDocumentsResponse;
import com.onfido.android.sdk.capture.document.supported.data.remote.model.SupportedDocumentsResponseKt;
import com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocument;
import com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.CountryCodeExtensionsKt;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Singleton;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\fH\u0016J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\f2\u0006\u0010\u001f\u001a\u00020\u001cH\u0016J\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u001f\u001a\u00020\u001cH\u0016J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u001e\u0010%\u001a\u00020\"2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010'\u001a\u00020\rH\u0002J\u0012\u0010(\u001a\u0004\u0018\u00010\u001c2\u0006\u0010#\u001a\u00020$H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0011\u001a\u0004\b\u0013\u0010\u0014R!\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0017\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/onfido/android/sdk/capture/document/supported/data/repository/RemoteSupportedDocumentsRepository;", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocumentsRepository;", "supportedDocumentsApi", "Lcom/onfido/android/sdk/capture/document/supported/data/remote/datasource/SupportedDocumentsApi;", "supportedDocumentsLocalDataSource", "Lcom/onfido/android/sdk/capture/document/supported/data/remote/datasource/SupportedDocumentsLocalDataSource;", "allDocumentsDataSource", "Lcom/onfido/android/sdk/capture/document/supported/data/remote/datasource/AllDocumentsDataSource;", "(Lcom/onfido/android/sdk/capture/document/supported/data/remote/datasource/SupportedDocumentsApi;Lcom/onfido/android/sdk/capture/document/supported/data/remote/datasource/SupportedDocumentsLocalDataSource;Lcom/onfido/android/sdk/capture/document/supported/data/remote/datasource/AllDocumentsDataSource;)V", "countriesSupportedDocuments", "", "", "", "Lcom/onfido/android/sdk/capture/DocumentType;", "getCountriesSupportedDocuments", "()Ljava/util/Map;", "countriesSupportedDocuments$delegate", "Lkotlin/Lazy;", "supportedCountries", "getSupportedCountries", "()Ljava/util/List;", "supportedCountries$delegate", "supportedDocumentTypes", "getSupportedDocumentTypes", "supportedDocumentTypes$delegate", "fetchSupportedDocuments", "Lio/reactivex/rxjava3/core/Completable;", "findAllSupportedCountries", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "findGenericDocuments", "Lcom/onfido/android/sdk/capture/document/GenericDocument;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "findSupportedDocumentTypes", "isDocumentAllowed", "", "supportedDocument", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocument;", "isDocumentAllowedForCountry", "countrySupportedDocuments", "documentType", "mapCountryCode", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Singleton
/* loaded from: classes2.dex */
public final class RemoteSupportedDocumentsRepository implements SupportedDocumentsRepository {
    private final AllDocumentsDataSource allDocumentsDataSource;

    /* renamed from: countriesSupportedDocuments$delegate, reason: from kotlin metadata */
    private final Lazy countriesSupportedDocuments;

    /* renamed from: supportedCountries$delegate, reason: from kotlin metadata */
    private final Lazy supportedCountries;

    /* renamed from: supportedDocumentTypes$delegate, reason: from kotlin metadata */
    private final Lazy supportedDocumentTypes;
    private final SupportedDocumentsApi supportedDocumentsApi;
    private final SupportedDocumentsLocalDataSource supportedDocumentsLocalDataSource;

    @Inject
    public RemoteSupportedDocumentsRepository(SupportedDocumentsApi supportedDocumentsApi, SupportedDocumentsLocalDataSource supportedDocumentsLocalDataSource, AllDocumentsDataSource allDocumentsDataSource) {
        Intrinsics.checkNotNullParameter(supportedDocumentsApi, "supportedDocumentsApi");
        Intrinsics.checkNotNullParameter(supportedDocumentsLocalDataSource, "supportedDocumentsLocalDataSource");
        Intrinsics.checkNotNullParameter(allDocumentsDataSource, "allDocumentsDataSource");
        this.supportedDocumentsApi = supportedDocumentsApi;
        this.supportedDocumentsLocalDataSource = supportedDocumentsLocalDataSource;
        this.allDocumentsDataSource = allDocumentsDataSource;
        this.supportedDocumentTypes = LazyKt.lazy(new Function0<List<? extends DocumentType>>() { // from class: com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository$supportedDocumentTypes$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends DocumentType> invoke() {
                return this.this$0.supportedDocumentsLocalDataSource.getSupportedDocumentTypes();
            }
        });
        this.supportedCountries = LazyKt.lazy(new Function0<List<? extends String>>() { // from class: com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository$supportedCountries$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends String> invoke() {
                return this.this$0.supportedDocumentsLocalDataSource.getSupportedCountries();
            }
        });
        this.countriesSupportedDocuments = LazyKt.lazy(new Function0<Map<String, ? extends List<? extends DocumentType>>>() { // from class: com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository$countriesSupportedDocuments$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map<String, ? extends List<? extends DocumentType>> invoke() {
                return this.this$0.supportedDocumentsLocalDataSource.getCountriesSupportedDocuments();
            }
        });
    }

    private final Map<String, List<DocumentType>> getCountriesSupportedDocuments() {
        return (Map) this.countriesSupportedDocuments.getValue();
    }

    private final List<String> getSupportedCountries() {
        return (List) this.supportedCountries.getValue();
    }

    private final List<DocumentType> getSupportedDocumentTypes() {
        return (List) this.supportedDocumentTypes.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isDocumentAllowed(SupportedDocument supportedDocument) {
        return getSupportedDocumentTypes().isEmpty() || CollectionsKt.contains(getSupportedDocumentTypes(), supportedDocument.getDocumentType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isDocumentAllowedForCountry(List<? extends DocumentType> countrySupportedDocuments, DocumentType documentType) {
        return countrySupportedDocuments.isEmpty() || countrySupportedDocuments.contains(documentType);
    }

    private final CountryCode mapCountryCode(SupportedDocument supportedDocument) {
        Object objM6095constructorimpl;
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
        CountryCode countryCode = (CountryCode) objM6095constructorimpl;
        if (countryCode == null) {
            return null;
        }
        countryCode.setNativeName(supportedDocument.getCountryNativeName());
        countryCode.setLocaleName(supportedDocument.getCountryLocaleName());
        return countryCode;
    }

    public final Completable fetchSupportedDocuments() {
        Completable completableFlatMapCompletable = this.supportedDocumentsApi.getSupportedDocuments().map(new Function() { // from class: com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository.fetchSupportedDocuments.1
            @Override // io.reactivex.rxjava3.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                apply((SupportedDocumentsResponse) obj);
                return Unit.INSTANCE;
            }

            public final void apply(SupportedDocumentsResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                List<SupportedDocumentResponse> supportedDocuments = response.getSupportedDocuments();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (T t : supportedDocuments) {
                    String countryCode = ((SupportedDocumentResponse) t).getCountryCode();
                    Object arrayList = linkedHashMap.get(countryCode);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        linkedHashMap.put(countryCode, arrayList);
                    }
                    ((List) arrayList).add(t);
                }
                LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap.size()));
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    Object key = entry.getKey();
                    Iterable iterable = (Iterable) entry.getValue();
                    ArrayList arrayList2 = new ArrayList();
                    Iterator<T> it = iterable.iterator();
                    while (it.hasNext()) {
                        DocumentType documentType = SupportedDocumentsResponseKt.toDocumentType(((SupportedDocumentResponse) it.next()).getDocumentType());
                        if (documentType != null) {
                            arrayList2.add(documentType);
                        }
                    }
                    linkedHashMap2.put(key, arrayList2);
                }
                RemoteSupportedDocumentsRepository.this.supportedDocumentsLocalDataSource.setDocuments(linkedHashMap2);
            }
        }).flatMapCompletable(new Function() { // from class: com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository.fetchSupportedDocuments.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final CompletableSource apply(Unit it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Completable.complete();
            }
        });
        Intrinsics.checkNotNullExpressionValue(completableFlatMapCompletable, "flatMapCompletable(...)");
        return completableFlatMapCompletable;
    }

    @Override // com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository
    public List<CountryCode> findAllSupportedCountries() {
        List<SupportedDocument> listAllSupportedDocuments = this.allDocumentsDataSource.allSupportedDocuments();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listAllSupportedDocuments) {
            SupportedDocument supportedDocument = (SupportedDocument) obj;
            if (getSupportedCountries().contains(supportedDocument.getCountryCodeAlpha3()) && CollectionsKt.contains(getSupportedDocumentTypes(), supportedDocument.getDocumentType())) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            listAllSupportedDocuments = arrayList;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj2 : listAllSupportedDocuments) {
            String countryCodeAlpha2 = ((SupportedDocument) obj2).getCountryCodeAlpha2();
            Object arrayList2 = linkedHashMap.get(countryCodeAlpha2);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                linkedHashMap.put(countryCodeAlpha2, arrayList2);
            }
            ((List) arrayList2).add(obj2);
        }
        CountryCode[] countryCodeArrValues = CountryCode.values();
        ArrayList arrayList3 = new ArrayList();
        for (CountryCode countryCode : countryCodeArrValues) {
            List list = (List) linkedHashMap.get(countryCode.name());
            if (list != null) {
                arrayList3.add(list);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object obj3 : arrayList3) {
            List list2 = (List) obj3;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator it = list2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (((SupportedDocument) it.next()).getHasValidUseCase()) {
                        arrayList4.add(obj3);
                        break;
                    }
                }
            }
        }
        ArrayList arrayList5 = new ArrayList();
        Iterator it2 = arrayList4.iterator();
        while (it2.hasNext()) {
            SupportedDocument supportedDocument2 = (SupportedDocument) CollectionsKt.firstOrNull((List) it2.next());
            CountryCode countryCodeMapCountryCode = supportedDocument2 == null ? null : mapCountryCode(supportedDocument2);
            if (countryCodeMapCountryCode != null) {
                arrayList5.add(countryCodeMapCountryCode);
            }
        }
        return CollectionsKt.sortedWith(arrayList5, new Comparator() { // from class: com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository$findAllSupportedCountries$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(CountryCodeExtensionsKt.getDisplayName((CountryCode) t), CountryCodeExtensionsKt.getDisplayName((CountryCode) t2));
            }
        });
    }

    @Override // com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository
    public List<GenericDocument> findGenericDocuments(CountryCode countryCode) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        return CollectionsKt.emptyList();
    }

    @Override // com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository
    public List<DocumentType> findSupportedDocumentTypes(final CountryCode countryCode) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        List<SupportedDocument> listAllSupportedDocuments = this.allDocumentsDataSource.allSupportedDocuments();
        final List<DocumentType> listEmptyList = getCountriesSupportedDocuments().get(countryCode.getAlpha3());
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        return SequencesKt.toList(SequencesKt.sortedWith(SequencesKt.distinct(SequencesKt.filter(SequencesKt.mapNotNull(SequencesKt.filter(CollectionsKt.asSequence(listAllSupportedDocuments), new Function1<SupportedDocument, Boolean>() { // from class: com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository.findSupportedDocumentTypes.1
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
                    com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository r0 = com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository.this
                    boolean r0 = com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository.access$isDocumentAllowed(r0, r3)
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
                throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository.C05731.invoke(com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocument):java.lang.Boolean");
            }
        }), new Function1<SupportedDocument, DocumentType>() { // from class: com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository.findSupportedDocumentTypes.2
            @Override // kotlin.jvm.functions.Function1
            public final DocumentType invoke(SupportedDocument it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getDocumentType();
            }
        }), new Function1<DocumentType, Boolean>() { // from class: com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository.findSupportedDocumentTypes.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(DocumentType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(RemoteSupportedDocumentsRepository.this.isDocumentAllowedForCountry(listEmptyList, it));
            }
        })), new Comparator() { // from class: com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository$findSupportedDocumentTypes$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((DocumentType) t).ordinal()), Integer.valueOf(((DocumentType) t2).ordinal()));
            }
        }));
    }
}
