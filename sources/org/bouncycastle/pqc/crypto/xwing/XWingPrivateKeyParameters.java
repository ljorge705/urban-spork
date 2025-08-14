package org.bouncycastle.pqc.crypto.xwing;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPrivateKeyParameters;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class XWingPrivateKeyParameters extends XWingKeyParameters {
    private final KyberPrivateKeyParameters kybPriv;
    private final X25519PrivateKeyParameters xdhPriv;

    XWingPrivateKeyParameters(AsymmetricKeyParameter asymmetricKeyParameter, AsymmetricKeyParameter asymmetricKeyParameter2) {
        super(true);
        this.kybPriv = (KyberPrivateKeyParameters) asymmetricKeyParameter;
        this.xdhPriv = (X25519PrivateKeyParameters) asymmetricKeyParameter2;
    }

    public XWingPrivateKeyParameters(byte[] bArr) {
        super(false);
        this.kybPriv = new KyberPrivateKeyParameters(KyberParameters.kyber768, Arrays.copyOfRange(bArr, 0, bArr.length - 32));
        this.xdhPriv = new X25519PrivateKeyParameters(bArr, bArr.length - 32);
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(this.kybPriv.getEncoded(), this.xdhPriv.getEncoded());
    }

    KyberPrivateKeyParameters getKyberPrivateKey() {
        return this.kybPriv;
    }

    X25519PrivateKeyParameters getXDHPrivateKey() {
        return this.xdhPriv;
    }
}
