package org.bouncycastle.crypto.agreement;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithID;
import org.bouncycastle.crypto.params.SM2KeyExchangePrivateParameters;
import org.bouncycastle.crypto.params.SM2KeyExchangePublicParameters;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes4.dex */
public class SM2KeyExchange {
    private final Digest digest;
    private ECDomainParameters ecParams;
    private ECPrivateKeyParameters ephemeralKey;
    private ECPoint ephemeralPubPoint;
    private boolean initiator;
    private ECPrivateKeyParameters staticKey;
    private ECPoint staticPubPoint;
    private byte[] userID;
    private int w;

    public SM2KeyExchange() {
        this(new SM3Digest());
    }

    public SM2KeyExchange(Digest digest) {
        this.digest = digest;
    }

    private byte[] S1(Digest digest, ECPoint eCPoint, byte[] bArr) {
        digest.update((byte) 2);
        addFieldElement(digest, eCPoint.getAffineYCoord());
        digest.update(bArr, 0, bArr.length);
        return digestDoFinal();
    }

    private byte[] S2(Digest digest, ECPoint eCPoint, byte[] bArr) {
        digest.update((byte) 3);
        addFieldElement(digest, eCPoint.getAffineYCoord());
        digest.update(bArr, 0, bArr.length);
        return digestDoFinal();
    }

    private void addFieldElement(Digest digest, ECFieldElement eCFieldElement) {
        byte[] encoded = eCFieldElement.getEncoded();
        digest.update(encoded, 0, encoded.length);
    }

    private void addUserID(Digest digest, byte[] bArr) {
        int length = bArr.length * 8;
        digest.update((byte) (length >>> 8));
        digest.update((byte) length);
        digest.update(bArr, 0, bArr.length);
    }

    private byte[] calculateInnerHash(Digest digest, ECPoint eCPoint, byte[] bArr, byte[] bArr2, ECPoint eCPoint2, ECPoint eCPoint3) {
        addFieldElement(digest, eCPoint.getAffineXCoord());
        digest.update(bArr, 0, bArr.length);
        digest.update(bArr2, 0, bArr2.length);
        addFieldElement(digest, eCPoint2.getAffineXCoord());
        addFieldElement(digest, eCPoint2.getAffineYCoord());
        addFieldElement(digest, eCPoint3.getAffineXCoord());
        addFieldElement(digest, eCPoint3.getAffineYCoord());
        return digestDoFinal();
    }

    private ECPoint calculateU(SM2KeyExchangePublicParameters sM2KeyExchangePublicParameters) {
        ECDomainParameters parameters = this.staticKey.getParameters();
        ECPoint eCPointCleanPoint = ECAlgorithms.cleanPoint(parameters.getCurve(), sM2KeyExchangePublicParameters.getStaticPublicKey().getQ());
        ECPoint eCPointCleanPoint2 = ECAlgorithms.cleanPoint(parameters.getCurve(), sM2KeyExchangePublicParameters.getEphemeralPublicKey().getQ());
        BigInteger bigIntegerReduce = reduce(this.ephemeralPubPoint.getAffineXCoord().toBigInteger());
        BigInteger bigIntegerReduce2 = reduce(eCPointCleanPoint2.getAffineXCoord().toBigInteger());
        BigInteger bigIntegerMod = this.ecParams.getH().multiply(this.staticKey.getD().add(bigIntegerReduce.multiply(this.ephemeralKey.getD()))).mod(this.ecParams.getN());
        return ECAlgorithms.sumOfTwoMultiplies(eCPointCleanPoint, bigIntegerMod, eCPointCleanPoint2, bigIntegerMod.multiply(bigIntegerReduce2).mod(this.ecParams.getN())).normalize();
    }

    private byte[] digestDoFinal() {
        byte[] bArr = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr, 0);
        return bArr;
    }

    private byte[] getZ(Digest digest, byte[] bArr, ECPoint eCPoint) {
        addUserID(digest, bArr);
        addFieldElement(digest, this.ecParams.getCurve().getA());
        addFieldElement(digest, this.ecParams.getCurve().getB());
        addFieldElement(digest, this.ecParams.getG().getAffineXCoord());
        addFieldElement(digest, this.ecParams.getG().getAffineYCoord());
        addFieldElement(digest, eCPoint.getAffineXCoord());
        addFieldElement(digest, eCPoint.getAffineYCoord());
        return digestDoFinal();
    }

    private byte[] kdf(ECPoint eCPoint, byte[] bArr, byte[] bArr2, int i) {
        Memoable memoable;
        Memoable memoableCopy;
        int digestSize = this.digest.getDigestSize();
        byte[] bArr3 = new byte[Math.max(4, digestSize)];
        int i2 = (i + 7) / 8;
        byte[] bArr4 = new byte[i2];
        Digest digest = this.digest;
        if (digest instanceof Memoable) {
            addFieldElement(digest, eCPoint.getAffineXCoord());
            addFieldElement(this.digest, eCPoint.getAffineYCoord());
            this.digest.update(bArr, 0, bArr.length);
            this.digest.update(bArr2, 0, bArr2.length);
            memoable = (Memoable) this.digest;
            memoableCopy = memoable.copy();
        } else {
            memoable = null;
            memoableCopy = null;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            if (memoable != null) {
                memoable.reset(memoableCopy);
            } else {
                addFieldElement(this.digest, eCPoint.getAffineXCoord());
                addFieldElement(this.digest, eCPoint.getAffineYCoord());
                this.digest.update(bArr, 0, bArr.length);
                this.digest.update(bArr2, 0, bArr2.length);
            }
            i4++;
            Pack.intToBigEndian(i4, bArr3, 0);
            this.digest.update(bArr3, 0, 4);
            this.digest.doFinal(bArr3, 0);
            int iMin = Math.min(digestSize, i2 - i3);
            System.arraycopy(bArr3, 0, bArr4, i3, iMin);
            i3 += iMin;
        }
        return bArr4;
    }

    private BigInteger reduce(BigInteger bigInteger) {
        return bigInteger.and(BigInteger.valueOf(1L).shiftLeft(this.w).subtract(BigInteger.valueOf(1L))).setBit(this.w);
    }

    public byte[] calculateKey(int i, CipherParameters cipherParameters) {
        SM2KeyExchangePublicParameters sM2KeyExchangePublicParameters;
        byte[] id;
        if (cipherParameters instanceof ParametersWithID) {
            ParametersWithID parametersWithID = (ParametersWithID) cipherParameters;
            sM2KeyExchangePublicParameters = (SM2KeyExchangePublicParameters) parametersWithID.getParameters();
            id = parametersWithID.getID();
        } else {
            sM2KeyExchangePublicParameters = (SM2KeyExchangePublicParameters) cipherParameters;
            id = new byte[0];
        }
        byte[] z = getZ(this.digest, this.userID, this.staticPubPoint);
        byte[] z2 = getZ(this.digest, id, sM2KeyExchangePublicParameters.getStaticPublicKey().getQ());
        ECPoint eCPointCalculateU = calculateU(sM2KeyExchangePublicParameters);
        return this.initiator ? kdf(eCPointCalculateU, z, z2, i) : kdf(eCPointCalculateU, z2, z, i);
    }

    public byte[][] calculateKeyWithConfirmation(int i, byte[] bArr, CipherParameters cipherParameters) {
        SM2KeyExchangePublicParameters sM2KeyExchangePublicParameters;
        byte[] id;
        if (cipherParameters instanceof ParametersWithID) {
            ParametersWithID parametersWithID = (ParametersWithID) cipherParameters;
            sM2KeyExchangePublicParameters = (SM2KeyExchangePublicParameters) parametersWithID.getParameters();
            id = parametersWithID.getID();
        } else {
            sM2KeyExchangePublicParameters = (SM2KeyExchangePublicParameters) cipherParameters;
            id = new byte[0];
        }
        if (this.initiator && bArr == null) {
            throw new IllegalArgumentException("if initiating, confirmationTag must be set");
        }
        byte[] z = getZ(this.digest, this.userID, this.staticPubPoint);
        byte[] z2 = getZ(this.digest, id, sM2KeyExchangePublicParameters.getStaticPublicKey().getQ());
        ECPoint eCPointCalculateU = calculateU(sM2KeyExchangePublicParameters);
        if (!this.initiator) {
            byte[] bArrKdf = kdf(eCPointCalculateU, z2, z, i);
            byte[] bArrCalculateInnerHash = calculateInnerHash(this.digest, eCPointCalculateU, z2, z, sM2KeyExchangePublicParameters.getEphemeralPublicKey().getQ(), this.ephemeralPubPoint);
            return new byte[][]{bArrKdf, S1(this.digest, eCPointCalculateU, bArrCalculateInnerHash), S2(this.digest, eCPointCalculateU, bArrCalculateInnerHash)};
        }
        byte[] bArrKdf2 = kdf(eCPointCalculateU, z, z2, i);
        byte[] bArrCalculateInnerHash2 = calculateInnerHash(this.digest, eCPointCalculateU, z, z2, this.ephemeralPubPoint, sM2KeyExchangePublicParameters.getEphemeralPublicKey().getQ());
        if (Arrays.constantTimeAreEqual(S1(this.digest, eCPointCalculateU, bArrCalculateInnerHash2), bArr)) {
            return new byte[][]{bArrKdf2, S2(this.digest, eCPointCalculateU, bArrCalculateInnerHash2)};
        }
        throw new IllegalStateException("confirmation tag mismatch");
    }

    public void init(CipherParameters cipherParameters) {
        SM2KeyExchangePrivateParameters sM2KeyExchangePrivateParameters;
        if (cipherParameters instanceof ParametersWithID) {
            ParametersWithID parametersWithID = (ParametersWithID) cipherParameters;
            sM2KeyExchangePrivateParameters = (SM2KeyExchangePrivateParameters) parametersWithID.getParameters();
            this.userID = parametersWithID.getID();
        } else {
            sM2KeyExchangePrivateParameters = (SM2KeyExchangePrivateParameters) cipherParameters;
            this.userID = new byte[0];
        }
        this.initiator = sM2KeyExchangePrivateParameters.isInitiator();
        this.staticKey = sM2KeyExchangePrivateParameters.getStaticPrivateKey();
        this.ephemeralKey = sM2KeyExchangePrivateParameters.getEphemeralPrivateKey();
        this.ecParams = this.staticKey.getParameters();
        this.staticPubPoint = sM2KeyExchangePrivateParameters.getStaticPublicPoint();
        this.ephemeralPubPoint = sM2KeyExchangePrivateParameters.getEphemeralPublicPoint();
        this.w = (this.ecParams.getCurve().getFieldSize() / 2) - 1;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties("SM2KE", this.staticKey));
    }
}
