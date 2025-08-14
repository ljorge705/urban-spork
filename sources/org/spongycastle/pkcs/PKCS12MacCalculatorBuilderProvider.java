package org.spongycastle.pkcs;

import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes7.dex */
public interface PKCS12MacCalculatorBuilderProvider {
    PKCS12MacCalculatorBuilder get(AlgorithmIdentifier algorithmIdentifier);
}
