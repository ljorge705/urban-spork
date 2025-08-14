package io.sentry.android.replay.video;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.util.Range;
import android.view.Surface;
import androidx.media3.common.MimeTypes;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.io.IOException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SimpleVideoEncoder.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\u0010\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u0013H\u0002J\u000e\u0010+\u001a\u00020\b2\u0006\u0010,\u001a\u00020-J\u0006\u0010.\u001a\u00020\bJ\u0006\u0010/\u001a\u00020\bR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0018\u001a\u00020\u0019X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u0017\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lio/sentry/android/replay/video/SimpleVideoEncoder;", "", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "muxerConfig", "Lio/sentry/android/replay/video/MuxerConfig;", "onClose", "Lkotlin/Function0;", "", "(Lio/sentry/SentryOptions;Lio/sentry/android/replay/video/MuxerConfig;Lkotlin/jvm/functions/Function0;)V", "bufferInfo", "Landroid/media/MediaCodec$BufferInfo;", "duration", "", "getDuration", "()J", "frameMuxer", "Lio/sentry/android/replay/video/SimpleMp4FrameMuxer;", "hasExynosCodec", "", "getHasExynosCodec", "()Z", "hasExynosCodec$delegate", "Lkotlin/Lazy;", "mediaCodec", "Landroid/media/MediaCodec;", "getMediaCodec$sentry_android_replay_release", "()Landroid/media/MediaCodec;", "mediaFormat", "Landroid/media/MediaFormat;", "getMediaFormat", "()Landroid/media/MediaFormat;", "mediaFormat$delegate", "getMuxerConfig", "()Lio/sentry/android/replay/video/MuxerConfig;", "getOnClose", "()Lkotlin/jvm/functions/Function0;", "getOptions", "()Lio/sentry/SentryOptions;", "surface", "Landroid/view/Surface;", "drainCodec", "endOfStream", "encode", MimeTypes.BASE_TYPE_IMAGE, "Landroid/graphics/Bitmap;", "release", ViewProps.START, "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class SimpleVideoEncoder {
    private final MediaCodec.BufferInfo bufferInfo;
    private final SimpleMp4FrameMuxer frameMuxer;

    /* renamed from: hasExynosCodec$delegate, reason: from kotlin metadata */
    private final Lazy hasExynosCodec;
    private final MediaCodec mediaCodec;

    /* renamed from: mediaFormat$delegate, reason: from kotlin metadata */
    private final Lazy mediaFormat;
    private final MuxerConfig muxerConfig;
    private final Function0<Unit> onClose;
    private final SentryOptions options;
    private Surface surface;

    /* renamed from: getMediaCodec$sentry_android_replay_release, reason: from getter */
    public final MediaCodec getMediaCodec() {
        return this.mediaCodec;
    }

    public final MuxerConfig getMuxerConfig() {
        return this.muxerConfig;
    }

    public final Function0<Unit> getOnClose() {
        return this.onClose;
    }

    public final SentryOptions getOptions() {
        return this.options;
    }

    public SimpleVideoEncoder(SentryOptions options, MuxerConfig muxerConfig, Function0<Unit> function0) throws IOException {
        MediaCodec mediaCodecCreateEncoderByType;
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(muxerConfig, "muxerConfig");
        this.options = options;
        this.muxerConfig = muxerConfig;
        this.onClose = function0;
        this.hasExynosCodec = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Boolean>() { // from class: io.sentry.android.replay.video.SimpleVideoEncoder$hasExynosCodec$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                boolean z = false;
                MediaCodecInfo[] codecInfos = new MediaCodecList(0).getCodecInfos();
                Intrinsics.checkNotNullExpressionValue(codecInfos, "MediaCodecList(MediaCode…)\n            .codecInfos");
                MediaCodecInfo[] mediaCodecInfoArr = codecInfos;
                int length = mediaCodecInfoArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    String name = mediaCodecInfoArr[i].getName();
                    Intrinsics.checkNotNullExpressionValue(name, "it.name");
                    if (StringsKt.contains$default((CharSequence) name, (CharSequence) "c2.exynos", false, 2, (Object) null)) {
                        z = true;
                        break;
                    }
                    i++;
                }
                return Boolean.valueOf(z);
            }
        });
        if (getHasExynosCodec()) {
            mediaCodecCreateEncoderByType = MediaCodec.createByCodecName("c2.android.avc.encoder");
        } else {
            mediaCodecCreateEncoderByType = MediaCodec.createEncoderByType(this.muxerConfig.getMimeType());
        }
        Intrinsics.checkNotNullExpressionValue(mediaCodecCreateEncoderByType, "if (hasExynosCodec) {\n  …onfig.mimeType)\n        }");
        this.mediaCodec = mediaCodecCreateEncoderByType;
        this.mediaFormat = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<MediaFormat>() { // from class: io.sentry.android.replay.video.SimpleVideoEncoder$mediaFormat$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MediaFormat invoke() {
                int bitRate = this.this$0.getMuxerConfig().getBitRate();
                try {
                    MediaCodecInfo.VideoCapabilities videoCapabilities = this.this$0.getMediaCodec().getCodecInfo().getCapabilitiesForType(this.this$0.getMuxerConfig().getMimeType()).getVideoCapabilities();
                    if (!videoCapabilities.getBitrateRange().contains((Range<Integer>) Integer.valueOf(bitRate))) {
                        this.this$0.getOptions().getLogger().log(SentryLevel.DEBUG, "Encoder doesn't support the provided bitRate: " + bitRate + ", the value will be clamped to the closest one", new Object[0]);
                        Object objClamp = videoCapabilities.getBitrateRange().clamp(Integer.valueOf(bitRate));
                        Intrinsics.checkNotNullExpressionValue(objClamp, "videoCapabilities.bitrateRange.clamp(bitRate)");
                        bitRate = ((Number) objClamp).intValue();
                    }
                } catch (Throwable th) {
                    this.this$0.getOptions().getLogger().log(SentryLevel.DEBUG, "Could not retrieve MediaCodec info", th);
                }
                MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(this.this$0.getMuxerConfig().getMimeType(), this.this$0.getMuxerConfig().getRecordingWidth(), this.this$0.getMuxerConfig().getRecordingHeight());
                Intrinsics.checkNotNullExpressionValue(mediaFormatCreateVideoFormat, "createVideoFormat(\n     …recordingHeight\n        )");
                mediaFormatCreateVideoFormat.setInteger("color-format", 2130708361);
                mediaFormatCreateVideoFormat.setInteger("bitrate", bitRate);
                mediaFormatCreateVideoFormat.setFloat("frame-rate", this.this$0.getMuxerConfig().getFrameRate());
                mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 6);
                return mediaFormatCreateVideoFormat;
            }
        });
        this.bufferInfo = new MediaCodec.BufferInfo();
        String absolutePath = muxerConfig.getFile().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "muxerConfig.file.absolutePath");
        this.frameMuxer = new SimpleMp4FrameMuxer(absolutePath, muxerConfig.getFrameRate());
    }

    public /* synthetic */ SimpleVideoEncoder(SentryOptions sentryOptions, MuxerConfig muxerConfig, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sentryOptions, muxerConfig, (i & 4) != 0 ? null : function0);
    }

    private final boolean getHasExynosCodec() {
        return ((Boolean) this.hasExynosCodec.getValue()).booleanValue();
    }

    private final MediaFormat getMediaFormat() {
        return (MediaFormat) this.mediaFormat.getValue();
    }

    public final long getDuration() {
        return this.frameMuxer.getVideoTime();
    }

    public final void start() {
        this.mediaCodec.configure(getMediaFormat(), (Surface) null, (MediaCrypto) null, 1);
        this.surface = this.mediaCodec.createInputSurface();
        this.mediaCodec.start();
        drainCodec(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void encode(android.graphics.Bitmap r4) {
        /*
            r3 = this;
            java.lang.String r0 = "image"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = android.os.Build.MANUFACTURER
            java.lang.String r1 = "MANUFACTURER"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            java.lang.String r1 = "xiaomi"
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r2 = 1
            boolean r0 = kotlin.text.StringsKt.contains(r0, r1, r2)
            r1 = 0
            if (r0 == 0) goto L24
            android.view.Surface r0 = r3.surface
            if (r0 == 0) goto L2d
            android.graphics.Canvas r0 = r0.lockCanvas(r1)
            goto L2e
        L24:
            android.view.Surface r0 = r3.surface
            if (r0 == 0) goto L2d
            android.graphics.Canvas r0 = r0.lockHardwareCanvas()
            goto L2e
        L2d:
            r0 = r1
        L2e:
            if (r0 == 0) goto L34
            r2 = 0
            r0.drawBitmap(r4, r2, r2, r1)
        L34:
            android.view.Surface r4 = r3.surface
            if (r4 == 0) goto L3b
            r4.unlockCanvasAndPost(r0)
        L3b:
            r4 = 0
            r3.drainCodec(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.replay.video.SimpleVideoEncoder.encode(android.graphics.Bitmap):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0179, code lost:
    
        throw new java.lang.RuntimeException("encoderOutputBuffer " + r1 + " was null");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void drainCodec(boolean r9) {
        /*
            Method dump skipped, instructions count: 378
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.replay.video.SimpleVideoEncoder.drainCodec(boolean):void");
    }

    public final void release() {
        try {
            Function0<Unit> function0 = this.onClose;
            if (function0 != null) {
                function0.invoke();
            }
            drainCodec(true);
            this.mediaCodec.stop();
            this.mediaCodec.release();
            Surface surface = this.surface;
            if (surface != null) {
                surface.release();
            }
            this.frameMuxer.release();
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Failed to properly release video encoder", th);
        }
    }
}
