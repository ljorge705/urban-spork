package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbi;
import com.google.android.gms.maps.model.Polyline;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
final class zzp extends zzbi {
    final /* synthetic */ GoogleMap.OnPolylineClickListener zza;

    zzp(GoogleMap googleMap, GoogleMap.OnPolylineClickListener onPolylineClickListener) {
        this.zza = onPolylineClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbj
    public final void zzb(com.google.android.gms.internal.maps.zzaj zzajVar) {
        this.zza.onPolylineClick(new Polyline(zzajVar));
    }
}
