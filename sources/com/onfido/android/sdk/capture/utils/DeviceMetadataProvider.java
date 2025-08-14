package com.onfido.android.sdk.capture.utils;

import com.onfido.android.sdk.capture.antifraud.Signal;
import com.onfido.android.sdk.capture.antifraud.SignalExtractor;
import com.onfido.android.sdk.capture.antifraud.SignalHolder;
import com.onfido.android.sdk.capture.antifraud.SignalSet;
import com.onfido.api.client.data.DeviceMetadata;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u000f\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/DeviceMetadataProvider;", "", "signalExtractor", "Lcom/onfido/android/sdk/capture/antifraud/SignalExtractor;", "(Lcom/onfido/android/sdk/capture/antifraud/SignalExtractor;)V", "signals", "Lcom/onfido/android/sdk/capture/antifraud/SignalHolder;", "provideDeviceMetadata", "Lcom/onfido/api/client/data/DeviceMetadata;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Singleton
/* loaded from: classes2.dex */
public final class DeviceMetadataProvider {
    private final SignalHolder signals;

    @Inject
    public DeviceMetadataProvider(SignalExtractor signalExtractor) {
        Intrinsics.checkNotNullParameter(signalExtractor, "signalExtractor");
        this.signals = signalExtractor.extract(SignalSet.LEGACY);
    }

    public final DeviceMetadata provideDeviceMetadata() {
        return new DeviceMetadata(this.signals.get(Signal.FINGERPRINT), this.signals.get(Signal.MODEL), this.signals.get(Signal.MANUFACTURER), this.signals.get(Signal.BRAND), this.signals.get(Signal.PRODUCT), this.signals.get(Signal.HARDWARE), this.signals.get(Signal.API_LEVEL), this.signals.get(Signal.ANDROID_VERSION), (String) null, (String) null, 768, (DefaultConstructorMarker) null);
    }
}
