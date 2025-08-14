package com.onfido.android.sdk.capture.utils.mlmodel;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlDocumentSide;", "", "(Ljava/lang/String;I)V", "FRONT", "BACK", "UNKNOWN", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoMlDocumentSide {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ OnfidoMlDocumentSide[] $VALUES;
    public static final OnfidoMlDocumentSide FRONT = new OnfidoMlDocumentSide("FRONT", 0);
    public static final OnfidoMlDocumentSide BACK = new OnfidoMlDocumentSide("BACK", 1);
    public static final OnfidoMlDocumentSide UNKNOWN = new OnfidoMlDocumentSide("UNKNOWN", 2);

    private static final /* synthetic */ OnfidoMlDocumentSide[] $values() {
        return new OnfidoMlDocumentSide[]{FRONT, BACK, UNKNOWN};
    }

    static {
        OnfidoMlDocumentSide[] onfidoMlDocumentSideArr$values = $values();
        $VALUES = onfidoMlDocumentSideArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(onfidoMlDocumentSideArr$values);
    }

    private OnfidoMlDocumentSide(String str, int i) {
    }

    public static EnumEntries<OnfidoMlDocumentSide> getEntries() {
        return $ENTRIES;
    }

    public static OnfidoMlDocumentSide valueOf(String str) {
        return (OnfidoMlDocumentSide) Enum.valueOf(OnfidoMlDocumentSide.class, str);
    }

    public static OnfidoMlDocumentSide[] values() {
        return (OnfidoMlDocumentSide[]) $VALUES.clone();
    }
}
