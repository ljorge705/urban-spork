package com.onfido.android.sdk.capture.errors;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EnterpriseFeaturesConflictingException.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/errors/EnterpriseFeaturesConflictingException;", "Ljava/lang/RuntimeException;", "feature1", "", "feature2", "(Ljava/lang/String;Ljava/lang/String;)V", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EnterpriseFeaturesConflictingException extends RuntimeException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnterpriseFeaturesConflictingException(String feature1, String feature2) {
        super("Both " + feature1 + " and " + feature2 + " are enabled in your enterprise features configuration, but are mutually exclusive with one another. Please remove one from your OnfidoConfig configuration.");
        Intrinsics.checkNotNullParameter(feature1, "feature1");
        Intrinsics.checkNotNullParameter(feature2, "feature2");
    }
}
