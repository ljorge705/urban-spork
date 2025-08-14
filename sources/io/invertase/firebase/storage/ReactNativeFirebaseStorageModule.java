package io.invertase.firebase.storage;

import android.net.Uri;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes6.dex */
public class ReactNativeFirebaseStorageModule extends ReactNativeFirebaseModule {
    private static final String TAG = "Storage";
    private static HashMap<String, String> emulatorConfigs = new HashMap<>();

    ReactNativeFirebaseStorageModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, TAG);
    }

    @Override // io.invertase.firebase.common.ReactNativeFirebaseModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        ReactNativeFirebaseStorageTask.destroyAllTasks();
        super.onCatalystInstanceDestroy();
    }

    @ReactMethod
    public void delete(String str, String str2, final Promise promise) {
        try {
            getReferenceFromUrl(str2, str).delete().addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.storage.ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda6
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    ReactNativeFirebaseStorageModule.lambda$delete$0(promise, task);
                }
            });
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    static /* synthetic */ void lambda$delete$0(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(null);
        } else {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, (Exception) Objects.requireNonNull(task.getException()));
        }
    }

    @ReactMethod
    public void getDownloadURL(String str, String str2, final Promise promise) {
        try {
            getReferenceFromUrl(str2, str).getDownloadUrl().addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.storage.ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    ReactNativeFirebaseStorageModule.lambda$getDownloadURL$1(promise, task);
                }
            });
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    static /* synthetic */ void lambda$getDownloadURL$1(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult() != null ? ((Uri) task.getResult()).toString() : null);
        } else {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, task.getException());
        }
    }

    @ReactMethod
    public void getMetadata(String str, String str2, final Promise promise) {
        try {
            getReferenceFromUrl(str2, str).getMetadata().addOnCompleteListener(getExecutor(), new OnCompleteListener() { // from class: io.invertase.firebase.storage.ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda3
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    ReactNativeFirebaseStorageModule.lambda$getMetadata$2(promise, task);
                }
            });
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    static /* synthetic */ void lambda$getMetadata$2(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(ReactNativeFirebaseStorageCommon.getMetadataAsMap((StorageMetadata) task.getResult()));
        } else {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, task.getException());
        }
    }

    @ReactMethod
    public void list(String str, String str2, ReadableMap readableMap, final Promise promise) {
        Task<ListResult> list;
        try {
            StorageReference referenceFromUrl = getReferenceFromUrl(str2, str);
            int i = readableMap.getInt("maxResults");
            if (readableMap.hasKey("pageToken")) {
                list = referenceFromUrl.list(i, (String) Objects.requireNonNull(readableMap.getString("pageToken")));
            } else {
                list = referenceFromUrl.list(i);
            }
            list.addOnCompleteListener(getExecutor(), new OnCompleteListener() { // from class: io.invertase.firebase.storage.ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda5
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    ReactNativeFirebaseStorageModule.lambda$list$3(promise, task);
                }
            });
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    static /* synthetic */ void lambda$list$3(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(ReactNativeFirebaseStorageCommon.getListResultAsMap((ListResult) Objects.requireNonNull((ListResult) task.getResult())));
        } else {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, task.getException());
        }
    }

    @ReactMethod
    public void listAll(String str, String str2, final Promise promise) {
        try {
            getReferenceFromUrl(str2, str).listAll().addOnCompleteListener(getExecutor(), new OnCompleteListener() { // from class: io.invertase.firebase.storage.ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda4
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    ReactNativeFirebaseStorageModule.lambda$listAll$4(promise, task);
                }
            });
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    static /* synthetic */ void lambda$listAll$4(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(ReactNativeFirebaseStorageCommon.getListResultAsMap((ListResult) Objects.requireNonNull((ListResult) task.getResult())));
        } else {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, task.getException());
        }
    }

    private void dumpMetadata(StorageMetadata storageMetadata) {
        System.err.println("STORAGE dumping metadata contents");
        System.err.println("STORAGE - cacheControl " + storageMetadata.getCacheControl());
        System.err.println("STORAGE - contentDisposition " + storageMetadata.getContentDisposition());
        System.err.println("STORAGE - contentEncoding " + storageMetadata.getContentEncoding());
        System.err.println("STORAGE - contentLanguage " + storageMetadata.getContentLanguage());
        System.err.println("STORAGE - contentType " + storageMetadata.getContentType());
        for (String str : storageMetadata.getCustomMetadataKeys()) {
            System.err.println("STORAGE - customMetadata: '" + str + "' / '" + storageMetadata.getCustomMetadata(str) + "'");
        }
    }

    @ReactMethod
    public void updateMetadata(String str, String str2, final ReadableMap readableMap, final Promise promise) {
        try {
            final StorageReference referenceFromUrl = getReferenceFromUrl(str2, str);
            referenceFromUrl.getMetadata().addOnCompleteListener(getExecutor(), new OnCompleteListener() { // from class: io.invertase.firebase.storage.ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    this.f$0.lambda$updateMetadata$6(readableMap, referenceFromUrl, promise, task);
                }
            });
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateMetadata$6(ReadableMap readableMap, StorageReference storageReference, final Promise promise, Task task) {
        if (task.isSuccessful()) {
            storageReference.updateMetadata(ReactNativeFirebaseStorageCommon.buildMetadataFromMap(readableMap, null, (StorageMetadata) task.getResult())).addOnCompleteListener(getExecutor(), new OnCompleteListener() { // from class: io.invertase.firebase.storage.ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda2
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    ReactNativeFirebaseStorageModule.lambda$updateMetadata$5(promise, task2);
                }
            });
        } else {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, task.getException());
        }
    }

    static /* synthetic */ void lambda$updateMetadata$5(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(ReactNativeFirebaseStorageCommon.getMetadataAsMap((StorageMetadata) task.getResult()));
        } else {
            task.getException().printStackTrace();
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, task.getException());
        }
    }

    @ReactMethod
    public void setMaxDownloadRetryTime(String str, double d, Promise promise) {
        FirebaseStorage.getInstance(FirebaseApp.getInstance(str)).setMaxDownloadRetryTimeMillis((long) d);
        promise.resolve(null);
    }

    @ReactMethod
    public void setMaxOperationRetryTime(String str, double d, Promise promise) {
        FirebaseStorage.getInstance(FirebaseApp.getInstance(str)).setMaxOperationRetryTimeMillis((long) d);
        promise.resolve(null);
    }

    @ReactMethod
    public void setMaxUploadRetryTime(String str, double d, Promise promise) {
        FirebaseStorage.getInstance(FirebaseApp.getInstance(str)).setMaxUploadRetryTimeMillis((long) d);
        promise.resolve(null);
    }

    @ReactMethod
    public void useEmulator(String str, String str2, int i, Promise promise) {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance(FirebaseApp.getInstance(str));
        if (emulatorConfigs.get(str) == null) {
            firebaseStorage.useEmulator(str2, i);
            emulatorConfigs.put(str, "true");
        }
        promise.resolve(null);
    }

    @ReactMethod
    public void writeToFile(String str, String str2, String str3, int i, Promise promise) {
        if (!ReactNativeFirebaseStorageCommon.isExternalStorageWritable()) {
            rejectPromiseWithCodeAndMessage(promise, "invalid-device-file-path", "The specified device file path is invalid or is restricted.");
            return;
        }
        try {
            ReactNativeFirebaseStorageDownloadTask reactNativeFirebaseStorageDownloadTask = new ReactNativeFirebaseStorageDownloadTask(i, getReferenceFromUrl(str2, str), str);
            reactNativeFirebaseStorageDownloadTask.begin(getTransactionalExecutor(), str3);
            reactNativeFirebaseStorageDownloadTask.addOnCompleteListener(getTransactionalExecutor(), promise);
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    @ReactMethod
    public void putString(String str, String str2, String str3, String str4, ReadableMap readableMap, int i, Promise promise) {
        try {
            ReactNativeFirebaseStorageUploadTask reactNativeFirebaseStorageUploadTask = new ReactNativeFirebaseStorageUploadTask(i, getReferenceFromUrl(str2, str), str);
            reactNativeFirebaseStorageUploadTask.begin(getTransactionalExecutor(), str3, str4, readableMap);
            reactNativeFirebaseStorageUploadTask.addOnCompleteListener(getTransactionalExecutor(), promise);
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    @ReactMethod
    public void putFile(String str, String str2, String str3, ReadableMap readableMap, int i, Promise promise) {
        try {
            ReactNativeFirebaseStorageUploadTask reactNativeFirebaseStorageUploadTask = new ReactNativeFirebaseStorageUploadTask(i, getReferenceFromUrl(str2, str), str);
            reactNativeFirebaseStorageUploadTask.begin(getTransactionalExecutor(), str3, readableMap);
            reactNativeFirebaseStorageUploadTask.addOnCompleteListener(getTransactionalExecutor(), promise);
        } catch (Exception e) {
            ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, e);
        }
    }

    @ReactMethod
    public void setTaskStatus(String str, int i, int i2, Promise promise) {
        if (i2 == 0) {
            promise.resolve(Boolean.valueOf(ReactNativeFirebaseStorageTask.pauseTaskById(i)));
        } else if (i2 == 1) {
            promise.resolve(Boolean.valueOf(ReactNativeFirebaseStorageTask.resumeTaskById(i)));
        } else {
            if (i2 != 2) {
                return;
            }
            promise.resolve(Boolean.valueOf(ReactNativeFirebaseStorageTask.cancelTaskById(i)));
        }
    }

    private String getBucketFromUrl(String str) {
        return str.substring(0, str.substring(5).indexOf("/") + 5);
    }

    private StorageReference getReferenceFromUrl(String str, String str2) throws IllegalArgumentException {
        return FirebaseStorage.getInstance(FirebaseApp.getInstance(str2), getBucketFromUrl(str)).getReferenceFromUrl(str);
    }

    @Override // io.invertase.firebase.common.ReactNativeFirebaseModule, com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap map = new HashMap();
        if (FirebaseApp.getApps(getReactApplicationContext()).size() > 0) {
            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
            map.put("maxDownloadRetryTime", Long.valueOf(firebaseStorage.getMaxDownloadRetryTimeMillis()));
            map.put("maxOperationRetryTime", Long.valueOf(firebaseStorage.getMaxOperationRetryTimeMillis()));
            map.put("maxUploadRetryTime", Long.valueOf(firebaseStorage.getMaxUploadRetryTimeMillis()));
        }
        return map;
    }
}
