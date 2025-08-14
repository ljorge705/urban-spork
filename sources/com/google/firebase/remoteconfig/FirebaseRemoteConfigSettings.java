package com.google.firebase.remoteconfig;

import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;

/* loaded from: classes2.dex */
public class FirebaseRemoteConfigSettings {
    private final long fetchTimeoutInSeconds;
    private final long minimumFetchInterval;

    public long getFetchTimeoutInSeconds() {
        return this.fetchTimeoutInSeconds;
    }

    public long getMinimumFetchIntervalInSeconds() {
        return this.minimumFetchInterval;
    }

    private FirebaseRemoteConfigSettings(Builder builder) {
        this.fetchTimeoutInSeconds = builder.fetchTimeoutInSeconds;
        this.minimumFetchInterval = builder.minimumFetchInterval;
    }

    public Builder toBuilder() throws IllegalArgumentException {
        Builder builder = new Builder();
        builder.setFetchTimeoutInSeconds(getFetchTimeoutInSeconds());
        builder.setMinimumFetchIntervalInSeconds(getMinimumFetchIntervalInSeconds());
        return builder;
    }

    public static class Builder {
        private long fetchTimeoutInSeconds = 60;
        private long minimumFetchInterval = ConfigFetchHandler.DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS;

        public long getFetchTimeoutInSeconds() {
            return this.fetchTimeoutInSeconds;
        }

        public long getMinimumFetchIntervalInSeconds() {
            return this.minimumFetchInterval;
        }

        public Builder setFetchTimeoutInSeconds(long j) throws IllegalArgumentException {
            if (j < 0) {
                throw new IllegalArgumentException(String.format("Fetch connection timeout has to be a non-negative number. %d is an invalid argument", Long.valueOf(j)));
            }
            this.fetchTimeoutInSeconds = j;
            return this;
        }

        public Builder setMinimumFetchIntervalInSeconds(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("Minimum interval between fetches has to be a non-negative number. " + j + " is an invalid argument");
            }
            this.minimumFetchInterval = j;
            return this;
        }

        public FirebaseRemoteConfigSettings build() {
            return new FirebaseRemoteConfigSettings(this);
        }
    }
}
