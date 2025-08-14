package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzbn extends AbstractSet {
    final /* synthetic */ zzbs zza;

    zzbn(zzbs zzbsVar) {
        this.zza = zzbsVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        Map mapZzl = this.zza.zzl();
        if (mapZzl != null) {
            return mapZzl.entrySet().contains(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            int iZzv = this.zza.zzv(entry.getKey());
            if (iZzv != -1 && zzam.zza(zzbs.zzj(this.zza, iZzv), entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        zzbs zzbsVar = this.zza;
        Map mapZzl = zzbsVar.zzl();
        return mapZzl != null ? mapZzl.entrySet().iterator() : new zzbl(zzbsVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        Map mapZzl = this.zza.zzl();
        if (mapZzl != null) {
            return mapZzl.entrySet().remove(obj);
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        zzbs zzbsVar = this.zza;
        if (zzbsVar.zzq()) {
            return false;
        }
        int iZzu = zzbsVar.zzu();
        int iZzb = zzbt.zzb(entry.getKey(), entry.getValue(), iZzu, zzbs.zzk(this.zza), this.zza.zzz(), this.zza.zzA(), this.zza.zzB());
        if (iZzb == -1) {
            return false;
        }
        this.zza.zzp(iZzb, iZzu);
        zzbs.zzb(this.zza);
        this.zza.zzn();
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }
}
