package com.onfido.android.sdk.capture.ui.nfc.scan;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/ScanFailed;", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ScanFailed extends NfcScanState {
    private final String message;

    /* JADX WARN: Multi-variable type inference failed */
    public ScanFailed() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ ScanFailed copy$default(ScanFailed scanFailed, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = scanFailed.message;
        }
        return scanFailed.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final ScanFailed copy(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new ScanFailed(message);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ScanFailed) && Intrinsics.areEqual(this.message, ((ScanFailed) other).message);
    }

    public final String getMessage() {
        return this.message;
    }

    public int hashCode() {
        return this.message.hashCode();
    }

    public String toString() {
        return "ScanFailed(message=" + this.message + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScanFailed(String message) {
        super(null);
        Intrinsics.checkNotNullParameter(message, "message");
        this.message = message;
    }

    public /* synthetic */ ScanFailed(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }
}
