package com.onfido.hosted.web.module.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\nHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lcom/onfido/hosted/web/module/model/HostedWebModuleSuccess;", "Lcom/onfido/hosted/web/module/model/HostedWebModuleResult;", "body", "", "(Ljava/lang/String;)V", "getBody", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class HostedWebModuleSuccess implements HostedWebModuleResult {
    public static final Parcelable.Creator<HostedWebModuleSuccess> CREATOR = new Creator();
    private final String body;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<HostedWebModuleSuccess> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final HostedWebModuleSuccess createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new HostedWebModuleSuccess(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final HostedWebModuleSuccess[] newArray(int i) {
            return new HostedWebModuleSuccess[i];
        }
    }

    public HostedWebModuleSuccess(String body) {
        Intrinsics.checkNotNullParameter(body, "body");
        this.body = body;
    }

    public static /* synthetic */ HostedWebModuleSuccess copy$default(HostedWebModuleSuccess hostedWebModuleSuccess, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = hostedWebModuleSuccess.body;
        }
        return hostedWebModuleSuccess.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getBody() {
        return this.body;
    }

    public final HostedWebModuleSuccess copy(String body) {
        Intrinsics.checkNotNullParameter(body, "body");
        return new HostedWebModuleSuccess(body);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof HostedWebModuleSuccess) && Intrinsics.areEqual(this.body, ((HostedWebModuleSuccess) other).body);
    }

    public final String getBody() {
        return this.body;
    }

    public int hashCode() {
        return this.body.hashCode();
    }

    public String toString() {
        return "HostedWebModuleSuccess(body=" + this.body + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.body);
    }
}
