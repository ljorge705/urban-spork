package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
final class zzaa extends com.google.android.gms.maps.internal.zzaq {
    final /* synthetic */ GoogleMap.OnMapLongClickListener zza;

    zzaa(GoogleMap googleMap, GoogleMap.OnMapLongClickListener onMapLongClickListener) {
        this.zza = onMapLongClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzar
    public final void zzb(LatLng latLng) {
        this.zza.onMapLongClick(latLng);
    }
}
