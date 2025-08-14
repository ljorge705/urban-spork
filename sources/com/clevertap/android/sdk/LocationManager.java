package com.clevertap.android.sdk;

import android.content.Context;
import android.location.Location;
import com.clevertap.android.sdk.events.BaseEventQueueManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Iterator;
import java.util.concurrent.Future;
import org.json.JSONObject;

/* loaded from: classes5.dex */
class LocationManager extends BaseLocationManager {
    private int lastLocationPingTime = 0;
    private int lastLocationPingTimeForGeofence = 0;
    private final BaseEventQueueManager mBaseEventQueueManager;
    private final CleverTapInstanceConfig mConfig;
    private final Context mContext;
    private final CoreMetaData mCoreMetaData;
    private final Logger mLogger;

    int getLastLocationPingTime() {
        return this.lastLocationPingTime;
    }

    int getLastLocationPingTimeForGeofence() {
        return this.lastLocationPingTimeForGeofence;
    }

    void setLastLocationPingTime(int i) {
        this.lastLocationPingTime = i;
    }

    void setLastLocationPingTimeForGeofence(int i) {
        this.lastLocationPingTimeForGeofence = i;
    }

    LocationManager(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, CoreMetaData coreMetaData, BaseEventQueueManager baseEventQueueManager) {
        this.mContext = context;
        this.mConfig = cleverTapInstanceConfig;
        this.mLogger = cleverTapInstanceConfig.getLogger();
        this.mCoreMetaData = coreMetaData;
        this.mBaseEventQueueManager = baseEventQueueManager;
    }

    @Override // com.clevertap.android.sdk.BaseLocationManager
    public Location _getLocation() {
        try {
            android.location.LocationManager locationManager = (android.location.LocationManager) this.mContext.getSystemService(FirebaseAnalytics.Param.LOCATION);
            if (locationManager == null) {
                Logger.d("Location Manager is null.");
                return null;
            }
            Iterator<String> it = locationManager.getProviders(true).iterator();
            Location location = null;
            Location lastKnownLocation = null;
            while (it.hasNext()) {
                try {
                    lastKnownLocation = locationManager.getLastKnownLocation(it.next());
                } catch (SecurityException e) {
                    Logger.v("Location security exception", e);
                }
                if (lastKnownLocation != null && (location == null || lastKnownLocation.getAccuracy() < location.getAccuracy())) {
                    location = lastKnownLocation;
                }
            }
            return location;
        } catch (Throwable th) {
            Logger.v("Couldn't get user's location", th);
            return null;
        }
    }

    @Override // com.clevertap.android.sdk.BaseLocationManager
    Future<?> _setLocation(Location location) {
        if (location == null) {
            return null;
        }
        this.mCoreMetaData.setLocationFromUser(location);
        this.mLogger.verbose(this.mConfig.getAccountId(), "Location updated (" + location.getLatitude() + ", " + location.getLongitude() + ")");
        if (!this.mCoreMetaData.isLocationForGeofence() && !CleverTapAPI.isAppForeground()) {
            return null;
        }
        int now = getNow();
        if (this.mCoreMetaData.isLocationForGeofence() && now > this.lastLocationPingTimeForGeofence + 10) {
            Future<?> futureQueueEvent = this.mBaseEventQueueManager.queueEvent(this.mContext, new JSONObject(), 2);
            setLastLocationPingTimeForGeofence(now);
            this.mLogger.verbose(this.mConfig.getAccountId(), "Queuing location ping event for geofence location (" + location.getLatitude() + ", " + location.getLongitude() + ")");
            return futureQueueEvent;
        }
        if (this.mCoreMetaData.isLocationForGeofence() || now <= this.lastLocationPingTime + 10) {
            return null;
        }
        Future<?> futureQueueEvent2 = this.mBaseEventQueueManager.queueEvent(this.mContext, new JSONObject(), 2);
        setLastLocationPingTime(now);
        this.mLogger.verbose(this.mConfig.getAccountId(), "Queuing location ping event for location (" + location.getLatitude() + ", " + location.getLongitude() + ")");
        return futureQueueEvent2;
    }

    int getNow() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
