package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.collection.ArrayMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzic implements zzhk {
    private static final Map zza = new ArrayMap();
    private final SharedPreferences zzb;
    private final SharedPreferences.OnSharedPreferenceChangeListener zzc;

    static zzic zza(Context context, String str, Runnable runnable) {
        zzic zzicVar;
        if (zzhb.zzb()) {
            throw null;
        }
        synchronized (zzic.class) {
            zzicVar = (zzic) zza.get(null);
            if (zzicVar == null) {
                StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                try {
                    throw null;
                } catch (Throwable th) {
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                    throw th;
                }
            }
        }
        return zzicVar;
    }

    static synchronized void zzc() {
        Map map = zza;
        Iterator it = map.values().iterator();
        if (it.hasNext()) {
            zzic zzicVar = (zzic) it.next();
            SharedPreferences sharedPreferences = zzicVar.zzb;
            SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = zzicVar.zzc;
            throw null;
        }
        map.clear();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public final Object zzb(String str) {
        throw null;
    }
}
