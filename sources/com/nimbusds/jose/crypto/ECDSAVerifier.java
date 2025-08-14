package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.impl.AlgorithmSupportMessage;
import com.nimbusds.jose.crypto.impl.CriticalHeaderParamsDeferral;
import com.nimbusds.jose.crypto.impl.ECDSA;
import com.nimbusds.jose.crypto.impl.ECDSAProvider;
import com.nimbusds.jose.crypto.utils.ECChecks;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.util.Base64URL;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPublicKey;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class ECDSAVerifier extends ECDSAProvider implements JWSVerifier, CriticalHeaderParamsAware {
    private final CriticalHeaderParamsDeferral critPolicy;
    private final ECPublicKey publicKey;

    public ECPublicKey getPublicKey() {
        return this.publicKey;
    }

    public ECDSAVerifier(ECPublicKey eCPublicKey) throws JOSEException {
        this(eCPublicKey, null);
    }

    public ECDSAVerifier(ECKey eCKey) throws JOSEException {
        this(eCKey.toECPublicKey());
    }

    public ECDSAVerifier(ECPublicKey eCPublicKey, Set<String> set) throws JOSEException {
        super(ECDSA.resolveAlgorithm(eCPublicKey));
        CriticalHeaderParamsDeferral criticalHeaderParamsDeferral = new CriticalHeaderParamsDeferral();
        this.critPolicy = criticalHeaderParamsDeferral;
        this.publicKey = eCPublicKey;
        if (!ECChecks.isPointOnCurve(eCPublicKey, Curve.forJWSAlgorithm(supportedECDSAAlgorithm()).iterator().next().toECParameterSpec())) {
            throw new JOSEException("Curve / public key parameters mismatch");
        }
        criticalHeaderParamsDeferral.setDeferredCriticalHeaderParams(set);
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getProcessedCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getDeferredCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Override // com.nimbusds.jose.JWSVerifier
    public boolean verify(JWSHeader jWSHeader, byte[] bArr, Base64URL base64URL) throws Exception {
        JWSAlgorithm algorithm = jWSHeader.getAlgorithm();
        if (!supportedJWSAlgorithms().contains(algorithm)) {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(algorithm, supportedJWSAlgorithms()));
        }
        if (!this.critPolicy.headerPasses(jWSHeader)) {
            return false;
        }
        byte[] bArrDecode = base64URL.decode();
        try {
            ECDSA.ensureLegalSignature(bArrDecode, algorithm);
            byte[] bArrTranscodeSignatureToDER = ECDSA.transcodeSignatureToDER(bArrDecode);
            Signature signerAndVerifier = ECDSA.getSignerAndVerifier(algorithm, getJCAContext().getProvider());
            try {
                signerAndVerifier.initVerify(this.publicKey);
                signerAndVerifier.update(bArr);
                return signerAndVerifier.verify(bArrTranscodeSignatureToDER);
            } catch (InvalidKeyException e) {
                throw new JOSEException("Invalid EC public key: " + e.getMessage(), e);
            } catch (SignatureException unused) {
                return false;
            }
        } catch (JOSEException unused2) {
            return false;
        }
    }
}
