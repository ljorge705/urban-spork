package org.spongycastle.crypto;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes.dex */
public interface KeyEncoder {
    byte[] getEncoded(AsymmetricKeyParameter asymmetricKeyParameter);
}
