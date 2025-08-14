package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdw;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes2.dex */
public final class zzad extends zzdw implements zzfm {
    private zzad() {
        super(zzae.zza);
    }

    public final zzad zza(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzae.zze((zzae) this.zza, i);
        return this;
    }

    public final zzad zzb(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzae.zzf((zzae) this.zza, i);
        return this;
    }

    /* synthetic */ zzad(zza zzaVar) {
        super(zzae.zza);
    }
}
