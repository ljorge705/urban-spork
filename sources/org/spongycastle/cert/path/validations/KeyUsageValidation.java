package org.spongycastle.cert.path.validations;

import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.KeyUsage;
import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.cert.path.CertPathValidation;
import org.spongycastle.cert.path.CertPathValidationContext;
import org.spongycastle.cert.path.CertPathValidationException;
import org.spongycastle.util.Memoable;

/* loaded from: classes4.dex */
public class KeyUsageValidation implements CertPathValidation {
    private boolean isMandatory;

    public KeyUsageValidation() {
        this(true);
    }

    public KeyUsageValidation(boolean z) {
        this.isMandatory = z;
    }

    @Override // org.spongycastle.cert.path.CertPathValidation
    public void validate(CertPathValidationContext certPathValidationContext, X509CertificateHolder x509CertificateHolder) throws CertPathValidationException {
        certPathValidationContext.addHandledExtension(Extension.keyUsage);
        if (certPathValidationContext.isEndEntity()) {
            return;
        }
        KeyUsage keyUsageFromExtensions = KeyUsage.fromExtensions(x509CertificateHolder.getExtensions());
        if (keyUsageFromExtensions != null) {
            if (!keyUsageFromExtensions.hasUsages(4)) {
                throw new CertPathValidationException("Issuer certificate KeyUsage extension does not permit key signing");
            }
        } else if (this.isMandatory) {
            throw new CertPathValidationException("KeyUsage extension not present in CA certificate");
        }
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new KeyUsageValidation(this.isMandatory);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        this.isMandatory = ((KeyUsageValidation) memoable).isMandatory;
    }
}
