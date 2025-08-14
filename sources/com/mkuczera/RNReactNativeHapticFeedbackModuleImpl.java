package com.mkuczera;

import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import androidx.media3.common.MimeTypes;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.mkuczera.vibrateFactory.Vibrate;
import com.mkuczera.vibrateFactory.VibrateFactory;

/* loaded from: classes2.dex */
public class RNReactNativeHapticFeedbackModuleImpl {
    public static final String NAME = "RNHapticFeedback";

    public static boolean isVibrationEnabled(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
        AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        boolean z = vibrator != null && vibrator.hasVibrator();
        boolean z2 = audioManager.getRingerMode() != 0;
        boolean z3 = audioManager.getRingerMode() == 1;
        if (z) {
            return z2 || z3;
        }
        return false;
    }

    public static void trigger(ReactApplicationContext reactApplicationContext, String str, ReadableMap readableMap) {
        boolean z = readableMap.getBoolean("ignoreAndroidSystemSettings");
        boolean zIsVibrationEnabled = isVibrationEnabled(reactApplicationContext);
        if (z || zIsVibrationEnabled) {
            Vibrator vibrator = (Vibrator) reactApplicationContext.getSystemService("vibrator");
            Vibrate vibration = VibrateFactory.getVibration(str);
            if (vibrator == null || vibration == null) {
                return;
            }
            vibration.apply(vibrator);
        }
    }
}
