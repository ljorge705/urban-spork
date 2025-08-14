package com.onfido.android.sdk.capture.ui.camera.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.camera.liveness.intro.error.LoadingErrorState;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.sentry.Session;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B%\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\u000bH\u0014J\b\u0010\r\u001a\u00020\u000bH\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0016J\b\u0010\u000f\u001a\u00020\u000bH\u0002J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\bH\u0002¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/view/PlaybackControlsVideoPlayerView;", "Lcom/onfido/android/sdk/capture/ui/camera/view/AutoPlayVideoView;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/intro/error/LoadingErrorState$LoadingErrorStateListener;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "makeButtonVisible", "", "onStarted", "pause", "resume", "setupVideoPlayButton", "startPauseButtonTimer", "duration", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PlaybackControlsVideoPlayerView extends AutoPlayVideoView implements LoadingErrorState.LoadingErrorStateListener {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PlaybackControlsVideoPlayerView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void makeButtonVisible() {
        getBinding().videoPlayButton.setVisibility(0);
    }

    private final void setupVideoPlayButton() {
        getBinding().videoPlayButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.view.PlaybackControlsVideoPlayerView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalStateException {
                PlaybackControlsVideoPlayerView.setupVideoPlayButton$lambda$1$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupVideoPlayButton$lambda$1$lambda$0(PlaybackControlsVideoPlayerView this$0, View view) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getMediaPlayer().isPlaying()) {
            this$0.pause();
        } else {
            this$0.resume();
        }
    }

    private final void startPauseButtonTimer(int duration) {
        if (duration <= 0) {
            return;
        }
        setDisposable(Observable.timer(duration, TimeUnit.MILLISECONDS, Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.view.PlaybackControlsVideoPlayerView.startPauseButtonTimer.1
            public final void accept(long j) {
                PlaybackControlsVideoPlayerView.this.makeButtonVisible();
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept(((Number) obj).longValue());
            }
        }).subscribe());
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.view.AutoPlayVideoView
    protected void onStarted() {
        super.onStarted();
        startPauseButtonTimer(getMediaPlayer().getDuration());
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.view.AutoPlayVideoView
    public void pause() throws IllegalStateException {
        ImageButton imageButton = getBinding().videoPlayButton;
        imageButton.setImageResource(R.drawable.onfido_video_play);
        imageButton.setContentDescription(imageButton.getContext().getString(R.string.onfido_video_intro_button_play_accessibility));
        super.pause();
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.view.AutoPlayVideoView
    public void resume() throws IllegalStateException {
        ImageButton imageButton = getBinding().videoPlayButton;
        imageButton.setImageResource(R.drawable.onfido_video_pause);
        imageButton.setContentDescription(imageButton.getContext().getString(R.string.onfido_video_intro_button_pause_accessibility));
        super.resume();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PlaybackControlsVideoPlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaybackControlsVideoPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        setupVideoPlayButton();
    }

    public /* synthetic */ PlaybackControlsVideoPlayerView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
