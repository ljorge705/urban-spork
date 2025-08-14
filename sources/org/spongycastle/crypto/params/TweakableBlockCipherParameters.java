package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.util.Arrays;

/* loaded from: classes7.dex */
public class TweakableBlockCipherParameters implements CipherParameters {
    private final KeyParameter key;
    private final byte[] tweak;

    public KeyParameter getKey() {
        return this.key;
    }

    public byte[] getTweak() {
        return this.tweak;
    }

    public TweakableBlockCipherParameters(KeyParameter keyParameter, byte[] bArr) {
        this.key = keyParameter;
        this.tweak = Arrays.clone(bArr);
    }
}
