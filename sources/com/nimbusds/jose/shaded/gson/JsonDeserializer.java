package com.nimbusds.jose.shaded.gson;

import java.lang.reflect.Type;

/* loaded from: classes2.dex */
public interface JsonDeserializer<T> {
    T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException;
}
