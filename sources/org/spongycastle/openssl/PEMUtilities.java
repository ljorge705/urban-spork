package org.spongycastle.openssl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.util.Integers;

/* loaded from: classes7.dex */
final class PEMUtilities {
    private static final Map KEYSIZES;
    private static final Set PKCS5_SCHEME_1;
    private static final Set PKCS5_SCHEME_2;

    PEMUtilities() {
    }

    static {
        HashMap map = new HashMap();
        KEYSIZES = map;
        HashSet hashSet = new HashSet();
        PKCS5_SCHEME_1 = hashSet;
        HashSet hashSet2 = new HashSet();
        PKCS5_SCHEME_2 = hashSet2;
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD2AndRC2_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC);
        hashSet2.add(PKCSObjectIdentifiers.id_PBES2);
        hashSet2.add(PKCSObjectIdentifiers.des_EDE3_CBC);
        hashSet2.add(NISTObjectIdentifiers.id_aes128_CBC);
        hashSet2.add(NISTObjectIdentifiers.id_aes192_CBC);
        hashSet2.add(NISTObjectIdentifiers.id_aes256_CBC);
        map.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), Integers.valueOf(192));
        map.put(NISTObjectIdentifiers.id_aes128_CBC.getId(), Integers.valueOf(128));
        map.put(NISTObjectIdentifiers.id_aes192_CBC.getId(), Integers.valueOf(192));
        map.put(NISTObjectIdentifiers.id_aes256_CBC.getId(), Integers.valueOf(256));
    }

    static int getKeySize(String str) {
        Map map = KEYSIZES;
        if (!map.containsKey(str)) {
            throw new IllegalStateException("no key size for algorithm: " + str);
        }
        return ((Integer) map.get(str)).intValue();
    }

    static boolean isPKCS5Scheme1(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return PKCS5_SCHEME_1.contains(aSN1ObjectIdentifier);
    }

    public static boolean isPKCS5Scheme2(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return PKCS5_SCHEME_2.contains(aSN1ObjectIdentifier);
    }

    public static boolean isPKCS12(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return aSN1ObjectIdentifier.getId().startsWith(PKCSObjectIdentifiers.pkcs_12PbeIds.getId());
    }
}
