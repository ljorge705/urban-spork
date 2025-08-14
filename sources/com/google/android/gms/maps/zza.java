package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
final class zza extends com.google.android.gms.maps.internal.zzau {
    final /* synthetic */ GoogleMap.OnMarkerClickListener zza;

    zza(GoogleMap googleMap, GoogleMap.OnMarkerClickListener onMarkerClickListener) {
        this.zza = onMarkerClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzav
    public final boolean zzb(com.google.android.gms.internal.maps.zzad zzadVar) {
        return this.zza.onMarkerClick(new Marker(zzadVar));
    }
}
