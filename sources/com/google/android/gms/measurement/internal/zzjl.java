package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zzjl extends zzan {
    final /* synthetic */ zzjz zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzjl(zzjz zzjzVar, zzgy zzgyVar) {
        super(zzgyVar);
        this.zza = zzjzVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzan
    public final void zzc() {
        this.zza.zzt.zzaA().zzk().zza("Tasks have been queued for a long time");
    }
}
