package org.spongycastle.asn1.ua;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.math.ec.ECAlgorithms;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.field.PolynomialExtensionField;
import org.spongycastle.util.Arrays;

/* loaded from: classes4.dex */
public class DSTU4145ECBinary extends ASN1Object {

    /* renamed from: a, reason: collision with root package name */
    ASN1Integer f378a;
    ASN1OctetString b;
    ASN1OctetString bp;
    DSTU4145BinaryField f;

    /* renamed from: n, reason: collision with root package name */
    ASN1Integer f379n;
    BigInteger version;

    public DSTU4145BinaryField getField() {
        return this.f;
    }

    public DSTU4145ECBinary(ECDomainParameters eCDomainParameters) {
        this.version = BigInteger.valueOf(0L);
        ECCurve curve = eCDomainParameters.getCurve();
        if (!ECAlgorithms.isF2mCurve(curve)) {
            throw new IllegalArgumentException("only binary domain is possible");
        }
        int[] exponentsPresent = ((PolynomialExtensionField) curve.getField()).getMinimalPolynomial().getExponentsPresent();
        if (exponentsPresent.length == 3) {
            this.f = new DSTU4145BinaryField(exponentsPresent[2], exponentsPresent[1]);
        } else if (exponentsPresent.length == 5) {
            this.f = new DSTU4145BinaryField(exponentsPresent[4], exponentsPresent[1], exponentsPresent[2], exponentsPresent[3]);
        } else {
            throw new IllegalArgumentException("curve must have a trinomial or pentanomial basis");
        }
        this.f378a = new ASN1Integer(curve.getA().toBigInteger());
        this.b = new DEROctetString(curve.getB().getEncoded());
        this.f379n = new ASN1Integer(eCDomainParameters.getN());
        this.bp = new DEROctetString(DSTU4145PointEncoder.encodePoint(eCDomainParameters.getG()));
    }

    private DSTU4145ECBinary(ASN1Sequence aSN1Sequence) {
        this.version = BigInteger.valueOf(0L);
        int i = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(0);
            if (aSN1TaggedObject.isExplicit() && aSN1TaggedObject.getTagNo() == 0) {
                this.version = ASN1Integer.getInstance(aSN1TaggedObject.getLoadedObject()).getValue();
                i = 1;
            } else {
                throw new IllegalArgumentException("object parse error");
            }
        }
        this.f = DSTU4145BinaryField.getInstance(aSN1Sequence.getObjectAt(i));
        this.f378a = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i + 1));
        this.b = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(i + 2));
        this.f379n = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i + 3));
        this.bp = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(i + 4));
    }

    public static DSTU4145ECBinary getInstance(Object obj) {
        if (obj instanceof DSTU4145ECBinary) {
            return (DSTU4145ECBinary) obj;
        }
        if (obj != null) {
            return new DSTU4145ECBinary(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getA() {
        return this.f378a.getValue();
    }

    public byte[] getB() {
        return Arrays.clone(this.b.getOctets());
    }

    public BigInteger getN() {
        return this.f379n.getValue();
    }

    public byte[] getG() {
        return Arrays.clone(this.bp.getOctets());
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.version.compareTo(BigInteger.valueOf(0L)) != 0) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, new ASN1Integer(this.version)));
        }
        aSN1EncodableVector.add(this.f);
        aSN1EncodableVector.add(this.f378a);
        aSN1EncodableVector.add(this.b);
        aSN1EncodableVector.add(this.f379n);
        aSN1EncodableVector.add(this.bp);
        return new DERSequence(aSN1EncodableVector);
    }
}
