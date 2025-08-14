package io.sentry.android.replay.video;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.Session;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SimpleMp4FrameMuxer.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0011\u001a\u00020\bH\u0016J\b\u0010\u0012\u001a\u00020\rH\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0014H\u0016J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lio/sentry/android/replay/video/SimpleMp4FrameMuxer;", "Lio/sentry/android/replay/video/SimpleFrameMuxer;", ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, "", "fps", "", "(Ljava/lang/String;F)V", "finalVideoTime", "", "frameDurationUsec", "muxer", "Landroid/media/MediaMuxer;", Session.JsonKeys.STARTED, "", "videoFrames", "", "videoTrackIndex", "getVideoTime", "isStarted", "muxVideoFrame", "", "encodedData", "Ljava/nio/ByteBuffer;", "bufferInfo", "Landroid/media/MediaCodec$BufferInfo;", "release", ViewProps.START, "videoFormat", "Landroid/media/MediaFormat;", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class SimpleMp4FrameMuxer implements SimpleFrameMuxer {
    public static final int $stable = 8;
    private long finalVideoTime;
    private final long frameDurationUsec;
    private final MediaMuxer muxer;
    private boolean started;
    private int videoFrames;
    private int videoTrackIndex;

    @Override // io.sentry.android.replay.video.SimpleFrameMuxer
    /* renamed from: isStarted, reason: from getter */
    public boolean getStarted() {
        return this.started;
    }

    public SimpleMp4FrameMuxer(String path, float f) {
        Intrinsics.checkNotNullParameter(path, "path");
        this.frameDurationUsec = (long) (TimeUnit.SECONDS.toMicros(1L) / f);
        this.muxer = new MediaMuxer(path, 0);
    }

    @Override // io.sentry.android.replay.video.SimpleFrameMuxer
    public void start(MediaFormat videoFormat) {
        Intrinsics.checkNotNullParameter(videoFormat, "videoFormat");
        this.videoTrackIndex = this.muxer.addTrack(videoFormat);
        this.muxer.start();
        this.started = true;
    }

    @Override // io.sentry.android.replay.video.SimpleFrameMuxer
    public void muxVideoFrame(ByteBuffer encodedData, MediaCodec.BufferInfo bufferInfo) {
        Intrinsics.checkNotNullParameter(encodedData, "encodedData");
        Intrinsics.checkNotNullParameter(bufferInfo, "bufferInfo");
        long j = this.frameDurationUsec;
        int i = this.videoFrames;
        this.videoFrames = i + 1;
        long j2 = j * i;
        this.finalVideoTime = j2;
        bufferInfo.presentationTimeUs = j2;
        this.muxer.writeSampleData(this.videoTrackIndex, encodedData, bufferInfo);
    }

    @Override // io.sentry.android.replay.video.SimpleFrameMuxer
    public void release() {
        this.muxer.stop();
        this.muxer.release();
    }

    @Override // io.sentry.android.replay.video.SimpleFrameMuxer
    public long getVideoTime() {
        if (this.videoFrames == 0) {
            return 0L;
        }
        return TimeUnit.MILLISECONDS.convert(this.finalVideoTime + this.frameDurationUsec, TimeUnit.MICROSECONDS);
    }
}
