package com.onfido.android.sdk.capture.document.supported.data.remote.model;

import com.onfido.android.sdk.capture.DocumentType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"toDocumentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SupportedDocumentsResponseKt {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static final DocumentType toDocumentType(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        switch (str.hashCode()) {
            case 67772:
                if (str.equals("DLD")) {
                    return DocumentType.DRIVING_LICENCE;
                }
                return null;
            case 77288:
                if (str.equals("NIC")) {
                    return DocumentType.NATIONAL_IDENTITY_CARD;
                }
                return null;
            case 79439:
                if (str.equals("PPO")) {
                    return DocumentType.PASSPORT;
                }
                return null;
            case 81021:
                if (str.equals("REP")) {
                    return DocumentType.RESIDENCE_PERMIT;
                }
                return null;
            case 84992:
                if (str.equals("VIS")) {
                    return DocumentType.VISA;
                }
                return null;
            case 86012:
                if (str.equals("WKP")) {
                    return DocumentType.WORK_PERMIT;
                }
                return null;
            default:
                return null;
        }
    }
}
