package com.onfido.android.sdk.capture.internal.performance.trackers;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.performance.AggregatedPerformanceAnalytics;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformanceAnalyticsScreen;
import com.onfido.android.sdk.capture.internal.performance.trackers.PerformanceEvents;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.api.client.data.DocSide;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0011\u0018\u00002\u00020\u0001B\u0017\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0010¢\u0006\u0002\b\u000bJ5\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0010¢\u0006\u0002\b\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/performance/trackers/ScreenLoadTracker;", "", "performanceAnalytics", "Lcom/onfido/android/sdk/capture/internal/performance/AggregatedPerformanceAnalytics;", "timeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "(Lcom/onfido/android/sdk/capture/internal/performance/AggregatedPerformanceAnalytics;Lcom/onfido/android/sdk/capture/utils/TimeProvider;)V", "trackNavigationCompleted", "", FirebaseAnalytics.Param.DESTINATION, "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceAnalyticsScreen;", "trackNavigationCompleted$onfido_capture_sdk_core_release", "trackNavigationStarted", "origin", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "trackNavigationStarted$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Singleton
/* loaded from: classes2.dex */
public class ScreenLoadTracker {
    private final AggregatedPerformanceAnalytics performanceAnalytics;
    private final TimeProvider timeProvider;

    @Inject
    public ScreenLoadTracker(AggregatedPerformanceAnalytics performanceAnalytics, TimeProvider timeProvider) {
        Intrinsics.checkNotNullParameter(performanceAnalytics, "performanceAnalytics");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        this.performanceAnalytics = performanceAnalytics;
        this.timeProvider = timeProvider;
    }

    public static /* synthetic */ void trackNavigationStarted$onfido_capture_sdk_core_release$default(ScreenLoadTracker screenLoadTracker, PerformanceAnalyticsScreen performanceAnalyticsScreen, PerformanceAnalyticsScreen performanceAnalyticsScreen2, DocumentType documentType, DocSide docSide, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trackNavigationStarted");
        }
        if ((i & 4) != 0) {
            documentType = null;
        }
        if ((i & 8) != 0) {
            docSide = null;
        }
        screenLoadTracker.trackNavigationStarted$onfido_capture_sdk_core_release(performanceAnalyticsScreen, performanceAnalyticsScreen2, documentType, docSide);
    }

    public void trackNavigationCompleted$onfido_capture_sdk_core_release(PerformanceAnalyticsScreen destination) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        this.performanceAnalytics.trackEnd(new PerformanceEvents.ScreenLoadCompleted(this.timeProvider.getCurrentTimestamp(), destination));
    }

    public void trackNavigationStarted$onfido_capture_sdk_core_release(PerformanceAnalyticsScreen origin, PerformanceAnalyticsScreen destination, DocumentType documentType, DocSide documentSide) {
        Intrinsics.checkNotNullParameter(origin, "origin");
        Intrinsics.checkNotNullParameter(destination, "destination");
        this.performanceAnalytics.trackStart(new PerformanceEvents.ScreenLoadStarted(this.timeProvider.getCurrentTimestamp(), origin, destination, documentType, documentSide));
    }
}
