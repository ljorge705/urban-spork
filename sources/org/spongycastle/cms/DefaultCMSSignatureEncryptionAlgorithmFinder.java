package org.spongycastle.cms;

import java.util.HashSet;
import java.util.Set;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes4.dex */
public class DefaultCMSSignatureEncryptionAlgorithmFinder implements CMSSignatureEncryptionAlgorithmFinder {
    private static final Set RSA_PKCS1d5;

    static {
        HashSet hashSet = new HashSet();
        RSA_PKCS1d5 = hashSet;
        hashSet.add(PKCSObjectIdentifiers.md2WithRSAEncryption);
        hashSet.add(PKCSObjectIdentifiers.md4WithRSAEncryption);
        hashSet.add(PKCSObjectIdentifiers.md5WithRSAEncryption);
        hashSet.add(PKCSObjectIdentifiers.sha1WithRSAEncryption);
        hashSet.add(OIWObjectIdentifiers.md4WithRSAEncryption);
        hashSet.add(OIWObjectIdentifiers.md4WithRSA);
        hashSet.add(OIWObjectIdentifiers.md5WithRSA);
        hashSet.add(OIWObjectIdentifiers.sha1WithRSA);
        hashSet.add(TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128);
        hashSet.add(TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160);
        hashSet.add(TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256);
    }

    @Override // org.spongycastle.cms.CMSSignatureEncryptionAlgorithmFinder
    public AlgorithmIdentifier findEncryptionAlgorithm(AlgorithmIdentifier algorithmIdentifier) {
        return RSA_PKCS1d5.contains(algorithmIdentifier.getAlgorithm()) ? new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE) : algorithmIdentifier;
    }
}
