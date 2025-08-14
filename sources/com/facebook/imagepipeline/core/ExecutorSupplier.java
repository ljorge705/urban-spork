package com.facebook.imagepipeline.core;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nullable;

/* loaded from: classes5.dex */
public interface ExecutorSupplier {
    Executor forBackgroundTasks();

    Executor forDecode();

    Executor forLightweightBackgroundTasks();

    Executor forLocalStorageRead();

    Executor forLocalStorageWrite();

    Executor forThumbnailProducer();

    @Nullable
    ScheduledExecutorService scheduledExecutorServiceForBackgroundTasks();
}
