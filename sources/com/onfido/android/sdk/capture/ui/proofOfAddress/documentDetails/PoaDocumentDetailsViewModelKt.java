package com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails;

import com.onfido.android.sdk.capture.utils.CountryCode;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"isUK", "", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PoaDocumentDetailsViewModelKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isUK(CountryCode countryCode) {
        return CountryCode.GB == countryCode;
    }
}
