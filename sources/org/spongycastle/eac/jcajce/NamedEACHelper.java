package org.spongycastle.eac.jcajce;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/* loaded from: classes7.dex */
class NamedEACHelper implements EACHelper {
    private final String providerName;

    NamedEACHelper(String str) {
        this.providerName = str;
    }

    @Override // org.spongycastle.eac.jcajce.EACHelper
    public KeyFactory createKeyFactory(String str) throws NoSuchAlgorithmException, NoSuchProviderException {
        return KeyFactory.getInstance(str, this.providerName);
    }
}
