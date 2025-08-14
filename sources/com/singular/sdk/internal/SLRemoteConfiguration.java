package com.singular.sdk.internal;

import app.notifee.core.event.LogEvent;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class SLRemoteConfiguration {
    private static final SingularLog logger = SingularLog.getLogger("SLRemoteConfiguration");

    @SerializedName("admon_batching")
    private SLRemoteConfigurationAdmonBatching slRemoteConfigurationAdmonBatching = new SLRemoteConfigurationAdmonBatching();

    static class SLRemoteConfigurationAdmonBatching {
        private static final SingularLog logger = SingularLog.getLogger("SLRemoteConfiguration");

        @SerializedName("AggregateAdmonEvents")
        private boolean aggregateAdmonEvents = false;

        @SerializedName(LogEvent.LEVEL_DEBUG)
        private boolean debug = false;

        public boolean isAggregateAdmonEvents() {
            return this.aggregateAdmonEvents;
        }

        public boolean isDebug() {
            return this.debug;
        }

        public int hashCode() {
            return Objects.hash(Boolean.valueOf(this.aggregateAdmonEvents), Boolean.valueOf(this.debug));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SLRemoteConfigurationAdmonBatching sLRemoteConfigurationAdmonBatching = (SLRemoteConfigurationAdmonBatching) obj;
            return this.aggregateAdmonEvents == sLRemoteConfigurationAdmonBatching.aggregateAdmonEvents && this.debug == sLRemoteConfigurationAdmonBatching.debug;
        }

        SLRemoteConfigurationAdmonBatching() {
        }
    }

    public int hashCode() {
        return Objects.hash(this.slRemoteConfigurationAdmonBatching);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this.slRemoteConfigurationAdmonBatching.equals(((SLRemoteConfiguration) obj).slRemoteConfigurationAdmonBatching);
        }
        return false;
    }

    private SLRemoteConfiguration() {
    }

    public JSONObject toJson() {
        try {
            return new JSONObject(new Gson().toJson(this));
        } catch (Throwable th) {
            logger.error(Utils.formatException(th));
            return new JSONObject();
        }
    }

    public static SLRemoteConfiguration fromJson(JSONObject jSONObject) {
        try {
            return (SLRemoteConfiguration) new Gson().fromJson(jSONObject.toString(), SLRemoteConfiguration.class);
        } catch (Throwable th) {
            logger.error(Utils.formatException(th));
            return new SLRemoteConfiguration();
        }
    }

    public static SLRemoteConfiguration defaultConfig() {
        return new SLRemoteConfiguration();
    }

    public boolean isAggregateAdmonEvents() {
        return this.slRemoteConfigurationAdmonBatching.isAggregateAdmonEvents();
    }

    public boolean isAdmonEventsDebug() {
        return this.slRemoteConfigurationAdmonBatching.isDebug();
    }
}
