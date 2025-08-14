package app.notifee.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import app.notifee.core.interfaces.EventListener;
import app.notifee.core.interfaces.MethodCallResult;
import app.notifee.core.model.NotificationModel;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import io.sentry.protocol.Device;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.f;
import n.o.t.i.f.e.e.g;
import n.o.t.i.f.e.e.h;
import n.o.t.i.f.e.e.i;
import n.o.t.i.f.e.e.m;

/* loaded from: classes5.dex */
public class Notifee {
    public static final int REQUEST_CODE_NOTIFICATION_PERMISSION = 11111;
    public static Notifee b = null;
    public static boolean c = false;

    /* renamed from: a, reason: collision with root package name */
    public MethodCallResult<Bundle> f40a;

    public static /* synthetic */ void a(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete(null, (Void) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void b(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete(null, (Void) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void c(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete(null, (Void) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void d(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete(null, (Void) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void e(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete(null, (Void) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void f(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete(null, (Void) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void g(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete(null, null);
        } else {
            Logger.e("API", "createTriggerNotification", task.getException());
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static Context getContext() {
        return e.f305a;
    }

    public static /* synthetic */ void h(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete(null, null);
        } else {
            Logger.e("API", "displayNotification", task.getException());
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void i(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete(null, (Bundle) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static void initialize(EventListener eventListener) {
        synchronized (Notifee.class) {
            if (c) {
                return;
            }
            if (b == null) {
                b = new Notifee();
            }
            if (eventListener != null) {
                EventSubscriber.register(eventListener);
            }
            c = true;
        }
    }

    public static /* synthetic */ void j(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete(null, (Bundle) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void k(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete(null, (List) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void l(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete(null, (List) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void m(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete(null, (List) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void n(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete(null, (Boolean) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public static /* synthetic */ void o(MethodCallResult methodCallResult, Task task) {
        if (task.isSuccessful()) {
            methodCallResult.onComplete(null, (Boolean) task.getResult());
        } else {
            methodCallResult.onComplete(task.getException(), null);
        }
    }

    public void cancelAllNotifications(int i, final MethodCallResult<Void> methodCallResult) {
        c.a(i).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda13
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Notifee.a(methodCallResult, task);
            }
        });
    }

    public void cancelAllNotificationsWithIds(int i, List<String> list, String str, final MethodCallResult<Void> methodCallResult) {
        c.a(i, list, str).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda12
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Notifee.b(methodCallResult, task);
            }
        });
    }

    public void createChannel(Bundle bundle, final MethodCallResult<Void> methodCallResult) {
        a.a(new n.o.t.i.f.e.e.c(bundle)).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda9
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Notifee.c(methodCallResult, task);
            }
        });
    }

    public void createChannelGroup(Bundle bundle, final MethodCallResult<Void> methodCallResult) {
        a.a(new n.o.t.i.f.e.e.b(bundle)).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Notifee.d(methodCallResult, task);
            }
        });
    }

    public void createChannelGroups(List<Bundle> list, final MethodCallResult<Void> methodCallResult) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<Bundle> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new n.o.t.i.f.e.e.b(it.next()));
        }
        a.a(arrayList).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda8
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Notifee.e(methodCallResult, task);
            }
        });
    }

    public void createChannels(List<Bundle> list, final MethodCallResult<Void> methodCallResult) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<Bundle> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new n.o.t.i.f.e.e.c(it.next()));
        }
        a.b(arrayList).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda6
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Notifee.f(methodCallResult, task);
            }
        });
    }

    public void createTriggerNotification(Bundle bundle, Bundle bundle2, final MethodCallResult<Void> methodCallResult) {
        c.a(new NotificationModel(bundle), bundle2).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Notifee.g(methodCallResult, task);
            }
        });
    }

    public void deleteChannel(String str, MethodCallResult<Void> methodCallResult) {
        ExecutorService executorService = a.f43a;
        NotificationManagerCompat.from(e.f305a).deleteNotificationChannel(str);
        methodCallResult.onComplete(null, null);
    }

    public void deleteChannelGroup(String str, MethodCallResult<Void> methodCallResult) {
        ExecutorService executorService = a.f43a;
        NotificationManagerCompat.from(e.f305a).deleteNotificationChannelGroup(str);
        methodCallResult.onComplete(null, null);
    }

    public void displayNotification(Bundle bundle, final MethodCallResult<Void> methodCallResult) {
        c.b(new NotificationModel(bundle), (Bundle) null).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda14
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Notifee.h(methodCallResult, task);
            }
        });
    }

    public void getChannel(String str, final MethodCallResult<Bundle> methodCallResult) {
        a.a(str).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda11
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Notifee.i(methodCallResult, task);
            }
        });
    }

    public void getChannelGroup(String str, final MethodCallResult<Bundle> methodCallResult) {
        a.b(str).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Notifee.j(methodCallResult, task);
            }
        });
    }

    public void getChannelGroups(final MethodCallResult<List<Bundle>> methodCallResult) {
        a.a().addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda7
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Notifee.k(methodCallResult, task);
            }
        });
    }

    public void getChannels(final MethodCallResult<List<Bundle>> methodCallResult) {
        a.b().addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Notifee.l(methodCallResult, task);
            }
        });
    }

    public void getDisplayedNotifications(final MethodCallResult<List<Bundle>> methodCallResult) {
        c.a().addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Notifee.m(methodCallResult, task);
            }
        });
    }

    public void getInitialNotification(Activity activity, MethodCallResult<Bundle> methodCallResult) {
        g gVar = (g) f.b.f306a.removeStickyEvent(g.class);
        Bundle bundle = new Bundle();
        if (gVar != null) {
            bundle.putAll(gVar.b);
            bundle.putBundle("notification", gVar.f307a.toBundle());
            methodCallResult.onComplete(null, bundle);
            return;
        }
        if (activity != null) {
            try {
                Intent intent = activity.getIntent();
                if (intent != null && intent.getExtras() != null && intent.hasExtra("notification")) {
                    bundle.putBundle("notification", intent.getBundleExtra("notification"));
                    methodCallResult.onComplete(null, bundle);
                    return;
                }
            } catch (Exception e) {
                Logger.e("API", "getInitialNotification", e);
            }
        }
        methodCallResult.onComplete(null, null);
    }

    public String getMainComponent(String str) {
        i iVar = (i) f.b.f306a.removeStickyEvent(i.class);
        return iVar == null ? str : iVar.f308a;
    }

    public void getNotificationSettings(MethodCallResult<Bundle> methodCallResult) {
        boolean zAreNotificationsEnabled = NotificationManagerCompat.from(e.f305a).areNotificationsEnabled();
        Bundle bundle = new Bundle();
        if (zAreNotificationsEnabled) {
            bundle.putInt("authorizationStatus", 1);
        } else {
            bundle.putInt("authorizationStatus", 0);
        }
        boolean zCanScheduleExactAlarms = Build.VERSION.SDK_INT >= 31 ? n.o.t.i.f.e.e.a.a().canScheduleExactAlarms() : true;
        Bundle bundle2 = new Bundle();
        if (zCanScheduleExactAlarms) {
            bundle2.putInt(NotificationCompat.CATEGORY_ALARM, 1);
        } else {
            bundle2.putInt(NotificationCompat.CATEGORY_ALARM, 0);
        }
        bundle.putBundle(Constants.KEY_ANDROID, bundle2);
        methodCallResult.onComplete(null, bundle);
    }

    public void getPowerManagerInfo(MethodCallResult<Bundle> methodCallResult) {
        String strA = h.a(m.a(e.f305a));
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        String str3 = Build.VERSION.RELEASE;
        Bundle bundle = new Bundle();
        bundle.putString(Device.JsonKeys.MANUFACTURER, str);
        bundle.putString(Device.JsonKeys.MODEL, str2);
        bundle.putString("version", str3);
        bundle.putString("activity", strA);
        methodCallResult.onComplete(null, bundle);
    }

    public void getTriggerNotificationIds(MethodCallResult<List<String>> methodCallResult) {
        c.a(methodCallResult);
    }

    public void getTriggerNotifications(MethodCallResult<List<Bundle>> methodCallResult) {
        c.b(methodCallResult);
    }

    public void isBatteryOptimizationEnabled(MethodCallResult<Boolean> methodCallResult) {
        Context context = e.f305a;
        methodCallResult.onComplete(null, Boolean.valueOf(!((PowerManager) context.getSystemService("power")).isIgnoringBatteryOptimizations(context.getPackageName())));
    }

    public void isChannelBlocked(String str, final MethodCallResult<Boolean> methodCallResult) {
        a.c(str).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda15
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Notifee.n(methodCallResult, task);
            }
        });
    }

    public void isChannelCreated(String str, final MethodCallResult<Boolean> methodCallResult) {
        a.d(str).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Notifee.o(methodCallResult, task);
            }
        });
    }

    public void openNotificationSettings(String str, Activity activity, MethodCallResult<Void> methodCallResult) {
        final Intent intent;
        if (getContext() == null || activity == null) {
            Logger.d("openNotificationSettings", "attempted to start activity but no current activity or context was available.");
            methodCallResult.onComplete(null, null);
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            if (str != null) {
                intent = new Intent("android.settings.CHANNEL_NOTIFICATION_SETTINGS");
                intent.putExtra("android.provider.extra.CHANNEL_ID", str);
            } else {
                intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS");
            }
            intent.putExtra("android.provider.extra.APP_PACKAGE", getContext().getPackageName());
        } else {
            intent = new Intent("android.settings.APPLICATION_SETTINGS");
        }
        intent.setFlags(268435456);
        activity.runOnUiThread(new Runnable() { // from class: app.notifee.core.Notifee$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                Notifee.getContext().startActivity(intent);
            }
        });
        methodCallResult.onComplete(null, null);
    }

    public void openPowerManagerSettings(Activity activity, MethodCallResult<Void> methodCallResult) {
        Intent intentA;
        synchronized (m.class) {
            intentA = m.f309a;
        }
        if (intentA == null) {
            intentA = m.a(e.f305a);
        }
        if (intentA != null) {
            try {
                intentA.setFlags(268435456);
                h.a(activity, intentA);
            } catch (Exception e) {
                Logger.e("PowerManagerUtils", "Unable to start activity: " + h.a(intentA), e);
            }
        } else {
            Logger.w("PowerManagerUtils", "Unable to find an activity to open the device's power manager");
        }
        methodCallResult.onComplete(null, null);
    }

    public void setRequestPermissionCallback(MethodCallResult<Bundle> methodCallResult) {
        this.f40a = methodCallResult;
    }

    public void stopForegroundService(MethodCallResult<Void> methodCallResult) {
        String str = ForegroundService.f39a;
        Intent intent = new Intent(e.f305a, (Class<?>) ForegroundService.class);
        intent.setAction("app.notifee.core.ForegroundService.STOP");
        try {
            e.f305a.startService(intent);
        } catch (IllegalStateException unused) {
            e.f305a.stopService(intent);
        } catch (Exception e) {
            Logger.e("ForegroundService", "Unable to stop foreground service", e);
        }
        methodCallResult.onComplete(null, null);
    }

    public static Notifee getInstance() {
        if (!c) {
            Logger.w("API", "getInstance() accessed before event listener is initialized");
            b = new Notifee();
        }
        return b;
    }

    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        MethodCallResult<Bundle> methodCallResult;
        if (i != 11111 || (methodCallResult = this.f40a) == null) {
            return false;
        }
        getNotificationSettings(methodCallResult);
        return true;
    }

    public void openAlarmPermissionSettings(Activity activity, MethodCallResult<Void> methodCallResult) {
        if (Build.VERSION.SDK_INT >= 31) {
            try {
                Intent intent = new Intent();
                intent.setFlags(268435456);
                intent.setAction("android.settings.REQUEST_SCHEDULE_EXACT_ALARM");
                intent.setData(Uri.parse("package:" + e.f305a.getPackageName()));
                h.a(activity, intent);
            } catch (Exception e) {
                Logger.e("AlarmUtils", "An error occurred whilst trying to open alarm permission settings", e);
            }
        }
        methodCallResult.onComplete(null, null);
    }

    public void openBatteryOptimizationSettings(Activity activity, MethodCallResult<Void> methodCallResult) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS");
            intent.setFlags(268435456);
            if (activity != null) {
                if (!Boolean.valueOf(h.a(e.f305a, intent)).booleanValue()) {
                    Logger.d("PowerManagerUtils", "battery optimization settings is not available on device");
                } else {
                    h.a(activity, intent);
                }
            }
        } catch (Exception e) {
            Logger.e("PowerManagerUtils", "An error occurred whilst trying to open battery optimization settings", e);
        }
        methodCallResult.onComplete(null, null);
    }
}
