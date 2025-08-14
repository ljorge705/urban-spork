package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzdz implements zzdr {
    final int zza;
    final zzhf zzb;
    final boolean zzc = false;

    zzdz(zzef zzefVar, int i, zzhf zzhfVar, boolean z, boolean z2) {
        this.zza = i;
        this.zzb = zzhfVar;
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return this.zza - ((zzdz) obj).zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdr
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdr
    public final zzfk zzb(zzfk zzfkVar, zzfl zzflVar) {
        ((zzdw) zzfkVar).zzi((zzec) zzflVar);
        return zzfkVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdr
    public final zzfq zzc(zzfq zzfqVar, zzfq zzfqVar2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdr
    public final zzhf zzd() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdr
    public final zzhg zze() {
        return this.zzb.zza();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdr
    public final boolean zzf() {
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdr
    public final boolean zzg() {
        return false;
    }
}
