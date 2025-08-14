package org.spongycastle.crypto.signers;

import androidx.exifinterface.media.ExifInterface;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.params.GOST3410KeyParameters;
import org.spongycastle.crypto.params.GOST3410Parameters;
import org.spongycastle.crypto.params.GOST3410PrivateKeyParameters;
import org.spongycastle.crypto.params.GOST3410PublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;

/* loaded from: classes7.dex */
public class GOST3410Signer implements DSA {
    GOST3410KeyParameters key;
    SecureRandom random;

    @Override // org.spongycastle.crypto.DSA
    public void init(boolean z, CipherParameters cipherParameters) {
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.random = parametersWithRandom.getRandom();
                this.key = (GOST3410PrivateKeyParameters) parametersWithRandom.getParameters();
                return;
            } else {
                this.random = new SecureRandom();
                this.key = (GOST3410PrivateKeyParameters) cipherParameters;
                return;
            }
        }
        this.key = (GOST3410PublicKeyParameters) cipherParameters;
    }

    @Override // org.spongycastle.crypto.DSA
    public BigInteger[] generateSignature(byte[] bArr) {
        BigInteger bigInteger;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i != length; i++) {
            bArr2[i] = bArr[(length - 1) - i];
        }
        BigInteger bigInteger2 = new BigInteger(1, bArr2);
        GOST3410Parameters parameters = this.key.getParameters();
        do {
            bigInteger = new BigInteger(parameters.getQ().bitLength(), this.random);
        } while (bigInteger.compareTo(parameters.getQ()) >= 0);
        BigInteger bigIntegerMod = parameters.getA().modPow(bigInteger, parameters.getP()).mod(parameters.getQ());
        return new BigInteger[]{bigIntegerMod, bigInteger.multiply(bigInteger2).add(((GOST3410PrivateKeyParameters) this.key).getX().multiply(bigIntegerMod)).mod(parameters.getQ())};
    }

    @Override // org.spongycastle.crypto.DSA
    public boolean verifySignature(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i != length; i++) {
            bArr2[i] = bArr[(length - 1) - i];
        }
        BigInteger bigInteger3 = new BigInteger(1, bArr2);
        GOST3410Parameters parameters = this.key.getParameters();
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0L);
        if (bigIntegerValueOf.compareTo(bigInteger) >= 0 || parameters.getQ().compareTo(bigInteger) <= 0 || bigIntegerValueOf.compareTo(bigInteger2) >= 0 || parameters.getQ().compareTo(bigInteger2) <= 0) {
            return false;
        }
        BigInteger bigIntegerModPow = bigInteger3.modPow(parameters.getQ().subtract(new BigInteger(ExifInterface.GPS_MEASUREMENT_2D)), parameters.getQ());
        return parameters.getA().modPow(bigInteger2.multiply(bigIntegerModPow).mod(parameters.getQ()), parameters.getP()).multiply(((GOST3410PublicKeyParameters) this.key).getY().modPow(parameters.getQ().subtract(bigInteger).multiply(bigIntegerModPow).mod(parameters.getQ()), parameters.getP())).mod(parameters.getP()).mod(parameters.getQ()).equals(bigInteger);
    }
}
