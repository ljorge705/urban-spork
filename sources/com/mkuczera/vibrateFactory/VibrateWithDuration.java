package com.mkuczera.vibrateFactory;

import android.os.Vibrator;

/* loaded from: classes2.dex */
public class VibrateWithDuration implements Vibrate {
    long[] durations;

    public VibrateWithDuration(long[] jArr) {
        this.durations = jArr;
    }

    @Override // com.mkuczera.vibrateFactory.Vibrate
    public void apply(Vibrator vibrator) {
        try {
            if (vibrator.hasVibrator()) {
                vibrator.vibrate(this.durations, -1);
            }
        } catch (Exception unused) {
        }
    }
}
