package com.onfido.android.sdk.capture.component.active.video.capture.presentation.host;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.AvcScreen;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.uploaderror.MotionUploadErrorFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003HÂ\u0003J\u0013\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\b\u0010\u0007\u001a\u00020\bH\u0016J\t\u0010\t\u001a\u00020\nHÖ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\nHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/AvcUploadErrorScreen;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/AvcScreen;", "filePath", "", "(Ljava/lang/String;)V", "component1", Constants.COPY_TYPE, "createFragment", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/uploaderror/MotionUploadErrorFragment;", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class AvcUploadErrorScreen implements AvcScreen {
    public static final Parcelable.Creator<AvcUploadErrorScreen> CREATOR = new Creator();
    private final String filePath;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<AvcUploadErrorScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AvcUploadErrorScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new AvcUploadErrorScreen(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AvcUploadErrorScreen[] newArray(int i) {
            return new AvcUploadErrorScreen[i];
        }
    }

    public AvcUploadErrorScreen(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        this.filePath = filePath;
    }

    /* renamed from: component1, reason: from getter */
    private final String getFilePath() {
        return this.filePath;
    }

    public static /* synthetic */ AvcUploadErrorScreen copy$default(AvcUploadErrorScreen avcUploadErrorScreen, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = avcUploadErrorScreen.filePath;
        }
        return avcUploadErrorScreen.copy(str);
    }

    public final AvcUploadErrorScreen copy(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        return new AvcUploadErrorScreen(filePath);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AvcUploadErrorScreen) && Intrinsics.areEqual(this.filePath, ((AvcUploadErrorScreen) other).filePath);
    }

    public int hashCode() {
        return this.filePath.hashCode();
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return AvcScreen.DefaultImpls.screenKey(this);
    }

    public String toString() {
        return "AvcUploadErrorScreen(filePath=" + this.filePath + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.filePath);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public MotionUploadErrorFragment createFragment() {
        return MotionUploadErrorFragment.INSTANCE.createInstance$onfido_capture_sdk_core_release(this.filePath);
    }
}
