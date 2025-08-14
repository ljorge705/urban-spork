package com.drew.metadata;

import com.drew.lang.CompoundException;

/* loaded from: classes5.dex */
public class MetadataException extends CompoundException {
    private static final long serialVersionUID = 8612756143363919682L;

    public MetadataException(String str) {
        super(str);
    }

    public MetadataException(Throwable th) {
        super(th);
    }

    public MetadataException(String str, Throwable th) {
        super(str, th);
    }
}
