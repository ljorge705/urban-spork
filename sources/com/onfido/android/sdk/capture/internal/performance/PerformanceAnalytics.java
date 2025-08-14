package com.onfido.android.sdk.capture.internal.performance;

import com.onfido.android.sdk.capture.internal.performance.domain.PerformanceEvent;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0006H&¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/performance/PerformanceAnalytics;", "", "clearEvents", "", "trackEnd", "endEvent", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceEvent;", "trackStart", "startEvent", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface PerformanceAnalytics {
    void clearEvents();

    void trackEnd(PerformanceEvent endEvent);

    void trackStart(PerformanceEvent startEvent);
}
