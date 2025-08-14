package com.onfido.android.sdk.capture.audio;

import android.media.AudioManager;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/audio/VolumeManager;", "", "audioManager", "Landroid/media/AudioManager;", "(Landroid/media/AudioManager;)V", "isVolumeOn", "", "turnVolumeOn", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class VolumeManager {
    private final AudioManager audioManager;

    @Inject
    public VolumeManager(AudioManager audioManager) {
        Intrinsics.checkNotNullParameter(audioManager, "audioManager");
        this.audioManager = audioManager;
    }

    public boolean isVolumeOn() {
        return this.audioManager.getStreamVolume(3) > 0;
    }

    public void turnVolumeOn() {
        AudioManager audioManager = this.audioManager;
        audioManager.setStreamVolume(3, audioManager.getStreamMaxVolume(3), 0);
    }
}
