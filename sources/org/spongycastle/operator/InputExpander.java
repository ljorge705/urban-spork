package org.spongycastle.operator;

import java.io.InputStream;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes7.dex */
public interface InputExpander {
    AlgorithmIdentifier getAlgorithmIdentifier();

    InputStream getInputStream(InputStream inputStream);
}
