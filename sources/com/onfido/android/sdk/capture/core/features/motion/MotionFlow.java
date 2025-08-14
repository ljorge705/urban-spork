package com.onfido.android.sdk.capture.core.features.motion;

import android.os.Parcel;
import android.os.Parcelable;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment;
import com.onfido.android.sdk.capture.core.config.FailureReason;
import com.onfido.android.sdk.capture.core.config.Flow;
import com.onfido.android.sdk.capture.core.config.FlowFragment;
import io.sentry.protocol.OperatingSystem;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/core/features/motion/MotionFlow;", "Lcom/onfido/android/sdk/capture/core/config/Flow;", "()V", "createFragment", "Lcom/onfido/android/sdk/capture/core/config/FlowFragment;", "previousFlowResult", "Lcom/onfido/android/sdk/capture/core/config/Flow$Result;", "Builder", "Result", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionFlow extends Flow {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/core/features/motion/MotionFlow$Builder;", "", "()V", OperatingSystem.JsonKeys.BUILD, "Lcom/onfido/android/sdk/capture/core/features/motion/MotionFlow;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Builder {
        public final MotionFlow build() {
            return new MotionFlow(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/onfido/android/sdk/capture/core/features/motion/MotionFlow$Result;", "", "Failed", "Success", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Result {

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bHÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/core/features/motion/MotionFlow$Result$Failed;", "Lcom/onfido/android/sdk/capture/core/config/Flow$Result$Failed;", "reason", "Lcom/onfido/android/sdk/capture/core/config/FailureReason;", "(Lcom/onfido/android/sdk/capture/core/config/FailureReason;)V", "getReason", "()Lcom/onfido/android/sdk/capture/core/config/FailureReason;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
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

        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/core/features/motion/MotionFlow$Result$Success;", "Lcom/onfido/android/sdk/capture/core/config/Flow$Result$Success;", "mediaId", "", "(Ljava/lang/String;)V", "getMediaId", "()Ljava/lang/String;", "mediaIds", "", "getMediaIds", "()Ljava/util/List;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Success implements Flow.Result.Success {
            public static final Parcelable.Creator<Success> CREATOR = new Creator();
            private final String mediaId;
            private final List<String> mediaIds;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Success> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Success createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new Success(parcel.readString());
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Success[] newArray(int i) {
                    return new Success[i];
                }
            }

            public Success(String mediaId) {
                Intrinsics.checkNotNullParameter(mediaId, "mediaId");
                this.mediaId = mediaId;
                this.mediaIds = CollectionsKt.listOf(mediaId);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public final String getMediaId() {
                return this.mediaId;
            }

            @Override // com.onfido.android.sdk.capture.core.config.Flow.Result.Success
            public List<String> getMediaIds() {
                return this.mediaIds;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeString(this.mediaId);
            }
        }
    }

    private MotionFlow() {
    }

    @Override // com.onfido.android.sdk.capture.core.config.Flow
    public FlowFragment createFragment(Flow.Result previousFlowResult) {
        return new MotionHostFragment();
    }

    public /* synthetic */ MotionFlow(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
