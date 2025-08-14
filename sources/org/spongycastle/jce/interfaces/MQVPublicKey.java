package org.spongycastle.jce.interfaces;

import java.security.PublicKey;

/* loaded from: classes7.dex */
public interface MQVPublicKey extends PublicKey {
    PublicKey getEphemeralKey();

    PublicKey getStaticKey();
}
