package com.clevertap.android.sdk.video;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: InboxVideoPlayerHandle.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J,\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\bH&J \u0010\n\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\bH&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&J(\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0012H&J\b\u0010\u0019\u001a\u00020\u001aH&¨\u0006\u001b"}, d2 = {"Lcom/clevertap/android/sdk/video/InboxVideoPlayerHandle;", "", "handleMute", "", "initExoplayer", "context", "Landroid/content/Context;", "buffering", "Lkotlin/Function0;", "playerReady", "initPlayerView", "artworkAsset", "Landroid/graphics/drawable/Drawable;", "pause", "playerVolume", "", "setPlayWhenReady", "play", "", "startPlaying", "ctx", "uriString", "", "isMediaAudio", "isMediaVideo", "videoSurface", "Landroid/view/View;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public interface InboxVideoPlayerHandle {
    void handleMute();

    void initExoplayer(Context context, Function0<Unit> buffering, Function0<Unit> playerReady);

    void initPlayerView(Context context, Function0<? extends Drawable> artworkAsset);

    void pause();

    float playerVolume();

    void setPlayWhenReady(boolean play);

    void startPlaying(Context ctx, String uriString, boolean isMediaAudio, boolean isMediaVideo);

    View videoSurface();
}
