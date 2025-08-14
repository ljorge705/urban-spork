package com.onfido.hosted.web.module.di;

import com.onfido.dagger.Binds;
import com.onfido.dagger.Module;
import com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepository;
import com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepositoryImpl;
import kotlin.Metadata;

@Module
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\ba\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\u0006"}, d2 = {"Lcom/onfido/hosted/web/module/di/HostedWebModuleBindsModule;", "", "provideExternalLinkRepository", "Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkRepository;", "it", "Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkRepositoryImpl;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface HostedWebModuleBindsModule {
    @Binds
    HostedWebModuleExternalLinkRepository provideExternalLinkRepository(HostedWebModuleExternalLinkRepositoryImpl it);
}
