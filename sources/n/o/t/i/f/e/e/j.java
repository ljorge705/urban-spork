package n.o.t.i.f.e.e;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import app.notifee.core.Logger;
import app.notifee.core.NotificationReceiverActivity;
import app.notifee.core.model.NotificationAndroidPressActionModel;
import com.facebook.hermes.intl.Constants;
import java.util.UUID;

/* loaded from: classes4.dex */
public class j {
    public static PendingIntent a(int i, Bundle bundle, int i2, String[] strArr, Bundle... bundleArr) {
        Intent[] intentArr;
        NotificationAndroidPressActionModel notificationAndroidPressActionModelFromBundle;
        Context context = e.f305a;
        Intent intent = null;
        if (bundle != null && (notificationAndroidPressActionModelFromBundle = NotificationAndroidPressActionModel.fromBundle(bundle)) != null && (notificationAndroidPressActionModelFromBundle.getLaunchActivity() != null || notificationAndroidPressActionModelFromBundle.getMainComponent() != null)) {
            try {
                Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                String launchActivity = notificationAndroidPressActionModelFromBundle != null ? notificationAndroidPressActionModelFromBundle.getLaunchActivity() : null;
                Boolean boolValueOf = Boolean.valueOf(launchIntentForPackage == null);
                if (launchIntentForPackage != null) {
                    boolValueOf = Boolean.valueOf((launchActivity == Constants.COLLATION_DEFAULT || launchIntentForPackage.getComponent().getClassName() == launchActivity) ? false : true);
                }
                if (launchActivity != null && boolValueOf.booleanValue()) {
                    Intent intent2 = new Intent(context, h.a(launchActivity));
                    intent2.setPackage(null);
                    intent2.setFlags(270532608);
                    launchIntentForPackage = intent2;
                }
                if (notificationAndroidPressActionModelFromBundle.getLaunchActivityFlags() != -1) {
                    launchIntentForPackage.setFlags(notificationAndroidPressActionModelFromBundle.getLaunchActivityFlags());
                }
                if (notificationAndroidPressActionModelFromBundle.getMainComponent() != null) {
                    launchIntentForPackage.putExtra("mainComponent", notificationAndroidPressActionModelFromBundle.getMainComponent());
                    f.b(new i(notificationAndroidPressActionModelFromBundle.getMainComponent()));
                }
                intent = launchIntentForPackage;
            } catch (Exception e) {
                Logger.e("NotificationPendingIntent", "Failed to create LaunchActivityIntent for notification " + i, e);
            }
        }
        Intent intent3 = new Intent(context, (Class<?>) NotificationReceiverActivity.class);
        a(intent, i2, i, strArr, bundleArr);
        a(intent3, i2, i, strArr, bundleArr);
        int iHashCode = UUID.randomUUID().hashCode();
        if (intent != null) {
            intent3.setFlags(603979776);
            intentArr = new Intent[]{intent, intent3};
        } else {
            intent3.setFlags(403177472);
            intentArr = new Intent[]{intent3};
        }
        return PendingIntent.getActivities(context, iHashCode, intentArr, 167772160);
    }

    public static void a(Intent intent, int i, int i2, String[] strArr, Bundle... bundleArr) {
        if (intent == null) {
            return;
        }
        intent.putExtra("notifee_event_type", i);
        intent.putExtra("notification_id", i2);
        for (int i3 = 0; i3 < strArr.length; i3++) {
            String str = strArr[i3];
            if (i3 <= bundleArr.length - 1) {
                intent.putExtra(str, bundleArr[i3]);
            } else {
                intent.putExtra(str, (String) null);
            }
        }
    }
}
