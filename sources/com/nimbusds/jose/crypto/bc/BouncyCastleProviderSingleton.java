package com.nimbusds.jose.crypto.bc;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* loaded from: classes2.dex */
public final class BouncyCastleProviderSingleton {
    private static BouncyCastleProvider bouncyCastleProvider;

    private BouncyCastleProviderSingleton() {
    }

    public static BouncyCastleProvider getInstance() {
        if (bouncyCastleProvider == null) {
            bouncyCastleProvider = new BouncyCastleProvider();
        }
        return bouncyCastleProvider;
    }
}
