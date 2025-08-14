package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzaj extends zzec implements zzfm {
    public static final zzea zza;
    private static final zzej zze = new zzag();
    private static final zzaj zzf;
    private int zzg;
    private zzy zzm;
    private zzjh zzn;
    private byte zzo = 2;
    private String zzh = "";
    private String zzi = "";
    private zzei zzj = zzN();
    private String zzk = "";
    private String zzl = "";

    static {
        zzaj zzajVar = new zzaj();
        zzf = zzajVar;
        zzec.zzS(zzaj.class, zzajVar);
        zza = zzec.zzH(zzjh.zzf(), zzajVar, zzajVar, null, 308676116, zzhf.MESSAGE, zzaj.class);
    }

    private zzaj() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzo);
        }
        if (i2 == 2) {
            return zzR(zzf, "\u0001\u0007\u0000\u0001\u0001Ǵ\u0007\u0000\u0001\u0002\u0001ᔈ\u0000\u0002ဈ\u0001\u0003\u001e\u0005ဈ\u0002\u0006ဈ\u0003\u000fᐉ\u0005Ǵဉ\u0004", new Object[]{"zzg", "zzh", "zzi", "zzj", zzai.zza, "zzk", "zzl", "zzn", "zzm"});
        }
        if (i2 == 3) {
            return new zzaj();
        }
        zzaf zzafVar = null;
        if (i2 == 4) {
            return new zzah(zzafVar);
        }
        if (i2 == 5) {
            return zzf;
        }
        this.zzo = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
