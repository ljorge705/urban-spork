package org.spongycastle.cms.bc;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.operator.GenericKey;

/* loaded from: classes4.dex */
class CMSUtils {
    CMSUtils() {
    }

    static CipherParameters getBcKey(GenericKey genericKey) {
        if (genericKey.getRepresentation() instanceof CipherParameters) {
            return (CipherParameters) genericKey.getRepresentation();
        }
        if (genericKey.getRepresentation() instanceof byte[]) {
            return new KeyParameter((byte[]) genericKey.getRepresentation());
        }
        throw new IllegalArgumentException("unknown generic key type");
    }
}
