package com.onfido.android.sdk.capture.ui.nfc;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey;
import com.onfido.android.sdk.capture.ui.nfc.NfcScreen;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment;
import com.onfido.android.sdk.capture.utils.CountryCode;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0010HÖ\u0001R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcScanScreen;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcScreen;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "passportBACKey", "Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;", "aaChallenge", "", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;[B)V", "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "", "equals", "", "other", "", "hashCode", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcScanScreen implements NfcScreen {
    public static final Parcelable.Creator<NfcScanScreen> CREATOR = new Creator();
    private final byte[] aaChallenge;
    private final CountryCode countryCode;
    private final DocumentType documentType;
    private final NfcFlowType nfcFlowType;
    private final PassportBACKey passportBACKey;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<NfcScanScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcScanScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new NfcScanScreen(DocumentType.valueOf(parcel.readString()), parcel.readInt() == 0 ? null : CountryCode.valueOf(parcel.readString()), NfcFlowType.valueOf(parcel.readString()), (PassportBACKey) parcel.readSerializable(), parcel.createByteArray());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcScanScreen[] newArray(int i) {
            return new NfcScanScreen[i];
        }
    }

    public NfcScanScreen(DocumentType documentType, CountryCode countryCode, NfcFlowType nfcFlowType, PassportBACKey passportBACKey, byte[] aaChallenge) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        Intrinsics.checkNotNullParameter(passportBACKey, "passportBACKey");
        Intrinsics.checkNotNullParameter(aaChallenge, "aaChallenge");
        this.documentType = documentType;
        this.countryCode = countryCode;
        this.nfcFlowType = nfcFlowType;
        this.passportBACKey = passportBACKey;
        this.aaChallenge = aaChallenge;
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public Fragment createFragment() {
        return NfcScanFragment.INSTANCE.createInstance(this.documentType, this.countryCode, this.nfcFlowType, this.passportBACKey, this.aaChallenge);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(NfcScanScreen.class, other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.nfc.NfcScanScreen");
        NfcScanScreen nfcScanScreen = (NfcScanScreen) other;
        return this.documentType == nfcScanScreen.documentType && Intrinsics.areEqual(this.passportBACKey, nfcScanScreen.passportBACKey) && Arrays.equals(this.aaChallenge, nfcScanScreen.aaChallenge);
    }

    public int hashCode() {
        return (((this.documentType.hashCode() * 31) + this.passportBACKey.hashCode()) * 31) + Arrays.hashCode(this.aaChallenge);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return NfcScreen.DefaultImpls.screenKey(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.documentType.name());
        CountryCode countryCode = this.countryCode;
        if (countryCode == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeString(countryCode.name());
        }
        parcel.writeString(this.nfcFlowType.name());
        parcel.writeSerializable(this.passportBACKey);
        parcel.writeByteArray(this.aaChallenge);
    }
}
