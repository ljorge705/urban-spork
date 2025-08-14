package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzbp extends zzbm implements zzbr {
    zzbp(IBinder iBinder) {
        super(iBinder, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
    }

    @Override // com.google.android.gms.internal.measurement.zzbr
    public final Bundle zzd(Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        zzbo.zzd(parcelZza, bundle);
        Parcel parcelZzb = zzb(1, parcelZza);
        Bundle bundle2 = (Bundle) zzbo.zza(parcelZzb, Bundle.CREATOR);
        parcelZzb.recycle();
        return bundle2;
    }
}
