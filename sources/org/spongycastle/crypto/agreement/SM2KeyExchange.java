package org.spongycastle.crypto.agreement;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SM3Digest;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ParametersWithID;
import org.spongycastle.crypto.params.SM2KeyExchangePrivateParameters;
import org.spongycastle.crypto.params.SM2KeyExchangePublicParameters;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

/* loaded from: classes3.dex */
public class SM2KeyExchange {
    private int curveLength;
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
        this.curveLength = (this.ecParams.getCurve().getFieldSize() + 7) / 8;
        this.w = (this.ecParams.getCurve().getFieldSize() / 2) - 1;
    }

    public int getFieldSize() {
        return (this.staticKey.getParameters().getCurve().getFieldSize() + 7) / 8;
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
        if (this.initiator) {
            return kdf(eCPointCalculateU, z, z2, i);
        }
        return kdf(eCPointCalculateU, z2, z, i);
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
        if (this.initiator) {
            byte[] bArrKdf = kdf(eCPointCalculateU, z, z2, i);
            byte[] bArrCalculateInnerHash = calculateInnerHash(this.digest, eCPointCalculateU, z, z2, this.ephemeralPubPoint, sM2KeyExchangePublicParameters.getEphemeralPublicKey().getQ());
            if (Arrays.constantTimeAreEqual(S1(this.digest, eCPointCalculateU, bArrCalculateInnerHash), bArr)) {
                return new byte[][]{bArrKdf, S2(this.digest, eCPointCalculateU, bArrCalculateInnerHash)};
            }
            throw new IllegalStateException("confirmation tag mismatch");
        }
        byte[] bArrKdf2 = kdf(eCPointCalculateU, z2, z, i);
        byte[] bArrCalculateInnerHash2 = calculateInnerHash(this.digest, eCPointCalculateU, z2, z, sM2KeyExchangePublicParameters.getEphemeralPublicKey().getQ(), this.ephemeralPubPoint);
        return new byte[][]{bArrKdf2, S1(this.digest, eCPointCalculateU, bArrCalculateInnerHash2), S2(this.digest, eCPointCalculateU, bArrCalculateInnerHash2)};
    }

    private ECPoint calculateU(SM2KeyExchangePublicParameters sM2KeyExchangePublicParameters) {
        BigInteger bigIntegerMod = this.staticKey.getD().add(reduce(this.ephemeralPubPoint.getAffineXCoord().toBigInteger()).multiply(this.ephemeralKey.getD())).mod(this.ecParams.getN());
        return sM2KeyExchangePublicParameters.getStaticPublicKey().getQ().add(sM2KeyExchangePublicParameters.getEphemeralPublicKey().getQ().multiply(reduce(sM2KeyExchangePublicParameters.getEphemeralPublicKey().getQ().getAffineXCoord().toBigInteger())).normalize()).normalize().multiply(this.ecParams.getH().multiply(bigIntegerMod)).normalize();
    }

    private byte[] kdf(ECPoint eCPoint, byte[] bArr, byte[] bArr2, int i) {
        int digestSize = this.digest.getDigestSize() * 8;
        int digestSize2 = this.digest.getDigestSize();
        byte[] bArr3 = new byte[digestSize2];
        int i2 = (i + 7) / 8;
        byte[] bArr4 = new byte[i2];
        int i3 = 1;
        int i4 = 1;
        int i5 = 0;
        while (i3 <= ((i + digestSize) - 1) / digestSize) {
            addFieldElement(this.digest, eCPoint.getAffineXCoord());
            addFieldElement(this.digest, eCPoint.getAffineYCoord());
            this.digest.update(bArr, 0, bArr.length);
            this.digest.update(bArr2, 0, bArr2.length);
            this.digest.update((byte) (i4 >> 24));
            this.digest.update((byte) (i4 >> 16));
            this.digest.update((byte) (i4 >> 8));
            this.digest.update((byte) i4);
            this.digest.doFinal(bArr3, 0);
            int i6 = i5 + digestSize2;
            if (i6 < i2) {
                System.arraycopy(bArr3, 0, bArr4, i5, digestSize2);
            } else {
                System.arraycopy(bArr3, 0, bArr4, i5, i2 - i5);
            }
            i4++;
            i3++;
            i5 = i6;
        }
        return bArr4;
    }

    private BigInteger reduce(BigInteger bigInteger) {
        return bigInteger.and(BigInteger.valueOf(1L).shiftLeft(this.w).subtract(BigInteger.valueOf(1L))).setBit(this.w);
    }

    private byte[] S1(Digest digest, ECPoint eCPoint, byte[] bArr) {
        byte[] bArr2 = new byte[digest.getDigestSize()];
        digest.update((byte) 2);
        addFieldElement(digest, eCPoint.getAffineYCoord());
        digest.update(bArr, 0, bArr.length);
        digest.doFinal(bArr2, 0);
        return bArr2;
    }

    private byte[] calculateInnerHash(Digest digest, ECPoint eCPoint, byte[] bArr, byte[] bArr2, ECPoint eCPoint2, ECPoint eCPoint3) {
        addFieldElement(digest, eCPoint.getAffineXCoord());
        digest.update(bArr, 0, bArr.length);
        digest.update(bArr2, 0, bArr2.length);
        addFieldElement(digest, eCPoint2.getAffineXCoord());
        addFieldElement(digest, eCPoint2.getAffineYCoord());
        addFieldElement(digest, eCPoint3.getAffineXCoord());
        addFieldElement(digest, eCPoint3.getAffineYCoord());
        byte[] bArr3 = new byte[digest.getDigestSize()];
        digest.doFinal(bArr3, 0);
        return bArr3;
    }

    private byte[] S2(Digest digest, ECPoint eCPoint, byte[] bArr) {
        byte[] bArr2 = new byte[digest.getDigestSize()];
        digest.update((byte) 3);
        addFieldElement(digest, eCPoint.getAffineYCoord());
        digest.update(bArr, 0, bArr.length);
        digest.doFinal(bArr2, 0);
        return bArr2;
    }

    private byte[] getZ(Digest digest, byte[] bArr, ECPoint eCPoint) {
        addUserID(digest, bArr);
        addFieldElement(digest, this.ecParams.getCurve().getA());
        addFieldElement(digest, this.ecParams.getCurve().getB());
        addFieldElement(digest, this.ecParams.getG().getAffineXCoord());
        addFieldElement(digest, this.ecParams.getG().getAffineYCoord());
        addFieldElement(digest, eCPoint.getAffineXCoord());
        addFieldElement(digest, eCPoint.getAffineYCoord());
        byte[] bArr2 = new byte[digest.getDigestSize()];
        digest.doFinal(bArr2, 0);
        return bArr2;
    }

    private void addUserID(Digest digest, byte[] bArr) {
        int length = bArr.length * 8;
        digest.update((byte) ((length >> 8) & 255));
        digest.update((byte) (length & 255));
        digest.update(bArr, 0, bArr.length);
    }

    private void addFieldElement(Digest digest, ECFieldElement eCFieldElement) {
        byte[] bArrAsUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.curveLength, eCFieldElement.toBigInteger());
        digest.update(bArrAsUnsignedByteArray, 0, bArrAsUnsignedByteArray.length);
    }
}
