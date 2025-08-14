package org.spongycastle.crypto.prng.drbg;

/* loaded from: classes7.dex */
public interface SP80090DRBG {
    int generate(byte[] bArr, byte[] bArr2, boolean z);

    int getBlockSize();

    void reseed(byte[] bArr);
}
