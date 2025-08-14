package com.onfido.android.sdk.capture.internal.validation.backend;

import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DefaultBackendValidations_Factory implements Factory<DefaultBackendValidations> {
    private final Provider<NativeDetector> nativeDetectorProvider;

    public DefaultBackendValidations_Factory(Provider<NativeDetector> provider) {
        this.nativeDetectorProvider = provider;
    }

    public static DefaultBackendValidations_Factory create(Provider<NativeDetector> provider) {
        return new DefaultBackendValidations_Factory(provider);
    }

    public static DefaultBackendValidations newInstance(NativeDetector nativeDetector) {
        return new DefaultBackendValidations(nativeDetector);
    }

    @Override // com.onfido.javax.inject.Provider
    public DefaultBackendValidations get() {
        return newInstance(this.nativeDetectorProvider.get());
    }
}
