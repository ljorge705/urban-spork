package org.spongycastle.cms;

import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes4.dex */
public interface CMSSignatureEncryptionAlgorithmFinder {
    AlgorithmIdentifier findEncryptionAlgorithm(AlgorithmIdentifier algorithmIdentifier);
}
