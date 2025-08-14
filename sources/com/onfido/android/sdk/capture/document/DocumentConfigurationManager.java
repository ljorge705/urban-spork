package com.onfido.android.sdk.capture.document;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentFormat;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.DocSide;
import com.onfido.javax.inject.Inject;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0017\u0018\u0000  2\u00020\u0001:\u0001 B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J8\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\bH\u0017J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0012\u0010\u0015\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006H\u0013J\u0018\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0005H\u0012J\u0018\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0012J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u0005H\u0016J,\u0010\u0019\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0005H\u0012J$\u0010\u001a\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0017\u001a\u00020\u0006H\u0012J\u001a\u0010\u001b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u000bH\u0012J\u001a\u0010\u001d\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u001e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0010\u0010\u001f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/onfido/android/sdk/capture/document/DocumentConfigurationManager;", "", "()V", "scanEnabledDocuments", "", "Lcom/onfido/android/sdk/capture/DocumentType;", "Lcom/onfido/api/client/data/DocSide;", "backSideCaptureNeeded", "", "documentType", "genericDocPages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "captureFrameContentDescription", "", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "docSide", "docFormat", "Lcom/onfido/android/sdk/capture/DocumentFormat;", "isAccessibilityEnabled", "countrySelectionNeeded", "getAccessibilityContentDescription", "getContentDescription", "nonNullDocSide", "getDocSideForNfcProperties", "getDrivingLicenseContentDescription", "getNationalIdContentDescription", "isGenericDocumentWithSingleSide", "pages", "shouldAutocapture", "shouldScanNfc", "shouldShowInitialOverlay", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class DocumentConfigurationManager {
    private static final Map<CountryCode, Map<DocSide, Integer>> COUNTRY_CODE_SPECIALIZED_ACCESSIBILITY_MAP;
    private static final Companion Companion = new Companion(null);
    private static final Map<DocumentType, Map<DocSide, Integer>> DOCUMENT_TYPES_ACCESSIBILITY_MAP;
    private final Map<DocumentType, DocSide> scanEnabledDocuments;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R&\u0010\u0003\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\t\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/document/DocumentConfigurationManager$Companion;", "", "()V", "COUNTRY_CODE_SPECIALIZED_ACCESSIBILITY_MAP", "", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "Lcom/onfido/api/client/data/DocSide;", "", "DOCUMENT_TYPES_ACCESSIBILITY_MAP", "Lcom/onfido/android/sdk/capture/DocumentType;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[DocumentType.values().length];
            try {
                iArr[DocumentType.PASSPORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DocumentType.DRIVING_LICENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DocumentType.NATIONAL_IDENTITY_CARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DocumentType.RESIDENCE_PERMIT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[DocSide.values().length];
            try {
                iArr2[DocSide.BACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[DocSide.FRONT.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static {
        DocumentType documentType = DocumentType.DRIVING_LICENCE;
        DocSide docSide = DocSide.FRONT;
        Pair pair = TuplesKt.to(docSide, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_dl_front_manual));
        DocSide docSide2 = DocSide.BACK;
        DOCUMENT_TYPES_ACCESSIBILITY_MAP = MapsKt.mapOf(TuplesKt.to(documentType, MapsKt.mapOf(pair, TuplesKt.to(docSide2, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_dl_back_manual)))), TuplesKt.to(DocumentType.RESIDENCE_PERMIT, MapsKt.mapOf(TuplesKt.to(docSide, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_rp_front_manual)), TuplesKt.to(docSide2, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_rp_back_manual)))), TuplesKt.to(DocumentType.NATIONAL_IDENTITY_CARD, MapsKt.mapOf(TuplesKt.to(docSide, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_ic_front_manual)), TuplesKt.to(docSide2, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_ic_back_manual)))));
        COUNTRY_CODE_SPECIALIZED_ACCESSIBILITY_MAP = MapsKt.mapOf(TuplesKt.to(CountryCode.FR, MapsKt.mapOf(TuplesKt.to(docSide, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_dl_fr_front_manual)), TuplesKt.to(docSide2, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_dl_fr_back_manual)))), TuplesKt.to(CountryCode.DE, MapsKt.mapOf(TuplesKt.to(docSide, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_dl_de_front_manual)), TuplesKt.to(docSide2, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_dl_de_back_manual)))), TuplesKt.to(CountryCode.IT, MapsKt.mapOf(TuplesKt.to(docSide, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_ic_it_front_manual)), TuplesKt.to(docSide2, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_ic_it_back_manual)))), TuplesKt.to(CountryCode.ZA, MapsKt.mapOf(TuplesKt.to(docSide, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_ic_za_front_manual)), TuplesKt.to(docSide2, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_ic_za_back_manual)))), TuplesKt.to(CountryCode.US, MapsKt.mapOf(TuplesKt.to(docSide, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_dl_front_auto)), TuplesKt.to(docSide2, Integer.valueOf(R.string.onfido_doc_capture_frame_accessibility_dl_back_auto)))));
    }

    @Inject
    public DocumentConfigurationManager() {
        Pair pair = TuplesKt.to(DocumentType.PASSPORT, DocSide.FRONT);
        DocumentType documentType = DocumentType.NATIONAL_IDENTITY_CARD;
        DocSide docSide = DocSide.BACK;
        this.scanEnabledDocuments = MapsKt.mapOf(pair, TuplesKt.to(documentType, docSide), TuplesKt.to(DocumentType.RESIDENCE_PERMIT, docSide));
    }

    public static /* synthetic */ int captureFrameContentDescription$default(DocumentConfigurationManager documentConfigurationManager, DocumentType documentType, CountryCode countryCode, DocSide docSide, DocumentFormat documentFormat, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: captureFrameContentDescription");
        }
        if ((i & 16) != 0) {
            z = false;
        }
        return documentConfigurationManager.captureFrameContentDescription(documentType, countryCode, docSide, documentFormat, z);
    }

    private int getAccessibilityContentDescription(DocSide docSide) {
        int i = docSide == null ? -1 : WhenMappings.$EnumSwitchMapping$1[docSide.ordinal()];
        return i != 1 ? i != 2 ? R.string.onfido_doc_capture_header_live_guidance_intro_pp_photo_accessibility : R.string.onfido_doc_capture_header_live_guidance_intro_doc_front_accessibility : R.string.onfido_doc_capture_header_live_guidance_intro_doc_back_accessibility;
    }

    private int getContentDescription(DocSide nonNullDocSide, DocumentType documentType) {
        return ((Number) MapsKt.getValue((Map) MapsKt.getValue(DOCUMENT_TYPES_ACCESSIBILITY_MAP, documentType), nonNullDocSide)).intValue();
    }

    private int getDrivingLicenseContentDescription(CountryCode countryCode, DocumentFormat docFormat, DocSide nonNullDocSide, DocumentType documentType) {
        CountryCode countryCode2 = CountryCode.FR;
        return ((countryCode == countryCode2 && docFormat == DocumentFormat.FOLDED) || (countryCode == (countryCode2 = CountryCode.DE) && docFormat == DocumentFormat.FOLDED)) ? getContentDescription(nonNullDocSide, countryCode2) : shouldAutocapture(documentType, countryCode) ? getContentDescription(nonNullDocSide, CountryCode.US) : getContentDescription(nonNullDocSide, DocumentType.DRIVING_LICENCE);
    }

    private int getNationalIdContentDescription(CountryCode countryCode, DocumentFormat docFormat, DocSide nonNullDocSide) {
        CountryCode countryCode2 = CountryCode.IT;
        return ((countryCode == countryCode2 && docFormat == DocumentFormat.FOLDED) || (countryCode == (countryCode2 = CountryCode.ZA) && docFormat == DocumentFormat.FOLDED)) ? getContentDescription(nonNullDocSide, countryCode2) : getContentDescription(nonNullDocSide, DocumentType.NATIONAL_IDENTITY_CARD);
    }

    private boolean isGenericDocumentWithSingleSide(DocumentType documentType, DocumentPages pages) {
        return documentType == DocumentType.GENERIC && pages == DocumentPages.SINGLE;
    }

    public boolean backSideCaptureNeeded(DocumentType documentType, DocumentPages genericDocPages) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        return (SetsKt.setOf((Object[]) new DocumentType[]{DocumentType.PASSPORT, DocumentType.VISA}).contains(documentType) || isGenericDocumentWithSingleSide(documentType, genericDocPages)) ? false : true;
    }

    public int captureFrameContentDescription(DocumentType documentType, CountryCode countryCode, DocSide docSide, DocumentFormat docFormat, boolean isAccessibilityEnabled) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        if (isAccessibilityEnabled) {
            return getAccessibilityContentDescription(docSide);
        }
        if (docSide == null) {
            docSide = DocSide.FRONT;
        }
        int i = R.string.onfido_doc_capture_frame_accessibility;
        int i2 = WhenMappings.$EnumSwitchMapping$0[documentType.ordinal()];
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i : getContentDescription(docSide, DocumentType.RESIDENCE_PERMIT) : getNationalIdContentDescription(countryCode, docFormat, docSide) : getDrivingLicenseContentDescription(countryCode, docFormat, docSide, documentType) : R.string.onfido_doc_capture_frame_accessibility_pp_auto;
    }

    public boolean countrySelectionNeeded(DocumentType documentType) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        return documentType != DocumentType.PASSPORT;
    }

    public DocSide getDocSideForNfcProperties(DocumentType documentType) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        if (this.scanEnabledDocuments.containsKey(documentType)) {
            return this.scanEnabledDocuments.get(documentType);
        }
        return null;
    }

    public boolean shouldAutocapture(DocumentType documentType, CountryCode countryCode) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        int i = WhenMappings.$EnumSwitchMapping$0[documentType.ordinal()];
        if (i == 1) {
            return true;
        }
        if (i != 2) {
            if (i == 3 && countryCode == CountryCode.NL) {
                return true;
            }
        } else if (countryCode == CountryCode.US) {
            return true;
        }
        return false;
    }

    public boolean shouldScanNfc(DocumentType documentType) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        return this.scanEnabledDocuments.containsKey(documentType);
    }

    public boolean shouldShowInitialOverlay(DocumentType documentType) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        return documentType == DocumentType.PASSPORT;
    }

    private int getContentDescription(DocSide nonNullDocSide, CountryCode countryCode) {
        return ((Number) MapsKt.getValue((Map) MapsKt.getValue(COUNTRY_CODE_SPECIALIZED_ACCESSIBILITY_MAP, countryCode), nonNullDocSide)).intValue();
    }
}
