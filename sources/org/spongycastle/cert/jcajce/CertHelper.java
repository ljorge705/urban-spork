package org.spongycastle.cert.jcajce;

import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/* loaded from: classes4.dex */
abstract class CertHelper {
    protected abstract CertificateFactory createCertificateFactory(String str) throws CertificateException, NoSuchProviderException;

    CertHelper() {
    }

    public CertificateFactory getCertificateFactory(String str) throws CertificateException, NoSuchProviderException {
        return createCertificateFactory(str);
    }
}
