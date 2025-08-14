package com.onfido.hosted.web.module.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/onfido/hosted/web/module/model/StudioModuleInfo;", "Landroid/os/Parcelable;", "workflowRunId", "", "taskId", "taskDefId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTaskDefId", "()Ljava/lang/String;", "getTaskId", "getWorkflowRunId", "component1", "component2", "component3", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class StudioModuleInfo implements Parcelable {
    public static final Parcelable.Creator<StudioModuleInfo> CREATOR = new Creator();
    private final String taskDefId;
    private final String taskId;
    private final String workflowRunId;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<StudioModuleInfo> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final StudioModuleInfo createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new StudioModuleInfo(parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final StudioModuleInfo[] newArray(int i) {
            return new StudioModuleInfo[i];
        }
    }

    public StudioModuleInfo(String workflowRunId, String taskId, String taskDefId) {
        Intrinsics.checkNotNullParameter(workflowRunId, "workflowRunId");
        Intrinsics.checkNotNullParameter(taskId, "taskId");
        Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
        this.workflowRunId = workflowRunId;
        this.taskId = taskId;
        this.taskDefId = taskDefId;
    }

    public static /* synthetic */ StudioModuleInfo copy$default(StudioModuleInfo studioModuleInfo, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = studioModuleInfo.workflowRunId;
        }
        if ((i & 2) != 0) {
            str2 = studioModuleInfo.taskId;
        }
        if ((i & 4) != 0) {
            str3 = studioModuleInfo.taskDefId;
        }
        return studioModuleInfo.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getWorkflowRunId() {
        return this.workflowRunId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTaskId() {
        return this.taskId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTaskDefId() {
        return this.taskDefId;
    }

    public final StudioModuleInfo copy(String workflowRunId, String taskId, String taskDefId) {
        Intrinsics.checkNotNullParameter(workflowRunId, "workflowRunId");
        Intrinsics.checkNotNullParameter(taskId, "taskId");
        Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
        return new StudioModuleInfo(workflowRunId, taskId, taskDefId);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StudioModuleInfo)) {
            return false;
        }
        StudioModuleInfo studioModuleInfo = (StudioModuleInfo) other;
        return Intrinsics.areEqual(this.workflowRunId, studioModuleInfo.workflowRunId) && Intrinsics.areEqual(this.taskId, studioModuleInfo.taskId) && Intrinsics.areEqual(this.taskDefId, studioModuleInfo.taskDefId);
    }

    public final String getTaskDefId() {
        return this.taskDefId;
    }

    public final String getTaskId() {
        return this.taskId;
    }

    public final String getWorkflowRunId() {
        return this.workflowRunId;
    }

    public int hashCode() {
        return (((this.workflowRunId.hashCode() * 31) + this.taskId.hashCode()) * 31) + this.taskDefId.hashCode();
    }

    public String toString() {
        return "StudioModuleInfo(workflowRunId=" + this.workflowRunId + ", taskId=" + this.taskId + ", taskDefId=" + this.taskDefId + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.workflowRunId);
        parcel.writeString(this.taskId);
        parcel.writeString(this.taskDefId);
    }
}
