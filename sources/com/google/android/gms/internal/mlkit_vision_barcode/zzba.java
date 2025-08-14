package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
class zzba implements Iterator {
    final Iterator zza;
    final Collection zzb;
    final /* synthetic */ zzbb zzc;

    zzba(zzbb zzbbVar) {
        this.zzc = zzbbVar;
        this.zzb = zzbbVar.zzb;
        Collection collection = zzbbVar.zzb;
        this.zza = collection instanceof List ? ((List) collection).listIterator() : collection.iterator();
    }

    zzba(zzbb zzbbVar, Iterator it) {
        this.zzc = zzbbVar;
        this.zzb = zzbbVar.zzb;
        this.zza = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        zza();
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        zza();
        return this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.zza.remove();
        zzbe.zze(this.zzc.zze);
        this.zzc.zzc();
    }

    final void zza() {
        this.zzc.zzb();
        if (this.zzc.zzb != this.zzb) {
            throw new ConcurrentModificationException();
        }
    }
}
