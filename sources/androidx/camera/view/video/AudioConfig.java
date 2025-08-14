package androidx.camera.view.video;

/* loaded from: classes.dex */
public class AudioConfig {
    public static final AudioConfig AUDIO_DISABLED = new AudioConfig(false);
    private final boolean mIsAudioEnabled;

    public boolean getAudioEnabled() {
        return this.mIsAudioEnabled;
    }

    AudioConfig(boolean z) {
        this.mIsAudioEnabled = z;
    }

    public static AudioConfig create(boolean z) {
        return new AudioConfig(z);
    }
}
