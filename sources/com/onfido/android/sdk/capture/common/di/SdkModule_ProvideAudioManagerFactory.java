package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import android.media.AudioManager;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideAudioManagerFactory implements Factory<AudioManager> {
    private final Provider<Context> contextProvider;
    private final SdkModule module;

    public SdkModule_ProvideAudioManagerFactory(SdkModule sdkModule, Provider<Context> provider) {
        this.module = sdkModule;
        this.contextProvider = provider;
    }

    public static SdkModule_ProvideAudioManagerFactory create(SdkModule sdkModule, Provider<Context> provider) {
        return new SdkModule_ProvideAudioManagerFactory(sdkModule, provider);
    }

    public static AudioManager provideAudioManager(SdkModule sdkModule, Context context) {
        return (AudioManager) Preconditions.checkNotNullFromProvides(sdkModule.provideAudioManager(context));
    }

    @Override // com.onfido.javax.inject.Provider
    public AudioManager get() {
        return provideAudioManager(this.module, this.contextProvider.get());
    }
}
