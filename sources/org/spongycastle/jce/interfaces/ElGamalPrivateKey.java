package org.spongycastle.jce.interfaces;

import java.math.BigInteger;
import javax.crypto.interfaces.DHPrivateKey;

/* loaded from: classes7.dex */
public interface ElGamalPrivateKey extends ElGamalKey, DHPrivateKey {
    BigInteger getX();
}
