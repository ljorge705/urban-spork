package org.spongycastle.cms.jcajce;

import java.io.IOException;
import org.spongycastle.asn1.cms.ecc.ECCCMSSharedInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.util.Pack;

/* loaded from: classes4.dex */
class RFC5753KeyMaterialGenerator implements KeyMaterialGenerator {
    RFC5753KeyMaterialGenerator() {
    }

    @Override // org.spongycastle.cms.jcajce.KeyMaterialGenerator
    public byte[] generateKDFMaterial(AlgorithmIdentifier algorithmIdentifier, int i, byte[] bArr) {
        try {
            return new ECCCMSSharedInfo(algorithmIdentifier, bArr, Pack.intToBigEndian(i)).getEncoded("DER");
        } catch (IOException e) {
            throw new IllegalStateException("Unable to create KDF material: " + e);
        }
    }
}
