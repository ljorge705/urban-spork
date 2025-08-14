package com.clevertap.android.sdk.pushnotification.fcm;

import android.os.Bundle;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.messaging.RemoteMessage;
import io.sentry.protocol.OperatingSystem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FcmNotificationBundleManipulation.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/clevertap/android/sdk/pushnotification/fcm/FcmNotificationBundleManipulation;", "Lcom/clevertap/android/sdk/pushnotification/fcm/INotificationBundleManipulation;", "Lcom/google/firebase/messaging/RemoteMessage;", "messageBundle", "Landroid/os/Bundle;", "(Landroid/os/Bundle;)V", "addPriority", "message", OperatingSystem.JsonKeys.BUILD, "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class FcmNotificationBundleManipulation implements INotificationBundleManipulation<RemoteMessage> {
    private final Bundle messageBundle;

    @Override // com.clevertap.android.sdk.pushnotification.fcm.INotificationBundleManipulation
    /* renamed from: build, reason: from getter */
    public Bundle getMessageBundle() {
        return this.messageBundle;
    }

    public FcmNotificationBundleManipulation(Bundle messageBundle) {
        Intrinsics.checkNotNullParameter(messageBundle, "messageBundle");
        this.messageBundle = messageBundle;
    }

    @Override // com.clevertap.android.sdk.pushnotification.fcm.INotificationBundleManipulation
    public INotificationBundleManipulation<RemoteMessage> addPriority(RemoteMessage message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (message.getOriginalPriority() != message.getPriority()) {
            int priority = message.getPriority();
            this.messageBundle.putString(Constants.WZRK_PN_PRT, priority != 0 ? priority != 1 ? priority != 2 ? "" : "normal" : "high" : Constants.PRIORITY_UNKNOWN);
        }
        return this;
    }
}
