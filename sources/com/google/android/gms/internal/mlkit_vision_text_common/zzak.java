package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
class zzak implements Iterator {
    final Iterator zza;
    final Collection zzb;
    final /* synthetic */ zzal zzc;

    zzak(zzal zzalVar) {
        this.zzc = zzalVar;
        this.zzb = zzalVar.zzb;
        Collection collection = zzalVar.zzb;
        this.zza = collection instanceof List ? ((List) collection).listIterator() : collection.iterator();
    }

    zzak(zzal zzalVar, Iterator it) {
        this.zzc = zzalVar;
        this.zzb = zzalVar.zzb;
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
        zzao.zze(this.zzc.zze);
        this.zzc.zzc();
    }

    final void zza() {
        this.zzc.zzb();
        if (this.zzc.zzb != this.zzb) {
            throw new ConcurrentModificationException();
        }
    }
}
