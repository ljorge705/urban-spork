package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
public final class MapCapabilities {
    private final com.google.android.gms.internal.maps.zzaa zza;

    public MapCapabilities(com.google.android.gms.internal.maps.zzaa zzaaVar) {
        this.zza = (com.google.android.gms.internal.maps.zzaa) Preconditions.checkNotNull(zzaaVar);
    }

    public boolean isAdvancedMarkersAvailable() {
        try {
            return this.zza.zzd();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
