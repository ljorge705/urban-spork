package com.google.mlkit.vision.text;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.tasks.Task;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.interfaces.Detector;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes2.dex */
public interface TextRecognizer extends Detector<Text> {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void close();

    Task<Text> process(MlImage mlImage);

    Task<Text> process(InputImage inputImage);
}
