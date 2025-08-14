package com.onfido.android.sdk.capture.internal.analytics.inhouse.domain;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventType;", "", "(Ljava/lang/String;I)V", "SCREEN", "FLOW", "ACTION", "VIEW", "PERFORMANCE", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EventType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ EventType[] $VALUES;
    public static final EventType SCREEN = new EventType("SCREEN", 0);
    public static final EventType FLOW = new EventType("FLOW", 1);
    public static final EventType ACTION = new EventType("ACTION", 2);
    public static final EventType VIEW = new EventType("VIEW", 3);
    public static final EventType PERFORMANCE = new EventType("PERFORMANCE", 4);

    private static final /* synthetic */ EventType[] $values() {
        return new EventType[]{SCREEN, FLOW, ACTION, VIEW, PERFORMANCE};
    }

    static {
        EventType[] eventTypeArr$values = $values();
        $VALUES = eventTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(eventTypeArr$values);
    }

    private EventType(String str, int i) {
    }

    public static EnumEntries<EventType> getEntries() {
        return $ENTRIES;
    }

    public static EventType valueOf(String str) {
        return (EventType) Enum.valueOf(EventType.class, str);
    }

    public static EventType[] values() {
        return (EventType[]) $VALUES.clone();
    }
}
