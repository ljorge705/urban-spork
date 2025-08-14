package com.onfido.android.sdk.capture.antifraud;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0002\u0010\u0005R\u001b\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/antifraud/SignalSet;", "", "signals", "", "Lcom/onfido/android/sdk/capture/antifraud/Signal;", "(Ljava/lang/String;I[Lcom/onfido/android/sdk/capture/antifraud/Signal;)V", "getSignals", "()[Lcom/onfido/android/sdk/capture/antifraud/Signal;", "[Lcom/onfido/android/sdk/capture/antifraud/Signal;", "LEGACY", "BUILD_FIELDS", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SignalSet {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ SignalSet[] $VALUES;
    public static final SignalSet BUILD_FIELDS;
    public static final SignalSet LEGACY;
    private final Signal[] signals;

    private static final /* synthetic */ SignalSet[] $values() {
        return new SignalSet[]{LEGACY, BUILD_FIELDS};
    }

    static {
        Signal signal = Signal.API_LEVEL;
        Signal signal2 = Signal.ANDROID_VERSION;
        Signal signal3 = Signal.BRAND;
        Signal signal4 = Signal.FINGERPRINT;
        Signal signal5 = Signal.HARDWARE;
        Signal signal6 = Signal.MANUFACTURER;
        Signal signal7 = Signal.MODEL;
        Signal signal8 = Signal.PRODUCT;
        LEGACY = new SignalSet("LEGACY", 0, signal, signal2, signal3, signal4, signal5, signal6, signal7, signal8);
        BUILD_FIELDS = new SignalSet("BUILD_FIELDS", 1, signal, signal2, Signal.BOARD, Signal.BOOTLOADER, signal3, Signal.DEVICE, Signal.DISPLAY, signal4, signal5, Signal.BUILD_ID, signal6, signal7, Signal.ODM_SKU, signal8, Signal.SERIAL, Signal.SKU, Signal.SOC_MANUFACTURER, Signal.SOC_MODEL, Signal.SUPPORTED_ABIS);
        SignalSet[] signalSetArr$values = $values();
        $VALUES = signalSetArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(signalSetArr$values);
    }

    private SignalSet(String str, int i, Signal... signalArr) {
        this.signals = signalArr;
    }

    public static EnumEntries<SignalSet> getEntries() {
        return $ENTRIES;
    }

    public static SignalSet valueOf(String str) {
        return (SignalSet) Enum.valueOf(SignalSet.class, str);
    }

    public static SignalSet[] values() {
        return (SignalSet[]) $VALUES.clone();
    }

    public final Signal[] getSignals() {
        return this.signals;
    }
}
