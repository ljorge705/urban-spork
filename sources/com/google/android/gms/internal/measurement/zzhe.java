package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zzhe extends ContentObserver {
    final /* synthetic */ zzhf zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzhe(zzhf zzhfVar, Handler handler) {
        super(null);
        this.zza = zzhfVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.zza.zzf();
    }
}
