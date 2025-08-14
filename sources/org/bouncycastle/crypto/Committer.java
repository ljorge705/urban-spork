package org.bouncycastle.crypto;

/* loaded from: classes4.dex */
public interface Committer {
    Commitment commit(byte[] bArr);

    boolean isRevealed(Commitment commitment, byte[] bArr);
}
