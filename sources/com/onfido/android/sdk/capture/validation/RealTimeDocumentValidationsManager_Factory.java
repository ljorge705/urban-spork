package com.onfido.android.sdk.capture.validation;

import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class RealTimeDocumentValidationsManager_Factory implements Factory<RealTimeDocumentValidationsManager> {
    private final Provider<IdentityInteractor> identityInteractorProvider;

    public RealTimeDocumentValidationsManager_Factory(Provider<IdentityInteractor> provider) {
        this.identityInteractorProvider = provider;
    }

    public static RealTimeDocumentValidationsManager_Factory create(Provider<IdentityInteractor> provider) {
        return new RealTimeDocumentValidationsManager_Factory(provider);
    }

    public static RealTimeDocumentValidationsManager newInstance(IdentityInteractor identityInteractor) {
        return new RealTimeDocumentValidationsManager(identityInteractor);
    }

    @Override // com.onfido.javax.inject.Provider
    public RealTimeDocumentValidationsManager get() {
        return newInstance(this.identityInteractorProvider.get());
    }
}
