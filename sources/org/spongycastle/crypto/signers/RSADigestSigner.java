package org.spongycastle.crypto.signers;

import java.io.IOException;
import java.util.Hashtable;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSKeyParameters;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DigestInfo;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.encodings.PKCS1Encoding;
import org.spongycastle.crypto.engines.RSABlindedEngine;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.Arrays;

/* loaded from: classes7.dex */
public class RSADigestSigner implements Signer {
    private static final Hashtable oidMap;
    private final AlgorithmIdentifier algId;
    private final Digest digest;
    private boolean forSigning;
    private final AsymmetricBlockCipher rsaEngine;

    static {
        Hashtable hashtable = new Hashtable();
        oidMap = hashtable;
        hashtable.put("RIPEMD128", TeleTrusTObjectIdentifiers.ripemd128);
        hashtable.put("RIPEMD160", TeleTrusTObjectIdentifiers.ripemd160);
        hashtable.put("RIPEMD256", TeleTrusTObjectIdentifiers.ripemd256);
        hashtable.put("SHA-1", X509ObjectIdentifiers.id_SHA1);
        hashtable.put("SHA-224", NISTObjectIdentifiers.id_sha224);
        hashtable.put("SHA-256", NISTObjectIdentifiers.id_sha256);
        hashtable.put("SHA-384", NISTObjectIdentifiers.id_sha384);
        hashtable.put("SHA-512", NISTObjectIdentifiers.id_sha512);
        hashtable.put("SHA-512/224", NISTObjectIdentifiers.id_sha512_224);
        hashtable.put(SPHINCSKeyParameters.SHA512_256, NISTObjectIdentifiers.id_sha512_256);
        hashtable.put("SHA3-224", NISTObjectIdentifiers.id_sha3_224);
        hashtable.put("SHA3-256", NISTObjectIdentifiers.id_sha3_256);
        hashtable.put("SHA3-384", NISTObjectIdentifiers.id_sha3_384);
        hashtable.put("SHA3-512", NISTObjectIdentifiers.id_sha3_512);
        hashtable.put("MD2", PKCSObjectIdentifiers.md2);
        hashtable.put("MD4", PKCSObjectIdentifiers.md4);
        hashtable.put("MD5", PKCSObjectIdentifiers.md5);
    }

    public RSADigestSigner(Digest digest) {
        this(digest, (ASN1ObjectIdentifier) oidMap.get(digest.getAlgorithmName()));
    }

    public RSADigestSigner(Digest digest, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.rsaEngine = new PKCS1Encoding(new RSABlindedEngine());
        this.digest = digest;
        this.algId = new AlgorithmIdentifier(aSN1ObjectIdentifier, DERNull.INSTANCE);
    }

    public String getAlgorithmName() {
        return this.digest.getAlgorithmName() + "withRSA";
    }

    @Override // org.spongycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        AsymmetricKeyParameter asymmetricKeyParameter;
        this.forSigning = z;
        if (cipherParameters instanceof ParametersWithRandom) {
            asymmetricKeyParameter = (AsymmetricKeyParameter) ((ParametersWithRandom) cipherParameters).getParameters();
        } else {
            asymmetricKeyParameter = (AsymmetricKeyParameter) cipherParameters;
        }
        if (z && !asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("signing requires private key");
        }
        if (!z && asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("verification requires public key");
        }
        reset();
        this.rsaEngine.init(z, cipherParameters);
    }

    @Override // org.spongycastle.crypto.Signer
    public void update(byte b) {
        this.digest.update(b);
    }

    @Override // org.spongycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        this.digest.update(bArr, i, i2);
    }

    @Override // org.spongycastle.crypto.Signer
    public byte[] generateSignature() throws DataLengthException, CryptoException {
        if (!this.forSigning) {
            throw new IllegalStateException("RSADigestSigner not initialised for signature generation.");
        }
        byte[] bArr = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr, 0);
        try {
            byte[] bArrDerEncode = derEncode(bArr);
            return this.rsaEngine.processBlock(bArrDerEncode, 0, bArrDerEncode.length);
        } catch (IOException e) {
            throw new CryptoException("unable to encode signature: " + e.getMessage(), e);
        }
    }

    @Override // org.spongycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        byte[] bArrProcessBlock;
        byte[] bArrDerEncode;
        if (this.forSigning) {
            throw new IllegalStateException("RSADigestSigner not initialised for verification");
        }
        int digestSize = this.digest.getDigestSize();
        byte[] bArr2 = new byte[digestSize];
        this.digest.doFinal(bArr2, 0);
        try {
            bArrProcessBlock = this.rsaEngine.processBlock(bArr, 0, bArr.length);
            bArrDerEncode = derEncode(bArr2);
        } catch (Exception unused) {
        }
        if (bArrProcessBlock.length == bArrDerEncode.length) {
            return Arrays.constantTimeAreEqual(bArrProcessBlock, bArrDerEncode);
        }
        if (bArrProcessBlock.length == bArrDerEncode.length - 2) {
            int length = (bArrProcessBlock.length - digestSize) - 2;
            int length2 = (bArrDerEncode.length - digestSize) - 2;
            bArrDerEncode[1] = (byte) (bArrDerEncode[1] - 2);
            bArrDerEncode[3] = (byte) (bArrDerEncode[3] - 2);
            int i = 0;
            for (int i2 = 0; i2 < digestSize; i2++) {
                i |= bArrProcessBlock[length + i2] ^ bArrDerEncode[length2 + i2];
            }
            for (int i3 = 0; i3 < length; i3++) {
                i |= bArrProcessBlock[i3] ^ bArrDerEncode[i3];
            }
            return i == 0;
        }
        Arrays.constantTimeAreEqual(bArrDerEncode, bArrDerEncode);
        return false;
    }

    @Override // org.spongycastle.crypto.Signer
    public void reset() {
        this.digest.reset();
    }

    private byte[] derEncode(byte[] bArr) throws IOException {
        return new DigestInfo(this.algId, bArr).getEncoded("DER");
    }
}
