package com.reactnativegooglesignin;

import com.facebook.react.bridge.WritableMap;

/* loaded from: classes6.dex */
public class PendingAuthRecovery {
    private WritableMap userProperties;

    public WritableMap getUserProperties() {
        return this.userProperties;
    }

    public PendingAuthRecovery(WritableMap writableMap) {
        this.userProperties = writableMap;
    }
}
