package com.onfido.android.sdk.capture.ui.camera.capture;

import androidx.core.app.FrameMetricsAggregator;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u001f\b\u0080\b\u0018\u0000 +2\u00020\u0001:\u0001+B_\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0007HÆ\u0003J\t\u0010 \u001a\u00020\u0007HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003J\t\u0010$\u001a\u00020\rHÆ\u0003J\t\u0010%\u001a\u00020\rHÆ\u0003Jc\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\rHÆ\u0001J\u0013\u0010'\u001a\u00020\u00052\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\u0007HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011¨\u0006,"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;", "", "fileNamePrefix", "", "hasAudio", "", "maxDuration", "", "qualityProfile", ViewProps.ASPECT_RATIO, "bitrate", "maxFps", "outOfStorageThreshold", "", "maxSize", "(Ljava/lang/String;ZIIIIIJJ)V", "getAspectRatio", "()I", "getBitrate", "getFileNamePrefix", "()Ljava/lang/String;", "getHasAudio", "()Z", "getMaxDuration", "getMaxFps", "getMaxSize", "()J", "getOutOfStorageThreshold", "getQualityProfile", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class VideoCaptureConfig {
    public static final String DEFAULT_FILE_NAME_PREFIX = "onfido-video";
    private final int aspectRatio;
    private final int bitrate;
    private final String fileNamePrefix;
    private final boolean hasAudio;
    private final int maxDuration;
    private final int maxFps;
    private final long maxSize;
    private final long outOfStorageThreshold;
    private final int qualityProfile;

    public VideoCaptureConfig() {
        this(null, false, 0, 0, 0, 0, 0, 0L, 0L, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getFileNamePrefix() {
        return this.fileNamePrefix;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getHasAudio() {
        return this.hasAudio;
    }

    /* renamed from: component3, reason: from getter */
    public final int getMaxDuration() {
        return this.maxDuration;
    }

    /* renamed from: component4, reason: from getter */
    public final int getQualityProfile() {
        return this.qualityProfile;
    }

    /* renamed from: component5, reason: from getter */
    public final int getAspectRatio() {
        return this.aspectRatio;
    }

    /* renamed from: component6, reason: from getter */
    public final int getBitrate() {
        return this.bitrate;
    }

    /* renamed from: component7, reason: from getter */
    public final int getMaxFps() {
        return this.maxFps;
    }

    /* renamed from: component8, reason: from getter */
    public final long getOutOfStorageThreshold() {
        return this.outOfStorageThreshold;
    }

    /* renamed from: component9, reason: from getter */
    public final long getMaxSize() {
        return this.maxSize;
    }

    public final VideoCaptureConfig copy(String fileNamePrefix, boolean hasAudio, int maxDuration, int qualityProfile, int aspectRatio, int bitrate, int maxFps, long outOfStorageThreshold, long maxSize) {
        Intrinsics.checkNotNullParameter(fileNamePrefix, "fileNamePrefix");
        return new VideoCaptureConfig(fileNamePrefix, hasAudio, maxDuration, qualityProfile, aspectRatio, bitrate, maxFps, outOfStorageThreshold, maxSize);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VideoCaptureConfig)) {
            return false;
        }
        VideoCaptureConfig videoCaptureConfig = (VideoCaptureConfig) other;
        return Intrinsics.areEqual(this.fileNamePrefix, videoCaptureConfig.fileNamePrefix) && this.hasAudio == videoCaptureConfig.hasAudio && this.maxDuration == videoCaptureConfig.maxDuration && this.qualityProfile == videoCaptureConfig.qualityProfile && this.aspectRatio == videoCaptureConfig.aspectRatio && this.bitrate == videoCaptureConfig.bitrate && this.maxFps == videoCaptureConfig.maxFps && this.outOfStorageThreshold == videoCaptureConfig.outOfStorageThreshold && this.maxSize == videoCaptureConfig.maxSize;
    }

    public final int getAspectRatio() {
        return this.aspectRatio;
    }

    public final int getBitrate() {
        return this.bitrate;
    }

    public final String getFileNamePrefix() {
        return this.fileNamePrefix;
    }

    public final boolean getHasAudio() {
        return this.hasAudio;
    }

    public final int getMaxDuration() {
        return this.maxDuration;
    }

    public final int getMaxFps() {
        return this.maxFps;
    }

    public final long getMaxSize() {
        return this.maxSize;
    }

    public final long getOutOfStorageThreshold() {
        return this.outOfStorageThreshold;
    }

    public final int getQualityProfile() {
        return this.qualityProfile;
    }

    public int hashCode() {
        return (((((((((((((((this.fileNamePrefix.hashCode() * 31) + Boolean.hashCode(this.hasAudio)) * 31) + Integer.hashCode(this.maxDuration)) * 31) + Integer.hashCode(this.qualityProfile)) * 31) + Integer.hashCode(this.aspectRatio)) * 31) + Integer.hashCode(this.bitrate)) * 31) + Integer.hashCode(this.maxFps)) * 31) + Long.hashCode(this.outOfStorageThreshold)) * 31) + Long.hashCode(this.maxSize);
    }

    public String toString() {
        return "VideoCaptureConfig(fileNamePrefix=" + this.fileNamePrefix + ", hasAudio=" + this.hasAudio + ", maxDuration=" + this.maxDuration + ", qualityProfile=" + this.qualityProfile + ", aspectRatio=" + this.aspectRatio + ", bitrate=" + this.bitrate + ", maxFps=" + this.maxFps + ", outOfStorageThreshold=" + this.outOfStorageThreshold + ", maxSize=" + this.maxSize + ')';
    }

    public VideoCaptureConfig(String fileNamePrefix, boolean z, int i, int i2, int i3, int i4, int i5, long j, long j2) {
        Intrinsics.checkNotNullParameter(fileNamePrefix, "fileNamePrefix");
        this.fileNamePrefix = fileNamePrefix;
        this.hasAudio = z;
        this.maxDuration = i;
        this.qualityProfile = i2;
        this.aspectRatio = i3;
        this.bitrate = i4;
        this.maxFps = i5;
        this.outOfStorageThreshold = j;
        this.maxSize = j2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ VideoCaptureConfig(String str, boolean z, int i, int i2, int i3, int i4, int i5, long j, long j2, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        String str2 = (i6 & 1) != 0 ? DEFAULT_FILE_NAME_PREFIX : str;
        boolean z2 = (i6 & 2) != 0 ? true : z;
        int i7 = (i6 & 4) != 0 ? LivenessConstants.LIVE_VIDEO_MAX_DURATION_MS : i;
        int i8 = (i6 & 8) != 0 ? 6 : i2;
        int i9 = (i6 & 16) != 0 ? -1 : i3;
        int i10 = (i6 & 32) != 0 ? LivenessConstants.LIVE_VIDEO_ENCODING_BIT_RATE : i4;
        this(str2, z2, i7, i8, i9, i10, (i6 & 64) == 0 ? i5 : -1, (i6 & 128) != 0 ? 500000L : j, (i6 & 256) != 0 ? ((i7 / 1000) * i10) / 8 : j2);
    }
}
