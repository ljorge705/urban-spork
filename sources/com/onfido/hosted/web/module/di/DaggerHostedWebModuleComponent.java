package com.onfido.hosted.web.module.di;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.common.di.SdkComponent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.hosted.web.module.HostedWebModuleFragment;
import com.onfido.hosted.web.module.HostedWebModuleViewModel;
import com.onfido.hosted.web.module.di.HostedWebModuleComponent;
import com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment;
import com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkRepositoryImpl;
import com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkViewModel;
import kotlinx.serialization.json.Json;
import okhttp3.OkHttpClient;

/* loaded from: classes6.dex */
public final class DaggerHostedWebModuleComponent {

    private static final class Factory implements HostedWebModuleComponent.Factory {
        private Factory() {
        }

        @Override // com.onfido.hosted.web.module.di.HostedWebModuleComponent.Factory
        public HostedWebModuleComponent create(SdkComponent sdkComponent) {
            Preconditions.checkNotNull(sdkComponent);
            return new HostedWebModuleComponentImpl(sdkComponent);
        }
    }

    private static final class HostedWebModuleComponentImpl implements HostedWebModuleComponent {
        private final HostedWebModuleComponentImpl hostedWebModuleComponentImpl;
        private final SdkComponent sdkComponent;

        private HostedWebModuleComponentImpl(SdkComponent sdkComponent) {
            this.hostedWebModuleComponentImpl = this;
            this.sdkComponent = sdkComponent;
        }

        private HostedWebModuleExternalLinkRepositoryImpl hostedWebModuleExternalLinkRepositoryImpl() {
            return new HostedWebModuleExternalLinkRepositoryImpl((OkHttpClient) Preconditions.checkNotNullFromComponent(this.sdkComponent.getOkHttpClient()));
        }

        @Override // com.onfido.hosted.web.module.di.HostedWebModuleComponent
        public HostedWebModuleExternalLinkViewModel getHostedWebModuleExternalLinkViewModel() {
            return new HostedWebModuleExternalLinkViewModel(hostedWebModuleExternalLinkRepositoryImpl());
        }

        @Override // com.onfido.hosted.web.module.di.HostedWebModuleComponent
        public HostedWebModuleViewModel getHostedWebModuleViewModel() {
            return new HostedWebModuleViewModel((OnfidoConfig) Preconditions.checkNotNullFromComponent(this.sdkComponent.onfidoConfig()), (Json) Preconditions.checkNotNullFromComponent(this.sdkComponent.getJsonParser()), (OnfidoRemoteConfig) Preconditions.checkNotNullFromComponent(this.sdkComponent.onfidoRemoteConfig()), (FlowTracker) Preconditions.checkNotNullFromComponent(this.sdkComponent.flowTracker()), (OnfidoAnalytics) Preconditions.checkNotNullFromComponent(this.sdkComponent.onfidoAnalytics()));
        }

        @Override // com.onfido.hosted.web.module.di.HostedWebModuleComponent
        public void inject(HostedWebModuleFragment hostedWebModuleFragment) {
        }

        @Override // com.onfido.hosted.web.module.di.HostedWebModuleComponent
        public void inject(HostedWebModuleExternalLinkFragment hostedWebModuleExternalLinkFragment) {
        }
    }

    private DaggerHostedWebModuleComponent() {
    }

    public static HostedWebModuleComponent.Factory factory() {
        return new Factory();
    }
}
