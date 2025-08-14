package com.clevertap.android.sdk.network;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;

/* compiled from: BatchListeners.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/clevertap/android/sdk/network/CompositeBatchListener;", "Lcom/clevertap/android/sdk/network/BatchListener;", "()V", "listeners", "", "addListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "onBatchSent", "batch", "Lorg/json/JSONArray;", FirebaseAnalytics.Param.SUCCESS, "", "removeListener", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CompositeBatchListener implements BatchListener {
    private final List<BatchListener> listeners = new ArrayList();

    public final void addListener(BatchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.add(listener);
    }

    public final void removeListener(BatchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.remove(listener);
    }

    @Override // com.clevertap.android.sdk.network.BatchListener
    public void onBatchSent(JSONArray batch, boolean success) {
        Intrinsics.checkNotNullParameter(batch, "batch");
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((BatchListener) it.next()).onBatchSent(batch, success);
        }
    }
}
