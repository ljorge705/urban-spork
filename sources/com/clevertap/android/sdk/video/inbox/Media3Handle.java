package com.clevertap.android.sdk.video.inbox;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter;
import androidx.media3.ui.PlayerView;
import com.clevertap.android.sdk.video.InboxVideoPlayerHandle;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Media3Handle.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J,\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\rH\u0016J \u0010\u000f\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\rH\u0016J\b\u0010\u0012\u001a\u00020\bH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J(\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0017H\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/clevertap/android/sdk/video/inbox/Media3Handle;", "Lcom/clevertap/android/sdk/video/InboxVideoPlayerHandle;", "()V", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "videoSurfaceView", "Landroidx/media3/ui/PlayerView;", "handleMute", "", "initExoplayer", "context", "Landroid/content/Context;", "buffering", "Lkotlin/Function0;", "playerReady", "initPlayerView", "artworkAsset", "Landroid/graphics/drawable/Drawable;", "pause", "playerVolume", "", "setPlayWhenReady", "play", "", "startPlaying", "ctx", "uriString", "", "isMediaAudio", "isMediaVideo", "videoSurface", "Landroid/view/View;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class Media3Handle implements InboxVideoPlayerHandle {
    private ExoPlayer player;
    private PlayerView videoSurfaceView;

    @Override // com.clevertap.android.sdk.video.InboxVideoPlayerHandle
    public void initExoplayer(Context context, final Function0<Unit> buffering, final Function0<Unit> playerReady) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(buffering, "buffering");
        Intrinsics.checkNotNullParameter(playerReady, "playerReady");
        if (this.player != null) {
            return;
        }
        final ExoPlayer exoPlayerBuild = new ExoPlayer.Builder(context).setTrackSelector(new DefaultTrackSelector(context, new AdaptiveTrackSelection.Factory())).build();
        exoPlayerBuild.setVolume(0.0f);
        exoPlayerBuild.addListener(new Media3PlayerListener() { // from class: com.clevertap.android.sdk.video.inbox.Media3Handle$initExoplayer$1$1
            @Override // androidx.media3.common.Player.Listener
            public void onPlaybackStateChanged(int playbackState) {
                if (playbackState == 2) {
                    buffering.invoke();
                    return;
                }
                if (playbackState != 3) {
                    if (playbackState == 4 && this.player != null) {
                        exoPlayerBuild.seekTo(0L);
                        exoPlayerBuild.setPlayWhenReady(false);
                        PlayerView playerView = this.videoSurfaceView;
                        if (playerView != null) {
                            playerView.showController();
                            return;
                        }
                        return;
                    }
                    return;
                }
                playerReady.invoke();
            }
        });
        this.player = exoPlayerBuild;
    }

    @Override // com.clevertap.android.sdk.video.InboxVideoPlayerHandle
    public View videoSurface() {
        View view = this.videoSurfaceView;
        Intrinsics.checkNotNull(view);
        return view;
    }

    @Override // com.clevertap.android.sdk.video.InboxVideoPlayerHandle
    public void setPlayWhenReady(boolean play) {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(play);
        }
    }

    @Override // com.clevertap.android.sdk.video.InboxVideoPlayerHandle
    public void pause() {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
        }
        this.player = null;
        this.videoSurfaceView = null;
    }

    @Override // com.clevertap.android.sdk.video.InboxVideoPlayerHandle
    public void initPlayerView(Context context, Function0<? extends Drawable> artworkAsset) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(artworkAsset, "artworkAsset");
        if (this.videoSurfaceView != null) {
            return;
        }
        PlayerView playerView = new PlayerView(context);
        playerView.setBackgroundColor(0);
        playerView.setResizeMode(context.getResources().getConfiguration().orientation == 2 ? 3 : 0);
        playerView.setUseArtwork(true);
        playerView.setDefaultArtwork(artworkAsset.invoke());
        playerView.setUseController(true);
        playerView.setControllerAutoShow(false);
        playerView.setPlayer(this.player);
        this.videoSurfaceView = playerView;
    }

    @Override // com.clevertap.android.sdk.video.InboxVideoPlayerHandle
    public float playerVolume() {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            return exoPlayer.getVolume();
        }
        return 0.0f;
    }

    @Override // com.clevertap.android.sdk.video.InboxVideoPlayerHandle
    public void handleMute() {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            float fPlayerVolume = playerVolume();
            if (fPlayerVolume > 0.0f) {
                exoPlayer.setVolume(0.0f);
            } else if (fPlayerVolume == 0.0f) {
                exoPlayer.setVolume(1.0f);
            }
        }
    }

    @Override // com.clevertap.android.sdk.video.InboxVideoPlayerHandle
    public void startPlaying(Context ctx, String uriString, boolean isMediaAudio, boolean isMediaVideo) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(uriString, "uriString");
        PlayerView playerView = this.videoSurfaceView;
        if (playerView != null) {
            playerView.requestFocus();
            playerView.setShowBuffering(0);
        }
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            TransferListener transferListenerBuild = new DefaultBandwidthMeter.Builder(ctx).build();
            Intrinsics.checkNotNullExpressionValue(transferListenerBuild, "Builder(ctx).build()");
            String userAgent = Util.getUserAgent(ctx, ctx.getPackageName());
            Intrinsics.checkNotNullExpressionValue(userAgent, "getUserAgent(ctx, ctx.packageName)");
            MediaItem mediaItemFromUri = MediaItem.fromUri(uriString);
            Intrinsics.checkNotNullExpressionValue(mediaItemFromUri, "fromUri(uriString)");
            DataSource.Factory transferListener = new DefaultHttpDataSource.Factory().setUserAgent(userAgent).setTransferListener(transferListenerBuild);
            Intrinsics.checkNotNullExpressionValue(transferListener, "Factory().setUserAgent(u…er(defaultBandwidthMeter)");
            MediaSource mediaSourceCreateMediaSource = new HlsMediaSource.Factory(new DefaultDataSource.Factory(ctx, transferListener)).createMediaSource(mediaItemFromUri);
            Intrinsics.checkNotNullExpressionValue(mediaSourceCreateMediaSource, "Factory(dataSourceFactor…ateMediaSource(mediaItem)");
            exoPlayer.setMediaSource(mediaSourceCreateMediaSource);
            exoPlayer.prepare();
            if (!isMediaAudio) {
                if (isMediaVideo) {
                    exoPlayer.setPlayWhenReady(true);
                    exoPlayer.setVolume(playerVolume());
                    return;
                }
                return;
            }
            PlayerView playerView2 = this.videoSurfaceView;
            if (playerView2 != null) {
                playerView2.showController();
            }
            exoPlayer.setPlayWhenReady(false);
            exoPlayer.setVolume(1.0f);
        }
    }
}
