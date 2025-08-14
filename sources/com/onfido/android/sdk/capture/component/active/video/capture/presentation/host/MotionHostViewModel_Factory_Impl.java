package com.onfido.android.sdk.capture.component.active.video.capture.presentation.host;

import androidx.lifecycle.SavedStateHandle;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostViewModel;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionHostViewModel_Factory_Impl implements MotionHostViewModel.Factory {
    private final C0569MotionHostViewModel_Factory delegateFactory;

    MotionHostViewModel_Factory_Impl(C0569MotionHostViewModel_Factory c0569MotionHostViewModel_Factory) {
        this.delegateFactory = c0569MotionHostViewModel_Factory;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostViewModel.Factory
    public MotionHostViewModel create(SavedStateHandle savedStateHandle, boolean z, boolean z2) {
        return this.delegateFactory.get(savedStateHandle, z, z2);
    }

    public static Provider<MotionHostViewModel.Factory> create(C0569MotionHostViewModel_Factory c0569MotionHostViewModel_Factory) {
        return InstanceFactory.create(new MotionHostViewModel_Factory_Impl(c0569MotionHostViewModel_Factory));
    }
}
