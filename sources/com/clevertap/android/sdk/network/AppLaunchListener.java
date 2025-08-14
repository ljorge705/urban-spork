package com.clevertap.android.sdk.network;

import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;

/* compiled from: BatchListeners.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\b\u0010\t\u001a\u00020\u0006H\u0002J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0014\u0010\u000f\u001a\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/clevertap/android/sdk/network/AppLaunchListener;", "Lcom/clevertap/android/sdk/network/BatchListener;", "()V", "listeners", "", "Lkotlin/Function0;", "", "addListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "onAppLaunchedFound", "onBatchSent", "batch", "Lorg/json/JSONArray;", FirebaseAnalytics.Param.SUCCESS, "", "removeListener", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class AppLaunchListener implements BatchListener {
    private final List<Function0<Unit>> listeners = new ArrayList();

    public final void addListener(Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.add(listener);
    }

    public final void removeListener(Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.remove(listener);
    }

    @Override // com.clevertap.android.sdk.network.BatchListener
    public void onBatchSent(JSONArray batch, boolean success) {
        Intrinsics.checkNotNullParameter(batch, "batch");
        int length = batch.length();
        for (int i = 0; i < length; i++) {
            if (Intrinsics.areEqual(batch.getJSONObject(i).optString(Constants.KEY_EVT_NAME), Constants.APP_LAUNCHED_EVENT) && success) {
                onAppLaunchedFound();
                return;
            }
        }
    }

    private final void onAppLaunchedFound() {
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
    }
}
