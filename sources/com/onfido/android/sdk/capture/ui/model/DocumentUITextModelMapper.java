package com.onfido.android.sdk.capture.ui.model;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentFormat;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.CountryCode;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\u000f\u001a\u00020\u00072\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0002\u0010\u0012J$\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00052\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\nH\u0002J2\u0010\u0017\u001a\u00020\u0007*\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u0019R&\u0010\u0003\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\t\u001a&\u0012\u0004\u0012\u00020\n\u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00040\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\r\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u000e\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/model/DocumentUITextModelMapper;", "", "()V", "commonDocFormatModels", "", "Lcom/onfido/android/sdk/capture/DocumentFormat;", "Lcom/onfido/android/sdk/capture/DocumentType;", "Lcom/onfido/android/sdk/capture/ui/model/DocumentTypeUIModel;", "commonDocTypeModels", "docFormatModels", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "frenchDocFormatModels", "germanDocFormatModels", "italianDocFormatModels", "southAfricanDocFormatModels", "foldedDocModel", "label", "", "(Ljava/lang/Integer;)Lcom/onfido/android/sdk/capture/ui/model/DocumentTypeUIModel;", "getDocModel", "documentType", "documentFormat", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "toDocumentUIModel", "isAccessibilityEnabled", "", "isForProofAddress", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentUITextModelMapper {
    public static final DocumentUITextModelMapper INSTANCE;
    private static final Map<DocumentFormat, Map<DocumentType, DocumentTypeUIModel>> commonDocFormatModels;
    private static final Map<DocumentType, DocumentTypeUIModel> commonDocTypeModels;
    private static final Map<CountryCode, Map<DocumentFormat, Map<DocumentType, DocumentTypeUIModel>>> docFormatModels;
    private static final Map<DocumentFormat, Map<DocumentType, DocumentTypeUIModel>> frenchDocFormatModels;
    private static final Map<DocumentFormat, Map<DocumentType, DocumentTypeUIModel>> germanDocFormatModels;
    private static final Map<DocumentFormat, Map<DocumentType, DocumentTypeUIModel>> italianDocFormatModels;
    private static final Map<DocumentFormat, Map<DocumentType, DocumentTypeUIModel>> southAfricanDocFormatModels;

    static {
        DocumentUITextModelMapper documentUITextModelMapper = new DocumentUITextModelMapper();
        INSTANCE = documentUITextModelMapper;
        DocumentFormat documentFormat = DocumentFormat.FOLDED;
        DocumentType documentType = DocumentType.NATIONAL_IDENTITY_CARD;
        Pair pair = TuplesKt.to(documentFormat, MapsKt.mapOf(TuplesKt.to(documentType, documentUITextModelMapper.foldedDocModel(Integer.valueOf(R.string.onfido_app_title_doc_capture_id_it)))));
        DocumentFormat documentFormat2 = DocumentFormat.CARD;
        DocumentTypeUIModels documentTypeUIModels = DocumentTypeUIModels.INSTANCE;
        Map<DocumentFormat, Map<DocumentType, DocumentTypeUIModel>> mapMapOf = MapsKt.mapOf(pair, TuplesKt.to(documentFormat2, MapsKt.mapOf(TuplesKt.to(documentType, documentTypeUIModels.getIdDocModel$onfido_capture_sdk_core_release()))));
        italianDocFormatModels = mapMapOf;
        Map<DocumentFormat, Map<DocumentType, DocumentTypeUIModel>> mapMapOf2 = MapsKt.mapOf(TuplesKt.to(documentFormat, MapsKt.mapOf(TuplesKt.to(documentType, documentUITextModelMapper.foldedDocModel(Integer.valueOf(R.string.onfido_app_title_doc_capture_id_za))))), TuplesKt.to(documentFormat2, MapsKt.mapOf(TuplesKt.to(documentType, documentTypeUIModels.getIdDocModel$onfido_capture_sdk_core_release()))));
        southAfricanDocFormatModels = mapMapOf2;
        DocumentType documentType2 = DocumentType.DRIVING_LICENCE;
        Map<DocumentFormat, Map<DocumentType, DocumentTypeUIModel>> mapMapOf3 = MapsKt.mapOf(TuplesKt.to(documentFormat, MapsKt.mapOf(TuplesKt.to(documentType2, documentUITextModelMapper.foldedDocModel(Integer.valueOf(R.string.onfido_app_title_doc_capture_license_fr))))), TuplesKt.to(documentFormat2, MapsKt.mapOf(TuplesKt.to(documentType2, documentTypeUIModels.getDrivingLicenseDocModel$onfido_capture_sdk_core_release()))));
        frenchDocFormatModels = mapMapOf3;
        Map<DocumentFormat, Map<DocumentType, DocumentTypeUIModel>> mapMapOf4 = MapsKt.mapOf(TuplesKt.to(documentFormat, MapsKt.mapOf(TuplesKt.to(documentType2, documentUITextModelMapper.foldedDocModel(Integer.valueOf(R.string.onfido_app_title_doc_capture_license_de))))), TuplesKt.to(documentFormat2, MapsKt.mapOf(TuplesKt.to(documentType2, documentTypeUIModels.getDrivingLicenseDocModel$onfido_capture_sdk_core_release()))));
        germanDocFormatModels = mapMapOf4;
        Map<DocumentType, DocumentTypeUIModel> mapMapOf5 = MapsKt.mapOf(TuplesKt.to(DocumentType.PASSPORT, documentTypeUIModels.getPassportDocModel$onfido_capture_sdk_core_release()), TuplesKt.to(documentType, documentTypeUIModels.getIdDocModel$onfido_capture_sdk_core_release()), TuplesKt.to(documentType2, documentTypeUIModels.getDrivingLicenseDocModel$onfido_capture_sdk_core_release()), TuplesKt.to(DocumentType.RESIDENCE_PERMIT, documentTypeUIModels.getResidencePermitDocModel$onfido_capture_sdk_core_release()), TuplesKt.to(DocumentType.VISA, documentTypeUIModels.getVisaDocModel$onfido_capture_sdk_core_release()), TuplesKt.to(DocumentType.WORK_PERMIT, documentTypeUIModels.getWorkPermitDocModel$onfido_capture_sdk_core_release()), TuplesKt.to(DocumentType.GENERIC, documentTypeUIModels.getGenericDocModel$onfido_capture_sdk_core_release()));
        commonDocTypeModels = mapMapOf5;
        commonDocFormatModels = MapsKt.mapOf(TuplesKt.to(documentFormat2, mapMapOf5), TuplesKt.to(documentFormat, mapMapOf5));
        docFormatModels = MapsKt.mapOf(TuplesKt.to(CountryCode.IT, mapMapOf), TuplesKt.to(CountryCode.ZA, mapMapOf2), TuplesKt.to(CountryCode.FR, mapMapOf3), TuplesKt.to(CountryCode.DE, mapMapOf4));
    }

    private DocumentUITextModelMapper() {
    }

    private final DocumentTypeUIModel foldedDocModel(Integer label) {
        return new DocumentTypeUIModel(label, R.string.onfido_doc_capture_header_folded_doc_front, R.string.onfido_doc_capture_header_folded_doc_back, R.string.onfido_doc_capture_detail_folded_doc_front, R.string.onfido_doc_capture_detail_folded_doc_back, R.string.onfido_doc_confirmation_body_folded_doc, R.string.onfido_doc_confirmation_button_primary_folded_doc, R.string.onfido_doc_confirmation_button_secondary_folded_doc, 0, 256, null);
    }

    private final DocumentTypeUIModel getDocModel(DocumentType documentType, DocumentFormat documentFormat, CountryCode countryCode) {
        DocumentTypeUIModel documentTypeUIModel;
        Map<DocumentFormat, Map<DocumentType, DocumentTypeUIModel>> map = ((CollectionsKt.contains(SetsKt.setOf((Object[]) new CountryCode[]{CountryCode.IT, CountryCode.ZA}), countryCode) && documentType == DocumentType.NATIONAL_IDENTITY_CARD) || (CollectionsKt.contains(SetsKt.setOf((Object[]) new CountryCode[]{CountryCode.FR, CountryCode.DE}), countryCode) && documentType == DocumentType.DRIVING_LICENCE)) ? docFormatModels.get(countryCode) : commonDocFormatModels;
        Map<DocumentType, DocumentTypeUIModel> map2 = map != null ? map.get(documentFormat) : null;
        if (map2 == null || (documentTypeUIModel = map2.get(documentType)) == null) {
            throw new IllegalArgumentException("No UI model found for " + documentType + " of " + countryCode);
        }
        return documentTypeUIModel;
    }

    static /* synthetic */ DocumentTypeUIModel getDocModel$default(DocumentUITextModelMapper documentUITextModelMapper, DocumentType documentType, DocumentFormat documentFormat, CountryCode countryCode, int i, Object obj) {
        if ((i & 4) != 0) {
            countryCode = null;
        }
        return documentUITextModelMapper.getDocModel(documentType, documentFormat, countryCode);
    }

    public final DocumentTypeUIModel toDocumentUIModel(DocumentType documentType, DocumentFormat documentFormat, CountryCode countryCode, boolean z, boolean z2) {
        DocumentTypeUIModel docModel;
        Intrinsics.checkNotNullParameter(documentType, "<this>");
        if (z2) {
            docModel = DocumentTypeUIModels.INSTANCE.getProofOfAddressDocModel$onfido_capture_sdk_core_release();
        } else {
            docModel = getDocModel(documentType, documentFormat == null ? DocumentFormat.CARD : documentFormat, countryCode);
        }
        DocumentTypeUIModel documentTypeUIModel = docModel;
        if (z) {
            return documentTypeUIModel.copy((505 & 1) != 0 ? documentTypeUIModel.uppercaseLabel : null, (505 & 2) != 0 ? documentTypeUIModel.captureFrontPrimaryLabel : documentType == DocumentType.PASSPORT ? R.string.onfido_doc_capture_header_live_guidance_intro_pp_photo : R.string.onfido_doc_capture_header_live_guidance_intro_doc_front, (505 & 4) != 0 ? documentTypeUIModel.captureBackPrimaryLabel : R.string.onfido_doc_capture_header_live_guidance_intro_doc_back, (505 & 8) != 0 ? documentTypeUIModel.captureFrontSecondaryLabel : 0, (505 & 16) != 0 ? documentTypeUIModel.captureBackSecondaryLabel : 0, (505 & 32) != 0 ? documentTypeUIModel.readabilityCheckLabel : 0, (505 & 64) != 0 ? documentTypeUIModel.readabilityConfirmationLabel : 0, (505 & 128) != 0 ? documentTypeUIModel.readabilityDiscardLabel : 0, (505 & 256) != 0 ? documentTypeUIModel.icon : 0);
        }
        return documentTypeUIModel;
    }
}
