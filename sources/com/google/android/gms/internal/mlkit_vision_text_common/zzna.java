package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zzna {
    private final zzkb zza;
    private zzlr zzb = new zzlr();
    private final int zzc;

    private zzna(zzkb zzkbVar, int i) {
        this.zza = zzkbVar;
        zznj.zza();
        this.zzc = i;
    }

    public static zzna zzd(zzkb zzkbVar) {
        return new zzna(zzkbVar, 0);
    }

    public static zzna zze(zzkb zzkbVar, int i) {
        return new zzna(zzkbVar, 1);
    }

    public final int zza() {
        return this.zzc;
    }

    public final String zzb() {
        zzlt zzltVarZze = this.zza.zzi().zze();
        return (zzltVarZze == null || zzab.zzb(zzltVarZze.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzltVarZze.zzk());
    }

    public final byte[] zzc(int i, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.zzb.zze(false);
        this.zza.zzh(this.zzb.zzm());
        try {
            zznj.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzik.zza).ignoreNullValues(true).build().encode(this.zza.zzi()).getBytes("utf-8");
            }
            zzkd zzkdVarZzi = this.zza.zzi();
            zzdd zzddVar = new zzdd();
            zzik.zza.configure(zzddVar);
            return zzddVar.zza().zza(zzkdVarZzi);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }

    public final zzna zzf(zzka zzkaVar) {
        this.zza.zzf(zzkaVar);
        return this;
    }

    public final zzna zzg(zzlr zzlrVar) {
        this.zzb = zzlrVar;
        return this;
    }
}
