package com.onfido.hosted.web.module.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\bHÆ\u0003J5\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u0017HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0017HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006#"}, d2 = {"Lcom/onfido/hosted/web/module/model/HostedWebModuleModuleInfo;", "Landroid/os/Parcelable;", "config", "", "input", "studioInfo", "Lcom/onfido/hosted/web/module/model/StudioModuleInfo;", "classicInfo", "Lcom/onfido/hosted/web/module/model/ClassicModuleInfo;", "(Ljava/lang/String;Ljava/lang/String;Lcom/onfido/hosted/web/module/model/StudioModuleInfo;Lcom/onfido/hosted/web/module/model/ClassicModuleInfo;)V", "getClassicInfo", "()Lcom/onfido/hosted/web/module/model/ClassicModuleInfo;", "getConfig", "()Ljava/lang/String;", "getInput", "getStudioInfo", "()Lcom/onfido/hosted/web/module/model/StudioModuleInfo;", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class HostedWebModuleModuleInfo implements Parcelable {
    public static final Parcelable.Creator<HostedWebModuleModuleInfo> CREATOR = new Creator();
    private final ClassicModuleInfo classicInfo;
    private final String config;
    private final String input;
    private final StudioModuleInfo studioInfo;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<HostedWebModuleModuleInfo> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final HostedWebModuleModuleInfo createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new HostedWebModuleModuleInfo(parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : StudioModuleInfo.CREATOR.createFromParcel(parcel), parcel.readInt() != 0 ? ClassicModuleInfo.CREATOR.createFromParcel(parcel) : null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final HostedWebModuleModuleInfo[] newArray(int i) {
            return new HostedWebModuleModuleInfo[i];
        }
    }

    public HostedWebModuleModuleInfo(String config, String input, StudioModuleInfo studioModuleInfo, ClassicModuleInfo classicModuleInfo) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(input, "input");
        this.config = config;
        this.input = input;
        this.studioInfo = studioModuleInfo;
        this.classicInfo = classicModuleInfo;
    }

    public static /* synthetic */ HostedWebModuleModuleInfo copy$default(HostedWebModuleModuleInfo hostedWebModuleModuleInfo, String str, String str2, StudioModuleInfo studioModuleInfo, ClassicModuleInfo classicModuleInfo, int i, Object obj) {
        if ((i & 1) != 0) {
            str = hostedWebModuleModuleInfo.config;
        }
        if ((i & 2) != 0) {
            str2 = hostedWebModuleModuleInfo.input;
        }
        if ((i & 4) != 0) {
            studioModuleInfo = hostedWebModuleModuleInfo.studioInfo;
        }
        if ((i & 8) != 0) {
            classicModuleInfo = hostedWebModuleModuleInfo.classicInfo;
        }
        return hostedWebModuleModuleInfo.copy(str, str2, studioModuleInfo, classicModuleInfo);
    }

    /* renamed from: component1, reason: from getter */
    public final String getConfig() {
        return this.config;
    }

    /* renamed from: component2, reason: from getter */
    public final String getInput() {
        return this.input;
    }

    /* renamed from: component3, reason: from getter */
    public final StudioModuleInfo getStudioInfo() {
        return this.studioInfo;
    }

    /* renamed from: component4, reason: from getter */
    public final ClassicModuleInfo getClassicInfo() {
        return this.classicInfo;
    }

    public final HostedWebModuleModuleInfo copy(String config, String input, StudioModuleInfo studioInfo, ClassicModuleInfo classicInfo) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(input, "input");
        return new HostedWebModuleModuleInfo(config, input, studioInfo, classicInfo);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HostedWebModuleModuleInfo)) {
            return false;
        }
        HostedWebModuleModuleInfo hostedWebModuleModuleInfo = (HostedWebModuleModuleInfo) other;
        return Intrinsics.areEqual(this.config, hostedWebModuleModuleInfo.config) && Intrinsics.areEqual(this.input, hostedWebModuleModuleInfo.input) && Intrinsics.areEqual(this.studioInfo, hostedWebModuleModuleInfo.studioInfo) && Intrinsics.areEqual(this.classicInfo, hostedWebModuleModuleInfo.classicInfo);
    }

    public final ClassicModuleInfo getClassicInfo() {
        return this.classicInfo;
    }

    public final String getConfig() {
        return this.config;
    }

    public final String getInput() {
        return this.input;
    }

    public final StudioModuleInfo getStudioInfo() {
        return this.studioInfo;
    }

    public int hashCode() {
        int iHashCode = ((this.config.hashCode() * 31) + this.input.hashCode()) * 31;
        StudioModuleInfo studioModuleInfo = this.studioInfo;
        int iHashCode2 = (iHashCode + (studioModuleInfo == null ? 0 : studioModuleInfo.hashCode())) * 31;
        ClassicModuleInfo classicModuleInfo = this.classicInfo;
        return iHashCode2 + (classicModuleInfo != null ? classicModuleInfo.hashCode() : 0);
    }

    public String toString() {
        return "HostedWebModuleModuleInfo(config=" + this.config + ", input=" + this.input + ", studioInfo=" + this.studioInfo + ", classicInfo=" + this.classicInfo + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.config);
        parcel.writeString(this.input);
        StudioModuleInfo studioModuleInfo = this.studioInfo;
        if (studioModuleInfo == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            studioModuleInfo.writeToParcel(parcel, flags);
        }
        ClassicModuleInfo classicModuleInfo = this.classicInfo;
        if (classicModuleInfo == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            classicModuleInfo.writeToParcel(parcel, flags);
        }
    }

    public /* synthetic */ HostedWebModuleModuleInfo(String str, String str2, StudioModuleInfo studioModuleInfo, ClassicModuleInfo classicModuleInfo, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : studioModuleInfo, (i & 8) != 0 ? null : classicModuleInfo);
    }
}
