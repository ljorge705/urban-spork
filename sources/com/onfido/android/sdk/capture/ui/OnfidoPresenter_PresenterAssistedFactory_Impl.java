package com.onfido.android.sdk.capture.ui;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.ui.OnfidoPresenter;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class OnfidoPresenter_PresenterAssistedFactory_Impl implements OnfidoPresenter.PresenterAssistedFactory {
    private final C0588OnfidoPresenter_Factory delegateFactory;

    OnfidoPresenter_PresenterAssistedFactory_Impl(C0588OnfidoPresenter_Factory c0588OnfidoPresenter_Factory) {
        this.delegateFactory = c0588OnfidoPresenter_Factory;
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoPresenter.Factory
    public OnfidoPresenter create(OnfidoConfig onfidoConfig) {
        return this.delegateFactory.get(onfidoConfig);
    }

    public static Provider<OnfidoPresenter.PresenterAssistedFactory> create(C0588OnfidoPresenter_Factory c0588OnfidoPresenter_Factory) {
        return InstanceFactory.create(new OnfidoPresenter_PresenterAssistedFactory_Impl(c0588OnfidoPresenter_Factory));
    }
}
