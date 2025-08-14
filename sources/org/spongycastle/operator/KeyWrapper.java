package org.spongycastle.operator;

import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes7.dex */
public interface KeyWrapper {
    byte[] generateWrappedKey(GenericKey genericKey) throws OperatorException;

    AlgorithmIdentifier getAlgorithmIdentifier();
}
