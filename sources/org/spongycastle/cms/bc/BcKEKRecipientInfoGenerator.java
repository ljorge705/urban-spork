package org.spongycastle.cms.bc;

import org.spongycastle.asn1.cms.KEKIdentifier;
import org.spongycastle.cms.KEKRecipientInfoGenerator;
import org.spongycastle.operator.bc.BcSymmetricKeyWrapper;

/* loaded from: classes4.dex */
public class BcKEKRecipientInfoGenerator extends KEKRecipientInfoGenerator {
    public BcKEKRecipientInfoGenerator(KEKIdentifier kEKIdentifier, BcSymmetricKeyWrapper bcSymmetricKeyWrapper) {
        super(kEKIdentifier, bcSymmetricKeyWrapper);
    }

    public BcKEKRecipientInfoGenerator(byte[] bArr, BcSymmetricKeyWrapper bcSymmetricKeyWrapper) {
        this(new KEKIdentifier(bArr, null, null), bcSymmetricKeyWrapper);
    }
}
