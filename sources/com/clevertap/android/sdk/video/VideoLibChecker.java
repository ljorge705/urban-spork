package com.clevertap.android.sdk.video;

import com.clevertap.android.sdk.Logger;
import io.sentry.SentryReplayOptions;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: VideoLibChecker.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\u0004H\u0002J\b\u0010\n\u001a\u00020\u0004H\u0002J\b\u0010\u000b\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/clevertap/android/sdk/video/VideoLibChecker;", "", "()V", "hasExoplayer", "", "hasMedia3", "haveVideoPlayerSupport", "mediaLibType", "Lcom/clevertap/android/sdk/video/VideoLibraryIntegrated;", "checkForExoPlayer", "checkForMedia3", "checkForVideoPlayerSupport", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class VideoLibChecker {
    public static final VideoLibChecker INSTANCE;
    private static final boolean hasExoplayer;
    private static final boolean hasMedia3;
    public static final boolean haveVideoPlayerSupport;
    public static final VideoLibraryIntegrated mediaLibType;

    private VideoLibChecker() {
    }

    static {
        VideoLibraryIntegrated videoLibraryIntegrated;
        VideoLibChecker videoLibChecker = new VideoLibChecker();
        INSTANCE = videoLibChecker;
        boolean zCheckForExoPlayer = videoLibChecker.checkForExoPlayer();
        hasExoplayer = zCheckForExoPlayer;
        boolean zCheckForMedia3 = videoLibChecker.checkForMedia3();
        hasMedia3 = zCheckForMedia3;
        haveVideoPlayerSupport = videoLibChecker.checkForVideoPlayerSupport();
        if (zCheckForMedia3) {
            videoLibraryIntegrated = VideoLibraryIntegrated.MEDIA3;
        } else if (zCheckForExoPlayer) {
            videoLibraryIntegrated = VideoLibraryIntegrated.EXOPLAYER;
        } else {
            videoLibraryIntegrated = VideoLibraryIntegrated.NONE;
        }
        mediaLibType = videoLibraryIntegrated;
    }

    private final boolean checkForVideoPlayerSupport() {
        boolean z = hasMedia3;
        if (!z && !hasExoplayer) {
            Logger.d("Please add ExoPlayer/Media3 dependencies to render InApp or Inbox messages playing video. For more information checkout CleverTap documentation.");
        }
        return hasExoplayer || z;
    }

    private final boolean checkForExoPlayer() {
        for (String str : CollectionsKt.listOf((Object[]) new String[]{"com.google.android.exoplayer2.ExoPlayer", "com.google.android.exoplayer2.source.hls.HlsMediaSource", SentryReplayOptions.EXOPLAYER_STYLED_CLASS_NAME})) {
            try {
                Class.forName(str);
            } catch (Throwable unused) {
                Logger.d(str + " is missing!!!");
                Logger.d("One or more ExoPlayer library files are missing!!!");
                return false;
            }
        }
        Logger.d("ExoPlayer is present");
        return true;
    }

    private final boolean checkForMedia3() {
        for (String str : CollectionsKt.listOf((Object[]) new String[]{"androidx.media3.exoplayer.ExoPlayer", "androidx.media3.exoplayer.hls.HlsMediaSource", SentryReplayOptions.ANDROIDX_MEDIA_VIEW_CLASS_NAME})) {
            try {
                Class.forName(str);
            } catch (Throwable unused) {
                Logger.d(str + " is missing!!!");
                Logger.d("One or more Media3 library files are missing!!!");
                return false;
            }
        }
        Logger.d("Media3 is present");
        return true;
    }
}
