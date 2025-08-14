package com.onfido.android.sdk.capture.component.active.video.capture.data.remote;

import com.onfido.android.sdk.capture.common.cryptography.PayloadHelper;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionRemoteDataSource_Factory implements Factory<MotionRemoteDataSource> {
    private final Provider<MotionApi> motionApiProvider;
    private final Provider<MotionCaptureVariantOptions> motionCaptureVariantOptionsProvider;
    private final Provider<MotionMetaDataHelper> motionMetaDataHelperProvider;
    private final Provider<PayloadHelper> payloadHelperProvider;

    public MotionRemoteDataSource_Factory(Provider<MotionCaptureVariantOptions> provider, Provider<MotionApi> provider2, Provider<PayloadHelper> provider3, Provider<MotionMetaDataHelper> provider4) {
        this.motionCaptureVariantOptionsProvider = provider;
        this.motionApiProvider = provider2;
        this.payloadHelperProvider = provider3;
        this.motionMetaDataHelperProvider = provider4;
    }

    public static MotionRemoteDataSource_Factory create(Provider<MotionCaptureVariantOptions> provider, Provider<MotionApi> provider2, Provider<PayloadHelper> provider3, Provider<MotionMetaDataHelper> provider4) {
        return new MotionRemoteDataSource_Factory(provider, provider2, provider3, provider4);
    }

    public static MotionRemoteDataSource newInstance(MotionCaptureVariantOptions motionCaptureVariantOptions, MotionApi motionApi, PayloadHelper payloadHelper, MotionMetaDataHelper motionMetaDataHelper) {
        return new MotionRemoteDataSource(motionCaptureVariantOptions, motionApi, payloadHelper, motionMetaDataHelper);
    }

    @Override // com.onfido.javax.inject.Provider
    public MotionRemoteDataSource get() {
        return newInstance(this.motionCaptureVariantOptionsProvider.get(), this.motionApiProvider.get(), this.payloadHelperProvider.get(), this.motionMetaDataHelperProvider.get());
    }
}
