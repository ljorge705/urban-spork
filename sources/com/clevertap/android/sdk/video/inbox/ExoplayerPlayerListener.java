package com.clevertap.android.sdk.video.inbox;

import com.google.android.exoplayer2.DeviceInfo;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Tracks;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.CueGroup;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.video.VideoSize;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.microsoft.codepush.react.CodePushConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExoplayerPlayerListener.kt */
@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0016\u0010\r\u001a\u00020\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u0019H\u0016J\u0010\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0019H\u0016J\u0010\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020%H\u0016J\u001a\u0010&\u001a\u00020\u00042\b\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020\tH\u0016J\u0010\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020,H\u0016J\u0010\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020/H\u0016J\u0018\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\tH\u0016J\u0010\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u000204H\u0016J\u0010\u00105\u001a\u00020\u00042\u0006\u00106\u001a\u00020\tH\u0016J\u0010\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u000209H\u0016J\u0012\u0010:\u001a\u00020\u00042\b\u00108\u001a\u0004\u0018\u000109H\u0016J\u0018\u0010;\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u00192\u0006\u0010<\u001a\u00020\tH\u0016J\u0010\u0010=\u001a\u00020\u00042\u0006\u0010+\u001a\u00020,H\u0016J \u0010>\u001a\u00020\u00042\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020@2\u0006\u0010)\u001a\u00020\tH\u0016J\u0010\u0010>\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\tH\u0016J\b\u0010B\u001a\u00020\u0004H\u0016J\u0010\u0010C\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\tH\u0016J\u0010\u0010E\u001a\u00020\u00042\u0006\u0010F\u001a\u00020%H\u0016J\u0010\u0010G\u001a\u00020\u00042\u0006\u0010H\u001a\u00020%H\u0016J\u0010\u0010I\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u0019H\u0016J\u0010\u0010K\u001a\u00020\u00042\u0006\u0010L\u001a\u00020\u0019H\u0016J\u0018\u0010M\u001a\u00020\u00042\u0006\u0010N\u001a\u00020\t2\u0006\u0010O\u001a\u00020\tH\u0016J\u0018\u0010P\u001a\u00020\u00042\u0006\u0010Q\u001a\u00020R2\u0006\u0010)\u001a\u00020\tH\u0016J\u0010\u0010S\u001a\u00020\u00042\u0006\u0010T\u001a\u00020UH\u0016J\u0010\u0010V\u001a\u00020\u00042\u0006\u0010W\u001a\u00020XH\u0016J\u0010\u0010Y\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020[H\u0016J\u0010\u0010\\\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020]H\u0016¨\u0006^"}, d2 = {"Lcom/clevertap/android/sdk/video/inbox/ExoplayerPlayerListener;", "Lcom/google/android/exoplayer2/Player$Listener;", "()V", "onAudioAttributesChanged", "", "audioAttributes", "Lcom/google/android/exoplayer2/audio/AudioAttributes;", "onAudioSessionIdChanged", "audioSessionId", "", "onAvailableCommandsChanged", "availableCommands", "Lcom/google/android/exoplayer2/Player$Commands;", "onCues", "cueGroup", "Lcom/google/android/exoplayer2/text/CueGroup;", "cues", "", "Lcom/google/android/exoplayer2/text/Cue;", "onDeviceInfoChanged", "deviceInfo", "Lcom/google/android/exoplayer2/DeviceInfo;", "onDeviceVolumeChanged", "volume", "muted", "", "onEvents", "player", "Lcom/google/android/exoplayer2/Player;", "events", "Lcom/google/android/exoplayer2/Player$Events;", "onIsLoadingChanged", CodePushConstants.PENDING_UPDATE_IS_LOADING_KEY, "onIsPlayingChanged", "isPlaying", "onMaxSeekToPreviousPositionChanged", "maxSeekToPreviousPositionMs", "", "onMediaItemTransition", "mediaItem", "Lcom/google/android/exoplayer2/MediaItem;", "reason", "onMediaMetadataChanged", "mediaMetadata", "Lcom/google/android/exoplayer2/MediaMetadata;", "onMetadata", "metadata", "Lcom/google/android/exoplayer2/metadata/Metadata;", "onPlayWhenReadyChanged", "playWhenReady", "onPlaybackParametersChanged", "playbackParameters", "Lcom/google/android/exoplayer2/PlaybackParameters;", "onPlaybackSuppressionReasonChanged", "playbackSuppressionReason", "onPlayerError", "error", "Lcom/google/android/exoplayer2/PlaybackException;", "onPlayerErrorChanged", "onPlayerStateChanged", "playbackState", "onPlaylistMetadataChanged", "onPositionDiscontinuity", "oldPosition", "Lcom/google/android/exoplayer2/Player$PositionInfo;", "newPosition", "onRenderedFirstFrame", "onRepeatModeChanged", "repeatMode", "onSeekBackIncrementChanged", "seekBackIncrementMs", "onSeekForwardIncrementChanged", "seekForwardIncrementMs", "onShuffleModeEnabledChanged", "shuffleModeEnabled", "onSkipSilenceEnabledChanged", "skipSilenceEnabled", "onSurfaceSizeChanged", "width", "height", "onTimelineChanged", "timeline", "Lcom/google/android/exoplayer2/Timeline;", "onTrackSelectionParametersChanged", DynamicLink.Builder.KEY_DYNAMIC_LINK_PARAMETERS, "Lcom/google/android/exoplayer2/trackselection/TrackSelectionParameters;", "onTracksChanged", "tracks", "Lcom/google/android/exoplayer2/Tracks;", "onVideoSizeChanged", "videoSize", "Lcom/google/android/exoplayer2/video/VideoSize;", "onVolumeChanged", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public class ExoplayerPlayerListener implements Player.Listener {
    public void onAudioAttributesChanged(AudioAttributes audioAttributes) {
        Intrinsics.checkNotNullParameter(audioAttributes, "audioAttributes");
    }

    public void onAudioSessionIdChanged(int audioSessionId) {
    }

    public void onAvailableCommandsChanged(Player.Commands availableCommands) {
        Intrinsics.checkNotNullParameter(availableCommands, "availableCommands");
    }

    public void onCues(CueGroup cueGroup) {
        Intrinsics.checkNotNullParameter(cueGroup, "cueGroup");
    }

    public void onCues(List<Cue> cues) {
        Intrinsics.checkNotNullParameter(cues, "cues");
    }

    public void onDeviceInfoChanged(DeviceInfo deviceInfo) {
        Intrinsics.checkNotNullParameter(deviceInfo, "deviceInfo");
    }

    public void onDeviceVolumeChanged(int volume, boolean muted) {
    }

    public void onEvents(Player player, Player.Events events) {
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(events, "events");
    }

    public void onIsLoadingChanged(boolean isLoading) {
    }

    public void onIsPlayingChanged(boolean isPlaying) {
    }

    public void onMaxSeekToPreviousPositionChanged(long maxSeekToPreviousPositionMs) {
    }

    public void onMediaItemTransition(MediaItem mediaItem, int reason) {
    }

    public void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
        Intrinsics.checkNotNullParameter(mediaMetadata, "mediaMetadata");
    }

    public void onMetadata(com.google.android.exoplayer2.metadata.Metadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
    }

    public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        Intrinsics.checkNotNullParameter(playbackParameters, "playbackParameters");
    }

    public void onPlaybackSuppressionReasonChanged(int playbackSuppressionReason) {
    }

    public void onPlayerError(PlaybackException error) {
        Intrinsics.checkNotNullParameter(error, "error");
    }

    public void onPlayerErrorChanged(PlaybackException error) {
    }

    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
    }

    public void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
        Intrinsics.checkNotNullParameter(mediaMetadata, "mediaMetadata");
    }

    public void onPositionDiscontinuity(int reason) {
    }

    public void onPositionDiscontinuity(Player.PositionInfo oldPosition, Player.PositionInfo newPosition, int reason) {
        Intrinsics.checkNotNullParameter(oldPosition, "oldPosition");
        Intrinsics.checkNotNullParameter(newPosition, "newPosition");
    }

    public void onRenderedFirstFrame() {
    }

    public void onRepeatModeChanged(int repeatMode) {
    }

    public void onSeekBackIncrementChanged(long seekBackIncrementMs) {
    }

    public void onSeekForwardIncrementChanged(long seekForwardIncrementMs) {
    }

    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
    }

    public void onSkipSilenceEnabledChanged(boolean skipSilenceEnabled) {
    }

    public void onSurfaceSizeChanged(int width, int height) {
    }

    public void onTimelineChanged(Timeline timeline, int reason) {
        Intrinsics.checkNotNullParameter(timeline, "timeline");
    }

    public void onTrackSelectionParametersChanged(TrackSelectionParameters parameters) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
    }

    public void onTracksChanged(Tracks tracks) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
    }

    public void onVideoSizeChanged(VideoSize videoSize) {
        Intrinsics.checkNotNullParameter(videoSize, "videoSize");
    }

    public void onVolumeChanged(float volume) {
    }
}
