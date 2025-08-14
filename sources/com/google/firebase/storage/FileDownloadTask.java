package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.common.net.HttpHeaders;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.GetNetworkRequest;
import com.google.firebase.storage.network.NetworkRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;

/* loaded from: classes2.dex */
public class FileDownloadTask extends StorageTask<TaskSnapshot> {
    static final int PREFERRED_CHUNK_SIZE = 262144;
    private static final String TAG = "FileDownloadTask";
    private long mBytesDownloaded;
    private final Uri mDestinationFile;
    private int mResultCode;
    private ExponentialBackoffSender mSender;
    private StorageReference mStorageRef;
    private long mTotalBytes = -1;
    private String mETagVerification = null;
    private volatile Exception mException = null;
    private long mResumeOffset = 0;

    private boolean isValidHttpResponseCode(int i) {
        return i == 308 || (i >= 200 && i < 300);
    }

    long getDownloadedSizeInBytes() {
        return this.mBytesDownloaded;
    }

    @Override // com.google.firebase.storage.StorageTask
    StorageReference getStorage() {
        return this.mStorageRef;
    }

    long getTotalBytes() {
        return this.mTotalBytes;
    }

    FileDownloadTask(StorageReference storageReference, Uri uri) {
        this.mStorageRef = storageReference;
        this.mDestinationFile = uri;
        FirebaseStorage storage = storageReference.getStorage();
        this.mSender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getAppCheckProvider(), storage.getMaxDownloadRetryTimeMillis());
    }

    @Override // com.google.firebase.storage.StorageTask
    protected void schedule() {
        StorageTaskScheduler.getInstance().scheduleDownload(getRunnable());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.storage.StorageTask
    public TaskSnapshot snapStateImpl() {
        return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(this.mException, this.mResultCode), this.mBytesDownloaded + this.mResumeOffset);
    }

    private int fillBuffer(InputStream inputStream, byte[] bArr) {
        int i;
        int i2 = 0;
        boolean z = false;
        while (i2 != bArr.length && (i = inputStream.read(bArr, i2, bArr.length - i2)) != -1) {
            try {
                i2 += i;
                z = true;
            } catch (IOException e) {
                this.mException = e;
            }
        }
        if (z) {
            return i2;
        }
        return -1;
    }

    private boolean processResponse(NetworkRequest networkRequest) throws IOException {
        FileOutputStream fileOutputStream;
        InputStream stream = networkRequest.getStream();
        if (stream != null) {
            File file = new File(this.mDestinationFile.getPath());
            if (!file.exists()) {
                if (this.mResumeOffset > 0) {
                    throw new IOException("The file to download to has been deleted.");
                }
                if (!file.createNewFile()) {
                    Log.w(TAG, "unable to create file:" + file.getAbsolutePath());
                }
            }
            boolean z = true;
            if (this.mResumeOffset > 0) {
                Log.d(TAG, "Resuming download file " + file.getAbsolutePath() + " at " + this.mResumeOffset);
                fileOutputStream = new FileOutputStream(file, true);
            } else {
                fileOutputStream = new FileOutputStream(file);
            }
            try {
                byte[] bArr = new byte[262144];
                while (z) {
                    int iFillBuffer = fillBuffer(stream, bArr);
                    if (iFillBuffer == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, iFillBuffer);
                    this.mBytesDownloaded += iFillBuffer;
                    if (this.mException != null) {
                        Log.d(TAG, "Exception occurred during file download. Retrying.", this.mException);
                        this.mException = null;
                        z = false;
                    }
                    if (!tryChangeState(4, false)) {
                        z = false;
                    }
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                stream.close();
                return z;
            } catch (Throwable th) {
                fileOutputStream.flush();
                fileOutputStream.close();
                stream.close();
                throw th;
            }
        }
        this.mException = new IllegalStateException("Unable to open Firebase Storage stream.");
        return false;
    }

    @Override // com.google.firebase.storage.StorageTask
    void run() throws ProtocolException {
        String str;
        if (this.mException != null) {
            tryChangeState(64, false);
            return;
        }
        if (tryChangeState(4, false)) {
            do {
                this.mBytesDownloaded = 0L;
                this.mException = null;
                this.mSender.reset();
                GetNetworkRequest getNetworkRequest = new GetNetworkRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp(), this.mResumeOffset);
                this.mSender.sendWithExponentialBackoff(getNetworkRequest, false);
                this.mResultCode = getNetworkRequest.getResultCode();
                this.mException = getNetworkRequest.getException() != null ? getNetworkRequest.getException() : this.mException;
                boolean zProcessResponse = isValidHttpResponseCode(this.mResultCode) && this.mException == null && getInternalState() == 4;
                if (zProcessResponse) {
                    this.mTotalBytes = getNetworkRequest.getResultingContentLength() + this.mResumeOffset;
                    String resultString = getNetworkRequest.getResultString(HttpHeaders.ETAG);
                    if (!TextUtils.isEmpty(resultString) && (str = this.mETagVerification) != null && !str.equals(resultString)) {
                        Log.w(TAG, "The file at the server has changed.  Restarting from the beginning.");
                        this.mResumeOffset = 0L;
                        this.mETagVerification = null;
                        getNetworkRequest.performRequestEnd();
                        schedule();
                        return;
                    }
                    this.mETagVerification = resultString;
                    try {
                        zProcessResponse = processResponse(getNetworkRequest);
                    } catch (IOException e) {
                        Log.e(TAG, "Exception occurred during file write.  Aborting.", e);
                        this.mException = e;
                    }
                }
                getNetworkRequest.performRequestEnd();
                if (zProcessResponse && this.mException == null && getInternalState() == 4) {
                    tryChangeState(128, false);
                    return;
                }
                File file = new File(this.mDestinationFile.getPath());
                if (file.exists()) {
                    this.mResumeOffset = file.length();
                } else {
                    this.mResumeOffset = 0L;
                }
                if (getInternalState() == 8) {
                    tryChangeState(16, false);
                    return;
                } else if (getInternalState() == 32) {
                    if (tryChangeState(256, false)) {
                        return;
                    }
                    Log.w(TAG, "Unable to change download task to final state from " + getInternalState());
                    return;
                }
            } while (this.mBytesDownloaded > 0);
            tryChangeState(64, false);
        }
    }

    @Override // com.google.firebase.storage.StorageTask
    protected void onCanceled() {
        this.mSender.cancel();
        this.mException = StorageException.fromErrorStatus(Status.RESULT_CANCELED);
    }

    public class TaskSnapshot extends StorageTask<TaskSnapshot>.SnapshotBase {
        private final long mBytesDownloaded;

        public long getBytesTransferred() {
            return this.mBytesDownloaded;
        }

        TaskSnapshot(Exception exc, long j) {
            super(exc);
            this.mBytesDownloaded = j;
        }

        public long getTotalByteCount() {
            return FileDownloadTask.this.getTotalBytes();
        }
    }
}
