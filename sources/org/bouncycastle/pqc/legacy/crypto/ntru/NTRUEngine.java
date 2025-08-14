package org.bouncycastle.pqc.legacy.crypto.ntru;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.DenseTernaryPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.IntegerPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.Polynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.ProductFormPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.SparseTernaryPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.TernaryPolynomial;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class NTRUEngine implements AsymmetricBlockCipher {
    private boolean forEncryption;
    private NTRUEncryptionParameters params;
    private NTRUEncryptionPrivateKeyParameters privKey;
    private NTRUEncryptionPublicKeyParameters pubKey;
    private SecureRandom random;

    private IntegerPolynomial MGF(byte[] bArr, int i, int i2, boolean z) {
        Digest digest = this.params.hashAlg;
        int digestSize = digest.getDigestSize();
        byte[] bArrCalcHash = new byte[i2 * digestSize];
        if (z) {
            bArr = calcHash(digest, bArr);
        }
        int i3 = 0;
        while (i3 < i2) {
            digest.update(bArr, 0, bArr.length);
            putInt(digest, i3);
            System.arraycopy(calcHash(digest), 0, bArrCalcHash, i3 * digestSize, digestSize);
            i3++;
        }
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(i);
        while (true) {
            int i4 = 0;
            for (int i5 = 0; i5 != bArrCalcHash.length; i5++) {
                int i6 = bArrCalcHash[i5] & 255;
                if (i6 < 243) {
                    for (int i7 = 0; i7 < 4; i7++) {
                        int i8 = i6 % 3;
                        integerPolynomial.coeffs[i4] = i8 - 1;
                        i4++;
                        if (i4 == i) {
                            return integerPolynomial;
                        }
                        i6 = (i6 - i8) / 3;
                    }
                    integerPolynomial.coeffs[i4] = i6 - 1;
                    i4++;
                    if (i4 == i) {
                        return integerPolynomial;
                    }
                }
            }
            if (i4 >= i) {
                return integerPolynomial;
            }
            digest.update(bArr, 0, bArr.length);
            putInt(digest, i3);
            bArrCalcHash = calcHash(digest);
            i3++;
        }
    }

    private byte[] buildSData(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        byte[] bArr5 = new byte[bArr.length + i + bArr3.length + bArr4.length];
        System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr5, bArr.length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr5, bArr.length + bArr2.length, bArr3.length);
        System.arraycopy(bArr4, 0, bArr5, bArr.length + bArr2.length + bArr3.length, bArr4.length);
        return bArr5;
    }

    private byte[] calcHash(Digest digest) {
        byte[] bArr = new byte[digest.getDigestSize()];
        digest.doFinal(bArr, 0);
        return bArr;
    }

    private byte[] calcHash(Digest digest, byte[] bArr) {
        byte[] bArr2 = new byte[digest.getDigestSize()];
        digest.update(bArr, 0, bArr.length);
        digest.doFinal(bArr2, 0);
        return bArr2;
    }

    private byte[] copyOf(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        if (i >= bArr.length) {
            i = bArr.length;
        }
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    private byte[] decrypt(byte[] bArr, NTRUEncryptionPrivateKeyParameters nTRUEncryptionPrivateKeyParameters) throws InvalidCipherTextException {
        Polynomial polynomial = nTRUEncryptionPrivateKeyParameters.t;
        IntegerPolynomial integerPolynomial = nTRUEncryptionPrivateKeyParameters.fp;
        IntegerPolynomial integerPolynomial2 = nTRUEncryptionPrivateKeyParameters.h;
        int i = this.params.N;
        int i2 = this.params.q;
        int i3 = this.params.db;
        int i4 = this.params.maxMsgLenBytes;
        int i5 = this.params.dm0;
        int i6 = this.params.pkLen;
        int i7 = this.params.minCallsMask;
        boolean z = this.params.hashSeed;
        byte[] bArr2 = this.params.oid;
        if (i4 > 255) {
            throw new DataLengthException("maxMsgLenBytes values bigger than 255 are not supported");
        }
        int i8 = i3 / 8;
        IntegerPolynomial integerPolynomialFromBinary = IntegerPolynomial.fromBinary(bArr, i, i2);
        IntegerPolynomial integerPolynomialDecrypt = decrypt(integerPolynomialFromBinary, polynomial, integerPolynomial);
        if (integerPolynomialDecrypt.count(-1) < i5) {
            throw new InvalidCipherTextException("Less than dm0 coefficients equal -1");
        }
        if (integerPolynomialDecrypt.count(0) < i5) {
            throw new InvalidCipherTextException("Less than dm0 coefficients equal 0");
        }
        if (integerPolynomialDecrypt.count(1) < i5) {
            throw new InvalidCipherTextException("Less than dm0 coefficients equal 1");
        }
        IntegerPolynomial integerPolynomial3 = (IntegerPolynomial) integerPolynomialFromBinary.clone();
        integerPolynomial3.sub(integerPolynomialDecrypt);
        integerPolynomial3.modPositive(i2);
        IntegerPolynomial integerPolynomial4 = (IntegerPolynomial) integerPolynomial3.clone();
        integerPolynomial4.modPositive(4);
        integerPolynomialDecrypt.sub(MGF(integerPolynomial4.toBinary(4), i, i7, z));
        integerPolynomialDecrypt.mod3();
        byte[] binary3Sves = integerPolynomialDecrypt.toBinary3Sves();
        byte[] bArr3 = new byte[i8];
        System.arraycopy(binary3Sves, 0, bArr3, 0, i8);
        int i9 = 255 & binary3Sves[i8];
        if (i9 > i4) {
            throw new InvalidCipherTextException("Message too long: " + i9 + ">" + i4);
        }
        byte[] bArr4 = new byte[i9];
        int i10 = i8 + 1;
        System.arraycopy(binary3Sves, i10, bArr4, 0, i9);
        int i11 = i10 + i9;
        int length = binary3Sves.length - i11;
        byte[] bArr5 = new byte[length];
        System.arraycopy(binary3Sves, i11, bArr5, 0, length);
        if (!Arrays.constantTimeAreEqual(bArr5, new byte[length])) {
            throw new InvalidCipherTextException("The message is not followed by zeroes");
        }
        IntegerPolynomial integerPolynomialMult = generateBlindingPoly(buildSData(bArr2, bArr4, i9, bArr3, copyOf(integerPolynomial2.toBinary(i2), i6 / 8)), bArr4).mult(integerPolynomial2);
        integerPolynomialMult.modPositive(i2);
        if (integerPolynomialMult.equals(integerPolynomial3)) {
            return bArr4;
        }
        throw new InvalidCipherTextException("Invalid message encoding");
    }

    private byte[] encrypt(byte[] bArr, NTRUEncryptionPublicKeyParameters nTRUEncryptionPublicKeyParameters) {
        byte[] bArr2 = bArr;
        IntegerPolynomial integerPolynomial = nTRUEncryptionPublicKeyParameters.h;
        int i = this.params.N;
        int i2 = this.params.q;
        int i3 = this.params.maxMsgLenBytes;
        int i4 = this.params.db;
        int i5 = this.params.bufferLenBits;
        int i6 = this.params.dm0;
        int i7 = this.params.pkLen;
        int i8 = this.params.minCallsMask;
        boolean z = this.params.hashSeed;
        byte[] bArr3 = this.params.oid;
        int length = bArr2.length;
        if (i3 > 255) {
            throw new IllegalArgumentException("llen values bigger than 1 are not supported");
        }
        if (length > i3) {
            throw new DataLengthException("Message too long: " + length + ">" + i3);
        }
        while (true) {
            int i9 = i4 / 8;
            byte[] bArr4 = new byte[i9];
            boolean z2 = z;
            this.random.nextBytes(bArr4);
            int i10 = (i3 + 1) - length;
            int i11 = i8;
            int i12 = i4;
            byte[] bArr5 = new byte[i5 / 8];
            int i13 = i5;
            System.arraycopy(bArr4, 0, bArr5, 0, i9);
            bArr5[i9] = (byte) length;
            int i14 = i9 + 1;
            System.arraycopy(bArr2, 0, bArr5, i14, bArr2.length);
            System.arraycopy(new byte[i10], 0, bArr5, i14 + bArr2.length, i10);
            IntegerPolynomial integerPolynomialFromBinary3Sves = IntegerPolynomial.fromBinary3Sves(bArr5, i);
            int i15 = length;
            byte[] bArr6 = bArr3;
            int i16 = i7;
            IntegerPolynomial integerPolynomialMult = generateBlindingPoly(buildSData(bArr3, bArr, i15, bArr4, copyOf(integerPolynomial.toBinary(i2), i7 / 8)), bArr5).mult(integerPolynomial, i2);
            IntegerPolynomial integerPolynomial2 = (IntegerPolynomial) integerPolynomialMult.clone();
            integerPolynomial2.modPositive(4);
            integerPolynomialFromBinary3Sves.add(MGF(integerPolynomial2.toBinary(4), i, i11, z2));
            integerPolynomialFromBinary3Sves.mod3();
            if (integerPolynomialFromBinary3Sves.count(-1) >= i6 && integerPolynomialFromBinary3Sves.count(0) >= i6 && integerPolynomialFromBinary3Sves.count(1) >= i6) {
                integerPolynomialMult.add(integerPolynomialFromBinary3Sves, i2);
                integerPolynomialMult.ensurePositive(i2);
                return integerPolynomialMult.toBinary(i2);
            }
            z = z2;
            i8 = i11;
            i7 = i16;
            i4 = i12;
            i5 = i13;
            length = i15;
            bArr3 = bArr6;
            bArr2 = bArr;
        }
    }

    private int[] generateBlindingCoeffs(IndexGenerator indexGenerator, int i) {
        int[] iArr = new int[this.params.N];
        for (int i2 = -1; i2 <= 1; i2 += 2) {
            int i3 = 0;
            while (i3 < i) {
                int iNextIndex = indexGenerator.nextIndex();
                if (iArr[iNextIndex] == 0) {
                    iArr[iNextIndex] = i2;
                    i3++;
                }
            }
        }
        return iArr;
    }

    private Polynomial generateBlindingPoly(byte[] bArr, byte[] bArr2) {
        IndexGenerator indexGenerator = new IndexGenerator(bArr, this.params);
        if (this.params.polyType == 1) {
            return new ProductFormPolynomial(new SparseTernaryPolynomial(generateBlindingCoeffs(indexGenerator, this.params.dr1)), new SparseTernaryPolynomial(generateBlindingCoeffs(indexGenerator, this.params.dr2)), new SparseTernaryPolynomial(generateBlindingCoeffs(indexGenerator, this.params.dr3)));
        }
        int i = this.params.dr;
        boolean z = this.params.sparse;
        int[] iArrGenerateBlindingCoeffs = generateBlindingCoeffs(indexGenerator, i);
        return z ? new SparseTernaryPolynomial(iArrGenerateBlindingCoeffs) : new DenseTernaryPolynomial(iArrGenerateBlindingCoeffs);
    }

    private int log2(int i) {
        if (i == 2048) {
            return 11;
        }
        throw new IllegalStateException("log2 not fully implemented");
    }

    private void putInt(Digest digest, int i) {
        digest.update((byte) (i >> 24));
        digest.update((byte) (i >> 16));
        digest.update((byte) (i >> 8));
        digest.update((byte) i);
    }

    protected IntegerPolynomial decrypt(IntegerPolynomial integerPolynomial, Polynomial polynomial, IntegerPolynomial integerPolynomial2) {
        IntegerPolynomial integerPolynomialMult;
        if (this.params.fastFp) {
            integerPolynomialMult = polynomial.mult(integerPolynomial, this.params.q);
            integerPolynomialMult.mult(3);
            integerPolynomialMult.add(integerPolynomial);
        } else {
            integerPolynomialMult = polynomial.mult(integerPolynomial, this.params.q);
        }
        integerPolynomialMult.center0(this.params.q);
        integerPolynomialMult.mod3();
        if (!this.params.fastFp) {
            integerPolynomialMult = new DenseTernaryPolynomial(integerPolynomialMult).mult(integerPolynomial2, 3);
        }
        integerPolynomialMult.center0(3);
        return integerPolynomialMult;
    }

    protected IntegerPolynomial encrypt(IntegerPolynomial integerPolynomial, TernaryPolynomial ternaryPolynomial, IntegerPolynomial integerPolynomial2) {
        IntegerPolynomial integerPolynomialMult = ternaryPolynomial.mult(integerPolynomial2, this.params.q);
        integerPolynomialMult.add(integerPolynomial, this.params.q);
        integerPolynomialMult.ensurePositive(this.params.q);
        return integerPolynomialMult;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        return this.params.maxMsgLenBytes;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        return ((this.params.N * log2(this.params.q)) + 7) / 8;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        SecureRandom random;
        this.forEncryption = z;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            random = parametersWithRandom.getRandom();
            cipherParameters = parametersWithRandom.getParameters();
        } else {
            random = null;
        }
        if (z) {
            NTRUEncryptionPublicKeyParameters nTRUEncryptionPublicKeyParameters = (NTRUEncryptionPublicKeyParameters) cipherParameters;
            this.pubKey = nTRUEncryptionPublicKeyParameters;
            this.privKey = null;
            this.params = nTRUEncryptionPublicKeyParameters.getParameters();
            this.random = CryptoServicesRegistrar.getSecureRandom(random);
            return;
        }
        this.pubKey = null;
        NTRUEncryptionPrivateKeyParameters nTRUEncryptionPrivateKeyParameters = (NTRUEncryptionPrivateKeyParameters) cipherParameters;
        this.privKey = nTRUEncryptionPrivateKeyParameters;
        this.params = nTRUEncryptionPrivateKeyParameters.getParameters();
        this.random = null;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return this.forEncryption ? encrypt(bArr2, this.pubKey) : decrypt(bArr2, this.privKey);
    }
}
