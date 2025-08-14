package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public abstract class zzbp extends zzbh implements Set {

    @CheckForNull
    private transient zzbm zza;

    zzbp() {
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzcn.zza(this);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbh, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: zzd */
    public abstract zzcq iterator();

    public final zzbm zzf() {
        zzbm zzbmVar = this.zza;
        if (zzbmVar != null) {
            return zzbmVar;
        }
        zzbm zzbmVarZzg = zzg();
        this.zza = zzbmVarZzg;
        return zzbmVarZzg;
    }

    zzbm zzg() {
        return zzbm.zzh(toArray());
    }
}
