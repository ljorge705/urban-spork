package io.invertase.firebase.analytics;

import android.os.Bundle;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public class ReactNativeFirebaseAnalyticsModule extends ReactNativeFirebaseModule {
    private static final String SERVICE_NAME = "Analytics";
    private final UniversalFirebaseAnalyticsModule module;

    ReactNativeFirebaseAnalyticsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, SERVICE_NAME);
        this.module = new UniversalFirebaseAnalyticsModule(reactApplicationContext, SERVICE_NAME);
    }

    @ReactMethod
    public void logEvent(String str, @Nullable ReadableMap readableMap, final Promise promise) {
        this.module.logEvent(str, toBundle(readableMap)).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.analytics.ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseAnalyticsModule.lambda$logEvent$0(promise, task);
            }
        });
    }

    static /* synthetic */ void lambda$logEvent$0(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setAnalyticsCollectionEnabled(Boolean bool, final Promise promise) {
        this.module.setAnalyticsCollectionEnabled(bool).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.analytics.ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseAnalyticsModule.lambda$setAnalyticsCollectionEnabled$1(promise, task);
            }
        });
    }

    static /* synthetic */ void lambda$setAnalyticsCollectionEnabled$1(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setSessionTimeoutDuration(double d, final Promise promise) {
        this.module.setSessionTimeoutDuration((long) d).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.analytics.ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseAnalyticsModule.lambda$setSessionTimeoutDuration$2(promise, task);
            }
        });
    }

    static /* synthetic */ void lambda$setSessionTimeoutDuration$2(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void getAppInstanceId(final Promise promise) {
        this.module.getAppInstanceId().addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.analytics.ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda7
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseAnalyticsModule.lambda$getAppInstanceId$3(promise, task);
            }
        });
    }

    static /* synthetic */ void lambda$getAppInstanceId$3(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setUserId(String str, final Promise promise) {
        this.module.setUserId(str).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.analytics.ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda8
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseAnalyticsModule.lambda$setUserId$4(promise, task);
            }
        });
    }

    static /* synthetic */ void lambda$setUserId$4(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setUserProperty(String str, String str2, final Promise promise) {
        this.module.setUserProperty(str, str2).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.analytics.ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda6
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseAnalyticsModule.lambda$setUserProperty$5(promise, task);
            }
        });
    }

    static /* synthetic */ void lambda$setUserProperty$5(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setUserProperties(ReadableMap readableMap, final Promise promise) {
        this.module.setUserProperties(Arguments.toBundle(readableMap)).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.analytics.ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseAnalyticsModule.lambda$setUserProperties$6(promise, task);
            }
        });
    }

    static /* synthetic */ void lambda$setUserProperties$6(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void resetAnalyticsData(final Promise promise) {
        this.module.resetAnalyticsData().addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.analytics.ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseAnalyticsModule.lambda$resetAnalyticsData$7(promise, task);
            }
        });
    }

    static /* synthetic */ void lambda$resetAnalyticsData$7(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setDefaultEventParameters(@Nullable ReadableMap readableMap, final Promise promise) {
        this.module.setDefaultEventParameters(toBundle(readableMap)).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.analytics.ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseAnalyticsModule.lambda$setDefaultEventParameters$8(promise, task);
            }
        });
    }

    static /* synthetic */ void lambda$setDefaultEventParameters$8(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    private Bundle toBundle(ReadableMap readableMap) {
        Bundle bundle = Arguments.toBundle(readableMap);
        if (bundle == null) {
            return null;
        }
        ArrayList arrayList = (ArrayList) bundle.getSerializable(FirebaseAnalytics.Param.ITEMS);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof Bundle) {
                Bundle bundle2 = (Bundle) next;
                if (bundle2.containsKey("quantity")) {
                    bundle2.putInt("quantity", (int) bundle2.getDouble("quantity"));
                }
            }
        }
        if (bundle.containsKey(FirebaseAnalytics.Param.EXTEND_SESSION)) {
            bundle.putLong(FirebaseAnalytics.Param.EXTEND_SESSION, (long) bundle.getDouble(FirebaseAnalytics.Param.EXTEND_SESSION));
        }
        return bundle;
    }
}
