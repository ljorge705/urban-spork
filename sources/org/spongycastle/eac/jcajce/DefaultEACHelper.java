package org.spongycastle.eac.jcajce;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes7.dex */
class DefaultEACHelper implements EACHelper {
    DefaultEACHelper() {
    }

    @Override // org.spongycastle.eac.jcajce.EACHelper
    public KeyFactory createKeyFactory(String str) throws NoSuchAlgorithmException {
        return KeyFactory.getInstance(str);
    }
}
