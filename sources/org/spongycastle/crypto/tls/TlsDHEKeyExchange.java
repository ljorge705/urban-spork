package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.util.io.TeeInputStream;

/* loaded from: classes7.dex */
public class TlsDHEKeyExchange extends TlsDHKeyExchange {
    protected TlsSignerCredentials serverCredentials;

    public TlsDHEKeyExchange(int i, Vector vector, DHParameters dHParameters) {
        super(i, vector, dHParameters);
        this.serverCredentials = null;
    }

    @Override // org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (!(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert((short) 80);
        }
        processServerCertificate(tlsCredentials.getCertificate());
        this.serverCredentials = (TlsSignerCredentials) tlsCredentials;
    }

    @Override // org.spongycastle.crypto.tls.TlsDHKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() throws IOException {
        if (this.dhParameters == null) {
            throw new TlsFatalAlert((short) 80);
        }
        DigestInputBuffer digestInputBuffer = new DigestInputBuffer();
        this.dhAgreePrivateKey = TlsDHUtils.generateEphemeralServerKeyExchange(this.context.getSecureRandom(), this.dhParameters, digestInputBuffer);
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(this.context, this.serverCredentials);
        Digest digestCreateHash = TlsUtils.createHash(signatureAndHashAlgorithm);
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        digestCreateHash.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        digestCreateHash.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        digestInputBuffer.updateDigest(digestCreateHash);
        byte[] bArr = new byte[digestCreateHash.getDigestSize()];
        digestCreateHash.doFinal(bArr, 0);
        new DigitallySigned(signatureAndHashAlgorithm, this.serverCredentials.generateCertificateSignature(bArr)).encode(digestInputBuffer);
        return digestInputBuffer.toByteArray();
    }

    @Override // org.spongycastle.crypto.tls.TlsDHKeyExchange, org.spongycastle.crypto.tls.AbstractTlsKeyExchange, org.spongycastle.crypto.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) throws IOException {
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        SignerInputBuffer signerInputBuffer = new SignerInputBuffer();
        ServerDHParams serverDHParams = ServerDHParams.parse(new TeeInputStream(inputStream, signerInputBuffer));
        DigitallySigned signature = parseSignature(inputStream);
        Signer signerInitVerifyer = initVerifyer(this.tlsSigner, signature.getAlgorithm(), securityParameters);
        signerInputBuffer.updateSigner(signerInitVerifyer);
        if (!signerInitVerifyer.verifySignature(signature.getSignature())) {
            throw new TlsFatalAlert((short) 51);
        }
        this.dhAgreePublicKey = TlsDHUtils.validateDHPublicKey(serverDHParams.getPublicKey());
        this.dhParameters = validateDHParameters(this.dhAgreePublicKey.getParameters());
    }

    protected Signer initVerifyer(TlsSigner tlsSigner, SignatureAndHashAlgorithm signatureAndHashAlgorithm, SecurityParameters securityParameters) {
        Signer signerCreateVerifyer = tlsSigner.createVerifyer(signatureAndHashAlgorithm, this.serverPublicKey);
        signerCreateVerifyer.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        signerCreateVerifyer.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        return signerCreateVerifyer;
    }
}
