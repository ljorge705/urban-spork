package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.PrivateKey;

/* loaded from: classes4.dex */
public interface FalconPrivateKey extends PrivateKey, FalconKey {
    FalconPublicKey getPublicKey();
}
