package androidx.camera.video;

import android.content.Context;
import androidx.camera.core.impl.utils.ContextUtil;
import androidx.core.content.PermissionChecker;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class PendingRecording {
    private final Context mContext;
    private Consumer<VideoRecordEvent> mEventListener;
    private Executor mListenerExecutor;
    private final OutputOptions mOutputOptions;
    private final Recorder mRecorder;
    private boolean mAudioEnabled = false;
    private boolean mIsPersistent = false;

    public PendingRecording asPersistentRecording() {
        this.mIsPersistent = true;
        return this;
    }

    Context getApplicationContext() {
        return this.mContext;
    }

    Consumer<VideoRecordEvent> getEventListener() {
        return this.mEventListener;
    }

    Executor getListenerExecutor() {
        return this.mListenerExecutor;
    }

    OutputOptions getOutputOptions() {
        return this.mOutputOptions;
    }

    Recorder getRecorder() {
        return this.mRecorder;
    }

    boolean isAudioEnabled() {
        return this.mAudioEnabled;
    }

    boolean isPersistent() {
        return this.mIsPersistent;
    }

    PendingRecording(Context context, Recorder recorder, OutputOptions outputOptions) {
        this.mContext = ContextUtil.getApplicationContext(context);
        this.mRecorder = recorder;
        this.mOutputOptions = outputOptions;
    }

    public PendingRecording withAudioEnabled() {
        if (PermissionChecker.checkSelfPermission(this.mContext, "android.permission.RECORD_AUDIO") == -1) {
            throw new SecurityException("Attempted to enable audio for recording but application does not have RECORD_AUDIO permission granted.");
        }
        Preconditions.checkState(this.mRecorder.isAudioSupported(), "The Recorder this recording is associated to doesn't support audio.");
        this.mAudioEnabled = true;
        return this;
    }

    public Recording start(Executor executor, Consumer<VideoRecordEvent> consumer) {
        Preconditions.checkNotNull(executor, "Listener Executor can't be null.");
        Preconditions.checkNotNull(consumer, "Event listener can't be null");
        this.mListenerExecutor = executor;
        this.mEventListener = consumer;
        return this.mRecorder.start(this);
    }
}
