package com.onfido.android.sdk.capture;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: ExitCode.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ExitCode;", "", "(Ljava/lang/String;I)V", "USER_LEFT_ACTIVITY", "USER_CONSENT_DENIED", "REQUIRED_NFC_FLOW_NOT_COMPLETED", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExitCode {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ExitCode[] $VALUES;
    public static final ExitCode USER_LEFT_ACTIVITY = new ExitCode("USER_LEFT_ACTIVITY", 0);
    public static final ExitCode USER_CONSENT_DENIED = new ExitCode("USER_CONSENT_DENIED", 1);
    public static final ExitCode REQUIRED_NFC_FLOW_NOT_COMPLETED = new ExitCode("REQUIRED_NFC_FLOW_NOT_COMPLETED", 2);

    private static final /* synthetic */ ExitCode[] $values() {
        return new ExitCode[]{USER_LEFT_ACTIVITY, USER_CONSENT_DENIED, REQUIRED_NFC_FLOW_NOT_COMPLETED};
    }

    public static EnumEntries<ExitCode> getEntries() {
        return $ENTRIES;
    }

    public static ExitCode valueOf(String str) {
        return (ExitCode) Enum.valueOf(ExitCode.class, str);
    }

    public static ExitCode[] values() {
        return (ExitCode[]) $VALUES.clone();
    }

    private ExitCode(String str, int i) {
    }

    static {
        ExitCode[] exitCodeArr$values = $values();
        $VALUES = exitCodeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(exitCodeArr$values);
    }
}
