package com.google.android.gms.measurement.internal;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zzeq implements Runnable {
    final /* synthetic */ int zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ Object zzc;
    final /* synthetic */ Object zzd;
    final /* synthetic */ Object zze;
    final /* synthetic */ zzet zzf;

    zzeq(zzet zzetVar, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzf = zzetVar;
        this.zza = i;
        this.zzb = str;
        this.zzc = obj;
        this.zzd = obj2;
        this.zze = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfi zzfiVarZzm = this.zzf.zzt.zzm();
        if (!zzfiVarZzm.zzy()) {
            Log.println(6, this.zzf.zzr(), "Persisted config not initialized. Not logging error/warn");
            return;
        }
        zzet zzetVar = this.zzf;
        if (zzetVar.zza == 0) {
            if (zzetVar.zzt.zzf().zzy()) {
                zzet zzetVar2 = this.zzf;
                zzetVar2.zzt.zzay();
                zzetVar2.zza = 'C';
            } else {
                zzet zzetVar3 = this.zzf;
                zzetVar3.zzt.zzay();
                zzetVar3.zza = Constants.INAPP_POSITION_CENTER;
            }
        }
        zzet zzetVar4 = this.zzf;
        if (zzetVar4.zzb < 0) {
            zzetVar4.zzt.zzf().zzh();
            zzetVar4.zzb = 79000L;
        }
        char cCharAt = "01VDIWEA?".charAt(this.zza);
        zzet zzetVar5 = this.zzf;
        String strSubstring = ExifInterface.GPS_MEASUREMENT_2D + cCharAt + zzetVar5.zza + zzetVar5.zzb + ":" + zzet.zzo(true, this.zzb, this.zzc, this.zzd, this.zze);
        if (strSubstring.length() > 1024) {
            strSubstring = this.zzb.substring(0, 1024);
        }
        zzfg zzfgVar = zzfiVarZzm.zzb;
        if (zzfgVar != null) {
            zzfgVar.zzb(strSubstring, 1L);
        }
    }
}
