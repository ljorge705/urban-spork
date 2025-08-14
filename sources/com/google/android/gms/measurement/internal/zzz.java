package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzoy;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
final class zzz extends zzy {
    final /* synthetic */ zzaa zza;
    private final com.google.android.gms.internal.measurement.zzet zzh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzz(zzaa zzaaVar, String str, int i, com.google.android.gms.internal.measurement.zzet zzetVar) {
        super(str, i);
        this.zza = zzaaVar;
        this.zzh = zzetVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzy
    final int zza() {
        return this.zzh.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzy
    final boolean zzb() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzy
    final boolean zzc() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    final boolean zzd(Long l, Long l2, com.google.android.gms.internal.measurement.zzgm zzgmVar, boolean z) {
        zzoy.zzc();
        boolean zZzs = this.zza.zzt.zzf().zzs(this.zzb, zzeg.zzW);
        boolean zZzg = this.zzh.zzg();
        boolean zZzh = this.zzh.zzh();
        boolean zZzi = this.zzh.zzi();
        Object[] objArr = zZzg || zZzh || zZzi;
        Boolean boolZzj = null;
        boolZzj = null;
        boolZzj = null;
        boolZzj = null;
        boolZzj = null;
        if (z && objArr != true) {
            this.zza.zzt.zzaA().zzj().zzc("Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(this.zzc), this.zzh.zzj() ? Integer.valueOf(this.zzh.zza()) : null);
            return true;
        }
        com.google.android.gms.internal.measurement.zzem zzemVarZzb = this.zzh.zzb();
        boolean zZzg2 = zzemVarZzb.zzg();
        if (zzgmVar.zzr()) {
            if (zzemVarZzb.zzi()) {
                boolZzj = zzj(zzh(zzgmVar.zzb(), zzemVarZzb.zzc()), zZzg2);
            } else {
                this.zza.zzt.zzaA().zzk().zzb("No number filter for long property. property", this.zza.zzt.zzj().zzf(zzgmVar.zzf()));
            }
        } else if (zzgmVar.zzq()) {
            if (zzemVarZzb.zzi()) {
                boolZzj = zzj(zzg(zzgmVar.zza(), zzemVarZzb.zzc()), zZzg2);
            } else {
                this.zza.zzt.zzaA().zzk().zzb("No number filter for double property. property", this.zza.zzt.zzj().zzf(zzgmVar.zzf()));
            }
        } else if (!zzgmVar.zzt()) {
            this.zza.zzt.zzaA().zzk().zzb("User property has no value, property", this.zza.zzt.zzj().zzf(zzgmVar.zzf()));
        } else if (zzemVarZzb.zzk()) {
            boolZzj = zzj(zzf(zzgmVar.zzg(), zzemVarZzb.zzd(), this.zza.zzt.zzaA()), zZzg2);
        } else if (!zzemVarZzb.zzi()) {
            this.zza.zzt.zzaA().zzk().zzb("No string or number filter defined. property", this.zza.zzt.zzj().zzf(zzgmVar.zzf()));
        } else if (zzlj.zzy(zzgmVar.zzg())) {
            boolZzj = zzj(zzi(zzgmVar.zzg(), zzemVarZzb.zzc()), zZzg2);
        } else {
            this.zza.zzt.zzaA().zzk().zzc("Invalid user property value for Numeric number filter. property, value", this.zza.zzt.zzj().zzf(zzgmVar.zzf()), zzgmVar.zzg());
        }
        this.zza.zzt.zzaA().zzj().zzb("Property filter result", boolZzj == null ? "null" : boolZzj);
        if (boolZzj == null) {
            return false;
        }
        this.zzd = true;
        if (zZzi && !boolZzj.booleanValue()) {
            return true;
        }
        if (!z || this.zzh.zzg()) {
            this.zze = boolZzj;
        }
        if (boolZzj.booleanValue() && objArr != false && zzgmVar.zzs()) {
            long jZzc = zzgmVar.zzc();
            if (l != null) {
                jZzc = l.longValue();
            }
            if (zZzs && this.zzh.zzg() && !this.zzh.zzh() && l2 != null) {
                jZzc = l2.longValue();
            }
            if (this.zzh.zzh()) {
                this.zzg = Long.valueOf(jZzc);
            } else {
                this.zzf = Long.valueOf(jZzc);
            }
        }
        return true;
    }
}
