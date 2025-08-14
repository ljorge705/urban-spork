package com.google.mlkit.vision.text.internal;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes2.dex */
public final class zzl extends LazyInstanceMap {
    private final MlKitContext zza;

    public zzl(MlKitContext mlKitContext) {
        this.zza = mlKitContext;
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
        return new TextRecognizerTaskWithResource(this.zza, (TextRecognizerOptionsInterface) obj);
    }
}
