package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzbb extends AbstractCollection {
    final /* synthetic */ zzbc zza;

    zzbb(zzbc zzbcVar) {
        this.zza = zzbcVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        zzbc zzbcVar = this.zza;
        Map mapZzl = zzbcVar.zzl();
        return mapZzl != null ? mapZzl.values().iterator() : new zzaw(zzbcVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.zza.size();
    }
}
