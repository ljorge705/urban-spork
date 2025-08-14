package io.sentry.rrweb;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import java.io.IOException;

/* loaded from: classes6.dex */
public enum RRWebEventType implements JsonSerializable {
    DomContentLoaded,
    Load,
    FullSnapshot,
    IncrementalSnapshot,
    Meta,
    Custom,
    Plugin;

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.value(ordinal());
    }

    public static final class Deserializer implements JsonDeserializer<RRWebEventType> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public RRWebEventType deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            return RRWebEventType.values()[objectReader.nextInt()];
        }
    }
}
