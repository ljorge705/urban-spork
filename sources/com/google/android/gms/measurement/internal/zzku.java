package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
abstract class zzku extends zzkt {
    private boolean zza;

    zzku(zzlh zzlhVar) {
        super(zzlhVar);
        this.zzf.zzM();
    }

    protected final void zzW() {
        if (!zzY()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    final boolean zzY() {
        return this.zza;
    }

    protected abstract boolean zzb();

    public final void zzX() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzb();
        this.zzf.zzH();
        this.zza = true;
    }
}
