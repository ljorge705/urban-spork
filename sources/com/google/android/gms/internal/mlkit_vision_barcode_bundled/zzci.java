package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzci extends zzec implements zzfm {
    private static final zzci zza;
    private int zze;
    private int zzf;
    private String zzg = "";

    static {
        zzci zzciVar = new zzci();
        zza = zzciVar;
        zzec.zzS(zzci.class, zzciVar);
    }

    private zzci() {
    }

    public static zzci zzb() {
        return zza;
    }

    public final String zzc() {
        return this.zzg;
    }

    public final int zzd() {
        int iZza = zzch.zza(this.zzf);
        if (iZza == 0) {
            return 1;
        }
        return iZza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", zzcg.zza, "zzg"});
        }
        if (i2 == 3) {
            return new zzci();
        }
        zzbw zzbwVar = null;
        if (i2 == 4) {
            return new zzce(zzbwVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
