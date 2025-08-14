package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzax extends AbstractSet {
    final /* synthetic */ zzbc zza;

    zzax(zzbc zzbcVar) {
        this.zza = zzbcVar;
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
            if (iZzv != -1 && zzw.zza(zzbc.zzj(this.zza, iZzv), entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        zzbc zzbcVar = this.zza;
        Map mapZzl = zzbcVar.zzl();
        return mapZzl != null ? mapZzl.entrySet().iterator() : new zzav(zzbcVar);
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
        zzbc zzbcVar = this.zza;
        if (zzbcVar.zzq()) {
            return false;
        }
        int iZzu = zzbcVar.zzu();
        int iZzb = zzbd.zzb(entry.getKey(), entry.getValue(), iZzu, zzbc.zzk(this.zza), this.zza.zzz(), this.zza.zzA(), this.zza.zzB());
        if (iZzb == -1) {
            return false;
        }
        this.zza.zzp(iZzb, iZzu);
        zzbc.zzb(this.zza);
        this.zza.zzn();
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }
}
