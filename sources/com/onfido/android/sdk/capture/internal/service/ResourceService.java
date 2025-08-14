package com.onfido.android.sdk.capture.internal.service;

import android.content.Context;
import com.nimbusds.jose.jwk.JWKParameterNames;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/service/ResourceService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "convertDpToPx", "", JWKParameterNames.RSA_FIRST_FACTOR_CRT_EXPONENT, "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ResourceService {
    private final Context context;

    @Inject
    public ResourceService(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final float convertDpToPx(int dp) {
        return ContextUtilsKt.dpToPixel(this.context, dp);
    }
}
