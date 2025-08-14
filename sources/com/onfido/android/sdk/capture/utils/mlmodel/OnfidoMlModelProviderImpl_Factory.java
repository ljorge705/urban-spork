package com.onfido.android.sdk.capture.utils.mlmodel;

import android.content.Context;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.CaptureTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class OnfidoMlModelProviderImpl_Factory implements Factory<OnfidoMlModelProviderImpl> {
    private final Provider<CaptureTracker> captureTrackerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<OnfidoDocumentDetectorFactory> documentDetectorFactoryProvider;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;
    private final Provider<TimeProvider> timeProvider;

    public OnfidoMlModelProviderImpl_Factory(Provider<Context> provider, Provider<CaptureTracker> provider2, Provider<TimeProvider> provider3, Provider<OnfidoDocumentDetectorFactory> provider4, Provider<OnfidoRemoteConfig> provider5) {
        this.contextProvider = provider;
        this.captureTrackerProvider = provider2;
        this.timeProvider = provider3;
        this.documentDetectorFactoryProvider = provider4;
        this.remoteConfigProvider = provider5;
    }

    public static OnfidoMlModelProviderImpl_Factory create(Provider<Context> provider, Provider<CaptureTracker> provider2, Provider<TimeProvider> provider3, Provider<OnfidoDocumentDetectorFactory> provider4, Provider<OnfidoRemoteConfig> provider5) {
        return new OnfidoMlModelProviderImpl_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static OnfidoMlModelProviderImpl newInstance(Context context, CaptureTracker captureTracker, TimeProvider timeProvider, OnfidoDocumentDetectorFactory onfidoDocumentDetectorFactory, OnfidoRemoteConfig onfidoRemoteConfig) {
        return new OnfidoMlModelProviderImpl(context, captureTracker, timeProvider, onfidoDocumentDetectorFactory, onfidoRemoteConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoMlModelProviderImpl get() {
        return newInstance(this.contextProvider.get(), this.captureTrackerProvider.get(), this.timeProvider.get(), this.documentDetectorFactoryProvider.get(), this.remoteConfigProvider.get());
    }
}
