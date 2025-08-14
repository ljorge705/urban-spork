package com.google.mlkit.vision.barcode.internal;

import com.google.android.gms.internal.mlkit_vision_barcode.zzcc;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes2.dex */
public class BarcodeRegistrar implements ComponentRegistrar {
    public static final /* synthetic */ int zza = 0;

    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        return zzcc.zzi(Component.builder(zzf.class).add(Dependency.required((Class<?>) MlKitContext.class)).factory(new ComponentFactory() { // from class: com.google.mlkit.vision.barcode.internal.zzc
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                int i = BarcodeRegistrar.zza;
                return new zzf((MlKitContext) componentContainer.get(MlKitContext.class));
            }
        }).build(), Component.builder(zze.class).add(Dependency.required((Class<?>) zzf.class)).add(Dependency.required((Class<?>) ExecutorSelector.class)).factory(new ComponentFactory() { // from class: com.google.mlkit.vision.barcode.internal.zzd
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return new zze((zzf) componentContainer.get(zzf.class), (ExecutorSelector) componentContainer.get(ExecutorSelector.class));
            }
        }).build());
    }
}
