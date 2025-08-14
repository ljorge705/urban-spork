package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
abstract class zzcc extends AbstractMap {

    @CheckForNull
    private transient Set zza;

    @CheckForNull
    private transient Set zzb;

    @CheckForNull
    private transient Collection zzc;

    zzcc() {
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set set = this.zza;
        if (set != null) {
            return set;
        }
        Set setZza = zza();
        this.zza = setZza;
        return setZza;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set keySet() {
        Set set = this.zzb;
        if (set != null) {
            return set;
        }
        zzca zzcaVar = new zzca(this);
        this.zzb = zzcaVar;
        return zzcaVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection collection = this.zzc;
        if (collection != null) {
            return collection;
        }
        zzcb zzcbVar = new zzcb(this);
        this.zzc = zzcbVar;
        return zzcbVar;
    }

    abstract Set zza();
}
