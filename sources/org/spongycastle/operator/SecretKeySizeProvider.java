package org.spongycastle.operator;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes7.dex */
public interface SecretKeySizeProvider {
    int getKeySize(ASN1ObjectIdentifier aSN1ObjectIdentifier);

    int getKeySize(AlgorithmIdentifier algorithmIdentifier);
}
