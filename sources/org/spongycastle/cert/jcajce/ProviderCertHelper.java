package org.spongycastle.cert.jcajce;

import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/* loaded from: classes4.dex */
class ProviderCertHelper extends CertHelper {
    private final Provider provider;

    ProviderCertHelper(Provider provider) {
        this.provider = provider;
    }

    @Override // org.spongycastle.cert.jcajce.CertHelper
    protected CertificateFactory createCertificateFactory(String str) throws CertificateException {
        return CertificateFactory.getInstance(str, this.provider);
    }
}
