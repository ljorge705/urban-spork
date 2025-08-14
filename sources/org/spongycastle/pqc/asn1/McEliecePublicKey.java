package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

/* loaded from: classes7.dex */
public class McEliecePublicKey extends ASN1Object {
    private final GF2Matrix g;

    /* renamed from: n, reason: collision with root package name */
    private final int f404n;
    private final int t;

    public int getN() {
        return this.f404n;
    }

    public int getT() {
        return this.t;
    }

    public McEliecePublicKey(int i, int i2, GF2Matrix gF2Matrix) {
        this.f404n = i;
        this.t = i2;
        this.g = new GF2Matrix(gF2Matrix);
    }

    private McEliecePublicKey(ASN1Sequence aSN1Sequence) {
        this.f404n = ((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue().intValue();
        this.t = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).getValue().intValue();
        this.g = new GF2Matrix(((ASN1OctetString) aSN1Sequence.getObjectAt(2)).getOctets());
    }

    public GF2Matrix getG() {
        return new GF2Matrix(this.g);
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.f404n));
        aSN1EncodableVector.add(new ASN1Integer(this.t));
        aSN1EncodableVector.add(new DEROctetString(this.g.getEncoded()));
        return new DERSequence(aSN1EncodableVector);
    }

    public static McEliecePublicKey getInstance(Object obj) {
        if (obj instanceof McEliecePublicKey) {
            return (McEliecePublicKey) obj;
        }
        if (obj != null) {
            return new McEliecePublicKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }
}
