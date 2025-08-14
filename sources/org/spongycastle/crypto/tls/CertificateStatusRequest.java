package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes7.dex */
public class CertificateStatusRequest {
    protected Object request;
    protected short statusType;

    public Object getRequest() {
        return this.request;
    }

    public short getStatusType() {
        return this.statusType;
    }

    public CertificateStatusRequest(short s, Object obj) {
        if (!isCorrectType(s, obj)) {
            throw new IllegalArgumentException("'request' is not an instance of the correct type");
        }
        this.statusType = s;
        this.request = obj;
    }

    public OCSPStatusRequest getOCSPStatusRequest() {
        if (!isCorrectType((short) 1, this.request)) {
            throw new IllegalStateException("'request' is not an OCSPStatusRequest");
        }
        return (OCSPStatusRequest) this.request;
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint8(this.statusType, outputStream);
        if (this.statusType == 1) {
            ((OCSPStatusRequest) this.request).encode(outputStream);
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static CertificateStatusRequest parse(InputStream inputStream) throws IOException {
        short uint8 = TlsUtils.readUint8(inputStream);
        if (uint8 != 1) {
            throw new TlsFatalAlert((short) 50);
        }
        return new CertificateStatusRequest(uint8, OCSPStatusRequest.parse(inputStream));
    }

    protected static boolean isCorrectType(short s, Object obj) {
        if (s == 1) {
            return obj instanceof OCSPStatusRequest;
        }
        throw new IllegalArgumentException("'statusType' is an unsupported CertificateStatusType");
    }
}
