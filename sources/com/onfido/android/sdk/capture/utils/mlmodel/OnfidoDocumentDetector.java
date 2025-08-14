package com.onfido.android.sdk.capture.utils.mlmodel;

import android.graphics.Bitmap;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bà\u0080\u0001\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoDocumentDetector;", "", "detect", "", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlDocument;", "bitmap", "Landroid/graphics/Bitmap;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface OnfidoDocumentDetector {
    List<OnfidoMlDocument> detect(Bitmap bitmap);
}
