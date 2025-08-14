package com.onfido.android.sdk.capture.document;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: GenericDocument.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/document/DocumentPages;", "", "(Ljava/lang/String;I)V", "SINGLE", "FRONT_AND_BACK", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentPages {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ DocumentPages[] $VALUES;
    public static final DocumentPages SINGLE = new DocumentPages("SINGLE", 0);
    public static final DocumentPages FRONT_AND_BACK = new DocumentPages("FRONT_AND_BACK", 1);

    private static final /* synthetic */ DocumentPages[] $values() {
        return new DocumentPages[]{SINGLE, FRONT_AND_BACK};
    }

    public static EnumEntries<DocumentPages> getEntries() {
        return $ENTRIES;
    }

    public static DocumentPages valueOf(String str) {
        return (DocumentPages) Enum.valueOf(DocumentPages.class, str);
    }

    public static DocumentPages[] values() {
        return (DocumentPages[]) $VALUES.clone();
    }

    private DocumentPages(String str, int i) {
    }

    static {
        DocumentPages[] documentPagesArr$values = $values();
        $VALUES = documentPagesArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(documentPagesArr$values);
    }
}
