package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

/* loaded from: classes7.dex */
public class McElieceCCA2PublicKey extends ASN1Object {
    private final AlgorithmIdentifier digest;
    private final GF2Matrix g;

    /* renamed from: n, reason: collision with root package name */
    private final int f402n;
    private final int t;

    public AlgorithmIdentifier getDigest() {
        return this.digest;
    }

    public GF2Matrix getG() {
        return this.g;
    }

    public int getN() {
        return this.f402n;
    }

    public int getT() {
        return this.t;
    }

    public McElieceCCA2PublicKey(int i, int i2, GF2Matrix gF2Matrix, AlgorithmIdentifier algorithmIdentifier) {
        this.f402n = i;
        this.t = i2;
        this.g = new GF2Matrix(gF2Matrix.getEncoded());
        this.digest = algorithmIdentifier;
    }

    private McElieceCCA2PublicKey(ASN1Sequence aSN1Sequence) {
        this.f402n = ((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue().intValue();
        this.t = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).getValue().intValue();
        this.g = new GF2Matrix(((ASN1OctetString) aSN1Sequence.getObjectAt(2)).getOctets());
        this.digest = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(3));
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.f402n));
        aSN1EncodableVector.add(new ASN1Integer(this.t));
        aSN1EncodableVector.add(new DEROctetString(this.g.getEncoded()));
        aSN1EncodableVector.add(this.digest);
        return new DERSequence(aSN1EncodableVector);
    }

    public static McElieceCCA2PublicKey getInstance(Object obj) {
        if (obj instanceof McElieceCCA2PublicKey) {
            return (McElieceCCA2PublicKey) obj;
        }
        if (obj != null) {
            return new McElieceCCA2PublicKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }
}
