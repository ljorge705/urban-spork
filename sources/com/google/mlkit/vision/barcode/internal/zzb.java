package com.google.mlkit.vision.barcode.internal;

import android.util.SparseArray;
import com.google.android.gms.internal.mlkit_vision_barcode.zzbz;
import com.google.android.gms.internal.mlkit_vision_barcode.zzki;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkj;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkk;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkl;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkv;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkw;
import com.google.android.gms.internal.mlkit_vision_barcode.zzla;
import com.google.android.gms.internal.mlkit_vision_barcode.zzmv;
import com.google.android.gms.internal.mlkit_vision_barcode.zzmw;
import com.google.android.gms.internal.mlkit_vision_barcode.zzmy;
import com.google.android.gms.internal.mlkit_vision_barcode.zznk;
import com.google.android.gms.internal.mlkit_vision_barcode.zznm;
import com.google.android.gms.internal.mlkit_vision_barcode.zznp;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes2.dex */
public final class zzb {
    static final AtomicReference zza;
    private static final SparseArray zzb;
    private static final SparseArray zzc;
    private static final Map zzd;

    static {
        SparseArray sparseArray = new SparseArray();
        zzb = sparseArray;
        SparseArray sparseArray2 = new SparseArray();
        zzc = sparseArray2;
        zza = new AtomicReference();
        sparseArray.put(-1, zzkv.FORMAT_UNKNOWN);
        sparseArray.put(1, zzkv.FORMAT_CODE_128);
        sparseArray.put(2, zzkv.FORMAT_CODE_39);
        sparseArray.put(4, zzkv.FORMAT_CODE_93);
        sparseArray.put(8, zzkv.FORMAT_CODABAR);
        sparseArray.put(16, zzkv.FORMAT_DATA_MATRIX);
        sparseArray.put(32, zzkv.FORMAT_EAN_13);
        sparseArray.put(64, zzkv.FORMAT_EAN_8);
        sparseArray.put(128, zzkv.FORMAT_ITF);
        sparseArray.put(256, zzkv.FORMAT_QR_CODE);
        sparseArray.put(512, zzkv.FORMAT_UPC_A);
        sparseArray.put(1024, zzkv.FORMAT_UPC_E);
        sparseArray.put(2048, zzkv.FORMAT_PDF417);
        sparseArray.put(4096, zzkv.FORMAT_AZTEC);
        sparseArray2.put(0, zzkw.TYPE_UNKNOWN);
        sparseArray2.put(1, zzkw.TYPE_CONTACT_INFO);
        sparseArray2.put(2, zzkw.TYPE_EMAIL);
        sparseArray2.put(3, zzkw.TYPE_ISBN);
        sparseArray2.put(4, zzkw.TYPE_PHONE);
        sparseArray2.put(5, zzkw.TYPE_PRODUCT);
        sparseArray2.put(6, zzkw.TYPE_SMS);
        sparseArray2.put(7, zzkw.TYPE_TEXT);
        sparseArray2.put(8, zzkw.TYPE_URL);
        sparseArray2.put(9, zzkw.TYPE_WIFI);
        sparseArray2.put(10, zzkw.TYPE_GEO);
        sparseArray2.put(11, zzkw.TYPE_CALENDAR_EVENT);
        sparseArray2.put(12, zzkw.TYPE_DRIVER_LICENSE);
        HashMap map = new HashMap();
        zzd = map;
        map.put(1, zzmv.CODE_128);
        map.put(2, zzmv.CODE_39);
        map.put(4, zzmv.CODE_93);
        map.put(8, zzmv.CODABAR);
        map.put(16, zzmv.DATA_MATRIX);
        map.put(32, zzmv.EAN_13);
        map.put(64, zzmv.EAN_8);
        map.put(128, zzmv.ITF);
        map.put(256, zzmv.QR_CODE);
        map.put(512, zzmv.UPC_A);
        map.put(1024, zzmv.UPC_E);
        map.put(2048, zzmv.PDF417);
        map.put(4096, zzmv.AZTEC);
    }

    public static zzkv zza(int i) {
        zzkv zzkvVar = (zzkv) zzb.get(i);
        return zzkvVar == null ? zzkv.FORMAT_UNKNOWN : zzkvVar;
    }

    public static zzkw zzb(int i) {
        zzkw zzkwVar = (zzkw) zzc.get(i);
        return zzkwVar == null ? zzkw.TYPE_UNKNOWN : zzkwVar;
    }

    public static zzmy zzc(BarcodeScannerOptions barcodeScannerOptions) {
        int iZza = barcodeScannerOptions.zza();
        zzbz zzbzVar = new zzbz();
        if (iZza == 0) {
            zzbzVar.zze(zzd.values());
        } else {
            for (Map.Entry entry : zzd.entrySet()) {
                if ((((Integer) entry.getKey()).intValue() & iZza) != 0) {
                    zzbzVar.zzd((zzmv) entry.getValue());
                }
            }
        }
        zzmw zzmwVar = new zzmw();
        zzmwVar.zzb(zzbzVar.zzf());
        return zzmwVar.zzc();
    }

    public static String zzd() {
        return true != zzf() ? "play-services-mlkit-barcode-scanning" : "barcode-scanning";
    }

    static void zze(zznm zznmVar, final zzkj zzkjVar) {
        zznmVar.zzb(new zznk() { // from class: com.google.mlkit.vision.barcode.internal.zza
            @Override // com.google.android.gms.internal.mlkit_vision_barcode.zznk
            public final zznp zza() {
                zzkj zzkjVar2 = zzkjVar;
                zzkl zzklVar = new zzkl();
                zzklVar.zze(zzb.zzf() ? zzki.TYPE_THICK : zzki.TYPE_THIN);
                zzla zzlaVar = new zzla();
                zzlaVar.zzb(zzkjVar2);
                zzklVar.zzh(zzlaVar.zzc());
                return zznp.zzd(zzklVar);
            }
        }, zzkk.ON_DEVICE_BARCODE_LOAD);
    }

    static boolean zzf() {
        AtomicReference atomicReference = zza;
        if (atomicReference.get() != null) {
            return ((Boolean) atomicReference.get()).booleanValue();
        }
        boolean zZzd = zzl.zzd(MlKitContext.getInstance().getApplicationContext());
        atomicReference.set(Boolean.valueOf(zZzd));
        return zZzd;
    }
}
