package com.onfido.android.sdk.capture;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: OnfidoTheme.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/OnfidoTheme;", "", "(Ljava/lang/String;I)V", "AUTOMATIC", "LIGHT", "DARK", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoTheme {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ OnfidoTheme[] $VALUES;
    public static final OnfidoTheme AUTOMATIC = new OnfidoTheme("AUTOMATIC", 0);
    public static final OnfidoTheme LIGHT = new OnfidoTheme("LIGHT", 1);
    public static final OnfidoTheme DARK = new OnfidoTheme("DARK", 2);

    private static final /* synthetic */ OnfidoTheme[] $values() {
        return new OnfidoTheme[]{AUTOMATIC, LIGHT, DARK};
    }

    public static EnumEntries<OnfidoTheme> getEntries() {
        return $ENTRIES;
    }

    public static OnfidoTheme valueOf(String str) {
        return (OnfidoTheme) Enum.valueOf(OnfidoTheme.class, str);
    }

    public static OnfidoTheme[] values() {
        return (OnfidoTheme[]) $VALUES.clone();
    }

    private OnfidoTheme(String str, int i) {
    }

    static {
        OnfidoTheme[] onfidoThemeArr$values = $values();
        $VALUES = onfidoThemeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(onfidoThemeArr$values);
    }
}
