package com.onfido.android.sdk.capture.ui.camera;

import com.onfido.android.sdk.capture.common.cryptography.Cryptography;
import com.onfido.android.sdk.capture.common.cryptography.PayloadHelper;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.javax.inject.Provider;

/* renamed from: com.onfido.android.sdk.capture.ui.camera.CaptureUploadService_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0612CaptureUploadService_Factory {
    private final Provider<Cryptography> cryptographyProvider;
    private final Provider<OnfidoApiService> onfidoApiServiceProvider;
    private final Provider<PayloadHelper> payloadHelperProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public C0612CaptureUploadService_Factory(Provider<OnfidoApiService> provider, Provider<SchedulersProvider> provider2, Provider<Cryptography> provider3, Provider<PayloadHelper> provider4) {
        this.onfidoApiServiceProvider = provider;
        this.schedulersProvider = provider2;
        this.cryptographyProvider = provider3;
        this.payloadHelperProvider = provider4;
    }

    public static C0612CaptureUploadService_Factory create(Provider<OnfidoApiService> provider, Provider<SchedulersProvider> provider2, Provider<Cryptography> provider3, Provider<PayloadHelper> provider4) {
        return new C0612CaptureUploadService_Factory(provider, provider2, provider3, provider4);
    }

    public static CaptureUploadService newInstance(CaptureType captureType, OnfidoApiService onfidoApiService, CaptureUploadServiceListener captureUploadServiceListener, SchedulersProvider schedulersProvider, Cryptography cryptography, PayloadHelper payloadHelper) {
        return new CaptureUploadService(captureType, onfidoApiService, captureUploadServiceListener, schedulersProvider, cryptography, payloadHelper);
    }

    public CaptureUploadService get(CaptureType captureType, CaptureUploadServiceListener captureUploadServiceListener) {
        return newInstance(captureType, this.onfidoApiServiceProvider.get(), captureUploadServiceListener, this.schedulersProvider.get(), this.cryptographyProvider.get(), this.payloadHelperProvider.get());
    }
}
