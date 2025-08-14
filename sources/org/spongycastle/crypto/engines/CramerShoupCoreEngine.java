package org.spongycastle.crypto.engines;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.CramerShoupKeyParameters;
import org.spongycastle.crypto.params.CramerShoupPrivateKeyParameters;
import org.spongycastle.crypto.params.CramerShoupPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.BigIntegers;

/* loaded from: classes4.dex */
public class CramerShoupCoreEngine {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private boolean forEncryption;
    private CramerShoupKeyParameters key;
    private String label = null;
    private SecureRandom random;

    public void init(boolean z, CipherParameters cipherParameters, String str) {
        init(z, cipherParameters);
        this.label = str;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        SecureRandom random;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.key = (CramerShoupKeyParameters) parametersWithRandom.getParameters();
            random = parametersWithRandom.getRandom();
        } else {
            this.key = (CramerShoupKeyParameters) cipherParameters;
            random = null;
        }
        this.random = initSecureRandom(z, random);
        this.forEncryption = z;
    }

    public int getInputBlockSize() {
        int iBitLength = this.key.getParameters().getP().bitLength();
        if (this.forEncryption) {
            return ((iBitLength + 7) / 8) - 1;
        }
        return (iBitLength + 7) / 8;
    }

    public int getOutputBlockSize() {
        int iBitLength = this.key.getParameters().getP().bitLength();
        if (this.forEncryption) {
            return (iBitLength + 7) / 8;
        }
        return ((iBitLength + 7) / 8) - 1;
    }

    public BigInteger convertInput(byte[] bArr, int i, int i2) {
        if (i2 > getInputBlockSize() + 1) {
            throw new DataLengthException("input too large for Cramer Shoup cipher.");
        }
        if (i2 == getInputBlockSize() + 1 && this.forEncryption) {
            throw new DataLengthException("input too large for Cramer Shoup cipher.");
        }
        if (i != 0 || i2 != bArr.length) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            bArr = bArr2;
        }
        BigInteger bigInteger = new BigInteger(1, bArr);
        if (bigInteger.compareTo(this.key.getParameters().getP()) < 0) {
            return bigInteger;
        }
        throw new DataLengthException("input too large for Cramer Shoup cipher.");
    }

    public byte[] convertOutput(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (!this.forEncryption) {
            if (byteArray[0] == 0 && byteArray.length > getOutputBlockSize()) {
                int length = byteArray.length - 1;
                byte[] bArr = new byte[length];
                System.arraycopy(byteArray, 1, bArr, 0, length);
                return bArr;
            }
            if (byteArray.length < getOutputBlockSize()) {
                int outputBlockSize = getOutputBlockSize();
                byte[] bArr2 = new byte[outputBlockSize];
                System.arraycopy(byteArray, 0, bArr2, outputBlockSize - byteArray.length, byteArray.length);
                return bArr2;
            }
        } else if (byteArray[0] == 0) {
            int length2 = byteArray.length - 1;
            byte[] bArr3 = new byte[length2];
            System.arraycopy(byteArray, 1, bArr3, 0, length2);
            return bArr3;
        }
        return byteArray;
    }

    public CramerShoupCiphertext encryptBlock(BigInteger bigInteger) {
        if (this.key.isPrivate() || !this.forEncryption) {
            return null;
        }
        CramerShoupKeyParameters cramerShoupKeyParameters = this.key;
        if (!(cramerShoupKeyParameters instanceof CramerShoupPublicKeyParameters)) {
            return null;
        }
        CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters = (CramerShoupPublicKeyParameters) cramerShoupKeyParameters;
        BigInteger p = cramerShoupPublicKeyParameters.getParameters().getP();
        BigInteger g1 = cramerShoupPublicKeyParameters.getParameters().getG1();
        BigInteger g2 = cramerShoupPublicKeyParameters.getParameters().getG2();
        BigInteger h = cramerShoupPublicKeyParameters.getH();
        if (!isValidMessage(bigInteger, p)) {
            return null;
        }
        BigInteger bigIntegerGenerateRandomElement = generateRandomElement(p, this.random);
        BigInteger bigIntegerModPow = g1.modPow(bigIntegerGenerateRandomElement, p);
        BigInteger bigIntegerModPow2 = g2.modPow(bigIntegerGenerateRandomElement, p);
        BigInteger bigIntegerMod = h.modPow(bigIntegerGenerateRandomElement, p).multiply(bigInteger).mod(p);
        Digest h2 = cramerShoupPublicKeyParameters.getParameters().getH();
        byte[] byteArray = bigIntegerModPow.toByteArray();
        h2.update(byteArray, 0, byteArray.length);
        byte[] byteArray2 = bigIntegerModPow2.toByteArray();
        h2.update(byteArray2, 0, byteArray2.length);
        byte[] byteArray3 = bigIntegerMod.toByteArray();
        h2.update(byteArray3, 0, byteArray3.length);
        String str = this.label;
        if (str != null) {
            byte[] bytes = str.getBytes();
            h2.update(bytes, 0, bytes.length);
        }
        byte[] bArr = new byte[h2.getDigestSize()];
        h2.doFinal(bArr, 0);
        return new CramerShoupCiphertext(bigIntegerModPow, bigIntegerModPow2, bigIntegerMod, cramerShoupPublicKeyParameters.getC().modPow(bigIntegerGenerateRandomElement, p).multiply(cramerShoupPublicKeyParameters.getD().modPow(bigIntegerGenerateRandomElement.multiply(new BigInteger(1, bArr)), p)).mod(p));
    }

    public BigInteger decryptBlock(CramerShoupCiphertext cramerShoupCiphertext) throws CramerShoupCiphertextException {
        if (this.key.isPrivate() && !this.forEncryption) {
            CramerShoupKeyParameters cramerShoupKeyParameters = this.key;
            if (cramerShoupKeyParameters instanceof CramerShoupPrivateKeyParameters) {
                CramerShoupPrivateKeyParameters cramerShoupPrivateKeyParameters = (CramerShoupPrivateKeyParameters) cramerShoupKeyParameters;
                BigInteger p = cramerShoupPrivateKeyParameters.getParameters().getP();
                Digest h = cramerShoupPrivateKeyParameters.getParameters().getH();
                byte[] byteArray = cramerShoupCiphertext.getU1().toByteArray();
                h.update(byteArray, 0, byteArray.length);
                byte[] byteArray2 = cramerShoupCiphertext.getU2().toByteArray();
                h.update(byteArray2, 0, byteArray2.length);
                byte[] byteArray3 = cramerShoupCiphertext.getE().toByteArray();
                h.update(byteArray3, 0, byteArray3.length);
                String str = this.label;
                if (str != null) {
                    byte[] bytes = str.getBytes();
                    h.update(bytes, 0, bytes.length);
                }
                byte[] bArr = new byte[h.getDigestSize()];
                h.doFinal(bArr, 0);
                BigInteger bigInteger = new BigInteger(1, bArr);
                if (cramerShoupCiphertext.v.equals(cramerShoupCiphertext.u1.modPow(cramerShoupPrivateKeyParameters.getX1().add(cramerShoupPrivateKeyParameters.getY1().multiply(bigInteger)), p).multiply(cramerShoupCiphertext.u2.modPow(cramerShoupPrivateKeyParameters.getX2().add(cramerShoupPrivateKeyParameters.getY2().multiply(bigInteger)), p)).mod(p))) {
                    return cramerShoupCiphertext.e.multiply(cramerShoupCiphertext.u1.modPow(cramerShoupPrivateKeyParameters.getZ(), p).modInverse(p)).mod(p);
                }
                throw new CramerShoupCiphertextException("Sorry, that ciphertext is not correct");
            }
        }
        return null;
    }

    private BigInteger generateRandomElement(BigInteger bigInteger, SecureRandom secureRandom) {
        BigInteger bigInteger2 = ONE;
        return BigIntegers.createRandomInRange(bigInteger2, bigInteger.subtract(bigInteger2), secureRandom);
    }

    private boolean isValidMessage(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.compareTo(bigInteger2) < 0;
    }

    protected SecureRandom initSecureRandom(boolean z, SecureRandom secureRandom) {
        if (z) {
            return secureRandom != null ? secureRandom : new SecureRandom();
        }
        return null;
    }

    public static class CramerShoupCiphertextException extends Exception {
        private static final long serialVersionUID = -6360977166495345076L;

        public CramerShoupCiphertextException(String str) {
            super(str);
        }
    }
}
