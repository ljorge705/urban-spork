package com.onfido.android.sdk.capture.component.active.video.capture.di.host;

import com.onfido.android.sdk.capture.internal.metadata.SdkUploadMetadataHelper;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionHostModule_ProvideUploadMetadataFactory implements Factory<SdkUploadMetaData> {
    private final MotionHostModule module;
    private final Provider<SdkUploadMetadataHelper> sdkUploadMetadataHelperProvider;

    public MotionHostModule_ProvideUploadMetadataFactory(MotionHostModule motionHostModule, Provider<SdkUploadMetadataHelper> provider) {
        this.module = motionHostModule;
        this.sdkUploadMetadataHelperProvider = provider;
    }

    public static MotionHostModule_ProvideUploadMetadataFactory create(MotionHostModule motionHostModule, Provider<SdkUploadMetadataHelper> provider) {
        return new MotionHostModule_ProvideUploadMetadataFactory(motionHostModule, provider);
    }

    public static SdkUploadMetaData provideUploadMetadata(MotionHostModule motionHostModule, SdkUploadMetadataHelper sdkUploadMetadataHelper) {
        return (SdkUploadMetaData) Preconditions.checkNotNullFromProvides(motionHostModule.provideUploadMetadata(sdkUploadMetadataHelper));
    }

    @Override // com.onfido.javax.inject.Provider
    public SdkUploadMetaData get() {
        return provideUploadMetadata(this.module, this.sdkUploadMetadataHelperProvider.get());
    }
}
