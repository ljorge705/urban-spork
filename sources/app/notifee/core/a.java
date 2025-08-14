package app.notifee.core;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.TypedValue;
import androidx.core.app.NotificationManagerCompat;
import com.facebook.common.util.UriUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import n.o.t.i.f.e.e.d;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.l;
import n.o.t.i.f.e.e.n;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public static ExecutorService f43a = Executors.newCachedThreadPool();

    public static Task<Void> a(final n.o.t.i.f.e.e.c cVar) {
        return Tasks.call(f43a, new Callable() { // from class: app.notifee.core.a$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return a.b(cVar);
            }
        });
    }

    public static List c() throws Exception {
        List<NotificationChannelGroup> notificationChannelGroups = NotificationManagerCompat.from(e.f305a).getNotificationChannelGroups();
        if (notificationChannelGroups.size() == 0 || Build.VERSION.SDK_INT < 26) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(notificationChannelGroups.size());
        Iterator<NotificationChannelGroup> it = notificationChannelGroups.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return arrayList;
    }

    public static List d() throws Exception {
        List<NotificationChannel> notificationChannels = NotificationManagerCompat.from(e.f305a).getNotificationChannels();
        if (notificationChannels.size() == 0 || Build.VERSION.SDK_INT < 26) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(notificationChannels.size());
        Iterator<NotificationChannel> it = notificationChannels.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return arrayList;
    }

    public static Bundle e(String str) throws Exception {
        return a(NotificationManagerCompat.from(e.f305a).getNotificationChannel(str));
    }

    public static Bundle f(String str) throws Exception {
        return a(NotificationManagerCompat.from(e.f305a).getNotificationChannelGroup(str));
    }

    public static Boolean g(String str) throws Exception {
        if (Build.VERSION.SDK_INT < 26) {
            return Boolean.FALSE;
        }
        NotificationChannel notificationChannel = NotificationManagerCompat.from(e.f305a).getNotificationChannel(str);
        if (notificationChannel == null) {
            return Boolean.FALSE;
        }
        return Boolean.valueOf(notificationChannel.getImportance() == 0);
    }

    public static Boolean h(String str) throws Exception {
        if (Build.VERSION.SDK_INT < 26) {
            return Boolean.FALSE;
        }
        return Boolean.valueOf(NotificationManagerCompat.from(e.f305a).getNotificationChannel(str) != null);
    }

    public static Task<Void> a(final n.o.t.i.f.e.e.b bVar) {
        return Tasks.call(f43a, new Callable() { // from class: app.notifee.core.a$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return a.b(bVar);
            }
        });
    }

    public static Task<Void> a(final List<n.o.t.i.f.e.e.b> list) {
        return Tasks.call(f43a, new Callable() { // from class: app.notifee.core.a$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return a.c(list);
            }
        });
    }

    public static Task<Bundle> a(final String str) {
        return Tasks.call(f43a, new Callable() { // from class: app.notifee.core.a$$ExternalSyntheticLambda8
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return a.e(str);
            }
        });
    }

    public static Task<List<Bundle>> a() {
        return Tasks.call(f43a, new Callable() { // from class: app.notifee.core.a$$ExternalSyntheticLambda5
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return a.c();
            }
        });
    }

    public static Void b(n.o.t.i.f.e.e.c cVar) throws Exception {
        Integer numValueOf;
        long[] jArr;
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        String str = (String) Objects.requireNonNull(cVar.f303a.getString("id"));
        String str2 = (String) Objects.requireNonNull(cVar.f303a.getString("name"));
        if (cVar.f303a.containsKey("importance")) {
            numValueOf = Integer.valueOf(l.a(cVar.f303a.get("importance")));
        } else {
            numValueOf = 3;
        }
        NotificationChannel notificationChannel = new NotificationChannel(str, str2, numValueOf.intValue());
        notificationChannel.setShowBadge(Boolean.valueOf(cVar.f303a.getBoolean("badge", true)).booleanValue());
        notificationChannel.setBypassDnd(Boolean.valueOf(cVar.f303a.getBoolean("bypassDnd", false)).booleanValue());
        notificationChannel.setDescription(cVar.f303a.getString("description"));
        notificationChannel.setGroup(cVar.f303a.getString("groupId"));
        notificationChannel.enableLights(Boolean.valueOf(cVar.f303a.getBoolean("lights", true)).booleanValue());
        if (cVar.a() != null) {
            notificationChannel.setLightColor(cVar.a().intValue());
        }
        notificationChannel.setLockscreenVisibility(cVar.f303a.containsKey(ViewHierarchyNode.JsonKeys.VISIBILITY) ? l.a(cVar.f303a.get(ViewHierarchyNode.JsonKeys.VISIBILITY)) : 0);
        notificationChannel.enableVibration(Boolean.valueOf(cVar.f303a.getBoolean("vibration", true)).booleanValue());
        if (cVar.f303a.containsKey("vibrationPattern")) {
            ArrayList arrayList = (ArrayList) Objects.requireNonNull(cVar.f303a.getParcelableArrayList("vibrationPattern"));
            long[] jArr2 = new long[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                jArr2[i] = ((Integer) arrayList.get(i)).longValue();
            }
            jArr = jArr2;
        } else {
            jArr = new long[0];
        }
        if (jArr.length > 0) {
            notificationChannel.setVibrationPattern(jArr);
        }
        if ((!cVar.f303a.containsKey("sound") ? null : cVar.f303a.getString("sound")) != null) {
            Uri uriC = n.c(!cVar.f303a.containsKey("sound") ? null : cVar.f303a.getString("sound"));
            if (uriC != null) {
                notificationChannel.setSound(uriC, new AudioAttributes.Builder().setUsage(5).setContentType(4).build());
            } else {
                Logger.w("ChannelManager", "Unable to retrieve sound for channel, sound was specified as: " + notificationChannel.getSound());
            }
        } else {
            notificationChannel.setSound(null, null);
        }
        NotificationManagerCompat.from(e.f305a).createNotificationChannel(notificationChannel);
        return null;
    }

    public static Bundle a(NotificationChannel notificationChannel) throws Resources.NotFoundException {
        String strReplace = null;
        if (notificationChannel == null || Build.VERSION.SDK_INT < 26) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("id", notificationChannel.getId());
        bundle.putString("name", notificationChannel.getName().toString());
        bundle.putBoolean("badge", notificationChannel.canShowBadge());
        bundle.putBoolean("bypassDnd", notificationChannel.canBypassDnd());
        if (notificationChannel.getDescription() != null) {
            bundle.putString("description", notificationChannel.getDescription());
        }
        if (notificationChannel.getGroup() != null) {
            bundle.putString("groupId", notificationChannel.getGroup());
        }
        bundle.putInt("importance", notificationChannel.getImportance());
        bundle.putBoolean("lights", notificationChannel.shouldShowLights());
        bundle.putBoolean("vibration", notificationChannel.shouldVibrate());
        bundle.putBoolean("blocked", notificationChannel.getImportance() == 0);
        if (notificationChannel.getSound() != null) {
            bundle.putString("soundURI", notificationChannel.getSound().toString());
            Uri sound = notificationChannel.getSound();
            if (sound != null && sound.toString().contains(UriUtil.QUALIFIED_RESOURCE_SCHEME)) {
                String lastPathSegment = sound.getLastPathSegment();
                try {
                    int iIntValue = Integer.valueOf(lastPathSegment).intValue();
                    Logger.e("ResourceUtils", "Loaded sound by resource id. New app builds will fail to play sound. Create a new channel to resolve. Issue #341");
                    if (iIntValue != 0) {
                        TypedValue typedValue = new TypedValue();
                        e.f305a.getResources().getValue(iIntValue, typedValue, true);
                        CharSequence charSequence = typedValue.string;
                        if (charSequence != null || charSequence.length() > 0) {
                            strReplace = charSequence.toString().replace("res/raw/", "");
                        }
                    }
                } catch (NumberFormatException unused) {
                    strReplace = lastPathSegment;
                }
            }
            if (strReplace != null) {
                bundle.putString("sound", strReplace);
            }
        }
        if (notificationChannel.getLightColor() != 0) {
            int lightColor = notificationChannel.getLightColor();
            String str = d.f304a.get(Integer.valueOf(lightColor));
            if (str == null) {
                str = "#" + Integer.toHexString(lightColor).substring(2);
            }
            bundle.putString("lightColor", str);
        }
        long[] vibrationPattern = notificationChannel.getVibrationPattern();
        if (vibrationPattern != null && vibrationPattern.length > 0) {
            try {
                int[] iArr = new int[vibrationPattern.length];
                for (int i = 0; i < vibrationPattern.length; i++) {
                    iArr[i] = (int) vibrationPattern[i];
                }
                bundle.putIntArray("vibrationPattern", iArr);
            } catch (Exception e) {
                Logger.e("ChannelManager", "Unable to convert Vibration Pattern to Channel Bundle", e);
            }
        }
        int lockscreenVisibility = notificationChannel.getLockscreenVisibility();
        if (lockscreenVisibility != -1000) {
            bundle.putInt(ViewHierarchyNode.JsonKeys.VISIBILITY, lockscreenVisibility);
        }
        return bundle;
    }

    public static /* synthetic */ Void d(List list) throws Exception {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Tasks.await(a((n.o.t.i.f.e.e.c) it.next()));
        }
        return null;
    }

    public static Task<Boolean> d(final String str) {
        return Tasks.call(f43a, new Callable() { // from class: app.notifee.core.a$$ExternalSyntheticLambda6
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return a.h(str);
            }
        });
    }

    public static /* synthetic */ Void c(List list) throws Exception {
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Tasks.await(a((n.o.t.i.f.e.e.b) it.next()));
        }
        return null;
    }

    public static Task<Boolean> c(final String str) {
        return Tasks.call(f43a, new Callable() { // from class: app.notifee.core.a$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return a.g(str);
            }
        });
    }

    public static Task<Void> b(final List<n.o.t.i.f.e.e.c> list) {
        return Tasks.call(f43a, new Callable() { // from class: app.notifee.core.a$$ExternalSyntheticLambda7
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return a.d(list);
            }
        });
    }

    public static Void b(n.o.t.i.f.e.e.b bVar) throws Exception {
        int i = Build.VERSION.SDK_INT;
        if (i < 26) {
            return null;
        }
        NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup((String) Objects.requireNonNull(bVar.f302a.getString("id")), (String) Objects.requireNonNull(bVar.f302a.getString("name")));
        if (i >= 28 && bVar.f302a.getString("description") != null) {
            notificationChannelGroup.setDescription(bVar.f302a.getString("description"));
        }
        NotificationManagerCompat.from(e.f305a).createNotificationChannelGroup(notificationChannelGroup);
        return null;
    }

    public static Bundle a(NotificationChannelGroup notificationChannelGroup) {
        if (notificationChannelGroup == null || Build.VERSION.SDK_INT < 26) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("id", notificationChannelGroup.getId());
        bundle.putString("name", notificationChannelGroup.getName().toString());
        List<NotificationChannel> channels = notificationChannelGroup.getChannels();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(channels.size());
        Iterator<NotificationChannel> it = channels.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        bundle.putParcelableArrayList("channels", arrayList);
        if (Build.VERSION.SDK_INT >= 28) {
            bundle.putBoolean("blocked", notificationChannelGroup.isBlocked());
            bundle.putString("description", notificationChannelGroup.getDescription());
        } else {
            bundle.putBoolean("blocked", false);
        }
        return bundle;
    }

    public static Task<List<Bundle>> b() {
        return Tasks.call(f43a, new Callable() { // from class: app.notifee.core.a$$ExternalSyntheticLambda9
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return a.d();
            }
        });
    }

    public static Task<Bundle> b(final String str) {
        return Tasks.call(f43a, new Callable() { // from class: app.notifee.core.a$$ExternalSyntheticLambda3
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return a.f(str);
            }
        });
    }
}
