package org.spongycastle.pqc.jcajce.interfaces;

import java.security.PrivateKey;

/* loaded from: classes7.dex */
public interface NHPrivateKey extends NHKey, PrivateKey {
    short[] getSecretData();
}
