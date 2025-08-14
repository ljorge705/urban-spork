package com.google.android.play.core.splitinstall.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzae implements ServiceConnection {
    final /* synthetic */ zzaf zza;

    /* synthetic */ zzae(zzaf zzafVar, zzad zzadVar) {
        this.zza = zzafVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.zza.zzc.zzd("ServiceConnectionImpl.onServiceConnected(%s)", componentName);
        zzaf zzafVar = this.zza;
        zzafVar.zzc().post(new zzab(this, iBinder));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.zza.zzc.zzd("ServiceConnectionImpl.onServiceDisconnected(%s)", componentName);
        zzaf zzafVar = this.zza;
        zzafVar.zzc().post(new zzac(this));
    }
}
