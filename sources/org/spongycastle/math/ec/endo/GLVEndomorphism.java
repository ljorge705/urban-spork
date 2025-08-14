package org.spongycastle.math.ec.endo;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public interface GLVEndomorphism extends ECEndomorphism {
    BigInteger[] decomposeScalar(BigInteger bigInteger);
}
