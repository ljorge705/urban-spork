package com.google.mlkit.vision.text.internal;

import com.google.android.gms.internal.mlkit_vision_text_common.zzbm;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes2.dex */
public class TextRegistrar implements ComponentRegistrar {
    public static final /* synthetic */ int zza = 0;

    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        return zzbm.zzj(Component.builder(zzl.class).add(Dependency.required((Class<?>) MlKitContext.class)).factory(new ComponentFactory() { // from class: com.google.mlkit.vision.text.internal.zzo
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                int i = TextRegistrar.zza;
                return new zzl((MlKitContext) componentContainer.get(MlKitContext.class));
            }
        }).build(), Component.builder(zzk.class).add(Dependency.required((Class<?>) zzl.class)).add(Dependency.required((Class<?>) ExecutorSelector.class)).factory(new ComponentFactory() { // from class: com.google.mlkit.vision.text.internal.zzp
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return new zzk((zzl) componentContainer.get(zzl.class), (ExecutorSelector) componentContainer.get(ExecutorSelector.class));
            }
        }).build());
    }
}
