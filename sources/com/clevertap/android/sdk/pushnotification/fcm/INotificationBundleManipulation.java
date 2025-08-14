package com.clevertap.android.sdk.pushnotification.fcm;

import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import io.sentry.protocol.OperatingSystem;
import kotlin.Metadata;

/* compiled from: INotificationBundleManipulation.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u001b\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0004\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/clevertap/android/sdk/pushnotification/fcm/INotificationBundleManipulation;", ExifInterface.GPS_DIRECTION_TRUE, "", "addPriority", "message", "(Ljava/lang/Object;)Lcom/clevertap/android/sdk/pushnotification/fcm/INotificationBundleManipulation;", OperatingSystem.JsonKeys.BUILD, "Landroid/os/Bundle;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public interface INotificationBundleManipulation<T> {
    INotificationBundleManipulation<T> addPriority(T message);

    Bundle build();
}
