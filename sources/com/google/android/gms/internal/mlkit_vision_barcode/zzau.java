package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzau extends zzck {
    final /* synthetic */ zzaw zza;

    zzau(zzaw zzawVar) {
        this.zza = zzawVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzck, java.util.AbstractCollection, java.util.Collection, java.util.Set
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
        return new zzav(this.zza);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzck, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        if (!contains(obj)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        entry.getClass();
        zzbe.zzm(this.zza.zzb, entry.getKey());
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzck
    final Map zza() {
        return this.zza;
    }
}
