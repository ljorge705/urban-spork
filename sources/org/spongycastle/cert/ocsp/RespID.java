package org.spongycastle.cert.ocsp;

import java.io.IOException;
import java.io.OutputStream;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.ocsp.ResponderID;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.operator.DigestCalculator;

/* loaded from: classes4.dex */
public class RespID {
    public static final AlgorithmIdentifier HASH_SHA1 = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
    ResponderID id;

    public ResponderID toASN1Primitive() {
        return this.id;
    }

    public RespID(ResponderID responderID) {
        this.id = responderID;
    }

    public RespID(X500Name x500Name) {
        this.id = new ResponderID(x500Name);
    }

    public RespID(SubjectPublicKeyInfo subjectPublicKeyInfo, DigestCalculator digestCalculator) throws OCSPException, IOException {
        try {
            if (!digestCalculator.getAlgorithmIdentifier().equals(HASH_SHA1)) {
                throw new IllegalArgumentException("only SHA-1 can be used with RespID - found: " + digestCalculator.getAlgorithmIdentifier().getAlgorithm());
            }
            OutputStream outputStream = digestCalculator.getOutputStream();
            outputStream.write(subjectPublicKeyInfo.getPublicKeyData().getBytes());
            outputStream.close();
            this.id = new ResponderID(new DEROctetString(digestCalculator.getDigest()));
        } catch (Exception e) {
            throw new OCSPException("problem creating ID: " + e, e);
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof RespID) {
            return this.id.equals(((RespID) obj).id);
        }
        return false;
    }

    public int hashCode() {
        return this.id.hashCode();
    }
}
