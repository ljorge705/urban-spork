package com.onfido.android.sdk.capture.ui.nfc;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.nfc.NfcScreen;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u00002\u00020\u0001:\u0002$%B/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÂ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0012\u001a\u00020\tHÂ\u0003J\t\u0010\u0013\u001a\u00020\u000bHÂ\u0003J=\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0018HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001J\u0019\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0018HÖ\u0001R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcScreen;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "failureReason", "", "errorState", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$ErrorState;", "primaryAction", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$Actions$Primary;", "secondaryAction", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$Actions$Secondary;", "(Lcom/onfido/android/sdk/capture/DocumentType;Ljava/lang/String;Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$ErrorState;Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$Actions$Primary;Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$Actions$Secondary;)V", "getErrorState", "()Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$ErrorState;", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Actions", "ErrorState", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class NfcFailedScreen implements NfcScreen {
    public static final Parcelable.Creator<NfcFailedScreen> CREATOR = new Creator();
    private final DocumentType documentType;
    private final ErrorState errorState;
    private final String failureReason;
    private final Actions.Primary primaryAction;
    private final Actions.Secondary secondaryAction;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$Actions;", "Landroid/os/Parcelable;", "Primary", "Secondary", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$Actions$Primary;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$Actions$Secondary;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Actions extends Parcelable {

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0011\b\u0002\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$Actions$Primary;", "", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$Actions;", "title", "", "(Ljava/lang/String;II)V", "getTitle", "()I", "describeContents", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Retry", "DifferentDocument", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Primary implements Actions {
            private static final /* synthetic */ EnumEntries $ENTRIES;
            private static final /* synthetic */ Primary[] $VALUES;
            public static final Parcelable.Creator<Primary> CREATOR;
            private final int title;
            public static final Primary Retry = new Primary("Retry", 0, R.string.onfido_nfc_scan_error_button_primary);
            public static final Primary DifferentDocument = new Primary("DifferentDocument", 1, R.string.onfido_nfc_scan_error_final_primary_button);

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Primary> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Primary createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return Primary.valueOf(parcel.readString());
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Primary[] newArray(int i) {
                    return new Primary[i];
                }
            }

            private static final /* synthetic */ Primary[] $values() {
                return new Primary[]{Retry, DifferentDocument};
            }

            static {
                Primary[] primaryArr$values = $values();
                $VALUES = primaryArr$values;
                $ENTRIES = EnumEntriesKt.enumEntries(primaryArr$values);
                CREATOR = new Creator();
            }

            private Primary(String str, int i, int i2) {
                this.title = i2;
            }

            public static EnumEntries<Primary> getEntries() {
                return $ENTRIES;
            }

            public static Primary valueOf(String str) {
                return (Primary) Enum.valueOf(Primary.class, str);
            }

            public static Primary[] values() {
                return (Primary[]) $VALUES.clone();
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public final int getTitle() {
                return this.title;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeString(name());
            }
        }

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0019\b\u0002\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$Actions$Secondary;", "", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$Actions;", "title", "", ViewProps.VISIBLE, "", "(Ljava/lang/String;IIZ)V", "getTitle", "()I", "getVisible", "()Z", "describeContents", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Skip", "Hide", "Exit", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Secondary implements Actions {
            private static final /* synthetic */ EnumEntries $ENTRIES;
            private static final /* synthetic */ Secondary[] $VALUES;
            public static final Parcelable.Creator<Secondary> CREATOR;
            private final int title;
            private final boolean visible;
            public static final Secondary Skip = new Secondary("Skip", 0, R.string.onfido_nfc_scan_error_final_possible_card_secondary_button, true);
            public static final Secondary Hide = new Secondary("Hide", 1, 0, false);
            public static final Secondary Exit = new Secondary("Exit", 2, R.string.onfido_nfc_scan_error_final_required_card_secondary_button, true);

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Secondary> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Secondary createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return Secondary.valueOf(parcel.readString());
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Secondary[] newArray(int i) {
                    return new Secondary[i];
                }
            }

            private static final /* synthetic */ Secondary[] $values() {
                return new Secondary[]{Skip, Hide, Exit};
            }

            static {
                Secondary[] secondaryArr$values = $values();
                $VALUES = secondaryArr$values;
                $ENTRIES = EnumEntriesKt.enumEntries(secondaryArr$values);
                CREATOR = new Creator();
            }

            private Secondary(String str, int i, int i2, boolean z) {
                this.title = i2;
                this.visible = z;
            }

            public static EnumEntries<Secondary> getEntries() {
                return $ENTRIES;
            }

            public static Secondary valueOf(String str) {
                return (Secondary) Enum.valueOf(Secondary.class, str);
            }

            public static Secondary[] values() {
                return (Secondary[]) $VALUES.clone();
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public final int getTitle() {
                return this.title;
            }

            public final boolean getVisible() {
                return this.visible;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeString(name());
            }
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<NfcFailedScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcFailedScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new NfcFailedScreen(parcel.readInt() == 0 ? null : DocumentType.valueOf(parcel.readString()), parcel.readString(), ErrorState.CREATOR.createFromParcel(parcel), Actions.Primary.CREATOR.createFromParcel(parcel), Actions.Secondary.CREATOR.createFromParcel(parcel));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcFailedScreen[] newArray(int i) {
            return new NfcFailedScreen[i];
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u001b\b\u0002\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$ErrorState;", "", "Landroid/os/Parcelable;", "title", "", KeychainModule.AuthPromptOptions.SUBTITLE, "(Ljava/lang/String;III)V", "getSubtitle", "()I", "getTitle", "describeContents", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "ScanningFailed", "VerificationFailed", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ErrorState implements Parcelable {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ ErrorState[] $VALUES;
        public static final Parcelable.Creator<ErrorState> CREATOR;
        public static final ErrorState ScanningFailed = new ErrorState("ScanningFailed", 0, R.string.onfido_nfc_scan_error_title, 0);
        public static final ErrorState VerificationFailed = new ErrorState("VerificationFailed", 1, R.string.onfido_nfc_scan_error_final_title, R.string.onfido_nfc_scan_error_final_subtitle);
        private final int subtitle;
        private final int title;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<ErrorState> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final ErrorState createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return ErrorState.valueOf(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final ErrorState[] newArray(int i) {
                return new ErrorState[i];
            }
        }

        private static final /* synthetic */ ErrorState[] $values() {
            return new ErrorState[]{ScanningFailed, VerificationFailed};
        }

        static {
            ErrorState[] errorStateArr$values = $values();
            $VALUES = errorStateArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(errorStateArr$values);
            CREATOR = new Creator();
        }

        private ErrorState(String str, int i, int i2, int i3) {
            this.title = i2;
            this.subtitle = i3;
        }

        public static EnumEntries<ErrorState> getEntries() {
            return $ENTRIES;
        }

        public static ErrorState valueOf(String str) {
            return (ErrorState) Enum.valueOf(ErrorState.class, str);
        }

        public static ErrorState[] values() {
            return (ErrorState[]) $VALUES.clone();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public final int getSubtitle() {
            return this.subtitle;
        }

        public final int getTitle() {
            return this.title;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(name());
        }
    }

    public NfcFailedScreen(DocumentType documentType, String failureReason, ErrorState errorState, Actions.Primary primaryAction, Actions.Secondary secondaryAction) {
        Intrinsics.checkNotNullParameter(failureReason, "failureReason");
        Intrinsics.checkNotNullParameter(errorState, "errorState");
        Intrinsics.checkNotNullParameter(primaryAction, "primaryAction");
        Intrinsics.checkNotNullParameter(secondaryAction, "secondaryAction");
        this.documentType = documentType;
        this.failureReason = failureReason;
        this.errorState = errorState;
        this.primaryAction = primaryAction;
        this.secondaryAction = secondaryAction;
    }

    /* renamed from: component1, reason: from getter */
    private final DocumentType getDocumentType() {
        return this.documentType;
    }

    /* renamed from: component2, reason: from getter */
    private final String getFailureReason() {
        return this.failureReason;
    }

    /* renamed from: component4, reason: from getter */
    private final Actions.Primary getPrimaryAction() {
        return this.primaryAction;
    }

    /* renamed from: component5, reason: from getter */
    private final Actions.Secondary getSecondaryAction() {
        return this.secondaryAction;
    }

    public static /* synthetic */ NfcFailedScreen copy$default(NfcFailedScreen nfcFailedScreen, DocumentType documentType, String str, ErrorState errorState, Actions.Primary primary, Actions.Secondary secondary, int i, Object obj) {
        if ((i & 1) != 0) {
            documentType = nfcFailedScreen.documentType;
        }
        if ((i & 2) != 0) {
            str = nfcFailedScreen.failureReason;
        }
        String str2 = str;
        if ((i & 4) != 0) {
            errorState = nfcFailedScreen.errorState;
        }
        ErrorState errorState2 = errorState;
        if ((i & 8) != 0) {
            primary = nfcFailedScreen.primaryAction;
        }
        Actions.Primary primary2 = primary;
        if ((i & 16) != 0) {
            secondary = nfcFailedScreen.secondaryAction;
        }
        return nfcFailedScreen.copy(documentType, str2, errorState2, primary2, secondary);
    }

    /* renamed from: component3, reason: from getter */
    public final ErrorState getErrorState() {
        return this.errorState;
    }

    public final NfcFailedScreen copy(DocumentType documentType, String failureReason, ErrorState errorState, Actions.Primary primaryAction, Actions.Secondary secondaryAction) {
        Intrinsics.checkNotNullParameter(failureReason, "failureReason");
        Intrinsics.checkNotNullParameter(errorState, "errorState");
        Intrinsics.checkNotNullParameter(primaryAction, "primaryAction");
        Intrinsics.checkNotNullParameter(secondaryAction, "secondaryAction");
        return new NfcFailedScreen(documentType, failureReason, errorState, primaryAction, secondaryAction);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public Fragment createFragment() {
        return NfcScanFailFragment.INSTANCE.createInstance(this.documentType, this.failureReason, this.errorState, this.primaryAction, this.secondaryAction);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NfcFailedScreen)) {
            return false;
        }
        NfcFailedScreen nfcFailedScreen = (NfcFailedScreen) other;
        return this.documentType == nfcFailedScreen.documentType && Intrinsics.areEqual(this.failureReason, nfcFailedScreen.failureReason) && this.errorState == nfcFailedScreen.errorState && this.primaryAction == nfcFailedScreen.primaryAction && this.secondaryAction == nfcFailedScreen.secondaryAction;
    }

    public final ErrorState getErrorState() {
        return this.errorState;
    }

    public int hashCode() {
        DocumentType documentType = this.documentType;
        return ((((((((documentType == null ? 0 : documentType.hashCode()) * 31) + this.failureReason.hashCode()) * 31) + this.errorState.hashCode()) * 31) + this.primaryAction.hashCode()) * 31) + this.secondaryAction.hashCode();
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return NfcScreen.DefaultImpls.screenKey(this);
    }

    public String toString() {
        return "NfcFailedScreen(documentType=" + this.documentType + ", failureReason=" + this.failureReason + ", errorState=" + this.errorState + ", primaryAction=" + this.primaryAction + ", secondaryAction=" + this.secondaryAction + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        DocumentType documentType = this.documentType;
        if (documentType == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeString(documentType.name());
        }
        parcel.writeString(this.failureReason);
        this.errorState.writeToParcel(parcel, flags);
        this.primaryAction.writeToParcel(parcel, flags);
        this.secondaryAction.writeToParcel(parcel, flags);
    }
}
