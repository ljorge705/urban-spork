package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.GroundOverlay;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
final class zzm extends com.google.android.gms.maps.internal.zzy {
    final /* synthetic */ GoogleMap.OnGroundOverlayClickListener zza;

    zzm(GoogleMap googleMap, GoogleMap.OnGroundOverlayClickListener onGroundOverlayClickListener) {
        this.zza = onGroundOverlayClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzz
    public final void zzb(com.google.android.gms.internal.maps.zzr zzrVar) {
        this.zza.onGroundOverlayClick(new GroundOverlay(zzrVar));
    }
}
