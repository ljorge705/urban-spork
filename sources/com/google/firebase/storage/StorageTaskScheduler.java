package com.google.firebase.storage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class StorageTaskScheduler {
    private static final ThreadPoolExecutor CALLBACK_QUEUE_EXECUTOR;
    private static final ThreadPoolExecutor COMMAND_POOL_EXECUTOR;
    private static final ThreadPoolExecutor DOWNLOAD_QUEUE_EXECUTOR;
    private static final ThreadPoolExecutor UPLOAD_QUEUE_EXECUTOR;
    private static BlockingQueue<Runnable> mCallbackQueue;
    private static BlockingQueue<Runnable> mDownloadQueue;
    private static BlockingQueue<Runnable> mUploadQueue;
    public static StorageTaskScheduler sInstance = new StorageTaskScheduler();
    private static BlockingQueue<Runnable> mCommandQueue = new LinkedBlockingQueue();

    public static StorageTaskScheduler getInstance() {
        return sInstance;
    }

    public Executor getCommandPoolExecutor() {
        return COMMAND_POOL_EXECUTOR;
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 5L, TimeUnit.SECONDS, mCommandQueue, new StorageThreadFactory("Command-"));
        COMMAND_POOL_EXECUTOR = threadPoolExecutor;
        mUploadQueue = new LinkedBlockingQueue();
        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(2, 2, 5L, TimeUnit.SECONDS, mUploadQueue, new StorageThreadFactory("Upload-"));
        UPLOAD_QUEUE_EXECUTOR = threadPoolExecutor2;
        mDownloadQueue = new LinkedBlockingQueue();
        ThreadPoolExecutor threadPoolExecutor3 = new ThreadPoolExecutor(3, 3, 5L, TimeUnit.SECONDS, mDownloadQueue, new StorageThreadFactory("Download-"));
        DOWNLOAD_QUEUE_EXECUTOR = threadPoolExecutor3;
        mCallbackQueue = new LinkedBlockingQueue();
        ThreadPoolExecutor threadPoolExecutor4 = new ThreadPoolExecutor(1, 1, 5L, TimeUnit.SECONDS, mCallbackQueue, new StorageThreadFactory("Callbacks-"));
        CALLBACK_QUEUE_EXECUTOR = threadPoolExecutor4;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor2.allowCoreThreadTimeOut(true);
        threadPoolExecutor3.allowCoreThreadTimeOut(true);
        threadPoolExecutor4.allowCoreThreadTimeOut(true);
    }

    public static void setCallbackQueueKeepAlive(long j, TimeUnit timeUnit) {
        CALLBACK_QUEUE_EXECUTOR.setKeepAliveTime(j, timeUnit);
    }

    public void scheduleCommand(Runnable runnable) {
        COMMAND_POOL_EXECUTOR.execute(runnable);
    }

    public void scheduleUpload(Runnable runnable) {
        UPLOAD_QUEUE_EXECUTOR.execute(runnable);
    }

    public void scheduleDownload(Runnable runnable) {
        DOWNLOAD_QUEUE_EXECUTOR.execute(runnable);
    }

    public void scheduleCallback(Runnable runnable) {
        CALLBACK_QUEUE_EXECUTOR.execute(runnable);
    }

    static class StorageThreadFactory implements ThreadFactory {
        private final String mNameSuffix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        StorageThreadFactory(String str) {
            this.mNameSuffix = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "FirebaseStorage-" + this.mNameSuffix + this.threadNumber.getAndIncrement());
            thread.setDaemon(false);
            thread.setPriority(9);
            return thread;
        }
    }
}
