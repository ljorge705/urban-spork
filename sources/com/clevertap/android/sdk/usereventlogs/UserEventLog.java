package com.clevertap.android.sdk.usereventlogs;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.db.Column;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserEventLog.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\tHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f¨\u0006!"}, d2 = {"Lcom/clevertap/android/sdk/usereventlogs/UserEventLog;", "", "eventName", "", Column.NORMALIZED_EVENT_NAME, Column.FIRST_TS, "", Column.LAST_TS, "countOfEvents", "", Column.DEVICE_ID, "(Ljava/lang/String;Ljava/lang/String;JJILjava/lang/String;)V", "getCountOfEvents", "()I", "getDeviceID", "()Ljava/lang/String;", "getEventName", "getFirstTs", "()J", "getLastTs", "getNormalizedEventName", "component1", "component2", "component3", "component4", "component5", "component6", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class UserEventLog {
    private final int countOfEvents;
    private final String deviceID;
    private final String eventName;
    private final long firstTs;
    private final long lastTs;
    private final String normalizedEventName;

    /* renamed from: component1, reason: from getter */
    public final String getEventName() {
        return this.eventName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getNormalizedEventName() {
        return this.normalizedEventName;
    }

    /* renamed from: component3, reason: from getter */
    public final long getFirstTs() {
        return this.firstTs;
    }

    /* renamed from: component4, reason: from getter */
    public final long getLastTs() {
        return this.lastTs;
    }

    /* renamed from: component5, reason: from getter */
    public final int getCountOfEvents() {
        return this.countOfEvents;
    }

    /* renamed from: component6, reason: from getter */
    public final String getDeviceID() {
        return this.deviceID;
    }

    public final UserEventLog copy(String eventName, String normalizedEventName, long firstTs, long lastTs, int countOfEvents, String deviceID) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(normalizedEventName, "normalizedEventName");
        Intrinsics.checkNotNullParameter(deviceID, "deviceID");
        return new UserEventLog(eventName, normalizedEventName, firstTs, lastTs, countOfEvents, deviceID);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserEventLog)) {
            return false;
        }
        UserEventLog userEventLog = (UserEventLog) other;
        return Intrinsics.areEqual(this.eventName, userEventLog.eventName) && Intrinsics.areEqual(this.normalizedEventName, userEventLog.normalizedEventName) && this.firstTs == userEventLog.firstTs && this.lastTs == userEventLog.lastTs && this.countOfEvents == userEventLog.countOfEvents && Intrinsics.areEqual(this.deviceID, userEventLog.deviceID);
    }

    public final int getCountOfEvents() {
        return this.countOfEvents;
    }

    public final String getDeviceID() {
        return this.deviceID;
    }

    public final String getEventName() {
        return this.eventName;
    }

    public final long getFirstTs() {
        return this.firstTs;
    }

    public final long getLastTs() {
        return this.lastTs;
    }

    public final String getNormalizedEventName() {
        return this.normalizedEventName;
    }

    public int hashCode() {
        return (((((((((this.eventName.hashCode() * 31) + this.normalizedEventName.hashCode()) * 31) + Long.hashCode(this.firstTs)) * 31) + Long.hashCode(this.lastTs)) * 31) + Integer.hashCode(this.countOfEvents)) * 31) + this.deviceID.hashCode();
    }

    public String toString() {
        return "UserEventLog(eventName=" + this.eventName + ", normalizedEventName=" + this.normalizedEventName + ", firstTs=" + this.firstTs + ", lastTs=" + this.lastTs + ", countOfEvents=" + this.countOfEvents + ", deviceID=" + this.deviceID + ')';
    }

    public UserEventLog(String eventName, String normalizedEventName, long j, long j2, int i, String deviceID) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(normalizedEventName, "normalizedEventName");
        Intrinsics.checkNotNullParameter(deviceID, "deviceID");
        this.eventName = eventName;
        this.normalizedEventName = normalizedEventName;
        this.firstTs = j;
        this.lastTs = j2;
        this.countOfEvents = i;
        this.deviceID = deviceID;
    }
}
