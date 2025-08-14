package com.google.android.play.core.splitinstall.model;

import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
public final class zza {
    private static final Map zza;
    private static final Map zzb;
    private static final Map zzc;

    static {
        HashMap map = new HashMap();
        zza = map;
        HashMap map2 = new HashMap();
        zzb = map2;
        map.put(-1, "Too many sessions are running for current app, existing sessions must be resolved first.");
        map.put(-2, "A requested module is not available (to this user/device, for the installed apk).");
        map.put(-3, "Request is otherwise invalid.");
        map.put(-4, "Requested session is not found.");
        map.put(-5, "Split Install API is not available.");
        map.put(-6, "Network error: unable to obtain split details.");
        map.put(-7, "Download not permitted under current device circumstances (e.g. in background).");
        map.put(-8, "Requested session contains modules from an existing active session and also new modules.");
        map.put(-9, "Service handling split install has died.");
        map.put(-10, "Install failed due to insufficient storage.");
        map.put(-11, "Signature verification error when invoking SplitCompat.");
        map.put(-12, "Error in SplitCompat emulation.");
        map.put(-13, "Error in copying files for SplitCompat.");
        map.put(-14, "The Play Store app is either not installed or not the official version.");
        map.put(-15, "The app is not owned by any user on this device. An app is \"owned\" if it has been acquired from Play.");
        map.put(-16, "The download is too large to start over the current connection.");
        map.put(-100, "Unknown error processing split install.");
        map2.put(-1, "ACTIVE_SESSIONS_LIMIT_EXCEEDED");
        map2.put(-2, "MODULE_UNAVAILABLE");
        map2.put(-3, "INVALID_REQUEST");
        map2.put(-4, "DOWNLOAD_NOT_FOUND");
        map2.put(-5, "API_NOT_AVAILABLE");
        map2.put(-6, "NETWORK_ERROR");
        map2.put(-7, "ACCESS_DENIED");
        map2.put(-8, "INCOMPATIBLE_WITH_EXISTING_SESSION");
        map2.put(-9, "SERVICE_DIED");
        map2.put(-10, "INSUFFICIENT_STORAGE");
        map2.put(-11, "SPLITCOMPAT_VERIFICATION_ERROR");
        map2.put(-12, "SPLITCOMPAT_EMULATION_ERROR");
        map2.put(-13, "SPLITCOMPAT_COPY_ERROR");
        map2.put(-14, "PLAY_STORE_NOT_FOUND");
        map2.put(-15, "APP_NOT_OWNED");
        map2.put(-16, "DOWNLOAD_TOO_LARGE");
        map2.put(-100, "INTERNAL_ERROR");
        zzc = new HashMap();
        for (Map.Entry entry : map2.entrySet()) {
            zzc.put((String) entry.getValue(), (Integer) entry.getKey());
        }
    }

    public static int zza(String str) {
        Integer num = (Integer) zzc.get(str);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException(String.valueOf(str).concat(" is unknown error."));
    }

    public static String zzb(int i) {
        Map map = zza;
        Integer numValueOf = Integer.valueOf(i);
        if (!map.containsKey(numValueOf)) {
            return "";
        }
        Map map2 = zzb;
        if (!map2.containsKey(numValueOf)) {
            return "";
        }
        return ((String) map.get(numValueOf)) + " (https://developer.android.com/reference/com/google/android/play/core/splitinstall/model/SplitInstallErrorCode.html#" + ((String) map2.get(numValueOf)) + ")";
    }
}
