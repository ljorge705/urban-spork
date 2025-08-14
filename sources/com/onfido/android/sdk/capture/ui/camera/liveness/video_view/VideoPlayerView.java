package com.onfido.android.sdk.capture.ui.camera.liveness.video_view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.databinding.OnfidoVideoViewBinding;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.android.sdk.capture.utils.listeners.SurfaceTextureListener;
import io.sentry.Session;
import java.io.IOException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\b\u0000\u0018\u0000 %2\u00020\u0001:\u0001%B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0013\u001a\u00020\u0007J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0017J\u0010\u0010\u0019\u001a\u00020\u00172\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\b\u0010\u001a\u001a\u00020\u0017H\u0002J\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dJ\u001c\u0010\u001e\u001a\u00020\u00172\b\b\u0001\u0010\u001f\u001a\u00020\u00072\b\b\u0001\u0010 \u001a\u00020\u0007H\u0002J\u0006\u0010!\u001a\u00020\u0017J\u0018\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u0007H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006&"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/video_view/VideoPlayerView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoVideoViewBinding;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/video_view/VideoPlayViewListener;", "mediaPlayer", "Landroid/media/MediaPlayer;", "getMediaPlayer", "()Landroid/media/MediaPlayer;", "mediaPlayer$delegate", "Lkotlin/Lazy;", "getCurrentTimestamp", "isPlaying", "", "prepare", "", "release", "setListener", "setThumbnail", "setVideoPath", ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, "", "setVideoViewScreenReaderContent", "contentDescription", "hint", "stop", "updateTextureViewSize", "mVideoWidth", "mVideoHeight", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VideoPlayerView extends RelativeLayout {
    private static final long THUMBNAIL_DRAW_DELAY_MS = 200;
    private final OnfidoVideoViewBinding binding;
    private VideoPlayViewListener listener;

    /* renamed from: mediaPlayer$delegate, reason: from kotlin metadata */
    private final Lazy mediaPlayer;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VideoPlayerView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaPlayer getMediaPlayer() {
        return (MediaPlayer) this.mediaPlayer.getValue();
    }

    static final void lambda$1$lambda$0(VideoPlayerView this$0, OnfidoVideoViewBinding this_apply, View view) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        if (this$0.getMediaPlayer().isPlaying()) {
            ImageButton play = this_apply.play;
            Intrinsics.checkNotNullExpressionValue(play, "play");
            ViewExtensionsKt.animateToAlpha$default(play, 1.0f, 0L, 2, null);
            this$0.getMediaPlayer().pause();
            VideoPlayViewListener videoPlayViewListener = this$0.listener;
            if (videoPlayViewListener != null) {
                videoPlayViewListener.onVideoPaused();
                return;
            }
            return;
        }
        ImageButton play2 = this_apply.play;
        Intrinsics.checkNotNullExpressionValue(play2, "play");
        ViewExtensionsKt.animateToAlpha$default(play2, 0.0f, 0L, 2, null);
        this$0.getMediaPlayer().start();
        VideoPlayViewListener videoPlayViewListener2 = this$0.listener;
        if (videoPlayViewListener2 != null) {
            videoPlayViewListener2.onVideoStarted();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setThumbnail() {
        this.binding.videoView.postDelayed(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.video_view.VideoPlayerView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() throws IllegalStateException {
                VideoPlayerView.setThumbnail$lambda$2(this.f$0);
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setThumbnail$lambda$2(VideoPlayerView this$0) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            this$0.getMediaPlayer().seekTo(1);
        } catch (IllegalStateException unused) {
        }
    }

    private final void setVideoViewScreenReaderContent(int contentDescription, final int hint) {
        TextureView videoView = this.binding.videoView;
        Intrinsics.checkNotNullExpressionValue(videoView, "videoView");
        ViewExtensionsKt.setContentDescription(videoView, contentDescription);
        ViewCompat.setAccessibilityDelegate(this.binding.videoView, new AccessibilityDelegateCompat() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.video_view.VideoPlayerView.setVideoViewScreenReaderContent.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                Intrinsics.checkNotNullParameter(host, "host");
                Intrinsics.checkNotNullParameter(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                String string = host.getContext().getString(hint);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16, string));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateTextureViewSize(int mVideoWidth, int mVideoHeight) {
        int width = this.binding.videoView.getWidth();
        int height = this.binding.videoView.getHeight();
        float f = mVideoWidth / mVideoHeight;
        float f2 = width;
        float f3 = height;
        float f4 = f2 / f3;
        TextureView textureView = this.binding.videoView;
        ViewGroup.LayoutParams layoutParams = textureView.getLayoutParams();
        layoutParams.width = f > f4 ? (int) (f3 / f) : (int) (f * f3);
        layoutParams.height = height;
        textureView.setLayoutParams(layoutParams);
    }

    public final int getCurrentTimestamp() {
        return getMediaPlayer().getCurrentPosition();
    }

    public final boolean isPlaying() {
        return getMediaPlayer().isPlaying();
    }

    public final void prepare() throws IllegalStateException {
        getMediaPlayer().prepareAsync();
    }

    public final void release() throws IllegalStateException {
        getMediaPlayer().stop();
        getMediaPlayer().setOnCompletionListener(null);
        getMediaPlayer().release();
    }

    public final void setListener(VideoPlayViewListener listener) {
        this.listener = listener;
    }

    public final void setVideoPath(String path) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            getMediaPlayer().setDataSource(path);
        } catch (Exception unused) {
            VideoPlayViewListener videoPlayViewListener = this.listener;
            if (videoPlayViewListener != null) {
                videoPlayViewListener.onVideoError();
            }
        }
    }

    public final void stop() throws IllegalStateException {
        if (getMediaPlayer().isPlaying()) {
            getMediaPlayer().pause();
        }
        getMediaPlayer().stop();
        this.binding.play.setAlpha(1.0f);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VideoPlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        final OnfidoVideoViewBinding onfidoVideoViewBindingInflate = OnfidoVideoViewBinding.inflate(LayoutInflater.from(context), this);
        Intrinsics.checkNotNullExpressionValue(onfidoVideoViewBindingInflate, "inflate(...)");
        this.binding = onfidoVideoViewBindingInflate;
        onfidoVideoViewBindingInflate.videoView.setSurfaceTextureListener(new SurfaceTextureListener() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.video_view.VideoPlayerView$1$1
            @Override // com.onfido.android.sdk.capture.utils.listeners.SurfaceTextureListener, android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                this.this$0.getMediaPlayer().setSurface(new Surface(surface));
            }
        });
        onfidoVideoViewBindingInflate.videoView.setScaleX(-1.0f);
        setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.video_view.VideoPlayerView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalStateException {
                VideoPlayerView.lambda$1$lambda$0(this.f$0, onfidoVideoViewBindingInflate, view);
            }
        });
        setVideoViewScreenReaderContent(R.string.onfido_video_confirmation_video_accessibility, R.string.onfido_video_confirmation_button_play_and_pause_accessibility);
        this.mediaPlayer = LazyKt.lazy(new VideoPlayerView$mediaPlayer$2(this));
    }

    public /* synthetic */ VideoPlayerView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
