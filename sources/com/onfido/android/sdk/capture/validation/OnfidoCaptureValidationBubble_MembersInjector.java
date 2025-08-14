package com.onfido.android.sdk.capture.validation;

import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class OnfidoCaptureValidationBubble_MembersInjector implements MembersInjector<OnfidoCaptureValidationBubble> {
    private final Provider<AnnouncementService> announcementServiceProvider;
    private final Provider<CaptureValidationBubblePresenter.Factory> captureValidationBubblePresenterFactoryProvider;

    public OnfidoCaptureValidationBubble_MembersInjector(Provider<CaptureValidationBubblePresenter.Factory> provider, Provider<AnnouncementService> provider2) {
        this.captureValidationBubblePresenterFactoryProvider = provider;
        this.announcementServiceProvider = provider2;
    }

    public static MembersInjector<OnfidoCaptureValidationBubble> create(Provider<CaptureValidationBubblePresenter.Factory> provider, Provider<AnnouncementService> provider2) {
        return new OnfidoCaptureValidationBubble_MembersInjector(provider, provider2);
    }

    public static void injectAnnouncementService(OnfidoCaptureValidationBubble onfidoCaptureValidationBubble, AnnouncementService announcementService) {
        onfidoCaptureValidationBubble.announcementService = announcementService;
    }

    public static void injectCaptureValidationBubblePresenterFactory(OnfidoCaptureValidationBubble onfidoCaptureValidationBubble, CaptureValidationBubblePresenter.Factory factory) {
        onfidoCaptureValidationBubble.captureValidationBubblePresenterFactory = factory;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(OnfidoCaptureValidationBubble onfidoCaptureValidationBubble) {
        injectCaptureValidationBubblePresenterFactory(onfidoCaptureValidationBubble, this.captureValidationBubblePresenterFactoryProvider.get());
        injectAnnouncementService(onfidoCaptureValidationBubble, this.announcementServiceProvider.get());
    }
}
