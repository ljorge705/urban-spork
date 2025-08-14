package com.clevertap.android.sdk;

/* loaded from: classes5.dex */
public class CTLockManager {
    private final Object eventLock = new Object();
    private final Object inboxControllerLock = new Object();

    public Object getEventLock() {
        return this.eventLock;
    }

    public Object getInboxControllerLock() {
        return this.inboxControllerLock;
    }
}
