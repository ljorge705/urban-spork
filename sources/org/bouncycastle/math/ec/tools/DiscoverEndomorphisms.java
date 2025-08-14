package org.bouncycastle.math.ec.tools;

import com.clevertap.android.sdk.Constants;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes4.dex */
public class DiscoverEndomorphisms {
    private static final int radix = 16;

    private static boolean areRelativelyPrime(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.gcd(bigInteger2).equals(ECConstants.ONE);
    }

    private static BigInteger[] calculateRange(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return order(bigInteger.subtract(bigInteger2).divide(bigInteger3), bigInteger.add(bigInteger2).divide(bigInteger3));
    }

    private static BigInteger[] chooseShortest(BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2) {
        return isShorter(bigIntegerArr, bigIntegerArr2) ? bigIntegerArr : bigIntegerArr2;
    }

    private static void discoverEndomorphisms(String str) {
        X9ECParameters byName = CustomNamedCurves.getByName(str);
        if (byName == null && (byName = ECNamedCurveTable.getByName(str)) == null) {
            System.err.println("Unknown curve: " + str);
        } else {
            discoverEndomorphisms(byName, str);
        }
    }

    public static void discoverEndomorphisms(X9ECParameters x9ECParameters) {
        if (x9ECParameters == null) {
            throw new NullPointerException("x9");
        }
        discoverEndomorphisms(x9ECParameters, "<UNKNOWN>");
    }

    private static void discoverEndomorphisms(X9ECParameters x9ECParameters, String str) {
        ECCurve curve = x9ECParameters.getCurve();
        if (ECAlgorithms.isFpCurve(curve)) {
            BigInteger characteristic = curve.getField().getCharacteristic();
            if (curve.getB().isZero() && characteristic.mod(ECConstants.FOUR).equals(ECConstants.ONE)) {
                System.out.println("Curve '" + str + "' has a 'GLV Type A' endomorphism with these parameters:");
                printGLVTypeAParameters(x9ECParameters);
            }
            if (curve.getA().isZero() && characteristic.mod(ECConstants.THREE).equals(ECConstants.ONE)) {
                System.out.println("Curve '" + str + "' has a 'GLV Type B' endomorphism with these parameters:");
                printGLVTypeBParameters(x9ECParameters);
            }
        }
    }

    private static List enumToList(Enumeration enumeration) {
        ArrayList arrayList = new ArrayList();
        while (enumeration.hasMoreElements()) {
            arrayList.add(enumeration.nextElement());
        }
        return arrayList;
    }

    private static BigInteger[] extEuclidBezout(BigInteger[] bigIntegerArr) {
        boolean z = bigIntegerArr[0].compareTo(bigIntegerArr[1]) < 0;
        if (z) {
            swap(bigIntegerArr);
        }
        BigInteger bigInteger = bigIntegerArr[0];
        BigInteger bigInteger2 = bigIntegerArr[1];
        BigInteger bigInteger3 = ECConstants.ONE;
        BigInteger bigIntegerSubtract = ECConstants.ZERO;
        BigInteger bigInteger4 = ECConstants.ZERO;
        BigInteger bigIntegerSubtract2 = ECConstants.ONE;
        BigInteger bigInteger5 = bigInteger2;
        BigInteger bigInteger6 = bigInteger;
        while (bigInteger5.compareTo(ECConstants.ONE) > 0) {
            BigInteger[] bigIntegerArrDivideAndRemainder = bigInteger6.divideAndRemainder(bigInteger5);
            BigInteger bigInteger7 = bigIntegerArrDivideAndRemainder[0];
            BigInteger bigInteger8 = bigInteger5;
            bigInteger5 = bigIntegerArrDivideAndRemainder[1];
            bigInteger6 = bigInteger8;
            BigInteger bigInteger9 = bigIntegerSubtract;
            bigIntegerSubtract = bigInteger3.subtract(bigInteger7.multiply(bigIntegerSubtract));
            bigInteger3 = bigInteger9;
            BigInteger bigInteger10 = bigIntegerSubtract2;
            bigIntegerSubtract2 = bigInteger4.subtract(bigInteger7.multiply(bigIntegerSubtract2));
            bigInteger4 = bigInteger10;
        }
        if (bigInteger5.signum() <= 0) {
            return null;
        }
        BigInteger[] bigIntegerArr2 = {bigIntegerSubtract, bigIntegerSubtract2};
        if (z) {
            swap(bigIntegerArr2);
        }
        return bigIntegerArr2;
    }

    private static BigInteger[] extEuclidGLV(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = ECConstants.ZERO;
        BigInteger bigInteger4 = ECConstants.ONE;
        BigInteger bigInteger5 = bigInteger;
        while (true) {
            BigInteger[] bigIntegerArrDivideAndRemainder = bigInteger5.divideAndRemainder(bigInteger2);
            BigInteger bigInteger6 = bigIntegerArrDivideAndRemainder[0];
            BigInteger bigInteger7 = bigIntegerArrDivideAndRemainder[1];
            BigInteger bigIntegerSubtract = bigInteger3.subtract(bigInteger6.multiply(bigInteger4));
            if (isLessThanSqrt(bigInteger2, bigInteger)) {
                return new BigInteger[]{bigInteger5, bigInteger3, bigInteger2, bigInteger4, bigInteger7, bigIntegerSubtract};
            }
            bigInteger5 = bigInteger2;
            bigInteger3 = bigInteger4;
            bigInteger2 = bigInteger7;
            bigInteger4 = bigIntegerSubtract;
        }
    }

    private static ECFieldElement[] findNonTrivialOrder3FieldElements(ECCurve eCCurve) {
        BigInteger bigIntegerModPow;
        BigInteger characteristic = eCCurve.getField().getCharacteristic();
        BigInteger bigIntegerDivide = characteristic.divide(ECConstants.THREE);
        SecureRandom secureRandom = new SecureRandom();
        do {
            bigIntegerModPow = BigIntegers.createRandomInRange(ECConstants.TWO, characteristic.subtract(ECConstants.TWO), secureRandom).modPow(bigIntegerDivide, characteristic);
        } while (bigIntegerModPow.equals(ECConstants.ONE));
        ECFieldElement eCFieldElementFromBigInteger = eCCurve.fromBigInteger(bigIntegerModPow);
        return new ECFieldElement[]{eCFieldElementFromBigInteger, eCFieldElementFromBigInteger.square()};
    }

    private static ECFieldElement[] findNonTrivialOrder4FieldElements(ECCurve eCCurve) {
        ECFieldElement eCFieldElementSqrt = eCCurve.fromBigInteger(ECConstants.ONE).negate().sqrt();
        if (eCFieldElementSqrt != null) {
            return new ECFieldElement[]{eCFieldElementSqrt, eCFieldElementSqrt.negate()};
        }
        throw new IllegalStateException("Calculation of non-trivial order-4  field elements failed unexpectedly");
    }

    private static BigInteger firstNonResidue(BigInteger bigInteger, BigInteger bigInteger2) {
        for (int i = 2; i < 1000; i++) {
            BigInteger bigIntegerValueOf = BigInteger.valueOf(i);
            if (!bigIntegerValueOf.modPow(bigInteger2, bigInteger).equals(ECConstants.ONE)) {
                return bigIntegerValueOf;
            }
        }
        throw new IllegalStateException();
    }

    private static BigInteger[] intersect(BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2) {
        BigInteger bigIntegerMax = bigIntegerArr[0].max(bigIntegerArr2[0]);
        BigInteger bigIntegerMin = bigIntegerArr[1].min(bigIntegerArr2[1]);
        if (bigIntegerMax.compareTo(bigIntegerMin) > 0) {
            return null;
        }
        return new BigInteger[]{bigIntegerMax, bigIntegerMin};
    }

    private static boolean isLessThanSqrt(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigIntegerAbs = bigInteger.abs();
        BigInteger bigIntegerAbs2 = bigInteger2.abs();
        int iBitLength = bigIntegerAbs2.bitLength();
        int iBitLength2 = bigIntegerAbs.bitLength() * 2;
        return iBitLength2 + (-1) <= iBitLength && (iBitLength2 < iBitLength || bigIntegerAbs.multiply(bigIntegerAbs).compareTo(bigIntegerAbs2) < 0);
    }

    private static boolean isShorter(BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2) {
        BigInteger bigIntegerAbs = bigIntegerArr[0].abs();
        BigInteger bigIntegerAbs2 = bigIntegerArr[1].abs();
        BigInteger bigIntegerAbs3 = bigIntegerArr2[0].abs();
        BigInteger bigIntegerAbs4 = bigIntegerArr2[1].abs();
        boolean z = bigIntegerAbs.compareTo(bigIntegerAbs3) < 0;
        return z == (bigIntegerAbs2.compareTo(bigIntegerAbs4) < 0) ? z : bigIntegerAbs.multiply(bigIntegerAbs).add(bigIntegerAbs2.multiply(bigIntegerAbs2)).compareTo(bigIntegerAbs3.multiply(bigIntegerAbs3).add(bigIntegerAbs4.multiply(bigIntegerAbs4))) < 0;
    }

    private static boolean isVectorBoundedBySqrt(BigInteger[] bigIntegerArr, BigInteger bigInteger) {
        return isLessThanSqrt(bigIntegerArr[0].abs().max(bigIntegerArr[1].abs()), bigInteger);
    }

    private static BigInteger isqrt(BigInteger bigInteger) {
        BigInteger bigIntegerShiftRight = bigInteger.shiftRight(bigInteger.bitLength() / 2);
        while (true) {
            BigInteger bigIntegerShiftRight2 = bigIntegerShiftRight.add(bigInteger.divide(bigIntegerShiftRight)).shiftRight(1);
            if (bigIntegerShiftRight2.equals(bigIntegerShiftRight)) {
                return bigIntegerShiftRight2;
            }
            bigIntegerShiftRight = bigIntegerShiftRight2;
        }
    }

    public static void main(String[] strArr) {
        if (strArr.length > 0) {
            for (String str : strArr) {
                discoverEndomorphisms(str);
            }
            return;
        }
        TreeSet treeSet = new TreeSet(enumToList(ECNamedCurveTable.getNames()));
        treeSet.addAll(enumToList(CustomNamedCurves.getNames()));
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            discoverEndomorphisms((String) it.next());
        }
    }

    private static BigInteger modSqrt(BigInteger bigInteger, BigInteger bigInteger2) {
        if (!bigInteger2.testBit(0)) {
            throw new IllegalStateException();
        }
        BigInteger bigIntegerShiftRight = bigInteger2.subtract(ECConstants.ONE).shiftRight(1);
        if (!bigInteger.modPow(bigIntegerShiftRight, bigInteger2).equals(ECConstants.ONE)) {
            return null;
        }
        BigInteger bigIntegerShiftRight2 = bigIntegerShiftRight;
        while (!bigIntegerShiftRight2.testBit(0)) {
            bigIntegerShiftRight2 = bigIntegerShiftRight2.shiftRight(1);
            if (!bigInteger.modPow(bigIntegerShiftRight2, bigInteger2).equals(ECConstants.ONE)) {
                return modSqrtComplex(bigInteger, bigIntegerShiftRight2, bigInteger2, bigIntegerShiftRight);
            }
        }
        return bigInteger.modPow(bigIntegerShiftRight2.add(ECConstants.ONE).shiftRight(1), bigInteger2);
    }

    private static BigInteger modSqrtComplex(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        BigInteger bigIntegerFirstNonResidue = firstNonResidue(bigInteger3, bigInteger4);
        BigInteger bigIntegerShiftRight = bigInteger4;
        while (!bigInteger2.testBit(0)) {
            bigInteger2 = bigInteger2.shiftRight(1);
            bigIntegerShiftRight = bigIntegerShiftRight.shiftRight(1);
            if (!bigInteger.modPow(bigInteger2, bigInteger3).equals(bigIntegerFirstNonResidue.modPow(bigIntegerShiftRight, bigInteger3))) {
                bigIntegerShiftRight = bigIntegerShiftRight.add(bigInteger4);
            }
        }
        return bigInteger.modInverse(bigInteger3).modPow(bigInteger2.subtract(ECConstants.ONE).shiftRight(1), bigInteger3).multiply(bigIntegerFirstNonResidue.modPow(bigIntegerShiftRight.shiftRight(1), bigInteger3)).mod(bigInteger3);
    }

    private static BigInteger[] order(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.compareTo(bigInteger2) <= 0 ? new BigInteger[]{bigInteger, bigInteger2} : new BigInteger[]{bigInteger2, bigInteger};
    }

    private static void printGLVTypeAParameters(X9ECParameters x9ECParameters) {
        BigInteger[] bigIntegerArrSolveQuadraticEquation = solveQuadraticEquation(x9ECParameters.getN(), ECConstants.ONE, ECConstants.ZERO, ECConstants.ONE);
        ECFieldElement[] eCFieldElementArrFindNonTrivialOrder4FieldElements = findNonTrivialOrder4FieldElements(x9ECParameters.getCurve());
        printGLVTypeAParameters(x9ECParameters, bigIntegerArrSolveQuadraticEquation[0], eCFieldElementArrFindNonTrivialOrder4FieldElements);
        System.out.println("OR");
        printGLVTypeAParameters(x9ECParameters, bigIntegerArrSolveQuadraticEquation[1], eCFieldElementArrFindNonTrivialOrder4FieldElements);
    }

    private static void printGLVTypeAParameters(X9ECParameters x9ECParameters, BigInteger bigInteger, ECFieldElement[] eCFieldElementArr) {
        ECPoint eCPointNormalize = x9ECParameters.getG().normalize();
        ECPoint eCPointNormalize2 = eCPointNormalize.multiply(bigInteger).normalize();
        if (!eCPointNormalize.getXCoord().negate().equals(eCPointNormalize2.getXCoord())) {
            throw new IllegalStateException("Derivation of GLV Type A parameters failed unexpectedly");
        }
        ECFieldElement eCFieldElement = eCFieldElementArr[0];
        if (!eCPointNormalize.getYCoord().multiply(eCFieldElement).equals(eCPointNormalize2.getYCoord())) {
            eCFieldElement = eCFieldElementArr[1];
            if (!eCPointNormalize.getYCoord().multiply(eCFieldElement).equals(eCPointNormalize2.getYCoord())) {
                throw new IllegalStateException("Derivation of GLV Type A parameters failed unexpectedly");
            }
        }
        printProperty("Point map", "lambda * (x, y) = (-x, i * y)");
        printProperty("i", eCFieldElement.toBigInteger().toString(16));
        printProperty("lambda", bigInteger.toString(16));
        printScalarDecompositionParameters(x9ECParameters.getN(), bigInteger);
    }

    private static void printGLVTypeBParameters(X9ECParameters x9ECParameters) {
        BigInteger[] bigIntegerArrSolveQuadraticEquation = solveQuadraticEquation(x9ECParameters.getN(), ECConstants.ONE, ECConstants.ONE, ECConstants.ONE);
        ECFieldElement[] eCFieldElementArrFindNonTrivialOrder3FieldElements = findNonTrivialOrder3FieldElements(x9ECParameters.getCurve());
        printGLVTypeBParameters(x9ECParameters, bigIntegerArrSolveQuadraticEquation[0], eCFieldElementArrFindNonTrivialOrder3FieldElements);
        System.out.println("OR");
        printGLVTypeBParameters(x9ECParameters, bigIntegerArrSolveQuadraticEquation[1], eCFieldElementArrFindNonTrivialOrder3FieldElements);
    }

    private static void printGLVTypeBParameters(X9ECParameters x9ECParameters, BigInteger bigInteger, ECFieldElement[] eCFieldElementArr) {
        ECPoint eCPointNormalize = x9ECParameters.getG().normalize();
        ECPoint eCPointNormalize2 = eCPointNormalize.multiply(bigInteger).normalize();
        if (!eCPointNormalize.getYCoord().equals(eCPointNormalize2.getYCoord())) {
            throw new IllegalStateException("Derivation of GLV Type B parameters failed unexpectedly");
        }
        ECFieldElement eCFieldElement = eCFieldElementArr[0];
        if (!eCPointNormalize.getXCoord().multiply(eCFieldElement).equals(eCPointNormalize2.getXCoord())) {
            eCFieldElement = eCFieldElementArr[1];
            if (!eCPointNormalize.getXCoord().multiply(eCFieldElement).equals(eCPointNormalize2.getXCoord())) {
                throw new IllegalStateException("Derivation of GLV Type B parameters failed unexpectedly");
            }
        }
        printProperty("Point map", "lambda * (x, y) = (beta * x, y)");
        printProperty(Constants.KEY_BETA, eCFieldElement.toBigInteger().toString(16));
        printProperty("lambda", bigInteger.toString(16));
        printScalarDecompositionParameters(x9ECParameters.getN(), bigInteger);
    }

    private static void printProperty(String str, Object obj) {
        StringBuffer stringBuffer = new StringBuffer("  ");
        stringBuffer.append(str);
        while (stringBuffer.length() < 20) {
            stringBuffer.append(' ');
        }
        stringBuffer.append(": ");
        stringBuffer.append(obj.toString());
        System.out.println(stringBuffer.toString());
    }

    private static void printScalarDecompositionParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger[] bigIntegerArrExtEuclidGLV = extEuclidGLV(bigInteger, bigInteger2);
        BigInteger[] bigIntegerArr = {bigIntegerArrExtEuclidGLV[2], bigIntegerArrExtEuclidGLV[3].negate()};
        BigInteger[] bigIntegerArrChooseShortest = chooseShortest(new BigInteger[]{bigIntegerArrExtEuclidGLV[0], bigIntegerArrExtEuclidGLV[1].negate()}, new BigInteger[]{bigIntegerArrExtEuclidGLV[4], bigIntegerArrExtEuclidGLV[5].negate()});
        if (!isVectorBoundedBySqrt(bigIntegerArrChooseShortest, bigInteger) && areRelativelyPrime(bigIntegerArr[0], bigIntegerArr[1])) {
            BigInteger bigInteger3 = bigIntegerArr[0];
            BigInteger bigInteger4 = bigIntegerArr[1];
            BigInteger bigIntegerDivide = bigInteger3.add(bigInteger4.multiply(bigInteger2)).divide(bigInteger);
            BigInteger[] bigIntegerArrExtEuclidBezout = extEuclidBezout(new BigInteger[]{bigIntegerDivide.abs(), bigInteger4.abs()});
            if (bigIntegerArrExtEuclidBezout != null) {
                BigInteger bigIntegerNegate = bigIntegerArrExtEuclidBezout[0];
                BigInteger bigIntegerNegate2 = bigIntegerArrExtEuclidBezout[1];
                if (bigIntegerDivide.signum() < 0) {
                    bigIntegerNegate = bigIntegerNegate.negate();
                }
                if (bigInteger4.signum() > 0) {
                    bigIntegerNegate2 = bigIntegerNegate2.negate();
                }
                if (!bigIntegerDivide.multiply(bigIntegerNegate).subtract(bigInteger4.multiply(bigIntegerNegate2)).equals(ECConstants.ONE)) {
                    throw new IllegalStateException();
                }
                BigInteger bigIntegerSubtract = bigIntegerNegate2.multiply(bigInteger).subtract(bigIntegerNegate.multiply(bigInteger2));
                BigInteger bigIntegerNegate3 = bigIntegerNegate.negate();
                BigInteger bigIntegerNegate4 = bigIntegerSubtract.negate();
                BigInteger bigIntegerAdd = isqrt(bigInteger.subtract(ECConstants.ONE)).add(ECConstants.ONE);
                BigInteger[] bigIntegerArrIntersect = intersect(calculateRange(bigIntegerNegate3, bigIntegerAdd, bigInteger4), calculateRange(bigIntegerNegate4, bigIntegerAdd, bigInteger3));
                if (bigIntegerArrIntersect != null) {
                    for (BigInteger bigIntegerAdd2 = bigIntegerArrIntersect[0]; bigIntegerAdd2.compareTo(bigIntegerArrIntersect[1]) <= 0; bigIntegerAdd2 = bigIntegerAdd2.add(ECConstants.ONE)) {
                        BigInteger[] bigIntegerArr2 = {bigIntegerSubtract.add(bigIntegerAdd2.multiply(bigInteger3)), bigIntegerNegate.add(bigIntegerAdd2.multiply(bigInteger4))};
                        if (isShorter(bigIntegerArr2, bigIntegerArrChooseShortest)) {
                            bigIntegerArrChooseShortest = bigIntegerArr2;
                        }
                    }
                }
            }
        }
        BigInteger bigIntegerSubtract2 = bigIntegerArr[0].multiply(bigIntegerArrChooseShortest[1]).subtract(bigIntegerArr[1].multiply(bigIntegerArrChooseShortest[0]));
        int iBitLength = (bigInteger.bitLength() + 16) - (bigInteger.bitLength() & 7);
        BigInteger bigIntegerRoundQuotient = roundQuotient(bigIntegerArrChooseShortest[1].shiftLeft(iBitLength), bigIntegerSubtract2);
        BigInteger bigIntegerNegate5 = roundQuotient(bigIntegerArr[1].shiftLeft(iBitLength), bigIntegerSubtract2).negate();
        printProperty("v1", "{ " + bigIntegerArr[0].toString(16) + ", " + bigIntegerArr[1].toString(16) + " }");
        printProperty("v2", "{ " + bigIntegerArrChooseShortest[0].toString(16) + ", " + bigIntegerArrChooseShortest[1].toString(16) + " }");
        printProperty("d", bigIntegerSubtract2.toString(16));
        printProperty("(OPT) g1", bigIntegerRoundQuotient.toString(16));
        printProperty("(OPT) g2", bigIntegerNegate5.toString(16));
        printProperty("(OPT) bits", Integer.toString(iBitLength));
    }

    private static BigInteger roundQuotient(BigInteger bigInteger, BigInteger bigInteger2) {
        boolean z = bigInteger.signum() != bigInteger2.signum();
        BigInteger bigIntegerAbs = bigInteger.abs();
        BigInteger bigIntegerAbs2 = bigInteger2.abs();
        BigInteger bigIntegerDivide = bigIntegerAbs.add(bigIntegerAbs2.shiftRight(1)).divide(bigIntegerAbs2);
        return z ? bigIntegerDivide.negate() : bigIntegerDivide;
    }

    private static BigInteger[] solveQuadraticEquation(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        BigInteger bigIntegerModSqrt = modSqrt(bigInteger3.multiply(bigInteger3).subtract(bigInteger2.multiply(bigInteger4).shiftLeft(2)).mod(bigInteger), bigInteger);
        if (bigIntegerModSqrt == null) {
            throw new IllegalStateException("Solving quadratic equation failed unexpectedly");
        }
        BigInteger bigIntegerModInverse = bigInteger2.shiftLeft(1).modInverse(bigInteger);
        return new BigInteger[]{bigIntegerModSqrt.subtract(bigInteger3).multiply(bigIntegerModInverse).mod(bigInteger), bigIntegerModSqrt.negate().subtract(bigInteger3).multiply(bigIntegerModInverse).mod(bigInteger)};
    }

    private static void swap(BigInteger[] bigIntegerArr) {
        BigInteger bigInteger = bigIntegerArr[0];
        bigIntegerArr[0] = bigIntegerArr[1];
        bigIntegerArr[1] = bigInteger;
    }
}
