package app.notifee.core.event;

import app.notifee.core.interfaces.MethodCallResult;
import app.notifee.core.model.NotificationModel;

/* loaded from: classes5.dex */
public class ForegroundServiceEvent {

    /* renamed from: a, reason: collision with root package name */
    public final NotificationModel f50a;
    public MethodCallResult<Void> b;
    public boolean c = false;

    public ForegroundServiceEvent(NotificationModel notificationModel, MethodCallResult<Void> methodCallResult) {
        this.f50a = notificationModel;
        this.b = methodCallResult;
    }

    public NotificationModel getNotification() {
        return this.f50a;
    }

    public void setCompletionResult() {
        if (this.c) {
            return;
        }
        this.c = true;
        this.b.onComplete(null, null);
    }
}
