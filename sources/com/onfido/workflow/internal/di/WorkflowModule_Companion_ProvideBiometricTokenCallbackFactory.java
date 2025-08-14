package com.onfido.workflow.internal.di;

import android.os.ResultReceiver;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class WorkflowModule_Companion_ProvideBiometricTokenCallbackFactory implements Factory<ResultReceiver> {
    private final Provider<OnfidoConfig> onfidoConfigProvider;

    public WorkflowModule_Companion_ProvideBiometricTokenCallbackFactory(Provider<OnfidoConfig> provider) {
        this.onfidoConfigProvider = provider;
    }

    @Override // com.onfido.javax.inject.Provider
    public ResultReceiver get() {
        return provideBiometricTokenCallback(this.onfidoConfigProvider.get());
    }

    public static WorkflowModule_Companion_ProvideBiometricTokenCallbackFactory create(Provider<OnfidoConfig> provider) {
        return new WorkflowModule_Companion_ProvideBiometricTokenCallbackFactory(provider);
    }

    public static ResultReceiver provideBiometricTokenCallback(OnfidoConfig onfidoConfig) {
        return WorkflowModule.INSTANCE.provideBiometricTokenCallback(onfidoConfig);
    }
}
