package com.onfido.android.sdk.capture.core.features.nfc;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.os.BundleKt;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.core.config.FailureReason;
import com.onfido.android.sdk.capture.core.config.Flow;
import com.onfido.android.sdk.capture.core.config.FlowFragment;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/core/features/nfc/NfcFlow;", "Lcom/onfido/android/sdk/capture/core/config/Flow;", "()V", "createFragment", "Lcom/onfido/android/sdk/capture/core/config/FlowFragment;", "previousFlowResult", "Lcom/onfido/android/sdk/capture/core/config/Flow$Result;", "Result", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcFlow extends Flow {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/onfido/android/sdk/capture/core/features/nfc/NfcFlow$Result;", "", "Failed", "Success", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Result {

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bHÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/core/features/nfc/NfcFlow$Result$Failed;", "Lcom/onfido/android/sdk/capture/core/config/Flow$Result$Failed;", "reason", "Lcom/onfido/android/sdk/capture/core/config/FailureReason;", "(Lcom/onfido/android/sdk/capture/core/config/FailureReason;)V", "getReason", "()Lcom/onfido/android/sdk/capture/core/config/FailureReason;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Failed implements Flow.Result.Failed {
            public static final Parcelable.Creator<Failed> CREATOR = new Creator();
            private final FailureReason reason;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Failed> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Failed createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new Failed((FailureReason) parcel.readParcelable(Failed.class.getClassLoader()));
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Failed[] newArray(int i) {
                    return new Failed[i];
                }
            }

            public Failed(FailureReason reason) {
                Intrinsics.checkNotNullParameter(reason, "reason");
                this.reason = reason;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // com.onfido.android.sdk.capture.core.config.Flow.Result.Failed
            public FailureReason getReason() {
                return this.reason;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeParcelable(this.reason, flags);
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\b\u001a\u00020\tHÖ\u0001J\u0019\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tHÖ\u0001R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/core/features/nfc/NfcFlow$Result$Success;", "Lcom/onfido/android/sdk/capture/core/config/Flow$Result$Success;", "()V", "mediaIds", "", "", "getMediaIds", "()Ljava/util/List;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Success implements Flow.Result.Success {
            public static final Success INSTANCE = new Success();
            private static final List<String> mediaIds = CollectionsKt.emptyList();
            public static final Parcelable.Creator<Success> CREATOR = new Creator();

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Success> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Success createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return Success.INSTANCE;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Success[] newArray(int i) {
                    return new Success[i];
                }
            }

            private Success() {
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // com.onfido.android.sdk.capture.core.config.Flow.Result.Success
            public List<String> getMediaIds() {
                return mediaIds;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeInt(1);
            }
        }
    }

    @Override // com.onfido.android.sdk.capture.core.config.Flow
    public FlowFragment createFragment(Flow.Result previousFlowResult) {
        NfcHostFragment nfcHostFragment = new NfcHostFragment();
        nfcHostFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(NfcHostFragment.KEY_ARG_DOC_TYPE, DocumentType.PASSPORT), TuplesKt.to(NfcHostFragment.KEY_ARG_NFC_PROPERTIES, new NfcProperties(true, "", new byte[1], "", false, false, 0, 0, null, 496, null))));
        return nfcHostFragment;
    }
}
