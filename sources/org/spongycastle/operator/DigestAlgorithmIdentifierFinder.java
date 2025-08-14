package org.spongycastle.operator;

import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes7.dex */
public interface DigestAlgorithmIdentifierFinder {
    AlgorithmIdentifier find(String str);

    AlgorithmIdentifier find(AlgorithmIdentifier algorithmIdentifier);
}
