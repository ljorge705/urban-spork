package com.onfido.android.sdk.capture.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.core.config.Flow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0006HÆ\u0003J#\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\u0013\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/core/OnfidoResult;", "Landroid/os/Parcelable;", OnfidoLauncher.KEY_RESULT, "", "Lcom/onfido/android/sdk/capture/core/config/Flow$Result;", "isSuccess", "", "(Ljava/util/List;Z)V", "()Z", "getResult", "()Ljava/util/List;", "component1", "component2", Constants.COPY_TYPE, "describeContents", "", "equals", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class OnfidoResult implements Parcelable {
    public static final Parcelable.Creator<OnfidoResult> CREATOR = new Creator();
    private final boolean isSuccess;
    private final List<Flow.Result> result;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<OnfidoResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final OnfidoResult createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            int i = parcel.readInt();
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 != i; i2++) {
                arrayList.add(parcel.readParcelable(OnfidoResult.class.getClassLoader()));
            }
            return new OnfidoResult(arrayList, parcel.readInt() != 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final OnfidoResult[] newArray(int i) {
            return new OnfidoResult[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OnfidoResult(List<? extends Flow.Result> result, boolean z) {
        Intrinsics.checkNotNullParameter(result, "result");
        this.result = result;
        this.isSuccess = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ OnfidoResult copy$default(OnfidoResult onfidoResult, List list, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            list = onfidoResult.result;
        }
        if ((i & 2) != 0) {
            z = onfidoResult.isSuccess;
        }
        return onfidoResult.copy(list, z);
    }

    public final List<Flow.Result> component1() {
        return this.result;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsSuccess() {
        return this.isSuccess;
    }

    public final OnfidoResult copy(List<? extends Flow.Result> result, boolean isSuccess) {
        Intrinsics.checkNotNullParameter(result, "result");
        return new OnfidoResult(result, isSuccess);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OnfidoResult)) {
            return false;
        }
        OnfidoResult onfidoResult = (OnfidoResult) other;
        return Intrinsics.areEqual(this.result, onfidoResult.result) && this.isSuccess == onfidoResult.isSuccess;
    }

    public final List<Flow.Result> getResult() {
        return this.result;
    }

    public int hashCode() {
        return (this.result.hashCode() * 31) + Boolean.hashCode(this.isSuccess);
    }

    public final boolean isSuccess() {
        return this.isSuccess;
    }

    public String toString() {
        return "OnfidoResult(result=" + this.result + ", isSuccess=" + this.isSuccess + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        List<Flow.Result> list = this.result;
        parcel.writeInt(list.size());
        Iterator<Flow.Result> it = list.iterator();
        while (it.hasNext()) {
            parcel.writeParcelable(it.next(), flags);
        }
        parcel.writeInt(this.isSuccess ? 1 : 0);
    }
}
