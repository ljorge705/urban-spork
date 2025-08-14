package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.impl.AAD;
import com.nimbusds.jose.crypto.impl.CriticalHeaderParamsDeferral;
import com.nimbusds.jose.crypto.impl.ECDH;
import com.nimbusds.jose.crypto.impl.ECDHCryptoProvider;
import com.nimbusds.jose.crypto.utils.ECChecks;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.util.Base64URL;
import java.security.PrivateKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public class ECDHDecrypter extends ECDHCryptoProvider implements JWEDecrypter, CriticalHeaderParamsAware {
    public static final Set<Curve> SUPPORTED_ELLIPTIC_CURVES;
    private final CriticalHeaderParamsDeferral critPolicy;
    private final PrivateKey privateKey;

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    @Override // com.nimbusds.jose.crypto.impl.ECDHCryptoProvider
    public Set<Curve> supportedEllipticCurves() {
        return SUPPORTED_ELLIPTIC_CURVES;
    }

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(Curve.P_256);
        linkedHashSet.add(Curve.P_384);
        linkedHashSet.add(Curve.P_521);
        SUPPORTED_ELLIPTIC_CURVES = Collections.unmodifiableSet(linkedHashSet);
    }

    public ECDHDecrypter(ECPrivateKey eCPrivateKey) throws JOSEException {
        this(eCPrivateKey, null);
    }

    public ECDHDecrypter(ECKey eCKey) throws JOSEException {
        super(eCKey.getCurve(), null);
        this.critPolicy = new CriticalHeaderParamsDeferral();
        if (!eCKey.isPrivate()) {
            throw new JOSEException("The EC JWK doesn't contain a private part");
        }
        this.privateKey = eCKey.toECPrivateKey();
    }

    public ECDHDecrypter(ECPrivateKey eCPrivateKey, Set<String> set) throws JOSEException {
        this(eCPrivateKey, set, Curve.forECParameterSpec(eCPrivateKey.getParams()));
    }

    public ECDHDecrypter(PrivateKey privateKey, Set<String> set, Curve curve) throws JOSEException {
        super(curve, null);
        CriticalHeaderParamsDeferral criticalHeaderParamsDeferral = new CriticalHeaderParamsDeferral();
        this.critPolicy = criticalHeaderParamsDeferral;
        criticalHeaderParamsDeferral.setDeferredCriticalHeaderParams(set);
        this.privateKey = privateKey;
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getProcessedCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getDeferredCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Deprecated
    public byte[] decrypt(JWEHeader jWEHeader, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4) throws JOSEException {
        return decrypt(jWEHeader, base64URL, base64URL2, base64URL3, base64URL4, AAD.compute(jWEHeader));
    }

    @Override // com.nimbusds.jose.JWEDecrypter
    public byte[] decrypt(JWEHeader jWEHeader, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, byte[] bArr) throws JOSEException {
        this.critPolicy.ensureHeaderPasses(jWEHeader);
        ECKey eCKey = (ECKey) jWEHeader.getEphemeralPublicKey();
        if (eCKey == null) {
            throw new JOSEException("Missing ephemeral public EC key \"epk\" JWE header parameter");
        }
        ECPublicKey eCPublicKey = eCKey.toECPublicKey();
        if (getPrivateKey() instanceof ECPrivateKey) {
            if (!ECChecks.isPointOnCurve(eCPublicKey, (ECPrivateKey) getPrivateKey())) {
                throw new JOSEException("Invalid ephemeral public EC key: Point(s) not on the expected curve");
            }
        } else if (!ECChecks.isPointOnCurve(eCPublicKey, getCurve().toECParameterSpec())) {
            throw new JOSEException("Invalid ephemeral public EC key: Point(s) not on the expected curve");
        }
        return decryptWithZ(jWEHeader, bArr, ECDH.deriveSharedSecret(eCPublicKey, this.privateKey, getJCAContext().getKeyEncryptionProvider()), base64URL, base64URL2, base64URL3, base64URL4);
    }
}
