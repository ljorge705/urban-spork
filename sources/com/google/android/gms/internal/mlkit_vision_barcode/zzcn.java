package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
abstract class zzcn extends AbstractMap {

    @CheckForNull
    private transient Set zza;

    @CheckForNull
    private transient Set zzb;

    @CheckForNull
    private transient Collection zzc;

    zzcn() {
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
        zzcl zzclVar = new zzcl(this);
        this.zzb = zzclVar;
        return zzclVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection collection = this.zzc;
        if (collection != null) {
            return collection;
        }
        zzcm zzcmVar = new zzcm(this);
        this.zzc = zzcmVar;
        return zzcmVar;
    }

    abstract Set zza();
}
