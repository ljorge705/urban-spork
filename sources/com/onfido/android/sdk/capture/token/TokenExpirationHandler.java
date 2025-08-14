package com.onfido.android.sdk.capture.token;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: TokenExpirationHandler.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00030\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/token/TokenExpirationHandler;", "", "refreshToken", "", "injectNewToken", "Lkotlin/Function1;", "", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface TokenExpirationHandler {
    void refreshToken(Function1<? super String, Unit> injectNewToken);
}
