package com.onfido.android.sdk.capture.antifraud;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001J\b\u0010\n\u001a\u00020\u0003H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0082\u0001\u0002\u000b\f¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/antifraud/ExtractedSignal;", "", "name", "", "getName", "()Ljava/lang/String;", "signal", "Lcom/onfido/android/sdk/capture/antifraud/Signal;", "getSignal", "()Lcom/onfido/android/sdk/capture/antifraud/Signal;", "value", "Lcom/onfido/android/sdk/capture/antifraud/BuildExtractedSignal;", "Lcom/onfido/android/sdk/capture/antifraud/LocalUuid;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ExtractedSignal {
    String getName();

    Signal getSignal();

    String value();
}
