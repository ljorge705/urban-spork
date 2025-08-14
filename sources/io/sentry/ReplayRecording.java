package io.sentry;

import io.sentry.rrweb.RRWebEvent;
import io.sentry.rrweb.RRWebEventType;
import io.sentry.rrweb.RRWebIncrementalSnapshotEvent;
import io.sentry.util.Objects;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public final class ReplayRecording implements JsonUnknown, JsonSerializable {
    private List<? extends RRWebEvent> payload;
    private Integer segmentId;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String SEGMENT_ID = "segment_id";
    }

    public List<? extends RRWebEvent> getPayload() {
        return this.payload;
    }

    public Integer getSegmentId() {
        return this.segmentId;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public void setPayload(List<? extends RRWebEvent> list) {
        this.payload = list;
    }

    public void setSegmentId(Integer num) {
        this.segmentId = num;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ReplayRecording replayRecording = (ReplayRecording) obj;
        return Objects.equals(this.segmentId, replayRecording.segmentId) && Objects.equals(this.payload, replayRecording.payload);
    }

    public int hashCode() {
        return Objects.hash(this.segmentId, this.payload);
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        if (this.segmentId != null) {
            objectWriter.name("segment_id").value(this.segmentId);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
        objectWriter.setLenient(true);
        if (this.segmentId != null) {
            objectWriter.jsonValue("\n");
        }
        List<? extends RRWebEvent> list = this.payload;
        if (list != null) {
            objectWriter.value(iLogger, list);
        }
        objectWriter.setLenient(false);
    }

    public static final class Deserializer implements JsonDeserializer<ReplayRecording> {
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00dd  */
        @Override // io.sentry.JsonDeserializer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.sentry.ReplayRecording deserialize(io.sentry.ObjectReader r17, io.sentry.ILogger r18) throws java.lang.Exception {
            /*
                Method dump skipped, instructions count: 442
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.sentry.ReplayRecording.Deserializer.deserialize(io.sentry.ObjectReader, io.sentry.ILogger):io.sentry.ReplayRecording");
        }
    }

    /* renamed from: io.sentry.ReplayRecording$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$sentry$rrweb$RRWebEventType;
        static final /* synthetic */ int[] $SwitchMap$io$sentry$rrweb$RRWebIncrementalSnapshotEvent$IncrementalSource;

        static {
            int[] iArr = new int[RRWebEventType.values().length];
            $SwitchMap$io$sentry$rrweb$RRWebEventType = iArr;
            try {
                iArr[RRWebEventType.IncrementalSnapshot.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$sentry$rrweb$RRWebEventType[RRWebEventType.Meta.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$sentry$rrweb$RRWebEventType[RRWebEventType.Custom.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[RRWebIncrementalSnapshotEvent.IncrementalSource.values().length];
            $SwitchMap$io$sentry$rrweb$RRWebIncrementalSnapshotEvent$IncrementalSource = iArr2;
            try {
                iArr2[RRWebIncrementalSnapshotEvent.IncrementalSource.MouseInteraction.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$sentry$rrweb$RRWebIncrementalSnapshotEvent$IncrementalSource[RRWebIncrementalSnapshotEvent.IncrementalSource.TouchMove.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
