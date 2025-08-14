package com.onfido.workflow.internal.ui;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import com.onfido.android.sdk.capture.utils.CountryCode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Screens.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0081\b\u0018\u0000 $2\u00020\u0001:\u0001$B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rJ\t\u0010\u000e\u001a\u00020\u0003HÂ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÂ\u0003J\t\u0010\u0010\u001a\u00020\u0007HÂ\u0003J\t\u0010\u0011\u001a\u00020\tHÂ\u0003J\t\u0010\u0012\u001a\u00020\u000bHÂ\u0003J\t\u0010\u0013\u001a\u00020\u000bHÂ\u0003JG\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bHÆ\u0001J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0013\u0010\u0019\u001a\u00020\u000b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u0018HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\u0019\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0018HÖ\u0001R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/onfido/workflow/internal/ui/NfcFlowScreen;", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "nfcOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions$Enabled;", "isOnlyOneDocAvailable", "", "isInWorkflow", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;Lcom/onfido/android/sdk/capture/model/NFCOptions$Enabled;ZZ)V", "component1", "component2", "component3", "component4", "component5", "component6", Constants.COPY_TYPE, "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "", "equals", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class NfcFlowScreen implements Screen {
    public static final String KEY_REQUEST = "request_key_nfc_flow";
    private final CountryCode countryCode;
    private final DocumentType documentType;
    private final boolean isInWorkflow;
    private final boolean isOnlyOneDocAvailable;
    private final NFCOptions.Enabled nfcOptions;
    private final NfcProperties nfcProperties;
    public static final Parcelable.Creator<NfcFlowScreen> CREATOR = new Creator();

    /* compiled from: Screens.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<NfcFlowScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcFlowScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new NfcFlowScreen(DocumentType.valueOf(parcel.readString()), parcel.readInt() == 0 ? null : CountryCode.valueOf(parcel.readString()), (NfcProperties) parcel.readParcelable(NfcFlowScreen.class.getClassLoader()), (NFCOptions.Enabled) parcel.readParcelable(NfcFlowScreen.class.getClassLoader()), parcel.readInt() != 0, parcel.readInt() != 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcFlowScreen[] newArray(int i) {
            return new NfcFlowScreen[i];
        }
    }

    /* renamed from: component1, reason: from getter */
    private final DocumentType getDocumentType() {
        return this.documentType;
    }

    /* renamed from: component2, reason: from getter */
    private final CountryCode getCountryCode() {
        return this.countryCode;
    }

    /* renamed from: component3, reason: from getter */
    private final NfcProperties getNfcProperties() {
        return this.nfcProperties;
    }

    /* renamed from: component4, reason: from getter */
    private final NFCOptions.Enabled getNfcOptions() {
        return this.nfcOptions;
    }

    /* renamed from: component5, reason: from getter */
    private final boolean getIsOnlyOneDocAvailable() {
        return this.isOnlyOneDocAvailable;
    }

    /* renamed from: component6, reason: from getter */
    private final boolean getIsInWorkflow() {
        return this.isInWorkflow;
    }

    public static /* synthetic */ NfcFlowScreen copy$default(NfcFlowScreen nfcFlowScreen, DocumentType documentType, CountryCode countryCode, NfcProperties nfcProperties, NFCOptions.Enabled enabled, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            documentType = nfcFlowScreen.documentType;
        }
        if ((i & 2) != 0) {
            countryCode = nfcFlowScreen.countryCode;
        }
        CountryCode countryCode2 = countryCode;
        if ((i & 4) != 0) {
            nfcProperties = nfcFlowScreen.nfcProperties;
        }
        NfcProperties nfcProperties2 = nfcProperties;
        if ((i & 8) != 0) {
            enabled = nfcFlowScreen.nfcOptions;
        }
        NFCOptions.Enabled enabled2 = enabled;
        if ((i & 16) != 0) {
            z = nfcFlowScreen.isOnlyOneDocAvailable;
        }
        boolean z3 = z;
        if ((i & 32) != 0) {
            z2 = nfcFlowScreen.isInWorkflow;
        }
        return nfcFlowScreen.copy(documentType, countryCode2, nfcProperties2, enabled2, z3, z2);
    }

    public final NfcFlowScreen copy(DocumentType documentType, CountryCode countryCode, NfcProperties nfcProperties, NFCOptions.Enabled nfcOptions, boolean isOnlyOneDocAvailable, boolean isInWorkflow) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(nfcProperties, "nfcProperties");
        Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
        return new NfcFlowScreen(documentType, countryCode, nfcProperties, nfcOptions, isOnlyOneDocAvailable, isInWorkflow);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NfcFlowScreen)) {
            return false;
        }
        NfcFlowScreen nfcFlowScreen = (NfcFlowScreen) other;
        return this.documentType == nfcFlowScreen.documentType && this.countryCode == nfcFlowScreen.countryCode && Intrinsics.areEqual(this.nfcProperties, nfcFlowScreen.nfcProperties) && Intrinsics.areEqual(this.nfcOptions, nfcFlowScreen.nfcOptions) && this.isOnlyOneDocAvailable == nfcFlowScreen.isOnlyOneDocAvailable && this.isInWorkflow == nfcFlowScreen.isInWorkflow;
    }

    public int hashCode() {
        int iHashCode = this.documentType.hashCode() * 31;
        CountryCode countryCode = this.countryCode;
        return ((((((((iHashCode + (countryCode == null ? 0 : countryCode.hashCode())) * 31) + this.nfcProperties.hashCode()) * 31) + this.nfcOptions.hashCode()) * 31) + Boolean.hashCode(this.isOnlyOneDocAvailable)) * 31) + Boolean.hashCode(this.isInWorkflow);
    }

    public String toString() {
        return "NfcFlowScreen(documentType=" + this.documentType + ", countryCode=" + this.countryCode + ", nfcProperties=" + this.nfcProperties + ", nfcOptions=" + this.nfcOptions + ", isOnlyOneDocAvailable=" + this.isOnlyOneDocAvailable + ", isInWorkflow=" + this.isInWorkflow + ")";
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
        parcel.writeParcelable(this.nfcProperties, flags);
        parcel.writeParcelable(this.nfcOptions, flags);
        parcel.writeInt(this.isOnlyOneDocAvailable ? 1 : 0);
        parcel.writeInt(this.isInWorkflow ? 1 : 0);
    }

    public NfcFlowScreen(DocumentType documentType, CountryCode countryCode, NfcProperties nfcProperties, NFCOptions.Enabled nfcOptions, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(nfcProperties, "nfcProperties");
        Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
        this.documentType = documentType;
        this.countryCode = countryCode;
        this.nfcProperties = nfcProperties;
        this.nfcOptions = nfcOptions;
        this.isOnlyOneDocAvailable = z;
        this.isInWorkflow = z2;
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return Screen.DefaultImpls.screenKey(this);
    }

    public /* synthetic */ NfcFlowScreen(DocumentType documentType, CountryCode countryCode, NfcProperties nfcProperties, NFCOptions.Enabled enabled, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(documentType, countryCode, nfcProperties, enabled, z, (i & 32) != 0 ? true : z2);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public Fragment createFragment() {
        return NfcHostFragment.INSTANCE.newInstance(KEY_REQUEST, this.documentType, this.countryCode, this.nfcProperties, this.nfcOptions, this.isOnlyOneDocAvailable, this.isInWorkflow);
    }
}
