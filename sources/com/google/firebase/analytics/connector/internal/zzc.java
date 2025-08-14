package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzix;
import com.google.android.gms.internal.measurement.zzja;
import com.google.android.gms.internal.measurement.zzjb;
import com.google.android.gms.measurement.internal.zzhe;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import io.sentry.protocol.App;

/* compiled from: com.google.android.gms:play-services-measurement-api@@21.3.0 */
/* loaded from: classes5.dex */
public final class zzc {
    public static final /* synthetic */ int zza = 0;
    private static final zzjb zzb = zzjb.zzi("_in", "_xa", "_xu", "_aq", "_aa", "_ai", "_ac", FirebaseAnalytics.Event.CAMPAIGN_DETAILS, "_ug", "_iapx", "_exp_set", "_exp_clear", "_exp_activate", "_exp_timeout", "_exp_expire");
    private static final zzja zzc = zzja.zzj("_e", "_f", "_iap", "_s", "_au", "_ui", "_cd");
    private static final zzja zzd = zzja.zzi("auto", App.TYPE, "am");
    private static final zzja zze = zzja.zzh("_r", "_dbg");
    private static final zzja zzf;
    private static final zzja zzg;

    static {
        zzix zzixVar = new zzix();
        zzixVar.zza(zzhe.zza);
        zzixVar.zza(zzhe.zzb);
        zzf = zzixVar.zzb();
        zzg = zzja.zzh("^_ltv_[A-Z]{3}$", "^_cc[1-5]{1}$");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean zza(java.lang.String r5, java.lang.String r6, android.os.Bundle r7) {
        /*
            java.lang.String r0 = "_cmp"
            boolean r6 = r0.equals(r6)
            r0 = 1
            if (r6 != 0) goto La
            return r0
        La:
            boolean r6 = zzd(r5)
            r1 = 0
            if (r6 != 0) goto L12
            return r1
        L12:
            if (r7 != 0) goto L15
            return r1
        L15:
            com.google.android.gms.internal.measurement.zzja r6 = com.google.firebase.analytics.connector.internal.zzc.zze
            int r2 = r6.size()
            r3 = r1
        L1c:
            if (r3 >= r2) goto L2d
            java.lang.Object r4 = r6.get(r3)
            java.lang.String r4 = (java.lang.String) r4
            boolean r4 = r7.containsKey(r4)
            int r3 = r3 + 1
            if (r4 == 0) goto L1c
            return r1
        L2d:
            int r6 = r5.hashCode()
            r2 = 101200(0x18b50, float:1.41811E-40)
            r3 = 2
            if (r6 == r2) goto L56
            r2 = 101230(0x18b6e, float:1.41853E-40)
            if (r6 == r2) goto L4c
            r2 = 3142703(0x2ff42f, float:4.403865E-39)
            if (r6 == r2) goto L42
            goto L60
        L42:
            java.lang.String r6 = "fiam"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L60
            r5 = r3
            goto L61
        L4c:
            java.lang.String r6 = "fdl"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L60
            r5 = r0
            goto L61
        L56:
            java.lang.String r6 = "fcm"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L60
            r5 = r1
            goto L61
        L60:
            r5 = -1
        L61:
            java.lang.String r6 = "_cis"
            if (r5 == 0) goto L76
            if (r5 == r0) goto L70
            if (r5 == r3) goto L6a
            return r1
        L6a:
            java.lang.String r5 = "fiam_integration"
            r7.putString(r6, r5)
            return r0
        L70:
            java.lang.String r5 = "fdl_integration"
            r7.putString(r6, r5)
            return r0
        L76:
            java.lang.String r5 = "fcm_integration"
            r7.putString(r6, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.analytics.connector.internal.zzc.zza(java.lang.String, java.lang.String, android.os.Bundle):boolean");
    }

    public static boolean zzb(String str, Bundle bundle) {
        if (zzc.contains(str)) {
            return false;
        }
        if (bundle == null) {
            return true;
        }
        zzja zzjaVar = zze;
        int size = zzjaVar.size();
        int i = 0;
        while (i < size) {
            boolean zContainsKey = bundle.containsKey((String) zzjaVar.get(i));
            i++;
            if (zContainsKey) {
                return false;
            }
        }
        return true;
    }

    public static boolean zzc(String str) {
        return !zzb.contains(str);
    }

    public static boolean zzd(String str) {
        return !zzd.contains(str);
    }

    public static boolean zze(String str, String str2) {
        if ("_ce1".equals(str2) || "_ce2".equals(str2)) {
            return str.equals("fcm") || str.equals(FirebaseABTesting.OriginService.REMOTE_CONFIG);
        }
        if (Constants.ScionAnalytics.USER_PROPERTY_FIREBASE_LAST_NOTIFICATION.equals(str2)) {
            return str.equals("fcm") || str.equals("fiam");
        }
        if (zzf.contains(str2)) {
            return false;
        }
        zzja zzjaVar = zzg;
        int size = zzjaVar.size();
        int i = 0;
        while (i < size) {
            boolean zMatches = str2.matches((String) zzjaVar.get(i));
            i++;
            if (zMatches) {
                return false;
            }
        }
        return true;
    }
}
