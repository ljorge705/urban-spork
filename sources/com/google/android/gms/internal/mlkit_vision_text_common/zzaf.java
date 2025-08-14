package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzaf implements Iterator {
    final Iterator zza;

    @CheckForNull
    Collection zzb;
    final /* synthetic */ zzag zzc;

    zzaf(zzag zzagVar) {
        this.zzc = zzagVar;
        this.zza = zzagVar.zza.entrySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.zza.next();
        this.zzb = (Collection) entry.getValue();
        zzag zzagVar = this.zzc;
        Object key = entry.getKey();
        return new zzbi(key, zzagVar.zzb.zzb(key, (Collection) entry.getValue()));
    }

    @Override // java.util.Iterator
    public final void remove() {
        zzaa.zzd(this.zzb != null, "no calls to next() since the last call to remove()");
        this.zza.remove();
        zzao.zzg(this.zzc.zzb, this.zzb.size());
        this.zzb.clear();
        this.zzb = null;
    }
}
