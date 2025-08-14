package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.MultiBlockCipher;
import org.bouncycastle.crypto.SkippingStreamCipher;

/* loaded from: classes4.dex */
public interface CTRModeCipher extends MultiBlockCipher, SkippingStreamCipher {
    BlockCipher getUnderlyingCipher();
}
