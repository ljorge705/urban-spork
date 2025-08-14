package com.onfido.android.sdk.capture.internal.navigation.screens;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment;
import com.onfido.android.sdk.capture.utils.CountryCode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0001%B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\bHÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J9\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u0013\u0010\u001a\u001a\u00020\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\u0019\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0019HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006&"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/navigation/screens/DocumentSelectionScreen;", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentTypes", "", "Lcom/onfido/android/sdk/capture/DocumentType;", "nfcRequiredWarningVisible", "", "isInWorkflow", "(Lcom/onfido/android/sdk/capture/utils/CountryCode;Ljava/util/List;ZZ)V", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getDocumentTypes", "()Ljava/util/List;", "()Z", "getNfcRequiredWarningVisible", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "", "equals", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DocumentSelectionScreen implements Screen {
    public static final String KEY_REQUEST = "request_key_document_selection";
    private final CountryCode countryCode;
    private final List<DocumentType> documentTypes;
    private final boolean isInWorkflow;
    private final boolean nfcRequiredWarningVisible;
    public static final Parcelable.Creator<DocumentSelectionScreen> CREATOR = new Creator();

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<DocumentSelectionScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DocumentSelectionScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            CountryCode countryCodeValueOf = parcel.readInt() == 0 ? null : CountryCode.valueOf(parcel.readString());
            int i = parcel.readInt();
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 != i; i2++) {
                arrayList.add(DocumentType.valueOf(parcel.readString()));
            }
            return new DocumentSelectionScreen(countryCodeValueOf, arrayList, parcel.readInt() != 0, parcel.readInt() != 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DocumentSelectionScreen[] newArray(int i) {
            return new DocumentSelectionScreen[i];
        }
    }

    public DocumentSelectionScreen() {
        this(null, null, false, false, 15, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DocumentSelectionScreen copy$default(DocumentSelectionScreen documentSelectionScreen, CountryCode countryCode, List list, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            countryCode = documentSelectionScreen.countryCode;
        }
        if ((i & 2) != 0) {
            list = documentSelectionScreen.documentTypes;
        }
        if ((i & 4) != 0) {
            z = documentSelectionScreen.nfcRequiredWarningVisible;
        }
        if ((i & 8) != 0) {
            z2 = documentSelectionScreen.isInWorkflow;
        }
        return documentSelectionScreen.copy(countryCode, list, z, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final CountryCode getCountryCode() {
        return this.countryCode;
    }

    public final List<DocumentType> component2() {
        return this.documentTypes;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getNfcRequiredWarningVisible() {
        return this.nfcRequiredWarningVisible;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getIsInWorkflow() {
        return this.isInWorkflow;
    }

    public final DocumentSelectionScreen copy(CountryCode countryCode, List<? extends DocumentType> documentTypes, boolean nfcRequiredWarningVisible, boolean isInWorkflow) {
        Intrinsics.checkNotNullParameter(documentTypes, "documentTypes");
        return new DocumentSelectionScreen(countryCode, documentTypes, nfcRequiredWarningVisible, isInWorkflow);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public Fragment createFragment() {
        return DocumentSelectionHostFragment.INSTANCE.createInstance(this.countryCode, KEY_REQUEST, this.nfcRequiredWarningVisible, this.isInWorkflow);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentSelectionScreen)) {
            return false;
        }
        DocumentSelectionScreen documentSelectionScreen = (DocumentSelectionScreen) other;
        return this.countryCode == documentSelectionScreen.countryCode && Intrinsics.areEqual(this.documentTypes, documentSelectionScreen.documentTypes) && this.nfcRequiredWarningVisible == documentSelectionScreen.nfcRequiredWarningVisible && this.isInWorkflow == documentSelectionScreen.isInWorkflow;
    }

    public final CountryCode getCountryCode() {
        return this.countryCode;
    }

    public final List<DocumentType> getDocumentTypes() {
        return this.documentTypes;
    }

    public final boolean getNfcRequiredWarningVisible() {
        return this.nfcRequiredWarningVisible;
    }

    public int hashCode() {
        CountryCode countryCode = this.countryCode;
        return ((((((countryCode == null ? 0 : countryCode.hashCode()) * 31) + this.documentTypes.hashCode()) * 31) + Boolean.hashCode(this.nfcRequiredWarningVisible)) * 31) + Boolean.hashCode(this.isInWorkflow);
    }

    public final boolean isInWorkflow() {
        return this.isInWorkflow;
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return Screen.DefaultImpls.screenKey(this);
    }

    public String toString() {
        return "DocumentSelectionScreen(countryCode=" + this.countryCode + ", documentTypes=" + this.documentTypes + ", nfcRequiredWarningVisible=" + this.nfcRequiredWarningVisible + ", isInWorkflow=" + this.isInWorkflow + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        CountryCode countryCode = this.countryCode;
        if (countryCode == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeString(countryCode.name());
        }
        List<DocumentType> list = this.documentTypes;
        parcel.writeInt(list.size());
        Iterator<DocumentType> it = list.iterator();
        while (it.hasNext()) {
            parcel.writeString(it.next().name());
        }
        parcel.writeInt(this.nfcRequiredWarningVisible ? 1 : 0);
        parcel.writeInt(this.isInWorkflow ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DocumentSelectionScreen(CountryCode countryCode, List<? extends DocumentType> documentTypes, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(documentTypes, "documentTypes");
        this.countryCode = countryCode;
        this.documentTypes = documentTypes;
        this.nfcRequiredWarningVisible = z;
        this.isInWorkflow = z2;
    }

    public /* synthetic */ DocumentSelectionScreen(CountryCode countryCode, List list, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : countryCode, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2);
    }
}
