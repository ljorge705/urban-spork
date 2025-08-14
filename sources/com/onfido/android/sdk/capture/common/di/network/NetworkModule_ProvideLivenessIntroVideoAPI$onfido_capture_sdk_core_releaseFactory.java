package com.onfido.android.sdk.capture.common.di.network;

import com.onfido.android.sdk.capture.ui.camera.liveness.intro.LivenessIntroVideoApi;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;
import retrofit2.Retrofit;

/* loaded from: classes2.dex */
public final class NetworkModule_ProvideLivenessIntroVideoAPI$onfido_capture_sdk_core_releaseFactory implements Factory<LivenessIntroVideoApi> {
    private final Provider<Retrofit> retrofitProvider;

    public NetworkModule_ProvideLivenessIntroVideoAPI$onfido_capture_sdk_core_releaseFactory(Provider<Retrofit> provider) {
        this.retrofitProvider = provider;
    }

    public static NetworkModule_ProvideLivenessIntroVideoAPI$onfido_capture_sdk_core_releaseFactory create(Provider<Retrofit> provider) {
        return new NetworkModule_ProvideLivenessIntroVideoAPI$onfido_capture_sdk_core_releaseFactory(provider);
    }

    public static LivenessIntroVideoApi provideLivenessIntroVideoAPI$onfido_capture_sdk_core_release(Retrofit retrofit) {
        return (LivenessIntroVideoApi) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideLivenessIntroVideoAPI$onfido_capture_sdk_core_release(retrofit));
    }

    @Override // com.onfido.javax.inject.Provider
    public LivenessIntroVideoApi get() {
        return provideLivenessIntroVideoAPI$onfido_capture_sdk_core_release(this.retrofitProvider.get());
    }
}
