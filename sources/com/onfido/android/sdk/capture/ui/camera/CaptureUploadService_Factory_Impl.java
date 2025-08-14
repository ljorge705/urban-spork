package com.onfido.android.sdk.capture.ui.camera;

import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.camera.CaptureUploadService;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class CaptureUploadService_Factory_Impl implements CaptureUploadService.Factory {
    private final C0612CaptureUploadService_Factory delegateFactory;

    CaptureUploadService_Factory_Impl(C0612CaptureUploadService_Factory c0612CaptureUploadService_Factory) {
        this.delegateFactory = c0612CaptureUploadService_Factory;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CaptureUploadService.Factory
    public CaptureUploadService create(CaptureType captureType, CaptureUploadServiceListener captureUploadServiceListener) {
        return this.delegateFactory.get(captureType, captureUploadServiceListener);
    }

    public static Provider<CaptureUploadService.Factory> create(C0612CaptureUploadService_Factory c0612CaptureUploadService_Factory) {
        return InstanceFactory.create(new CaptureUploadService_Factory_Impl(c0612CaptureUploadService_Factory));
    }
}
