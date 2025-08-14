package com.google.android.gms.internal.mlkit_vision_face;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzay extends AbstractSet {
    final /* synthetic */ zzbd zza;

    zzay(zzbd zzbdVar) {
        this.zza = zzbdVar;
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
            if (iZzv != -1 && zzx.zza(zzbd.zzj(this.zza, iZzv), entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        zzbd zzbdVar = this.zza;
        Map mapZzl = zzbdVar.zzl();
        return mapZzl != null ? mapZzl.entrySet().iterator() : new zzaw(zzbdVar);
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
        zzbd zzbdVar = this.zza;
        if (zzbdVar.zzq()) {
            return false;
        }
        int iZzu = zzbdVar.zzu();
        int iZzb = zzbe.zzb(entry.getKey(), entry.getValue(), iZzu, zzbd.zzk(this.zza), this.zza.zzz(), this.zza.zzA(), this.zza.zzB());
        if (iZzb == -1) {
            return false;
        }
        this.zza.zzp(iZzb, iZzu);
        zzbd.zzb(this.zza);
        this.zza.zzn();
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }
}
