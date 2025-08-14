package com.onfido.android.sdk.capture.internal.analytics.inhouse.domain;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/WarningOrigin;", "", "(Ljava/lang/String;I)V", "DEVICE", "REMOTE", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WarningOrigin {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ WarningOrigin[] $VALUES;
    public static final WarningOrigin DEVICE = new WarningOrigin("DEVICE", 0);
    public static final WarningOrigin REMOTE = new WarningOrigin("REMOTE", 1);

    private static final /* synthetic */ WarningOrigin[] $values() {
        return new WarningOrigin[]{DEVICE, REMOTE};
    }

    static {
        WarningOrigin[] warningOriginArr$values = $values();
        $VALUES = warningOriginArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(warningOriginArr$values);
    }

    private WarningOrigin(String str, int i) {
    }

    public static EnumEntries<WarningOrigin> getEntries() {
        return $ENTRIES;
    }

    public static WarningOrigin valueOf(String str) {
        return (WarningOrigin) Enum.valueOf(WarningOrigin.class, str);
    }

    public static WarningOrigin[] values() {
        return (WarningOrigin[]) $VALUES.clone();
    }
}
