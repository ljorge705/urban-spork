package com.nimbusds.jose.shaded.gson.internal.bind;

import com.nimbusds.jose.shaded.gson.TypeAdapter;

/* loaded from: classes2.dex */
public abstract class SerializationDelegatingTypeAdapter<T> extends TypeAdapter<T> {
    public abstract TypeAdapter<T> getSerializationDelegate();
}
