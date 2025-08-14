package io.sentry;

import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository;
import io.sentry.clientreport.ClientReport;
import io.sentry.exception.SentryEnvelopeException;
import io.sentry.metrics.EncodedMetrics;
import io.sentry.protocol.SentryTransaction;
import io.sentry.util.FileUtils;
import io.sentry.util.JsonSerializationUtils;
import io.sentry.util.Objects;
import io.sentry.vendor.Base64;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* loaded from: classes6.dex */
public final class SentryEnvelopeItem {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private byte[] data;
    private final Callable<byte[]> dataFactory;
    private final SentryEnvelopeItemHeader header;

    public SentryEnvelopeItemHeader getHeader() {
        return this.header;
    }

    SentryEnvelopeItem(SentryEnvelopeItemHeader sentryEnvelopeItemHeader, byte[] bArr) {
        this.header = (SentryEnvelopeItemHeader) Objects.requireNonNull(sentryEnvelopeItemHeader, "SentryEnvelopeItemHeader is required.");
        this.data = bArr;
        this.dataFactory = null;
    }

    SentryEnvelopeItem(SentryEnvelopeItemHeader sentryEnvelopeItemHeader, Callable<byte[]> callable) {
        this.header = (SentryEnvelopeItemHeader) Objects.requireNonNull(sentryEnvelopeItemHeader, "SentryEnvelopeItemHeader is required.");
        this.dataFactory = (Callable) Objects.requireNonNull(callable, "DataFactory is required.");
        this.data = null;
    }

    public byte[] getData() throws Exception {
        Callable<byte[]> callable;
        if (this.data == null && (callable = this.dataFactory) != null) {
            this.data = callable.call();
        }
        return this.data;
    }

    public static SentryEnvelopeItem fromSession(final ISerializer iSerializer, final Session session) throws IOException {
        Objects.requireNonNull(iSerializer, "ISerializer is required.");
        Objects.requireNonNull(session, "Session is required.");
        final CachedItem cachedItem = new CachedItem(new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda3
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SentryEnvelopeItem.lambda$fromSession$0(iSerializer, session);
            }
        });
        return new SentryEnvelopeItem(new SentryEnvelopeItemHeader(SentryItemType.Session, new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Integer.valueOf(cachedItem.getBytes().length);
            }
        }, NfcDataRepository.FILE_TYPE_JSON, null), (Callable<byte[]>) new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda5
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return cachedItem.getBytes();
            }
        });
    }

    static /* synthetic */ byte[] lambda$fromSession$0(ISerializer iSerializer, Session session) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, UTF_8));
            try {
                iSerializer.serialize((ISerializer) session, (Writer) bufferedWriter);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                bufferedWriter.close();
                byteArrayOutputStream.close();
                return byteArray;
            } finally {
            }
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public SentryEvent getEvent(ISerializer iSerializer) throws Exception {
        SentryEnvelopeItemHeader sentryEnvelopeItemHeader = this.header;
        if (sentryEnvelopeItemHeader == null || sentryEnvelopeItemHeader.getType() != SentryItemType.Event) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(getData()), UTF_8));
        try {
            SentryEvent sentryEvent = (SentryEvent) iSerializer.deserialize(bufferedReader, SentryEvent.class);
            bufferedReader.close();
            return sentryEvent;
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static SentryEnvelopeItem fromEvent(final ISerializer iSerializer, final SentryBaseEvent sentryBaseEvent) {
        Objects.requireNonNull(iSerializer, "ISerializer is required.");
        Objects.requireNonNull(sentryBaseEvent, "SentryEvent is required.");
        final CachedItem cachedItem = new CachedItem(new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda20
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SentryEnvelopeItem.lambda$fromEvent$3(iSerializer, sentryBaseEvent);
            }
        });
        return new SentryEnvelopeItem(new SentryEnvelopeItemHeader(SentryItemType.resolve(sentryBaseEvent), new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda21
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Integer.valueOf(cachedItem.getBytes().length);
            }
        }, NfcDataRepository.FILE_TYPE_JSON, null), (Callable<byte[]>) new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda22
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return cachedItem.getBytes();
            }
        });
    }

    static /* synthetic */ byte[] lambda$fromEvent$3(ISerializer iSerializer, SentryBaseEvent sentryBaseEvent) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, UTF_8));
            try {
                iSerializer.serialize((ISerializer) sentryBaseEvent, (Writer) bufferedWriter);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                bufferedWriter.close();
                byteArrayOutputStream.close();
                return byteArray;
            } finally {
            }
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public SentryTransaction getTransaction(ISerializer iSerializer) throws Exception {
        SentryEnvelopeItemHeader sentryEnvelopeItemHeader = this.header;
        if (sentryEnvelopeItemHeader == null || sentryEnvelopeItemHeader.getType() != SentryItemType.Transaction) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(getData()), UTF_8));
        try {
            SentryTransaction sentryTransaction = (SentryTransaction) iSerializer.deserialize(bufferedReader, SentryTransaction.class);
            bufferedReader.close();
            return sentryTransaction;
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static SentryEnvelopeItem fromUserFeedback(final ISerializer iSerializer, final UserFeedback userFeedback) {
        Objects.requireNonNull(iSerializer, "ISerializer is required.");
        Objects.requireNonNull(userFeedback, "UserFeedback is required.");
        final CachedItem cachedItem = new CachedItem(new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda26
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SentryEnvelopeItem.lambda$fromUserFeedback$6(iSerializer, userFeedback);
            }
        });
        return new SentryEnvelopeItem(new SentryEnvelopeItemHeader(SentryItemType.UserFeedback, new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Integer.valueOf(cachedItem.getBytes().length);
            }
        }, NfcDataRepository.FILE_TYPE_JSON, null), (Callable<byte[]>) new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return cachedItem.getBytes();
            }
        });
    }

    static /* synthetic */ byte[] lambda$fromUserFeedback$6(ISerializer iSerializer, UserFeedback userFeedback) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, UTF_8));
            try {
                iSerializer.serialize((ISerializer) userFeedback, (Writer) bufferedWriter);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                bufferedWriter.close();
                byteArrayOutputStream.close();
                return byteArray;
            } finally {
            }
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static SentryEnvelopeItem fromCheckIn(final ISerializer iSerializer, final CheckIn checkIn) {
        Objects.requireNonNull(iSerializer, "ISerializer is required.");
        Objects.requireNonNull(checkIn, "CheckIn is required.");
        final CachedItem cachedItem = new CachedItem(new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda23
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SentryEnvelopeItem.lambda$fromCheckIn$9(iSerializer, checkIn);
            }
        });
        return new SentryEnvelopeItem(new SentryEnvelopeItemHeader(SentryItemType.CheckIn, new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda24
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Integer.valueOf(cachedItem.getBytes().length);
            }
        }, NfcDataRepository.FILE_TYPE_JSON, null), (Callable<byte[]>) new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda25
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return cachedItem.getBytes();
            }
        });
    }

    static /* synthetic */ byte[] lambda$fromCheckIn$9(ISerializer iSerializer, CheckIn checkIn) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, UTF_8));
            try {
                iSerializer.serialize((ISerializer) checkIn, (Writer) bufferedWriter);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                bufferedWriter.close();
                byteArrayOutputStream.close();
                return byteArray;
            } finally {
            }
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static SentryEnvelopeItem fromMetrics(final EncodedMetrics encodedMetrics) {
        final CachedItem cachedItem = new CachedItem(new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda6
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return encodedMetrics.encodeToStatsd();
            }
        });
        return new SentryEnvelopeItem(new SentryEnvelopeItemHeader(SentryItemType.Statsd, new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda7
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Integer.valueOf(cachedItem.getBytes().length);
            }
        }, "application/octet-stream", null), (Callable<byte[]>) new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda8
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return cachedItem.getBytes();
            }
        });
    }

    public static SentryEnvelopeItem fromAttachment(final ISerializer iSerializer, final ILogger iLogger, final Attachment attachment, final long j) {
        final CachedItem cachedItem = new CachedItem(new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda9
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SentryEnvelopeItem.lambda$fromAttachment$15(attachment, j, iSerializer, iLogger);
            }
        });
        return new SentryEnvelopeItem(new SentryEnvelopeItemHeader(SentryItemType.Attachment, (Callable<Integer>) new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda10
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Integer.valueOf(cachedItem.getBytes().length);
            }
        }, attachment.getContentType(), attachment.getFilename(), attachment.getAttachmentType()), (Callable<byte[]>) new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda12
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return cachedItem.getBytes();
            }
        });
    }

    static /* synthetic */ byte[] lambda$fromAttachment$15(Attachment attachment, long j, ISerializer iSerializer, ILogger iLogger) throws Exception {
        if (attachment.getBytes() != null) {
            byte[] bytes = attachment.getBytes();
            ensureAttachmentSizeLimit(bytes.length, j, attachment.getFilename());
            return bytes;
        }
        if (attachment.getSerializable() != null) {
            byte[] bArrBytesFrom = JsonSerializationUtils.bytesFrom(iSerializer, iLogger, attachment.getSerializable());
            if (bArrBytesFrom != null) {
                ensureAttachmentSizeLimit(bArrBytesFrom.length, j, attachment.getFilename());
                return bArrBytesFrom;
            }
        } else if (attachment.getPathname() != null) {
            return FileUtils.readBytesFromFile(attachment.getPathname(), j);
        }
        throw new SentryEnvelopeException(String.format("Couldn't attach the attachment %s.\nPlease check that either bytes, serializable or a path is set.", attachment.getFilename()));
    }

    private static void ensureAttachmentSizeLimit(long j, long j2, String str) throws SentryEnvelopeException {
        if (j > j2) {
            throw new SentryEnvelopeException(String.format("Dropping attachment with filename '%s', because the size of the passed bytes with %d bytes is bigger than the maximum allowed attachment size of %d bytes.", str, Long.valueOf(j), Long.valueOf(j2)));
        }
    }

    public static SentryEnvelopeItem fromProfilingTrace(final ProfilingTraceData profilingTraceData, final long j, final ISerializer iSerializer) throws SentryEnvelopeException {
        final File traceFile = profilingTraceData.getTraceFile();
        final CachedItem cachedItem = new CachedItem(new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda16
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SentryEnvelopeItem.lambda$fromProfilingTrace$18(traceFile, j, profilingTraceData, iSerializer);
            }
        });
        return new SentryEnvelopeItem(new SentryEnvelopeItemHeader(SentryItemType.Profile, new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda17
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Integer.valueOf(cachedItem.getBytes().length);
            }
        }, "application-json", traceFile.getName()), (Callable<byte[]>) new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda18
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return cachedItem.getBytes();
            }
        });
    }

    static /* synthetic */ byte[] lambda$fromProfilingTrace$18(File file, long j, ProfilingTraceData profilingTraceData, ISerializer iSerializer) throws Exception {
        if (!file.exists()) {
            throw new SentryEnvelopeException(String.format("Dropping profiling trace data, because the file '%s' doesn't exists", file.getName()));
        }
        String strEncodeToString = Base64.encodeToString(FileUtils.readBytesFromFile(file.getPath(), j), 3);
        if (strEncodeToString.isEmpty()) {
            throw new SentryEnvelopeException("Profiling trace file is empty");
        }
        profilingTraceData.setSampledProfile(strEncodeToString);
        profilingTraceData.readDeviceCpuFrequencies();
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, UTF_8));
                    try {
                        iSerializer.serialize((ISerializer) profilingTraceData, (Writer) bufferedWriter);
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        bufferedWriter.close();
                        byteArrayOutputStream.close();
                        return byteArray;
                    } finally {
                    }
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException e) {
                throw new SentryEnvelopeException(String.format("Failed to serialize profiling trace data\n%s", e.getMessage()));
            }
        } finally {
            file.delete();
        }
    }

    public static SentryEnvelopeItem fromClientReport(final ISerializer iSerializer, final ClientReport clientReport) throws IOException {
        Objects.requireNonNull(iSerializer, "ISerializer is required.");
        Objects.requireNonNull(clientReport, "ClientReport is required.");
        final CachedItem cachedItem = new CachedItem(new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda13
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SentryEnvelopeItem.lambda$fromClientReport$21(iSerializer, clientReport);
            }
        });
        return new SentryEnvelopeItem(new SentryEnvelopeItemHeader(SentryItemType.resolve(clientReport), new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda14
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Integer.valueOf(cachedItem.getBytes().length);
            }
        }, NfcDataRepository.FILE_TYPE_JSON, null), (Callable<byte[]>) new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda15
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return cachedItem.getBytes();
            }
        });
    }

    static /* synthetic */ byte[] lambda$fromClientReport$21(ISerializer iSerializer, ClientReport clientReport) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, UTF_8));
            try {
                iSerializer.serialize((ISerializer) clientReport, (Writer) bufferedWriter);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                bufferedWriter.close();
                byteArrayOutputStream.close();
                return byteArray;
            } finally {
            }
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public ClientReport getClientReport(ISerializer iSerializer) throws Exception {
        SentryEnvelopeItemHeader sentryEnvelopeItemHeader = this.header;
        if (sentryEnvelopeItemHeader == null || sentryEnvelopeItemHeader.getType() != SentryItemType.ClientReport) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(getData()), UTF_8));
        try {
            ClientReport clientReport = (ClientReport) iSerializer.deserialize(bufferedReader, ClientReport.class);
            bufferedReader.close();
            return clientReport;
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static SentryEnvelopeItem fromReplay(final ISerializer iSerializer, final ILogger iLogger, final SentryReplayEvent sentryReplayEvent, final ReplayRecording replayRecording, final boolean z) {
        final File videoFile = sentryReplayEvent.getVideoFile();
        final CachedItem cachedItem = new CachedItem(new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SentryEnvelopeItem.lambda$fromReplay$24(iSerializer, sentryReplayEvent, replayRecording, videoFile, iLogger, z);
            }
        });
        return new SentryEnvelopeItem(new SentryEnvelopeItemHeader(SentryItemType.ReplayVideo, new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda11
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Integer.valueOf(cachedItem.getBytes().length);
            }
        }, null, null), (Callable<byte[]>) new Callable() { // from class: io.sentry.SentryEnvelopeItem$$ExternalSyntheticLambda19
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return cachedItem.getBytes();
            }
        });
    }

    static /* synthetic */ byte[] lambda$fromReplay$24(ISerializer iSerializer, SentryReplayEvent sentryReplayEvent, ReplayRecording replayRecording, File file, ILogger iLogger, boolean z) throws Exception {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, UTF_8));
                try {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    iSerializer.serialize((ISerializer) sentryReplayEvent, (Writer) bufferedWriter);
                    linkedHashMap.put(SentryItemType.ReplayEvent.getItemType(), byteArrayOutputStream.toByteArray());
                    byteArrayOutputStream.reset();
                    if (replayRecording != null) {
                        iSerializer.serialize((ISerializer) replayRecording, (Writer) bufferedWriter);
                        linkedHashMap.put(SentryItemType.ReplayRecording.getItemType(), byteArrayOutputStream.toByteArray());
                        byteArrayOutputStream.reset();
                    }
                    if (file != null && file.exists()) {
                        byte[] bytesFromFile = FileUtils.readBytesFromFile(file.getPath(), SentryReplayEvent.REPLAY_VIDEO_MAX_SIZE);
                        if (bytesFromFile.length > 0) {
                            linkedHashMap.put(SentryItemType.ReplayVideo.getItemType(), bytesFromFile);
                        }
                    }
                    byte[] bArrSerializeToMsgpack = serializeToMsgpack(linkedHashMap);
                    bufferedWriter.close();
                    byteArrayOutputStream.close();
                    return bArrSerializeToMsgpack;
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                iLogger.log(SentryLevel.ERROR, "Could not serialize replay recording", th);
                if (file == null) {
                    return null;
                }
                if (z) {
                    FileUtils.deleteRecursively(file.getParentFile());
                    return null;
                }
                file.delete();
                return null;
            } finally {
                if (file != null) {
                    if (z) {
                        FileUtils.deleteRecursively(file.getParentFile());
                    } else {
                        file.delete();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class CachedItem {
        private byte[] bytes;
        private final Callable<byte[]> dataFactory;

        private static byte[] orEmptyArray(byte[] bArr) {
            return bArr != null ? bArr : new byte[0];
        }

        public CachedItem(Callable<byte[]> callable) {
            this.dataFactory = callable;
        }

        public byte[] getBytes() throws Exception {
            Callable<byte[]> callable;
            if (this.bytes == null && (callable = this.dataFactory) != null) {
                this.bytes = callable.call();
            }
            return orEmptyArray(this.bytes);
        }
    }

    private static byte[] serializeToMsgpack(Map<String, byte[]> map) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write((byte) (map.size() | 128));
            for (Map.Entry<String, byte[]> entry : map.entrySet()) {
                byte[] bytes = entry.getKey().getBytes(UTF_8);
                int length = bytes.length;
                byteArrayOutputStream.write(-39);
                byteArrayOutputStream.write((byte) length);
                byteArrayOutputStream.write(bytes);
                byte[] value = entry.getValue();
                int length2 = value.length;
                byteArrayOutputStream.write(-58);
                byteArrayOutputStream.write(ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(length2).array());
                byteArrayOutputStream.write(value);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
