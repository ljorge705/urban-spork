package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes7.dex */
public class AEADParameters implements CipherParameters {
    private byte[] associatedText;
    private KeyParameter key;
    private int macSize;
    private byte[] nonce;

    public byte[] getAssociatedText() {
        return this.associatedText;
    }

    public KeyParameter getKey() {
        return this.key;
    }

    public int getMacSize() {
        return this.macSize;
    }

    public byte[] getNonce() {
        return this.nonce;
    }

    public AEADParameters(KeyParameter keyParameter, int i, byte[] bArr) {
        this(keyParameter, i, bArr, null);
    }

    public AEADParameters(KeyParameter keyParameter, int i, byte[] bArr, byte[] bArr2) {
        this.key = keyParameter;
        this.nonce = bArr;
        this.macSize = i;
        this.associatedText = bArr2;
    }
}
