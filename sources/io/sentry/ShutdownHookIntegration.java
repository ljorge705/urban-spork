package io.sentry;

import io.sentry.util.IntegrationUtils;
import io.sentry.util.Objects;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes6.dex */
public final class ShutdownHookIntegration implements Integration, Closeable {
    private final Runtime runtime;
    private Thread thread;

    Thread getHook() {
        return this.thread;
    }

    public ShutdownHookIntegration(Runtime runtime) {
        this.runtime = (Runtime) Objects.requireNonNull(runtime, "Runtime is required");
    }

    public ShutdownHookIntegration() {
        this(Runtime.getRuntime());
    }

    @Override // io.sentry.Integration
    public void register(final IHub iHub, final SentryOptions sentryOptions) {
        Objects.requireNonNull(iHub, "Hub is required");
        Objects.requireNonNull(sentryOptions, "SentryOptions is required");
        if (sentryOptions.isEnableShutdownHook()) {
            this.thread = new Thread(new Runnable() { // from class: io.sentry.ShutdownHookIntegration$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    iHub.flush(sentryOptions.getFlushTimeoutMillis());
                }
            });
            handleShutdownInProgress(new Runnable() { // from class: io.sentry.ShutdownHookIntegration$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m5996lambda$register$1$iosentryShutdownHookIntegration(sentryOptions);
                }
            });
        } else {
            sentryOptions.getLogger().log(SentryLevel.INFO, "enableShutdownHook is disabled.", new Object[0]);
        }
    }

    /* renamed from: lambda$register$1$io-sentry-ShutdownHookIntegration, reason: not valid java name */
    /* synthetic */ void m5996lambda$register$1$iosentryShutdownHookIntegration(SentryOptions sentryOptions) {
        this.runtime.addShutdownHook(this.thread);
        sentryOptions.getLogger().log(SentryLevel.DEBUG, "ShutdownHookIntegration installed.", new Object[0]);
        IntegrationUtils.addIntegrationToSdkVersion("ShutdownHook");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.thread != null) {
            handleShutdownInProgress(new Runnable() { // from class: io.sentry.ShutdownHookIntegration$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m5995lambda$close$2$iosentryShutdownHookIntegration();
                }
            });
        }
    }

    /* renamed from: lambda$close$2$io-sentry-ShutdownHookIntegration, reason: not valid java name */
    /* synthetic */ void m5995lambda$close$2$iosentryShutdownHookIntegration() {
        this.runtime.removeShutdownHook(this.thread);
    }

    private void handleShutdownInProgress(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalStateException e) {
            String message = e.getMessage();
            if (message == null || !(message.equals("Shutdown in progress") || message.equals("VM already shutting down"))) {
                throw e;
            }
        }
    }
}
