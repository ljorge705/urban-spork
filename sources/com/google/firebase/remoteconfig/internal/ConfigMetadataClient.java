package com.google.firebase.remoteconfig.internal;

import android.content.SharedPreferences;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import java.util.Date;

/* loaded from: classes2.dex */
public class ConfigMetadataClient {
    private static final String BACKOFF_END_TIME_IN_MILLIS_KEY = "backoff_end_time_in_millis";
    private static final String FETCH_TIMEOUT_IN_SECONDS_KEY = "fetch_timeout_in_seconds";
    private static final String LAST_FETCH_ETAG_KEY = "last_fetch_etag";
    private static final String LAST_FETCH_STATUS_KEY = "last_fetch_status";
    public static final long LAST_FETCH_TIME_IN_MILLIS_NO_FETCH_YET = -1;
    private static final String LAST_SUCCESSFUL_FETCH_TIME_IN_MILLIS_KEY = "last_fetch_time_in_millis";
    private static final String LAST_TEMPLATE_VERSION = "last_template_version";
    private static final String MINIMUM_FETCH_INTERVAL_IN_SECONDS_KEY = "minimum_fetch_interval_in_seconds";
    private static final long NO_BACKOFF_TIME_IN_MILLIS = -1;
    static final int NO_FAILED_FETCHES = 0;
    static final int NO_FAILED_REALTIME_STREAMS = 0;
    private static final String NUM_FAILED_FETCHES_KEY = "num_failed_fetches";
    private static final String NUM_FAILED_REALTIME_STREAMS_KEY = "num_failed_realtime_streams";
    private static final String REALTIME_BACKOFF_END_TIME_IN_MILLIS_KEY = "realtime_backoff_end_time_in_millis";
    private final SharedPreferences frcMetadata;
    static final Date LAST_FETCH_TIME_NO_FETCH_YET = new Date(-1);
    static final Date NO_BACKOFF_TIME = new Date(-1);
    private final Object frcInfoLock = new Object();
    private final Object backoffMetadataLock = new Object();
    private final Object realtimeBackoffMetadataLock = new Object();

    public ConfigMetadataClient(SharedPreferences sharedPreferences) {
        this.frcMetadata = sharedPreferences;
    }

    public long getFetchTimeoutInSeconds() {
        return this.frcMetadata.getLong(FETCH_TIMEOUT_IN_SECONDS_KEY, 60L);
    }

    public long getMinimumFetchIntervalInSeconds() {
        return this.frcMetadata.getLong(MINIMUM_FETCH_INTERVAL_IN_SECONDS_KEY, ConfigFetchHandler.DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS);
    }

    int getLastFetchStatus() {
        return this.frcMetadata.getInt(LAST_FETCH_STATUS_KEY, 0);
    }

    Date getLastSuccessfulFetchTime() {
        return new Date(this.frcMetadata.getLong(LAST_SUCCESSFUL_FETCH_TIME_IN_MILLIS_KEY, -1L));
    }

    String getLastFetchETag() {
        return this.frcMetadata.getString(LAST_FETCH_ETAG_KEY, null);
    }

    long getLastTemplateVersion() {
        return this.frcMetadata.getLong(LAST_TEMPLATE_VERSION, 0L);
    }

    public FirebaseRemoteConfigInfo getInfo() {
        FirebaseRemoteConfigInfoImpl firebaseRemoteConfigInfoImplBuild;
        synchronized (this.frcInfoLock) {
            long j = this.frcMetadata.getLong(LAST_SUCCESSFUL_FETCH_TIME_IN_MILLIS_KEY, -1L);
            int i = this.frcMetadata.getInt(LAST_FETCH_STATUS_KEY, 0);
            firebaseRemoteConfigInfoImplBuild = FirebaseRemoteConfigInfoImpl.newBuilder().withLastFetchStatus(i).withLastSuccessfulFetchTimeInMillis(j).withConfigSettings(new FirebaseRemoteConfigSettings.Builder().setFetchTimeoutInSeconds(this.frcMetadata.getLong(FETCH_TIMEOUT_IN_SECONDS_KEY, 60L)).setMinimumFetchIntervalInSeconds(this.frcMetadata.getLong(MINIMUM_FETCH_INTERVAL_IN_SECONDS_KEY, ConfigFetchHandler.DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS)).build()).build();
        }
        return firebaseRemoteConfigInfoImplBuild;
    }

    public void clear() {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().clear().commit();
        }
    }

    public void setConfigSettings(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putLong(FETCH_TIMEOUT_IN_SECONDS_KEY, firebaseRemoteConfigSettings.getFetchTimeoutInSeconds()).putLong(MINIMUM_FETCH_INTERVAL_IN_SECONDS_KEY, firebaseRemoteConfigSettings.getMinimumFetchIntervalInSeconds()).commit();
        }
    }

    public void setConfigSettingsWithoutWaitingOnDiskWrite(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putLong(FETCH_TIMEOUT_IN_SECONDS_KEY, firebaseRemoteConfigSettings.getFetchTimeoutInSeconds()).putLong(MINIMUM_FETCH_INTERVAL_IN_SECONDS_KEY, firebaseRemoteConfigSettings.getMinimumFetchIntervalInSeconds()).apply();
        }
    }

    void updateLastFetchAsSuccessfulAt(Date date) {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putInt(LAST_FETCH_STATUS_KEY, -1).putLong(LAST_SUCCESSFUL_FETCH_TIME_IN_MILLIS_KEY, date.getTime()).apply();
        }
    }

    void updateLastFetchAsFailed() {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putInt(LAST_FETCH_STATUS_KEY, 1).apply();
        }
    }

    void updateLastFetchAsThrottled() {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putInt(LAST_FETCH_STATUS_KEY, 2).apply();
        }
    }

    void setLastFetchETag(String str) {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putString(LAST_FETCH_ETAG_KEY, str).apply();
        }
    }

    void setLastTemplateVersion(long j) {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putLong(LAST_TEMPLATE_VERSION, j).apply();
        }
    }

    BackoffMetadata getBackoffMetadata() {
        BackoffMetadata backoffMetadata;
        synchronized (this.backoffMetadataLock) {
            backoffMetadata = new BackoffMetadata(this.frcMetadata.getInt(NUM_FAILED_FETCHES_KEY, 0), new Date(this.frcMetadata.getLong(BACKOFF_END_TIME_IN_MILLIS_KEY, -1L)));
        }
        return backoffMetadata;
    }

    void setBackoffMetadata(int i, Date date) {
        synchronized (this.backoffMetadataLock) {
            this.frcMetadata.edit().putInt(NUM_FAILED_FETCHES_KEY, i).putLong(BACKOFF_END_TIME_IN_MILLIS_KEY, date.getTime()).apply();
        }
    }

    void resetBackoff() {
        setBackoffMetadata(0, NO_BACKOFF_TIME);
    }

    static class BackoffMetadata {
        private Date backoffEndTime;
        private int numFailedFetches;

        Date getBackoffEndTime() {
            return this.backoffEndTime;
        }

        int getNumFailedFetches() {
            return this.numFailedFetches;
        }

        BackoffMetadata(int i, Date date) {
            this.numFailedFetches = i;
            this.backoffEndTime = date;
        }
    }

    RealtimeBackoffMetadata getRealtimeBackoffMetadata() {
        RealtimeBackoffMetadata realtimeBackoffMetadata;
        synchronized (this.realtimeBackoffMetadataLock) {
            realtimeBackoffMetadata = new RealtimeBackoffMetadata(this.frcMetadata.getInt(NUM_FAILED_REALTIME_STREAMS_KEY, 0), new Date(this.frcMetadata.getLong(REALTIME_BACKOFF_END_TIME_IN_MILLIS_KEY, -1L)));
        }
        return realtimeBackoffMetadata;
    }

    void setRealtimeBackoffMetadata(int i, Date date) {
        synchronized (this.realtimeBackoffMetadataLock) {
            this.frcMetadata.edit().putInt(NUM_FAILED_REALTIME_STREAMS_KEY, i).putLong(REALTIME_BACKOFF_END_TIME_IN_MILLIS_KEY, date.getTime()).apply();
        }
    }

    void resetRealtimeBackoff() {
        setRealtimeBackoffMetadata(0, NO_BACKOFF_TIME);
    }

    static class RealtimeBackoffMetadata {
        private Date backoffEndTime;
        private int numFailedStreams;

        Date getBackoffEndTime() {
            return this.backoffEndTime;
        }

        int getNumFailedStreams() {
            return this.numFailedStreams;
        }

        RealtimeBackoffMetadata(int i, Date date) {
            this.numFailedStreams = i;
            this.backoffEndTime = date;
        }
    }
}
