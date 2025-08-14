package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* loaded from: classes3.dex */
final class zzlu extends zzlw {
    private zzlu() {
        super(null);
    }

    /* synthetic */ zzlu(zzlt zzltVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzlw
    final void zza(Object obj, long j) {
        ((zzli) zznu.zzf(obj, j)).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzlw
    final void zzb(Object obj, Object obj2, long j) {
        zzli zzliVarZzd = (zzli) zznu.zzf(obj, j);
        zzli zzliVar = (zzli) zznu.zzf(obj2, j);
        int size = zzliVarZzd.size();
        int size2 = zzliVar.size();
        if (size > 0 && size2 > 0) {
            if (!zzliVarZzd.zzc()) {
                zzliVarZzd = zzliVarZzd.zzd(size2 + size);
            }
            zzliVarZzd.addAll(zzliVar);
        }
        if (size > 0) {
            zzliVar = zzliVarZzd;
        }
        zznu.zzs(obj, j, zzliVar);
    }
}
