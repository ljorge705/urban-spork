package com.google.mlkit.vision.text.internal;

import com.google.android.gms.internal.mlkit_vision_text_common.zzni;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes2.dex */
public final class zzk {
    private final zzl zza;
    private final ExecutorSelector zzb;

    zzk(zzl zzlVar, ExecutorSelector executorSelector) {
        this.zza = zzlVar;
        this.zzb = executorSelector;
    }

    public final TextRecognizer zza(TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        return new TextRecognizerImpl((TextRecognizerTaskWithResource) this.zza.get(textRecognizerOptionsInterface), this.zzb.getExecutorToUse(textRecognizerOptionsInterface.getExecutor()), zzni.zzb(textRecognizerOptionsInterface.getLoggingLibraryName()), textRecognizerOptionsInterface);
    }
}
