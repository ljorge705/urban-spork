package com.google.android.play.core.splitinstall.internal;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
public final class zzbz implements zzcb, zzby {
    private static final Object zza = new Object();
    private volatile zzcb zzb;
    private volatile Object zzc = zza;

    private zzbz(zzcb zzcbVar) {
        this.zzb = zzcbVar;
    }

    public static zzby zzb(zzcb zzcbVar) {
        return zzcbVar instanceof zzby ? (zzby) zzcbVar : new zzbz(zzcbVar);
    }

    public static zzcb zzc(zzcb zzcbVar) {
        zzcbVar.getClass();
        return zzcbVar instanceof zzbz ? zzcbVar : new zzbz(zzcbVar);
    }

    @Override // com.google.android.play.core.splitinstall.internal.zzcb, com.google.android.play.core.splitinstall.internal.zzby
    public final Object zza() {
        Object objZza = this.zzc;
        Object obj = zza;
        if (objZza == obj) {
            synchronized (this) {
                objZza = this.zzc;
                if (objZza == obj) {
                    objZza = this.zzb.zza();
                    Object obj2 = this.zzc;
                    if (obj2 != obj && obj2 != objZza) {
                        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj2 + " & " + objZza + ". This is likely due to a circular dependency.");
                    }
                    this.zzc = objZza;
                    this.zzb = null;
                }
            }
        }
        return objZza;
    }
}
