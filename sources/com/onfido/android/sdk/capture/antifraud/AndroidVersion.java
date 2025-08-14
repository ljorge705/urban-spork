package com.onfido.android.sdk.capture.antifraud;

import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/antifraud/AndroidVersion;", "Lcom/onfido/android/sdk/capture/antifraud/BuildExtractedSignal;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AndroidVersion extends BuildExtractedSignal {
    public static final AndroidVersion INSTANCE = new AndroidVersion();

    private AndroidVersion() {
        super(Signal.ANDROID_VERSION, "androidVersionNumber", new Function0<Object>() { // from class: com.onfido.android.sdk.capture.antifraud.AndroidVersion.1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Build.VERSION.RELEASE;
            }
        }, null);
    }
}
