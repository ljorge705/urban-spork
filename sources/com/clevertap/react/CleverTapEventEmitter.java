package com.clevertap.react;

import android.util.Log;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.protocol.Message;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: CleverTapEventEmitter.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001cB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0002J\u001c\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007J\u0018\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001J\u000e\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007J\u000e\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007J\u000e\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015J\u001a\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001d"}, d2 = {"Lcom/clevertap/react/CleverTapEventEmitter;", "", "()V", "LOG_TAG", "", "eventsBuffers", "", "Lcom/clevertap/react/CleverTapEvent;", "Lcom/clevertap/react/CleverTapEventEmitter$Buffer;", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "getReactContext", "()Lcom/facebook/react/bridge/ReactContext;", "setReactContext", "(Lcom/facebook/react/bridge/ReactContext;)V", "addToBuffer", "", "event", Message.JsonKeys.PARAMS, "createBuffersMap", "enableBuffers", "", "disableBuffer", "emit", "enableBuffer", "flushBuffer", "resetAllBuffers", "sendEvent", "Buffer", "clevertap-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CleverTapEventEmitter {
    public static final CleverTapEventEmitter INSTANCE;
    private static final String LOG_TAG = "CleverTapEventEmitter";
    private static Map<CleverTapEvent, Buffer> eventsBuffers;
    private static ReactContext reactContext;

    public final ReactContext getReactContext() {
        return reactContext;
    }

    public final void setReactContext(ReactContext reactContext2) {
        reactContext = reactContext2;
    }

    private CleverTapEventEmitter() {
    }

    static {
        CleverTapEventEmitter cleverTapEventEmitter = new CleverTapEventEmitter();
        INSTANCE = cleverTapEventEmitter;
        eventsBuffers = cleverTapEventEmitter.createBuffersMap(true);
    }

    public final void enableBuffer(CleverTapEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Buffer buffer = eventsBuffers.get(event);
        if (buffer == null) {
            return;
        }
        buffer.setEnabled(true);
    }

    public final void disableBuffer(CleverTapEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Buffer buffer = eventsBuffers.get(event);
        if (buffer == null) {
            return;
        }
        buffer.setEnabled(false);
    }

    public final void resetAllBuffers(boolean enableBuffers) {
        eventsBuffers = createBuffersMap(enableBuffers);
        Log.i(LOG_TAG, "Buffers reset and enabled: " + enableBuffers);
    }

    public final void flushBuffer(CleverTapEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Buffer buffer = eventsBuffers.get(event);
        if (buffer == null) {
            return;
        }
        synchronized (buffer) {
            while (buffer.size() > 0) {
                INSTANCE.sendEvent(event, buffer.remove());
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void emit(CleverTapEvent event, Object params) {
        Intrinsics.checkNotNullParameter(event, "event");
        Buffer buffer = eventsBuffers.get(event);
        if (buffer != null && buffer.getEnabled()) {
            addToBuffer(event, params);
        } else {
            sendEvent(event, params);
        }
    }

    private final void addToBuffer(CleverTapEvent event, Object params) {
        Buffer buffer = eventsBuffers.get(event);
        if (buffer == null) {
            return;
        }
        buffer.add(params);
        Log.i(LOG_TAG, "Event " + event + " added to buffer.");
    }

    private final void sendEvent(CleverTapEvent event, Object params) {
        ReactContext reactContext2 = reactContext;
        if (reactContext2 == null) {
            Log.e(LOG_TAG, "Sending event " + event + " failed. ReactContext is null");
            return;
        }
        try {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext2.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(event.getEventName(), params);
            Log.i(LOG_TAG, "Sending event " + event);
        } catch (Throwable th) {
            Log.e(LOG_TAG, "Sending event " + event + " failed", th);
        }
    }

    private final Map<CleverTapEvent, Buffer> createBuffersMap(boolean enableBuffers) {
        CleverTapEvent[] cleverTapEventArrValues = CleverTapEvent.values();
        ArrayList arrayList = new ArrayList();
        for (CleverTapEvent cleverTapEvent : cleverTapEventArrValues) {
            if (cleverTapEvent.getBufferable()) {
                arrayList.add(cleverTapEvent);
            }
        }
        ArrayList arrayList2 = arrayList;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList2, 10)), 16));
        for (Object obj : arrayList2) {
            linkedHashMap.put(obj, new Buffer(enableBuffers));
        }
        return linkedHashMap;
    }

    /* compiled from: CleverTapEventEmitter.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001J\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001J\u0006\u0010\u0011\u001a\u00020\u0012R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R#\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcom/clevertap/react/CleverTapEventEmitter$Buffer;", "", ViewProps.ENABLED, "", "(Z)V", "getEnabled", "()Z", "setEnabled", FirebaseAnalytics.Param.ITEMS, "Ljava/util/Queue;", "getItems", "()Ljava/util/Queue;", "items$delegate", "Lkotlin/Lazy;", "add", com.clevertap.android.sdk.leanplum.Constants.IAP_ITEM_PARAM, "remove", RRWebVideoEvent.JsonKeys.SIZE, "", "clevertap-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Buffer {
        private boolean enabled;

        /* renamed from: items$delegate, reason: from kotlin metadata */
        private final Lazy items = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<LinkedList<Object>>() { // from class: com.clevertap.react.CleverTapEventEmitter$Buffer$items$2
            @Override // kotlin.jvm.functions.Function0
            public final LinkedList<Object> invoke() {
                return new LinkedList<>();
            }
        });

        public final boolean getEnabled() {
            return this.enabled;
        }

        public final void setEnabled(boolean z) {
            this.enabled = z;
        }

        public Buffer(boolean z) {
            this.enabled = z;
        }

        private final Queue<Object> getItems() {
            return (Queue) this.items.getValue();
        }

        public final boolean add(Object item) {
            return getItems().add(item);
        }

        public final Object remove() {
            return getItems().remove();
        }

        public final int size() {
            return getItems().size();
        }
    }
}
