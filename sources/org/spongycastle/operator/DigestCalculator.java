package org.spongycastle.operator;

import java.io.OutputStream;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes7.dex */
public interface DigestCalculator {
    AlgorithmIdentifier getAlgorithmIdentifier();

    byte[] getDigest();

    OutputStream getOutputStream();
}
