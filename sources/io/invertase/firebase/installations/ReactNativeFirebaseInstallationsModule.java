package io.invertase.firebase.installations;

import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import java.util.concurrent.Callable;

/* loaded from: classes6.dex */
public class ReactNativeFirebaseInstallationsModule extends ReactNativeFirebaseModule {
    private static final String TAG = "Installations";

    ReactNativeFirebaseInstallationsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, TAG);
    }

    @ReactMethod
    public void getId(String str, final Promise promise) {
        final FirebaseApp firebaseApp = FirebaseApp.getInstance(str);
        Tasks.call(getExecutor(), new Callable() { // from class: io.invertase.firebase.installations.ReactNativeFirebaseInstallationsModule$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ReactNativeFirebaseInstallationsModule.lambda$getId$0(firebaseApp);
            }
        }).addOnCompleteListener(getExecutor(), new OnCompleteListener() { // from class: io.invertase.firebase.installations.ReactNativeFirebaseInstallationsModule$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseInstallationsModule.lambda$getId$1(promise, task);
            }
        });
    }

    static /* synthetic */ String lambda$getId$0(FirebaseApp firebaseApp) throws Exception {
        return (String) Tasks.await(FirebaseInstallations.getInstance(firebaseApp).getId());
    }

    static /* synthetic */ void lambda$getId$1(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            Log.e(TAG, "RNFB: Unknown error while fetching Installations ID " + task.getException().getMessage());
            rejectPromiseWithCodeAndMessage(promise, "id-error", task.getException().getMessage());
        }
    }

    @ReactMethod
    public void getToken(String str, final boolean z, final Promise promise) {
        final FirebaseApp firebaseApp = FirebaseApp.getInstance(str);
        Tasks.call(getExecutor(), new Callable() { // from class: io.invertase.firebase.installations.ReactNativeFirebaseInstallationsModule$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ReactNativeFirebaseInstallationsModule.lambda$getToken$2(firebaseApp, z);
            }
        }).addOnCompleteListener(getExecutor(), new OnCompleteListener() { // from class: io.invertase.firebase.installations.ReactNativeFirebaseInstallationsModule$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseInstallationsModule.lambda$getToken$3(promise, task);
            }
        });
    }

    static /* synthetic */ InstallationTokenResult lambda$getToken$2(FirebaseApp firebaseApp, boolean z) throws Exception {
        return (InstallationTokenResult) Tasks.await(FirebaseInstallations.getInstance(firebaseApp).getToken(z));
    }

    static /* synthetic */ void lambda$getToken$3(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(((InstallationTokenResult) task.getResult()).getToken());
        } else {
            Log.e(TAG, "RNFB: Unknown error while fetching Installations token " + task.getException().getMessage());
            rejectPromiseWithCodeAndMessage(promise, "token-error", task.getException().getMessage());
        }
    }

    @ReactMethod
    public void delete(String str, final Promise promise) {
        final FirebaseApp firebaseApp = FirebaseApp.getInstance(str);
        Tasks.call(getExecutor(), new Callable() { // from class: io.invertase.firebase.installations.ReactNativeFirebaseInstallationsModule$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ReactNativeFirebaseInstallationsModule.lambda$delete$4(firebaseApp);
            }
        }).addOnCompleteListener(getExecutor(), new OnCompleteListener() { // from class: io.invertase.firebase.installations.ReactNativeFirebaseInstallationsModule$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseInstallationsModule.lambda$delete$5(promise, task);
            }
        });
    }

    static /* synthetic */ Void lambda$delete$4(FirebaseApp firebaseApp) throws Exception {
        return (Void) Tasks.await(FirebaseInstallations.getInstance(firebaseApp).delete());
    }

    static /* synthetic */ void lambda$delete$5(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(null);
        } else {
            Log.e(TAG, "RNFB: Unknown error while deleting Installations" + task.getException().getMessage());
            rejectPromiseWithCodeAndMessage(promise, "delete-error", task.getException().getMessage());
        }
    }
}
