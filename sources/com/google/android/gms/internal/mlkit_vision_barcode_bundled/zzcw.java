package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzcw extends zzcz {
    private final int zzc;
    private final int zzd;

    zzcw(byte[] bArr, int i, int i2) {
        super(bArr);
        zzo(i, i + i2, bArr.length);
        this.zzc = i;
        this.zzd = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcz, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final byte zza(int i) {
        zzw(i, this.zzd);
        return this.zza[this.zzc + i];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcz, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    final byte zzb(int i) {
        return this.zza[this.zzc + i];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcz
    protected final int zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcz, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final int zzd() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcz, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    protected final void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, this.zzc + i, bArr, i2, i3);
    }
}
