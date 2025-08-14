package com.onfido.android.sdk.capture.ui.documentselection.di;

import com.onfido.android.sdk.capture.common.di.FragmentScope;
import com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment;
import com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment;
import com.onfido.dagger.Subcomponent;
import io.sentry.protocol.Request;
import kotlin.Metadata;

@FragmentScope
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\ba\u0018\u00002\u00020\u0001:\u0001\u0007J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0006H&¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/di/RestrictedDocumentSelectionHostComponent;", "", "inject", "", Request.JsonKeys.FRAGMENT, "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionFragment;", "Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment;", "Factory", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Subcomponent(modules = {RestrictedDocumentSelectionHostNavigationModule.class})
/* loaded from: classes2.dex */
public interface RestrictedDocumentSelectionHostComponent {

    @Subcomponent.Factory
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/di/RestrictedDocumentSelectionHostComponent$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/ui/documentselection/di/RestrictedDocumentSelectionHostComponent;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        RestrictedDocumentSelectionHostComponent create();
    }

    void inject(DocumentTypeSelectionFragment fragment);

    void inject(DocumentSelectionHostFragment fragment);
}
