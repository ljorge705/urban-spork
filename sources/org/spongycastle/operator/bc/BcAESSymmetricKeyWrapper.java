package org.spongycastle.operator.bc;

import org.spongycastle.crypto.engines.AESWrapEngine;
import org.spongycastle.crypto.params.KeyParameter;

/* loaded from: classes7.dex */
public class BcAESSymmetricKeyWrapper extends BcSymmetricKeyWrapper {
    public BcAESSymmetricKeyWrapper(KeyParameter keyParameter) {
        super(AESUtil.determineKeyEncAlg(keyParameter), new AESWrapEngine(), keyParameter);
    }
}
