package io.sentry;

import io.sentry.protocol.SdkVersion;
import io.sentry.util.SampleRateUtils;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes6.dex */
public final class SentryReplayOptions {
    public static final String ANDROIDX_MEDIA_VIEW_CLASS_NAME = "androidx.media3.ui.PlayerView";
    public static final String EXOPLAYER_CLASS_NAME = "com.google.android.exoplayer2.ui.PlayerView";
    public static final String EXOPLAYER_STYLED_CLASS_NAME = "com.google.android.exoplayer2.ui.StyledPlayerView";
    public static final String IMAGE_VIEW_CLASS_NAME = "android.widget.ImageView";
    public static final String TEXT_VIEW_CLASS_NAME = "android.widget.TextView";
    public static final String VIDEO_VIEW_CLASS_NAME = "android.widget.VideoView";
    public static final String WEB_VIEW_CLASS_NAME = "android.webkit.WebView";
    private long errorReplayDuration;
    private int frameRate;
    private Set<String> maskViewClasses;
    private String maskViewContainerClass;
    private Double onErrorSampleRate;
    private SentryReplayQuality quality;
    private SdkVersion sdkVersion;
    private long sessionDuration;
    private Double sessionSampleRate;
    private long sessionSegmentDuration;
    private boolean trackOrientationChange;
    private Set<String> unmaskViewClasses;
    private String unmaskViewContainerClass;

    public long getErrorReplayDuration() {
        return this.errorReplayDuration;
    }

    public int getFrameRate() {
        return this.frameRate;
    }

    public Set<String> getMaskViewClasses() {
        return this.maskViewClasses;
    }

    public String getMaskViewContainerClass() {
        return this.maskViewContainerClass;
    }

    public Double getOnErrorSampleRate() {
        return this.onErrorSampleRate;
    }

    public SentryReplayQuality getQuality() {
        return this.quality;
    }

    public SdkVersion getSdkVersion() {
        return this.sdkVersion;
    }

    public long getSessionDuration() {
        return this.sessionDuration;
    }

    public Double getSessionSampleRate() {
        return this.sessionSampleRate;
    }

    public long getSessionSegmentDuration() {
        return this.sessionSegmentDuration;
    }

    public Set<String> getUnmaskViewClasses() {
        return this.unmaskViewClasses;
    }

    public String getUnmaskViewContainerClass() {
        return this.unmaskViewContainerClass;
    }

    public boolean isTrackOrientationChange() {
        return this.trackOrientationChange;
    }

    public void setQuality(SentryReplayQuality sentryReplayQuality) {
        this.quality = sentryReplayQuality;
    }

    public void setSdkVersion(SdkVersion sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public void setTrackOrientationChange(boolean z) {
        this.trackOrientationChange = z;
    }

    public void setUnmaskViewContainerClass(String str) {
        this.unmaskViewContainerClass = str;
    }

    public enum SentryReplayQuality {
        LOW(0.8f, 50000, 10),
        MEDIUM(1.0f, 75000, 30),
        HIGH(1.0f, 100000, 50);

        public final int bitRate;
        public final int screenshotQuality;
        public final float sizeScale;

        SentryReplayQuality(float f, int i, int i2) {
            this.sizeScale = f;
            this.bitRate = i;
            this.screenshotQuality = i2;
        }

        public String serializedName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }

    public SentryReplayOptions(boolean z, SdkVersion sdkVersion) {
        this.maskViewClasses = new CopyOnWriteArraySet();
        this.unmaskViewClasses = new CopyOnWriteArraySet();
        this.maskViewContainerClass = null;
        this.unmaskViewContainerClass = null;
        this.quality = SentryReplayQuality.MEDIUM;
        this.frameRate = 1;
        this.errorReplayDuration = 30000L;
        this.sessionSegmentDuration = 5000L;
        this.sessionDuration = org.apache.commons.lang3.time.DateUtils.MILLIS_PER_HOUR;
        this.trackOrientationChange = true;
        if (z) {
            return;
        }
        setMaskAllText(true);
        setMaskAllImages(true);
        this.maskViewClasses.add(WEB_VIEW_CLASS_NAME);
        this.maskViewClasses.add(VIDEO_VIEW_CLASS_NAME);
        this.maskViewClasses.add(ANDROIDX_MEDIA_VIEW_CLASS_NAME);
        this.maskViewClasses.add(EXOPLAYER_CLASS_NAME);
        this.maskViewClasses.add(EXOPLAYER_STYLED_CLASS_NAME);
        this.sdkVersion = sdkVersion;
    }

    public SentryReplayOptions(Double d, Double d2, SdkVersion sdkVersion) {
        this(false, sdkVersion);
        this.sessionSampleRate = d;
        this.onErrorSampleRate = d2;
        this.sdkVersion = sdkVersion;
    }

    public boolean isSessionReplayEnabled() {
        return getSessionSampleRate() != null && getSessionSampleRate().doubleValue() > 0.0d;
    }

    public void setOnErrorSampleRate(Double d) {
        if (!SampleRateUtils.isValidSampleRate(d)) {
            throw new IllegalArgumentException("The value " + d + " is not valid. Use null to disable or values >= 0.0 and <= 1.0.");
        }
        this.onErrorSampleRate = d;
    }

    public boolean isSessionReplayForErrorsEnabled() {
        return getOnErrorSampleRate() != null && getOnErrorSampleRate().doubleValue() > 0.0d;
    }

    public void setSessionSampleRate(Double d) {
        if (!SampleRateUtils.isValidSampleRate(d)) {
            throw new IllegalArgumentException("The value " + d + " is not valid. Use null to disable or values >= 0.0 and <= 1.0.");
        }
        this.sessionSampleRate = d;
    }

    public void setMaskAllText(boolean z) {
        if (z) {
            addMaskViewClass("android.widget.TextView");
            this.unmaskViewClasses.remove("android.widget.TextView");
        } else {
            addUnmaskViewClass("android.widget.TextView");
            this.maskViewClasses.remove("android.widget.TextView");
        }
    }

    public void setMaskAllImages(boolean z) {
        if (z) {
            addMaskViewClass(IMAGE_VIEW_CLASS_NAME);
            this.unmaskViewClasses.remove(IMAGE_VIEW_CLASS_NAME);
        } else {
            addUnmaskViewClass(IMAGE_VIEW_CLASS_NAME);
            this.maskViewClasses.remove(IMAGE_VIEW_CLASS_NAME);
        }
    }

    public void addMaskViewClass(String str) {
        this.maskViewClasses.add(str);
    }

    public void addUnmaskViewClass(String str) {
        this.unmaskViewClasses.add(str);
    }

    public void setMaskViewContainerClass(String str) {
        addMaskViewClass(str);
        this.maskViewContainerClass = str;
    }
}
