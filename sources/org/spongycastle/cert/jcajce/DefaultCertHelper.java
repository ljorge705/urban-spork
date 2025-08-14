package org.spongycastle.cert.jcajce;

import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/* loaded from: classes4.dex */
class DefaultCertHelper extends CertHelper {
    DefaultCertHelper() {
    }

    @Override // org.spongycastle.cert.jcajce.CertHelper
    protected CertificateFactory createCertificateFactory(String str) throws CertificateException {
        return CertificateFactory.getInstance(str);
    }
}
