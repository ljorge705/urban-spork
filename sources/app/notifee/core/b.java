package app.notifee.core;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.AlarmManagerCompat;
import app.notifee.core.model.NotificationModel;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.l;
import n.o.t.i.f.e.e.q;

/* loaded from: classes5.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public static final ExecutorService f44a = Executors.newCachedThreadPool();

    public static PendingIntent a(String str) {
        try {
            Context context = e.f305a;
            Intent intent = new Intent(context, (Class<?>) NotificationAlarmReceiver.class);
            intent.putExtra(Constants.PT_NOTIF_ID, str);
            return PendingIntent.getBroadcast(context, str.hashCode(), intent, 167772160);
        } catch (Exception e) {
            Logger.e("NotifeeAlarmManager", "Unable to create AlarmManager intent", e);
            return null;
        }
    }

    public static Object b(Task task) throws Exception {
        return app.notifee.core.database.a.a(e.f305a).a(Boolean.TRUE).continueWith(new Continuation() { // from class: app.notifee.core.b$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task2) {
                return b.a(task2);
            }
        });
    }

    public static /* synthetic */ void c(Task task) {
        if (task.isSuccessful()) {
            return;
        }
        Logger.e("NotifeeAlarmManager", "Failed to display notification", task.getException());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Task task) {
        byte[] bArr;
        for (q qVar : (List) task.getResult()) {
            byte[] bArr2 = qVar.b;
            if (bArr2 != null && (bArr = qVar.c) != null) {
                Bundle bundleA = l.a(bArr);
                NotificationModel notificationModel = new NotificationModel(l.a(bArr2));
                if (l.a(bundleA.get("type")) == 0) {
                    app.notifee.core.model.a aVar = new app.notifee.core.model.a(bundleA);
                    if (aVar.d.booleanValue()) {
                        a(notificationModel, aVar);
                    }
                }
            }
        }
    }

    public void b() {
        Logger.d("NotifeeAlarmManager", "Reschedule Notifications on reboot");
        new app.notifee.core.database.a(e.f305a).a(Boolean.TRUE).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.b$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.d(task);
            }
        });
    }

    public static void a(Bundle bundle) {
        final String string;
        if (bundle == null || (string = bundle.getString(Constants.PT_NOTIF_ID)) == null) {
            return;
        }
        new app.notifee.core.database.a(e.f305a).b(string).continueWithTask(f44a, new Continuation() { // from class: app.notifee.core.b$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return b.a(string, task);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.b$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                b.c(task);
            }
        });
    }

    public static Task a(final String str, Task task) throws Exception {
        byte[] bArr;
        final q qVar = (q) task.getResult();
        if (qVar != null && qVar.b != null && (bArr = qVar.c) != null) {
            final Bundle bundleA = l.a(bArr);
            final NotificationModel notificationModel = new NotificationModel(l.a(qVar.b));
            return c.b(notificationModel, bundleA).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.b$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    b.a(bundleA, notificationModel, str, qVar, task2);
                }
            });
        }
        Logger.w("NotifeeAlarmManager", "Attempted to handle doScheduledWork but no notification data was found.");
        return null;
    }

    public static void a(Bundle bundle, NotificationModel notificationModel, String str, q qVar, Task task) {
        if (!task.isSuccessful()) {
            Logger.e("NotifeeAlarmManager", "Failed to display notification", task.getException());
            return;
        }
        if (bundle.containsKey("repeatFrequency") && l.a(bundle.get("repeatFrequency")) != -1) {
            app.notifee.core.model.a aVar = new app.notifee.core.model.a(bundle);
            aVar.a();
            a(notificationModel, aVar);
            app.notifee.core.database.a.a(e.f305a).d(new q(str, qVar.b, l.a(bundle), Boolean.TRUE));
            return;
        }
        app.notifee.core.database.a.a(e.f305a).a(str);
    }

    public static void a(NotificationModel notificationModel, app.notifee.core.model.a aVar) {
        PendingIntent pendingIntentA = a(notificationModel.c());
        AlarmManager alarmManagerA = n.o.t.i.f.e.e.a.a();
        if (Build.VERSION.SDK_INT >= 31 && !alarmManagerA.canScheduleExactAlarms()) {
            System.err.println("Missing SCHEDULE_EXACT_ALARM permission. Trigger not scheduled. See: https://notifee.app/react-native/docs/triggers#android-12-limitations");
            return;
        }
        aVar.a();
        int iOrdinal = aVar.e.ordinal();
        if (iOrdinal == 0) {
            alarmManagerA.set(1, aVar.g.longValue(), pendingIntentA);
            return;
        }
        if (iOrdinal == 1) {
            AlarmManagerCompat.setAndAllowWhileIdle(alarmManagerA, 0, aVar.g.longValue(), pendingIntentA);
            return;
        }
        if (iOrdinal == 2) {
            AlarmManagerCompat.setExact(alarmManagerA, 0, aVar.g.longValue(), pendingIntentA);
            return;
        }
        if (iOrdinal == 3) {
            AlarmManagerCompat.setExactAndAllowWhileIdle(alarmManagerA, 0, aVar.g.longValue(), pendingIntentA);
        } else {
            if (iOrdinal != 4) {
                return;
            }
            Context context = e.f305a;
            AlarmManagerCompat.setAlarmClock(alarmManagerA, aVar.g.longValue(), PendingIntent.getActivity(context, notificationModel.c().hashCode(), context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()), 201326592), pendingIntentA);
        }
    }

    public static Continuation<Object, Task> a() {
        return new Continuation() { // from class: app.notifee.core.b$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return b.b(task);
            }
        };
    }

    public static Object a(Task task) throws Exception {
        if (!task.isSuccessful()) {
            return null;
        }
        Iterator it = ((List) task.getResult()).iterator();
        while (it.hasNext()) {
            PendingIntent pendingIntentA = a(((q) it.next()).f313a);
            AlarmManager alarmManagerA = n.o.t.i.f.e.e.a.a();
            if (pendingIntentA != null) {
                alarmManagerA.cancel(pendingIntentA);
            }
        }
        return null;
    }
}
