package org.spongycastle.eac.operator.jcajce;

import java.security.NoSuchAlgorithmException;
import java.security.Signature;

/* loaded from: classes7.dex */
class DefaultEACHelper extends EACHelper {
    DefaultEACHelper() {
    }

    @Override // org.spongycastle.eac.operator.jcajce.EACHelper
    protected Signature createSignature(String str) throws NoSuchAlgorithmException {
        return Signature.getInstance(str);
    }
}
