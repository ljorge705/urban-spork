package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzeu extends zzcm implements RandomAccess, zzev {
    public static final zzev zza;
    private static final zzeu zzb;
    private final List zzc;

    static {
        zzeu zzeuVar = new zzeu(10);
        zzb = zzeuVar;
        zzeuVar.zzb();
        zza = zzeuVar;
    }

    public zzeu() {
        this(10);
    }

    private static String zzi(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzdb ? ((zzdb) obj).zzu(zzel.zzb) : zzel.zzh((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcm, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zza();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcm, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        zza();
        if (collection instanceof zzev) {
            collection = ((zzev) collection).zzh();
        }
        boolean zAddAll = this.zzc.addAll(i, collection);
        this.modCount++;
        return zAddAll;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcm, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zza();
        this.zzc.clear();
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcm, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        Object objRemove = this.zzc.remove(i);
        this.modCount++;
        return zzi(objRemove);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcm, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zza();
        return zzi(this.zzc.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzek
    public final /* bridge */ /* synthetic */ zzek zzd(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzc);
        return new zzeu(arrayList);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzev
    public final zzev zze() {
        return zzc() ? new zzgu(this) : this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzev
    public final Object zzf(int i) {
        return this.zzc.get(i);
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: zzg, reason: merged with bridge method [inline-methods] */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzdb) {
            zzdb zzdbVar = (zzdb) obj;
            String strZzu = zzdbVar.zzu(zzel.zzb);
            if (zzdbVar.zzn()) {
                this.zzc.set(i, strZzu);
            }
            return strZzu;
        }
        byte[] bArr = (byte[]) obj;
        String strZzh = zzel.zzh(bArr);
        if (zzel.zzj(bArr)) {
            this.zzc.set(i, strZzh);
        }
        return strZzh;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzev
    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    public zzeu(int i) {
        this.zzc = new ArrayList(i);
    }

    private zzeu(ArrayList arrayList) {
        this.zzc = arrayList;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcm, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
