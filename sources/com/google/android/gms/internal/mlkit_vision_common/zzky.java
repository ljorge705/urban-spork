package com.google.android.gms.internal.mlkit_vision_common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes3.dex */
public final class zzky implements zzkp {
    private final zzht zza;
    private zzjo zzb = new zzjo();

    private zzky(zzht zzhtVar, int i) {
        this.zza = zzhtVar;
        zzlk.zza();
    }

    public static zzkp zze(zzht zzhtVar) {
        return new zzky(zzhtVar, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzkp
    public final zzkp zza(zzhs zzhsVar) {
        this.zza.zzc(zzhsVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzkp
    public final zzkp zzb(zzjo zzjoVar) {
        this.zzb = zzjoVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzkp
    public final String zzc() {
        zzjq zzjqVarZzc = this.zza.zzf().zzc();
        return (zzjqVarZzc == null || zzg.zzb(zzjqVarZzc.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzjqVarZzc.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzkp
    public final byte[] zzd(int i, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.zzb.zze(false);
        this.zza.zze(this.zzb.zzm());
        try {
            zzlk.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzgh.zza).ignoreNullValues(true).build().encode(this.zza.zzf()).getBytes("utf-8");
            }
            zzhv zzhvVarZzf = this.zza.zzf();
            zzam zzamVar = new zzam();
            zzgh.zza.configure(zzamVar);
            return zzamVar.zza().zza(zzhvVarZzf);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}
