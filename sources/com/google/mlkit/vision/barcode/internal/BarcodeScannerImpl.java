package com.google.mlkit.vision.barcode.internal;

import com.google.android.gms.internal.mlkit_vision_barcode.zzki;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkk;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkl;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkx;
import com.google.android.gms.internal.mlkit_vision_barcode.zzkz;
import com.google.android.gms.internal.mlkit_vision_barcode.zznm;
import com.google.android.gms.internal.mlkit_vision_barcode.zznp;
import com.google.android.gms.tasks.Task;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.MobileVisionBase;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes2.dex */
public class BarcodeScannerImpl extends MobileVisionBase<List<Barcode>> implements BarcodeScanner {
    private static final BarcodeScannerOptions zzb = new BarcodeScannerOptions.Builder().build();

    BarcodeScannerImpl(BarcodeScannerOptions barcodeScannerOptions, zzi zziVar, Executor executor, zznm zznmVar) {
        super(zziVar, executor);
        zzkx zzkxVar = new zzkx();
        zzkxVar.zzi(zzb.zzc(barcodeScannerOptions));
        zzkz zzkzVarZzj = zzkxVar.zzj();
        zzkl zzklVar = new zzkl();
        zzklVar.zze(zzb.zzf() ? zzki.TYPE_THICK : zzki.TYPE_THIN);
        zzklVar.zzg(zzkzVarZzj);
        zznmVar.zze(zznp.zze(zzklVar, 1), zzkk.ON_DEVICE_BARCODE_CREATE);
    }

    @Override // com.google.mlkit.vision.interfaces.Detector
    public final int getDetectorType() {
        return 1;
    }

    @Override // com.google.mlkit.vision.barcode.BarcodeScanner
    public final Task<List<Barcode>> process(MlImage mlImage) {
        return super.processBase(mlImage);
    }

    @Override // com.google.mlkit.vision.barcode.BarcodeScanner
    public final Task<List<Barcode>> process(InputImage inputImage) {
        return super.processBase(inputImage);
    }
}
