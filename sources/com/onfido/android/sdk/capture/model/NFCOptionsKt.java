package com.onfido.android.sdk.capture.model;

import com.onfido.android.sdk.capture.model.NFCOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NFCOptions.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0003¨\u0006\u0005"}, d2 = {"isEnabled", "", "Lcom/onfido/android/sdk/capture/model/NFCOptions;", "(Lcom/onfido/android/sdk/capture/model/NFCOptions;)Z", "isRequired", "onfido-public-api_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NFCOptionsKt {
    public static final boolean isEnabled(NFCOptions nFCOptions) {
        Intrinsics.checkNotNullParameter(nFCOptions, "<this>");
        return nFCOptions instanceof NFCOptions.Enabled;
    }

    public static final boolean isRequired(NFCOptions nFCOptions) {
        Intrinsics.checkNotNullParameter(nFCOptions, "<this>");
        return nFCOptions instanceof NFCOptions.Enabled.Required;
    }
}
