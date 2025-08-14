package io.sentry;

import java.io.StringReader;
import java.nio.charset.Charset;

/* loaded from: classes6.dex */
public final class EnvelopeReader implements IEnvelopeReader {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final ISerializer serializer;

    public EnvelopeReader(ISerializer iSerializer) {
        this.serializer = iSerializer;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0081, code lost:
    
        r11 = new io.sentry.SentryEnvelope(r0, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0086, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0089, code lost:
    
        return r11;
     */
    @Override // io.sentry.IEnvelopeReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public io.sentry.SentryEnvelope read(java.io.InputStream r11) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.EnvelopeReader.read(java.io.InputStream):io.sentry.SentryEnvelope");
    }

    private SentryEnvelopeHeader deserializeEnvelopeHeader(byte[] bArr, int i, int i2) {
        StringReader stringReader = new StringReader(new String(bArr, i, i2, UTF_8));
        try {
            SentryEnvelopeHeader sentryEnvelopeHeader = (SentryEnvelopeHeader) this.serializer.deserialize(stringReader, SentryEnvelopeHeader.class);
            stringReader.close();
            return sentryEnvelopeHeader;
        } catch (Throwable th) {
            try {
                stringReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private SentryEnvelopeItemHeader deserializeEnvelopeItemHeader(byte[] bArr, int i, int i2) {
        StringReader stringReader = new StringReader(new String(bArr, i, i2, UTF_8));
        try {
            SentryEnvelopeItemHeader sentryEnvelopeItemHeader = (SentryEnvelopeItemHeader) this.serializer.deserialize(stringReader, SentryEnvelopeItemHeader.class);
            stringReader.close();
            return sentryEnvelopeItemHeader;
        } catch (Throwable th) {
            try {
                stringReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
