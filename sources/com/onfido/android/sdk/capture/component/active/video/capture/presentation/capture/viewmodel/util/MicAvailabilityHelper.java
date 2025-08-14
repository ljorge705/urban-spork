package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import androidx.media3.common.MimeTypes;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0003J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0006\u0010\t\u001a\u00020\bJ\b\u0010\n\u001a\u00020\bH\u0002J\b\u0010\u000b\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/MicAvailabilityHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "createRecorder", "Landroid/media/AudioRecord;", "isAnotherAppUsingMic", "", "isMicAvailable", "isMicMutedByAnotherApp", "isVoiceCallActive", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MicAvailabilityHelper {
    public static final int SAMPLE_RATE_HZ = 44100;
    private final Context context;

    @Inject
    public MicAvailabilityHelper(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    private final AudioRecord createRecorder() {
        return new AudioRecord(1, SAMPLE_RATE_HZ, 16, 2, AudioRecord.getMinBufferSize(SAMPLE_RATE_HZ, 16, 2) * 2);
    }

    private final boolean isAnotherAppUsingMic() {
        if (ContextUtilsKt.apilevelAtLeast(29)) {
            return false;
        }
        AudioRecord audioRecordCreateRecorder = createRecorder();
        try {
            audioRecordCreateRecorder.startRecording();
            if (audioRecordCreateRecorder.getRecordingState() != 3) {
                return true;
            }
            audioRecordCreateRecorder.stop();
            return false;
        } catch (IllegalStateException unused) {
            return true;
        } finally {
            audioRecordCreateRecorder.release();
        }
    }

    private final boolean isMicMutedByAnotherApp() {
        Object systemService = this.context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        return ((AudioManager) systemService).isMicrophoneMute();
    }

    private final boolean isVoiceCallActive() {
        Object systemService = this.context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        return CollectionsKt.listOf((Object[]) new Integer[]{2, 3}).contains(Integer.valueOf(((AudioManager) systemService).getMode()));
    }

    public final boolean isMicAvailable() {
        return (isAnotherAppUsingMic() || isVoiceCallActive() || isMicMutedByAnotherApp()) ? false : true;
    }
}
