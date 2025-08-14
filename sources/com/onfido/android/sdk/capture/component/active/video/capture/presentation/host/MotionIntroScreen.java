package com.onfido.android.sdk.capture.component.active.video.capture.presentation.host;

import android.os.Parcel;
import android.os.Parcelable;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.AvcScreen;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.intro.MotionIntroFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\t\u0010\u0005\u001a\u00020\u0006HÖ\u0001J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006HÖ\u0001¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionIntroScreen;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/AvcScreen;", "()V", "createFragment", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/intro/MotionIntroFragment;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionIntroScreen implements AvcScreen {
    public static final MotionIntroScreen INSTANCE = new MotionIntroScreen();
    public static final Parcelable.Creator<MotionIntroScreen> CREATOR = new Creator();

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<MotionIntroScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final MotionIntroScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.readInt();
            return MotionIntroScreen.INSTANCE;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final MotionIntroScreen[] newArray(int i) {
            return new MotionIntroScreen[i];
        }
    }

    private MotionIntroScreen() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return AvcScreen.DefaultImpls.screenKey(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(1);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public MotionIntroFragment createFragment() {
        return MotionIntroFragment.INSTANCE.createInstance();
    }
}
