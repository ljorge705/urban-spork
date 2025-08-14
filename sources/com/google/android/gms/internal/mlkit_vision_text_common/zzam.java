package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.List;
import java.util.ListIterator;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzam extends zzak implements ListIterator {
    final /* synthetic */ zzan zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzam(zzan zzanVar) {
        super(zzanVar);
        this.zzd = zzanVar;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        boolean zIsEmpty = this.zzd.isEmpty();
        zza();
        ((ListIterator) this.zza).add(obj);
        zzao.zzd(this.zzd.zzf);
        if (zIsEmpty) {
            this.zzd.zza();
        }
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        zza();
        return ((ListIterator) this.zza).hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        zza();
        return ((ListIterator) this.zza).nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        zza();
        return ((ListIterator) this.zza).previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        zza();
        return ((ListIterator) this.zza).previousIndex();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        zza();
        ((ListIterator) this.zza).set(obj);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzam(zzan zzanVar, int i) {
        super(zzanVar, ((List) zzanVar.zzb).listIterator(i));
        this.zzd = zzanVar;
    }
}
