package org.bouncycastle.crypto;

/* loaded from: classes4.dex */
public interface EncapsulatedSecretExtractor {
    byte[] extractSecret(byte[] bArr);

    int getEncapsulationLength();
}
