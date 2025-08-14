package com.onfido.hosted.web.module.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\rHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0019"}, d2 = {"Lcom/onfido/hosted/web/module/model/ClassicModuleInfo;", "Landroid/os/Parcelable;", AnalyticsPropertyKeys.STEP, "", "type", "(Ljava/lang/String;Ljava/lang/String;)V", "getStep", "()Ljava/lang/String;", "getType", "component1", "component2", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class ClassicModuleInfo implements Parcelable {
    public static final Parcelable.Creator<ClassicModuleInfo> CREATOR = new Creator();
    private final String step;
    private final String type;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<ClassicModuleInfo> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ClassicModuleInfo createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ClassicModuleInfo(parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ClassicModuleInfo[] newArray(int i) {
            return new ClassicModuleInfo[i];
        }
    }

    public ClassicModuleInfo(String step, String type) {
        Intrinsics.checkNotNullParameter(step, "step");
        Intrinsics.checkNotNullParameter(type, "type");
        this.step = step;
        this.type = type;
    }

    public static /* synthetic */ ClassicModuleInfo copy$default(ClassicModuleInfo classicModuleInfo, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = classicModuleInfo.step;
        }
        if ((i & 2) != 0) {
            str2 = classicModuleInfo.type;
        }
        return classicModuleInfo.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getStep() {
        return this.step;
    }

    /* renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    public final ClassicModuleInfo copy(String step, String type) {
        Intrinsics.checkNotNullParameter(step, "step");
        Intrinsics.checkNotNullParameter(type, "type");
        return new ClassicModuleInfo(step, type);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClassicModuleInfo)) {
            return false;
        }
        ClassicModuleInfo classicModuleInfo = (ClassicModuleInfo) other;
        return Intrinsics.areEqual(this.step, classicModuleInfo.step) && Intrinsics.areEqual(this.type, classicModuleInfo.type);
    }

    public final String getStep() {
        return this.step;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        return (this.step.hashCode() * 31) + this.type.hashCode();
    }

    public String toString() {
        return "ClassicModuleInfo(step=" + this.step + ", type=" + this.type + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.step);
        parcel.writeString(this.type);
    }

    public /* synthetic */ ClassicModuleInfo(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? "hosted" : str2);
    }
}
