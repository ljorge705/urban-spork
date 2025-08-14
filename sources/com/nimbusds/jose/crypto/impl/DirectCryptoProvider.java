package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.util.ByteUtils;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.crypto.SecretKey;

/* loaded from: classes2.dex */
public abstract class DirectCryptoProvider extends BaseJWEProvider {
    public static final Set<JWEAlgorithm> SUPPORTED_ALGORITHMS;
    public static final Set<EncryptionMethod> SUPPORTED_ENCRYPTION_METHODS = ContentCryptoProvider.SUPPORTED_ENCRYPTION_METHODS;

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(JWEAlgorithm.DIR);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
    }

    private static Set<EncryptionMethod> getCompatibleEncryptionMethods(int i) throws KeyLengthException {
        if (i == 0) {
            return EncryptionMethod.Family.AES_GCM;
        }
        Set<EncryptionMethod> set = ContentCryptoProvider.COMPATIBLE_ENCRYPTION_METHODS.get(Integer.valueOf(i));
        if (set != null) {
            return set;
        }
        throw new KeyLengthException("The Content Encryption Key length must be 128 bits (16 bytes), 192 bits (24 bytes), 256 bits (32 bytes), 384 bits (48 bytes) or 512 bites (64 bytes)");
    }

    protected DirectCryptoProvider(SecretKey secretKey) throws KeyLengthException {
        super(SUPPORTED_ALGORITHMS, getCompatibleEncryptionMethods(ByteUtils.bitLength(secretKey.getEncoded())), secretKey);
    }

    public SecretKey getKey() {
        try {
            return getCEK(null);
        } catch (Exception unused) {
            return null;
        }
    }
}
