package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
public final class zzjl {
    private final String zza;
    private final zzjj zzc;
    private final String zze;
    private final zzji zzf;
    private final String zzb = null;
    private final String zzd = null;
    private final Long zzg = null;
    private final Boolean zzh = null;
    private final Boolean zzi = null;

    /* synthetic */ zzjl(zzjh zzjhVar, zzjk zzjkVar) {
        this.zza = zzjhVar.zza;
        this.zzc = zzjhVar.zzb;
        this.zze = zzjhVar.zzc;
        this.zzf = zzjhVar.zzd;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzjl)) {
            return false;
        }
        zzjl zzjlVar = (zzjl) obj;
        if (Objects.equal(this.zza, zzjlVar.zza)) {
            String str = zzjlVar.zzb;
            if (Objects.equal(null, null) && Objects.equal(this.zzc, zzjlVar.zzc)) {
                String str2 = zzjlVar.zzd;
                if (Objects.equal(null, null) && Objects.equal(this.zze, zzjlVar.zze) && Objects.equal(this.zzf, zzjlVar.zzf)) {
                    Long l = zzjlVar.zzg;
                    if (Objects.equal(null, null)) {
                        Boolean bool = zzjlVar.zzh;
                        if (Objects.equal(null, null)) {
                            Boolean bool2 = zzjlVar.zzi;
                            if (Objects.equal(null, null)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, this.zzc, null, this.zze, this.zzf, null, null, null);
    }

    public final zzji zza() {
        return this.zzf;
    }

    public final zzjj zzb() {
        return this.zzc;
    }

    public final String zzc() {
        return this.zze;
    }

    public final String zzd() {
        return this.zza;
    }
}
