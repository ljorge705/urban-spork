package com.google.android.gms.internal.mlkit_vision_face;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzbc extends AbstractCollection {
    final /* synthetic */ zzbd zza;

    zzbc(zzbd zzbdVar) {
        this.zza = zzbdVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        zzbd zzbdVar = this.zza;
        Map mapZzl = zzbdVar.zzl();
        return mapZzl != null ? mapZzl.values().iterator() : new zzax(zzbdVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.zza.size();
    }
}
