package com.onfido.api.client;

import com.onfido.api.client.data.DeviceInfo;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.api.client.data.SdkConfigurationRequestBody;
import io.reactivex.rxjava3.core.Single;

/* loaded from: classes6.dex */
public class SdkConfigurationAPI {
    private final OnfidoService onfidoService;

    SdkConfigurationAPI(OnfidoService onfidoService) {
        this.onfidoService = onfidoService;
    }

    Single<SdkConfiguration> getConfiguration(String str, String str2, DeviceInfo deviceInfo) {
        return this.onfidoService.getSdkConfiguration(new SdkConfigurationRequestBody(str, str2, deviceInfo));
    }
}
