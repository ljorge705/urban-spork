package com.onfido.android.sdk.capture.ui.nfc;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.ui.nfc.NfcScreen;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0004\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B5\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\t\u0010\r\u001a\u00020\u0005HÂ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0007HÂ\u0003J\t\u0010\u000f\u001a\u00020\tHÂ\u0003J\t\u0010\u0010\u001a\u00020\tHÂ\u0003J?\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tHÆ\u0001J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcCanEntryScreen;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcScreen;", "knownCanNumber", "", "canNumberLength", "", "inputCanNumber", "", "isRetry", "", "withError", "(Ljava/lang/String;ILjava/lang/Number;ZZ)V", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class NfcCanEntryScreen implements NfcScreen {
    public static final Parcelable.Creator<NfcCanEntryScreen> CREATOR = new Creator();
    private final int canNumberLength;
    private final Number inputCanNumber;
    private final boolean isRetry;
    private final String knownCanNumber;
    private final boolean withError;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<NfcCanEntryScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcCanEntryScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new NfcCanEntryScreen(parcel.readString(), parcel.readInt(), (Number) parcel.readSerializable(), parcel.readInt() != 0, parcel.readInt() != 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcCanEntryScreen[] newArray(int i) {
            return new NfcCanEntryScreen[i];
        }
    }

    public NfcCanEntryScreen(String str, int i, Number number, boolean z, boolean z2) {
        this.knownCanNumber = str;
        this.canNumberLength = i;
        this.inputCanNumber = number;
        this.isRetry = z;
        this.withError = z2;
    }

    /* renamed from: component1, reason: from getter */
    private final String getKnownCanNumber() {
        return this.knownCanNumber;
    }

    /* renamed from: component2, reason: from getter */
    private final int getCanNumberLength() {
        return this.canNumberLength;
    }

    /* renamed from: component3, reason: from getter */
    private final Number getInputCanNumber() {
        return this.inputCanNumber;
    }

    /* renamed from: component4, reason: from getter */
    private final boolean getIsRetry() {
        return this.isRetry;
    }

    /* renamed from: component5, reason: from getter */
    private final boolean getWithError() {
        return this.withError;
    }

    public static /* synthetic */ NfcCanEntryScreen copy$default(NfcCanEntryScreen nfcCanEntryScreen, String str, int i, Number number, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = nfcCanEntryScreen.knownCanNumber;
        }
        if ((i2 & 2) != 0) {
            i = nfcCanEntryScreen.canNumberLength;
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            number = nfcCanEntryScreen.inputCanNumber;
        }
        Number number2 = number;
        if ((i2 & 8) != 0) {
            z = nfcCanEntryScreen.isRetry;
        }
        boolean z3 = z;
        if ((i2 & 16) != 0) {
            z2 = nfcCanEntryScreen.withError;
        }
        return nfcCanEntryScreen.copy(str, i3, number2, z3, z2);
    }

    public final NfcCanEntryScreen copy(String knownCanNumber, int canNumberLength, Number inputCanNumber, boolean isRetry, boolean withError) {
        return new NfcCanEntryScreen(knownCanNumber, canNumberLength, inputCanNumber, isRetry, withError);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public Fragment createFragment() {
        return NfcCanEntryFragment.INSTANCE.createInstance(this.knownCanNumber, this.inputCanNumber, this.canNumberLength, this.isRetry, this.withError);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NfcCanEntryScreen)) {
            return false;
        }
        NfcCanEntryScreen nfcCanEntryScreen = (NfcCanEntryScreen) other;
        return Intrinsics.areEqual(this.knownCanNumber, nfcCanEntryScreen.knownCanNumber) && this.canNumberLength == nfcCanEntryScreen.canNumberLength && Intrinsics.areEqual(this.inputCanNumber, nfcCanEntryScreen.inputCanNumber) && this.isRetry == nfcCanEntryScreen.isRetry && this.withError == nfcCanEntryScreen.withError;
    }

    public int hashCode() {
        String str = this.knownCanNumber;
        int iHashCode = (((str == null ? 0 : str.hashCode()) * 31) + Integer.hashCode(this.canNumberLength)) * 31;
        Number number = this.inputCanNumber;
        return ((((iHashCode + (number != null ? number.hashCode() : 0)) * 31) + Boolean.hashCode(this.isRetry)) * 31) + Boolean.hashCode(this.withError);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return NfcScreen.DefaultImpls.screenKey(this);
    }

    public String toString() {
        return "NfcCanEntryScreen(knownCanNumber=" + this.knownCanNumber + ", canNumberLength=" + this.canNumberLength + ", inputCanNumber=" + this.inputCanNumber + ", isRetry=" + this.isRetry + ", withError=" + this.withError + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.knownCanNumber);
        parcel.writeInt(this.canNumberLength);
        parcel.writeSerializable(this.inputCanNumber);
        parcel.writeInt(this.isRetry ? 1 : 0);
        parcel.writeInt(this.withError ? 1 : 0);
    }

    public /* synthetic */ NfcCanEntryScreen(String str, int i, Number number, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i2 & 4) != 0 ? null : number, z, (i2 & 16) != 0 ? false : z2);
    }
}
