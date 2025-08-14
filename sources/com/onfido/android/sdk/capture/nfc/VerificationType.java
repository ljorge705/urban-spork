package com.onfido.android.sdk.capture.nfc;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: MRTDModels.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/nfc/VerificationType;", "", "()V", "ActiveAuthentication", "ChipAuthentication", "Lcom/onfido/android/sdk/capture/nfc/VerificationType$ActiveAuthentication;", "Lcom/onfido/android/sdk/capture/nfc/VerificationType$ChipAuthentication;", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class VerificationType {
    public /* synthetic */ VerificationType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: MRTDModels.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/nfc/VerificationType$ActiveAuthentication;", "Lcom/onfido/android/sdk/capture/nfc/VerificationType;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ActiveAuthentication extends VerificationType {
        public static final ActiveAuthentication INSTANCE = new ActiveAuthentication();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ActiveAuthentication)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1414321893;
        }

        public String toString() {
            return "ActiveAuthentication";
        }

        private ActiveAuthentication() {
            super(null);
        }
    }

    private VerificationType() {
    }

    /* compiled from: MRTDModels.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/nfc/VerificationType$ChipAuthentication;", "Lcom/onfido/android/sdk/capture/nfc/VerificationType;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ChipAuthentication extends VerificationType {
        public static final ChipAuthentication INSTANCE = new ChipAuthentication();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ChipAuthentication)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1151893355;
        }

        public String toString() {
            return "ChipAuthentication";
        }

        private ChipAuthentication() {
            super(null);
        }
    }
}
