package com.google.android.gms.internal.mlkit_vision_barcode;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public final class zznf implements zznl {
    final List zza;

    public zznf(Context context, zzne zzneVar) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zzneVar.zzc()) {
            arrayList.add(new zznu(context, zzneVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zznl
    public final void zza(zznp zznpVar) {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            ((zznl) it.next()).zza(zznpVar);
        }
    }
}
