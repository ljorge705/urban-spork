package org.bouncycastle.oer.its.ieee1609dot2.basetypes;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class EccP384CurvePoint extends EccCurvePoint implements ASN1Choice {
    public static final int compressedY0 = 2;
    public static final int compressedY1 = 3;
    public static final int fill = 1;
    public static final int uncompressedP384 = 4;
    public static final int xonly = 0;
    private final int choice;
    private final ASN1Encodable eccP384CurvePoint;

    public EccP384CurvePoint(int i, ASN1Encodable aSN1Encodable) {
        this.choice = i;
        this.eccP384CurvePoint = aSN1Encodable;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private EccP384CurvePoint(org.bouncycastle.asn1.ASN1TaggedObject r4) {
        /*
            r3 = this;
            r3.<init>()
            int r0 = r4.getTagNo()
            r3.choice = r0
            int r0 = r4.getTagNo()
            if (r0 == 0) goto L46
            r1 = 1
            if (r0 == r1) goto L3d
            r1 = 2
            if (r0 == r1) goto L46
            r1 = 3
            if (r0 == r1) goto L46
            r1 = 4
            if (r0 != r1) goto L24
            org.bouncycastle.asn1.ASN1Object r4 = r4.getExplicitBaseObject()
            org.bouncycastle.asn1.ASN1Sequence r4 = org.bouncycastle.asn1.ASN1Sequence.getInstance(r4)
            goto L4e
        L24:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "invalid choice value "
            r1.<init>(r2)
            int r4 = r4.getTagNo()
            java.lang.StringBuilder r4 = r1.append(r4)
            java.lang.String r4 = r4.toString()
            r0.<init>(r4)
            throw r0
        L3d:
            org.bouncycastle.asn1.ASN1Object r4 = r4.getExplicitBaseObject()
            org.bouncycastle.asn1.ASN1Null r4 = org.bouncycastle.asn1.ASN1Null.getInstance(r4)
            goto L4e
        L46:
            org.bouncycastle.asn1.ASN1Object r4 = r4.getExplicitBaseObject()
            org.bouncycastle.asn1.ASN1OctetString r4 = org.bouncycastle.asn1.ASN1OctetString.getInstance(r4)
        L4e:
            r3.eccP384CurvePoint = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccP384CurvePoint.<init>(org.bouncycastle.asn1.ASN1TaggedObject):void");
    }

    public static EccP384CurvePoint compressedY0(ASN1OctetString aSN1OctetString) {
        return new EccP384CurvePoint(2, aSN1OctetString);
    }

    public static EccP384CurvePoint compressedY0(byte[] bArr) {
        return new EccP384CurvePoint(2, new DEROctetString(Arrays.clone(bArr)));
    }

    public static EccP384CurvePoint compressedY1(ASN1OctetString aSN1OctetString) {
        return new EccP384CurvePoint(3, aSN1OctetString);
    }

    public static EccP384CurvePoint compressedY1(byte[] bArr) {
        return new EccP384CurvePoint(3, new DEROctetString(Arrays.clone(bArr)));
    }

    public static EccP384CurvePoint fill() {
        return new EccP384CurvePoint(1, DERNull.INSTANCE);
    }

    public static EccP384CurvePoint getInstance(Object obj) {
        if (obj instanceof EccP384CurvePoint) {
            return (EccP384CurvePoint) obj;
        }
        if (obj != null) {
            return new EccP384CurvePoint(ASN1TaggedObject.getInstance(obj, 128));
        }
        return null;
    }

    public static EccP384CurvePoint uncompressedP384(Point384 point384) {
        return new EccP384CurvePoint(4, point384);
    }

    public static EccP384CurvePoint xOnly(ASN1OctetString aSN1OctetString) {
        return new EccP384CurvePoint(0, aSN1OctetString);
    }

    public static EccP384CurvePoint xOnly(byte[] bArr) {
        return new EccP384CurvePoint(0, new DEROctetString(Arrays.clone(bArr)));
    }

    public int getChoice() {
        return this.choice;
    }

    public ASN1Encodable getEccP384CurvePoint() {
        return this.eccP384CurvePoint;
    }

    @Override // org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccCurvePoint
    public byte[] getEncodedPoint() {
        byte[] bArr;
        int i = this.choice;
        if (i == 0) {
            throw new IllegalStateException("x Only not implemented");
        }
        if (i == 2) {
            byte[] octets = DEROctetString.getInstance(this.eccP384CurvePoint).getOctets();
            bArr = new byte[octets.length + 1];
            bArr[0] = 2;
            System.arraycopy(octets, 0, bArr, 1, octets.length);
        } else {
            if (i != 3) {
                if (i != 4) {
                    throw new IllegalStateException("unknown point choice");
                }
                ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(this.eccP384CurvePoint);
                return Arrays.concatenate(new byte[]{4}, DEROctetString.getInstance(aSN1Sequence.getObjectAt(0)).getOctets(), DEROctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets());
            }
            byte[] octets2 = DEROctetString.getInstance(this.eccP384CurvePoint).getOctets();
            bArr = new byte[octets2.length + 1];
            bArr[0] = 3;
            System.arraycopy(octets2, 0, bArr, 1, octets2.length);
        }
        return bArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(this.choice, this.eccP384CurvePoint);
    }
}
