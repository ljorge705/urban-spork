package io.sentry.android.replay.util;

import io.sentry.ISentryExecutorService;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: Executors.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001aB\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000\u001a*\u0010\u0011\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0012*\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000\u001a*\u0010\u0011\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0012*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000¨\u0006\u0014"}, d2 = {"gracefullyShutdown", "", "Ljava/util/concurrent/ExecutorService;", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "scheduleAtFixedRateSafely", "Ljava/util/concurrent/ScheduledFuture;", "Ljava/util/concurrent/ScheduledExecutorService;", "taskName", "", "initialDelay", "", "period", "unit", "Ljava/util/concurrent/TimeUnit;", "task", "Ljava/lang/Runnable;", "submitSafely", "Ljava/util/concurrent/Future;", "Lio/sentry/ISentryExecutorService;", "sentry-android-replay_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ExecutorsKt {
    public static final void gracefullyShutdown(ExecutorService executorService, SentryOptions options) {
        Intrinsics.checkNotNullParameter(executorService, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        synchronized (executorService) {
            if (!executorService.isShutdown()) {
                executorService.shutdown();
            }
            try {
            } catch (InterruptedException unused) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
            if (!executorService.awaitTermination(options.getShutdownTimeoutMillis(), TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
                Unit unit = Unit.INSTANCE;
            } else {
                Unit unit2 = Unit.INSTANCE;
            }
        }
    }

    public static final Future<?> submitSafely(ISentryExecutorService iSentryExecutorService, final SentryOptions options, final String taskName, final Runnable task) {
        Intrinsics.checkNotNullParameter(iSentryExecutorService, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(taskName, "taskName");
        Intrinsics.checkNotNullParameter(task, "task");
        try {
            return iSentryExecutorService.submit(new Runnable() { // from class: io.sentry.android.replay.util.ExecutorsKt$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ExecutorsKt.submitSafely$lambda$1(task, options, taskName);
                }
            });
        } catch (Throwable th) {
            options.getLogger().log(SentryLevel.ERROR, "Failed to submit task " + taskName + " to executor", th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void submitSafely$lambda$1(Runnable task, SentryOptions options, String taskName) {
        Intrinsics.checkNotNullParameter(task, "$task");
        Intrinsics.checkNotNullParameter(options, "$options");
        Intrinsics.checkNotNullParameter(taskName, "$taskName");
        try {
            task.run();
        } catch (Throwable th) {
            options.getLogger().log(SentryLevel.ERROR, "Failed to execute task " + taskName, th);
        }
    }

    public static final Future<?> submitSafely(ExecutorService executorService, final SentryOptions options, final String taskName, final Runnable task) {
        Intrinsics.checkNotNullParameter(executorService, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(taskName, "taskName");
        Intrinsics.checkNotNullParameter(task, "task");
        String name = Thread.currentThread().getName();
        Intrinsics.checkNotNullExpressionValue(name, "currentThread().name");
        if (StringsKt.startsWith$default(name, "SentryReplayIntegration", false, 2, (Object) null)) {
            task.run();
            return null;
        }
        try {
            return executorService.submit(new Runnable() { // from class: io.sentry.android.replay.util.ExecutorsKt$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ExecutorsKt.submitSafely$lambda$2(task, options, taskName);
                }
            });
        } catch (Throwable th) {
            options.getLogger().log(SentryLevel.ERROR, "Failed to submit task " + taskName + " to executor", th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void submitSafely$lambda$2(Runnable task, SentryOptions options, String taskName) {
        Intrinsics.checkNotNullParameter(task, "$task");
        Intrinsics.checkNotNullParameter(options, "$options");
        Intrinsics.checkNotNullParameter(taskName, "$taskName");
        try {
            task.run();
        } catch (Throwable th) {
            options.getLogger().log(SentryLevel.ERROR, "Failed to execute task " + taskName, th);
        }
    }

    public static final ScheduledFuture<?> scheduleAtFixedRateSafely(ScheduledExecutorService scheduledExecutorService, final SentryOptions options, final String taskName, long j, long j2, TimeUnit unit, final Runnable task) {
        Intrinsics.checkNotNullParameter(scheduledExecutorService, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(taskName, "taskName");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(task, "task");
        try {
            return scheduledExecutorService.scheduleAtFixedRate(new Runnable() { // from class: io.sentry.android.replay.util.ExecutorsKt$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    ExecutorsKt.scheduleAtFixedRateSafely$lambda$3(task, options, taskName);
                }
            }, j, j2, unit);
        } catch (Throwable th) {
            options.getLogger().log(SentryLevel.ERROR, "Failed to submit task " + taskName + " to executor", th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scheduleAtFixedRateSafely$lambda$3(Runnable task, SentryOptions options, String taskName) {
        Intrinsics.checkNotNullParameter(task, "$task");
        Intrinsics.checkNotNullParameter(options, "$options");
        Intrinsics.checkNotNullParameter(taskName, "$taskName");
        try {
            task.run();
        } catch (Throwable th) {
            options.getLogger().log(SentryLevel.ERROR, "Failed to execute task " + taskName, th);
        }
    }
}
