package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
abstract class zzay implements Iterator {
    int zzb;
    int zzc;
    int zzd = -1;
    final /* synthetic */ zzbc zze;

    /* synthetic */ zzay(zzbc zzbcVar, zzau zzauVar) {
        this.zze = zzbcVar;
        this.zzb = zzbcVar.zzf;
        this.zzc = zzbcVar.zze();
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
        zzaa.zzd(this.zzd >= 0, "no calls to next() since the last call to remove()");
        this.zzb += 32;
        zzbc zzbcVar = this.zze;
        zzbcVar.remove(zzbc.zzg(zzbcVar, this.zzd));
        this.zzc--;
        this.zzd = -1;
    }

    abstract Object zza(int i);
}
