package app.notifee.core.model;

import android.graphics.Color;
import android.os.Bundle;
import app.notifee.core.Logger;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import io.sentry.protocol.SentryThread;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import n.o.t.i.f.e.e.l;
import n.o.t.i.f.e.e.n;

/* loaded from: classes5.dex */
public class NotificationAndroidModel {
    private static final String TAG = "NotificationAndroidModel";
    private Bundle mNotificationAndroidBundle;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public int f53a;
        public int b;
        public boolean c;

        public a(int i, int i2, boolean z) {
            this.f53a = i;
            this.b = i2;
            this.c = z;
        }
    }

    private NotificationAndroidModel(Bundle bundle) {
        this.mNotificationAndroidBundle = bundle;
    }

    public static NotificationAndroidModel fromBundle(Bundle bundle) {
        return new NotificationAndroidModel(bundle);
    }

    public ArrayList<NotificationAndroidActionModel> getActions() {
        if (!this.mNotificationAndroidBundle.containsKey(Constants.KEY_ACTIONS)) {
            return null;
        }
        ArrayList arrayList = (ArrayList) Objects.requireNonNull(this.mNotificationAndroidBundle.getParcelableArrayList(Constants.KEY_ACTIONS));
        ArrayList<NotificationAndroidActionModel> arrayList2 = new ArrayList<>(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(NotificationAndroidActionModel.fromBundle((Bundle) it.next()));
        }
        return arrayList2;
    }

    public Boolean getAsForegroundService() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("asForegroundService", false));
    }

    public Boolean getAutoCancel() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("autoCancel", false));
    }

    public Integer getBadgeIconType() {
        if (this.mNotificationAndroidBundle.containsKey("badgeIconType")) {
            return Integer.valueOf(l.a(this.mNotificationAndroidBundle.get("badgeIconType")));
        }
        return 2;
    }

    public String getCategory() {
        return this.mNotificationAndroidBundle.getString("category");
    }

    public String getChannelId() {
        return this.mNotificationAndroidBundle.containsKey("channelId") ? (String) Objects.requireNonNull(this.mNotificationAndroidBundle.getString("channelId")) : "";
    }

    public Boolean getChronometerCountDown() {
        if (!this.mNotificationAndroidBundle.containsKey("chronometerDirection")) {
            return Boolean.FALSE;
        }
        String string = this.mNotificationAndroidBundle.getString("chronometerDirection");
        return Boolean.valueOf(string != null && string.equals("down"));
    }

    public Integer getColor() {
        if (this.mNotificationAndroidBundle.containsKey("color")) {
            return Integer.valueOf(Color.parseColor(this.mNotificationAndroidBundle.getString("color")));
        }
        return null;
    }

    public Boolean getColorized() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("colorized", false));
    }

    public Integer getDefaults(Boolean bool) {
        Integer numValueOf;
        if (this.mNotificationAndroidBundle.containsKey(RemoteConfigComponent.DEFAULTS_FILE_NAME)) {
            Iterator it = ((ArrayList) Objects.requireNonNull(this.mNotificationAndroidBundle.getIntegerArrayList(RemoteConfigComponent.DEFAULTS_FILE_NAME))).iterator();
            numValueOf = null;
            while (it.hasNext()) {
                Integer num = (Integer) it.next();
                numValueOf = numValueOf == null ? num : Integer.valueOf(numValueOf.intValue() | num.intValue());
            }
        } else {
            numValueOf = -1;
        }
        if (bool.booleanValue()) {
            numValueOf = Integer.valueOf(numValueOf.intValue() & (-2));
        }
        if (!this.mNotificationAndroidBundle.containsKey("vibrationPattern")) {
            numValueOf = Integer.valueOf(numValueOf.intValue() & (-3));
        }
        return this.mNotificationAndroidBundle.containsKey("lights") ? Integer.valueOf(numValueOf.intValue() & (-5)) : numValueOf;
    }

    public int[] getFlags() {
        if (!this.mNotificationAndroidBundle.containsKey("flags")) {
            return null;
        }
        ArrayList arrayList = (ArrayList) Objects.requireNonNull(this.mNotificationAndroidBundle.getParcelableArrayList("flags"));
        int[] iArr = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            iArr[i] = l.a(arrayList.get(i));
        }
        return iArr;
    }

    public NotificationAndroidPressActionModel getFullScreenAction() {
        if (hasFullScreenAction().booleanValue()) {
            return NotificationAndroidPressActionModel.fromBundle(this.mNotificationAndroidBundle.getBundle("fullScreenAction"));
        }
        return null;
    }

    public String getGroup() {
        return this.mNotificationAndroidBundle.getString("groupId");
    }

    public int getGroupAlertBehaviour() {
        if (this.mNotificationAndroidBundle.containsKey("groupAlertBehavior")) {
            return l.a(this.mNotificationAndroidBundle.get("groupAlertBehavior"));
        }
        return 0;
    }

    public Boolean getGroupSummary() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("groupSummary", false));
    }

    public CharSequence[] getInputHistory() {
        if (!this.mNotificationAndroidBundle.containsKey("inputHistory")) {
            return null;
        }
        ArrayList<String> stringArrayList = this.mNotificationAndroidBundle.getStringArrayList("inputHistory");
        return (CharSequence[]) ((ArrayList) Objects.requireNonNull(stringArrayList)).toArray(new CharSequence[stringArrayList.size()]);
    }

    public String getLargeIcon() {
        if (hasLargeIcon().booleanValue()) {
            return (String) Objects.requireNonNull(this.mNotificationAndroidBundle.getString("largeIcon"));
        }
        return null;
    }

    public Boolean getLightUpScreen() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("lightUpScreen", false));
    }

    public ArrayList<Integer> getLights() {
        if (this.mNotificationAndroidBundle.containsKey("lights")) {
            try {
                ArrayList arrayList = (ArrayList) Objects.requireNonNull(this.mNotificationAndroidBundle.getParcelableArrayList("lights"));
                String str = (String) arrayList.get(0);
                ArrayList<Integer> arrayList2 = new ArrayList<>(3);
                arrayList2.add(Integer.valueOf(Color.parseColor(str)));
                arrayList2.add((Integer) arrayList.get(1));
                arrayList2.add((Integer) arrayList.get(2));
                return arrayList2;
            } catch (Exception unused) {
                Logger.e(TAG, "getLights -> Failed to parse lights");
            }
        }
        return null;
    }

    public Boolean getLocalOnly() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("localOnly", false));
    }

    public Boolean getLoopSound() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("loopSound", false));
    }

    public Integer getNumber() {
        if (this.mNotificationAndroidBundle.containsKey("badgeCount")) {
            return Integer.valueOf(l.a(this.mNotificationAndroidBundle.get("badgeCount")));
        }
        return null;
    }

    public Boolean getOngoing() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("ongoing", false));
    }

    public Boolean getOnlyAlertOnce() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("onlyAlertOnce", false));
    }

    public Bundle getPressAction() {
        return this.mNotificationAndroidBundle.getBundle("pressAction");
    }

    public int getPriority() {
        if (!this.mNotificationAndroidBundle.containsKey("importance")) {
            return 0;
        }
        int iA = l.a(this.mNotificationAndroidBundle.get("importance"));
        if (iA == 0) {
            return -2;
        }
        if (iA != 1) {
            return iA != 4 ? 0 : 1;
        }
        return -1;
    }

    public a getProgress() {
        if (!this.mNotificationAndroidBundle.containsKey("progress")) {
            return null;
        }
        Bundle bundle = (Bundle) Objects.requireNonNull(this.mNotificationAndroidBundle.getBundle("progress"));
        return new a(l.a(bundle.get("max")), l.a(bundle.get(SentryThread.JsonKeys.CURRENT)), bundle.getBoolean("indeterminate", false));
    }

    public String getShortcutId() {
        return this.mNotificationAndroidBundle.getString("shortcutId");
    }

    public Boolean getShowChronometer() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("showChronometer", false));
    }

    public Boolean getShowTimestamp() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("showTimestamp", false));
    }

    public Integer getSmallIcon() {
        if (!this.mNotificationAndroidBundle.containsKey("smallIcon")) {
            return null;
        }
        String string = this.mNotificationAndroidBundle.getString("smallIcon");
        int iA = n.a(string, "mipmap");
        if (iA == 0) {
            iA = n.a(string, "drawable");
        }
        if (iA != 0) {
            return Integer.valueOf(iA);
        }
        Logger.d(TAG, String.format("Notification small icon '%s' could not be found", string));
        return null;
    }

    public Integer getSmallIconLevel() {
        if (this.mNotificationAndroidBundle.containsKey("smallIconLevel")) {
            return Integer.valueOf(this.mNotificationAndroidBundle.getInt("smallIconLevel"));
        }
        return null;
    }

    public String getSortKey() {
        return this.mNotificationAndroidBundle.getString("sortKey");
    }

    public String getSound() {
        if (this.mNotificationAndroidBundle.containsKey("sound")) {
            return this.mNotificationAndroidBundle.getString("sound");
        }
        return null;
    }

    public NotificationAndroidStyleModel getStyle() {
        if (hasStyle().booleanValue()) {
            return NotificationAndroidStyleModel.fromBundle(this.mNotificationAndroidBundle.getBundle("style"));
        }
        return null;
    }

    public String getTag() {
        return this.mNotificationAndroidBundle.getString("tag");
    }

    public String getTicker() {
        return this.mNotificationAndroidBundle.getString("ticker");
    }

    public Long getTimeoutAfter() {
        if (this.mNotificationAndroidBundle.containsKey("timeoutAfter")) {
            return Long.valueOf(l.b(this.mNotificationAndroidBundle.get("timeoutAfter")));
        }
        return null;
    }

    public long getTimestamp() {
        if (this.mNotificationAndroidBundle.containsKey("timestamp")) {
            return l.b(this.mNotificationAndroidBundle.get("timestamp"));
        }
        return -1L;
    }

    public long[] getVibrationPattern() {
        if (!this.mNotificationAndroidBundle.containsKey("vibrationPattern")) {
            return new long[0];
        }
        ArrayList arrayList = (ArrayList) Objects.requireNonNull(this.mNotificationAndroidBundle.getParcelableArrayList("vibrationPattern"));
        long[] jArr = new long[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            jArr[i] = ((Integer) arrayList.get(i)).longValue();
        }
        return jArr;
    }

    public int getVisibility() {
        if (this.mNotificationAndroidBundle.containsKey(ViewHierarchyNode.JsonKeys.VISIBILITY)) {
            return l.a(this.mNotificationAndroidBundle.get(ViewHierarchyNode.JsonKeys.VISIBILITY));
        }
        return 0;
    }

    public Boolean hasFullScreenAction() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.containsKey("fullScreenAction"));
    }

    public Boolean hasLargeIcon() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.containsKey("largeIcon"));
    }

    public Boolean hasStyle() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.containsKey("style"));
    }

    public Boolean getCircularLargeIcon() {
        return (Boolean) Objects.requireNonNull(Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("circularLargeIcon", false)));
    }
}
