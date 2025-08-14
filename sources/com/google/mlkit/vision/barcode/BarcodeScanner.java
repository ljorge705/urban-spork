package com.google.mlkit.vision.barcode;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.tasks.Task;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.interfaces.Detector;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes2.dex */
public interface BarcodeScanner extends Detector<List<Barcode>> {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void close();

    Task<List<Barcode>> process(MlImage mlImage);

    Task<List<Barcode>> process(InputImage inputImage);
}
