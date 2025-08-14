package io.sentry.transport;

import io.sentry.DateUtils;
import io.sentry.Hint;
import io.sentry.ILogger;
import io.sentry.RequestDetails;
import io.sentry.SentryDateProvider;
import io.sentry.SentryEnvelope;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.UncaughtExceptionHandlerIntegration;
import io.sentry.cache.IEnvelopeCache;
import io.sentry.clientreport.DiscardReason;
import io.sentry.hints.Cached;
import io.sentry.hints.DiskFlushNotification;
import io.sentry.hints.Enqueable;
import io.sentry.hints.Retryable;
import io.sentry.hints.SubmissionResult;
import io.sentry.util.HintUtils;
import io.sentry.util.LogUtils;
import io.sentry.util.Objects;
import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public final class AsyncHttpTransport implements ITransport {
    private final HttpConnection connection;
    private volatile Runnable currentRunnable;
    private final IEnvelopeCache envelopeCache;
    private final QueuedThreadPoolExecutor executor;
    private final SentryOptions options;
    private final RateLimiter rateLimiter;
    private final ITransportGate transportGate;

    @Override // io.sentry.transport.ITransport
    public RateLimiter getRateLimiter() {
        return this.rateLimiter;
    }

    public AsyncHttpTransport(SentryOptions sentryOptions, RateLimiter rateLimiter, ITransportGate iTransportGate, RequestDetails requestDetails) {
        this(initExecutor(sentryOptions.getMaxQueueSize(), sentryOptions.getEnvelopeDiskCache(), sentryOptions.getLogger(), sentryOptions.getDateProvider()), sentryOptions, rateLimiter, iTransportGate, new HttpConnection(sentryOptions, requestDetails, rateLimiter));
    }

    public AsyncHttpTransport(QueuedThreadPoolExecutor queuedThreadPoolExecutor, SentryOptions sentryOptions, RateLimiter rateLimiter, ITransportGate iTransportGate, HttpConnection httpConnection) {
        this.currentRunnable = null;
        this.executor = (QueuedThreadPoolExecutor) Objects.requireNonNull(queuedThreadPoolExecutor, "executor is required");
        this.envelopeCache = (IEnvelopeCache) Objects.requireNonNull(sentryOptions.getEnvelopeDiskCache(), "envelopeCache is required");
        this.options = (SentryOptions) Objects.requireNonNull(sentryOptions, "options is required");
        this.rateLimiter = (RateLimiter) Objects.requireNonNull(rateLimiter, "rateLimiter is required");
        this.transportGate = (ITransportGate) Objects.requireNonNull(iTransportGate, "transportGate is required");
        this.connection = (HttpConnection) Objects.requireNonNull(httpConnection, "httpConnection is required");
    }

    @Override // io.sentry.transport.ITransport
    public void send(SentryEnvelope sentryEnvelope, Hint hint) throws IOException {
        IEnvelopeCache noOpEnvelopeCache = this.envelopeCache;
        boolean z = false;
        if (HintUtils.hasType(hint, Cached.class)) {
            noOpEnvelopeCache = NoOpEnvelopeCache.getInstance();
            this.options.getLogger().log(SentryLevel.DEBUG, "Captured Envelope is already cached", new Object[0]);
            z = true;
        }
        SentryEnvelope sentryEnvelopeFilter = this.rateLimiter.filter(sentryEnvelope, hint);
        if (sentryEnvelopeFilter == null) {
            if (z) {
                this.envelopeCache.discard(sentryEnvelope);
                return;
            }
            return;
        }
        if (HintUtils.hasType(hint, UncaughtExceptionHandlerIntegration.UncaughtExceptionHint.class)) {
            sentryEnvelopeFilter = this.options.getClientReportRecorder().attachReportToEnvelope(sentryEnvelopeFilter);
        }
        Future<?> futureSubmit = this.executor.submit(new EnvelopeSender(sentryEnvelopeFilter, hint, noOpEnvelopeCache));
        if (futureSubmit != null && futureSubmit.isCancelled()) {
            this.options.getClientReportRecorder().recordLostEnvelope(DiscardReason.QUEUE_OVERFLOW, sentryEnvelopeFilter);
        } else {
            HintUtils.runIfHasType(hint, Enqueable.class, new HintUtils.SentryConsumer() { // from class: io.sentry.transport.AsyncHttpTransport$$ExternalSyntheticLambda2
                @Override // io.sentry.util.HintUtils.SentryConsumer
                public final void accept(Object obj) {
                    this.f$0.m6085lambda$send$0$iosentrytransportAsyncHttpTransport((Enqueable) obj);
                }
            });
        }
    }

    /* renamed from: lambda$send$0$io-sentry-transport-AsyncHttpTransport, reason: not valid java name */
    /* synthetic */ void m6085lambda$send$0$iosentrytransportAsyncHttpTransport(Enqueable enqueable) {
        enqueable.markEnqueued();
        this.options.getLogger().log(SentryLevel.DEBUG, "Envelope enqueued", new Object[0]);
    }

    @Override // io.sentry.transport.ITransport
    public void flush(long j) {
        this.executor.waitTillIdle(j);
    }

    private static QueuedThreadPoolExecutor initExecutor(int i, final IEnvelopeCache iEnvelopeCache, final ILogger iLogger, SentryDateProvider sentryDateProvider) {
        return new QueuedThreadPoolExecutor(1, i, new AsyncConnectionThreadFactory(), new RejectedExecutionHandler() { // from class: io.sentry.transport.AsyncHttpTransport$$ExternalSyntheticLambda3
            @Override // java.util.concurrent.RejectedExecutionHandler
            public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                AsyncHttpTransport.lambda$initExecutor$1(iEnvelopeCache, iLogger, runnable, threadPoolExecutor);
            }
        }, iLogger, sentryDateProvider);
    }

    static /* synthetic */ void lambda$initExecutor$1(IEnvelopeCache iEnvelopeCache, ILogger iLogger, Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        if (runnable instanceof EnvelopeSender) {
            EnvelopeSender envelopeSender = (EnvelopeSender) runnable;
            if (!HintUtils.hasType(envelopeSender.hint, Cached.class)) {
                iEnvelopeCache.store(envelopeSender.envelope, envelopeSender.hint);
            }
            markHintWhenSendingFailed(envelopeSender.hint, true);
            iLogger.log(SentryLevel.WARNING, "Envelope rejected", new Object[0]);
        }
    }

    @Override // io.sentry.transport.ITransport
    public boolean isHealthy() {
        return (this.rateLimiter.isAnyRateLimitActive() || this.executor.didRejectRecently()) ? false : true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        close(false);
    }

    @Override // io.sentry.transport.ITransport
    public void close(boolean z) throws IOException {
        long flushTimeoutMillis;
        this.rateLimiter.close();
        this.executor.shutdown();
        this.options.getLogger().log(SentryLevel.DEBUG, "Shutting down", new Object[0]);
        if (z) {
            flushTimeoutMillis = 0;
        } else {
            try {
                flushTimeoutMillis = this.options.getFlushTimeoutMillis();
            } catch (InterruptedException unused) {
                this.options.getLogger().log(SentryLevel.DEBUG, "Thread interrupted while closing the connection.", new Object[0]);
                Thread.currentThread().interrupt();
                return;
            }
        }
        if (this.executor.awaitTermination(flushTimeoutMillis, TimeUnit.MILLISECONDS)) {
            return;
        }
        this.options.getLogger().log(SentryLevel.WARNING, "Failed to shutdown the async connection async sender  within " + flushTimeoutMillis + " ms. Trying to force it now.", new Object[0]);
        this.executor.shutdownNow();
        if (this.currentRunnable != null) {
            this.executor.getRejectedExecutionHandler().rejectedExecution(this.currentRunnable, this.executor);
        }
    }

    private static void markHintWhenSendingFailed(Hint hint, final boolean z) {
        HintUtils.runIfHasType(hint, SubmissionResult.class, new HintUtils.SentryConsumer() { // from class: io.sentry.transport.AsyncHttpTransport$$ExternalSyntheticLambda0
            @Override // io.sentry.util.HintUtils.SentryConsumer
            public final void accept(Object obj) {
                ((SubmissionResult) obj).setResult(false);
            }
        });
        HintUtils.runIfHasType(hint, Retryable.class, new HintUtils.SentryConsumer() { // from class: io.sentry.transport.AsyncHttpTransport$$ExternalSyntheticLambda1
            @Override // io.sentry.util.HintUtils.SentryConsumer
            public final void accept(Object obj) {
                ((Retryable) obj).setRetry(z);
            }
        });
    }

    private static final class AsyncConnectionThreadFactory implements ThreadFactory {
        private int cnt;

        private AsyncConnectionThreadFactory() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            StringBuilder sb = new StringBuilder("SentryAsyncConnection-");
            int i = this.cnt;
            this.cnt = i + 1;
            Thread thread = new Thread(runnable, sb.append(i).toString());
            thread.setDaemon(true);
            return thread;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class EnvelopeSender implements Runnable {
        private final SentryEnvelope envelope;
        private final IEnvelopeCache envelopeCache;
        private final TransportResult failedResult = TransportResult.error();
        private final Hint hint;

        EnvelopeSender(SentryEnvelope sentryEnvelope, Hint hint, IEnvelopeCache iEnvelopeCache) {
            this.envelope = (SentryEnvelope) Objects.requireNonNull(sentryEnvelope, "Envelope is required.");
            this.hint = hint;
            this.envelopeCache = (IEnvelopeCache) Objects.requireNonNull(iEnvelopeCache, "EnvelopeCache is required.");
        }

        @Override // java.lang.Runnable
        public void run() {
            AsyncHttpTransport.this.currentRunnable = this;
            final TransportResult transportResultFlush = this.failedResult;
            try {
                transportResultFlush = flush();
                AsyncHttpTransport.this.options.getLogger().log(SentryLevel.DEBUG, "Envelope flushed", new Object[0]);
            } finally {
            }
        }

        /* renamed from: lambda$run$0$io-sentry-transport-AsyncHttpTransport$EnvelopeSender, reason: not valid java name */
        /* synthetic */ void m6090x71a92bcf(TransportResult transportResult, SubmissionResult submissionResult) {
            AsyncHttpTransport.this.options.getLogger().log(SentryLevel.DEBUG, "Marking envelope submission result: %s", Boolean.valueOf(transportResult.isSuccess()));
            submissionResult.setResult(transportResult.isSuccess());
        }

        private TransportResult flush() {
            TransportResult transportResult = this.failedResult;
            this.envelope.getHeader().setSentAt(null);
            this.envelopeCache.store(this.envelope, this.hint);
            HintUtils.runIfHasType(this.hint, DiskFlushNotification.class, new HintUtils.SentryConsumer() { // from class: io.sentry.transport.AsyncHttpTransport$EnvelopeSender$$ExternalSyntheticLambda1
                @Override // io.sentry.util.HintUtils.SentryConsumer
                public final void accept(Object obj) {
                    this.f$0.m6086xad3aa5c9((DiskFlushNotification) obj);
                }
            });
            if (AsyncHttpTransport.this.transportGate.isConnected()) {
                final SentryEnvelope sentryEnvelopeAttachReportToEnvelope = AsyncHttpTransport.this.options.getClientReportRecorder().attachReportToEnvelope(this.envelope);
                try {
                    sentryEnvelopeAttachReportToEnvelope.getHeader().setSentAt(DateUtils.nanosToDate(AsyncHttpTransport.this.options.getDateProvider().now().nanoTimestamp()));
                    TransportResult transportResultSend = AsyncHttpTransport.this.connection.send(sentryEnvelopeAttachReportToEnvelope);
                    if (transportResultSend.isSuccess()) {
                        this.envelopeCache.discard(this.envelope);
                        return transportResultSend;
                    }
                    String str = "The transport failed to send the envelope with response code " + transportResultSend.getResponseCode();
                    AsyncHttpTransport.this.options.getLogger().log(SentryLevel.ERROR, str, new Object[0]);
                    if (transportResultSend.getResponseCode() >= 400 && transportResultSend.getResponseCode() != 429) {
                        HintUtils.runIfDoesNotHaveType(this.hint, Retryable.class, new HintUtils.SentryNullableConsumer() { // from class: io.sentry.transport.AsyncHttpTransport$EnvelopeSender$$ExternalSyntheticLambda2
                            @Override // io.sentry.util.HintUtils.SentryNullableConsumer
                            public final void accept(Object obj) {
                                this.f$0.m6087xd68efb0a(sentryEnvelopeAttachReportToEnvelope, obj);
                            }
                        });
                    }
                    throw new IllegalStateException(str);
                } catch (IOException e) {
                    HintUtils.runIfHasType(this.hint, Retryable.class, new HintUtils.SentryConsumer() { // from class: io.sentry.transport.AsyncHttpTransport$EnvelopeSender$$ExternalSyntheticLambda3
                        @Override // io.sentry.util.HintUtils.SentryConsumer
                        public final void accept(Object obj) {
                            ((Retryable) obj).setRetry(true);
                        }
                    }, new HintUtils.SentryHintFallback() { // from class: io.sentry.transport.AsyncHttpTransport$EnvelopeSender$$ExternalSyntheticLambda4
                        @Override // io.sentry.util.HintUtils.SentryHintFallback
                        public final void accept(Object obj, Class cls) {
                            this.f$0.m6088x2937a58c(sentryEnvelopeAttachReportToEnvelope, obj, cls);
                        }
                    });
                    throw new IllegalStateException("Sending the event failed.", e);
                }
            }
            HintUtils.runIfHasType(this.hint, Retryable.class, new HintUtils.SentryConsumer() { // from class: io.sentry.transport.AsyncHttpTransport$EnvelopeSender$$ExternalSyntheticLambda5
                @Override // io.sentry.util.HintUtils.SentryConsumer
                public final void accept(Object obj) {
                    ((Retryable) obj).setRetry(true);
                }
            }, new HintUtils.SentryHintFallback() { // from class: io.sentry.transport.AsyncHttpTransport$EnvelopeSender$$ExternalSyntheticLambda6
                @Override // io.sentry.util.HintUtils.SentryHintFallback
                public final void accept(Object obj, Class cls) {
                    this.f$0.m6089x7be0500e(obj, cls);
                }
            });
            return transportResult;
        }

        /* renamed from: lambda$flush$1$io-sentry-transport-AsyncHttpTransport$EnvelopeSender, reason: not valid java name */
        /* synthetic */ void m6086xad3aa5c9(DiskFlushNotification diskFlushNotification) {
            if (!diskFlushNotification.isFlushable(this.envelope.getHeader().getEventId())) {
                AsyncHttpTransport.this.options.getLogger().log(SentryLevel.DEBUG, "Not firing envelope flush as there's an ongoing transaction", new Object[0]);
            } else {
                diskFlushNotification.markFlushed();
                AsyncHttpTransport.this.options.getLogger().log(SentryLevel.DEBUG, "Disk flush envelope fired", new Object[0]);
            }
        }

        /* renamed from: lambda$flush$2$io-sentry-transport-AsyncHttpTransport$EnvelopeSender, reason: not valid java name */
        /* synthetic */ void m6087xd68efb0a(SentryEnvelope sentryEnvelope, Object obj) {
            AsyncHttpTransport.this.options.getClientReportRecorder().recordLostEnvelope(DiscardReason.NETWORK_ERROR, sentryEnvelope);
        }

        /* renamed from: lambda$flush$4$io-sentry-transport-AsyncHttpTransport$EnvelopeSender, reason: not valid java name */
        /* synthetic */ void m6088x2937a58c(SentryEnvelope sentryEnvelope, Object obj, Class cls) {
            LogUtils.logNotInstanceOf(cls, obj, AsyncHttpTransport.this.options.getLogger());
            AsyncHttpTransport.this.options.getClientReportRecorder().recordLostEnvelope(DiscardReason.NETWORK_ERROR, sentryEnvelope);
        }

        /* renamed from: lambda$flush$6$io-sentry-transport-AsyncHttpTransport$EnvelopeSender, reason: not valid java name */
        /* synthetic */ void m6089x7be0500e(Object obj, Class cls) {
            LogUtils.logNotInstanceOf(cls, obj, AsyncHttpTransport.this.options.getLogger());
            AsyncHttpTransport.this.options.getClientReportRecorder().recordLostEnvelope(DiscardReason.NETWORK_ERROR, this.envelope);
        }
    }
}
