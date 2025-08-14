package io.grpc.internal;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import io.grpc.InternalMetadata;
import io.grpc.InternalStatus;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.AbstractClientStream;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import net.sf.scuba.smartcards.ISO7816;
import okhttp3.internal.http2.Header;

/* loaded from: classes6.dex */
public abstract class Http2ClientStreamTransportState extends AbstractClientStream.TransportState {
    private static final Metadata.Key<Integer> HTTP2_STATUS;
    private static final InternalMetadata.TrustedAsciiMarshaller<Integer> HTTP_STATUS_MARSHALLER;
    private Charset errorCharset;
    private boolean headersReceived;
    private Status transportError;
    private Metadata transportErrorMetadata;

    protected abstract void http2ProcessingFailed(Status status, boolean z, Metadata metadata);

    @Override // io.grpc.internal.AbstractClientStream.TransportState, io.grpc.internal.MessageDeframer.Listener
    public /* bridge */ /* synthetic */ void deframerClosed(boolean z) {
        super.deframerClosed(z);
    }

    static {
        InternalMetadata.TrustedAsciiMarshaller<Integer> trustedAsciiMarshaller = new InternalMetadata.TrustedAsciiMarshaller<Integer>() { // from class: io.grpc.internal.Http2ClientStreamTransportState.1
            @Override // io.grpc.Metadata.TrustedAsciiMarshaller
            public byte[] toAsciiString(Integer num) {
                throw new UnsupportedOperationException();
            }

            @Override // io.grpc.Metadata.TrustedAsciiMarshaller
            public Integer parseAsciiString(byte[] bArr) {
                if (bArr.length >= 3) {
                    return Integer.valueOf(((bArr[0] + ISO7816.INS_WRITE_BINARY) * 100) + ((bArr[1] + ISO7816.INS_WRITE_BINARY) * 10) + bArr[2] + ISO7816.INS_WRITE_BINARY);
                }
                throw new NumberFormatException("Malformed status code ".concat(new String(bArr, InternalMetadata.US_ASCII)));
            }
        };
        HTTP_STATUS_MARSHALLER = trustedAsciiMarshaller;
        HTTP2_STATUS = InternalMetadata.keyOf(Header.RESPONSE_STATUS_UTF8, trustedAsciiMarshaller);
    }

    protected Http2ClientStreamTransportState(int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer) {
        super(i, statsTraceContext, transportTracer);
        this.errorCharset = Charsets.UTF_8;
    }

    /* JADX WARN: Finally extract failed */
    protected void transportHeadersReceived(Metadata metadata) {
        Preconditions.checkNotNull(metadata, "headers");
        Status status = this.transportError;
        if (status != null) {
            this.transportError = status.augmentDescription("headers: " + metadata);
            return;
        }
        try {
            if (this.headersReceived) {
                Status statusWithDescription = Status.INTERNAL.withDescription("Received headers twice");
                this.transportError = statusWithDescription;
                if (statusWithDescription != null) {
                    this.transportError = statusWithDescription.augmentDescription("headers: " + metadata);
                    this.transportErrorMetadata = metadata;
                    this.errorCharset = extractCharset(metadata);
                    return;
                }
                return;
            }
            Integer num = (Integer) metadata.get(HTTP2_STATUS);
            if (num != null && num.intValue() >= 100 && num.intValue() < 200) {
                Status status2 = this.transportError;
                if (status2 != null) {
                    this.transportError = status2.augmentDescription("headers: " + metadata);
                    this.transportErrorMetadata = metadata;
                    this.errorCharset = extractCharset(metadata);
                    return;
                }
                return;
            }
            this.headersReceived = true;
            Status statusValidateInitialMetadata = validateInitialMetadata(metadata);
            this.transportError = statusValidateInitialMetadata;
            if (statusValidateInitialMetadata != null) {
                if (statusValidateInitialMetadata != null) {
                    this.transportError = statusValidateInitialMetadata.augmentDescription("headers: " + metadata);
                    this.transportErrorMetadata = metadata;
                    this.errorCharset = extractCharset(metadata);
                    return;
                }
                return;
            }
            stripTransportDetails(metadata);
            inboundHeadersReceived(metadata);
            Status status3 = this.transportError;
            if (status3 != null) {
                this.transportError = status3.augmentDescription("headers: " + metadata);
                this.transportErrorMetadata = metadata;
                this.errorCharset = extractCharset(metadata);
            }
        } catch (Throwable th) {
            Status status4 = this.transportError;
            if (status4 != null) {
                this.transportError = status4.augmentDescription("headers: " + metadata);
                this.transportErrorMetadata = metadata;
                this.errorCharset = extractCharset(metadata);
            }
            throw th;
        }
    }

    protected void transportDataReceived(ReadableBuffer readableBuffer, boolean z) {
        Status status = this.transportError;
        if (status != null) {
            this.transportError = status.augmentDescription("DATA-----------------------------\n" + ReadableBuffers.readAsString(readableBuffer, this.errorCharset));
            readableBuffer.close();
            if (this.transportError.getDescription().length() > 1000 || z) {
                http2ProcessingFailed(this.transportError, false, this.transportErrorMetadata);
                return;
            }
            return;
        }
        if (!this.headersReceived) {
            http2ProcessingFailed(Status.INTERNAL.withDescription("headers not received before payload"), false, new Metadata());
            return;
        }
        int i = readableBuffer.readableBytes();
        inboundDataReceived(readableBuffer);
        if (z) {
            if (i > 0) {
                this.transportError = Status.INTERNAL.withDescription("Received unexpected EOS on non-empty DATA frame from server");
            } else {
                this.transportError = Status.INTERNAL.withDescription("Received unexpected EOS on empty DATA frame from server");
            }
            Metadata metadata = new Metadata();
            this.transportErrorMetadata = metadata;
            transportReportStatus(this.transportError, false, metadata);
        }
    }

    protected void transportTrailersReceived(Metadata metadata) {
        Preconditions.checkNotNull(metadata, GrpcUtil.TE_TRAILERS);
        if (this.transportError == null && !this.headersReceived) {
            Status statusValidateInitialMetadata = validateInitialMetadata(metadata);
            this.transportError = statusValidateInitialMetadata;
            if (statusValidateInitialMetadata != null) {
                this.transportErrorMetadata = metadata;
            }
        }
        Status status = this.transportError;
        if (status != null) {
            Status statusAugmentDescription = status.augmentDescription("trailers: " + metadata);
            this.transportError = statusAugmentDescription;
            http2ProcessingFailed(statusAugmentDescription, false, this.transportErrorMetadata);
        } else {
            Status statusStatusFromTrailers = statusFromTrailers(metadata);
            stripTransportDetails(metadata);
            inboundTrailersReceived(metadata, statusStatusFromTrailers);
        }
    }

    private Status statusFromTrailers(Metadata metadata) {
        Status statusWithDescription;
        Status status = (Status) metadata.get(InternalStatus.CODE_KEY);
        if (status != null) {
            return status.withDescription((String) metadata.get(InternalStatus.MESSAGE_KEY));
        }
        if (this.headersReceived) {
            return Status.UNKNOWN.withDescription("missing GRPC status in response");
        }
        Integer num = (Integer) metadata.get(HTTP2_STATUS);
        if (num != null) {
            statusWithDescription = GrpcUtil.httpStatusToGrpcStatus(num.intValue());
        } else {
            statusWithDescription = Status.INTERNAL.withDescription("missing HTTP status code");
        }
        return statusWithDescription.augmentDescription("missing GRPC status, inferred error from HTTP status code");
    }

    @Nullable
    private Status validateInitialMetadata(Metadata metadata) {
        Integer num = (Integer) metadata.get(HTTP2_STATUS);
        if (num == null) {
            return Status.INTERNAL.withDescription("Missing HTTP status code");
        }
        String str = (String) metadata.get(GrpcUtil.CONTENT_TYPE_KEY);
        if (GrpcUtil.isGrpcContentType(str)) {
            return null;
        }
        return GrpcUtil.httpStatusToGrpcStatus(num.intValue()).augmentDescription("invalid content-type: " + str);
    }

    private static Charset extractCharset(Metadata metadata) {
        String str = (String) metadata.get(GrpcUtil.CONTENT_TYPE_KEY);
        if (str != null) {
            try {
                return Charset.forName(str.split("charset=", 2)[r2.length - 1].trim());
            } catch (Exception unused) {
            }
        }
        return Charsets.UTF_8;
    }

    private static void stripTransportDetails(Metadata metadata) {
        metadata.discardAll(HTTP2_STATUS);
        metadata.discardAll(InternalStatus.CODE_KEY);
        metadata.discardAll(InternalStatus.MESSAGE_KEY);
    }
}
