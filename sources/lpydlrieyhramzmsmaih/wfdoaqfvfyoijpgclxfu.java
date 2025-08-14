package lpydlrieyhramzmsmaih;

import android.content.Context;
import android.os.Build;

/* loaded from: classes4.dex */
abstract class wfdoaqfvfyoijpgclxfu {
    public static boolean yvrzbryuycempgkdhpvj() {
        String str = Build.FINGERPRINT;
        if (!str.startsWith("generic") && !str.startsWith("unknown")) {
            String str2 = Build.MODEL;
            if (!str2.contains("google_sdk") && !str2.contains("Emulator") && !str2.contains("Android SDK built for x86") && !Build.MANUFACTURER.contains("Genymotion") && ((!Build.BRAND.startsWith("generic") || !Build.DEVICE.startsWith("generic")) && !"google_sdk".equals(Build.PRODUCT))) {
                return false;
            }
        }
        return true;
    }

    public static boolean yvrzbryuycempgkdhpvj(Context context) {
        return new nqavqmicmgayorfggxof.oacciftezlubzxpkwvyc().yvrzbryuycempgkdhpvj(context);
    }
}
