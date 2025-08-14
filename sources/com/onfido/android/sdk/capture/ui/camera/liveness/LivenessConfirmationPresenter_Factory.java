package com.onfido.android.sdk.capture.ui.camera.liveness;

import android.os.ResultReceiver;
import com.onfido.android.sdk.capture.audio.VolumeManager;
import com.onfido.android.sdk.capture.common.cryptography.PayloadHelper;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.metadata.SdkUploadMetadataHelper;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LivenessConfirmationPresenter_Factory implements Factory<LivenessConfirmationPresenter> {
    private final Provider<SdkConfiguration.LivenessCapture> livenessCaptureSettingsProvider;
    private final Provider<LivenessTracker> livenessTrackerProvider;
    private final Provider<ResultReceiver> mediaCallbackProvider;
    private final Provider<OnfidoApiService> onfidoAPIProvider;
    private final Provider<PayloadHelper> payloadHelperProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<SdkUploadMetadataHelper> sdkUploadMetadataHelperProvider;
    private final Provider<TimeProvider> timeProvider;
    private final Provider<VolumeManager> volumeManagerProvider;
    private final Provider<WaitingScreenTracker> waitingScreenTrackerProvider;

    public LivenessConfirmationPresenter_Factory(Provider<OnfidoApiService> provider, Provider<SdkUploadMetadataHelper> provider2, Provider<PayloadHelper> provider3, Provider<SdkConfiguration.LivenessCapture> provider4, Provider<VolumeManager> provider5, Provider<SchedulersProvider> provider6, Provider<LivenessTracker> provider7, Provider<TimeProvider> provider8, Provider<WaitingScreenTracker> provider9, Provider<ResultReceiver> provider10) {
        this.onfidoAPIProvider = provider;
        this.sdkUploadMetadataHelperProvider = provider2;
        this.payloadHelperProvider = provider3;
        this.livenessCaptureSettingsProvider = provider4;
        this.volumeManagerProvider = provider5;
        this.schedulersProvider = provider6;
        this.livenessTrackerProvider = provider7;
        this.timeProvider = provider8;
        this.waitingScreenTrackerProvider = provider9;
        this.mediaCallbackProvider = provider10;
    }

    public static LivenessConfirmationPresenter_Factory create(Provider<OnfidoApiService> provider, Provider<SdkUploadMetadataHelper> provider2, Provider<PayloadHelper> provider3, Provider<SdkConfiguration.LivenessCapture> provider4, Provider<VolumeManager> provider5, Provider<SchedulersProvider> provider6, Provider<LivenessTracker> provider7, Provider<TimeProvider> provider8, Provider<WaitingScreenTracker> provider9, Provider<ResultReceiver> provider10) {
        return new LivenessConfirmationPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static LivenessConfirmationPresenter newInstance(OnfidoApiService onfidoApiService, SdkUploadMetadataHelper sdkUploadMetadataHelper, PayloadHelper payloadHelper, SdkConfiguration.LivenessCapture livenessCapture, VolumeManager volumeManager, SchedulersProvider schedulersProvider, LivenessTracker livenessTracker, TimeProvider timeProvider, WaitingScreenTracker waitingScreenTracker, ResultReceiver resultReceiver) {
        return new LivenessConfirmationPresenter(onfidoApiService, sdkUploadMetadataHelper, payloadHelper, livenessCapture, volumeManager, schedulersProvider, livenessTracker, timeProvider, waitingScreenTracker, resultReceiver);
    }

    @Override // com.onfido.javax.inject.Provider
    public LivenessConfirmationPresenter get() {
        return newInstance(this.onfidoAPIProvider.get(), this.sdkUploadMetadataHelperProvider.get(), this.payloadHelperProvider.get(), this.livenessCaptureSettingsProvider.get(), this.volumeManagerProvider.get(), this.schedulersProvider.get(), this.livenessTrackerProvider.get(), this.timeProvider.get(), this.waitingScreenTrackerProvider.get(), this.mediaCallbackProvider.get());
    }
}
