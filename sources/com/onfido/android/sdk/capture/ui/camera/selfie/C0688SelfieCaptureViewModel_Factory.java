package com.onfido.android.sdk.capture.ui.camera.selfie;

import androidx.lifecycle.SavedStateHandle;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.metadata.SdkUploadMetadataHelper;
import com.onfido.android.sdk.capture.internal.usecase.MediaCallbacksUseCase;
import com.onfido.android.sdk.capture.ui.camera.CaptureUploadService;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.javax.inject.Provider;

/* renamed from: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureViewModel_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0688SelfieCaptureViewModel_Factory {
    private final Provider<MediaCallbacksUseCase> mediaCallbacksUseCaseProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<SdkUploadMetadataHelper> sdkUploadMetaDataHelperProvider;
    private final Provider<SharedPreferencesDataSource> storageProvider;
    private final Provider<TimeProvider> timeProvider;
    private final Provider<SelfieCaptureTracker> trackerProvider;
    private final Provider<CaptureUploadService.Factory> uploadServiceFactoryProvider;

    public C0688SelfieCaptureViewModel_Factory(Provider<CaptureUploadService.Factory> provider, Provider<TimeProvider> provider2, Provider<SelfieCaptureTracker> provider3, Provider<SharedPreferencesDataSource> provider4, Provider<OnfidoRemoteConfig> provider5, Provider<SdkUploadMetadataHelper> provider6, Provider<MediaCallbacksUseCase> provider7) {
        this.uploadServiceFactoryProvider = provider;
        this.timeProvider = provider2;
        this.trackerProvider = provider3;
        this.storageProvider = provider4;
        this.onfidoRemoteConfigProvider = provider5;
        this.sdkUploadMetaDataHelperProvider = provider6;
        this.mediaCallbacksUseCaseProvider = provider7;
    }

    public static C0688SelfieCaptureViewModel_Factory create(Provider<CaptureUploadService.Factory> provider, Provider<TimeProvider> provider2, Provider<SelfieCaptureTracker> provider3, Provider<SharedPreferencesDataSource> provider4, Provider<OnfidoRemoteConfig> provider5, Provider<SdkUploadMetadataHelper> provider6, Provider<MediaCallbacksUseCase> provider7) {
        return new C0688SelfieCaptureViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static SelfieCaptureViewModel newInstance(CaptureUploadService.Factory factory, TimeProvider timeProvider, SelfieCaptureTracker selfieCaptureTracker, SharedPreferencesDataSource sharedPreferencesDataSource, OnfidoRemoteConfig onfidoRemoteConfig, SdkUploadMetadataHelper sdkUploadMetadataHelper, MediaCallbacksUseCase mediaCallbacksUseCase, SavedStateHandle savedStateHandle) {
        return new SelfieCaptureViewModel(factory, timeProvider, selfieCaptureTracker, sharedPreferencesDataSource, onfidoRemoteConfig, sdkUploadMetadataHelper, mediaCallbacksUseCase, savedStateHandle);
    }

    public SelfieCaptureViewModel get(SavedStateHandle savedStateHandle) {
        return newInstance(this.uploadServiceFactoryProvider.get(), this.timeProvider.get(), this.trackerProvider.get(), this.storageProvider.get(), this.onfidoRemoteConfigProvider.get(), this.sdkUploadMetaDataHelperProvider.get(), this.mediaCallbacksUseCaseProvider.get(), savedStateHandle);
    }
}
