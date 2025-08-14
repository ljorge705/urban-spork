package com.onfido.android.sdk.capture.ui.nfc.scan;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/Scanned;", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanState;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "duration", "", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;J)V", "getDuration", "()J", "getNfcFlowType", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class Scanned extends NfcScanState {
    private final long duration;
    private final NfcFlowType nfcFlowType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Scanned(NfcFlowType nfcFlowType, long j) {
        super(null);
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        this.nfcFlowType = nfcFlowType;
        this.duration = j;
    }

    public static /* synthetic */ Scanned copy$default(Scanned scanned, NfcFlowType nfcFlowType, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            nfcFlowType = scanned.nfcFlowType;
        }
        if ((i & 2) != 0) {
            j = scanned.duration;
        }
        return scanned.copy(nfcFlowType, j);
    }

    /* renamed from: component1, reason: from getter */
    public final NfcFlowType getNfcFlowType() {
        return this.nfcFlowType;
    }

    /* renamed from: component2, reason: from getter */
    public final long getDuration() {
        return this.duration;
    }

    public final Scanned copy(NfcFlowType nfcFlowType, long duration) {
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        return new Scanned(nfcFlowType, duration);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Scanned)) {
            return false;
        }
        Scanned scanned = (Scanned) other;
        return this.nfcFlowType == scanned.nfcFlowType && this.duration == scanned.duration;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final NfcFlowType getNfcFlowType() {
        return this.nfcFlowType;
    }

    public int hashCode() {
        return (this.nfcFlowType.hashCode() * 31) + Long.hashCode(this.duration);
    }

    public String toString() {
        return "Scanned(nfcFlowType=" + this.nfcFlowType + ", duration=" + this.duration + ')';
    }
}
