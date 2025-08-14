package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzex extends zzez {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzex() {
        super(null);
    }

    /* synthetic */ zzex(zzew zzewVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzez
    final void zza(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzgz.zzf(obj, j);
        if (list instanceof zzev) {
            objUnmodifiableList = ((zzev) list).zze();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzft) && (list instanceof zzek)) {
                zzek zzekVar = (zzek) list;
                if (zzekVar.zzc()) {
                    zzekVar.zzb();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzgz.zzs(obj, j, objUnmodifiableList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzez
    final void zzb(Object obj, Object obj2, long j) {
        zzeu zzeuVar;
        List list = (List) zzgz.zzf(obj2, j);
        int size = list.size();
        List listZzd = (List) zzgz.zzf(obj, j);
        if (listZzd.isEmpty()) {
            listZzd = listZzd instanceof zzev ? new zzeu(size) : ((listZzd instanceof zzft) && (listZzd instanceof zzek)) ? ((zzek) listZzd).zzd(size) : new ArrayList(size);
            zzgz.zzs(obj, j, listZzd);
        } else {
            if (zza.isAssignableFrom(listZzd.getClass())) {
                ArrayList arrayList = new ArrayList(listZzd.size() + size);
                arrayList.addAll(listZzd);
                zzgz.zzs(obj, j, arrayList);
                zzeuVar = arrayList;
            } else if (listZzd instanceof zzgu) {
                zzeu zzeuVar2 = new zzeu(listZzd.size() + size);
                zzeuVar2.addAll(zzeuVar2.size(), (zzgu) listZzd);
                zzgz.zzs(obj, j, zzeuVar2);
                zzeuVar = zzeuVar2;
            } else if ((listZzd instanceof zzft) && (listZzd instanceof zzek)) {
                zzek zzekVar = (zzek) listZzd;
                if (!zzekVar.zzc()) {
                    listZzd = zzekVar.zzd(listZzd.size() + size);
                    zzgz.zzs(obj, j, listZzd);
                }
            }
            listZzd = zzeuVar;
        }
        int size2 = listZzd.size();
        int size3 = list.size();
        if (size2 > 0 && size3 > 0) {
            listZzd.addAll(list);
        }
        if (size2 > 0) {
            list = listZzd;
        }
        zzgz.zzs(obj, j, list);
    }
}
