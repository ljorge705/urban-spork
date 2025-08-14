package com.google.android.gms.internal.mlkit_vision_text_common;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
abstract class zzao extends zzaq implements Serializable {
    private transient Map zza;
    private transient int zzb;

    protected zzao(Map map) {
        if (!map.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.zza = map;
    }

    static /* synthetic */ int zzd(zzao zzaoVar) {
        int i = zzaoVar.zzb;
        zzaoVar.zzb = i + 1;
        return i;
    }

    static /* synthetic */ int zze(zzao zzaoVar) {
        int i = zzaoVar.zzb;
        zzaoVar.zzb = i - 1;
        return i;
    }

    static /* synthetic */ int zzf(zzao zzaoVar, int i) {
        int i2 = zzaoVar.zzb + i;
        zzaoVar.zzb = i2;
        return i2;
    }

    static /* synthetic */ int zzg(zzao zzaoVar, int i) {
        int i2 = zzaoVar.zzb - i;
        zzaoVar.zzb = i2;
        return i2;
    }

    static /* synthetic */ void zzm(zzao zzaoVar, Object obj) {
        Object objRemove;
        Map map = zzaoVar.zza;
        map.getClass();
        try {
            objRemove = map.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            objRemove = null;
        }
        Collection collection = (Collection) objRemove;
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            zzaoVar.zzb -= size;
        }
    }

    abstract Collection zza();

    Collection zzb(Object obj, Collection collection) {
        throw null;
    }

    public final Collection zzh(Object obj) {
        Collection collectionZza = (Collection) this.zza.get(obj);
        if (collectionZza == null) {
            collectionZza = zza();
        }
        return zzb(obj, collectionZza);
    }

    final List zzi(Object obj, List list, @CheckForNull zzal zzalVar) {
        return list instanceof RandomAccess ? new zzaj(this, obj, list, zzalVar) : new zzan(this, obj, list, zzalVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzaq
    final Map zzk() {
        return new zzag(this, this.zza);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzaq
    final Set zzl() {
        return new zzai(this, this.zza);
    }

    public final void zzn() {
        Iterator it = this.zza.values().iterator();
        while (it.hasNext()) {
            ((Collection) it.next()).clear();
        }
        this.zza.clear();
        this.zzb = 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzaq, com.google.android.gms.internal.mlkit_vision_text_common.zzce
    public final boolean zzo(Object obj, Object obj2) {
        Collection collection = (Collection) this.zza.get(obj);
        if (collection != null) {
            if (!collection.add(obj2)) {
                return false;
            }
            this.zzb++;
            return true;
        }
        Collection collectionZza = zza();
        if (!collectionZza.add(obj2)) {
            throw new AssertionError("New Collection violated the Collection spec");
        }
        this.zzb++;
        this.zza.put(obj, collectionZza);
        return true;
    }
}
