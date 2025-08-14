package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
abstract class zzbo implements Iterator {
    int zzb;
    int zzc;
    int zzd = -1;
    final /* synthetic */ zzbs zze;

    /* synthetic */ zzbo(zzbs zzbsVar, zzbk zzbkVar) {
        this.zze = zzbsVar;
        this.zzb = zzbsVar.zzf;
        this.zzc = zzbsVar.zze();
    }

    private final void zzb() {
        if (this.zze.zzf != this.zzb) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzc >= 0;
    }

    @Override // java.util.Iterator
    public final Object next() {
        zzb();
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i = this.zzc;
        this.zzd = i;
        Object objZza = zza(i);
        this.zzc = this.zze.zzf(this.zzc);
        return objZza;
    }

    @Override // java.util.Iterator
    public final void remove() {
        zzb();
        zzaq.zzd(this.zzd >= 0, "no calls to next() since the last call to remove()");
        this.zzb += 32;
        zzbs zzbsVar = this.zze;
        zzbsVar.remove(zzbs.zzg(zzbsVar, this.zzd));
        this.zzc--;
        this.zzd = -1;
    }

    abstract Object zza(int i);
}
