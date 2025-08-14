package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@21.3.0 */
/* loaded from: classes3.dex */
final class zzdh extends zzdu {
    final /* synthetic */ Bundle zza;
    final /* synthetic */ zzbz zzb;
    final /* synthetic */ zzef zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdh(zzef zzefVar, Bundle bundle, zzbz zzbzVar) {
        super(zzefVar, true);
        this.zzc = zzefVar;
        this.zza = bundle;
        this.zzb = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc) Preconditions.checkNotNull(this.zzc.zzj)).performAction(this.zza, this.zzb, this.zzh);
    }

    @Override // com.google.android.gms.internal.measurement.zzdu
    protected final void zzb() {
        this.zzb.zze(null);
    }
}
