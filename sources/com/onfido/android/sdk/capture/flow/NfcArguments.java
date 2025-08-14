package com.onfido.android.sdk.capture.flow;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u001cB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000fHÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001d"}, d2 = {"Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "Landroid/os/Parcelable;", "nfcOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions;", "capturedData", "Lcom/onfido/android/sdk/capture/flow/NfcArguments$CapturedNfcData;", "(Lcom/onfido/android/sdk/capture/model/NFCOptions;Lcom/onfido/android/sdk/capture/flow/NfcArguments$CapturedNfcData;)V", "getCapturedData", "()Lcom/onfido/android/sdk/capture/flow/NfcArguments$CapturedNfcData;", "getNfcOptions", "()Lcom/onfido/android/sdk/capture/model/NFCOptions;", "component1", "component2", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "CapturedNfcData", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class NfcArguments implements Parcelable {
    public static final Parcelable.Creator<NfcArguments> CREATOR = new Creator();
    private final CapturedNfcData capturedData;
    private final NFCOptions nfcOptions;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0010HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/flow/NfcArguments$CapturedNfcData;", "Landroid/os/Parcelable;", "frontId", "", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "nfcSupported", "", "(Ljava/lang/String;Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;Z)V", "getFrontId$onfido_capture_sdk_core_release", "()Ljava/lang/String;", "getNfcProperties$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "getNfcSupported$onfido_capture_sdk_core_release", "()Z", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CapturedNfcData implements Parcelable {
        public static final Parcelable.Creator<CapturedNfcData> CREATOR = new Creator();
        private final String frontId;
        private final NfcProperties nfcProperties;
        private final boolean nfcSupported;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<CapturedNfcData> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final CapturedNfcData createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new CapturedNfcData(parcel.readString(), parcel.readInt() == 0 ? null : NfcProperties.CREATOR.createFromParcel(parcel), parcel.readInt() != 0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final CapturedNfcData[] newArray(int i) {
                return new CapturedNfcData[i];
            }
        }

        public CapturedNfcData(String frontId, NfcProperties nfcProperties, boolean z) {
            Intrinsics.checkNotNullParameter(frontId, "frontId");
            this.frontId = frontId;
            this.nfcProperties = nfcProperties;
            this.nfcSupported = z;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        /* renamed from: getFrontId$onfido_capture_sdk_core_release, reason: from getter */
        public final String getFrontId() {
            return this.frontId;
        }

        /* renamed from: getNfcProperties$onfido_capture_sdk_core_release, reason: from getter */
        public final NfcProperties getNfcProperties() {
            return this.nfcProperties;
        }

        /* renamed from: getNfcSupported$onfido_capture_sdk_core_release, reason: from getter */
        public final boolean getNfcSupported() {
            return this.nfcSupported;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.frontId);
            NfcProperties nfcProperties = this.nfcProperties;
            if (nfcProperties == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                nfcProperties.writeToParcel(parcel, flags);
            }
            parcel.writeInt(this.nfcSupported ? 1 : 0);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<NfcArguments> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcArguments createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new NfcArguments((NFCOptions) parcel.readParcelable(NfcArguments.class.getClassLoader()), parcel.readInt() == 0 ? null : CapturedNfcData.CREATOR.createFromParcel(parcel));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcArguments[] newArray(int i) {
            return new NfcArguments[i];
        }
    }

    public NfcArguments(NFCOptions nfcOptions, CapturedNfcData capturedNfcData) {
        Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
        this.nfcOptions = nfcOptions;
        this.capturedData = capturedNfcData;
    }

    public static /* synthetic */ NfcArguments copy$default(NfcArguments nfcArguments, NFCOptions nFCOptions, CapturedNfcData capturedNfcData, int i, Object obj) {
        if ((i & 1) != 0) {
            nFCOptions = nfcArguments.nfcOptions;
        }
        if ((i & 2) != 0) {
            capturedNfcData = nfcArguments.capturedData;
        }
        return nfcArguments.copy(nFCOptions, capturedNfcData);
    }

    /* renamed from: component1, reason: from getter */
    public final NFCOptions getNfcOptions() {
        return this.nfcOptions;
    }

    /* renamed from: component2, reason: from getter */
    public final CapturedNfcData getCapturedData() {
        return this.capturedData;
    }

    public final NfcArguments copy(NFCOptions nfcOptions, CapturedNfcData capturedData) {
        Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
        return new NfcArguments(nfcOptions, capturedData);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NfcArguments)) {
            return false;
        }
        NfcArguments nfcArguments = (NfcArguments) other;
        return Intrinsics.areEqual(this.nfcOptions, nfcArguments.nfcOptions) && Intrinsics.areEqual(this.capturedData, nfcArguments.capturedData);
    }

    public final CapturedNfcData getCapturedData() {
        return this.capturedData;
    }

    public final NFCOptions getNfcOptions() {
        return this.nfcOptions;
    }

    public int hashCode() {
        int iHashCode = this.nfcOptions.hashCode() * 31;
        CapturedNfcData capturedNfcData = this.capturedData;
        return iHashCode + (capturedNfcData == null ? 0 : capturedNfcData.hashCode());
    }

    public String toString() {
        return "NfcArguments(nfcOptions=" + this.nfcOptions + ", capturedData=" + this.capturedData + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeParcelable(this.nfcOptions, flags);
        CapturedNfcData capturedNfcData = this.capturedData;
        if (capturedNfcData == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            capturedNfcData.writeToParcel(parcel, flags);
        }
    }

    public /* synthetic */ NfcArguments(NFCOptions nFCOptions, CapturedNfcData capturedNfcData, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nFCOptions, (i & 2) != 0 ? null : capturedNfcData);
    }
}
