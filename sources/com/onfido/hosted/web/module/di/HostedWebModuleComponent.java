package com.onfido.hosted.web.module.di;

import com.onfido.android.sdk.capture.common.di.FragmentScope;
import com.onfido.android.sdk.capture.common.di.SdkComponent;
import com.onfido.dagger.Component;
import com.onfido.hosted.web.module.HostedWebModuleFragment;
import com.onfido.hosted.web.module.HostedWebModuleViewModel;
import com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment;
import com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkViewModel;
import io.sentry.protocol.Request;
import kotlin.Metadata;

@FragmentScope
@Component(dependencies = {SdkComponent.class}, modules = {HostedWebModuleBindsModule.class})
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\ba\u0018\u00002\u00020\u0001:\u0001\u000fJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000eH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/onfido/hosted/web/module/di/HostedWebModuleComponent;", "", "hostedWebModuleExternalLinkViewModel", "Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkViewModel;", "getHostedWebModuleExternalLinkViewModel", "()Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkViewModel;", "hostedWebModuleViewModel", "Lcom/onfido/hosted/web/module/HostedWebModuleViewModel;", "getHostedWebModuleViewModel", "()Lcom/onfido/hosted/web/module/HostedWebModuleViewModel;", "inject", "", Request.JsonKeys.FRAGMENT, "Lcom/onfido/hosted/web/module/HostedWebModuleFragment;", "Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkFragment;", "Factory", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface HostedWebModuleComponent {

    @Component.Factory
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/hosted/web/module/di/HostedWebModuleComponent$Factory;", "", "create", "Lcom/onfido/hosted/web/module/di/HostedWebModuleComponent;", "appComponent", "Lcom/onfido/android/sdk/capture/common/di/SdkComponent;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        HostedWebModuleComponent create(SdkComponent appComponent);
    }

    HostedWebModuleExternalLinkViewModel getHostedWebModuleExternalLinkViewModel();

    HostedWebModuleViewModel getHostedWebModuleViewModel();

    void inject(HostedWebModuleFragment fragment);

    void inject(HostedWebModuleExternalLinkFragment fragment);
}
