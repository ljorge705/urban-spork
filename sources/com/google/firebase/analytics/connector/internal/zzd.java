package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzhc;

/* compiled from: com.google.android.gms:play-services-measurement-api@@21.3.0 */
/* loaded from: classes5.dex */
final class zzd implements AppMeasurementSdk.OnEventListener {
    final /* synthetic */ zze zza;

    public zzd(zze zzeVar) {
        this.zza = zzeVar;
    }

    @Override // com.google.android.gms.measurement.api.AppMeasurementSdk.OnEventListener, com.google.android.gms.measurement.internal.zzhg
    public final void onEvent(String str, String str2, Bundle bundle, long j) {
        if (this.zza.zza.contains(str2)) {
            Bundle bundle2 = new Bundle();
            int i = zzc.zza;
            String strZza = zzhc.zza(str2);
            if (strZza != null) {
                str2 = strZza;
            }
            bundle2.putString("events", str2);
            this.zza.zzb.onMessageTriggered(2, bundle2);
        }
    }
}
