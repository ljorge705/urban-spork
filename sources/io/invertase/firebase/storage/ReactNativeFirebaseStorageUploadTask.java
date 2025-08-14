package io.invertase.firebase.storage;

import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;
import io.invertase.firebase.common.SharedUtils;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
class ReactNativeFirebaseStorageUploadTask extends ReactNativeFirebaseStorageTask {
    private static final String TAG = "RNFBStorageUpload";
    private UploadTask uploadTask;

    ReactNativeFirebaseStorageUploadTask(int i, StorageReference storageReference, String str) {
        super(i, storageReference, str);
    }

    private static WritableMap buildUploadSnapshotMap(@Nullable UploadTask.TaskSnapshot taskSnapshot) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        if (taskSnapshot != null) {
            writableMapCreateMap.putDouble("totalBytes", taskSnapshot.getTotalByteCount());
            writableMapCreateMap.putDouble("bytesTransferred", taskSnapshot.getBytesTransferred());
            writableMapCreateMap.putString("state", ReactNativeFirebaseStorageCommon.getTaskStatus(taskSnapshot.getTask()));
            writableMapCreateMap.putMap("metadata", ReactNativeFirebaseStorageCommon.getMetadataAsMap(taskSnapshot.getMetadata()));
        } else {
            writableMapCreateMap.putDouble("totalBytes", 0.0d);
            writableMapCreateMap.putDouble("bytesTransferred", 0.0d);
            writableMapCreateMap.putString("state", ReactNativeFirebaseStorageCommon.getTaskStatus(null));
            writableMapCreateMap.putMap("metadata", Arguments.createMap());
        }
        return writableMapCreateMap;
    }

    private byte[] uploadStringToByteArray(String str, String str2) {
        str2.hashCode();
        if (str2.equals("base64url")) {
            return Base64.decode(str, 8);
        }
        if (str2.equals("base64")) {
            return Base64.decode(str, 0);
        }
        return null;
    }

    private void addEventListeners(ExecutorService executorService) {
        this.uploadTask.addOnProgressListener((Executor) executorService, new OnProgressListener() { // from class: io.invertase.firebase.storage.ReactNativeFirebaseStorageUploadTask$$ExternalSyntheticLambda1
            @Override // com.google.firebase.storage.OnProgressListener
            public final void onProgress(Object obj) {
                this.f$0.lambda$addEventListeners$0((UploadTask.TaskSnapshot) obj);
            }
        });
        this.uploadTask.addOnCanceledListener((Executor) executorService, new OnCanceledListener() { // from class: io.invertase.firebase.storage.ReactNativeFirebaseStorageUploadTask$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnCanceledListener
            public final void onCanceled() {
                this.f$0.lambda$addEventListeners$1();
            }
        });
        this.uploadTask.addOnPausedListener((Executor) executorService, new OnPausedListener() { // from class: io.invertase.firebase.storage.ReactNativeFirebaseStorageUploadTask$$ExternalSyntheticLambda3
            @Override // com.google.firebase.storage.OnPausedListener
            public final void onPaused(Object obj) {
                this.f$0.lambda$addEventListeners$2((UploadTask.TaskSnapshot) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addEventListeners$0(UploadTask.TaskSnapshot taskSnapshot) {
        Log.d(TAG, "onProgress " + this.storageReference.toString());
        ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseStorageEvent(buildUploadSnapshotMap(taskSnapshot), "state_changed", this.appName, this.taskId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addEventListeners$1() {
        Log.d(TAG, "onCancelled " + this.storageReference.toString());
        ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseStorageEvent(buildCancelledSnapshotMap(buildUploadSnapshotMap(this.uploadTask.getSnapshot())), "state_changed", this.appName, this.taskId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addEventListeners$2(UploadTask.TaskSnapshot taskSnapshot) {
        Log.d(TAG, "onPaused " + this.storageReference.toString());
        ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseStorageEvent(buildUploadSnapshotMap(taskSnapshot), "state_changed", this.appName, this.taskId));
    }

    void addOnCompleteListener(ExecutorService executorService, final Promise promise) {
        this.uploadTask.addOnCompleteListener((Executor) executorService, new OnCompleteListener() { // from class: io.invertase.firebase.storage.ReactNativeFirebaseStorageUploadTask$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.lambda$addOnCompleteListener$3(promise, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addOnCompleteListener$3(Promise promise, Task task) {
        destroyTask();
        if (task.isSuccessful()) {
            ReactNativeFirebaseEventEmitter sharedInstance = ReactNativeFirebaseEventEmitter.getSharedInstance();
            sharedInstance.sendEvent(new ReactNativeFirebaseStorageEvent(buildUploadSnapshotMap((UploadTask.TaskSnapshot) task.getResult()), "state_changed", this.appName, this.taskId));
            sharedInstance.sendEvent(new ReactNativeFirebaseStorageEvent(buildUploadSnapshotMap((UploadTask.TaskSnapshot) task.getResult()), "upload_success", this.appName, this.taskId));
            promise.resolve(buildUploadSnapshotMap((UploadTask.TaskSnapshot) task.getResult()));
            return;
        }
        ReactNativeFirebaseEventEmitter sharedInstance2 = ReactNativeFirebaseEventEmitter.getSharedInstance();
        WritableMap writableMapBuildErrorSnapshotMap = buildErrorSnapshotMap(task.getException(), buildUploadSnapshotMap(this.uploadTask.getSnapshot()), true);
        if (writableMapBuildErrorSnapshotMap != null) {
            sharedInstance2.sendEvent(new ReactNativeFirebaseStorageEvent(writableMapBuildErrorSnapshotMap, "state_changed", this.appName, this.taskId));
        }
        sharedInstance2.sendEvent(new ReactNativeFirebaseStorageEvent(buildErrorSnapshotMap(task.getException(), buildUploadSnapshotMap(this.uploadTask.getSnapshot()), false), "upload_failure", this.appName, this.taskId));
        ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, task.getException());
    }

    void begin(ExecutorService executorService, String str, String str2, ReadableMap readableMap) {
        UploadTask uploadTaskPutBytes = this.storageReference.putBytes(uploadStringToByteArray(str, str2), ReactNativeFirebaseStorageCommon.buildMetadataFromMap(readableMap, null, null));
        this.uploadTask = uploadTaskPutBytes;
        setStorageTask(uploadTaskPutBytes);
        addEventListeners(executorService);
    }

    void begin(ExecutorService executorService, String str, ReadableMap readableMap) {
        Uri uri = SharedUtils.getUri(str);
        UploadTask uploadTaskPutFile = this.storageReference.putFile(uri, ReactNativeFirebaseStorageCommon.buildMetadataFromMap(readableMap, uri, null));
        this.uploadTask = uploadTaskPutFile;
        setStorageTask(uploadTaskPutFile);
        addEventListeners(executorService);
    }
}
