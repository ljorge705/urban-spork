package com.nimbusds.jose.crypto;

import com.nimbusds.jose.ActionRequiredForJWSCompletionException;
import com.nimbusds.jose.CompletableJWSObjectSigning;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSSignerOption;
import com.nimbusds.jose.crypto.impl.AlgorithmSupportMessage;
import com.nimbusds.jose.crypto.impl.ECDSA;
import com.nimbusds.jose.crypto.impl.ECDSAProvider;
import com.nimbusds.jose.crypto.opts.OptionUtils;
import com.nimbusds.jose.crypto.opts.UserAuthenticationRequired;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.util.Base64URL;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.util.Collections;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class ECDSASigner extends ECDSAProvider implements JWSSigner {
    private final Set<JWSSignerOption> opts;
    private final PrivateKey privateKey;

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ECDSASigner(ECPrivateKey eCPrivateKey) throws JOSEException {
        this(eCPrivateKey, (Set<JWSSignerOption>) null);
    }

    public ECDSASigner(ECPrivateKey eCPrivateKey, Set<JWSSignerOption> set) throws JOSEException {
        super(ECDSA.resolveAlgorithm(eCPrivateKey));
        this.privateKey = eCPrivateKey;
        this.opts = set == null ? Collections.emptySet() : set;
    }

    public ECDSASigner(PrivateKey privateKey, Curve curve) throws JOSEException {
        this(privateKey, curve, null);
    }

    public ECDSASigner(PrivateKey privateKey, Curve curve, Set<JWSSignerOption> set) throws JOSEException {
        super(ECDSA.resolveAlgorithm(curve));
        if (!"EC".equalsIgnoreCase(privateKey.getAlgorithm())) {
            throw new IllegalArgumentException("The private key algorithm must be EC");
        }
        this.privateKey = privateKey;
        this.opts = set == null ? Collections.emptySet() : set;
    }

    public ECDSASigner(ECKey eCKey) throws JOSEException {
        this(eCKey, (Set<JWSSignerOption>) null);
    }

    public ECDSASigner(ECKey eCKey, Set<JWSSignerOption> set) throws JOSEException {
        super(ECDSA.resolveAlgorithm(eCKey.getCurve()));
        if (!eCKey.isPrivate()) {
            throw new JOSEException("The EC JWK doesn't contain a private part");
        }
        this.privateKey = eCKey.toPrivateKey();
        this.opts = set == null ? Collections.emptySet() : set;
    }

    @Override // com.nimbusds.jose.JWSSigner
    public Base64URL sign(final JWSHeader jWSHeader, final byte[] bArr) throws SignatureException, JOSEException, InvalidKeyException {
        JWSAlgorithm algorithm = jWSHeader.getAlgorithm();
        if (!supportedJWSAlgorithms().contains(algorithm)) {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(algorithm, supportedJWSAlgorithms()));
        }
        try {
            final Signature signerAndVerifier = ECDSA.getSignerAndVerifier(algorithm, getJCAContext().getProvider());
            signerAndVerifier.initSign(this.privateKey, getJCAContext().getSecureRandom());
            if (OptionUtils.optionIsPresent(this.opts, UserAuthenticationRequired.class)) {
                throw new ActionRequiredForJWSCompletionException("Authenticate user to complete signing", UserAuthenticationRequired.getInstance(), new CompletableJWSObjectSigning() { // from class: com.nimbusds.jose.crypto.ECDSASigner.1
                    @Override // com.nimbusds.jose.CompletableJWSObjectSigning
                    public Signature getInitializedSignature() {
                        return signerAndVerifier;
                    }

                    @Override // com.nimbusds.jose.CompletableJWSObjectSigning
                    public Base64URL complete() throws SignatureException, JOSEException {
                        try {
                            signerAndVerifier.update(bArr);
                            return Base64URL.encode(ECDSA.transcodeSignatureToConcat(signerAndVerifier.sign(), ECDSA.getSignatureByteArrayLength(jWSHeader.getAlgorithm())));
                        } catch (SignatureException e) {
                            throw new JOSEException(e.getMessage(), e);
                        }
                    }
                });
            }
            signerAndVerifier.update(bArr);
            return Base64URL.encode(ECDSA.transcodeSignatureToConcat(signerAndVerifier.sign(), ECDSA.getSignatureByteArrayLength(jWSHeader.getAlgorithm())));
        } catch (InvalidKeyException | SignatureException e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }
}
