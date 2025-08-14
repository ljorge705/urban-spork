package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
abstract class zzbg implements zzcp {

    @CheckForNull
    private transient Set zza;

    @CheckForNull
    private transient Map zzb;

    zzbg() {
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzcp) {
            return zzp().equals(((zzcp) obj).zzp());
        }
        return false;
    }

    public final int hashCode() {
        return zzp().hashCode();
    }

    public final String toString() {
        return ((zzaw) zzp()).zza.toString();
    }

    abstract Map zzk();

    abstract Set zzl();

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcp
    public boolean zzo(Object obj, Object obj2) {
        throw null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcp
    public final Map zzp() {
        Map map = this.zzb;
        if (map != null) {
            return map;
        }
        Map mapZzk = zzk();
        this.zzb = mapZzk;
        return mapZzk;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcp
    public final Set zzq() {
        Set set = this.zza;
        if (set != null) {
            return set;
        }
        Set setZzl = zzl();
        this.zza = setZzl;
        return setZzl;
    }
}
