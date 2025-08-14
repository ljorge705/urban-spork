package org.spongycastle.operator;

import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes7.dex */
public class GenericKey {
    private AlgorithmIdentifier algorithmIdentifier;
    private Object representation;

    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.algorithmIdentifier;
    }

    public Object getRepresentation() {
        return this.representation;
    }

    public GenericKey(Object obj) {
        this.algorithmIdentifier = null;
        this.representation = obj;
    }

    public GenericKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        this.algorithmIdentifier = algorithmIdentifier;
        this.representation = bArr;
    }

    protected GenericKey(AlgorithmIdentifier algorithmIdentifier, Object obj) {
        this.algorithmIdentifier = algorithmIdentifier;
        this.representation = obj;
    }
}
