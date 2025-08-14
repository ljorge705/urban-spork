package com.onfido.android.sdk.capture.ui.camera.liveness.video_view;

import android.media.MediaPlayer;
import android.widget.ImageButton;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/media/MediaPlayer;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
final class VideoPlayerView$mediaPlayer$2 extends Lambda implements Function0<MediaPlayer> {
    final /* synthetic */ VideoPlayerView this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoPlayerView$mediaPlayer$2(VideoPlayerView videoPlayerView) {
        super(0);
        this.this$0 = videoPlayerView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$4$lambda$0(VideoPlayerView this$0, MediaPlayer mediaPlayer, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updateTextureViewSize(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$4$lambda$1(VideoPlayerView this$0, MediaPlayer this_apply, MediaPlayer mediaPlayer) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        VideoPlayViewListener videoPlayViewListener = this$0.listener;
        if (videoPlayViewListener != null) {
            videoPlayViewListener.onVideoFinished();
        }
        this_apply.seekTo(0);
        ImageButton play = this$0.binding.play;
        Intrinsics.checkNotNullExpressionValue(play, "play");
        ViewExtensionsKt.animateToAlpha$default(play, 1.0f, 0L, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$4$lambda$2(VideoPlayerView this$0, MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setThumbnail();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean invoke$lambda$4$lambda$3(VideoPlayerView this$0, MediaPlayer mediaPlayer, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        VideoPlayViewListener videoPlayViewListener = this$0.listener;
        if (videoPlayViewListener == null) {
            return true;
        }
        videoPlayViewListener.onVideoError();
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final MediaPlayer invoke() {
        final MediaPlayer mediaPlayer = new MediaPlayer();
        final VideoPlayerView videoPlayerView = this.this$0;
        mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.video_view.VideoPlayerView$mediaPlayer$2$$ExternalSyntheticLambda0
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public final void onVideoSizeChanged(MediaPlayer mediaPlayer2, int i, int i2) {
                VideoPlayerView$mediaPlayer$2.invoke$lambda$4$lambda$0(videoPlayerView, mediaPlayer2, i, i2);
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.video_view.VideoPlayerView$mediaPlayer$2$$ExternalSyntheticLambda1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer2) throws IllegalStateException {
                VideoPlayerView$mediaPlayer$2.invoke$lambda$4$lambda$1(videoPlayerView, mediaPlayer, mediaPlayer2);
            }
        });
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.video_view.VideoPlayerView$mediaPlayer$2$$ExternalSyntheticLambda2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer2) {
                VideoPlayerView$mediaPlayer$2.invoke$lambda$4$lambda$2(videoPlayerView, mediaPlayer2);
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.video_view.VideoPlayerView$mediaPlayer$2$$ExternalSyntheticLambda3
            @Override // android.media.MediaPlayer.OnErrorListener
            public final boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
                return VideoPlayerView$mediaPlayer$2.invoke$lambda$4$lambda$3(videoPlayerView, mediaPlayer2, i, i2);
            }
        });
        mediaPlayer.setScreenOnWhilePlaying(true);
        return mediaPlayer;
    }
}
