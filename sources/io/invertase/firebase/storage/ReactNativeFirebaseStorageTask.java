package io.invertase.firebase.storage;

import android.util.Log;
import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
class ReactNativeFirebaseStorageTask {
    static final String KEY_BYTES_TRANSFERRED = "bytesTransferred";
    private static final String KEY_CODE = "code";
    private static final String KEY_ERROR = "error";
    private static final String KEY_MESSAGE = "message";
    static final String KEY_META_DATA = "metadata";
    private static final String KEY_NATIVE_ERROR_MESSAGE = "nativeErrorMessage";
    static final String KEY_STATE = "state";
    static final String KEY_TOTAL_BYTES = "totalBytes";
    private static final SparseArray<ReactNativeFirebaseStorageTask> PENDING_TASKS = new SparseArray<>();
    private static final String TAG = "RNFBStorageTask";
    String appName;
    StorageReference storageReference;
    private StorageTask storageTask;
    int taskId;

    void setStorageTask(StorageTask storageTask) {
        this.storageTask = storageTask;
    }

    ReactNativeFirebaseStorageTask(int i, StorageReference storageReference, String str) {
        this.taskId = i;
        this.storageReference = storageReference;
        this.appName = str;
        PENDING_TASKS.put(i, this);
    }

    static boolean pauseTaskById(int i) {
        ReactNativeFirebaseStorageTask reactNativeFirebaseStorageTask = PENDING_TASKS.get(i);
        if (reactNativeFirebaseStorageTask != null) {
            return reactNativeFirebaseStorageTask.pause();
        }
        return false;
    }

    static boolean resumeTaskById(int i) {
        ReactNativeFirebaseStorageTask reactNativeFirebaseStorageTask = PENDING_TASKS.get(i);
        if (reactNativeFirebaseStorageTask != null) {
            return reactNativeFirebaseStorageTask.resume();
        }
        return false;
    }

    static boolean cancelTaskById(int i) {
        ReactNativeFirebaseStorageTask reactNativeFirebaseStorageTask = PENDING_TASKS.get(i);
        if (reactNativeFirebaseStorageTask != null) {
            return reactNativeFirebaseStorageTask.cancel();
        }
        return false;
    }

    static void destroyAllTasks() {
        int size = PENDING_TASKS.size();
        for (int i = 0; i < size; i++) {
            SparseArray<ReactNativeFirebaseStorageTask> sparseArray = PENDING_TASKS;
            sparseArray.get(sparseArray.keyAt(i)).cancel();
        }
        PENDING_TASKS.clear();
    }

    static WritableMap buildCancelledSnapshotMap(WritableMap writableMap) {
        writableMap.putString("state", "cancelled");
        return writableMap;
    }

    static WritableMap buildErrorSnapshotMap(@Nullable Exception exc, WritableMap writableMap, boolean z) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        String[] exceptionCodeAndMessage = ReactNativeFirebaseStorageCommon.getExceptionCodeAndMessage(exc);
        if (z && exceptionCodeAndMessage[0].equals("cancelled")) {
            return null;
        }
        writableMapCreateMap.putString(KEY_CODE, exceptionCodeAndMessage[0]);
        writableMapCreateMap.putString("message", exceptionCodeAndMessage[1]);
        if (exc != null) {
            writableMapCreateMap.putString(KEY_NATIVE_ERROR_MESSAGE, exc.getMessage());
        }
        writableMap.putMap("error", writableMapCreateMap);
        writableMap.putString("state", "error");
        return writableMap;
    }

    private boolean pause() {
        Log.d(TAG, "pausing task for " + this.storageReference.toString());
        if (this.storageTask.isPaused() || !this.storageTask.isInProgress()) {
            return false;
        }
        return this.storageTask.pause();
    }

    private boolean resume() {
        Log.d(TAG, "resuming task for " + this.storageReference.toString());
        if (this.storageTask.isPaused()) {
            return this.storageTask.resume();
        }
        return false;
    }

    private boolean cancel() {
        Log.d(TAG, "cancelling task for " + this.storageReference.toString());
        if (this.storageTask.isCanceled() || !this.storageTask.isInProgress()) {
            return false;
        }
        destroyTask();
        return this.storageTask.cancel();
    }

    void destroyTask() {
        PENDING_TASKS.remove(this.taskId);
        Log.d(TAG, "destroyed completed task for " + this.storageReference.toString());
    }
}
