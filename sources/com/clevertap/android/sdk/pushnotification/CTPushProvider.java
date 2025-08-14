package com.clevertap.android.sdk.pushnotification;

import com.clevertap.android.sdk.pushnotification.PushConstants;

/* loaded from: classes5.dex */
public interface CTPushProvider {
    int getPlatform();

    PushConstants.PushType getPushType();

    boolean isAvailable();

    boolean isSupported();

    int minSDKSupportVersionCode();

    void requestToken();
}
