package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzfz implements Iterator {
    private final ArrayDeque zza;
    private zzcy zzb;

    /* synthetic */ zzfz(zzdb zzdbVar, zzfx zzfxVar) {
        if (!(zzdbVar instanceof zzga)) {
            this.zza = null;
            this.zzb = (zzcy) zzdbVar;
            return;
        }
        zzga zzgaVar = (zzga) zzdbVar;
        ArrayDeque arrayDeque = new ArrayDeque(zzgaVar.zzf());
        this.zza = arrayDeque;
        arrayDeque.push(zzgaVar);
        this.zzb = zzb(zzgaVar.zzd);
    }

    private final zzcy zzb(zzdb zzdbVar) {
        while (zzdbVar instanceof zzga) {
            zzga zzgaVar = (zzga) zzdbVar;
            this.zza.push(zzgaVar);
            zzdbVar = zzgaVar.zzd;
        }
        return (zzcy) zzdbVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb != null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final zzcy next() {
        zzcy zzcyVarZzb;
        zzcy zzcyVar = this.zzb;
        if (zzcyVar == null) {
            throw new NoSuchElementException();
        }
        do {
            ArrayDeque arrayDeque = this.zza;
            zzcyVarZzb = null;
            if (arrayDeque == null || arrayDeque.isEmpty()) {
                break;
            }
            zzcyVarZzb = zzb(((zzga) this.zza.pop()).zze);
        } while (zzcyVarZzb.zzd() == 0);
        this.zzb = zzcyVarZzb;
        return zzcyVar;
    }
}
