package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzgu extends AbstractList implements RandomAccess, zzev {
    private final zzev zza;

    public zzgu(zzev zzevVar) {
        this.zza = zzevVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzeu) this.zza).get(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new zzgt(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new zzgs(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzev
    public final zzev zze() {
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzev
    public final Object zzf(int i) {
        return this.zza.zzf(i);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzev
    public final List zzh() {
        return this.zza.zzh();
    }
}
