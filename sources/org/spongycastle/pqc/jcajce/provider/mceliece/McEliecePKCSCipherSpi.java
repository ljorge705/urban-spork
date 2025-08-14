package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.crypto.mceliece.McElieceCipher;
import org.spongycastle.pqc.crypto.mceliece.McElieceKeyParameters;
import org.spongycastle.pqc.jcajce.provider.util.AsymmetricBlockCipher;

/* loaded from: classes7.dex */
public class McEliecePKCSCipherSpi extends AsymmetricBlockCipher implements PKCSObjectIdentifiers, X509ObjectIdentifiers {
    private McElieceCipher cipher;

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public String getName() {
        return "McEliecePKCS";
    }

    public McEliecePKCSCipherSpi(McElieceCipher mcElieceCipher) {
        this.cipher = mcElieceCipher;
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.AsymmetricBlockCipher
    protected void initCipherEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        this.cipher.init(true, new ParametersWithRandom(McElieceKeysToParams.generatePublicKeyParameter((PublicKey) key), secureRandom));
        this.maxPlainTextSize = this.cipher.maxPlainTextSize;
        this.cipherTextSize = this.cipher.cipherTextSize;
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.AsymmetricBlockCipher
    protected void initCipherDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException, InvalidAlgorithmParameterException {
        this.cipher.init(false, McElieceKeysToParams.generatePrivateKeyParameter((PrivateKey) key));
        this.maxPlainTextSize = this.cipher.maxPlainTextSize;
        this.cipherTextSize = this.cipher.cipherTextSize;
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.AsymmetricBlockCipher
    protected byte[] messageEncrypt(byte[] bArr) throws BadPaddingException, IllegalBlockSizeException {
        try {
            return this.cipher.messageEncrypt(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.AsymmetricBlockCipher
    protected byte[] messageDecrypt(byte[] bArr) throws BadPaddingException, IllegalBlockSizeException {
        try {
            return this.cipher.messageDecrypt(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public int getKeySize(Key key) throws InvalidKeyException {
        McElieceKeyParameters mcElieceKeyParameters;
        if (key instanceof PublicKey) {
            mcElieceKeyParameters = (McElieceKeyParameters) McElieceKeysToParams.generatePublicKeyParameter((PublicKey) key);
        } else {
            mcElieceKeyParameters = (McElieceKeyParameters) McElieceKeysToParams.generatePrivateKeyParameter((PrivateKey) key);
        }
        return this.cipher.getKeySize(mcElieceKeyParameters);
    }

    public static class McEliecePKCS extends McEliecePKCSCipherSpi {
        public McEliecePKCS() {
            super(new McElieceCipher());
        }
    }
}
