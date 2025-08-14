package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzae extends zzbz {
    final /* synthetic */ zzag zza;

    zzae(zzag zzagVar) {
        this.zza = zzagVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbz, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        Set setEntrySet = this.zza.zza.entrySet();
        setEntrySet.getClass();
        try {
            return setEntrySet.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new zzaf(this.zza);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbz, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        if (!contains(obj)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        entry.getClass();
        zzao.zzm(this.zza.zzb, entry.getKey());
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbz
    final Map zza() {
        return this.zza;
    }
}
