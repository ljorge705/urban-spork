package com.onfido.android.sdk.capture.ui.camera.document;

import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.internal.camera.factory.CameraFactory;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.service.VibratorService;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DocumentCaptureFragment_MembersInjector implements MembersInjector<DocumentCaptureFragment> {
    private final Provider<AnnouncementService> announcementServiceProvider;
    private final Provider<CameraFactory> cameraFactoryProvider;
    private final Provider<ImageUtils> imageUtilsProvider;
    private final Provider<RuntimePermissionsManager> permissionsManagerProvider;
    private final Provider<VibratorService> vibratorServiceProvider;
    private final Provider<DocumentCaptureViewModel> viewModelProvider;

    public DocumentCaptureFragment_MembersInjector(Provider<DocumentCaptureViewModel> provider, Provider<RuntimePermissionsManager> provider2, Provider<AnnouncementService> provider3, Provider<VibratorService> provider4, Provider<ImageUtils> provider5, Provider<CameraFactory> provider6) {
        this.viewModelProvider = provider;
        this.permissionsManagerProvider = provider2;
        this.announcementServiceProvider = provider3;
        this.vibratorServiceProvider = provider4;
        this.imageUtilsProvider = provider5;
        this.cameraFactoryProvider = provider6;
    }

    public static MembersInjector<DocumentCaptureFragment> create(Provider<DocumentCaptureViewModel> provider, Provider<RuntimePermissionsManager> provider2, Provider<AnnouncementService> provider3, Provider<VibratorService> provider4, Provider<ImageUtils> provider5, Provider<CameraFactory> provider6) {
        return new DocumentCaptureFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectAnnouncementService(DocumentCaptureFragment documentCaptureFragment, AnnouncementService announcementService) {
        documentCaptureFragment.announcementService = announcementService;
    }

    public static void injectCameraFactory(DocumentCaptureFragment documentCaptureFragment, CameraFactory cameraFactory) {
        documentCaptureFragment.cameraFactory = cameraFactory;
    }

    public static void injectImageUtils(DocumentCaptureFragment documentCaptureFragment, ImageUtils imageUtils) {
        documentCaptureFragment.imageUtils = imageUtils;
    }

    public static void injectPermissionsManager(DocumentCaptureFragment documentCaptureFragment, RuntimePermissionsManager runtimePermissionsManager) {
        documentCaptureFragment.permissionsManager = runtimePermissionsManager;
    }

    public static void injectVibratorService(DocumentCaptureFragment documentCaptureFragment, VibratorService vibratorService) {
        documentCaptureFragment.vibratorService = vibratorService;
    }

    public static void injectViewModelProvider(DocumentCaptureFragment documentCaptureFragment, Provider<DocumentCaptureViewModel> provider) {
        documentCaptureFragment.viewModelProvider = provider;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(DocumentCaptureFragment documentCaptureFragment) {
        injectViewModelProvider(documentCaptureFragment, this.viewModelProvider);
        injectPermissionsManager(documentCaptureFragment, this.permissionsManagerProvider.get());
        injectAnnouncementService(documentCaptureFragment, this.announcementServiceProvider.get());
        injectVibratorService(documentCaptureFragment, this.vibratorServiceProvider.get());
        injectImageUtils(documentCaptureFragment, this.imageUtilsProvider.get());
        injectCameraFactory(documentCaptureFragment, this.cameraFactoryProvider.get());
    }
}
