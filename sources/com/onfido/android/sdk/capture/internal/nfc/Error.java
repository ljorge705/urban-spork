package com.onfido.android.sdk.capture.internal.nfc;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/Error;", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcReadState;", "message", "", "authAccess", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;", "(Ljava/lang/String;Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;)V", "getAuthAccess$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;", "getMessage$onfido_capture_sdk_core_release", "()Ljava/lang/String;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Error implements NfcReadState {
    private final PassportAuthAccess authAccess;
    private final String message;

    public Error(String message, PassportAuthAccess passportAuthAccess) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.message = message;
        this.authAccess = passportAuthAccess;
    }

    /* renamed from: getAuthAccess$onfido_capture_sdk_core_release, reason: from getter */
    public final PassportAuthAccess getAuthAccess() {
        return this.authAccess;
    }

    /* renamed from: getMessage$onfido_capture_sdk_core_release, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public /* synthetic */ Error(String str, PassportAuthAccess passportAuthAccess, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : passportAuthAccess);
    }
}
