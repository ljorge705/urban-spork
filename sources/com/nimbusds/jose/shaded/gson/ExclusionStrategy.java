package com.nimbusds.jose.shaded.gson;

/* loaded from: classes2.dex */
public interface ExclusionStrategy {
    boolean shouldSkipClass(Class<?> cls);

    boolean shouldSkipField(FieldAttributes fieldAttributes);
}
