package com.onfido.android.sdk.capture.core.di;

import com.onfido.android.sdk.capture.common.di.SdkComponent;
import com.onfido.android.sdk.capture.core.OnfidoFragment;
import com.onfido.android.sdk.capture.core.OnfidoFragment_MembersInjector;
import com.onfido.android.sdk.capture.core.OnfidoViewModel;
import com.onfido.android.sdk.capture.core.di.OnfidoComponent;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class DaggerOnfidoComponent {

    private static final class Factory implements OnfidoComponent.Factory {
        private Factory() {
        }

        @Override // com.onfido.android.sdk.capture.core.di.OnfidoComponent.Factory
        public OnfidoComponent create(SdkComponent sdkComponent) {
            Preconditions.checkNotNull(sdkComponent);
            return new OnfidoComponentImpl(sdkComponent);
        }
    }

    private static final class OnfidoComponentImpl extends OnfidoComponent {
        private final OnfidoComponentImpl onfidoComponentImpl;
        private final SdkComponent sdkComponent;

        private OnfidoComponentImpl(SdkComponent sdkComponent) {
            this.onfidoComponentImpl = this;
            this.sdkComponent = sdkComponent;
        }

        private OnfidoFragment injectOnfidoFragment(OnfidoFragment onfidoFragment) {
            OnfidoFragment_MembersInjector.injectViewModel(onfidoFragment, getOnfidoViewModel$onfido_capture_sdk_core_release());
            return onfidoFragment;
        }

        @Override // com.onfido.android.sdk.capture.core.di.OnfidoComponent
        public OnfidoViewModel getOnfidoViewModel$onfido_capture_sdk_core_release() {
            return new OnfidoViewModel(this.sdkComponent.flowConfig());
        }

        @Override // com.onfido.android.sdk.capture.core.di.OnfidoComponent
        public void inject$onfido_capture_sdk_core_release(OnfidoFragment onfidoFragment) {
            injectOnfidoFragment(onfidoFragment);
        }
    }

    private DaggerOnfidoComponent() {
    }

    public static OnfidoComponent.Factory factory() {
        return new Factory();
    }
}
