package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.internal.AdaptiveStreamBuffer;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.internal.Sleeper;
import com.google.firebase.storage.internal.SleeperImpl;
import com.google.firebase.storage.internal.Util;
import com.google.firebase.storage.network.NetworkRequest;
import com.google.firebase.storage.network.ResumableUploadByteRequest;
import com.google.firebase.storage.network.ResumableUploadCancelRequest;
import com.google.firebase.storage.network.ResumableUploadQueryRequest;
import com.google.firebase.storage.network.ResumableUploadStartRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class UploadTask extends StorageTask<TaskSnapshot> {
    private static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
    private static final int MAXIMUM_CHUNK_SIZE = 33554432;
    static final int PREFERRED_CHUNK_SIZE = 262144;
    private static final String RESUMABLE_FINAL_STATUS = "final";
    private static final String TAG = "UploadTask";
    private static final String X_GOOG_UPLOAD_URL = "X-Goog-Upload-URL";
    private final InteropAppCheckTokenProvider mAppCheckProvider;
    private final InternalAuthProvider mAuthProvider;
    private final AtomicLong mBytesUploaded;
    private int mCurrentChunkSize;
    private volatile Exception mException;
    private boolean mIsStreamOwned;
    private volatile StorageMetadata mMetadata;
    private volatile int mResultCode;
    private ExponentialBackoffSender mSender;
    private volatile Exception mServerException;
    private volatile String mServerStatus;
    private final StorageReference mStorageRef;
    private final AdaptiveStreamBuffer mStreamBuffer;
    private final long mTotalByteCount;
    private volatile Uri mUploadUri;
    private final Uri mUri;
    private volatile long maxSleepTime;
    private final int minimumSleepInterval;
    private int sleepTime;
    private static final Random random = new Random();
    static Sleeper sleeper = new SleeperImpl();
    static Clock clock = DefaultClock.getInstance();

    private boolean isValidHttpResponseCode(int i) {
        return i == 308 || (i >= 200 && i < 300);
    }

    @Override // com.google.firebase.storage.StorageTask
    StorageReference getStorage() {
        return this.mStorageRef;
    }

    long getTotalByteCount() {
        return this.mTotalByteCount;
    }

    @Override // com.google.firebase.storage.StorageTask
    protected void resetState() {
        this.mException = null;
        this.mServerException = null;
        this.mResultCode = 0;
        this.mServerStatus = null;
    }

    UploadTask(StorageReference storageReference, StorageMetadata storageMetadata, byte[] bArr) {
        this.mBytesUploaded = new AtomicLong(0L);
        this.mCurrentChunkSize = 262144;
        this.mUploadUri = null;
        this.mException = null;
        this.mServerException = null;
        this.mResultCode = 0;
        this.sleepTime = 0;
        this.minimumSleepInterval = 1000;
        Preconditions.checkNotNull(storageReference);
        Preconditions.checkNotNull(bArr);
        FirebaseStorage storage = storageReference.getStorage();
        this.mTotalByteCount = bArr.length;
        this.mStorageRef = storageReference;
        this.mMetadata = storageMetadata;
        InternalAuthProvider authProvider = storage.getAuthProvider();
        this.mAuthProvider = authProvider;
        InteropAppCheckTokenProvider appCheckProvider = storage.getAppCheckProvider();
        this.mAppCheckProvider = appCheckProvider;
        this.mUri = null;
        this.mStreamBuffer = new AdaptiveStreamBuffer(new ByteArrayInputStream(bArr), 262144);
        this.mIsStreamOwned = true;
        this.maxSleepTime = storage.getMaxChunkUploadRetry();
        this.mSender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), authProvider, appCheckProvider, storage.getMaxDownloadRetryTimeMillis());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a9  */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v8, types: [long] */
    /* JADX WARN: Type inference failed for: r6v9, types: [long] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    UploadTask(com.google.firebase.storage.StorageReference r12, com.google.firebase.storage.StorageMetadata r13, android.net.Uri r14, android.net.Uri r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 234
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.storage.UploadTask.<init>(com.google.firebase.storage.StorageReference, com.google.firebase.storage.StorageMetadata, android.net.Uri, android.net.Uri):void");
    }

    UploadTask(StorageReference storageReference, StorageMetadata storageMetadata, InputStream inputStream) {
        this.mBytesUploaded = new AtomicLong(0L);
        this.mCurrentChunkSize = 262144;
        this.mUploadUri = null;
        this.mException = null;
        this.mServerException = null;
        this.mResultCode = 0;
        this.sleepTime = 0;
        this.minimumSleepInterval = 1000;
        Preconditions.checkNotNull(storageReference);
        Preconditions.checkNotNull(inputStream);
        FirebaseStorage storage = storageReference.getStorage();
        this.mTotalByteCount = -1L;
        this.mStorageRef = storageReference;
        this.mMetadata = storageMetadata;
        InternalAuthProvider authProvider = storage.getAuthProvider();
        this.mAuthProvider = authProvider;
        InteropAppCheckTokenProvider appCheckProvider = storage.getAppCheckProvider();
        this.mAppCheckProvider = appCheckProvider;
        this.mStreamBuffer = new AdaptiveStreamBuffer(inputStream, 262144);
        this.mIsStreamOwned = false;
        this.mUri = null;
        this.maxSleepTime = storage.getMaxChunkUploadRetry();
        this.mSender = new ExponentialBackoffSender(storageReference.getApp().getApplicationContext(), authProvider, appCheckProvider, storage.getMaxUploadRetryTimeMillis());
    }

    @Override // com.google.firebase.storage.StorageTask
    protected void schedule() {
        StorageTaskScheduler.getInstance().scheduleUpload(getRunnable());
    }

    @Override // com.google.firebase.storage.StorageTask
    void run() {
        this.mSender.reset();
        if (!tryChangeState(4, false)) {
            Log.d(TAG, "The upload cannot continue as it is not in a valid state.");
            return;
        }
        if (this.mStorageRef.getParent() == null) {
            this.mException = new IllegalArgumentException("Cannot upload to getRoot. You should upload to a storage location such as .getReference('image.png').putFile...");
        }
        if (this.mException != null) {
            return;
        }
        if (this.mUploadUri == null) {
            beginResumableUpload();
        } else {
            recoverStatus(false);
        }
        boolean zShouldContinue = shouldContinue();
        while (zShouldContinue) {
            uploadChunk();
            zShouldContinue = shouldContinue();
            if (zShouldContinue) {
                tryChangeState(4, false);
            }
        }
        if (!this.mIsStreamOwned || getInternalState() == 16) {
            return;
        }
        try {
            this.mStreamBuffer.close();
        } catch (IOException e) {
            Log.e(TAG, "Unable to close stream.", e);
        }
    }

    private void beginResumableUpload() {
        String contentType = this.mMetadata != null ? this.mMetadata.getContentType() : null;
        if (this.mUri != null && TextUtils.isEmpty(contentType)) {
            contentType = this.mStorageRef.getStorage().getApp().getApplicationContext().getContentResolver().getType(this.mUri);
        }
        if (TextUtils.isEmpty(contentType)) {
            contentType = APPLICATION_OCTET_STREAM;
        }
        ResumableUploadStartRequest resumableUploadStartRequest = new ResumableUploadStartRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp(), this.mMetadata != null ? this.mMetadata.createJSONObject() : null, contentType);
        if (sendWithRetry(resumableUploadStartRequest)) {
            String resultString = resumableUploadStartRequest.getResultString(X_GOOG_UPLOAD_URL);
            if (TextUtils.isEmpty(resultString)) {
                return;
            }
            this.mUploadUri = Uri.parse(resultString);
        }
    }

    private boolean shouldContinue() {
        if (getInternalState() == 128) {
            return false;
        }
        if (Thread.interrupted()) {
            this.mException = new InterruptedException();
            tryChangeState(64, false);
            return false;
        }
        if (getInternalState() == 32) {
            tryChangeState(256, false);
            return false;
        }
        if (getInternalState() == 8) {
            tryChangeState(16, false);
            return false;
        }
        if (!serverStateValid()) {
            return false;
        }
        if (this.mUploadUri == null) {
            if (this.mException == null) {
                this.mException = new IllegalStateException("Unable to obtain an upload URL.");
            }
            tryChangeState(64, false);
            return false;
        }
        if (this.mException != null) {
            tryChangeState(64, false);
            return false;
        }
        boolean z = this.mServerException != null || this.mResultCode < 200 || this.mResultCode >= 300;
        long jElapsedRealtime = clock.elapsedRealtime() + this.maxSleepTime;
        long jElapsedRealtime2 = clock.elapsedRealtime() + this.sleepTime;
        if (z) {
            if (jElapsedRealtime2 > jElapsedRealtime || !recoverStatus(true)) {
                if (serverStateValid()) {
                    tryChangeState(64, false);
                }
                return false;
            }
            this.sleepTime = Math.max(this.sleepTime * 2, 1000);
        }
        return true;
    }

    private boolean serverStateValid() {
        if (!RESUMABLE_FINAL_STATUS.equals(this.mServerStatus)) {
            return true;
        }
        if (this.mException == null) {
            this.mException = new IOException("The server has terminated the upload session", this.mServerException);
        }
        tryChangeState(64, false);
        return false;
    }

    private boolean recoverStatus(boolean z) {
        ResumableUploadQueryRequest resumableUploadQueryRequest = new ResumableUploadQueryRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp(), this.mUploadUri);
        if (RESUMABLE_FINAL_STATUS.equals(this.mServerStatus)) {
            return false;
        }
        if (z) {
            if (!sendWithRetry(resumableUploadQueryRequest)) {
                return false;
            }
        } else if (!send(resumableUploadQueryRequest)) {
            return false;
        }
        if (RESUMABLE_FINAL_STATUS.equals(resumableUploadQueryRequest.getResultString("X-Goog-Upload-Status"))) {
            this.mException = new IOException("The server has terminated the upload session");
            return false;
        }
        String resultString = resumableUploadQueryRequest.getResultString("X-Goog-Upload-Size-Received");
        long j = !TextUtils.isEmpty(resultString) ? Long.parseLong(resultString) : 0L;
        long j2 = this.mBytesUploaded.get();
        if (j2 > j) {
            this.mException = new IOException("Unexpected error. The server lost a chunk update.");
            return false;
        }
        if (j2 >= j) {
            return true;
        }
        try {
            if (this.mStreamBuffer.advance((int) r7) != j - j2) {
                this.mException = new IOException("Unexpected end of stream encountered.");
                return false;
            }
            if (this.mBytesUploaded.compareAndSet(j2, j)) {
                return true;
            }
            Log.e(TAG, "Somehow, the uploaded bytes changed during an uploaded.  This should nothappen");
            this.mException = new IllegalStateException("uploaded bytes changed unexpectedly.");
            return false;
        } catch (IOException e) {
            Log.e(TAG, "Unable to recover position in Stream during resumable upload", e);
            this.mException = e;
            return false;
        }
    }

    private boolean delaySend(NetworkRequest networkRequest) throws ProtocolException {
        try {
            Log.d(TAG, "Waiting " + this.sleepTime + " milliseconds");
            sleeper.sleep(this.sleepTime + random.nextInt(250));
            boolean zSend = send(networkRequest);
            if (zSend) {
                this.sleepTime = 0;
            }
            return zSend;
        } catch (InterruptedException e) {
            Log.w(TAG, "thread interrupted during exponential backoff.");
            Thread.currentThread().interrupt();
            this.mServerException = e;
            return false;
        }
    }

    private void uploadChunk() {
        try {
            this.mStreamBuffer.fill(this.mCurrentChunkSize);
            int iMin = Math.min(this.mCurrentChunkSize, this.mStreamBuffer.available());
            ResumableUploadByteRequest resumableUploadByteRequest = new ResumableUploadByteRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp(), this.mUploadUri, this.mStreamBuffer.get(), this.mBytesUploaded.get(), iMin, this.mStreamBuffer.isFinished());
            if (!delaySend(resumableUploadByteRequest)) {
                this.mCurrentChunkSize = 262144;
                Log.d(TAG, "Resetting chunk size to " + this.mCurrentChunkSize);
                return;
            }
            this.mBytesUploaded.getAndAdd(iMin);
            if (!this.mStreamBuffer.isFinished()) {
                this.mStreamBuffer.advance(iMin);
                int i = this.mCurrentChunkSize;
                if (i < MAXIMUM_CHUNK_SIZE) {
                    this.mCurrentChunkSize = i * 2;
                    Log.d(TAG, "Increasing chunk size to " + this.mCurrentChunkSize);
                    return;
                }
                return;
            }
            try {
                this.mMetadata = new StorageMetadata.Builder(resumableUploadByteRequest.getResultBody(), this.mStorageRef).build();
                tryChangeState(4, false);
                tryChangeState(128, false);
            } catch (JSONException e) {
                Log.e(TAG, "Unable to parse resulting metadata from upload:" + resumableUploadByteRequest.getRawResult(), e);
                this.mException = e;
            }
        } catch (IOException e2) {
            Log.e(TAG, "Unable to read bytes for uploading", e2);
            this.mException = e2;
        }
    }

    private boolean send(NetworkRequest networkRequest) throws ProtocolException {
        networkRequest.performRequest(Util.getCurrentAuthToken(this.mAuthProvider), Util.getCurrentAppCheckToken(this.mAppCheckProvider), this.mStorageRef.getApp().getApplicationContext());
        return processResultValid(networkRequest);
    }

    private boolean sendWithRetry(NetworkRequest networkRequest) throws ProtocolException {
        this.mSender.sendWithExponentialBackoff(networkRequest);
        return processResultValid(networkRequest);
    }

    private boolean processResultValid(NetworkRequest networkRequest) {
        int resultCode = networkRequest.getResultCode();
        if (this.mSender.isRetryableError(resultCode)) {
            resultCode = -2;
        }
        this.mResultCode = resultCode;
        this.mServerException = networkRequest.getException();
        this.mServerStatus = networkRequest.getResultString("X-Goog-Upload-Status");
        return isValidHttpResponseCode(this.mResultCode) && this.mServerException == null;
    }

    @Override // com.google.firebase.storage.StorageTask
    protected void onCanceled() {
        this.mSender.cancel();
        final ResumableUploadCancelRequest resumableUploadCancelRequest = this.mUploadUri != null ? new ResumableUploadCancelRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp(), this.mUploadUri) : null;
        if (resumableUploadCancelRequest != null) {
            StorageTaskScheduler.getInstance().scheduleCommand(new Runnable() { // from class: com.google.firebase.storage.UploadTask.1
                @Override // java.lang.Runnable
                public void run() throws ProtocolException {
                    resumableUploadCancelRequest.performRequest(Util.getCurrentAuthToken(UploadTask.this.mAuthProvider), Util.getCurrentAppCheckToken(UploadTask.this.mAppCheckProvider), UploadTask.this.mStorageRef.getApp().getApplicationContext());
                }
            });
        }
        this.mException = StorageException.fromErrorStatus(Status.RESULT_CANCELED);
        super.onCanceled();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.storage.StorageTask
    public TaskSnapshot snapStateImpl() {
        return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(this.mException != null ? this.mException : this.mServerException, this.mResultCode), this.mBytesUploaded.get(), this.mUploadUri, this.mMetadata);
    }

    public class TaskSnapshot extends StorageTask<TaskSnapshot>.SnapshotBase {
        private final long mBytesUploaded;
        private final StorageMetadata mMetadata;
        private final Uri mUploadUri;

        public long getBytesTransferred() {
            return this.mBytesUploaded;
        }

        public StorageMetadata getMetadata() {
            return this.mMetadata;
        }

        public Uri getUploadSessionUri() {
            return this.mUploadUri;
        }

        TaskSnapshot(Exception exc, long j, Uri uri, StorageMetadata storageMetadata) {
            super(exc);
            this.mBytesUploaded = j;
            this.mUploadUri = uri;
            this.mMetadata = storageMetadata;
        }

        public long getTotalByteCount() {
            return UploadTask.this.getTotalByteCount();
        }
    }
}
