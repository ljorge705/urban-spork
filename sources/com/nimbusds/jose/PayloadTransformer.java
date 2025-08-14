package com.nimbusds.jose;

/* loaded from: classes2.dex */
public interface PayloadTransformer<T> {
    T transform(Payload payload);
}
