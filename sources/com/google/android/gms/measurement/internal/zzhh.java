package com.google.android.gms.measurement.internal;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zzhh implements zzen {
    final /* synthetic */ zzgd zza;

    zzhh(zzhi zzhiVar, zzgd zzgdVar) {
        this.zza = zzgdVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzen
    public final boolean zza() {
        return this.zza.zzL() && Log.isLoggable(this.zza.zzaA().zzr(), 3);
    }
}
