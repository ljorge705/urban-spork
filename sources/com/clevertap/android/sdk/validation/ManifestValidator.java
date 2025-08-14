package com.clevertap.android.sdk.validation;

import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.InAppNotificationActivity;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.ManifestInfo;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.inbox.CTInboxActivity;
import com.clevertap.android.sdk.pushnotification.CTNotificationIntentService;
import com.clevertap.android.sdk.pushnotification.CTPushNotificationReceiver;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.clevertap.android.sdk.pushnotification.PushProviders;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public final class ManifestValidator {
    private static final String ourApplicationClassName = "com.clevertap.android.sdk.Application";

    public static void validate(Context context, DeviceInfo deviceInfo, PushProviders pushProviders) {
        if (!Utils.hasPermission(context, "android.permission.INTERNET")) {
            Logger.d("Missing Permission: android.permission.INTERNET");
        }
        checkSDKVersion(deviceInfo);
        validationApplicationLifecyleCallback(context);
        checkReceiversServices(context, pushProviders);
        if (TextUtils.isEmpty(ManifestInfo.getInstance(context).getFCMSenderId())) {
            return;
        }
        Logger.i("We have noticed that your app is using a custom FCM Sender ID, this feature will be DISCONTINUED from the next version of the CleverTap Android SDK. With the next release, CleverTap Android SDK will only fetch the token using the google-services.json. Please reach out to CleverTap Support for any questions.");
    }

    private static void checkApplicationClass(Context context) {
        String str = context.getApplicationInfo().className;
        if (str == null || str.isEmpty()) {
            Logger.i("Unable to determine Application Class");
        } else if (str.equals(ourApplicationClassName)) {
            Logger.i("AndroidManifest.xml uses the CleverTap Application class, be sure you have properly added the CleverTap Account ID and Token to your AndroidManifest.xml, \nor set them programmatically in the onCreate method of your custom application class prior to calling super.onCreate()");
        } else {
            Logger.i("Application Class is " + str);
        }
    }

    private static void checkReceiversServices(Context context, PushProviders pushProviders) {
        try {
            validateReceiverInManifest((Application) context.getApplicationContext(), CTPushNotificationReceiver.class.getName());
            validateServiceInManifest((Application) context.getApplicationContext(), CTNotificationIntentService.class.getName());
            validateActivityInManifest((Application) context.getApplicationContext(), InAppNotificationActivity.class);
            validateActivityInManifest((Application) context.getApplicationContext(), CTInboxActivity.class);
            validateReceiverInManifest((Application) context.getApplicationContext(), "com.clevertap.android.geofence.CTGeofenceReceiver");
            validateReceiverInManifest((Application) context.getApplicationContext(), "com.clevertap.android.geofence.CTLocationUpdateReceiver");
            validateReceiverInManifest((Application) context.getApplicationContext(), "com.clevertap.android.geofence.CTGeofenceBootReceiver");
        } catch (Exception e) {
            Logger.v("Receiver/Service issue : " + e.toString());
        }
        ArrayList<PushConstants.PushType> availablePushTypes = pushProviders.getAvailablePushTypes();
        if (availablePushTypes == null) {
            return;
        }
        Iterator<PushConstants.PushType> it = availablePushTypes.iterator();
        while (it.hasNext()) {
            PushConstants.PushType next = it.next();
            if (next == PushConstants.PushType.FCM) {
                try {
                    validateServiceInManifest((Application) context.getApplicationContext(), "com.clevertap.android.sdk.pushnotification.fcm.FcmMessageListenerService");
                } catch (Error e2) {
                    Logger.v("FATAL : " + e2.getMessage());
                } catch (Exception e3) {
                    Logger.v("Receiver/Service issue : " + e3.toString());
                }
            } else if (next == PushConstants.PushType.HPS) {
                try {
                    validateServiceInManifest((Application) context.getApplicationContext(), "com.clevertap.android.hms.CTHmsMessageService");
                } catch (Error e4) {
                    Logger.v("FATAL : " + e4.getMessage());
                } catch (Exception e5) {
                    Logger.v("Receiver/Service issue : " + e5.toString());
                }
            }
        }
    }

    private static void checkSDKVersion(DeviceInfo deviceInfo) {
        Logger.i("SDK Version Code is " + deviceInfo.getSdkVersion());
    }

    private static void validateActivityInManifest(Application application, Class cls) throws PackageManager.NameNotFoundException {
        ActivityInfo[] activityInfoArr = application.getPackageManager().getPackageInfo(application.getPackageName(), 1).activities;
        String name = cls.getName();
        for (ActivityInfo activityInfo : activityInfoArr) {
            if (activityInfo.name.equals(name)) {
                Logger.i(name.replaceFirst("com.clevertap.android.sdk.", "") + " is present");
                return;
            }
        }
        Logger.i(name.replaceFirst("com.clevertap.android.sdk.", "") + " not present");
    }

    private static void validateReceiverInManifest(Application application, String str) throws PackageManager.NameNotFoundException {
        for (ActivityInfo activityInfo : application.getPackageManager().getPackageInfo(application.getPackageName(), 2).receivers) {
            if (activityInfo.name.equals(str)) {
                Logger.i(str.replaceFirst("com.clevertap.android.", "") + " is present");
                return;
            }
        }
        Logger.i(str.replaceFirst("com.clevertap.android.", "") + " not present");
    }

    private static void validateServiceInManifest(Application application, String str) throws PackageManager.NameNotFoundException {
        for (ServiceInfo serviceInfo : application.getPackageManager().getPackageInfo(application.getPackageName(), 4).services) {
            if (serviceInfo.name.equals(str)) {
                Logger.i(str.replaceFirst("com.clevertap.android.sdk.", "") + " is present");
                return;
            }
        }
        Logger.i(str.replaceFirst("com.clevertap.android.sdk.", "") + " not present");
    }

    private static void validationApplicationLifecyleCallback(Context context) {
        if (ActivityLifecycleCallback.registered || CleverTapAPI.isAppForeground()) {
            return;
        }
        Logger.i("Activity Lifecycle Callback not registered. Either set the android:name in your AndroidManifest.xml application tag to com.clevertap.android.sdk.Application, \n or, if you have a custom Application class, call ActivityLifecycleCallback.register(this); before super.onCreate() in your class");
        checkApplicationClass(context);
    }
}
