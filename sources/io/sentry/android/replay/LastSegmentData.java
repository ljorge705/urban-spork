package io.sentry.android.replay;

import com.clevertap.android.sdk.Constants;
import io.sentry.SentryReplayEvent;
import io.sentry.rrweb.RRWebEvent;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReplayCache.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0080\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0002\u0010\u0013J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\t\u0010'\u001a\u00020\tHÆ\u0003J\t\u0010(\u001a\u00020\u000bHÆ\u0003J\t\u0010)\u001a\u00020\rHÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011HÆ\u0003Ja\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011HÆ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u00020\tHÖ\u0001J\t\u00101\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u00062"}, d2 = {"Lio/sentry/android/replay/LastSegmentData;", "", "recorderConfig", "Lio/sentry/android/replay/ScreenshotRecorderConfig;", "cache", "Lio/sentry/android/replay/ReplayCache;", "timestamp", "Ljava/util/Date;", "id", "", "duration", "", "replayType", "Lio/sentry/SentryReplayEvent$ReplayType;", "screenAtStart", "", "events", "", "Lio/sentry/rrweb/RRWebEvent;", "(Lio/sentry/android/replay/ScreenshotRecorderConfig;Lio/sentry/android/replay/ReplayCache;Ljava/util/Date;IJLio/sentry/SentryReplayEvent$ReplayType;Ljava/lang/String;Ljava/util/List;)V", "getCache", "()Lio/sentry/android/replay/ReplayCache;", "getDuration", "()J", "getEvents", "()Ljava/util/List;", "getId", "()I", "getRecorderConfig", "()Lio/sentry/android/replay/ScreenshotRecorderConfig;", "getReplayType", "()Lio/sentry/SentryReplayEvent$ReplayType;", "getScreenAtStart", "()Ljava/lang/String;", "getTimestamp", "()Ljava/util/Date;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class LastSegmentData {
    private final ReplayCache cache;
    private final long duration;
    private final List<RRWebEvent> events;
    private final int id;
    private final ScreenshotRecorderConfig recorderConfig;
    private final SentryReplayEvent.ReplayType replayType;
    private final String screenAtStart;
    private final Date timestamp;

    /* renamed from: component1, reason: from getter */
    public final ScreenshotRecorderConfig getRecorderConfig() {
        return this.recorderConfig;
    }

    /* renamed from: component2, reason: from getter */
    public final ReplayCache getCache() {
        return this.cache;
    }

    /* renamed from: component3, reason: from getter */
    public final Date getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component4, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* renamed from: component5, reason: from getter */
    public final long getDuration() {
        return this.duration;
    }

    /* renamed from: component6, reason: from getter */
    public final SentryReplayEvent.ReplayType getReplayType() {
        return this.replayType;
    }

    /* renamed from: component7, reason: from getter */
    public final String getScreenAtStart() {
        return this.screenAtStart;
    }

    public final List<RRWebEvent> component8() {
        return this.events;
    }

    public final LastSegmentData copy(ScreenshotRecorderConfig recorderConfig, ReplayCache cache, Date timestamp, int id, long duration, SentryReplayEvent.ReplayType replayType, String screenAtStart, List<? extends RRWebEvent> events) {
        Intrinsics.checkNotNullParameter(recorderConfig, "recorderConfig");
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(replayType, "replayType");
        Intrinsics.checkNotNullParameter(events, "events");
        return new LastSegmentData(recorderConfig, cache, timestamp, id, duration, replayType, screenAtStart, events);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LastSegmentData)) {
            return false;
        }
        LastSegmentData lastSegmentData = (LastSegmentData) other;
        return Intrinsics.areEqual(this.recorderConfig, lastSegmentData.recorderConfig) && Intrinsics.areEqual(this.cache, lastSegmentData.cache) && Intrinsics.areEqual(this.timestamp, lastSegmentData.timestamp) && this.id == lastSegmentData.id && this.duration == lastSegmentData.duration && this.replayType == lastSegmentData.replayType && Intrinsics.areEqual(this.screenAtStart, lastSegmentData.screenAtStart) && Intrinsics.areEqual(this.events, lastSegmentData.events);
    }

    public final ReplayCache getCache() {
        return this.cache;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final List<RRWebEvent> getEvents() {
        return this.events;
    }

    public final int getId() {
        return this.id;
    }

    public final ScreenshotRecorderConfig getRecorderConfig() {
        return this.recorderConfig;
    }

    public final SentryReplayEvent.ReplayType getReplayType() {
        return this.replayType;
    }

    public final String getScreenAtStart() {
        return this.screenAtStart;
    }

    public final Date getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        int iHashCode = ((((((((((this.recorderConfig.hashCode() * 31) + this.cache.hashCode()) * 31) + this.timestamp.hashCode()) * 31) + Integer.hashCode(this.id)) * 31) + Long.hashCode(this.duration)) * 31) + this.replayType.hashCode()) * 31;
        String str = this.screenAtStart;
        return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.events.hashCode();
    }

    public String toString() {
        return "LastSegmentData(recorderConfig=" + this.recorderConfig + ", cache=" + this.cache + ", timestamp=" + this.timestamp + ", id=" + this.id + ", duration=" + this.duration + ", replayType=" + this.replayType + ", screenAtStart=" + this.screenAtStart + ", events=" + this.events + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LastSegmentData(ScreenshotRecorderConfig recorderConfig, ReplayCache cache, Date timestamp, int i, long j, SentryReplayEvent.ReplayType replayType, String str, List<? extends RRWebEvent> events) {
        Intrinsics.checkNotNullParameter(recorderConfig, "recorderConfig");
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(replayType, "replayType");
        Intrinsics.checkNotNullParameter(events, "events");
        this.recorderConfig = recorderConfig;
        this.cache = cache;
        this.timestamp = timestamp;
        this.id = i;
        this.duration = j;
        this.replayType = replayType;
        this.screenAtStart = str;
        this.events = events;
    }
}
