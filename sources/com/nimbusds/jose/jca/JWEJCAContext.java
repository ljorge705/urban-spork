package com.nimbusds.jose.jca;

import java.security.Provider;
import java.security.SecureRandom;

/* loaded from: classes2.dex */
public final class JWEJCAContext extends JCAContext {
    private Provider ceProvider;
    private Provider keProvider;
    private Provider macProvider;

    public void setContentEncryptionProvider(Provider provider) {
        this.ceProvider = provider;
    }

    public void setKeyEncryptionProvider(Provider provider) {
        this.keProvider = provider;
    }

    public void setMACProvider(Provider provider) {
        this.macProvider = provider;
    }

    public JWEJCAContext() {
        this(null, null, null, null, null);
    }

    public JWEJCAContext(Provider provider, Provider provider2, Provider provider3, Provider provider4, SecureRandom secureRandom) {
        super(provider, secureRandom);
        this.keProvider = provider2;
        this.ceProvider = provider3;
        this.macProvider = provider4;
    }

    public Provider getKeyEncryptionProvider() {
        Provider provider = this.keProvider;
        return provider != null ? provider : getProvider();
    }

    public Provider getContentEncryptionProvider() {
        Provider provider = this.ceProvider;
        return provider != null ? provider : getProvider();
    }

    public Provider getMACProvider() {
        Provider provider = this.macProvider;
        return provider != null ? provider : getProvider();
    }
}
