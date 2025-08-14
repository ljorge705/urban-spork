package org.spongycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.prng.DigestRandomGenerator;
import org.spongycastle.pqc.crypto.MessageEncryptor;
import org.spongycastle.pqc.math.linearalgebra.ByteUtils;
import org.spongycastle.pqc.math.linearalgebra.GF2Vector;

/* loaded from: classes7.dex */
public class McEliecePointchevalCipher implements MessageEncryptor {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2.2";
    private boolean forEncryption;
    private int k;
    McElieceCCA2KeyParameters key;
    private Digest messDigest;

    /* renamed from: n, reason: collision with root package name */
    private int f413n;
    private SecureRandom sr;
    private int t;

    protected int decryptOutputSize(int i) {
        return 0;
    }

    protected int encryptOutputSize(int i) {
        return 0;
    }

    @Override // org.spongycastle.pqc.crypto.MessageEncryptor
    public void init(boolean z, CipherParameters cipherParameters) {
        this.forEncryption = z;
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.sr = parametersWithRandom.getRandom();
                McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters = (McElieceCCA2PublicKeyParameters) parametersWithRandom.getParameters();
                this.key = mcElieceCCA2PublicKeyParameters;
                initCipherEncrypt(mcElieceCCA2PublicKeyParameters);
                return;
            }
            this.sr = new SecureRandom();
            McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters2 = (McElieceCCA2PublicKeyParameters) cipherParameters;
            this.key = mcElieceCCA2PublicKeyParameters2;
            initCipherEncrypt(mcElieceCCA2PublicKeyParameters2);
            return;
        }
        McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters = (McElieceCCA2PrivateKeyParameters) cipherParameters;
        this.key = mcElieceCCA2PrivateKeyParameters;
        initCipherDecrypt(mcElieceCCA2PrivateKeyParameters);
    }

    public int getKeySize(McElieceCCA2KeyParameters mcElieceCCA2KeyParameters) throws IllegalArgumentException {
        if (mcElieceCCA2KeyParameters instanceof McElieceCCA2PublicKeyParameters) {
            return ((McElieceCCA2PublicKeyParameters) mcElieceCCA2KeyParameters).getN();
        }
        if (mcElieceCCA2KeyParameters instanceof McElieceCCA2PrivateKeyParameters) {
            return ((McElieceCCA2PrivateKeyParameters) mcElieceCCA2KeyParameters).getN();
        }
        throw new IllegalArgumentException("unsupported type");
    }

    private void initCipherEncrypt(McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters) {
        SecureRandom secureRandom = this.sr;
        if (secureRandom == null) {
            secureRandom = new SecureRandom();
        }
        this.sr = secureRandom;
        this.messDigest = Utils.getDigest(mcElieceCCA2PublicKeyParameters.getDigest());
        this.f413n = mcElieceCCA2PublicKeyParameters.getN();
        this.k = mcElieceCCA2PublicKeyParameters.getK();
        this.t = mcElieceCCA2PublicKeyParameters.getT();
    }

    private void initCipherDecrypt(McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters) {
        this.messDigest = Utils.getDigest(mcElieceCCA2PrivateKeyParameters.getDigest());
        this.f413n = mcElieceCCA2PrivateKeyParameters.getN();
        this.k = mcElieceCCA2PrivateKeyParameters.getK();
        this.t = mcElieceCCA2PrivateKeyParameters.getT();
    }

    @Override // org.spongycastle.pqc.crypto.MessageEncryptor
    public byte[] messageEncrypt(byte[] bArr) {
        if (!this.forEncryption) {
            throw new IllegalStateException("cipher initialised for decryption");
        }
        int i = this.k >> 3;
        byte[] bArr2 = new byte[i];
        this.sr.nextBytes(bArr2);
        GF2Vector gF2Vector = new GF2Vector(this.k, this.sr);
        byte[] encoded = gF2Vector.getEncoded();
        byte[] bArrConcatenate = ByteUtils.concatenate(bArr, bArr2);
        this.messDigest.update(bArrConcatenate, 0, bArrConcatenate.length);
        byte[] bArr3 = new byte[this.messDigest.getDigestSize()];
        this.messDigest.doFinal(bArr3, 0);
        byte[] encoded2 = McElieceCCA2Primitives.encryptionPrimitive((McElieceCCA2PublicKeyParameters) this.key, gF2Vector, Conversions.encode(this.f413n, this.t, bArr3)).getEncoded();
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.addSeedMaterial(encoded);
        byte[] bArr4 = new byte[bArr.length + i];
        digestRandomGenerator.nextBytes(bArr4);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr4[i2] = (byte) (bArr4[i2] ^ bArr[i2]);
        }
        for (int i3 = 0; i3 < i; i3++) {
            int length = bArr.length + i3;
            bArr4[length] = (byte) (bArr4[length] ^ bArr2[i3]);
        }
        return ByteUtils.concatenate(encoded2, bArr4);
    }

    @Override // org.spongycastle.pqc.crypto.MessageEncryptor
    public byte[] messageDecrypt(byte[] bArr) throws InvalidCipherTextException, ArrayIndexOutOfBoundsException {
        if (this.forEncryption) {
            throw new IllegalStateException("cipher initialised for decryption");
        }
        int i = (this.f413n + 7) >> 3;
        int length = bArr.length - i;
        byte[][] bArrSplit = ByteUtils.split(bArr, i);
        byte[] bArr2 = bArrSplit[0];
        byte[] bArr3 = bArrSplit[1];
        GF2Vector[] gF2VectorArrDecryptionPrimitive = McElieceCCA2Primitives.decryptionPrimitive((McElieceCCA2PrivateKeyParameters) this.key, GF2Vector.OS2VP(this.f413n, bArr2));
        byte[] encoded = gF2VectorArrDecryptionPrimitive[0].getEncoded();
        GF2Vector gF2Vector = gF2VectorArrDecryptionPrimitive[1];
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.addSeedMaterial(encoded);
        byte[] bArr4 = new byte[length];
        digestRandomGenerator.nextBytes(bArr4);
        for (int i2 = 0; i2 < length; i2++) {
            bArr4[i2] = (byte) (bArr4[i2] ^ bArr3[i2]);
        }
        this.messDigest.update(bArr4, 0, length);
        byte[] bArr5 = new byte[this.messDigest.getDigestSize()];
        this.messDigest.doFinal(bArr5, 0);
        if (!Conversions.encode(this.f413n, this.t, bArr5).equals(gF2Vector)) {
            throw new InvalidCipherTextException("Bad Padding: Invalid ciphertext.");
        }
        return ByteUtils.split(bArr4, length - (this.k >> 3))[0];
    }
}
