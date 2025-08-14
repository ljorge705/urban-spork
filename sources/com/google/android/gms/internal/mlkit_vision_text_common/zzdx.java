package com.google.android.gms.internal.mlkit_vision_text_common;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zzdx {
    private zzea zza;
    private Integer zzb;
    private zzji zzc;

    public final zzdx zza(Integer num) {
        this.zzb = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzdx zzb(zzji zzjiVar) {
        this.zzc = zzjiVar;
        return this;
    }

    public final zzdx zzc(zzea zzeaVar) {
        this.zza = zzeaVar;
        return this;
    }

    public final zzec zze() {
        return new zzec(this, null);
    }
}
