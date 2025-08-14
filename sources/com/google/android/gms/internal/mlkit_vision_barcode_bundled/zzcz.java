package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
class zzcz extends zzcy {
    protected final byte[] zza;

    zzcz(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdb) || zzd() != ((zzdb) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzcz)) {
            return obj.equals(this);
        }
        zzcz zzczVar = (zzcz) obj;
        int iZzp = zzp();
        int iZzp2 = zzczVar.zzp();
        if (iZzp == 0 || iZzp2 == 0 || iZzp == iZzp2) {
            return zzg(zzczVar, 0, zzd());
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    byte zzb(int i) {
        return this.zza[i];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    protected void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, i, bArr, i2, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcy
    final boolean zzg(zzdb zzdbVar, int i, int i2) {
        if (i2 > zzdbVar.zzd()) {
            int iZzd = zzd();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(iZzd);
            throw new IllegalArgumentException(sb.toString());
        }
        int i3 = i + i2;
        if (i3 > zzdbVar.zzd()) {
            int iZzd2 = zzdbVar.zzd();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(iZzd2);
            throw new IllegalArgumentException(sb2.toString());
        }
        if (!(zzdbVar instanceof zzcz)) {
            return zzdbVar.zzk(i, i3).equals(zzk(0, i2));
        }
        zzcz zzczVar = (zzcz) zzdbVar;
        byte[] bArr = this.zza;
        byte[] bArr2 = zzczVar.zza;
        int iZzc = zzc() + i2;
        int iZzc2 = zzc();
        int iZzc3 = zzczVar.zzc() + i;
        while (iZzc2 < iZzc) {
            if (bArr[iZzc2] != bArr2[iZzc3]) {
                return false;
            }
            iZzc2++;
            iZzc3++;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    protected final int zzi(int i, int i2, int i3) {
        return zzel.zzd(i, this.zza, zzc() + i2, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    protected final int zzj(int i, int i2, int i3) {
        int iZzc = zzc() + i2;
        return zzhe.zzf(i, this.zza, iZzc, i3 + iZzc);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final zzdb zzk(int i, int i2) {
        int iZzo = zzo(i, i2, zzd());
        return iZzo == 0 ? zzdb.zzb : new zzcw(this.zza, zzc() + i, iZzo);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    protected final String zzl(Charset charset) {
        return new String(this.zza, zzc(), zzd(), charset);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    final void zzm(zzcr zzcrVar) throws IOException {
        ((zzdg) zzcrVar).zzc(this.zza, zzc(), zzd());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final boolean zzn() {
        int iZzc = zzc();
        return zzhe.zzi(this.zza, iZzc, zzd() + iZzc);
    }
}
