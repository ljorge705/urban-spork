package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@21.3.0 */
/* loaded from: classes3.dex */
final class zzdq extends zzdu {
    final /* synthetic */ zzdw zza;
    final /* synthetic */ zzef zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdq(zzef zzefVar, zzdw zzdwVar) {
        super(zzefVar, true);
        this.zzb = zzefVar;
        this.zza = zzdwVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc) Preconditions.checkNotNull(this.zzb.zzj)).registerOnMeasurementEventListener(this.zza);
    }
}
