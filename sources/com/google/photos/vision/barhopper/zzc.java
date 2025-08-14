package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzei;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzek;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;
import java.util.List;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes2.dex */
public final class zzc extends zzec implements zzfm {
    private static final zzc zza;
    private int zze;
    private int zzf;
    private int zzi;
    private zzr zzj;
    private zzy zzk;
    private zzci zzl;
    private zzag zzm;
    private zzao zzn;
    private zzaj zzo;
    private zzac zzp;
    private zzp zzq;
    private zzt zzr;
    private zzl zzs;
    private double zzy;
    private byte zzA = 2;
    private zzdb zzg = zzdb.zzb;
    private String zzh = "";
    private zzek zzt = zzO();
    private zzei zzu = zzN();
    private String zzv = "";
    private zzek zzw = zzO();
    private boolean zzx = true;
    private zzdb zzz = zzdb.zzb;

    static {
        zzc zzcVar = new zzc();
        zza = zzcVar;
        zzec.zzS(zzc.class, zzcVar);
    }

    private zzc() {
    }

    static /* synthetic */ void zzp(zzc zzcVar, int i, zzae zzaeVar) {
        zzaeVar.getClass();
        zzek zzekVar = zzcVar.zzt;
        if (!zzekVar.zzc()) {
            zzcVar.zzt = zzec.zzP(zzekVar);
        }
        zzcVar.zzt.set(i, zzaeVar);
    }

    public final int zzA() {
        int iZza = zzi.zza(this.zzi);
        if (iZza == 0) {
            return 1;
        }
        return iZza;
    }

    public final int zza() {
        return this.zzt.size();
    }

    public final zzci zzb() {
        zzci zzciVar = this.zzl;
        return zzciVar == null ? zzci.zzb() : zzciVar;
    }

    public final zzp zzd() {
        zzp zzpVar = this.zzq;
        return zzpVar == null ? zzp.zzd() : zzpVar;
    }

    public final zzr zze() {
        zzr zzrVar = this.zzj;
        return zzrVar == null ? zzr.zzc() : zzrVar;
    }

    public final zzt zzf() {
        zzt zztVar = this.zzr;
        return zztVar == null ? zzt.zzb() : zztVar;
    }

    public final zzy zzh() {
        zzy zzyVar = this.zzk;
        return zzyVar == null ? zzy.zzb() : zzyVar;
    }

    public final zzac zzi() {
        zzac zzacVar = this.zzp;
        return zzacVar == null ? zzac.zzd() : zzacVar;
    }

    public final zzag zzj() {
        zzag zzagVar = this.zzm;
        return zzagVar == null ? zzag.zzb() : zzagVar;
    }

    public final zzaj zzk() {
        zzaj zzajVar = this.zzo;
        return zzajVar == null ? zzaj.zzb() : zzajVar;
    }

    public final zzao zzl() {
        zzao zzaoVar = this.zzn;
        return zzaoVar == null ? zzao.zzb() : zzaoVar;
    }

    public final zzdb zzm() {
        return this.zzg;
    }

    public final String zzn() {
        return this.zzh;
    }

    public final List zzo() {
        return this.zzt;
    }

    public final boolean zzq() {
        return (this.zze & 2048) != 0;
    }

    public final boolean zzr() {
        return (this.zze & 16) != 0;
    }

    public final boolean zzs() {
        return (this.zze & 4096) != 0;
    }

    public final boolean zzt() {
        return (this.zze & 32) != 0;
    }

    public final boolean zzu() {
        return (this.zze & 1024) != 0;
    }

    public final boolean zzv() {
        return (this.zze & 64) != 0;
    }

    public final boolean zzw() {
        return (this.zze & 128) != 0;
    }

    public final boolean zzx() {
        return (this.zze & 512) != 0;
    }

    public final boolean zzy() {
        return (this.zze & 256) != 0;
    }

    public final int zzz() {
        int iZza = zzf.zza(this.zzf);
        if (iZza == 0) {
            return 1;
        }
        return iZza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzA);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0015\u0000\u0001\u0001\u0015\u0015\u0000\u0003\u000b\u0001ᔌ\u0000\u0002ᔊ\u0001\u0003ᔈ\u0002\u0004ᔌ\u0003\u0005ᐉ\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bᐉ\u0007\tᐉ\b\nᐉ\t\u000bЛ\fဈ\u000e\rЛ\u000eည\u0011\u000fᐉ\n\u0010ဉ\u000b\u0011ဉ\f\u0012\u0016\u0013ဉ\r\u0014ဇ\u000f\u0015က\u0010", new Object[]{"zze", "zzf", zze.zza, "zzg", "zzh", "zzi", zzh.zza, "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzt", zzae.class, "zzv", "zzw", zzae.class, "zzz", "zzp", "zzq", "zzr", "zzu", "zzs", "zzx", "zzy"});
        }
        if (i2 == 3) {
            return new zzc();
        }
        zza zzaVar = null;
        if (i2 == 4) {
            return new zzb(zzaVar);
        }
        if (i2 == 5) {
            return zza;
        }
        this.zzA = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
