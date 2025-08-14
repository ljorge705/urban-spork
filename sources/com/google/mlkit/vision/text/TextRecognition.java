package com.google.mlkit.vision.text;

import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.text.internal.zzk;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes2.dex */
public class TextRecognition {
    private TextRecognition() {
    }

    public static TextRecognizer getClient(TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        return ((zzk) MlKitContext.getInstance().get(zzk.class)).zza(textRecognizerOptionsInterface);
    }
}
