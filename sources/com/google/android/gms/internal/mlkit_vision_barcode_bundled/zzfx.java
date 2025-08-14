package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.NoSuchElementException;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzfx extends zzcv {
    final zzfz zza;
    zzcx zzb = zzb();
    final /* synthetic */ zzga zzc;

    zzfx(zzga zzgaVar) {
        this.zzc = zzgaVar;
        this.zza = new zzfz(zzgaVar, null);
    }

    private final zzcx zzb() {
        zzfz zzfzVar = this.zza;
        if (zzfzVar.hasNext()) {
            return zzfzVar.next().iterator();
        }
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb != null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcx
    public final byte zza() {
        zzcx zzcxVar = this.zzb;
        if (zzcxVar == null) {
            throw new NoSuchElementException();
        }
        byte bZza = zzcxVar.zza();
        if (!this.zzb.hasNext()) {
            this.zzb = zzb();
        }
        return bZza;
    }
}
