package com.onfido.android.sdk.capture.component.active.video.capture.presentation.host;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.common.permissions.PermissionsManagementFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.AvcScreen;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0081\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003HÂ\u0003J\u0013\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\b\u0010\u0007\u001a\u00020\bH\u0016J\t\u0010\t\u001a\u00020\nHÖ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\nHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/PermissionsScreen;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/AvcScreen;", "captureStepDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "(Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;)V", "component1", Constants.COPY_TYPE, "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class PermissionsScreen implements AvcScreen {
    public static final String KEY_REQUEST = "request_key_permissions_screen";
    private final CaptureStepDataBundle captureStepDataBundle;
    public static final Parcelable.Creator<PermissionsScreen> CREATOR = new Creator();

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<PermissionsScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PermissionsScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new PermissionsScreen((CaptureStepDataBundle) parcel.readSerializable());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PermissionsScreen[] newArray(int i) {
            return new PermissionsScreen[i];
        }
    }

    public PermissionsScreen(CaptureStepDataBundle captureStepDataBundle) {
        Intrinsics.checkNotNullParameter(captureStepDataBundle, "captureStepDataBundle");
        this.captureStepDataBundle = captureStepDataBundle;
    }

    /* renamed from: component1, reason: from getter */
    private final CaptureStepDataBundle getCaptureStepDataBundle() {
        return this.captureStepDataBundle;
    }

    public static /* synthetic */ PermissionsScreen copy$default(PermissionsScreen permissionsScreen, CaptureStepDataBundle captureStepDataBundle, int i, Object obj) {
        if ((i & 1) != 0) {
            captureStepDataBundle = permissionsScreen.captureStepDataBundle;
        }
        return permissionsScreen.copy(captureStepDataBundle);
    }

    public final PermissionsScreen copy(CaptureStepDataBundle captureStepDataBundle) {
        Intrinsics.checkNotNullParameter(captureStepDataBundle, "captureStepDataBundle");
        return new PermissionsScreen(captureStepDataBundle);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public Fragment createFragment() {
        return PermissionsManagementFragment.INSTANCE.createInstance("request_key_permissions_screen", this.captureStepDataBundle);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PermissionsScreen) && Intrinsics.areEqual(this.captureStepDataBundle, ((PermissionsScreen) other).captureStepDataBundle);
    }

    public int hashCode() {
        return this.captureStepDataBundle.hashCode();
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return AvcScreen.DefaultImpls.screenKey(this);
    }

    public String toString() {
        return "PermissionsScreen(captureStepDataBundle=" + this.captureStepDataBundle + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeSerializable(this.captureStepDataBundle);
    }
}
