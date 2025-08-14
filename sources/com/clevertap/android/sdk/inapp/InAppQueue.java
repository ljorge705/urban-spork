package com.clevertap.android.sdk.inapp;

import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.inapp.store.preference.InAppStore;
import com.clevertap.android.sdk.inapp.store.preference.StoreRegistry;
import com.clevertap.android.sdk.utils.JsonUtilsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: InAppQueue.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0007J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0007J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0007J\u0017\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u000eH\u0002¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/clevertap/android/sdk/inapp/InAppQueue;", "", "config", "Lcom/clevertap/android/sdk/CleverTapInstanceConfig;", "storeRegistry", "Lcom/clevertap/android/sdk/inapp/store/preference/StoreRegistry;", "(Lcom/clevertap/android/sdk/CleverTapInstanceConfig;Lcom/clevertap/android/sdk/inapp/store/preference/StoreRegistry;)V", "dequeue", "Lorg/json/JSONObject;", "enqueue", "", "jsonObject", "enqueueAll", "jsonArray", "Lorg/json/JSONArray;", "getQueue", "getQueueLength", "", "insertInFront", "saveQueue", "queue", "(Lorg/json/JSONArray;)Lkotlin/Unit;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class InAppQueue {
    private final CleverTapInstanceConfig config;
    private final StoreRegistry storeRegistry;

    public InAppQueue(CleverTapInstanceConfig config, StoreRegistry storeRegistry) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(storeRegistry, "storeRegistry");
        this.config = config;
        this.storeRegistry = storeRegistry;
    }

    public final synchronized void enqueue(JSONObject jsonObject) {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        JSONArray queue = getQueue();
        queue.put(jsonObject);
        saveQueue(queue);
    }

    public final synchronized void enqueueAll(JSONArray jsonArray) {
        Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
        JSONArray queue = getQueue();
        int length = jsonArray.length();
        for (int i = 0; i < length; i++) {
            try {
                queue.put(jsonArray.getJSONObject(i));
            } catch (Exception e) {
                Logger.d(this.config.getAccountId(), "InAppController: Malformed InApp notification: " + e.getMessage());
            }
        }
        saveQueue(queue);
    }

    public final synchronized void insertInFront(JSONObject jsonObject) {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        JSONArray queue = getQueue();
        JsonUtilsKt.prepend(queue, jsonObject);
        saveQueue(queue);
    }

    public final synchronized JSONObject dequeue() {
        JSONArray queue = getQueue();
        if (queue.length() == 0) {
            return null;
        }
        Object objRemove = queue.remove(0);
        saveQueue(queue);
        return objRemove instanceof JSONObject ? (JSONObject) objRemove : null;
    }

    public final synchronized int getQueueLength() {
        return getQueue().length();
    }

    private final JSONArray getQueue() {
        InAppStore inAppStore = this.storeRegistry.getInAppStore();
        return inAppStore == null ? new JSONArray() : inAppStore.readServerSideInApps();
    }

    private final Unit saveQueue(JSONArray queue) {
        InAppStore inAppStore = this.storeRegistry.getInAppStore();
        if (inAppStore == null) {
            return null;
        }
        inAppStore.storeServerSideInApps(queue);
        return Unit.INSTANCE;
    }
}
