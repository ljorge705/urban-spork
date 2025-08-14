package com.onfido.android.sdk.capture.ui.documentselection;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentDisplayItemFactoryImpl;", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentDisplayItemFactory;", "()V", "getFor", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentDisplayItem;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "documentItem", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentItem;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentDisplayItemFactoryImpl implements DocumentDisplayItemFactory {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DocumentType.values().length];
            try {
                iArr[DocumentType.PASSPORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DocumentType.NATIONAL_IDENTITY_CARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DocumentType.DRIVING_LICENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DocumentType.RESIDENCE_PERMIT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[DocumentType.VISA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[DocumentType.WORK_PERMIT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private final DocumentDisplayItem getFor(DocumentType documentType) {
        switch (WhenMappings.$EnumSwitchMapping$0[documentType.ordinal()]) {
            case 1:
                return new DocumentDisplayItem(documentType, R.drawable.onfido_restricted_doc_ic_passport, R.string.onfido_doc_select_button_passport, R.string.onfido_doc_select_subtitle_photo_page);
            case 2:
                return new DocumentDisplayItem(documentType, R.drawable.onfido_restricted_doc_ic_national_identity_card, R.string.onfido_doc_select_button_id, R.string.onfido_doc_select_subtitle_front_back);
            case 3:
                return new DocumentDisplayItem(documentType, R.drawable.onfido_restricted_doc_ic_driving_licence, R.string.onfido_doc_select_button_license, R.string.onfido_doc_select_subtitle_front_back);
            case 4:
                return new DocumentDisplayItem(documentType, R.drawable.onfido_restricted_doc_ic_residence_permit, R.string.onfido_doc_select_button_permit, R.string.onfido_doc_select_subtitle_front_back);
            case 5:
                return new DocumentDisplayItem(documentType, R.drawable.onfido_restricted_doc_ic_national_identity_card, R.string.onfido_doc_select_button_visa, R.string.onfido_doc_select_subtitle_front_back);
            case 6:
                return new DocumentDisplayItem(documentType, R.drawable.onfido_restricted_doc_ic_residence_permit, R.string.onfido_doc_select_button_permit_work, R.string.onfido_doc_select_subtitle_front_back);
            default:
                return new DocumentDisplayItem(documentType, R.drawable.onfido_restricted_doc_ic_residence_permit, 0, 0);
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.documentselection.DocumentDisplayItemFactory
    public DocumentDisplayItem getFor(DocumentItem documentItem) {
        Intrinsics.checkNotNullParameter(documentItem, "documentItem");
        if (!(documentItem instanceof GenericDocumentItem)) {
            return getFor(documentItem.getDocumentType());
        }
        GenericDocumentItem genericDocumentItem = (GenericDocumentItem) documentItem;
        return new GenericDocumentDisplayItem(genericDocumentItem.getTitle(), genericDocumentItem.getSubtitle(), genericDocumentItem.getPages());
    }
}
