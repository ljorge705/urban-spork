package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.NoSuchElementException;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzcs extends zzcv {
    final /* synthetic */ zzdb zza;
    private int zzb = 0;
    private final int zzc;

    zzcs(zzdb zzdbVar) {
        this.zza = zzdbVar;
        this.zzc = zzdbVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcx
    public final byte zza() {
        int i = this.zzb;
        if (i >= this.zzc) {
            throw new NoSuchElementException();
        }
        this.zzb = i + 1;
        return this.zza.zzb(i);
    }
}
