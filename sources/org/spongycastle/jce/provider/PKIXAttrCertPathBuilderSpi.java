package org.spongycastle.jce.provider;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathParameters;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.jcajce.PKIXCertStoreSelector;
import org.spongycastle.jcajce.PKIXExtendedBuilderParameters;
import org.spongycastle.jce.exception.ExtCertPathBuilderException;
import org.spongycastle.util.Store;
import org.spongycastle.util.StoreException;
import org.spongycastle.x509.ExtendedPKIXBuilderParameters;
import org.spongycastle.x509.ExtendedPKIXParameters;
import org.spongycastle.x509.X509AttributeCertStoreSelector;
import org.spongycastle.x509.X509AttributeCertificate;
import org.spongycastle.x509.X509CertStoreSelector;

/* loaded from: classes7.dex */
public class PKIXAttrCertPathBuilderSpi extends CertPathBuilderSpi {
    private Exception certPathException;

    @Override // java.security.cert.CertPathBuilderSpi
    public CertPathBuilderResult engineBuild(CertPathParameters certPathParameters) throws CertPathBuilderException, NoSuchAlgorithmException, CertificateException, AnnotatedException, NoSuchProviderException, InvalidAlgorithmParameterException {
        PKIXExtendedBuilderParameters pKIXExtendedBuilderParametersBuild;
        boolean z = certPathParameters instanceof PKIXBuilderParameters;
        if (!z && !(certPathParameters instanceof ExtendedPKIXBuilderParameters) && !(certPathParameters instanceof PKIXExtendedBuilderParameters)) {
            throw new InvalidAlgorithmParameterException("Parameters must be an instance of " + PKIXBuilderParameters.class.getName() + " or " + PKIXExtendedBuilderParameters.class.getName() + ".");
        }
        List arrayList = new ArrayList();
        if (z) {
            PKIXExtendedBuilderParameters.Builder builder = new PKIXExtendedBuilderParameters.Builder((PKIXBuilderParameters) certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXBuilderParameters extendedPKIXBuilderParameters = (ExtendedPKIXBuilderParameters) certPathParameters;
                builder.addExcludedCerts(extendedPKIXBuilderParameters.getExcludedCerts());
                builder.setMaxPathLength(extendedPKIXBuilderParameters.getMaxPathLength());
                arrayList = extendedPKIXBuilderParameters.getStores();
            }
            pKIXExtendedBuilderParametersBuild = builder.build();
        } else {
            pKIXExtendedBuilderParametersBuild = (PKIXExtendedBuilderParameters) certPathParameters;
        }
        ArrayList arrayList2 = new ArrayList();
        Cloneable targetConstraints = pKIXExtendedBuilderParametersBuild.getBaseParameters().getTargetConstraints();
        if (!(targetConstraints instanceof X509AttributeCertStoreSelector)) {
            throw new CertPathBuilderException("TargetConstraints must be an instance of " + X509AttributeCertStoreSelector.class.getName() + " for " + getClass().getName() + " class.");
        }
        try {
            Collection collectionFindCertificates = findCertificates((X509AttributeCertStoreSelector) targetConstraints, arrayList);
            if (collectionFindCertificates.isEmpty()) {
                throw new CertPathBuilderException("No attribute certificate found matching targetContraints.");
            }
            Iterator it = collectionFindCertificates.iterator();
            CertPathBuilderResult certPathBuilderResultBuild = null;
            while (it.hasNext() && certPathBuilderResultBuild == null) {
                X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate) it.next();
                X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
                Principal[] principals = x509AttributeCertificate.getIssuer().getPrincipals();
                HashSet hashSet = new HashSet();
                for (Principal principal : principals) {
                    try {
                        if (principal instanceof X500Principal) {
                            x509CertStoreSelector.setSubject(((X500Principal) principal).getEncoded());
                        }
                        PKIXCertStoreSelector<? extends Certificate> pKIXCertStoreSelectorBuild = new PKIXCertStoreSelector.Builder(x509CertStoreSelector).build();
                        hashSet.addAll(CertPathValidatorUtilities.findCertificates(pKIXCertStoreSelectorBuild, pKIXExtendedBuilderParametersBuild.getBaseParameters().getCertStores()));
                        hashSet.addAll(CertPathValidatorUtilities.findCertificates(pKIXCertStoreSelectorBuild, pKIXExtendedBuilderParametersBuild.getBaseParameters().getCertificateStores()));
                    } catch (IOException e) {
                        throw new ExtCertPathBuilderException("cannot encode X500Principal.", e);
                    } catch (AnnotatedException e2) {
                        throw new ExtCertPathBuilderException("Public key certificate for attribute certificate cannot be searched.", e2);
                    }
                }
                if (hashSet.isEmpty()) {
                    throw new CertPathBuilderException("Public key certificate for attribute certificate cannot be found.");
                }
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext() && certPathBuilderResultBuild == null) {
                    certPathBuilderResultBuild = build(x509AttributeCertificate, (X509Certificate) it2.next(), pKIXExtendedBuilderParametersBuild, arrayList2);
                }
            }
            if (certPathBuilderResultBuild == null && this.certPathException != null) {
                throw new ExtCertPathBuilderException("Possible certificate chain could not be validated.", this.certPathException);
            }
            if (certPathBuilderResultBuild == null && this.certPathException == null) {
                throw new CertPathBuilderException("Unable to find certificate chain.");
            }
            return certPathBuilderResultBuild;
        } catch (AnnotatedException e3) {
            throw new ExtCertPathBuilderException("Error finding target attribute certificate.", e3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x010b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.security.cert.CertPathBuilderResult build(org.spongycastle.x509.X509AttributeCertificate r6, java.security.cert.X509Certificate r7, org.spongycastle.jcajce.PKIXExtendedBuilderParameters r8, java.util.List r9) throws java.security.NoSuchAlgorithmException, java.security.cert.CertificateException, org.spongycastle.jce.provider.AnnotatedException, java.security.NoSuchProviderException {
        /*
            Method dump skipped, instructions count: 279
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.PKIXAttrCertPathBuilderSpi.build(org.spongycastle.x509.X509AttributeCertificate, java.security.cert.X509Certificate, org.spongycastle.jcajce.PKIXExtendedBuilderParameters, java.util.List):java.security.cert.CertPathBuilderResult");
    }

    protected static Collection findCertificates(X509AttributeCertStoreSelector x509AttributeCertStoreSelector, List list) throws AnnotatedException {
        HashSet hashSet = new HashSet();
        for (Object obj : list) {
            if (obj instanceof Store) {
                try {
                    hashSet.addAll(((Store) obj).getMatches(x509AttributeCertStoreSelector));
                } catch (StoreException e) {
                    throw new AnnotatedException("Problem while picking certificates from X.509 store.", e);
                }
            }
        }
        return hashSet;
    }
}
