package com.google.firebase.storage;

import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class StorageRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-gcs";

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(Component.builder(FirebaseStorageComponent.class).name(LIBRARY_NAME).add(Dependency.required((Class<?>) FirebaseApp.class)).add(Dependency.optionalProvider((Class<?>) InternalAuthProvider.class)).add(Dependency.optionalProvider((Class<?>) InteropAppCheckTokenProvider.class)).factory(new ComponentFactory() { // from class: com.google.firebase.storage.StorageRegistrar$$ExternalSyntheticLambda0
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return StorageRegistrar.lambda$getComponents$0(componentContainer);
            }
        }).build(), LibraryVersionComponent.create(LIBRARY_NAME, BuildConfig.VERSION_NAME));
    }

    static /* synthetic */ FirebaseStorageComponent lambda$getComponents$0(ComponentContainer componentContainer) {
        return new FirebaseStorageComponent((FirebaseApp) componentContainer.get(FirebaseApp.class), componentContainer.getProvider(InternalAuthProvider.class), componentContainer.getProvider(InteropAppCheckTokenProvider.class));
    }
}
