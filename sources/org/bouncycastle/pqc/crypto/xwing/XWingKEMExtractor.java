package org.bouncycastle.pqc.crypto.xwing;

import org.bouncycastle.crypto.EncapsulatedSecretExtractor;
import org.bouncycastle.crypto.agreement.X25519Agreement;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKEMExtractor;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* loaded from: classes4.dex */
public class XWingKEMExtractor implements EncapsulatedSecretExtractor {
    private final KyberKEMExtractor kemExtractor;
    private final XWingPrivateKeyParameters key;

    public XWingKEMExtractor(XWingPrivateKeyParameters xWingPrivateKeyParameters) {
        this.key = xWingPrivateKeyParameters;
        this.kemExtractor = new KyberKEMExtractor(xWingPrivateKeyParameters.getKyberPrivateKey());
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public byte[] extractSecret(byte[] bArr) {
        byte[] bArrExtractSecret = this.kemExtractor.extractSecret(Arrays.copyOfRange(bArr, 0, bArr.length - 32));
        X25519Agreement x25519Agreement = new X25519Agreement();
        int length = bArrExtractSecret.length + x25519Agreement.getAgreementSize();
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArrExtractSecret, 0, bArr2, 0, bArrExtractSecret.length);
        Arrays.clear(bArrExtractSecret);
        x25519Agreement.init(this.key.getXDHPrivateKey());
        X25519PublicKeyParameters x25519PublicKeyParameters = new X25519PublicKeyParameters(Arrays.copyOfRange(bArr, bArr.length - 32, bArr.length));
        x25519Agreement.calculateAgreement(x25519PublicKeyParameters, bArr2, bArrExtractSecret.length);
        SHA3Digest sHA3Digest = new SHA3Digest(256);
        sHA3Digest.update(Strings.toByteArray("\\.//^\\"), 0, 6);
        sHA3Digest.update(bArr2, 0, length);
        sHA3Digest.update(x25519PublicKeyParameters.getEncoded(), 0, 32);
        sHA3Digest.update(this.key.getXDHPrivateKey().generatePublicKey().getEncoded(), 0, 32);
        byte[] bArr3 = new byte[32];
        sHA3Digest.doFinal(bArr3, 0);
        return bArr3;
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public int getEncapsulationLength() {
        return this.kemExtractor.getEncapsulationLength() + 32;
    }
}
