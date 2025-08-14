package com.nimbusds.jwt;

/* loaded from: classes2.dex */
public interface JWTClaimsSetTransformer<T> {
    T transform(JWTClaimsSet jWTClaimsSet);
}
