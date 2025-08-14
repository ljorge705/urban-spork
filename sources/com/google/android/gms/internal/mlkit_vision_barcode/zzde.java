package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzde implements zzdj {
    private final int zza;
    private final zzdi zzb;

    zzde(int i, zzdi zzdiVar) {
        this.zza = i;
        this.zzb = zzdiVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzdj.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdj)) {
            return false;
        }
        zzdj zzdjVar = (zzdj) obj;
        return this.zza == zzdjVar.zza() && this.zzb.equals(zzdjVar.zzb());
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.zza ^ 14552422) + (this.zzb.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.zza + "intEncoding=" + this.zzb + ')';
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzdj
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzdj
    public final zzdi zzb() {
        return this.zzb;
    }
}
