package com.onfido.android.sdk.capture.internal.nfc;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.model.NFCOptionsKt;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import com.onfido.android.sdk.capture.utils.StringExtensionsKt;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/ShouldLaunchNfcFlowUseCase;", "", "nfcInteractor", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "(Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;Lcom/onfido/android/sdk/capture/OnfidoConfig;)V", "invoke", "", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ShouldLaunchNfcFlowUseCase {
    private final NfcInteractor nfcInteractor;
    private final OnfidoConfig onfidoConfig;

    @Inject
    public ShouldLaunchNfcFlowUseCase(NfcInteractor nfcInteractor, OnfidoConfig onfidoConfig) {
        Intrinsics.checkNotNullParameter(nfcInteractor, "nfcInteractor");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        this.nfcInteractor = nfcInteractor;
        this.onfidoConfig = onfidoConfig;
    }

    public final boolean invoke(NfcProperties nfcProperties) {
        if (NFCOptionsKt.isRequired(this.onfidoConfig.getNfcOptions())) {
            return true;
        }
        return this.nfcInteractor.isNfcSupported() && NFCOptionsKt.isEnabled(this.onfidoConfig.getNfcOptions()) && ((nfcProperties != null && nfcProperties.getIsNfcSupported()) && (StringExtensionsKt.isNotNullOrEmpty(nfcProperties != null ? nfcProperties.getNfcKey() : null) || (nfcProperties != null && nfcProperties.getHasCan())));
    }
}
