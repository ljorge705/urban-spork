package com.onfido.android.sdk.capture.ui.nfc.scan;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B+\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanViewState;", "", "titleResId", "", "primaryActionResId", "secondaryActionResId", "secondaryButtonVisible", "", "(IIIZ)V", "getPrimaryActionResId", "()I", "getSecondaryActionResId", "getSecondaryButtonVisible", "()Z", "getTitleResId", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class NfcScanViewState {
    private final int primaryActionResId;
    private final int secondaryActionResId;
    private final boolean secondaryButtonVisible;
    private final int titleResId;

    public NfcScanViewState(int i, int i2, int i3, boolean z) {
        this.titleResId = i;
        this.primaryActionResId = i2;
        this.secondaryActionResId = i3;
        this.secondaryButtonVisible = z;
    }

    public static /* synthetic */ NfcScanViewState copy$default(NfcScanViewState nfcScanViewState, int i, int i2, int i3, boolean z, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = nfcScanViewState.titleResId;
        }
        if ((i4 & 2) != 0) {
            i2 = nfcScanViewState.primaryActionResId;
        }
        if ((i4 & 4) != 0) {
            i3 = nfcScanViewState.secondaryActionResId;
        }
        if ((i4 & 8) != 0) {
            z = nfcScanViewState.secondaryButtonVisible;
        }
        return nfcScanViewState.copy(i, i2, i3, z);
    }

    /* renamed from: component1, reason: from getter */
    public final int getTitleResId() {
        return this.titleResId;
    }

    /* renamed from: component2, reason: from getter */
    public final int getPrimaryActionResId() {
        return this.primaryActionResId;
    }

    /* renamed from: component3, reason: from getter */
    public final int getSecondaryActionResId() {
        return this.secondaryActionResId;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getSecondaryButtonVisible() {
        return this.secondaryButtonVisible;
    }

    public final NfcScanViewState copy(int titleResId, int primaryActionResId, int secondaryActionResId, boolean secondaryButtonVisible) {
        return new NfcScanViewState(titleResId, primaryActionResId, secondaryActionResId, secondaryButtonVisible);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NfcScanViewState)) {
            return false;
        }
        NfcScanViewState nfcScanViewState = (NfcScanViewState) other;
        return this.titleResId == nfcScanViewState.titleResId && this.primaryActionResId == nfcScanViewState.primaryActionResId && this.secondaryActionResId == nfcScanViewState.secondaryActionResId && this.secondaryButtonVisible == nfcScanViewState.secondaryButtonVisible;
    }

    public final int getPrimaryActionResId() {
        return this.primaryActionResId;
    }

    public final int getSecondaryActionResId() {
        return this.secondaryActionResId;
    }

    public final boolean getSecondaryButtonVisible() {
        return this.secondaryButtonVisible;
    }

    public final int getTitleResId() {
        return this.titleResId;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.titleResId) * 31) + Integer.hashCode(this.primaryActionResId)) * 31) + Integer.hashCode(this.secondaryActionResId)) * 31) + Boolean.hashCode(this.secondaryButtonVisible);
    }

    public String toString() {
        return "NfcScanViewState(titleResId=" + this.titleResId + ", primaryActionResId=" + this.primaryActionResId + ", secondaryActionResId=" + this.secondaryActionResId + ", secondaryButtonVisible=" + this.secondaryButtonVisible + ')';
    }
}
