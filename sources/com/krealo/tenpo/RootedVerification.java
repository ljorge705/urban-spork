package com.krealo.tenpo;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.widget.Toast;
import com.scottyab.rootbeer.RootBeer;

/* loaded from: classes2.dex */
public class RootedVerification extends Activity {
    Context c;
    String message = "¡ERROR! No puede usar Tenpo si el dispositivo está rooteado. Pruebe desde otro celular.";

    public final Context getCContext() {
        return this.c;
    }

    public RootedVerification(Context context) {
        this.c = context;
    }

    public void evaluateRootedDevice() {
        if (!new RootBeer(this.c).isRootedWithoutBusyBoxCheck() || isEmulator() || BuildConfig.ROOT_ENABLED) {
            return;
        }
        Toast.makeText(this.c, this.message, 1).show();
        new Handler().postDelayed(new Runnable() { // from class: com.krealo.tenpo.RootedVerification.1
            @Override // java.lang.Runnable
            public void run() {
                Process.killProcess(Process.myPid());
            }
        }, 4500L);
    }

    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT);
    }
}
