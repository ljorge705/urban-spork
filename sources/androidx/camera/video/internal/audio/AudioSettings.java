package androidx.camera.video.internal.audio;

import androidx.camera.video.internal.audio.AutoValue_AudioSettings;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.MicAvailabilityHelper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public abstract class AudioSettings {
    public static final List<Integer> COMMON_SAMPLE_RATES = Collections.unmodifiableList(Arrays.asList(48000, Integer.valueOf(MicAvailabilityHelper.SAMPLE_RATE_HZ), 22050, 11025, Integer.valueOf(PhotoshopDirectory.TAG_LIGHTROOM_WORKFLOW), 4800));

    public abstract int getAudioFormat();

    public abstract int getAudioSource();

    public abstract int getChannelCount();

    public abstract int getSampleRate();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_AudioSettings.Builder().setAudioSource(-1).setSampleRate(-1).setChannelCount(-1).setAudioFormat(-1);
    }

    AudioSettings() {
    }

    public int getBytesPerFrame() {
        return AudioUtils.getBytesPerFrame(getAudioFormat(), getChannelCount());
    }

    public static abstract class Builder {
        abstract AudioSettings autoBuild();

        public abstract Builder setAudioFormat(int i);

        public abstract Builder setAudioSource(int i);

        public abstract Builder setChannelCount(int i);

        public abstract Builder setSampleRate(int i);

        public final AudioSettings build() {
            AudioSettings audioSettingsAutoBuild = autoBuild();
            String str = audioSettingsAutoBuild.getAudioSource() == -1 ? " audioSource" : "";
            if (audioSettingsAutoBuild.getSampleRate() <= 0) {
                str = str + " sampleRate";
            }
            if (audioSettingsAutoBuild.getChannelCount() <= 0) {
                str = str + " channelCount";
            }
            if (audioSettingsAutoBuild.getAudioFormat() == -1) {
                str = str + " audioFormat";
            }
            if (str.isEmpty()) {
                return audioSettingsAutoBuild;
            }
            throw new IllegalArgumentException("Required settings missing or non-positive:" + str);
        }

        Builder() {
        }
    }
}
