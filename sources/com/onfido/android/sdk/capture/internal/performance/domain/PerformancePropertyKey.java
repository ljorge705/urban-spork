package com.onfido.android.sdk.capture.internal.performance.domain;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformancePropertyKey;", "", "remoteValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getRemoteValue", "()Ljava/lang/String;", "ORIGIN_SCREEN", "DESTINATION_SCREEN", "DURATION", "DOCUMENT_TYPE", "DOCUMENT_SIDE", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PerformancePropertyKey {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ PerformancePropertyKey[] $VALUES;
    private final String remoteValue;
    public static final PerformancePropertyKey ORIGIN_SCREEN = new PerformancePropertyKey("ORIGIN_SCREEN", 0, "origin_screen");
    public static final PerformancePropertyKey DESTINATION_SCREEN = new PerformancePropertyKey("DESTINATION_SCREEN", 1, "destination_screen");
    public static final PerformancePropertyKey DURATION = new PerformancePropertyKey("DURATION", 2, "duration");
    public static final PerformancePropertyKey DOCUMENT_TYPE = new PerformancePropertyKey("DOCUMENT_TYPE", 3, AnalyticsPropertyKeys.DOCUMENT_TYPE);
    public static final PerformancePropertyKey DOCUMENT_SIDE = new PerformancePropertyKey("DOCUMENT_SIDE", 4, AnalyticsPropertyKeys.DOCUMENT_SIDE);

    private static final /* synthetic */ PerformancePropertyKey[] $values() {
        return new PerformancePropertyKey[]{ORIGIN_SCREEN, DESTINATION_SCREEN, DURATION, DOCUMENT_TYPE, DOCUMENT_SIDE};
    }

    static {
        PerformancePropertyKey[] performancePropertyKeyArr$values = $values();
        $VALUES = performancePropertyKeyArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(performancePropertyKeyArr$values);
    }

    private PerformancePropertyKey(String str, int i, String str2) {
        this.remoteValue = str2;
    }

    public static EnumEntries<PerformancePropertyKey> getEntries() {
        return $ENTRIES;
    }

    public static PerformancePropertyKey valueOf(String str) {
        return (PerformancePropertyKey) Enum.valueOf(PerformancePropertyKey.class, str);
    }

    public static PerformancePropertyKey[] values() {
        return (PerformancePropertyKey[]) $VALUES.clone();
    }

    public final String getRemoteValue() {
        return this.remoteValue;
    }
}
