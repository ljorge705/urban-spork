package com.clevertap.android.sdk.pushnotification.fcm;

import android.content.Context;
import android.text.TextUtils;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.ManifestInfo;
import com.clevertap.android.sdk.pushnotification.CTPushProviderListener;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.clevertap.android.sdk.utils.PackageUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

/* loaded from: classes5.dex */
public class FcmSdkHandlerImpl implements IFcmSdkHandler {
    private final CleverTapInstanceConfig config;
    private final Context context;
    private final CTPushProviderListener listener;
    private ManifestInfo manifestInfo;

    void setManifestInfo(ManifestInfo manifestInfo) {
        this.manifestInfo = manifestInfo;
    }

    public FcmSdkHandlerImpl(CTPushProviderListener cTPushProviderListener, Context context, CleverTapInstanceConfig cleverTapInstanceConfig) {
        this.context = context;
        this.config = cleverTapInstanceConfig;
        this.listener = cTPushProviderListener;
        this.manifestInfo = ManifestInfo.getInstance(context);
    }

    @Override // com.clevertap.android.sdk.pushnotification.fcm.IFcmSdkHandler
    public PushConstants.PushType getPushType() {
        return PushConstants.PushType.FCM;
    }

    @Override // com.clevertap.android.sdk.pushnotification.fcm.IFcmSdkHandler
    public boolean isAvailable() {
        try {
            if (!PackageUtils.isGooglePlayServicesAvailable(this.context)) {
                this.config.log(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "Google Play services is currently unavailable.");
                return false;
            }
            if (!TextUtils.isEmpty(getSenderId())) {
                return true;
            }
            this.config.log(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "The FCM sender ID is not set. Unable to register for FCM.");
            return false;
        } catch (Throwable th) {
            this.config.log(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "Unable to register with FCM.", th);
            return false;
        }
    }

    @Override // com.clevertap.android.sdk.pushnotification.fcm.IFcmSdkHandler
    public boolean isSupported() {
        return PackageUtils.isGooglePlayStoreAvailable(this.context);
    }

    @Override // com.clevertap.android.sdk.pushnotification.fcm.IFcmSdkHandler
    public void requestToken() {
        try {
            this.config.log(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "Requesting FCM token using googleservices.json");
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() { // from class: com.clevertap.android.sdk.pushnotification.fcm.FcmSdkHandlerImpl.1
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<String> task) {
                    if (!task.isSuccessful()) {
                        FcmSdkHandlerImpl.this.config.log(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "FCM token using googleservices.json failed", task.getException());
                        FcmSdkHandlerImpl.this.listener.onNewToken(null, FcmSdkHandlerImpl.this.getPushType());
                    } else {
                        String result = task.getResult() != null ? task.getResult() : null;
                        FcmSdkHandlerImpl.this.config.log(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "FCM token using googleservices.json - " + result);
                        FcmSdkHandlerImpl.this.listener.onNewToken(result, FcmSdkHandlerImpl.this.getPushType());
                    }
                }
            });
        } catch (Throwable th) {
            this.config.log(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "Error requesting FCM token", th);
            this.listener.onNewToken(null, getPushType());
        }
    }

    String getSenderId() {
        return FirebaseApp.getInstance().getOptions().getGcmSenderId();
    }
}
