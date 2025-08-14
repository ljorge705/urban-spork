package com.onfido.android.sdk.capture.utils;

import com.onfido.android.sdk.capture.OnfidoConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0005"}, d2 = {"hasPreselectedDocuments", "", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "hasPreselectedGenericDocuments", "inWorkflowMode", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoConfigExtensionsKt {
    public static final boolean hasPreselectedDocuments(OnfidoConfig onfidoConfig) {
        Intrinsics.checkNotNullParameter(onfidoConfig, "<this>");
        return !onfidoConfig.getDocumentTypes().isEmpty();
    }

    public static final boolean hasPreselectedGenericDocuments(OnfidoConfig onfidoConfig) {
        Intrinsics.checkNotNullParameter(onfidoConfig, "<this>");
        return !onfidoConfig.getGenericDocuments().isEmpty();
    }

    public static final boolean inWorkflowMode(OnfidoConfig onfidoConfig) {
        Intrinsics.checkNotNullParameter(onfidoConfig, "<this>");
        return onfidoConfig.getWorkflowConfig() != null;
    }
}
