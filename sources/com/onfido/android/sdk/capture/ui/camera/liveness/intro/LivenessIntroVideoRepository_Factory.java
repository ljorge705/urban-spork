package com.onfido.android.sdk.capture.ui.camera.liveness.intro;

import com.onfido.android.sdk.capture.internal.util.FileCache;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LivenessIntroVideoRepository_Factory implements Factory<LivenessIntroVideoRepository> {
    private final Provider<LivenessIntroVideoApi> apiProvider;
    private final Provider<FileCache> cacheProvider;
    private final Provider<LivenessIntroVideoUrlProvider> urlProvider;

    public LivenessIntroVideoRepository_Factory(Provider<LivenessIntroVideoUrlProvider> provider, Provider<FileCache> provider2, Provider<LivenessIntroVideoApi> provider3) {
        this.urlProvider = provider;
        this.cacheProvider = provider2;
        this.apiProvider = provider3;
    }

    public static LivenessIntroVideoRepository_Factory create(Provider<LivenessIntroVideoUrlProvider> provider, Provider<FileCache> provider2, Provider<LivenessIntroVideoApi> provider3) {
        return new LivenessIntroVideoRepository_Factory(provider, provider2, provider3);
    }

    public static LivenessIntroVideoRepository newInstance(LivenessIntroVideoUrlProvider livenessIntroVideoUrlProvider, FileCache fileCache, LivenessIntroVideoApi livenessIntroVideoApi) {
        return new LivenessIntroVideoRepository(livenessIntroVideoUrlProvider, fileCache, livenessIntroVideoApi);
    }

    @Override // com.onfido.javax.inject.Provider
    public LivenessIntroVideoRepository get() {
        return newInstance(this.urlProvider.get(), this.cacheProvider.get(), this.apiProvider.get());
    }
}
