package com.onfido.api.client.data;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: DocSide.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/onfido/api/client/data/DocSide;", "", "id", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "FRONT", "BACK", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class DocSide {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ DocSide[] $VALUES;
    private final String id;
    public static final DocSide FRONT = new DocSide("FRONT", 0, "front");
    public static final DocSide BACK = new DocSide("BACK", 1, "back");

    private static final /* synthetic */ DocSide[] $values() {
        return new DocSide[]{FRONT, BACK};
    }

    public static EnumEntries<DocSide> getEntries() {
        return $ENTRIES;
    }

    public static DocSide valueOf(String str) {
        return (DocSide) Enum.valueOf(DocSide.class, str);
    }

    public static DocSide[] values() {
        return (DocSide[]) $VALUES.clone();
    }

    public final String getId() {
        return this.id;
    }

    private DocSide(String str, int i, String str2) {
        this.id = str2;
    }

    static {
        DocSide[] docSideArr$values = $values();
        $VALUES = docSideArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(docSideArr$values);
    }
}
