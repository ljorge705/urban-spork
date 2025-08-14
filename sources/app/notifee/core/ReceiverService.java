package app.notifee.core;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import app.notifee.core.event.NotificationEvent;
import app.notifee.core.model.NotificationAndroidModel;
import app.notifee.core.model.NotificationAndroidPressActionModel;
import app.notifee.core.model.NotificationModel;
import java.util.concurrent.atomic.AtomicInteger;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.f;
import n.o.t.i.f.e.e.g;
import n.o.t.i.f.e.e.h;
import n.o.t.i.f.e.e.i;

/* loaded from: classes5.dex */
public class ReceiverService extends Service {

    /* renamed from: a, reason: collision with root package name */
    public static final AtomicInteger f41a = new AtomicInteger(0);

    public static PendingIntent a(String str, String[] strArr, Bundle... bundleArr) {
        Context context = e.f305a;
        Intent intent = new Intent(context, (Class<?>) ReceiverService.class);
        intent.setAction(str);
        for (int i = 0; i < strArr.length; i++) {
            String str2 = strArr[i];
            if (i <= bundleArr.length - 1) {
                intent.putExtra(str2, bundleArr[i]);
            } else {
                intent.putExtra(str2, (String) null);
            }
        }
        return PendingIntent.getService(context, f41a.getAndIncrement(), intent, 167772160);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) throws PendingIntent.CanceledException, ClassNotFoundException {
        CharSequence charSequence;
        NotificationAndroidPressActionModel notificationAndroidPressActionModelFromBundle;
        String action = intent.getAction();
        if (action == null) {
            return 2;
        }
        action.hashCode();
        switch (action) {
            case "app.notifee.core.ReceiverService.ACTION_PRESS_INTENT":
                Bundle bundleExtra = intent.getBundleExtra("notification");
                Bundle bundleExtra2 = intent.getBundleExtra("pressAction");
                if (bundleExtra != null && bundleExtra2 != null) {
                    NotificationModel notificationModel = new NotificationModel(bundleExtra);
                    NotificationAndroidModel notificationAndroidModelA = notificationModel.a();
                    NotificationAndroidPressActionModel notificationAndroidPressActionModelFromBundle2 = NotificationAndroidPressActionModel.fromBundle(bundleExtra2);
                    Bundle bundle = new Bundle();
                    bundle.putBundle("pressAction", notificationAndroidPressActionModelFromBundle2.toBundle());
                    Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
                    if (resultsFromIntent != null && (charSequence = resultsFromIntent.getCharSequence("app.notifee.core.ReceiverService.REMOTE_INPUT_RECEIVER_KEY")) != null) {
                        bundle.putString("input", charSequence.toString());
                    }
                    f.a(new NotificationEvent(2, notificationModel, bundle));
                    if (notificationModel.a().getAutoCancel().booleanValue()) {
                        NotificationManagerCompat.from(getApplicationContext()).cancel(notificationAndroidModelA.getTag(), notificationModel.c().hashCode());
                    }
                    String launchActivity = notificationAndroidPressActionModelFromBundle2.getLaunchActivity();
                    String mainComponent = notificationAndroidPressActionModelFromBundle2.getMainComponent();
                    if (launchActivity != null || mainComponent != null) {
                        a(new g(notificationModel, bundle), launchActivity, mainComponent, notificationAndroidPressActionModelFromBundle2.getLaunchActivityFlags());
                        int i3 = e.f305a.getApplicationInfo().targetSdkVersion;
                        if (Build.VERSION.SDK_INT < 31) {
                            e.f305a.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
                        }
                    }
                }
                return 2;
            case "app.notifee.core.ReceiverService.DELETE_INTENT":
                Bundle bundleExtra3 = intent.getBundleExtra("notification");
                if (bundleExtra3 != null) {
                    f.a(new NotificationEvent(0, new NotificationModel(bundleExtra3)));
                }
                return 2;
            case "app.notifee.core.ReceiverService.PRESS_INTENT":
                Bundle bundleExtra4 = intent.getBundleExtra("notification");
                if (bundleExtra4 != null) {
                    NotificationModel notificationModel2 = new NotificationModel(bundleExtra4);
                    Bundle bundleExtra5 = intent.getBundleExtra("pressAction");
                    Bundle bundle2 = new Bundle();
                    if (bundleExtra5 != null) {
                        notificationAndroidPressActionModelFromBundle = NotificationAndroidPressActionModel.fromBundle(bundleExtra5);
                        bundle2.putBundle("pressAction", notificationAndroidPressActionModelFromBundle.toBundle());
                    } else {
                        notificationAndroidPressActionModelFromBundle = null;
                    }
                    f.a(new NotificationEvent(1, notificationModel2, bundle2));
                    if (notificationAndroidPressActionModelFromBundle != null) {
                        String launchActivity2 = notificationAndroidPressActionModelFromBundle.getLaunchActivity();
                        String mainComponent2 = notificationAndroidPressActionModelFromBundle.getMainComponent();
                        if (launchActivity2 != null || mainComponent2 != null) {
                            a(new g(notificationModel2, bundle2), launchActivity2, mainComponent2, notificationAndroidPressActionModelFromBundle.getLaunchActivityFlags());
                        }
                    }
                }
                return 2;
            default:
                return 2;
        }
    }

    public final void a(g gVar, String str, String str2, int i) throws PendingIntent.CanceledException, ClassNotFoundException {
        Class<?> clsA = h.a(str);
        if (clsA == null) {
            Logger.e("ReceiverService", "Failed to get launch activity");
            return;
        }
        Intent intent = new Intent(getApplicationContext(), clsA);
        if (i != -1) {
            intent.addFlags(i);
        }
        if (str2 != null) {
            intent.putExtra("mainComponent", str2);
        }
        try {
            PendingIntent.getActivity(getApplicationContext(), gVar.f307a.b().intValue(), intent, 167772160).send();
            f.b(gVar);
            if (str2 != null) {
                f.b(new i(str2));
            }
        } catch (Exception e) {
            Logger.e("ReceiverService", "Failed to send PendingIntent from launchPendingIntentActivity for notification " + gVar.f307a.c(), e);
        }
    }
}
