package io.invertase.firebase.config;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigFetchThrottledException;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public class ReactNativeFirebaseConfigModule extends ReactNativeFirebaseModule {
    private static final String SERVICE_NAME = "Config";
    private final UniversalFirebaseConfigModule module;

    ReactNativeFirebaseConfigModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, SERVICE_NAME);
        this.module = new UniversalFirebaseConfigModule(reactApplicationContext, SERVICE_NAME);
    }

    @ReactMethod
    public void activate(String str, final Promise promise) {
        this.module.activate(str).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.config.ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda7
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.lambda$activate$0(promise, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$activate$0(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(task.getResult()));
        } else {
            rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void fetch(String str, double d, final Promise promise) {
        this.module.fetch(str, (long) d).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.config.ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.lambda$fetch$1(promise, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$fetch$1(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(task.getResult()));
        } else {
            rejectPromiseWithConfigException(promise, task.getException());
        }
    }

    @ReactMethod
    public void fetchAndActivate(String str, final Promise promise) {
        this.module.fetchAndActivate(str).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.config.ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.lambda$fetchAndActivate$2(promise, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$fetchAndActivate$2(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(task.getResult()));
        } else {
            rejectPromiseWithConfigException(promise, task.getException());
        }
    }

    @ReactMethod
    public void reset(String str, final Promise promise) {
        this.module.reset(str).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.config.ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.lambda$reset$3(promise, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$reset$3(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(task.getResult()));
        } else {
            rejectPromiseWithConfigException(promise, task.getException());
        }
    }

    @ReactMethod
    public void setConfigSettings(String str, ReadableMap readableMap, final Promise promise) {
        this.module.setConfigSettings(str, Arguments.toBundle(readableMap)).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.config.ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.lambda$setConfigSettings$4(promise, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setConfigSettings$4(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(task.getResult()));
        } else {
            rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setDefaults(String str, ReadableMap readableMap, final Promise promise) {
        this.module.setDefaults(str, readableMap.toHashMap()).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.config.ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.lambda$setDefaults$5(promise, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setDefaults$5(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(task.getResult()));
        } else {
            rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setDefaultsFromResource(String str, String str2, final Promise promise) {
        this.module.setDefaultsFromResource(str, str2).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.config.ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.lambda$setDefaultsFromResource$6(promise, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setDefaultsFromResource$6(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(task.getResult()));
            return;
        }
        Exception exception = task.getException();
        if (exception != null && exception.getMessage().equals("resource_not_found")) {
            rejectPromiseWithCodeAndMessage(promise, "resource_not_found", "The specified resource name was not found.");
        }
        rejectPromiseWithExceptionMap(promise, task.getException());
    }

    @ReactMethod
    public void ensureInitialized(String str, final Promise promise) {
        this.module.ensureInitialized(str).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.config.ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda6
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.lambda$ensureInitialized$7(promise, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$ensureInitialized$7(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(null));
        } else {
            rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    private WritableMap resultWithConstants(Object obj) {
        HashMap map = new HashMap(2);
        map.put(OnfidoLauncher.KEY_RESULT, obj);
        map.put("constants", this.module.getConstantsForApp(FirebaseApp.DEFAULT_APP_NAME));
        return Arguments.makeNativeMap(map);
    }

    private void rejectPromiseWithConfigException(Promise promise, @Nullable Exception exc) {
        if (exc == null) {
            rejectPromiseWithCodeAndMessage(promise, "unknown", "Operation cannot be completed successfully, due to an unknown error.");
        } else if (exc.getCause() instanceof FirebaseRemoteConfigFetchThrottledException) {
            rejectPromiseWithCodeAndMessage(promise, "throttled", "fetch() operation cannot be completed successfully, due to throttling.", exc.getMessage());
        } else {
            rejectPromiseWithCodeAndMessage(promise, "failure", "fetch() operation cannot be completed successfully.", exc.getMessage());
        }
    }

    @Override // io.invertase.firebase.common.ReactNativeFirebaseModule, com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return this.module.getConstants();
    }
}
