package com.onfido.android.sdk.capture.ui.welcome;

import com.onfido.android.sdk.capture.ui.options.WelcomeScreenOptions;
import com.onfido.android.sdk.capture.ui.welcome.WelcomePresenter;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class WelcomePresenter_Factory_Impl implements WelcomePresenter.Factory {
    private final C0719WelcomePresenter_Factory delegateFactory;

    WelcomePresenter_Factory_Impl(C0719WelcomePresenter_Factory c0719WelcomePresenter_Factory) {
        this.delegateFactory = c0719WelcomePresenter_Factory;
    }

    @Override // com.onfido.android.sdk.capture.ui.welcome.WelcomePresenter.Factory
    public WelcomePresenter create(WelcomeScreenOptions welcomeScreenOptions) {
        return this.delegateFactory.get(welcomeScreenOptions);
    }

    public static Provider<WelcomePresenter.Factory> create(C0719WelcomePresenter_Factory c0719WelcomePresenter_Factory) {
        return InstanceFactory.create(new WelcomePresenter_Factory_Impl(c0719WelcomePresenter_Factory));
    }
}
