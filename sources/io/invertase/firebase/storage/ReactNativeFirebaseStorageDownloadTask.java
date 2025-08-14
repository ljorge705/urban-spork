package io.invertase.firebase.storage;

import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
class ReactNativeFirebaseStorageDownloadTask extends ReactNativeFirebaseStorageTask {
    private static final String TAG = "RNFBStorageDownload";
    private FileDownloadTask fileDownloadTask;

    ReactNativeFirebaseStorageDownloadTask(int i, StorageReference storageReference, String str) {
        super(i, storageReference, str);
    }

    private static WritableMap buildDownloadSnapshotMap(@Nullable FileDownloadTask.TaskSnapshot taskSnapshot) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        if (taskSnapshot != null) {
            writableMapCreateMap.putDouble("totalBytes", taskSnapshot.getTotalByteCount());
            writableMapCreateMap.putDouble("bytesTransferred", taskSnapshot.getBytesTransferred());
            writableMapCreateMap.putString("state", ReactNativeFirebaseStorageCommon.getTaskStatus(taskSnapshot.getTask()));
        } else {
            writableMapCreateMap.putDouble("totalBytes", 0.0d);
            writableMapCreateMap.putDouble("bytesTransferred", 0.0d);
            writableMapCreateMap.putString("state", ReactNativeFirebaseStorageCommon.getTaskStatus(null));
        }
        return writableMapCreateMap;
    }

    private String getPath(String str) {
        int iLastIndexOf = str.lastIndexOf("/");
        return iLastIndexOf > 0 ? str.substring(0, iLastIndexOf) + "/" : "/";
    }

    private String getFileName(String str) {
        int iLastIndexOf = str.lastIndexOf("/");
        return iLastIndexOf > 0 ? str.substring(iLastIndexOf + 1) : str;
    }

    void addOnCompleteListener(ExecutorService executorService, final Promise promise) {
        FileDownloadTask fileDownloadTask = this.fileDownloadTask;
        if (fileDownloadTask == null) {
            ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, "error-creating-directory", "Unable to create the directory specified as the download path for your file.");
        } else {
            fileDownloadTask.addOnCompleteListener((Executor) executorService, new OnCompleteListener() { // from class: io.invertase.firebase.storage.ReactNativeFirebaseStorageDownloadTask$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    this.f$0.lambda$addOnCompleteListener$0(promise, task);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addOnCompleteListener$0(Promise promise, Task task) {
        destroyTask();
        if (task.isSuccessful()) {
            Log.d(TAG, "onComplete:success " + this.storageReference.toString());
            WritableMap writableMapBuildDownloadSnapshotMap = buildDownloadSnapshotMap((FileDownloadTask.TaskSnapshot) task.getResult());
            ReactNativeFirebaseEventEmitter sharedInstance = ReactNativeFirebaseEventEmitter.getSharedInstance();
            sharedInstance.sendEvent(new ReactNativeFirebaseStorageEvent(writableMapBuildDownloadSnapshotMap, "state_changed", this.appName, this.taskId));
            sharedInstance.sendEvent(new ReactNativeFirebaseStorageEvent(buildDownloadSnapshotMap((FileDownloadTask.TaskSnapshot) task.getResult()), "download_success", this.appName, this.taskId));
            promise.resolve(buildDownloadSnapshotMap((FileDownloadTask.TaskSnapshot) task.getResult()));
            return;
        }
        Log.d(TAG, "onComplete:failure " + this.storageReference.toString());
        ReactNativeFirebaseEventEmitter sharedInstance2 = ReactNativeFirebaseEventEmitter.getSharedInstance();
        WritableMap writableMapBuildErrorSnapshotMap = buildErrorSnapshotMap(task.getException(), buildDownloadSnapshotMap(this.fileDownloadTask.getSnapshot()), true);
        if (writableMapBuildErrorSnapshotMap != null) {
            sharedInstance2.sendEvent(new ReactNativeFirebaseStorageEvent(writableMapBuildErrorSnapshotMap, "state_changed", this.appName, this.taskId));
        }
        sharedInstance2.sendEvent(new ReactNativeFirebaseStorageEvent(buildErrorSnapshotMap(task.getException(), buildDownloadSnapshotMap(this.fileDownloadTask.getSnapshot()), false), "download_failure", this.appName, this.taskId));
        ReactNativeFirebaseStorageCommon.promiseRejectStorageException(promise, task.getException());
    }

    private void addEventListeners(ExecutorService executorService) {
        this.fileDownloadTask.addOnProgressListener((Executor) executorService, new OnProgressListener() { // from class: io.invertase.firebase.storage.ReactNativeFirebaseStorageDownloadTask$$ExternalSyntheticLambda1
            @Override // com.google.firebase.storage.OnProgressListener
            public final void onProgress(Object obj) {
                this.f$0.lambda$addEventListeners$1((FileDownloadTask.TaskSnapshot) obj);
            }
        });
        this.fileDownloadTask.addOnCanceledListener((Executor) executorService, new OnCanceledListener() { // from class: io.invertase.firebase.storage.ReactNativeFirebaseStorageDownloadTask$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnCanceledListener
            public final void onCanceled() {
                this.f$0.lambda$addEventListeners$2();
            }
        });
        this.fileDownloadTask.addOnPausedListener((Executor) executorService, new OnPausedListener() { // from class: io.invertase.firebase.storage.ReactNativeFirebaseStorageDownloadTask$$ExternalSyntheticLambda3
            @Override // com.google.firebase.storage.OnPausedListener
            public final void onPaused(Object obj) {
                this.f$0.lambda$addEventListeners$3((FileDownloadTask.TaskSnapshot) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addEventListeners$1(FileDownloadTask.TaskSnapshot taskSnapshot) {
        Log.d(TAG, "onProgress " + this.storageReference.toString());
        ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseStorageEvent(buildDownloadSnapshotMap(taskSnapshot), "state_changed", this.appName, this.taskId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addEventListeners$2() {
        Log.d(TAG, "onCancelled " + this.storageReference.toString());
        ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseStorageEvent(buildCancelledSnapshotMap(buildDownloadSnapshotMap(this.fileDownloadTask.getSnapshot())), "state_changed", this.appName, this.taskId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addEventListeners$3(FileDownloadTask.TaskSnapshot taskSnapshot) {
        Log.d(TAG, "onPaused " + this.storageReference.toString());
        ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseStorageEvent(buildDownloadSnapshotMap(taskSnapshot), "state_changed", this.appName, this.taskId));
    }

    void begin(ExecutorService executorService, String str) {
        String path = getPath(str);
        File file = new File(path);
        if (file.exists() || file.mkdirs()) {
            this.fileDownloadTask = this.storageReference.getFile(new File(path, getFileName(str)));
            addEventListeners(executorService);
            setStorageTask(this.fileDownloadTask);
        }
    }
}
