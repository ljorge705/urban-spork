package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* loaded from: classes3.dex */
final class zzmd {
    zzmd() {
    }

    public static final int zza(int i, Object obj, Object obj2) {
        zzmc zzmcVar = (zzmc) obj;
        if (zzmcVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzmcVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }

    public static final Object zzb(Object obj, Object obj2) {
        zzmc zzmcVarZzb = (zzmc) obj;
        zzmc zzmcVar = (zzmc) obj2;
        if (!zzmcVar.isEmpty()) {
            if (!zzmcVarZzb.zze()) {
                zzmcVarZzb = zzmcVarZzb.zzb();
            }
            zzmcVarZzb.zzd(zzmcVar);
        }
        return zzmcVarZzb;
    }
}
