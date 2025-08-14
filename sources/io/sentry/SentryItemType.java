package io.sentry;

import io.sentry.cache.EnvelopeCache;
import io.sentry.clientreport.ClientReport;
import io.sentry.protocol.SentryTransaction;
import java.io.IOException;
import java.util.Locale;

/* loaded from: classes6.dex */
public enum SentryItemType implements JsonSerializable {
    Session(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE),
    Event("event"),
    UserFeedback("user_report"),
    Attachment("attachment"),
    Transaction("transaction"),
    Profile("profile"),
    ClientReport("client_report"),
    ReplayEvent(SentryReplayEvent.REPLAY_EVENT_TYPE),
    ReplayRecording("replay_recording"),
    ReplayVideo("replay_video"),
    CheckIn("check_in"),
    Statsd("statsd"),
    Feedback("feedback"),
    Unknown("__unknown__");

    private final String itemType;

    public String getItemType() {
        return this.itemType;
    }

    public static SentryItemType resolve(Object obj) {
        return obj instanceof SentryEvent ? Event : obj instanceof SentryTransaction ? Transaction : obj instanceof Session ? Session : obj instanceof ClientReport ? ClientReport : Attachment;
    }

    SentryItemType(String str) {
        this.itemType = str;
    }

    public static SentryItemType valueOfLabel(String str) {
        for (SentryItemType sentryItemType : values()) {
            if (sentryItemType.itemType.equals(str)) {
                return sentryItemType;
            }
        }
        return Unknown;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.value(this.itemType);
    }

    public static final class Deserializer implements JsonDeserializer<SentryItemType> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SentryItemType deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            return SentryItemType.valueOfLabel(objectReader.nextString().toLowerCase(Locale.ROOT));
        }
    }
}
