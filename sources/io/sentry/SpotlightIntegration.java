package io.sentry;

import com.clevertap.android.sdk.Constants;
import com.google.common.net.HttpHeaders;
import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository;
import io.grpc.internal.GrpcUtil;
import io.sentry.SentryOptions;
import io.sentry.util.Platform;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.concurrent.RejectedExecutionException;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes6.dex */
public final class SpotlightIntegration implements Integration, SentryOptions.BeforeEnvelopeCallback, Closeable {
    private SentryOptions options;
    private ILogger logger = NoOpLogger.getInstance();
    private ISentryExecutorService executorService = NoOpSentryExecutorService.getInstance();

    @Override // io.sentry.Integration
    public void register(IHub iHub, SentryOptions sentryOptions) {
        this.options = sentryOptions;
        this.logger = sentryOptions.getLogger();
        if (sentryOptions.getBeforeEnvelopeCallback() == null && sentryOptions.isEnableSpotlight()) {
            this.executorService = new SentryExecutorService();
            sentryOptions.setBeforeEnvelopeCallback(this);
            this.logger.log(SentryLevel.DEBUG, "SpotlightIntegration enabled.", new Object[0]);
            return;
        }
        this.logger.log(SentryLevel.DEBUG, "SpotlightIntegration is not enabled. BeforeEnvelopeCallback is already set or spotlight is not enabled.", new Object[0]);
    }

    @Override // io.sentry.SentryOptions.BeforeEnvelopeCallback
    public void execute(final SentryEnvelope sentryEnvelope, Hint hint) {
        try {
            this.executorService.submit(new Runnable() { // from class: io.sentry.SpotlightIntegration$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() throws IOException {
                    this.f$0.m5997lambda$execute$0$iosentrySpotlightIntegration(sentryEnvelope);
                }
            });
        } catch (RejectedExecutionException e) {
            this.logger.log(SentryLevel.WARNING, "Spotlight envelope submission rejected.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendEnvelope, reason: merged with bridge method [inline-methods] */
    public void m5997lambda$execute$0$iosentrySpotlightIntegration(SentryEnvelope sentryEnvelope) throws IOException {
        try {
            if (this.options == null) {
                throw new IllegalArgumentException("SentryOptions are required to send envelopes.");
            }
            HttpURLConnection httpURLConnectionCreateConnection = createConnection(getSpotlightConnectionUrl());
            try {
                OutputStream outputStream = httpURLConnectionCreateConnection.getOutputStream();
                try {
                    GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
                    try {
                        this.options.getSerializer().serialize(sentryEnvelope, gZIPOutputStream);
                        gZIPOutputStream.close();
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        this.logger.log(SentryLevel.DEBUG, "Envelope sent to spotlight: %d", Integer.valueOf(httpURLConnectionCreateConnection.getResponseCode()));
                    } finally {
                    }
                } finally {
                }
            } catch (Throwable th) {
                try {
                    this.logger.log(SentryLevel.ERROR, "An exception occurred while submitting the envelope to the Sentry server.", th);
                    this.logger.log(SentryLevel.DEBUG, "Envelope sent to spotlight: %d", Integer.valueOf(httpURLConnectionCreateConnection.getResponseCode()));
                } catch (Throwable th2) {
                    this.logger.log(SentryLevel.DEBUG, "Envelope sent to spotlight: %d", Integer.valueOf(httpURLConnectionCreateConnection.getResponseCode()));
                    closeAndDisconnect(httpURLConnectionCreateConnection);
                    throw th2;
                }
            }
            closeAndDisconnect(httpURLConnectionCreateConnection);
        } catch (Exception e) {
            this.logger.log(SentryLevel.ERROR, "An exception occurred while creating the connection to spotlight.", e);
        }
    }

    public String getSpotlightConnectionUrl() {
        SentryOptions sentryOptions = this.options;
        if (sentryOptions == null || sentryOptions.getSpotlightConnectionUrl() == null) {
            return Platform.isAndroid() ? "http://10.0.2.2:8969/stream" : "http://localhost:8969/stream";
        }
        return this.options.getSpotlightConnectionUrl();
    }

    private HttpURLConnection createConnection(String str) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) URI.create(str).toURL().openConnection();
        httpURLConnection.setReadTimeout(1000);
        httpURLConnection.setConnectTimeout(1000);
        httpURLConnection.setRequestMethod(GrpcUtil.HTTP_METHOD);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_ENCODING, "gzip");
        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/x-sentry-envelope");
        httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT, NfcDataRepository.FILE_TYPE_JSON);
        httpURLConnection.setRequestProperty(HttpHeaders.CONNECTION, Constants.KEY_HIDE_CLOSE);
        httpURLConnection.connect();
        return httpURLConnection;
    }

    private void closeAndDisconnect(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.getInputStream().close();
        } catch (IOException unused) {
        } catch (Throwable th) {
            httpURLConnection.disconnect();
            throw th;
        }
        httpURLConnection.disconnect();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.executorService.close(0L);
        SentryOptions sentryOptions = this.options;
        if (sentryOptions == null || sentryOptions.getBeforeEnvelopeCallback() != this) {
            return;
        }
        this.options.setBeforeEnvelopeCallback(null);
    }
}
