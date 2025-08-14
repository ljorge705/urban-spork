package com.onfido.android.sdk.capture.internal.nfc;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.nfc.data.NfcPassportExtraction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/Success;", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcReadState;", "data", "Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "duration", "", "(Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;J)V", "getData$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "getDuration$onfido_capture_sdk_core_release", "()J", "getNfcFlowType$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Success implements NfcReadState {
    private final NfcPassportExtraction data;
    private final long duration;
    private final NfcFlowType nfcFlowType;

    public Success(NfcPassportExtraction data, NfcFlowType nfcFlowType, long j) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        this.data = data;
        this.nfcFlowType = nfcFlowType;
        this.duration = j;
    }

    /* renamed from: getData$onfido_capture_sdk_core_release, reason: from getter */
    public final NfcPassportExtraction getData() {
        return this.data;
    }

    /* renamed from: getDuration$onfido_capture_sdk_core_release, reason: from getter */
    public final long getDuration() {
        return this.duration;
    }

    /* renamed from: getNfcFlowType$onfido_capture_sdk_core_release, reason: from getter */
    public final NfcFlowType getNfcFlowType() {
        return this.nfcFlowType;
    }
}
