package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes7.dex */
public class SPHINCS256KeyParams extends ASN1Object {
    private final AlgorithmIdentifier treeDigest;
    private final ASN1Integer version;

    public AlgorithmIdentifier getTreeDigest() {
        return this.treeDigest;
    }

    public SPHINCS256KeyParams(AlgorithmIdentifier algorithmIdentifier) {
        this.version = new ASN1Integer(0L);
        this.treeDigest = algorithmIdentifier;
    }

    private SPHINCS256KeyParams(ASN1Sequence aSN1Sequence) {
        this.version = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        this.treeDigest = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public static final SPHINCS256KeyParams getInstance(Object obj) {
        if (obj instanceof SPHINCS256KeyParams) {
            return (SPHINCS256KeyParams) obj;
        }
        if (obj != null) {
            return new SPHINCS256KeyParams(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(this.treeDigest);
        return new DERSequence(aSN1EncodableVector);
    }
}
