package com.krealo.tenpo;

import com.clevertap.android.sdk.pushnotification.fcm.CTFcmMessageHandler;
import com.clevertap.android.sdk.pushnotification.fcm.IFcmMessageHandler;
import com.google.firebase.messaging.RemoteMessage;
import io.invertase.firebase.messaging.ReactNativeFirebaseMessagingService;

/* loaded from: classes2.dex */
public class TenpoFirebaseMessagingService extends ReactNativeFirebaseMessagingService {
    private IFcmMessageHandler mHandler = new CTFcmMessageHandler();

    @Override // io.invertase.firebase.messaging.ReactNativeFirebaseMessagingService, com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        this.mHandler.createNotification(getApplicationContext(), remoteMessage);
    }

    @Override // io.invertase.firebase.messaging.ReactNativeFirebaseMessagingService, com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String str) {
        super.onNewToken(str);
        this.mHandler.onNewToken(getApplicationContext(), str);
    }
}
