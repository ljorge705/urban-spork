package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import io.sentry.protocol.Message;

/* compiled from: com.google.android.gms:play-services-measurement-api@@21.3.0 */
/* loaded from: classes5.dex */
final class zzf implements AppMeasurementSdk.OnEventListener {
    final /* synthetic */ zzg zza;

    public zzf(zzg zzgVar) {
        this.zza = zzgVar;
    }

    @Override // com.google.android.gms.measurement.api.AppMeasurementSdk.OnEventListener, com.google.android.gms.measurement.internal.zzhg
    public final void onEvent(String str, String str2, Bundle bundle, long j) {
        if (str == null || !zzc.zzc(str2)) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("name", str2);
        bundle2.putLong("timestampInMillis", j);
        bundle2.putBundle(Message.JsonKeys.PARAMS, bundle);
        this.zza.zza.onMessageTriggered(3, bundle2);
    }
}
