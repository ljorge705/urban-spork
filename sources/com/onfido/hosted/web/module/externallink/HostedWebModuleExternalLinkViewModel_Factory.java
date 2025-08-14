package com.onfido.hosted.web.module.externallink;

import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class HostedWebModuleExternalLinkViewModel_Factory implements Factory<HostedWebModuleExternalLinkViewModel> {
    private final Provider<HostedWebModuleExternalLinkRepository> repositoryProvider;

    public HostedWebModuleExternalLinkViewModel_Factory(Provider<HostedWebModuleExternalLinkRepository> provider) {
        this.repositoryProvider = provider;
    }

    public static HostedWebModuleExternalLinkViewModel_Factory create(Provider<HostedWebModuleExternalLinkRepository> provider) {
        return new HostedWebModuleExternalLinkViewModel_Factory(provider);
    }

    public static HostedWebModuleExternalLinkViewModel newInstance(HostedWebModuleExternalLinkRepository hostedWebModuleExternalLinkRepository) {
        return new HostedWebModuleExternalLinkViewModel(hostedWebModuleExternalLinkRepository);
    }

    @Override // com.onfido.javax.inject.Provider
    public HostedWebModuleExternalLinkViewModel get() {
        return newInstance(this.repositoryProvider.get());
    }
}
