package com.onfido.android.sdk.capture.internal.nfc;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/Reading;", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcReadState;", "progress", "", "(I)V", "getProgress", "()I", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class Reading implements NfcReadState {
    private final int progress;

    public Reading(int i) {
        this.progress = i;
    }

    public static /* synthetic */ Reading copy$default(Reading reading, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = reading.progress;
        }
        return reading.copy(i);
    }

    /* renamed from: component1, reason: from getter */
    public final int getProgress() {
        return this.progress;
    }

    public final Reading copy(int progress) {
        return new Reading(progress);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof Reading) && this.progress == ((Reading) other).progress;
    }

    public final int getProgress() {
        return this.progress;
    }

    public int hashCode() {
        return Integer.hashCode(this.progress);
    }

    public String toString() {
        return "Reading(progress=" + this.progress + ')';
    }
}
