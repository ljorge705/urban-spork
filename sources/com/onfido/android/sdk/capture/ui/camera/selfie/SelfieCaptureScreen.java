package com.onfido.android.sdk.capture.ui.camera.selfie;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifact;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import io.sentry.SentryEvent;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0016J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\rH\u0007J\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bHÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0016\u0010\u0006\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen;", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "()V", "EXTRA_KEY_RESULT", "", "getEXTRA_KEY_RESULT$annotations", "KEY_REQUEST", "getKEY_REQUEST$annotations", "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "", "retrieveResult", "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult;", "bundle", "Landroid/os/Bundle;", "storeResult", "selfieCaptureResult", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "SelfieCaptureResult", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SelfieCaptureScreen implements Screen {
    private static final String EXTRA_KEY_RESULT = "EXTRA_KEY_RESULT_SELFIE_CAPTURE";
    public static final String KEY_REQUEST = "KEY_REQUEST_SELFIE_CAPTURE";
    public static final SelfieCaptureScreen INSTANCE = new SelfieCaptureScreen();
    public static final Parcelable.Creator<SelfieCaptureScreen> CREATOR = new Creator();

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<SelfieCaptureScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SelfieCaptureScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.readInt();
            return SelfieCaptureScreen.INSTANCE;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SelfieCaptureScreen[] newArray(int i) {
            return new SelfieCaptureScreen[i];
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult;", "Landroid/os/Parcelable;", "()V", "Back", "Completed", "Error", "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult$Back;", "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult$Completed;", "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult$Error;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class SelfieCaptureResult implements Parcelable {

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\u0004HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004HÖ\u0001¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult$Back;", "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult;", "()V", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Back extends SelfieCaptureResult {
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
                return 885783686;
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

        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult$Completed;", "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult;", "uploadId", "", "uploadedArtifact", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "(Ljava/lang/String;Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;)V", "getUploadId", "()Ljava/lang/String;", "getUploadedArtifact", "()Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "component1", "component2", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Completed extends SelfieCaptureResult {
            public static final Parcelable.Creator<Completed> CREATOR = new Creator();
            private final String uploadId;
            private final UploadedArtifact uploadedArtifact;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Completed> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Completed createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new Completed(parcel.readString(), (UploadedArtifact) parcel.readParcelable(Completed.class.getClassLoader()));
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Completed[] newArray(int i) {
                    return new Completed[i];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Completed(String uploadId, UploadedArtifact uploadedArtifact) {
                super(null);
                Intrinsics.checkNotNullParameter(uploadId, "uploadId");
                Intrinsics.checkNotNullParameter(uploadedArtifact, "uploadedArtifact");
                this.uploadId = uploadId;
                this.uploadedArtifact = uploadedArtifact;
            }

            public static /* synthetic */ Completed copy$default(Completed completed, String str, UploadedArtifact uploadedArtifact, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = completed.uploadId;
                }
                if ((i & 2) != 0) {
                    uploadedArtifact = completed.uploadedArtifact;
                }
                return completed.copy(str, uploadedArtifact);
            }

            /* renamed from: component1, reason: from getter */
            public final String getUploadId() {
                return this.uploadId;
            }

            /* renamed from: component2, reason: from getter */
            public final UploadedArtifact getUploadedArtifact() {
                return this.uploadedArtifact;
            }

            public final Completed copy(String uploadId, UploadedArtifact uploadedArtifact) {
                Intrinsics.checkNotNullParameter(uploadId, "uploadId");
                Intrinsics.checkNotNullParameter(uploadedArtifact, "uploadedArtifact");
                return new Completed(uploadId, uploadedArtifact);
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
                return Intrinsics.areEqual(this.uploadId, completed.uploadId) && Intrinsics.areEqual(this.uploadedArtifact, completed.uploadedArtifact);
            }

            public final String getUploadId() {
                return this.uploadId;
            }

            public final UploadedArtifact getUploadedArtifact() {
                return this.uploadedArtifact;
            }

            public int hashCode() {
                return (this.uploadId.hashCode() * 31) + this.uploadedArtifact.hashCode();
            }

            public String toString() {
                return "Completed(uploadId=" + this.uploadId + ", uploadedArtifact=" + this.uploadedArtifact + ')';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeString(this.uploadId);
                parcel.writeParcelable(this.uploadedArtifact, flags);
            }
        }

        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\b\u001a\u00060\u0003j\u0002`\u0004HÆ\u0003J\u0017\u0010\t\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004HÆ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000bHÖ\u0001R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult$Error;", "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult;", SentryEvent.JsonKeys.EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/Exception;)V", "getException", "()Ljava/lang/Exception;", "component1", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Error extends SelfieCaptureResult {
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

        private SelfieCaptureResult() {
        }

        public /* synthetic */ SelfieCaptureResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private SelfieCaptureScreen() {
    }

    private static /* synthetic */ void getEXTRA_KEY_RESULT$annotations() {
    }

    public static /* synthetic */ void getKEY_REQUEST$annotations() {
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public Fragment createFragment() {
        return SelfieCaptureFragment.INSTANCE.newInstance(KEY_REQUEST);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final SelfieCaptureResult retrieveResult(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        SelfieCaptureResult selfieCaptureResult = (SelfieCaptureResult) bundle.getParcelable("EXTRA_KEY_RESULT_SELFIE_CAPTURE");
        if (selfieCaptureResult != null) {
            return selfieCaptureResult;
        }
        throw new IllegalStateException("selfie capture result is missing".toString());
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return Screen.DefaultImpls.screenKey(this);
    }

    public final Bundle storeResult(SelfieCaptureResult selfieCaptureResult) {
        Intrinsics.checkNotNullParameter(selfieCaptureResult, "selfieCaptureResult");
        return BundleKt.bundleOf(TuplesKt.to("EXTRA_KEY_RESULT_SELFIE_CAPTURE", selfieCaptureResult));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(1);
    }
}
