package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zznh extends LazyInstanceMap {
    private zznh() {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzmq zzmqVar = (zzmq) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zzmx(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zzmr(MlKitContext.getInstance().getApplicationContext(), zzmqVar), zzmqVar.zzb());
    }

    /* synthetic */ zznh(zzng zzngVar) {
    }
}
