package com.google.android.gms.internal.measurement;

import java.io.Serializable;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzir {
    public static zzim zza(zzim zzimVar) {
        return ((zzimVar instanceof zzip) || (zzimVar instanceof zzin)) ? zzimVar : zzimVar instanceof Serializable ? new zzin(zzimVar) : new zzip(zzimVar);
    }

    public static zzim zzb(Object obj) {
        return new zziq(obj);
    }
}
