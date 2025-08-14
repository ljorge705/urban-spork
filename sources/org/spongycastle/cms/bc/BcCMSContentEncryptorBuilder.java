package org.spongycastle.cms.bc;

import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.cms.CMSAlgorithm;
import org.spongycastle.cms.CMSException;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.io.CipherOutputStream;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.operator.GenericKey;
import org.spongycastle.operator.OutputEncryptor;
import org.spongycastle.util.Integers;

/* loaded from: classes4.dex */
public class BcCMSContentEncryptorBuilder {
    private static Map keySizes;
    private final ASN1ObjectIdentifier encryptionOID;
    private EnvelopedDataHelper helper;
    private final int keySize;
    private SecureRandom random;

    public BcCMSContentEncryptorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    static {
        HashMap map = new HashMap();
        keySizes = map;
        map.put(CMSAlgorithm.AES128_CBC, Integers.valueOf(128));
        keySizes.put(CMSAlgorithm.AES192_CBC, Integers.valueOf(192));
        keySizes.put(CMSAlgorithm.AES256_CBC, Integers.valueOf(256));
        keySizes.put(CMSAlgorithm.CAMELLIA128_CBC, Integers.valueOf(128));
        keySizes.put(CMSAlgorithm.CAMELLIA192_CBC, Integers.valueOf(192));
        keySizes.put(CMSAlgorithm.CAMELLIA256_CBC, Integers.valueOf(256));
    }

    private static int getKeySize(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Integer num = (Integer) keySizes.get(aSN1ObjectIdentifier);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public BcCMSContentEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this(aSN1ObjectIdentifier, getKeySize(aSN1ObjectIdentifier));
    }

    public BcCMSContentEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i) {
        this.helper = new EnvelopedDataHelper();
        this.encryptionOID = aSN1ObjectIdentifier;
        this.keySize = i;
    }

    public OutputEncryptor build() throws CMSException {
        return new CMSOutputEncryptor(this.encryptionOID, this.keySize, this.random);
    }

    private class CMSOutputEncryptor implements OutputEncryptor {
        private AlgorithmIdentifier algorithmIdentifier;
        private Object cipher;
        private KeyParameter encKey;

        @Override // org.spongycastle.operator.OutputEncryptor
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.algorithmIdentifier;
        }

        CMSOutputEncryptor(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i, SecureRandom secureRandom) throws CMSException {
            secureRandom = secureRandom == null ? new SecureRandom() : secureRandom;
            this.encKey = new KeyParameter(BcCMSContentEncryptorBuilder.this.helper.createKeyGenerator(aSN1ObjectIdentifier, secureRandom).generateKey());
            this.algorithmIdentifier = BcCMSContentEncryptorBuilder.this.helper.generateAlgorithmIdentifier(aSN1ObjectIdentifier, this.encKey, secureRandom);
            EnvelopedDataHelper unused = BcCMSContentEncryptorBuilder.this.helper;
            this.cipher = EnvelopedDataHelper.createContentCipher(true, this.encKey, this.algorithmIdentifier);
        }

        @Override // org.spongycastle.operator.OutputEncryptor
        public OutputStream getOutputStream(OutputStream outputStream) {
            if (this.cipher instanceof BufferedBlockCipher) {
                return new CipherOutputStream(outputStream, (BufferedBlockCipher) this.cipher);
            }
            return new CipherOutputStream(outputStream, (StreamCipher) this.cipher);
        }

        @Override // org.spongycastle.operator.OutputEncryptor
        public GenericKey getKey() {
            return new GenericKey(this.algorithmIdentifier, this.encKey.getKey());
        }
    }
}
