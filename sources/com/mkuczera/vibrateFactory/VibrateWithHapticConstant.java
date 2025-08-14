package com.mkuczera.vibrateFactory;

import android.os.Vibrator;

/* loaded from: classes2.dex */
public class VibrateWithHapticConstant implements Vibrate {
    int hapticConstant;

    public VibrateWithHapticConstant(int i) {
        this.hapticConstant = i;
    }

    @Override // com.mkuczera.vibrateFactory.Vibrate
    public void apply(Vibrator vibrator) {
        try {
            if (vibrator.hasVibrator()) {
                vibrator.vibrate(this.hapticConstant);
            }
        } catch (Exception unused) {
        }
    }
}
