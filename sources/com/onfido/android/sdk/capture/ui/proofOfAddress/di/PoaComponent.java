package com.onfido.android.sdk.capture.ui.proofOfAddress.di;

import com.onfido.android.sdk.capture.common.di.FragmentScope;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsFragment;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionFragment;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostFragment;
import com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress.PoaVerifyAddressFragment;
import com.onfido.dagger.Subcomponent;
import io.sentry.protocol.Request;
import kotlin.Metadata;

@FragmentScope
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\ba\u0018\u00002\u00020\u0001:\u0001\nJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0006H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\bH&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\tH&¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/di/PoaComponent;", "", "inject", "", Request.JsonKeys.FRAGMENT, "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentDetails/PoaDocumentDetailsFragment;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSelection/PoaDocumentSelectionFragment;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionFragment;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/host/PoaHostFragment;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/verifyAddress/PoaVerifyAddressFragment;", "Factory", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Subcomponent(modules = {PoaModule.class})
/* loaded from: classes2.dex */
public interface PoaComponent {

    @Subcomponent.Factory
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/di/PoaComponent$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/di/PoaComponent;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        PoaComponent create();
    }

    void inject(PoaDocumentDetailsFragment fragment);

    void inject(PoaDocumentSelectionFragment fragment);

    void inject(PoaDocumentSubmissionFragment fragment);

    void inject(PoaHostFragment fragment);

    void inject(PoaVerifyAddressFragment fragment);
}
