package com.onfido.android.sdk.capture.ui.camera;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/TorchMode;", "", "(Ljava/lang/String;I)V", "AUTO", "OFF", "ON", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TorchMode {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ TorchMode[] $VALUES;
    public static final TorchMode AUTO = new TorchMode("AUTO", 0);
    public static final TorchMode OFF = new TorchMode("OFF", 1);
    public static final TorchMode ON = new TorchMode("ON", 2);

    private static final /* synthetic */ TorchMode[] $values() {
        return new TorchMode[]{AUTO, OFF, ON};
    }

    static {
        TorchMode[] torchModeArr$values = $values();
        $VALUES = torchModeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(torchModeArr$values);
    }

    private TorchMode(String str, int i) {
    }

    public static EnumEntries<TorchMode> getEntries() {
        return $ENTRIES;
    }

    public static TorchMode valueOf(String str) {
        return (TorchMode) Enum.valueOf(TorchMode.class, str);
    }

    public static TorchMode[] values() {
        return (TorchMode[]) $VALUES.clone();
    }
}
