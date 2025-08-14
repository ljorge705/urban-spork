package com.onfido.android.sdk.capture.ui.faceintro;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessTracker;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformanceAnalyticsScreen;
import com.onfido.android.sdk.capture.internal.performance.trackers.ScreenLoadTracker;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\r\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tJ\r\u0010\n\u001a\u00020\bH\u0000¢\u0006\u0002\b\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/faceintro/FaceIntroPresenter;", "", "livenessTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/LivenessTracker;", "screenLoadTracker", "Lcom/onfido/android/sdk/capture/internal/performance/trackers/ScreenLoadTracker;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/LivenessTracker;Lcom/onfido/android/sdk/capture/internal/performance/trackers/ScreenLoadTracker;)V", "onNextClicked", "", "onNextClicked$onfido_capture_sdk_core_release", "onStart", "onStart$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceIntroPresenter {
    private final LivenessTracker livenessTracker;
    private final ScreenLoadTracker screenLoadTracker;

    @Inject
    public FaceIntroPresenter(LivenessTracker livenessTracker, ScreenLoadTracker screenLoadTracker) {
        Intrinsics.checkNotNullParameter(livenessTracker, "livenessTracker");
        Intrinsics.checkNotNullParameter(screenLoadTracker, "screenLoadTracker");
        this.livenessTracker = livenessTracker;
        this.screenLoadTracker = screenLoadTracker;
    }

    public final void onNextClicked$onfido_capture_sdk_core_release() {
        this.livenessTracker.trackFaceIntroTakeSelfieButtonClicked$onfido_capture_sdk_core_release();
    }

    public final void onStart$onfido_capture_sdk_core_release() {
        this.livenessTracker.trackFaceIntro$onfido_capture_sdk_core_release(CaptureType.FACE);
        this.screenLoadTracker.trackNavigationCompleted$onfido_capture_sdk_core_release(PerformanceAnalyticsScreen.FACE_SELFIE_INTRO);
    }
}
