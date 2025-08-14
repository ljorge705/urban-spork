package com.onfido.android.sdk.capture.errors;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EnterpriseFeatureNotEnabledException.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/errors/EnterpriseFeatureNotEnabledException;", "Ljava/lang/RuntimeException;", "feature", "", "(Ljava/lang/String;)V", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EnterpriseFeatureNotEnabledException extends RuntimeException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnterpriseFeatureNotEnabledException(String feature) {
        super("The enterprise feature " + feature + " has not been enabled for your account. Please talk to your Onfido Account Representative about getting this feature enabled.");
        Intrinsics.checkNotNullParameter(feature, "feature");
    }
}
