package com.onfido.workflow.internal.ui;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.Constants;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.ui.faceintro.FaceIntroFragment;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Screens.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0081\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\bJ\t\u0010\t\u001a\u00020\u0003HÂ\u0003J\t\u0010\n\u001a\u00020\u0003HÂ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÂ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÂ\u0003J7\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0001J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0011HÖ\u0001R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/onfido/workflow/internal/ui/FaceSelfieIntroScreen;", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "title", "", KeychainModule.AuthPromptOptions.SUBTITLE, "infoText", "bullets", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class FaceSelfieIntroScreen implements Screen {
    public static final String KEY_REQUEST = "request_key_face_selfie_intro";
    private final List<String> bullets;
    private final String infoText;
    private final String subtitle;
    private final String title;
    public static final Parcelable.Creator<FaceSelfieIntroScreen> CREATOR = new Creator();

    /* compiled from: Screens.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<FaceSelfieIntroScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final FaceSelfieIntroScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new FaceSelfieIntroScreen(parcel.readString(), parcel.readString(), parcel.readString(), parcel.createStringArrayList());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final FaceSelfieIntroScreen[] newArray(int i) {
            return new FaceSelfieIntroScreen[i];
        }
    }

    /* renamed from: component1, reason: from getter */
    private final String getTitle() {
        return this.title;
    }

    /* renamed from: component2, reason: from getter */
    private final String getSubtitle() {
        return this.subtitle;
    }

    /* renamed from: component3, reason: from getter */
    private final String getInfoText() {
        return this.infoText;
    }

    private final List<String> component4() {
        return this.bullets;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FaceSelfieIntroScreen copy$default(FaceSelfieIntroScreen faceSelfieIntroScreen, String str, String str2, String str3, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = faceSelfieIntroScreen.title;
        }
        if ((i & 2) != 0) {
            str2 = faceSelfieIntroScreen.subtitle;
        }
        if ((i & 4) != 0) {
            str3 = faceSelfieIntroScreen.infoText;
        }
        if ((i & 8) != 0) {
            list = faceSelfieIntroScreen.bullets;
        }
        return faceSelfieIntroScreen.copy(str, str2, str3, list);
    }

    public final FaceSelfieIntroScreen copy(String title, String subtitle, String infoText, List<String> bullets) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        Intrinsics.checkNotNullParameter(infoText, "infoText");
        Intrinsics.checkNotNullParameter(bullets, "bullets");
        return new FaceSelfieIntroScreen(title, subtitle, infoText, bullets);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FaceSelfieIntroScreen)) {
            return false;
        }
        FaceSelfieIntroScreen faceSelfieIntroScreen = (FaceSelfieIntroScreen) other;
        return Intrinsics.areEqual(this.title, faceSelfieIntroScreen.title) && Intrinsics.areEqual(this.subtitle, faceSelfieIntroScreen.subtitle) && Intrinsics.areEqual(this.infoText, faceSelfieIntroScreen.infoText) && Intrinsics.areEqual(this.bullets, faceSelfieIntroScreen.bullets);
    }

    public int hashCode() {
        return (((((this.title.hashCode() * 31) + this.subtitle.hashCode()) * 31) + this.infoText.hashCode()) * 31) + this.bullets.hashCode();
    }

    public String toString() {
        return "FaceSelfieIntroScreen(title=" + this.title + ", subtitle=" + this.subtitle + ", infoText=" + this.infoText + ", bullets=" + this.bullets + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.title);
        parcel.writeString(this.subtitle);
        parcel.writeString(this.infoText);
        parcel.writeStringList(this.bullets);
    }

    public FaceSelfieIntroScreen(String title, String subtitle, String infoText, List<String> bullets) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        Intrinsics.checkNotNullParameter(infoText, "infoText");
        Intrinsics.checkNotNullParameter(bullets, "bullets");
        this.title = title;
        this.subtitle = subtitle;
        this.infoText = infoText;
        this.bullets = bullets;
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return Screen.DefaultImpls.screenKey(this);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public Fragment createFragment() {
        return FaceIntroFragment.INSTANCE.createInstance(KEY_REQUEST, this.title, this.subtitle, this.infoText, this.bullets);
    }
}
