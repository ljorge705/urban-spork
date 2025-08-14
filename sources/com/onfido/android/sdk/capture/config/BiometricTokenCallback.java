package com.onfido.android.sdk.capture.config;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: BiometricTokenCallback.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J$\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\tH&¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/config/BiometricTokenCallback;", "", "onTokenGenerated", "", "customerUserHash", "", "biometricToken", "onTokenRequested", "provideToken", "Lkotlin/Function1;", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface BiometricTokenCallback {
    void onTokenGenerated(String customerUserHash, String biometricToken);

    void onTokenRequested(String customerUserHash, Function1<? super String, Unit> provideToken);
}
