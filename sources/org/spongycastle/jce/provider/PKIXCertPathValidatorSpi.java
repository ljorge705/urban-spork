package org.spongycastle.jce.provider;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.TBSCertificate;
import org.spongycastle.jcajce.PKIXExtendedBuilderParameters;
import org.spongycastle.jcajce.PKIXExtendedParameters;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.exception.ExtCertPathValidatorException;
import org.spongycastle.x509.ExtendedPKIXParameters;

/* loaded from: classes7.dex */
public class PKIXCertPathValidatorSpi extends CertPathValidatorSpi {
    private final JcaJceHelper helper = new BCJcaJceHelper();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v8, types: [org.spongycastle.asn1.x509.AlgorithmIdentifier] */
    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) throws CertificateNotYetValidException, IOException, CertPathValidatorException, CertificateExpiredException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters baseParameters;
        List<? extends Certificate> list;
        X500Name ca2;
        PublicKey cAPublicKey;
        HashSet hashSet;
        PKIXNameConstraintValidator pKIXNameConstraintValidator;
        ArrayList[] arrayListArr;
        HashSet hashSet2;
        PKIXCertPathValidatorSpi pKIXCertPathValidatorSpi = this;
        if (certPathParameters instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder((PKIXParameters) certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters;
                builder.setUseDeltasEnabled(extendedPKIXParameters.isUseDeltasEnabled());
                builder.setValidityModel(extendedPKIXParameters.getValidityModel());
            }
            baseParameters = builder.build();
        } else if (certPathParameters instanceof PKIXExtendedBuilderParameters) {
            baseParameters = ((PKIXExtendedBuilderParameters) certPathParameters).getBaseParameters();
        } else if (certPathParameters instanceof PKIXExtendedParameters) {
            baseParameters = (PKIXExtendedParameters) certPathParameters;
        } else {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
        }
        if (baseParameters.getTrustAnchors() == null) {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
        List<? extends Certificate> certificates = certPath.getCertificates();
        int size = certificates.size();
        int algorithmIdentifier = -1;
        if (certificates.isEmpty()) {
            throw new CertPathValidatorException("Certification path is empty.", null, certPath, -1);
        }
        Set initialPolicies = baseParameters.getInitialPolicies();
        try {
            TrustAnchor trustAnchorFindTrustAnchor = CertPathValidatorUtilities.findTrustAnchor((X509Certificate) certificates.get(certificates.size() - 1), baseParameters.getTrustAnchors(), baseParameters.getSigProvider());
            if (trustAnchorFindTrustAnchor == null) {
                list = certificates;
                try {
                    throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath, -1);
                } catch (AnnotatedException e) {
                    e = e;
                    throw new CertPathValidatorException(e.getMessage(), e.getUnderlyingException(), certPath, list.size() - 1);
                }
            }
            checkCertificate(trustAnchorFindTrustAnchor.getTrustedCert());
            PKIXExtendedParameters pKIXExtendedParametersBuild = new PKIXExtendedParameters.Builder(baseParameters).setTrustAnchor(trustAnchorFindTrustAnchor).build();
            int i = size + 1;
            ArrayList[] arrayListArr2 = new ArrayList[i];
            for (int i2 = 0; i2 < i; i2++) {
                arrayListArr2[i2] = new ArrayList();
            }
            HashSet hashSet3 = new HashSet();
            hashSet3.add("2.5.29.32.0");
            PKIXPolicyNode pKIXPolicyNode = new PKIXPolicyNode(new ArrayList(), 0, hashSet3, null, new HashSet(), "2.5.29.32.0", false);
            arrayListArr2[0].add(pKIXPolicyNode);
            PKIXNameConstraintValidator pKIXNameConstraintValidator2 = new PKIXNameConstraintValidator();
            HashSet hashSet4 = new HashSet();
            int i3 = pKIXExtendedParametersBuild.isExplicitPolicyRequired() ? 0 : i;
            int i4 = pKIXExtendedParametersBuild.isAnyPolicyInhibited() ? 0 : i;
            if (pKIXExtendedParametersBuild.isPolicyMappingInhibited()) {
                i = 0;
            }
            X509Certificate trustedCert = trustAnchorFindTrustAnchor.getTrustedCert();
            try {
                if (trustedCert != null) {
                    ca2 = PrincipalUtils.getSubjectPrincipal(trustedCert);
                    cAPublicKey = trustedCert.getPublicKey();
                } else {
                    ca2 = PrincipalUtils.getCA(trustAnchorFindTrustAnchor);
                    cAPublicKey = trustAnchorFindTrustAnchor.getCAPublicKey();
                }
                try {
                    algorithmIdentifier = CertPathValidatorUtilities.getAlgorithmIdentifier(cAPublicKey);
                    algorithmIdentifier.getAlgorithm();
                    algorithmIdentifier.getParameters();
                    if (pKIXExtendedParametersBuild.getTargetConstraints() != null && !pKIXExtendedParametersBuild.getTargetConstraints().match((Certificate) certificates.get(0))) {
                        throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath, 0);
                    }
                    List certPathCheckers = pKIXExtendedParametersBuild.getCertPathCheckers();
                    Iterator it = certPathCheckers.iterator();
                    while (it.hasNext()) {
                        ((PKIXCertPathChecker) it.next()).init(false);
                    }
                    int iPrepareNextCertM = size;
                    X509Certificate x509Certificate = null;
                    int i5 = i;
                    int i6 = i4;
                    PKIXPolicyNode pKIXPolicyNode2 = pKIXPolicyNode;
                    int i7 = i3;
                    int size2 = certificates.size() - 1;
                    int iPrepareNextCertI1 = i7;
                    while (size2 >= 0) {
                        int i8 = size - size2;
                        Set set = initialPolicies;
                        X509Certificate x509Certificate2 = (X509Certificate) certificates.get(size2);
                        boolean z = size2 == certificates.size() + (-1);
                        try {
                            checkCertificate(x509Certificate2);
                            TrustAnchor trustAnchor = trustAnchorFindTrustAnchor;
                            JcaJceHelper jcaJceHelper = pKIXCertPathValidatorSpi.helper;
                            int i9 = i6;
                            List<? extends Certificate> list2 = certificates;
                            int i10 = iPrepareNextCertI1;
                            PKIXExtendedParameters pKIXExtendedParameters = pKIXExtendedParametersBuild;
                            int i11 = size2;
                            PKIXExtendedParameters pKIXExtendedParameters2 = pKIXExtendedParametersBuild;
                            int iPrepareNextCertI2 = i5;
                            PKIXNameConstraintValidator pKIXNameConstraintValidator3 = pKIXNameConstraintValidator2;
                            ArrayList[] arrayListArr3 = arrayListArr2;
                            RFC3280CertPathUtilities.processCertA(certPath, pKIXExtendedParameters, size2, cAPublicKey, z, ca2, trustedCert, jcaJceHelper);
                            RFC3280CertPathUtilities.processCertBC(certPath, i11, pKIXNameConstraintValidator3);
                            PKIXPolicyNode pKIXPolicyNodeProcessCertE = RFC3280CertPathUtilities.processCertE(certPath, i11, RFC3280CertPathUtilities.processCertD(certPath, i11, hashSet4, pKIXPolicyNode2, arrayListArr3, i9));
                            RFC3280CertPathUtilities.processCertF(certPath, i11, pKIXPolicyNodeProcessCertE, i10);
                            if (i8 == size) {
                                pKIXNameConstraintValidator = pKIXNameConstraintValidator3;
                                arrayListArr = arrayListArr3;
                                pKIXCertPathValidatorSpi = this;
                                pKIXPolicyNode2 = pKIXPolicyNodeProcessCertE;
                                i6 = i9;
                                iPrepareNextCertM = iPrepareNextCertM;
                                iPrepareNextCertI1 = i10;
                            } else if (x509Certificate2 != null && x509Certificate2.getVersion() == 1) {
                                if (i8 != 1 || !x509Certificate2.equals(trustAnchor.getTrustedCert())) {
                                    throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath, i11);
                                }
                                pKIXNameConstraintValidator = pKIXNameConstraintValidator3;
                                arrayListArr = arrayListArr3;
                                pKIXCertPathValidatorSpi = this;
                                pKIXPolicyNode2 = pKIXPolicyNodeProcessCertE;
                                i6 = i9;
                                iPrepareNextCertM = iPrepareNextCertM;
                                iPrepareNextCertI1 = i10;
                            } else {
                                RFC3280CertPathUtilities.prepareNextCertA(certPath, i11);
                                arrayListArr = arrayListArr3;
                                PKIXPolicyNode pKIXPolicyNodePrepareCertB = RFC3280CertPathUtilities.prepareCertB(certPath, i11, arrayListArr, pKIXPolicyNodeProcessCertE, iPrepareNextCertI2);
                                RFC3280CertPathUtilities.prepareNextCertG(certPath, i11, pKIXNameConstraintValidator3);
                                int iPrepareNextCertH1 = RFC3280CertPathUtilities.prepareNextCertH1(certPath, i11, i10);
                                int iPrepareNextCertH2 = RFC3280CertPathUtilities.prepareNextCertH2(certPath, i11, iPrepareNextCertI2);
                                int iPrepareNextCertH3 = RFC3280CertPathUtilities.prepareNextCertH3(certPath, i11, i9);
                                iPrepareNextCertI1 = RFC3280CertPathUtilities.prepareNextCertI1(certPath, i11, iPrepareNextCertH1);
                                iPrepareNextCertI2 = RFC3280CertPathUtilities.prepareNextCertI2(certPath, i11, iPrepareNextCertH2);
                                int iPrepareNextCertJ = RFC3280CertPathUtilities.prepareNextCertJ(certPath, i11, iPrepareNextCertH3);
                                RFC3280CertPathUtilities.prepareNextCertK(certPath, i11);
                                iPrepareNextCertM = RFC3280CertPathUtilities.prepareNextCertM(certPath, i11, RFC3280CertPathUtilities.prepareNextCertL(certPath, i11, iPrepareNextCertM));
                                RFC3280CertPathUtilities.prepareNextCertN(certPath, i11);
                                Set<String> criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                                if (criticalExtensionOIDs != null) {
                                    hashSet2 = new HashSet(criticalExtensionOIDs);
                                    hashSet2.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                    hashSet2.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                    hashSet2.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                    hashSet2.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                    hashSet2.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                    hashSet2.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                    hashSet2.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                    hashSet2.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                    hashSet2.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                    hashSet2.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                } else {
                                    hashSet2 = new HashSet();
                                }
                                RFC3280CertPathUtilities.prepareNextCertO(certPath, i11, hashSet2, certPathCheckers);
                                X500Name subjectPrincipal = PrincipalUtils.getSubjectPrincipal(x509Certificate2);
                                try {
                                    pKIXNameConstraintValidator = pKIXNameConstraintValidator3;
                                    pKIXCertPathValidatorSpi = this;
                                    try {
                                        PublicKey nextWorkingKey = CertPathValidatorUtilities.getNextWorkingKey(certPath.getCertificates(), i11, pKIXCertPathValidatorSpi.helper);
                                        AlgorithmIdentifier algorithmIdentifier2 = CertPathValidatorUtilities.getAlgorithmIdentifier(nextWorkingKey);
                                        algorithmIdentifier2.getAlgorithm();
                                        algorithmIdentifier2.getParameters();
                                        pKIXPolicyNode2 = pKIXPolicyNodePrepareCertB;
                                        i6 = iPrepareNextCertJ;
                                        ca2 = subjectPrincipal;
                                        cAPublicKey = nextWorkingKey;
                                        trustedCert = x509Certificate2;
                                    } catch (CertPathValidatorException e2) {
                                        e = e2;
                                        throw new CertPathValidatorException("Next working key could not be retrieved.", e, certPath, i11);
                                    }
                                } catch (CertPathValidatorException e3) {
                                    e = e3;
                                }
                            }
                            i5 = iPrepareNextCertI2;
                            size2 = i11 - 1;
                            x509Certificate = x509Certificate2;
                            initialPolicies = set;
                            certificates = list2;
                            pKIXExtendedParametersBuild = pKIXExtendedParameters2;
                            trustAnchorFindTrustAnchor = trustAnchor;
                            PKIXNameConstraintValidator pKIXNameConstraintValidator4 = pKIXNameConstraintValidator;
                            arrayListArr2 = arrayListArr;
                            pKIXNameConstraintValidator2 = pKIXNameConstraintValidator4;
                        } catch (AnnotatedException e4) {
                            throw new CertPathValidatorException(e4.getMessage(), e4.getUnderlyingException(), certPath, size2);
                        }
                    }
                    PKIXExtendedParameters pKIXExtendedParameters3 = pKIXExtendedParametersBuild;
                    ArrayList[] arrayListArr4 = arrayListArr2;
                    TrustAnchor trustAnchor2 = trustAnchorFindTrustAnchor;
                    Set set2 = initialPolicies;
                    X509Certificate x509Certificate3 = x509Certificate;
                    int i12 = size2;
                    int i13 = i12 + 1;
                    int iWrapupCertB = RFC3280CertPathUtilities.wrapupCertB(certPath, i13, RFC3280CertPathUtilities.wrapupCertA(iPrepareNextCertI1, x509Certificate3));
                    Set<String> criticalExtensionOIDs2 = x509Certificate3.getCriticalExtensionOIDs();
                    if (criticalExtensionOIDs2 != null) {
                        hashSet = new HashSet(criticalExtensionOIDs2);
                        hashSet.remove(RFC3280CertPathUtilities.KEY_USAGE);
                        hashSet.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                        hashSet.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                        hashSet.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                        hashSet.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                        hashSet.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                        hashSet.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                        hashSet.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                        hashSet.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                        hashSet.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                        hashSet.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
                        hashSet.remove(Extension.extendedKeyUsage.getId());
                    } else {
                        hashSet = new HashSet();
                    }
                    RFC3280CertPathUtilities.wrapupCertF(certPath, i13, certPathCheckers, hashSet);
                    PKIXPolicyNode pKIXPolicyNodeWrapupCertG = RFC3280CertPathUtilities.wrapupCertG(certPath, pKIXExtendedParameters3, set2, i13, arrayListArr4, pKIXPolicyNode2, hashSet4);
                    if (iWrapupCertB > 0 || pKIXPolicyNodeWrapupCertG != null) {
                        return new PKIXCertPathValidatorResult(trustAnchor2, pKIXPolicyNodeWrapupCertG, x509Certificate3.getPublicKey());
                    }
                    throw new CertPathValidatorException("Path processing failed on policy.", null, certPath, i12);
                } catch (CertPathValidatorException e5) {
                    throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e5, certPath, -1);
                }
            } catch (IllegalArgumentException e6) {
                throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", e6, certPath, algorithmIdentifier);
            }
        } catch (AnnotatedException e7) {
            e = e7;
            list = certificates;
        }
    }

    static void checkCertificate(X509Certificate x509Certificate) throws AnnotatedException {
        try {
            TBSCertificate.getInstance(x509Certificate.getTBSCertificate());
        } catch (IllegalArgumentException e) {
            throw new AnnotatedException(e.getMessage());
        } catch (CertificateEncodingException unused) {
            throw new AnnotatedException("unable to process TBSCertificate");
        }
    }
}
