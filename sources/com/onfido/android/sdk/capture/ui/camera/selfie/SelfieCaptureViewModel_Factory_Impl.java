package com.onfido.android.sdk.capture.ui.camera.selfie;

import androidx.lifecycle.SavedStateHandle;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureViewModel;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SelfieCaptureViewModel_Factory_Impl implements SelfieCaptureViewModel.Factory {
    private final C0688SelfieCaptureViewModel_Factory delegateFactory;

    SelfieCaptureViewModel_Factory_Impl(C0688SelfieCaptureViewModel_Factory c0688SelfieCaptureViewModel_Factory) {
        this.delegateFactory = c0688SelfieCaptureViewModel_Factory;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureViewModel.Factory
    public SelfieCaptureViewModel create(SavedStateHandle savedStateHandle) {
        return this.delegateFactory.get(savedStateHandle);
    }

    public static Provider<SelfieCaptureViewModel.Factory> create(C0688SelfieCaptureViewModel_Factory c0688SelfieCaptureViewModel_Factory) {
        return InstanceFactory.create(new SelfieCaptureViewModel_Factory_Impl(c0688SelfieCaptureViewModel_Factory));
    }
}
