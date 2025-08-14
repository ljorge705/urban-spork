package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public final class zznp {
    private final zzkl zza;
    private zzmc zzb = new zzmc();
    private final int zzc;

    private zznp(zzkl zzklVar, int i) {
        this.zza = zzklVar;
        zzny.zza();
        this.zzc = i;
    }

    public static zznp zzd(zzkl zzklVar) {
        return new zznp(zzklVar, 0);
    }

    public static zznp zze(zzkl zzklVar, int i) {
        return new zznp(zzklVar, 1);
    }

    public final int zza() {
        return this.zzc;
    }

    public final String zzb() {
        zzme zzmeVarZzf = this.zza.zzj().zzf();
        return (zzmeVarZzf == null || zzar.zzb(zzmeVarZzf.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzmeVarZzf.zzk());
    }

    public final byte[] zzc(int i, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.zzb.zze(false);
        this.zza.zzi(this.zzb.zzm());
        try {
            zzny.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zziu.zza).ignoreNullValues(true).build().encode(this.zza.zzj()).getBytes("utf-8");
            }
            zzkn zzknVarZzj = this.zza.zzj();
            zzdn zzdnVar = new zzdn();
            zziu.zza.configure(zzdnVar);
            return zzdnVar.zza().zza(zzknVarZzj);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }

    public final zznp zzf(zzkk zzkkVar) {
        this.zza.zzf(zzkkVar);
        return this;
    }

    public final zznp zzg(zzmc zzmcVar) {
        this.zzb = zzmcVar;
        return this;
    }
}
