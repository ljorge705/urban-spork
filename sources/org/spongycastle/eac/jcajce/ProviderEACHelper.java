package org.spongycastle.eac.jcajce;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

/* loaded from: classes7.dex */
class ProviderEACHelper implements EACHelper {
    private final Provider provider;

    ProviderEACHelper(Provider provider) {
        this.provider = provider;
    }

    @Override // org.spongycastle.eac.jcajce.EACHelper
    public KeyFactory createKeyFactory(String str) throws NoSuchAlgorithmException {
        return KeyFactory.getInstance(str, this.provider);
    }
}
