package org.spongycastle.operator.bc;

import org.spongycastle.asn1.kisa.KISAObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes7.dex */
class SEEDUtil {
    SEEDUtil() {
    }

    static AlgorithmIdentifier determineKeyEncAlg() {
        return new AlgorithmIdentifier(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap);
    }
}
