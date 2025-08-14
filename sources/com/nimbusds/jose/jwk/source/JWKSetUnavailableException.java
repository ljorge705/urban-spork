package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.KeySourceException;

/* loaded from: classes2.dex */
public class JWKSetUnavailableException extends KeySourceException {
    private static final long serialVersionUID = 1;

    public JWKSetUnavailableException(String str) {
        super(str);
    }

    public JWKSetUnavailableException(String str, Throwable th) {
        super(str, th);
    }
}
