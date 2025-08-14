package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
abstract class zzf extends zze {
    private boolean zza;

    zzf(zzgd zzgdVar) {
        super(zzgdVar);
        this.zzt.zzD();
    }

    protected final void zza() {
        if (!zze()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzb() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        if (zzf()) {
            return;
        }
        this.zzt.zzB();
        this.zza = true;
    }

    protected void zzd() {
    }

    final boolean zze() {
        return this.zza;
    }

    protected abstract boolean zzf();

    public final void zzc() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzd();
        this.zzt.zzB();
        this.zza = true;
    }
}
