package org.spongycastle.operator.bc;

import org.spongycastle.crypto.engines.AESWrapEngine;
import org.spongycastle.crypto.params.KeyParameter;

/* loaded from: classes7.dex */
public class BcAESSymmetricKeyUnwrapper extends BcSymmetricKeyUnwrapper {
    public BcAESSymmetricKeyUnwrapper(KeyParameter keyParameter) {
        super(AESUtil.determineKeyEncAlg(keyParameter), new AESWrapEngine(), keyParameter);
    }
}
