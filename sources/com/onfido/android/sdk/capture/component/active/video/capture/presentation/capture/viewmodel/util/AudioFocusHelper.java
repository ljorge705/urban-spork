package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.AudioPlaybackConfiguration;
import androidx.media3.common.MimeTypes;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0011H\u0002J\b\u0010\u001b\u001a\u00020\u0011H\u0002J\b\u0010\u001c\u001a\u00020\u0011H\u0003J\b\u0010\u001d\u001a\u00020\u0011H\u0002J\u0006\u0010\u001e\u001a\u00020\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R!\u0010\u0014\u001a\u0015\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00110\u00110\u0015¢\u0006\u0002\b\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/AudioFocusHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "audioChangeListener", "Landroid/media/AudioManager$OnAudioFocusChangeListener;", "audioFocusRequest", "Landroid/media/AudioFocusRequest;", "audioManager", "Landroid/media/AudioManager;", "getAudioManager", "()Landroid/media/AudioManager;", "audioManager$delegate", "Lkotlin/Lazy;", "focusLostObservable", "Lio/reactivex/rxjava3/core/Observable;", "", "getFocusLostObservable", "()Lio/reactivex/rxjava3/core/Observable;", "focusLostSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "abandonAudioFocus", "", "canStopActiveMusic", "requestAudioFocus", "requestAudioFocusForAndroidOreoAndAbove", "requestAudioFocusForBelowAndroidOreo", "requestAudioFocusIfPossible", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AudioFocusHelper {
    private final AudioManager.OnAudioFocusChangeListener audioChangeListener;
    private AudioFocusRequest audioFocusRequest;

    /* renamed from: audioManager$delegate, reason: from kotlin metadata */
    private final Lazy audioManager;
    private final Context context;
    private final Observable<Boolean> focusLostObservable;
    private final PublishSubject<Boolean> focusLostSubject;

    @Inject
    public AudioFocusHelper(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.audioManager = LazyKt.lazy(new Function0<AudioManager>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.AudioFocusHelper$audioManager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AudioManager invoke() {
                Object systemService = this.this$0.context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
                return (AudioManager) systemService;
            }
        });
        PublishSubject<Boolean> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this.focusLostSubject = publishSubjectCreate;
        Observable<Boolean> observableHide = publishSubjectCreate.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
        this.focusLostObservable = observableHide;
        this.audioChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.AudioFocusHelper$$ExternalSyntheticLambda0
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i) {
                AudioFocusHelper.audioChangeListener$lambda$0(this.f$0, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void audioChangeListener$lambda$0(AudioFocusHelper this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == -3 || i == -2 || i == -1) {
            this$0.focusLostSubject.onNext(Boolean.TRUE);
        }
    }

    private final boolean canStopActiveMusic() {
        if (!ContextUtilsKt.apilevelAtLeast(31)) {
            return false;
        }
        List<AudioPlaybackConfiguration> activePlaybackConfigurations = getAudioManager().getActivePlaybackConfigurations();
        Intrinsics.checkNotNullExpressionValue(activePlaybackConfigurations, "getActivePlaybackConfigurations(...)");
        Iterator<T> it = activePlaybackConfigurations.iterator();
        if (!it.hasNext()) {
            return false;
        }
        AudioPlaybackConfiguration audioPlaybackConfiguration = (AudioPlaybackConfiguration) it.next();
        return audioPlaybackConfiguration.getAudioAttributes().getContentType() != 1 && CollectionsKt.listOf((Object[]) new Integer[]{1, 14}).contains(Integer.valueOf(audioPlaybackConfiguration.getAudioAttributes().getUsage()));
    }

    private final AudioManager getAudioManager() {
        return (AudioManager) this.audioManager.getValue();
    }

    private final boolean requestAudioFocus() {
        return ContextUtilsKt.apilevelAtLeast(26) ? requestAudioFocusForAndroidOreoAndAbove() : requestAudioFocusForBelowAndroidOreo();
    }

    private final boolean requestAudioFocusForAndroidOreoAndAbove() {
        AudioFocusRequest.Builder builder = new AudioFocusRequest.Builder(1);
        AudioAttributes.Builder builder2 = new AudioAttributes.Builder();
        builder2.setUsage(2);
        builder2.setContentType(1);
        builder.setAudioAttributes(builder2.build());
        builder.setWillPauseWhenDucked(true);
        builder.setOnAudioFocusChangeListener(this.audioChangeListener);
        this.audioFocusRequest = builder.build();
        AudioManager audioManager = getAudioManager();
        AudioFocusRequest audioFocusRequest = this.audioFocusRequest;
        Intrinsics.checkNotNull(audioFocusRequest);
        return audioManager.requestAudioFocus(audioFocusRequest) == 1;
    }

    private final boolean requestAudioFocusForBelowAndroidOreo() {
        return getAudioManager().requestAudioFocus(this.audioChangeListener, 0, 1) == 1;
    }

    public final void abandonAudioFocus() {
        if (!ContextUtilsKt.apilevelAtLeast(26)) {
            getAudioManager().abandonAudioFocus(this.audioChangeListener);
            return;
        }
        AudioFocusRequest audioFocusRequest = this.audioFocusRequest;
        if (audioFocusRequest != null) {
            getAudioManager().abandonAudioFocusRequest(audioFocusRequest);
        }
    }

    public final Observable<Boolean> getFocusLostObservable() {
        return this.focusLostObservable;
    }

    public final boolean requestAudioFocusIfPossible() {
        if (!getAudioManager().isMusicActive() || canStopActiveMusic()) {
            return requestAudioFocus();
        }
        return false;
    }
}
