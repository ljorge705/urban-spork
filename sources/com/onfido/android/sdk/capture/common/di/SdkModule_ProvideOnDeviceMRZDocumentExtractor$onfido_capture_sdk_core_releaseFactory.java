package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractor;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractorEmpty;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractorGoogle;
import com.onfido.dagger.Lazy;
import com.onfido.dagger.internal.DoubleCheck;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideOnDeviceMRZDocumentExtractor$onfido_capture_sdk_core_releaseFactory implements Factory<MRZDocumentExtractor> {
    private final Provider<Context> contextProvider;
    private final SdkModule module;
    private final Provider<MRZDocumentExtractorEmpty> mrzDocumentExtractorEmptyProvider;
    private final Provider<MRZDocumentExtractorGoogle> mrzDocumentExtractorProvider;

    public SdkModule_ProvideOnDeviceMRZDocumentExtractor$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule, Provider<Context> provider, Provider<MRZDocumentExtractorGoogle> provider2, Provider<MRZDocumentExtractorEmpty> provider3) {
        this.module = sdkModule;
        this.contextProvider = provider;
        this.mrzDocumentExtractorProvider = provider2;
        this.mrzDocumentExtractorEmptyProvider = provider3;
    }

    public static SdkModule_ProvideOnDeviceMRZDocumentExtractor$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule, Provider<Context> provider, Provider<MRZDocumentExtractorGoogle> provider2, Provider<MRZDocumentExtractorEmpty> provider3) {
        return new SdkModule_ProvideOnDeviceMRZDocumentExtractor$onfido_capture_sdk_core_releaseFactory(sdkModule, provider, provider2, provider3);
    }

    public static MRZDocumentExtractor provideOnDeviceMRZDocumentExtractor$onfido_capture_sdk_core_release(SdkModule sdkModule, Context context, Lazy<MRZDocumentExtractorGoogle> lazy, Lazy<MRZDocumentExtractorEmpty> lazy2) {
        return (MRZDocumentExtractor) Preconditions.checkNotNullFromProvides(sdkModule.provideOnDeviceMRZDocumentExtractor$onfido_capture_sdk_core_release(context, lazy, lazy2));
    }

    @Override // com.onfido.javax.inject.Provider
    public MRZDocumentExtractor get() {
        return provideOnDeviceMRZDocumentExtractor$onfido_capture_sdk_core_release(this.module, this.contextProvider.get(), DoubleCheck.lazy(this.mrzDocumentExtractorProvider), DoubleCheck.lazy(this.mrzDocumentExtractorEmptyProvider));
    }
}
