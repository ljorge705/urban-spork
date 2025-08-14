package com.onfido.android.sdk.capture.internal.service;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import androidx.core.content.ContextCompat;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0003J\b\u0010\t\u001a\u00020\nH\u0007J\b\u0010\u000b\u001a\u00020\nH\u0007J\b\u0010\f\u001a\u00020\bH\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/service/VibratorService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "vibrator", "Landroid/os/Vibrator;", "oneShotVibrateApiLevelO", "", "vibrateForError", "Lio/reactivex/rxjava3/core/Completable;", "vibrateForSuccess", "waveFormVibrateApiLevelO", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VibratorService {

    @Deprecated
    public static final long SUCCESS_MILLIS = 100;

    @Deprecated
    public static final int VIBRATOR_NO_REPEAT = -1;
    private final Context context;
    private final Vibrator vibrator;
    private static final Companion Companion = new Companion(null);
    private static final long[] ERROR_VIBRATION_TIMINGS = {75, 75, 75, 75};
    private static final int[] ERROR_VIBRATION_AMPLITUDES = {255, 0, 255, 0};

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/service/VibratorService$Companion;", "", "()V", "ERROR_VIBRATION_AMPLITUDES", "", "getERROR_VIBRATION_AMPLITUDES", "()[I", "ERROR_VIBRATION_TIMINGS", "", "getERROR_VIBRATION_TIMINGS", "()[J", "SUCCESS_MILLIS", "", "VIBRATOR_NO_REPEAT", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public final int[] getERROR_VIBRATION_AMPLITUDES() {
            return VibratorService.ERROR_VIBRATION_AMPLITUDES;
        }

        public final long[] getERROR_VIBRATION_TIMINGS() {
            return VibratorService.ERROR_VIBRATION_TIMINGS;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public VibratorService(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.vibrator = (Vibrator) ContextCompat.getSystemService(context, Vibrator.class);
    }

    private final void oneShotVibrateApiLevelO() {
        Vibrator vibrator = this.vibrator;
        if (vibrator != null) {
            vibrator.vibrate(VibrationEffect.createOneShot(100L, -1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object vibrateForError$lambda$1(VibratorService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (ContextUtilsKt.apilevelAtLeast(26)) {
            this$0.waveFormVibrateApiLevelO();
        } else {
            Vibrator vibrator = this$0.vibrator;
            if (vibrator == null) {
                return null;
            }
            vibrator.vibrate(ERROR_VIBRATION_TIMINGS, -1);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object vibrateForSuccess$lambda$0(VibratorService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (ContextUtilsKt.apilevelAtLeast(26)) {
            this$0.oneShotVibrateApiLevelO();
        } else {
            Vibrator vibrator = this$0.vibrator;
            if (vibrator == null) {
                return null;
            }
            vibrator.vibrate(100L);
        }
        return Unit.INSTANCE;
    }

    private final void waveFormVibrateApiLevelO() {
        Vibrator vibrator = this.vibrator;
        if (vibrator != null) {
            vibrator.vibrate(VibrationEffect.createWaveform(ERROR_VIBRATION_TIMINGS, ERROR_VIBRATION_AMPLITUDES, -1));
        }
    }

    public final Completable vibrateForError() {
        Completable completableFromCallable = Completable.fromCallable(new Callable() { // from class: com.onfido.android.sdk.capture.internal.service.VibratorService$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return VibratorService.vibrateForError$lambda$1(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(completableFromCallable, "fromCallable(...)");
        return completableFromCallable;
    }

    public final Completable vibrateForSuccess() {
        Completable completableFromCallable = Completable.fromCallable(new Callable() { // from class: com.onfido.android.sdk.capture.internal.service.VibratorService$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return VibratorService.vibrateForSuccess$lambda$0(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(completableFromCallable, "fromCallable(...)");
        return completableFromCallable;
    }
}
