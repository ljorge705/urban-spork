package com.onfido.android.sdk.capture.internal.nfc;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/NfcNonCapabilityReason;", "", "(Ljava/lang/String;I)V", "DEVICE_WITHOUT_NFC_HARDWARE", "DEVICE_NFC_SETTINGS_DISABLED", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcNonCapabilityReason {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ NfcNonCapabilityReason[] $VALUES;
    public static final NfcNonCapabilityReason DEVICE_WITHOUT_NFC_HARDWARE = new NfcNonCapabilityReason("DEVICE_WITHOUT_NFC_HARDWARE", 0);
    public static final NfcNonCapabilityReason DEVICE_NFC_SETTINGS_DISABLED = new NfcNonCapabilityReason("DEVICE_NFC_SETTINGS_DISABLED", 1);

    private static final /* synthetic */ NfcNonCapabilityReason[] $values() {
        return new NfcNonCapabilityReason[]{DEVICE_WITHOUT_NFC_HARDWARE, DEVICE_NFC_SETTINGS_DISABLED};
    }

    static {
        NfcNonCapabilityReason[] nfcNonCapabilityReasonArr$values = $values();
        $VALUES = nfcNonCapabilityReasonArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(nfcNonCapabilityReasonArr$values);
    }

    private NfcNonCapabilityReason(String str, int i) {
    }

    public static EnumEntries<NfcNonCapabilityReason> getEntries() {
        return $ENTRIES;
    }

    public static NfcNonCapabilityReason valueOf(String str) {
        return (NfcNonCapabilityReason) Enum.valueOf(NfcNonCapabilityReason.class, str);
    }

    public static NfcNonCapabilityReason[] values() {
        return (NfcNonCapabilityReason[]) $VALUES.clone();
    }
}
