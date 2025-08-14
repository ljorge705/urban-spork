package org.spongycastle.pkcs.bc;

import java.io.IOException;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.util.SubjectPublicKeyInfoFactory;
import org.spongycastle.pkcs.PKCS10CertificationRequestBuilder;

/* loaded from: classes7.dex */
public class BcPKCS10CertificationRequestBuilder extends PKCS10CertificationRequestBuilder {
    public BcPKCS10CertificationRequestBuilder(X500Name x500Name, AsymmetricKeyParameter asymmetricKeyParameter) throws IOException {
        super(x500Name, SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(asymmetricKeyParameter));
    }
}
