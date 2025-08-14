package com.onfido.android.sdk.capture;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: DocumentType.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/DocumentType;", "", "id", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "PASSPORT", "DRIVING_LICENCE", "NATIONAL_IDENTITY_CARD", "RESIDENCE_PERMIT", "VISA", "WORK_PERMIT", "GENERIC", "UNKNOWN", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ DocumentType[] $VALUES;
    private final String id;
    public static final DocumentType PASSPORT = new DocumentType("PASSPORT", 0, "passport");
    public static final DocumentType DRIVING_LICENCE = new DocumentType("DRIVING_LICENCE", 1, "driving_licence");
    public static final DocumentType NATIONAL_IDENTITY_CARD = new DocumentType("NATIONAL_IDENTITY_CARD", 2, "national_id");
    public static final DocumentType RESIDENCE_PERMIT = new DocumentType("RESIDENCE_PERMIT", 3, "residence_permit");
    public static final DocumentType VISA = new DocumentType("VISA", 4, "visa");
    public static final DocumentType WORK_PERMIT = new DocumentType("WORK_PERMIT", 5, "work_permit");
    public static final DocumentType GENERIC = new DocumentType("GENERIC", 6, "generic");
    public static final DocumentType UNKNOWN = new DocumentType("UNKNOWN", 7, "unknown");

    private static final /* synthetic */ DocumentType[] $values() {
        return new DocumentType[]{PASSPORT, DRIVING_LICENCE, NATIONAL_IDENTITY_CARD, RESIDENCE_PERMIT, VISA, WORK_PERMIT, GENERIC, UNKNOWN};
    }

    public static EnumEntries<DocumentType> getEntries() {
        return $ENTRIES;
    }

    public static DocumentType valueOf(String str) {
        return (DocumentType) Enum.valueOf(DocumentType.class, str);
    }

    public static DocumentType[] values() {
        return (DocumentType[]) $VALUES.clone();
    }

    public final String getId() {
        return this.id;
    }

    private DocumentType(String str, int i, String str2) {
        this.id = str2;
    }

    static {
        DocumentType[] documentTypeArr$values = $values();
        $VALUES = documentTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(documentTypeArr$values);
    }
}
