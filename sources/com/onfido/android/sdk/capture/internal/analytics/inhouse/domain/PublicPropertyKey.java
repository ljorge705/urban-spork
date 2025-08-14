package com.onfido.android.sdk.capture.internal.analytics.inhouse.domain;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/PublicPropertyKey;", "", "(Ljava/lang/String;I)V", "DOCUMENT_TYPE", "IS_PORTRAIT", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PublicPropertyKey {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ PublicPropertyKey[] $VALUES;
    public static final PublicPropertyKey DOCUMENT_TYPE = new PublicPropertyKey("DOCUMENT_TYPE", 0);
    public static final PublicPropertyKey IS_PORTRAIT = new PublicPropertyKey("IS_PORTRAIT", 1);

    private static final /* synthetic */ PublicPropertyKey[] $values() {
        return new PublicPropertyKey[]{DOCUMENT_TYPE, IS_PORTRAIT};
    }

    static {
        PublicPropertyKey[] publicPropertyKeyArr$values = $values();
        $VALUES = publicPropertyKeyArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(publicPropertyKeyArr$values);
    }

    private PublicPropertyKey(String str, int i) {
    }

    public static EnumEntries<PublicPropertyKey> getEntries() {
        return $ENTRIES;
    }

    public static PublicPropertyKey valueOf(String str) {
        return (PublicPropertyKey) Enum.valueOf(PublicPropertyKey.class, str);
    }

    public static PublicPropertyKey[] values() {
        return (PublicPropertyKey[]) $VALUES.clone();
    }
}
