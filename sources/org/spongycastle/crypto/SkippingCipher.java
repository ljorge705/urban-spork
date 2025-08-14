package org.spongycastle.crypto;

/* loaded from: classes.dex */
public interface SkippingCipher {
    long getPosition();

    long seekTo(long j);

    long skip(long j);
}
