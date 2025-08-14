package org.spongycastle.operator.bc;

import java.security.Key;
import org.spongycastle.operator.GenericKey;

/* loaded from: classes7.dex */
class OperatorUtils {
    OperatorUtils() {
    }

    static byte[] getKeyBytes(GenericKey genericKey) {
        if (genericKey.getRepresentation() instanceof Key) {
            return ((Key) genericKey.getRepresentation()).getEncoded();
        }
        if (genericKey.getRepresentation() instanceof byte[]) {
            return (byte[]) genericKey.getRepresentation();
        }
        throw new IllegalArgumentException("unknown generic key type");
    }
}
