package com.onfido.workflow.internal.ui;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.ui.nfc.NfcDeviceNotSupportedFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Screens.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bHÖ\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/onfido/workflow/internal/ui/NfcNotSupportedScreen;", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "()V", "KEY_REQUEST", "", "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class NfcNotSupportedScreen implements Screen {
    public static final String KEY_REQUEST = "request_key_nfc_not_supported";
    public static final NfcNotSupportedScreen INSTANCE = new NfcNotSupportedScreen();
    public static final Parcelable.Creator<NfcNotSupportedScreen> CREATOR = new Creator();

    /* compiled from: Screens.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<NfcNotSupportedScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcNotSupportedScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.readInt();
            return NfcNotSupportedScreen.INSTANCE;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcNotSupportedScreen[] newArray(int i) {
            return new NfcNotSupportedScreen[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(1);
    }

    private NfcNotSupportedScreen() {
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return Screen.DefaultImpls.screenKey(this);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public Fragment createFragment() {
        return NfcDeviceNotSupportedFragment.INSTANCE.createInstance(KEY_REQUEST);
    }
}
