package com.onfido.workflow.internal.ui;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessIntroFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Screens.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0081\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003HÂ\u0003J\u0013\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\b\u0010\u0007\u001a\u00020\bH\u0016J\t\u0010\t\u001a\u00020\nHÖ\u0001J\u0013\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\nHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/onfido/workflow/internal/ui/FaceLivenessIntroScreen;", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "showIntro", "", "(Z)V", "component1", Constants.COPY_TYPE, "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "", "equals", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class FaceLivenessIntroScreen implements Screen {
    public static final String KEY_REQUEST = "request_key_face_liveness_intro";
    private final boolean showIntro;
    public static final Parcelable.Creator<FaceLivenessIntroScreen> CREATOR = new Creator();

    /* compiled from: Screens.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<FaceLivenessIntroScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final FaceLivenessIntroScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new FaceLivenessIntroScreen(parcel.readInt() != 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final FaceLivenessIntroScreen[] newArray(int i) {
            return new FaceLivenessIntroScreen[i];
        }
    }

    public FaceLivenessIntroScreen() {
        this(false, 1, null);
    }

    /* renamed from: component1, reason: from getter */
    private final boolean getShowIntro() {
        return this.showIntro;
    }

    public static /* synthetic */ FaceLivenessIntroScreen copy$default(FaceLivenessIntroScreen faceLivenessIntroScreen, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = faceLivenessIntroScreen.showIntro;
        }
        return faceLivenessIntroScreen.copy(z);
    }

    public final FaceLivenessIntroScreen copy(boolean showIntro) {
        return new FaceLivenessIntroScreen(showIntro);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof FaceLivenessIntroScreen) && this.showIntro == ((FaceLivenessIntroScreen) other).showIntro;
    }

    public int hashCode() {
        return Boolean.hashCode(this.showIntro);
    }

    public String toString() {
        return "FaceLivenessIntroScreen(showIntro=" + this.showIntro + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(this.showIntro ? 1 : 0);
    }

    public FaceLivenessIntroScreen(boolean z) {
        this.showIntro = z;
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return Screen.DefaultImpls.screenKey(this);
    }

    public /* synthetic */ FaceLivenessIntroScreen(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public Fragment createFragment() {
        return LivenessIntroFragment.INSTANCE.createInstance(KEY_REQUEST, this.showIntro);
    }
}
