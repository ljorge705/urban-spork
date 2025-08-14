package com.clevertap.android.sdk;

import com.clevertap.android.sdk.inapp.CTInAppNotification;
import java.util.Map;

/* loaded from: classes5.dex */
public interface InAppNotificationListener {
    boolean beforeShow(Map<String, Object> map);

    void onDismissed(Map<String, Object> map, Map<String, Object> map2);

    void onShow(CTInAppNotification cTInAppNotification);
}
