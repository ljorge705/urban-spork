package com.onfido.android.sdk.capture.document;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/document/DocumentMediaType;", "", "(Ljava/lang/String;I)V", "DOCUMENT_NFC_DATA", "DOCUMENT_PHOTO", "DOCUMENT_VIDEO", "DOCUMENT_LIVENESS_VIDEO", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentMediaType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ DocumentMediaType[] $VALUES;
    public static final DocumentMediaType DOCUMENT_NFC_DATA = new DocumentMediaType("DOCUMENT_NFC_DATA", 0);
    public static final DocumentMediaType DOCUMENT_PHOTO = new DocumentMediaType("DOCUMENT_PHOTO", 1);
    public static final DocumentMediaType DOCUMENT_VIDEO = new DocumentMediaType("DOCUMENT_VIDEO", 2);
    public static final DocumentMediaType DOCUMENT_LIVENESS_VIDEO = new DocumentMediaType("DOCUMENT_LIVENESS_VIDEO", 3);

    private static final /* synthetic */ DocumentMediaType[] $values() {
        return new DocumentMediaType[]{DOCUMENT_NFC_DATA, DOCUMENT_PHOTO, DOCUMENT_VIDEO, DOCUMENT_LIVENESS_VIDEO};
    }

    static {
        DocumentMediaType[] documentMediaTypeArr$values = $values();
        $VALUES = documentMediaTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(documentMediaTypeArr$values);
    }

    private DocumentMediaType(String str, int i) {
    }

    public static EnumEntries<DocumentMediaType> getEntries() {
        return $ENTRIES;
    }

    public static DocumentMediaType valueOf(String str) {
        return (DocumentMediaType) Enum.valueOf(DocumentMediaType.class, str);
    }

    public static DocumentMediaType[] values() {
        return (DocumentMediaType[]) $VALUES.clone();
    }
}
