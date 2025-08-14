package n.o.t.i.f.e.e;

import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationManagerCompat;
import app.notifee.core.event.NotificationEvent;
import app.notifee.core.model.NotificationAndroidPressActionModel;
import app.notifee.core.model.NotificationModel;

/* loaded from: classes4.dex */
public class k {
    public static void a(Context context, Intent intent) {
        NotificationAndroidPressActionModel notificationAndroidPressActionModelFromBundle;
        CharSequence charSequence;
        if (intent.hasExtra("notification")) {
            if (e.f305a == null) {
                e.a(context.getApplicationContext());
            }
            int intExtra = intent.getIntExtra("notifee_event_type", 0);
            if (intExtra == 0) {
                return;
            }
            if (intExtra == 1) {
                Bundle bundleExtra = intent.getBundleExtra("notification");
                if (bundleExtra == null) {
                    return;
                }
                NotificationModel notificationModel = new NotificationModel(bundleExtra);
                Bundle bundleExtra2 = intent.getBundleExtra("pressAction");
                Bundle bundle = new Bundle();
                if (bundleExtra2 != null) {
                    notificationAndroidPressActionModelFromBundle = NotificationAndroidPressActionModel.fromBundle(bundleExtra2);
                    bundle.putBundle("pressAction", notificationAndroidPressActionModelFromBundle.toBundle());
                } else {
                    notificationAndroidPressActionModelFromBundle = null;
                }
                f.a(new NotificationEvent(1, notificationModel, bundle));
                if (notificationAndroidPressActionModelFromBundle == null) {
                    return;
                }
                String mainComponent = notificationAndroidPressActionModelFromBundle.getMainComponent();
                f.b(new g(notificationModel, bundle));
                if (mainComponent != null) {
                    f.b(new i(mainComponent));
                    return;
                }
                return;
            }
            if (intExtra != 2) {
                return;
            }
            Bundle bundleExtra3 = intent.getBundleExtra("notification");
            Bundle bundleExtra4 = intent.getBundleExtra("pressAction");
            if (bundleExtra3 == null || bundleExtra4 == null) {
                return;
            }
            NotificationModel notificationModel2 = new NotificationModel(bundleExtra3);
            NotificationAndroidPressActionModel notificationAndroidPressActionModelFromBundle2 = NotificationAndroidPressActionModel.fromBundle(bundleExtra4);
            Bundle bundle2 = new Bundle();
            bundle2.putBundle("pressAction", notificationAndroidPressActionModelFromBundle2.toBundle());
            Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
            if (resultsFromIntent != null && (charSequence = resultsFromIntent.getCharSequence("app.notifee.core.ReceiverService.REMOTE_INPUT_RECEIVER_KEY")) != null) {
                bundle2.putString("input", charSequence.toString());
            }
            if (notificationModel2.a().getAutoCancel().booleanValue()) {
                NotificationManagerCompat.from(context).cancel(intent.getIntExtra("notification_id", 0));
            }
            f.b(new g(notificationModel2, bundle2));
            f.a(new NotificationEvent(2, notificationModel2, bundle2));
        }
    }
}
