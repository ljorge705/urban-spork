package app.notifee.core;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.service.notification.StatusBarNotification;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.text.HtmlCompat;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.WorkManager;
import app.notifee.core.event.NotificationEvent;
import app.notifee.core.interfaces.MethodCallResult;
import app.notifee.core.model.NotificationAndroidActionModel;
import app.notifee.core.model.NotificationAndroidModel;
import app.notifee.core.model.NotificationAndroidPressActionModel;
import app.notifee.core.model.NotificationAndroidStyleModel;
import app.notifee.core.model.NotificationModel;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.oblador.keychain.KeychainModule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.f;
import n.o.t.i.f.e.e.h;
import n.o.t.i.f.e.e.i;
import n.o.t.i.f.e.e.j;
import n.o.t.i.f.e.e.l;
import n.o.t.i.f.e.e.n;
import n.o.t.i.f.e.e.q;

/* loaded from: classes5.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    public static final ExecutorService f45a = Executors.newCachedThreadPool();

    public static Object a(Task task) throws Exception {
        app.notifee.core.database.a.a(e.f305a).a();
        return null;
    }

    public static Object b(int i) throws Exception {
        NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(e.f305a);
        if (i == 1 || i == 0) {
            notificationManagerCompatFrom.cancelAll();
        }
        if (i != 2 && i != 0) {
            return null;
        }
        WorkManager workManager = WorkManager.getInstance(e.f305a);
        workManager.cancelAllWorkByTag("app.notifee.core.NotificationManager.TRIGGER");
        workManager.pruneWork();
        return null;
    }

    public static Void a(int i, List list, Task task) throws Exception {
        if (i == 1) {
            return null;
        }
        app.notifee.core.database.a.a(e.f305a).a((List<String>) list);
        return null;
    }

    public static Object a(List list, int i, String str) throws Exception {
        WorkManager workManager = WorkManager.getInstance(e.f305a);
        NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(e.f305a);
        Iterator it = list.iterator();
        while (true) {
            Integer numValueOf = null;
            if (!it.hasNext()) {
                return null;
            }
            String str2 = (String) it.next();
            Logger.i("NotificationManager", "Removing notification with id " + str2);
            if (i != 2) {
                if (str != null && str2.equals("0")) {
                    try {
                        numValueOf = Integer.valueOf(Integer.parseInt(str2));
                    } catch (Exception unused) {
                        Logger.e("NotificationManager", "cancelAllNotificationsWithIds -> Failed to parse id as integer  " + str2);
                    }
                    if (numValueOf != null) {
                        notificationManagerCompatFrom.cancel(str, numValueOf.intValue());
                    }
                }
                notificationManagerCompatFrom.cancel(str, str2.hashCode());
            }
            if (i != 1) {
                Logger.i("NotificationManager", "Removing notification with id " + str2);
                workManager.cancelUniqueWork("trigger:" + str2);
                workManager.pruneWork();
                PendingIntent pendingIntentA = b.a(str2);
                AlarmManager alarmManagerA = n.o.t.i.f.e.e.a.a();
                if (pendingIntentA != null) {
                    alarmManagerA.cancel(pendingIntentA);
                }
            }
        }
    }

    public static NotificationCompat.Builder b(NotificationAndroidModel notificationAndroidModel, NotificationModel notificationModel, Task task) throws Exception {
        PendingIntent pendingIntentA;
        Bitmap bitmap;
        NotificationCompat.Builder builder = (NotificationCompat.Builder) task.getResult();
        ArrayList<NotificationAndroidActionModel> actions = notificationAndroidModel.getActions();
        if (actions == null) {
            return builder;
        }
        Iterator<NotificationAndroidActionModel> it = actions.iterator();
        while (it.hasNext()) {
            NotificationAndroidActionModel next = it.next();
            if (e.f305a.getApplicationInfo().targetSdkVersion >= 31 && Build.VERSION.SDK_INT >= 31) {
                pendingIntentA = j.a(notificationModel.b().intValue(), next.getPressAction().toBundle(), 2, new String[]{"notification", "pressAction"}, notificationModel.toBundle(), next.getPressAction().toBundle());
            } else {
                pendingIntentA = ReceiverService.a("app.notifee.core.ReceiverService.ACTION_PRESS_INTENT", new String[]{"notification", "pressAction"}, notificationModel.toBundle(), next.getPressAction().toBundle());
            }
            String icon = next.getIcon();
            if (icon != null) {
                try {
                    bitmap = (Bitmap) Tasks.await(n.a(next.getIcon()), 10L, TimeUnit.SECONDS);
                } catch (TimeoutException e) {
                    Logger.e("NotificationManager", "Timeout occurred whilst trying to retrieve an action icon: " + icon, (Exception) e);
                } catch (Exception e2) {
                    Logger.e("NotificationManager", "An error occurred whilst trying to retrieve an action icon: " + icon, e2);
                }
            } else {
                bitmap = null;
            }
            NotificationCompat.Action.Builder builder2 = new NotificationCompat.Action.Builder(bitmap != null ? IconCompat.createWithAdaptiveBitmap(bitmap) : null, HtmlCompat.fromHtml(next.getTitle(), 0), pendingIntentA);
            RemoteInput remoteInput = next.getRemoteInput(builder2);
            if (remoteInput != null) {
                builder2.addRemoteInput(remoteInput);
            }
            builder.addAction(builder2.build());
        }
        return builder;
    }

    public static Task<NotificationCompat.Builder> a(final NotificationModel notificationModel) {
        final NotificationAndroidModel notificationAndroidModelA = notificationModel.a();
        Callable callable = new Callable() { // from class: app.notifee.core.c$$ExternalSyntheticLambda3
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return c.a(notificationAndroidModelA, notificationModel);
            }
        };
        Continuation continuation = new Continuation() { // from class: app.notifee.core.c$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return c.a(notificationAndroidModelA, task);
            }
        };
        Continuation continuation2 = new Continuation() { // from class: app.notifee.core.c$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return c.a(notificationAndroidModelA, notificationModel, task);
            }
        };
        Continuation continuation3 = new Continuation() { // from class: app.notifee.core.c$$ExternalSyntheticLambda6
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return c.b(notificationAndroidModelA, notificationModel, task);
            }
        };
        Continuation continuation4 = new Continuation() { // from class: app.notifee.core.c$$ExternalSyntheticLambda7
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return c.b(notificationAndroidModelA, task);
            }
        };
        ExecutorService executorService = f45a;
        return Tasks.call(executorService, callable).continueWith(executorService, continuation).continueWith(executorService, continuation3).continueWith(executorService, continuation4).continueWith(executorService, continuation2);
    }

    public static /* synthetic */ NotificationCompat.Builder b(NotificationAndroidModel notificationAndroidModel, Task task) throws Exception {
        Task<NotificationCompat.Style> styleTask;
        NotificationCompat.Style style;
        NotificationCompat.Builder builder = (NotificationCompat.Builder) task.getResult();
        NotificationAndroidStyleModel style2 = notificationAndroidModel.getStyle();
        if (style2 != null && (styleTask = style2.getStyleTask(f45a)) != null && (style = (NotificationCompat.Style) Tasks.await(styleTask)) != null) {
            builder.setStyle(style);
        }
        return builder;
    }

    public static Task<Void> b(final NotificationModel notificationModel, final Bundle bundle) {
        return a(notificationModel).continueWith(f45a, new Continuation() { // from class: app.notifee.core.c$$ExternalSyntheticLambda10
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return c.a(notificationModel, bundle, task);
            }
        });
    }

    public static List b() throws Exception {
        ArrayList arrayList = new ArrayList();
        for (StatusBarNotification statusBarNotification : ((NotificationManager) e.f305a.getSystemService("notification")).getActiveNotifications()) {
            Notification notification = statusBarNotification.getNotification();
            Bundle bundle = notification.extras;
            Bundle bundle2 = new Bundle();
            Bundle bundle3 = bundle.getBundle("notifee.notification");
            Bundle bundle4 = bundle.getBundle("notifee.trigger");
            if (bundle3 == null) {
                bundle3 = new Bundle();
                bundle3.putString("id", "" + statusBarNotification.getId());
                Object obj = bundle.get(NotificationCompat.EXTRA_TITLE);
                if (obj != null) {
                    bundle3.putString("title", obj.toString());
                }
                Object obj2 = bundle.get(NotificationCompat.EXTRA_TEXT);
                if (obj2 != null) {
                    bundle3.putString("body", obj2.toString());
                }
                Object obj3 = bundle.get(NotificationCompat.EXTRA_SUB_TEXT);
                if (obj3 != null) {
                    bundle3.putString(KeychainModule.AuthPromptOptions.SUBTITLE, obj3.toString());
                }
                Bundle bundle5 = new Bundle();
                if (Build.VERSION.SDK_INT >= 26) {
                    bundle5.putString("channelId", notification.getChannelId());
                }
                bundle5.putString("tag", statusBarNotification.getTag());
                bundle5.putString("group", notification.getGroup());
                bundle3.putBundle(Constants.KEY_ANDROID, bundle5);
                bundle2.putString("id", "" + statusBarNotification.getId());
            } else {
                bundle2.putString("id", "" + bundle3.get("id"));
            }
            if (bundle4 != null) {
                bundle2.putBundle("trigger", bundle4);
            }
            bundle2.putBundle("notification", bundle3);
            bundle2.putString("date", "" + statusBarNotification.getPostTime());
            arrayList.add(bundle2);
        }
        return arrayList;
    }

    public static void b(final MethodCallResult<List<Bundle>> methodCallResult) {
        new app.notifee.core.database.a(e.f305a).b().addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.c$$ExternalSyntheticLambda12
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                c.b(methodCallResult, task);
            }
        });
    }

    public static void b(MethodCallResult methodCallResult, Task task) {
        ArrayList arrayList = new ArrayList();
        if (task.isSuccessful()) {
            for (q qVar : (List) task.getResult()) {
                Bundle bundle = new Bundle();
                bundle.putBundle("notification", l.a(qVar.b));
                bundle.putBundle("trigger", l.a(qVar.c));
                arrayList.add(bundle);
            }
            methodCallResult.onComplete(null, arrayList);
            return;
        }
        methodCallResult.onComplete(task.getException(), arrayList);
    }

    public static NotificationCompat.Builder a(NotificationAndroidModel notificationAndroidModel, NotificationModel notificationModel) throws Exception {
        Bundle bundle;
        Boolean bool = Boolean.FALSE;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(e.f305a, notificationAndroidModel.getChannelId());
        Bundle bundle2 = notificationModel.f54a.getBundle("data");
        if (bundle2 != null) {
            bundle = (Bundle) bundle2.clone();
        } else {
            bundle = new Bundle();
        }
        builder.setExtras(bundle);
        builder.setDeleteIntent(ReceiverService.a("app.notifee.core.ReceiverService.DELETE_INTENT", new String[]{"notification"}, notificationModel.toBundle()));
        if (e.f305a.getApplicationInfo().targetSdkVersion >= 31 && Build.VERSION.SDK_INT >= 31) {
            builder.setContentIntent(j.a(notificationModel.b().intValue(), notificationAndroidModel.getPressAction(), 1, new String[]{"notification", "pressAction"}, notificationModel.toBundle(), notificationAndroidModel.getPressAction()));
        } else {
            builder.setContentIntent(ReceiverService.a("app.notifee.core.ReceiverService.PRESS_INTENT", new String[]{"notification", "pressAction"}, notificationModel.toBundle(), notificationAndroidModel.getPressAction()));
        }
        if (notificationModel.f54a.getString("title") != null) {
            builder.setContentTitle(HtmlCompat.fromHtml(notificationModel.f54a.getString("title"), 0));
        }
        if (notificationModel.f54a.getString(KeychainModule.AuthPromptOptions.SUBTITLE) != null) {
            builder.setSubText(HtmlCompat.fromHtml(notificationModel.f54a.getString(KeychainModule.AuthPromptOptions.SUBTITLE), 0));
        }
        if (notificationModel.f54a.getString("body") != null) {
            builder.setContentText(HtmlCompat.fromHtml(notificationModel.f54a.getString("body"), 0));
        }
        if (notificationAndroidModel.getBadgeIconType() != null) {
            builder.setBadgeIconType(notificationAndroidModel.getBadgeIconType().intValue());
        }
        if (notificationAndroidModel.getCategory() != null) {
            builder.setCategory(notificationAndroidModel.getCategory());
        }
        if (notificationAndroidModel.getColor() != null) {
            builder.setColor(notificationAndroidModel.getColor().intValue());
        }
        builder.setColorized(notificationAndroidModel.getColorized().booleanValue());
        builder.setChronometerCountDown(notificationAndroidModel.getChronometerCountDown().booleanValue());
        if (notificationAndroidModel.getGroup() != null) {
            builder.setGroup(notificationAndroidModel.getGroup());
        }
        builder.setGroupAlertBehavior(notificationAndroidModel.getGroupAlertBehaviour());
        builder.setGroupSummary(notificationAndroidModel.getGroupSummary().booleanValue());
        if (notificationAndroidModel.getInputHistory() != null) {
            builder.setRemoteInputHistory(notificationAndroidModel.getInputHistory());
        }
        if (notificationAndroidModel.getLights() != null) {
            ArrayList<Integer> lights = notificationAndroidModel.getLights();
            builder.setLights(lights.get(0).intValue(), lights.get(1).intValue(), lights.get(2).intValue());
        }
        builder.setLocalOnly(notificationAndroidModel.getLocalOnly().booleanValue());
        if (notificationAndroidModel.getNumber() != null) {
            builder.setNumber(notificationAndroidModel.getNumber().intValue());
        }
        if (notificationAndroidModel.getSound() != null) {
            Uri uriC = n.c(notificationAndroidModel.getSound());
            if (uriC != null) {
                bool = Boolean.TRUE;
                builder.setSound(uriC);
            } else {
                Logger.w("NotificationManager", "Unable to retrieve sound for notification, sound was specified as: " + notificationAndroidModel.getSound());
            }
        }
        builder.setDefaults(notificationAndroidModel.getDefaults(bool).intValue());
        builder.setOngoing(notificationAndroidModel.getOngoing().booleanValue());
        builder.setOnlyAlertOnce(notificationAndroidModel.getOnlyAlertOnce().booleanValue());
        builder.setPriority(notificationAndroidModel.getPriority());
        NotificationAndroidModel.a progress = notificationAndroidModel.getProgress();
        if (progress != null) {
            builder.setProgress(progress.f53a, progress.b, progress.c);
        }
        if (notificationAndroidModel.getShortcutId() != null) {
            builder.setShortcutId(notificationAndroidModel.getShortcutId());
        }
        builder.setShowWhen(notificationAndroidModel.getShowTimestamp().booleanValue());
        Integer smallIcon = notificationAndroidModel.getSmallIcon();
        if (smallIcon != null) {
            Integer smallIconLevel = notificationAndroidModel.getSmallIconLevel();
            if (smallIconLevel != null) {
                builder.setSmallIcon(smallIcon.intValue(), smallIconLevel.intValue());
            } else {
                builder.setSmallIcon(smallIcon.intValue());
            }
        }
        if (notificationAndroidModel.getSortKey() != null) {
            builder.setSortKey(notificationAndroidModel.getSortKey());
        }
        if (notificationAndroidModel.getTicker() != null) {
            builder.setTicker(notificationAndroidModel.getTicker());
        }
        if (notificationAndroidModel.getTimeoutAfter() != null) {
            builder.setTimeoutAfter(notificationAndroidModel.getTimeoutAfter().longValue());
        }
        builder.setUsesChronometer(notificationAndroidModel.getShowChronometer().booleanValue());
        long[] vibrationPattern = notificationAndroidModel.getVibrationPattern();
        if (vibrationPattern.length > 0) {
            builder.setVibrate(vibrationPattern);
        }
        builder.setVisibility(notificationAndroidModel.getVisibility());
        long timestamp = notificationAndroidModel.getTimestamp();
        if (timestamp > -1) {
            builder.setWhen(timestamp);
        }
        builder.setAutoCancel(notificationAndroidModel.getAutoCancel().booleanValue());
        return builder;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.core.app.NotificationCompat.Builder a(app.notifee.core.model.NotificationAndroidModel r9, com.google.android.gms.tasks.Task r10) throws java.lang.Exception {
        /*
            java.lang.String r0 = "NotificationManager"
            java.lang.Object r10 = r10.getResult()
            androidx.core.app.NotificationCompat$Builder r10 = (androidx.core.app.NotificationCompat.Builder) r10
            java.lang.Boolean r1 = r9.hasLargeIcon()
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto Lc2
            java.lang.String r1 = r9.getLargeIcon()
            com.google.android.gms.tasks.Task r2 = n.o.t.i.f.e.e.n.a(r1)     // Catch: java.lang.Exception -> L25 java.util.concurrent.TimeoutException -> L39
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.Exception -> L25 java.util.concurrent.TimeoutException -> L39
            r4 = 10
            java.lang.Object r2 = com.google.android.gms.tasks.Tasks.await(r2, r4, r3)     // Catch: java.lang.Exception -> L25 java.util.concurrent.TimeoutException -> L39
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2     // Catch: java.lang.Exception -> L25 java.util.concurrent.TimeoutException -> L39
            goto L4d
        L25:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "An error occurred whilst trying to retrieve a largeIcon image: "
            r3.<init>(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            app.notifee.core.Logger.e(r0, r1, r2)
            goto L4c
        L39:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Timeout occurred whilst trying to retrieve a largeIcon image: "
            r3.<init>(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            app.notifee.core.Logger.e(r0, r1, r2)
        L4c:
            r2 = 0
        L4d:
            if (r2 == 0) goto Lc2
            java.lang.Boolean r9 = r9.getCircularLargeIcon()
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto Lbf
            int r9 = r2.getWidth()
            int r0 = r2.getHeight()
            r1 = 0
            if (r9 <= r0) goto L7d
            android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r3 = android.graphics.Bitmap.createBitmap(r0, r0, r3)
            int r9 = r9 - r0
            int r9 = r9 / 2
            int r4 = r9 + r0
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>(r9, r1, r4, r0)
            android.graphics.Rect r9 = new android.graphics.Rect
            r9.<init>(r1, r1, r0, r0)
            int r0 = r0 / 2
            float r0 = (float) r0
            goto L98
        L7d:
            android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r3 = android.graphics.Bitmap.createBitmap(r9, r9, r3)
            int r0 = r0 - r9
            int r0 = r0 / 2
            int r4 = r0 + r9
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>(r1, r0, r9, r4)
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>(r1, r1, r9, r9)
            int r9 = r9 / 2
            float r9 = (float) r9
            r8 = r0
            r0 = r9
            r9 = r8
        L98:
            android.graphics.Canvas r4 = new android.graphics.Canvas
            r4.<init>(r3)
            android.graphics.Paint r6 = new android.graphics.Paint
            r6.<init>()
            r7 = 1
            r6.setAntiAlias(r7)
            r4.drawARGB(r1, r1, r1, r1)
            r1 = -65536(0xffffffffffff0000, float:NaN)
            r6.setColor(r1)
            r4.drawCircle(r0, r0, r0, r6)
            android.graphics.PorterDuffXfermode r0 = new android.graphics.PorterDuffXfermode
            android.graphics.PorterDuff$Mode r1 = android.graphics.PorterDuff.Mode.SRC_IN
            r0.<init>(r1)
            r6.setXfermode(r0)
            r4.drawBitmap(r2, r5, r9, r6)
            r2 = r3
        Lbf:
            r10.setLargeIcon(r2)
        Lc2:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: app.notifee.core.c.a(app.notifee.core.model.NotificationAndroidModel, com.google.android.gms.tasks.Task):androidx.core.app.NotificationCompat$Builder");
    }

    public static NotificationCompat.Builder a(NotificationAndroidModel notificationAndroidModel, NotificationModel notificationModel, Task task) throws Exception {
        NotificationCompat.Builder builder = (NotificationCompat.Builder) task.getResult();
        if (notificationAndroidModel.hasFullScreenAction().booleanValue()) {
            NotificationAndroidPressActionModel fullScreenAction = notificationAndroidModel.getFullScreenAction();
            String launchActivity = fullScreenAction.getLaunchActivity();
            Class<?> clsA = h.a(launchActivity);
            if (clsA == null) {
                Logger.e("NotificationManager", String.format("Launch Activity for full-screen action does not exist ('%s').", launchActivity));
                return builder;
            }
            Intent intent = new Intent(e.f305a, clsA);
            if (fullScreenAction.getLaunchActivityFlags() != -1) {
                intent.addFlags(fullScreenAction.getLaunchActivityFlags());
            }
            if (fullScreenAction.getMainComponent() != null) {
                intent.putExtra("mainComponent", fullScreenAction.getMainComponent());
                intent.putExtra("notification", notificationModel.toBundle());
                f.b(new i(fullScreenAction.getMainComponent()));
            }
            builder.setFullScreenIntent(PendingIntent.getActivity(e.f305a, notificationModel.b().intValue(), intent, 167772160), true);
        }
        return builder;
    }

    public static Task<Void> a(final int i) {
        return Tasks.call(new Callable() { // from class: app.notifee.core.c$$ExternalSyntheticLambda17
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return c.b(i);
            }
        }).continueWith(f45a, new Continuation() { // from class: app.notifee.core.c$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return c.a(i, task);
            }
        });
    }

    public static /* synthetic */ Void a(int i, Task task) throws Exception {
        if (i != 2 && i != 0) {
            return null;
        }
        task.continueWith(b.a()).addOnSuccessListener(new OnSuccessListener() { // from class: app.notifee.core.c$$ExternalSyntheticLambda15
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                ((Task) obj).continueWith(new Continuation() { // from class: app.notifee.core.c$$ExternalSyntheticLambda16
                    @Override // com.google.android.gms.tasks.Continuation
                    public final Object then(Task task2) {
                        return c.a(task2);
                    }
                });
            }
        });
        return null;
    }

    public static Task<Void> a(final int i, final List<String> list, final String str) {
        return Tasks.call(new Callable() { // from class: app.notifee.core.c$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return c.a(list, i, str);
            }
        }).continueWith(new Continuation() { // from class: app.notifee.core.c$$ExternalSyntheticLambda9
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return c.a(i, list, task);
            }
        });
    }

    public static Void a(NotificationModel notificationModel, Bundle bundle, Task task) throws Exception {
        NotificationCompat.Builder builder = (NotificationCompat.Builder) task.getResult();
        Bundle bundle2 = new Bundle();
        bundle2.putBundle("notifee.notification", notificationModel.toBundle());
        if (bundle != null) {
            bundle2.putBundle("notifee.trigger", bundle);
        }
        builder.addExtras(bundle2);
        Notification notificationBuild = builder.build();
        int iIntValue = notificationModel.b().intValue();
        NotificationAndroidModel notificationAndroidModelA = notificationModel.a();
        if (notificationAndroidModelA.getLoopSound().booleanValue()) {
            notificationBuild.flags |= 4;
        }
        if (notificationAndroidModelA.getFlags() != null && notificationAndroidModelA.getFlags().length > 0) {
            for (int i : notificationAndroidModelA.getFlags()) {
                notificationBuild.flags = i | notificationBuild.flags;
            }
        }
        if (notificationAndroidModelA.getLightUpScreen().booleanValue()) {
            PowerManager powerManager = (PowerManager) e.f305a.getSystemService("power");
            if (!Boolean.valueOf(powerManager.isInteractive()).booleanValue()) {
                powerManager.newWakeLock(805306394, "Notifee:lock").acquire();
                powerManager.newWakeLock(1, "Notifee:cpuLock").acquire();
            }
        }
        if (notificationAndroidModelA.getAsForegroundService().booleanValue()) {
            Bundle bundle3 = notificationModel.toBundle();
            String str = ForegroundService.f39a;
            Intent intent = new Intent(e.f305a, (Class<?>) ForegroundService.class);
            intent.setAction("app.notifee.core.ForegroundService.START");
            intent.putExtra("hashCode", iIntValue);
            intent.putExtra("notification", notificationBuild);
            intent.putExtra("notificationBundle", bundle3);
            if (Build.VERSION.SDK_INT >= 26) {
                e.f305a.startForegroundService(intent);
            } else {
                e.f305a.startService(intent);
            }
        } else {
            NotificationManagerCompat.from(e.f305a).notify(notificationAndroidModelA.getTag(), iIntValue, notificationBuild);
        }
        f.a(new NotificationEvent(3, notificationModel));
        return null;
    }

    public static Task<Void> a(final NotificationModel notificationModel, final Bundle bundle) {
        return Tasks.call(f45a, new Callable() { // from class: app.notifee.core.c$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return c.a(bundle, notificationModel);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00c4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Void a(android.os.Bundle r17, app.notifee.core.model.NotificationModel r18) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 485
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: app.notifee.core.c.a(android.os.Bundle, app.notifee.core.model.NotificationModel):java.lang.Void");
    }

    public static Task<List<Bundle>> a() {
        return Tasks.call(new Callable() { // from class: app.notifee.core.c$$ExternalSyntheticLambda11
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return c.b();
            }
        });
    }

    public static void a(final MethodCallResult<List<String>> methodCallResult) {
        new app.notifee.core.database.a(e.f305a).b().addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.c$$ExternalSyntheticLambda8
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                c.a(methodCallResult, task);
            }
        });
    }

    public static void a(MethodCallResult methodCallResult, Task task) {
        ArrayList arrayList = new ArrayList();
        if (task.isSuccessful()) {
            Iterator it = ((List) task.getResult()).iterator();
            while (it.hasNext()) {
                arrayList.add(((q) it.next()).f313a);
            }
            methodCallResult.onComplete(null, arrayList);
            return;
        }
        methodCallResult.onComplete(task.getException(), null);
    }

    public static void a(final Data data, final CallbackToFutureAdapter.Completer<ListenableWorker.Result> completer) {
        final String string = data.getString("id");
        app.notifee.core.database.a aVar = new app.notifee.core.database.a(e.f305a);
        aVar.b(string).continueWithTask(f45a, new Continuation() { // from class: app.notifee.core.c$$ExternalSyntheticLambda13
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return c.a(data, completer, task);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: app.notifee.core.c$$ExternalSyntheticLambda14
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                c.a(completer, data, string, task);
            }
        });
    }

    public static Task a(Data data, CallbackToFutureAdapter.Completer completer, Task task) throws Exception {
        byte[] byteArray;
        q qVar = (q) task.getResult();
        if (qVar == null || (byteArray = qVar.b) == null) {
            byteArray = data.getByteArray("notification");
            if (byteArray != null) {
                Logger.w("NotificationManager", "The trigger notification was created using an older version, please consider recreating the notification.");
            } else {
                Logger.w("NotificationManager", "Attempted to handle doScheduledWork but no notification data was found.");
                completer.set(ListenableWorker.Result.success());
                return null;
            }
        }
        NotificationModel notificationModel = new NotificationModel(l.a(byteArray));
        byte[] bArr = qVar.c;
        return b(notificationModel, bArr != null ? l.a(bArr) : null);
    }

    public static void a(CallbackToFutureAdapter.Completer completer, Data data, String str, Task task) {
        completer.set(ListenableWorker.Result.success());
        if (!task.isSuccessful()) {
            Logger.e("NotificationManager", "Failed to display notification", task.getException());
            return;
        }
        String string = data.getString("workRequestType");
        if (string == null || !string.equals("OneTime")) {
            return;
        }
        app.notifee.core.database.a.a(e.f305a).a(str);
    }
}
