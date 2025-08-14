package com.onfido.android.sdk.capture.analytics;

import com.clevertap.android.sdk.Constants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OnfidoAnalyticsEvent.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0017\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005HÆ\u0003J+\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0007HÖ\u0001R\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEvent;", "", "type", "Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEventType;", OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES, "", "Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsPropertyKey;", "", "(Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEventType;Ljava/util/Map;)V", "getProperties", "()Ljava/util/Map;", "getType", "()Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEventType;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class OnfidoAnalyticsEvent {
    private final Map<OnfidoAnalyticsPropertyKey, String> properties;
    private final OnfidoAnalyticsEventType type;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ OnfidoAnalyticsEvent copy$default(OnfidoAnalyticsEvent onfidoAnalyticsEvent, OnfidoAnalyticsEventType onfidoAnalyticsEventType, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            onfidoAnalyticsEventType = onfidoAnalyticsEvent.type;
        }
        if ((i & 2) != 0) {
            map = onfidoAnalyticsEvent.properties;
        }
        return onfidoAnalyticsEvent.copy(onfidoAnalyticsEventType, map);
    }

    /* renamed from: component1, reason: from getter */
    public final OnfidoAnalyticsEventType getType() {
        return this.type;
    }

    public final Map<OnfidoAnalyticsPropertyKey, String> component2() {
        return this.properties;
    }

    public final OnfidoAnalyticsEvent copy(OnfidoAnalyticsEventType type, Map<OnfidoAnalyticsPropertyKey, String> properties) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(properties, "properties");
        return new OnfidoAnalyticsEvent(type, properties);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OnfidoAnalyticsEvent)) {
            return false;
        }
        OnfidoAnalyticsEvent onfidoAnalyticsEvent = (OnfidoAnalyticsEvent) other;
        return this.type == onfidoAnalyticsEvent.type && Intrinsics.areEqual(this.properties, onfidoAnalyticsEvent.properties);
    }

    public final Map<OnfidoAnalyticsPropertyKey, String> getProperties() {
        return this.properties;
    }

    public final OnfidoAnalyticsEventType getType() {
        return this.type;
    }

    public int hashCode() {
        return (this.type.hashCode() * 31) + this.properties.hashCode();
    }

    public String toString() {
        return "OnfidoAnalyticsEvent(type=" + this.type + ", properties=" + this.properties + ")";
    }

    public OnfidoAnalyticsEvent(OnfidoAnalyticsEventType type, Map<OnfidoAnalyticsPropertyKey, String> properties) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(properties, "properties");
        this.type = type;
        this.properties = properties;
    }

    public /* synthetic */ OnfidoAnalyticsEvent(OnfidoAnalyticsEventType onfidoAnalyticsEventType, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(onfidoAnalyticsEventType, (i & 2) != 0 ? MapsKt.emptyMap() : map);
    }
}
