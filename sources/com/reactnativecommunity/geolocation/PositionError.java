package com.reactnativecommunity.geolocation;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventNames;

/* loaded from: classes6.dex */
public class PositionError {
    public static int ACTIVITY_NULL = 4;
    public static int PERMISSION_DENIED = 1;
    public static int POSITION_UNAVAILABLE = 2;
    public static int TIMEOUT = 3;

    public static WritableMap buildError(int i, String str) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("code", i);
        if (str != null) {
            writableMapCreateMap.putString("message", str);
        }
        writableMapCreateMap.putInt(EventNames.Permissions.PERMISSION_DENIED, PERMISSION_DENIED);
        writableMapCreateMap.putInt("POSITION_UNAVAILABLE", POSITION_UNAVAILABLE);
        writableMapCreateMap.putInt("TIMEOUT", TIMEOUT);
        writableMapCreateMap.putInt("ACTIVITY_NULL", ACTIVITY_NULL);
        return writableMapCreateMap;
    }
}
