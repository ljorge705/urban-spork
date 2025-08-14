package org.spongycastle.crypto.agreement;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithUKM;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.BigIntegers;

/* loaded from: classes3.dex */
public class ECVKOAgreement {
    private final Digest digest;
    private ECPrivateKeyParameters key;
    private BigInteger ukm;

    public ECVKOAgreement(Digest digest) {
        this.digest = digest;
    }

    public void init(CipherParameters cipherParameters) {
        ParametersWithUKM parametersWithUKM = (ParametersWithUKM) cipherParameters;
        this.key = (ECPrivateKeyParameters) parametersWithUKM.getParameters();
        this.ukm = toInteger(parametersWithUKM.getUKM());
    }

    public int getFieldSize() {
        return (this.key.getParameters().getCurve().getFieldSize() + 7) / 8;
    }

    public byte[] calculateAgreement(CipherParameters cipherParameters) {
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) cipherParameters;
        ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
        if (!parameters.equals(this.key.getParameters())) {
            throw new IllegalStateException("ECVKO public key has wrong domain parameters");
        }
        ECPoint eCPointNormalize = eCPublicKeyParameters.getQ().multiply(parameters.getH().multiply(this.ukm).multiply(this.key.getD()).mod(parameters.getN())).normalize();
        if (eCPointNormalize.isInfinity()) {
            throw new IllegalStateException("Infinity is not a valid agreement value for ECVKO");
        }
        return fromPoint(eCPointNormalize.normalize());
    }

    private static BigInteger toInteger(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i != length; i++) {
            bArr2[i] = bArr[(bArr.length - i) - 1];
        }
        return new BigInteger(1, bArr2);
    }

    private byte[] fromPoint(ECPoint eCPoint) {
        BigInteger bigInteger = eCPoint.getAffineXCoord().toBigInteger();
        BigInteger bigInteger2 = eCPoint.getAffineYCoord().toBigInteger();
        int i = bigInteger.toByteArray().length > 33 ? 64 : 32;
        int i2 = i * 2;
        byte[] bArr = new byte[i2];
        byte[] bArrAsUnsignedByteArray = BigIntegers.asUnsignedByteArray(i, bigInteger);
        byte[] bArrAsUnsignedByteArray2 = BigIntegers.asUnsignedByteArray(i, bigInteger2);
        for (int i3 = 0; i3 != i; i3++) {
            bArr[i3] = bArrAsUnsignedByteArray[(i - i3) - 1];
        }
        for (int i4 = 0; i4 != i; i4++) {
            bArr[i + i4] = bArrAsUnsignedByteArray2[(i - i4) - 1];
        }
        this.digest.update(bArr, 0, i2);
        byte[] bArr2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr2, 0);
        return bArr2;
    }
}
