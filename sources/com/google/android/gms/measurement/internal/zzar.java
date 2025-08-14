package com.google.android.gms.measurement.internal;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zzar implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzas zzb;

    zzar(zzas zzasVar) {
        this.zzb = zzasVar;
        this.zza = zzasVar.zza.keySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    @Override // java.util.Iterator
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final String next() {
        return (String) this.zza.next();
    }
}
