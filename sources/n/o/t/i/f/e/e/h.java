package n.o.t.i.f.e.e;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import app.notifee.core.Logger;
import com.facebook.hermes.intl.Constants;

/* loaded from: classes4.dex */
public class h {
    public static void a(Activity activity, final Intent intent) {
        if (activity == null) {
            Logger.w("IntentUtils", "Activity or intent is null when calling startActivityOnUiThread()");
            return;
        }
        final Context context = e.f305a;
        if (context == null) {
            Logger.w("IntentUtils", "Unable to get application context when calling startActivityOnUiThread()");
        }
        activity.runOnUiThread(new Runnable() { // from class: n.o.t.i.f.e.e.h$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                h.b(context, intent);
            }
        });
    }

    public static /* synthetic */ void b(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            Logger.e("IntentUtils", "An error occurred whilst trying to start activity on Ui Thread", e);
        }
    }

    public static boolean a(Context context, Intent intent) {
        if (context != null && intent != null) {
            try {
                return context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
            } catch (Exception e) {
                Logger.e("IntentUtils", "An error occurred whilst trying to check if intent is available on device", e);
            }
        }
        return false;
    }

    public static String a(Intent intent) {
        if (intent == null) {
            return null;
        }
        try {
            String className = intent.getComponent().getClassName();
            int iLastIndexOf = className.lastIndexOf(".");
            if (iLastIndexOf != -1) {
                return className.substring(iLastIndexOf + 1);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static Class<?> a(String str) throws ClassNotFoundException {
        String className;
        Class<?> cls;
        if (str == null || str.equals(Constants.COLLATION_DEFAULT)) {
            Intent launchIntentForPackage = e.f305a.getPackageManager().getLaunchIntentForPackage(e.f305a.getPackageName());
            className = (launchIntentForPackage == null || launchIntentForPackage.getComponent() == null) ? null : launchIntentForPackage.getComponent().getClassName();
        } else {
            className = str;
        }
        if (className == null) {
            Logger.e("ReceiverService", "Launch Activity for notification could not be found.");
            return null;
        }
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls != null) {
            return cls;
        }
        Logger.e("ReceiverService", String.format("Launch Activity for notification does not exist ('%s').", str));
        return null;
    }
}
