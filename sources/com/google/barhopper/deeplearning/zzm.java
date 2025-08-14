package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdw;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzm extends zzdw implements zzfm {
    private zzm() {
        super(zzn.zza);
    }

    public final zzm zza(zzdb zzdbVar) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzn.zzd((zzn) this.zza, zzdbVar);
        return this;
    }

    public final zzm zzb(zzdb zzdbVar) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzn.zzc((zzn) this.zza, zzdbVar);
        return this;
    }

    /* synthetic */ zzm(zzl zzlVar) {
        super(zzn.zza);
    }
}
