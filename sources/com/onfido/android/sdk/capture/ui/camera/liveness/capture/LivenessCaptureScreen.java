package com.onfido.android.sdk.capture.ui.camera.liveness.capture;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenges;
import io.sentry.SentryEvent;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0016J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\rJ\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bHÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0016\u0010\u0006\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen;", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "()V", "EXTRA_KEY_RESULT", "", "getEXTRA_KEY_RESULT$annotations", "KEY_REQUEST", "getKEY_REQUEST$annotations", "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "", "retrieveResult", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult;", "bundle", "Landroid/os/Bundle;", "storeResult", OnfidoLauncher.KEY_RESULT, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "LivenessCaptureResult", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LivenessCaptureScreen implements Screen {
    private static final String EXTRA_KEY_RESULT = "EXTRA_KEY_RESULT_LIVENESS_CAPTURE";
    public static final String KEY_REQUEST = "face_liveness_capture";
    public static final LivenessCaptureScreen INSTANCE = new LivenessCaptureScreen();
    public static final Parcelable.Creator<LivenessCaptureScreen> CREATOR = new Creator();

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<LivenessCaptureScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LivenessCaptureScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.readInt();
            return LivenessCaptureScreen.INSTANCE;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LivenessCaptureScreen[] newArray(int i) {
            return new LivenessCaptureScreen[i];
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult;", "Landroid/os/Parcelable;", "()V", "Back", "Completed", "Error", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult$Back;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult$Completed;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult$Error;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class LivenessCaptureResult implements Parcelable {

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\u0004HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004HÖ\u0001¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult$Back;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult;", "()V", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Back extends LivenessCaptureResult {
            public static final Back INSTANCE = new Back();
            public static final Parcelable.Creator<Back> CREATOR = new Creator();

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Back> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Back createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return Back.INSTANCE;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Back[] newArray(int i) {
                    return new Back[i];
                }
            }

            private Back() {
                super(null);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof Back);
            }

            public int hashCode() {
                return -1125391063;
            }

            public String toString() {
                return "Back";
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeInt(1);
            }
        }

        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult$Completed;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult;", "videoPath", "", "performedChallenges", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;", "(Ljava/lang/String;Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;)V", "getPerformedChallenges", "()Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;", "getVideoPath", "()Ljava/lang/String;", "component1", "component2", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Completed extends LivenessCaptureResult {
            public static final Parcelable.Creator<Completed> CREATOR = new Creator();
            private final LivenessPerformedChallenges performedChallenges;
            private final String videoPath;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Completed> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Completed createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new Completed(parcel.readString(), (LivenessPerformedChallenges) parcel.readSerializable());
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Completed[] newArray(int i) {
                    return new Completed[i];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Completed(String videoPath, LivenessPerformedChallenges performedChallenges) {
                super(null);
                Intrinsics.checkNotNullParameter(videoPath, "videoPath");
                Intrinsics.checkNotNullParameter(performedChallenges, "performedChallenges");
                this.videoPath = videoPath;
                this.performedChallenges = performedChallenges;
            }

            public static /* synthetic */ Completed copy$default(Completed completed, String str, LivenessPerformedChallenges livenessPerformedChallenges, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = completed.videoPath;
                }
                if ((i & 2) != 0) {
                    livenessPerformedChallenges = completed.performedChallenges;
                }
                return completed.copy(str, livenessPerformedChallenges);
            }

            /* renamed from: component1, reason: from getter */
            public final String getVideoPath() {
                return this.videoPath;
            }

            /* renamed from: component2, reason: from getter */
            public final LivenessPerformedChallenges getPerformedChallenges() {
                return this.performedChallenges;
            }

            public final Completed copy(String videoPath, LivenessPerformedChallenges performedChallenges) {
                Intrinsics.checkNotNullParameter(videoPath, "videoPath");
                Intrinsics.checkNotNullParameter(performedChallenges, "performedChallenges");
                return new Completed(videoPath, performedChallenges);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Completed)) {
                    return false;
                }
                Completed completed = (Completed) other;
                return Intrinsics.areEqual(this.videoPath, completed.videoPath) && Intrinsics.areEqual(this.performedChallenges, completed.performedChallenges);
            }

            public final LivenessPerformedChallenges getPerformedChallenges() {
                return this.performedChallenges;
            }

            public final String getVideoPath() {
                return this.videoPath;
            }

            public int hashCode() {
                return (this.videoPath.hashCode() * 31) + this.performedChallenges.hashCode();
            }

            public String toString() {
                return "Completed(videoPath=" + this.videoPath + ", performedChallenges=" + this.performedChallenges + ')';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeString(this.videoPath);
                parcel.writeSerializable(this.performedChallenges);
            }
        }

        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\b\u001a\u00060\u0003j\u0002`\u0004HÆ\u0003J\u0017\u0010\t\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004HÆ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000bHÖ\u0001R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult$Error;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult;", SentryEvent.JsonKeys.EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/Exception;)V", "getException", "()Ljava/lang/Exception;", "component1", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Error extends LivenessCaptureResult {
            public static final Parcelable.Creator<Error> CREATOR = new Creator();
            private final Exception exception;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Error> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Error createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new Error((Exception) parcel.readSerializable());
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Error[] newArray(int i) {
                    return new Error[i];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Error(Exception exception) {
                super(null);
                Intrinsics.checkNotNullParameter(exception, "exception");
                this.exception = exception;
            }

            public static /* synthetic */ Error copy$default(Error error, Exception exc, int i, Object obj) {
                if ((i & 1) != 0) {
                    exc = error.exception;
                }
                return error.copy(exc);
            }

            /* renamed from: component1, reason: from getter */
            public final Exception getException() {
                return this.exception;
            }

            public final Error copy(Exception exception) {
                Intrinsics.checkNotNullParameter(exception, "exception");
                return new Error(exception);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Error) && Intrinsics.areEqual(this.exception, ((Error) other).exception);
            }

            public final Exception getException() {
                return this.exception;
            }

            public int hashCode() {
                return this.exception.hashCode();
            }

            public String toString() {
                return "Error(exception=" + this.exception + ')';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeSerializable(this.exception);
            }
        }

        private LivenessCaptureResult() {
        }

        public /* synthetic */ LivenessCaptureResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private LivenessCaptureScreen() {
    }

    private static /* synthetic */ void getEXTRA_KEY_RESULT$annotations() {
    }

    public static /* synthetic */ void getKEY_REQUEST$annotations() {
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public Fragment createFragment() {
        return LivenessCaptureFragment.INSTANCE.createInstance$onfido_capture_sdk_core_release("face_liveness_capture");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final LivenessCaptureResult retrieveResult(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        LivenessCaptureResult livenessCaptureResult = (LivenessCaptureResult) bundle.getParcelable(EXTRA_KEY_RESULT);
        if (livenessCaptureResult != null) {
            return livenessCaptureResult;
        }
        throw new IllegalStateException(("No liveness result in the bundle= " + bundle).toString());
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return Screen.DefaultImpls.screenKey(this);
    }

    public final Bundle storeResult(LivenessCaptureResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        return BundleKt.bundleOf(TuplesKt.to(EXTRA_KEY_RESULT, result));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(1);
    }
}
