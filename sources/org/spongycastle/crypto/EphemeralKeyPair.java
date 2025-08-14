package org.spongycastle.crypto;

/* loaded from: classes.dex */
public class EphemeralKeyPair {
    private AsymmetricCipherKeyPair keyPair;
    private KeyEncoder publicKeyEncoder;

    public AsymmetricCipherKeyPair getKeyPair() {
        return this.keyPair;
    }

    public EphemeralKeyPair(AsymmetricCipherKeyPair asymmetricCipherKeyPair, KeyEncoder keyEncoder) {
        this.keyPair = asymmetricCipherKeyPair;
        this.publicKeyEncoder = keyEncoder;
    }

    public byte[] getEncodedPublicKey() {
        return this.publicKeyEncoder.getEncoded(this.keyPair.getPublic());
    }
}
