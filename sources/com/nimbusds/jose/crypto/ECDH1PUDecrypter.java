package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.impl.AAD;
import com.nimbusds.jose.crypto.impl.CriticalHeaderParamsDeferral;
import com.nimbusds.jose.crypto.impl.ECDH1PU;
import com.nimbusds.jose.crypto.impl.ECDH1PUCryptoProvider;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.util.Base64URL;
import java.security.PrivateKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class ECDH1PUDecrypter extends ECDH1PUCryptoProvider implements JWEDecrypter, CriticalHeaderParamsAware {
    public static final Set<Curve> SUPPORTED_ELLIPTIC_CURVES;
    private final CriticalHeaderParamsDeferral critPolicy;
    private final ECPrivateKey privateKey;
    private final ECPublicKey publicKey;

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public ECPublicKey getPublicKey() {
        return this.publicKey;
    }

    @Override // com.nimbusds.jose.crypto.impl.ECDH1PUCryptoProvider
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

    public ECDH1PUDecrypter(ECPrivateKey eCPrivateKey, ECPublicKey eCPublicKey) throws JOSEException {
        this(eCPrivateKey, eCPublicKey, null);
    }

    public ECDH1PUDecrypter(ECPrivateKey eCPrivateKey, ECPublicKey eCPublicKey, Set<String> set) throws JOSEException {
        this(eCPrivateKey, eCPublicKey, set, Curve.forECParameterSpec(eCPrivateKey.getParams()));
    }

    public ECDH1PUDecrypter(ECPrivateKey eCPrivateKey, ECPublicKey eCPublicKey, Set<String> set, Curve curve) throws JOSEException {
        super(curve, null);
        CriticalHeaderParamsDeferral criticalHeaderParamsDeferral = new CriticalHeaderParamsDeferral();
        this.critPolicy = criticalHeaderParamsDeferral;
        criticalHeaderParamsDeferral.setDeferredCriticalHeaderParams(set);
        this.privateKey = eCPrivateKey;
        this.publicKey = eCPublicKey;
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
        return decryptWithZ(jWEHeader, bArr, ECDH1PU.deriveRecipientZ(this.privateKey, this.publicKey, eCKey.toECPublicKey(), getJCAContext().getKeyEncryptionProvider()), base64URL, base64URL2, base64URL3, base64URL4);
    }
}
