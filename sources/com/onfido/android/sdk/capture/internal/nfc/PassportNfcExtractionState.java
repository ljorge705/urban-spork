package com.onfido.android.sdk.capture.internal.nfc;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.nfc.data.NfcPassportExtraction;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcExtractionState;", "", "()V", "ConnectionLost", "Failed", "Reading", "Success", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcExtractionState$ConnectionLost;", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcExtractionState$Failed;", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcExtractionState$Reading;", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcExtractionState$Success;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class PassportNfcExtractionState {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcExtractionState$ConnectionLost;", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcExtractionState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ConnectionLost extends PassportNfcExtractionState {
        public static final ConnectionLost INSTANCE = new ConnectionLost();

        private ConnectionLost() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcExtractionState$Failed;", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcExtractionState;", "message", "", "authAccess", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;", "(Ljava/lang/String;Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;)V", "getAuthAccess", "()Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;", "getMessage", "()Ljava/lang/String;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Failed extends PassportNfcExtractionState {
        private final PassportAuthAccess authAccess;
        private final String message;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Failed(String message, PassportAuthAccess authAccess) {
            super(null);
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(authAccess, "authAccess");
            this.message = message;
            this.authAccess = authAccess;
        }

        public static /* synthetic */ Failed copy$default(Failed failed, String str, PassportAuthAccess passportAuthAccess, int i, Object obj) {
            if ((i & 1) != 0) {
                str = failed.message;
            }
            if ((i & 2) != 0) {
                passportAuthAccess = failed.authAccess;
            }
            return failed.copy(str, passportAuthAccess);
        }

        /* renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* renamed from: component2, reason: from getter */
        public final PassportAuthAccess getAuthAccess() {
            return this.authAccess;
        }

        public final Failed copy(String message, PassportAuthAccess authAccess) {
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(authAccess, "authAccess");
            return new Failed(message, authAccess);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Failed)) {
                return false;
            }
            Failed failed = (Failed) other;
            return Intrinsics.areEqual(this.message, failed.message) && Intrinsics.areEqual(this.authAccess, failed.authAccess);
        }

        public final PassportAuthAccess getAuthAccess() {
            return this.authAccess;
        }

        public final String getMessage() {
            return this.message;
        }

        public int hashCode() {
            return (this.message.hashCode() * 31) + this.authAccess.hashCode();
        }

        public String toString() {
            return "Failed(message=" + this.message + ", authAccess=" + this.authAccess + ')';
        }

        public /* synthetic */ Failed(String str, PassportAuthAccess passportAuthAccess, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "" : str, passportAuthAccess);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcExtractionState$Reading;", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcExtractionState;", "progress", "", "(I)V", "getProgress", "()I", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Reading extends PassportNfcExtractionState {
        private final int progress;

        public Reading(int i) {
            super(null);
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

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcExtractionState$Success;", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcExtractionState;", "data", "Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "authAccess", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;", "(Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;)V", "getAuthAccess", "()Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;", "getData", "()Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Success extends PassportNfcExtractionState {
        private final PassportAuthAccess authAccess;
        private final NfcPassportExtraction data;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Success(NfcPassportExtraction data, PassportAuthAccess authAccess) {
            super(null);
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(authAccess, "authAccess");
            this.data = data;
            this.authAccess = authAccess;
        }

        public static /* synthetic */ Success copy$default(Success success, NfcPassportExtraction nfcPassportExtraction, PassportAuthAccess passportAuthAccess, int i, Object obj) {
            if ((i & 1) != 0) {
                nfcPassportExtraction = success.data;
            }
            if ((i & 2) != 0) {
                passportAuthAccess = success.authAccess;
            }
            return success.copy(nfcPassportExtraction, passportAuthAccess);
        }

        /* renamed from: component1, reason: from getter */
        public final NfcPassportExtraction getData() {
            return this.data;
        }

        /* renamed from: component2, reason: from getter */
        public final PassportAuthAccess getAuthAccess() {
            return this.authAccess;
        }

        public final Success copy(NfcPassportExtraction data, PassportAuthAccess authAccess) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(authAccess, "authAccess");
            return new Success(data, authAccess);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Success)) {
                return false;
            }
            Success success = (Success) other;
            return Intrinsics.areEqual(this.data, success.data) && Intrinsics.areEqual(this.authAccess, success.authAccess);
        }

        public final PassportAuthAccess getAuthAccess() {
            return this.authAccess;
        }

        public final NfcPassportExtraction getData() {
            return this.data;
        }

        public int hashCode() {
            return (this.data.hashCode() * 31) + this.authAccess.hashCode();
        }

        public String toString() {
            return "Success(data=" + this.data + ", authAccess=" + this.authAccess + ')';
        }
    }

    private PassportNfcExtractionState() {
    }

    public /* synthetic */ PassportNfcExtractionState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
