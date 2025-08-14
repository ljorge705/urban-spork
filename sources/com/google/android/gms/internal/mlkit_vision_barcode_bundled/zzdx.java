package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public class zzdx extends zzdw implements zzfm {
    protected zzdx(zzdy zzdyVar) {
        super(zzdyVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdw, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfk
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final zzdy zzm() {
        if (this.zzb) {
            return (zzdy) this.zza;
        }
        ((zzdy) this.zza).zza.zzg();
        return (zzdy) super.zzm();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdw
    protected final void zzo() {
        super.zzo();
        zzdy zzdyVar = (zzdy) this.zza;
        zzdyVar.zza = zzdyVar.zza.clone();
    }
}
