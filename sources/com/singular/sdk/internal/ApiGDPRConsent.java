package com.singular.sdk.internal;

import com.singular.sdk.SingularConfig;
import com.singular.sdk.internal.Api;
import java.io.IOException;

/* loaded from: classes6.dex */
public class ApiGDPRConsent extends BaseApi {
    private static final SingularLog logger = SingularLog.getLogger("ApiGDPRConsent");
    static final String path = "/opengdpr";

    @Override // com.singular.sdk.internal.Api
    public String getPath() {
        return path;
    }

    @Override // com.singular.sdk.internal.BaseApi, com.singular.sdk.internal.Api
    public /* bridge */ /* synthetic */ long getTimestamp() {
        return super.getTimestamp();
    }

    @Override // com.singular.sdk.internal.BaseApi, com.singular.sdk.internal.Api
    public /* bridge */ /* synthetic */ String getType() {
        return super.getType();
    }

    @Override // com.singular.sdk.internal.BaseApi
    public /* bridge */ /* synthetic */ String getUrl() {
        return super.getUrl();
    }

    @Override // com.singular.sdk.internal.BaseApi
    public /* bridge */ /* synthetic */ boolean isAdmonEvent() {
        return super.isAdmonEvent();
    }

    @Override // com.singular.sdk.internal.BaseApi, com.singular.sdk.internal.Api
    public /* bridge */ /* synthetic */ boolean makeRequest(SingularInstance singularInstance) throws IOException {
        return super.makeRequest(singularInstance);
    }

    @Override // com.singular.sdk.internal.BaseApi, com.singular.sdk.internal.Api
    public /* bridge */ /* synthetic */ String toJsonAsString() {
        return super.toJsonAsString();
    }

    ApiGDPRConsent(long j) {
        super(Constants.API_TYPE_GDPR_CONSENT, j);
    }

    @Override // com.singular.sdk.internal.Api
    public Api.OnApiCallback getOnApiCallback() {
        return new OnResolveCallback();
    }

    public class OnResolveCallback implements Api.OnApiCallback {
        @Override // com.singular.sdk.internal.Api.OnApiCallback
        public boolean handle(SingularInstance singularInstance, int i, String str) {
            return i == 200;
        }

        public OnResolveCallback() {
        }
    }

    static class Params extends SingularParamsBase {
        private Params() {
        }

        static Params build(SingularInstance singularInstance) {
            return new Params().withBaseParams().withSingularConfig(singularInstance.getSingularConfig()).withDeviceInfo(singularInstance.getDeviceInfo());
        }

        private Params withBaseParams() {
            put("op", "consent");
            return this;
        }

        private Params withSingularConfig(SingularConfig singularConfig) {
            put("a", singularConfig.apiKey);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.singular.sdk.internal.SingularParamsBase
        public Params withDeviceInfo(DeviceInfo deviceInfo) {
            super.withDeviceInfo(deviceInfo);
            put("sdk", Utils.getSdkVersion());
            return this;
        }
    }
}
