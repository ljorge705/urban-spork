package com.onfido.android.sdk.capture.antifraud;

import io.sentry.protocol.DebugImage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\u0003H\u0016R\u0014\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/antifraud/LocalUuid;", "Lcom/onfido/android/sdk/capture/antifraud/ExtractedSignal;", DebugImage.JsonKeys.UUID, "", "(Ljava/lang/String;)V", "name", "getName", "()Ljava/lang/String;", "signal", "Lcom/onfido/android/sdk/capture/antifraud/Signal;", "getSignal", "()Lcom/onfido/android/sdk/capture/antifraud/Signal;", "value", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LocalUuid implements ExtractedSignal {
    private final String uuid;

    public LocalUuid(String uuid) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        this.uuid = uuid;
    }

    @Override // com.onfido.android.sdk.capture.antifraud.ExtractedSignal
    public String getName() {
        return "local_uuid";
    }

    @Override // com.onfido.android.sdk.capture.antifraud.ExtractedSignal
    public Signal getSignal() {
        return Signal.LOCAL_UUID;
    }

    @Override // com.onfido.android.sdk.capture.antifraud.ExtractedSignal
    /* renamed from: value, reason: from getter */
    public String getUuid() {
        return this.uuid;
    }
}
