package org.spongycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.spongycastle.jcajce.PKIXCertStore;
import org.spongycastle.jcajce.PKIXCertStoreSelector;
import org.spongycastle.jcajce.PKIXExtendedBuilderParameters;
import org.spongycastle.jcajce.PKIXExtendedParameters;
import org.spongycastle.jce.exception.ExtCertPathBuilderException;
import org.spongycastle.x509.ExtendedPKIXBuilderParameters;
import org.spongycastle.x509.ExtendedPKIXParameters;

/* loaded from: classes7.dex */
public class PKIXCertPathBuilderSpi extends CertPathBuilderSpi {
    private Exception certPathException;

    @Override // java.security.cert.CertPathBuilderSpi
    public CertPathBuilderResult engineBuild(CertPathParameters certPathParameters) throws CertPathBuilderException, AnnotatedException, InvalidAlgorithmParameterException {
        PKIXExtendedBuilderParameters pKIXExtendedBuilderParametersBuild;
        Exception exc;
        PKIXExtendedBuilderParameters.Builder builder;
        if (certPathParameters instanceof PKIXBuilderParameters) {
            PKIXBuilderParameters pKIXBuilderParameters = (PKIXBuilderParameters) certPathParameters;
            PKIXExtendedParameters.Builder builder2 = new PKIXExtendedParameters.Builder(pKIXBuilderParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXBuilderParameters extendedPKIXBuilderParameters = (ExtendedPKIXBuilderParameters) certPathParameters;
                Iterator it = extendedPKIXBuilderParameters.getAdditionalStores().iterator();
                while (it.hasNext()) {
                    builder2.addCertificateStore((PKIXCertStore) it.next());
                }
                builder = new PKIXExtendedBuilderParameters.Builder(builder2.build());
                builder.addExcludedCerts(extendedPKIXBuilderParameters.getExcludedCerts());
                builder.setMaxPathLength(extendedPKIXBuilderParameters.getMaxPathLength());
            } else {
                builder = new PKIXExtendedBuilderParameters.Builder(pKIXBuilderParameters);
            }
            pKIXExtendedBuilderParametersBuild = builder.build();
        } else if (certPathParameters instanceof PKIXExtendedBuilderParameters) {
            pKIXExtendedBuilderParametersBuild = (PKIXExtendedBuilderParameters) certPathParameters;
        } else {
            throw new InvalidAlgorithmParameterException("Parameters must be an instance of " + PKIXBuilderParameters.class.getName() + " or " + PKIXExtendedBuilderParameters.class.getName() + ".");
        }
        ArrayList arrayList = new ArrayList();
        PKIXCertStoreSelector targetConstraints = pKIXExtendedBuilderParametersBuild.getBaseParameters().getTargetConstraints();
        try {
            Collection collectionFindCertificates = CertPathValidatorUtilities.findCertificates(targetConstraints, pKIXExtendedBuilderParametersBuild.getBaseParameters().getCertificateStores());
            collectionFindCertificates.addAll(CertPathValidatorUtilities.findCertificates(targetConstraints, pKIXExtendedBuilderParametersBuild.getBaseParameters().getCertStores()));
            if (collectionFindCertificates.isEmpty()) {
                throw new CertPathBuilderException("No certificate found matching targetContraints.");
            }
            Iterator it2 = collectionFindCertificates.iterator();
            CertPathBuilderResult certPathBuilderResultBuild = null;
            while (it2.hasNext() && certPathBuilderResultBuild == null) {
                certPathBuilderResultBuild = build((X509Certificate) it2.next(), pKIXExtendedBuilderParametersBuild, arrayList);
            }
            if (certPathBuilderResultBuild == null && (exc = this.certPathException) != null) {
                if (exc instanceof AnnotatedException) {
                    throw new CertPathBuilderException(this.certPathException.getMessage(), this.certPathException.getCause());
                }
                throw new CertPathBuilderException("Possible certificate chain could not be validated.", this.certPathException);
            }
            if (certPathBuilderResultBuild == null && this.certPathException == null) {
                throw new CertPathBuilderException("Unable to find certificate chain.");
            }
            return certPathBuilderResultBuild;
        } catch (AnnotatedException e) {
            throw new ExtCertPathBuilderException("Error finding target certificate.", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.security.cert.CertPathBuilderResult build(java.security.cert.X509Certificate r6, org.spongycastle.jcajce.PKIXExtendedBuilderParameters r7, java.util.List r8) throws org.spongycastle.jce.provider.AnnotatedException {
        /*
            r5 = this;
            boolean r0 = r8.contains(r6)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            java.util.Set r0 = r7.getExcludedCerts()
            boolean r0 = r0.contains(r6)
            if (r0 == 0) goto L13
            return r1
        L13:
            int r0 = r7.getMaxPathLength()
            r2 = -1
            if (r0 == r2) goto L27
            int r0 = r8.size()
            int r0 = r0 + (-1)
            int r2 = r7.getMaxPathLength()
            if (r0 <= r2) goto L27
            return r1
        L27:
            r8.add(r6)
            org.spongycastle.jcajce.provider.asymmetric.x509.CertificateFactory r0 = new org.spongycastle.jcajce.provider.asymmetric.x509.CertificateFactory     // Catch: java.lang.Exception -> Lf5
            r0.<init>()     // Catch: java.lang.Exception -> Lf5
            org.spongycastle.jce.provider.PKIXCertPathValidatorSpi r2 = new org.spongycastle.jce.provider.PKIXCertPathValidatorSpi     // Catch: java.lang.Exception -> Lf5
            r2.<init>()     // Catch: java.lang.Exception -> Lf5
            org.spongycastle.jcajce.PKIXExtendedParameters r3 = r7.getBaseParameters()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.util.Set r3 = r3.getTrustAnchors()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            org.spongycastle.jcajce.PKIXExtendedParameters r4 = r7.getBaseParameters()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.lang.String r4 = r4.getSigProvider()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            boolean r3 = org.spongycastle.jce.provider.CertPathValidatorUtilities.isIssuerTrustAnchor(r6, r3, r4)     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            if (r3 == 0) goto L78
            java.security.cert.CertPath r0 = r0.engineGenerateCertPath(r8)     // Catch: java.lang.Exception -> L6f org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.security.cert.CertPathValidatorResult r7 = r2.engineValidate(r0, r7)     // Catch: java.lang.Exception -> L66 org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.security.cert.PKIXCertPathValidatorResult r7 = (java.security.cert.PKIXCertPathValidatorResult) r7     // Catch: java.lang.Exception -> L66 org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.security.cert.PKIXCertPathBuilderResult r2 = new java.security.cert.PKIXCertPathBuilderResult     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.security.cert.TrustAnchor r3 = r7.getTrustAnchor()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.security.cert.PolicyNode r4 = r7.getPolicyTree()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.security.PublicKey r7 = r7.getPublicKey()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            r2.<init>(r0, r3, r4, r7)     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            return r2
        L66:
            r7 = move-exception
            org.spongycastle.jce.provider.AnnotatedException r0 = new org.spongycastle.jce.provider.AnnotatedException     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.lang.String r2 = "Certification path could not be validated."
            r0.<init>(r2, r7)     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            throw r0     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
        L6f:
            r7 = move-exception
            org.spongycastle.jce.provider.AnnotatedException r0 = new org.spongycastle.jce.provider.AnnotatedException     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.lang.String r2 = "Certification path could not be constructed from certificate list."
            r0.<init>(r2, r7)     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            throw r0     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
        L78:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            r0.<init>()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            org.spongycastle.jcajce.PKIXExtendedParameters r2 = r7.getBaseParameters()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.util.List r2 = r2.getCertificateStores()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            r0.addAll(r2)     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            org.spongycastle.asn1.ASN1ObjectIdentifier r2 = org.spongycastle.asn1.x509.Extension.issuerAlternativeName     // Catch: java.security.cert.CertificateParsingException -> Le3 org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.lang.String r2 = r2.getId()     // Catch: java.security.cert.CertificateParsingException -> Le3 org.spongycastle.jce.provider.AnnotatedException -> Lec
            byte[] r2 = r6.getExtensionValue(r2)     // Catch: java.security.cert.CertificateParsingException -> Le3 org.spongycastle.jce.provider.AnnotatedException -> Lec
            org.spongycastle.jcajce.PKIXExtendedParameters r3 = r7.getBaseParameters()     // Catch: java.security.cert.CertificateParsingException -> Le3 org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.util.Map r3 = r3.getNamedCertificateStoreMap()     // Catch: java.security.cert.CertificateParsingException -> Le3 org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.util.List r2 = org.spongycastle.jce.provider.CertPathValidatorUtilities.getAdditionalStoresFromAltNames(r2, r3)     // Catch: java.security.cert.CertificateParsingException -> Le3 org.spongycastle.jce.provider.AnnotatedException -> Lec
            r0.addAll(r2)     // Catch: java.security.cert.CertificateParsingException -> Le3 org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.util.HashSet r2 = new java.util.HashSet     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            r2.<init>()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            org.spongycastle.jcajce.PKIXExtendedParameters r3 = r7.getBaseParameters()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lda
            java.util.List r3 = r3.getCertStores()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lda
            java.util.Collection r0 = org.spongycastle.jce.provider.CertPathValidatorUtilities.findIssuerCerts(r6, r3, r0)     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lda
            r2.addAll(r0)     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lda
            boolean r0 = r2.isEmpty()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            if (r0 != 0) goto Ld2
            java.util.Iterator r0 = r2.iterator()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
        Lbf:
            boolean r2 = r0.hasNext()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            if (r2 == 0) goto Lef
            if (r1 != 0) goto Lef
            java.lang.Object r2 = r0.next()     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.security.cert.X509Certificate r2 = (java.security.cert.X509Certificate) r2     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.security.cert.CertPathBuilderResult r1 = r5.build(r2, r7, r8)     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            goto Lbf
        Ld2:
            org.spongycastle.jce.provider.AnnotatedException r7 = new org.spongycastle.jce.provider.AnnotatedException     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.lang.String r0 = "No issuer certificate for certificate in certification path found."
            r7.<init>(r0)     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            throw r7     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
        Lda:
            r7 = move-exception
            org.spongycastle.jce.provider.AnnotatedException r0 = new org.spongycastle.jce.provider.AnnotatedException     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.lang.String r2 = "Cannot find issuer certificate for certificate in certification path."
            r0.<init>(r2, r7)     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            throw r0     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
        Le3:
            r7 = move-exception
            org.spongycastle.jce.provider.AnnotatedException r0 = new org.spongycastle.jce.provider.AnnotatedException     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            java.lang.String r2 = "No additional X.509 stores can be added from certificate locations."
            r0.<init>(r2, r7)     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
            throw r0     // Catch: org.spongycastle.jce.provider.AnnotatedException -> Lec
        Lec:
            r7 = move-exception
            r5.certPathException = r7
        Lef:
            if (r1 != 0) goto Lf4
            r8.remove(r6)
        Lf4:
            return r1
        Lf5:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.String r7 = "Exception creating support classes."
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.PKIXCertPathBuilderSpi.build(java.security.cert.X509Certificate, org.spongycastle.jcajce.PKIXExtendedBuilderParameters, java.util.List):java.security.cert.CertPathBuilderResult");
    }
}
