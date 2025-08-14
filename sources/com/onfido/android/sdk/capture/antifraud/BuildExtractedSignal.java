package com.onfido.android.sdk.capture.antifraud;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001B'\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010\tJ\b\u0010\u0010\u001a\u00020\u0005H\u0016R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0001\u0013\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#¨\u0006$"}, d2 = {"Lcom/onfido/android/sdk/capture/antifraud/BuildExtractedSignal;", "Lcom/onfido/android/sdk/capture/antifraud/ExtractedSignal;", "sourceSignal", "Lcom/onfido/android/sdk/capture/antifraud/Signal;", "signalName", "", "computeValue", "Lkotlin/Function0;", "", "(Lcom/onfido/android/sdk/capture/antifraud/Signal;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "name", "getName", "()Ljava/lang/String;", "signal", "getSignal", "()Lcom/onfido/android/sdk/capture/antifraud/Signal;", "value", "Lcom/onfido/android/sdk/capture/antifraud/AndroidVersion;", "Lcom/onfido/android/sdk/capture/antifraud/ApiLevel;", "Lcom/onfido/android/sdk/capture/antifraud/Board;", "Lcom/onfido/android/sdk/capture/antifraud/Bootloader;", "Lcom/onfido/android/sdk/capture/antifraud/Brand;", "Lcom/onfido/android/sdk/capture/antifraud/BuildId;", "Lcom/onfido/android/sdk/capture/antifraud/Device;", "Lcom/onfido/android/sdk/capture/antifraud/Display;", "Lcom/onfido/android/sdk/capture/antifraud/Fingerprint;", "Lcom/onfido/android/sdk/capture/antifraud/Hardware;", "Lcom/onfido/android/sdk/capture/antifraud/Manufacturer;", "Lcom/onfido/android/sdk/capture/antifraud/Model;", "Lcom/onfido/android/sdk/capture/antifraud/OdmSku;", "Lcom/onfido/android/sdk/capture/antifraud/Product;", "Lcom/onfido/android/sdk/capture/antifraud/Serial;", "Lcom/onfido/android/sdk/capture/antifraud/Sku;", "Lcom/onfido/android/sdk/capture/antifraud/SocManufacturer;", "Lcom/onfido/android/sdk/capture/antifraud/SocModel;", "Lcom/onfido/android/sdk/capture/antifraud/SupportedAbis;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class BuildExtractedSignal implements ExtractedSignal {
    private final Function0<Object> computeValue;
    private final String signalName;
    private final Signal sourceSignal;

    private BuildExtractedSignal(Signal signal, String str, Function0<? extends Object> function0) {
        this.sourceSignal = signal;
        this.signalName = str;
        this.computeValue = function0;
    }

    @Override // com.onfido.android.sdk.capture.antifraud.ExtractedSignal
    /* renamed from: getName, reason: from getter */
    public String getSignalName() {
        return this.signalName;
    }

    @Override // com.onfido.android.sdk.capture.antifraud.ExtractedSignal
    /* renamed from: getSignal, reason: from getter */
    public Signal getSourceSignal() {
        return this.sourceSignal;
    }

    @Override // com.onfido.android.sdk.capture.antifraud.ExtractedSignal
    /* renamed from: value */
    public String getUuid() {
        Object objInvoke = this.computeValue.invoke();
        String string = objInvoke != null ? objInvoke.toString() : null;
        return string == null ? "" : string;
    }

    public /* synthetic */ BuildExtractedSignal(Signal signal, String str, Function0 function0, DefaultConstructorMarker defaultConstructorMarker) {
        this(signal, str, function0);
    }
}
