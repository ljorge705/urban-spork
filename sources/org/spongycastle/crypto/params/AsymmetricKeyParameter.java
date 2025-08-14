package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes7.dex */
public class AsymmetricKeyParameter implements CipherParameters {
    boolean privateKey;

    public boolean isPrivate() {
        return this.privateKey;
    }

    public AsymmetricKeyParameter(boolean z) {
        this.privateKey = z;
    }
}
