package com.onfido.android.sdk.capture.audio;

import android.media.AudioManager;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class VolumeManager_Factory implements Factory<VolumeManager> {
    private final Provider<AudioManager> audioManagerProvider;

    public VolumeManager_Factory(Provider<AudioManager> provider) {
        this.audioManagerProvider = provider;
    }

    public static VolumeManager_Factory create(Provider<AudioManager> provider) {
        return new VolumeManager_Factory(provider);
    }

    public static VolumeManager newInstance(AudioManager audioManager) {
        return new VolumeManager(audioManager);
    }

    @Override // com.onfido.javax.inject.Provider
    public VolumeManager get() {
        return newInstance(this.audioManagerProvider.get());
    }
}
