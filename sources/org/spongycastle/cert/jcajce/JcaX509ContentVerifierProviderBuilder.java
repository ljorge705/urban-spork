package org.spongycastle.cert.jcajce;

import java.security.Provider;
import java.security.cert.CertificateException;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.cert.X509ContentVerifierProviderBuilder;
import org.spongycastle.operator.ContentVerifierProvider;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.operator.jcajce.JcaContentVerifierProviderBuilder;

/* loaded from: classes4.dex */
public class JcaX509ContentVerifierProviderBuilder implements X509ContentVerifierProviderBuilder {
    private JcaContentVerifierProviderBuilder builder = new JcaContentVerifierProviderBuilder();

    public JcaX509ContentVerifierProviderBuilder setProvider(Provider provider) {
        this.builder.setProvider(provider);
        return this;
    }

    public JcaX509ContentVerifierProviderBuilder setProvider(String str) {
        this.builder.setProvider(str);
        return this;
    }

    @Override // org.spongycastle.cert.X509ContentVerifierProviderBuilder
    public ContentVerifierProvider build(SubjectPublicKeyInfo subjectPublicKeyInfo) throws OperatorCreationException {
        return this.builder.build(subjectPublicKeyInfo);
    }

    @Override // org.spongycastle.cert.X509ContentVerifierProviderBuilder
    public ContentVerifierProvider build(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException {
        try {
            return this.builder.build(x509CertificateHolder);
        } catch (CertificateException e) {
            throw new OperatorCreationException("Unable to process certificate: " + e.getMessage(), e);
        }
    }
}
