package com.onfido.android.sdk.capture.common.permissions;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/common/permissions/PermissionResult;", "Landroid/os/Parcelable;", "()V", "Canceled", "Granted", "Lcom/onfido/android/sdk/capture/common/permissions/PermissionResult$Canceled;", "Lcom/onfido/android/sdk/capture/common/permissions/PermissionResult$Granted;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class PermissionResult implements Parcelable {

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/common/permissions/PermissionResult$Canceled;", "Lcom/onfido/android/sdk/capture/common/permissions/PermissionResult;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Canceled extends PermissionResult {
        public static final Canceled INSTANCE = new Canceled();
        public static final Parcelable.Creator<Canceled> CREATOR = new Creator();

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Canceled> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Canceled createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                parcel.readInt();
                return Canceled.INSTANCE;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Canceled[] newArray(int i) {
                return new Canceled[i];
            }
        }

        private Canceled() {
            super(null);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeInt(1);
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\nHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/common/permissions/PermissionResult$Granted;", "Lcom/onfido/android/sdk/capture/common/permissions/PermissionResult;", "captureStepDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "(Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;)V", "getCaptureStepDataBundle", "()Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "component1", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Granted extends PermissionResult {
        public static final Parcelable.Creator<Granted> CREATOR = new Creator();
        private final CaptureStepDataBundle captureStepDataBundle;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Granted> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Granted createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Granted((CaptureStepDataBundle) parcel.readSerializable());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Granted[] newArray(int i) {
                return new Granted[i];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Granted(CaptureStepDataBundle captureStepDataBundle) {
            super(null);
            Intrinsics.checkNotNullParameter(captureStepDataBundle, "captureStepDataBundle");
            this.captureStepDataBundle = captureStepDataBundle;
        }

        public static /* synthetic */ Granted copy$default(Granted granted, CaptureStepDataBundle captureStepDataBundle, int i, Object obj) {
            if ((i & 1) != 0) {
                captureStepDataBundle = granted.captureStepDataBundle;
            }
            return granted.copy(captureStepDataBundle);
        }

        /* renamed from: component1, reason: from getter */
        public final CaptureStepDataBundle getCaptureStepDataBundle() {
            return this.captureStepDataBundle;
        }

        public final Granted copy(CaptureStepDataBundle captureStepDataBundle) {
            Intrinsics.checkNotNullParameter(captureStepDataBundle, "captureStepDataBundle");
            return new Granted(captureStepDataBundle);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Granted) && Intrinsics.areEqual(this.captureStepDataBundle, ((Granted) other).captureStepDataBundle);
        }

        public final CaptureStepDataBundle getCaptureStepDataBundle() {
            return this.captureStepDataBundle;
        }

        public int hashCode() {
            return this.captureStepDataBundle.hashCode();
        }

        public String toString() {
            return "Granted(captureStepDataBundle=" + this.captureStepDataBundle + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeSerializable(this.captureStepDataBundle);
        }
    }

    private PermissionResult() {
    }

    public /* synthetic */ PermissionResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
