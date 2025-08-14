package org.spongycastle.eac.operator.jcajce;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import java.util.Hashtable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.eac.EACObjectIdentifiers;

/* loaded from: classes7.dex */
abstract class EACHelper {
    private static final Hashtable sigNames;

    protected abstract Signature createSignature(String str) throws NoSuchAlgorithmException, NoSuchProviderException;

    EACHelper() {
    }

    static {
        Hashtable hashtable = new Hashtable();
        sigNames = hashtable;
        hashtable.put(EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_1, "SHA1withRSA");
        hashtable.put(EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_256, "SHA256withRSA");
        hashtable.put(EACObjectIdentifiers.id_TA_RSA_PSS_SHA_1, "SHA1withRSAandMGF1");
        hashtable.put(EACObjectIdentifiers.id_TA_RSA_PSS_SHA_256, "SHA256withRSAandMGF1");
        hashtable.put(EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_512, "SHA512withRSA");
        hashtable.put(EACObjectIdentifiers.id_TA_RSA_PSS_SHA_512, "SHA512withRSAandMGF1");
        hashtable.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_1, "SHA1withECDSA");
        hashtable.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_224, "SHA224withECDSA");
        hashtable.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_256, "SHA256withECDSA");
        hashtable.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_384, "SHA384withECDSA");
        hashtable.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_512, "SHA512withECDSA");
    }

    public Signature getSignature(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws NoSuchAlgorithmException, NoSuchProviderException {
        return createSignature((String) sigNames.get(aSN1ObjectIdentifier));
    }
}
