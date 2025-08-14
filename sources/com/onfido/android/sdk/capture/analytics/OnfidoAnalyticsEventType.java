package com.onfido.android.sdk.capture.analytics;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: OnfidoAnalyticsEventType.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEventType;", "", "(Ljava/lang/String;I)V", "FLOW", "SCREEN", "VIEW", "ACTION", "ERROR", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoAnalyticsEventType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ OnfidoAnalyticsEventType[] $VALUES;
    public static final OnfidoAnalyticsEventType FLOW = new OnfidoAnalyticsEventType("FLOW", 0);
    public static final OnfidoAnalyticsEventType SCREEN = new OnfidoAnalyticsEventType("SCREEN", 1);
    public static final OnfidoAnalyticsEventType VIEW = new OnfidoAnalyticsEventType("VIEW", 2);
    public static final OnfidoAnalyticsEventType ACTION = new OnfidoAnalyticsEventType("ACTION", 3);
    public static final OnfidoAnalyticsEventType ERROR = new OnfidoAnalyticsEventType("ERROR", 4);

    private static final /* synthetic */ OnfidoAnalyticsEventType[] $values() {
        return new OnfidoAnalyticsEventType[]{FLOW, SCREEN, VIEW, ACTION, ERROR};
    }

    public static EnumEntries<OnfidoAnalyticsEventType> getEntries() {
        return $ENTRIES;
    }

    public static OnfidoAnalyticsEventType valueOf(String str) {
        return (OnfidoAnalyticsEventType) Enum.valueOf(OnfidoAnalyticsEventType.class, str);
    }

    public static OnfidoAnalyticsEventType[] values() {
        return (OnfidoAnalyticsEventType[]) $VALUES.clone();
    }

    private OnfidoAnalyticsEventType(String str, int i) {
    }

    static {
        OnfidoAnalyticsEventType[] onfidoAnalyticsEventTypeArr$values = $values();
        $VALUES = onfidoAnalyticsEventTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(onfidoAnalyticsEventTypeArr$values);
    }
}
