package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdw;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzh extends zzdw implements zzfm {
    private zzh() {
        super(zzi.zza);
    }

    public final zzh zza(zze zzeVar) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzi.zzc((zzi) this.zza, (zzf) zzeVar.zzl());
        return this;
    }

    public final zzh zzb(zzdb zzdbVar) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzi.zzd((zzi) this.zza, zzdbVar);
        return this;
    }

    /* synthetic */ zzh(zzg zzgVar) {
        super(zzi.zza);
    }
}
