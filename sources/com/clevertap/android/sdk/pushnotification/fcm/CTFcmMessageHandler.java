package com.clevertap.android.sdk.pushnotification.fcm;

import android.content.Context;
import android.os.Bundle;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.interfaces.INotificationParser;
import com.clevertap.android.sdk.interfaces.IPushAmpHandler;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.clevertap.android.sdk.pushnotification.PushNotificationHandler;
import com.google.firebase.messaging.RemoteMessage;

/* loaded from: classes5.dex */
public class CTFcmMessageHandler implements IFcmMessageHandler, IPushAmpHandler<RemoteMessage> {
    private final INotificationParser<RemoteMessage> mParser;

    public CTFcmMessageHandler() {
        this(new FcmNotificationParser());
    }

    CTFcmMessageHandler(INotificationParser<RemoteMessage> iNotificationParser) {
        this.mParser = iNotificationParser;
    }

    @Override // com.clevertap.android.sdk.pushnotification.fcm.IFcmMessageHandler
    public boolean createNotification(Context context, RemoteMessage remoteMessage) {
        Bundle bundle = this.mParser.toBundle(remoteMessage);
        if (bundle == null) {
            return false;
        }
        return PushNotificationHandler.getPushNotificationHandler().onMessageReceived(context, new FcmNotificationBundleManipulation(bundle).addPriority(remoteMessage).getMessageBundle(), PushConstants.PushType.FCM.toString());
    }

    @Override // com.clevertap.android.sdk.pushnotification.fcm.IFcmMessageHandler
    public boolean onNewToken(Context context, String str) {
        try {
            PushNotificationHandler.getPushNotificationHandler().onNewToken(context, str, PushConstants.PushType.FCM.getType());
            Logger.d(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "New token received from FCM - " + str);
            return true;
        } catch (Throwable th) {
            Logger.d(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "Error onNewToken", th);
            return false;
        }
    }

    @Override // com.clevertap.android.sdk.interfaces.IPushAmpHandler
    public void processPushAmp(Context context, RemoteMessage remoteMessage) {
        Bundle bundle = this.mParser.toBundle(remoteMessage);
        if (bundle != null) {
            CleverTapAPI.processPushNotification(context, bundle);
        }
    }
}
