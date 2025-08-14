package com.google.android.gms.internal.mlkit_vision_face;

import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzbm extends zzbn {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzbn zzc;

    zzbm(zzbn zzbnVar, int i, int i2) {
        this.zzc = zzbnVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzab.zza(i, this.zzb, "index");
        return this.zzc.get(i + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzbn, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzbi
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzbi
    final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzbi
    @CheckForNull
    final Object[] zze() {
        return this.zzc.zze();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzbn
    /* renamed from: zzf */
    public final zzbn subList(int i, int i2) {
        zzab.zzc(i, i2, this.zzb);
        zzbn zzbnVar = this.zzc;
        int i3 = this.zza;
        return zzbnVar.subList(i + i3, i2 + i3);
    }
}
