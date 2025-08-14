package com.onfido.android.sdk.capture.ui.options;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/Orientation;", "", "(Ljava/lang/String;I)V", "isPortrait", "", "isPortrait$onfido_capture_sdk_core_release", "()Z", "PORTRAIT", "LANDSCAPE", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Orientation {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ Orientation[] $VALUES;
    public static final Orientation PORTRAIT = new Orientation("PORTRAIT", 0);
    public static final Orientation LANDSCAPE = new Orientation("LANDSCAPE", 1);

    private static final /* synthetic */ Orientation[] $values() {
        return new Orientation[]{PORTRAIT, LANDSCAPE};
    }

    static {
        Orientation[] orientationArr$values = $values();
        $VALUES = orientationArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(orientationArr$values);
    }

    private Orientation(String str, int i) {
    }

    public static EnumEntries<Orientation> getEntries() {
        return $ENTRIES;
    }

    public static Orientation valueOf(String str) {
        return (Orientation) Enum.valueOf(Orientation.class, str);
    }

    public static Orientation[] values() {
        return (Orientation[]) $VALUES.clone();
    }

    public final boolean isPortrait$onfido_capture_sdk_core_release() {
        return this == PORTRAIT;
    }
}
