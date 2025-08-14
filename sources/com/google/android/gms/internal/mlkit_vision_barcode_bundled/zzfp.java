package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzfp implements zzgb {
    private final zzfl zza;
    private final zzgp zzb;
    private final boolean zzc;
    private final zzdo zzd;

    private zzfp(zzgp zzgpVar, zzdo zzdoVar, zzfl zzflVar) {
        this.zzb = zzgpVar;
        this.zzc = zzdoVar.zzf(zzflVar);
        this.zzd = zzdoVar;
        this.zza = zzflVar;
    }

    static zzfp zzc(zzgp zzgpVar, zzdo zzdoVar, zzfl zzflVar) {
        return new zzfp(zzgpVar, zzdoVar, zzflVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final int zza(Object obj) {
        zzgp zzgpVar = this.zzb;
        int iZzb = zzgpVar.zzb(zzgpVar.zzc(obj));
        return this.zzc ? iZzb + this.zzd.zzb(obj).zzb() : iZzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final int zzb(Object obj) {
        int iHashCode = this.zzb.zzc(obj).hashCode();
        return this.zzc ? (iHashCode * 53) + this.zzd.zzb(obj).zza.hashCode() : iHashCode;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final Object zze() {
        return this.zza.zzU().zzm();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final void zzf(Object obj) {
        this.zzb.zzg(obj);
        this.zzd.zze(obj);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final void zzg(Object obj, Object obj2) {
        zzgd.zzF(this.zzb, obj, obj2);
        if (this.zzc) {
            zzgd.zzE(this.zzd, obj, obj2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00bf A[EDGE_INSN: B:57:0x00bf->B:33:0x00bf BREAK  A[LOOP:1: B:18:0x0067->B:60:0x0067], SYNTHETIC] */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzh(java.lang.Object r11, byte[] r12, int r13, int r14, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzco r15) throws java.io.IOException {
        /*
            r10 = this;
            r0 = r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec r0 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec) r0
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgq r1 = r0.zzc
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgq r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgq.zzc()
            if (r1 != r2) goto L11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgq r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgq.zze()
            r0.zzc = r1
        L11:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdy r11 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdy) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds r11 = r11.zzc()
            r0 = 0
            r2 = r0
        L19:
            if (r13 >= r14) goto Lca
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r12, r13, r15)
            int r13 = r15.zza
            r3 = 11
            r5 = 2
            if (r13 == r3) goto L65
            r3 = r13 & 7
            if (r3 != r5) goto L60
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo r2 = r10.zzd
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn r3 = r15.zzd
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfl r5 = r10.zza
            int r6 = r13 >>> 3
            java.lang.Object r8 = r2.zzd(r3, r5, r6)
            if (r8 == 0) goto L55
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfu r13 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfu.zza()
            r2 = r8
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzea r2 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzea) r2
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfl r3 = r2.zzc
            java.lang.Class r3 = r3.getClass()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb r13 = r13.zzb(r3)
            int r13 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzd(r13, r12, r4, r14, r15)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdz r2 = r2.zzd
            java.lang.Object r3 = r15.zzc
            r11.zzi(r2, r3)
            goto L5e
        L55:
            r2 = r13
            r3 = r12
            r5 = r14
            r6 = r1
            r7 = r15
            int r13 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzi(r2, r3, r4, r5, r6, r7)
        L5e:
            r2 = r8
            goto L19
        L60:
            int r13 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzn(r13, r12, r4, r14, r15)
            goto L19
        L65:
            r13 = 0
            r3 = r0
        L67:
            if (r4 >= r14) goto Lbf
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r12, r4, r15)
            int r6 = r15.zza
            r7 = r6 & 7
            int r8 = r6 >>> 3
            if (r8 == r5) goto La3
            r9 = 3
            if (r8 == r9) goto L79
            goto Lb6
        L79:
            if (r2 == 0) goto L98
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfu r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfu.zza()
            r7 = r2
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzea r7 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzea) r7
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfl r8 = r7.zzc
            java.lang.Class r8 = r8.getClass()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb r6 = r6.zzb(r8)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzd(r6, r12, r4, r14, r15)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdz r6 = r7.zzd
            java.lang.Object r7 = r15.zzc
            r11.zzi(r6, r7)
            goto L67
        L98:
            if (r7 != r5) goto Lb6
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zza(r12, r4, r15)
            java.lang.Object r3 = r15.zzc
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r3 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r3
            goto L67
        La3:
            if (r7 != 0) goto Lb6
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r12, r4, r15)
            int r13 = r15.zza
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo r2 = r10.zzd
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn r6 = r15.zzd
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfl r7 = r10.zza
            java.lang.Object r2 = r2.zzd(r6, r7, r13)
            goto L67
        Lb6:
            r7 = 12
            if (r6 == r7) goto Lbf
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzn(r6, r12, r4, r14, r15)
            goto L67
        Lbf:
            if (r3 == 0) goto Lc7
            int r13 = r13 << 3
            r13 = r13 | r5
            r1.zzh(r13, r3)
        Lc7:
            r13 = r4
            goto L19
        Lca:
            if (r13 != r14) goto Lcd
            return
        Lcd:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzen r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzen.zze()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp.zzh(java.lang.Object, byte[], int, int, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzco):void");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final boolean zzi(Object obj, Object obj2) {
        if (!this.zzb.zzc(obj).equals(this.zzb.zzc(obj2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zzb(obj).equals(this.zzd.zzb(obj2));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final boolean zzj(Object obj) {
        return this.zzd.zzb(obj).zzk();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final void zzm(Object obj, zzdj zzdjVar) throws IOException {
        Iterator itZzf = this.zzd.zzb(obj).zzf();
        while (itZzf.hasNext()) {
            Map.Entry entry = (Map.Entry) itZzf.next();
            zzdr zzdrVar = (zzdr) entry.getKey();
            if (zzdrVar.zze() != zzhg.MESSAGE) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            zzdrVar.zzg();
            zzdrVar.zzf();
            if (entry instanceof zzeq) {
                zzdjVar.zzw(zzdrVar.zza(), ((zzeq) entry).zza().zzb());
            } else {
                zzdjVar.zzw(zzdrVar.zza(), entry.getValue());
            }
        }
        zzgp zzgpVar = this.zzb;
        zzgpVar.zzi(zzgpVar.zzc(obj), zzdjVar);
    }
}
