package org.spongycastle.crypto.kems;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.KeyEncapsulation;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECMultiplier;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.ec.FixedPointCombMultiplier;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

/* loaded from: classes2.dex */
public class ECIESKeyEncapsulation implements KeyEncapsulation {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private boolean CofactorMode;
    private boolean OldCofactorMode;
    private boolean SingleHashMode;
    private DerivationFunction kdf;
    private ECKeyParameters key;
    private SecureRandom rnd;

    public ECIESKeyEncapsulation(DerivationFunction derivationFunction, SecureRandom secureRandom) {
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
        this.CofactorMode = false;
        this.OldCofactorMode = false;
        this.SingleHashMode = false;
    }

    public ECIESKeyEncapsulation(DerivationFunction derivationFunction, SecureRandom secureRandom, boolean z, boolean z2, boolean z3) {
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
        this.CofactorMode = z;
        this.OldCofactorMode = z2;
        this.SingleHashMode = z3;
    }

    @Override // org.spongycastle.crypto.KeyEncapsulation
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ECKeyParameters)) {
            throw new IllegalArgumentException("EC key required");
        }
        this.key = (ECKeyParameters) cipherParameters;
    }

    @Override // org.spongycastle.crypto.KeyEncapsulation
    public CipherParameters encrypt(byte[] bArr, int i, int i2) throws IllegalArgumentException {
        ECKeyParameters eCKeyParameters = this.key;
        if (!(eCKeyParameters instanceof ECPublicKeyParameters)) {
            throw new IllegalArgumentException("Public key required for encryption");
        }
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) eCKeyParameters;
        ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
        ECCurve curve = parameters.getCurve();
        BigInteger n2 = parameters.getN();
        BigInteger h = parameters.getH();
        BigInteger bigIntegerCreateRandomInRange = BigIntegers.createRandomInRange(ONE, n2, this.rnd);
        ECPoint[] eCPointArr = {createBasePointMultiplier().multiply(parameters.getG(), bigIntegerCreateRandomInRange), eCPublicKeyParameters.getQ().multiply(this.CofactorMode ? bigIntegerCreateRandomInRange.multiply(h).mod(n2) : bigIntegerCreateRandomInRange)};
        curve.normalizeAll(eCPointArr);
        ECPoint eCPoint = eCPointArr[0];
        ECPoint eCPoint2 = eCPointArr[1];
        byte[] encoded = eCPoint.getEncoded(false);
        System.arraycopy(encoded, 0, bArr, i, encoded.length);
        return deriveKey(i2, encoded, eCPoint2.getAffineXCoord().getEncoded());
    }

    public CipherParameters encrypt(byte[] bArr, int i) {
        return encrypt(bArr, 0, i);
    }

    @Override // org.spongycastle.crypto.KeyEncapsulation
    public CipherParameters decrypt(byte[] bArr, int i, int i2, int i3) throws IllegalArgumentException {
        ECKeyParameters eCKeyParameters = this.key;
        if (!(eCKeyParameters instanceof ECPrivateKeyParameters)) {
            throw new IllegalArgumentException("Private key required for encryption");
        }
        ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters) eCKeyParameters;
        ECDomainParameters parameters = eCPrivateKeyParameters.getParameters();
        ECCurve curve = parameters.getCurve();
        BigInteger n2 = parameters.getN();
        BigInteger h = parameters.getH();
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        ECPoint eCPointDecodePoint = curve.decodePoint(bArr2);
        if (this.CofactorMode || this.OldCofactorMode) {
            eCPointDecodePoint = eCPointDecodePoint.multiply(h);
        }
        BigInteger d = eCPrivateKeyParameters.getD();
        if (this.CofactorMode) {
            d = d.multiply(h.modInverse(n2)).mod(n2);
        }
        return deriveKey(i3, bArr2, eCPointDecodePoint.multiply(d).normalize().getAffineXCoord().getEncoded());
    }

    public CipherParameters decrypt(byte[] bArr, int i) {
        return decrypt(bArr, 0, bArr.length, i);
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    protected KeyParameter deriveKey(int i, byte[] bArr, byte[] bArr2) {
        if (!this.SingleHashMode) {
            byte[] bArrConcatenate = Arrays.concatenate(bArr, bArr2);
            Arrays.fill(bArr2, (byte) 0);
            bArr2 = bArrConcatenate;
        }
        try {
            this.kdf.init(new KDFParameters(bArr2, null));
            byte[] bArr3 = new byte[i];
            this.kdf.generateBytes(bArr3, 0, i);
            return new KeyParameter(bArr3);
        } finally {
            Arrays.fill(bArr2, (byte) 0);
        }
    }
}
