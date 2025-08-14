package org.spongycastle.operator;

import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes7.dex */
public interface KeyUnwrapper {
    GenericKey generateUnwrappedKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) throws OperatorException;

    AlgorithmIdentifier getAlgorithmIdentifier();
}
