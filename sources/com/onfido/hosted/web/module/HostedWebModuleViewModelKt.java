package com.onfido.hosted.web.module;

import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventResultReceiver;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventType;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003H\u0000¨\u0006\u0006"}, d2 = {"getEventTypeFromProperties", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventType;", OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES, "", "", "", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class HostedWebModuleViewModelKt {
    public static final EventType getEventTypeFromProperties(Map<String, ? extends Object> map) {
        EventType eventType = null;
        Object obj = map != null ? map.get("event_type") : null;
        Iterator<EventType> it = EventType.getEntries().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            EventType next = it.next();
            String lowerCase = next.name().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (Intrinsics.areEqual(lowerCase, obj)) {
                eventType = next;
                break;
            }
        }
        EventType eventType2 = eventType;
        return eventType2 == null ? EventType.ACTION : eventType2;
    }
}
