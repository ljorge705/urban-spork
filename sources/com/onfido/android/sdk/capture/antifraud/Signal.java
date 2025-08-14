package com.onfido.android.sdk.capture.antifraud;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0016\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/antifraud/Signal;", "", "(Ljava/lang/String;I)V", "API_LEVEL", "ANDROID_VERSION", "BOARD", "BOOTLOADER", "BRAND", "DEVICE", "DISPLAY", "FINGERPRINT", "HARDWARE", "BUILD_ID", "MANUFACTURER", "MODEL", "ODM_SKU", "PRODUCT", "SERIAL", "SKU", "SOC_MANUFACTURER", "SOC_MODEL", "SUPPORTED_ABIS", "LOCAL_UUID", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Signal {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ Signal[] $VALUES;
    public static final Signal API_LEVEL = new Signal("API_LEVEL", 0);
    public static final Signal ANDROID_VERSION = new Signal("ANDROID_VERSION", 1);
    public static final Signal BOARD = new Signal("BOARD", 2);
    public static final Signal BOOTLOADER = new Signal("BOOTLOADER", 3);
    public static final Signal BRAND = new Signal("BRAND", 4);
    public static final Signal DEVICE = new Signal("DEVICE", 5);
    public static final Signal DISPLAY = new Signal("DISPLAY", 6);
    public static final Signal FINGERPRINT = new Signal("FINGERPRINT", 7);
    public static final Signal HARDWARE = new Signal("HARDWARE", 8);
    public static final Signal BUILD_ID = new Signal("BUILD_ID", 9);
    public static final Signal MANUFACTURER = new Signal("MANUFACTURER", 10);
    public static final Signal MODEL = new Signal("MODEL", 11);
    public static final Signal ODM_SKU = new Signal("ODM_SKU", 12);
    public static final Signal PRODUCT = new Signal("PRODUCT", 13);
    public static final Signal SERIAL = new Signal("SERIAL", 14);
    public static final Signal SKU = new Signal("SKU", 15);
    public static final Signal SOC_MANUFACTURER = new Signal("SOC_MANUFACTURER", 16);
    public static final Signal SOC_MODEL = new Signal("SOC_MODEL", 17);
    public static final Signal SUPPORTED_ABIS = new Signal("SUPPORTED_ABIS", 18);
    public static final Signal LOCAL_UUID = new Signal("LOCAL_UUID", 19);

    private static final /* synthetic */ Signal[] $values() {
        return new Signal[]{API_LEVEL, ANDROID_VERSION, BOARD, BOOTLOADER, BRAND, DEVICE, DISPLAY, FINGERPRINT, HARDWARE, BUILD_ID, MANUFACTURER, MODEL, ODM_SKU, PRODUCT, SERIAL, SKU, SOC_MANUFACTURER, SOC_MODEL, SUPPORTED_ABIS, LOCAL_UUID};
    }

    static {
        Signal[] signalArr$values = $values();
        $VALUES = signalArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(signalArr$values);
    }

    private Signal(String str, int i) {
    }

    public static EnumEntries<Signal> getEntries() {
        return $ENTRIES;
    }

    public static Signal valueOf(String str) {
        return (Signal) Enum.valueOf(Signal.class, str);
    }

    public static Signal[] values() {
        return (Signal[]) $VALUES.clone();
    }
}
