package org.spongycastle.cert.path;

import java.util.HashSet;
import java.util.Set;
import org.spongycastle.cert.X509CertificateHolder;

/* loaded from: classes4.dex */
class CertPathUtils {
    CertPathUtils() {
    }

    static Set getCriticalExtensionsOIDs(X509CertificateHolder[] x509CertificateHolderArr) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i != x509CertificateHolderArr.length; i++) {
            hashSet.addAll(x509CertificateHolderArr[i].getCriticalExtensionOIDs());
        }
        return hashSet;
    }
}
