package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public abstract class zzcf extends zzbx implements Set {

    @CheckForNull
    private transient zzcc zza;

    zzcf() {
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
        return zzcy.zza(this);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbx, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: zzd */
    public abstract zzda iterator();

    public final zzcc zzf() {
        zzcc zzccVar = this.zza;
        if (zzccVar != null) {
            return zzccVar;
        }
        zzcc zzccVarZzg = zzg();
        this.zza = zzccVarZzg;
        return zzccVarZzg;
    }

    zzcc zzg() {
        return zzcc.zzg(toArray());
    }
}
