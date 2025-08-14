package com.nimbusds.jose.crypto;

import com.nimbusds.jose.ActionRequiredForJWSCompletionException;
import com.nimbusds.jose.CompletableJWSObjectSigning;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSSignerOption;
import com.nimbusds.jose.crypto.impl.RSAKeyUtils;
import com.nimbusds.jose.crypto.impl.RSASSA;
import com.nimbusds.jose.crypto.impl.RSASSAProvider;
import com.nimbusds.jose.crypto.opts.AllowWeakRSAKey;
import com.nimbusds.jose.crypto.opts.OptionUtils;
import com.nimbusds.jose.crypto.opts.UserAuthenticationRequired;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.Base64URL;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.util.Collections;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class RSASSASigner extends RSASSAProvider implements JWSSigner {
    private final Set<JWSSignerOption> opts;
    private final PrivateKey privateKey;

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public RSASSASigner(PrivateKey privateKey) {
        this(privateKey, false);
    }

    @Deprecated
    public RSASSASigner(PrivateKey privateKey, boolean z) {
        this(privateKey, (Set<JWSSignerOption>) (z ? Collections.singleton(AllowWeakRSAKey.getInstance()) : Collections.emptySet()));
    }

    public RSASSASigner(PrivateKey privateKey, Set<JWSSignerOption> set) {
        int iKeyBitLength;
        if (!(privateKey instanceof RSAPrivateKey) && !"RSA".equalsIgnoreCase(privateKey.getAlgorithm())) {
            throw new IllegalArgumentException("The private key algorithm must be RSA");
        }
        this.privateKey = privateKey;
        set = set == null ? Collections.emptySet() : set;
        this.opts = set;
        if (!OptionUtils.optionIsPresent(set, AllowWeakRSAKey.class) && (iKeyBitLength = RSAKeyUtils.keyBitLength(privateKey)) > 0 && iKeyBitLength < 2048) {
            throw new IllegalArgumentException("The RSA key size must be at least 2048 bits");
        }
    }

    public RSASSASigner(RSAKey rSAKey) throws JOSEException {
        this(rSAKey, (Set<JWSSignerOption>) null);
    }

    @Deprecated
    public RSASSASigner(RSAKey rSAKey, boolean z) throws JOSEException {
        this(RSAKeyUtils.toRSAPrivateKey(rSAKey), z);
    }

    public RSASSASigner(RSAKey rSAKey, Set<JWSSignerOption> set) throws JOSEException {
        this(RSAKeyUtils.toRSAPrivateKey(rSAKey), set);
    }

    @Override // com.nimbusds.jose.JWSSigner
    public Base64URL sign(JWSHeader jWSHeader, final byte[] bArr) throws JOSEException, InvalidKeyException {
        final Signature initiatedSignature = getInitiatedSignature(jWSHeader);
        if (OptionUtils.optionIsPresent(this.opts, UserAuthenticationRequired.class)) {
            throw new ActionRequiredForJWSCompletionException("Authenticate user to complete signing", UserAuthenticationRequired.getInstance(), new CompletableJWSObjectSigning() { // from class: com.nimbusds.jose.crypto.RSASSASigner.1
                @Override // com.nimbusds.jose.CompletableJWSObjectSigning
                public Signature getInitializedSignature() {
                    return initiatedSignature;
                }

                @Override // com.nimbusds.jose.CompletableJWSObjectSigning
                public Base64URL complete() throws JOSEException {
                    return RSASSASigner.this.sign(bArr, initiatedSignature);
                }
            });
        }
        return sign(bArr, initiatedSignature);
    }

    private Signature getInitiatedSignature(JWSHeader jWSHeader) throws JOSEException, InvalidKeyException {
        Signature signerAndVerifier = RSASSA.getSignerAndVerifier(jWSHeader.getAlgorithm(), getJCAContext().getProvider());
        try {
            signerAndVerifier.initSign(this.privateKey);
            return signerAndVerifier;
        } catch (InvalidKeyException e) {
            throw new JOSEException("Invalid private RSA key: " + e.getMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Base64URL sign(byte[] bArr, Signature signature) throws SignatureException, JOSEException {
        try {
            signature.update(bArr);
            return Base64URL.encode(signature.sign());
        } catch (SignatureException e) {
            throw new JOSEException("RSA signature exception: " + e.getMessage(), e);
        }
    }
}
