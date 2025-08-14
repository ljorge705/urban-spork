package io.sentry.instrumentation.file;

import io.sentry.IHub;
import io.sentry.ISpan;
import io.sentry.SentryIntegrationPackageStorage;
import io.sentry.SentryOptions;
import io.sentry.SentryStackTraceFactory;
import io.sentry.SpanDataConvention;
import io.sentry.SpanStatus;
import io.sentry.util.Platform;
import io.sentry.util.StringUtils;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/* loaded from: classes6.dex */
final class FileIOSpanManager {
    private long byteCount;
    private final ISpan currentSpan;
    private final File file;
    private final SentryOptions options;
    private SpanStatus spanStatus = SpanStatus.OK;
    private final SentryStackTraceFactory stackTraceFactory;

    @FunctionalInterface
    interface FileIOCallable<T> {
        T call() throws IOException;
    }

    static ISpan startSpan(IHub iHub, String str) {
        ISpan transaction = Platform.isAndroid() ? iHub.getTransaction() : iHub.getSpan();
        if (transaction != null) {
            return transaction.startChild(str);
        }
        return null;
    }

    FileIOSpanManager(ISpan iSpan, File file, SentryOptions sentryOptions) {
        this.currentSpan = iSpan;
        this.file = file;
        this.options = sentryOptions;
        this.stackTraceFactory = new SentryStackTraceFactory(sentryOptions);
        SentryIntegrationPackageStorage.getInstance().addIntegration("FileIO");
    }

    /* JADX WARN: Multi-variable type inference failed */
    <T> T performIO(FileIOCallable<T> fileIOCallable) throws IOException {
        try {
            T tCall = fileIOCallable.call();
            if (tCall instanceof Integer) {
                int iIntValue = ((Integer) tCall).intValue();
                if (iIntValue != -1) {
                    this.byteCount += iIntValue;
                }
            } else if (tCall instanceof Long) {
                long jLongValue = ((Long) tCall).longValue();
                if (jLongValue != -1) {
                    this.byteCount += jLongValue;
                }
            }
            return tCall;
        } catch (IOException e) {
            this.spanStatus = SpanStatus.INTERNAL_ERROR;
            ISpan iSpan = this.currentSpan;
            if (iSpan != null) {
                iSpan.setThrowable(e);
            }
            throw e;
        }
    }

    void finish(Closeable closeable) throws IOException {
        try {
            try {
                closeable.close();
            } catch (IOException e) {
                this.spanStatus = SpanStatus.INTERNAL_ERROR;
                if (this.currentSpan != null) {
                    this.currentSpan.setThrowable(e);
                }
                throw e;
            }
        } finally {
            finishSpan();
        }
    }

    private void finishSpan() {
        if (this.currentSpan != null) {
            String strByteCountToString = StringUtils.byteCountToString(this.byteCount);
            if (this.file != null) {
                this.currentSpan.setDescription(this.file.getName() + " (" + strByteCountToString + ")");
                if (Platform.isAndroid() || this.options.isSendDefaultPii()) {
                    this.currentSpan.setData("file.path", this.file.getAbsolutePath());
                }
            } else {
                this.currentSpan.setDescription(strByteCountToString);
            }
            this.currentSpan.setData("file.size", Long.valueOf(this.byteCount));
            boolean zIsMainThread = this.options.getMainThreadChecker().isMainThread();
            this.currentSpan.setData(SpanDataConvention.BLOCKED_MAIN_THREAD_KEY, Boolean.valueOf(zIsMainThread));
            if (zIsMainThread) {
                this.currentSpan.setData(SpanDataConvention.CALL_STACK_KEY, this.stackTraceFactory.getInAppCallStack());
            }
            this.currentSpan.finish(this.spanStatus);
        }
    }
}
