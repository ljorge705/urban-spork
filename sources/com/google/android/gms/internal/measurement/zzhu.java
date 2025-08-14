package com.google.android.gms.internal.measurement;

import android.util.Log;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zzhu extends zzib {
    zzhu(zzhy zzhyVar, String str, Long l, boolean z) {
        super(zzhyVar, str, l, true, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzib
    @Nullable
    final /* synthetic */ Object zza(Object obj) {
        try {
            return Long.valueOf(Long.parseLong((String) obj));
        } catch (NumberFormatException unused) {
            Log.e("PhenotypeFlag", "Invalid long value for " + this.zzb + ": " + ((String) obj));
            return null;
        }
    }
}
