package io.sentry.rrweb;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.util.Objects;
import java.io.IOException;

/* loaded from: classes6.dex */
public abstract class RRWebIncrementalSnapshotEvent extends RRWebEvent {
    private IncrementalSource source;

    public static final class JsonKeys {
        public static final String SOURCE = "source";
    }

    public IncrementalSource getSource() {
        return this.source;
    }

    public void setSource(IncrementalSource incrementalSource) {
        this.source = incrementalSource;
    }

    public enum IncrementalSource implements JsonSerializable {
        Mutation,
        MouseMove,
        MouseInteraction,
        Scroll,
        ViewportResize,
        Input,
        TouchMove,
        MediaInteraction,
        StyleSheetRule,
        CanvasMutation,
        Font,
        Log,
        Drag,
        StyleDeclaration,
        Selection,
        AdoptedStyleSheet,
        CustomElement;

        @Override // io.sentry.JsonSerializable
        public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
            objectWriter.value(ordinal());
        }

        public static final class Deserializer implements JsonDeserializer<IncrementalSource> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.sentry.JsonDeserializer
            public IncrementalSource deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
                return IncrementalSource.values()[objectReader.nextInt()];
            }
        }
    }

    public RRWebIncrementalSnapshotEvent(IncrementalSource incrementalSource) {
        super(RRWebEventType.IncrementalSnapshot);
        this.source = incrementalSource;
    }

    public static final class Serializer {
        public void serialize(RRWebIncrementalSnapshotEvent rRWebIncrementalSnapshotEvent, ObjectWriter objectWriter, ILogger iLogger) throws IOException {
            objectWriter.name("source").value(iLogger, rRWebIncrementalSnapshotEvent.source);
        }
    }

    public static final class Deserializer {
        public boolean deserializeValue(RRWebIncrementalSnapshotEvent rRWebIncrementalSnapshotEvent, String str, ObjectReader objectReader, ILogger iLogger) throws Exception {
            if (!str.equals("source")) {
                return false;
            }
            rRWebIncrementalSnapshotEvent.source = (IncrementalSource) Objects.requireNonNull((IncrementalSource) objectReader.nextOrNull(iLogger, new IncrementalSource.Deserializer()), "");
            return true;
        }
    }
}
