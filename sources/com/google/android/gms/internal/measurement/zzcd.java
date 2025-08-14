package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzcd extends zzbm implements zzcf {
    zzcd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IBundleReceiver");
    }

    @Override // com.google.android.gms.internal.measurement.zzcf
    public final void zze(Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        zzbo.zzd(parcelZza, bundle);
        zzc(1, parcelZza);
    }
}
