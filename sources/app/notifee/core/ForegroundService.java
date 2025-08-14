package app.notifee.core;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import androidx.core.app.NotificationManagerCompat;
import app.notifee.core.event.ForegroundServiceEvent;
import app.notifee.core.event.NotificationEvent;
import app.notifee.core.interfaces.MethodCallResult;
import app.notifee.core.model.NotificationModel;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.f;

/* loaded from: classes5.dex */
public class ForegroundService extends Service {

    /* renamed from: a, reason: collision with root package name */
    public static String f39a;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(Exception exc, Void r2) {
        stopForeground(true);
        f39a = null;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null || "app.notifee.core.ForegroundService.STOP".equals(intent.getAction())) {
            stopSelf();
            f39a = null;
            return 0;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return 2;
        }
        int i3 = extras.getInt("hashCode");
        Notification notification = (Notification) extras.getParcelable("notification");
        Bundle bundle = extras.getBundle("notificationBundle");
        if (!(bundle != null) || !(notification != null)) {
            return 2;
        }
        NotificationModel notificationModel = new NotificationModel(bundle);
        String str = f39a;
        if (str == null) {
            f39a = notificationModel.c();
            startForeground(i3, notification);
            f.a(new ForegroundServiceEvent(notificationModel, new MethodCallResult() { // from class: app.notifee.core.ForegroundService$$ExternalSyntheticLambda0
                @Override // app.notifee.core.interfaces.MethodCallResult
                public final void onComplete(Exception exc, Object obj) {
                    this.f$0.a(exc, (Void) obj);
                }
            }));
            return 2;
        }
        if (str.equals(notificationModel.c())) {
            NotificationManagerCompat.from(e.f305a).notify(i3, notification);
            return 2;
        }
        f.a(new NotificationEvent(8, notificationModel));
        return 2;
    }
}
