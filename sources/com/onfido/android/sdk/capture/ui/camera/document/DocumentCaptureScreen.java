package com.onfido.android.sdk.capture.ui.camera.document;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.flow.NfcArguments;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import io.sentry.SentryEvent;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002!\"B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0007HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\u0013\u0010\u0016\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0015HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\u0019\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006#"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen;", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "captureDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "isFrontSide", "", "nfcArguments", "Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "(Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;ZLcom/onfido/android/sdk/capture/flow/NfcArguments;)V", "getCaptureDataBundle", "()Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "()Z", "getNfcArguments", "()Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "component1", "component2", "component3", Constants.COPY_TYPE, "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "", "equals", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "DocumentCaptureResult", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DocumentCaptureScreen implements Screen {
    public static final String EXTRA_KEY_RESULT = "EXTRA_KEY_RESULT_SELFIE_CAPTURE";
    public static final String KEY_NFC_PROPERTIES = "NFC_PROPERTIES";
    public static final String KEY_REQUEST = "KEY_REQUEST_DOCUMENT_CAPTURE";
    private final CaptureStepDataBundle captureDataBundle;
    private final boolean isFrontSide;
    private final NfcArguments nfcArguments;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final Parcelable.Creator<DocumentCaptureScreen> CREATOR = new Creator();

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\n\u001a\u00020\u000b*\u00020\fH\u0000¢\u0006\u0002\b\rJ\f\u0010\u000e\u001a\u00020\f*\u00020\u000bH\u0007R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0014\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0016\u0010\b\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$Companion;", "", "()V", "EXTRA_KEY_RESULT", "", "getEXTRA_KEY_RESULT$onfido_capture_sdk_core_release$annotations", "KEY_NFC_PROPERTIES", "getKEY_NFC_PROPERTIES$onfido_capture_sdk_core_release$annotations", "KEY_REQUEST", "getKEY_REQUEST$annotations", "toBundle", "Landroid/os/Bundle;", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult;", "toBundle$onfido_capture_sdk_core_release", "toDocumentCaptureResult", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public static /* synthetic */ void getEXTRA_KEY_RESULT$onfido_capture_sdk_core_release$annotations() {
        }

        public static /* synthetic */ void getKEY_NFC_PROPERTIES$onfido_capture_sdk_core_release$annotations() {
        }

        public static /* synthetic */ void getKEY_REQUEST$annotations() {
        }

        public final Bundle toBundle$onfido_capture_sdk_core_release(DocumentCaptureResult documentCaptureResult) {
            Intrinsics.checkNotNullParameter(documentCaptureResult, "<this>");
            Bundle bundleBundleOf = BundleKt.bundleOf(TuplesKt.to(DocumentCaptureScreen.EXTRA_KEY_RESULT, documentCaptureResult));
            if (documentCaptureResult instanceof DocumentCaptureResult.Completed) {
                DocumentCaptureResult.Completed completed = (DocumentCaptureResult.Completed) documentCaptureResult;
                if (completed.getNfcProperties() != null) {
                    bundleBundleOf.putParcelable(DocumentCaptureScreen.KEY_NFC_PROPERTIES, completed.getNfcProperties());
                }
            }
            return bundleBundleOf;
        }

        public final DocumentCaptureResult toDocumentCaptureResult(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "<this>");
            DocumentCaptureResult documentCaptureResult = (DocumentCaptureResult) bundle.getParcelable(DocumentCaptureScreen.EXTRA_KEY_RESULT);
            if (documentCaptureResult != null) {
                return documentCaptureResult;
            }
            throw new IllegalStateException("document capture result missing".toString());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<DocumentCaptureScreen> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DocumentCaptureScreen createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new DocumentCaptureScreen((CaptureStepDataBundle) parcel.readSerializable(), parcel.readInt() != 0, NfcArguments.CREATOR.createFromParcel(parcel));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DocumentCaptureScreen[] newArray(int i) {
            return new DocumentCaptureScreen[i];
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult;", "Landroid/os/Parcelable;", "()V", "Completed", "Error", "POACompleted", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult$Completed;", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult$Error;", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult$POACompleted;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class DocumentCaptureResult implements Parcelable {

        @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b¢\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003JG\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\bHÆ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\u0013\u0010\u001f\u001a\u00020\b2\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u001eHÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001J\u0019\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u001eHÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000fR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000f¨\u0006)"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult$Completed;", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult;", "uploadId", "", "videoUploadId", "documentData", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "isNfcSupported", "", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "withWarning", "(Ljava/lang/String;Ljava/lang/String;Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;ZLcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;Z)V", "getDocumentData", "()Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "()Z", "getNfcProperties", "()Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "getUploadId", "()Ljava/lang/String;", "getVideoUploadId", "getWithWarning", "component1", "component2", "component3", "component4", "component5", "component6", Constants.COPY_TYPE, "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Completed extends DocumentCaptureResult {
            public static final Parcelable.Creator<Completed> CREATOR = new Creator();
            private final CaptureStepDataBundle documentData;
            private final boolean isNfcSupported;
            private final NfcProperties nfcProperties;
            private final String uploadId;
            private final String videoUploadId;
            private final boolean withWarning;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Completed> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Completed createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new Completed(parcel.readString(), parcel.readString(), (CaptureStepDataBundle) parcel.readSerializable(), parcel.readInt() != 0, parcel.readInt() == 0 ? null : NfcProperties.CREATOR.createFromParcel(parcel), parcel.readInt() != 0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Completed[] newArray(int i) {
                    return new Completed[i];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Completed(String uploadId, String videoUploadId, CaptureStepDataBundle documentData, boolean z, NfcProperties nfcProperties, boolean z2) {
                super(null);
                Intrinsics.checkNotNullParameter(uploadId, "uploadId");
                Intrinsics.checkNotNullParameter(videoUploadId, "videoUploadId");
                Intrinsics.checkNotNullParameter(documentData, "documentData");
                this.uploadId = uploadId;
                this.videoUploadId = videoUploadId;
                this.documentData = documentData;
                this.isNfcSupported = z;
                this.nfcProperties = nfcProperties;
                this.withWarning = z2;
            }

            public static /* synthetic */ Completed copy$default(Completed completed, String str, String str2, CaptureStepDataBundle captureStepDataBundle, boolean z, NfcProperties nfcProperties, boolean z2, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = completed.uploadId;
                }
                if ((i & 2) != 0) {
                    str2 = completed.videoUploadId;
                }
                String str3 = str2;
                if ((i & 4) != 0) {
                    captureStepDataBundle = completed.documentData;
                }
                CaptureStepDataBundle captureStepDataBundle2 = captureStepDataBundle;
                if ((i & 8) != 0) {
                    z = completed.isNfcSupported;
                }
                boolean z3 = z;
                if ((i & 16) != 0) {
                    nfcProperties = completed.nfcProperties;
                }
                NfcProperties nfcProperties2 = nfcProperties;
                if ((i & 32) != 0) {
                    z2 = completed.withWarning;
                }
                return completed.copy(str, str3, captureStepDataBundle2, z3, nfcProperties2, z2);
            }

            /* renamed from: component1, reason: from getter */
            public final String getUploadId() {
                return this.uploadId;
            }

            /* renamed from: component2, reason: from getter */
            public final String getVideoUploadId() {
                return this.videoUploadId;
            }

            /* renamed from: component3, reason: from getter */
            public final CaptureStepDataBundle getDocumentData() {
                return this.documentData;
            }

            /* renamed from: component4, reason: from getter */
            public final boolean getIsNfcSupported() {
                return this.isNfcSupported;
            }

            /* renamed from: component5, reason: from getter */
            public final NfcProperties getNfcProperties() {
                return this.nfcProperties;
            }

            /* renamed from: component6, reason: from getter */
            public final boolean getWithWarning() {
                return this.withWarning;
            }

            public final Completed copy(String uploadId, String videoUploadId, CaptureStepDataBundle documentData, boolean isNfcSupported, NfcProperties nfcProperties, boolean withWarning) {
                Intrinsics.checkNotNullParameter(uploadId, "uploadId");
                Intrinsics.checkNotNullParameter(videoUploadId, "videoUploadId");
                Intrinsics.checkNotNullParameter(documentData, "documentData");
                return new Completed(uploadId, videoUploadId, documentData, isNfcSupported, nfcProperties, withWarning);
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
                return Intrinsics.areEqual(this.uploadId, completed.uploadId) && Intrinsics.areEqual(this.videoUploadId, completed.videoUploadId) && Intrinsics.areEqual(this.documentData, completed.documentData) && this.isNfcSupported == completed.isNfcSupported && Intrinsics.areEqual(this.nfcProperties, completed.nfcProperties) && this.withWarning == completed.withWarning;
            }

            public final CaptureStepDataBundle getDocumentData() {
                return this.documentData;
            }

            public final NfcProperties getNfcProperties() {
                return this.nfcProperties;
            }

            public final String getUploadId() {
                return this.uploadId;
            }

            public final String getVideoUploadId() {
                return this.videoUploadId;
            }

            public final boolean getWithWarning() {
                return this.withWarning;
            }

            public int hashCode() {
                int iHashCode = ((((((this.uploadId.hashCode() * 31) + this.videoUploadId.hashCode()) * 31) + this.documentData.hashCode()) * 31) + Boolean.hashCode(this.isNfcSupported)) * 31;
                NfcProperties nfcProperties = this.nfcProperties;
                return ((iHashCode + (nfcProperties == null ? 0 : nfcProperties.hashCode())) * 31) + Boolean.hashCode(this.withWarning);
            }

            public final boolean isNfcSupported() {
                return this.isNfcSupported;
            }

            public String toString() {
                return "Completed(uploadId=" + this.uploadId + ", videoUploadId=" + this.videoUploadId + ", documentData=" + this.documentData + ", isNfcSupported=" + this.isNfcSupported + ", nfcProperties=" + this.nfcProperties + ", withWarning=" + this.withWarning + ')';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeString(this.uploadId);
                parcel.writeString(this.videoUploadId);
                parcel.writeSerializable(this.documentData);
                parcel.writeInt(this.isNfcSupported ? 1 : 0);
                NfcProperties nfcProperties = this.nfcProperties;
                if (nfcProperties == null) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(1);
                    nfcProperties.writeToParcel(parcel, flags);
                }
                parcel.writeInt(this.withWarning ? 1 : 0);
            }

            public /* synthetic */ Completed(String str, String str2, CaptureStepDataBundle captureStepDataBundle, boolean z, NfcProperties nfcProperties, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, str2, captureStepDataBundle, z, (i & 16) != 0 ? null : nfcProperties, (i & 32) != 0 ? false : z2);
            }
        }

        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\b\u001a\u00060\u0003j\u0002`\u0004HÆ\u0003J\u0017\u0010\t\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004HÆ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000bHÖ\u0001R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult$Error;", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult;", SentryEvent.JsonKeys.EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/Exception;)V", "getException", "()Ljava/lang/Exception;", "component1", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Error extends DocumentCaptureResult {
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

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\nHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult$POACompleted;", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult;", "fileAddress", "", "(Ljava/lang/String;)V", "getFileAddress", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class POACompleted extends DocumentCaptureResult {
            public static final Parcelable.Creator<POACompleted> CREATOR = new Creator();
            private final String fileAddress;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<POACompleted> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final POACompleted createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new POACompleted(parcel.readString());
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final POACompleted[] newArray(int i) {
                    return new POACompleted[i];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public POACompleted(String fileAddress) {
                super(null);
                Intrinsics.checkNotNullParameter(fileAddress, "fileAddress");
                this.fileAddress = fileAddress;
            }

            public static /* synthetic */ POACompleted copy$default(POACompleted pOACompleted, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = pOACompleted.fileAddress;
                }
                return pOACompleted.copy(str);
            }

            /* renamed from: component1, reason: from getter */
            public final String getFileAddress() {
                return this.fileAddress;
            }

            public final POACompleted copy(String fileAddress) {
                Intrinsics.checkNotNullParameter(fileAddress, "fileAddress");
                return new POACompleted(fileAddress);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof POACompleted) && Intrinsics.areEqual(this.fileAddress, ((POACompleted) other).fileAddress);
            }

            public final String getFileAddress() {
                return this.fileAddress;
            }

            public int hashCode() {
                return this.fileAddress.hashCode();
            }

            public String toString() {
                return "POACompleted(fileAddress=" + this.fileAddress + ')';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeString(this.fileAddress);
            }
        }

        private DocumentCaptureResult() {
        }

        public /* synthetic */ DocumentCaptureResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public DocumentCaptureScreen(CaptureStepDataBundle captureDataBundle, boolean z, NfcArguments nfcArguments) {
        Intrinsics.checkNotNullParameter(captureDataBundle, "captureDataBundle");
        Intrinsics.checkNotNullParameter(nfcArguments, "nfcArguments");
        this.captureDataBundle = captureDataBundle;
        this.isFrontSide = z;
        this.nfcArguments = nfcArguments;
    }

    public static /* synthetic */ DocumentCaptureScreen copy$default(DocumentCaptureScreen documentCaptureScreen, CaptureStepDataBundle captureStepDataBundle, boolean z, NfcArguments nfcArguments, int i, Object obj) {
        if ((i & 1) != 0) {
            captureStepDataBundle = documentCaptureScreen.captureDataBundle;
        }
        if ((i & 2) != 0) {
            z = documentCaptureScreen.isFrontSide;
        }
        if ((i & 4) != 0) {
            nfcArguments = documentCaptureScreen.nfcArguments;
        }
        return documentCaptureScreen.copy(captureStepDataBundle, z, nfcArguments);
    }

    /* renamed from: component1, reason: from getter */
    public final CaptureStepDataBundle getCaptureDataBundle() {
        return this.captureDataBundle;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsFrontSide() {
        return this.isFrontSide;
    }

    /* renamed from: component3, reason: from getter */
    public final NfcArguments getNfcArguments() {
        return this.nfcArguments;
    }

    public final DocumentCaptureScreen copy(CaptureStepDataBundle captureDataBundle, boolean isFrontSide, NfcArguments nfcArguments) {
        Intrinsics.checkNotNullParameter(captureDataBundle, "captureDataBundle");
        Intrinsics.checkNotNullParameter(nfcArguments, "nfcArguments");
        return new DocumentCaptureScreen(captureDataBundle, isFrontSide, nfcArguments);
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public Fragment createFragment() {
        return DocumentCaptureFragment.INSTANCE.newInstance(KEY_REQUEST, this.captureDataBundle, this.isFrontSide, false, this.nfcArguments);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentCaptureScreen)) {
            return false;
        }
        DocumentCaptureScreen documentCaptureScreen = (DocumentCaptureScreen) other;
        return Intrinsics.areEqual(this.captureDataBundle, documentCaptureScreen.captureDataBundle) && this.isFrontSide == documentCaptureScreen.isFrontSide && Intrinsics.areEqual(this.nfcArguments, documentCaptureScreen.nfcArguments);
    }

    public final CaptureStepDataBundle getCaptureDataBundle() {
        return this.captureDataBundle;
    }

    public final NfcArguments getNfcArguments() {
        return this.nfcArguments;
    }

    public int hashCode() {
        return (((this.captureDataBundle.hashCode() * 31) + Boolean.hashCode(this.isFrontSide)) * 31) + this.nfcArguments.hashCode();
    }

    public final boolean isFrontSide() {
        return this.isFrontSide;
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.Screen
    public String screenKey() {
        return Screen.DefaultImpls.screenKey(this);
    }

    public String toString() {
        return "DocumentCaptureScreen(captureDataBundle=" + this.captureDataBundle + ", isFrontSide=" + this.isFrontSide + ", nfcArguments=" + this.nfcArguments + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeSerializable(this.captureDataBundle);
        parcel.writeInt(this.isFrontSide ? 1 : 0);
        this.nfcArguments.writeToParcel(parcel, flags);
    }
}
