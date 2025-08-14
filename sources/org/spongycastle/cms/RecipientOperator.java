package org.spongycastle.cms;

import java.io.InputStream;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.operator.InputDecryptor;
import org.spongycastle.operator.MacCalculator;
import org.spongycastle.util.io.TeeInputStream;

/* loaded from: classes4.dex */
public class RecipientOperator {
    private final AlgorithmIdentifier algorithmIdentifier;
    private final Object operator;

    public RecipientOperator(InputDecryptor inputDecryptor) {
        this.algorithmIdentifier = inputDecryptor.getAlgorithmIdentifier();
        this.operator = inputDecryptor;
    }

    public RecipientOperator(MacCalculator macCalculator) {
        this.algorithmIdentifier = macCalculator.getAlgorithmIdentifier();
        this.operator = macCalculator;
    }

    public InputStream getInputStream(InputStream inputStream) {
        Object obj = this.operator;
        if (obj instanceof InputDecryptor) {
            return ((InputDecryptor) obj).getInputStream(inputStream);
        }
        return new TeeInputStream(inputStream, ((MacCalculator) this.operator).getOutputStream());
    }

    public boolean isMacBased() {
        return this.operator instanceof MacCalculator;
    }

    public byte[] getMac() {
        return ((MacCalculator) this.operator).getMac();
    }
}
