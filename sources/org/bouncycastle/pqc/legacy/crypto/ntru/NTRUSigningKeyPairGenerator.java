package org.bouncycastle.pqc.legacy.crypto.ntru;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.legacy.crypto.ntru.NTRUSigningPrivateKeyParameters;
import org.bouncycastle.pqc.legacy.math.ntru.euclid.BigIntEuclidean;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.BigDecimalPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.BigIntPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.DenseTernaryPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.IntegerPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.Polynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.ProductFormPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.Resultant;

/* loaded from: classes4.dex */
public class NTRUSigningKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NTRUSigningKeyGenerationParameters params;

    private class BasisGenerationTask implements Callable<NTRUSigningPrivateKeyParameters.Basis> {
        private BasisGenerationTask() {
        }

        @Override // java.util.concurrent.Callable
        public NTRUSigningPrivateKeyParameters.Basis call() throws Exception {
            return NTRUSigningKeyPairGenerator.this.generateBoundedBasis();
        }
    }

    public static class FGBasis extends NTRUSigningPrivateKeyParameters.Basis {
        public IntegerPolynomial F;
        public IntegerPolynomial G;

        FGBasis(Polynomial polynomial, Polynomial polynomial2, IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) {
            super(polynomial, polynomial2, integerPolynomial, nTRUSigningKeyGenerationParameters);
            this.F = integerPolynomial2;
            this.G = integerPolynomial3;
        }

        boolean isNormOk() {
            double d = this.params.keyNormBoundSq;
            int i = this.params.q;
            return ((double) this.F.centeredNormSq(i)) < d && ((double) this.G.centeredNormSq(i)) < d;
        }
    }

    private FGBasis generateBasis() {
        boolean z;
        Polynomial polynomialGenerateRandom;
        Polynomial polynomial;
        IntegerPolynomial integerPolynomial;
        IntegerPolynomial integerPolynomialInvertFq;
        Resultant resultant;
        IntegerPolynomial integerPolynomial2;
        int i;
        IntegerPolynomial integerPolynomial3;
        int i2;
        Polynomial polynomial2;
        Polynomial polynomialGenerateRandom2;
        IntegerPolynomial integerPolynomial4;
        Resultant resultant2;
        BigIntEuclidean bigIntEuclideanCalculate;
        BigIntPolynomial bigIntPolynomialRound;
        IntegerPolynomial integerPolynomialMult;
        Polynomial polynomial3;
        int i3 = this.params.N;
        int i4 = this.params.q;
        int i5 = this.params.d;
        int i6 = this.params.d1;
        int i7 = this.params.d2;
        int i8 = this.params.d3;
        int i9 = this.params.basisType;
        int i10 = (i3 * 2) + 1;
        boolean z2 = this.params.primeCheck;
        while (true) {
            if (this.params.polyType == 0) {
                polynomialGenerateRandom = DenseTernaryPolynomial.generateRandom(i3, i5 + 1, i5, CryptoServicesRegistrar.getSecureRandom());
                z = z2;
            } else {
                z = z2;
                polynomialGenerateRandom = ProductFormPolynomial.generateRandom(i3, i6, i7, i8 + 1, i8, CryptoServicesRegistrar.getSecureRandom());
            }
            polynomial = polynomialGenerateRandom;
            integerPolynomial = polynomial.toIntegerPolynomial();
            if ((!z || !integerPolynomial.resultant(i10).res.equals(BigInteger.ZERO)) && (integerPolynomialInvertFq = integerPolynomial.invertFq(i4)) != null) {
                break;
            }
            z2 = z;
        }
        Resultant resultant3 = integerPolynomial.resultant();
        while (true) {
            if (this.params.polyType == 0) {
                polynomialGenerateRandom2 = DenseTernaryPolynomial.generateRandom(i3, i5 + 1, i5, CryptoServicesRegistrar.getSecureRandom());
                i = i6;
                resultant = resultant3;
                integerPolynomial2 = integerPolynomialInvertFq;
                integerPolynomial3 = integerPolynomial;
                i2 = i9;
                polynomial2 = polynomial;
            } else {
                int i11 = i6;
                resultant = resultant3;
                integerPolynomial2 = integerPolynomialInvertFq;
                i = i6;
                integerPolynomial3 = integerPolynomial;
                i2 = i9;
                polynomial2 = polynomial;
                polynomialGenerateRandom2 = ProductFormPolynomial.generateRandom(i3, i11, i7, i8 + 1, i8, CryptoServicesRegistrar.getSecureRandom());
            }
            integerPolynomial4 = polynomialGenerateRandom2.toIntegerPolynomial();
            if ((!z || !integerPolynomial4.resultant(i10).res.equals(BigInteger.ZERO)) && integerPolynomial4.invertFq(i4) != null) {
                resultant2 = integerPolynomial4.resultant();
                bigIntEuclideanCalculate = BigIntEuclidean.calculate(resultant.res, resultant2.res);
                if (bigIntEuclideanCalculate.gcd.equals(BigInteger.ONE)) {
                    break;
                }
            }
            integerPolynomial = integerPolynomial3;
            polynomial = polynomial2;
            resultant3 = resultant;
            i6 = i;
            i9 = i2;
            integerPolynomialInvertFq = integerPolynomial2;
        }
        BigIntPolynomial bigIntPolynomial = (BigIntPolynomial) resultant.rho.clone();
        bigIntPolynomial.mult(bigIntEuclideanCalculate.x.multiply(BigInteger.valueOf(i4)));
        BigIntPolynomial bigIntPolynomial2 = (BigIntPolynomial) resultant2.rho.clone();
        bigIntPolynomial2.mult(bigIntEuclideanCalculate.y.multiply(BigInteger.valueOf(-i4)));
        int i12 = 0;
        if (this.params.keyGenAlg == 0) {
            int[] iArr = new int[i3];
            int[] iArr2 = new int[i3];
            iArr[0] = integerPolynomial3.coeffs[0];
            iArr2[0] = integerPolynomial4.coeffs[0];
            for (int i13 = 1; i13 < i3; i13++) {
                int i14 = i3 - i13;
                iArr[i13] = integerPolynomial3.coeffs[i14];
                iArr2[i13] = integerPolynomial4.coeffs[i14];
            }
            IntegerPolynomial integerPolynomial5 = new IntegerPolynomial(iArr);
            IntegerPolynomial integerPolynomial6 = new IntegerPolynomial(iArr2);
            IntegerPolynomial integerPolynomialMult2 = polynomial2.mult(integerPolynomial5);
            integerPolynomialMult2.add(polynomialGenerateRandom2.mult(integerPolynomial6));
            Resultant resultant4 = integerPolynomialMult2.resultant();
            BigIntPolynomial bigIntPolynomialMult = integerPolynomial5.mult(bigIntPolynomial2);
            bigIntPolynomialMult.add(integerPolynomial6.mult(bigIntPolynomial));
            bigIntPolynomialRound = bigIntPolynomialMult.mult(resultant4.rho);
            bigIntPolynomialRound.div(resultant4.res);
        } else {
            for (int i15 = 1; i15 < i3; i15 *= 10) {
                i12++;
            }
            BigDecimalPolynomial bigDecimalPolynomialDiv = resultant.rho.div(new BigDecimal(resultant.res), bigIntPolynomial2.getMaxCoeffLength() + 1 + i12);
            BigDecimalPolynomial bigDecimalPolynomialDiv2 = resultant2.rho.div(new BigDecimal(resultant2.res), bigIntPolynomial.getMaxCoeffLength() + 1 + i12);
            BigDecimalPolynomial bigDecimalPolynomialMult = bigDecimalPolynomialDiv.mult(bigIntPolynomial2);
            bigDecimalPolynomialMult.add(bigDecimalPolynomialDiv2.mult(bigIntPolynomial));
            bigDecimalPolynomialMult.halve();
            bigIntPolynomialRound = bigDecimalPolynomialMult.round();
        }
        BigIntPolynomial bigIntPolynomial3 = (BigIntPolynomial) bigIntPolynomial2.clone();
        bigIntPolynomial3.sub(polynomial2.mult(bigIntPolynomialRound));
        BigIntPolynomial bigIntPolynomial4 = (BigIntPolynomial) bigIntPolynomial.clone();
        bigIntPolynomial4.sub(polynomialGenerateRandom2.mult(bigIntPolynomialRound));
        IntegerPolynomial integerPolynomial7 = new IntegerPolynomial(bigIntPolynomial3);
        IntegerPolynomial integerPolynomial8 = new IntegerPolynomial(bigIntPolynomial4);
        minimizeFG(integerPolynomial3, integerPolynomial4, integerPolynomial7, integerPolynomial8, i3);
        IntegerPolynomial integerPolynomial9 = integerPolynomial2;
        if (i2 == 0) {
            integerPolynomialMult = polynomialGenerateRandom2.mult(integerPolynomial9, i4);
            polynomial3 = integerPolynomial7;
        } else {
            integerPolynomialMult = integerPolynomial7.mult(integerPolynomial9, i4);
            polynomial3 = polynomialGenerateRandom2;
        }
        integerPolynomialMult.modPositive(i4);
        return new FGBasis(polynomial2, polynomial3, integerPolynomialMult, integerPolynomial7, integerPolynomial8, this.params);
    }

    private void minimizeFG(IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3, IntegerPolynomial integerPolynomial4, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += i * 2 * ((integerPolynomial.coeffs[i3] * integerPolynomial.coeffs[i3]) + (integerPolynomial2.coeffs[i3] * integerPolynomial2.coeffs[i3]));
        }
        int i4 = i2 - 4;
        IntegerPolynomial integerPolynomial5 = (IntegerPolynomial) integerPolynomial.clone();
        IntegerPolynomial integerPolynomial6 = (IntegerPolynomial) integerPolynomial2.clone();
        int i5 = 0;
        int i6 = 0;
        while (i5 < i && i6 < i) {
            int i7 = 0;
            for (int i8 = 0; i8 < i; i8++) {
                i7 += i * 4 * ((integerPolynomial3.coeffs[i8] * integerPolynomial.coeffs[i8]) + (integerPolynomial4.coeffs[i8] * integerPolynomial2.coeffs[i8]));
            }
            int iSumCoeffs = i7 - ((integerPolynomial3.sumCoeffs() + integerPolynomial4.sumCoeffs()) * 4);
            if (iSumCoeffs > i4) {
                integerPolynomial3.sub(integerPolynomial5);
                integerPolynomial4.sub(integerPolynomial6);
            } else if (iSumCoeffs < (-i4)) {
                integerPolynomial3.add(integerPolynomial5);
                integerPolynomial4.add(integerPolynomial6);
            } else {
                i6++;
                integerPolynomial5.rotate1();
                integerPolynomial6.rotate1();
            }
            i5++;
            i6 = 0;
            i6++;
            integerPolynomial5.rotate1();
            integerPolynomial6.rotate1();
        }
    }

    public NTRUSigningPrivateKeyParameters.Basis generateBoundedBasis() {
        FGBasis fGBasisGenerateBasis;
        do {
            fGBasisGenerateBasis = generateBasis();
        } while (!fGBasisGenerateBasis.isNormOk());
        return fGBasisGenerateBasis;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters;
        ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool();
        ArrayList arrayList = new ArrayList();
        int i = this.params.B;
        while (true) {
            nTRUSigningPublicKeyParameters = null;
            Object[] objArr = 0;
            if (i < 0) {
                break;
            }
            arrayList.add(executorServiceNewCachedThreadPool.submit(new BasisGenerationTask()));
            i--;
        }
        executorServiceNewCachedThreadPool.shutdown();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = this.params.B; i2 >= 0; i2--) {
            Future future = (Future) arrayList.get(i2);
            try {
                arrayList2.add((NTRUSigningPrivateKeyParameters.Basis) future.get());
                if (i2 == this.params.B) {
                    nTRUSigningPublicKeyParameters = new NTRUSigningPublicKeyParameters(((NTRUSigningPrivateKeyParameters.Basis) future.get()).h, this.params.getSigningParameters());
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) nTRUSigningPublicKeyParameters, (AsymmetricKeyParameter) new NTRUSigningPrivateKeyParameters(arrayList2, nTRUSigningPublicKeyParameters));
    }

    public AsymmetricCipherKeyPair generateKeyPairSingleThread() {
        ArrayList arrayList = new ArrayList();
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters = null;
        for (int i = this.params.B; i >= 0; i--) {
            NTRUSigningPrivateKeyParameters.Basis basisGenerateBoundedBasis = generateBoundedBasis();
            arrayList.add(basisGenerateBoundedBasis);
            if (i == 0) {
                nTRUSigningPublicKeyParameters = new NTRUSigningPublicKeyParameters(basisGenerateBoundedBasis.h, this.params.getSigningParameters());
            }
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) nTRUSigningPublicKeyParameters, (AsymmetricKeyParameter) new NTRUSigningPrivateKeyParameters(arrayList, nTRUSigningPublicKeyParameters));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.params = (NTRUSigningKeyGenerationParameters) keyGenerationParameters;
    }
}
