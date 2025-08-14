package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* loaded from: classes3.dex */
final class zzma implements zzmu {
    private static final zzmg zza = new zzly();
    private final zzmg zzb;

    public zzma() {
        zzmg zzmgVar;
        zzmg[] zzmgVarArr = new zzmg[2];
        zzmgVarArr[0] = zzkw.zza();
        try {
            zzmgVar = (zzmg) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzmgVar = zza;
        }
        zzmgVarArr[1] = zzmgVar;
        zzlz zzlzVar = new zzlz(zzmgVarArr);
        byte[] bArr = zzlj.zzd;
        this.zzb = zzlzVar;
    }

    private static boolean zzb(zzmf zzmfVar) {
        return zzmfVar.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.measurement.zzmu
    public final zzmt zza(Class cls) {
        zzmv.zzC(cls);
        zzmf zzmfVarZzb = this.zzb.zzb(cls);
        return zzmfVarZzb.zzb() ? zzlb.class.isAssignableFrom(cls) ? zzmm.zzc(zzmv.zzz(), zzkq.zzb(), zzmfVarZzb.zza()) : zzmm.zzc(zzmv.zzy(), zzkq.zza(), zzmfVarZzb.zza()) : zzlb.class.isAssignableFrom(cls) ? zzb(zzmfVarZzb) ? zzml.zzl(cls, zzmfVarZzb, zzmo.zzb(), zzlw.zzd(), zzmv.zzz(), zzkq.zzb(), zzme.zzb()) : zzml.zzl(cls, zzmfVarZzb, zzmo.zzb(), zzlw.zzd(), zzmv.zzz(), null, zzme.zzb()) : zzb(zzmfVarZzb) ? zzml.zzl(cls, zzmfVarZzb, zzmo.zza(), zzlw.zzc(), zzmv.zzy(), zzkq.zza(), zzme.zza()) : zzml.zzl(cls, zzmfVarZzb, zzmo.zza(), zzlw.zzc(), zzmv.zzy(), null, zzme.zza());
    }
}
