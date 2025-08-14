package com.clevertap.android.sdk.pushnotification.fcm;

import com.clevertap.android.sdk.pushnotification.PushConstants;

/* loaded from: classes5.dex */
public interface IFcmSdkHandler {
    PushConstants.PushType getPushType();

    boolean isAvailable();

    boolean isSupported();

    void requestToken();
}
