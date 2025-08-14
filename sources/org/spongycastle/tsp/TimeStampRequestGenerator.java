package org.spongycastle.tsp;

import java.io.IOException;
import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Boolean;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.tsp.MessageImprint;
import org.spongycastle.asn1.tsp.TimeStampReq;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.ExtensionsGenerator;

/* loaded from: classes7.dex */
public class TimeStampRequestGenerator {
    private ASN1Boolean certReq;
    private ExtensionsGenerator extGenerator = new ExtensionsGenerator();
    private ASN1ObjectIdentifier reqPolicy;

    public void setReqPolicy(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.reqPolicy = aSN1ObjectIdentifier;
    }

    public void setReqPolicy(String str) {
        this.reqPolicy = new ASN1ObjectIdentifier(str);
    }

    public void setCertReq(boolean z) {
        this.certReq = ASN1Boolean.getInstance(z);
    }

    public void addExtension(String str, boolean z, ASN1Encodable aSN1Encodable) throws IOException {
        addExtension(str, z, aSN1Encodable.toASN1Primitive().getEncoded());
    }

    public void addExtension(String str, boolean z, byte[] bArr) {
        this.extGenerator.addExtension(new ASN1ObjectIdentifier(str), z, bArr);
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, ASN1Encodable aSN1Encodable) throws TSPIOException {
        TSPUtil.addExtension(this.extGenerator, aSN1ObjectIdentifier, z, aSN1Encodable);
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, byte[] bArr) {
        this.extGenerator.addExtension(aSN1ObjectIdentifier, z, bArr);
    }

    public TimeStampRequest generate(String str, byte[] bArr) {
        return generate(str, bArr, (BigInteger) null);
    }

    public TimeStampRequest generate(String str, byte[] bArr, BigInteger bigInteger) {
        if (str == null) {
            throw new IllegalArgumentException("No digest algorithm specified");
        }
        MessageImprint messageImprint = new MessageImprint(new AlgorithmIdentifier(new ASN1ObjectIdentifier(str), DERNull.INSTANCE), bArr);
        Extensions extensionsGenerate = !this.extGenerator.isEmpty() ? this.extGenerator.generate() : null;
        if (bigInteger != null) {
            return new TimeStampRequest(new TimeStampReq(messageImprint, this.reqPolicy, new ASN1Integer(bigInteger), this.certReq, extensionsGenerate));
        }
        return new TimeStampRequest(new TimeStampReq(messageImprint, this.reqPolicy, null, this.certReq, extensionsGenerate));
    }

    public TimeStampRequest generate(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr) {
        return generate(aSN1ObjectIdentifier.getId(), bArr);
    }

    public TimeStampRequest generate(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr, BigInteger bigInteger) {
        return generate(aSN1ObjectIdentifier.getId(), bArr, bigInteger);
    }
}
