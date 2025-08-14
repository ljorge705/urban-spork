package com.onfido.android.sdk.capture.common.di.network;

import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* loaded from: classes2.dex */
public final class NetworkModule_ProvideRetrofit$onfido_capture_sdk_core_releaseFactory implements Factory<Retrofit> {
    private final Provider<Json> jsonProvider;
    private final Provider<OkHttpClient> okHttpClientProvider;

    public NetworkModule_ProvideRetrofit$onfido_capture_sdk_core_releaseFactory(Provider<OkHttpClient> provider, Provider<Json> provider2) {
        this.okHttpClientProvider = provider;
        this.jsonProvider = provider2;
    }

    public static NetworkModule_ProvideRetrofit$onfido_capture_sdk_core_releaseFactory create(Provider<OkHttpClient> provider, Provider<Json> provider2) {
        return new NetworkModule_ProvideRetrofit$onfido_capture_sdk_core_releaseFactory(provider, provider2);
    }

    public static Retrofit provideRetrofit$onfido_capture_sdk_core_release(OkHttpClient okHttpClient, Json json) {
        return (Retrofit) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideRetrofit$onfido_capture_sdk_core_release(okHttpClient, json));
    }

    @Override // com.onfido.javax.inject.Provider
    public Retrofit get() {
        return provideRetrofit$onfido_capture_sdk_core_release(this.okHttpClientProvider.get(), this.jsonProvider.get());
    }
}
