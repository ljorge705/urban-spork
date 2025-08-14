package com.onfido.android.sdk.capture.internal.performance;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.Event;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventType;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformanceEvent;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformancePropertyKey;
import com.onfido.android.sdk.capture.internal.performance.repository.PerformanceAnalyticsNetwork;
import com.onfido.javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\nH\u0016J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\nH\u0016R(\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/performance/PerformanceAnalyticsImpl;", "Lcom/onfido/android/sdk/capture/internal/performance/PerformanceAnalytics;", "performanceAnalyticsNetwork", "Lcom/onfido/android/sdk/capture/internal/performance/repository/PerformanceAnalyticsNetwork;", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Lcom/onfido/android/sdk/capture/internal/performance/repository/PerformanceAnalyticsNetwork;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "eventsMap", "", "", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceEvent;", "getEventsMap$onfido_capture_sdk_core_release$annotations", "()V", "getEventsMap$onfido_capture_sdk_core_release", "()Ljava/util/Map;", "clearEvents", "", "trackEnd", "endEvent", "trackStart", "startEvent", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PerformanceAnalyticsImpl implements PerformanceAnalytics {
    private final Map<String, PerformanceEvent> eventsMap;
    private final PerformanceAnalyticsNetwork performanceAnalyticsNetwork;
    private final OnfidoRemoteConfig remoteConfig;

    @Inject
    public PerformanceAnalyticsImpl(PerformanceAnalyticsNetwork performanceAnalyticsNetwork, OnfidoRemoteConfig remoteConfig) {
        Intrinsics.checkNotNullParameter(performanceAnalyticsNetwork, "performanceAnalyticsNetwork");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        this.performanceAnalyticsNetwork = performanceAnalyticsNetwork;
        this.remoteConfig = remoteConfig;
        this.eventsMap = new LinkedHashMap();
    }

    public static /* synthetic */ void getEventsMap$onfido_capture_sdk_core_release$annotations() {
    }

    @Override // com.onfido.android.sdk.capture.internal.performance.PerformanceAnalytics
    public void clearEvents() {
        if (this.remoteConfig.isPerformanceAnalyticsEnabled()) {
            this.eventsMap.clear();
        }
    }

    public final Map<String, PerformanceEvent> getEventsMap$onfido_capture_sdk_core_release() {
        return this.eventsMap;
    }

    @Override // com.onfido.android.sdk.capture.internal.performance.PerformanceAnalytics
    public void trackEnd(PerformanceEvent endEvent) {
        PerformanceEvent performanceEvent;
        Intrinsics.checkNotNullParameter(endEvent, "endEvent");
        if (this.remoteConfig.isPerformanceAnalyticsEnabled() && (performanceEvent = this.eventsMap.get(endEvent.getEventName())) != null) {
            this.eventsMap.remove(endEvent.getEventName());
            String eventName = endEvent.getEventName();
            Map mapPlus = MapsKt.plus(MapsKt.plus(endEvent.getProperties(), performanceEvent.getProperties()), MapsKt.mapOf(TuplesKt.to(PerformancePropertyKey.DURATION, Long.valueOf(endEvent.getTime() - performanceEvent.getTime()))));
            Event event = new Event(eventName, EventType.PERFORMANCE, null, null, 12, null);
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(mapPlus.size()));
            for (Map.Entry entry : mapPlus.entrySet()) {
                linkedHashMap.put(((PerformancePropertyKey) entry.getKey()).getRemoteValue(), entry.getValue());
            }
            this.performanceAnalyticsNetwork.track(new AnalyticsEvent(event, linkedHashMap, null, 4, null));
        }
    }

    @Override // com.onfido.android.sdk.capture.internal.performance.PerformanceAnalytics
    public void trackStart(PerformanceEvent startEvent) {
        Intrinsics.checkNotNullParameter(startEvent, "startEvent");
        if (this.remoteConfig.isPerformanceAnalyticsEnabled()) {
            this.eventsMap.put(startEvent.getEventName(), startEvent);
        }
    }
}
