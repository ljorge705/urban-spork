package com.google.android.play.core.splitcompat;

import java.io.IOException;
import java.util.Set;
import java.util.zip.ZipFile;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzi implements zzk {
    final /* synthetic */ Set zza;
    final /* synthetic */ zzt zzb;
    final /* synthetic */ zzn zzc;

    zzi(zzn zznVar, Set set, zzt zztVar) {
        this.zzc = zznVar;
        this.zza = set;
        this.zzb = zztVar;
    }

    @Override // com.google.android.play.core.splitcompat.zzk
    public final void zza(ZipFile zipFile, Set set) throws IOException {
        this.zza.addAll(zzn.zza(this.zzc, set, this.zzb, zipFile));
    }
}
