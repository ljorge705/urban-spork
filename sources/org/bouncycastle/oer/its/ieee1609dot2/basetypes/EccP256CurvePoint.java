package org.bouncycastle.oer.its.ieee1609dot2.basetypes;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class EccP256CurvePoint extends EccCurvePoint implements ASN1Choice {
    public static final int compressedY0 = 2;
    public static final int compressedY1 = 3;
    public static final int fill = 1;
    public static final int uncompressedP256 = 4;
    public static final int xonly = 0;
    private final int choice;
    private final ASN1Encodable eccp256CurvePoint;

    public EccP256CurvePoint(int i, ASN1Encodable aSN1Encodable) {
        this.choice = i;
        this.eccp256CurvePoint = aSN1Encodable;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private EccP256CurvePoint(org.bouncycastle.asn1.ASN1TaggedObject r4) {
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
            org.bouncycastle.oer.its.ieee1609dot2.basetypes.Point256 r4 = org.bouncycastle.oer.its.ieee1609dot2.basetypes.Point256.getInstance(r4)
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
            r3.eccp256CurvePoint = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccP256CurvePoint.<init>(org.bouncycastle.asn1.ASN1TaggedObject):void");
    }

    public static EccP256CurvePoint compressedY0(ASN1OctetString aSN1OctetString) {
        return new EccP256CurvePoint(2, aSN1OctetString);
    }

    public static EccP256CurvePoint compressedY0(byte[] bArr) {
        return new EccP256CurvePoint(2, new DEROctetString(Arrays.clone(bArr)));
    }

    public static EccP256CurvePoint compressedY1(ASN1OctetString aSN1OctetString) {
        return new EccP256CurvePoint(3, aSN1OctetString);
    }

    public static EccP256CurvePoint compressedY1(byte[] bArr) {
        return new EccP256CurvePoint(3, new DEROctetString(Arrays.clone(bArr)));
    }

    public static EccP256CurvePoint createEncodedPoint(byte[] bArr) {
        byte b = bArr[0];
        if (b == 2) {
            int length = bArr.length - 1;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 1, bArr2, 0, length);
            return new EccP256CurvePoint(2, new DEROctetString(bArr2));
        }
        if (b != 3) {
            if (b == 4) {
                return new EccP256CurvePoint(4, new Point256(new DEROctetString(Arrays.copyOfRange(bArr, 1, 34)), new DEROctetString(Arrays.copyOfRange(bArr, 34, 66))));
            }
            throw new IllegalArgumentException("unrecognised encoding " + ((int) bArr[0]));
        }
        int length2 = bArr.length - 1;
        byte[] bArr3 = new byte[length2];
        System.arraycopy(bArr, 1, bArr3, 0, length2);
        return new EccP256CurvePoint(3, new DEROctetString(bArr3));
    }

    public static EccP256CurvePoint fill() {
        return new EccP256CurvePoint(1, DERNull.INSTANCE);
    }

    public static EccP256CurvePoint getInstance(Object obj) {
        if (obj instanceof EccP256CurvePoint) {
            return (EccP256CurvePoint) obj;
        }
        if (obj != null) {
            return new EccP256CurvePoint(ASN1TaggedObject.getInstance(obj, 128));
        }
        return null;
    }

    public static EccP256CurvePoint uncompressedP256(BigInteger bigInteger, BigInteger bigInteger2) {
        return new EccP256CurvePoint(4, Point256.builder().setX(bigInteger).setY(bigInteger2).createPoint256());
    }

    public static EccP256CurvePoint uncompressedP256(Point256 point256) {
        return new EccP256CurvePoint(4, point256);
    }

    public static EccP256CurvePoint xOnly(ASN1OctetString aSN1OctetString) {
        return new EccP256CurvePoint(0, aSN1OctetString);
    }

    public static EccP256CurvePoint xOnly(byte[] bArr) {
        return new EccP256CurvePoint(0, new DEROctetString(Arrays.clone(bArr)));
    }

    public EccP256CurvePoint createCompressed(ECPoint eCPoint) {
        byte[] encoded = eCPoint.getEncoded(true);
        byte b = encoded[0];
        int i = 2;
        if (b != 2) {
            i = 3;
            if (b != 3) {
                i = 0;
            }
        }
        int length = encoded.length - 1;
        byte[] bArr = new byte[length];
        System.arraycopy(encoded, 1, bArr, 0, length);
        return new EccP256CurvePoint(i, new DEROctetString(bArr));
    }

    public int getChoice() {
        return this.choice;
    }

    public ASN1Encodable getEccp256CurvePoint() {
        return this.eccp256CurvePoint;
    }

    @Override // org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccCurvePoint
    public byte[] getEncodedPoint() {
        byte[] bArr;
        int i = this.choice;
        if (i == 0) {
            throw new IllegalStateException("x Only not implemented");
        }
        if (i == 2) {
            byte[] octets = DEROctetString.getInstance(this.eccp256CurvePoint).getOctets();
            bArr = new byte[octets.length + 1];
            bArr[0] = 2;
            System.arraycopy(octets, 0, bArr, 1, octets.length);
        } else {
            if (i != 3) {
                if (i != 4) {
                    throw new IllegalStateException("unknown point choice");
                }
                Point256 point256 = Point256.getInstance(this.eccp256CurvePoint);
                return Arrays.concatenate(new byte[]{4}, point256.getX().getOctets(), point256.getY().getOctets());
            }
            byte[] octets2 = DEROctetString.getInstance(this.eccp256CurvePoint).getOctets();
            bArr = new byte[octets2.length + 1];
            bArr[0] = 3;
            System.arraycopy(octets2, 0, bArr, 1, octets2.length);
        }
        return bArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(this.choice, this.eccp256CurvePoint);
    }
}
