package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.ArrayDeque;
import java.util.Arrays;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzfy {
    private final ArrayDeque zza = new ArrayDeque();

    private zzfy() {
    }

    static /* synthetic */ zzdb zza(zzfy zzfyVar, zzdb zzdbVar, zzdb zzdbVar2) {
        zzfyVar.zzb(zzdbVar);
        zzfyVar.zzb(zzdbVar2);
        zzdb zzgaVar = (zzdb) zzfyVar.zza.pop();
        while (!zzfyVar.zza.isEmpty()) {
            zzgaVar = new zzga((zzdb) zzfyVar.zza.pop(), zzgaVar, null);
        }
        return zzgaVar;
    }

    private final void zzb(zzdb zzdbVar) {
        zzfx zzfxVar;
        if (!zzdbVar.zzh()) {
            if (!(zzdbVar instanceof zzga)) {
                String strValueOf = String.valueOf(zzdbVar.getClass());
                String.valueOf(strValueOf).length();
                throw new IllegalArgumentException("Has a new type of ByteString been created? Found ".concat(String.valueOf(strValueOf)));
            }
            zzga zzgaVar = (zzga) zzdbVar;
            zzb(zzgaVar.zzd);
            zzb(zzgaVar.zze);
            return;
        }
        int iZzc = zzc(zzdbVar.zzd());
        int iZzc2 = zzga.zzc(iZzc + 1);
        if (this.zza.isEmpty() || ((zzdb) this.zza.peek()).zzd() >= iZzc2) {
            this.zza.push(zzdbVar);
            return;
        }
        int iZzc3 = zzga.zzc(iZzc);
        zzdb zzgaVar2 = (zzdb) this.zza.pop();
        while (true) {
            zzfxVar = null;
            if (this.zza.isEmpty() || ((zzdb) this.zza.peek()).zzd() >= iZzc3) {
                break;
            } else {
                zzgaVar2 = new zzga((zzdb) this.zza.pop(), zzgaVar2, zzfxVar);
            }
        }
        zzga zzgaVar3 = new zzga(zzgaVar2, zzdbVar, zzfxVar);
        while (!this.zza.isEmpty()) {
            if (((zzdb) this.zza.peek()).zzd() >= zzga.zzc(zzc(zzgaVar3.zzd()) + 1)) {
                break;
            } else {
                zzgaVar3 = new zzga((zzdb) this.zza.pop(), zzgaVar3, zzfxVar);
            }
        }
        this.zza.push(zzgaVar3);
    }

    private static final int zzc(int i) {
        int iBinarySearch = Arrays.binarySearch(zzga.zza, i);
        return iBinarySearch < 0 ? (-(iBinarySearch + 1)) - 1 : iBinarySearch;
    }

    /* synthetic */ zzfy(zzfx zzfxVar) {
    }
}
