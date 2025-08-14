package com.clevertap.android.sdk.inapp;

import android.content.Context;
import android.os.Bundle;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;

/* compiled from: InAppListener.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J6\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J$\u0010\r\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\u001a\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H&J\u001a\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H&¨\u0006\u0014"}, d2 = {"Lcom/clevertap/android/sdk/inapp/InAppListener;", "", "inAppNotificationActionTriggered", "Landroid/os/Bundle;", "inAppNotification", "Lcom/clevertap/android/sdk/inapp/CTInAppNotification;", Constants.KEY_ACTION, "Lcom/clevertap/android/sdk/inapp/CTInAppAction;", "callToAction", "", "additionalData", "activityContext", "Landroid/content/Context;", "inAppNotificationDidClick", "button", "Lcom/clevertap/android/sdk/inapp/CTInAppNotificationButton;", "inAppNotificationDidDismiss", "", "formData", "inAppNotificationDidShow", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public interface InAppListener {
    Bundle inAppNotificationActionTriggered(CTInAppNotification inAppNotification, CTInAppAction action, String callToAction, Bundle additionalData, Context activityContext);

    Bundle inAppNotificationDidClick(CTInAppNotification inAppNotification, CTInAppNotificationButton button, Context activityContext);

    void inAppNotificationDidDismiss(CTInAppNotification inAppNotification, Bundle formData);

    void inAppNotificationDidShow(CTInAppNotification inAppNotification, Bundle formData);
}
