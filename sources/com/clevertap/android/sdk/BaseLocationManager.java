package com.clevertap.android.sdk;

import android.location.Location;
import java.util.concurrent.Future;

/* loaded from: classes5.dex */
abstract class BaseLocationManager {
    public abstract Location _getLocation();

    abstract Future<?> _setLocation(Location location);

    BaseLocationManager() {
    }
}
