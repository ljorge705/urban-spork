package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzdp extends zzdo {
    zzdp() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo
    final int zza(Map.Entry entry) {
        return ((zzdz) entry.getKey()).zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo
    final zzds zzb(Object obj) {
        return ((zzdy) obj).zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo
    final zzds zzc(Object obj) {
        return ((zzdy) obj).zzc();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo
    final Object zzd(zzdn zzdnVar, zzfl zzflVar, int i) {
        return zzdnVar.zzb(zzflVar, i);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo
    final void zze(Object obj) {
        ((zzdy) obj).zza.zzg();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo
    final boolean zzf(zzfl zzflVar) {
        return zzflVar instanceof zzdy;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo
    final void zzg(zzdj zzdjVar, Map.Entry entry) throws IOException {
        zzdz zzdzVar = (zzdz) entry.getKey();
        zzhf zzhfVar = zzhf.DOUBLE;
        switch (zzdzVar.zzb) {
            case DOUBLE:
                zzdjVar.zzf(zzdzVar.zza, ((Double) entry.getValue()).doubleValue());
                break;
            case FLOAT:
                zzdjVar.zzo(zzdzVar.zza, ((Float) entry.getValue()).floatValue());
                break;
            case INT64:
                zzdjVar.zzt(zzdzVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case UINT64:
                zzdjVar.zzK(zzdzVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case INT32:
                zzdjVar.zzr(zzdzVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case FIXED64:
                zzdjVar.zzm(zzdzVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case FIXED32:
                zzdjVar.zzk(zzdzVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case BOOL:
                zzdjVar.zzb(zzdzVar.zza, ((Boolean) entry.getValue()).booleanValue());
                break;
            case STRING:
                zzdjVar.zzG(zzdzVar.zza, (String) entry.getValue());
                break;
            case GROUP:
                zzdjVar.zzq(zzdzVar.zza, entry.getValue(), zzfu.zza().zzb(entry.getValue().getClass()));
                break;
            case MESSAGE:
                zzdjVar.zzv(zzdzVar.zza, entry.getValue(), zzfu.zza().zzb(entry.getValue().getClass()));
                break;
            case BYTES:
                zzdjVar.zzd(zzdzVar.zza, (zzdb) entry.getValue());
                break;
            case UINT32:
                zzdjVar.zzI(zzdzVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case ENUM:
                zzdjVar.zzr(zzdzVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case SFIXED32:
                zzdjVar.zzx(zzdzVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case SFIXED64:
                zzdjVar.zzz(zzdzVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case SINT32:
                zzdjVar.zzB(zzdzVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case SINT64:
                zzdjVar.zzD(zzdzVar.zza, ((Long) entry.getValue()).longValue());
                break;
        }
    }
}
