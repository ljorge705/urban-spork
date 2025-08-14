package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* loaded from: classes3.dex */
final class zzmq {
    private static final zzmq zza = new zzmq();
    private final ConcurrentMap zzc = new ConcurrentHashMap();
    private final zzmu zzb = new zzma();

    private zzmq() {
    }

    public static zzmq zza() {
        return zza;
    }

    public final zzmt zzb(Class cls) {
        zzlj.zzc(cls, "messageType");
        zzmt zzmtVarZza = (zzmt) this.zzc.get(cls);
        if (zzmtVarZza == null) {
            zzmtVarZza = this.zzb.zza(cls);
            zzlj.zzc(cls, "messageType");
            zzlj.zzc(zzmtVarZza, "schema");
            zzmt zzmtVar = (zzmt) this.zzc.putIfAbsent(cls, zzmtVarZza);
            if (zzmtVar != null) {
                return zzmtVar;
            }
        }
        return zzmtVarZza;
    }
}
