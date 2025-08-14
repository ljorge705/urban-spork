package com.onfido.android.sdk.capture.config;

import com.onfido.android.sdk.capture.EnterpriseFeatures;
import kotlin.Metadata;

/* compiled from: EnterpriseConfig.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R&\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/config/EnterpriseConfig;", "", "()V", "enterpriseFeatures", "Lcom/onfido/android/sdk/capture/EnterpriseFeatures;", "getEnterpriseFeatures$annotations", "getEnterpriseFeatures", "()Lcom/onfido/android/sdk/capture/EnterpriseFeatures;", "setEnterpriseFeatures", "(Lcom/onfido/android/sdk/capture/EnterpriseFeatures;)V", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EnterpriseConfig {
    public static final EnterpriseConfig INSTANCE = new EnterpriseConfig();
    private static EnterpriseFeatures enterpriseFeatures;

    public static /* synthetic */ void getEnterpriseFeatures$annotations() {
    }

    public final EnterpriseFeatures getEnterpriseFeatures() {
        return enterpriseFeatures;
    }

    public final void setEnterpriseFeatures(EnterpriseFeatures enterpriseFeatures2) {
        enterpriseFeatures = enterpriseFeatures2;
    }

    private EnterpriseConfig() {
    }
}
