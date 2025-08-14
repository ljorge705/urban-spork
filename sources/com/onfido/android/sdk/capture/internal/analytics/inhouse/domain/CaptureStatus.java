package com.onfido.android.sdk.capture.internal.analytics.inhouse.domain;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/CaptureStatus;", "", "(Ljava/lang/String;I)V", "SUCCESS", "ERROR", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CaptureStatus {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ CaptureStatus[] $VALUES;
    public static final CaptureStatus SUCCESS = new CaptureStatus("SUCCESS", 0);
    public static final CaptureStatus ERROR = new CaptureStatus("ERROR", 1);

    private static final /* synthetic */ CaptureStatus[] $values() {
        return new CaptureStatus[]{SUCCESS, ERROR};
    }

    static {
        CaptureStatus[] captureStatusArr$values = $values();
        $VALUES = captureStatusArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(captureStatusArr$values);
    }

    private CaptureStatus(String str, int i) {
    }

    public static EnumEntries<CaptureStatus> getEntries() {
        return $ENTRIES;
    }

    public static CaptureStatus valueOf(String str) {
        return (CaptureStatus) Enum.valueOf(CaptureStatus.class, str);
    }

    public static CaptureStatus[] values() {
        return (CaptureStatus[]) $VALUES.clone();
    }
}
