package com.onfido.android.sdk.capture.validation;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/MRZDataType;", "", "(Ljava/lang/String;I)V", "PASSPORT_NUMBER", "DOCUMENT_NUMBER", "PERSONAL_NUMBER", "DATE_OF_BIRTH", "EXPIRY_DATE", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MRZDataType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ MRZDataType[] $VALUES;
    public static final MRZDataType PASSPORT_NUMBER = new MRZDataType("PASSPORT_NUMBER", 0);
    public static final MRZDataType DOCUMENT_NUMBER = new MRZDataType("DOCUMENT_NUMBER", 1);
    public static final MRZDataType PERSONAL_NUMBER = new MRZDataType("PERSONAL_NUMBER", 2);
    public static final MRZDataType DATE_OF_BIRTH = new MRZDataType("DATE_OF_BIRTH", 3);
    public static final MRZDataType EXPIRY_DATE = new MRZDataType("EXPIRY_DATE", 4);

    private static final /* synthetic */ MRZDataType[] $values() {
        return new MRZDataType[]{PASSPORT_NUMBER, DOCUMENT_NUMBER, PERSONAL_NUMBER, DATE_OF_BIRTH, EXPIRY_DATE};
    }

    static {
        MRZDataType[] mRZDataTypeArr$values = $values();
        $VALUES = mRZDataTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(mRZDataTypeArr$values);
    }

    private MRZDataType(String str, int i) {
    }

    public static EnumEntries<MRZDataType> getEntries() {
        return $ENTRIES;
    }

    public static MRZDataType valueOf(String str) {
        return (MRZDataType) Enum.valueOf(MRZDataType.class, str);
    }

    public static MRZDataType[] values() {
        return (MRZDataType[]) $VALUES.clone();
    }
}
