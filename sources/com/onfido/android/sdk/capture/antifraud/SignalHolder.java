package com.onfido.android.sdk.capture.antifraud;

import com.clevertap.android.sdk.Constants;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÂ\u0003J\u0019\u0010\u0007\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086\u0002J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\fHÖ\u0001R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/antifraud/SignalHolder;", "", "extractedSignals", "", "Lcom/onfido/android/sdk/capture/antifraud/ExtractedSignal;", "(Ljava/util/Set;)V", "component1", Constants.COPY_TYPE, "equals", "", "other", "get", "", "signal", "Lcom/onfido/android/sdk/capture/antifraud/Signal;", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class SignalHolder {
    private final Set<ExtractedSignal> extractedSignals;

    /* JADX WARN: Multi-variable type inference failed */
    public SignalHolder(Set<? extends ExtractedSignal> extractedSignals) {
        Intrinsics.checkNotNullParameter(extractedSignals, "extractedSignals");
        this.extractedSignals = extractedSignals;
    }

    private final Set<ExtractedSignal> component1() {
        return this.extractedSignals;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SignalHolder copy$default(SignalHolder signalHolder, Set set, int i, Object obj) {
        if ((i & 1) != 0) {
            set = signalHolder.extractedSignals;
        }
        return signalHolder.copy(set);
    }

    public final SignalHolder copy(Set<? extends ExtractedSignal> extractedSignals) {
        Intrinsics.checkNotNullParameter(extractedSignals, "extractedSignals");
        return new SignalHolder(extractedSignals);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SignalHolder) && Intrinsics.areEqual(this.extractedSignals, ((SignalHolder) other).extractedSignals);
    }

    public final String get(Signal signal) {
        Object next;
        Intrinsics.checkNotNullParameter(signal, "signal");
        Iterator<T> it = this.extractedSignals.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((ExtractedSignal) next).getSignal() == signal) {
                break;
            }
        }
        ExtractedSignal extractedSignal = (ExtractedSignal) next;
        String uuid = extractedSignal != null ? extractedSignal.getUuid() : null;
        return uuid == null ? "" : uuid;
    }

    public int hashCode() {
        return this.extractedSignals.hashCode();
    }

    public String toString() {
        return "SignalHolder(extractedSignals=" + this.extractedSignals + ')';
    }
}
