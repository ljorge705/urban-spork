package io.invertase.firebase.interfaces;

import com.facebook.react.bridge.WritableMap;

/* loaded from: classes6.dex */
public interface NativeEvent {
    WritableMap getEventBody();

    String getEventName();

    String getFirebaseAppName();
}
