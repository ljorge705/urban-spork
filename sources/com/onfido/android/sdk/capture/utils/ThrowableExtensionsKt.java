package com.onfido.android.sdk.capture.utils;

import com.google.mlkit.common.MlKitException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0003\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"isMLKitModelUnavailableException", "", "", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ThrowableExtensionsKt {
    public static final boolean isMLKitModelUnavailableException(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        return (th instanceof MlKitException) && ((MlKitException) th).getErrorCode() == 14;
    }
}
