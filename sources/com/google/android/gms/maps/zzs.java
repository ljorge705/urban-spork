package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MapCapabilities;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
final class zzs extends com.google.android.gms.maps.internal.zzak {
    final /* synthetic */ GoogleMap.OnMapCapabilitiesChangedListener zza;

    zzs(GoogleMap googleMap, GoogleMap.OnMapCapabilitiesChangedListener onMapCapabilitiesChangedListener) {
        this.zza = onMapCapabilitiesChangedListener;
    }

    @Override // com.google.android.gms.maps.internal.zzal
    public final void zzb(com.google.android.gms.internal.maps.zzaa zzaaVar) {
        this.zza.onMapCapabilitiesChanged(new MapCapabilities(zzaaVar));
    }
}
