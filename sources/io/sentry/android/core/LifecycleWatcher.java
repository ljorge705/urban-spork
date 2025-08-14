package io.sentry.android.core;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.facebook.react.modules.appstate.AppStateModule;
import io.sentry.Breadcrumb;
import io.sentry.IHub;
import io.sentry.IScope;
import io.sentry.ScopeCallback;
import io.sentry.SentryLevel;
import io.sentry.Session;
import io.sentry.transport.CurrentDateProvider;
import io.sentry.transport.ICurrentDateProvider;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes6.dex */
final class LifecycleWatcher implements DefaultLifecycleObserver {
    private final ICurrentDateProvider currentDateProvider;
    private final boolean enableAppLifecycleBreadcrumbs;
    private final boolean enableSessionTracking;
    private final IHub hub;
    private final AtomicBoolean isFreshSession;
    private final AtomicLong lastUpdatedSession;
    private final long sessionIntervalMillis;
    private final Timer timer;
    private final Object timerLock;
    private TimerTask timerTask;

    Timer getTimer() {
        return this.timer;
    }

    TimerTask getTimerTask() {
        return this.timerTask;
    }

    LifecycleWatcher(IHub iHub, long j, boolean z, boolean z2) {
        this(iHub, j, z, z2, CurrentDateProvider.getInstance());
    }

    LifecycleWatcher(IHub iHub, long j, boolean z, boolean z2, ICurrentDateProvider iCurrentDateProvider) {
        this.lastUpdatedSession = new AtomicLong(0L);
        this.isFreshSession = new AtomicBoolean(false);
        this.timer = new Timer(true);
        this.timerLock = new Object();
        this.sessionIntervalMillis = j;
        this.enableSessionTracking = z;
        this.enableAppLifecycleBreadcrumbs = z2;
        this.hub = iHub;
        this.currentDateProvider = iCurrentDateProvider;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStart(LifecycleOwner lifecycleOwner) {
        startSession();
        addAppBreadcrumb("foreground");
        AppState.getInstance().setInBackground(false);
    }

    private void startSession() {
        cancelTask();
        long currentTimeMillis = this.currentDateProvider.getCurrentTimeMillis();
        this.hub.configureScope(new ScopeCallback() { // from class: io.sentry.android.core.LifecycleWatcher$$ExternalSyntheticLambda0
            @Override // io.sentry.ScopeCallback
            public final void run(IScope iScope) {
                this.f$0.m6021lambda$startSession$0$iosentryandroidcoreLifecycleWatcher(iScope);
            }
        });
        long j = this.lastUpdatedSession.get();
        if (j == 0 || j + this.sessionIntervalMillis <= currentTimeMillis) {
            if (this.enableSessionTracking) {
                this.hub.startSession();
            }
            this.hub.getOptions().getReplayController().start();
        } else if (!this.isFreshSession.get()) {
            this.hub.getOptions().getReplayController().resume();
        }
        this.isFreshSession.set(false);
        this.lastUpdatedSession.set(currentTimeMillis);
    }

    /* renamed from: lambda$startSession$0$io-sentry-android-core-LifecycleWatcher, reason: not valid java name */
    /* synthetic */ void m6021lambda$startSession$0$iosentryandroidcoreLifecycleWatcher(IScope iScope) {
        Session session;
        if (this.lastUpdatedSession.get() != 0 || (session = iScope.getSession()) == null || session.getStarted() == null) {
            return;
        }
        this.lastUpdatedSession.set(session.getStarted().getTime());
        this.isFreshSession.set(true);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStop(LifecycleOwner lifecycleOwner) {
        this.lastUpdatedSession.set(this.currentDateProvider.getCurrentTimeMillis());
        this.hub.getOptions().getReplayController().pause();
        scheduleEndSession();
        AppState.getInstance().setInBackground(true);
        addAppBreadcrumb(AppStateModule.APP_STATE_BACKGROUND);
    }

    private void scheduleEndSession() {
        synchronized (this.timerLock) {
            cancelTask();
            if (this.timer != null) {
                TimerTask timerTask = new TimerTask() { // from class: io.sentry.android.core.LifecycleWatcher.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        if (LifecycleWatcher.this.enableSessionTracking) {
                            LifecycleWatcher.this.hub.endSession();
                        }
                        LifecycleWatcher.this.hub.getOptions().getReplayController().stop();
                    }
                };
                this.timerTask = timerTask;
                this.timer.schedule(timerTask, this.sessionIntervalMillis);
            }
        }
    }

    private void cancelTask() {
        synchronized (this.timerLock) {
            TimerTask timerTask = this.timerTask;
            if (timerTask != null) {
                timerTask.cancel();
                this.timerTask = null;
            }
        }
    }

    private void addAppBreadcrumb(String str) {
        if (this.enableAppLifecycleBreadcrumbs) {
            Breadcrumb breadcrumb = new Breadcrumb();
            breadcrumb.setType(NotificationCompat.CATEGORY_NAVIGATION);
            breadcrumb.setData("state", str);
            breadcrumb.setCategory("app.lifecycle");
            breadcrumb.setLevel(SentryLevel.INFO);
            this.hub.addBreadcrumb(breadcrumb);
        }
    }
}
