package com.google.android.gms.internal.mlkit_common;

import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
final class zzan extends zzao {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzao zzc;

    zzan(zzao zzaoVar, int i, int i2) {
        this.zzc = zzaoVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzac.zza(i, this.zzb, "index");
        return this.zzc.get(i + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzao, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzak
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzak
    final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzak
    @CheckForNull
    final Object[] zze() {
        return this.zzc.zze();
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzao
    /* renamed from: zzf */
    public final zzao subList(int i, int i2) {
        zzac.zzc(i, i2, this.zzb);
        zzao zzaoVar = this.zzc;
        int i3 = this.zza;
        return zzaoVar.subList(i + i3, i2 + i3);
    }
}
