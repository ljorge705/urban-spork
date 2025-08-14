package com.clevertap.android.sdk.pushnotification;

import android.content.Context;
import android.os.Bundle;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.interfaces.ActionButtonClickHandler;
import com.clevertap.android.sdk.interfaces.NotificationHandler;
import com.clevertap.android.sdk.pushnotification.PushConstants;

/* loaded from: classes5.dex */
public class PushNotificationHandler implements ActionButtonClickHandler {
    @Override // com.clevertap.android.sdk.interfaces.ActionButtonClickHandler
    public boolean onActionButtonClick(Context context, Bundle bundle, int i) {
        return false;
    }

    private static class SingletonNotificationHandler {
        private static final PushNotificationHandler INSTANCE = new PushNotificationHandler();

        private SingletonNotificationHandler() {
        }
    }

    public static NotificationHandler getPushNotificationHandler() {
        return SingletonNotificationHandler.INSTANCE;
    }

    public static boolean isForPushTemplates(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        String string = bundle.getString("pt_id");
        return ("0".equals(string) || string == null || string.isEmpty()) ? false : true;
    }

    private boolean isForSignedCall(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        return "signedcall".equals(bundle.getString("source"));
    }

    private PushNotificationHandler() {
    }

    @Override // com.clevertap.android.sdk.interfaces.NotificationHandler
    public synchronized boolean onMessageReceived(Context context, Bundle bundle, String str) {
        bundle.putLong(Constants.OMR_INVOKE_TIME_IN_MILLIS, System.currentTimeMillis());
        CleverTapAPI globalInstance = CleverTapAPI.getGlobalInstance(context, PushNotificationUtil.getAccountIdFromNotificationBundle(bundle));
        if (!CleverTapAPI.getNotificationInfo(bundle).fromCleverTap) {
            return false;
        }
        if (globalInstance != null) {
            globalInstance.getCoreState().getConfig().log(PushConstants.LOG_TAG, str + "received notification from CleverTap: " + bundle.toString());
            if (isForPushTemplates(bundle) && CleverTapAPI.getNotificationHandler() != null) {
                CleverTapAPI.getNotificationHandler().onMessageReceived(context, bundle, str);
            } else if (isForSignedCall(bundle) && CleverTapAPI.getSignedCallNotificationHandler() != null) {
                CleverTapAPI.getSignedCallNotificationHandler().onMessageReceived(context, bundle, str);
            } else {
                globalInstance.renderPushNotificationOnCallerThread(new CoreNotificationRenderer(), context, bundle);
            }
        } else {
            Logger.d(PushConstants.LOG_TAG, str + "received notification from CleverTap: " + bundle.toString());
            Logger.d(PushConstants.LOG_TAG, str + " not renderning since cleverTapAPI is null");
        }
        return true;
    }

    @Override // com.clevertap.android.sdk.interfaces.NotificationHandler
    public boolean onNewToken(Context context, String str, String str2) {
        if (str2.equals(PushConstants.PushType.FCM.getType())) {
            CleverTapAPI.tokenRefresh(context, str, PushConstants.PushType.FCM);
            return true;
        }
        if (!str2.equals(PushConstants.PushType.HPS.getType())) {
            return true;
        }
        CleverTapAPI.tokenRefresh(context, str, PushConstants.PushType.HPS);
        return true;
    }
}
