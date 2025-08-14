package org.spongycastle.operator.bc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.digests.GOST3411Digest;
import org.spongycastle.crypto.digests.MD2Digest;
import org.spongycastle.crypto.digests.MD4Digest;
import org.spongycastle.crypto.digests.MD5Digest;
import org.spongycastle.crypto.digests.RIPEMD128Digest;
import org.spongycastle.crypto.digests.RIPEMD160Digest;
import org.spongycastle.crypto.digests.RIPEMD256Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.digests.SHA224Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA384Digest;
import org.spongycastle.crypto.digests.SHA3Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.operator.OperatorCreationException;

/* loaded from: classes7.dex */
public class BcDefaultDigestProvider implements BcDigestProvider {
    private static final Map lookup = createTable();
    public static final BcDigestProvider INSTANCE = new BcDefaultDigestProvider();

    private static Map createTable() {
        HashMap map = new HashMap();
        map.put(OIWObjectIdentifiers.idSHA1, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.1
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA1Digest();
            }
        });
        map.put(NISTObjectIdentifiers.id_sha224, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.2
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA224Digest();
            }
        });
        map.put(NISTObjectIdentifiers.id_sha256, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.3
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA256Digest();
            }
        });
        map.put(NISTObjectIdentifiers.id_sha384, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.4
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA384Digest();
            }
        });
        map.put(NISTObjectIdentifiers.id_sha512, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.5
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA512Digest();
            }
        });
        map.put(NISTObjectIdentifiers.id_sha3_224, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.6
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA3Digest(224);
            }
        });
        map.put(NISTObjectIdentifiers.id_sha3_256, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.7
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA3Digest(256);
            }
        });
        map.put(NISTObjectIdentifiers.id_sha3_384, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.8
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA3Digest(384);
            }
        });
        map.put(NISTObjectIdentifiers.id_sha3_512, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.9
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new SHA3Digest(512);
            }
        });
        map.put(PKCSObjectIdentifiers.md5, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.10
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new MD5Digest();
            }
        });
        map.put(PKCSObjectIdentifiers.md4, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.11
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new MD4Digest();
            }
        });
        map.put(PKCSObjectIdentifiers.md2, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.12
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new MD2Digest();
            }
        });
        map.put(CryptoProObjectIdentifiers.gostR3411, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.13
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new GOST3411Digest();
            }
        });
        map.put(TeleTrusTObjectIdentifiers.ripemd128, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.14
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new RIPEMD128Digest();
            }
        });
        map.put(TeleTrusTObjectIdentifiers.ripemd160, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.15
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new RIPEMD160Digest();
            }
        });
        map.put(TeleTrusTObjectIdentifiers.ripemd256, new BcDigestProvider() { // from class: org.spongycastle.operator.bc.BcDefaultDigestProvider.16
            @Override // org.spongycastle.operator.bc.BcDigestProvider
            public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
                return new RIPEMD256Digest();
            }
        });
        return Collections.unmodifiableMap(map);
    }

    private BcDefaultDigestProvider() {
    }

    @Override // org.spongycastle.operator.bc.BcDigestProvider
    public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
        BcDigestProvider bcDigestProvider = (BcDigestProvider) lookup.get(algorithmIdentifier.getAlgorithm());
        if (bcDigestProvider == null) {
            throw new OperatorCreationException("cannot recognise digest");
        }
        return bcDigestProvider.get(algorithmIdentifier);
    }
}
