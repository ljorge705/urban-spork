package com.reactnativecommunity.asyncstorage;

import com.clevertap.android.sdk.Constants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public class AsyncStorageErrorUtil {
    static WritableMap getError(@Nullable String str, String str2) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("message", str2);
        if (str != null) {
            writableMapCreateMap.putString(Constants.KEY_KEY, str);
        }
        return writableMapCreateMap;
    }

    static WritableMap getInvalidKeyError(@Nullable String str) {
        return getError(str, "Invalid key");
    }

    static WritableMap getInvalidValueError(@Nullable String str) {
        return getError(str, "Invalid Value");
    }

    static WritableMap getDBError(@Nullable String str) {
        return getError(str, "Database Error");
    }
}
