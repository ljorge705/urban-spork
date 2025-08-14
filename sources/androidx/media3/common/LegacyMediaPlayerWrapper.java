package androidx.media3.common;

import android.media.MediaPlayer;
import android.os.Looper;
import androidx.media3.common.Player;
import androidx.media3.common.SimpleBasePlayer;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes5.dex */
public final class LegacyMediaPlayerWrapper extends SimpleBasePlayer {
    private boolean playWhenReady;
    private final MediaPlayer player;

    public LegacyMediaPlayerWrapper(Looper looper) {
        super(looper);
        this.player = new MediaPlayer();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected SimpleBasePlayer.State getState() {
        return new SimpleBasePlayer.State.Builder().setAvailableCommands(new Player.Commands.Builder().addAll(1).build()).setPlayWhenReady(this.playWhenReady, 1).build();
    }

    @Override // androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleSetPlayWhenReady(boolean z) throws IllegalStateException {
        this.playWhenReady = z;
        if (z) {
            this.player.start();
        } else {
            this.player.pause();
        }
        return Futures.immediateVoidFuture();
    }
}
