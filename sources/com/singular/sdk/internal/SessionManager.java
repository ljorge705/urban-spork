package com.singular.sdk.internal;

import android.app.Application;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
import com.singular.sdk.internal.BroadcastReceivers;
import io.sentry.Session;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes6.dex */
class SessionManager {
    private static final SingularLog logger = SingularLog.getLogger("Session");
    private final BroadcastReceivers.NetworkChange networkChangeReceiver;
    private final SingularInstance singular;
    private boolean usingForegroundTracking = false;
    private long sessionId = -1;
    private long lastSessionPauseTime = -1;
    private long sequence = 0;
    private boolean inForeground = true;

    private boolean inSession() {
        return this.sessionId > 0;
    }

    private void resetSequence() {
        this.sequence = 0L;
    }

    private void setSessionId(long j) {
        this.sessionId = j;
    }

    long getNextSequenceNumber() {
        long j = this.sequence + 1;
        this.sequence = j;
        return j;
    }

    long getSessionId() {
        return this.sessionId;
    }

    void useForegroundTracking() {
        this.usingForegroundTracking = true;
    }

    SessionManager(SingularInstance singularInstance) {
        this.singular = singularInstance;
        this.networkChangeReceiver = new BroadcastReceivers.NetworkChange(singularInstance);
        load();
        startNewSessionIfNeeded(Utils.getCurrentTimeMillis());
        enableForegroundTracking((Application) singularInstance.getContext());
        registerNetworkChangeReceiver();
    }

    private void enableForegroundTracking(Application application) {
        if (this.usingForegroundTracking) {
            return;
        }
        if (Utils.getWrapperName() == null || !Utils.getWrapperName().equalsIgnoreCase("mParticle")) {
            new SingularLifecycleCallbacks(this).registerSelf(application);
        }
    }

    private void load() {
        SharedPreferences sharedPreferences = this.singular.getContext().getSharedPreferences(Constants.PREF_SESSION, 0);
        this.sessionId = sharedPreferences.getLong("id", -1L);
        long j = sharedPreferences.getLong("lastSessionPauseTime", -1L);
        this.lastSessionPauseTime = j;
        if (j < 0) {
            this.lastSessionPauseTime = sharedPreferences.getLong("lastEvent", -1L);
        }
        this.sequence = sharedPreferences.getLong(Session.JsonKeys.SEQ, 0L);
        logger.debug("load() <= %s", toString());
    }

    private void persist() {
        try {
            SharedPreferences.Editor editorEdit = this.singular.getContext().getSharedPreferences(Constants.PREF_SESSION, 0).edit();
            editorEdit.putLong("id", this.sessionId);
            editorEdit.putLong("lastSessionPauseTime", this.lastSessionPauseTime);
            editorEdit.putLong(Session.JsonKeys.SEQ, this.sequence);
            editorEdit.commit();
        } catch (Throwable th) {
            logger.error(Utils.formatException(th));
        }
    }

    private long getSessionLengthInMs() {
        return System.currentTimeMillis() - this.sessionId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLastSessionPauseTime(long j) {
        this.lastSessionPauseTime = j;
        persist();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean startNewSessionIfNeeded(long j) {
        SingularInstance.getInstance().getDeviceInfo().sdid.sendResolveRequestIfNeeded(SingularInstance.getInstance().getDeviceInfo(), SingularInstance.getInstance().getContext());
        if (SingularInstance.getInstance().getSingularConfig().singularLink != null) {
            startNewSession(j);
            return true;
        }
        if (inSession() && isWithinMinTimeBetweenSessions(j)) {
            return false;
        }
        startNewSession(j);
        return true;
    }

    public void startNewSession(long j) {
        logger.debug("startNewSession() At %d", Long.valueOf(j));
        setSessionId(j);
        resetSequence();
        sendSessionStartEvent();
    }

    private boolean isWithinMinTimeBetweenSessions(long j) {
        return j - this.lastSessionPauseTime < this.singular.getSingularConfig().sessionTimeoutSec * 1000;
    }

    private void sendSessionStartEvent() {
        if (inSession()) {
            this.singular.logSessionStart(this.sessionId);
        }
    }

    void onExitForeground(final long j) {
        logger.debug("onExitForeground() At %d", Long.valueOf(j));
        this.singular.runOnWorker(new Runnable() { // from class: com.singular.sdk.internal.SessionManager.1
            @Override // java.lang.Runnable
            public void run() {
                SessionManager.this.setLastSessionPauseTime(j);
                SessionManager.this.inForeground = false;
                SessionManager.this.unregisterNetworkChangeReceiver();
                Utils.appMovedToBackground();
            }
        });
    }

    void onEnterForeground(final long j) {
        if (Utils.isOpenedWithDeeplink()) {
            return;
        }
        logger.debug("onEnterForeground() At %d", Long.valueOf(j));
        this.singular.runOnWorker(new Runnable() { // from class: com.singular.sdk.internal.SessionManager.2
            @Override // java.lang.Runnable
            public void run() {
                SessionManager.this.inForeground = true;
                SessionManager.this.startNewSessionIfNeeded(j);
                SessionManager.this.registerNetworkChangeReceiver();
            }
        });
    }

    void registerNetworkChangeReceiver() {
        if (this.inForeground || !this.usingForegroundTracking) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION);
            this.singular.getContext().registerReceiver(this.networkChangeReceiver, intentFilter);
            logger.debug("registerNetworkChangeReceiver()");
        }
    }

    void unregisterNetworkChangeReceiver() {
        if (this.networkChangeReceiver != null) {
            try {
                this.singular.getContext().unregisterReceiver(this.networkChangeReceiver);
                logger.debug("unregisterNetworkChangeReceiver()");
            } catch (Throwable unused) {
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{id=");
        sb.append(this.sessionId);
        sb.append(", lastSessionPauseTime=").append(this.lastSessionPauseTime);
        sb.append(", seq=").append(this.sequence);
        sb.append(AbstractJsonLexerKt.END_OBJ);
        return sb.toString();
    }
}
