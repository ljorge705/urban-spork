package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzfc implements zzfj {
    private final zzfj[] zza;

    zzfc(zzfj... zzfjVarArr) {
        this.zza = zzfjVarArr;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfj
    public final zzfi zzb(Class cls) {
        zzfj[] zzfjVarArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzfj zzfjVar = zzfjVarArr[i];
            if (zzfjVar.zzc(cls)) {
                return zzfjVar.zzb(cls);
            }
        }
        String strValueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(strValueOf.length() != 0 ? "No factory is available for message type: ".concat(strValueOf) : new String("No factory is available for message type: "));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfj
    public final boolean zzc(Class cls) {
        zzfj[] zzfjVarArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzfjVarArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
