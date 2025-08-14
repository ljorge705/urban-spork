package org.spongycastle.operator.bc;

import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.operator.OperatorCreationException;

/* loaded from: classes7.dex */
public interface BcDigestProvider {
    ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException;
}
