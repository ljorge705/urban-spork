package com.onfido.android.sdk.capture.internal.analytics.inhouse.domain;

import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventResultReceiver;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0017\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005¢\u0006\u0002\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001f\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "", "event", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/Event;", OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES, "", "", "extraPublicProperties", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/PublicPropertyKey;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/Event;Ljava/util/Map;Ljava/util/Map;)V", "getEvent", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/Event;", "getExtraPublicProperties", "()Ljava/util/Map;", "getProperties", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class AnalyticsEvent {
    private final Event event;
    private final Map<PublicPropertyKey, Object> extraPublicProperties;
    private final Map<String, Object> properties;

    public AnalyticsEvent(Event event, Map<String, ? extends Object> properties, Map<PublicPropertyKey, ? extends Object> extraPublicProperties) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(properties, "properties");
        Intrinsics.checkNotNullParameter(extraPublicProperties, "extraPublicProperties");
        this.event = event;
        this.properties = properties;
        this.extraPublicProperties = extraPublicProperties;
    }

    public final Event getEvent() {
        return this.event;
    }

    public final Map<PublicPropertyKey, Object> getExtraPublicProperties() {
        return this.extraPublicProperties;
    }

    public final Map<String, Object> getProperties() {
        return this.properties;
    }

    public /* synthetic */ AnalyticsEvent(Event event, Map map, Map map2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(event, (i & 2) != 0 ? MapsKt.emptyMap() : map, (i & 4) != 0 ? MapsKt.emptyMap() : map2);
    }
}
