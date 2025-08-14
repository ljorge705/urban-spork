package com.onfido.android.sdk.capture.internal.performance.domain;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0014\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\fH&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceEvent;", "", "eventName", "", "time", "", "(Ljava/lang/String;J)V", "getEventName", "()Ljava/lang/String;", "getTime", "()J", "getProperties", "", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformancePropertyKey;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class PerformanceEvent {
    private final String eventName;
    private final long time;

    public PerformanceEvent(String eventName, long j) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        this.eventName = eventName;
        this.time = j;
    }

    public final String getEventName() {
        return this.eventName;
    }

    public abstract Map<PerformancePropertyKey, Object> getProperties();

    public final long getTime() {
        return this.time;
    }
}
