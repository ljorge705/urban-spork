package com.onfido.android.sdk.capture.validation;

import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import com.onfido.javax.inject.Provider;

/* renamed from: com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0735CaptureValidationBubblePresenter_Factory {
    private final Provider<AnnouncementService> announcementServiceProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public C0735CaptureValidationBubblePresenter_Factory(Provider<AnnouncementService> provider, Provider<SchedulersProvider> provider2) {
        this.announcementServiceProvider = provider;
        this.schedulersProvider = provider2;
    }

    public static C0735CaptureValidationBubblePresenter_Factory create(Provider<AnnouncementService> provider, Provider<SchedulersProvider> provider2) {
        return new C0735CaptureValidationBubblePresenter_Factory(provider, provider2);
    }

    public static CaptureValidationBubblePresenter newInstance(AnnouncementService announcementService, SchedulersProvider schedulersProvider, OnfidoCaptureValidationBubble.BubbleMode bubbleMode, CaptureValidationBubblePresenter.View view) {
        return new CaptureValidationBubblePresenter(announcementService, schedulersProvider, bubbleMode, view);
    }

    public CaptureValidationBubblePresenter get(OnfidoCaptureValidationBubble.BubbleMode bubbleMode, CaptureValidationBubblePresenter.View view) {
        return newInstance(this.announcementServiceProvider.get(), this.schedulersProvider.get(), bubbleMode, view);
    }
}
