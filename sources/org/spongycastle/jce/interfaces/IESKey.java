package org.spongycastle.jce.interfaces;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

/* loaded from: classes7.dex */
public interface IESKey extends Key {
    PrivateKey getPrivate();

    PublicKey getPublic();
}
