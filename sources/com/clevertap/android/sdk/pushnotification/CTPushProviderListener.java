package com.clevertap.android.sdk.pushnotification;

import com.clevertap.android.sdk.pushnotification.PushConstants;

/* loaded from: classes5.dex */
public interface CTPushProviderListener {
    void onNewToken(String str, PushConstants.PushType pushType);
}
