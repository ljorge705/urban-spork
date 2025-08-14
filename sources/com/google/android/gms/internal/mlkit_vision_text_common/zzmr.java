package com.google.android.gms.internal.mlkit_vision_text_common;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zzmr implements zzmw {
    final List zza;

    public zzmr(Context context, zzmq zzmqVar) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zzmqVar.zzc()) {
            arrayList.add(new zznf(context, zzmqVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzmw
    public final void zza(zzna zznaVar) {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            ((zzmw) it.next()).zza(zznaVar);
        }
    }
}
