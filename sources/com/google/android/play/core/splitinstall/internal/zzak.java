package com.google.android.play.core.splitinstall.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.play.core.splitcompat.SplitCompat;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
public final class zzak implements com.google.android.play.core.splitinstall.zzh {
    private final Context zza;
    private final com.google.android.play.core.splitcompat.zze zzb;
    private final zzam zzc;
    private final Executor zzd;
    private final com.google.android.play.core.splitcompat.zzs zze;

    public zzak(Context context, Executor executor, zzam zzamVar, com.google.android.play.core.splitcompat.zze zzeVar, com.google.android.play.core.splitcompat.zzs zzsVar) {
        this.zza = context;
        this.zzb = zzeVar;
        this.zzc = zzamVar;
        this.zzd = executor;
        this.zze = zzsVar;
    }

    static /* bridge */ /* synthetic */ void zzb(zzak zzakVar, List list, com.google.android.play.core.splitinstall.zzf zzfVar) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Integer numZze = zzakVar.zze(list);
        if (numZze == null) {
            return;
        }
        if (numZze.intValue() == 0) {
            zzfVar.zzc();
        } else {
            zzfVar.zzb(numZze.intValue());
        }
    }

    static /* bridge */ /* synthetic */ void zzc(zzak zzakVar, com.google.android.play.core.splitinstall.zzf zzfVar) {
        try {
            if (SplitCompat.zzd(zzbr.zza(zzakVar.zza))) {
                Log.i("SplitCompat", "Splits installed.");
                zzfVar.zza();
            } else {
                Log.e("SplitCompat", "Emulating splits failed.");
                zzfVar.zzb(-12);
            }
        } catch (Exception e) {
            Log.e("SplitCompat", "Error emulating splits.", e);
            zzfVar.zzb(-12);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:90:0x0144 A[Catch: Exception -> 0x0148, TRY_LEAVE, TryCatch #9 {Exception -> 0x0148, blocks: (B:3:0x0004, B:90:0x0144, B:88:0x0141, B:87:0x013e, B:5:0x0016, B:11:0x0021, B:12:0x002a, B:14:0x0031, B:48:0x00b1, B:56:0x00c1, B:55:0x00be, B:57:0x00c2, B:58:0x00c7, B:60:0x00d3, B:62:0x00db, B:64:0x00e3, B:65:0x00f1, B:67:0x00f5, B:69:0x0106, B:81:0x012f, B:71:0x010d, B:72:0x0113, B:74:0x011a, B:77:0x0122, B:79:0x0129, B:84:0x0139), top: B:112:0x0004, inners: #11, #12 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.Integer zze(java.util.List r13) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instructions count: 339
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.internal.zzak.zze(java.util.List):java.lang.Integer");
    }

    @Override // com.google.android.play.core.splitinstall.zzh
    public final void zzd(List list, com.google.android.play.core.splitinstall.zzf zzfVar) {
        if (!SplitCompat.zze()) {
            throw new IllegalStateException("Ingestion should only be called in SplitCompat mode.");
        }
        this.zzd.execute(new zzaj(this, list, zzfVar));
    }
}
