package io.sentry.android.replay.video;

import android.media.MediaCodec;
import android.media.MediaFormat;
import com.facebook.react.uimanager.ViewProps;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* compiled from: SimpleFrameMuxer.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u0007H&J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH&¨\u0006\u0010"}, d2 = {"Lio/sentry/android/replay/video/SimpleFrameMuxer;", "", "getVideoTime", "", "isStarted", "", "muxVideoFrame", "", "encodedData", "Ljava/nio/ByteBuffer;", "bufferInfo", "Landroid/media/MediaCodec$BufferInfo;", "release", ViewProps.START, "videoFormat", "Landroid/media/MediaFormat;", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface SimpleFrameMuxer {
    long getVideoTime();

    boolean isStarted();

    void muxVideoFrame(ByteBuffer encodedData, MediaCodec.BufferInfo bufferInfo);

    void release();

    void start(MediaFormat videoFormat);
}
