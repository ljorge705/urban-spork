package org.bouncycastle.crypto.agreement;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.ECDHUPrivateParameters;
import org.bouncycastle.crypto.params.ECDHUPublicParameters;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes4.dex */
public class ECDHCUnifiedAgreement {
    private ECDHUPrivateParameters privParams;

    public byte[] calculateAgreement(CipherParameters cipherParameters) {
        ECDHUPublicParameters eCDHUPublicParameters = (ECDHUPublicParameters) cipherParameters;
        ECDHCBasicAgreement eCDHCBasicAgreement = new ECDHCBasicAgreement();
        ECDHCBasicAgreement eCDHCBasicAgreement2 = new ECDHCBasicAgreement();
        eCDHCBasicAgreement.init(this.privParams.getStaticPrivateKey());
        BigInteger bigIntegerCalculateAgreement = eCDHCBasicAgreement.calculateAgreement(eCDHUPublicParameters.getStaticPublicKey());
        eCDHCBasicAgreement2.init(this.privParams.getEphemeralPrivateKey());
        BigInteger bigIntegerCalculateAgreement2 = eCDHCBasicAgreement2.calculateAgreement(eCDHUPublicParameters.getEphemeralPublicKey());
        int fieldSize = getFieldSize();
        byte[] bArr = new byte[fieldSize * 2];
        BigIntegers.asUnsignedByteArray(bigIntegerCalculateAgreement2, bArr, 0, fieldSize);
        BigIntegers.asUnsignedByteArray(bigIntegerCalculateAgreement, bArr, fieldSize, fieldSize);
        return bArr;
    }

    public int getFieldSize() {
        return (this.privParams.getStaticPrivateKey().getParameters().getCurve().getFieldSize() + 7) / 8;
    }

    public void init(CipherParameters cipherParameters) {
        ECDHUPrivateParameters eCDHUPrivateParameters = (ECDHUPrivateParameters) cipherParameters;
        this.privParams = eCDHUPrivateParameters;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties("ECCDHU", eCDHUPrivateParameters.getStaticPrivateKey()));
    }
}
