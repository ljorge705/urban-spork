package com.mkuczera.vibrateFactory;

import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

/* loaded from: classes2.dex */
public class VibrateWithCreatePredefined implements Vibrate {
    int hapticConstant;

    VibrateWithCreatePredefined(int i) {
        this.hapticConstant = i;
    }

    @Override // com.mkuczera.vibrateFactory.Vibrate
    public void apply(Vibrator vibrator) {
        if (Build.VERSION.SDK_INT < 29) {
            return;
        }
        try {
            if (vibrator.hasVibrator()) {
                vibrator.vibrate(VibrationEffect.createPredefined(this.hapticConstant));
            }
        } catch (Exception unused) {
        }
    }
}
