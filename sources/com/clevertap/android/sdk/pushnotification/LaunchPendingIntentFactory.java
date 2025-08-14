package com.clevertap.android.sdk.pushnotification;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Utils;
import java.util.Random;

/* loaded from: classes5.dex */
public class LaunchPendingIntentFactory {
    public static PendingIntent getLaunchPendingIntent(Bundle bundle, Context context) {
        if (Build.VERSION.SDK_INT >= 31) {
            return getActivityIntent(bundle, context);
        }
        Intent intent = new Intent(context, (Class<?>) CTPushNotificationReceiver.class);
        intent.putExtras(bundle);
        intent.removeExtra(Constants.WZRK_ACTIONS);
        return PendingIntent.getBroadcast(context, new Random().nextInt(), intent, 201326592);
    }

    public static PendingIntent getActivityIntent(Bundle bundle, Context context) {
        Intent launchIntentForPackage;
        if (bundle.containsKey(Constants.DEEP_LINK_KEY) && bundle.getString(Constants.DEEP_LINK_KEY) != null) {
            launchIntentForPackage = new Intent("android.intent.action.VIEW", Uri.parse(bundle.getString(Constants.DEEP_LINK_KEY)));
            Utils.setPackageNameFromResolveInfoList(context, launchIntentForPackage);
        } else {
            launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            if (launchIntentForPackage == null) {
                return null;
            }
        }
        launchIntentForPackage.setFlags(872415232);
        launchIntentForPackage.putExtras(bundle);
        launchIntentForPackage.removeExtra(Constants.WZRK_ACTIONS);
        return PendingIntent.getActivity(context, new Random().nextInt(), launchIntentForPackage, 201326592, Build.VERSION.SDK_INT >= 34 ? ActivityOptions.makeBasic().setPendingIntentBackgroundActivityStartMode(1).toBundle() : null);
    }
}
