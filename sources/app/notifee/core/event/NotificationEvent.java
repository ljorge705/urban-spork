package app.notifee.core.event;

import android.os.Bundle;
import app.notifee.core.model.NotificationModel;

/* loaded from: classes5.dex */
public class NotificationEvent {
    public static final int TYPE_ACTION_PRESS = 2;
    public static final int TYPE_DELIVERED = 3;
    public static final int TYPE_DISMISSED = 0;
    public static final int TYPE_FG_ALREADY_EXIST = 8;
    public static final int TYPE_PRESS = 1;
    public static final int TYPE_TRIGGER_NOTIFICATION_CREATED = 7;

    /* renamed from: a, reason: collision with root package name */
    public final int f52a;
    public final Bundle b;
    public final NotificationModel c;

    public NotificationEvent(int i, NotificationModel notificationModel) {
        this.f52a = i;
        this.c = notificationModel;
        this.b = null;
    }

    public Bundle getExtras() {
        return this.b;
    }

    public NotificationModel getNotification() {
        return this.c;
    }

    public int getType() {
        return this.f52a;
    }

    public NotificationEvent(int i, NotificationModel notificationModel, Bundle bundle) {
        this.f52a = i;
        this.c = notificationModel;
        this.b = bundle;
    }
}
