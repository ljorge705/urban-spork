package com.onfido.android.sdk.capture.nfc;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BasicAccessControl.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/nfc/BasicAccessControl;", "", "documentNumber", "", "dateOfBirth", "dateOfExpiry", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDateOfBirth", "()Ljava/lang/String;", "getDateOfExpiry", "getDocumentNumber", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BasicAccessControl {
    private final String dateOfBirth;
    private final String dateOfExpiry;
    private final String documentNumber;

    public final String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public final String getDateOfExpiry() {
        return this.dateOfExpiry;
    }

    public final String getDocumentNumber() {
        return this.documentNumber;
    }

    public BasicAccessControl(String documentNumber, String dateOfBirth, String dateOfExpiry) {
        Intrinsics.checkNotNullParameter(documentNumber, "documentNumber");
        Intrinsics.checkNotNullParameter(dateOfBirth, "dateOfBirth");
        Intrinsics.checkNotNullParameter(dateOfExpiry, "dateOfExpiry");
        this.documentNumber = documentNumber;
        this.dateOfBirth = dateOfBirth;
        this.dateOfExpiry = dateOfExpiry;
    }
}
