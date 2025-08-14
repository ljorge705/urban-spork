package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.jwk.Curve;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.crypto.SecretKey;

/* loaded from: classes2.dex */
public abstract class MultiCryptoProvider extends BaseJWEProvider {
    public static final Map<Integer, Set<JWEAlgorithm>> COMPATIBLE_ALGORITHMS;
    public static final Set<JWEAlgorithm> SUPPORTED_ALGORITHMS;
    public static final Set<Curve> SUPPORTED_ELLIPTIC_CURVES;
    public static final Set<EncryptionMethod> SUPPORTED_ENCRYPTION_METHODS = ContentCryptoProvider.SUPPORTED_ENCRYPTION_METHODS;

    public Set<Curve> supportedEllipticCurves() {
        return SUPPORTED_ELLIPTIC_CURVES;
    }

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(null);
        linkedHashSet.add(JWEAlgorithm.A128KW);
        linkedHashSet.add(JWEAlgorithm.A192KW);
        linkedHashSet.add(JWEAlgorithm.A256KW);
        linkedHashSet.add(JWEAlgorithm.A128GCMKW);
        linkedHashSet.add(JWEAlgorithm.A192GCMKW);
        linkedHashSet.add(JWEAlgorithm.A256GCMKW);
        linkedHashSet.add(JWEAlgorithm.DIR);
        linkedHashSet.add(JWEAlgorithm.ECDH_ES_A128KW);
        linkedHashSet.add(JWEAlgorithm.ECDH_ES_A192KW);
        linkedHashSet.add(JWEAlgorithm.ECDH_ES_A256KW);
        linkedHashSet.add(JWEAlgorithm.RSA1_5);
        linkedHashSet.add(JWEAlgorithm.RSA_OAEP);
        linkedHashSet.add(JWEAlgorithm.RSA_OAEP_256);
        linkedHashSet.add(JWEAlgorithm.RSA_OAEP_384);
        linkedHashSet.add(JWEAlgorithm.RSA_OAEP_512);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
        HashMap map = new HashMap();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        hashSet.add(JWEAlgorithm.A128GCMKW);
        hashSet.add(JWEAlgorithm.A128KW);
        hashSet2.add(JWEAlgorithm.A192GCMKW);
        hashSet2.add(JWEAlgorithm.A192KW);
        hashSet3.add(JWEAlgorithm.A256GCMKW);
        hashSet3.add(JWEAlgorithm.A256KW);
        map.put(128, Collections.unmodifiableSet(hashSet));
        map.put(192, Collections.unmodifiableSet(hashSet2));
        map.put(256, Collections.unmodifiableSet(hashSet3));
        COMPATIBLE_ALGORITHMS = Collections.unmodifiableMap(map);
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        linkedHashSet2.add(Curve.P_256);
        linkedHashSet2.add(Curve.P_384);
        linkedHashSet2.add(Curve.P_521);
        linkedHashSet2.add(Curve.X25519);
        SUPPORTED_ELLIPTIC_CURVES = Collections.unmodifiableSet(linkedHashSet2);
    }

    protected MultiCryptoProvider(SecretKey secretKey) throws KeyLengthException {
        super(SUPPORTED_ALGORITHMS, ContentCryptoProvider.SUPPORTED_ENCRYPTION_METHODS, secretKey);
    }
}
