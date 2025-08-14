package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.core.OnlineState;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Logger;
import io.grpc.Status;
import java.util.Locale;

/* loaded from: classes2.dex */
class OnlineStateTracker {
    private static final String LOG_TAG = "OnlineStateTracker";
    private static final int MAX_WATCH_STREAM_FAILURES = 1;
    private static final int ONLINE_STATE_TIMEOUT_MS = 10000;
    private final OnlineStateCallback onlineStateCallback;
    private AsyncQueue.DelayedTask onlineStateTimer;
    private int watchStreamFailures;
    private final AsyncQueue workerQueue;
    private OnlineState state = OnlineState.UNKNOWN;
    private boolean shouldWarnClientIsOffline = true;

    interface OnlineStateCallback {
        void handleOnlineStateChange(OnlineState onlineState);
    }

    OnlineState getState() {
        return this.state;
    }

    OnlineStateTracker(AsyncQueue asyncQueue, OnlineStateCallback onlineStateCallback) {
        this.workerQueue = asyncQueue;
        this.onlineStateCallback = onlineStateCallback;
    }

    void handleWatchStreamStart() {
        if (this.watchStreamFailures == 0) {
            setAndBroadcastState(OnlineState.UNKNOWN);
            Assert.hardAssert(this.onlineStateTimer == null, "onlineStateTimer shouldn't be started yet", new Object[0]);
            this.onlineStateTimer = this.workerQueue.enqueueAfterDelay(AsyncQueue.TimerId.ONLINE_STATE_TIMEOUT, 10000L, new Runnable() { // from class: com.google.firebase.firestore.remote.OnlineStateTracker$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m5351x16bdafa1();
                }
            });
        }
    }

    /* renamed from: lambda$handleWatchStreamStart$0$com-google-firebase-firestore-remote-OnlineStateTracker, reason: not valid java name */
    /* synthetic */ void m5351x16bdafa1() {
        this.onlineStateTimer = null;
        Assert.hardAssert(this.state == OnlineState.UNKNOWN, "Timer should be canceled if we transitioned to a different state.", new Object[0]);
        logClientOfflineWarningIfNecessary(String.format(Locale.ENGLISH, "Backend didn't respond within %d seconds\n", 10));
        setAndBroadcastState(OnlineState.OFFLINE);
    }

    void handleWatchStreamFailure(Status status) {
        if (this.state == OnlineState.ONLINE) {
            setAndBroadcastState(OnlineState.UNKNOWN);
            Assert.hardAssert(this.watchStreamFailures == 0, "watchStreamFailures must be 0", new Object[0]);
            Assert.hardAssert(this.onlineStateTimer == null, "onlineStateTimer must be null", new Object[0]);
            return;
        }
        int i = this.watchStreamFailures + 1;
        this.watchStreamFailures = i;
        if (i >= 1) {
            clearOnlineStateTimer();
            logClientOfflineWarningIfNecessary(String.format(Locale.ENGLISH, "Connection failed %d times. Most recent error: %s", 1, status));
            setAndBroadcastState(OnlineState.OFFLINE);
        }
    }

    void updateState(OnlineState onlineState) {
        clearOnlineStateTimer();
        this.watchStreamFailures = 0;
        if (onlineState == OnlineState.ONLINE) {
            this.shouldWarnClientIsOffline = false;
        }
        setAndBroadcastState(onlineState);
    }

    private void setAndBroadcastState(OnlineState onlineState) {
        if (onlineState != this.state) {
            this.state = onlineState;
            this.onlineStateCallback.handleOnlineStateChange(onlineState);
        }
    }

    private void logClientOfflineWarningIfNecessary(String str) {
        String str2 = String.format("Could not reach Cloud Firestore backend. %s\nThis typically indicates that your device does not have a healthy Internet connection at the moment. The client will operate in offline mode until it is able to successfully connect to the backend.", str);
        if (this.shouldWarnClientIsOffline) {
            Logger.warn(LOG_TAG, "%s", str2);
            this.shouldWarnClientIsOffline = false;
        } else {
            Logger.debug(LOG_TAG, "%s", str2);
        }
    }

    private void clearOnlineStateTimer() {
        AsyncQueue.DelayedTask delayedTask = this.onlineStateTimer;
        if (delayedTask != null) {
            delayedTask.cancel();
            this.onlineStateTimer = null;
        }
    }
}
