package io.invertase.notifee;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ProcessLifecycleOwner;
import app.notifee.core.EventSubscriber;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import com.facebook.react.jstasks.HeadlessJsTaskEventListener;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes6.dex */
class NotifeeReactUtils {
    private static final SparseArray<GenericCallback> headlessTasks = new SparseArray<>();
    private static final HeadlessJsTaskEventListener headlessTasksListener = new HeadlessJsTaskEventListener() { // from class: io.invertase.notifee.NotifeeReactUtils.1
        @Override // com.facebook.react.jstasks.HeadlessJsTaskEventListener
        public void onHeadlessJsTaskStart(int i) {
        }

        @Override // com.facebook.react.jstasks.HeadlessJsTaskEventListener
        public void onHeadlessJsTaskFinish(int i) {
            synchronized (NotifeeReactUtils.headlessTasks) {
                GenericCallback genericCallback = (GenericCallback) NotifeeReactUtils.headlessTasks.get(i);
                if (genericCallback != null) {
                    NotifeeReactUtils.headlessTasks.remove(i);
                    genericCallback.call();
                }
            }
        }
    };

    interface GenericCallback {
        void call();
    }

    NotifeeReactUtils() {
    }

    static void promiseResolver(Promise promise, Exception exc, Bundle bundle) {
        if (exc != null) {
            promise.reject(exc);
        } else if (bundle != null) {
            promise.resolve(Arguments.fromBundle(bundle));
        } else {
            promise.resolve(null);
        }
    }

    static void promiseResolver(Promise promise, Exception exc, List<Bundle> list) {
        if (exc != null) {
            promise.reject(exc);
            return;
        }
        WritableArray writableArrayCreateArray = Arguments.createArray();
        Iterator<Bundle> it = list.iterator();
        while (it.hasNext()) {
            writableArrayCreateArray.pushMap(Arguments.fromBundle(it.next()));
        }
        promise.resolve(writableArrayCreateArray);
    }

    static void promiseBooleanResolver(Promise promise, Exception exc, Boolean bool) {
        if (exc != null) {
            promise.reject(exc);
        } else {
            promise.resolve(bool);
        }
    }

    static void promiseStringListResolver(Promise promise, Exception exc, List<String> list) {
        if (exc != null) {
            promise.reject(exc);
            return;
        }
        WritableArray writableArrayCreateArray = Arguments.createArray();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            writableArrayCreateArray.pushString(it.next());
        }
        promise.resolve(writableArrayCreateArray);
    }

    static void promiseResolver(Promise promise, Exception exc) {
        if (exc != null) {
            promise.reject(exc);
        } else {
            promise.resolve(null);
        }
    }

    private static ReactContext getReactContext() {
        return ((ReactApplication) EventSubscriber.getContext()).getReactNativeHost().getReactInstanceManager().getCurrentReactContext();
    }

    private static void initializeReactContext(final GenericCallback genericCallback) {
        final ReactInstanceManager reactInstanceManager = ((ReactApplication) EventSubscriber.getContext()).getReactNativeHost().getReactInstanceManager();
        reactInstanceManager.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() { // from class: io.invertase.notifee.NotifeeReactUtils.2
            @Override // com.facebook.react.ReactInstanceEventListener
            public void onReactContextInitialized(ReactContext reactContext) {
                reactInstanceManager.removeReactInstanceEventListener(this);
                Handler handler = new Handler(Looper.getMainLooper());
                final GenericCallback genericCallback2 = genericCallback;
                Objects.requireNonNull(genericCallback2);
                handler.postDelayed(new Runnable() { // from class: io.invertase.notifee.NotifeeReactUtils$2$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        genericCallback2.call();
                    }
                }, 100L);
            }
        });
        if (reactInstanceManager.hasStartedCreatingInitialContext()) {
            return;
        }
        reactInstanceManager.createReactContextInBackground();
    }

    static void clearRunningHeadlessTasks() {
        int i = 0;
        while (true) {
            SparseArray<GenericCallback> sparseArray = headlessTasks;
            if (i >= sparseArray.size()) {
                return;
            }
            sparseArray.valueAt(i).call();
            sparseArray.remove(i);
            i++;
        }
    }

    static void startHeadlessTask(final String str, final WritableMap writableMap, final long j, final GenericCallback genericCallback) {
        GenericCallback genericCallback2 = new GenericCallback() { // from class: io.invertase.notifee.NotifeeReactUtils$$ExternalSyntheticLambda0
            @Override // io.invertase.notifee.NotifeeReactUtils.GenericCallback
            public final void call() {
                NotifeeReactUtils.lambda$startHeadlessTask$1(str, writableMap, j, genericCallback);
            }
        };
        if (getReactContext() == null) {
            initializeReactContext(genericCallback2);
        } else {
            genericCallback2.call();
        }
    }

    static /* synthetic */ void lambda$startHeadlessTask$1(String str, WritableMap writableMap, long j, final GenericCallback genericCallback) {
        final HeadlessJsTaskContext headlessJsTaskContext = HeadlessJsTaskContext.getInstance(getReactContext());
        HeadlessJsTaskConfig headlessJsTaskConfig = new HeadlessJsTaskConfig(str, writableMap, j, true);
        SparseArray<GenericCallback> sparseArray = headlessTasks;
        synchronized (sparseArray) {
            if (sparseArray.size() == 0) {
                headlessJsTaskContext.addTaskEventListener(headlessTasksListener);
            }
        }
        sparseArray.put(headlessJsTaskContext.startTask(headlessJsTaskConfig), new GenericCallback() { // from class: io.invertase.notifee.NotifeeReactUtils$$ExternalSyntheticLambda1
            @Override // io.invertase.notifee.NotifeeReactUtils.GenericCallback
            public final void call() {
                NotifeeReactUtils.lambda$startHeadlessTask$0(headlessJsTaskContext, genericCallback);
            }
        });
    }

    static /* synthetic */ void lambda$startHeadlessTask$0(HeadlessJsTaskContext headlessJsTaskContext, GenericCallback genericCallback) {
        SparseArray<GenericCallback> sparseArray = headlessTasks;
        synchronized (sparseArray) {
            if (sparseArray.size() == 0) {
                headlessJsTaskContext.removeTaskEventListener(headlessTasksListener);
            }
        }
        if (genericCallback != null) {
            genericCallback.call();
        }
    }

    static void sendEvent(String str, WritableMap writableMap) {
        try {
            ReactContext reactContext = getReactContext();
            if (reactContext != null && reactContext.hasActiveCatalystInstance()) {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
            }
        } catch (Exception e) {
            Log.e("SEND_EVENT", "", e);
        }
    }

    static boolean isAppInForeground() {
        return Boolean.valueOf(ProcessLifecycleOwner.get().getLifecycleRegistry().getState().isAtLeast(Lifecycle.State.RESUMED)).booleanValue();
    }

    static void hideNotificationDrawer() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Object systemService = EventSubscriber.getContext().getSystemService("statusbar");
            Method method = Class.forName("android.app.StatusBarManager").getMethod("collapsePanels", new Class[0]);
            method.setAccessible(true);
            method.invoke(systemService, new Object[0]);
        } catch (Exception e) {
            Log.e("HIDE_NOTIF_DRAWER", "", e);
        }
    }
}
