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
import com.nimbusds.jose.jwk.OctetKeyPair;
import com.nimbusds.jose.util.Base64URL;
import java.util.Collections;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class ECDH1PUX25519Decrypter extends ECDH1PUCryptoProvider implements JWEDecrypter, CriticalHeaderParamsAware {
    private final CriticalHeaderParamsDeferral critPolicy;
    private final OctetKeyPair privateKey;
    private final OctetKeyPair publicKey;

    public OctetKeyPair getPrivateKey() {
        return this.privateKey;
    }

    public OctetKeyPair getPublicKey() {
        return this.publicKey;
    }

    public ECDH1PUX25519Decrypter(OctetKeyPair octetKeyPair, OctetKeyPair octetKeyPair2) throws JOSEException {
        this(octetKeyPair, octetKeyPair2, null);
    }

    public ECDH1PUX25519Decrypter(OctetKeyPair octetKeyPair, OctetKeyPair octetKeyPair2, Set<String> set) throws JOSEException {
        super(octetKeyPair.getCurve(), null);
        CriticalHeaderParamsDeferral criticalHeaderParamsDeferral = new CriticalHeaderParamsDeferral();
        this.critPolicy = criticalHeaderParamsDeferral;
        this.privateKey = octetKeyPair;
        this.publicKey = octetKeyPair2;
        criticalHeaderParamsDeferral.setDeferredCriticalHeaderParams(set);
    }

    @Override // com.nimbusds.jose.crypto.impl.ECDH1PUCryptoProvider
    public Set<Curve> supportedEllipticCurves() {
        return Collections.singleton(Curve.X25519);
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
        OctetKeyPair octetKeyPair = (OctetKeyPair) jWEHeader.getEphemeralPublicKey();
        if (octetKeyPair == null) {
            throw new JOSEException("Missing ephemeral public key \"epk\" JWE header parameter");
        }
        return decryptWithZ(jWEHeader, bArr, ECDH1PU.deriveRecipientZ(this.privateKey, this.publicKey, octetKeyPair), base64URL, base64URL2, base64URL3, base64URL4);
    }
}
