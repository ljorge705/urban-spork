package com.nimbusds.jose.shaded.gson;

import com.nimbusds.jose.shaded.gson.reflect.TypeToken;

/* loaded from: classes2.dex */
public interface TypeAdapterFactory {
    <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken);
}
