package org.bouncycastle.asn1.ua;

import java.math.BigInteger;
import java.util.Random;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;

/* loaded from: classes4.dex */
public abstract class DSTU4145PointEncoder {
    public static ECPoint decodePoint(ECCurve eCCurve, byte[] bArr) {
        ECFieldElement eCFieldElementMultiply;
        ECFieldElement eCFieldElementFromBigInteger = eCCurve.fromBigInteger(BigInteger.valueOf(bArr[bArr.length - 1] & 1));
        ECFieldElement eCFieldElementFromBigInteger2 = eCCurve.fromBigInteger(new BigInteger(1, bArr));
        if (!trace(eCFieldElementFromBigInteger2).equals(eCCurve.getA())) {
            eCFieldElementFromBigInteger2 = eCFieldElementFromBigInteger2.addOne();
        }
        if (eCFieldElementFromBigInteger2.isZero()) {
            eCFieldElementMultiply = eCCurve.getB().sqrt();
        } else {
            ECFieldElement eCFieldElementSolveQuadraticEquation = solveQuadraticEquation(eCCurve, eCFieldElementFromBigInteger2.square().invert().multiply(eCCurve.getB()).add(eCCurve.getA()).add(eCFieldElementFromBigInteger2));
            if (eCFieldElementSolveQuadraticEquation != null) {
                if (!trace(eCFieldElementSolveQuadraticEquation).equals(eCFieldElementFromBigInteger)) {
                    eCFieldElementSolveQuadraticEquation = eCFieldElementSolveQuadraticEquation.addOne();
                }
                eCFieldElementMultiply = eCFieldElementFromBigInteger2.multiply(eCFieldElementSolveQuadraticEquation);
            } else {
                eCFieldElementMultiply = null;
            }
        }
        if (eCFieldElementMultiply != null) {
            return eCCurve.validatePoint(eCFieldElementFromBigInteger2.toBigInteger(), eCFieldElementMultiply.toBigInteger());
        }
        throw new IllegalArgumentException("Invalid point compression");
    }

    public static byte[] encodePoint(ECPoint eCPoint) {
        ECPoint eCPointNormalize = eCPoint.normalize();
        ECFieldElement affineXCoord = eCPointNormalize.getAffineXCoord();
        byte[] encoded = affineXCoord.getEncoded();
        if (!affineXCoord.isZero()) {
            if (trace(eCPointNormalize.getAffineYCoord().divide(affineXCoord)).isOne()) {
                int length = encoded.length - 1;
                encoded[length] = (byte) (encoded[length] | 1);
            } else {
                int length2 = encoded.length - 1;
                encoded[length2] = (byte) (encoded[length2] & 254);
            }
        }
        return encoded;
    }

    private static ECFieldElement solveQuadraticEquation(ECCurve eCCurve, ECFieldElement eCFieldElement) {
        ECFieldElement eCFieldElementAdd;
        if (eCFieldElement.isZero()) {
            return eCFieldElement;
        }
        ECFieldElement eCFieldElementFromBigInteger = eCCurve.fromBigInteger(ECConstants.ZERO);
        Random random = new Random();
        int fieldSize = eCFieldElement.getFieldSize();
        do {
            ECFieldElement eCFieldElementFromBigInteger2 = eCCurve.fromBigInteger(new BigInteger(fieldSize, random));
            ECFieldElement eCFieldElementAdd2 = eCFieldElement;
            eCFieldElementAdd = eCFieldElementFromBigInteger;
            for (int i = 1; i <= fieldSize - 1; i++) {
                ECFieldElement eCFieldElementSquare = eCFieldElementAdd2.square();
                eCFieldElementAdd = eCFieldElementAdd.square().add(eCFieldElementSquare.multiply(eCFieldElementFromBigInteger2));
                eCFieldElementAdd2 = eCFieldElementSquare.add(eCFieldElement);
            }
            if (!eCFieldElementAdd2.isZero()) {
                return null;
            }
        } while (eCFieldElementAdd.square().add(eCFieldElementAdd).isZero());
        return eCFieldElementAdd;
    }

    private static ECFieldElement trace(ECFieldElement eCFieldElement) {
        ECFieldElement eCFieldElementAdd = eCFieldElement;
        for (int i = 1; i < eCFieldElement.getFieldSize(); i++) {
            eCFieldElementAdd = eCFieldElementAdd.square().add(eCFieldElement);
        }
        return eCFieldElementAdd;
    }
}
