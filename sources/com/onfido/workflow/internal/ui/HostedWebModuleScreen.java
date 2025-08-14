package com.onfido.workflow.internal.ui;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.hosted.web.module.HostedWebModuleFragment;
import com.onfido.hosted.web.module.model.HostedWebModuleModuleInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Screens.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0081\b\u0018\u0000  2\u00020\u0001:\u0001 B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\u0013\u0010\u0016\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0015HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001J\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0015HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006!"}, d2 = {"Lcom/onfido/workflow/internal/ui/HostedWebModuleScreen;", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "moduleInfo", "Lcom/onfido/hosted/web/module/model/HostedWebModuleModuleInfo;", "isDarkModeEnabled", "", "bridgeUrl", "", "(Lcom/onfido/hosted/web/module/model/HostedWebModuleModuleInfo;ZLjava/lang/String;)V", "getBridgeUrl", "()Ljava/lang/String;", "()Z", "getModuleInfo", "()Lcom/onfido/hosted/web/module/model/HostedWebModuleModuleInfo;", "component1", "component2", "component3", Constants.COPY_TYPE, "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class HostedWebModuleScreen implements Screen {
    public static final String KEY_REQUEST = "request_key_qes_consent";
    private final String bridgeUrl;
    private final boolean isDarkModeEnabled;
    private final HostedWebModuleModuleInfo moduleInfo;
    public static final Parcelable.Creator<HostedWebModuleScreen> CREATOR = new Creator();

    /* compiled from: Screens.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<HostedWebModuleScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final HostedWebModuleScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new HostedWebModuleScreen((HostedWebModuleModuleInfo) parcel.readParcelable(HostedWebModuleScreen.class.getClassLoader()), parcel.readInt() != 0, parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final HostedWebModuleScreen[] newArray(int i) {
            return new HostedWebModuleScreen[i];
        }
    }

    public static /* synthetic */ HostedWebModuleScreen copy$default(HostedWebModuleScreen hostedWebModuleScreen, HostedWebModuleModuleInfo hostedWebModuleModuleInfo, boolean z, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            hostedWebModuleModuleInfo = hostedWebModuleScreen.moduleInfo;
        }
        if ((i & 2) != 0) {
            z = hostedWebModuleScreen.isDarkModeEnabled;
        }
        if ((i & 4) != 0) {
            str = hostedWebModuleScreen.bridgeUrl;
        }
        return hostedWebModuleScreen.copy(hostedWebModuleModuleInfo, z, str);
    }

    /* renamed from: component1, reason: from getter */
    public final HostedWebModuleModuleInfo getModuleInfo() {
        return this.moduleInfo;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsDarkModeEnabled() {
        return this.isDarkModeEnabled;
    }

    /* renamed from: component3, reason: from getter */
    public final String getBridgeUrl() {
        return this.bridgeUrl;
    }

    public final HostedWebModuleScreen copy(HostedWebModuleModuleInfo moduleInfo, boolean isDarkModeEnabled, String bridgeUrl) {
        Intrinsics.checkNotNullParameter(moduleInfo, "moduleInfo");
        return new HostedWebModuleScreen(moduleInfo, isDarkModeEnabled, bridgeUrl);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HostedWebModuleScreen)) {
            return false;
        }
        HostedWebModuleScreen hostedWebModuleScreen = (HostedWebModuleScreen) other;
        return Intrinsics.areEqual(this.moduleInfo, hostedWebModuleScreen.moduleInfo) && this.isDarkModeEnabled == hostedWebModuleScreen.isDarkModeEnabled && Intrinsics.areEqual(this.bridgeUrl, hostedWebModuleScreen.bridgeUrl);
    }

    public final String getBridgeUrl() {
        return this.bridgeUrl;
    }

    public final HostedWebModuleModuleInfo getModuleInfo() {
        return this.moduleInfo;
    }

    public int hashCode() {
        int iHashCode = ((this.moduleInfo.hashCode() * 31) + Boolean.hashCode(this.isDarkModeEnabled)) * 31;
        String str = this.bridgeUrl;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public final boolean isDarkModeEnabled() {
        return this.isDarkModeEnabled;
    }

    public String toString() {
        return "HostedWebModuleScreen(moduleInfo=" + this.moduleInfo + ", isDarkModeEnabled=" + this.isDarkModeEnabled + ", bridgeUrl=" + this.bridgeUrl + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeParcelable(this.moduleInfo, flags);
        parcel.writeInt(this.isDarkModeEnabled ? 1 : 0);
        parcel.writeString(this.bridgeUrl);
    }

    public HostedWebModuleScreen(HostedWebModuleModuleInfo moduleInfo, boolean z, String str) {
        Intrinsics.checkNotNullParameter(moduleInfo, "moduleInfo");
        this.moduleInfo = moduleInfo;
        this.isDarkModeEnabled = z;
        this.bridgeUrl = str;
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return Screen.DefaultImpls.screenKey(this);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public Fragment createFragment() {
        return HostedWebModuleFragment.INSTANCE.newInstance(KEY_REQUEST, this.moduleInfo, this.bridgeUrl, this.isDarkModeEnabled);
    }
}
