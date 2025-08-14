package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzbj extends zzaw {
    @Override // com.google.android.gms.internal.measurement.zzaw
    public final zzap zza(String str, zzg zzgVar, List list) {
        if (str == null || str.isEmpty() || !zzgVar.zzh(str)) {
            throw new IllegalArgumentException(String.format("Command not found: %s", str));
        }
        zzap zzapVarZzd = zzgVar.zzd(str);
        if (zzapVarZzd instanceof zzai) {
            return ((zzai) zzapVarZzd).zza(zzgVar, list);
        }
        throw new IllegalArgumentException(String.format("Function %s is not defined", str));
    }
}
