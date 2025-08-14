package org.spongycastle.operator;

import java.io.OutputStream;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes7.dex */
public interface MacCalculator {
    AlgorithmIdentifier getAlgorithmIdentifier();

    GenericKey getKey();

    byte[] getMac();

    OutputStream getOutputStream();
}
