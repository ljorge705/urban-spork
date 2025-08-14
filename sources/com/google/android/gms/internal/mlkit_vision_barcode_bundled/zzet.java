package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public class zzet {
    private static final zzdn zzb = zzdn.zza();
    protected volatile zzfl zza;
    private volatile zzdb zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzet)) {
            return false;
        }
        zzet zzetVar = (zzet) obj;
        zzfl zzflVar = this.zza;
        zzfl zzflVar2 = zzetVar.zza;
        if (zzflVar == null && zzflVar2 == null) {
            return zzb().equals(zzetVar.zzb());
        }
        if (zzflVar != null && zzflVar2 != null) {
            return zzflVar.equals(zzflVar2);
        }
        if (zzflVar != null) {
            zzetVar.zzd(zzflVar.zzX());
            return zzflVar.equals(zzetVar.zza);
        }
        zzd(zzflVar2.zzX());
        return this.zza.equals(zzflVar2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzcz) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzE();
        }
        return 0;
    }

    public final zzdb zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                return this.zzc;
            }
            if (this.zza == null) {
                this.zzc = zzdb.zzb;
            } else {
                this.zzc = this.zza.zzC();
            }
            return this.zzc;
        }
    }

    public final zzfl zzc(zzfl zzflVar) {
        zzfl zzflVar2 = this.zza;
        this.zzc = null;
        this.zza = zzflVar;
        return zzflVar2;
    }

    protected final void zzd(zzfl zzflVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza == null) {
                try {
                    this.zza = zzflVar;
                    this.zzc = zzdb.zzb;
                } catch (zzen unused) {
                    this.zza = zzflVar;
                    this.zzc = zzdb.zzb;
                }
            }
        }
    }
}
