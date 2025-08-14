package com.onfido.android.sdk.capture.internal.ui.countryselection;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.document.GenericDocument;
import com.onfido.android.sdk.capture.utils.CountryCode;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H'J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u0006\u0010\u0007\u001a\u00020\u0004H'J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0007\u001a\u00020\u0004H'¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocumentsRepository;", "", "findAllSupportedCountries", "", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "findGenericDocuments", "Lcom/onfido/android/sdk/capture/document/GenericDocument;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "findSupportedDocumentTypes", "Lcom/onfido/android/sdk/capture/DocumentType;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface SupportedDocumentsRepository {
    List<CountryCode> findAllSupportedCountries();

    List<GenericDocument> findGenericDocuments(CountryCode countryCode);

    List<DocumentType> findSupportedDocumentTypes(CountryCode countryCode);
}
