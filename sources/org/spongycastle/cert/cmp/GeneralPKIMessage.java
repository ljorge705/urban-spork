package org.spongycastle.cert.cmp;

import java.io.IOException;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.cmp.PKIBody;
import org.spongycastle.asn1.cmp.PKIHeader;
import org.spongycastle.asn1.cmp.PKIMessage;
import org.spongycastle.cert.CertIOException;

/* loaded from: classes4.dex */
public class GeneralPKIMessage {
    private final PKIMessage pkiMessage;

    public PKIMessage toASN1Structure() {
        return this.pkiMessage;
    }

    private static PKIMessage parseBytes(byte[] bArr) throws IOException {
        try {
            return PKIMessage.getInstance(ASN1Primitive.fromByteArray(bArr));
        } catch (ClassCastException e) {
            throw new CertIOException("malformed data: " + e.getMessage(), e);
        } catch (IllegalArgumentException e2) {
            throw new CertIOException("malformed data: " + e2.getMessage(), e2);
        }
    }

    public GeneralPKIMessage(byte[] bArr) throws IOException {
        this(parseBytes(bArr));
    }

    public GeneralPKIMessage(PKIMessage pKIMessage) {
        this.pkiMessage = pKIMessage;
    }

    public PKIHeader getHeader() {
        return this.pkiMessage.getHeader();
    }

    public PKIBody getBody() {
        return this.pkiMessage.getBody();
    }

    public boolean hasProtection() {
        return this.pkiMessage.getHeader().getProtectionAlg() != null;
    }
}
