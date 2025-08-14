package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zzjw implements Runnable {
    final /* synthetic */ zzjy zza;

    zzjw(zzjy zzjyVar) {
        this.zza = zzjyVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjz zzjzVar = this.zza.zza;
        Context contextZzaw = zzjzVar.zzt.zzaw();
        this.zza.zza.zzt.zzay();
        zzjz.zzo(zzjzVar, new ComponentName(contextZzaw, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
