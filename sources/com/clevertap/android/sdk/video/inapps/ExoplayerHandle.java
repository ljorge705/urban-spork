package com.clevertap.android.sdk.video.inapps;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.content.res.ResourcesCompat;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.video.InAppVideoPlayerHandle;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExoplayerHandle.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u000eH\u0016J\b\u0010\u0017\u001a\u00020\u000eH\u0016J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u001b\u001a\u00020\u000eH\u0016J\u0010\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u0015H\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/clevertap/android/sdk/video/inapps/ExoplayerHandle;", "Lcom/clevertap/android/sdk/video/InAppVideoPlayerHandle;", "()V", "mediaPosition", "", "player", "Lcom/google/android/exoplayer2/ExoPlayer;", "playerView", "Lcom/google/android/exoplayer2/ui/StyledPlayerView;", "playerViewLayoutParamsFullScreen", "Landroid/widget/FrameLayout$LayoutParams;", "playerViewLayoutParamsNormal", "Landroid/view/ViewGroup$LayoutParams;", "initExoplayer", "", "context", "Landroid/content/Context;", "url", "", "initPlayerView", "isTablet", "", "pause", "play", "playerHeight", "", "playerWidth", "savePosition", "switchToFullScreen", "isFullScreen", "videoSurface", "Landroid/view/View;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class ExoplayerHandle implements InAppVideoPlayerHandle {
    private long mediaPosition;
    private ExoPlayer player;
    private StyledPlayerView playerView;
    private FrameLayout.LayoutParams playerViewLayoutParamsFullScreen = new FrameLayout.LayoutParams(-1, -1);
    private ViewGroup.LayoutParams playerViewLayoutParamsNormal;

    @Override // com.clevertap.android.sdk.video.InAppVideoPlayerHandle
    public void initExoplayer(Context context, String url) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        if (this.player != null) {
            return;
        }
        BandwidthMeter bandwidthMeterBuild = new DefaultBandwidthMeter.Builder(context).build();
        Intrinsics.checkNotNullExpressionValue(bandwidthMeterBuild, "Builder(context).build()");
        TrackSelector defaultTrackSelector = new DefaultTrackSelector(context, new AdaptiveTrackSelection.Factory());
        String userAgent = Util.getUserAgent(context, context.getPackageName());
        Intrinsics.checkNotNullExpressionValue(userAgent, "getUserAgent(context, context.packageName)");
        DataSource.Factory transferListener = new DefaultHttpDataSource.Factory().setUserAgent(userAgent).setTransferListener(bandwidthMeterBuild.getTransferListener());
        Intrinsics.checkNotNullExpressionValue(transferListener, "Factory().setUserAgent(u…ransferListener(listener)");
        DataSource.Factory factory = new DefaultDataSource.Factory(context, transferListener);
        MediaItem mediaItemFromUri = MediaItem.fromUri(url);
        Intrinsics.checkNotNullExpressionValue(mediaItemFromUri, "fromUri(url)");
        MediaSource mediaSourceCreateMediaSource = new HlsMediaSource.Factory(factory).createMediaSource(mediaItemFromUri);
        Intrinsics.checkNotNullExpressionValue(mediaSourceCreateMediaSource, "Factory(dataSourceFactor…ateMediaSource(mediaItem)");
        ExoPlayer exoPlayerBuild = new ExoPlayer.Builder(context).setTrackSelector(defaultTrackSelector).build();
        exoPlayerBuild.setMediaSource(mediaSourceCreateMediaSource);
        exoPlayerBuild.prepare();
        exoPlayerBuild.setRepeatMode(1);
        exoPlayerBuild.seekTo(this.mediaPosition);
        this.player = exoPlayerBuild;
    }

    @Override // com.clevertap.android.sdk.video.InAppVideoPlayerHandle
    public void initPlayerView(Context context, boolean isTablet) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.playerView != null) {
            return;
        }
        int iPlayerWidth = playerWidth(context, isTablet);
        int iPlayerHeight = playerHeight(context, isTablet);
        StyledPlayerView styledPlayerView = new StyledPlayerView(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(iPlayerWidth, iPlayerHeight);
        this.playerViewLayoutParamsNormal = layoutParams;
        styledPlayerView.setLayoutParams(layoutParams);
        styledPlayerView.setShowBuffering(1);
        styledPlayerView.setUseArtwork(true);
        styledPlayerView.setControllerAutoShow(false);
        styledPlayerView.setDefaultArtwork(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ct_audio, null));
        this.playerView = styledPlayerView;
    }

    @Override // com.clevertap.android.sdk.video.InAppVideoPlayerHandle
    public void play() {
        StyledPlayerView styledPlayerView = this.playerView;
        if (styledPlayerView != null) {
            styledPlayerView.requestFocus();
            styledPlayerView.setVisibility(0);
            styledPlayerView.setPlayer(this.player);
        }
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(true);
        }
    }

    @Override // com.clevertap.android.sdk.video.InAppVideoPlayerHandle
    public void pause() {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            this.player = null;
        }
    }

    @Override // com.clevertap.android.sdk.video.InAppVideoPlayerHandle
    public void savePosition() {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            Intrinsics.checkNotNull(exoPlayer);
            this.mediaPosition = exoPlayer.getCurrentPosition();
        }
    }

    @Override // com.clevertap.android.sdk.video.InAppVideoPlayerHandle
    public void switchToFullScreen(boolean isFullScreen) {
        if (isFullScreen) {
            StyledPlayerView styledPlayerView = this.playerView;
            Intrinsics.checkNotNull(styledPlayerView);
            this.playerViewLayoutParamsNormal = styledPlayerView.getLayoutParams();
            StyledPlayerView styledPlayerView2 = this.playerView;
            Intrinsics.checkNotNull(styledPlayerView2);
            styledPlayerView2.setLayoutParams(this.playerViewLayoutParamsFullScreen);
            return;
        }
        StyledPlayerView styledPlayerView3 = this.playerView;
        Intrinsics.checkNotNull(styledPlayerView3);
        styledPlayerView3.setLayoutParams(this.playerViewLayoutParamsNormal);
    }

    @Override // com.clevertap.android.sdk.video.InAppVideoPlayerHandle
    public View videoSurface() {
        View view = this.playerView;
        Intrinsics.checkNotNull(view);
        return view;
    }

    public final int playerWidth(Context context, boolean isTablet) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (int) TypedValue.applyDimension(1, isTablet ? 408.0f : 240.0f, context.getResources().getDisplayMetrics());
    }

    public final int playerHeight(Context context, boolean isTablet) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (int) TypedValue.applyDimension(1, isTablet ? 299.0f : 134.0f, context.getResources().getDisplayMetrics());
    }
}
