package org.spongycastle.asn1.cmc;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.util.Arrays;

/* loaded from: classes4.dex */
public class PublishTrustAnchors extends ASN1Object {
    private final ASN1Sequence anchorHashes;
    private final AlgorithmIdentifier hashAlgorithm;
    private final ASN1Integer seqNumber;

    public AlgorithmIdentifier getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public PublishTrustAnchors(BigInteger bigInteger, AlgorithmIdentifier algorithmIdentifier, byte[][] bArr) {
        this.seqNumber = new ASN1Integer(bigInteger);
        this.hashAlgorithm = algorithmIdentifier;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != bArr.length; i++) {
            aSN1EncodableVector.add(new DEROctetString(Arrays.clone(bArr[i])));
        }
        this.anchorHashes = new DERSequence(aSN1EncodableVector);
    }

    private PublishTrustAnchors(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.seqNumber = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        this.hashAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.anchorHashes = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public static PublishTrustAnchors getInstance(Object obj) {
        if (obj instanceof PublishTrustAnchors) {
            return (PublishTrustAnchors) obj;
        }
        if (obj != null) {
            return new PublishTrustAnchors(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getSeqNumber() {
        return this.seqNumber.getValue();
    }

    public byte[][] getAnchorHashes() {
        int size = this.anchorHashes.size();
        byte[][] bArr = new byte[size][];
        for (int i = 0; i != size; i++) {
            bArr[i] = Arrays.clone(ASN1OctetString.getInstance(this.anchorHashes.getObjectAt(i)).getOctets());
        }
        return bArr;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.seqNumber);
        aSN1EncodableVector.add(this.hashAlgorithm);
        aSN1EncodableVector.add(this.anchorHashes);
        return new DERSequence(aSN1EncodableVector);
    }
}
