package io.invertase.firebase.common;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import io.invertase.firebase.interfaces.NativeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class ReactNativeFirebaseEventEmitter {
    private static ReactNativeFirebaseEventEmitter sharedInstance = new ReactNativeFirebaseEventEmitter();
    private int jsListenerCount;
    private ReactContext reactContext;
    private final List<NativeEvent> queuedEvents = new ArrayList();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final HashMap<String, Integer> jsListeners = new HashMap<>();
    private Boolean jsReady = false;

    public static ReactNativeFirebaseEventEmitter getSharedInstance() {
        return sharedInstance;
    }

    public void attachReactContext(final ReactContext reactContext) {
        this.handler.post(new Runnable() { // from class: io.invertase.firebase.common.ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$attachReactContext$0(reactContext);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachReactContext$0(ReactContext reactContext) {
        this.reactContext = reactContext;
        sendQueuedEvents();
    }

    public void notifyJsReady(final Boolean bool) {
        this.handler.post(new Runnable() { // from class: io.invertase.firebase.common.ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$notifyJsReady$1(bool);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyJsReady$1(Boolean bool) {
        this.jsReady = bool;
        sendQueuedEvents();
    }

    public void sendEvent(final NativeEvent nativeEvent) {
        this.handler.post(new Runnable() { // from class: io.invertase.firebase.common.ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$sendEvent$2(nativeEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendEvent$2(NativeEvent nativeEvent) {
        synchronized (this.jsListeners) {
            if (!this.jsListeners.containsKey(nativeEvent.getEventName()) || !emit(nativeEvent)) {
                this.queuedEvents.add(nativeEvent);
            }
        }
    }

    public void addListener(String str) {
        synchronized (this.jsListeners) {
            this.jsListenerCount++;
            if (!this.jsListeners.containsKey(str)) {
                this.jsListeners.put(str, 1);
            } else {
                this.jsListeners.put(str, Integer.valueOf(this.jsListeners.get(str).intValue() + 1));
            }
        }
        this.handler.post(new Runnable() { // from class: io.invertase.firebase.common.ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.sendQueuedEvents();
            }
        });
    }

    public void removeListener(String str, Boolean bool) {
        synchronized (this.jsListeners) {
            if (this.jsListeners.containsKey(str)) {
                int iIntValue = this.jsListeners.get(str).intValue();
                if (iIntValue <= 1 || bool.booleanValue()) {
                    this.jsListeners.remove(str);
                } else {
                    this.jsListeners.put(str, Integer.valueOf(iIntValue - 1));
                }
                int i = this.jsListenerCount;
                if (!bool.booleanValue()) {
                    iIntValue = 1;
                }
                this.jsListenerCount = i - iIntValue;
            }
        }
    }

    public WritableMap getListenersMap() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        writableMapCreateMap.putInt("listeners", this.jsListenerCount);
        writableMapCreateMap.putInt("queued", this.queuedEvents.size());
        synchronized (this.jsListeners) {
            for (Map.Entry<String, Integer> entry : this.jsListeners.entrySet()) {
                writableMapCreateMap2.putInt(entry.getKey(), entry.getValue().intValue());
            }
        }
        writableMapCreateMap.putMap("events", writableMapCreateMap2);
        return writableMapCreateMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendQueuedEvents() {
        synchronized (this.jsListeners) {
            Iterator it = new ArrayList(this.queuedEvents).iterator();
            while (it.hasNext()) {
                NativeEvent nativeEvent = (NativeEvent) it.next();
                if (this.jsListeners.containsKey(nativeEvent.getEventName())) {
                    this.queuedEvents.remove(nativeEvent);
                    sendEvent(nativeEvent);
                }
            }
        }
    }

    private boolean emit(NativeEvent nativeEvent) {
        ReactContext reactContext;
        if (this.jsReady.booleanValue() && (reactContext = this.reactContext) != null && reactContext.hasActiveCatalystInstance()) {
            try {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("rnfb_" + nativeEvent.getEventName(), nativeEvent.getEventBody());
                return true;
            } catch (Exception e) {
                Log.wtf("RNFB_EMITTER", "Error sending Event " + nativeEvent.getEventName(), e);
            }
        }
        return false;
    }
}
