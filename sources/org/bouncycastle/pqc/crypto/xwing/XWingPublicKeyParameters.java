package org.bouncycastle.pqc.crypto.xwing;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPublicKeyParameters;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class XWingPublicKeyParameters extends XWingKeyParameters {
    private final KyberPublicKeyParameters kybPub;
    private final X25519PublicKeyParameters xdhPub;

    XWingPublicKeyParameters(AsymmetricKeyParameter asymmetricKeyParameter, AsymmetricKeyParameter asymmetricKeyParameter2) {
        super(false);
        this.kybPub = (KyberPublicKeyParameters) asymmetricKeyParameter;
        this.xdhPub = (X25519PublicKeyParameters) asymmetricKeyParameter2;
    }

    public XWingPublicKeyParameters(byte[] bArr) {
        super(false);
        this.kybPub = new KyberPublicKeyParameters(KyberParameters.kyber768, Arrays.copyOfRange(bArr, 0, bArr.length - 32));
        this.xdhPub = new X25519PublicKeyParameters(bArr, bArr.length - 32);
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(this.kybPub.getEncoded(), this.xdhPub.getEncoded());
    }

    KyberPublicKeyParameters getKyberPublicKey() {
        return this.kybPub;
    }

    X25519PublicKeyParameters getXDHPublicKey() {
        return this.xdhPub;
    }
}
