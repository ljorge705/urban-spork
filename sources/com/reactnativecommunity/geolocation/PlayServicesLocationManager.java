package com.reactnativecommunity.geolocation;

import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.SystemClock;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reactnativecommunity.geolocation.BaseLocationManager;

/* loaded from: classes6.dex */
public class PlayServicesLocationManager extends BaseLocationManager {
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private SettingsClient mLocationServicesSettingsClient;
    private LocationCallback mSingleLocationCallback;

    protected PlayServicesLocationManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(reactApplicationContext);
        this.mLocationServicesSettingsClient = LocationServices.getSettingsClient(reactApplicationContext);
    }

    @Override // com.reactnativecommunity.geolocation.BaseLocationManager
    public void getCurrentLocationData(final ReadableMap readableMap, final Callback callback, final Callback callback2) {
        final BaseLocationManager.LocationOptions locationOptionsFromReactMap = BaseLocationManager.LocationOptions.fromReactMap(readableMap);
        Activity currentActivity = this.mReactContext.getCurrentActivity();
        if (currentActivity == null) {
            LocationCallback locationCallbackCreateSingleLocationCallback = createSingleLocationCallback(callback, callback2);
            this.mSingleLocationCallback = locationCallbackCreateSingleLocationCallback;
            checkLocationSettings(readableMap, locationCallbackCreateSingleLocationCallback);
            return;
        }
        this.mFusedLocationClient.getLastLocation().addOnSuccessListener(currentActivity, new OnSuccessListener() { // from class: com.reactnativecommunity.geolocation.PlayServicesLocationManager$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                this.f$0.lambda$getCurrentLocationData$0(locationOptionsFromReactMap, callback, callback2, readableMap, (Location) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getCurrentLocationData$0(BaseLocationManager.LocationOptions locationOptions, Callback callback, Callback callback2, ReadableMap readableMap, Location location) {
        if (location != null && SystemClock.currentTimeMillis() - location.getTime() < locationOptions.maximumAge) {
            callback.invoke(locationToMap(location));
            return;
        }
        LocationCallback locationCallbackCreateSingleLocationCallback = createSingleLocationCallback(callback, callback2);
        this.mSingleLocationCallback = locationCallbackCreateSingleLocationCallback;
        checkLocationSettings(readableMap, locationCallbackCreateSingleLocationCallback);
    }

    @Override // com.reactnativecommunity.geolocation.BaseLocationManager
    public void startObserving(ReadableMap readableMap) {
        LocationCallback locationCallback = new LocationCallback() { // from class: com.reactnativecommunity.geolocation.PlayServicesLocationManager.1
            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    PlayServicesLocationManager.this.emitError(PositionError.POSITION_UNAVAILABLE, "No location provided (FusedLocationProvider/observer).");
                } else {
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) PlayServicesLocationManager.this.mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("geolocationDidChange", BaseLocationManager.locationToMap(locationResult.getLastLocation()));
                }
            }

            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                if (locationAvailability.isLocationAvailable()) {
                    return;
                }
                PlayServicesLocationManager.this.emitError(PositionError.POSITION_UNAVAILABLE, "Location not available (FusedLocationProvider).");
            }
        };
        this.mLocationCallback = locationCallback;
        checkLocationSettings(readableMap, locationCallback);
    }

    @Override // com.reactnativecommunity.geolocation.BaseLocationManager
    public void stopObserving() {
        LocationCallback locationCallback = this.mLocationCallback;
        if (locationCallback == null) {
            return;
        }
        this.mFusedLocationClient.removeLocationUpdates(locationCallback);
    }

    private void checkLocationSettings(ReadableMap readableMap, final LocationCallback locationCallback) {
        BaseLocationManager.LocationOptions locationOptionsFromReactMap = BaseLocationManager.LocationOptions.fromReactMap(readableMap);
        final LocationRequest locationRequestCreate = LocationRequest.create();
        locationRequestCreate.setInterval(locationOptionsFromReactMap.interval);
        if (locationOptionsFromReactMap.fastestInterval >= 0) {
            locationRequestCreate.setFastestInterval(locationOptionsFromReactMap.fastestInterval);
        }
        locationRequestCreate.setExpirationDuration((long) locationOptionsFromReactMap.maximumAge);
        if (locationOptionsFromReactMap.distanceFilter >= 0.0f) {
            locationRequestCreate.setSmallestDisplacement(locationOptionsFromReactMap.distanceFilter);
        }
        locationRequestCreate.setPriority(locationOptionsFromReactMap.highAccuracy ? 100 : 104);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequestCreate);
        this.mLocationServicesSettingsClient.checkLocationSettings(builder.build()).addOnSuccessListener(new OnSuccessListener() { // from class: com.reactnativecommunity.geolocation.PlayServicesLocationManager$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                this.f$0.lambda$checkLocationSettings$1(locationRequestCreate, locationCallback, (LocationSettingsResponse) obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.reactnativecommunity.geolocation.PlayServicesLocationManager$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                this.f$0.lambda$checkLocationSettings$2(locationRequestCreate, locationCallback, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkLocationSettings$1(LocationRequest locationRequest, LocationCallback locationCallback, LocationSettingsResponse locationSettingsResponse) {
        requestLocationUpdates(locationRequest, locationCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkLocationSettings$2(LocationRequest locationRequest, LocationCallback locationCallback, Exception exc) {
        if (isAnyProviderAvailable()) {
            requestLocationUpdates(locationRequest, locationCallback);
        } else {
            emitError(PositionError.POSITION_UNAVAILABLE, "Location not available (FusedLocationProvider/settings).");
        }
    }

    private void requestLocationUpdates(LocationRequest locationRequest, LocationCallback locationCallback) {
        this.mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    private boolean isAnyProviderAvailable() {
        LocationManager locationManager;
        if (this.mReactContext == null || (locationManager = (LocationManager) this.mReactContext.getSystemService(FirebaseAnalytics.Param.LOCATION)) == null) {
            return false;
        }
        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
    }

    private LocationCallback createSingleLocationCallback(final Callback callback, final Callback callback2) {
        return new LocationCallback() { // from class: com.reactnativecommunity.geolocation.PlayServicesLocationManager.2
            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    callback2.invoke(PositionError.buildError(PositionError.POSITION_UNAVAILABLE, "No location provided (FusedLocationProvider/lastLocation)."));
                    return;
                }
                callback.invoke(BaseLocationManager.locationToMap(locationResult.getLastLocation()));
                PlayServicesLocationManager.this.mFusedLocationClient.removeLocationUpdates(PlayServicesLocationManager.this.mSingleLocationCallback);
                PlayServicesLocationManager.this.mSingleLocationCallback = null;
            }

            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                if (locationAvailability.isLocationAvailable()) {
                    return;
                }
                callback2.invoke(PositionError.buildError(PositionError.POSITION_UNAVAILABLE, "Location not available (FusedLocationProvider/lastLocation)."));
            }
        };
    }
}
