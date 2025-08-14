package com.onfido.android.sdk.capture.analytics;

import android.content.Context;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.utils.DeviceMetadataProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class IdentityInteractor_Factory implements Factory<IdentityInteractor> {
    private final Provider<Context> contextProvider;
    private final Provider<DeviceMetadataProvider> deviceMetadataProvider;
    private final Provider<NativeDetector> nativeDetectorProvider;

    public IdentityInteractor_Factory(Provider<Context> provider, Provider<NativeDetector> provider2, Provider<DeviceMetadataProvider> provider3) {
        this.contextProvider = provider;
        this.nativeDetectorProvider = provider2;
        this.deviceMetadataProvider = provider3;
    }

    public static IdentityInteractor_Factory create(Provider<Context> provider, Provider<NativeDetector> provider2, Provider<DeviceMetadataProvider> provider3) {
        return new IdentityInteractor_Factory(provider, provider2, provider3);
    }

    public static IdentityInteractor newInstance(Context context, NativeDetector nativeDetector, DeviceMetadataProvider deviceMetadataProvider) {
        return new IdentityInteractor(context, nativeDetector, deviceMetadataProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public IdentityInteractor get() {
        return newInstance(this.contextProvider.get(), this.nativeDetectorProvider.get(), this.deviceMetadataProvider.get());
    }
}
