package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.GoogleMap;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
final class zzj extends com.google.android.gms.maps.internal.zzao {
    final /* synthetic */ GoogleMap.OnMapLoadedCallback zza;

    zzj(GoogleMap googleMap, GoogleMap.OnMapLoadedCallback onMapLoadedCallback) {
        this.zza = onMapLoadedCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzap
    public final void zzb() throws RemoteException {
        this.zza.onMapLoaded();
    }
}
