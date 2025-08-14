package com.clevertap.android.sdk.task;

import androidx.profileinstaller.ProfileInstallReceiver$$ExternalSyntheticLambda0;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.utils.UrlHashGenerator;
import java.util.HashMap;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public class CTExecutors {
    public final Executor CURRENT_THREAD_EXECUTOR;
    public final MainThreadExecutor DEFAULT_CALLBACK_EXECUTOR;
    public final IOExecutor IO_EXECUTOR;
    public final MainThreadExecutor MAIN_EXECUTOR;
    protected final CleverTapInstanceConfig config;
    private final HashMap<String, PostAsyncSafelyExecutor> postAsyncSafelyTasks;
    protected String singleThreadExecutorTag;

    CTExecutors(CleverTapInstanceConfig cleverTapInstanceConfig) {
        MainThreadExecutor mainThreadExecutor = new MainThreadExecutor();
        this.MAIN_EXECUTOR = mainThreadExecutor;
        this.CURRENT_THREAD_EXECUTOR = new ProfileInstallReceiver$$ExternalSyntheticLambda0();
        this.DEFAULT_CALLBACK_EXECUTOR = mainThreadExecutor;
        this.postAsyncSafelyTasks = new HashMap<>();
        this.config = cleverTapInstanceConfig;
        this.IO_EXECUTOR = new IOExecutor();
    }

    CTExecutors(int i) {
        MainThreadExecutor mainThreadExecutor = new MainThreadExecutor();
        this.MAIN_EXECUTOR = mainThreadExecutor;
        this.CURRENT_THREAD_EXECUTOR = new ProfileInstallReceiver$$ExternalSyntheticLambda0();
        this.DEFAULT_CALLBACK_EXECUTOR = mainThreadExecutor;
        this.postAsyncSafelyTasks = new HashMap<>();
        this.config = null;
        this.IO_EXECUTOR = new IOExecutor(i);
        this.singleThreadExecutorTag = UrlHashGenerator.INSTANCE.hashWithTsSeed();
    }

    CTExecutors() {
        MainThreadExecutor mainThreadExecutor = new MainThreadExecutor();
        this.MAIN_EXECUTOR = mainThreadExecutor;
        this.CURRENT_THREAD_EXECUTOR = new ProfileInstallReceiver$$ExternalSyntheticLambda0();
        this.DEFAULT_CALLBACK_EXECUTOR = mainThreadExecutor;
        this.postAsyncSafelyTasks = new HashMap<>();
        this.config = null;
        this.IO_EXECUTOR = new IOExecutor();
        this.singleThreadExecutorTag = UrlHashGenerator.INSTANCE.hashWithTsSeed();
    }

    public <TResult> Task<TResult> ioTask() {
        return taskOnExecutorWithName(this.IO_EXECUTOR, this.DEFAULT_CALLBACK_EXECUTOR, "ioTask");
    }

    public <TResult> Task<TResult> ioTaskNonUi() {
        IOExecutor iOExecutor = this.IO_EXECUTOR;
        return taskOnExecutorWithName(iOExecutor, iOExecutor, "ioTaskNonUi");
    }

    public <TResult> Task<TResult> ioTaskWithCallbackOnCurrentThread() {
        return taskOnExecutorWithName(this.IO_EXECUTOR, this.CURRENT_THREAD_EXECUTOR, "ioTaskWithCallbackOnCurrentThread");
    }

    public <TResult> Task<TResult> mainTask() {
        return taskOnExecutorWithName(this.MAIN_EXECUTOR, this.DEFAULT_CALLBACK_EXECUTOR, "Main");
    }

    public <TResult> Task<TResult> postAsyncSafelyTask(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Tag can't be null");
        }
        PostAsyncSafelyExecutor postAsyncSafelyExecutor = this.postAsyncSafelyTasks.get(str);
        if (postAsyncSafelyExecutor == null) {
            postAsyncSafelyExecutor = new PostAsyncSafelyExecutor();
            this.postAsyncSafelyTasks.put(str, postAsyncSafelyExecutor);
        }
        return taskOnExecutorWithName(postAsyncSafelyExecutor, this.DEFAULT_CALLBACK_EXECUTOR, "PostAsyncSafely");
    }

    public <TResult> Task<TResult> postAsyncSafelyTask() {
        CleverTapInstanceConfig cleverTapInstanceConfig = this.config;
        return postAsyncSafelyTask(cleverTapInstanceConfig != null ? cleverTapInstanceConfig.getAccountId() : this.singleThreadExecutorTag);
    }

    public <TResult> Task<TResult> taskOnExecutor(Executor executor, String str) {
        return taskOnExecutorWithName(executor, this.DEFAULT_CALLBACK_EXECUTOR, str);
    }

    public <TResult> Task<TResult> taskOnExecutorWithName(Executor executor, Executor executor2, String str) {
        if (executor == null || executor2 == null) {
            throw new IllegalArgumentException("Can't create task " + str + " with null executors");
        }
        return new Task<>(this.config, executor, executor2, str);
    }
}
