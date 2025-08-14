package com.google.android.gms.measurement;

import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzgd;
import com.google.android.gms.measurement.internal.zzhf;
import com.google.android.gms.measurement.internal.zzhg;
import com.google.android.gms.measurement.internal.zzik;
import com.google.android.gms.measurement.internal.zzlk;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zza extends zzd {
    private final zzgd zza;
    private final zzik zzb;

    public zza(zzgd zzgdVar) {
        super(null);
        Preconditions.checkNotNull(zzgdVar);
        this.zza = zzgdVar;
        this.zzb = zzgdVar.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final int zza(String str) {
        this.zzb.zzh(str);
        return 25;
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final long zzb() {
        return this.zza.zzv().zzq();
    }

    @Override // com.google.android.gms.measurement.zzd
    public final Boolean zzc() {
        return this.zzb.zzi();
    }

    @Override // com.google.android.gms.measurement.zzd
    public final Double zzd() {
        return this.zzb.zzj();
    }

    @Override // com.google.android.gms.measurement.zzd
    public final Integer zze() {
        return this.zzb.zzl();
    }

    @Override // com.google.android.gms.measurement.zzd
    public final Long zzf() {
        return this.zzb.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final String zzh() {
        return this.zzb.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final String zzi() {
        return this.zzb.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final String zzj() {
        return this.zzb.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final String zzk() {
        return this.zzb.zzo();
    }

    @Override // com.google.android.gms.measurement.zzd
    public final String zzl() {
        return this.zzb.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final List zzm(String str, String str2) {
        return this.zzb.zzs(str, str2);
    }

    @Override // com.google.android.gms.measurement.zzd
    public final Map zzn(boolean z) {
        List<zzlk> listZzt = this.zzb.zzt(z);
        ArrayMap arrayMap = new ArrayMap(listZzt.size());
        for (zzlk zzlkVar : listZzt) {
            Object objZza = zzlkVar.zza();
            if (objZza != null) {
                arrayMap.put(zzlkVar.zzb, objZza);
            }
        }
        return arrayMap;
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final Map zzo(String str, String str2, boolean z) {
        return this.zzb.zzu(str, str2, z);
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final void zzp(String str) throws IllegalStateException {
        this.zza.zzd().zzd(str, this.zza.zzax().elapsedRealtime());
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final void zzq(String str, String str2, Bundle bundle) throws IllegalStateException {
        this.zza.zzq().zzA(str, str2, bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final void zzr(String str) throws IllegalStateException {
        this.zza.zzd().zze(str, this.zza.zzax().elapsedRealtime());
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final void zzs(String str, String str2, Bundle bundle) throws IllegalStateException {
        this.zzb.zzD(str, str2, bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final void zzt(String str, String str2, Bundle bundle, long j) throws IllegalStateException {
        this.zzb.zzE(str, str2, bundle, true, false, j);
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final void zzu(zzhg zzhgVar) {
        this.zzb.zzJ(zzhgVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final void zzv(Bundle bundle) throws IllegalStateException {
        this.zzb.zzP(bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final void zzw(zzhf zzhfVar) {
        this.zzb.zzT(zzhfVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final void zzx(zzhg zzhgVar) {
        this.zzb.zzZ(zzhgVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    public final Object zzg(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? this.zzb.zzi() : this.zzb.zzl() : this.zzb.zzj() : this.zzb.zzm() : this.zzb.zzr();
    }
}
