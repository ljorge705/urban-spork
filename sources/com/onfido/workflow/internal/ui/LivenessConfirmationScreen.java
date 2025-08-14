package com.onfido.workflow.internal.ui;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenges;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Screens.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0081\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\t\u001a\u00020\u0003HÂ\u0003J\t\u0010\n\u001a\u00020\u0005HÂ\u0003J\t\u0010\u000b\u001a\u00020\u0007HÂ\u0003J'\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0010HÖ\u0001R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/onfido/workflow/internal/ui/LivenessConfirmationScreen;", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "videoPath", "", "challenges", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;", "showRecordedVideo", "", "(Ljava/lang/String;Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;Z)V", "component1", "component2", "component3", Constants.COPY_TYPE, "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class LivenessConfirmationScreen implements Screen {
    public static final String KEY_REQUEST = "request_key_liveness_confirmation";
    private final LivenessPerformedChallenges challenges;
    private final boolean showRecordedVideo;
    private final String videoPath;
    public static final Parcelable.Creator<LivenessConfirmationScreen> CREATOR = new Creator();

    /* compiled from: Screens.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<LivenessConfirmationScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LivenessConfirmationScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new LivenessConfirmationScreen(parcel.readString(), (LivenessPerformedChallenges) parcel.readSerializable(), parcel.readInt() != 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LivenessConfirmationScreen[] newArray(int i) {
            return new LivenessConfirmationScreen[i];
        }
    }

    /* renamed from: component1, reason: from getter */
    private final String getVideoPath() {
        return this.videoPath;
    }

    /* renamed from: component2, reason: from getter */
    private final LivenessPerformedChallenges getChallenges() {
        return this.challenges;
    }

    /* renamed from: component3, reason: from getter */
    private final boolean getShowRecordedVideo() {
        return this.showRecordedVideo;
    }

    public static /* synthetic */ LivenessConfirmationScreen copy$default(LivenessConfirmationScreen livenessConfirmationScreen, String str, LivenessPerformedChallenges livenessPerformedChallenges, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = livenessConfirmationScreen.videoPath;
        }
        if ((i & 2) != 0) {
            livenessPerformedChallenges = livenessConfirmationScreen.challenges;
        }
        if ((i & 4) != 0) {
            z = livenessConfirmationScreen.showRecordedVideo;
        }
        return livenessConfirmationScreen.copy(str, livenessPerformedChallenges, z);
    }

    public final LivenessConfirmationScreen copy(String videoPath, LivenessPerformedChallenges challenges, boolean showRecordedVideo) {
        Intrinsics.checkNotNullParameter(videoPath, "videoPath");
        Intrinsics.checkNotNullParameter(challenges, "challenges");
        return new LivenessConfirmationScreen(videoPath, challenges, showRecordedVideo);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LivenessConfirmationScreen)) {
            return false;
        }
        LivenessConfirmationScreen livenessConfirmationScreen = (LivenessConfirmationScreen) other;
        return Intrinsics.areEqual(this.videoPath, livenessConfirmationScreen.videoPath) && Intrinsics.areEqual(this.challenges, livenessConfirmationScreen.challenges) && this.showRecordedVideo == livenessConfirmationScreen.showRecordedVideo;
    }

    public int hashCode() {
        return (((this.videoPath.hashCode() * 31) + this.challenges.hashCode()) * 31) + Boolean.hashCode(this.showRecordedVideo);
    }

    public String toString() {
        return "LivenessConfirmationScreen(videoPath=" + this.videoPath + ", challenges=" + this.challenges + ", showRecordedVideo=" + this.showRecordedVideo + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.videoPath);
        parcel.writeSerializable(this.challenges);
        parcel.writeInt(this.showRecordedVideo ? 1 : 0);
    }

    public LivenessConfirmationScreen(String videoPath, LivenessPerformedChallenges challenges, boolean z) {
        Intrinsics.checkNotNullParameter(videoPath, "videoPath");
        Intrinsics.checkNotNullParameter(challenges, "challenges");
        this.videoPath = videoPath;
        this.challenges = challenges;
        this.showRecordedVideo = z;
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return Screen.DefaultImpls.screenKey(this);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public Fragment createFragment() {
        return LivenessConfirmationFragment.INSTANCE.createInstance(KEY_REQUEST, this.videoPath, this.showRecordedVideo, this.challenges);
    }
}
